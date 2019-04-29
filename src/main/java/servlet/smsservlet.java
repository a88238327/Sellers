package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.postSms;
import entity.selectdata;

/**
 * Servlet implementation class smsservlet
 */
@WebServlet("/smsservlet")
public class smsservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public smsservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String phone=request.getParameter("phone");
		String type=request.getParameter("type");
		
		if(type.equals("sms"))
		{
			String code=postSms.login(phone);
			if(!code.equals("")) {
				HttpSession session=request.getSession();
				session.setAttribute("code", code);
				session.setAttribute("phone", phone);
				response.getWriter().write("true");
			}
			else {
				response.getWriter().write("false");
			}
			
		}
		else {
			HttpSession session=request.getSession();
			String code=request.getParameter("code");
			String coedString=session.getAttribute("code").toString();
			String phoneString=session.getAttribute("phone").toString();
			if(code.equals(coedString))
			{
				if(phone.equals(phoneString))
				{
					if(selectdata.userphone(phone))
					{
						session.setAttribute("phone", phone);
						response.getWriter().write("true");
					}
					else {
						response.getWriter().write("bucunzai");
					}
				}
				else 
				{
					response.getWriter().write("phone_error");
				}
			}
			else {
				response.getWriter().write("false");
			}
		}
	}

}
