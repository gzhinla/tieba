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
		JFrame jf = new JFrame("贴吧用户主界面");
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// Commentt COM;
		// Artical ART;
		JPanel jp = new JPanel();
		A2 sq = new A2();// 调用连接数据库函数

		// 创建 内容面板，使用 边界布局
		JPanel panel = new JPanel(new BorderLayout());

		// 创建 一个工具栏实例
		JToolBar toolBar = new JToolBar("工具栏");

		// 创建 工具栏按钮
		JButton previousBtn = new JButton("浏览帖子");
		JButton pauseBtn = new JButton("我要发帖");
		JButton myinfoBtn = new JButton("个人中心");

		// 添加 按钮 到 工具栏
		toolBar.add(previousBtn);
		toolBar.add(pauseBtn);
		toolBar.add(myinfoBtn);

		columnNames = new Vector();
		columnNames.add("帖主");
		columnNames.add("帖标题");
		columnNames.add("帖内容");
		rowData = new Vector();
		Connection ct = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		int a = 0;
		try {
			Class.forName(sq.driverName);
			ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
			ps = ct.prepareStatement("select username,topic,postcontent from 帖子表");
			ResultSet rs = ps.executeQuery();
			// ART = new Artical(rs.getString(2), rs.getString(3),
			// rs.getString(4),rs.getString(2));
			while (rs.next()) {
				// rowData可以存放多行
				Vector hang = new Vector();
				for (int i = 1; i < 4; i++)
					hang.add(rs.getString(i));
				// 加入到rowData
				rowData.add(hang);
			}
			ps = ct.prepareStatement("select count(*) as id from 帖子表");
			ResultSet rs1 = ps.executeQuery();     //统计帖子总数
			if (rs1.next()) {
				a = rs1.getInt("id");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		System.out.println("帖子数：" + a + "条！");    //统计帖子总数
		final JTextArea textArea = new JTextArea();
		JTable jt = new JTable(rowData, columnNames);
		DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
		// 初始化 jsp
		jt.setPreferredScrollableViewportSize(new Dimension(400, 300));
		JScrollPane jsp = new JScrollPane(jt);

		// 创建一个文本区域，用于输出相关信息
		// final JTextArea textArea = new JTextArea();
		// textArea.setLineWrap(true);

		// 添加 按钮 的点击动作监听器，并把相关信息输入到 文本区域
		previousBtn.addActionListener(new ActionListener() {   //   浏览帖子
			@Override
			public void actionPerformed(ActionEvent e) {

				int rowcount = jt.getSelectedRow();
				if (rowcount >= 0) {
					String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();   //a为第一个表格的数据
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();   //b为第二个表格的数据
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();   //c为第三个表格的数据
					// COM =new Commentt(c,a,b);

					// System.out.println(a.toString());
					JFrame sp = new JFrame("浏览帖子");
					sp.setSize(500, 500);
					sp.setLocation(400, 200);
					JLabel laba = new JLabel(b);
					JLabel labb = new JLabel("帖主:  " + a);
					JTextArea jta = new JTextArea(c);
					jta.setLineWrap(true);
					laba.setFont(new Font("黑体", Font.BOLD, 22));
					JPanel jpa = new JPanel();
					JButton mylike = new JButton("关注");
					JButton myfavor = new JButton("收藏");
					JButton comment = new JButton("评论");

					mylike.setBounds(60, 360, 80, 25);
					jpa.add(mylike);
					// JOptionPane.showMessageDialog(null, "关注帖主成功");

					myfavor.setBounds(200, 360, 80, 25);
					jpa.add(myfavor);
					// JOptionPane.showMessageDialog(null, "收藏帖子成功");

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
					mylike.addActionListener(new ActionListener() {     //关注帖子  功能

						public void actionPerformed(ActionEvent e) {
							Connection ct = null;
							PreparedStatement ps1 = null;
							try {
								Class.forName(sq.driverName);
								ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
								String sql4 = "UPDATE 贴吧用户表 SET mylike=? WHERE Unumber=?";
								ps1 = ct.prepareStatement(sql4);
								ps1.setString(1, a);
								ps1.setString(2, USER.getuNumber());
								ps1.executeUpdate();    //将登录用户的关注改为最新关注
								JOptionPane.showMessageDialog(null, "关注帖主成功");
							} catch (Exception e1) {
								e1.printStackTrace();
								System.exit(0);
							}

						}
					});
					myfavor.addActionListener(new ActionListener() {     //收藏帖子功能
						@Override
						public void actionPerformed(ActionEvent e) {
							Connection ct = null;
							PreparedStatement ps1 = null;
							try {
								Class.forName(sq.driverName);
								ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
								String sql3 = "INSERT INTO 用户收藏夹表 (postcontent,topic,username) VALUES (?,?,?)";
								ps1 = ct.prepareStatement(sql3);// 预编译SQL，减少sql执行
								ps1.setString(1, c);// 赋值语句，给第一个？赋值s1
								ps1.setString(2, b);
								ps1.setString(3, USER.getUserName());
								ps1.executeUpdate();       //将收藏的帖子加入到数据库用户收藏表中
								JOptionPane.showMessageDialog(null, "帖子收藏成功");
							} catch (Exception e1) {
								e1.printStackTrace();
								System.exit(0);
							}
						}
					});
					comment.addActionListener(new ActionListener() {       //浏览评论和发表评论界面
						@Override
						public void actionPerformed(ActionEvent e) {
							// Comment com=new Comment();
							// com.comment();
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
								ps1 = ct1.prepareStatement("select username,comcontent from 贴吧评论表 WHERE topic=?");
								ps1.setString(1,b);
								ResultSet rs = ps1.executeQuery();    //  输出当前浏览帖子的评论
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
											ps1.setString(1, s1);// 赋值语句，给第一个？赋值s1
											ps1.setString(2, USER.getUserName());
											ps1.setString(3, b);
											ps1.executeUpdate();     //发表评论存入到当前浏览的帖子评论中
											JOptionPane.showMessageDialog(null, "评论发表成功！");
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
		pauseBtn.addActionListener(new ActionListener() {     //建立新帖界面
			@Override
			public void actionPerformed(ActionEvent e) {
				BuidT build = new BuidT();
				build.build();     //调用建立新帖方法
			}
		});
		myinfoBtn.addActionListener(new ActionListener() {     //个人中心界面
			@Override
			public void actionPerformed(ActionEvent e) {
				Personal per = new Personal();
				per.person();       //调用个人中心界面方法
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
	
}
