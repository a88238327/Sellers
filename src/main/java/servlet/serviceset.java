package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.selectdata;
import entity.updata;

/**
 * Servlet implementation class serviceset
 */
@WebServlet("/serviceset")
public class serviceset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serviceset() {
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
			String jsonString=selectdata.getservicestate(phone);
			response.getWriter().write(jsonString);
		}
		else {
			response.getWriter().write("phone_error");
		}
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
			String name=request.getParameter("name");
			String enable=request.getParameter("enable");
			String phone=session.getAttribute("phone").toString();
			if(enable.equals("启用"))
			{
				String key="enable";
				String value="0";
				updata.updatasller_service(phone,name, key, value);
				response.getWriter().write("停用");
			}
			else {
				String key="enable";
				String value="1";
				updata.updatasller_service(phone,name, key, value);
				response.getWriter().write("启用");
			}
			
		}
		else {
			response.getWriter().write("phone_error");
		}
	
	}

}
