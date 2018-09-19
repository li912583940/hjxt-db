package com.slxt.rs.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;



import org.apache.log4j.Logger;

public class Pool implements Serializable{
	  static Logger logger = Logger.getLogger ( Pool.class.getName () ) ;
	    public static Connection getcon(String dbIP,String dbUserName,String dbPassWord,String dbName){

	    	Connection con = null;
	 	   	try {
	 		    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			     con = DriverManager.getConnection("jdbc:sqlserver://"+dbIP+":1433;databaseName="+dbName,dbUserName,dbPassWord);
			     logger.info("数据库con是否连接上:"+con);
			} catch (Exception e) {
				logger.info("连接数据库错误信息时"+e.toString());
				e.printStackTrace();
			}
			return con;
	    }
   
}
