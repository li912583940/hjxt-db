package com.slxt.rs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.pinnacle.face.core.TFaceClientEx.TFaceClientEx;
import com.pinnacle.face.core.base.IFaceClientEx;

import net.sf.json.JSONObject;

public class MyServletSP implements Servlet
{   
//	private  HjDjService hds;
//	public void setHds(HjDjService hds) {
//		this.hds = hds;
//	}
	private Map<String, String> getDataMap(ServletRequest req) throws IOException
	{
		  req.setCharacterEncoding("UTF-8");
		  
		  BufferedReader in     = new BufferedReader(new InputStreamReader(req.getInputStream()));
		  String         line   = null;
		  String         result = "";
		  
		  line = in.readLine();
		  while (line != null)
		  {
		    result = result + line;
		    line = in.readLine();
		  }
		  System.out.println("result--" + result);
		  Map<String, String> data = JSONObject.fromObject(result);
		  return data;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map<String, String> dataMap = getDataMap(req);
  
  
		if(dataMap.get("isFinish") != null && (dataMap.get("finishState")!= null && dataMap.get("finishState").equals("cancel")))
		{
			String id          = null;
			id            	   = dataMap.get("id");
			String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			//SQL数据库引擎
			//String connectDB="jdbc:sqlserver://127.0.0.1:1433;databaseName=TeleJY_GD_mingkang_hj20160927";
			String connectDB="jdbc:sqlserver://127.0.0.1:1433;databaseName=TeleJY";
			//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
			// 我将在下面详述
			try {
				//加载数据库引擎，返回给定字符串名的类
				Class.forName(JDriver);
			}catch(ClassNotFoundException e)
			{
				//e.printStackTrace();
				System.out.println("加载数据库引擎失败");
				System.exit(0);
			}     
			    System.out.println("数据库驱动成功");
			       
			    try {
			           String user="sa";                                    
			           //这里只要注意用户名密码不要写错即可
			           String password="sa";
			           Connection con=DriverManager.getConnection(connectDB,user,password);
			           //连接数据库对象
			           System.out.println("连接数据库成功");
			           Statement stmt=con.createStatement();
			           //创建SQL命令对象
			        				 		
			            String str1="update jl_hj_sp_qs set face_state = 2 where face_id = "+Integer.parseInt(id)+"";    
						stmt.executeUpdate(str1);
						System.out.println("更新会见家属人脸注册状态成功");
			            System.out.println("读取完毕");
			        
			        //关闭连接
			        stmt.close();//关闭命令对象连接
			        con.close();//关闭数据库连接
			     }catch(SQLException e){
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        //System.out.println("数据库连接错误");
			        System.exit(0);
			     }
			
		}else if(dataMap.get("isFinish") != null && (dataMap.get("finishState")!= null && dataMap.get("finishState").equals("duplicate")))
		{
			String id          = null;
			id            	   = dataMap.get("id");
			String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			//SQL数据库引擎
			//String connectDB="jdbc:sqlserver://127.0.0.1:1433;databaseName=TeleJY_GD_mingkang_hj20160927";
			String connectDB="jdbc:sqlserver://127.0.0.1:1433;databaseName=TeleJY";
			//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
			// 我将在下面详述
			try {
				//加载数据库引擎，返回给定字符串名的类
				Class.forName(JDriver);
			}catch(ClassNotFoundException e)
			{
				//e.printStackTrace();
				System.out.println("加载数据库引擎失败");
				System.exit(0);
			}     
			    System.out.println("数据库驱动成功");
			       
			    try {
			           String user="sa";                                    
			           //这里只要注意用户名密码不要写错即可
			           String password="sa";
			           Connection con=DriverManager.getConnection(connectDB,user,password);
			           //连接数据库对象
			           System.out.println("连接数据库成功");
			           Statement stmt=con.createStatement();
			           //创建SQL命令对象
			        				 		
			            String str1="update jl_hj_sp_qs set face_state = 3 where face_id = "+Integer.parseInt(id)+"";    
						stmt.executeUpdate(str1);
						System.out.println("更新会见家属人脸注册状态成功");
			            System.out.println("读取完毕");
			        
			        //关闭连接
			        stmt.close();//关闭命令对象连接
			        con.close();//关闭数据库连接
			     }catch(SQLException e){
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        //System.out.println("数据库连接错误");
			        System.exit(0);
			     }
			
		}else if(dataMap.get("isFinish") != null && (dataMap.get("finishState")!= null && dataMap.get("finishState").equals("timeout")))
		{
			String id          = null;
			id            	   = dataMap.get("id");
			String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			//SQL数据库引擎
			//String connectDB="jdbc:sqlserver://127.0.0.1:1433;databaseName=TeleJY_GD_mingkang_hj20160927";
			String connectDB="jdbc:sqlserver://127.0.0.1:1433;databaseName=TeleJY";
			//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
			// 我将在下面详述
			try {
				//加载数据库引擎，返回给定字符串名的类
				Class.forName(JDriver);
			}catch(ClassNotFoundException e)
			{
				//e.printStackTrace();
				System.out.println("加载数据库引擎失败");
				System.exit(0);
			}     
			    System.out.println("数据库驱动成功");
			       
			    try {
			           String user="sa";                                    
			           //这里只要注意用户名密码不要写错即可
			           String password="sa";
			           Connection con=DriverManager.getConnection(connectDB,user,password);
			           //连接数据库对象
			           System.out.println("连接数据库成功");
			           Statement stmt=con.createStatement();
			           //创建SQL命令对象
			        				 		
			            String str1="update jl_hj_sp_qs set face_state = 4 where face_id = "+Integer.parseInt(id)+"";    
						stmt.executeUpdate(str1);
						System.out.println("更新会见家属人脸注册状态成功");
			            System.out.println("读取完毕");
			        
			        //关闭连接
			        stmt.close();//关闭命令对象连接
			        con.close();//关闭数据库连接
			     }catch(SQLException e){
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        //System.out.println("数据库连接错误");
			        System.exit(0);
			     }
			
		}else if(dataMap.get("isFinish") != null && (dataMap.get("finishState")!= null && dataMap.get("finishState").equals("success")))
		{
			System.out.println("进入注册成功流程开始。。。。");
			String photo       = null;
			String faceFeature = null;
			String id          = null;
     
			if(dataMap.get("photo") != null && dataMap.get("faceFeature") != null)
			{
				id            = dataMap.get("id");
				photo         = dataMap.get("photo");
				faceFeature   = dataMap.get("faceFeature");
 
				IFaceClientEx faceClient = new TFaceClientEx();
				boolean       isConnect  = faceClient.connect("http://127.0.0.1:8080/PinnacleFace");
 
				if(isConnect)
				{
					String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
					//SQL数据库引擎
					//String connectDB="jdbc:sqlserver://127.0.0.1:1433;databaseName=TeleJY_GD_mingkang_hj20160927";
					String connectDB="jdbc:sqlserver://127.0.0.1:1433;databaseName=TeleJY";
					//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
					// 我将在下面详述
					try {
						//加载数据库引擎，返回给定字符串名的类
						Class.forName(JDriver);
					}catch(ClassNotFoundException e)
					{
						//e.printStackTrace();
						System.out.println("加载数据库引擎失败");
						System.exit(0);
					}     
					    System.out.println("数据库驱动成功");
					       
					    try {
					           String user="sa";                                    
					           //这里只要注意用户名密码不要写错即可
					           String password="sa";
					           Connection con=DriverManager.getConnection(connectDB,user,password);
					           //连接数据库对象
					           System.out.println("连接数据库成功");
					           Statement stmt=con.createStatement();
					           //创建SQL命令对象
					        
					        
				            
					            //读取数据
					            System.out.println("开始读取数据");
					            ResultSet rs=stmt.executeQuery("select * from jl_hj_sp_qs jl where jl.face_Id="+Integer.parseInt(id)+"");
					            //返回SQL语句查询结果集(集合)
					            //循环输出每一条记录
					            while(rs.next()){
					            	//输出每个字段
					            	//System.out.println(rs.getString("")+"\t"+rs.getString("NAME"));
					            	long num = faceClient.addPersonInfo(id, rs.getString("QS_Info"), false, faceFeature, photo, "0123456789");         
									System.out.println("num："+num);
									System.out.println("调用添加人员人脸信息成功");
					            }
					            
//					            sun.misc.BASE64Decoder decode=new sun.misc.BASE64Decoder();
//						 		byte[] datas=decode.decodeBuffer(photo.substring(1, photo.length()-2));
					            
					           
						 		
					            String str1="update jl_hj_sp_qs set face_state = 1 where face_id = "+Integer.parseInt(id)+"";    
								stmt.executeUpdate(str1);
								System.out.println("更新会见家属人脸注册状态成功");
					            System.out.println("读取完毕");
					        
					        //关闭连接
					        stmt.close();//关闭命令对象连接
					        con.close();//关闭数据库连接
					     }catch(SQLException e){
					        e.printStackTrace();
					        System.out.print(e.getErrorCode());
					        //System.out.println("数据库连接错误");
					        System.exit(0);
					     }
					
					
				}
			}
			System.out.println("进入注册成功流程结束。。。。");
		}else
		{
			System.out.println("系统异常sp。。。。");
		}
	}
	
}


