package project.TB.A;

import static project.TB.A.BigMenu.USER;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author ����
 *
 */
public class Personal {
	static Vector rowData;
	static Vector columnNames;  //���������Ҫ��
	public void person() {
		A2 sq = new A2();   //�����������ݿ⺯��

		JFrame sp = new JFrame("��������");
		sp.setSize(500, 500);
		sp.setLocation(750, 300);
		JLabel username = new JLabel("�ҵ�������" + USER.getUserName());   // USER.getUserName()����֮ǰ�������ݿ���Ϣ
		JButton mylike = new JButton("��ע");
		JButton notice = new JButton("֪ͨ");
		JButton myfavor = new JButton("�ղؼ�");
		JLabel Unumber = new JLabel("�ҵ��˺ţ�" + USER.getuNumber());   //USER.getuNumber()����֮ǰ�������ݿ���Ϣ
		JButton update = new JButton("�༭");
		JPanel jpa = new JPanel();
		sp.add(jpa);
		jpa.setLayout(null);
		mylike.setBounds(320, 200, 80, 45);// ��ע��ť
		jpa.add(mylike);
		notice.setBounds(200, 200, 80, 45);// ֪ͨ��ť
		jpa.add(notice);
		myfavor.setBounds(80, 200, 80, 45);// �ղؼа�ť
		jpa.add(myfavor);
		update.setBounds(340, 340, 100, 55);// �༭��ť
		jpa.add(update);

		jpa.add(username);
		jpa.add(Unumber);
		username.setBounds(120, 20, 180, 45);
		Unumber.setBounds(120, 90, 180, 45);
		sp.setVisible(true);
		myfavor.addActionListener(new ActionListener() {// �ղؼ�
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("�ղؼ�");
				jf.setSize(500, 500);
				jf.setLocationRelativeTo(null);
				// jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

				JPanel jp = new JPanel();
				A2 sq = new A2();

				// ���� ������壬ʹ�� �߽粼��
				JPanel panel = new JPanel(new BorderLayout());

				// ���� һ��������ʵ��
				JToolBar toolBar = new JToolBar("����");

				// ���� ��������ť
				JButton previousBtn = new JButton("�������");

				// ��� ��ť �� ������
				toolBar.add(previousBtn);
				columnNames = new Vector();
				columnNames.add("����");
				columnNames.add("������");
				columnNames.add("������");
				rowData = new Vector();
				Connection ct = null;
				PreparedStatement ps = null;
				try {
					Class.forName(sq.driverName);
					ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
					ps = ct.prepareStatement("select username,topic,postcontent from �û��ղؼб�");  //�������ݿ����ղؼб����Ϣ
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						// rowData���Դ�Ŷ���
						Vector hang = new Vector();
						for (int i = 1; i < 4; i++)
							hang.add(rs.getString(i));
						// ���뵽rowData
						rowData.add(hang);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(0);
				}
				final JTextArea textArea = new JTextArea();
				JTable jt = new JTable(rowData, columnNames);
				DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
				// ��ʼ�� jsp
				jt.setPreferredScrollableViewportSize(new Dimension(400, 300));
				JScrollPane jsp = new JScrollPane(jt);  // ���ݹ����ʵ��ҳ���»�
				previousBtn.addActionListener(new ActionListener() {// �������
					@Override
					public void actionPerformed(ActionEvent e) {

						int rowcount = jt.getSelectedRow();  // ѡ�����е�һ�У���rowcount��¼����
						if (rowcount >= 0) {
							String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();
							String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();
							String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();  // a,b,c�ֱ𴢴�ѡ������е�����,a����,c������,b�����⡣
							// System.out.println(a.toString());
							JFrame sp = new JFrame("�������");   // ������ӽ���
							sp.setSize(500, 500);
							sp.setLocation(400, 200);
							JLabel laba = new JLabel(b);
							JLabel labb = new JLabel("����:  " + a);
							JTextArea jta = new JTextArea(c);
							jta.setLineWrap(true);
							laba.setFont(new Font("����", Font.BOLD, 22));
							JPanel jpa = new JPanel();
							JButton comment = new JButton("����");

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
							comment.addActionListener(new ActionListener() {  //�������۽��淢����
								@Override
								public void actionPerformed(ActionEvent e) {
									A2 sq = new A2();  //�������ݿ�

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
										ps1 = ct1.prepareStatement("select username,comcontent from �������۱�  WHERE topic=?");
										ps1.setString(1, a);  //�ҵ������Ӷ�Ӧ������
										ResultSet rs = ps1.executeQuery();  //�������ݿ��������۱���Ϣ
										while (rs.next()) {
											String username = rs.getString(1);
											String comcontent = rs.getString(2);   //��ȡ���ݿ��������۱�����
											jta.append(username + ":  ");
											jta.append(comcontent + "  \n");   //�������
											jpa.repaint();
										}
									} catch (Exception e1) {
										e1.printStackTrace();
										System.exit(0);
									}

									publish.addActionListener(new ActionListener() {  //���۷������
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
													ps1.setString(1, s1);   // ��ֵ��䣬����һ������ֵs1��������������
													ps1.setString(2, USER.getUserName());  //���������û�����
													ps1.setString(1, b);    //������ѡ��������۵����ӱ���
													ps1.executeUpdate();
													JOptionPane.showMessageDialog(null, "���۷���ɹ���");
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

				// ��� ������ �� ������� �� ����
				panel.add(toolBar, BorderLayout.PAGE_START);
				// ��� �ı����� �� ������� �� �м�
				// panel.add(textArea, BorderLayout.CENTER);
				panel.add(jsp, BorderLayout.CENTER);
				jf.setContentPane(panel);
				jf.setVisible(true);
			}
		});
		notice.addActionListener(new ActionListener() {// ֪ͨ����
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("֪ͨ");
				jf.setSize(300, 300);
				jf.setLocationRelativeTo(null);
				// jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

				// �����ı��������
				JTextArea textArea = new JTextArea();
				textArea.setLineWrap(true); // �Զ�����
				textArea.setFont(new Font(null, Font.PLAIN, 18)); // ��������

				// �����������, ָ��������ʾ����ͼ���(textArea), ��ֱ������һֱ��ʾ, ˮƽ�������Ӳ���ʾ
				JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

				Connection ct1 = null;
				PreparedStatement ps1 = null;
				try {
					Class.forName(sq.driverName);
					ct1 = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
					ps1 = ct1.prepareStatement("select notice from �����û��� WHERE username=?");
					ps1.setString(1, USER.getUserName());
					ResultSet rs = ps1.executeQuery();   //�������ݿ��е�֪ͨ��Ϣ
					while (rs.next()) {
						String notice = rs.getString(1);
						textArea.append(notice + "  \n");  // ���֪ͨ��Ϣ
						jpa.repaint();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(0);
				}

				jf.setContentPane(scrollPane);
				jf.setVisible(true);
			}
		});

		mylike.addActionListener(new ActionListener() {// ��ע����
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("��ע");
				jf.setSize(300, 300);
				jf.setLocationRelativeTo(null);
				// jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

				// �����ı��������
				JTextArea textArea = new JTextArea();
				textArea.setLineWrap(true); // �Զ�����
				textArea.setFont(new Font(null, Font.PLAIN, 18)); // ��������

				// �����������, ָ��������ʾ����ͼ���(textArea), ��ֱ������һֱ��ʾ, ˮƽ�������Ӳ���ʾ
				JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

				Connection ct1 = null;
				PreparedStatement ps1 = null;
				try {
					Class.forName(sq.driverName);
					ct1 = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
					ps1 = ct1.prepareStatement("select mylike from �����û��� WHERE username=?");
					ps1.setString(1, USER.getUserName());   //�ҵ���ǰ��¼�û��Ĺ�ע
					ResultSet rs = ps1.executeQuery();
					while (rs.next()) {
						String notice = rs.getString(1);
						textArea.append(notice + "  \n");
						jpa.repaint();   //����
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(0);
				}

				jf.setContentPane(scrollPane);
				jf.setVisible(true);
			}
		});
		update.addActionListener(new ActionListener() {// �༭������Ϣ
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("�༭������Ϣ");
				jf.setSize(350, 250);
				jf.setLocation(750, 200);
				JPanel jp = new JPanel();
				jf.add(jp);
				jp.setLayout(null);
				JLabel acc = new JLabel("�������ʺ�");
				JLabel pass = new JLabel("����������");
				jp.add(pass);
				jp.add(acc);
				acc.setBounds(80, 10, 80, 25);
				pass.setBounds(80, 70, 80, 25);
				JTextField text1 = new JTextField();// �ʺ�����
				JPasswordField text2 = new JPasswordField();// ��������
				text1.setBounds(80, 40, 165, 25);
				text2.setBounds(80, 100, 165, 25);
				jp.add(text1);
				jp.add(text2);
				JButton regButton = new JButton("�޸�");// ע�ᰴť
				regButton.setBounds(125, 150, 80, 40);
				jp.add(regButton);
				jf.setVisible(true);
				A2 sql = new A2();
				regButton.addActionListener(new ActionListener() {
					private int ERROR_MESSAGE;

					public void actionPerformed(ActionEvent e) {
						String s1 = text1.getText().toString();// �˺Ż�ȡ
						String s2 = new String(text2.getPassword());// �����ȡ
						Connection c = null;
						PreparedStatement stmt2 = null;
						PreparedStatement stmt4 = null;
						int i;// ��������
						try {
							Class.forName(sql.driverName);
							c = DriverManager.getConnection(sql.dbURL, sql.userName, sql.userPwd);
							if (s1.length() == 0 || s2.length() == 0) {
								JOptionPane.showMessageDialog(null, "������Ϣδ��", "����", ERROR_MESSAGE);
							}
							// String sql1 = "SELECT * FROM �����û��� where username=?";
							// stmt1 = c.prepareStatement(sql1);
							// stmt1.setString(1, USER.getUserName());//
							// setString����ҪĿ���ǰ��ʺ��������Ϣ�Ž�sql1�������ָ�����ݵĲ�ѯ
							// ResultSet rs1 = stmt1.executeQuery();
							// i = rs1.getInt("ID");
							// if (i == 0) {
							else {
								String sql2 = "SELECT * FROM �����û��� where Unumber=?";
								stmt2 = c.prepareStatement(sql2);// Ԥ����SQL������sqlִ��
								stmt2.setString(1, s1);
								ResultSet rs2 = stmt2.executeQuery();// ִ�в�ѯ���
								if (!rs2.next()) {
									JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
									String sql4 = "UPDATE �����û��� SET Unumber=?,password=? WHERE username=?";
									stmt4 = c.prepareStatement(sql4);
									stmt4.setString(1, s1);
									stmt4.setString(2, s2);
									stmt4.setString(3, USER.getUserName());  // �ҵ���ǰ��¼�û������˺Ż��������޸�
									stmt4.executeUpdate();
								} else {
									JOptionPane.showMessageDialog(null, "�˺��Ѿ�����", "����", ERROR_MESSAGE);
								}
							} // }
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
