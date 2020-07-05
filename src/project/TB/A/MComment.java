package project.TB.A;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author 公子
 *
 */
public class MComment {     //管理员审核用户评论界面
	static Vector rowData;
	static Vector columnNames;
	public void mComment() {
		JFrame jf = new JFrame("评论总表");
        jf.setSize(500, 500);
        jf.setLocationRelativeTo(null);
        //jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jp=new JPanel();
        A2 sq = new A2();
        
        // 创建 内容面板，使用 边界布局
        JPanel panel = new JPanel(new BorderLayout());
        
        // 创建 一个工具栏实例
        JToolBar toolBar = new JToolBar("工具栏");

        // 创建 工具栏按钮    
        JButton delBtn = new JButton("删除评论");

        // 添加 按钮 到 工具栏
        toolBar.add(delBtn);
        
        columnNames=new Vector();
		columnNames.add("评论用户");
		columnNames.add("评论内容");
		rowData = new Vector();
		Connection ct=null;
		PreparedStatement ps=null;
		try {
			Class.forName(sq.driverName);
			ct=DriverManager.getConnection(sq.dbURL,sq.userName,sq.userPwd);					
			ps=ct.prepareStatement("select username,comcontent from 贴吧评论表");					
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//rowData可以存放多行
				Vector hang=new Vector();
				for(int i=1;i<3;i++)
					hang.add(rs.getString(i));	
				//加入到rowData
				rowData.add(hang);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		final JTextArea textArea = new JTextArea();
		JTable jt = new JTable(rowData,columnNames);
		DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
		//初始化 jsp
		jt.setPreferredScrollableViewportSize(new Dimension(400, 300));
		JScrollPane jsp = new JScrollPane(jt);
		
		delBtn.addActionListener(new ActionListener() {     //删除评论
            @Override
            public void actionPerformed(ActionEvent e) {
            	int rowcount = jt.getSelectedRow();    //捕获管理员所点击的表格行数
				if(rowcount >= 0){
					String a = jt.getValueAt(jt.getSelectedRow(), 1).toString();
					Connection ct=null;
					PreparedStatement ps=null;
					try {
						Class.forName(sq.driverName);
						ct=DriverManager.getConnection(sq.dbURL,sq.userName,sq.userPwd);					
						String sql4 = "delete from 贴吧评论表 WHERE comcontent=?";
						ps= ct.prepareStatement(sql4);
						ps.setString(1, a);
						ps.executeUpdate();    //删除评论
					} catch (Exception e1) {
						e1.printStackTrace();
						System.exit(0);
					}
					tableModel.removeRow(rowcount);    //删除评论表相应行
				}
            }
        });

		 // 添加 工具栏 到 内容面板 的 顶部
        panel.add(toolBar, BorderLayout.PAGE_START);
        // 添加 文本区域 到 内容面板 的 中间
        //panel.add(textArea, BorderLayout.CENTER);
        panel.add(jsp, BorderLayout.CENTER);
        jf.setContentPane(panel);
        jf.setVisible(true);
	}

}
