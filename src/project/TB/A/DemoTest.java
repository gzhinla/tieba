package project.TB.A;

import static project.TB.A.BigMenu.USER;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

public class DemoTest {
	
	static Vector rowData;
	static Vector columnNames;
	@Test
	public void testInsert() {
		JFrame jf = new JFrame("�����û�������");
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// Commentt COM;
		// Artical ART;
		JPanel jp = new JPanel();
		A2 sq = new A2();// �����������ݿ⺯��

		// ���� ������壬ʹ�� �߽粼��
		JPanel panel = new JPanel(new BorderLayout());

		// ���� һ��������ʵ��
		JToolBar toolBar = new JToolBar("������");

		// ���� ��������ť
		JButton previousBtn = new JButton("�������");
		JButton pauseBtn = new JButton("��Ҫ����");
		JButton myinfoBtn = new JButton("��������");

		// ��� ��ť �� ������
		toolBar.add(previousBtn);
		toolBar.add(pauseBtn);
		toolBar.add(myinfoBtn);

		columnNames = new Vector();
		columnNames.add("����");
		columnNames.add("������");
		columnNames.add("������");
		rowData = new Vector();
		Connection ct = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		int a = 0;
		try {
			Class.forName(sq.driverName);
			ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
			ps = ct.prepareStatement("select username,topic,postcontent from ���ӱ�");
			ResultSet rs = ps.executeQuery();
			// ART = new Artical(rs.getString(2), rs.getString(3),
			// rs.getString(4),rs.getString(2));
			while (rs.next()) {
				// rowData���Դ�Ŷ���
				Vector hang = new Vector();
				for (int i = 1; i < 4; i++)
					hang.add(rs.getString(i));
				// ���뵽rowData
				rowData.add(hang);
			}
			ps = ct.prepareStatement("select count(*) as id from ���ӱ�");
			ResultSet rs1 = ps.executeQuery();     //ͳ����������
			if (rs1.next()) {
				a = rs1.getInt("id");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		System.out.println("��������" + a + "����");    //ͳ����������
		final JTextArea textArea = new JTextArea();
		JTable jt = new JTable(rowData, columnNames);
		DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
		// ��ʼ�� jsp
		jt.setPreferredScrollableViewportSize(new Dimension(400, 300));
		JScrollPane jsp = new JScrollPane(jt);

		// ����һ���ı�����������������Ϣ
		// final JTextArea textArea = new JTextArea();
		// textArea.setLineWrap(true);

		// ��� ��ť �ĵ�����������������������Ϣ���뵽 �ı�����
		previousBtn.addActionListener(new ActionListener() {   //   �������
			@Override
			public void actionPerformed(ActionEvent e) {

				int rowcount = jt.getSelectedRow();
				if (rowcount >= 0) {
					String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();   //aΪ��һ����������
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();   //bΪ�ڶ�����������
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();   //cΪ��������������
					// COM =new Commentt(c,a,b);

					// System.out.println(a.toString());
					JFrame sp = new JFrame("�������");
					sp.setSize(500, 500);
					sp.setLocation(400, 200);
					JLabel laba = new JLabel(b);
					JLabel labb = new JLabel("����:  " + a);
					JTextArea jta = new JTextArea(c);
					jta.setLineWrap(true);
					laba.setFont(new Font("����", Font.BOLD, 22));
					JPanel jpa = new JPanel();
					JButton mylike = new JButton("��ע");
					JButton myfavor = new JButton("�ղ�");
					JButton comment = new JButton("����");

					mylike.setBounds(60, 360, 80, 25);
					jpa.add(mylike);
					// JOptionPane.showMessageDialog(null, "��ע�����ɹ�");

					myfavor.setBounds(200, 360, 80, 25);
					jpa.add(myfavor);
					// JOptionPane.showMessageDialog(null, "�ղ����ӳɹ�");

					comment.setBounds(340, 360, 80, 25);
					jpa.add(comment);

					sp.add(jpa);
					jpa.setLayout(null);
					jpa.add(laba);
					jpa.add(labb);
					jpa.add(jta);
					laba.setBounds(130, 10, 200, 20);
					labb.setBounds(30, 25, 100, 20);
					jta.setBounds(30, 60, 410, 250);
					sp.setVisible(true);
					// sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mylike.addActionListener(new ActionListener() {     //��ע����  ����

						public void actionPerformed(ActionEvent e) {
							Connection ct = null;
							PreparedStatement ps1 = null;
							try {
								Class.forName(sq.driverName);
								ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
								String sql4 = "UPDATE �����û��� SET mylike=? WHERE Unumber=?";
								ps1 = ct.prepareStatement(sql4);
								ps1.setString(1, a);
								ps1.setString(2, USER.getuNumber());
								ps1.executeUpdate();    //����¼�û��Ĺ�ע��Ϊ���¹�ע
								JOptionPane.showMessageDialog(null, "��ע�����ɹ�");
							} catch (Exception e1) {
								e1.printStackTrace();
								System.exit(0);
							}

						}
					});
					myfavor.addActionListener(new ActionListener() {     //�ղ����ӹ���
						@Override
						public void actionPerformed(ActionEvent e) {
							Connection ct = null;
							PreparedStatement ps1 = null;
							try {
								Class.forName(sq.driverName);
								ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
								String sql3 = "INSERT INTO �û��ղؼб� (postcontent,topic,username) VALUES (?,?,?)";
								ps1 = ct.prepareStatement(sql3);// Ԥ����SQL������sqlִ��
								ps1.setString(1, c);// ��ֵ��䣬����һ������ֵs1
								ps1.setString(2, b);
								ps1.setString(3, USER.getUserName());
								ps1.executeUpdate();       //���ղص����Ӽ��뵽���ݿ��û��ղر���
								JOptionPane.showMessageDialog(null, "�����ղسɹ�");
							} catch (Exception e1) {
								e1.printStackTrace();
								System.exit(0);
							}
						}
					});
					comment.addActionListener(new ActionListener() {       //������ۺͷ������۽���
						@Override
						public void actionPerformed(ActionEvent e) {
							// Comment com=new Comment();
							// com.comment();
							JFrame sp = new JFrame("������");
							sp.setSize(500, 500);
							sp.setLocation(400, 200);
							JTextField text = new JTextField(30);
							JLabel myc = new JLabel("�������۰ɣ�");
							JTextArea jta = new JTextArea();
							jta.setLineWrap(true);
							JPanel jpa = new JPanel();
							JButton publish = new JButton("����");
							sp.add(jpa);
							jpa.setLayout(null);
							publish.setBounds(360, 370, 80, 45);// ��ť
							jpa.add(publish);
							jpa.add(text);
							jpa.add(myc);// ����
							jpa.add(jta);
							text.setBounds(10, 370, 320, 45);// ���ۿ�
							myc.setBounds(10, 340, 80, 25);// ����ǰ��
							jta.setBounds(10, 10, 430, 320);// ���������
							sp.setVisible(true);

							Connection ct1 = null;
							PreparedStatement ps1 = null;
							try {
								Class.forName(sq.driverName);
								ct1 = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
								ps1 = ct1.prepareStatement("select username,comcontent from �������۱� WHERE topic=?");
								ps1.setString(1,b);
								ResultSet rs = ps1.executeQuery();    //  �����ǰ������ӵ�����
								while (rs.next()) {
									String username = rs.getString(1);
									String comcontent = rs.getString(2);
									jta.append(username + ":  ");
									jta.append(comcontent + "  \n");
									jpa.repaint();
								}
							} catch (Exception e1) {
								e1.printStackTrace();
								System.exit(0);
							}

							publish.addActionListener(new ActionListener() {
								private int ERROR_MESSAGE;

								public void actionPerformed(ActionEvent e) {
									String s1 = text.getText().toString();// ���ۻ�ȡ
									Connection ct = null;
									PreparedStatement ps1 = null;
									try {
										Class.forName(sq.driverName);
										ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
										if (s1.length() == 0) {
											JOptionPane.showMessageDialog(null, "δ��д����", "����", ERROR_MESSAGE);
										} else {
											String sql3 = "INSERT INTO �������۱� (comcontent,username,topic) VALUES (?,?,?)";
											ps1 = ct.prepareStatement(sql3);// Ԥ����SQL������sqlִ��
											ps1.setString(1, s1);// ��ֵ��䣬����һ������ֵs1
											ps1.setString(2, USER.getUserName());
											ps1.setString(3, b);
											ps1.executeUpdate();     //�������۴��뵽��ǰ���������������
											JOptionPane.showMessageDialog(null, "���۷���ɹ���");
											// WNotice.writeNotice();
											jpa.repaint();
										}
									} catch (Exception e1) {
										System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
										System.exit(0);
									}
								}
							});
						}
					});
				}
			}
		});
		pauseBtn.addActionListener(new ActionListener() {     //������������
			@Override
			public void actionPerformed(ActionEvent e) {
				BuidT build = new BuidT();
				build.build();     //���ý�����������
			}
		});
		myinfoBtn.addActionListener(new ActionListener() {     //�������Ľ���
			@Override
			public void actionPerformed(ActionEvent e) {
				Personal per = new Personal();
				per.person();       //���ø������Ľ��淽��
			}
		});
		// ��� ������ �� ������� �� ����
		panel.add(toolBar, BorderLayout.PAGE_START);
		// ��� �ı����� �� ������� �� �м�
		// panel.add(textArea, BorderLayout.CENTER);
		panel.add(jsp, BorderLayout.CENTER);
		jf.setContentPane(panel);
		jf.setVisible(true);
	}
	
}
