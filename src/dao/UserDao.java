package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDao {
	//�ڶ���
	//�������ݿ�֮�󣬴����ݿ������ȡ��
	/*
	 * ���̣�
	 * 1.д�����ݿ��ѯ���
	 * 2.preparedstatement��Ԥ�Ȳ�ѯ���
	 * 3.setstring������ѯ�Ķ�������һ���Ⱥ�˳��
	 * 4.�滻�ʺţ�resultset+pstmt.excutequery()
	 * 
	 * */

	/**
	 * ��¼��֤
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
