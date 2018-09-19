package com.slxt.rs.action;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.slxt.rs.form.HjrSpForm;
import com.slxt.rs.model.JlHjDj;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.model.SysUserGroup;
import com.slxt.rs.svc.HjrSpService;
import com.slxt.rs.util.Constant;
import com.slxt.rs.vo.HJSP;
import com.slxt.rs.vo.HJSPBM;
import com.slxt.rs.vo.HJSPQS;
import com.slxt.rs.vo.HjSpVO;
import com.slxt.rs.vo.Page;

public class HjrSpAction extends DispatchAction{
	private HjrSpService hss;

	public void setHss(HjrSpService hss) {
		this.hss = hss;
	}
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user = (SysUser) request.getSession().getAttribute("users");
			if (user == null) {
				return mapping.findForward("sessionFailure");
			}
			HjrSpForm hsf = (HjrSpForm) form;
			Page page = new Page();
			page.setPageNo(1);
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			
			//StringBuffer str1 = new StringBuffer("select sq.WebID,sq.FR_Name,sq.JQ_Name,sq.SQ_UserName,sq.SQ_Dept,sq.SQ_Nr,sq.SQ_Sm,sq.SQ_Time,mx.SP_Dept,mx.SP_User_Name,sq.SP_State,dbo.f_get_sp_js(sq.SQ_ID) as SQ_ID,sq.HJ_Type from JL_HJ_SQ sq,JL_SP_MX mx where sq.SQ_ID=mx.sqid and mx.xh=dbo.f_get_Mxsp_xh(sq.SQ_ID)");
			StringBuffer str1 = new StringBuffer("select sp.SPID,sp.SP_State,sp.SP_Group_No,sp.SP_Tj_Time, sp.SP_Tj_User, fr.FR_Name,dj.JQ_Name,sp.SP_User,sp.SP_Tj_UserName,sp.SP_UserName from JL_HJ_SP sp, JL_HJ_SP_FR fr, JL_HJ_DJ dj  where sp.SPID=fr.SPID and sp.HJID=dj.HJID and sp.SP_State<>0 ");
			if (hsf.getCallTimeEnd() != null || hsf.getCallTimeStart() != null || hsf.getSpState() != null  || hsf.getFrName()!=null ) {
				if (hsf.getCallTimeStart() != null && !hsf.getCallTimeStart().trim().equals("")) {
					str1.append(" and sp.SP_Tj_Time>='"+ hsf.getCallTimeStart() + "'");
					todayBegin = hsf.getCallTimeStart();
				}
				if (hsf.getCallTimeEnd() != null && !hsf.getCallTimeEnd().trim().equals("")) {
					str1.append(" and sp.SP_Tj_Time<='"+ hsf.getCallTimeEnd() + "'");
					todayEnd = hsf.getCallTimeEnd();
				}
				if (hsf.getSpState() != null && !hsf.getSpState().trim().equals("null")) {
					str1.append(" and sp.SP_State='"+ hsf.getSpState() + "'");
					todayEnd = hsf.getCallTimeEnd();
				}
				if (hsf.getFrName() != null && !hsf.getFrName().trim().equals("")) {
					str1.append(" and fr.FR_Name like '%"+ hsf.getFrName() + "%'");
					todayEnd = hsf.getCallTimeEnd();
				}
				
			} else {
				StringBuffer str = new StringBuffer(now.substring(0, 10));
				str.append(" 00:00:00");
				todayBegin = str.toString();
				StringBuffer str2 = new StringBuffer(now.substring(0, 10));
				str2.append(" 23:59:59");
				todayEnd = str2.toString();
				str1.append(" and sp.SP_Tj_Time>='"+ todayBegin + "'");
				str1.append(" and sp.SP_Tj_Time<='"+ todayEnd + "'");
			}
			Object[] obj1 = {};
			Map map = hss.searchToPageBySql(str1.toString(), page.getPageNo(),20, obj1);
			page.setRecordCount((Integer) map.get(Constant.ALLFILEDCOUNT));
			List list=(List)map.get(Constant.RESULTLIST);
			for(int i=0;i<list.size();i++){
				Object[] obj=(Object[])list.get(i);
				HjSpVO hjSpVO=new HjSpVO();
				hjSpVO.setSpId(obj[0].toString());
				hjSpVO.setSpState(obj[1].toString());
				if(obj[2]!=null && !obj[2].toString().equals("")){
					String sql1="select Group_No,Group_Name from SYS_USER_GROUP where Group_No='"+obj[2].toString()+"'";
					List list1 =  hss.searchAllBySql(sql1);
					if(list1.size()>0){
						Object[] obj3 = (Object[])list1.get(0);
						if(obj3[0]!=null && !obj3[0].toString().equals("") ){
							hjSpVO.setSpGroupNo(obj3[0].toString());
						}
						if(obj3[1]!=null && !obj3[1].toString().equals("") ){
							hjSpVO.setSpGroupName(obj3[1].toString());
							
						}
					}
				}
				if(obj[3]!=null && !obj[3].toString().equals("")){
					hjSpVO.setSpTjTime(obj[3].toString());
				}
				if(obj[4]!=null && !obj[4].toString().equals("")){
					hjSpVO.setSpTjUser(obj[4].toString());
				}
				if(obj[5]!=null && !obj[5].toString().equals("")){
					hjSpVO.setFrName(obj[5].toString());
				}
				if(obj[6]!=null && !obj[6].toString().equals("")){
					hjSpVO.setJqName(obj[6].toString());
				}
				if(obj[7]!=null && !obj[7].toString().equals("")){
					hjSpVO.setSpUser(obj[7].toString());
				}
				if(obj[8]!=null && !obj[8].toString().equals("")){
					hjSpVO.setSpTjUserName(obj[8].toString());
				}
				if(obj[9]!=null && !obj[9].toString().equals("")){
					hjSpVO.setSpUserName(obj[9].toString());
				}
				page.getList().add(hjSpVO);
			}
			page.setPageSize(20);
			
			
			
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("hjrspMain");
		}
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user = (SysUser) request.getSession().getAttribute("users");
			if (user == null) {
				return mapping.findForward("sessionFailure");
			}
			HjrSpForm hsf = (HjrSpForm) form;
			Page page = new Page();
			page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			
			StringBuffer str1 = new StringBuffer("select sp.SPID,sp.SP_State,sp.SP_Group_No,sp.SP_Tj_Time, sp.SP_Tj_User, fr.FR_Name,dj.JQ_Name,sp.SP_User,sp.SP_Tj_UserName,sp.SP_UserName from JL_HJ_SP sp, JL_HJ_SP_FR fr, JL_HJ_DJ dj  where sp.SPID=fr.SPID and sp.HJID=dj.HJID ");
			if (hsf.getCallTimeEnd() != null || hsf.getCallTimeStart() != null || hsf.getSpState() != null  || hsf.getFrName()!=null ) {
				if (hsf.getCallTimeStart() != null && !hsf.getCallTimeStart().trim().equals("")) {
					str1.append(" and sp.SP_Tj_Time>='"+ hsf.getCallTimeStart() + "'");
					todayBegin = hsf.getCallTimeStart();
				}
				if (hsf.getCallTimeEnd() != null && !hsf.getCallTimeEnd().trim().equals("")) {
					str1.append(" and sp.SP_Tj_Time<='"+ hsf.getCallTimeEnd() + "'");
					todayEnd = hsf.getCallTimeEnd();
				}
				if (hsf.getSpState() != null && !hsf.getSpState().trim().equals("null")) {
					str1.append(" and sp.SP_State='"+ hsf.getSpState() + "'");
					todayEnd = hsf.getCallTimeEnd();
				}
				if (hsf.getFrName() != null && !hsf.getFrName().trim().equals("")) {
					str1.append(" and fr.FR_Name like '%"+ hsf.getFrName() + "%'");
					todayEnd = hsf.getCallTimeEnd();
				}
				
			} else {
				StringBuffer str = new StringBuffer(now.substring(0, 10));
				str.append(" 00:00:00");
				todayBegin = str.toString();
				StringBuffer str2 = new StringBuffer(now.substring(0, 10));
				str2.append(" 23:59:59");
				todayEnd = str2.toString();
				str1.append(" and sp.SP_Tj_Time>='"+ todayBegin + "'");
				str1.append(" and sp.SP_Tj_Time<='"+ todayEnd + "'");
			}
			Object[] obj1 = {};
			Map map = hss.searchToPageBySql(str1.toString(), page.getPageNo(),20, obj1);
			page.setRecordCount((Integer) map.get(Constant.ALLFILEDCOUNT));
			List list=(List)map.get(Constant.RESULTLIST);
			for(int i=0;i<list.size();i++){
				Object[] obj=(Object[])list.get(i);
				HjSpVO hjSpVO=new HjSpVO();
				hjSpVO.setSpId(obj[0].toString());
				hjSpVO.setSpState(obj[1].toString());
				if(obj[2]!=null && !obj[2].toString().equals("")){
					String sql1="select Group_No,Group_Name from SYS_USER_GROUP where Group_No='"+obj[2].toString()+"'";
					List list1 =  hss.searchAllBySql(sql1);
					if(list1.size()>0){
						Object[] obj3 = (Object[])list1.get(0);
						if(obj3[0]!=null && !obj3[0].toString().equals("") ){
							hjSpVO.setSpGroupNo(obj3[0].toString());
						}
						if(obj3[1]!=null && !obj3[1].toString().equals("") ){
							hjSpVO.setSpGroupName(obj3[1].toString());
						}
					}
				}
				if(obj[3]!=null && !obj[3].toString().equals("")){
					hjSpVO.setSpTjTime(obj[3].toString());
				}
				if(obj[4]!=null && !obj[4].toString().equals("")){
					hjSpVO.setSpTjUser(obj[4].toString());
				}
				if(obj[5]!=null && !obj[5].toString().equals("")){
					hjSpVO.setFrName(obj[5].toString());
				}
				if(obj[6]!=null && !obj[6].toString().equals("")){
					hjSpVO.setJqName(obj[6].toString());
				}
				if(obj[7]!=null && !obj[7].toString().equals("")){
					hjSpVO.setSpUser(obj[7].toString());
				}
				if(obj[8]!=null && !obj[8].toString().equals("")){
					hjSpVO.setSpTjUserName(obj[8].toString());
				}
				if(obj[9]!=null && !obj[9].toString().equals("")){
					hjSpVO.setSpUserName(obj[9].toString());
				}
				page.getList().add(hjSpVO);
			}
			page.setPageSize(20);
			
			
			
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("hjrspMain");
		}
	public ActionForward updateHjSp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user = (SysUser) request.getSession().getAttribute("users");
		if (user == null) {
			return mapping.findForward("sessionFailure");
		}
		String spId = request.getParameter("spId");
		JlHjDj jlHjDj=null;
		HJSP hjsp = new HJSP();
		
		
		//根据会见ID查询审批表
		String sql1="select SPID,SP_JB,HJID from JL_HJ_SP where SPID='"+spId+"'";
		List list1 = hss.searchAllBySql(sql1);
		if(list1.size()>0){
			Object[] obj =(Object[]) list1.get(0);
			if(obj[0]!=null && !obj[0].toString().trim().equals("")){
				hjsp.setSpId(obj[0].toString().trim());
			}
			if(obj[1]!=null && !obj[1].toString().trim().equals("")){
				hjsp.setSpJb(obj[1].toString().trim());
			}
			if(obj[2]!=null && !obj[2].toString().trim().equals("")){
				String hql="from JlHjDj where HJID='"+obj[2].toString().trim()+"' and state=3";
				List<JlHjDj> list=hss.searchAll(hql);
				if(list.size()>0){
					jlHjDj=(JlHjDj)list.get(0);
				}else{
					return null;
				}
				hjsp.setJqName(jlHjDj.getJqName());
				hjsp.setFrNo(jlHjDj.getFrNo());
				hjsp.setFrName(jlHjDj.getFrName());
				hjsp.setHjid(jlHjDj.getHjid()+"");
			}
		}
		request.setAttribute("hjsp", hjsp);
		
		//查询审批部门
		List<HJSPBM> bmlist = new ArrayList<HJSPBM>();
		String sqlbm="select Group_No,Group_Name from SYS_USER_GROUP where Is_SP_Depart=1 and SP_QX_JB>"+Integer.parseInt(hjsp.getSpJb())+"";
		List listbm = hss.searchAllBySql(sqlbm);
		if(listbm.size()>0){
			for(int i=0;i<listbm.size();i++){
				HJSPBM hjspbm = new HJSPBM();
				Object[] obj = (Object[]) listbm.get(i);
				if(obj[0]!=null && !obj[0].toString().trim().equals("")){
					hjspbm.setGroupNo(obj[0].toString().trim());
				}
				if(obj[1]!=null && !obj[1].toString().trim().equals("")){
					hjspbm.setGroupName(obj[1].toString().trim());
				}
				bmlist.add(hjspbm);
			}
		}
		request.setAttribute("bmlist", bmlist);
		//查询审批犯人表
		String sqlfr="select SP_Reason,SP_Remarks from JL_HJ_SP_FR where SPID='"+hjsp.getSpId()+"'";
		List listfr =hss.searchAllBySql(sqlfr);
		if(listfr.size()>0){
			Object[] obj = (Object[]) listfr.get(0);
			if(obj[0]!=null && !obj[0].toString().trim().equals("")){
				hjsp.setSpReason(obj[0].toString().trim());
			}
			if(obj[1]!=null && !obj[1].toString().trim().equals("")){
				hjsp.setSpRemarks(obj[1].toString().trim());
			}
		}
		//查询审批亲属表
		List<HJSPQS> spqslist = new ArrayList<HJSPQS>();
		String sql2="select SPID,QS_Info,Special,SP_Remarks,QS_Annex,QS_SFZ,QS_XB,QS_ZP,SP_Reason,WebID,QS_ZJLB,SPBZ from JL_HJ_SP_QS where SPID='"+hjsp.getSpId()+"'";
		List list2 = hss.searchAllBySql(sql2);
		if(list2.size()>0){
			for(int i=0;i<list2.size();i++){
				HJSPQS hjspqs = new HJSPQS();
				Object[] obj = (Object[]) list2.get(i);
				if(obj[0]!=null && !obj[0].toString().trim().equals("")){
					hjspqs.setSpId(obj[0].toString().trim());
				}
				if(obj[1]!=null && !obj[1].toString().trim().equals("")){
					//hjspqs.setQsInfo(obj[1].toString().trim());
					hjspqs.setQsName(obj[1].toString().trim().substring(obj[1].toString().trim().lastIndexOf("]")+1));
					hjspqs.setQsGx(obj[1].toString().trim().substring(obj[1].toString().trim().lastIndexOf("[")+1,obj[1].toString().trim().lastIndexOf("]")));
				}
				if(obj[2]!=null && !obj[2].toString().trim().equals("")){
					hjspqs.setSpecial(obj[2].toString().trim());
				}
				if(obj[3]!=null && !obj[3].toString().trim().equals("")){
					hjspqs.setSpRemarks(obj[3].toString().trim());
				}
				if(obj[4]!=null && !obj[4].toString().trim().equals("")){
					hjspqs.setQsAnnex(obj[4].toString().trim());
				}
				if(obj[5]!=null && !obj[5].toString().trim().equals("")){
					hjspqs.setQsSFZ(obj[5].toString().trim());
				}
				if(obj[6]!=null && !obj[6].toString().trim().equals("")){
					hjspqs.setQsXB(obj[6].toString().trim());
				}
				if(obj[7]!=null && !obj[7].toString().trim().equals("")){
					hjspqs.setQsZP(obj[7].toString().trim());
				}
				if(obj[8]!=null && !obj[8].toString().trim().equals("")){
					hjspqs.setSpReason(obj[8].toString().trim());
				}
				if(obj[9]!=null && !obj[9].toString().trim().equals("")){
					hjspqs.setWebId(obj[9].toString().trim());
				}
				if(obj[10]!=null && !obj[10].toString().trim().equals("")){
					hjspqs.setQsZjlb(obj[10].toString());
				}
				if(obj[11]!=null && !obj[11].toString().trim().equals("")){
					hjspqs.setSpbz(obj[11].toString());
				}
				spqslist.add(hjspqs);
			}
		}
				
		int spUserIsNodisable=0;
		request.setAttribute("spUserIsNodisable",spUserIsNodisable);
		request.setAttribute("spqslist", spqslist);
		return mapping.findForward("tohjsp");
	}
	//通过用户组编号查询用户
	public ActionForward checkSpUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	String groupNo = java.net.URLDecoder.decode((String)request.getParameter("spbm1"),"UTF-8");
		 	

	 		String sql="select User_No,User_Name from SYS_USER where Group_No='"+groupNo+"'";
	 		List list=hss.searchAllBySql(sql);
	 		List<SysUser> list1=new ArrayList();
	 		Iterator it=list.iterator();
	 		while(it.hasNext()){
	 			Object[] objects=(Object[])it.next();
	 			SysUser sysUser=new SysUser();
	 			sysUser.setUserNo(objects[0].toString());
	 			sysUser.setUserName(objects[1].toString());
	 			list1.add(sysUser);
	 		}
	 		JSONArray jsonArray=JSONArray.fromObject(list1);
	 	 	response.setContentType("text/json; charset=UTF-8");   
			response.setCharacterEncoding("UTF-8");  
	 	 	response.getWriter().println(jsonArray.toString());

		 	return null;
	}
	//提交会见审批
	public ActionForward tjhjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user = (SysUser) request.getSession().getAttribute("users");
		if (user == null) {
			return mapping.findForward("sessionFailure");
		}
		String spid = request.getParameter("spid").trim();
		String spbm1 = request.getParameter("spbm1");
		String spUser = request.getParameter("spUser");
		Integer spjg = Integer.parseInt(request.getParameter("spjg")) ;
		Integer spjb = Integer.parseInt(request.getParameter("spjb").trim());
		Integer hjid = Integer.parseInt(request.getParameter("hjid").trim());
		
		String hql11="from SysUserGroup where groupNo='"+spbm1+"'";
 		List<SysUserGroup> list=hss.searchAll(hql11);
 				
 		String hql="from SysUser where userNo='"+spUser+"'";
	 	List<SysUser> list1=hss.searchAll(hql);		
		if(spjg==1){
			if(spbm1!=null && !spbm1.trim().equals("null")){
				String sql="update JL_HJ_SP set SP_JB="+list.get(0).getSpQxJb()+", SP_Group_No='"+spbm1.trim()+"', SP_Result="+spjg+",SP_User='"+spUser+"',SP_UserName='"+list1.get(0).getUserName()+"' where SPID="+Integer.parseInt(spid);
				hss.executeUpdate(sql);
				
			}else{
				List list2=hss.searchAll("from JlHjDj where hjid="+hjid+"");
				if(list2.size()>0){
					JlHjDj jlHjDj1=(JlHjDj)list2.get(0);
					String qsSQL="update JL_QS set sp_state=1 where FR_No='"+jlHjDj1.getFrNo()+"' and sp_state=0";
	 				hss.executeUpdate(qsSQL);
				}
				String sql2="update JL_HJ_DJ set state=0 where HJID="+hjid;
				hss.executeUpdate(sql2);
				
				String sql="update JL_HJ_SP set SP_Result="+spjg+", SP_State=2 where SPID="+Integer.parseInt(spid);
				hss.executeUpdate(sql);
			}
			String sql1="insert into JL_HJ_SP_RESULT (SPID, SP_JB, SP_Result,  SP_User) values ('"+spid+"','"+spjb+"', '"+spjg+"','"+user.getUserName()+"' )";
			hss.executeUpdate(sql1);
		}else{
			List list2=hss.searchAll("from JlHjDj where hjid="+hjid+"");
			if(list2.size()>0){
				JlHjDj jlHjDj1=(JlHjDj)list2.get(0);
				String qsSQL="delete from JL_QS where FR_No='"+jlHjDj1.getFrNo()+"' and sp_state<>1";
 				hss.executeUpdate(qsSQL);
			}
			String sql="update JL_HJ_SP set SP_Result="+spjg+", SP_State=3 where SPID="+Integer.parseInt(spid);
			hss.executeUpdate(sql);
			String sql1="insert into JL_HJ_SP_RESULT (SPID, SP_JB, SP_Result,  SP_User) values ('"+spid+"','"+spjb+"', '"+spjg+"','"+user.getUserName()+"' )";
			hss.executeUpdate(sql1);
			String sql2="update JL_HJ_DJ set state=2,Cancel_Info='审批未通过' where HJID="+hjid;
			hss.executeUpdate(sql2);
		}
		
		JSONArray json=JSONArray.fromObject(0);
 	    response.getWriter().println(json.toString());
		
 		return null;
	}
	
	//修改保存犯人备注
	public ActionForward updateFRRemarks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user = (SysUser) request.getSession().getAttribute("users");
		if (user == null) {
			return mapping.findForward("sessionFailure");
		}
		String spid = request.getParameter("spid");
		String frRemarks = request.getParameter("frRemarks");
		if(frRemarks!=null ){
			String sql="update JL_HJ_SP_FR set SP_Remarks='"+frRemarks.trim()+"' where SPID="+Long.parseLong(spid.trim());
			hss.executeUpdate(sql);
		}
		return null;
	}
	//修改保存亲属备注
	public ActionForward updateQSRemarks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user = (SysUser) request.getSession().getAttribute("users");
		if (user == null) {
			return mapping.findForward("sessionFailure");
		}
		String webId = request.getParameter("webId");
		String qsRemarks = request.getParameter("qsRemarks");
		if(qsRemarks!=null ){
			String sql="update JL_HJ_SP_QS set SP_Remarks='"+qsRemarks.trim()+"' where WebId="+Integer.parseInt(webId.trim());
			hss.executeUpdate(sql);
		}
		return null;
	}
	
	public ActionForward seeHjSp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user = (SysUser) request.getSession().getAttribute("users");
		if (user == null) {
			return mapping.findForward("sessionFailure");
		}
		Integer spId = Integer.parseInt(request.getParameter("spId").trim());
		JlHjDj jlHjDj=null;
		HJSP hjsp = new HJSP();
		
		//根据会见ID查询审批表
		String sql1="select SPID,SP_JB,HJID from JL_HJ_SP where SPID="+spId;
		List list1 = hss.searchAllBySql(sql1);
		if(list1.size()>0){
			Object[] obj =(Object[]) list1.get(0);
			if(obj[0]!=null && !obj[0].toString().trim().equals("")){
				hjsp.setSpId(obj[0].toString().trim());
			}
			if(obj[1]!=null && !obj[1].toString().trim().equals("")){
				hjsp.setSpJb(obj[1].toString().trim());
			}
			if(obj[2]!=null && !obj[2].toString().trim().equals("")){
				String hql="from JlHjDj where HJID="+obj[2].toString().trim();
				List<JlHjDj> list=hss.searchAll(hql);
				if(list.size()>0){
					jlHjDj=(JlHjDj)list.get(0);
				}else{
					return null;
				}
				hjsp.setJqName(jlHjDj.getJqName());
				hjsp.setFrNo(jlHjDj.getFrNo());
				hjsp.setFrName(jlHjDj.getFrName());
				hjsp.setHjid(jlHjDj.getHjid()+"");
			}
		}
		request.setAttribute("hjsp", hjsp);
		
		//查询审批部门
		List<HJSPBM> bmlist = new ArrayList<HJSPBM>();
		String sqlbm="select Group_No,Group_Name from SYS_USER_GROUP where Is_SP_Depart=1 and SP_QX_JB='"+(Integer.parseInt(hjsp.getSpJb())+1)+"'";
		List listbm = hss.searchAllBySql(sqlbm);
		if(listbm.size()>0){
			for(int i=0;i<listbm.size();i++){
				HJSPBM hjspbm = new HJSPBM();
				Object[] obj = (Object[]) listbm.get(i);
				if(obj[0]!=null && !obj[0].toString().trim().equals("")){
					hjspbm.setGroupNo(obj[0].toString().trim());
				}
				if(obj[1]!=null && !obj[1].toString().trim().equals("")){
					hjspbm.setGroupName(obj[1].toString().trim());
				}
				bmlist.add(hjspbm);
			}
		}
		request.setAttribute("bmlist", bmlist);
		//查询审批犯人表
		String sqlfr="select SP_Reason,SP_Remarks from JL_HJ_SP_FR where SPID='"+hjsp.getSpId()+"'";
		List listfr =hss.searchAllBySql(sqlfr);
		if(listfr.size()>0){
			Object[] obj = (Object[]) listfr.get(0);
			if(obj[0]!=null && !obj[0].toString().trim().equals("")){
				hjsp.setSpReason(obj[0].toString().trim());
			}
			if(obj[1]!=null && !obj[1].toString().trim().equals("")){
				hjsp.setSpRemarks(obj[1].toString().trim());
			}
		}
		//查询审批亲属表
		List<HJSPQS> spqslist = new ArrayList<HJSPQS>();
		String sql2="select SPID,QS_Info,Special,SP_Remarks,QS_Annex,QS_SFZ,QS_XB,QS_ZP,SP_Reason,WebID,QS_ZJLB,SPBZ from JL_HJ_SP_QS where SPID='"+hjsp.getSpId()+"'";
		List list2 = hss.searchAllBySql(sql2);
		if(list2.size()>0){
			for(int i=0;i<list2.size();i++){
				HJSPQS hjspqs = new HJSPQS();
				Object[] obj = (Object[]) list2.get(i);
				if(obj[0]!=null && !obj[0].toString().trim().equals("")){
					hjspqs.setSpId(obj[0].toString().trim());
				}
				if(obj[1]!=null && !obj[1].toString().trim().equals("")){
					//hjspqs.setQsInfo(obj[1].toString().trim());
					hjspqs.setQsName(obj[1].toString().trim().substring(obj[1].toString().trim().lastIndexOf("]")+1));
					hjspqs.setQsGx(obj[1].toString().trim().substring(obj[1].toString().trim().lastIndexOf("[")+1,obj[1].toString().trim().lastIndexOf("]")));
				}
				if(obj[2]!=null && !obj[2].toString().trim().equals("")){
					hjspqs.setSpecial(obj[2].toString().trim());
				}
				if(obj[3]!=null && !obj[3].toString().trim().equals("")){
					hjspqs.setSpRemarks(obj[3].toString().trim());
				}
				if(obj[4]!=null && !obj[4].toString().trim().equals("")){
					hjspqs.setQsAnnex(obj[4].toString().trim());
				}
				if(obj[5]!=null && !obj[5].toString().trim().equals("")){
					hjspqs.setQsSFZ(obj[5].toString().trim());
				}
				if(obj[6]!=null && !obj[6].toString().trim().equals("")){
					hjspqs.setQsXB(obj[6].toString().trim());
				}
				if(obj[7]!=null && !obj[7].toString().trim().equals("")){
					hjspqs.setQsZP(obj[7].toString().trim());
				}
				if(obj[8]!=null && !obj[8].toString().trim().equals("")){
					hjspqs.setSpReason(obj[8].toString().trim());
				}
				if(obj[9]!=null && !obj[9].toString().trim().equals("")){
					hjspqs.setWebId(obj[9].toString().trim());
				}
				if(obj[10]!=null && !obj[10].toString().trim().equals("")){
					hjspqs.setQsZjlb(obj[10].toString());
				}
				if(obj[11]!=null && !obj[11].toString().trim().equals("")){
					hjspqs.setSpbz(obj[11].toString());
				}
				spqslist.add(hjspqs);
			}
		}
		
		
		
		request.setAttribute("spqslist", spqslist);
		return mapping.findForward("seeHjSp");
	}
}
