package com.slxt.rs.action;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysMenu;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.Rsservice;

//public class LoginAction extends DispatchAction implements Printable{
public class LoginAction extends DispatchAction{
	private Rsservice rs;
	public void setRs(Rsservice rs) {
		this.rs = rs;
	}
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {//登陆验证
			String username = java.net.URLDecoder.decode((String)request.getParameter("aa"),"UTF-8") ;
			String password = java.net.URLDecoder.decode((String)request.getParameter("bb"),"UTF-8") ;
			List list=rs.login(username, password);
			if(list.size()>0){
				SysUser users=(SysUser)list.get(0);
				Calendar c = Calendar.getInstance();   
			    SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
			    c  =  Calendar.getInstance(Locale.CHINESE);   
			    String loginTime = simpleDateTimeFormat.format(c.getTime());
			    SysLog sl=new SysLog();
			    sl.setType("正常");
			    sl.setLogTime(loginTime);
			    sl.setInfo("用户"+users.getUserNo()+"登录");
			    sl.setModel("用户登录");
			    sl.setOp("登录");
			    sl.setUserName(users.getUserName());
			    sl.setUserNo(users.getUserNo());
			    sl.setUserIp(request.getRemoteAddr());
			    rs.save(sl);
			    request.getSession().setAttribute("loginTime", loginTime);
				request.getSession().setAttribute("users", users);
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}else{
				Calendar c = Calendar.getInstance();   
	      	    SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
	      	    c  =  Calendar.getInstance(Locale.CHINESE);   
	      	    String loginTime = simpleDateTimeFormat.format(c.getTime());
	      	    SysLog sl=new SysLog();
	      	    sl.setType("严重");
	      	    sl.setLogTime(loginTime);
	      	    sl.setInfo("用户"+username+"登录密码错误");
	      	    sl.setModel("用户登录");
	      	    sl.setOp("密码错误");
	      	    sl.setUserName("");
	      	    sl.setUserNo(username);
	      	    sl.setUserIp(request.getRemoteAddr());
	      	    rs.save(sl);
	        	JSONArray json=JSONArray.fromObject(1);
	        	response.getWriter().println(json.toString());
			}
			return null;
	}
	//用户登陆成功后 查询每个用户的权限菜单 
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser sys_user=(SysUser)request.getSession().getAttribute("users");
		    if(sys_user!=null){
		    	List<SysMenu> list=null;
		    	if(sys_user.getIsSuper()==1) {
		    		String hql="from SysMenu sm where 1=1 order by sm.menuIndex";
		    		list=rs.searchAll(hql);
		    		request.setAttribute("listMenu", list);
		    	}else{
		    		if(sys_user.getGroupNo().equals("Admin")){
		    			String hql="from SysMenu sm where 1=1 order by sm.menuIndex";
		    			list=rs.searchAll(hql);
		    			request.setAttribute("listMenu", list);
		    		}else{
		    			StringBuffer str=new StringBuffer("select sm.webId,sm.menuNo,sm.menuName,sm.menuUrl,sm.menuIndex,sm.menuAdmin from SysMenu sm,SysUserMenu sp,SysUser su  where su.groupNo=sp.groupNo and sm.menuNo=sp.menuNo");
		    			str.append(" and su.groupNo='"+sys_user.getGroupNo()+"'");
		    			str.append(" and su.userNo='"+sys_user.getUserNo()+"'");
		    			str.append(" order by sm.menuIndex");
		    			list=rs.searchAll(str.toString());  
		    			List newList = new ArrayList() ;
		    			Iterator it=list.iterator();
		    			while(it.hasNext()){
		    				Object ob[] = (Object[]) it.next() ;
		    				SysMenu sm= new SysMenu() ;
		    				sm.setWebId(Integer.valueOf(ob[0].toString())) ;
		    				sm.setMenuNo(ob[1].toString()) ;
		    				sm.setMenuName(ob[2].toString()) ;
		    				sm.setMenuUrl(ob[3].toString()) ;
		    				sm.setMenuIndex(Integer.valueOf(ob[4].toString())) ;
		    				sm.setMenuAdmin(Integer.valueOf(ob[5].toString())) ;
		    				newList.add(sm);
		    			}
		    			request.getSession().setAttribute("listMenu", newList);  
		    		}
		    	}
		    }
		    return mapping.findForward("success");
	}
	public ActionForward uploadfj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			if(request.getParameter("folder")==null||request.getParameter("folder")==""){
				return null;
			}
			String sqid=request.getParameter("sqid");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			String path = "D:\\upload";
			String hjid=sqid;
			String sourcePath = path + "\\"+hjid;
			File folder = new File(path);
			File sourceFolder = new File(sourcePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			if (!sourceFolder.exists()) {
				sourceFolder.mkdirs();
			}
			ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
			sfu.setHeaderEncoding("UTF-8");
			try {
				List<?> fileList = sfu.parseRequest(request);
				String sourceName = "";
				String extName = "";
				String name = "";
				String sfileName = "";
				for (int i = 0; i < fileList.size(); i++) {
					FileItem fi = (FileItem) fileList.get(i);
					if (!fi.isFormField()) {
						sourceName = fi.getName();
						if (sourceName == null || "".equals(sourceName.trim())) {
							continue;
						}
						if (sourceName.lastIndexOf(".") >= 0) {
							name = sourceName.substring(0,sourceName.lastIndexOf("."));
							extName = sourceName.substring(sourceName.lastIndexOf("."));
						}
		
						sfileName = name  + extName;
						File saveSourceFile = new File(sourcePath +"\\"+ sfileName);
						fi.write(saveSourceFile);
						
					}
				}
			
				String scName=sourcePath +"\\"+ sfileName;
				scName = scName.replace(":", "");
				scName = scName.replace("\\", "/");
				response.getWriter().println(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+scName);
			} catch (FileUploadException e) {
				response.getWriter().println("0");
				e.printStackTrace();
			} catch (Exception e) {
				response.getWriter().println("0");
				e.printStackTrace();
			}
			return null;
	}
}
