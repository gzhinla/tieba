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
 * @author 公子
 *
 */
public class Personal {
	static Vector rowData;
	static Vector columnNames;  //建立表格需要用
	public void person() {
		A2 sq = new A2();   //调用连接数据库函数

		JFrame sp = new JFrame("个人中心");
		sp.setSize(500, 500);
		sp.setLocation(750, 300);
		JLabel username = new JLabel("我的姓名：" + USER.getUserName());   // USER.getUserName()是用之前存入数据库信息
		JButton mylike = new JButton("关注");
		JButton notice = new JButton("通知");
		JButton myfavor = new JButton("收藏夹");
		JLabel Unumber = new JLabel("我的账号：" + USER.getuNumber());   //USER.getuNumber()是用之前存入数据库信息
		JButton update = new JButton("编辑");
		JPanel jpa = new JPanel();
		sp.add(jpa);
		jpa.setLayout(null);
		mylike.setBounds(320, 200, 80, 45);// 关注按钮
		jpa.add(mylike);
		notice.setBounds(200, 200, 80, 45);// 通知按钮
		jpa.add(notice);
		myfavor.setBounds(80, 200, 80, 45);// 收藏夹按钮
		jpa.add(myfavor);
		update.setBounds(340, 340, 100, 55);// 编辑按钮
		jpa.add(update);

		jpa.add(username);
		jpa.add(Unumber);
		username.setBounds(120, 20, 180, 45);
		Unumber.setBounds(120, 90, 180, 45);
		sp.setVisible(true);
		myfavor.addActionListener(new ActionListener() {// 收藏夹
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("收藏夹");
				jf.setSize(500, 500);
				jf.setLocationRelativeTo(null);
				// jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

				JPanel jp = new JPanel();
				A2 sq = new A2();

				// 创建 内容面板，使用 边界布局
				JPanel panel = new JPanel(new BorderLayout());

				// 创建 一个工具栏实例
				JToolBar toolBar = new JToolBar("工具");

				// 创建 工具栏按钮
				JButton previousBtn = new JButton("浏览帖子");

				// 添加 按钮 到 工具栏
				toolBar.add(previousBtn);
				columnNames = new Vector();
				columnNames.add("帖主");
				columnNames.add("帖标题");
				columnNames.add("帖内容");
				rowData = new Vector();
				Connection ct = null;
				PreparedStatement ps = null;
				try {
					Class.forName(sq.driverName);
					ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
					ps = ct.prepareStatement("select username,topic,postcontent from 用户收藏夹表");  //查找数据库中收藏夹表的信息
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						// rowData可以存放多行
						Vector hang = new Vector();
						for (int i = 1; i < 4; i++)
							hang.add(rs.getString(i));
						// 加入到rowData
						rowData.add(hang);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(0);
				}
				final JTextArea textArea = new JTextArea();
				JTable jt = new JTable(rowData, columnNames);
				DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
				// 初始化 jsp
				jt.setPreferredScrollableViewportSize(new Dimension(400, 300));
				JScrollPane jsp = new JScrollPane(jt);  // 数据过多可实现页面下滑
				previousBtn.addActionListener(new ActionListener() {// 浏览帖子
					@Override
					public void actionPerformed(ActionEvent e) {

						int rowcount = jt.getSelectedRow();  // 选择表格中的一行，用rowcount记录行数
						if (rowcount >= 0) {
							String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();
							String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();
							String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();  // a,b,c分别储存选择改行中的数据,a帖主,c帖内容,b帖标题。
							// System.out.println(a.toString());
							JFrame sp = new JFrame("浏览帖子");   // 浏览帖子界面
							sp.setSize(500, 500);
							sp.setLocation(400, 200);
							JLabel laba = new JLabel(b);
							JLabel labb = new JLabel("帖主:  " + a);
							JTextArea jta = new JTextArea(c);
							jta.setLineWrap(true);
							laba.setFont(new Font("黑体", Font.BOLD, 22));
							JPanel jpa = new JPanel();
							JButton comment = new JButton("评论");

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
							comment.addActionListener(new ActionListener() {  //调用评论界面发生器
								@Override
								public void actionPerformed(ActionEvent e) {
									A2 sq = new A2();  //连接数据库

									JFrame sp = new JFrame("评论区");
									sp.setSize(500, 500);
									sp.setLocation(400, 200);
									JTextField text = new JTextField(30);
									JLabel myc = new JLabel("快来评论吧！");
									JTextArea jta = new JTextArea();
									jta.setLineWrap(true);
									JPanel jpa = new JPanel();
									JButton publish = new JButton("发表");
									sp.add(jpa);
									jpa.setLayout(null);
									publish.setBounds(360, 370, 80, 45);// 按钮
									jpa.add(publish);
									jpa.add(text);
									jpa.add(myc);// 评论
									jpa.add(jta);
									text.setBounds(10, 370, 320, 45);// 评论框
									myc.setBounds(10, 340, 80, 25);// 评论前言
									jta.setBounds(10, 10, 430, 320);// 评论浏览区
									sp.setVisible(true);

									Connection ct1 = null;
									PreparedStatement ps1 = null;
									try {
										Class.forName(sq.driverName);
										ct1 = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
										ps1 = ct1.prepareStatement("select username,comcontent from 贴吧评论表  WHERE topic=?");
										ps1.setString(1, a);  //找到该帖子对应的评论
										ResultSet rs = ps1.executeQuery();  //查找数据库帖子评论表信息
										while (rs.next()) {
											String username = rs.getString(1);
											String comcontent = rs.getString(2);   //读取数据库贴吧评论表数据
											jta.append(username + ":  ");
											jta.append(comcontent + "  \n");   //输出界面
											jpa.repaint();
										}
									} catch (Exception e1) {
										e1.printStackTrace();
										System.exit(0);
									}

									publish.addActionListener(new ActionListener() {  //评论发表界面
										private int ERROR_MESSAGE;

										public void actionPerformed(ActionEvent e) {
											String s1 = text.getText().toString();// 评论获取
											Connection ct = null;
											PreparedStatement ps1 = null;
											try {
												Class.forName(sq.driverName);
												ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
												if (s1.length() == 0) {
													JOptionPane.showMessageDialog(null, "未填写评论", "错误", ERROR_MESSAGE);
												} else {
													String sql3 = "INSERT INTO 贴吧评论表 (comcontent,username,topic) VALUES (?,?,?)";
													ps1 = ct.prepareStatement(sql3);// 预编译SQL，减少sql执行
													ps1.setString(1, s1);   // 赋值语句，给第一个？赋值s1，存入评论内容
													ps1.setString(2, USER.getUserName());  //存入评论用户姓名
													ps1.setString(1, b);    //存入所选择进行评论的帖子标题
													ps1.executeUpdate();
													JOptionPane.showMessageDialog(null, "评论发表成功！");
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

				// 添加 工具栏 到 内容面板 的 顶部
				panel.add(toolBar, BorderLayout.PAGE_START);
				// 添加 文本区域 到 内容面板 的 中间
				// panel.add(textArea, BorderLayout.CENTER);
				panel.add(jsp, BorderLayout.CENTER);
				jf.setContentPane(panel);
				jf.setVisible(true);
			}
		});
		notice.addActionListener(new ActionListener() {// 通知界面
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("通知");
				jf.setSize(300, 300);
				jf.setLocationRelativeTo(null);
				// jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

				// 创建文本区域组件
				JTextArea textArea = new JTextArea();
				textArea.setLineWrap(true); // 自动换行
				textArea.setFont(new Font(null, Font.PLAIN, 18)); // 设置字体

				// 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
				JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

				Connection ct1 = null;
				PreparedStatement ps1 = null;
				try {
					Class.forName(sq.driverName);
					ct1 = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
					ps1 = ct1.prepareStatement("select notice from 贴吧用户表 WHERE username=?");
					ps1.setString(1, USER.getUserName());
					ResultSet rs = ps1.executeQuery();   //查找数据库中的通知信息
					while (rs.next()) {
						String notice = rs.getString(1);
						textArea.append(notice + "  \n");  // 输出通知信息
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

		mylike.addActionListener(new ActionListener() {// 关注界面
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("关注");
				jf.setSize(300, 300);
				jf.setLocationRelativeTo(null);
				// jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

				// 创建文本区域组件
				JTextArea textArea = new JTextArea();
				textArea.setLineWrap(true); // 自动换行
				textArea.setFont(new Font(null, Font.PLAIN, 18)); // 设置字体

				// 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
				JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

				Connection ct1 = null;
				PreparedStatement ps1 = null;
				try {
					Class.forName(sq.driverName);
					ct1 = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
					ps1 = ct1.prepareStatement("select mylike from 贴吧用户表 WHERE username=?");
					ps1.setString(1, USER.getUserName());   //找到当前登录用户的关注
					ResultSet rs = ps1.executeQuery();
					while (rs.next()) {
						String notice = rs.getString(1);
						textArea.append(notice + "  \n");
						jpa.repaint();   //更新
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(0);
				}

				jf.setContentPane(scrollPane);
				jf.setVisible(true);
			}
		});
		update.addActionListener(new ActionListener() {// 编辑个人信息
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("编辑个人信息");
				jf.setSize(350, 250);
				jf.setLocation(750, 200);
				JPanel jp = new JPanel();
				jf.add(jp);
				jp.setLayout(null);
				JLabel acc = new JLabel("输入新帐号");
				JLabel pass = new JLabel("输入新密码");
				jp.add(pass);
				jp.add(acc);
				acc.setBounds(80, 10, 80, 25);
				pass.setBounds(80, 70, 80, 25);
				JTextField text1 = new JTextField();// 帐号输入
				JPasswordField text2 = new JPasswordField();// 密码输入
				text1.setBounds(80, 40, 165, 25);
				text2.setBounds(80, 100, 165, 25);
				jp.add(text1);
				jp.add(text2);
				JButton regButton = new JButton("修改");// 注册按钮
				regButton.setBounds(125, 150, 80, 40);
				jp.add(regButton);
				jf.setVisible(true);
				A2 sql = new A2();
				regButton.addActionListener(new ActionListener() {
					private int ERROR_MESSAGE;

					public void actionPerformed(ActionEvent e) {
						String s1 = text1.getText().toString();// 账号获取
						String s2 = new String(text2.getPassword());// 密码获取
						Connection c = null;
						PreparedStatement stmt2 = null;
						PreparedStatement stmt4 = null;
						int i;// 更改限制
						try {
							Class.forName(sql.driverName);
							c = DriverManager.getConnection(sql.dbURL, sql.userName, sql.userPwd);
							if (s1.length() == 0 || s2.length() == 0) {
								JOptionPane.showMessageDialog(null, "还有信息未填", "错误", ERROR_MESSAGE);
							}
							// String sql1 = "SELECT * FROM 贴吧用户表 where username=?";
							// stmt1 = c.prepareStatement(sql1);
							// stmt1.setString(1, USER.getUserName());//
							// setString的主要目的是把帐号密码的信息放进sql1里面进行指定内容的查询
							// ResultSet rs1 = stmt1.executeQuery();
							// i = rs1.getInt("ID");
							// if (i == 0) {
							else {
								String sql2 = "SELECT * FROM 贴吧用户表 where Unumber=?";
								stmt2 = c.prepareStatement(sql2);// 预编译SQL，减少sql执行
								stmt2.setString(1, s1);
								ResultSet rs2 = stmt2.executeQuery();// 执行查询语句
								if (!rs2.next()) {
									JOptionPane.showMessageDialog(null, "修改成功");
									String sql4 = "UPDATE 贴吧用户表 SET Unumber=?,password=? WHERE username=?";
									stmt4 = c.prepareStatement(sql4);
									stmt4.setString(1, s1);
									stmt4.setString(2, s2);
									stmt4.setString(3, USER.getUserName());  // 找到当前登录用户进行账号或者密码修改
									stmt4.executeUpdate();
								} else {
									JOptionPane.showMessageDialog(null, "账号已经存在", "错误", ERROR_MESSAGE);
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
