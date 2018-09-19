package com.slxt.rs.util;

import java.io.FileInputStream;



import com.pinnacle.face.core.TFaceClientEx.TFaceClientEx;

import com.pinnacle.face.core.base.IFaceClientEx;

import com.slxt.rs.svc.WebServiceImportFrService;

public class WebServiceImportFr {
	static private WebServiceImportFrService wfs;

	public void setWfs(WebServiceImportFrService wfs) {
		this.wfs = wfs;
	}

	public static byte[] encodeImage(String file) {
		FileInputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(file);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void work() {
		IFaceClientEx iFaceClientEx = new TFaceClientEx();
	    boolean isConnect = iFaceClientEx.connect("http://127.0.0.1:8080/PinnacleFace");
	    System.out.println("连接人脸注册系统状态："+isConnect);
	    if(isConnect)
	    {
	    	boolean a = false;
	    	
	    	a = iFaceClientEx.clearAll();
	    	if(a){
	    		System.out.println("清空数据成功1");
	    	}else{
	    		System.out.println("清空数据失败1");
	    	}
	    	

	    }else{
	    	System.out.println("连接系统失败1");
	    }
	}

	
}
