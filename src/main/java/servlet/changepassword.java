package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.postSms;
import entity.updata;

/**
 * Servlet implementation class changepassword
 */
@WebServlet("/changepassword")
public class changepassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changepassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		HttpSession session=request.getSession();
		if(session.getAttribute("phone")!=null)
		{
			String phone=session.getAttribute("phone").toString();
			String smscode=postSms.changepassword(phone,"SMS_151045111");
			if(!smscode.equals(""))
			{
				session.setAttribute("code", smscode);
				session.setAttribute("smsphone", phone);
				System.out.println(smscode);
				response.getWriter().write("true");
			}
		}
		else {
			response.getWriter().write("false");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String getcode=request.getParameter("code");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		String phone=session.getAttribute("phone").toString();
		String smsphone=session.getAttribute("smsphone").toString();
		String code=session.getAttribute("code").toString();
		if(phone.equals(smsphone))
		{
			if(code.equals(getcode))
			{
				String key="password";
				String value=password;
				updata.updatauserinfo(phone,key,value);
				response.getWriter().write("true");
			}
			else {
				response.getWriter().write("false");
			}
		}
		else {
			response.getWriter().write("false");
		}
	}

}
