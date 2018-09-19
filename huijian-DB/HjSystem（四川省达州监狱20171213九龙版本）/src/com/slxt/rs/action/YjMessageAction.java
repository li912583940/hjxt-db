package com.slxt.rs.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.slxt.rs.form.YjMessageForm;
import com.slxt.rs.model.JlJq;
import com.slxt.rs.model.JlYj;
import com.slxt.rs.model.SysHjServer;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.model.SysUserJq;
import com.slxt.rs.svc.YjMessageService;
import com.slxt.rs.util.Constant;
import com.slxt.rs.vo.Page;
import com.slxt.rs.vo.YjMessage;

public class YjMessageAction extends DispatchAction{
     private YjMessageService yms;
	 public void setYms(YjMessageService yms) {
		this.yms = yms;
	 }
	 //查询狱警
	 public ActionForward search(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response)
	 		throws Exception {
		   	SysUser user=(SysUser)request.getSession().getAttribute("users");
		   	if(user==null){
		   		return mapping.findForward("sessionFailure");
		   	}
		   	Page page=new Page();
		   	page.setPageNo(1);
		   	YjMessageForm yjMessageForm=(YjMessageForm)form;
		   	String deptName2 = request.getParameter("deptName2");
			String yjNum2 = request.getParameter("yjNum2");
			String jyName2 = request.getParameter("jyName2");
			String pageNo2=request.getParameter("pageNo2");
			if(deptName2!=null && !deptName2.equals("null")){
				yjMessageForm.setDeptName(deptName2);
			}
			if(pageNo2!=null && !pageNo2.equals("-1")){
				page.setPageNo(Integer.parseInt(pageNo2));
			}
			if(yjNum2!=null && !yjNum2.trim().equals("")){
				yjMessageForm.setYjNum(yjNum2);
			}
			if(jyName2!=null && !jyName2.trim().equals("")){
				yjMessageForm.setJyName(jyName2);
			}
		   	if(yjMessageForm.getDeptName()!=null || yjMessageForm.getYjNum()!=null || yjMessageForm.getJyName()!=null || (pageNo2!=null && !pageNo2.equals("-1"))){
		   		StringBuffer str=new StringBuffer("from JlYj where 1=1");
		   		if(yjMessageForm.getDeptName()!=null && !yjMessageForm.getDeptName().equals("null")){
		   			 str.append(" and deptName='"+yjMessageForm.getDeptName()+"'");
		   		}
		   		if(yjMessageForm.getYjNum()!=null && !yjMessageForm.getYjNum().trim().equals("")){
		   			str.append(" and yjNum like '%"+yjMessageForm.getYjNum().trim()+"%'");
		   		}
		   		if(yjMessageForm.getJyName()!=null && !yjMessageForm.getJyName().trim().equals("")){
		   			str.append(" and yjName like '%"+yjMessageForm.getJyName().trim()+"%'");
		   		}
		   		str.append(" order by deptName,yjNum");
		   		Object[] obj={};
		   		Map map=yms.searchToPage(str.toString(), page.getPageNo(), 20, obj);
		   		page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
		   		List<JlYj> list1=(List)map.get(Constant.RESULTLIST);
		   		page.setList(list1);
		   		page.setPageSize(20);
		   	}
		   	String sql="select distinct Dept_Name from Dept";
		   	List list=yms.searchAllBySql(sql);
		   	request.setAttribute("deptList", list);
		   	request.setAttribute("page", page);
		   	return mapping.findForward("yjMessageMain");
	 }
	 public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		   	SysUser user=(SysUser)request.getSession().getAttribute("users");
		   	if(user==null){
		   		return mapping.findForward("sessionFailure");
		   	}
		   	Page page=new Page();
		   	page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		   	YjMessageForm yjMessageForm=(YjMessageForm)form;
		   	if(yjMessageForm.getDeptName()!=null || yjMessageForm.getYjNum()!=null || yjMessageForm.getJyName()!=null){
		   		StringBuffer str=new StringBuffer("from JlYj where 1=1");
		   		if(yjMessageForm.getDeptName()!=null && !yjMessageForm.getDeptName().equals("null")){
		   			 str.append(" and deptName='"+yjMessageForm.getDeptName()+"'");
		   		}
		   		if(yjMessageForm.getYjNum()!=null && !yjMessageForm.getYjNum().trim().equals("")){
		   			str.append(" and yjNum like '%"+yjMessageForm.getYjNum().trim()+"%'");
		   		}
		   		if(yjMessageForm.getJyName()!=null && !yjMessageForm.getJyName().trim().equals("")){
		   			str.append(" and yjName like '%"+yjMessageForm.getJyName().trim()+"%'");
		   		}
		   		Object[] obj={};
		   		str.append(" order by deptName,yjNum");
		   		Map map=yms.searchToPage(str.toString(), page.getPageNo(), 20, obj);
		   		page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
		   		List<JlYj> list1=(List)map.get(Constant.RESULTLIST);
		   		page.setList(list1);
		   		page.setPageSize(20);
		   	}
		   	String sql="select distinct Dept_Name from Dept";
		   	List list=yms.searchAllBySql(sql);
		   	request.setAttribute("deptList", list);
		   	request.setAttribute("page", page);
		   	return mapping.findForward("yjMessageMain");
	 }
//	 public ActionForward search(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		   	SysUser user=(SysUser)request.getSession().getAttribute("users");
//		   	if(user==null){
//		   		return mapping.findForward("sessionFailure");
//		   	}
//		   	Page page=new Page();
//		   	page.setPageNo(1);
//		   	YjMessageForm yjMessageForm=(YjMessageForm)form;
//		   	if(user.getIsSuper()==1){
//		   		int jqIsNodisable=0;
//		   		if(yjMessageForm.getJy()!=null  || yjMessageForm.getJyNo()!=null  || yjMessageForm.getJyName()!=null  || yjMessageForm.getJq()!=null ){
//		   			StringBuffer str=new StringBuffer("select yj.WebID,yj.YJ_No,yj.YJ_Name,yj.YJ_Card,yj.JQ,jq.JQ_Name,yj.jy from JL_YJ yj,JL_JQ jq,SYS_QQ_SERVER sys where yj.JQ=jq.JQ_No and yj.jy=sys.Server_Name");
//		   			if(yjMessageForm.getJy()!=null && !yjMessageForm.getJy().equals("null")){
//			   			str.append(" and yj.JY ='"+yjMessageForm.getJy()+"'");
//			   			jqIsNodisable=1;
//			   			String hql1="from JlJq where jy='"+yjMessageForm.getJy()+"'";
//					 	List<JlJq> list1=yms.searchAll(hql1);
//						request.setAttribute("jlJqList", list1);
//			   		}
//			   		if(yjMessageForm.getJyNo()!=null && yjMessageForm.getJyNo().trim()!=""){
//			   			str.append(" and yj.YJ_No ='"+yjMessageForm.getJyNo()+"'");
//			   		}
//			   		if(yjMessageForm.getJyName()!=null && yjMessageForm.getJyName()!=""){
//			   			str.append(" and yj.YJ_Name like '%"+yjMessageForm.getJyName()+"%'");
//			   		}
//			   		if(yjMessageForm.getJq()!=null && !yjMessageForm.getJq().equals("null")){
//			   			str.append(" and yj.JQ ='"+yjMessageForm.getJq()+"'");
//			   		}
//			   		Object[] obj={};
//			   		Map map=yms.searchToPageBySql(str.toString(), page.getPageNo(), Constant.PAGESIZE, obj);
//			   		page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
//			   		page.setPageSize(Constant.PAGESIZE);
//			   		List list=(List)map.get(Constant.RESULTLIST);
//			   		Iterator it=list.iterator();
//			   		while(it.hasNext()){
//			   			Object[] obj1=(Object[])it.next();
//			   			YjMessage yjMessage=new YjMessage();
//			   			yjMessage.setWebId(obj1[0].toString());
//			   			yjMessage.setYjNo(obj1[1].toString());
//			   			if(obj1[2]!=null && obj1[2].toString()!=""){
//			   				yjMessage.setYjName(obj1[2].toString());
//			   			}
//			   			if(obj1[3]!=null && obj1[3].toString()!=""){
//			   				yjMessage.setYjCard(obj1[3].toString());
//			   			}
//			   			yjMessage.setJq(obj1[4].toString());
//			   			if(obj1[5]!=null && obj1[5].toString()!=""){
//			   				yjMessage.setJqName(obj1[5].toString());
//			   			}
//			   			if(obj1[6]!=null && obj1[6].toString()!=""){
//			   				yjMessage.setJy(obj1[6].toString());
//			   			}
//			   			page.getList().add(yjMessage);
//			   		}
//		   		}
//		   		String hql="from SysHjServer";
//		   		List<SysHjServer> list1=yms.searchAll(hql);
//		 		request.setAttribute("jqIsNodisable",jqIsNodisable);
//		   		request.setAttribute("sysQqServerList", list1);
//		   		request.setAttribute("page",page);
//		   	}else if(user.getGroupNo().equals("Admin")){
//		   		int jqIsNodisable=0;
//		   		if(yjMessageForm.getJy()!=null || yjMessageForm.getJyNo()!=null  || yjMessageForm.getJyName()!=null || yjMessageForm.getJq()!=null ){
//		   			StringBuffer str=new StringBuffer("select yj.WebID,yj.YJ_No,yj.YJ_Name,yj.YJ_Card,yj.JQ,jq.JQ_Name,yj.jy from JL_YJ yj,JL_JQ jq,SYS_HJ_SERVER sys where yj.JQ=jq.JQ_No and yj.jy=sys.Server_Name and jq.Is_Ts!=1");
//		   			if(yjMessageForm.getJy()!=null && !yjMessageForm.getJy().equals("null")){
//			   			str.append(" and yj.JY ='"+yjMessageForm.getJy()+"'");
//			   			jqIsNodisable=1;
//			   			String hql1="from JlJq where jy='"+yjMessageForm.getJy()+"'";
//					 	List<JlJq> list1=yms.searchAll(hql1);
//						request.setAttribute("jlJqList", list1);
//			   		}
//			   		if(yjMessageForm.getJyNo()!=null && yjMessageForm.getJyNo().trim()!=""){
//			   			str.append(" and yj.YJ_No ='"+yjMessageForm.getJyNo()+"'");
//			   		}
//			   		if(yjMessageForm.getJyName()!=null && yjMessageForm.getJyName()!=""){
//			   			str.append(" and yj.YJ_Name like '%"+yjMessageForm.getJyName()+"%'");
//			   		}
//			   		if(yjMessageForm.getJq()!=null && !yjMessageForm.getJq().equals("null")){
//			   			str.append(" and yj.JQ ='"+yjMessageForm.getJq()+"'");
//			   		}
//			   		Object[] obj={};
//			   		Map map=yms.searchToPageBySql(str.toString(), page.getPageNo(), Constant.PAGESIZE, obj);
//			   		page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
//			   		page.setPageSize(Constant.PAGESIZE);
//			   		List list=(List)map.get(Constant.RESULTLIST);
//			   		Iterator it=list.iterator();
//			   		while(it.hasNext()){
//			   			Object[] obj1=(Object[])it.next();
//			   			YjMessage yjMessage=new YjMessage();
//			   			yjMessage.setWebId(obj1[0].toString());
//			   			yjMessage.setYjNo(obj1[1].toString());
//			   			if(obj1[2]!=null && obj1[2].toString()!=""){
//			   				yjMessage.setYjName(obj1[2].toString());
//			   			}
//			   			if(obj1[3]!=null && obj1[3].toString()!=""){
//			   				yjMessage.setYjCard(obj1[3].toString());
//			   			}
//			   			yjMessage.setJq(obj1[4].toString());
//			   			if(obj1[5]!=null && obj1[5].toString()!=""){
//			   				yjMessage.setJqName(obj1[5].toString());
//			   			}
//			   			if(obj1[6]!=null && obj1[6].toString()!=""){
//			   				yjMessage.setJy(obj1[6].toString());
//			   			}
//			   			page.getList().add(yjMessage);
//			   		}
//		   		}
//		   		String hql="from SysHjServer";
//		   		List<SysHjServer> list1=yms.searchAll(hql);
//		 		request.setAttribute("jqIsNodisable",jqIsNodisable);
//		   		request.setAttribute("sysQqServerList", list1);
//		   		request.setAttribute("page",page);
//		   	}else{
//		   		int jqIsNodisable=0;
//		   		StringBuffer str=new StringBuffer("select yj.WebID,yj.YJ_No,yj.YJ_Name,yj.YJ_Card,yj.JQ,jq.JQ_Name,yj.jy from JL_YJ yj,JL_JQ jq,SYS_HJ_SERVER sys where yj.JQ=jq.JQ_No and yj.jy=sys.Server_Name");
//				String sql5="from SysUserJq where groupNo='"+user.getGroupNo()+"'";
//				List list5=yms.searchAll(sql5);
//				if(list5.size()>0){
//					str.append(" and (");
//					for(int i=0;i<list5.size();i++){
//						SysUserJq sysUserJq=(SysUserJq)list5.get(i);
//						if(i==0){
//				    		str.append(" jq.jq_No='"+sysUserJq.getJqNo()+"'");
//				    	}else{
//				    		str.append(" or jq.jq_No='"+sysUserJq.getJqNo()+"'");
//				    	}
//				    }
//					str.append(" ) ");
//				}
//				String sql6="select distinct JY from SYS_USER_JQ where Group_No='"+user.getGroupNo()+"'";
//				List list6=yms.searchAllBySql(sql6);
//				if(list6.size()>0){
//					StringBuffer hql=new StringBuffer("from SysHjServer ");
//					Iterator it=list6.iterator();
//					int i=0;
//					while(it.hasNext()){
//						if(i==0){
//							hql.append(" where serverName='"+it.next()+"' ");
//							i++;
//						}else{
//							hql.append("or serverName='"+it.next()+"' ");
//							i++;
//						}
//					}
//					List<SysHjServer> list=yms.searchAll(hql.toString());
//					request.setAttribute("sysQqServerList", list);
//				}
//				if(yjMessageForm.getJy()!=null  || yjMessageForm.getJyNo()!=null  || yjMessageForm.getJyName()!=null  || yjMessageForm.getJq()!=null ){
//			   		if(yjMessageForm.getJy()!=null && !yjMessageForm.getJy().equals("null")){
//			   			jqIsNodisable=1;
//			   			str.append(" and yj.JY ='"+yjMessageForm.getJy()+"'");
//			   			String hql1="select jq from JlJq jq,SysUserJq sys where jq.jy='"+yjMessageForm.getJy()+"' and jq.jqNo=sys.jqNo and sys.groupNo='"+user.getGroupNo()+"'";
//					 	List<JlJq> list1=yms.searchAll(hql1);
//						request.setAttribute("jlJqList", list1);
//			   		}
//			   		if(yjMessageForm.getJyNo()!=null && yjMessageForm.getJyNo().trim()!=""){
//			   			str.append(" and yj.YJ_No ='"+yjMessageForm.getJyNo()+"'");
//			   		}
//			   		if(yjMessageForm.getJyName()!=null && yjMessageForm.getJyName()!=""){
//			   			str.append(" and yj.YJ_Name like '%"+yjMessageForm.getJyName()+"%'");
//			   		}
//			   		if(yjMessageForm.getJq()!=null && !yjMessageForm.getJq().equals("null")){
//			   			str.append(" and yj.JQ ='"+yjMessageForm.getJq()+"'");
//			   		}
//			   		Object[] obj={};
//			   		Map map=yms.searchToPageBySql(str.toString(), page.getPageNo(), Constant.PAGESIZE, obj);
//			   		page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
//			   		page.setPageSize(Constant.PAGESIZE);
//			   		List list=(List)map.get(Constant.RESULTLIST);
//			   		Iterator it=list.iterator();
//			   		while(it.hasNext()){
//			   			Object[] obj1=(Object[])it.next();
//			   			YjMessage yjMessage=new YjMessage();
//			   			yjMessage.setWebId(obj1[0].toString());
//			   			yjMessage.setYjNo(obj1[1].toString());
//			   			if(obj1[2]!=null && obj1[2].toString()!=""){
//			   				yjMessage.setYjName(obj1[2].toString());
//			   			}
//			   			if(obj1[3]!=null && obj1[3].toString()!=""){
//			   				yjMessage.setYjCard(obj1[3].toString());
//			   			}
//			   			yjMessage.setJq(obj1[4].toString());
//			   			if(obj1[5]!=null && obj1[5].toString()!=""){
//			   				yjMessage.setJqName(obj1[5].toString());
//			   			}
//			   			if(obj1[6]!=null && obj1[6].toString()!=""){
//			   				yjMessage.setJy(obj1[6].toString());
//			   			}
//			   			page.getList().add(yjMessage);
//			   		}
//		   		}
//		   		request.setAttribute("jqIsNodisable",jqIsNodisable);
//		   		request.setAttribute("page",page);
//		   	}
//		   	return mapping.findForward("yjMessageMain");
//	}
//	 public ActionForward search1(ActionMapping mapping, ActionForm form,
//				HttpServletRequest request, HttpServletResponse response)
//	 			throws Exception {
//		 		SysUser user=(SysUser)request.getSession().getAttribute("users");
//		 		if(user==null){
//		 			return mapping.findForward("sessionFailure");
//		 		}
//		 		Page page=new Page();
//		 		page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
//		 		YjMessageForm yjMessageForm=(YjMessageForm)form;
//		 		if(user.getIsSuper()==1){
//		 			int jqIsNodisable=0;
//		 			if(yjMessageForm.getJy()!=null  || yjMessageForm.getJyNo()!=null  || yjMessageForm.getJyName()!=null  || yjMessageForm.getJq()!=null ){
//		 				StringBuffer str=new StringBuffer("select yj.WebID,yj.YJ_No,yj.YJ_Name,yj.YJ_Card,yj.JQ,jq.JQ_Name,yj.jy from JL_YJ yj,JL_JQ jq,SYS_HJ_SERVER sys where yj.JQ=jq.JQ_No and yj.jy=sys.Server_Name");
//		 				if(yjMessageForm.getJy()!=null && !yjMessageForm.getJy().equals("null")){
//				   			str.append(" and yj.JY ='"+yjMessageForm.getJy()+"'");
//				   			jqIsNodisable=1;
//				   			String hql1="from JlJq where jy='"+yjMessageForm.getJy()+"'";
//						 	List<JlJq> list1=yms.searchAll(hql1);
//							request.setAttribute("jlJqList", list1);
//				   		}
//				   		if(yjMessageForm.getJyNo()!=null && yjMessageForm.getJyNo().trim()!=""){
//				   			str.append(" and yj.YJ_No ='"+yjMessageForm.getJyNo()+"'");
//				   		}
//				   		if(yjMessageForm.getJyName()!=null && yjMessageForm.getJyName()!=""){
//				   			str.append(" and yj.YJ_Name like '%"+yjMessageForm.getJyName()+"%'");
//				   		}
//				   		if(yjMessageForm.getJq()!=null && !yjMessageForm.getJq().equals("null")){
//				   			str.append(" and yj.JQ ='"+yjMessageForm.getJq()+"'");
//				   		}
//				   		Object[] obj={};
//				   		Map map=yms.searchToPageBySql(str.toString(), page.getPageNo(), Constant.PAGESIZE, obj);
//				   		page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
//				   		page.setPageSize(Constant.PAGESIZE);
//				   		List list=(List)map.get(Constant.RESULTLIST);
//				   		Iterator it=list.iterator();
//				   		while(it.hasNext()){
//				   			Object[] obj1=(Object[])it.next();
//				   			YjMessage yjMessage=new YjMessage();
//				   			yjMessage.setWebId(obj1[0].toString());
//				   			yjMessage.setYjNo(obj1[1].toString());
//				   			if(obj1[2]!=null && obj1[2].toString()!=""){
//				   				yjMessage.setYjName(obj1[2].toString());
//				   			}
//				   			if(obj1[3]!=null && obj1[3].toString()!=""){
//				   				yjMessage.setYjCard(obj1[3].toString());
//				   			}
//				   			yjMessage.setJq(obj1[4].toString());
//				   			if(obj1[5]!=null && obj1[5].toString()!=""){
//				   				yjMessage.setJqName(obj1[5].toString());
//				   			}
//				   			if(obj1[6]!=null && obj1[6].toString()!=""){
//				   				yjMessage.setJy(obj1[6].toString());
//				   			}
//				   			page.getList().add(yjMessage);
//				   		}
//			   		}
//			   		String hql="from SysHjServer";
//			   		List<SysHjServer> list1=yms.searchAll(hql);
//			 		request.setAttribute("jqIsNodisable",jqIsNodisable);
//			   		request.setAttribute("sysQqServerList", list1);
//			   		request.setAttribute("page",page);
//			   	}else if(user.getGroupNo().equals("Admin")){
//			   		int jqIsNodisable=0;
//			   		if((yjMessageForm.getJy()!=null && !yjMessageForm.getJy().equals("null")) || (yjMessageForm.getJyNo()!=null && yjMessageForm.getJyNo().trim()!="") || (yjMessageForm.getJyName()!=null && yjMessageForm.getJyName()!="") || (yjMessageForm.getJq()!=null && !yjMessageForm.getJq().equals("null")) ){
//			   			StringBuffer str=new StringBuffer("select yj.WebID,yj.YJ_No,yj.YJ_Name,yj.YJ_Card,yj.JQ,jq.JQ_Name,yj.jy from JL_YJ yj,JL_JQ jq,SYS_HJ_SERVER sys where yj.JQ=jq.JQ_No and yj.jy=sys.Server_Name and jq.Is_Ts!=1");
//			   			if(yjMessageForm.getJy()!=null && !yjMessageForm.getJy().equals("null")){
//				   			str.append(" and yj.JY ='"+yjMessageForm.getJy()+"'");
//				   			jqIsNodisable=1;
//				   			String hql1="from JlJq where jy='"+yjMessageForm.getJy()+"'";
//						 	List<JlJq> list1=yms.searchAll(hql1);
//							request.setAttribute("jlJqList", list1);
//				   		}
//				   		if(yjMessageForm.getJyNo()!=null && yjMessageForm.getJyNo().trim()!=""){
//				   			str.append(" and yj.YJ_No ='"+yjMessageForm.getJyNo()+"'");
//				   		}
//				   		if(yjMessageForm.getJyName()!=null && yjMessageForm.getJyName()!=""){
//				   			str.append(" and yj.YJ_Name like '%"+yjMessageForm.getJyName()+"%'");
//				   		}
//				   		if(yjMessageForm.getJq()!=null && !yjMessageForm.getJq().equals("null")){
//				   			str.append(" and yj.JQ ='"+yjMessageForm.getJq()+"'");
//				   		}
//				   		Object[] obj={};
//				   		Map map=yms.searchToPageBySql(str.toString(), page.getPageNo(), Constant.PAGESIZE, obj);
//				   		page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
//				   		page.setPageSize(Constant.PAGESIZE);
//				   		List list=(List)map.get(Constant.RESULTLIST);
//				   		Iterator it=list.iterator();
//				   		while(it.hasNext()){
//				   			Object[] obj1=(Object[])it.next();
//				   			YjMessage yjMessage=new YjMessage();
//				   			yjMessage.setWebId(obj1[0].toString());
//				   			yjMessage.setYjNo(obj1[1].toString());
//				   			if(obj1[2]!=null && obj1[2].toString()!=""){
//				   				yjMessage.setYjName(obj1[2].toString());
//				   			}
//				   			if(obj1[3]!=null && obj1[3].toString()!=""){
//				   				yjMessage.setYjCard(obj1[3].toString());
//				   			}
//				   			yjMessage.setJq(obj1[4].toString());
//				   			if(obj1[5]!=null && obj1[5].toString()!=""){
//				   				yjMessage.setJqName(obj1[5].toString());
//				   			}
//				   			if(obj1[6]!=null && obj1[6].toString()!=""){
//				   				yjMessage.setJy(obj1[6].toString());
//				   			}
//				   			page.getList().add(yjMessage);
//				   		}
//			   		}
//			   		String hql="from SysHjServer";
//			   		List<SysHjServer> list1=yms.searchAll(hql);
//			 		request.setAttribute("jqIsNodisable",jqIsNodisable);
//			   		request.setAttribute("sysQqServerList", list1);
//			   		request.setAttribute("page",page);
//			   	}else{
//			   		int jqIsNodisable=0;
//			   		StringBuffer str=new StringBuffer("select yj.WebID,yj.YJ_No,yj.YJ_Name,yj.YJ_Card,yj.JQ,jq.JQ_Name,yj.jy from JL_YJ yj,JL_JQ jq,SYS_HJ_SERVER sys where yj.JQ=jq.JQ_No and yj.jy=sys.Server_Name");
//					String sql5="from SysUserJq where groupNo='"+user.getGroupNo()+"'";
//					List list5=yms.searchAll(sql5);
//					if(list5.size()>0){
//						str.append(" and (");
//						for(int i=0;i<list5.size();i++){
//							SysUserJq sysUserJq=(SysUserJq)list5.get(i);
//							if(i==0){
//					    		str.append(" jq.jq_No='"+sysUserJq.getJqNo()+"'");
//					    	}else{
//					    		str.append(" or jq.jq_No='"+sysUserJq.getJqNo()+"'");
//					    	}
//					    }
//						str.append(" ) ");
//					}
//					String sql6="select distinct JY from SYS_USER_JQ where Group_No='"+user.getGroupNo()+"'";
//					List list6=yms.searchAllBySql(sql6);
//					if(list6.size()>0){
//						StringBuffer hql=new StringBuffer("from SysHjServer ");
//						Iterator it=list6.iterator();
//						int i=0;
//						while(it.hasNext()){
//							if(i==0){
//								hql.append(" where serverName='"+it.next()+"' ");
//								i++;
//							}else{
//								hql.append("or serverName='"+it.next()+"' ");
//								i++;
//							}
//						}
//						List<SysHjServer> list=yms.searchAll(hql.toString());
//						request.setAttribute("sysQqServerList", list);
//					}
//					if((yjMessageForm.getJy()!=null && !yjMessageForm.getJy().equals("null")) || (yjMessageForm.getJyNo()!=null && yjMessageForm.getJyNo().trim()!="") || (yjMessageForm.getJyName()!=null && yjMessageForm.getJyName()!="") || (yjMessageForm.getJq()!=null && !yjMessageForm.getJq().equals("null")) ){
//				   		if(yjMessageForm.getJy()!=null && !yjMessageForm.getJy().equals("null")){
//				   			jqIsNodisable=1;
//				   			str.append(" and yj.JY ='"+yjMessageForm.getJy()+"'");
//				   			String hql1="select jq from JlJq jq,SysUserJq sys where jq.jy='"+yjMessageForm.getJy()+"' and jq.jqNo=sys.jqNo and sys.groupNo='"+user.getGroupNo()+"'";
//						 	List<JlJq> list1=yms.searchAll(hql1);
//							request.setAttribute("jlJqList", list1);
//				   		}
//				   		if(yjMessageForm.getJyNo()!=null && yjMessageForm.getJyNo().trim()!=""){
//				   			str.append(" and yj.YJ_No ='"+yjMessageForm.getJyNo()+"'");
//				   		}
//				   		if(yjMessageForm.getJyName()!=null && yjMessageForm.getJyName()!=""){
//				   			str.append(" and yj.YJ_Name like '%"+yjMessageForm.getJyName()+"%'");
//				   		}
//				   		if(yjMessageForm.getJq()!=null && !yjMessageForm.getJq().equals("null")){
//				   			str.append(" and yj.JQ ='"+yjMessageForm.getJq()+"'");
//				   		}
//				   		Object[] obj={};
//				   		Map map=yms.searchToPageBySql(str.toString(), page.getPageNo(), Constant.PAGESIZE, obj);
//				   		page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
//				   		page.setPageSize(Constant.PAGESIZE);
//				   		List list=(List)map.get(Constant.RESULTLIST);
//				   		Iterator it=list.iterator();
//				   		while(it.hasNext()){
//				   			Object[] obj1=(Object[])it.next();
//				   			YjMessage yjMessage=new YjMessage();
//				   			yjMessage.setWebId(obj1[0].toString());
//				   			yjMessage.setYjNo(obj1[1].toString());
//				   			if(obj1[2]!=null && obj1[2].toString()!=""){
//				   				yjMessage.setYjName(obj1[2].toString());
//				   			}
//				   			if(obj1[3]!=null && obj1[3].toString()!=""){
//				   				yjMessage.setYjCard(obj1[3].toString());
//				   			}
//				   			yjMessage.setJq(obj1[4].toString());
//				   			if(obj1[5]!=null && obj1[5].toString()!=""){
//				   				yjMessage.setJqName(obj1[5].toString());
//				   			}
//				   			if(obj1[6]!=null && obj1[6].toString()!=""){
//				   				yjMessage.setJy(obj1[6].toString());
//				   			}
//				   			page.getList().add(yjMessage);
//				   		}
//			   		}
//			   		request.setAttribute("jqIsNodisable",jqIsNodisable);
//			   		request.setAttribute("page",page);
//			   	}
//			   	return mapping.findForward("yjMessageMain");
//		}
	 //添加狱警
	public ActionForward addYj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			if(user.getIsSuper()==1 || user.getGroupNo().equals("Admin")){
				String hql="from SysHjServer";
				List<SysHjServer> list=yms.searchAll(hql);
				request.setAttribute("list1", list);
		    }else{
		    	String hql1="select distinct jl.serverName  from SysUserJq suj,SysHjServer jl where jl.serverName=suj.jy and suj.groupNo='"+user.getGroupNo()+"'";
				List list1=yms.searchAll(hql1);
				Iterator it1=list1.iterator();
				List<SysHjServer> list4=new ArrayList();
				while(it1.hasNext()){
					SysHjServer sysQqServer=new SysHjServer();
					sysQqServer.setServerName(it1.next().toString());
					list4.add(sysQqServer);
				}
				request.setAttribute("list1", list4);
		    }
			String sql="select distinct Dept_Name from Dept";
		   	List list=yms.searchAllBySql(sql);
		   	request.setAttribute("deptList", list);
			return mapping.findForward("addyjMessage");
	} 
	//添加保存狱警
	public ActionForward addSaveyjMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String yjNum = java.net.URLDecoder.decode((String)request.getParameter("yjNum1"),"UTF-8") ;
		    String yjName = java.net.URLDecoder.decode((String)request.getParameter("yjName1"),"UTF-8") ;
	        String yjCard=request.getParameter("yjCard");
	        String jyId=request.getParameter("jyId");
	        String jqId=request.getParameter("jqId");
	        String deptName=request.getParameter("deptName");
	        String hql="from JlYj where yjNum='"+yjNum+"'";
		    List list=yms.searchAll(hql);
		    if(list.size()>0){
		    	JSONArray json=JSONArray.fromObject(1);
		    	response.getWriter().println(json.toString());
		    }else{
		    	JlYj jlYj=new JlYj();
		    	jlYj.setYjNum(yjNum);
		    	if(yjName.trim()!=""){
		    		jlYj.setYjName(yjName);
		    	}
		    	if(yjCard.trim()!=""){
		    		jlYj.setYjCard(yjCard);
		    	}
		    	jlYj.setJy(jyId);
		    	jlYj.setJq(jqId);
		    	jlYj.setDeptName(deptName);
		    	yms.save(jlYj);
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
		    	sl.setModel("狱警管理");
		    	sl.setInfo("新增狱警（狱警编号："+yjNum+" 部门："+deptName+")");
		    	sl.setOp("增加狱警信息");
		    	yms.save(sl);
		    	JSONArray json=JSONArray.fromObject(null);
		    	response.getWriter().println(json.toString());
		    }
		    return null;
	}
	//修改狱警
	public ActionForward updateYjMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
		    JlYj jlYj=(JlYj)yms.findById(JlYj.class, Integer.parseInt(webId));
		    request.setAttribute("jlYj", jlYj);
		    if(user.getIsSuper()==1){
				String hql="from SysHjServer";
				List<SysHjServer> list=yms.searchAll(hql);
				request.setAttribute("sysQqServerList", list);
				String hql1="from JlJq where jy='"+jlYj.getJy()+"'";
				List<JlJq> jlJqList=yms.searchAll(hql1);
				request.setAttribute("jlJqList", jlJqList);
			}else if(user.getGroupNo().equals("Admin")){
				String hql="from SysHjServer";
				List<SysHjServer> list=yms.searchAll(hql);
				request.setAttribute("sysQqServerList", list);
				String hql1="from JlJq where jy='"+jlYj.getJy()+"' and isTs!=1";
				List<JlJq> jlJqList=yms.searchAll(hql1);
				request.setAttribute("jlJqList", jlJqList);
			}else{
				String hql1="select distinct jl.serverName  from SysUserJq suj,SysHjServer jl where jl.serverName=suj.jy and suj.groupNo='"+user.getGroupNo()+"'";
				List list1=yms.searchAll(hql1);
				Iterator it1=list1.iterator();
				List<SysHjServer> list4=new ArrayList();
				while(it1.hasNext()){
					SysHjServer sysQqServer=new SysHjServer();
					sysQqServer.setServerName(it1.next().toString());
					list4.add(sysQqServer);
				}
				request.setAttribute("sysQqServerList", list4);
				String hql2="select jl.jqNo,jl.jqName  from SysUserJq suj,JlJq jl where jl.jqNo=suj.jqNo and suj.groupNo='"+user.getGroupNo()+"'"+" and suj.jy='"+jlYj.getJy()+"'";
				List list2=yms.searchAll(hql2);
				Iterator it2=list2.iterator();
				List<JlJq> list3=new ArrayList();
				while(it2.hasNext()){
					Object[] objects=(Object[] )it2.next();
					JlJq jlJq=new JlJq();
					jlJq.setJqNo(objects[0].toString());
					if(objects[1]!=null && objects[1].toString()!=""){
						jlJq.setJqName(objects[1].toString());
					}
					list3.add(jlJq);
				}
				request.setAttribute("jlJqList", list3);
			}
		    String sql="select distinct Dept_Name from Dept";
		   	List list=yms.searchAllBySql(sql);
		   	request.setAttribute("deptList", list);
		    return mapping.findForward("updateYjMessage");
	}
	//修改保存狱警
	public ActionForward updateSaveyjMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String webId = request.getParameter("webId");
		    String yjName = java.net.URLDecoder.decode((String)request.getParameter("yjName1"),"UTF-8") ;
	        String yjCard=request.getParameter("yjCard");
	        String jqId=request.getParameter("jqId");
	        String jyId=request.getParameter("jyId");
	        String deptName=request.getParameter("deptName");
	        JlYj jlYj=(JlYj)yms.findById(JlYj.class, Integer.parseInt(webId));
	        StringBuffer str=new StringBuffer("update JlYj set ");
	        str.append("teleUser=null");
	        str.append(",jy='"+jyId+"'");
	        if(yjName!=""){
	        	str.append(",yjName='"+yjName+"'");
	        }else{
	        	str.append(",yjName=''");
	        }
	        if(yjCard!=""){
	        	str.append(",yjCard='"+yjCard+"'");
	        }else{
	        	str.append(",yjCard=''");
	        }
	        if(jqId!=""){
	        	str.append(",jq='"+jqId+"'");
	        }else{
	        	str.append(",jq=''");
	        }
	        if(deptName!=""){
	        	str.append(",deptName='"+deptName+"'");
	        }else{
	        	str.append(",deptName=''");
	        }
	        str.append(" where webId=?");
	        if((jlYj.getYjName()!=null && !jlYj.getYjName().equals(yjName.trim())) || (jlYj.getYjCard()!=null && !jlYj.getYjCard().equals(yjCard.trim())) || (jlYj.getJq()!=null && !jlYj.getJq().equals(jqId.trim()))){
	        	StringBuffer str2=new StringBuffer("编号为"+jlYj.getYjNo()+"的狱警");
	        	if(jlYj.getYjName()!=null && !jlYj.getYjName().equals(yjName.trim())){
	        		str2.append(",狱警名称"+jlYj.getYjName()+"修改为"+yjName.trim());
	        	}
	        	if(jlYj.getYjCard()!=null && !jlYj.getYjCard().equals(yjCard.trim())){
	        		str2.append(",狱警IC卡"+jlYj.getYjCard()+"修改为"+yjCard.trim());
	        	}
	        	if(jlYj.getJq()!=null && !jlYj.getJq().equals(jqId.trim())){
	        		str2.append(",所属监区编号"+jlYj.getJq()+"修改为"+jqId.trim());
	        	}
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
	        	sl.setModel("狱警管理");
	        	sl.setInfo(str2.toString());
	        	sl.setOp("修改狱警信息");
	        	yms.save(sl);
	        }
	        yms.updates(str.toString(),Integer.parseInt(webId));
	        JSONArray json=JSONArray.fromObject(null);
	        response.getWriter().println(json.toString());
		    return null;
	}
	//修改狱警
	public ActionForward updateYjMessage1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
		    JlYj jlYj=(JlYj)yms.findById(JlYj.class, Integer.parseInt(webId));
		    request.setAttribute("jlYj", jlYj);
		    
		    String deptName2 = java.net.URLDecoder.decode((String)request.getParameter("deptName2"),"UTF-8") ;
		    String yjNum2 = java.net.URLDecoder.decode((String)request.getParameter("yjNum2"),"UTF-8") ;
		    String jyName2 = java.net.URLDecoder.decode((String)request.getParameter("jyName2"),"UTF-8") ;
		    String pageNo2=request.getParameter("pageNo2");
		    request.setAttribute("deptName2", deptName2);
		    request.setAttribute("yjNum2", yjNum2);
		    request.setAttribute("jyName2", jyName2);
		    request.setAttribute("pageNo2", pageNo2);
		    return mapping.findForward("updateYjMessage1");
	}
	public ActionForward updateSaveyjMessage1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String webId = request.getParameter("webId");
		    String yjNum = java.net.URLDecoder.decode((String)request.getParameter("yjNum"),"UTF-8") ;
		    JlYj jlYj=(JlYj)yms.findById(JlYj.class, Integer.parseInt(webId));
		    jlYj.setYjNum(yjNum);
		    yms.update(jlYj);
		    JSONArray json=JSONArray.fromObject(null);
	        response.getWriter().println(json.toString());
		    return null;
	}
	//删除狱警
	public ActionForward delYjMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String webId=request.getParameter("webId");
		    JlYj jlYj=(JlYj)yms.findById(JlYj.class, Integer.parseInt(webId));
		    yms.delete(JlYj.class, Integer.parseInt(webId));
		    Calendar c = Calendar.getInstance();   
			SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
			c  =  Calendar.getInstance(Locale.CHINESE);   
			String loginTime = simpleDateTimeFormat.format(c.getTime());
			SysLog sl=new SysLog();
		    sl.setType("严重");
			sl.setLogTime(loginTime);
			sl.setUserName(user.getUserName());
			sl.setUserNo(user.getUserNo());
			sl.setUserIp(request.getRemoteAddr());
			sl.setModel("狱警管理");
			sl.setInfo("删除狱警（狱警编号："+jlYj.getYjNo()+" 狱警名称："+jlYj.getYjName()+"）");
			sl.setOp("删除狱警信息");
			yms.save(sl);
			return mapping.findForward("delSuccess");
	}
	//通过服务器名称查询监区
	public ActionForward checkJq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	String jy1 = java.net.URLDecoder.decode((String)request.getParameter("jy1"),"UTF-8");
		 	if(user.getIsSuper()==1){
		 	 	String hql="from JlJq where jy='"+jy1+"'";
		 	 	List<JlJq> list=yms.searchAll(hql);
		 	 	JSONArray jsonArray=JSONArray.fromObject(list);
		 	 	response.setContentType("text/json; charset=UTF-8");   
				response.setCharacterEncoding("UTF-8");  
		 	 	response.getWriter().println(jsonArray.toString());
		 	}else if(user.getGroupNo().equals("Admin")){
		 		String hql="from JlJq where jy='"+jy1+"' and isTs!=1";
		 	 	List<JlJq> list=yms.searchAll(hql);
		 	 	JSONArray jsonArray=JSONArray.fromObject(list);
		 	 	response.setContentType("text/json; charset=UTF-8");   
				response.setCharacterEncoding("UTF-8");  
		 	 	response.getWriter().println(jsonArray.toString());
		 	}else{
		 		String sql="select jl.JQ_No,jl.JQ_Name from JL_JQ jl,SYS_USER_JQ sys where jl.JQ_No=sys.JQ_No and sys.jy='"+jy1+"' and sys.Group_No='"+user.getGroupNo()+"'";
		 		List list=yms.searchAllBySql(sql);
		 		List<JlJq> list1=new ArrayList();
		 		Iterator it=list.iterator();
		 		while(it.hasNext()){
		 			Object[] objects=(Object[])it.next();
		 			JlJq jlJq=new JlJq();
		 			jlJq.setJqNo(objects[0].toString());
		 			jlJq.setJqName(objects[1].toString());
		 			list1.add(jlJq);
		 		}
		 		JSONArray jsonArray=JSONArray.fromObject(list1);
		 	 	response.setContentType("text/json; charset=UTF-8");   
				response.setCharacterEncoding("UTF-8");  
		 	 	response.getWriter().println(jsonArray.toString());
		 	}
		 	return null;
	}
}
