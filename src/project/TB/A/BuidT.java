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
 * @author ����
 *
 */
public class BuidT {
	public void build() {
		
		A2 sq = new A2();     // �����������ݿ����
		
		JFrame sp = new JFrame("�½���");
		sp.setSize(500, 500);
		sp.setLocation(400, 200);
		JLabel topic = new JLabel("�����⣺");
		JLabel content = new JLabel("�����ݣ�");
		JTextField ttopic = new JTextField(30);
		JTextField tcontent = new JTextField(200);
		JButton publish = new JButton("����");
		JPanel jpa = new JPanel();
		sp.add(jpa);
		jpa.setLayout(null);
		publish.setBounds(360, 370, 80, 45);// ����ť
		jpa.add(publish);      //��ӷ���ť
		jpa.add(topic);     //���������
		jpa.add(content);     //���������
		jpa.add(ttopic);     
		jpa.add(tcontent);
		topic.setBounds(10, 30, 200, 45);
		content.setBounds(10, 140, 200, 45);
		ttopic.setBounds(10, 80, 410, 50);
		tcontent.setBounds(10, 190, 410, 150);     // ����λ��
		sp.setVisible(true);
		publish.addActionListener(new ActionListener() {
			int ERROR_MESSAGE;
			public void actionPerformed(ActionEvent e) {
				String s1 = ttopic.getText().toString();     //��ȡ��������ӱ���
				String s2 = tcontent.getText().toString();     // ��ȡ�������������
				//int a=0;
				//while(a<10000){   // ����10000������
				Connection ct = null;
				PreparedStatement ps1 = null;
				try {
					Class.forName(sq.driverName);
					ct = DriverManager.getConnection(sq.dbURL, sq.userName, sq.userPwd);
					if (s1.length() == 0 || s2.length() == 0) {      //�ж���Ϣ�Ƿ���������
						JOptionPane.showMessageDialog(null, "δ��д����", "����", ERROR_MESSAGE);   // ��ʾ��Ϣ
					}else {
					String sql3 = "INSERT INTO ���ӱ� (postcontent,topic,username) VALUES (?,?,?)";
					ps1 = ct.prepareStatement(sql3);// Ԥ����SQL������sqlִ��
					ps1.setString(1, s2);//��ֵ��䣬����һ������ֵs1
					ps1.setString(2, s1);
					ps1.setString(3, USER.getUserName());
					//ps1.setString(1, "���ͼ���");//10000������
					//ps1.setString(2, "Ŭ��Ŭ��");
					//ps1.setString(3, "С��");
					//ps1.executeUpdate();
					JOptionPane.showMessageDialog(null, "���ӷ���ɹ�");
					//a=a+1;
					WNotice.writeNotice();  //���ӷ���ɹ�������ʾ����Ա��ˣ�������ù���Ա֪ͨ����
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
