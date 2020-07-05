package project.TB.A;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author ����
 *
 */
public class Manager {        // ����Ա��������
	static Vector rowData;
	static Vector columnNames;
	//String a=null;
	//String b=null;
	//String c=null;
	public void manager() {
		JFrame jf = new JFrame("�����ܱ�");
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel jp = new JPanel();
		A2 sq = new A2();   //�������ݿⷽ��

		// ���� ������壬ʹ�� �߽粼��
		JPanel panel = new JPanel(new BorderLayout());

		// ���� һ��������ʵ��
		JToolBar toolBar = new JToolBar("������");

		// ���� ��������ť
		JButton previousBtn = new JButton("�������");
		JButton pauseBtn = new JButton("ɾ������");
		JButton noticeBtn = new JButton("֪ͨ");

		// ���� ��ť �� ������
		toolBar.add(previousBtn);
		toolBar.add(pauseBtn);
		toolBar.add(noticeBtn);

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
			ps = ct.prepareStatement("select username,topic,postcontent from ���ӱ�");
			ResultSet rs = ps.executeQuery();    //�ڹ���Ա����������ӱ������
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
		JScrollPane jsp = new JScrollPane(jt);
		noticeBtn.addActionListener(new ActionListener() {// ����Ա֪ͨ
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("֪ͨ");
				jf.setSize(600, 400);
				jf.setLocationRelativeTo(null);
				// jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

				// �����ı��������
				JTextArea textArea = new JTextArea();
				textArea.setLineWrap(true); // �Զ�����
				textArea.setFont(new Font(null, Font.PLAIN, 18)); // ��������

				// �����������, ָ��������ʾ����ͼ���(textArea), ��ֱ������һֱ��ʾ, ˮƽ�������Ӳ���ʾ
				JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

				String notice;
				try {
					notice = WNotice.getNotice();     //ͨ���û�����֪ͨ����Ա���������
					textArea.append(notice + "  \n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jf.setContentPane(scrollPane);
				jf.setVisible(true);
				WNotice.DeleteFile();           //����Ա�����֪ͨ���Զ�ɾ��֪ͨ����
			}
		});
		previousBtn.addActionListener(new ActionListener() {// �������
			@Override
			public void actionPerformed(ActionEvent e) {

				int rowcount = jt.getSelectedRow();
				if (rowcount >= 0) {
					String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();     //aΪ�����һ�����ݣ�bΪ����ڶ������ݣ�cΪ�������������
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
					comment.addActionListener(new ActionListener() {      // ���۽���
						@Override
						public void actionPerformed(ActionEvent e) {
							MComment mcom = new MComment();
							mcom.mComment();     //���ù���Ա������۽���
							
						}
					});
				}
			}
		});
		pauseBtn.addActionListener(new ActionListener() {      //ɾ�����ӹ���
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowcount = jt.getSelectedRow();
				if (rowcount >= 0) {
					String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();     //aΪ�����һ�����ݣ�bΪ����ڶ������ݣ�cΪ�������������
					Connection ct = null;
					PreparedStatement ps = null;
					try {
						Class.forName(sq.driverName);
						ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
						String sql4 = "delete from ���ӱ� WHERE username=?";
						ps = ct.prepareStatement(sql4);
						ps.setString(1, a);     //ɾ��ѡ������
						ps.executeUpdate();
					} catch (Exception e1) {
						e1.printStackTrace();
						System.exit(0);
					}
					tableModel.removeRow(rowcount);   //�ӽ���������Ƴ�����
				}
			}
		});

		// ���� ������ �� ������� �� ����
		panel.add(toolBar, BorderLayout.PAGE_START);
		// ���� �ı����� �� ������� �� �м�
		// panel.add(textArea, BorderLayout.CENTER);
		panel.add(jsp, BorderLayout.CENTER);
		jf.setContentPane(panel);
		jf.setVisible(true);
	}

}