package com.slxt.rs.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import com.slxt.rs.form.CancelDjForm;
import com.slxt.rs.form.HjRecordForm;
import com.slxt.rs.model.JlHjDj;
import com.slxt.rs.model.JlHjRec;
import com.slxt.rs.model.JlJq;
import com.slxt.rs.model.JlYj;
import com.slxt.rs.model.SysHjServer;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.CancelDjService;
import com.slxt.rs.util.Constant;
import com.slxt.rs.vo.HjDjVO;
import com.slxt.rs.vo.MessageVO;
import com.slxt.rs.vo.Page;
import com.slxt.rs.vo.RecordSearch;

public class CancelDjAction extends DispatchAction{
	private CancelDjService cds;

	public void setCds(CancelDjService cds) {
		this.cds = cds;
	}
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user = (SysUser) request.getSession().getAttribute("users");
			if (user == null) {
				return mapping.findForward("sessionFailure");
			}
			CancelDjForm cdf = (CancelDjForm) form;
			Page page = new Page();
			page.setPageNo(1);
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			if(cdf.getBeginTime()!=null || cdf.getEndTime()!=null || cdf.getState()!=null || cdf.getHjType()!=null || cdf.getFrNo()!=null || cdf.getFrName()!=null || cdf.getJq()!=null){
				StringBuffer str=null;
				if(user.getIsSuper()==1){
					str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj where 1=1");
				}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
					str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq where dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0");
				}else{
					str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"'");
				}
				if(cdf.getBeginTime()!=null){
					str.append(" and dj.DJ_Time >='"+cdf.getBeginTime()+"'");
					todayBegin=cdf.getBeginTime();
				}
				if(cdf.getEndTime()!=null){
					str.append(" and dj.DJ_Time <='"+cdf.getEndTime()+"'");
					todayEnd=cdf.getEndTime();
				}
				if(cdf.getState()!=null && !cdf.getState().equals("null")){
					str.append(" and dj.State ="+cdf.getState());
				}
				if(cdf.getHjType()!=null && !cdf.getHjType().equals("null")){
					str.append(" and dj.HJ_Type ="+cdf.getHjType());
				}
				if(cdf.getFrNo()!=null && !cdf.getFrNo().equals("")){
					str.append(" and dj.FR_No ='"+cdf.getFrNo()+"'");
				}
				if(cdf.getFrName()!=null && !cdf.getFrName().equals("")){
					str.append(" and (dbo.f_get_fryp(dj.FR_Name,'"+cdf.getFrName().trim()+"') =1 or dj.FR_Name like '%"+cdf.getFrName()+"%')");
				}
				if(cdf.getJq()!=null && !cdf.getJq().equals("null")){
					str.append(" and dj.JQ_No ='"+cdf.getJq()+"'");
				}
				str.append(" order by dj.JY,dj.JQ_Name,dj.FP_Flag,dj.HJID desc");
				Object[] obj={};
				Map map=cds.searchToPageBySql(str.toString(),page.getPageNo(),20,obj);
				page.setPageSize(20);
				page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
				List list=(List)map.get(Constant.RESULTLIST);
				for(int i=0;i<list.size();i++){
					Object[] obj1=(Object[])list.get(i);
			    	HjDjVO hjDjVO=new HjDjVO();
			    	hjDjVO.setHjid(obj1[0].toString());
			    	hjDjVO.setJy(obj1[1].toString());
			    	if(obj1[2]!=null && !obj1[2].toString().equals("")){
			    		hjDjVO.setJqName(obj1[2].toString());
			    	}
			    	hjDjVO.setFrNo(obj1[3].toString());
			    	if(obj1[4]!=null && !obj1[4].toString().equals("")){
			    		hjDjVO.setFrName(obj1[4].toString());
			    	}
			    	String qsName="";
			    	if(obj1[5]!=null && !obj1[5].toString().equals("")){
			    		qsName+=obj1[5].toString();
			    	}
			    	if(obj1[6]!=null && !obj1[6].toString().equals("")){
			    		qsName+=obj1[6].toString();
			    	}
			    	if(obj1[7]!=null && !obj1[7].toString().equals("")){
			    		qsName+=obj1[7].toString();
			    	}
			    	if(obj1[8]!=null && !obj1[8].toString().equals("")){
			    		qsName+=obj1[8].toString();
			    	}
			    	if(obj1[9]!=null && !obj1[9].toString().equals("")){
			    		qsName+=obj1[9].toString();
			    	}
			    	if(obj1[10]!=null && !obj1[10].toString().equals("")){
			    		qsName+=obj1[10].toString();
			    	}
			    	if(obj1[11]!=null && !obj1[11].toString().equals("")){
			    		qsName+=obj1[11].toString();
			    	}
			    	if(obj1[12]!=null && !obj1[12].toString().equals("")){
			    		qsName+=obj1[12].toString();
			    	}
			    	if(obj1[13]!=null && !obj1[13].toString().equals("")){
			    		qsName+=obj1[13].toString();
			    	}
			    	hjDjVO.setQsInfo1(qsName);
			    	hjDjVO.setHjTime(Integer.parseInt(obj1[14].toString())/60+"");
			    	if(obj1[15]!=null && !obj1[15].toString().equals("")){
			    		hjDjVO.setHjInfo(obj1[15].toString());	
			    	}
			    	hjDjVO.setHjType(obj1[16].toString());
			    	if(obj1[17]!=null && !obj1[17].toString().equals("")){
			    		hjDjVO.setMonitorFlag(obj1[17].toString());
			    	}
			    	hjDjVO.setFpFlag(obj1[18].toString());
			    	if(obj1[19]!=null && !obj1[19].toString().equals("")){
			    		hjDjVO.setZw(obj1[19].toString());
			    	}
			    	if(obj1[20]!=null && !obj1[20].toString().equals("")){
			    		hjDjVO.setDjUser(obj1[20].toString());
			    	}
			    	if(obj1[21]!=null && !obj1[21].toString().equals("")){
			    		hjDjVO.setDjTime(obj1[21].toString().substring(0, 19));
			    	}
			    	if(obj1[22]!=null){
			    		hjDjVO.setCancelInfo(obj1[22].toString());
			    	}else{
			    		hjDjVO.setCancelInfo("未取消");
			    	}
			    	if(obj1[23]!=null && !obj1[23].toString().equals("")){
			    		hjDjVO.setHjIndex(obj1[23].toString());
			    	}
			    	if(obj1[24]!=null && !obj1[24].toString().equals("")){
			    		hjDjVO.setInfoWp(obj1[24].toString());
			    	}else{
			    		hjDjVO.setInfoWp("无");
			    	}
			    	page.getList().add(hjDjVO);
			    }
			}else{
				StringBuffer str = new StringBuffer(now.substring(0, 10));
				str.append(" 00:00:00");
				todayBegin = str.toString();
				StringBuffer str1 = new StringBuffer(now.substring(0, 10));
				str1.append(" 23:59:59");
				todayEnd = str1.toString();
			}
			String hql1="from JlJq";
			List<JlJq> list1=cds.searchAll(hql1);
			request.setAttribute("jlJqList", list1);
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("cancelDjMain");
	}
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user = (SysUser) request.getSession().getAttribute("users");
			if (user == null) {
				return mapping.findForward("sessionFailure");
			}
			CancelDjForm cdf = (CancelDjForm) form;
			Page page = new Page();
			page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			if(cdf.getBeginTime()!=null || cdf.getEndTime()!=null || cdf.getState()!=null || cdf.getHjType()!=null || cdf.getFrNo()!=null || cdf.getFrName()!=null || cdf.getJq()!=null){
				StringBuffer str=null;
				if(user.getIsSuper()==1){
					str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj where 1=1");
				}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
					str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq where dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0");
				}else{
					str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"'");
				}
				if(cdf.getBeginTime()!=null){
					str.append(" and dj.DJ_Time >='"+cdf.getBeginTime()+"'");
					todayBegin=cdf.getBeginTime();
				}
				if(cdf.getEndTime()!=null){
					str.append(" and dj.DJ_Time <='"+cdf.getEndTime()+"'");
					todayEnd=cdf.getEndTime();
				}
				if(cdf.getState()!=null && !cdf.getState().equals("null")){
					str.append(" and dj.State ="+cdf.getState());
				}
				if(cdf.getHjType()!=null && !cdf.getHjType().equals("null")){
					str.append(" and dj.HJ_Type ="+cdf.getHjType());
				}
				if(cdf.getFrNo()!=null && !cdf.getFrNo().equals("")){
					str.append(" and dj.FR_No ='"+cdf.getFrNo()+"'");
				}
				if(cdf.getFrName()!=null && !cdf.getFrName().equals("")){
					str.append(" and (dbo.f_get_fryp(dj.FR_Name,'"+cdf.getFrName().trim()+"') =1 or dj.FR_Name like '%"+cdf.getFrName()+"%')");
				}
				if(cdf.getJq()!=null && !cdf.getJq().equals("null")){
					str.append(" and dj.JQ_No ='"+cdf.getJq()+"'");
				}
				str.append(" order by dj.JY,dj.JQ_Name,dj.FP_Flag,dj.HJID desc");
				Object[] obj={};
				Map map=cds.searchToPageBySql(str.toString(),page.getPageNo(),20,obj);
				page.setPageSize(20);
				page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
				List list=(List)map.get(Constant.RESULTLIST);
				for(int i=0;i<list.size();i++){
					Object[] obj1=(Object[])list.get(i);
			    	HjDjVO hjDjVO=new HjDjVO();
			    	hjDjVO.setHjid(obj1[0].toString());
			    	hjDjVO.setJy(obj1[1].toString());
			    	if(obj1[2]!=null && !obj1[2].toString().equals("")){
			    		hjDjVO.setJqName(obj1[2].toString());
			    	}
			    	hjDjVO.setFrNo(obj1[3].toString());
			    	if(obj1[4]!=null && !obj1[4].toString().equals("")){
			    		hjDjVO.setFrName(obj1[4].toString());
			    	}
			    	String qsName="";
			    	if(obj1[5]!=null && !obj1[5].toString().equals("")){
			    		qsName+=obj1[5].toString();
			    	}
			    	if(obj1[6]!=null && !obj1[6].toString().equals("")){
			    		qsName+=obj1[6].toString();
			    	}
			    	if(obj1[7]!=null && !obj1[7].toString().equals("")){
			    		qsName+=obj1[7].toString();
			    	}
			    	if(obj1[8]!=null && !obj1[8].toString().equals("")){
			    		qsName+=obj1[8].toString();
			    	}
			    	if(obj1[9]!=null && !obj1[9].toString().equals("")){
			    		qsName+=obj1[9].toString();
			    	}
			    	if(obj1[10]!=null && !obj1[10].toString().equals("")){
			    		qsName+=obj1[10].toString();
			    	}
			    	if(obj1[11]!=null && !obj1[11].toString().equals("")){
			    		qsName+=obj1[11].toString();
			    	}
			    	if(obj1[12]!=null && !obj1[12].toString().equals("")){
			    		qsName+=obj1[12].toString();
			    	}
			    	if(obj1[13]!=null && !obj1[13].toString().equals("")){
			    		qsName+=obj1[13].toString();
			    	}
			    	hjDjVO.setQsInfo1(qsName);
			    	hjDjVO.setHjTime(Integer.parseInt(obj1[14].toString())/60+"");
			    	if(obj1[15]!=null && !obj1[15].toString().equals("")){
			    		hjDjVO.setHjInfo(obj1[15].toString());	
			    	}
			    	hjDjVO.setHjType(obj1[16].toString());
			    	if(obj1[17]!=null && !obj1[17].toString().equals("")){
			    		hjDjVO.setMonitorFlag(obj1[17].toString());
			    	}
			    	hjDjVO.setFpFlag(obj1[18].toString());
			    	if(obj1[19]!=null && !obj1[19].toString().equals("")){
			    		hjDjVO.setZw(obj1[19].toString());
			    	}
			    	if(obj1[20]!=null && !obj1[20].toString().equals("")){
			    		hjDjVO.setDjUser(obj1[20].toString());
			    	}
			    	if(obj1[21]!=null && !obj1[21].toString().equals("")){
			    		hjDjVO.setDjTime(obj1[21].toString().substring(0, 19));
			    	}
			    	if(obj1[22]!=null){
			    		hjDjVO.setCancelInfo(obj1[22].toString());
			    	}else{
			    		hjDjVO.setCancelInfo("未取消");
			    	}
			    	if(obj1[23]!=null && !obj1[23].toString().equals("")){
			    		hjDjVO.setHjIndex(obj1[23].toString());
			    	}
			    	if(obj1[24]!=null && !obj1[24].toString().equals("")){
			    		hjDjVO.setInfoWp(obj1[24].toString());
			    	}else{
			    		hjDjVO.setInfoWp("无");
			    	}
			    	page.getList().add(hjDjVO);
			    }
			}else{
				StringBuffer str = new StringBuffer(now.substring(0, 10));
				str.append(" 00:00:00");
				todayBegin = str.toString();
				StringBuffer str1 = new StringBuffer(now.substring(0, 10));
				str1.append(" 23:59:59");
				todayEnd = str1.toString();
			}
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("cancelDjMain");
	}
	public ActionForward search2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user = (SysUser) request.getSession().getAttribute("users");
			if (user == null) {
				return mapping.findForward("sessionFailure");
			}
			CancelDjForm cdf = (CancelDjForm) form;
			Page page = new Page();
			page.setPageNo(1);
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			if(cdf.getBeginTime()!=null || cdf.getEndTime()!=null || cdf.getFrNo()!=null || cdf.getFrName()!=null || cdf.getJq()!=null){
				StringBuffer str=null;
				if(user.getIsSuper()==1){
					str=new StringBuffer("select rec.WebID,cast(rec.Call_Time_Start AS varchar(19)) AS Call_Time_Start,cast(rec.Call_Time_End AS varchar(19)) AS Call_Time_End,rec.Call_Time_Len,rec.ZW,rec.JQ_Name,rec.FR_No,rec.FR_Name,rec.QS_Info1,rec.QS_Info2,rec.QS_Info3,rec.QS_Info4,rec.QS_Info5,rec.QS_Info6,rec.QS_Info7,rec.QS_Info8,rec.QS_Info9 from JL_HJ_REC rec where rec.FR_Out_Time is null");
				}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
					str=new StringBuffer("select rec.WebID,cast(rec.Call_Time_Start AS varchar(19)) AS Call_Time_Start,cast(rec.Call_Time_End AS varchar(19)) AS Call_Time_End,rec.Call_Time_Len,rec.ZW,rec.JQ_Name,rec.FR_No,rec.FR_Name,rec.QS_Info1,rec.QS_Info2,rec.QS_Info3,rec.QS_Info4,rec.QS_Info5,rec.QS_Info6,rec.QS_Info7,rec.QS_Info8,rec.QS_Info9 from JL_HJ_REC rec where rec.FR_Out_Time is null");	
				}else{
					str=new StringBuffer("select rec.WebID,cast(rec.Call_Time_Start AS varchar(19)) AS Call_Time_Start,cast(rec.Call_Time_End AS varchar(19)) AS Call_Time_End,rec.Call_Time_Len,rec.ZW,rec.JQ_Name,rec.FR_No,rec.FR_Name,rec.QS_Info1,rec.QS_Info2,rec.QS_Info3,rec.QS_Info4,rec.QS_Info5,rec.QS_Info6,rec.QS_Info7,rec.QS_Info8,rec.QS_Info9 from JL_HJ_REC rec,SYS_USER_JQ jq where rec.FR_Out_Time is null and rec.JQ_No=jq.JQ_No and jq.Group_No='"+user.getGroupNo()+"'");	
				}
				if(cdf.getBeginTime()!=null){
					str.append(" and rec.Call_Time_Start >='"+cdf.getBeginTime()+"'");
					todayBegin=cdf.getBeginTime();
				}
				if(cdf.getEndTime()!=null){
					str.append(" and rec.Call_Time_Start <='"+cdf.getEndTime()+"'");
					todayEnd=cdf.getEndTime();
				}
				if(cdf.getFrNo()!=null && !cdf.getFrNo().equals("")){
					str.append(" and rec.FR_No ='"+cdf.getFrNo()+"'");
				}
				if(cdf.getFrName()!=null && !cdf.getFrName().equals("")){
					str.append(" and (dbo.f_get_fryp(rec.FR_Name,'"+cdf.getFrName().trim()+"') =1 or rec.FR_Name like '%"+cdf.getFrName()+"%')");
				}
				if(cdf.getJq()!=null && !cdf.getJq().equals("null")){
					str.append(" and rec.JQ_No ='"+cdf.getJq()+"'");
				}
				str.append(" order by rec.WebID desc");
				Object[] obj={};
				Map map=cds.searchToPageBySql(str.toString(),page.getPageNo(),20,obj);
				page.setPageSize(20);
				page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
				List list=(List)map.get(Constant.RESULTLIST);
				for(int i=0;i<list.size();i++){
					Object[] obj1=(Object[])list.get(i);
					RecordSearch recordSearch = new RecordSearch();
					recordSearch.setWebId(Long.parseLong(obj1[0].toString()));
					if (obj1[1] != null && !obj1[1].toString().equals("")) {
						recordSearch.setCallTimeStart(obj1[1].toString());
					}
					if (obj1[2] != null && !obj1[2].toString().equals("")) {
						recordSearch.setCallTimeEnd(obj1[2].toString());
					}	
					if (obj1[3] != null && !obj1[3].toString().equals("")) {
						recordSearch.setCallTimeLen(obj1[3].toString());
					}
					if (obj1[4] != null && !obj1[4].toString().equals("")) {
						recordSearch.setZw(obj1[4].toString());
					}
					if (obj1[5] != null && !obj1[5].toString() .equals("")) {
						recordSearch.setJqName(obj1[5].toString());
					}
					if (obj1[6] != null && !obj1[6].toString().equals("")) {
						recordSearch.setFrNo(obj1[6].toString());
					}
					if (obj1[7] != null && !obj1[7].toString().equals("")) {
						recordSearch.setFrName(obj1[7].toString());
					}
					String info="";
					if (obj1[8] != null && !obj1[8].toString().equals("")) {
						info+=obj1[8].toString();
					}
					if (obj1[9] != null && !obj1[9].toString().equals("")) {
						info+=obj1[9].toString();
					}
					if (obj1[10] != null && !obj1[10].toString().equals("")) {
						info+=obj1[10].toString();
					}
					if (obj1[11] != null && !obj1[11].toString().equals("")) {
						info+=obj1[11].toString();
					}
					if (obj1[12] != null && !obj1[12].toString().equals("")) {
						info+=obj1[12].toString();
					}
					if (obj1[13] != null && !obj1[13].toString().equals("")) {
						info+=obj1[13].toString();
					}
					if (obj1[14] != null && !obj1[14].toString() .equals("")) {
						info+=obj1[14].toString();
					}
					if (obj1[15] != null && !obj1[15].toString() .equals("")) {
						info+=obj1[15].toString();
					}
					if (obj1[16] != null && !obj1[16].toString().equals("")) {
						info+=obj1[16].toString();
					}
					recordSearch.setQsInfo(info);
			    	page.getList().add(recordSearch);
			    }
			}else{
				StringBuffer str = new StringBuffer(now.substring(0, 10));
				str.append(" 00:00:00");
				todayBegin = str.toString();
				StringBuffer str1 = new StringBuffer(now.substring(0, 10));
				str1.append(" 23:59:59");
				todayEnd = str1.toString();
			}
			if (user.getIsSuper() == 1) {
				String hql1="from JlJq";
				List<JlJq> list1=cds.searchAll(hql1);
				request.setAttribute("jlJqList", list1);
			} else if (user.getGroupNo().trim().equals("Admin")) {
				String hql1="from JlJq";
				List<JlJq> list1=cds.searchAll(hql1);
				request.setAttribute("jlJqList", list1);
			} else {
				String sql6 = "select distinct JQ_No from SYS_USER_JQ where Group_No='"+ user.getGroupNo() + "'";
				List list6 = cds.searchAllBySql(sql6);
				if (list6.size() > 0) {
					StringBuffer hql = new StringBuffer("from JlJq ");
					Iterator it = list6.iterator();
					int i = 0;
					while (it.hasNext()) {
						if (i == 0) {
							hql.append(" where jqNo='" + it.next() + "' ");
							i++;
						} else {
							hql.append("or jqNo='" + it.next() + "' ");
							i++;
						}
					}
					List<JlJq> list = cds.searchAll(hql.toString());
					request.setAttribute("jlJqList", list);
				}
			}
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("cancelDjMain1");
	}
	public ActionForward search3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user = (SysUser) request.getSession().getAttribute("users");
			if (user == null) {
				return mapping.findForward("sessionFailure");
			}
			CancelDjForm cdf = (CancelDjForm) form;
			Page page = new Page();
			page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			if(cdf.getBeginTime()!=null || cdf.getEndTime()!=null || cdf.getFrNo()!=null || cdf.getFrName()!=null || cdf.getJq()!=null){
				StringBuffer str=null;
				if(user.getIsSuper()==1){
					str=new StringBuffer("select rec.WebID,cast(rec.Call_Time_Start AS varchar(19)) AS Call_Time_Start,cast(rec.Call_Time_End AS varchar(19)) AS Call_Time_End,rec.Call_Time_Len,rec.ZW,rec.JQ_Name,rec.FR_No,rec.FR_Name,rec.QS_Info1,rec.QS_Info2,rec.QS_Info3,rec.QS_Info4,rec.QS_Info5,rec.QS_Info6,rec.QS_Info7,rec.QS_Info8,rec.QS_Info9 from JL_HJ_REC rec where rec.FR_Out_Time is null");
				}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
					str=new StringBuffer("select rec.WebID,cast(rec.Call_Time_Start AS varchar(19)) AS Call_Time_Start,cast(rec.Call_Time_End AS varchar(19)) AS Call_Time_End,rec.Call_Time_Len,rec.ZW,rec.JQ_Name,rec.FR_No,rec.FR_Name,rec.QS_Info1,rec.QS_Info2,rec.QS_Info3,rec.QS_Info4,rec.QS_Info5,rec.QS_Info6,rec.QS_Info7,rec.QS_Info8,rec.QS_Info9 from JL_HJ_REC rec where rec.FR_Out_Time is null");	
				}else{
					str=new StringBuffer("select rec.WebID,cast(rec.Call_Time_Start AS varchar(19)) AS Call_Time_Start,cast(rec.Call_Time_End AS varchar(19)) AS Call_Time_End,rec.Call_Time_Len,rec.ZW,rec.JQ_Name,rec.FR_No,rec.FR_Name,rec.QS_Info1,rec.QS_Info2,rec.QS_Info3,rec.QS_Info4,rec.QS_Info5,rec.QS_Info6,rec.QS_Info7,rec.QS_Info8,rec.QS_Info9 from JL_HJ_REC rec,SYS_USER_JQ jq where rec.FR_Out_Time is null and rec.JQ_No=jq.JQ_No and jq.Group_No='"+user.getGroupNo()+"'");	
				}
				if(cdf.getBeginTime()!=null){
					str.append(" and rec.Call_Time_Start >='"+cdf.getBeginTime()+"'");
					todayBegin=cdf.getBeginTime();
				}
				if(cdf.getEndTime()!=null){
					str.append(" and rec.Call_Time_Start <='"+cdf.getEndTime()+"'");
					todayEnd=cdf.getEndTime();
				}
				if(cdf.getFrNo()!=null && !cdf.getFrNo().equals("")){
					str.append(" and rec.FR_No ='"+cdf.getFrNo()+"'");
				}
				if(cdf.getFrName()!=null && !cdf.getFrName().equals("")){
					str.append(" and (dbo.f_get_fryp(rec.FR_Name,'"+cdf.getFrName().trim()+"') =1 or rec.FR_Name like '%"+cdf.getFrName()+"%')");
				}
				if(cdf.getJq()!=null && !cdf.getJq().equals("null")){
					str.append(" and rec.JQ_No ='"+cdf.getJq()+"'");
				}
				str.append(" order by rec.WebID desc");
				Object[] obj={};
				Map map=cds.searchToPageBySql(str.toString(),page.getPageNo(),20,obj);
				page.setPageSize(20);
				page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
				List list=(List)map.get(Constant.RESULTLIST);
				for(int i=0;i<list.size();i++){
					Object[] obj1=(Object[])list.get(i);
					RecordSearch recordSearch = new RecordSearch();
					recordSearch.setWebId(Long.parseLong(obj1[0].toString()));
					if (obj1[1] != null && !obj1[1].toString().equals("")) {
						recordSearch.setCallTimeStart(obj1[1].toString());
					}
					if (obj1[2] != null && !obj1[2].toString().equals("")) {
						recordSearch.setCallTimeEnd(obj1[2].toString());
					}	
					if (obj1[3] != null && !obj1[3].toString().equals("")) {
						recordSearch.setCallTimeLen(obj1[3].toString());
					}
					if (obj1[4] != null && !obj1[4].toString().equals("")) {
						recordSearch.setZw(obj1[4].toString());
					}
					if (obj1[5] != null && !obj1[5].toString() .equals("")) {
						recordSearch.setJqName(obj1[5].toString());
					}
					if (obj1[6] != null && !obj1[6].toString().equals("")) {
						recordSearch.setFrNo(obj1[6].toString());
					}
					if (obj1[7] != null && !obj1[7].toString().equals("")) {
						recordSearch.setFrName(obj1[7].toString());
					}
					String info="";
					if (obj1[8] != null && !obj1[8].toString().equals("")) {
						info+=obj1[8].toString();
					}
					if (obj1[9] != null && !obj1[9].toString().equals("")) {
						info+=obj1[9].toString();
					}
					if (obj1[10] != null && !obj1[10].toString().equals("")) {
						info+=obj1[10].toString();
					}
					if (obj1[11] != null && !obj1[11].toString().equals("")) {
						info+=obj1[11].toString();
					}
					if (obj1[12] != null && !obj1[12].toString().equals("")) {
						info+=obj1[12].toString();
					}
					if (obj1[13] != null && !obj1[13].toString().equals("")) {
						info+=obj1[13].toString();
					}
					if (obj1[14] != null && !obj1[14].toString() .equals("")) {
						info+=obj1[14].toString();
					}
					if (obj1[15] != null && !obj1[15].toString() .equals("")) {
						info+=obj1[15].toString();
					}
					if (obj1[16] != null && !obj1[16].toString().equals("")) {
						info+=obj1[16].toString();
					}
					recordSearch.setQsInfo(info);
			    	page.getList().add(recordSearch);
			    }
			}else{
				StringBuffer str = new StringBuffer(now.substring(0, 10));
				str.append(" 00:00:00");
				todayBegin = str.toString();
				StringBuffer str1 = new StringBuffer(now.substring(0, 10));
				str1.append(" 23:59:59");
				todayEnd = str1.toString();
			}
			if (user.getIsSuper() == 1) {
				String hql1="from JlJq";
				List<JlJq> list1=cds.searchAll(hql1);
				request.setAttribute("jlJqList", list1);
			} else if (user.getGroupNo().trim().equals("Admin")) {
				String hql1="from JlJq";
				List<JlJq> list1=cds.searchAll(hql1);
				request.setAttribute("jlJqList", list1);
			} else {
				String sql6 = "select distinct JQ_No from SYS_USER_JQ where Group_No='"+ user.getGroupNo() + "'";
				List list6 = cds.searchAllBySql(sql6);
				if (list6.size() > 0) {
					StringBuffer hql = new StringBuffer("from JlJq ");
					Iterator it = list6.iterator();
					int i = 0;
					while (it.hasNext()) {
						if (i == 0) {
							hql.append(" where jqNo='" + it.next() + "' ");
							i++;
						} else {
							hql.append("or jqNo='" + it.next() + "' ");
							i++;
						}
					}
					List<JlJq> list = cds.searchAll(hql.toString());
					request.setAttribute("jlJqList", list);
				}
			}
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("cancelDjMain1");
	}
	public ActionForward selectRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user = (SysUser) request.getSession().getAttribute("users");
			if (user == null) {
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
			JlHjRec jlHjRec=(JlHjRec)cds.findById(JlHjRec.class, Long.parseLong(webId));
			if(jlHjRec!=null && jlHjRec.getFrOutTime()==null){
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(1);
				response.getWriter().println(json.toString());
			}
	
			return null;
	}
	public ActionForward plqdlk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String strs=request.getParameter("str");
			String[] str=strs.split(";");
			List<JlHjRec> list=new ArrayList<JlHjRec>();
			for(int i=0;i<str.length;i++){
				String hjid=str[i];
				JlHjRec jlHjRec=(JlHjRec)cds.findByIdLong(JlHjRec.class, Long.parseLong(hjid));
				if(jlHjRec.getFrOutTime()==null){
					list.add(jlHjRec);
				}
			}
			String returnName="plQdlk";
			if(list.size()==0){
				returnName="Allyjfp";
			}else{
				request.setAttribute("jlHjRec", list);
			}
			
			return mapping.findForward(returnName);
	}
	public ActionForward getYjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String yjCardNo=request.getParameter("yjCardNo");
			if(!yjCardNo.trim().equals("")){
				String  hql="from JlYj where yjCard='"+yjCardNo+"'";
				List<JlYj> list=cds.searchAll(hql);
				response.setContentType("text/json; charset=utf-8"); 
				JSONArray json=JSONArray.fromObject(list);
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
			}
			
			
			return null;
	}
	public ActionForward plfpZw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjids=request.getParameter("hjid");
			String[] str=hjids.split(";");
			String yjNum = java.net.URLDecoder.decode((String)request.getParameter("yjNum"),"UTF-8");
			String hql="from JlYj where yjNum='"+yjNum.trim()+"'";
			List<JlYj> list=cds.searchAll(hql);
			List<MessageVO> mv=new ArrayList<MessageVO>();
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			if(list.size()>0){
				JlYj jlYj=list.get(0);
				for(int i=0;i<str.length;i++){
					JlHjRec jlHjRec=(JlHjRec)cds.findByIdLong(JlHjRec.class, Long.parseLong(str[i]));
					jlHjRec.setFrOutTime(new Timestamp(System.currentTimeMillis()));
					jlHjRec.setFrOutUser(yjNum);
					cds.update(jlHjRec);
					MessageVO messageVO=new MessageVO();
					messageVO.setFrName(jlHjRec.getFrName());
					messageVO.setXx(now);
					mv.add(messageVO);
				}	
			}else{
				MessageVO messageVO=new MessageVO();
				messageVO.setFrName("警察信息不存在：");
				messageVO.setXx("返回签到失败");
				mv.add(messageVO);
			}
			request.setAttribute("mv", mv);
			return mapping.findForward("fpMessage");
	}
	//番禺新增
	public ActionForward search4(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user = (SysUser) request.getSession().getAttribute("users");
			if (user == null) {
				return mapping.findForward("sessionFailure");
			}
			CancelDjForm cdf = (CancelDjForm) form;
			Page page = new Page();
			page.setPageNo(1);
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			if(cdf.getBeginTime()!=null || cdf.getEndTime()!=null || cdf.getState()!=null || cdf.getHjType()!=null || cdf.getFrNo()!=null || cdf.getFrName()!=null || cdf.getJq()!=null){
				StringBuffer str=null;
				if(user.getIsSuper()==1){
					str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj where 1=1");
				}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
					if(user.getIsBj()==1){
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq where dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0");	
					}else{
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq where dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0 and dj.HJID not in (select HJID from JL_HJ_SP sp where sp.HJID=dj.HJID)");	
					}
					
				}else{
					if(user.getIsBj()==1){
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where (dj.JQ_Name=jq.JQ_Name) and (jq.JQ_No=suj.JQ_No) and (suj.Group_No='"+user.getGroupNo()+"')");
					}else{
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where (dj.JQ_Name=jq.JQ_Name) and (jq.JQ_No=suj.JQ_No) and (suj.Group_No='"+user.getGroupNo()+"') and dj.HJID not in (select HJID from JL_HJ_SP sp where sp.HJID=dj.HJID)");
					}
					
				}
				if(cdf.getBeginTime()!=null){
					str.append(" and dj.DJ_Time >='"+cdf.getBeginTime()+"'");
					todayBegin=cdf.getBeginTime();
				}
				if(cdf.getEndTime()!=null){
					str.append(" and dj.DJ_Time <='"+cdf.getEndTime()+"'");
					todayEnd=cdf.getEndTime();
				}
				if(cdf.getState()!=null && !cdf.getState().equals("null")){
					str.append(" and dj.State ="+cdf.getState());
				}
				if(cdf.getHjType()!=null && !cdf.getHjType().equals("null")){
					str.append(" and dj.HJ_Type ="+cdf.getHjType());
				}
				if(cdf.getFrNo()!=null && !cdf.getFrNo().equals("")){
					str.append(" and dj.FR_No ='"+cdf.getFrNo()+"'");
				}
				if(cdf.getFrName()!=null && !cdf.getFrName().equals("")){
					str.append(" and (dbo.f_get_fryp(dj.FR_Name,'"+cdf.getFrName().trim()+"') =1 or dj.FR_Name like '%"+cdf.getFrName()+"%')");
				}
				if(cdf.getJq()!=null && !cdf.getJq().equals("null")){
					str.append(" and dj.JQ_No ='"+cdf.getJq()+"'");
				}
				str.append(" order by dj.JY,dj.JQ_Name,dj.FP_Flag,dj.HJID desc");
				Object[] obj={};
				Map map=cds.searchToPageBySql(str.toString(),page.getPageNo(),20,obj);
				page.setPageSize(20);
				page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
				List list=(List)map.get(Constant.RESULTLIST);
				for(int i=0;i<list.size();i++){
					Object[] obj1=(Object[])list.get(i);
			    	HjDjVO hjDjVO=new HjDjVO();
			    	hjDjVO.setHjid(obj1[0].toString());
			    	hjDjVO.setJy(obj1[1].toString());
			    	if(obj1[2]!=null && !obj1[2].toString().equals("")){
			    		hjDjVO.setJqName(obj1[2].toString());
			    	}
			    	hjDjVO.setFrNo(obj1[3].toString());
			    	if(obj1[4]!=null && !obj1[4].toString().equals("")){
			    		hjDjVO.setFrName(obj1[4].toString());
			    	}
			    	String qsName="";
			    	if(obj1[5]!=null && !obj1[5].toString().equals("")){
			    		qsName+=obj1[5].toString();
			    	}
			    	if(obj1[6]!=null && !obj1[6].toString().equals("")){
			    		qsName+=obj1[6].toString();
			    	}
			    	if(obj1[7]!=null && !obj1[7].toString().equals("")){
			    		qsName+=obj1[7].toString();
			    	}
			    	if(obj1[8]!=null && !obj1[8].toString().equals("")){
			    		qsName+=obj1[8].toString();
			    	}
			    	if(obj1[9]!=null && !obj1[9].toString().equals("")){
			    		qsName+=obj1[9].toString();
			    	}
			    	if(obj1[10]!=null && !obj1[10].toString().equals("")){
			    		qsName+=obj1[10].toString();
			    	}
			    	if(obj1[11]!=null && !obj1[11].toString().equals("")){
			    		qsName+=obj1[11].toString();
			    	}
			    	if(obj1[12]!=null && !obj1[12].toString().equals("")){
			    		qsName+=obj1[12].toString();
			    	}
			    	if(obj1[13]!=null && !obj1[13].toString().equals("")){
			    		qsName+=obj1[13].toString();
			    	}
			    	hjDjVO.setQsInfo1(qsName);
			    	hjDjVO.setHjTime(Integer.parseInt(obj1[14].toString())/60+"");
			    	if(obj1[15]!=null && !obj1[15].toString().equals("")){
			    		hjDjVO.setHjInfo(obj1[15].toString());	
			    	}
			    	hjDjVO.setHjType(obj1[16].toString());
			    	if(obj1[17]!=null && !obj1[17].toString().equals("")){
			    		hjDjVO.setMonitorFlag(obj1[17].toString());
			    	}
			    	hjDjVO.setFpFlag(obj1[18].toString());
			    	if(obj1[19]!=null && !obj1[19].toString().equals("")){
			    		hjDjVO.setZw(obj1[19].toString());
			    	}
			    	if(obj1[20]!=null && !obj1[20].toString().equals("")){
			    		hjDjVO.setDjUser(obj1[20].toString());
			    	}
			    	if(obj1[21]!=null && !obj1[21].toString().equals("")){
			    		hjDjVO.setDjTime(obj1[21].toString().substring(0, 19));
			    	}
			    	if(obj1[22]!=null){
			    		hjDjVO.setCancelInfo(obj1[22].toString());
			    	}else{
			    		hjDjVO.setCancelInfo("未取消");
			    	}
			    	if(obj1[23]!=null && !obj1[23].toString().equals("")){
			    		hjDjVO.setHjIndex(obj1[23].toString());
			    	}
			    	if(obj1[24]!=null && !obj1[24].toString().equals("")){
			    		hjDjVO.setInfoWp(obj1[24].toString());
			    	}else{
			    		hjDjVO.setInfoWp("无");
			    	}
			    	page.getList().add(hjDjVO);
			    }
			}else{
				StringBuffer str = new StringBuffer(now.substring(0, 10));
				str.append(" 00:00:00");
				todayBegin = str.toString();
				StringBuffer str1 = new StringBuffer(now.substring(0, 10));
				str1.append(" 23:59:59");
				todayEnd = str1.toString();
			}
			String hql1="from JlJq";
			List<JlJq> list1=cds.searchAll(hql1);
			request.setAttribute("jlJqList", list1);
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("cancelDjMain4");
	}
	public ActionForward search5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user = (SysUser) request.getSession().getAttribute("users");
			if (user == null) {
				return mapping.findForward("sessionFailure");
			}
			CancelDjForm cdf = (CancelDjForm) form;
			Page page = new Page();
			page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			if(cdf.getBeginTime()!=null || cdf.getEndTime()!=null || cdf.getState()!=null || cdf.getHjType()!=null || cdf.getFrNo()!=null || cdf.getFrName()!=null || cdf.getJq()!=null){
				StringBuffer str=null;
				if(user.getIsSuper()==1){
					str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj where 1=1");
				}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
					if(user.getIsBj()==1){
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq where dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0");	
					}else{
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq where dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0 and dj.HJID not in (select HJID from JL_HJ_SP sp where sp.HJID=dj.HJID)");	
					}
				}else{
					if(user.getIsBj()==1){
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"'");
					}else{
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' and dj.HJID not in (select HJID from JL_HJ_SP sp where sp.HJID=dj.HJID)");
					}
				}
				if(cdf.getBeginTime()!=null){
					str.append(" and dj.DJ_Time >='"+cdf.getBeginTime()+"'");
					todayBegin=cdf.getBeginTime();
				}
				if(cdf.getEndTime()!=null){
					str.append(" and dj.DJ_Time <='"+cdf.getEndTime()+"'");
					todayEnd=cdf.getEndTime();
				}
				if(cdf.getState()!=null && !cdf.getState().equals("null")){
					str.append(" and dj.State ="+cdf.getState());
				}
				if(cdf.getHjType()!=null && !cdf.getHjType().equals("null")){
					str.append(" and dj.HJ_Type ="+cdf.getHjType());
				}
				if(cdf.getFrNo()!=null && !cdf.getFrNo().equals("")){
					str.append(" and dj.FR_No ='"+cdf.getFrNo()+"'");
				}
				if(cdf.getFrName()!=null && !cdf.getFrName().equals("")){
					str.append(" and (dbo.f_get_fryp(dj.FR_Name,'"+cdf.getFrName().trim()+"') =1 or dj.FR_Name like '%"+cdf.getFrName()+"%')");
				}
				if(cdf.getJq()!=null && !cdf.getJq().equals("null")){
					str.append(" and dj.JQ_No ='"+cdf.getJq()+"'");
				}
				str.append(" order by dj.JY,dj.JQ_Name,dj.FP_Flag,dj.HJID desc");
				Object[] obj={};
				Map map=cds.searchToPageBySql(str.toString(),page.getPageNo(),20,obj);
				page.setPageSize(20);
				page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
				List list=(List)map.get(Constant.RESULTLIST);
				for(int i=0;i<list.size();i++){
					Object[] obj1=(Object[])list.get(i);
			    	HjDjVO hjDjVO=new HjDjVO();
			    	hjDjVO.setHjid(obj1[0].toString());
			    	hjDjVO.setJy(obj1[1].toString());
			    	if(obj1[2]!=null && !obj1[2].toString().equals("")){
			    		hjDjVO.setJqName(obj1[2].toString());
			    	}
			    	hjDjVO.setFrNo(obj1[3].toString());
			    	if(obj1[4]!=null && !obj1[4].toString().equals("")){
			    		hjDjVO.setFrName(obj1[4].toString());
			    	}
			    	String qsName="";
			    	if(obj1[5]!=null && !obj1[5].toString().equals("")){
			    		qsName+=obj1[5].toString();
			    	}
			    	if(obj1[6]!=null && !obj1[6].toString().equals("")){
			    		qsName+=obj1[6].toString();
			    	}
			    	if(obj1[7]!=null && !obj1[7].toString().equals("")){
			    		qsName+=obj1[7].toString();
			    	}
			    	if(obj1[8]!=null && !obj1[8].toString().equals("")){
			    		qsName+=obj1[8].toString();
			    	}
			    	if(obj1[9]!=null && !obj1[9].toString().equals("")){
			    		qsName+=obj1[9].toString();
			    	}
			    	if(obj1[10]!=null && !obj1[10].toString().equals("")){
			    		qsName+=obj1[10].toString();
			    	}
			    	if(obj1[11]!=null && !obj1[11].toString().equals("")){
			    		qsName+=obj1[11].toString();
			    	}
			    	if(obj1[12]!=null && !obj1[12].toString().equals("")){
			    		qsName+=obj1[12].toString();
			    	}
			    	if(obj1[13]!=null && !obj1[13].toString().equals("")){
			    		qsName+=obj1[13].toString();
			    	}
			    	hjDjVO.setQsInfo1(qsName);
			    	hjDjVO.setHjTime(Integer.parseInt(obj1[14].toString())/60+"");
			    	if(obj1[15]!=null && !obj1[15].toString().equals("")){
			    		hjDjVO.setHjInfo(obj1[15].toString());	
			    	}
			    	hjDjVO.setHjType(obj1[16].toString());
			    	if(obj1[17]!=null && !obj1[17].toString().equals("")){
			    		hjDjVO.setMonitorFlag(obj1[17].toString());
			    	}
			    	hjDjVO.setFpFlag(obj1[18].toString());
			    	if(obj1[19]!=null && !obj1[19].toString().equals("")){
			    		hjDjVO.setZw(obj1[19].toString());
			    	}
			    	if(obj1[20]!=null && !obj1[20].toString().equals("")){
			    		hjDjVO.setDjUser(obj1[20].toString());
			    	}
			    	if(obj1[21]!=null && !obj1[21].toString().equals("")){
			    		hjDjVO.setDjTime(obj1[21].toString().substring(0, 19));
			    	}
			    	if(obj1[22]!=null){
			    		hjDjVO.setCancelInfo(obj1[22].toString());
			    	}else{
			    		hjDjVO.setCancelInfo("未取消");
			    	}
			    	if(obj1[23]!=null && !obj1[23].toString().equals("")){
			    		hjDjVO.setHjIndex(obj1[23].toString());
			    	}
			    	if(obj1[24]!=null && !obj1[24].toString().equals("")){
			    		hjDjVO.setInfoWp(obj1[24].toString());
			    	}else{
			    		hjDjVO.setInfoWp("无");
			    	}
			    	page.getList().add(hjDjVO);
			    }
			}else{
				StringBuffer str = new StringBuffer(now.substring(0, 10));
				str.append(" 00:00:00");
				todayBegin = str.toString();
				StringBuffer str1 = new StringBuffer(now.substring(0, 10));
				str1.append(" 23:59:59");
				todayEnd = str1.toString();
			}
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("cancelDjMain4");
	}
	
	public ActionForward dcCancelDjSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user = (SysUser) request.getSession().getAttribute("users");
			if (user == null) {
				return mapping.findForward("sessionFailure");
			}
			Date date=new Date(System.currentTimeMillis());
		    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss"); 
		    String now=df1.format(date);
		    String filenameTemp = "D:\\\\"+now.substring(0,4)+now.substring(5,7)+"登记查询记录.xls"; 
		    File filename = new File(filenameTemp); 
		    if (!filename.exists()){
		    	filename.createNewFile(); 
		    } 
			WritableWorkbook wwb6 = null;
			wwb6 = Workbook.createWorkbook(filename);
			WritableSheet ws6 = wwb6.createSheet("登记查询记录", 0);
			ws6.addCell(new Label(0, 0, "监区"));
			ws6.addCell(new Label(1, 0, "罪犯编号"));
			ws6.addCell(new Label(2, 0, "会见编号"));
			ws6.addCell(new Label(3, 0, "罪犯姓名"));
			ws6.addCell(new Label(4, 0, "亲属"));
			ws6.addCell(new Label(5, 0, "会见时长"));
			ws6.addCell(new Label(6, 0, "会见类型"));
			ws6.addCell(new Label(7, 0, "会见说明"));
			ws6.addCell(new Label(8, 0, "登记时间"));
			ws6.addCell(new Label(9, 0, "登记人"));
			ws6.addCell(new Label(10, 0, "取消原因"));
			
			CancelDjForm cdf = (CancelDjForm) form;
			if(cdf.getBeginTime()!=null || cdf.getEndTime()!=null || cdf.getState()!=null || cdf.getHjType()!=null || cdf.getFrNo()!=null || cdf.getFrName()!=null || cdf.getJq()!=null){

				StringBuffer str=null;
				if(user.getIsSuper()==1){
					str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj where 1=1");
				}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
					if(user.getIsBj()==1){
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq where dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0");
					}else{
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq where dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0 and dj.HJID not in (select HJID from JL_HJ_SP sp where sp.HJID=dj.HJID)");
					}
					
				}else{
					if(user.getIsBj()==1){
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"'");
					}else{
						str=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.Cancel_Info,dj.HJ_Index,dbo.f_get_fr_wp(dj.hjid) as wp from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' and dj.HJID not in (select HJID from JL_HJ_SP sp where sp.HJID=dj.HJID)");
					}
					
				}
				if(cdf.getBeginTime()!=null){
					str.append(" and dj.DJ_Time >='"+cdf.getBeginTime()+"'");
				}
				if(cdf.getEndTime()!=null){
					str.append(" and dj.DJ_Time <='"+cdf.getEndTime()+"'");
				}
				if(cdf.getState()!=null && !cdf.getState().equals("null")){
					str.append(" and dj.State ="+cdf.getState());
				}
				if(cdf.getHjType()!=null && !cdf.getHjType().equals("null")){
					str.append(" and dj.HJ_Type ="+cdf.getHjType());
				}
				if(cdf.getFrNo()!=null && !cdf.getFrNo().equals("")){
					str.append(" and dj.FR_No ='"+cdf.getFrNo()+"'");
				}
				if(cdf.getFrName()!=null && !cdf.getFrName().equals("")){
					str.append(" and (dbo.f_get_fryp(dj.FR_Name,'"+cdf.getFrName().trim()+"') =1 or dj.FR_Name like '%"+cdf.getFrName()+"%')");
				}
				if(cdf.getJq()!=null && !cdf.getJq().equals("null")){
					str.append(" and dj.JQ_No ='"+cdf.getJq()+"'");
				}
				str.append(" order by dj.JY,dj.JQ_Name,dj.FP_Flag,dj.HJID desc");
				Object[] obj={};
				List list=cds.searchAllBySql(str.toString());
				for(int i=0;i<list.size();i++){
					Object[] obj1=(Object[])list.get(i);
			    	HjDjVO hjDjVO=new HjDjVO();
			    	hjDjVO.setHjid(obj1[0].toString());
			    	hjDjVO.setJy(obj1[1].toString());
			    	if(obj1[2]!=null && !obj1[2].toString().equals("")){
			    		ws6.addCell(new Label(0, i+1, obj1[2].toString()));
			    	}
			    	ws6.addCell(new Label(1, i+1, obj1[3].toString()));
			    	if(obj1[23]!=null && !obj1[23].toString().equals("")){
			    		ws6.addCell(new Label(2, i+1, obj1[23].toString()));
			    	}
			    	if(obj1[4]!=null && !obj1[4].toString().equals("")){
			    		ws6.addCell(new Label(3, i+1, obj1[4].toString()));
			    	}
			    	String qsName="";
			    	if(obj1[5]!=null && !obj1[5].toString().equals("")){
			    		qsName+=obj1[5].toString();
			    	}
			    	if(obj1[6]!=null && !obj1[6].toString().equals("")){
			    		qsName+=obj1[6].toString();
			    	}
			    	if(obj1[7]!=null && !obj1[7].toString().equals("")){
			    		qsName+=obj1[7].toString();
			    	}
			    	if(obj1[8]!=null && !obj1[8].toString().equals("")){
			    		qsName+=obj1[8].toString();
			    	}
			    	if(obj1[9]!=null && !obj1[9].toString().equals("")){
			    		qsName+=obj1[9].toString();
			    	}
			    	if(obj1[10]!=null && !obj1[10].toString().equals("")){
			    		qsName+=obj1[10].toString();
			    	}
			    	if(obj1[11]!=null && !obj1[11].toString().equals("")){
			    		qsName+=obj1[11].toString();
			    	}
			    	if(obj1[12]!=null && !obj1[12].toString().equals("")){
			    		qsName+=obj1[12].toString();
			    	}
			    	if(obj1[13]!=null && !obj1[13].toString().equals("")){
			    		qsName+=obj1[13].toString();
			    	}
			    	ws6.addCell(new Label(4, i+1, qsName));
			    	ws6.addCell(new Label(5, i+1, Integer.parseInt(obj1[14].toString())/60+""));
			    	if(obj1[16].toString().equals("1")){
			    		ws6.addCell(new Label(6, i+1, "考察"));
			    	}else{
			    		ws6.addCell(new Label(6, i+1, "其它"));
			    	}
			    	if(obj1[15]!=null && !obj1[15].toString().equals("")){
			    		ws6.addCell(new Label(7, i+1, obj1[15].toString()));
			    	}
			    	if(obj1[21]!=null && !obj1[21].toString().equals("")){
			    		ws6.addCell(new Label(8, i+1, obj1[21].toString().substring(0, 19)));
			    	}
			    	if(obj1[20]!=null && !obj1[20].toString().equals("")){
			    		ws6.addCell(new Label(9, i+1, obj1[20].toString()));
			    	}
			    	if(obj1[22]!=null){
			    		ws6.addCell(new Label(10, i+1, obj1[22].toString()));
			    	}else{
			    		ws6.addCell(new Label(10, i+1, "未取消"));
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
