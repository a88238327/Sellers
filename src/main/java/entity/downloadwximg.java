package entity;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class downloadwximg {
	final static public String URL="https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	public static void download(String serverId, String sellerphone) throws Exception {
		String url=URL.replace("ACCESS_TOKEN", getToken.get_ACCESSTOKEN()).replace("MEDIA_ID", serverId);
		
		byte[] b=WeiXinUtil.getFile(url);
		FileOutputStream fos=new FileOutputStream("C:\\apache-tomcat-7.0.92\\webapps\\img\\touxiang\\"+sellerphone+"_touxiang.jpg");
		BufferedOutputStream bos =new BufferedOutputStream(fos);  
		bos.write(b);
		bos.close();
		fos.close();
		
	}
	
	public static void downloadevaluation(String[] serverId,String orderID) throws Exception {
		for(int i=0;i<serverId.length;i++)
		{
			String url=URL.replace("ACCESS_TOKEN", getToken.get_ACCESSTOKEN()).replace("MEDIA_ID", serverId[i]);		
			byte[] b=WeiXinUtil.getFile(url);
			FileOutputStream fos=new FileOutputStream("C:\\apache-tomcat-7.0.92\\webapps\\img\\evaluation\\"+orderID+"_"+i+".jpg");
			String name=orderID+"_"+i+".jpg";
			selectdata.insertevaluationimg(orderID,name);
			BufferedOutputStream bos =new BufferedOutputStream(fos);  
			bos.write(b);
			bos.close();
			fos.close();
		}
		
		
	}

	
}
