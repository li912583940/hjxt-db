package com.slxt.rs.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.slxt.rs.form.AccessControlMessageForm;
import com.slxt.rs.form.CancelDjForm;
import com.slxt.rs.form.CardMessageForm;
import com.slxt.rs.model.JlJq;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.AccessControlMessageService;
import com.slxt.rs.svc.CardMessageService;
import com.slxt.rs.util.Constant;
import com.slxt.rs.vo.AccessControlMessageVO;
import com.slxt.rs.vo.CardMessageVO;
import com.slxt.rs.vo.HjDjVO;
import com.slxt.rs.vo.Page;

public class AccessControlMessagAction extends DispatchAction{
	private AccessControlMessageService acms;

	public void setAcms(AccessControlMessageService acms) {
		this.acms = acms;
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			AccessControlMessageForm cmf=(AccessControlMessageForm) form;
			Page page = new Page();
			page.setPageNo(1);
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			if (cmf.getCallTimeEnd() != null || cmf.getCallTimeStart() != null  || (cmf.getState() != null && !cmf.getState().equals("null"))  ||  (cmf.getJqNo() != null && !cmf.getJqNo().equals("null"))) {
				StringBuffer str=new StringBuffer("select jl.HJID,jl.state,jl.InTime,jl.OutTime,jl.WebID,dj.FR_No,dj.FR_Name,dj.JQ_Name,dj.YJ_No,dj.YJ_Name,DATEDIFF(Minute,jl.InTime,jl.OutTime) as a from JL_HJ_ACCESS_CONTROL_INFO jl,JL_HJ_DJ dj where jl.HJID=dj.HJID and jl.KzqAddress=4 and jl.DianAddress=1 and jl.HJID is not null");
				if (cmf.getCallTimeStart() != null && !cmf.getCallTimeStart().trim().equals("")) {
					str.append(" and jl.InTime>='"+ cmf.getCallTimeStart() + "'");
					todayBegin = cmf.getCallTimeStart();
				}
				if (cmf.getCallTimeEnd() != null && !cmf.getCallTimeEnd().trim().equals("")) {
					str.append(" and jl.InTime<='"+ cmf.getCallTimeEnd() + "'");
					todayEnd = cmf.getCallTimeEnd();
				}
				if (cmf.getJqNo() != null && !cmf.getJqNo().equals("null")) {
					str.append(" and dj.JQ_No='" + cmf.getJqNo() + "'");
				}
				if (cmf.getState() != null && !cmf.getState().equals("null")) {
					str.append(" and jl.state='" + cmf.getState() + "'");
				}

				Object[] obj={};
				Map map=acms.searchToPageBySql(str.toString(), page.getPageNo(), Constant.PAGESIZE, obj);
				page.setRecordCount((Integer) map.get(Constant.ALLFILEDCOUNT));
				List list2 = (List) map.get(Constant.RESULTLIST);
				page.setPageSize(Constant.PAGESIZE);
				Iterator it = list2.iterator();
				while (it.hasNext()) {
						Object[] obj1=(Object[])it.next();
						AccessControlMessageVO accessControlMessageVO=new AccessControlMessageVO();
						accessControlMessageVO.setHjid(obj1[0].toString());
						accessControlMessageVO.setState(obj1[1].toString());
						accessControlMessageVO.setInTime(obj1[2].toString().substring(0, 19));
						if(obj1[3]!=null && !obj1[3].toString().equals("")){
							accessControlMessageVO.setOutTime(obj1[3].toString().substring(0, 19));
						}						
						accessControlMessageVO.setWebId(obj1[4].toString());
						accessControlMessageVO.setFrNo(obj1[5].toString());
						accessControlMessageVO.setFrName(obj1[6].toString());
						accessControlMessageVO.setJqName(obj1[7].toString());
						if(obj1[8]!=null && !obj1[8].toString().equals("")){
							accessControlMessageVO.setYjNo(obj1[8].toString());
						}
						if(obj1[9]!=null && !obj1[9].toString().equals("")){
							accessControlMessageVO.setYjName(obj1[9].toString());
						}
						if(obj1[10]!=null && !obj1[10].toString().equals("")){
							accessControlMessageVO.setSjc(obj1[10].toString()+"分钟");
						}
						
						page.getList().add(accessControlMessageVO);
				}
			}else{
				StringBuffer str = new StringBuffer(now.substring(0, 10));
				str.append(" 00:00:00");
				todayBegin = str.toString();
				StringBuffer str1 = new StringBuffer(now.substring(0, 10));
				str1.append(" 23:59:59");
				todayEnd = str1.toString();
			}
			String hql1 = "from JlJq";
			List<JlJq> list1 = acms.searchAll(hql1);
			request.setAttribute("jlJqList", list1);
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("accessControlMessageMain");
	}
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			AccessControlMessageForm cmf=(AccessControlMessageForm) form;
			Page page = new Page();
			page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			if (cmf.getCallTimeEnd() != null || cmf.getCallTimeStart() != null  || (cmf.getState() != null && !cmf.getState().equals("null"))  ||  (cmf.getJqNo() != null && !cmf.getJqNo().equals("null"))) {
				StringBuffer str=new StringBuffer("select jl.HJID,jl.state,jl.InTime,jl.OutTime,jl.WebID,dj.FR_No,dj.FR_Name,dj.JQ_Name,dj.YJ_No,dj.YJ_Name,DATEDIFF(Minute,jl.InTime,jl.OutTime) as a from JL_HJ_ACCESS_CONTROL_INFO jl,JL_HJ_DJ dj where jl.HJID=dj.HJID and jl.KzqAddress=4 and jl.DianAddress=1 and jl.HJID is not null");
				if (cmf.getCallTimeStart() != null && !cmf.getCallTimeStart().trim().equals("")) {
					str.append(" and jl.InTime>='"+ cmf.getCallTimeStart() + "'");
					todayBegin = cmf.getCallTimeStart();
				}
				if (cmf.getCallTimeEnd() != null && !cmf.getCallTimeEnd().trim().equals("")) {
					str.append(" and jl.InTime<='"+ cmf.getCallTimeEnd() + "'");
					todayEnd = cmf.getCallTimeEnd();
				}
				if (cmf.getJqNo() != null && !cmf.getJqNo().equals("null")) {
					str.append(" and dj.JQ_No='" + cmf.getJqNo() + "'");
				}
				if (cmf.getState() != null && !cmf.getState().equals("null")) {
					str.append(" and jl.state='" + cmf.getState() + "'");
				}

				Object[] obj={};
				Map map=acms.searchToPageBySql(str.toString(), page.getPageNo(), Constant.PAGESIZE, obj);
				page.setRecordCount((Integer) map.get(Constant.ALLFILEDCOUNT));
				List list2 = (List) map.get(Constant.RESULTLIST);
				page.setPageSize(Constant.PAGESIZE);
				Iterator it = list2.iterator();
				while (it.hasNext()) {
						Object[] obj1=(Object[])it.next();
						AccessControlMessageVO accessControlMessageVO=new AccessControlMessageVO();
						accessControlMessageVO.setHjid(obj1[0].toString());
						accessControlMessageVO.setState(obj1[1].toString());
						accessControlMessageVO.setInTime(obj1[2].toString().substring(0, 19));
						if(obj1[3]!=null && !obj1[3].toString().equals("")){
							accessControlMessageVO.setOutTime(obj1[3].toString().substring(0, 19));
						}						
						accessControlMessageVO.setWebId(obj1[4].toString());
						accessControlMessageVO.setFrNo(obj1[5].toString());
						accessControlMessageVO.setFrName(obj1[6].toString());
						accessControlMessageVO.setJqName(obj1[7].toString());
						if(obj1[8]!=null && !obj1[8].toString().equals("")){
							accessControlMessageVO.setYjNo(obj1[8].toString());
						}
						if(obj1[9]!=null && !obj1[9].toString().equals("")){
							accessControlMessageVO.setYjName(obj1[9].toString());
						}
						if(obj1[10]!=null && !obj1[10].toString().equals("")){
							accessControlMessageVO.setSjc(obj1[10].toString()+"分钟");
						}
						
						page.getList().add(accessControlMessageVO);
				}
			}else{
				StringBuffer str = new StringBuffer(now.substring(0, 10));
				str.append(" 00:00:00");
				todayBegin = str.toString();
				StringBuffer str1 = new StringBuffer(now.substring(0, 10));
				str1.append(" 23:59:59");
				todayEnd = str1.toString();
			}
			String hql1 = "from JlJq";
			List<JlJq> list1 = acms.searchAll(hql1);
			request.setAttribute("jlJqList", list1);
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("accessControlMessageMain");
	}
	public ActionForward dcSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user = (SysUser) request.getSession().getAttribute("users");
			if (user == null) {
				return mapping.findForward("sessionFailure");
			}
			Date date=new Date(System.currentTimeMillis());
		    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss"); 
		    String now=df1.format(date);
		    String filenameTemp = "D:\\\\"+now.substring(0,4)+now.substring(5,7)+now.substring(8,10)+"罪犯通道刷卡记录.xls"; 
		    File filename = new File(filenameTemp); 
		    if (!filename.exists()){
		    	filename.createNewFile(); 
		    } 
			WritableWorkbook wwb6 = null;
			wwb6 = Workbook.createWorkbook(filename);
			WritableSheet ws6 = wwb6.createSheet("罪犯通道刷卡记录", 0);
			ws6.addCell(new Label(0, 0, "罪犯编号"));
			ws6.addCell(new Label(1, 0, "罪犯姓名"));
			ws6.addCell(new Label(2, 0, "监区"));
			ws6.addCell(new Label(3, 0, "进入时间"));
			ws6.addCell(new Label(4, 0, "离开时间"));
			ws6.addCell(new Label(5, 0, "进出时间差"));
			ws6.addCell(new Label(6, 0, "警察编号"));
			ws6.addCell(new Label(7, 0, "警察姓名"));
			ws6.addCell(new Label(8, 0, "类型"));
			
			AccessControlMessageForm cmf=(AccessControlMessageForm) form;
			if (cmf.getCallTimeEnd() != null || cmf.getCallTimeStart() != null  || (cmf.getState() != null && !cmf.getState().equals("null"))  ||  (cmf.getJqNo() != null && !cmf.getJqNo().equals("null"))) {
				StringBuffer str=new StringBuffer("select dj.FR_No,dj.FR_Name,dj.JQ_Name,jl.InTime,jl.OutTime,DATEDIFF(Minute,jl.InTime,jl.OutTime) as a,dj.YJ_No,dj.YJ_Name,jl.state from JL_HJ_ACCESS_CONTROL_INFO jl,JL_HJ_DJ dj where jl.HJID=dj.HJID and jl.KzqAddress=4 and jl.DianAddress=1 and jl.HJID is not null");
				if (cmf.getCallTimeStart() != null && !cmf.getCallTimeStart().trim().equals("")) {
					str.append(" and jl.InTime>='"+ cmf.getCallTimeStart() + "'");
				}
				if (cmf.getCallTimeEnd() != null && !cmf.getCallTimeEnd().trim().equals("")) {
					str.append(" and jl.InTime<='"+ cmf.getCallTimeEnd() + "'");
				}
				if (cmf.getJqNo() != null && !cmf.getJqNo().equals("null")) {
					str.append(" and dj.JQ_No='" + cmf.getJqNo() + "'");
				}
				if (cmf.getState() != null && !cmf.getState().equals("null")) {
					str.append(" and jl.state='" + cmf.getState() + "'");
				}
				
				List list=acms.searchAllBySql(str.toString());
				for(int i=0;i<list.size();i++){
					Object[] obj1=(Object[])list.get(i);

			    	ws6.addCell(new Label(0, i+1, obj1[0].toString()));
			    	ws6.addCell(new Label(1, i+1, obj1[1].toString()));
			    	ws6.addCell(new Label(2, i+1, obj1[2].toString()));
			    	ws6.addCell(new Label(3, i+1, obj1[3].toString()));;
			    	if(obj1[4]!=null && !obj1[4].toString().equals("")){
			    		ws6.addCell(new Label(4, i+1, obj1[4].toString()));
			    	}
			    	if(obj1[5]!=null && !obj1[5].toString().equals("")){
			    		ws6.addCell(new Label(5, i+1, obj1[5].toString()));
			    	}
			    	if(obj1[6]!=null && !obj1[6].toString().equals("")){
			    		ws6.addCell(new Label(6, i+1, obj1[6].toString()));
			    	}
			    	if(obj1[7]!=null && !obj1[7].toString().equals("")){
			    		ws6.addCell(new Label(7, i+1, obj1[7].toString()));
			    	}
			    	if(obj1[8].toString().equals("1")){
			    		ws6.addCell(new Label(8, i+1, "正常"));
			    	}else{
			    		ws6.addCell(new Label(8, i+1, "异常"));
			    	}
			    	
			    }
			} 
			wwb6.write();
			wwb6.close(); 
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			OutputStream fos = null;
			InputStream fis = null;
			fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);
			fos = response.getOutputStream();
			bos = new BufferedOutputStream(fos);
			response.addHeader("Content-Disposition","attachment; filename="+ URLEncoder.encode(filenameTemp, "utf-8"));
			response.setContentType("application/octet-stream");
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.flush();
			fis.close();
			bis.close();
			fos.close();
			bos.close();
			return null;
	}
}
