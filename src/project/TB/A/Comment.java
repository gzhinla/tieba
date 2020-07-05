package project.TB.A;

import static project.TB.A.BigMenu.USER;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import project.TB.A.pojo.Commentt;
public class Comment {
	public static Commentt COM;
	public void comment() {
		
		A2 sq = new A2();
		
		JFrame sp = new JFrame("������");
		sp.setSize(500, 500);
		sp.setLocation(400,200);
		JTextField text=new JTextField(30);
		JLabel myc=new JLabel("�������۰ɣ�");
		JTextArea jta=new JTextArea();
		jta.setLineWrap(true);  
		JPanel jpa=new JPanel();                              //���ô���飡
		JButton publish=new JButton("����");
		sp.add(jpa);
		jpa.setLayout(null);
		publish.setBounds(360, 370, 80, 45);//��ť
		jpa.add(publish);
		jpa.add(text);
		jpa.add(myc);//����
		jpa.add(jta);
		text.setBounds(10, 370, 320, 45);//���ۿ�
		myc.setBounds(10, 340, 80, 25);//����ǰ��
		jta.setBounds(10, 10, 430, 320);//���������
		sp.setVisible(true);
		
		Connection ct1=null;
		PreparedStatement ps1=null;
		try {
			Class.forName(sq.driverName);
			ct1=DriverManager.getConnection(sq.dbURL,sq.userName,sq.userPwd);					
			ps1=ct1.prepareStatement("select username,comcontent from �������۱�");					
			ResultSet rs=ps1.executeQuery();
			while (rs.next()) {
				String username=rs.getString(1);
				String comcontent=rs.getString(2);
				jta.append(username+":  ");
				jta.append(comcontent+"  \n");
				jpa.repaint();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		
		publish.addActionListener(new ActionListener() {
	            private int ERROR_MESSAGE;
	            public void actionPerformed(ActionEvent e) {
	            	String s1 = text.getText().toString();//���ۻ�ȡ
	            	Connection ct=null;
					PreparedStatement ps1=null;
					try {
						Class.forName(sq.driverName);
						ct=DriverManager.getConnection(sq.dbURL,sq.userName,sq.userPwd);
						if (s1.length() == 0) {
						JOptionPane.showMessageDialog(null, "δ��д����", "����", ERROR_MESSAGE);
						}else {
						String sql3 = "INSERT INTO �������۱� (comcontent,username,topic) VALUES (?,?,?)";
						ps1 = ct.prepareStatement(sql3);//Ԥ����SQL������sqlִ��
						ps1.setString(1, s1);//��ֵ��䣬����һ������ֵs1
						ps1.setString(2, USER.getUserName());
						ps1.setString(3, COM.getTopic());
						ps1.executeUpdate();
						JOptionPane.showMessageDialog(null, "���۷���ɹ���");
						//WNotice.writeNotice();
						jpa.repaint();
						}
					} catch (Exception e1) {
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						System.exit(0);
					}
	            }
	        });
		
		//sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
