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
 * @author 公子
 *
 */
class BigMenu {         //登录界面以及操作

	 // 以下为连接数据库
	static final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=贴吧用户表";
	static final String userName = "sa";// 数据库用户名
	static final String userPwd = "123456789";// 密码

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
	int a;//标记下拉表是否有选择
	public static User USER;  //DAO USER类
	public BigMenu() {
		JFrame frame = new JFrame("贴吧登录界面");

		JLabel accout = new JLabel("帐号");
		JTextField text1 = new JTextField(16);
		JLabel password = new JLabel("密码");
		JPasswordField text2 = new JPasswordField(16);
		JButton loginButton = new JButton("登录");
		JButton regButton = new JButton("注册");
		JLabel txt = new JLabel("请选择登录身份:");

		frame.setSize(370, 280);
		frame.setLocation(750, 300);
		// frame.setResizable(false);
		// jPanel.setLayout(null);
		jPanel.setLayout(null);
		jPanel.add(accout);
		accout.setBounds(70, 40, 80, 25);
		password.setBounds(70, 75, 80, 25);

		frame.add(jPanel);

		// 帐号输入
		text1.setBounds(120, 40, 165, 25);
		jPanel.add(text1);

		jPanel.add(password);
		// 密码输入
		text2.setBounds(120, 75, 165, 25);
		jPanel.add(text2);

		// 登录按钮
		loginButton.setBounds(80, 120, 80, 25);
		jPanel.add(loginButton);

		// 注册按钮
		regButton.setBounds(200, 120, 80, 25);
		jPanel.add(regButton);

		txt.setBounds(85, 170, 100, 25);
		jPanel.add(txt);

		comBox.addItem("--请选择--"); // 向下拉列表中添加一项
		comBox.addItem("贴吧用户");
		comBox.addItem("管理员");
		comBox.setBounds(190, 170, 80, 25);
		jPanel.add(comBox);
		comBox.addItemListener(new MyItemListener());//调用下拉表的选择显示
		frame.setVisible(true);

		loginButton.addActionListener(new ActionListener() {//登录事件
			private int ERROR_MESSAGE;

			@Override
			public void actionPerformed(ActionEvent e) {
				if(a==0) 
				{
					JOptionPane.showMessageDialog(null, "请选择身份！", "错误", ERROR_MESSAGE);
				}
				if (a == 1) {     //选择用户登录 
					String s1 = text1.getText().toString();
					String s2 = new String(text2.getPassword());
					Connection c = null;
					PreparedStatement stmt = null;
					try {
						Class.forName(driverName);
						c = DriverManager.getConnection(dbURL, userName, userPwd);
						String sql1 = "SELECT * FROM 贴吧用户表 where Unumber=? and password=?";//匹配
						stmt = c.prepareStatement(sql1);
						stmt.setString(1, s1);
						stmt.setString(2, s2);
						ResultSet rs = stmt.executeQuery();
						if (s1.length() != 0 && s2.length() != 0) {//判断用户是否有输入
							if (rs.next()) {
								USER = new User(rs.getString(5), rs.getString(4), rs.getString(1), rs.getString(2),
										rs.getString(3), rs.getString(6));//DAO存储数据库数据，之后直接调用
								JOptionPane.showMessageDialog(null, "登陆成功");
								Interface dff = new Interface();
								dff.interf();           //调用操作界面函数
							} else {
								JOptionPane.showMessageDialog(null, "帐号或密码有误", "错误", ERROR_MESSAGE);
							}
						} else if (s1.length() == 0 || s2.length() == 0) {         //有输入
							JOptionPane.showMessageDialog(null, "请输入帐号或密码", "错误", ERROR_MESSAGE);
						}
						rs.close();
						stmt.close();
						c.close();
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						System.exit(0);
					}
				}
				if (a == 2) {               //选择管理员登录
					String s1 = text1.getText().toString();
					String s2 = new String(text2.getPassword());
					Connection c = null;
					PreparedStatement stmt = null;
					try {
						Class.forName(driverName);
						c = DriverManager.getConnection(dbURL, userName, userPwd);  //连接数据库
						String sql1 = "SELECT * FROM 贴吧管理员表 where Mnumber=? and password=?";
						stmt = c.prepareStatement(sql1);
						stmt.setString(1, s1);
						stmt.setString(2, s2);
						ResultSet rs = stmt.executeQuery();
						if (s1.length() != 0 && s2.length() != 0) {       //判断是否有输入
							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "登陆成功");
								Manager man = new Manager();
								man.manager();            //调用管理员界面
							} else {
								JOptionPane.showMessageDialog(null, "帐号或密码有误", "错误", ERROR_MESSAGE);
							}
						} else if (s1.length() == 0 || s2.length() == 0) {           //有输入
							JOptionPane.showMessageDialog(null, "请输入帐号或密码", "错误", ERROR_MESSAGE);
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
		regButton.addActionListener(new ActionListener() {        //注册按钮发生器
			private int ERROR_MESSAGE;

			public void actionPerformed(ActionEvent e) {
				if (a == 1) {      //下拉表选择了用户，可以注册
					A3 re = new A3();
					re.reg();          //调用注册界面
				} else {               //未选择用户，不能注册
					JOptionPane.showMessageDialog(null, "只有贴吧用户才能注册！", "错误", ERROR_MESSAGE);
				}
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * 
	 * @author 公子
	 *
	 */
	class MyItemListener implements ItemListener {            // 下拉表选择发生器
		@Override
		public void itemStateChanged(ItemEvent e) {
			a = comBox.getSelectedIndex();
			String str = e.getItem().toString();
			showInfo.setBounds(5, 0, 150, 25);        //下拉表选择后的提示信息输出位置
			jPanel.add(showInfo);
			if (a == 1 || a == 2) {     //选择了用户身份或者管理员身份
				showInfo.setText("尊敬的" + str + "，请登录");
			} else {                    //未选择
				showInfo.setText("请选择身份！");
			}
		}
	}

}
/**
 * 
 * @author 公子
 *
 */
public class A1 {

	public static void main(String[] args) {
		new BigMenu();          // 调用登录界面

	}

}
