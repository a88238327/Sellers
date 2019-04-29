package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.base64tofile;
import entity.downloadwximg;
import entity.selectdata;
import entity.updata;

/**
 * Servlet implementation class adduserinfo
 */
@WebServlet("/adduserinfo")
public class adduserinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adduserinfo() {
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
		String phone=session.getAttribute("phone").toString();
		//资料未完善
		if(!selectdata.usermaster(phone))
		{
			response.sendRedirect("adduserinfo.html");
		}
		else {
			response.sendRedirect("shouye.html");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String dianpuming=request.getParameter("dianpuming");
		String mastername=request.getParameter("mastername");
		String servicephone=request.getParameter("servicephone");
		String address=request.getParameter("address");
		String lat=request.getParameter("lat");
		String lng=request.getParameter("lng");
		String city=request.getParameter("city");
		String country=request.getParameter("country");
		String district=request.getParameter("district");
		String province=request.getParameter("province");
		String street=request.getParameter("street");
		String streetNumber=request.getParameter("streetNumber");
		String town=request.getParameter("town");
		String village=request.getParameter("village");
		
		HttpSession session=request.getSession();
		if(session.getAttribute("phone")!=null) {
			String phone=session.getAttribute("phone").toString();
			String[] checkboxvalue=request.getParameterValues("checkboxvalue[]");
			String dataurl=request.getParameter("dataurl");
		
			//下载图片并保存为sellerphone_touxiang.jpg文件的头像
			if(base64tofile.GenerateImage(dataurl,"C:\\apache-tomcat-7.0.92\\webapps\\img\\touxiang\\"+phone+"_touxiang.jpg" ))
			{
				System.out.println("头像储存成功");
			}
			updata.updatainfo(dianpuming,mastername,servicephone,address,lat,lng,phone,"http://cloud.hnjtbf.com/img/touxiang/"+phone+"_touxiang.jpg",city,country,district,province,street,streetNumber,town,village);
			for(int i=0;i<checkboxvalue.length;i++)
			{
				String servicename=checkboxvalue[i];
				updata.insertservice(phone,servicename);
			}
			response.getWriter().write("true");
		}
		else {
			response.getWriter().write("phone_error");
		}
	}

}
