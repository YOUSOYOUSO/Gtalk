package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDao {
	//第二步
	//链接数据库之后，从数据库里面获取表
	/*
	 * 流程：
	 * 1.写好数据库查询语句
	 * 2.preparedstatement，预先查询语句
	 * 3.setstring，将查询的东西设置一个先后顺序
	 * 4.替换问号，resultset+pstmt.excutequery()
	 * 
	 * */

	/**
	 * 登录验证
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con,User user) throws Exception{
		User resultUser=null;
		String sql="select * from t_user where user_name=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserName(rs.getString("user_name"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
}
