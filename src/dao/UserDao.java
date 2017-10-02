package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDao {

	public User login(Connection con,User user) throws Exception{
		User resultUser=null;
		String sql="select * from t_user where user_name=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		
		System.out.println(user.getUserName()+user.getPassword());
		
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserName(rs.getString("user_name"));
			resultUser.setPassword(rs.getString("password"));
		}
		
		String sql1="select * from t_user where user_name='G梁' and password='admin'";
		PreparedStatement pstmt1=con.prepareStatement(sql1);
		ResultSet rs1=pstmt1.executeQuery();
		if(rs1.next()){
			System.out.println("G梁密码"+rs1.getString("password"));
		}
		
		return resultUser;
	}
}
