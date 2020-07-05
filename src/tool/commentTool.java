package tool;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.xml.stream.events.Comment;

import project.TB.A.pojo.Commentt;
/**
 * 
 * @author 公子
 *
 */
public class commentTool {
	
	static final String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=贴吧用户表";
	static final String userName="sa";//数据库用户名
	static final String userPwd="123456789";//密码
	public static ArrayList commentT(String topic) {
	commentTool sq=new commentTool();
	Connection ct1=null;
	PreparedStatement ps1=null;
	ArrayList<Commentt> list =new ArrayList();
	try {
		Class.forName(sq.driverName);
		ct1=DriverManager.getConnection(sq.dbURL,sq.userName,sq.userPwd);					
		ps1=ct1.prepareStatement("select * from 贴吧评论表 where topic=?");	
		ps1.setString(1, topic);
		ResultSet rs=ps1.executeQuery();
		while (rs.next()) {
			Commentt comment = new Commentt(rs.getString(2),rs.getString(3),rs.getString(4));
			list.add(comment);
		}
	} catch (Exception e1) {
		e1.printStackTrace();
		System.exit(0);
	}
	return list;
}
}
