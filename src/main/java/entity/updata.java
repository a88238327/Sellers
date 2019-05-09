package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import mubanxiaoxi.evaluation;

public class updata {

	public static void updatainfo(String dianpuming, String mastername, String servicephone, String address,String lat,String lng,String phone,String touxingpath, String city, String country, String district, String province, String street, String streetNumber, String town, String village) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="update sellers set name='"+dianpuming+"',mastername='"+mastername+"',lat='"+lat+"',lng='"+lng+"',address='"+address+"',enable='1',servicephone='"+servicephone+"',touxiang='"+touxingpath+"',city='"+city+"',country='"+country+"',district='"+district+"',province='"+province+"',street='"+street+"',streetNumber='"+streetNumber+"',town='"+town+"',village='"+village+"' where phone='"+phone+"'";//查询语句
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
			// TODO: handle exception
		}
		
	}

	public static void insertservice(String phone, String servicename) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime="\'"+df.format(new Date())+"\'";
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="insert into seller_service (createtime,name,sellerphone,enable) values ('"+createtime+"','"+servicename+"','"+phone+"',1)";//查询语句
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
			// TODO: handle exception
		}
		
		
	}

	public static void updatauserinfo(String phone, String key, String value) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime="\'"+df.format(new Date())+"\'";
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="update sellers set "+key+"='"+value+"' where phone='"+phone+"'";//查询语句
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
			System.out.println("更新成功");
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public static void insertservicefalse(String phone, String name) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime=df.format(new Date());
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="insert into seller_service (createtime,name,sellerphone,enable) values ('"+createtime+"','"+name+"','"+phone+"',0)";//查询语句
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

	public static void updatasller_service(String phone,String name, String key, String value) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime="\'"+df.format(new Date())+"\'";
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="update seller_service set "+key+"="+value+" where sellerphone='"+phone+"' and name='"+name+"'";//查询语句
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
			System.out.println("更新成功");
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public static void insertorder(String sellerphone, String userphone, String number,String servicename) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime=df.format(new Date());
		String orderID=ID.getOrderId();
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="insert into userorder (createtime,sellerphone,name,type,orderID,status,userphone,号牌号码) values ('"+createtime+"','"+sellerphone+"','"+servicename+"','service','"+orderID+"','已完成','"+userphone+"','"+number+"')";//查询语句
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
		//给用户发送模版消息
		String openid=selectdata.getopenid(userphone);
		String sellername=selectdata.getsellername(sellerphone);
		String url="cloud2.hnjtbf.com/Sellers/evaluation.html?orderID="+orderID;
		evaluation.postserviceevaluation(openid, createtime, servicename, sellername, url);
		
	}

	public static void insertevaluation(String orderID, String starnumber, String evaluation,int Total_photos) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime=df.format(new Date());
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="insert into evaluation (createtime,orderID,star,evaluation,Total_photos) values ('"+createtime+"','"+orderID+"',"+starnumber+",'"+evaluation+"',"+Total_photos+")";//查询语句
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
