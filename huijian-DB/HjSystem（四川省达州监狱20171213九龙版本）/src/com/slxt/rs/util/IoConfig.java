package com.slxt.rs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class IoConfig implements Servlet{

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

	public void init(ServletConfig arg0) throws ServletException {
		Properties properties=new Properties();
		InputStream in=IoConfig.class.getResourceAsStream("/AllConfig.properties");
		try {
			properties.load(in);
			if( properties.getProperty("dbIP")!=null && !properties.getProperty("dbIP").trim().equals("") && properties.getProperty("dbuserName")!=null && !properties.getProperty("dbuserName").trim().equals("")
					&& properties.getProperty("dbpassword")!=null && !properties.getProperty("dbpassword").trim().equals("") && properties.getProperty("dbName")!=null && !properties.getProperty("dbName").trim().equals("")){
				Constant.dbIP= properties.getProperty("dbIP");
				Constant.dbuserName=properties.getProperty("dbuserName");
				Constant.dbpassword=properties.getProperty("dbpassword");
				Constant.dbName=properties.getProperty("dbName");
			}
			Constant.DoorID=properties.getProperty("DoorID");
			Constant.DoorID1=properties.getProperty("DoorID1");
			Constant.PassWord=properties.getProperty("PassWord");
			Constant.DueDate=properties.getProperty("DueDate");
			Constant.AuthorType=properties.getProperty("AuthorType");
			Constant.AuthorStatus=properties.getProperty("AuthorStatus");
			Constant.AuthorStatus1=properties.getProperty("AuthorStatus1");
			Constant.UserTimeGrp=properties.getProperty("UserTimeGrp");
			Constant.DownLoaded=properties.getProperty("DownLoaded");
			Constant.FirstDownLoaded=properties.getProperty("FirstDownLoaded");
			Constant.PreventCard=properties.getProperty("PreventCard");
			Constant.shixiaoshijian=properties.getProperty("shixiaoshijian");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
