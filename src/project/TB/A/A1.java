package project.TB.A;

import javax.swing.*;

import project.TB.A.pojo.User;

import java.awt.event.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.sql.*;

/**
 * 
 * @author ����
 *
 */
class BigMenu {         //��¼�����Լ�����

	 // ����Ϊ�������ݿ�
	static final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=�����û���";
	static final String userName = "sa";// ���ݿ��û���
	static final String userPwd = "123456789";// ����

	private JFrame frame;
	private JPanel jPanel = new JPanel();
	private JLabel accout;
	private JLabel password;
	private JLabel showInfo = new JLabel();
	private JTextField text1;
	private JPasswordField text2;
	private JButton loginButton;
	private JButton regButton;
	private JComboBox comBox = new JComboBox();
	private JLabel txt;
	private LayoutManager gridLayout;
	int a;//����������Ƿ���ѡ��
	public static User USER;  //DAO USER��
	public BigMenu() {
		JFrame frame = new JFrame("���ɵ�¼����");

		JLabel accout = new JLabel("�ʺ�");
		JTextField text1 = new JTextField(16);
		JLabel password = new JLabel("����");
		JPasswordField text2 = new JPasswordField(16);
		JButton loginButton = new JButton("��¼");
		JButton regButton = new JButton("ע��");
		JLabel txt = new JLabel("��ѡ���¼���:");

		frame.setSize(370, 280);
		frame.setLocation(750, 300);
		// frame.setResizable(false);
		// jPanel.setLayout(null);
		jPanel.setLayout(null);
		jPanel.add(accout);
		accout.setBounds(70, 40, 80, 25);
		password.setBounds(70, 75, 80, 25);

		frame.add(jPanel);

		// �ʺ�����
		text1.setBounds(120, 40, 165, 25);
		jPanel.add(text1);

		jPanel.add(password);
		// ��������
		text2.setBounds(120, 75, 165, 25);
		jPanel.add(text2);

		// ��¼��ť
		loginButton.setBounds(80, 120, 80, 25);
		jPanel.add(loginButton);

		// ע�ᰴť
		regButton.setBounds(200, 120, 80, 25);
		jPanel.add(regButton);

		txt.setBounds(85, 170, 100, 25);
		jPanel.add(txt);

		comBox.addItem("--��ѡ��--"); // �������б������һ��
		comBox.addItem("�����û�");
		comBox.addItem("����Ա");
		comBox.setBounds(190, 170, 80, 25);
		jPanel.add(comBox);
		comBox.addItemListener(new MyItemListener());//�����������ѡ����ʾ
		frame.setVisible(true);

		loginButton.addActionListener(new ActionListener() {//��¼�¼�
			private int ERROR_MESSAGE;

			@Override
			public void actionPerformed(ActionEvent e) {
				if(a==0) 
				{
					JOptionPane.showMessageDialog(null, "��ѡ����ݣ�", "����", ERROR_MESSAGE);
				}
				if (a == 1) {     //ѡ���û���¼ 
					String s1 = text1.getText().toString();
					String s2 = new String(text2.getPassword());
					Connection c = null;
					PreparedStatement stmt = null;
					try {
						Class.forName(driverName);
						c = DriverManager.getConnection(dbURL, userName, userPwd);
						String sql1 = "SELECT * FROM �����û��� where Unumber=? and password=?";//ƥ��
						stmt = c.prepareStatement(sql1);
						stmt.setString(1, s1);
						stmt.setString(2, s2);
						ResultSet rs = stmt.executeQuery();
						if (s1.length() != 0 && s2.length() != 0) {//�ж��û��Ƿ�������
							if (rs.next()) {
								USER = new User(rs.getString(5), rs.getString(4), rs.getString(1), rs.getString(2),
										rs.getString(3), rs.getString(6));//DAO�洢���ݿ����ݣ�֮��ֱ�ӵ���
								JOptionPane.showMessageDialog(null, "��½�ɹ�");
								Interface dff = new Interface();
								dff.interf();           //���ò������溯��
							} else {
								JOptionPane.showMessageDialog(null, "�ʺŻ���������", "����", ERROR_MESSAGE);
							}
						} else if (s1.length() == 0 || s2.length() == 0) {         //������
							JOptionPane.showMessageDialog(null, "�������ʺŻ�����", "����", ERROR_MESSAGE);
						}
						rs.close();
						stmt.close();
						c.close();
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						System.exit(0);
					}
				}
				if (a == 2) {               //ѡ�����Ա��¼
					String s1 = text1.getText().toString();
					String s2 = new String(text2.getPassword());
					Connection c = null;
					PreparedStatement stmt = null;
					try {
						Class.forName(driverName);
						c = DriverManager.getConnection(dbURL, userName, userPwd);  //�������ݿ�
						String sql1 = "SELECT * FROM ���ɹ���Ա�� where Mnumber=? and password=?";
						stmt = c.prepareStatement(sql1);
						stmt.setString(1, s1);
						stmt.setString(2, s2);
						ResultSet rs = stmt.executeQuery();
						if (s1.length() != 0 && s2.length() != 0) {       //�ж��Ƿ�������
							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "��½�ɹ�");
								Manager man = new Manager();
								man.manager();            //���ù���Ա����
							} else {
								JOptionPane.showMessageDialog(null, "�ʺŻ���������", "����", ERROR_MESSAGE);
							}
						} else if (s1.length() == 0 || s2.length() == 0) {           //������
							JOptionPane.showMessageDialog(null, "�������ʺŻ�����", "����", ERROR_MESSAGE);
						}
						rs.close();
						stmt.close();
						c.close();
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						System.exit(0);
					}
				}
			}
		});
		regButton.addActionListener(new ActionListener() {        //ע�ᰴť������
			private int ERROR_MESSAGE;

			public void actionPerformed(ActionEvent e) {
				if (a == 1) {      //������ѡ�����û�������ע��
					A3 re = new A3();
					re.reg();          //����ע�����
				} else {               //δѡ���û�������ע��
					JOptionPane.showMessageDialog(null, "ֻ�������û�����ע�ᣡ", "����", ERROR_MESSAGE);
				}
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * 
	 * @author ����
	 *
	 */
	class MyItemListener implements ItemListener {            // ������ѡ������
		@Override
		public void itemStateChanged(ItemEvent e) {
			a = comBox.getSelectedIndex();
			String str = e.getItem().toString();
			showInfo.setBounds(5, 0, 150, 25);        //������ѡ������ʾ��Ϣ���λ��
			jPanel.add(showInfo);
			if (a == 1 || a == 2) {     //ѡ�����û���ݻ��߹���Ա���
				showInfo.setText("�𾴵�" + str + "�����¼");
			} else {                    //δѡ��
				showInfo.setText("��ѡ����ݣ�");
			}
		}
	}

}
/**
 * 
 * @author ����
 *
 */
public class A1 {

	public static void main(String[] args) {
		new BigMenu();          // ���õ�¼����

	}

}
