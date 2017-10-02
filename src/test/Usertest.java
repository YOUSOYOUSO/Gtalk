package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;


import java.sql.Connection;
import java.sql.DriverManager;

import util.DbUtil;


public class Usertest {
	
	//第一步
	//首先获取数据库连接：1.数据库地址，用户名，密码。获取连接：drivermanager.getconnection
	//根据数据库类型来加载数据库驱动，，用Class.forName(jdbcName)来创建对象
	//connection con,对于con对象，需要close
	
	private String dbUrl="jdbc:mysql://localhost:3306/db_gtalk?characterEncoding=gbk";
	private String dbUserName="root";
	private String dbPassword="liuscut-jnu";
	private String jdbcName="com.mysql.jdbc.Driver";
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
	

	
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
	

	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			
			Connection con=dbUtil.getCon();
			System.out.println("数据库连接成功");
			
			String sql="select * from t_user where user_name='G梁' and password='admin'";
			PreparedStatement pstmt=con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				System.out.println("密码"+rs.getString("password"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
