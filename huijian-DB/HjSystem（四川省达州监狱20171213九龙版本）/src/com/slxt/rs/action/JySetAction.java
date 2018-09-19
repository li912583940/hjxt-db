package com.slxt.rs.action;

import java.text.SimpleDateFormat;
import java.util.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.pinnacle.face.core.TFaceClientEx.TFaceClientEx;
import com.pinnacle.face.core.base.IFaceClientEx;

import com.slxt.rs.model.SysHjServer;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.JySetService;
import com.slxt.rs.vo.HJSPParam;

public class JySetAction extends DispatchAction{
	private JySetService jss;
	public void setJss(JySetService jss) {
		this.jss = jss;
	}
    //查询服务器表
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
    
		    String hql="from SysHjServer";
		    List<SysHjServer> list=jss.searchAll(hql);
		    request.setAttribute("sysHjServerList", list);
		    return mapping.findForward("jySetMain");
	}
	
	public ActionForward getFaceInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			
	      	return null;
	     
	}
	
//	public Map<String, String> getDataMap(HttpServletRequest request) throws IOException{
//	      request.setCharacterEncoding("UTF-8");
//	      
//	      BufferedReader in     = new BufferedReader(new InputStreamReader(request.getInputStream()));
//	      String         line   = null;
//	      String         result = "";
//	      
//	      line = in.readLine();
//	      while (line != null)
//	      {
//	        result = result + line;
//	        line = in.readLine();
//	      }
////	      result = URLDecoder.decode(result, "utf-8");
//	      System.out.println("result--" + result);
//	      Map<String, String> data = JSONObject.fromObject(result);
//	      return data;
//
//	}
	
	//修改服务器表
	public ActionForward updateJySet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    
		    
		      
		    String jyId=request.getParameter("jyId");
		    SysHjServer sysQqServer=(SysHjServer)jss.findById(SysHjServer.class, Integer.parseInt(jyId));
		    request.setAttribute("sysQqServer", sysQqServer);
		    return mapping.findForward("updateJySet");
	}
	//保存服务器修改后设置
	public ActionForward saveJySet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  	SysUser user=(SysUser)request.getSession().getAttribute("users");
		  	if(user==null){
		  		return mapping.findForward("sessionFailure");
		  	}
		  	String jyId=request.getParameter("jyId");
		  	SysHjServer sysQqServer=(SysHjServer)jss.findById(SysHjServer.class,Integer.parseInt(jyId));
		  	String port=request.getParameter("port");
		  	String audioPort=request.getParameter("audioPort");
		  	String serverIP = java.net.URLDecoder.decode((String)request.getParameter("serverIP"),"UTF-8") ;
		  	String recordURL = java.net.URLDecoder.decode((String)request.getParameter("recordURL"),"UTF-8") ;
		  	StringBuffer str=new StringBuffer("update SysHjServer sqs set sqs.ip='");
		  	str.append(serverIP+"',");
		  	StringBuffer str1=new StringBuffer("");
		  	if(!sysQqServer.getIp().trim().equals(serverIP.trim())){
		  		str1.append("服务器IP由"+sysQqServer.getIp()+"修改为"+serverIP);
			}
		  	if(recordURL!=""){
				str.append("sqs.recUrl='"+recordURL);
			    str.append("',");
				if(!sysQqServer.getRecUrl().trim().equals(recordURL.trim())){
					  str1.append("  服务器URL"+sysQqServer.getRecUrl()+"修改为"+recordURL);
				}
		  	}else{
				str.append("sqs.recUrl='',");
				if(!sysQqServer.getRecUrl().trim().equals(recordURL.trim())){
					  str1.append("  服务器URL"+sysQqServer.getRecUrl()+"修改为"+recordURL);
				}
		  	}
		  	str.append("sqs.port="+Integer.parseInt(port)+",");
		  	if(sysQqServer.getPort()!=Integer.parseInt(port.trim())){
				str1.append("  服务器状态端口"+sysQqServer.getPort()+"修改为"+port);
		  	}
		  	str.append("sqs.audioPort="+Integer.parseInt(audioPort));
		  	if(sysQqServer.getAudioPort()!=Integer.parseInt(audioPort.trim())){
				str1.append("  服务器监听端口"+sysQqServer.getAudioPort()+"修改为"+audioPort);
		  	}
		  	str.append(" where sqs.webId=?");
		  	Object[] obj={Integer.parseInt(jyId)};
		  	jss.updates(str.toString(), obj);
		  	if(!str1.toString().equals("")){
				Calendar c = Calendar.getInstance();   
				SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
				c  =  Calendar.getInstance(Locale.CHINESE);   
				String loginTime = simpleDateTimeFormat.format(c.getTime());
				SysLog sl=new SysLog();
				sl.setType("正常");
				sl.setLogTime(loginTime);
				sl.setUserName(user.getUserName());
				sl.setUserNo(user.getUserNo());
				sl.setUserIp(request.getRemoteAddr());
				sl.setModel("系统参数");
			    sl.setInfo(str1.toString());
			    sl.setOp("系统参数");
			    jss.save(sl);
		  	}
		  	JSONArray json=JSONArray.fromObject(null);
		  	response.getWriter().println(json.toString());
		  	return null;
	}
	
	//跳转到会见审批参数设置页面
	public ActionForward toHjSpParam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  	SysUser user=(SysUser)request.getSession().getAttribute("users");
		  	if(user==null){
		  		return mapping.findForward("sessionFailure");
		  	}
		  	String sql="select Total_Switch,Mon_Num_Switch,JB_Switch,Ban_Switch,JQ_Ban_Switch,QSGX_Switch from JL_HJ_SP_PARAM";
		  	List list = jss.searchAllBySql(sql);
		  	if(list.size()>0){
		  		Object[] obj = (Object[]) list.get(0);
		  		HJSPParam hjspParam = new HJSPParam();
		  		if(obj[0]!=null && !obj[0].toString().equals("")){
		  			hjspParam.setTotalSwitch(obj[0].toString());
		  		}
		  		if(obj[1]!=null && !obj[1].toString().equals("")){
		  			hjspParam.setMonNumSwitch(obj[1].toString());
		  		}
		  		if(obj[2]!=null && !obj[2].toString().equals("")){
		  			hjspParam.setJbSwitch(obj[2].toString());
		  		}
		  		if(obj[3]!=null && !obj[3].toString().equals("")){
		  			hjspParam.setBanSwitch(obj[3].toString());
		  		}
		  		if(obj[4]!=null && !obj[4].toString().equals("")){
		  			hjspParam.setJqBanSwitch(obj[4].toString());
		  		}
		  		if(obj[5]!=null && !obj[5].toString().equals("")){
		  			hjspParam.setQsgxSwitch(obj[5].toString());
		  		}
		  		request.setAttribute("hjspParam", hjspParam);
		  	}
		  	
		  	return mapping.findForward("hjSpParam");
	}
	
	//保存修改后的会见审批参数设置
	public ActionForward saveHjSpParam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  	SysUser user=(SysUser)request.getSession().getAttribute("users");
		  	if(user==null){
		  		return mapping.findForward("sessionFailure");
		  	}
		  	Integer totalSwitch1=Integer.parseInt(request.getParameter("totalSwitch1").trim());
		  	Integer monNumSwitch1=Integer.parseInt(request.getParameter("monNumSwitch1").trim());
		  	Integer jbSwitch1=Integer.parseInt(request.getParameter("jbSwitch1").trim());
		  	Integer banSwitch1=Integer.parseInt(request.getParameter("banSwitch1").trim());
		  	Integer jqBanSwitch1=Integer.parseInt(request.getParameter("jqBanSwitch1").trim());
		  	Integer qsgxSwitch1=Integer.parseInt(request.getParameter("qsgxSwitch1").trim());
		  	String sql="update JL_HJ_SP_PARAM set Total_Switch="+totalSwitch1+", Mon_Num_Switch="+monNumSwitch1+", JB_Switch="+jbSwitch1+", Ban_Switch="+banSwitch1+", JQ_Ban_Switch="+jqBanSwitch1+", QSGX_Switch="+qsgxSwitch1;
		  	jss.executeUpdate(sql);
		  	JSONArray json=JSONArray.fromObject(0);
		  	response.getWriter().println(json.toString());
		  	return null;
	}
	//清空第一台人脸注册设备上的人脸数据
	public ActionForward clearFace(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  	SysUser user=(SysUser)request.getSession().getAttribute("users");
		  	if(user==null){
		  		return mapping.findForward("sessionFailure");
		  	}
		 
		  	IFaceClientEx iFaceClientEx = new TFaceClientEx();
		    boolean isConnect = iFaceClientEx.connect("http://127.0.0.1:8080/PinnacleFace");
		    System.out.println("连接人脸注册系统状态："+isConnect);
		    if(isConnect)
		    {
		    	boolean a = false;
		    	a = iFaceClientEx.clearDeviceInfo("1070000034765", true);
		    	if(a){
		    		JSONArray json=JSONArray.fromObject(0);
					response.getWriter().println(json.toString());
					return null;
		    	}else{
		    		JSONArray json=JSONArray.fromObject(1);
					response.getWriter().println(json.toString());
					return null;
		    	}
		    	

		    }else{
		    	JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
				return null;
		    }


	}
	//清空第二台人脸注册设备上的人脸数据
	public ActionForward clearFace1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  	SysUser user=(SysUser)request.getSession().getAttribute("users");
		  	if(user==null){
		  		return mapping.findForward("sessionFailure");
		  	}
		 
		  	IFaceClientEx iFaceClientEx = new TFaceClientEx();
		    boolean isConnect = iFaceClientEx.connect("http://127.0.0.1:8080/PinnacleFace");
		    System.out.println("连接人脸注册系统状态："+isConnect);
		    if(isConnect)
		    {
		    	boolean a = false;
		    	a = iFaceClientEx.clearDeviceInfo("1070000034261", true);
		    	if(a){
		    		JSONArray json=JSONArray.fromObject(0);
					response.getWriter().println(json.toString());
					return null;
		    	}else{
		    		JSONArray json=JSONArray.fromObject(1);
					response.getWriter().println(json.toString());
					return null;
		    	}
		    	

		    }else{
		    	JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
				return null;
		    }


	}
	//清空第一台人脸识别设备上的人脸数据
	public ActionForward clearFace2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  	SysUser user=(SysUser)request.getSession().getAttribute("users");
		  	if(user==null){
		  		return mapping.findForward("sessionFailure");
		  	}
		 
		  	IFaceClientEx faceClientEx = new TFaceClientEx();
		    boolean isConnect = faceClientEx.connect("http://127.0.0.1:8080/PinnacleFace");
		    System.out.println("连接人脸注册系统状态："+isConnect);
		    if(isConnect)
		    {
		    	boolean a = false;
		    	a = faceClientEx.clearDeviceInfo("1070000035433", true);
		    	if(a){
		    		JSONArray json=JSONArray.fromObject(0);
					response.getWriter().println(json.toString());
					return null;
		    	}else{
		    		JSONArray json=JSONArray.fromObject(1);
					response.getWriter().println(json.toString());
					return null;
		    	}
		    	

		    }else{
		    	JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
				return null;
		    }


	}
	//清空第二台人脸识别设备上的人脸数据
	public ActionForward clearFace3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  	SysUser user=(SysUser)request.getSession().getAttribute("users");
		  	if(user==null){
		  		return mapping.findForward("sessionFailure");
		  	}
		 
		  	IFaceClientEx faceClientEx = new TFaceClientEx();
		    boolean isConnect = faceClientEx.connect("http://127.0.0.1:8080/PinnacleFace");
		    System.out.println("连接人脸注册系统状态："+isConnect);
		    if(isConnect)
		    {
		    	boolean a = false;
		    	a = faceClientEx.clearDeviceInfo("1070000037717", true);
		    	if(a){
		    		JSONArray json=JSONArray.fromObject(0);
					response.getWriter().println(json.toString());
					return null;
		    	}else{
		    		JSONArray json=JSONArray.fromObject(1);
					response.getWriter().println(json.toString());
					return null;
		    	}
		    	

		    }else{
		    	JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
				return null;
		    }


	}
	//清空所有人脸设备上的数据
	public ActionForward clearFace4(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  	SysUser user=(SysUser)request.getSession().getAttribute("users");
		  	if(user==null){
		  		return mapping.findForward("sessionFailure");
		  	}
		 
			IFaceClientEx faceClientEx = new TFaceClientEx();
		    boolean isConnect = faceClientEx.connect("http://127.0.0.1:8080/PinnacleFace");
		    System.out.println("连接人脸注册系统状态："+isConnect);
		    if(isConnect)
		    {
		    	boolean a = false; 
		    	a  = faceClientEx.clearAll();
		    	if(a){
		    		JSONArray json=JSONArray.fromObject(0);
					response.getWriter().println(json.toString());
					return null;
		    	}else{
		    		JSONArray json=JSONArray.fromObject(1);
					response.getWriter().println(json.toString());
					return null;
		    	}
		    	

		    }else{
		    	JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
				return null;
		    }


	}
}
