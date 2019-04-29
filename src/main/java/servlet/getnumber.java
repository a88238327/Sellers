package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Util;
import entity.WeiXinUtil;
import entity.base64tofile;
import entity.getToken;
import entity.ocr;
import entity.sysDate;
import entity.tobyte;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class getnumber
 */
@WebServlet("/getnumber")
public class getnumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getnumber() {
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
		String serverId=request.getParameter("serverId");
		BASE64Decoder d = new BASE64Decoder();
        byte[] data = d.decodeBuffer(serverId);
        HttpSession session=request.getSession();
        String phone=session.getAttribute("phone").toString();
        String str="";
        base64tofile.GenerateImage(serverId, "C:\\apache-tomcat-7.0.92\\webapps\\getnumberimg\\"+phone+".jpg");
		try {
			str=ocr.ocr_plateLicense(data);
			System.out.println(str);
		} catch (Exception e) {
			System.out.println(e);
		}
		JSONObject jsonObject=JSONObject.fromObject(str);
		if(jsonObject.has("words_result"))
		{
			String list=jsonObject.getString("words_result");
			JSONObject jsonObject2=JSONObject.fromObject(list);
			response.getWriter().write(jsonObject2.getString("number"));
			System.out.println(jsonObject2.getString("number"));
		}
		else {
			response.getWriter().write("error");
		}
		
		
	}

}
