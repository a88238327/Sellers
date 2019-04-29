package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.selectdata;
import entity.updata;
import mubanxiaoxi.evaluation;

/**
 * Servlet implementation class startservice
 */
@WebServlet("/startservice")
public class startservice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public startservice() {
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
		HttpSession session=request.getSession();
		if(session.getAttribute("phone")!=null)
		{
			String sellerphone=session.getAttribute("phone").toString();
			String number=request.getParameter("number");
			if(selectdata.usercar(number))
			{
				String servicename=request.getParameter("servicename");
				String userphone=selectdata.getuserphone(number);
				updata.insertorder(sellerphone, userphone, number, servicename);
				response.getWriter().write("true");
			}
			else {
				response.getWriter().write("bucunzai");
			}
		}
		else {
			response.getWriter().write("phone_error");
		}
	}

}
