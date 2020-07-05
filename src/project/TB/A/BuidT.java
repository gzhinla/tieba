package project.TB.A;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static project.TB.A.BigMenu.USER;
/**
 * 
 * @author 公子
 *
 */
public class BuidT {
	public void build() {
		
		A2 sq = new A2();     // 调用连接数据库操作
		
		JFrame sp = new JFrame("新建帖");
		sp.setSize(500, 500);
		sp.setLocation(400, 200);
		JLabel topic = new JLabel("帖标题：");
		JLabel content = new JLabel("帖内容：");
		JTextField ttopic = new JTextField(30);
		JTextField tcontent = new JTextField(200);
		JButton publish = new JButton("发表");
		JPanel jpa = new JPanel();
		sp.add(jpa);
		jpa.setLayout(null);
		publish.setBounds(360, 370, 80, 45);// 发表按钮
		jpa.add(publish);      //添加发表按钮
		jpa.add(topic);     //添加贴标题
		jpa.add(content);     //添加帖内容
		jpa.add(ttopic);     
		jpa.add(tcontent);
		topic.setBounds(10, 30, 200, 45);
		content.setBounds(10, 140, 200, 45);
		ttopic.setBounds(10, 80, 410, 50);
		tcontent.setBounds(10, 190, 410, 150);     // 界面位置
		sp.setVisible(true);
		publish.addActionListener(new ActionListener() {
			int ERROR_MESSAGE;
			public void actionPerformed(ActionEvent e) {
				String s1 = ttopic.getText().toString();     //读取输入的帖子标题
				String s2 = tcontent.getText().toString();     // 读取输入的帖子内容
				//int a=0;
				//while(a<10000){   // 测试10000条数据
				Connection ct = null;
				PreparedStatement ps1 = null;
				try {
					Class.forName(sq.driverName);
					ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
					if (s1.length() == 0 || s2.length() == 0) {      //判断信息是否输入完整
						JOptionPane.showMessageDialog(null, "未填写完整", "错误", ERROR_MESSAGE);   // 提示信息
					}else {
					String sql3 = "INSERT INTO 帖子表 (postcontent,topic,username) VALUES (?,?,?)";
					ps1 = ct.prepareStatement(sql3);// 预编译SQL，减少sql执行
					ps1.setString(1, s2);//赋值语句，给第一个？赋值s1
					ps1.setString(2, s1);
					ps1.setString(3, USER.getUserName());
					//ps1.setString(1, "加油加油");//10000个数据
					//ps1.setString(2, "努力努力");
					//ps1.setString(3, "小明");
					//ps1.executeUpdate();
					JOptionPane.showMessageDialog(null, "帖子发表成功");
					//a=a+1;
					WNotice.writeNotice();  //帖子发表成功，将提示管理员审核，这里调用管理员通知函数
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(0);
				}
			}//}
		});
		//sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
