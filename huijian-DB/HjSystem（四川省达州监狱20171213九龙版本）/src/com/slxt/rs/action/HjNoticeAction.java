package com.slxt.rs.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bsh.ParseException;

import com.slxt.rs.model.JlHjDj;
import com.slxt.rs.model.SysParam;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.HjNoticeService;
import com.slxt.rs.vo.HjDjVO;
import com.slxt.rs.vo.HjTZ;

public class HjNoticeAction extends DispatchAction{
	private HjNoticeService hns;
	public void setHns(HjNoticeService hns) {
		this.hns = hns;
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hql="";
			String hql11="";
			String hql10="from SysParam where paramName='HJ_Client1'";
			List<SysParam> list10=hns.searchAll(hql10);
			if(list10.size()>0){
				SysParam sysParam=list10.get(0);
				if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
					hql11=" and (dj.DJ_Type=0 or dj.DJ_Type=2)";
				}
			}
			if(user.getIsSuper()==1){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time,dj.page_tz_state,dj.FR_DAH,dj.page_tz_UserNo,dj.page_tz_UserName,dj.page_tz_Time from JL_HJ_DJ dj where dj.State=0 ";
				hql+=hql11;
				hql+=" order by  dj.page_tz_state asc";
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time,dj.page_tz_state,dj.FR_DAH,dj.page_tz_UserNo,dj.page_tz_UserName,dj.page_tz_Time from JL_HJ_DJ dj,JL_JQ jq where dj.State=0  and dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0";
				hql+=hql11;
				hql+=" order by  dj.page_tz_state asc";
			}else{
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time,dj.page_tz_state,dj.FR_DAH,dj.page_tz_UserNo,dj.page_tz_UserName,dj.page_tz_Time from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.State=0 ";
				hql+=hql11;
				hql+=" and dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' order by dj.page_tz_state asc";
			}
			List list=hns.searchAllBySql(hql);
			List<HjDjVO> list1=new ArrayList<HjDjVO>();
			int s1=0;
			int s2=0;
			int s3=0;
			int s4=0;
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
		    	if(!obj1[18].toString().equals("0")){
		    		s1++;
		    	}else{
		    		s2++;
		    	}
		    	if(obj1[19]!=null && !obj1[19].toString().equals("")){
		    		hjDjVO.setZw(obj1[19].toString());
		    	}
		    	if(obj1[20]!=null && !obj1[20].toString().equals("")){
		    		hjDjVO.setDjUser(obj1[20].toString());
		    	}
		    	if(obj1[21]!=null && !obj1[21].toString().equals("")){
		    		hjDjVO.setDjTime(obj1[21].toString().substring(0, 19));
		    		
		    		String hql100="from SysParam where paramName='HJ_Client3'";
					List<SysParam> list100=hns.searchAll(hql100);
					
		    		//登记时间加上30分钟后的时间
//		    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		    		Date date=null;
//		    		date = sdf.parse(obj1[21].toString());
//		    		Calendar ca=Calendar.getInstance();
//		    		ca.setTime(date);
//		    		ca.add(Calendar.MINUTE,Integer.parseInt(list100.get(0).getParamData4()));
		    		
					//系统当前时间减去参数表设置的超时时间后的时间
		    		long curren = System.currentTimeMillis();
		            curren -= Integer.parseInt(list100.get(0).getParamData4()) * 60 * 1000;
		            Date da = new Date(curren);
		            SimpleDateFormat dateFormat = new SimpleDateFormat(
		                    "yyyy-MM-dd HH:mm:ss");
//		            System.out.println(dateFormat.format(da));
		            Timestamp aa = Timestamp.valueOf(dateFormat.format(da));
		            Timestamp bb = Timestamp.valueOf(obj1[21].toString());

		            if(aa.after((bb)) && obj1[26].toString().equals("0")){
		            	hjDjVO.setIsOverTime("1");
		            }else{
		            	hjDjVO.setIsOverTime("0");
		            }
		    	}
		    	if(obj1[22]!=null && !obj1[22].toString().equals("")){
		    		hjDjVO.setHjIndex(obj1[22].toString());
		    	}
		    	if(obj1[23]!=null){
		    		hjDjVO.setFpTzfrFlag(obj1[23].toString());
		    	}
		    	if(obj1[24]!=null){
		    		hjDjVO.setInfoWp(obj1[24].toString());
		    	}else{
		    		hjDjVO.setInfoWp("0");
		    	}
		    	if(obj1[25]!=null && !obj1[25].toString().equals("")){
		    		hjDjVO.setQsInTime(obj1[25].toString());
		    	}else{
		    		hjDjVO.setQsInTime("");
		    	}
		    	if(obj1[26]!=null && !obj1[26].toString().equals("") && obj1[26].toString().equals("1")){
		    		hjDjVO.setPageTzState(obj1[26].toString());
		    		s3++;
		    	}else{
		    		hjDjVO.setPageTzState("0");
		    		s4++;
		    	}
		    	if(obj1[27]!=null && !obj1[27].toString().equals("")){
		    		hjDjVO.setFrDah(obj1[27].toString());
		    	}
		    	if(obj1[28]!=null && !obj1[28].toString().equals("")){
		    		hjDjVO.setPageTzUserNo(obj1[28].toString());
		    	}
		    	if(obj1[29]!=null && !obj1[29].toString().equals("")){
		    		hjDjVO.setPageTzUserName(obj1[29].toString());
		    	}
		    	if(obj1[30]!=null && !obj1[30].toString().equals("")){
		    		hjDjVO.setPageTzTime(obj1[30].toString());
		    	}
		    	
		    	list1.add(hjDjVO);
		    }
			request.setAttribute("list1",list1);
			
			HjDjVO hjs = new HjDjVO();
			hjs.setYifenpei(s1+"");
			hjs.setWeifenpei(s2+"");
			hjs.setYifaqi(s3+"");
			hjs.setWeifaqi(s4+"");
			request.setAttribute("hjs", hjs);
			String hql4="from SysParam where paramName='HJ_Client'";
			List<SysParam> list4=hns.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("hJClient1", sysParam1);
			}
			
			
			return mapping.findForward("hjNoticeMain");
	}
	public ActionForward jquerSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			
			String hql="";
			String hql11="";
			String hql2="";
			String hql10="from SysParam where paramName='HJ_Client1'";
			List<SysParam> list10=hns.searchAll(hql10);
			if(list10.size()>0){
				SysParam sysParam=list10.get(0);
				if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
					hql11=" and (dj.DJ_Type=0 or dj.DJ_Type=2)";
				}
			}
			if(user.getIsSuper()==1){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time,dj.page_tz_state,dj.FR_DAH,dj.page_tz_UserNo,dj.page_tz_UserName,dj.page_tz_Time from JL_HJ_DJ dj where dj.State=0 ";
				hql+=hql11;
				hql+=" order by dj.page_tz_state asc";
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time,dj.page_tz_state,dj.FR_DAH,dj.page_tz_UserNo,dj.page_tz_UserName,dj.page_tz_Time from JL_HJ_DJ dj,JL_JQ jq where dj.State=0  and dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0";
				hql+=hql11;
				hql+=" order by dj.page_tz_state asc";
			}else{
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time,dj.page_tz_state,dj.FR_DAH,dj.page_tz_UserNo,dj.page_tz_UserName,dj.page_tz_Time from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.State=0 ";
				hql+=hql11;
				hql+=" and dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' order by dj.page_tz_state asc";
			}
			List list=hns.searchAllBySql(hql);
			//查询当前是否有未接收会见通知的记录
			if(user.getIsSuper()==1){
				hql2="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time,dj.page_tz_state,dj.FR_DAH from JL_HJ_DJ dj where dj.State=0 and dj.page_tz_state=0 ";
				hql2+=hql11;
				hql2+=" order by dj.page_tz_state asc";
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				hql2="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time,dj.page_tz_state,dj.FR_DAH from JL_HJ_DJ dj,JL_JQ jq where dj.State=0 and dj.page_tz_state=0 and dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0";
				hql2+=hql11;
				hql2+=" order by dj.page_tz_state asc";
			}else{
				hql2="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time,dj.page_tz_state,dj.FR_DAH from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.State=0 and dj.page_tz_state=0 ";
				hql2+=hql11;
				hql2+=" and dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' order by dj.page_tz_state asc";
			}
			List list1=hns.searchAllBySql(hql2);
			//List<HjDjVO> list1=new ArrayList<HjDjVO>();
			List<HjTZ>  hjlist = new ArrayList<HjTZ>();
			HjTZ hjtz = new HjTZ();
			if(list1.size()>0){
				hjtz.setPageTzState(1);
			}else{
				hjtz.setPageTzState(0);
			}
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
		    		
		    		String hql100="from SysParam where paramName='HJ_Client3'";
					List<SysParam> list100=hns.searchAll(hql100);
					
		    		//登记时间加上30分钟后的时间
//		    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		    		Date date=null;
//		    		date = sdf.parse(obj1[21].toString());
//		    		Calendar ca=Calendar.getInstance();
//		    		ca.setTime(date);
//		    		ca.add(Calendar.MINUTE,Integer.parseInt(list100.get(0).getParamData4()));
		    		
		    		//系统当前时间减去参数表设置的超时时间后的时间
		    		long curren = System.currentTimeMillis();
		            curren -= Integer.parseInt(list100.get(0).getParamData4()) * 60 * 1000;
		            Date da = new Date(curren);
		            SimpleDateFormat dateFormat = new SimpleDateFormat(
		                    "yyyy-MM-dd HH:mm:ss");
//		            System.out.println(dateFormat.format(da));
		            Timestamp aa = Timestamp.valueOf(dateFormat.format(da));
		            Timestamp bb = Timestamp.valueOf(obj1[21].toString());
		            if(aa.after((bb)) && obj1[26].toString().equals("0")){
		            	hjDjVO.setIsOverTime("1");
		            }else{
		            	hjDjVO.setIsOverTime("0");
		            }
		    	}
		    	if(obj1[22]!=null && !obj1[22].toString().equals("")){
		    		hjDjVO.setHjIndex(obj1[22].toString());
		    	}
		    	if(obj1[23]!=null){
		    		hjDjVO.setFpTzfrFlag(obj1[23].toString());
		    	}
		    	if(obj1[24]!=null){
		    		hjDjVO.setInfoWp(obj1[24].toString());
		    	}else{
		    		hjDjVO.setInfoWp("0");
		    	}
		    	if(obj1[25]!=null && !obj1[25].toString().equals("")){
		    		hjDjVO.setQsInTime(obj1[25].toString());
		    	}else{
		    		hjDjVO.setQsInTime("");
		    	}
		    	if(obj1[26]!=null && !obj1[26].toString().equals("")){
		    		hjDjVO.setPageTzState(obj1[26].toString());
		    	}else{
		    		hjDjVO.setPageTzState("0");
		    	}
		    	if(obj1[27]!=null && !obj1[27].toString().equals("")){
		    		hjDjVO.setFrDah(obj1[27].toString());
		    	}
		    	if(obj1[28]!=null && !obj1[28].toString().equals("")){
		    		hjDjVO.setPageTzUserNo(obj1[28].toString());
		    	}
		    	if(obj1[29]!=null && !obj1[29].toString().equals("")){
		    		hjDjVO.setPageTzUserName(obj1[29].toString());
		    	}
		    	if(obj1[30]!=null && !obj1[30].toString().equals("")){
		    		hjDjVO.setPageTzTime(obj1[30].toString());
		    	}
		    	hjtz.getList1().add(hjDjVO);

		    }
			
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String now = df1.format(date);
			String sql1="select jq.TZ_Handle, jq.TZ_SX, jq.TZ_SJ from JL_JQ jq, SYS_USER_JQ suj where jq.TZ_Handle='1' and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' and jq.TZ_SX>='"+now+"'";
			List list2=hns.searchAllBySql(sql1);
			if(list2.size()>0){
				Object[] obj=(Object[])list2.get(0);
				HjDjVO hjDjVO =new HjDjVO();
				if(obj[0]!=null && !obj[0].toString().equals("")){
					hjDjVO.setTzHandle("1");
				}
				if(obj[1]!=null && !obj[1].toString().equals("")){
					hjDjVO.setTzSX(obj[1].toString());
				}
				if(obj[2]!=null && !obj[2].toString().equals("")){
					hjDjVO.setTzSJ(obj[2].toString());
				}
				hjtz.getList4().add(hjDjVO);
				if(!user.getGroupNo().equals("")){
					String sql2="update jq set jq.TZ_Handle='2' from JL_JQ jq, SYS_USER_JQ suj where jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"'";
					hns.executeUpdate(sql2);
				}
			}else{
				HjDjVO hjDjVO =new HjDjVO();
				hjDjVO.setTzHandle("2");
				hjtz.getList4().add(hjDjVO);
			}
			hjlist.add(hjtz);
			response.setContentType("text/json; charset=utf-8"); 
			JSONArray json=JSONArray.fromObject(hjlist);
			response.getWriter().println(json.toString());
			return null;
	}
	public ActionForward sdNotice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			Timestamp now1=new Timestamp(System.currentTimeMillis());
			JlHjDj jlHjDj=(JlHjDj)hns.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
			jlHjDj.setPageTzState(1);
			jlHjDj.setPageTzUserNo(user.getUserNo());
			jlHjDj.setPageTzUserName(user.getUserName());
			jlHjDj.setPageTzTime(now1);
			hns.update(jlHjDj);
			JSONArray json=JSONArray.fromObject(0);
			response.getWriter().println(json.toString());
			return null;
	}
	public ActionForward changeWpState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			String infoWp=request.getParameter("infoWp");
			JlHjDj jlHjDj=(JlHjDj)hns.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
			jlHjDj.setInfoWp(Integer.parseInt(infoWp));
			hns.update(jlHjDj);
			JSONArray josn=JSONArray.fromObject(null);
			response.getWriter().println(josn.toString());
			return null;
	}
	public ActionForward jrNotice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			JlHjDj jlHjDj=(JlHjDj)hns.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
			Timestamp now=new Timestamp(System.currentTimeMillis());
			jlHjDj.setQsInTime(now);
			jlHjDj.setQsInUser(user.getUserNo());
			hns.update(jlHjDj);
			JSONArray json=JSONArray.fromObject(0);
			response.getWriter().println(json.toString());
			return null;
	}
}
