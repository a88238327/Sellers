package mubanxiaoxi;

import entity.getToken;

public class evaluation{

	public static void postserviceevaluation(String openid,String createtime,String servicename,String sellername,String url){
		String str="{\r\n" + 
				"	\"touser\":\"OPENID\",\r\n" + 
				"	\"template_id\":\"TEMPLATE_ID\",\r\n" + 
				"	\"url\":\"URL\",\r\n" + 
				"	\"data\":{\r\n" + 
				"		\"first\":{\r\n" + 
				"			\"value\":\"您有一份订单未评价\"\r\n" + 
				"		},\r\n" + 
				"		\"keyword1\":{\r\n" + 
				"			\"value\":\"KEYWORD1\"\r\n" + 
				"		},\r\n" + 
				"		\"keyword2\":{\r\n" + 
				"			\"value\":\"KEYWORD2\"\r\n" + 
				"		},\r\n" + 
				"		\"keyword3\":{\r\n" + 
				"			\"value\":\"KEYWORD3\"\r\n" + 
				"		},\r\n" + 
				"		\"remark\":{\r\n" + 
				"			\"value\":\"感谢您的使用，本次评价为匿名评价\"\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"}";
		//System.out.println(str);
		str=str.replace("OPENID", openid);
		str=str.replace("TEMPLATE_ID", "UaeLy1H-juqBU0mbO25d3ggDNqw2R9QF1s6I2HFyZZU");
		str=str.replace("KEYWORD1", servicename);
		str=str.replace("KEYWORD2", createtime);
		str=str.replace("KEYWORD3", sellername);
		str=str.replace("URL", url);
		String urlString="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		String resultString=entity.Util.post(urlString.replace("ACCESS_TOKEN", getToken.get_ACCESSTOKEN()), str);
		System.out.println(resultString);
	}
}
