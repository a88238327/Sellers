package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.downloadwximg;
import entity.selectdata;
import entity.updata;

/**
 * Servlet implementation class evaluation
 */
@WebServlet("/evaluation")
public class evaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public evaluation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String orderID=request.getParameter("orderID");
		String sellerphone=selectdata.getsellerphone(orderID);
		if(selectdata.selectevaluation(orderID))
		{
			//通过商家账号查询商家的头像跟名称并返回json
			String str=selectdata.getsellerinfo(sellerphone);
			response.getWriter().write(str);
			System.out.println(str);
		}
		else {
			response.getWriter().write("yipingjia");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String orderID=request.getParameter("orderID");
        String starnumber=request.getParameter("starnumber");
        String text=request.getParameter("text");
     
        String[] serverId=request.getParameterValues("serverId");
    
        System.out.println(serverId);
        int Total_photos=0;
        if(serverId!=null)
        {
        	Total_photos=serverId.length;
        }		
        String evaluation=text;
        updata.insertevaluation(orderID,starnumber,evaluation,Total_photos);
     
        try {
        	if(Total_photos>0)
        	{
        		downloadwximg.downloadevaluation(serverId, orderID);
        	}
			
		} catch (Exception e) {
			System.out.println(e);
		}
        response.getWriter().write("true");
	}

}
