package util;

import java.sql.Connection;
import java.sql.DriverManager;

import util.DbUtil;

public class DbUtil {
	
	//第一步
	//首先获取数据库连接：1.数据库地址，用户名，密码。获取连接：drivermanager.getconnection
	//根据数据库类型来加载数据库驱动，，用Class.forName(jdbcName)来创建对象
	//connection con,对于con对象，需要close
	
	private String dbUrl="jdbc:mysql://localhost:3306/db_gtalk";
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
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
