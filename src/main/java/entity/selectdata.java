package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jdk.nashorn.api.scripting.JSObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class selectdata {

	public static boolean userphone(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select * from sellers where phone='"+phone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return true;//手机号已存在
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean userphonepassword(String phone, String password) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select * from sellers where phone='"+phone+"' and password='"+password+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return true;//手机号已存在
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean usermaster(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select mastername from sellers where phone='"+phone+"' and mastername is not null";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return true;//手机号已存在
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static String getservice(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select name from seller_service where sellerphone='"+phone+"' and enable='1'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		HashMap<String , String > map=new HashMap<String, String>();
		List list= new ArrayList();	
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{			
	
				list.add(rs.getString("name"));
//				return true;//手机号已存在
			}
			if(list.isEmpty())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return "null";
			}
		
			
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		JSONArray jsonObject=JSONArray.fromObject(list);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	public static String getservicestate(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select service_name.name,seller_service.enable from service_name LEFT JOIN seller_service on service_name.name=seller_service.name and seller_service.sellerphone='"+phone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		List<serviceset> list=new ArrayList<serviceset>();
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			while(rs.next())
			{		
			
				if(rs.getString("enable")==null)
				{
					list.add(new serviceset(rs.getString("name"), "0"));
					updata.insertservicefalse(phone, rs.getString("name"));
				}
				else {
					
					list.add(new serviceset(rs.getString("name"), rs.getString("enable")));
				}
			}
			if(list.isEmpty())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return "null";
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		
		JSONArray jsonObject=JSONArray.fromObject(list);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	public static boolean usercar(String number) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select 号牌号码 from cars where 号牌号码='"+number+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				return true;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public static String getuserphone(String number) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select phone from cars where 号牌号码='"+number+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				String phone=rs.getString("phone");
				return phone;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getopenid(String userphone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select openid from customer where USER_FORM_INFO_FLAG_MOBILE='"+userphone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				String openid=rs.getString("openid");
				return openid;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getsellername(String sellerphone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select name from sellers where phone='"+sellerphone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				String name=rs.getString("name");
				return name;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getsellerphone(String orderID) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select sellerphone from userorder where orderID='"+orderID+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				String sellerphone=rs.getString("sellerphone");
				return sellerphone;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getsellerinfo(String sellerphone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select name,touxiang from sellers where phone='"+sellerphone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				HashMap<String , String > map=new HashMap<String, String>();
				map.put("name", rs.getString("name"));
				map.put("touxiang", rs.getString("touxiang"));
				JSONObject jsonObject=JSONObject.fromObject(map);
				return jsonObject.toString();
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static boolean selectevaluation(String orderID) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select orderID from evaluation where orderID='"+orderID+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return false;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}


    public static void insertevaluationimg(String orderID, String name) {
		Connection conn=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime=df.format(new Date());
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="insert into evaluation_imgs (createtime,name,orderID) values('"+createtime+"','"+name+"','"+orderID+"')";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
		    Class.forName(driver);//加载驱动器类
		    conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
		    //建立处理的SQL语句
		    pstmt = conn.prepareStatement(sql) ;
		    System.out.println(pstmt.toString());
		    int resultString=pstmt.executeUpdate();
		    pstmt.close();//关闭SQL语句集
		    conn.close();//关闭连接
		    System.out.println("添加信息成功");
		}catch (Exception e) {
		    System.out.println(e);
		}
    }
}
