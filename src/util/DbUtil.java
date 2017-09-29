package util;

import java.sql.Connection;
import java.sql.DriverManager;

import util.DbUtil;

public class DbUtil {
	
	//��һ��
	//���Ȼ�ȡ���ݿ����ӣ�1.���ݿ��ַ���û��������롣��ȡ���ӣ�drivermanager.getconnection
	//�������ݿ��������������ݿ�����������Class.forName(jdbcName)����������
	//connection con,����con������Ҫclose
	
	private String dbUrl="jdbc:mysql://localhost:3306/db_gtalk";
	private String dbUserName="root";
	private String dbPassword="liuscut-jnu";
	private String jdbcName="com.mysql.jdbc.Driver";
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	
	/**
	 * �ر����ݿ�����
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
			System.out.println("���ݿ����ӳɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
