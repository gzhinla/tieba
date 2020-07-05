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
 * @author 公子
 *
 */
public class Manager {        // 管理员操作界面
	static Vector rowData;
	static Vector columnNames;
	//String a=null;
	//String b=null;
	//String c=null;
	public void manager() {
		JFrame jf = new JFrame("帖子总表");
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel jp = new JPanel();
		A2 sq = new A2();   //调用数据库方法

		// 创建 内容面板，使用 边界布局
		JPanel panel = new JPanel(new BorderLayout());

		// 创建 一个工具栏实例
		JToolBar toolBar = new JToolBar("工具栏");

		// 创建 工具栏按钮
		JButton previousBtn = new JButton("浏览帖子");
		JButton pauseBtn = new JButton("删除帖子");
		JButton noticeBtn = new JButton("通知");

		// 添加 按钮 到 工具栏
		toolBar.add(previousBtn);
		toolBar.add(pauseBtn);
		toolBar.add(noticeBtn);

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
			ps = ct.prepareStatement("select username,topic,postcontent from 帖子表");
			ResultSet rs = ps.executeQuery();    //在管理员界面输出帖子表待审查
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
		JScrollPane jsp = new JScrollPane(jt);
		noticeBtn.addActionListener(new ActionListener() {// 管理员通知
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame("通知");
				jf.setSize(600, 400);
				jf.setLocationRelativeTo(null);
				// jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

				// 创建文本区域组件
				JTextArea textArea = new JTextArea();
				textArea.setLineWrap(true); // 自动换行
				textArea.setFont(new Font(null, Font.PLAIN, 18)); // 设置字体

				// 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
				JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

				String notice;
				try {
					notice = WNotice.getNotice();     //通过用户发帖通知管理员有新帖审核
					textArea.append(notice + "  \n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jf.setContentPane(scrollPane);
				jf.setVisible(true);
				WNotice.DeleteFile();           //管理员浏览完通知后自动删除通知内容
			}
		});
		previousBtn.addActionListener(new ActionListener() {// 浏览帖子
			@Override
			public void actionPerformed(ActionEvent e) {

				int rowcount = jt.getSelectedRow();
				if (rowcount >= 0) {
					String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();     //a为表格第一个数据，b为表格第二个数据，c为表格第三个数据
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
					comment.addActionListener(new ActionListener() {      // 评论界面
						@Override
						public void actionPerformed(ActionEvent e) {
							MComment mcom = new MComment();
							mcom.mComment();     //调用管理员审核评论界面
							
						}
					});
				}
			}
		});
		pauseBtn.addActionListener(new ActionListener() {      //删除帖子功能
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowcount = jt.getSelectedRow();
				if (rowcount >= 0) {
					String a = jt.getValueAt(jt.getSelectedRow(), 0).toString();
					String b = jt.getValueAt(jt.getSelectedRow(), 1).toString();
					String c = jt.getValueAt(jt.getSelectedRow(), 2).toString();     //a为表格第一个数据，b为表格第二个数据，c为表格第三个数据
					Connection ct = null;
					PreparedStatement ps = null;
					try {
						Class.forName(sq.driverName);
						ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
						String sql4 = "delete from 帖子表 WHERE username=?";
						ps = ct.prepareStatement(sql4);
						ps.setString(1, a);     //删除选定帖子
						ps.executeUpdate();
					} catch (Exception e1) {
						e1.printStackTrace();
						System.exit(0);
					}
					tableModel.removeRow(rowcount);   //从界面表格中移除帖子
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

}
