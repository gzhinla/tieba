package project.TB.A;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
/**
 * 
 * @author ����
 *
 */
public class A3 {
	public void reg() {
		JFrame jf = new JFrame("ע��");
		jf.setSize(370, 280);
		jf.setLocation(750, 300);
		JPanel jp = new JPanel();
		jf.add(jp);
		jp.setLayout(null);
		JLabel acc = new JLabel("�ʺ�:");
		JLabel pass = new JLabel("����:");
		JLabel id = new JLabel("�û���:");
		JLabel warn = new JLabel("�û���ע��ɹ��󲻿��޸ģ�");
		jp.add(pass);
		jp.add(id);
		jp.add(acc);
		jp.add(warn);
		acc.setBounds(40, 20, 80, 25);
		pass.setBounds(40, 60, 80, 25);
		id.setBounds(40, 100, 80, 25);
		warn.setBounds(40, 125, 200, 25);
		JTextField text1 = new JTextField();// �ʺ�����
		JPasswordField text2 = new JPasswordField();// ��������
		JTextField text3 = new JTextField();// �û�������
		text1.setBounds(100, 20, 165, 25);
		text2.setBounds(100, 60, 165, 25);
		text3.setBounds(100, 100, 165, 25);
		jp.add(text1);
		jp.add(text2);
		jp.add(text3);
		JButton regButton = new JButton("ע��");// ע�ᰴť
		regButton.setBounds(125, 160, 80, 30);
		jp.add(regButton);
		jf.setVisible(true);
		A2 sql = new A2();
		regButton.addActionListener(new ActionListener() {
			private int ERROR_MESSAGE;

			public void actionPerformed(ActionEvent e) {
				String s1 = text1.getText().toString();// �˺Ż�ȡ
				String s2 = new String(text2.getPassword());// �����ȡ
				String s3 = text3.getText().toString();// �û�����ȡ
				Connection c = null;
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				int i;// ע������
				try {
					Class.forName(sql.driverName);
					c = DriverManager.getConnection(sql.dbURL, sql.userName, sql.userPwd);
					if (s1.length() == 0 || s2.length() == 0 || s3.length() == 0) {
						JOptionPane.showMessageDialog(null, "������Ϣδ��", "����", ERROR_MESSAGE);
					} else if (s1.length() != 0 && s2.length() != 0 && s3.length() != 0) {
						String sql1 = "SELECT * FROM �����û��� where username=?";
						stmt1 = c.prepareStatement(sql1);// Ԥ����SQL������sqlִ��
						stmt1.setString(1, s3);
						ResultSet rs1 = stmt1.executeQuery();// ִ�в�ѯ���
						if (!rs1.next()) {
							String sql2 = "SELECT * FROM �����û��� where Unumber=?";
							stmt2 = c.prepareStatement(sql2);// Ԥ����SQL������sqlִ��
							stmt2.setString(1, s1);
							ResultSet rs2 = stmt2.executeQuery();// ִ�в�ѯ���
							if (!rs2.next()) {
								JOptionPane.showMessageDialog(null, "ע��ɹ�");
								String sql3 = "INSERT INTO �����û��� (Unumber,password,username,notice) VALUES (?,?,?,?)";
								stmt3 = c.prepareStatement(sql3);// Ԥ����SQL������sqlִ��
								stmt3.setString(1, s1);// ��ֵ��䣬����һ������ֵs1
								stmt3.setString(2, s2);
								stmt3.setString(3, s3);
								stmt3.setString(4, "��ӭ��������");
								stmt3.executeUpdate();// executeUpdate �ķ���ֵ��һ��������ָʾ��Ӱ��������������¼����������� CREATE TABLE �� DROP
														// TABLE �Ȳ������е���䣬executeUpdate �ķ���ֵ��Ϊ�㡣
								String sql4 = "UPDATE �����û��� SET ID=? WHERE Unumber=?";
								stmt4 = c.prepareStatement(sql4);
								stmt4.setString(1, "1");
								stmt4.setString(2, s1);
								stmt4.executeUpdate();
							} else {
								JOptionPane.showMessageDialog(null, "�˺��Ѿ�����", "����", ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "�û����Ѿ���ʹ��", "����", ERROR_MESSAGE);
						}

					}
				} catch (Exception e1) {
					System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					System.exit(0);
				}
			}
		});
	}
}
