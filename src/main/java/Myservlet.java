import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/regform")
public class Myservlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out=resp.getWriter();
	String myname=req.getParameter("name");
	String Email=req.getParameter("email");
	String mypass=req.getParameter("password");
	String mygender=req.getParameter("gender");
	String mycity=req.getParameter("city");
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/registrationform","root","satyam@1161");
		PreparedStatement ps=conn.prepareStatement("insert into register values(?,?,?,?,?)");
		ps.setString(1, myname);
		ps.setString(2, Email);
		ps.setString(3, mypass);
		ps.setString(4, mygender);
		ps.setString(5, mycity);
		int count=ps.executeUpdate();
		if(count>0) {
			resp.setContentType("text/html");
			out.print("<h3 style='color:green'> User registered successfully</h3>");
			RequestDispatcher rd=req.getRequestDispatcher("/register.jsp");
			rd.include(req, resp);
		}
		else {
			resp.setContentType("text/html");
			out.print("<h3 style='color:green'> User NOT registered Due to some Error</h3>");
			RequestDispatcher rd=req.getRequestDispatcher("/register.jsp");
			rd.include(req, resp);
			
		}
	}
	catch(Exception e) {
		e.printStackTrace();
		resp.setContentType("text/html");
		out.print("<h3 style='color:green'>Exception Occcured: "+e.getMessage()+"</h3>");
		RequestDispatcher rd=req.getRequestDispatcher("/register.jsp");
		rd.include(req, resp);
	}
	
	
	
}
}
