package server;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;
import util.DbUtil;

public class LoginServlet extends HttpServlet{
	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userName=request.getParameter("username");
		System.out.println("servlet:"+userName);
		String  newname=new String(userName.getBytes("iso-8859-1"), "utf-8");
		System.out.println(newname);
		String password=request.getParameter("password");
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		User user=new User(userName,password);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser==null){
				request.setAttribute("error", "用户名或密码错误");
				System.out.println("用户名或密码错误");
			
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{

				HttpSession session=request.getSession();
				session.setAttribute("currentUser", currentUser);
			
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}
