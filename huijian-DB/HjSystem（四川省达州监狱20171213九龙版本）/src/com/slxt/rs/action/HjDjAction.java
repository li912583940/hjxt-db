package com.slxt.rs.action;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.pinnacle.face.core.TFaceClient;

import com.pinnacle.face.core.base.IFaceClient;

import com.slxt.rs.form.HjdjForm;
import com.slxt.rs.model.HjdjAcdInfo;

import com.slxt.rs.model.ErrorInfoWebService;
import com.slxt.rs.model.HjdjAcdWindowsInfo;
import com.slxt.rs.model.HjdjFaceInfo;
import com.slxt.rs.model.JlFr;
import com.slxt.rs.model.JlHjDj;
import com.slxt.rs.model.JlHjDjQs;
import com.slxt.rs.model.JlHjSpQs;
import com.slxt.rs.model.JlJq;
import com.slxt.rs.model.JlQs;
import com.slxt.rs.model.JlYjABDoor;
import com.slxt.rs.model.SysUserGroup;

import com.slxt.rs.model.JlTradeCardInfo;
import com.slxt.rs.model.SysHjLine;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysParam;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.HjDjService;
import com.slxt.rs.util.Acd;
import com.slxt.rs.util.Constant;
import com.slxt.rs.util.Pool;
import com.slxt.rs.vo.CallResult;
import com.slxt.rs.vo.ErrorInfoWebServiceVO;
import com.slxt.rs.vo.Fr;
import com.slxt.rs.vo.FrQs;
import com.slxt.rs.vo.FrQsToABDoor;
import com.slxt.rs.vo.FrQsToABDoorResult;
import com.slxt.rs.vo.HJSP;
import com.slxt.rs.vo.HJSPBM;
import com.slxt.rs.vo.HJSPQS;
import com.slxt.rs.vo.HjDjVO;
import com.slxt.rs.vo.InterviewQs;
import com.slxt.rs.vo.InterviewQsList;
import com.slxt.rs.vo.InterviewUser;
import com.slxt.rs.vo.Page;
import com.slxt.rs.vo.PrintHjDjVO;
import com.slxt.rs.vo.QueryQs;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import org.dom4j.Element;

import com.daswebsrv.DasWebSrv;
import com.daswebsrv.DasWebSrvPortType;
import com.entlogic.jgxt.realtime.webservices.RtInterface;
import com.entlogic.jgxt.realtime.webservices.Service;

public class HjDjAction extends DispatchAction{
	private  HjDjService hds;
	public void setHds(HjDjService hds) {
		this.hds = hds;
	}
	

	//查询登记记录
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String paramData2=request.getParameter("paramData2");
		 	if(paramData2!=null){
		 		if(Integer.parseInt(paramData2)==0){
		 			StringBuffer str=new StringBuffer("update SysParam sp set sp.paramData2=1 where sp.paramName=?");
		 			Object[] obj={"HJ_Client3"};
		 			hds.updates(str.toString(), obj);
				}if(Integer.parseInt(paramData2)==1){
					StringBuffer str=new StringBuffer("update SysParam sp set sp.paramData2=0 where sp.paramName=?");
					Object[] obj={"HJ_Client3"};
					hds.updates(str.toString(), obj);
				}
		 	}
			Page page=new Page();
			page.setPageNo(1);
			String hql="";
			if(user.getIsSuper()==1){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.DJ_Type,dj.SFYZ_STATE,dj.FR_DAH,dj.state from JL_HJ_DJ dj where (dj.State=0 or dj.State=3)";
				hql+=" order by HJ_Index ";
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.DJ_Type,dj.SFYZ_STATE,dj.FR_DAH,dj.state from JL_HJ_DJ dj,JL_JQ jq where (dj.State=0 or dj.State=3)";
				hql+=" and dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0 order by dj.HJ_Index ";
			}else{
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.DJ_Type,dj.SFYZ_STATE,dj.FR_DAH,dj.state from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where (dj.State=0 or dj.State=3)";
				hql+=" and dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' order by dj.HJ_Index ";
			}
		    Object[] obj={};
		    Map map=hds.searchToPageBySql(hql,1,8,obj);
		    page.setPageSize(8);
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
		    	if(obj1[22]!=null && !obj1[22].toString().equals("")){
		    		hjDjVO.setHjIndex(obj1[22].toString());
		    	}
		    	if(obj1[26]!=null && !obj1[26].toString().equals("")){
		    		if(obj1[26].toString().equals("3")){
		    			hjDjVO.setState("待审批");
		    		}else{
		    			hjDjVO.setState("审批通过");
		    		}
		    	}
		    	if(obj1[24]!=null && !obj1[24].toString().equals("")){
		    		hjDjVO.setSfyzState(obj1[24].toString());
		    	}
		    	if(obj1[25]!=null && !obj1[25].toString().equals("")){
		    		hjDjVO.setFrDah(obj1[25].toString());
		    	}
		    	page.getList().add(hjDjVO);
		    }
		    String hql4="from SysParam where paramName='HJ_Client'";
			List<SysParam> list4=hds.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.getSession().setAttribute("hJClient", sysParam1);
			}
			String hql5="from SysParam where paramName='HJ_Client3'";
			List<SysParam> list5=hds.searchAll(hql5);
			if(list5.size()>0){
				SysParam sysParam1=(SysParam)list5.get(0);
				request.getSession().setAttribute("hJClient3", sysParam1);
			}
		    request.setAttribute("page", page);
			return mapping.findForward("hjDjMain");
	}
	//查询登记记录
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			Page page=new Page();
			page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			String hql="";
			if(user.getIsSuper()==1){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.DJ_Type,dj.SFYZ_STATE,dj.FR_DAH,dj.state from JL_HJ_DJ dj where (dj.State=0 or dj.State=3)";
				hql+=" order by dj.HJ_Index";
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.DJ_Type,dj.SFYZ_STATE,dj.FR_DAH,dj.state from JL_HJ_DJ dj,JL_JQ jq where (dj.State=0 or dj.State=3)";
				hql+=" and dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0 order by dj.HJ_Index";
			}else{
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.DJ_Type,dj.SFYZ_STATE,dj.FR_DAH,dj.state from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where (dj.State=0 or dj.State=3)";
				hql+=" and dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' order by dj.HJ_Index";
			}
		    Object[] obj={};
		    Map map=hds.searchToPageBySql(hql,page.getPageNo(),8,obj);
		    page.setPageSize(8);
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
		    	if(obj1[22]!=null && !obj1[22].toString().equals("")){
		    		hjDjVO.setHjIndex(obj1[22].toString());
		    	}
		    	if(obj1[26]!=null && !obj1[26].toString().equals("")){
		    		if(obj1[26].toString().equals("3")){
		    			hjDjVO.setState("待审批");
		    		}else{
		    			hjDjVO.setState("审批通过");
		    		}
		    	}
		    	if(obj1[24]!=null && !obj1[24].toString().equals("")){
		    		hjDjVO.setSfyzState(obj1[24].toString());
		    	}
		    	if(obj1[25]!=null && !obj1[25].toString().equals("")){
		    		hjDjVO.setFrDah(obj1[25].toString());
		    	}
		    	page.getList().add(hjDjVO);
		    }
		    String hql4="from SysParam where paramName='HJ_Client'";
			List<SysParam> list4=hds.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.getSession().setAttribute("hJClient", sysParam1);
			}
			String hql5="from SysParam where paramName='HJ_Client3'";
			List<SysParam> list5=hds.searchAll(hql5);
			if(list5.size()>0){
				SysParam sysParam1=(SysParam)list5.get(0);
				request.getSession().setAttribute("hJClient3", sysParam1);
			}
		    request.setAttribute("page", page);
			return mapping.findForward("hjDjMain");
	}
	//查询犯人
	public ActionForward checkFr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 	String frNo=request.getParameter("frNo");
		 	String hjid=request.getParameter("hjid");
		 	
		 	String sql2="update JL_HJ_DJ set state=2,Cancel_Info='会见登记处取消提交审批' where HJID="+hjid;
			hds.executeUpdate(sql2);
			
		 	List<Fr> jlFrList=new ArrayList<Fr>();
			List<Fr> unjlFrList=new ArrayList<Fr>();
			Calendar c = Calendar.getInstance();
		 	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		 	c  =  Calendar.getInstance(Locale.CHINESE);   
		 	String loginTime = simpleDateTimeFormat.format(c.getTime());
		 	HjdjForm ff=(HjdjForm)form;
			if(ff.getJq()!=null  || ff.getQssfz()!=null || ff.getQsName()!=null || ff.getFrName()!=null || frNo!=null){
				String qsSFZ=request.getParameter("qsSFZ");
			 	if(qsSFZ!=null){
			 		String[] qsSFZstr=qsSFZ.split(";");
			 		for(int i=0; i<qsSFZstr.length;i++){
			 			if(!qsSFZstr[i].equals("")){
			 				String qsSQL="delete from JL_QS where QS_SFZ='"+qsSFZstr[i]+"' and FR_No='"+frNo+"' and sp_state<>1";
			 				hds.executeUpdate(qsSQL);
			 			}
			 		}
	 		 	}
				StringBuffer str=new StringBuffer("");
//				if(user.getIsSuper()==1){
//					str.append("select distinct aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_Use,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home from JL_FR jl,JL_JB jb,JL_Jq jq where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq ) as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
//					//str.append("select distinct fr.webId,fr.FR_No,fr.FR_Name,fr.jy,fr.jq,IsNull((select JB_Name from JL_JB where JB_No=fr.JB_No),'未定义'),IsNull((select JQ_Name from JL_JQ where JQ_No=fr.JQ),'未定义') ,dbo.get_hj_UseCount(fr.FR_No) as use1,fr.HJ_JB,fr.Monitor_Flag,IsNull((select HJ_Count from JL_JB where JB_No=fr.JB_No),30) ,fr.HJ_Last_Time,fr.HJ_Use,fr.Info_RJSJ,dbo.f_get_jg(fr.Info_JG) as Info_JG,fr.Info_ZDZF,fr.Info_ZM,fr.Info_XQ,fr.Info_CSRQ,fr.info_home from  JL_FR  fr left join JL_QS qs  on fr.FR_No=qs.FR_No where 1=1");
//				}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
//					str.append("select distinct aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_Use,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home from JL_FR jl,JL_JB jb,JL_Jq jq where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq and jq.Is_Ts=0) as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
//				}else{
//					str.append("select distinct aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_Use,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home from JL_FR jl,JL_JB jb,JL_Jq jq,SYS_USER_JQ suj where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq and jq.jq_no=suj.JQ_No and suj.Group_No='");
//					str.append(user.getGroupNo());
//					str.append("') as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
//				}
				if(user.getIsSuper()==1){
					str.append("select aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,dbo.get_hj_UseCount(aa.FR_No) as use1,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,(select top 1 DJ_Time from jl_hj_dj where FR_No=aa.FR_No and state=1 order by hjid desc) as HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home,(select top 1 JQ_Name from jl_hj_dj where FR_No=aa.FR_No and state=1 order by hjid desc) as Former_JQ_Name,aa.Fr_DAH,aa.State_ZDZF,aa.ZDZF_Type,aa.FR_GJ,(select top 1 (jldj.QS_Info1+ISNULL(jldj.QS_Info2,'')+ISNULL(jldj.QS_Info3,'')+ISNULL(jldj.QS_Info4,'')+ISNULL(jldj.QS_Info5,'')+ISNULL(jldj.QS_Info6,'')) from jl_hj_dj jldj where FR_No=aa.FR_No and state=1 order by hjid desc) as SP_Mon,aa.HJ_STOP_TIME from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home,jl.Former_JQ_Name,jl.Fr_DAH,jl.State_ZDZF,jl.ZDZF_Type,jl.FR_GJ,(select top 1 (jldj.QS_Info1+ISNULL(jldj.QS_Info2,'')+ISNULL(jldj.QS_Info3,'')+ISNULL(jldj.QS_Info4,'')+ISNULL(jldj.QS_Info5,'')+ISNULL(jldj.QS_Info6,'')) from jl_hj_dj jldj where FR_No=jl.FR_No and state=1 order by hjid desc) as SP_Mon,jl.HJ_STOP_TIME from JL_FR jl,JL_JB jb,JL_Jq jq where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq ) as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
				}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
					str.append("select aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,dbo.get_hj_UseCount(aa.FR_No) as use1,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,(select top 1 DJ_Time from jl_hj_dj where FR_No=aa.FR_No and state=1 order by hjid desc) as HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home,(select top 1 JQ_Name from jl_hj_dj where FR_No=aa.FR_No and state=1 order by hjid desc) as Former_JQ_Name,aa.Fr_DAH,aa.State_ZDZF,aa.ZDZF_Type,aa.FR_GJ,(select top 1 (jldj.QS_Info1+ISNULL(jldj.QS_Info2,'')+ISNULL(jldj.QS_Info3,'')+ISNULL(jldj.QS_Info4,'')+ISNULL(jldj.QS_Info5,'')+ISNULL(jldj.QS_Info6,'')) from jl_hj_dj jldj where FR_No=aa.FR_No and state=1 order by hjid desc) as SP_Mon,aa.HJ_STOP_TIME from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home,jl.Former_JQ_Name,jl.Fr_DAH,jl.State_ZDZF,jl.ZDZF_Type,jl.FR_GJ,(select top 1 (jldj.QS_Info1+ISNULL(jldj.QS_Info2,'')+ISNULL(jldj.QS_Info3,'')+ISNULL(jldj.QS_Info4,'')+ISNULL(jldj.QS_Info5,'')+ISNULL(jldj.QS_Info6,'')) from jl_hj_dj jldj where FR_No=jl.FR_No and state=1 order by hjid desc) as SP_Mon,jl.HJ_STOP_TIME from JL_FR jl,JL_JB jb,JL_Jq jq where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq and jq.Is_Ts=0) as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
				}else{
					str.append("select aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,dbo.get_hj_UseCount(aa.FR_No) as use1,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,(select top 1 DJ_Time from jl_hj_dj where FR_No=aa.FR_No and state=1 order by hjid desc) as HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home,(select top 1 JQ_Name from jl_hj_dj where FR_No=aa.FR_No and state=1 order by hjid desc) as Former_JQ_Name,aa.Fr_DAH,aa.State_ZDZF,aa.ZDZF_Type,aa.FR_GJ,(select top 1 (jldj.QS_Info1+ISNULL(jldj.QS_Info2,'')+ISNULL(jldj.QS_Info3,'')+ISNULL(jldj.QS_Info4,'')+ISNULL(jldj.QS_Info5,'')+ISNULL(jldj.QS_Info6,'')) from jl_hj_dj jldj where FR_No=aa.FR_No and state=1 order by hjid desc) as SP_Mon,aa.HJ_STOP_TIME from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home,jl.Former_JQ_Name,jl.Fr_DAH,jl.State_ZDZF,jl.ZDZF_Type,jl.FR_GJ,(select top 1 (jldj.QS_Info1+ISNULL(jldj.QS_Info2,'')+ISNULL(jldj.QS_Info3,'')+ISNULL(jldj.QS_Info4,'')+ISNULL(jldj.QS_Info5,'')+ISNULL(jldj.QS_Info6,'')) from jl_hj_dj jldj where FR_No=jl.FR_No and state=1 order by hjid desc) as SP_Mon,jl.HJ_STOP_TIME from JL_FR jl,JL_JB jb,JL_Jq jq,SYS_USER_JQ suj where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq and jq.jq_no=suj.JQ_No and suj.Group_No='");
					str.append(user.getGroupNo());
					str.append("') as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
				}
				if(ff.getJq()!=null  && !ff.getJq().equals("null")){
					str.append("and aa.jq='"+ff.getJq()+"' ");
				}
			
				if(ff.getFrName()!=null && !ff.getFrName().trim().equals("")){
					str.append("and (dbo.f_get_fryp(aa.FR_Name,'"+ff.getFrName().trim()+"') =1 or aa.FR_Name like '%"+ff.getFrName()+"%')");
				}
				if(ff.getFrNo()!=null  && !ff.getFrNo().trim().equals("")){
					str.append("and aa.FR_No='"+ff.getFrNo().trim()+"' ");
				}
//				if(frNo!=null){
//					str.append("and aa.FR_No='"+frNo.trim()+"' ");
//				}
				if(ff.getQsName()!=null && !ff.getQsName().trim().equals("")){
					str.append("and qs.QS_Name = '"+ff.getQsName().trim()+"' ");
				}
				if(ff.getQssfz()!=null && !ff.getQssfz().trim().equals("")){
					str.append("and qs.QS_SFZ ='"+ff.getQssfz().trim()+"' ");
				}
				str.append(" group by aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,aa.Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home,aa.Former_JQ_Name,aa.Fr_DAH,aa.State_ZDZF,aa.ZDZF_Type,aa.FR_GJ,aa.SP_Mon,aa.HJ_STOP_TIME order by aa.webId desc");
				//str.append("group by aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,aa.Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home order by aa.webId desc");
				//str.append(" order by aa.webId desc");
				List list3=hds.searchAllBySql(str.toString());
				Iterator it=list3.iterator();
				while(it.hasNext()){
					Object[] obj=(Object[])it.next();
					Fr fr=new Fr();
					//System.out.println(obj[0].toString());
					fr.setWebId(obj[0].toString());
					
					fr.setFrNo(obj[1].toString());
					//System.out.println(obj[1].toString());
					if(obj[2]!=null && !obj[2].toString().equals("")){
						fr.setFrName(obj[2].toString());
					}
					fr.setJy(obj[3].toString());
					fr.setJq(obj[4].toString());
					if(obj[5]!=null && !obj[5].toString().equals("")){
						fr.setJbName(obj[5].toString());
					}
					if(obj[6]!=null && !obj[6].toString().equals("")){
						fr.setJqName(obj[6].toString());
					}
					//fr.setHjUse(obj[7].toString());
					
					fr.setHjJb(obj[8].toString());
					fr.setMonitorFlag(obj[9].toString());
					fr.setHjLeft(obj[10].toString());
					if(obj[11]!=null && !obj[11].toString().equals("")){
						fr.setHjLastTime(obj[11].toString().substring(0, 10));
					}
					
					if(obj[12]!=null && !obj[12].toString().equals("")){
						if(fr.getHjLastTime()!=null && !fr.getHjLastTime().substring(0, 7).equals(loginTime.substring(0, 7))){
							fr.setHjUse("0");
						}else{
							fr.setHjUse(obj[12].toString());
						}
					}else{
						fr.setHjUse("0");
					}
					if(obj[13]!=null && !obj[13].toString().equals("")){
						fr.setInfoRjsj(obj[13].toString());
					}
					if(obj[14]!=null && !obj[14].toString().equals("")){
						fr.setInfoJg(obj[14].toString());
					}
					if(obj[15]!=null && obj[15].toString().equals("1")){
						fr.setInfoZdzf("A类");
					}else if(obj[15]!=null && obj[15].toString().equals("2")){
						fr.setInfoZdzf("B类");
					}else if(obj[15]!=null && obj[15].toString().equals("3")){
						fr.setInfoZdzf("C类");
					}
					if(obj[16]!=null && !obj[16].toString().equals("")){
						fr.setInfoZm(obj[16].toString());
					}
					if(obj[17]!=null && !obj[17].toString().equals("")){
						fr.setInfoXq(obj[17].toString());
					}
					if(obj[18]!=null && !obj[18].toString().equals("")){
						fr.setInfoCsrq(obj[18].toString());
						
					}
					if(obj[19]!=null && !obj[19].toString().equals("")){
						fr.setInfoHome(obj[19].toString());
						
					}
					if(obj[20]!=null && !obj[20].toString().equals("")){
						fr.setFormerJQName(obj[20].toString());
						//System.out.println(obj[20].toString());
					}
					if(obj[21]!=null && !obj[21].toString().equals("")){
						fr.setFrDah(obj[21].toString());
						//System.out.println("frdah="+obj[21].toString());
					}
					if(obj[22]!=null && !obj[22].toString().equals("")){
						fr.setStateZdzf(obj[22].toString());
						//System.out.println("frdah="+obj[21].toString());
					}
					if(obj[23]!=null && !obj[23].toString().equals("")){
						fr.setZdzfType(obj[23].toString());
						//System.out.println("frdah="+obj[21].toString());
					}
					if(obj[24]!=null && !obj[24].toString().equals("")){
						fr.setFrGj(obj[24].toString());
						//System.out.println("frdah="+obj[21].toString());
					}
					if(obj[25]!=null && !obj[25].toString().equals("")){
						fr.setQsName(obj[25].toString());
						//System.out.println("frdah="+obj[21].toString());
					}
					if(obj[26]!=null && !obj[26].toString().equals("")){
						fr.setHjStopTime(obj[26].toString().substring(0,10));
					}
					
					jlFrList.add(fr);
				}
		 	}
			if(user.getIsSuper()==1){
				String hql="from JlJq";
		 	 	List<JlJq> list=hds.searchAll(hql);
		 	 	request.setAttribute("jljqList", list);
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				String hql="from JlJq where isTs=0";
		 	 	List<JlJq> list=hds.searchAll(hql);
		 	 	request.setAttribute("jljqList", list);
			}else{
				String hql="select jq from JlJq jq,SysUserJq suj where jq.jqNo=suj.jqNo and suj.groupNo='"+user.getGroupNo()+"'";
		 	 	List<JlJq> list=hds.searchAll(hql);
		 	 	request.setAttribute("jljqList", list);
			}
		 	request.setAttribute("jlFrList", jlFrList);
		 	if(jlFrList.size()<5){
		 		for(int i=0;i<5-jlFrList.size();i++){
		 			Fr fr=new Fr();
					fr.setWebId("");
					fr.setFrNo("");
					fr.setFrName("");
					fr.setJy("");
					fr.setJq("");
					fr.setJbName("");
					fr.setQqUse("");
					fr.setQqLeft("");
					fr.setJqName("");
					fr.setHjUse("");
					fr.setHjLeft("");
					fr.setHjJb("");
					fr.setHjLastTime("");
					fr.setFormerJQName("");
					fr.setFrDah("");
					fr.setStateZdzf("");
					fr.setZdzfType("");
					fr.setFrGj("");
					fr.setQsName("");
					fr.setHjStopTime("");
					unjlFrList.add(fr);
		 		}
		 	}
		 	request.setAttribute("unjlFrList", unjlFrList);
		 	if(frNo!=null){
		 		String hql2="from JlQs where frNo='"+frNo+"'";
			 	List<JlQs> listJlQs=hds.searchAll(hql2);
			 	List<JlQs> unjlListJlQs=new ArrayList<JlQs>();
			 	if(listJlQs.size()<10){
			 		for(int i=0;i<10-listJlQs.size();i++){
			 			JlQs jlQs=new JlQs();
						unjlListJlQs.add(jlQs);
			 		}
			 	}
			 	int hjTime=hds.getLineNo("get_HJ_SJ", frNo);
			 	if(jlFrList.size()>0){
			 		Fr fr=(Fr)jlFrList.get(0);
				 	request.setAttribute("monitorFlag", fr.getMonitorFlag());
			 	}
			 	
			 	request.setAttribute("hjTime", hjTime);
			 	request.setAttribute("listJlQs", listJlQs);
			 	request.setAttribute("unjlListJlQs", unjlListJlQs);
			 	request.setAttribute("frNo", frNo);
		 	}
		 	if(jlFrList.size()==1){
		 		Fr fr=jlFrList.get(0);
		 		String hql3="from JlQs where frNo='"+fr.getFrNo()+"'";
			 	List<JlQs> listJlQs=hds.searchAll(hql3);
			 	List<JlQs> unjlListJlQs=new ArrayList<JlQs>();
			 	if(listJlQs.size()<10){
			 		for(int i=0;i<10-listJlQs.size();i++){
			 			JlQs jlQs=new JlQs();
						unjlListJlQs.add(jlQs);
			 		}
			 	}
			 	request.setAttribute("listJlQs", listJlQs);
			 	request.setAttribute("unjlListJlQs", unjlListJlQs);
			 	request.setAttribute("frNo", frNo);
		 	}
		 	String isDjck="from HjdjAcdWindowsInfo where acdip='"+request.getRemoteAddr()+"'";
		 	List<HjdjAcdWindowsInfo> listHjdjAcdWindowsInfo=hds.searchAll(isDjck);
		 	if(listHjdjAcdWindowsInfo.size()>0){
		 		HjdjAcdWindowsInfo hjdjAcdWindowsInfo=(HjdjAcdWindowsInfo)listHjdjAcdWindowsInfo.get(0);
		 		request.setAttribute("hjdjAcdWindowsInfo", hjdjAcdWindowsInfo);
		 		List<HjdjAcdInfo> listHjdjAcdInfo=hds.searchAll("from HjdjAcdInfo");
				if(listHjdjAcdInfo.size()>0){
					HjdjAcdInfo hjdjAcdInfo=(HjdjAcdInfo)listHjdjAcdInfo.get(0);
					request.setAttribute("hjdjAcdInfo", hjdjAcdInfo);
				}
		 	}
		 	String hql5="from SysParam where paramName='HJ_Client3'";
			List<SysParam> list5=hds.searchAll(hql5);
			if(list5.size()>0){
				SysParam sysParam1=(SysParam)list5.get(0);
				request.getSession().setAttribute("hJClient3", sysParam1);
			}
		 	return mapping.findForward("addHjdj");
	}
	public ActionForward checkFr1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	if(user.getIsSuper()==1){
				String hql="from JlJq";
		 	 	List<JlJq> list=hds.searchAll(hql);
		 	 	request.setAttribute("jljqList", list);
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				String hql="from JlJq where isTs=0";
		 	 	List<JlJq> list=hds.searchAll(hql);
		 	 	request.setAttribute("jljqList", list);
			}else{
				String hql="select jq from JlJq jq,SysUserJq suj where jq.jqNo=suj.jqNo and suj.groupNo='"+user.getGroupNo()+"'";
		 	 	List<JlJq> list=hds.searchAll(hql);
		 	 	request.setAttribute("jljqList", list);
			}
		 	List<Fr> unjlFrList=new ArrayList<Fr>();
		 	for(int i=0;i<5;i++){
	 			Fr fr=new Fr();
				fr.setWebId("");
				fr.setFrNo("");
				fr.setFrName("");
				fr.setJy("");
				fr.setJq("");
				fr.setJbName("");
				fr.setQqUse("");
				fr.setQqLeft("");
				fr.setJqName("");
				fr.setHjUse("");
				fr.setHjLeft("");
				fr.setHjJb("");
				fr.setHjLastTime("");
				fr.setFormerJQName("");
				fr.setFrDah("");
				fr.setStateZdzf("");
				fr.setZdzfType("");
				fr.setFrGj("");
				fr.setQsName("");
				fr.setHjStopTime("");
				unjlFrList.add(fr);
	 		}
		 	request.setAttribute("unjlFrList", unjlFrList);
		 	
		 	String isDjck="from HjdjAcdWindowsInfo where acdip='"+request.getRemoteAddr()+"'";
		 	List<HjdjAcdWindowsInfo> listHjdjAcdWindowsInfo=hds.searchAll(isDjck);
		 	if(listHjdjAcdWindowsInfo.size()>0){
		 		HjdjAcdWindowsInfo hjdjAcdWindowsInfo=(HjdjAcdWindowsInfo)listHjdjAcdWindowsInfo.get(0);
		 		request.setAttribute("hjdjAcdWindowsInfo", hjdjAcdWindowsInfo);
		 		List<HjdjAcdInfo> listHjdjAcdInfo=hds.searchAll("from HjdjAcdInfo");
				if(listHjdjAcdInfo.size()>0){
					HjdjAcdInfo hjdjAcdInfo=(HjdjAcdInfo)listHjdjAcdInfo.get(0);
					request.setAttribute("hjdjAcdInfo", hjdjAcdInfo);
				}
		 	}
		 	return mapping.findForward("addHjdj");
	}
	public ActionForward checkJqueryFr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user!=null){
				String frName = java.net.URLDecoder.decode((String)request.getParameter("frName"),"UTF-8");
			 	String qsSfz = java.net.URLDecoder.decode((String)request.getParameter("qsSfz"),"UTF-8");
			 	String qsName = java.net.URLDecoder.decode((String)request.getParameter("qsName"),"UTF-8");
			 	String jq=request.getParameter("jq");
			 	Calendar c = Calendar.getInstance();
			 	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
			 	c  =  Calendar.getInstance(Locale.CHINESE);   
			 	String loginTime = simpleDateTimeFormat.format(c.getTime());
			 	//StringBuffer str=new StringBuffer("select aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_Use,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home from JL_FR jl,JL_JB jb,JL_Jq jq where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq ) as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
			 	//StringBuffer str=new StringBuffer("");
			 	StringBuffer str=new StringBuffer("select aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,dbo.get_hj_UseCount(aa.FR_No) as use1,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,(select top 1 DJ_Time from jl_hj_dj where FR_No=aa.FR_No and state=1 order by hjid desc) as HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,Info_ZDZF,Info_ZM,Info_XQ,Info_CSRQ,info_home,(select top 1 JQ_Name from jl_hj_dj where FR_No=aa.FR_No and state=1 order by hjid desc) as Former_JQ_Name,aa.Fr_DAH,aa.State_ZDZF,aa.ZDZF_Type,aa.FR_GJ,(select top 1 (jldj.QS_Info1+ISNULL(jldj.QS_Info2,'')+ISNULL(jldj.QS_Info3,'')+ISNULL(jldj.QS_Info4,'')+ISNULL(jldj.QS_Info5,'')+ISNULL(jldj.QS_Info6,'')) from jl_hj_dj jldj where FR_No=aa.FR_No and state=1 order by hjid desc) as SP_Mon,aa.HJ_STOP_TIME from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home,jl.Former_JQ_Name,jl.Fr_DAH,jl.State_ZDZF,jl.ZDZF_Type,jl.FR_GJ,(select top 1 (jldj.QS_Info1+ISNULL(jldj.QS_Info2,'')+ISNULL(jldj.QS_Info3,'')+ISNULL(jldj.QS_Info4,'')+ISNULL(jldj.QS_Info5,'')+ISNULL(jldj.QS_Info6,'')) from jl_hj_dj jldj where FR_No=jl.FR_No and state=1 order by hjid desc) as SP_Mon,jl.HJ_STOP_TIME from JL_FR jl,JL_JB jb,JL_Jq jq where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq ) as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
//			 	if(user.getIsSuper()==1){
//					str.append("select distinct aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_Use,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home from JL_FR jl,JL_JB jb,JL_Jq jq where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq ) as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
//					//str.append("select distinct fr.webId,fr.FR_No,fr.FR_Name,fr.jy,fr.jq,IsNull((select JB_Name from JL_JB where JB_No=fr.JB_No),'未定义'),IsNull((select JQ_Name from JL_JQ where JQ_No=fr.JQ),'未定义') ,dbo.get_hj_UseCount(fr.FR_No) as use1,fr.HJ_JB,fr.Monitor_Flag,IsNull((select HJ_Count from JL_JB where JB_No=fr.JB_No),30) ,fr.HJ_Last_Time,fr.HJ_Use,fr.Info_RJSJ,dbo.f_get_jg(fr.Info_JG) as Info_JG,fr.Info_ZDZF,fr.Info_ZM,fr.Info_XQ,fr.Info_CSRQ,fr.info_home from  JL_FR  fr left join JL_QS qs  on fr.FR_No=qs.FR_No where 1=1");
//				}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
//					str.append("select distinct aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_Use,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home from JL_FR jl,JL_JB jb,JL_Jq jq where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq and jq.Is_Ts=0) as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
//				}else{
//					str.append("select distinct aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_Use,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home from JL_FR jl,JL_JB jb,JL_Jq jq,SYS_USER_JQ suj where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq and jq.jq_no=suj.JQ_No and suj.Group_No='");
//					str.append(user.getGroupNo());
//					str.append("') as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
//				}
			 	if(!jq.equals("null")){
					str.append("and aa.jq='"+jq+"' ");
				}
				if(!frName.trim().equals("")){
					str.append("and (dbo.f_get_fryp(aa.FR_Name,'"+frName.trim()+"') =1 or aa.FR_Name like '%"+frName.trim()+"%') ");
				}
				if(!qsName.trim().equals("")){
					str.append("and qs.QS_Name = '"+qsName.trim()+"' ");
				}
				if(!qsSfz.trim().equals("")){
					str.append("and qs.QS_SFZ ='"+qsSfz.trim()+"' ");
				}
				str.append("group by aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,aa.Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home,aa.Former_JQ_Name,aa.Fr_DAH,aa.State_ZDZF,aa.ZDZF_Type,aa.FR_GJ,aa.SP_Mon,aa.HJ_STOP_TIME order by aa.webId desc");
				//str.append("group by aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,aa.Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home order by aa.webId desc");
				//str.append(" order by aa.webId desc");
				List list3=hds.searchAllBySql(str.toString());
				Iterator it=list3.iterator();
				List<Fr> listFrList=new ArrayList<Fr>();
				while(it.hasNext()){
					Object[] obj=(Object[])it.next();
					Fr fr=new Fr();
					fr.setWebId(obj[0].toString());
					fr.setFrNo(obj[1].toString());
					if(obj[2]!=null && !obj[2].toString().equals("")){
						fr.setFrName(obj[2].toString());
					}
					fr.setJy(obj[3].toString());
					fr.setJq(obj[4].toString());
					if(obj[5]!=null && !obj[5].toString().equals("")){
						fr.setJbName(obj[5].toString());
					}
					if(obj[6]!=null && !obj[6].toString().equals("")){
						fr.setJqName(obj[6].toString());
					}
					fr.setHjUse(obj[7].toString());
					fr.setHjJb(obj[8].toString());
					fr.setMonitorFlag(obj[9].toString());
					fr.setHjLeft(obj[10].toString());
					if(obj[11]!=null && !obj[11].toString().equals("")){
						fr.setHjLastTime(obj[11].toString().substring(0, 10));
					}
					if(obj[12]!=null && !obj[12].toString().equals("")){
						if(fr.getHjLastTime()!=null && !fr.getHjLastTime().substring(0, 7).equals(loginTime.substring(0, 7))){
							fr.setHjUse("0");
						}else{
							fr.setHjUse(obj[12].toString());
						}
					}else{
						fr.setHjUse("0");
					}
					if(obj[13]!=null && !obj[13].toString().equals("")){
						fr.setInfoRjsj(obj[13].toString());
					}
					if(obj[14]!=null && !obj[14].toString().equals("")){
						fr.setInfoJg(obj[14].toString());
					}
					if(obj[15]!=null && obj[15].toString().equals("1")){
						fr.setInfoZdzf("A类");
					}else if(obj[15]!=null && obj[15].toString().equals("2")){
						fr.setInfoZdzf("B类");
					}else if(obj[15]!=null && obj[15].toString().equals("3")){
						fr.setInfoZdzf("C类");
					}
					if(obj[16]!=null && !obj[16].toString().equals("")){
						fr.setInfoZm(obj[16].toString());
					}
					if(obj[17]!=null && !obj[17].toString().equals("")){
						fr.setInfoXq(obj[17].toString());
					}
					if(obj[18]!=null && !obj[18].toString().equals("")){
						fr.setInfoCsrq(obj[18].toString());
					}
					if(obj[19]!=null && !obj[19].toString().equals("")){
						fr.setInfoHome(obj[19].toString());
					}
					if(obj[20]!=null && !obj[20].toString().equals("")){
						fr.setFormerJQName(obj[20].toString());
						//System.out.println(obj[20].toString());
					}
					if(obj[21]!=null && !obj[21].toString().equals("")){
						fr.setFrDah(obj[21].toString());
						//System.out.println(obj[20].toString());
					}
					if(obj[22]!=null && !obj[22].toString().equals("")){
						fr.setStateZdzf(obj[22].toString());
						//System.out.println("frdah="+obj[21].toString());
					}
					if(obj[23]!=null && !obj[23].toString().equals("")){
						fr.setZdzfType(obj[23].toString());
						//System.out.println("frdah="+obj[21].toString());
					}
					if(obj[24]!=null && !obj[24].toString().equals("")){
						fr.setFrGj(obj[24].toString());
						//System.out.println("frdah="+obj[21].toString());
					}
					if(obj[25]!=null && !obj[25].toString().equals("")){
						fr.setQsName(obj[25].toString());
						//System.out.println("frdah="+obj[21].toString());
					}
					if(obj[26]!=null && !obj[26].toString().equals("")){
						fr.setHjStopTime(obj[26].toString().substring(0,10));
					}
					listFrList.add(fr);
				}
				response.setContentType("text/json; charset=utf-8");
				JSONArray json=JSONArray.fromObject(listFrList);
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
			}
			
			return null;
	}
	//单击查询犯人亲属
	public ActionForward checkFrQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	String frNo = java.net.URLDecoder.decode((String)request.getParameter("frNo"),"UTF-8");
		 	List<FrQs> frQsList=new ArrayList();
		 	QueryQs queryQs=new QueryQs();
		 	StringBuffer str=new StringBuffer("select jl.webId,jl.frNo,jl.qsSfz,jl.qsName,jl.qsCard,jl.gx,jl.xb,jl.dz,jl.tele,jl.sw,jlFr.frName,jl.faceState,jl.qsZjlb,jl.qsSfzWlh,jl.bz,jl.hjStopTime,jl.spState from JlFr jlFr,JlQs jl where jlFr.frNo=jl.frNo and jlFr.frNo='");
			str.append(frNo+"'");
			List<JlQs> list=hds.searchAll(str.toString());
			Iterator it=list.iterator();
			while(it.hasNext()){
				Object[] obj=(Object[])it.next();
				FrQs frQs=new FrQs();
				frQs.setWebId(Integer.parseInt(obj[0].toString()));
				frQs.setFrNo(obj[1].toString());
				if(obj[2]!=null && obj[2].toString().trim()!=""){
					frQs.setQsSfz(obj[2].toString());
				}
				if(obj[3]!=null && obj[3].toString().trim()!=""){
					frQs.setQsName(obj[3].toString());
				}
				if( obj[4]!=null && obj[4].toString().trim()!=""){
					frQs.setQsCard(obj[4].toString());
				}
				if( obj[5]!=null && obj[5].toString().trim()!=""){
					frQs.setGx(obj[5].toString());
				}
				if(obj[6]!=null && obj[6].toString().trim()!=""){
					frQs.setXb(obj[6].toString());
				}
				if( obj[7]!=null && obj[7].toString().trim()!=""){
					frQs.setDz(obj[7].toString());
				}
				if(obj[8]!=null && obj[8].toString().trim()!=""){
					frQs.setTele(obj[8].toString());
				}
				if( obj[9]!=null && obj[9].toString().trim()!=""){
					frQs.setSw(Integer.parseInt(obj[9].toString()));
				}
				if(obj[10]!=null && obj[10].toString().trim()!=""){
					frQs.setFrName(obj[10].toString());
				}
				if(obj[11]!=null && obj[11].toString().trim()!=""){
					frQs.setFaceState(Integer.parseInt(obj[11].toString()));
				}
				if(obj[12]!=null && obj[12].toString().trim()!=""){
					frQs.setQsZjlb(Integer.parseInt(obj[12].toString()));
				}
				if(obj[13]!=null && obj[13].toString().trim()!=""){
					frQs.setQsSfzWlh(obj[13].toString());
				}
				if(obj[14]!=null && obj[14].toString().trim()!=""){
					frQs.setBz(obj[14].toString());
				}
				if(obj[15]!=null && obj[15].toString().trim()!=""){
					frQs.setHjStopTime(obj[15].toString().substring(0, 10));
				}
				if(obj[16]!=null && obj[16].toString().trim()!=""){
					frQs.setSpState(obj[16].toString());
				}
				frQsList.add(frQs);
			}

		 	queryQs.setList(frQsList);
		 	queryQs.setHjTime(hds.getLineNo("get_HJ_SJ", frNo));
		 	String hql1="from SysParam where paramName='HJ_Client'";
		 	List<SysParam> list1=hds.searchAll(hql1);
		 	if(list1.size()>0){
		 		SysParam sysParam=(SysParam)list1.get(0);
		 		queryQs.setEnter(sysParam.getParamData2());
		 	}
		 	List<QueryQs> list2=new ArrayList<QueryQs>();
		 	list2.add(queryQs);
		 	response.setContentType("text/json; charset=utf-8");   
		 	JSONArray jsonArray=JSONArray.fromObject(list2);
		 	response.getWriter().println(jsonArray.toString());
		 	return null;
	}
	//双击查询犯人亲属
	public ActionForward checkQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String frNo = java.net.URLDecoder.decode((String)request.getParameter("frNo"),"UTF-8");
			List<FrQs> frQsList=new ArrayList();
			StringBuffer str=new StringBuffer("select jl.webId,jl.frNo,jl.qsSfz,jl.qsName,jl.qsCard,jl.gx,jl.xb,jl.dz,jl.tele,jl.sw,jlFr.frName,jl.faceState,jl.qsZjlb,jl.qsSfzWlh,jl.bz,jl.hjStopTime,jl.spState,jl.spUserNo,jl.spTime from JlFr jlFr,JlQs jl where jlFr.frNo=jl.frNo and jlFr.frNo='");
			str.append(frNo+"'");
			List<JlQs> list=hds.searchAll(str.toString());
			Iterator it=list.iterator();
			while(it.hasNext()){
				Object[] obj=(Object[])it.next();
				FrQs frQs=new FrQs();
				frQs.setWebId(Integer.parseInt(obj[0].toString()));
				frQs.setFrNo(obj[1].toString());
				if(obj[2]!=null && obj[2].toString().trim()!=""){
					frQs.setQsSfz(obj[2].toString());
				}
				if(obj[3]!=null && obj[3].toString().trim()!=""){
					frQs.setQsName(obj[3].toString());
				}
				if( obj[4]!=null && obj[4].toString().trim()!=""){
					frQs.setQsCard(obj[4].toString());
				}
				if( obj[5]!=null && obj[5].toString().trim()!=""){
					frQs.setGx(obj[5].toString());
				}
				if(obj[6]!=null && obj[6].toString().trim()!=""){
					frQs.setXb(obj[6].toString());
				}
				if( obj[7]!=null && obj[7].toString().trim()!=""){
					frQs.setDz(obj[7].toString());
				}
				if(obj[8]!=null && obj[8].toString().trim()!=""){
					frQs.setTele(obj[8].toString());
				}
				if( obj[9]!=null && obj[9].toString().trim()!=""){
					frQs.setSw(Integer.parseInt(obj[9].toString()));
				}
				if(obj[10]!=null && obj[10].toString().trim()!=""){
					frQs.setFrName(obj[10].toString());
				}
				if(obj[11]!=null && obj[11].toString().trim()!=""){
					frQs.setFaceState(Integer.parseInt(obj[11].toString()));
				}
				if(obj[12]!=null && obj[12].toString().trim()!=""){
					frQs.setQsZjlb(Integer.parseInt(obj[12].toString()));
				}
				if(obj[13]!=null && obj[13].toString().trim()!=""){
					frQs.setQsSfzWlh(obj[13].toString());
				}
				if(obj[14]!=null && obj[14].toString().trim()!=""){
					frQs.setBz(obj[14].toString());
				}
				if(obj[15]!=null && obj[15].toString().trim()!=""){
					frQs.setHjStopTime(obj[15].toString().substring(0, 10));
				}
				if(obj[16]!=null && obj[16].toString().trim()!=""){
					frQs.setSpState(obj[16].toString());
				}
				if(obj[17]!=null && obj[17].toString().trim()!=""){
					frQs.setSpUserNo(obj[17].toString());
				}
				if(obj[18]!=null && obj[18].toString().trim()!=""){
					frQs.setSpTime(obj[18].toString().substring(0, 10));
				}
				frQsList.add(frQs);
			}
			request.setAttribute("qsList",frQsList);
			request.setAttribute("frNo",frNo);
			
			String hql4="from SysParam where paramName='HJ_Client1'";
			List<SysParam> list4=hds.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("sysParam", sysParam1);
			}
			String hql5="from SysParam where paramName='HJ_Client2'";
			List<SysParam> list5=hds.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("sysParam1", sysParam1);
			}
			return mapping.findForward("qsMain");
	}
	//添加亲属
	public ActionForward addQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
			String frNo=request.getParameter("frNo");
			StringBuffer str=new StringBuffer(" from JlFr jl where jl.frNo='");
			str.append(frNo+"'");
			List<JlFr> list=hds.searchAll(str.toString());
			JlFr jlFr=(JlFr)list.get(0);
			List<Integer> qjswList=new ArrayList();
			for(int i=1;i<=9;i++){
				qjswList.add(i);
			}
			List<Integer> unswList=new ArrayList();
			unswList=qjswList;
			StringBuffer str1=new StringBuffer(" from JlQs jl where jl.frNo='");
			str1.append(frNo+"'");
			List<JlQs> swlist=hds.searchAll(str1.toString());
			if(swlist!=null){
				for(int i=0;i<unswList.size();i++)
					for(int j=0;j<swlist.size();j++){
						JlQs jlQs=(JlQs)swlist.get(j);
						if(jlQs.getSw()!=null){
							if(jlQs.getSw().intValue()==unswList.get(i)){
							unswList.remove(i);
							i=0;
							}
						}
					}
			}
			String hql3="from SysParam where paramName='System_Set'";
			List<SysParam> list3=hds.searchAll(hql3);
			SysParam sysParam=(SysParam)list3.get(0);
			String qsSfz="";
			if(sysParam.getParamData5().equals("1")){
				qsSfz=System.currentTimeMillis()+"";
			}
			String hql4="from SysParam where paramName='HJ_Client'";
			List<SysParam> list4=hds.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("sysParam", sysParam1);
			}
			List<String> qsList=new ArrayList();
			String sqlGx="select QS_GX from JL_QS_GX";
			List<String> qsList1 =hds.searchAllBySql(sqlGx);
			for(int i=0;i<qsList1.size();i++){
				qsList.add(qsList1.get(i));
			}
			String hql5="from SysUser where userNo='"+user.getUserNo()+"'";
			List<SysUser> list5=hds.searchAll(hql5);
			if(list5.size()>0){
				SysUser sysUser=(SysUser)list5.get(0);
				request.setAttribute("sysUser", sysUser);
			}
			request.setAttribute("qsGxList",qsList);
			request.setAttribute("qsSfz", qsSfz);
			request.setAttribute("unswList",unswList);
			request.setAttribute("jlFr", jlFr);
		    return mapping.findForward("addFrQs");
	}
	//添加保存亲属
	public ActionForward addSaveFrQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    Timestamp now1=new Timestamp(System.currentTimeMillis());
		 	String frNo = java.net.URLDecoder.decode((String)request.getParameter("frNo"),"UTF-8");
		 	String frName = java.net.URLDecoder.decode((String)request.getParameter("frName1"),"UTF-8");
			String jqNo1 = java.net.URLDecoder.decode((String)request.getParameter("jqNo1"),"UTF-8");
		 	String qsSfz = java.net.URLDecoder.decode((String)request.getParameter("qsSfz"),"UTF-8");
		 	String qsName = java.net.URLDecoder.decode((String)request.getParameter("qsName"),"UTF-8");
		 	String qsCard = java.net.URLDecoder.decode((String)request.getParameter("qsCard"),"UTF-8");
		 	String dz = java.net.URLDecoder.decode((String)request.getParameter("dz"),"UTF-8");
		 	String tele = java.net.URLDecoder.decode((String)request.getParameter("tele"),"UTF-8");
		 	String gx = java.net.URLDecoder.decode((String)request.getParameter("gx"),"UTF-8");
		 	String xb = java.net.URLDecoder.decode((String)request.getParameter("xb"),"UTF-8");
		 	String zjlb = java.net.URLDecoder.decode((String)request.getParameter("zjlb"),"UTF-8");
		 	String cardType = java.net.URLDecoder.decode((String)request.getParameter("cardType"),"UTF-8");
		 	String spState = java.net.URLDecoder.decode((String)request.getParameter("spState"),"UTF-8");
		 	String photoAddress=request.getParameter("photoAddress");
		 	String jz=request.getParameter("jz");
		 	String qsSfzWlh = java.net.URLDecoder.decode((String)request.getParameter("qsSfzWlh"),"UTF-8");
		 	String bz = java.net.URLDecoder.decode((String)request.getParameter("bz"),"UTF-8");
		 	if(!qsSfz.trim().equals("")){
		 		String hql="from JlQs where frNo='"+frNo+"' and qsSfz='"+qsSfz.trim()+"'";
			 	List list=hds.searchAll(hql);
		 		if(list.size()>0){
		 			  JSONArray json=JSONArray.fromObject(-1);
		 		 	  response.getWriter().println(json.toString());
			 	}else{
			 		if(!qsCard.trim().equals("")){
			 			String hql1="from JlQs where qsCard='"+qsCard+"'";
						List<JlQs> jlQslist1=hds.searchAll(hql1);
						if(jlQslist1.size()>0){
							for(int i=0;i<jlQslist1.size();i++){
								jlQslist1.get(i).setQsCard("");
								hds.update(jlQslist1.get(i));
								
							}
						}
			 		}
			 		JlQs jlQs=new JlQs();
			 		jlQs.setFrNo(frNo);
			 		jlQs.setQsSfz(qsSfz);
			 		if(!qsName.trim().equals("")){
			 			jlQs.setQsName(qsName);
			 	    }
			 		if(!qsCard.trim().equals("")){
			 			jlQs.setQsCard(qsCard);
			 		}
			 		if(!dz.trim().equals("")){
			 			jlQs.setDz(dz);
			 	    }
			 		if(!tele.trim().equals("")){
			 			jlQs.setTele(tele);
			 		}
			 		if(!qsSfzWlh.trim().equals("")){
			 			jlQs.setQsSfzWlh(qsSfzWlh);
			 	    }
			 		if(!bz.trim().equals("")){
			 			jlQs.setBz(bz);
			 	    }
			 		jlQs.setGx(gx);
			 		jlQs.setXb(xb);
			 	    jlQs.setCreateTime(now1);
			 	    if(!zjlb.equals("null")){
			 	    	jlQs.setQsZjlb(Integer.parseInt(zjlb));
			 	    }
			 	    if(!photoAddress.trim().equals("")){
			 		   String khdIp=request.getRemoteAddr();
			 		   String url1="http://"+khdIp+":9009/"+photoAddress.replace(":", "").replace("\\", "/");
			 		   URL url=new URL(url1);
			 		   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			 		   if(connection.getResponseCode()==200){
			 			  InputStream fis = url.openStream();
			 			  Thread.sleep(1000);
				 		  byte[] byte1 = new byte[fis.available()];
				 		  fis.read(byte1);
				 		  jlQs.setZp(byte1);
			 		   }
			 	    }
			 	    if(jz.length()>2){
			 		   sun.misc.BASE64Decoder decode=new sun.misc.BASE64Decoder();
			 		   byte[] datas=decode.decodeBuffer(jz.substring(1, jz.length()-2));
			 		   jlQs.setJz(datas);
			 	    }
			 	    
			 	    String hql8="from SysUser where userNo='"+user.getUserNo()+"'";
					List<SysUser> list8=hds.searchAll(hql8);
					SysUser sysUser=(SysUser)list8.get(0);
					if(!spState.equals("null")){
						if(sysUser.getIsSp()==1){
							jlQs.setSpState(Integer.parseInt(spState));
					 		jlQs.setSpUserNo(user.getUserNo());
					 		jlQs.setSpTime(now1);
						}else{
							jlQs.setSpState(Integer.parseInt(spState));
					 		jlQs.setSpUserNo(user.getUserNo());
					 		jlQs.setSpTime(now1);
						}				 		
				 	}else{
				 		jlQs.setSpState(0);
				 	}
					
			 	    jlQs.setFaceState(0);
					
			 	    String zpUrl = "http://"+request.getRemoteAddr()+":9009/C/"+qsSfz+".Jpg";
			 	    String zpName = qsSfz+".Jpg";
			 	    
			 	    boolean a = isNetFileAvailable(zpUrl);
//				 	System.out.println(a);				 	
				 	if(!a){
//			            throw new RuntimeException("文件不存在..");
				 		System.out.println("身份证图片文件不存在");
				 	
				 	}else{
				 		download(zpUrl,zpName,"D:\\Photo\\");
				 	    String zpUrl1="";
				 	    
				 	    String hql5="from SysParam where paramName='HJ_Client3'";
						List<SysParam> list5=hds.searchAll(hql5);
						SysParam sysParam1=(SysParam)list5.get(0);						
						zpUrl1=sysParam1.getParamData3()+qsSfz+".Jpg";
						jlQs.setZpUrl(zpUrl1);
				 	}
			 	    hds.save(jlQs);
			 	    StringBuffer str1=new StringBuffer("");
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
			 	    sl.setModel("罪犯管理");
			 	    sl.setInfo(str1.append("给罪犯编号为").append(frNo).append("的增加亲属（亲属姓名：").append(qsName).append(" 电话号码：").append(tele).append("）").toString());
			 	    sl.setOp("增加罪犯亲属信息");
			 	    hds.save(sl);
			 	   // if(cardType!=null && cardType.equals("1") && qsCard!=null && !qsCard.trim().equals("")){
			 	    if(cardType!=null && cardType.equals("1")){	
			 	    	JlTradeCardInfo jlTradeCardInfo=new JlTradeCardInfo();
			 	    	jlTradeCardInfo.setCardNum(qsCard);
			 	    	jlTradeCardInfo.setQsName(qsName);
			 	    	jlTradeCardInfo.setFrNo(frNo);
			 	    	jlTradeCardInfo.setOpName(user.getUserName());
			 	    	jlTradeCardInfo.setSj(now1);
			 	    	jlTradeCardInfo.setFrName(frName);
			 	    	jlTradeCardInfo.setJqName(jqNo1);
			 	    	hds.save(jlTradeCardInfo);
			 	    }
			 	    JSONArray json=JSONArray.fromObject(0);
			 	    response.getWriter().println(json.toString());
			 	    
			 	   
			 	}
		 	}else{
		 		JlQs jlQs=new JlQs();
		 		jlQs.setFrNo(frNo);
		 		jlQs.setQsSfz(qsSfz.trim());
		 		if(!qsName.trim().equals("")){
		 			jlQs.setQsName(qsName);
		 	    }
		 		if(!qsCard.trim().equals("")){
		 			String hql1="from JlQs where qsCard='"+qsCard+"'";
					List<JlQs> jlQslist1=hds.searchAll(hql1);
					if(jlQslist1.size()>0){
						for(int i=0;i<jlQslist1.size();i++){
							jlQslist1.get(i).setQsCard("");
							hds.update(jlQslist1.get(i));
						}
					}
		 			jlQs.setQsCard(qsCard);
		 		}
		 		if(!dz.trim().equals("")){
		 			jlQs.setDz(dz);
		 	    }
		 		if(!tele.trim().equals("")){
		 			jlQs.setTele(tele);
		 		}
		 		if(!qsSfzWlh.trim().equals("")){
		 			jlQs.setQsSfzWlh(qsSfzWlh);
		 	    }
		 		if(!bz.trim().equals("")){
		 			jlQs.setBz(bz);
		 	    }
		 		jlQs.setGx(gx);
		 		jlQs.setXb(xb);
		 	    if(!zjlb.equals("null")){
		 	    	jlQs.setQsZjlb(Integer.parseInt(zjlb));
		 	    }
		 	    if(!photoAddress.trim().equals("")){
		 		   String khdIp=request.getRemoteAddr();
		 		   String url1="http://"+khdIp+":9009/"+photoAddress.replace(":", "").replace("\\", "/");
		 		   URL url=new URL(url1);
		 		   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		 		   if(connection.getResponseCode()==200){
		 			  InputStream fis = url.openStream();
		 			  Thread.sleep(1000);
			 		  byte[] byte1 = new byte[fis.available()];
			 		  fis.read(byte1);
			 		  jlQs.setZp(byte1);
		 		   }
		 	    }
		 	    if(jz.length()>2){
		 		   sun.misc.BASE64Decoder decode=new sun.misc.BASE64Decoder();
		 		   byte[] datas=decode.decodeBuffer(jz.substring(1, jz.length()-2));
		 		   jlQs.setJz(datas);
		 	    }
		 	    jlQs.setCreateTime(now1);
		 
		 	    String hql8="from SysUser where userNo='"+user.getUserNo()+"'";
				List<SysUser> list8=hds.searchAll(hql8);
				SysUser sysUser=(SysUser)list8.get(0);
				if(!spState.equals("null")){
					if(sysUser.getIsSp()==1){
						jlQs.setSpState(Integer.parseInt(spState));
				 		jlQs.setSpUserNo(user.getUserNo());
				 		jlQs.setSpTime(now1);
					}else{
						jlQs.setSpState(Integer.parseInt(spState));
					}				 		
			 	}else{
			 		jlQs.setSpState(0);
			 	}
		 	    hds.save(jlQs);
		 	    StringBuffer str1=new StringBuffer("");
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
		 	    sl.setModel("罪犯管理");
		 	    sl.setInfo(str1.append("给罪犯编号为").append(frNo).append("的增加亲属（亲属姓名：").append(qsName).append(" 电话号码：").append(tele).append("）").toString());
		 	    sl.setOp("增加罪犯亲属信息");
		 	    hds.save(sl);
		 	   //if(cardType!=null && cardType.equals("1") && qsCard!=null && !qsCard.trim().equals("")){
		 	    if(cardType!=null && cardType.equals("1") ){	   
		 	    	JlTradeCardInfo jlTradeCardInfo=new JlTradeCardInfo();
		 	    	jlTradeCardInfo.setCardNum(qsCard);
		 	    	jlTradeCardInfo.setQsName(qsName);
		 	    	jlTradeCardInfo.setFrNo(frNo);
		 	    	jlTradeCardInfo.setOpName(user.getUserName());
		 	    	jlTradeCardInfo.setSj(now1);
		 	    	jlTradeCardInfo.setFrName(frName);
		 	    	jlTradeCardInfo.setJqName(jqNo1);
		 	    	hds.save(jlTradeCardInfo);
		 	    }
		 	    JSONArray json=JSONArray.fromObject(0);
		 	    response.getWriter().println(json.toString());
		 	}
			 	
	 	    return null;
	}	
	//删除亲属
	public ActionForward delFrQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String frNo=request.getParameter("frNo");
		    String webId=request.getParameter("webId");
		    JlQs jlQs=(JlQs)hds.findById(JlQs.class, Integer.parseInt(webId));
		    String hql="delete from JlQs jl where webId=?";
		    Object[] obj={Integer.parseInt(webId)};
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
		    sl.setModel("罪犯管理");
		    sl.setInfo("罪犯编号为"+frNo+"的亲属（姓名:"+jlQs.getQsName()+" 电话号码:"+jlQs.getTele()+"）被删除");
		    sl.setOp("删除罪犯亲属信息");
		    hds.save(sl);
		    hds.deleteByHql(hql, obj);
		    request.setAttribute("frNo", frNo);
		    return mapping.findForward("delFrQs");
	}
	//修改亲属
	public ActionForward updateFrQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
			String frNo=request.getParameter("frNo");
			StringBuffer str=new StringBuffer(" from JlFr jl where jl.frNo='");
			str.append(frNo+"'");
			List<JlFr> list=hds.searchAll(str.toString());
			JlFr jlFr=(JlFr)list.get(0);
			String hql="from JlQs jl where webId=?";
			Object[] obj={Integer.parseInt(webId)};
			List<JlQs> list1=hds.searchAll(hql, obj);
			JlQs jlQs=(JlQs)list1.get(0);
			List<Integer> qjswList=new ArrayList();
			for(int i=1;i<=9;i++){
				qjswList.add(i);
			}
			List<Integer> unswList=new ArrayList();
			unswList=qjswList;
			StringBuffer str1=new StringBuffer(" from JlQs jl where jl.frNo='");
			str1.append(frNo+"'");
			List<JlQs> swlist=hds.searchAll(str1.toString());
			if(swlist!=null){
				for(int i=0;i<unswList.size();i++)
					for(int j=0;j<swlist.size();j++){
						JlQs jlqs=(JlQs)swlist.get(j);
						if(jlqs.getSw()!=null){
							if(jlqs.getSw().intValue()==unswList.get(i)){
								unswList.remove(i);
								i=0;
							}
						}
					}
			}
			List<String> qsList=new ArrayList();
			String sqlGx="select QS_GX from JL_QS_GX";
			List<String> qsList1 =hds.searchAllBySql(sqlGx);
			for(int i=0;i<qsList1.size();i++){
				qsList.add(qsList1.get(i));
			}
			List<String> newQsList = new ArrayList<String>();
			String newQsGx = "select QS_GX from JL_QS_GX where  QS_GX='"+jlQs.getGx()+"'";
			List<String> newQsList1 = hds.searchAllBySql(newQsGx);
//			System.out.println(":"+newQsList1.size());
			if(newQsList1.size()==0){
				newQsList.add(jlQs.getGx());
			}
			
			String hql4="from SysParam where paramName='HJ_Client'";
			List<SysParam> list4=hds.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("sysParam", sysParam1);
			}
			String hql5="from SysUser where userNo='"+user.getUserNo()+"'";
			List<SysUser> list5=hds.searchAll(hql5);
			if(list5.size()>0){
				SysUser sysUser=(SysUser)list5.get(0);
				request.setAttribute("sysUser", sysUser);
			}
			request.setAttribute("qsGxList",qsList);
			request.setAttribute("newQsList",newQsList);
			request.setAttribute("unswList",unswList);
			request.setAttribute("jlFr", jlFr);
			request.setAttribute("jlQs", jlQs);
			return mapping.findForward("updateFrQs");
	}
	//修改保存亲属
	public ActionForward updateSaveFrQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			Timestamp now1=new Timestamp(System.currentTimeMillis());
			String qsId= request.getParameter("qsId");
			StringBuffer str2=new StringBuffer("");
			JlQs jlQs=(JlQs)hds.findById(JlQs.class, Integer.parseInt(qsId));
			String qsName = java.net.URLDecoder.decode((String)request.getParameter("qsName"),"UTF-8");
		 	String qsCard = java.net.URLDecoder.decode((String)request.getParameter("qsCard"),"UTF-8");
		 	String dz = java.net.URLDecoder.decode((String)request.getParameter("dz"),"UTF-8");
		 	String tele = java.net.URLDecoder.decode((String)request.getParameter("tele"),"UTF-8");
		 	String gx = java.net.URLDecoder.decode((String)request.getParameter("gx"),"UTF-8");
		 	String xb = java.net.URLDecoder.decode((String)request.getParameter("xb"),"UTF-8");
			String frName = java.net.URLDecoder.decode((String)request.getParameter("frName1"),"UTF-8");
			String jqNo1 = java.net.URLDecoder.decode((String)request.getParameter("jqNo1"),"UTF-8");
			String cardType = java.net.URLDecoder.decode((String)request.getParameter("cardType"),"UTF-8");
		 	String zjlb = request.getParameter("zjlb");
		 	String qsSfz = request.getParameter("qsSfz");
			String jz = request.getParameter("jz");
			String spState = request.getParameter("spState");
			String qsSfzWlh = java.net.URLDecoder.decode((String)request.getParameter("qsSfzWlh"),"UTF-8");
			String bz = java.net.URLDecoder.decode((String)request.getParameter("bz"),"UTF-8");
		 	String photoAddress = java.net.URLDecoder.decode((String)request.getParameter("photoAddress"),"UTF-8");
		 	if(jlQs.getQsSfz()!=null && !jlQs.getQsSfz().equals(qsSfz.trim())){
		 		String hql="from JlQs jl where jl.qsSfz='"+qsSfz.trim()+"'"+" and jl.frNo='"+jlQs.getFrNo()+"'";
		 		List list=hds.searchAll(hql);
		 		if(list.size()>0){
				 	JSONArray json=JSONArray.fromObject(-1);
				 	response.getWriter().println(json.toString());
		 		}else{
		 			if(!qsCard.trim().equals("")){
			 			String hql1="from JlQs where qsCard='"+qsCard+"'";
						List<JlQs> jlQslist1=hds.searchAll(hql1);
						if(jlQslist1.size()>0){
							for(int i=0;i<jlQslist1.size();i++){
								jlQslist1.get(i).setQsCard("");
								hds.update(jlQslist1.get(i));
							}
						}
			 			jlQs.setQsCard(qsCard);
			 		}
		 			jlQs.setQsSfz(qsSfz.trim());
				 	if(!qsName.equals("")){
				 		jlQs.setQsName(qsName.trim());
				 	}else{
				 		jlQs.setQsName("");
				 	}
				 	jlQs.setQsSfz(qsSfz.trim());
				 	if(!qsCard.equals("")){
				 		if(cardType!=null && cardType.equals("1") && !jlQs.getQsCard().equals(qsCard)){
				 	    	JlTradeCardInfo jlTradeCardInfo=new JlTradeCardInfo();
				 	    	jlTradeCardInfo.setCardNum(qsCard);
				 	    	jlTradeCardInfo.setQsName(qsName);
				 	    	jlTradeCardInfo.setFrNo(jlQs.getFrNo());
				 	    	jlTradeCardInfo.setOpName(user.getUserName());
				 	    	jlTradeCardInfo.setSj(now1);
				 	    	jlTradeCardInfo.setFrName(frName);
				 	    	jlTradeCardInfo.setJqName(jqNo1);
				 	    	hds.save(jlTradeCardInfo);
				 	    }
				 		jlQs.setQsCard(qsCard);
				 	}else{
				 		jlQs.setQsCard("");
				 	}
				 	if(!dz.equals("")){
				 		jlQs.setDz(dz.trim());
				 	}else{
				 		jlQs.setDz("");
				 	}
				 	if(!tele.equals("")){
				 		jlQs.setTele(tele);
				 	}else{
				 		jlQs.setTele("");
				 	}
				 	if(!zjlb.equals("null")){
				 		jlQs.setQsZjlb(Integer.parseInt(zjlb));
				 	}
				 	if(!qsSfzWlh.equals("")){
				 		jlQs.setQsSfzWlh(qsSfzWlh.trim());
				 	}else{
				 		jlQs.setQsSfzWlh("");
				 	}
				 	if(!bz.equals("")){
				 		jlQs.setBz(bz.trim());
				 	}else{
				 		jlQs.setBz("");
				 	}
				 	
				 	String hql8="from SysUser where userNo='"+user.getUserNo()+"'";
					List<SysUser> list8=hds.searchAll(hql8);
					SysUser sysUser=(SysUser)list8.get(0);
					if(!spState.equals("null")){
						if(sysUser.getIsSp()==1){
							jlQs.setSpState(Integer.parseInt(spState));
//					 		jlQs.setSpUserNo(user.getUserNo());
//					 		jlQs.setSpTime(now1);
						}else{
							jlQs.setSpState(Integer.parseInt(spState));
						}				 		
				 	}else{
				 		jlQs.setSpState(0);
				 	}
					
				 	jlQs.setGx(gx);
				 	if(!photoAddress.trim().equals("")){
				 		String khdIp=request.getRemoteAddr();
				 		String url1="http://"+khdIp+":9009/"+photoAddress.replace(":", "").replace("\\", "/");
				 		URL url=new URL(url1);
				 		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				 		if(connection.getResponseCode()==200){
				 			InputStream fis = url.openStream();
				 			Thread.sleep(1000);
				 			byte[] byte1 = new byte[fis.available()];
				 			fis.read(byte1);
				 			jlQs.setZp(byte1);
				 		}
				 	}
				 	if(jz.length()>2){
				 		sun.misc.BASE64Decoder decode=new sun.misc.BASE64Decoder();
					 	byte[] datas=decode.decodeBuffer(jz.substring(1, jz.length()-2));
					 	jlQs.setJz(datas);
				 	}
				 	jlQs.setXb(xb);
				 	if((jlQs.getQsName()!=null && !jlQs.getQsName().equals(qsName)) || (jlQs.getTele()!=null && !jlQs.getTele().equals(tele.trim()))){
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
						sl.setModel("罪犯管理");
						str2.append("罪犯编号为"+jlQs.getFrNo()+"的亲属身份证为"+jlQs.getQsSfz()+"的亲属");
						if(jlQs.getQsName()!=null && !jlQs.getQsName().equals(qsName)){
							str2.append(" 亲属姓名由"+jlQs.getQsName()+"修改为"+qsName);
						}
						if(jlQs.getTele()!=null && !jlQs.getTele().equals(tele.trim())){
							str2.append(" 亲属电话由"+jlQs.getTele()+"修改为"+tele);
						}
						if(jlQs.getSpState()!=null && !jlQs.getSpState().equals(spState)){
							str2.append(" 审批状态由"+jlQs.getSpState()+"修改为"+spState);
						}
						sl.setInfo(str2.toString());
					    sl.setOp("修改罪犯亲属信息");
					    hds.save(sl);
				 	}
				 	//将身份证照片传输至服务器指定文件夹
				 	String zpUrl = "http://"+request.getRemoteAddr()+":9009/C/"+qsSfz+".Jpg";
			 	    String zpName = qsSfz+".Jpg";
			 	    
			 	    boolean a = isNetFileAvailable(zpUrl);
//				 	System.out.println(a);				 	
				 	if(!a){
//			            throw new RuntimeException("文件不存在..");
				 		System.out.println("身份证图片文件不存在");
				 	
				 	}else{
				 		download(zpUrl,zpName,"D:\\Photo\\");
				 	    String zpUrl1="";
				 	    
				 	    String hql5="from SysParam where paramName='HJ_Client3'";
						List<SysParam> list5=hds.searchAll(hql5);
						SysParam sysParam1=(SysParam)list5.get(0);						
						zpUrl1=sysParam1.getParamData3()+qsSfz+".Jpg";
						jlQs.setZpUrl(zpUrl1);
				 	}
				 	hds.update(jlQs);
				 	JSONArray json=JSONArray.fromObject(0);
				 	response.getWriter().println(json.toString());
		 		}
		 	}else{
		 		if(jlQs.getQsSfz()==null){
		 			String hql="from JlQs jl where jl.qsSfz='"+qsSfz.trim()+"'"+" and jl.frNo='"+jlQs.getFrNo()+"'";
			 		List list=hds.searchAll(hql);
			 		if(list.size()>0){
					 	JSONArray json=JSONArray.fromObject(-1);
					 	response.getWriter().println(json.toString());
			 		}else{
			 			if(!qsCard.trim().equals("")){
				 			String hql1="from JlQs where qsCard='"+qsCard+"'";
							List<JlQs> jlQslist1=hds.searchAll(hql1);
							if(jlQslist1.size()>0){
								for(int i=0;i<jlQslist1.size();i++){
									jlQslist1.get(i).setQsCard("");
									hds.update(jlQslist1.get(i));
								}
							}
				 			jlQs.setQsCard(qsCard);
				 		}
			 			jlQs.setQsSfz(qsSfz.trim());
					 	if(!qsName.equals("")){
					 		jlQs.setQsName(qsName.trim());
					 	}else{
					 		jlQs.setQsName("");
					 	}
					 	jlQs.setQsSfz(qsSfz.trim());
					 	if(!qsCard.equals("")){
					 		if(cardType!=null && cardType.equals("1") && !jlQs.getQsCard().equals(qsCard)){
					 	    	JlTradeCardInfo jlTradeCardInfo=new JlTradeCardInfo();
					 	    	jlTradeCardInfo.setCardNum(qsCard);
					 	    	jlTradeCardInfo.setQsName(qsName);
					 	    	jlTradeCardInfo.setFrNo(jlQs.getFrNo());
					 	    	jlTradeCardInfo.setOpName(user.getUserName());
					 	    	jlTradeCardInfo.setSj(now1);
					 	    	jlTradeCardInfo.setFrName(frName);
					 	    	jlTradeCardInfo.setJqName(jqNo1);
					 	    	hds.save(jlTradeCardInfo);
					 	    }
					 		jlQs.setQsCard(qsCard);
					 	}else{
					 		jlQs.setQsCard("");
					 	}
					 	if(!dz.equals("")){
					 		jlQs.setDz(dz.trim());
					 	}else{
					 		jlQs.setDz("");
					 	}
					 	if(!tele.equals("")){
					 		jlQs.setTele(tele);
					 	}else{
					 		jlQs.setTele("");
					 	}
					 	if(!zjlb.equals("null")){
					 		jlQs.setQsZjlb(Integer.parseInt(zjlb));
					 	}
					 	if(!qsSfzWlh.equals("")){
					 		jlQs.setQsSfzWlh(qsSfzWlh.trim());
					 	}else{
					 		jlQs.setQsSfzWlh("");
					 	}
					 	if(!bz.equals("")){
					 		jlQs.setBz(bz.trim());
					 	}else{
					 		jlQs.setBz("");
					 	}
					 	
					 	String hql8="from SysUser where userNo='"+user.getUserNo()+"'";
						List<SysUser> list8=hds.searchAll(hql8);
						SysUser sysUser=(SysUser)list8.get(0);
						if(!spState.equals("null")){
							if(sysUser.getIsSp()==1){
								jlQs.setSpState(Integer.parseInt(spState));
//						 		jlQs.setSpUserNo(user.getUserNo());
//						 		jlQs.setSpTime(now1);
							}else{
								jlQs.setSpState(Integer.parseInt(spState));
							}				 		
					 	}else{
					 		jlQs.setSpState(0);
					 	}
						
					 	jlQs.setGx(gx);
					 	if(!photoAddress.trim().equals("")){
					 		String khdIp=request.getRemoteAddr();
					 		String url1="http://"+khdIp+":9009/"+photoAddress.replace(":", "").replace("\\", "/");
					 		URL url=new URL(url1);
					 		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					 		if(connection.getResponseCode()==200){
					 			InputStream fis = url.openStream();
					 			Thread.sleep(1000);
					 			byte[] byte1 = new byte[fis.available()];
					 			fis.read(byte1);
					 			jlQs.setZp(byte1);
					 		}
					 	}
					 	if(jz.length()>2){
					 		sun.misc.BASE64Decoder decode=new sun.misc.BASE64Decoder();
						 	byte[] datas=decode.decodeBuffer(jz.substring(1, jz.length()-2));
						 	jlQs.setJz(datas);
					 	}
					 	jlQs.setXb(xb);
					 	if((jlQs.getQsName()!=null && !jlQs.getQsName().equals(qsName)) || (jlQs.getTele()!=null && !jlQs.getTele().equals(tele.trim()))){
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
							sl.setModel("罪犯管理");
							str2.append("罪犯编号为"+jlQs.getFrNo()+"的亲属身份证为"+jlQs.getQsSfz()+"的亲属");
							if(jlQs.getQsName()!=null && !jlQs.getQsName().equals(qsName)){
								str2.append(" 亲属姓名由"+jlQs.getQsName()+"修改为"+qsName);
							}
							if(jlQs.getTele()!=null && !jlQs.getTele().equals(tele.trim())){
								str2.append(" 亲属电话由"+jlQs.getTele()+"修改为"+tele);
							}
							if(jlQs.getSpState()!=null && !jlQs.getSpState().equals(spState)){
								str2.append(" 审批状态由"+jlQs.getSpState()+"修改为"+spState);
							}
							sl.setInfo(str2.toString());
						    sl.setOp("修改罪犯亲属信息");
						    hds.save(sl);
					 	}
					 	//将身份证照片传输至服务器指定文件夹
					 	String zpUrl = "http://"+request.getRemoteAddr()+":9009/C/"+qsSfz+".Jpg";
				 	    String zpName = qsSfz+".Jpg";
				 	    
				 	    boolean a = isNetFileAvailable(zpUrl);
//					 	System.out.println(a);				 	
					 	if(!a){
//				            throw new RuntimeException("文件不存在..");
					 		System.out.println("身份证图片文件不存在");
					 	
					 	}else{
					 		download(zpUrl,zpName,"D:\\Photo\\");
					 	    String zpUrl1="";
					 	    
					 	    String hql5="from SysParam where paramName='HJ_Client3'";
							List<SysParam> list5=hds.searchAll(hql5);
							SysParam sysParam1=(SysParam)list5.get(0);						
							zpUrl1=sysParam1.getParamData3()+qsSfz+".Jpg";
							jlQs.setZpUrl(zpUrl1);
					 	}
					 	hds.update(jlQs);
					 	JSONArray json=JSONArray.fromObject(0);
					 	response.getWriter().println(json.toString());
					
			 		}
		 		}else{
		 			if(!qsCard.trim().equals("")){
			 			String hql1="from JlQs where qsCard='"+qsCard+"'";
						List<JlQs> jlQslist1=hds.searchAll(hql1);
						if(jlQslist1.size()>0){
							for(int i=0;i<jlQslist1.size();i++){
								jlQslist1.get(i).setQsCard("");
								hds.update(jlQslist1.get(i));
							}
						}
			 			jlQs.setQsCard(qsCard);
			 		}
		 			if(!qsName.equals("")){
				 		jlQs.setQsName(qsName.trim());
				 	}else{
				 		jlQs.setQsName("");
				 	}
				 	if(!qsCard.equals("")){
				 		if(cardType!=null && cardType.equals("1") && !jlQs.getQsCard().equals(qsCard)){
				 	    	JlTradeCardInfo jlTradeCardInfo=new JlTradeCardInfo();
				 	    	jlTradeCardInfo.setCardNum(qsCard);
				 	    	jlTradeCardInfo.setQsName(qsName);
				 	    	jlTradeCardInfo.setFrNo(jlQs.getFrNo());
				 	    	jlTradeCardInfo.setOpName(user.getUserName());
				 	    	jlTradeCardInfo.setSj(now1);
				 	    	jlTradeCardInfo.setFrName(frName);
				 	    	jlTradeCardInfo.setJqName(jqNo1);
				 	    	hds.save(jlTradeCardInfo);
				 	    }
				 		jlQs.setQsCard(qsCard);
				 	}else{
				 		jlQs.setQsCard("");
				 	}
				 	if(!dz.equals("")){
				 		jlQs.setDz(dz.trim());
				 	}else{
				 		jlQs.setDz("");
				 	}
				 	if(!tele.equals("")){
				 		jlQs.setTele(tele);
				 	}else{
				 		jlQs.setTele("");
				 	}
				 	if(!zjlb.equals("null")){
				 		jlQs.setQsZjlb(Integer.parseInt(zjlb));
				 	}
				 	if(!qsSfzWlh.equals("")){
				 		jlQs.setQsSfzWlh(qsSfzWlh.trim());
				 	}else{
				 		jlQs.setQsSfzWlh("");
				 	}
				 	if(!bz.equals("")){
				 		jlQs.setBz(bz.trim());
				 	}else{
				 		jlQs.setBz("");
				 	}
				 	
				 	String hql8="from SysUser where userNo='"+user.getUserNo()+"'";
					List<SysUser> list8=hds.searchAll(hql8);
					SysUser sysUser=(SysUser)list8.get(0);
					if(!spState.equals("null")){
						if(sysUser.getIsSp()==1){
							jlQs.setSpState(Integer.parseInt(spState));
//					 		jlQs.setSpUserNo(user.getUserNo());
//					 		jlQs.setSpTime(now1);
						}else{
							jlQs.setSpState(Integer.parseInt(spState));
						}				 		
				 	}else{
				 		jlQs.setSpState(0);
				 	}
					
				 	jlQs.setGx(gx);
				 	if(!photoAddress.trim().equals("")){
				 		String khdIp=request.getRemoteAddr();
				 		String url1="http://"+khdIp+":9009/"+photoAddress.replace(":", "").replace("\\", "/");
				 		URL url=new URL(url1);
				 		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				 		if(connection.getResponseCode()==200){
				 			InputStream fis = url.openStream();
				 			Thread.sleep(1000);
				 			byte[] byte1 = new byte[fis.available()];
				 			fis.read(byte1);
				 			jlQs.setZp(byte1);
				 		}
				 	}
				 	if(jz.length()>2){
				 		sun.misc.BASE64Decoder decode=new sun.misc.BASE64Decoder();
					 	byte[] datas=decode.decodeBuffer(jz.substring(1, jz.length()-2));
					 	jlQs.setJz(datas);
//					 	String savePath=request.getRealPath("/")+"images/";
//						String filename=String.valueOf(System.currentTimeMillis())+".jpg";
//						File file=new File(savePath+filename);
//						OutputStream fos=new FileOutputStream(file);
//						System.out.println("图片文件名称:"+filename);
//						fos.write(datas);
//						fos.close();
				 	}
				 	jlQs.setXb(xb);
				 	Object[] obj={Integer.parseInt(qsId)};
				 	if((jlQs.getQsName()!=null && !jlQs.getQsName().equals(qsName)) || (jlQs.getTele()!=null && !jlQs.getTele().equals(tele.trim()))){
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
						sl.setModel("罪犯管理");
						str2.append("罪犯编号为"+jlQs.getFrNo()+"的亲属身份证为"+jlQs.getQsSfz()+"的亲属");
						if(jlQs.getQsName()!=null && !jlQs.getQsName().equals(qsName)){
							str2.append(" 亲属姓名由"+jlQs.getQsName()+"修改为"+qsName);
						}
						if(jlQs.getTele()!=null && !jlQs.getTele().equals(tele.trim())){
							str2.append(" 亲属电话由"+jlQs.getTele()+"修改为"+tele);
						}
						if(jlQs.getSpState()!=null && !jlQs.getSpState().equals(spState)){
							str2.append(" 审批状态由"+jlQs.getSpState()+"修改为"+spState);
						}
						sl.setInfo(str2.toString());
					    sl.setOp("修改罪犯亲属信息");
					    hds.save(sl);
				 	}
				 	//将身份证照片传输至服务器指定文件夹
				 	String zpUrl = "http://"+request.getRemoteAddr()+":9009/C/"+qsSfz+".Jpg";
			 	    String zpName = qsSfz+".Jpg";
			 	    
			 	    boolean a = isNetFileAvailable(zpUrl);
//				 	System.out.println(a);				 	
				 	if(!a){
//			            throw new RuntimeException("文件不存在..");
				 		System.out.println("身份证图片文件不存在");
				 	
				 	}else{
				 		download(zpUrl,zpName,"D:\\Photo\\");
				 	    String zpUrl1="";
				 	    
				 	    String hql5="from SysParam where paramName='HJ_Client3'";
						List<SysParam> list5=hds.searchAll(hql5);
						SysParam sysParam1=(SysParam)list5.get(0);						
						zpUrl1=sysParam1.getParamData3()+qsSfz+".Jpg";
						jlQs.setZpUrl(zpUrl1);
				 	}
				 	hds.update(jlQs);
				 	JSONArray json=JSONArray.fromObject(0);
				 	response.getWriter().println(json.toString());
		 		}
		 		
		 	}
		 	return null;
	}
	//远程更新亲属数据
	public ActionForward updateDownQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String returnName="updateDownSuccess";
			String frNo = request.getParameter("frNo");
			int i=hds.getLineNo("import_qs", frNo);
			if(i==0 || i==-1){
				returnName="updateDownError";
			}
			request.setAttribute("frNo", frNo);
			return mapping.findForward(returnName);
	}
	//直连数据库模式
	public ActionForward updateDownQs1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String frNo = request.getParameter("frNo");
			int i=hds.getLineNo("import_qs", frNo);
			if(i>0){
				JSONArray json=JSONArray.fromObject(i);
			 	response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(-1);
			 	response.getWriter().println(json.toString());
			}
			return null;
	}
	//webservice模式
//	public ActionForward updateDownQs1(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//			SysUser user=(SysUser)request.getSession().getAttribute("users");
//			if(user==null){
//				return mapping.findForward("sessionFailure");
//			}
//			int a = 0;
//			try {
//				String frNo = request.getParameter("frNo");
//				System.out.println("访问webservice接口开始。。。。。");
//				Service service = new Service();
//				RtInterface rtInterface = service.getRtInterfaceImpPort();
//												
//				System.out.println("访问webservice接口成功。。。。。");
//				String bh = "@bh='"+frNo+"'";					
//				String zfshgxdg = rtInterface.invoke("unitop","unitop","zf_shgx_dg",bh);
//				System.out.println("开始处理罪犯家属数据");
//				Document doc2= DocumentHelper.parseText(zfshgxdg);
//				Element rootElement2 = doc2.getRootElement();
//				Element root2 = doc2.getRootElement();
//				List attrList2 = root2.attributes();
//				Attribute item3 = (Attribute)attrList2.get(0);
//				System.out.println("获取到家属根节点判断登录成功属性"+item3.getName()+"="+item3.getValue());
//				String code2 = item3.getValue();
//				if(!code2.equals("0")){
//					Attribute item4 = (Attribute)attrList2.get(1);
//					System.out.println("获取到根节点登录失败原因"+item4.getName()+"="+item4.getValue());
//					List<ErrorInfoWebServiceVO> error = new ArrayList<ErrorInfoWebServiceVO>();
//					ErrorInfoWebServiceVO errorInfoWebService = new ErrorInfoWebServiceVO();
//					errorInfoWebService.setCause(item4.getValue());
//					error.add(errorInfoWebService);
//					ErrorInfoWebService errorInfo = new ErrorInfoWebService();
//					errorInfo.setActionName("updateDownQs1");
//					errorInfo.setErrorInfo(item4.getValue());
//					Timestamp now1=new Timestamp(System.currentTimeMillis());
//					errorInfo.setCreateTime(now1);
//					hds.save(errorInfo);
//					response.setCharacterEncoding("utf-8");
//					JSONArray json=JSONArray.fromObject(error);
//					response.getWriter().println(json.toString());
//					
//					return null;
//				}
//				
//				List<Element> shgx1 = rootElement2.elements("zf_shgx_dg");
//				
//				for (int i = 0; i < shgx1.size(); i++) {
//					Element shgx = shgx1.get(i);
//					String bhss = shgx.element("bh").getText();
//					String xmss = shgx.element("xm").getText();						
//					String hql111 = "from JlQs where frNo="+ frNo + " and qsName='" + xmss+ "'";
//					List<JlQs> list1 = hds.searchAll(hql111);
//					if(list1.size()>0){
//						
//					}else{
//						JlQs jlQs = new JlQs();
//						jlQs.setFrNo(shgx.element("bh").getText());
//						jlQs.setQsName(shgx.element("xm").getText());
//						if(shgx.element("zjbm").getText() != null && !shgx.element("zjbm").getText().equals("null")){
//							jlQs.setQsSfz(shgx.element("zjbm").getText());
//						}else{
//							jlQs.setQsSfz(null);
//						}
//						if(shgx.element("gx").getText() != null && !shgx.element("gx").getText().equals("null")){
//							jlQs.setGx(shgx.element("gx").getText());
//						}
//						if(shgx.element("jtqh").getText() != null && !shgx.element("jtqh").getText().equals("null")){
//							jlQs.setDz(shgx.element("jtqh").getText());
//						}
//						if(shgx.element("dh").getText() != null && !shgx.element("dh").getText().equals("null")){
//							jlQs.setTele(shgx.element("dh").getText());
//						}
//						if(shgx.element("dh").getText() != null && !shgx.element("dh").getText().equals("null")){
//							jlQs.setTele(shgx.element("dh").getText());
//						}
//						jlQs.setSpState(1);
//						jlQs.setFaceState(0);
//						Timestamp now1=new Timestamp(System.currentTimeMillis());
//						jlQs.setCreateTime(now1);
//						jlQs.setQsZjlb(1);
//						a++;						
//						hds.save(jlQs);
//					}
//					
//				}				
//					
//		
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println("处理数据结束");
//			JSONArray json=JSONArray.fromObject(a);
//	 	    response.getWriter().println(json.toString());
//			return null;
//	}
	
	//添加保存会见登记，不验证家属卡号
	public ActionForward addSaveHjdj2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String selectFrId=request.getParameter("selectFrId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjsc=request.getParameter("hjsc");
			String hjsm = java.net.URLDecoder.decode((String)request.getParameter("hjsm"),"UTF-8");
			String hjType=request.getParameter("hjType");
			//String monitor = java.net.URLDecoder.decode((String)request.getParameter("monitor"),"UTF-8");
			String callNo=request.getParameter("callNo");
			//审批
			String tpQs=request.getParameter("tpQs");
			String qzSp=request.getParameter("qzSp");
			
			boolean flag=true;
			JlHjDj jlHjDj= new JlHjDj();
			String hql="from JlFr where frNo='"+selectFrId+"'";
			String hql3="from JlHjDj where frNo='"+selectFrId+"' and State=0";
			List<JlFr> list=hds.searchAll(hql);
			List<JlHjDj> list3=hds.searchAll(hql3);
			if(list.size()>0 && list3.size()==0){
				JlFr jlFr=(JlFr)list.get(0);
				if(hjType.equals("2")){
					jlHjDj.setJy("Server3");
				}else{
					jlHjDj.setJy(jlFr.getJy());
				}
				//jlHjDj.setJy(jlFr.getJy());
				jlHjDj.setFrNo(jlFr.getFrNo());
				if(jlFr.getFrName()!=null){
					jlHjDj.setFrName(jlFr.getFrName());
				}
				jlHjDj.setJqNo(jlFr.getJq());
				String hql1="from JlJq where jqNo='"+jlFr.getJq()+"'";
				List<JlJq> list1=hds.searchAll(hql1);
				if(list1.size()>0){
					JlJq jlJq=list1.get(0);
					if(jlJq.getJqName()!=null){
						jlHjDj.setJqName(jlJq.getJqName());
					}
				}else{
					flag=false;
				}
			}else{
				flag=false;
			}
			if(flag){
				if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
									if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
										JSONArray json=JSONArray.fromObject(-5);
										response.getWriter().println(json.toString());
										return null;
									}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard1(jlQs.getQsCard());
								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard2(jlQs.getQsCard());
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard3(jlQs.getQsCard());
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard4(jlQs.getQsCard());
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard5(jlQs.getQsCard());
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard6(jlQs.getQsCard());
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard7(jlQs.getQsCard());
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard8(jlQs.getQsCard());
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard9(jlQs.getQsCard());
								}
							}
							break;
						default:
							break;
					}
				}
				}
				jlHjDj.setHjType(Integer.parseInt(hjType));
				if(jlHjDj.getHjType()==1){
					jlHjDj.setHjTime(Integer.parseInt(hjsc)*60);
				}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
					jlHjDj.setHjTime(30*60);
				}else{
					jlHjDj.setHjTime(180*60);
				}
				
				if(!hjsm.trim().equals("")){
					jlHjDj.setHjInfo(hjsm.trim());
				}
				
				jlHjDj.setDjUser(user.getUserNo());
				Timestamp now=new Timestamp(System.currentTimeMillis());
				jlHjDj.setDjTime(now);
				jlHjDj.setFpFlag(0);
				jlHjDj.setFpTzfrFlag(0);
				jlHjDj.setFpTzqsFlag(0);
				jlHjDj.setHjOrder(Integer.parseInt(callNo));
				jlHjDj.setMonitorFlag("");
				jlHjDj.setPageTzState(0);
				String hql10="from SysParam where paramName='HJ_Client1'";
				List<SysParam> list10=hds.searchAll(hql10);
				if(list10.size()>0){
					SysParam sysParam=list10.get(0);
					if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
						jlHjDj.setDjType(1);
					}else{
						jlHjDj.setDjType(0);
					}
				}else{
					jlHjDj.setDjType(0);
					
				}
				jlHjDj.setState(0);
				jlHjDj.setHjIndex(hds.getIndex("get_hj_index", jlHjDj.getFrNo()));
				jlHjDj.setImportFlag(0);
				jlHjDj.setTpQsNum(Integer.parseInt(tpQs));
				jlHjDj.setQzSp(Integer.parseInt(qzSp.trim()));
				hds.save(jlHjDj);
				
				int result=hds.getsptj("HJSP",jlHjDj.getHjid());
				//要审批
				List<HJSPQS> listsp = new ArrayList<HJSPQS>();
				if(result==1){
					HJSPQS hj =new HJSPQS();
					String sql3="select SP_Reason,HJID from JL_HJ_SP where HJID="+jlHjDj.getHjid();
					List list1 = hds.searchAllBySql(sql3);
					if(list1.size()>0){
						Object[] obj =(Object[]) list1.get(0);
						if(obj[0]!=null && !obj[0].toString().equals("")){
							hj.setZzzReason(obj[0].toString());
						}
					}
					String sql1="update JL_HJ_DJ set state=3 where HJID="+jlHjDj.getHjid();
					hds.executeUpdate(sql1);
					
					hj.setZzz(-6);
					hj.setZzzhjid(new Long(jlHjDj.getHjid()).intValue());
					listsp.add(hj);
					response.setContentType("text/json; charset=utf-8"); 
					JSONArray json=JSONArray.fromObject(listsp);
					response.getWriter().println(json.toString());
					return null;
				}
				
				JSONArray json=JSONArray.fromObject(jlHjDj.getHjid());
				response.getWriter().println(json.toString());
				
//				String hql1="from SysHjLine where state=1 and hjstate=0 and hjid is null ";
//				List<SysHjLine> list1=hds.searchAll(hql1);
//				if(list1.size()>0){
//					hds.save(jlHjDj);
//					int result=hds.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
////					int result=hds.getLineNo("set_ZW_ex",jlHjDj.getFrNo());
//					if(result==0){
//						List list2=hds.searchAll("from JlHjDj where state=0 and frNo='"+jlHjDj.getFrNo()+"'");
//						if(list2.size()>0){
//							JlHjDj jlHjDj1=(JlHjDj)list2.get(0);
//							System.out.println(jlHjDj1.getHjid());
//							JSONArray json=JSONArray.fromObject(jlHjDj1.getHjid());
//							response.getWriter().println(json.toString());
//						}else{
//							JSONArray json=JSONArray.fromObject(0);
//							response.getWriter().println(json.toString());
//						}
//						
//					}else{
//						JSONArray json=JSONArray.fromObject(-2);
//						response.getWriter().println(json.toString());
//					}
//				}else{
//					JSONArray json=JSONArray.fromObject(-2);
//					response.getWriter().println(json.toString());
//				}
//				JSONArray json=JSONArray.fromObject(-1);
//				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}
			Thread.sleep(1000);
			return null;
	}
	//添加保存会见登记，不验证家属卡号（辽宁沈阳第一监狱）
	public ActionForward addSaveHjdj1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String selectFrId=request.getParameter("selectFrId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjsc=request.getParameter("hjsc");
			String hjsm = java.net.URLDecoder.decode((String)request.getParameter("hjsm"),"UTF-8");
			String hjType=request.getParameter("hjType");
			//String monitor = java.net.URLDecoder.decode((String)request.getParameter("monitor"),"UTF-8");
			String callNo=request.getParameter("callNo");
			//审批
			String tpQs=request.getParameter("tpQs");
			String qzSp=request.getParameter("qzSp");
			
			boolean flag=true;
			JlHjDj jlHjDj= new JlHjDj();
			String hql="from JlFr where frNo='"+selectFrId+"'";
			String hql3="from JlHjDj where frNo='"+selectFrId+"' and State=0";
			List<JlFr> list=hds.searchAll(hql);
			List<JlHjDj> list3=hds.searchAll(hql3);
			if(list.size()>0 && list3.size()==0){
				JlFr jlFr=(JlFr)list.get(0);
				if(hjType.equals("2")){
					jlHjDj.setJy("Server3");
				}else{
					jlHjDj.setJy(jlFr.getJy());
				}
				//jlHjDj.setJy(jlFr.getJy());
				jlHjDj.setFrNo(jlFr.getFrNo());
				if(jlFr.getFrDah()!=null){
					jlHjDj.setFrDah(jlFr.getFrDah());
				}
				if(jlFr.getFrName()!=null){
					jlHjDj.setFrName(jlFr.getFrName());
				}
				jlHjDj.setJqNo(jlFr.getJq());
				String hql1="from JlJq where jqNo='"+jlFr.getJq()+"'";
				List<JlJq> list1=hds.searchAll(hql1);
				if(list1.size()>0){
					JlJq jlJq=list1.get(0);
					if(jlJq.getJqName()!=null){
						jlHjDj.setJqName(jlJq.getJqName());
					}
				}else{
					flag=false;
				}
			}else{
				flag=false;
			}
			if(flag){
				if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
										JSONArray json=JSONArray.fromObject(-5);
										response.getWriter().println(json.toString());
										return null;
									}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard1(jlQs.getQsCard());
								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard2(jlQs.getQsCard());
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard3(jlQs.getQsCard());
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard4(jlQs.getQsCard());
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard5(jlQs.getQsCard());
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard6(jlQs.getQsCard());
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard7(jlQs.getQsCard());
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard8(jlQs.getQsCard());
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard9(jlQs.getQsCard());
								}
							}
							break;
						default:
							break;
					}
				}
				}
				jlHjDj.setHjType(Integer.parseInt(hjType));
				if(jlHjDj.getHjType()==1){
					jlHjDj.setHjTime(Integer.parseInt(hjsc)*60);
				}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
					jlHjDj.setHjTime(30*60);
				}else{
					jlHjDj.setHjTime(180*60);
				}
				
				if(!hjsm.trim().equals("")){
					jlHjDj.setHjInfo(hjsm.trim());
				}
				
				jlHjDj.setDjUser(user.getUserNo());
				Timestamp now=new Timestamp(System.currentTimeMillis());
				jlHjDj.setDjTime(now);
				jlHjDj.setFpFlag(0);
				jlHjDj.setFpTzfrFlag(0);
				jlHjDj.setFpTzqsFlag(0);
				jlHjDj.setHjOrder(Integer.parseInt(callNo));
				jlHjDj.setMonitorFlag("");
				jlHjDj.setPageTzState(0);
				String hql10="from SysParam where paramName='HJ_Client1'";
				List<SysParam> list10=hds.searchAll(hql10);
				if(list10.size()>0){
					SysParam sysParam=list10.get(0);
					if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
						jlHjDj.setDjType(1);
					}else{
						jlHjDj.setDjType(0);
					}
				}else{
					jlHjDj.setDjType(0);
					
				}
				jlHjDj.setState(0);
				jlHjDj.setHjIndex(hds.getIndex("get_hj_index", jlHjDj.getFrNo()));
				jlHjDj.setImportFlag(0);
				jlHjDj.setTpQsNum(Integer.parseInt(tpQs));
				jlHjDj.setQzSp(Integer.parseInt(qzSp.trim()));
				hds.save(jlHjDj);
				
				int result=hds.getsptj("HJSP",jlHjDj.getHjid());
				//要审批
				List<HJSPQS> listsp = new ArrayList<HJSPQS>();
				if(result==1){
					HJSPQS hj =new HJSPQS();
					String sql3="select SP_Reason,HJID from JL_HJ_SP where HJID="+jlHjDj.getHjid();
					List list1 = hds.searchAllBySql(sql3);
					if(list1.size()>0){
						Object[] obj =(Object[]) list1.get(0);
						if(obj[0]!=null && !obj[0].toString().equals("")){
							hj.setZzzReason(obj[0].toString());
						}
					}
					String sql1="update JL_HJ_DJ set state=3 where HJID="+jlHjDj.getHjid();
					hds.executeUpdate(sql1);
					
					hj.setZzz(-6);
					hj.setZzzhjid(new Long(jlHjDj.getHjid()).intValue());
					listsp.add(hj);
					response.setContentType("text/json; charset=utf-8"); 
					JSONArray json=JSONArray.fromObject(listsp);
					response.getWriter().println(json.toString());
					return null;
				}
				
				JSONArray json=JSONArray.fromObject(jlHjDj.getHjid());
				response.getWriter().println(json.toString());
				
//				String hql1="from SysHjLine where state=1 and hjstate=0 and hjid is null ";
//				List<SysHjLine> list1=hds.searchAll(hql1);
//				if(list1.size()>0){
//					hds.save(jlHjDj);
//					int result=hds.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
////					int result=hds.getLineNo("set_ZW_ex",jlHjDj.getFrNo());
//					if(result==0){
//						List list2=hds.searchAll("from JlHjDj where state=0 and frNo='"+jlHjDj.getFrNo()+"'");
//						if(list2.size()>0){
//							JlHjDj jlHjDj1=(JlHjDj)list2.get(0);
//							System.out.println(jlHjDj1.getHjid());
//							JSONArray json=JSONArray.fromObject(jlHjDj1.getHjid());
//							response.getWriter().println(json.toString());
//						}else{
//							JSONArray json=JSONArray.fromObject(0);
//							response.getWriter().println(json.toString());
//						}
//						
//					}else{
//						JSONArray json=JSONArray.fromObject(-2);
//						response.getWriter().println(json.toString());
//					}
//				}else{
//					JSONArray json=JSONArray.fromObject(-2);
//					response.getWriter().println(json.toString());
//				}
//				JSONArray json=JSONArray.fromObject(-1);
//				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}
			Thread.sleep(1000);
			return null;
	}
	//要验证家属身份证但不验证卡号（广东高明/云南第一戒毒所/广西女子监狱）
	public ActionForward addSaveHjdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String selectFrId=request.getParameter("selectFrId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjsc=request.getParameter("hjsc");
			String hjsm = java.net.URLDecoder.decode((String)request.getParameter("hjsm"),"UTF-8");
			String hjType=request.getParameter("hjType");
//			String monitor = java.net.URLDecoder.decode((String)request.getParameter("monitor"),"UTF-8");
			String callNo=request.getParameter("callNo");
			//审批
			String tpQs=request.getParameter("tpQs");
			String qzSp=request.getParameter("qzSp");
			
			boolean flag=true;
			JlHjDj jlHjDj= new JlHjDj();
			String hql="from JlFr where frNo='"+selectFrId+"'";
			String hql3="from JlHjDj where frNo='"+selectFrId+"' and (State=0 or State=3)";
			List<JlFr> list=hds.searchAll(hql);
			List<JlHjDj> list3=hds.searchAll(hql3);
			if(list.size()>0 && list3.size()==0){
				JlFr jlFr=(JlFr)list.get(0);
				jlHjDj.setJy(jlFr.getJy());
				jlHjDj.setFrNo(jlFr.getFrNo());
				if(jlFr.getFrName()!=null){
					jlHjDj.setFrName(jlFr.getFrName());
				}
				jlHjDj.setJqNo(jlFr.getJq());
				String hql1="from JlJq where jqNo='"+jlFr.getJq()+"'";
				List<JlJq> list1=hds.searchAll(hql1);
				if(list1.size()>0){
					JlJq jlJq=list1.get(0);
					if(jlJq.getJqName()!=null){
						jlHjDj.setJqName(jlJq.getJqName());
					}
				}else{
					flag=false;
				}
			}else{
				flag=false;
			}
			
			List<FrQsToABDoor> listFrQsToABDoor = new ArrayList<FrQsToABDoor>();
			
			if(flag){
				if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard1(jlQs.getQsCard());

								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard2(jlQs.getQsCard());
									
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard3(jlQs.getQsCard());
									
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard4(jlQs.getQsCard());
									
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard5(jlQs.getQsCard());
									
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard6(jlQs.getQsCard());
									
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard7(jlQs.getQsCard());
									
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard8(jlQs.getQsCard());
									
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);

								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard9(jlQs.getQsCard());
									
								}
							}
							break;
						default:
							break;
					}
				}
				}
				jlHjDj.setHjType(Integer.parseInt(hjType));
				if(jlHjDj.getHjType()==1){
					jlHjDj.setHjTime(Integer.parseInt(hjsc)*60);
				}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
					jlHjDj.setHjTime(60*60);
				}else{
					jlHjDj.setHjTime(180*60);
				}
				
				if(!hjsm.trim().equals("")){
					jlHjDj.setHjInfo(hjsm.trim());
				}
				
				jlHjDj.setDjUser(user.getUserNo());
				Timestamp now=new Timestamp(System.currentTimeMillis());
				jlHjDj.setDjTime(now);
				jlHjDj.setFpFlag(0);
				jlHjDj.setFpTzfrFlag(0);
				jlHjDj.setFpTzqsFlag(0);
				jlHjDj.setHjOrder(Integer.parseInt(callNo));
				jlHjDj.setMonitorFlag("");
				jlHjDj.setPageTzState(0);
				String hql10="from SysParam where paramName='HJ_Client1'";
				List<SysParam> list10=hds.searchAll(hql10);
				if(list10.size()>0){
					SysParam sysParam=list10.get(0);
					if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
						jlHjDj.setDjType(1);
					}else{
						jlHjDj.setDjType(0);
					}
				}else{
					jlHjDj.setDjType(0);
					
				}
				if(jlHjDj.getHjType()==1){
					jlHjDj.setState(0);
				}else{
					jlHjDj.setState(1);
				}
				
				jlHjDj.setHjIndex(hds.getIndex("get_hj_index", jlHjDj.getFrNo()));
				jlHjDj.setImportFlag(0);
				jlHjDj.setTpQsNum(Integer.parseInt(tpQs));
				jlHjDj.setQzSp(Integer.parseInt(qzSp.trim()));
				hds.save(jlHjDj);
				
				if(!isNo.trim().equals("")){
					String[] isNo2=isNo.split(",");
					for(int i=0;i<isNo2.length;i++){
						String hql2="from JlQs where webId="+Integer.parseInt(isNo2[i]);
						List<JlQs> list2=hds.searchAll(hql2);
						switch (i) {
							case 0:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 1:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 2:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 3:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 4:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 5:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 6:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 7:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 8:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							default:
								break;
						}
					}
				}
				
				
				int result=hds.getsptj("HJSP",jlHjDj.getHjid());
				//要审批
				List<HJSPQS> listsp = new ArrayList<HJSPQS>();
				if(result==1){
					if(Integer.parseInt(hjType)!=1){
						JSONArray json=JSONArray.fromObject(-11);
						response.getWriter().println(json.toString());
						return null;
					}
					HJSPQS hj =new HJSPQS();
					String sql3="select SP_Reason,HJID from JL_HJ_SP where HJID="+jlHjDj.getHjid();
					List list1 = hds.searchAllBySql(sql3);
					if(list1.size()>0){
						Object[] obj =(Object[]) list1.get(0);
						if(obj[0]!=null && !obj[0].toString().equals("")){
							hj.setZzzReason(obj[0].toString());
						}
					}
					String sql1="update JL_HJ_DJ set state=3 where HJID="+jlHjDj.getHjid();
					hds.executeUpdate(sql1);
					
					hj.setZzz(-6);
					hj.setZzzhjid(new Long(jlHjDj.getHjid()).intValue());
					listsp.add(hj);
					response.setContentType("text/json; charset=utf-8"); 
					JSONArray json=JSONArray.fromObject(listsp);
					response.getWriter().println(json.toString());
					return null;
				}
				
				JSONArray json=JSONArray.fromObject(jlHjDj.getHjid());
				response.getWriter().println(json.toString());
				
//				String hql1="from SysHjLine where state=1 and hjstate=0 and hjid is null ";
//				List<SysHjLine> list1=hds.searchAll(hql1);
//				if(list1.size()>0){
//					hds.save(jlHjDj);
//					int result=hds.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
////					int result=hds.getLineNo("set_ZW_ex",jlHjDj.getFrNo());
//					if(result==0){
//						List list2=hds.searchAll("from JlHjDj where state=0 and frNo='"+jlHjDj.getFrNo()+"'");
//						if(list2.size()>0){
//							JlHjDj jlHjDj1=(JlHjDj)list2.get(0);
//							System.out.println(jlHjDj1.getHjid());
//							JSONArray json=JSONArray.fromObject(jlHjDj1.getHjid());
//							response.getWriter().println(json.toString());
//						}else{
//							JSONArray json=JSONArray.fromObject(0);
//							response.getWriter().println(json.toString());
//						}
//						
//					}else{
//						JSONArray json=JSONArray.fromObject(-2);
//						response.getWriter().println(json.toString());
//					}
//				}else{
//					JSONArray json=JSONArray.fromObject(-2);
//					response.getWriter().println(json.toString());
//				}
//				JSONArray json=JSONArray.fromObject(-1);
//				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}
			Thread.sleep(1000);
			return null;
	}
	//要验证家属身份证但和IC卡号（四川达州）
	public ActionForward addSaveHjdj123(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String selectFrId=request.getParameter("selectFrId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjsc=request.getParameter("hjsc");
			String hjsm = java.net.URLDecoder.decode((String)request.getParameter("hjsm"),"UTF-8");
			String hjType=request.getParameter("hjType");
//			String monitor = java.net.URLDecoder.decode((String)request.getParameter("monitor"),"UTF-8");
			String callNo=request.getParameter("callNo");
			//审批
			String tpQs=request.getParameter("tpQs");
			String qzSp=request.getParameter("qzSp");
			
			boolean flag=true;
			JlHjDj jlHjDj= new JlHjDj();
			String hql="from JlFr where frNo='"+selectFrId+"'";
			String hql3="from JlHjDj where frNo='"+selectFrId+"' and (State=0 or State=3)";
			List<JlFr> list=hds.searchAll(hql);
			List<JlHjDj> list3=hds.searchAll(hql3);
			if(list.size()>0 && list3.size()==0){
				JlFr jlFr=(JlFr)list.get(0);
				jlHjDj.setJy(jlFr.getJy());
				jlHjDj.setFrNo(jlFr.getFrNo());
				if(jlFr.getFrName()!=null){
					jlHjDj.setFrName(jlFr.getFrName());
				}
				jlHjDj.setJqNo(jlFr.getJq());
				String hql1="from JlJq where jqNo='"+jlFr.getJq()+"'";
				List<JlJq> list1=hds.searchAll(hql1);
				if(list1.size()>0){
					JlJq jlJq=list1.get(0);
					if(jlJq.getJqName()!=null){
						jlHjDj.setJqName(jlJq.getJqName());
					}
				}else{
					flag=false;
				}
			}else{
				flag=false;
			}
			
			List<FrQsToABDoor> listFrQsToABDoor = new ArrayList<FrQsToABDoor>();
			
			if(flag){
				if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard1(jlQs.getQsCard());

								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard2(jlQs.getQsCard());
									
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard3(jlQs.getQsCard());
									
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard4(jlQs.getQsCard());
									
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard5(jlQs.getQsCard());
									
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard6(jlQs.getQsCard());
									
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard7(jlQs.getQsCard());
									
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard8(jlQs.getQsCard());
									
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);

								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard9(jlQs.getQsCard());
									
								}
							}
							break;
						default:
							break;
					}
				}
				}
				jlHjDj.setHjType(Integer.parseInt(hjType));
				if(jlHjDj.getHjType()==1){
					jlHjDj.setHjTime(Integer.parseInt(hjsc)*60);
				}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
					jlHjDj.setHjTime(60*60);
				}else{
					jlHjDj.setHjTime(180*60);
				}
				
				if(!hjsm.trim().equals("")){
					jlHjDj.setHjInfo(hjsm.trim());
				}
				
				jlHjDj.setDjUser(user.getUserNo());
				Timestamp now=new Timestamp(System.currentTimeMillis());
				jlHjDj.setDjTime(now);
				jlHjDj.setFpFlag(0);
				jlHjDj.setFpTzfrFlag(0);
				jlHjDj.setFpTzqsFlag(0);
				jlHjDj.setHjOrder(Integer.parseInt(callNo));
				jlHjDj.setMonitorFlag("");
				jlHjDj.setPageTzState(0);
				String hql10="from SysParam where paramName='HJ_Client1'";
				List<SysParam> list10=hds.searchAll(hql10);
				if(list10.size()>0){
					SysParam sysParam=list10.get(0);
					if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
						jlHjDj.setDjType(1);
					}else{
						jlHjDj.setDjType(0);
					}
				}else{
					jlHjDj.setDjType(0);
					
				}
				if(jlHjDj.getHjType()==1){
					jlHjDj.setState(0);
				}else{
					jlHjDj.setState(1);
				}
				
				jlHjDj.setHjIndex(hds.getIndex("get_hj_index", jlHjDj.getFrNo()));
				jlHjDj.setImportFlag(0);
				jlHjDj.setTpQsNum(Integer.parseInt(tpQs));
				jlHjDj.setQzSp(Integer.parseInt(qzSp.trim()));
				hds.save(jlHjDj);
				
				if(!isNo.trim().equals("")){
					String[] isNo2=isNo.split(",");
					for(int i=0;i<isNo2.length;i++){
						String hql2="from JlQs where webId="+Integer.parseInt(isNo2[i]);
						List<JlQs> list2=hds.searchAll(hql2);
						switch (i) {
							case 0:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 1:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 2:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 3:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 4:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 5:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 6:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 7:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							case 8:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									jlHjDjQs.setFrNo(jlQs.getFrNo());
									jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
									jlHjDjQs.setQsSfz(jlQs.getQsSfz());
									jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
									jlHjDjQs.setQsName(jlQs.getQsName());
									jlHjDjQs.setQsCard(jlQs.getQsCard());
									jlHjDjQs.setGx(jlQs.getGx());
									jlHjDjQs.setXb(jlQs.getXb());
									jlHjDjQs.setDz(jlQs.getDz());
									jlHjDjQs.setTele(jlQs.getTele());
									jlHjDjQs.setZp(jlQs.getZp());
									jlHjDjQs.setJz(jlQs.getJz());
									hds.save(jlHjDjQs);
								}
								break;
							default:
								break;
						}
					}
				}
				
				
				int result=hds.getsptj("HJSP",jlHjDj.getHjid());
				//要审批
				List<HJSPQS> listsp = new ArrayList<HJSPQS>();
				if(result==1){
					if(Integer.parseInt(hjType)!=1){
						JSONArray json=JSONArray.fromObject(-11);
						response.getWriter().println(json.toString());
						return null;
					}
					HJSPQS hj =new HJSPQS();
					String sql3="select SP_Reason,HJID from JL_HJ_SP where HJID="+jlHjDj.getHjid();
					List list1 = hds.searchAllBySql(sql3);
					if(list1.size()>0){
						Object[] obj =(Object[]) list1.get(0);
						if(obj[0]!=null && !obj[0].toString().equals("")){
							hj.setZzzReason(obj[0].toString());
						}
					}
					String sql1="update JL_HJ_DJ set state=3 where HJID="+jlHjDj.getHjid();
					hds.executeUpdate(sql1);
					
					hj.setZzz(-6);
					hj.setZzzhjid(new Long(jlHjDj.getHjid()).intValue());
					listsp.add(hj);
					response.setContentType("text/json; charset=utf-8"); 
					JSONArray json=JSONArray.fromObject(listsp);
					response.getWriter().println(json.toString());
					return null;
				}
				
				JSONArray json=JSONArray.fromObject(jlHjDj.getHjid());
				response.getWriter().println(json.toString());
				
//				String hql1="from SysHjLine where state=1 and hjstate=0 and hjid is null ";
//				List<SysHjLine> list1=hds.searchAll(hql1);
//				if(list1.size()>0){
//					hds.save(jlHjDj);
//					int result=hds.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
////					int result=hds.getLineNo("set_ZW_ex",jlHjDj.getFrNo());
//					if(result==0){
//						List list2=hds.searchAll("from JlHjDj where state=0 and frNo='"+jlHjDj.getFrNo()+"'");
//						if(list2.size()>0){
//							JlHjDj jlHjDj1=(JlHjDj)list2.get(0);
//							System.out.println(jlHjDj1.getHjid());
//							JSONArray json=JSONArray.fromObject(jlHjDj1.getHjid());
//							response.getWriter().println(json.toString());
//						}else{
//							JSONArray json=JSONArray.fromObject(0);
//							response.getWriter().println(json.toString());
//						}
//						
//					}else{
//						JSONArray json=JSONArray.fromObject(-2);
//						response.getWriter().println(json.toString());
//					}
//				}else{
//					JSONArray json=JSONArray.fromObject(-2);
//					response.getWriter().println(json.toString());
//				}
//				JSONArray json=JSONArray.fromObject(-1);
//				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}
			Thread.sleep(1000);
			return null;
	}
	//要验证家属身份证验证物理号（重庆九龙连门禁）
	public ActionForward addSaveHjdj12(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String selectFrId=request.getParameter("selectFrId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjsc=request.getParameter("hjsc");
			String hjsm = java.net.URLDecoder.decode((String)request.getParameter("hjsm"),"UTF-8");
			String hjType=request.getParameter("hjType");
//			String monitor = java.net.URLDecoder.decode((String)request.getParameter("monitor"),"UTF-8");
			String callNo=request.getParameter("callNo");
			//审批
			String tpQs=request.getParameter("tpQs");
			String qzSp=request.getParameter("qzSp");
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String StartTime=sdf.format(date);
			String EndTime=sdf.format(new Date(date.getTime()+Integer.parseInt(Constant.shixiaoshijian) * 60 * 1000));
			
			boolean flag=true;
			JlHjDj jlHjDj= new JlHjDj();
			String hql="from JlFr where frNo='"+selectFrId+"'";
			String hql3="from JlHjDj where frNo='"+selectFrId+"' and (State=0 or State=3)";
			List<JlFr> list=hds.searchAll(hql);
			List<JlHjDj> list3=hds.searchAll(hql3);
			if(list.size()>0 && list3.size()==0){
				JlFr jlFr=(JlFr)list.get(0);
				jlHjDj.setJy(jlFr.getJy());
				jlHjDj.setFrNo(jlFr.getFrNo());
				if(jlFr.getFrName()!=null){
					jlHjDj.setFrName(jlFr.getFrName());
				}
				jlHjDj.setJqNo(jlFr.getJq());
				String hql1="from JlJq where jqNo='"+jlFr.getJq()+"'";
				List<JlJq> list1=hds.searchAll(hql1);
				if(list1.size()>0){
					JlJq jlJq=list1.get(0);
					if(jlJq.getJqName()!=null){
						jlHjDj.setJqName(jlJq.getJqName());
					}
				}else{
					flag=false;
				}
			}else{
				flag=false;
			}
			
			List<FrQsToABDoor> listFrQsToABDoor = new ArrayList<FrQsToABDoor>();
			
//			Timestamp now1=new Timestamp(System.currentTimeMillis());
//			System.out.println(now1);
//			
//			long curren = System.currentTimeMillis();
//			curren +=120*60*1000;
//			Date da = new Date(curren);
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println(dateFormat.format(da));
			
			if(flag){
				if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard1(jlQs.getQsSfzWlh());
								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard2(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard3(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard4(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard5(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard6(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard7(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard8(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);

								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard9(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						default:
							break;
					}
				}
				}
				jlHjDj.setHjType(Integer.parseInt(hjType));
				if(jlHjDj.getHjType()==1){
					jlHjDj.setHjTime(Integer.parseInt(hjsc)*60);
				}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
					jlHjDj.setHjTime(60*60);
				}else{
					jlHjDj.setHjTime(180*60);
				}
				
				if(!hjsm.trim().equals("")){
					jlHjDj.setHjInfo(hjsm.trim());
				}
				
				jlHjDj.setDjUser(user.getUserNo());
				Timestamp now=new Timestamp(System.currentTimeMillis());
				jlHjDj.setDjTime(now);
				jlHjDj.setFpFlag(0);
				jlHjDj.setFpTzfrFlag(0);
				jlHjDj.setFpTzqsFlag(0);
				jlHjDj.setHjOrder(Integer.parseInt(callNo));
				jlHjDj.setMonitorFlag("");
				jlHjDj.setPageTzState(0);
				String hql10="from SysParam where paramName='HJ_Client1'";
				List<SysParam> list10=hds.searchAll(hql10);
				if(list10.size()>0){
					SysParam sysParam=list10.get(0);
					if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
						jlHjDj.setDjType(1);
					}else{
						jlHjDj.setDjType(0);
					}
				}else{
					jlHjDj.setDjType(0);
					
				}
				jlHjDj.setState(0);
				jlHjDj.setHjIndex(hds.getIndex("get_hj_index", jlHjDj.getFrNo()));
				jlHjDj.setImportFlag(0);
				jlHjDj.setTpQsNum(Integer.parseInt(tpQs));
				jlHjDj.setQzSp(Integer.parseInt(qzSp.trim()));
				hds.save(jlHjDj);
				
				if(!isNo.trim().equals("")){
					String[] isNo2=isNo.split(",");
					for(int i=0;i<isNo2.length;i++){
						String hql2="from JlQs where webId="+Integer.parseInt(isNo2[i]);
						List<JlQs> list2=hds.searchAll(hql2);
						switch (i) {
							case 0:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 1:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 2:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 3:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 4:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 5:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 6:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 7:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 8:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							default:
								break;
						}
					}
				}
				
				
				int result=hds.getsptj("HJSP",jlHjDj.getHjid());
				//要审批
				List<HJSPQS> listsp = new ArrayList<HJSPQS>();
				if(result==1){
					HJSPQS hj =new HJSPQS();
					String sql3="select SP_Reason,HJID from JL_HJ_SP where HJID="+jlHjDj.getHjid();
					List list1 = hds.searchAllBySql(sql3);
					if(list1.size()>0){
						Object[] obj =(Object[]) list1.get(0);
						if(obj[0]!=null && !obj[0].toString().equals("")){
							hj.setZzzReason(obj[0].toString());
						}
					}
					String sql1="update JL_HJ_DJ set state=3 where HJID="+jlHjDj.getHjid();
					hds.executeUpdate(sql1);
					
					hj.setZzz(-6);
					hj.setZzzhjid(new Long(jlHjDj.getHjid()).intValue());
					listsp.add(hj);
					response.setContentType("text/json; charset=utf-8"); 
					JSONArray json=JSONArray.fromObject(listsp);
					response.getWriter().println(json.toString());
					return null;
				}
				
				JSONArray json=JSONArray.fromObject(listFrQsToABDoor);
				response.getWriter().println(json.toString());
				
//				String hql1="from SysHjLine where state=1 and hjstate=0 and hjid is null ";
//				List<SysHjLine> list1=hds.searchAll(hql1);
//				if(list1.size()>0){
//					hds.save(jlHjDj);
//					int result=hds.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
////					int result=hds.getLineNo("set_ZW_ex",jlHjDj.getFrNo());
//					if(result==0){
//						List list2=hds.searchAll("from JlHjDj where state=0 and frNo='"+jlHjDj.getFrNo()+"'");
//						if(list2.size()>0){
//							JlHjDj jlHjDj1=(JlHjDj)list2.get(0);
//							System.out.println(jlHjDj1.getHjid());
//							JSONArray json=JSONArray.fromObject(jlHjDj1.getHjid());
//							response.getWriter().println(json.toString());
//						}else{
//							JSONArray json=JSONArray.fromObject(0);
//							response.getWriter().println(json.toString());
//						}
//						
//					}else{
//						JSONArray json=JSONArray.fromObject(-2);
//						response.getWriter().println(json.toString());
//					}
//				}else{
//					JSONArray json=JSONArray.fromObject(-2);
//					response.getWriter().println(json.toString());
//				}
//				JSONArray json=JSONArray.fromObject(-1);
//				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}
			Thread.sleep(1000);
			return null;
	}
	//要验证家属身份证验证物理号（云南第一戒毒所连门禁）
	public ActionForward addSaveHjdj13(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String selectFrId=request.getParameter("selectFrId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjsc=request.getParameter("hjsc");
			String hjsm = java.net.URLDecoder.decode((String)request.getParameter("hjsm"),"UTF-8");
			String hjType=request.getParameter("hjType");
//			String monitor = java.net.URLDecoder.decode((String)request.getParameter("monitor"),"UTF-8");
			String callNo=request.getParameter("callNo");
			//审批
			String tpQs=request.getParameter("tpQs");
			String qzSp=request.getParameter("qzSp");
			
			//门禁权限所需要的数据
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String StartTime=sdf.format(date);
			String EndTime=sdf.format(new Date(date.getTime()+Integer.parseInt(Constant.shixiaoshijian) * 60 * 1000));
			
			boolean flag=true;
			JlHjDj jlHjDj= new JlHjDj();
			String hql="from JlFr where frNo='"+selectFrId+"'";
			String hql3="from JlHjDj where frNo='"+selectFrId+"' and (State=0 or State=3)";
			List<JlFr> list=hds.searchAll(hql);
			List<JlHjDj> list3=hds.searchAll(hql3);
			if(list.size()>0 && list3.size()==0){
				JlFr jlFr=(JlFr)list.get(0);
				jlHjDj.setJy(jlFr.getJy());
				jlHjDj.setFrNo(jlFr.getFrNo());
				if(jlFr.getFrName()!=null){
					jlHjDj.setFrName(jlFr.getFrName());
				}
				jlHjDj.setJqNo(jlFr.getJq());
				String hql1="from JlJq where jqNo='"+jlFr.getJq()+"'";
				List<JlJq> list1=hds.searchAll(hql1);
				if(list1.size()>0){
					JlJq jlJq=list1.get(0);
					if(jlJq.getJqName()!=null){
						jlHjDj.setJqName(jlJq.getJqName());
					}
				}else{
					flag=false;
				}
			}else{
				flag=false;
			}
			
			List<FrQsToABDoor> listFrQsToABDoor = new ArrayList<FrQsToABDoor>();
			
//			Timestamp now1=new Timestamp(System.currentTimeMillis());
//			System.out.println(now1);
//			
//			long curren = System.currentTimeMillis();
//			curren +=120*60*1000;
//			Date da = new Date(curren);
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println(dateFormat.format(da));
			
			if(flag){
				if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard1(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);
									System.out.println(qswlh);

									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard2(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);
									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard3(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);
									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard4(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);
									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard5(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);
									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard6(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);
									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard7(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);
									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard8(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);
									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);

								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard9(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);
									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						default:
							break;
					}
				}
				}
				jlHjDj.setHjType(Integer.parseInt(hjType));
				if(jlHjDj.getHjType()==1){
					jlHjDj.setHjTime(Integer.parseInt(hjsc)*60);
				}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
					jlHjDj.setHjTime(60*60);
				}else{
					jlHjDj.setHjTime(180*60);
				}
				
				if(!hjsm.trim().equals("")){
					jlHjDj.setHjInfo(hjsm.trim());
				}
				
				jlHjDj.setDjUser(user.getUserNo());
				Timestamp now=new Timestamp(System.currentTimeMillis());
				jlHjDj.setDjTime(now);
				jlHjDj.setFpFlag(0);
				jlHjDj.setFpTzfrFlag(0);
				jlHjDj.setFpTzqsFlag(0);
				jlHjDj.setHjOrder(Integer.parseInt(callNo));
				jlHjDj.setMonitorFlag("");
				jlHjDj.setPageTzState(0);
				String hql10="from SysParam where paramName='HJ_Client1'";
				List<SysParam> list10=hds.searchAll(hql10);
				if(list10.size()>0){
					SysParam sysParam=list10.get(0);
					if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
						jlHjDj.setDjType(1);
					}else{
						jlHjDj.setDjType(0);
					}
				}else{
					jlHjDj.setDjType(0);
					
				}
				jlHjDj.setState(0);
				jlHjDj.setHjIndex(hds.getIndex("get_hj_index", jlHjDj.getFrNo()));
				jlHjDj.setImportFlag(0);
				jlHjDj.setTpQsNum(Integer.parseInt(tpQs));
				jlHjDj.setQzSp(Integer.parseInt(qzSp.trim()));
				hds.save(jlHjDj);
				
				if(!isNo.trim().equals("")){
					String[] isNo2=isNo.split(",");
					for(int i=0;i<isNo2.length;i++){
						String hql2="from JlQs where webId="+Integer.parseInt(isNo2[i]);
						List<JlQs> list2=hds.searchAll(hql2);
						switch (i) {
							case 0:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 1:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 2:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 3:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 4:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 5:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 6:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 7:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							case 8:
								if(list2.size()>0){
									JlQs jlQs=list2.get(0);
									
									JlHjDjQs jlHjDjQs = new JlHjDjQs();
									jlHjDjQs.setQsId(jlQs.getWebId());
									jlHjDjQs.setHjId(jlHjDj.getHjid());
									hds.save(jlHjDjQs);
								}
								break;
							default:
								break;
						}
					}
				}
				
				
				int result=hds.getsptj("HJSP",jlHjDj.getHjid());
				//要审批
				List<HJSPQS> listsp = new ArrayList<HJSPQS>();
				if(result==1){
					HJSPQS hj =new HJSPQS();
					String sql3="select SP_Reason,HJID from JL_HJ_SP where HJID="+jlHjDj.getHjid();
					List list1 = hds.searchAllBySql(sql3);
					if(list1.size()>0){
						Object[] obj =(Object[]) list1.get(0);
						if(obj[0]!=null && !obj[0].toString().equals("")){
							hj.setZzzReason(obj[0].toString());
						}
					}
					String sql1="update JL_HJ_DJ set state=3 where HJID="+jlHjDj.getHjid();
					hds.executeUpdate(sql1);
					
					hj.setZzz(-6);
					hj.setZzzhjid(new Long(jlHjDj.getHjid()).intValue());
					listsp.add(hj);
					response.setContentType("text/json; charset=utf-8"); 
					JSONArray json=JSONArray.fromObject(listsp);
					response.getWriter().println(json.toString());
					return null;
				}
				
				JSONArray json=JSONArray.fromObject(jlHjDj.getHjid());
				response.getWriter().println(json.toString());
				
//				String hql1="from SysHjLine where state=1 and hjstate=0 and hjid is null ";
//				List<SysHjLine> list1=hds.searchAll(hql1);
//				if(list1.size()>0){
//					hds.save(jlHjDj);
//					int result=hds.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
////					int result=hds.getLineNo("set_ZW_ex",jlHjDj.getFrNo());
//					if(result==0){
//						List list2=hds.searchAll("from JlHjDj where state=0 and frNo='"+jlHjDj.getFrNo()+"'");
//						if(list2.size()>0){
//							JlHjDj jlHjDj1=(JlHjDj)list2.get(0);
//							System.out.println(jlHjDj1.getHjid());
//							JSONArray json=JSONArray.fromObject(jlHjDj1.getHjid());
//							response.getWriter().println(json.toString());
//						}else{
//							JSONArray json=JSONArray.fromObject(0);
//							response.getWriter().println(json.toString());
//						}
//						
//					}else{
//						JSONArray json=JSONArray.fromObject(-2);
//						response.getWriter().println(json.toString());
//					}
//				}else{
//					JSONArray json=JSONArray.fromObject(-2);
//					response.getWriter().println(json.toString());
//				}
//				JSONArray json=JSONArray.fromObject(-1);
//				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}
			Thread.sleep(1000);
			return null;
	}
	//要验证家属身份证但不验证卡号（广东高明）
	public ActionForward addSaveHjdj11(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String selectFrId=request.getParameter("selectFrId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjsc=request.getParameter("hjsc");
			String hjsm = java.net.URLDecoder.decode((String)request.getParameter("hjsm"),"UTF-8");
			String hjType=request.getParameter("hjType");
//			String monitor = java.net.URLDecoder.decode((String)request.getParameter("monitor"),"UTF-8");
			String callNo=request.getParameter("callNo");
			//审批
			String tpQs=request.getParameter("tpQs");
			String qzSp=request.getParameter("qzSp");
			
			boolean flag=true;
			JlHjDj jlHjDj= new JlHjDj();
			String hql="from JlFr where frNo='"+selectFrId+"'";
			String hql3="from JlHjDj where frNo='"+selectFrId+"' and State=0";
			List<JlFr> list=hds.searchAll(hql);
			List<JlHjDj> list3=hds.searchAll(hql3);
			if(list.size()>0 && list3.size()==0){
				JlFr jlFr=(JlFr)list.get(0);
				jlHjDj.setJy(jlFr.getJy());
				jlHjDj.setFrNo(jlFr.getFrNo());
				if(jlFr.getFrName()!=null){
					jlHjDj.setFrName(jlFr.getFrName());
				}
				jlHjDj.setJqNo(jlFr.getJq());
				String hql1="from JlJq where jqNo='"+jlFr.getJq()+"'";
				List<JlJq> list1=hds.searchAll(hql1);
				if(list1.size()>0){
					JlJq jlJq=list1.get(0);
					if(jlJq.getJqName()!=null){
						jlHjDj.setJqName(jlJq.getJqName());
					}
				}else{
					flag=false;
				}
			}else{
				flag=false;
			}
			List<InterviewUser> listInterviewUser = new ArrayList<InterviewUser>();
			
			List<FrQsToABDoor> listFrQsToABDoor = new ArrayList<FrQsToABDoor>();
			
			if(flag){
				if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard1(jlQs.getQsCard());
								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard2(jlQs.getQsCard());
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard3(jlQs.getQsCard());
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard4(jlQs.getQsCard());
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard5(jlQs.getQsCard());
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard6(jlQs.getQsCard());
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard7(jlQs.getQsCard());
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard8(jlQs.getQsCard());
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard9(jlQs.getQsCard());
								}
							}
							break;
						default:
							break;
					}
				}
				}
				jlHjDj.setHjType(Integer.parseInt(hjType));
				if(jlHjDj.getHjType()==1){
					jlHjDj.setHjTime(Integer.parseInt(hjsc)*60);
				}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
					jlHjDj.setHjTime(60*60);
				}else{
					jlHjDj.setHjTime(180*60);
				}
				
				if(!hjsm.trim().equals("")){
					jlHjDj.setHjInfo(hjsm.trim());
				}
				
				jlHjDj.setDjUser(user.getUserNo());
				Timestamp now=new Timestamp(System.currentTimeMillis());
				jlHjDj.setDjTime(now);
				jlHjDj.setFpFlag(0);
				jlHjDj.setFpTzfrFlag(0);
				jlHjDj.setFpTzqsFlag(0);
				jlHjDj.setHjOrder(Integer.parseInt(callNo));
				jlHjDj.setMonitorFlag("");
				jlHjDj.setPageTzState(0);
				String hql10="from SysParam where paramName='HJ_Client1'";
				List<SysParam> list10=hds.searchAll(hql10);
				if(list10.size()>0){
					SysParam sysParam=list10.get(0);
					if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
						jlHjDj.setDjType(1);
					}else{
						jlHjDj.setDjType(0);
					}
				}else{
					jlHjDj.setDjType(0);
					
				}
				jlHjDj.setState(0);
				jlHjDj.setHjIndex(hds.getIndex("get_hj_index", jlHjDj.getFrNo()));
				jlHjDj.setImportFlag(0);
				jlHjDj.setTpQsNum(Integer.parseInt(tpQs));
				jlHjDj.setQzSp(Integer.parseInt(qzSp.trim()));
				hds.save(jlHjDj);
				
				int result=hds.getsptj("HJSP",jlHjDj.getHjid());
				//要审批
				List<HJSPQS> listsp = new ArrayList<HJSPQS>();
				if(result==1){
					HJSPQS hj =new HJSPQS();
					String sql3="select SP_Reason,HJID from JL_HJ_SP where HJID="+jlHjDj.getHjid();
					List list1 = hds.searchAllBySql(sql3);
					if(list1.size()>0){
						Object[] obj =(Object[]) list1.get(0);
						if(obj[0]!=null && !obj[0].toString().equals("")){
							hj.setZzzReason(obj[0].toString());
						}
					}
					String sql1="update JL_HJ_DJ set state=3 where HJID="+jlHjDj.getHjid();
					hds.executeUpdate(sql1);
					
					hj.setZzz(-6);
					hj.setZzzhjid(new Long(jlHjDj.getHjid()).intValue());
					listsp.add(hj);
					response.setContentType("text/json; charset=utf-8"); 
					JSONArray json=JSONArray.fromObject(listsp);
					response.getWriter().println(json.toString());
					return null;
				}
				
				JSONArray json=JSONArray.fromObject(jlHjDj.getHjid());
				response.getWriter().println(json.toString());
				
//				String hql1="from SysHjLine where state=1 and hjstate=0 and hjid is null ";
//				List<SysHjLine> list1=hds.searchAll(hql1);
//				if(list1.size()>0){
//					hds.save(jlHjDj);
//					int result=hds.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
////					int result=hds.getLineNo("set_ZW_ex",jlHjDj.getFrNo());
//					if(result==0){
//						List list2=hds.searchAll("from JlHjDj where state=0 and frNo='"+jlHjDj.getFrNo()+"'");
//						if(list2.size()>0){
//							JlHjDj jlHjDj1=(JlHjDj)list2.get(0);
//							System.out.println(jlHjDj1.getHjid());
//							JSONArray json=JSONArray.fromObject(jlHjDj1.getHjid());
//							response.getWriter().println(json.toString());
//						}else{
//							JSONArray json=JSONArray.fromObject(0);
//							response.getWriter().println(json.toString());
//						}
//						
//					}else{
//						JSONArray json=JSONArray.fromObject(-2);
//						response.getWriter().println(json.toString());
//					}
//				}else{
//					JSONArray json=JSONArray.fromObject(-2);
//					response.getWriter().println(json.toString());
//				}
//				JSONArray json=JSONArray.fromObject(-1);
//				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}
			Thread.sleep(1000);
			return null;
	}
	//要验证家属卡号（广州监狱）
	public ActionForward addSaveHjdj3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String selectFrId=request.getParameter("selectFrId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjsc=request.getParameter("hjsc");
			String hjsm = java.net.URLDecoder.decode((String)request.getParameter("hjsm"),"UTF-8");
			String hjType=request.getParameter("hjType");
//			String monitor = java.net.URLDecoder.decode((String)request.getParameter("monitor"),"UTF-8");
			String callNo=request.getParameter("callNo");
			//审批
			String tpQs=request.getParameter("tpQs");
			String qzSp=request.getParameter("qzSp");
			
			boolean flag=true;
			JlHjDj jlHjDj= new JlHjDj();
			String hql="from JlFr where frNo='"+selectFrId+"'";
			String hql3="from JlHjDj where frNo='"+selectFrId+"' and State=0";
			List<JlFr> list=hds.searchAll(hql);
			List<JlHjDj> list3=hds.searchAll(hql3);
			if(list.size()>0 && list3.size()==0){
				JlFr jlFr=(JlFr)list.get(0);
				jlHjDj.setJy(jlFr.getJy());
				jlHjDj.setFrNo(jlFr.getFrNo());
				if(jlFr.getFrName()!=null){
					jlHjDj.setFrName(jlFr.getFrName());
				}
				jlHjDj.setJqNo(jlFr.getJq());
				String hql1="from JlJq where jqNo='"+jlFr.getJq()+"'";
				List<JlJq> list1=hds.searchAll(hql1);
				if(list1.size()>0){
					JlJq jlJq=list1.get(0);
					if(jlJq.getJqName()!=null){
						jlHjDj.setJqName(jlJq.getJqName());
					}
				}else{
					flag=false;
				}
			}else{
				flag=false;
			}
			if(flag){
				if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard1(jlQs.getQsCard());
								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard2(jlQs.getQsCard());
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard3(jlQs.getQsCard());
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard4(jlQs.getQsCard());
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard5(jlQs.getQsCard());
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard6(jlQs.getQsCard());
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard7(jlQs.getQsCard());
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard8(jlQs.getQsCard());
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard9(jlQs.getQsCard());
								}
							}
							break;
						default:
							break;
					}
				}
				}
				jlHjDj.setHjType(Integer.parseInt(hjType));
				if(jlHjDj.getHjType()==1){
					jlHjDj.setHjTime(Integer.parseInt(hjsc)*60);
				}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
					jlHjDj.setHjTime(60*60);
				}else{
					jlHjDj.setHjTime(180*60);
				}
				
				if(!hjsm.trim().equals("")){
					jlHjDj.setHjInfo(hjsm.trim());
				}
				
				jlHjDj.setDjUser(user.getUserNo());
				Timestamp now=new Timestamp(System.currentTimeMillis());
				jlHjDj.setDjTime(now);
				jlHjDj.setFpFlag(0);
				jlHjDj.setFpTzfrFlag(0);
				jlHjDj.setFpTzqsFlag(0);
				jlHjDj.setHjOrder(Integer.parseInt(callNo));
				jlHjDj.setMonitorFlag("");
				jlHjDj.setPageTzState(0);
				String hql10="from SysParam where paramName='HJ_Client1'";
				List<SysParam> list10=hds.searchAll(hql10);
				if(list10.size()>0){
					SysParam sysParam=list10.get(0);
					if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
						jlHjDj.setDjType(1);
					}else{
						jlHjDj.setDjType(0);
					}
				}else{
					jlHjDj.setDjType(0);
					
				}
				jlHjDj.setState(0);
				jlHjDj.setHjIndex(hds.getIndex("get_hj_index", jlHjDj.getFrNo()));
				jlHjDj.setImportFlag(0);
				jlHjDj.setTpQsNum(Integer.parseInt(tpQs));
				jlHjDj.setQzSp(Integer.parseInt(qzSp.trim()));
				hds.save(jlHjDj);
				
				int result=hds.getsptj("HJSP",jlHjDj.getHjid());
				//要审批
				List<HJSPQS> listsp = new ArrayList<HJSPQS>();
				if(result==1){
					HJSPQS hj =new HJSPQS();
					String sql3="select SP_Reason,HJID from JL_HJ_SP where HJID="+jlHjDj.getHjid();
					List list1 = hds.searchAllBySql(sql3);
					if(list1.size()>0){
						Object[] obj =(Object[]) list1.get(0);
						if(obj[0]!=null && !obj[0].toString().equals("")){
							hj.setZzzReason(obj[0].toString());
						}
					}
					String sql1="update JL_HJ_DJ set state=3 where HJID="+jlHjDj.getHjid();
					hds.executeUpdate(sql1);
					
					hj.setZzz(-6);
					hj.setZzzhjid(new Long(jlHjDj.getHjid()).intValue());
					listsp.add(hj);
					response.setContentType("text/json; charset=utf-8"); 
					JSONArray json=JSONArray.fromObject(listsp);
					response.getWriter().println(json.toString());
					return null;
				}
				
				JSONArray json=JSONArray.fromObject(jlHjDj.getHjid());
				response.getWriter().println(json.toString());
				
//				String hql1="from SysHjLine where state=1 and hjstate=0 and hjid is null ";
//				List<SysHjLine> list1=hds.searchAll(hql1);
//				if(list1.size()>0){
//					hds.save(jlHjDj);
//					int result=hds.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
////					int result=hds.getLineNo("set_ZW_ex",jlHjDj.getFrNo());
//					if(result==0){
//						List list2=hds.searchAll("from JlHjDj where state=0 and frNo='"+jlHjDj.getFrNo()+"'");
//						if(list2.size()>0){
//							JlHjDj jlHjDj1=(JlHjDj)list2.get(0);
//							System.out.println(jlHjDj1.getHjid());
//							JSONArray json=JSONArray.fromObject(jlHjDj1.getHjid());
//							response.getWriter().println(json.toString());
//						}else{
//							JSONArray json=JSONArray.fromObject(0);
//							response.getWriter().println(json.toString());
//						}
//						
//					}else{
//						JSONArray json=JSONArray.fromObject(-2);
//						response.getWriter().println(json.toString());
//					}
//				}else{
//					JSONArray json=JSONArray.fromObject(-2);
//					response.getWriter().println(json.toString());
//				}
//				JSONArray json=JSONArray.fromObject(-1);
//				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}
			Thread.sleep(1000);
			return null;
	}
	//打印（广东揭阳监狱）
	public ActionForward printXp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//			SysUser user=(SysUser)request.getSession().getAttribute("users");
//			if(user==null){
//				return mapping.findForward("sessionFailure");
//			}
//			String webId=request.getParameter("webId");
//			String webId1=request.getParameter("webId1");
//			JlHjDj jlHjDj=null;
//			if(webId!=null){
//				jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
//			}else{
//				//jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId1));
//				String hql="from JlHjDj where frNo='"+webId1+"' and state=0";
//				List<JlHjDj> list=hds.searchAll(hql);
//				if(list.size()>0){
//					jlHjDj=(JlHjDj)list.get(0);
//				}
//			}
//			
//			//boolean flag=true;
//			String returnName="printView";
//			if(jlHjDj!=null){
//				PrintHjDjVO printHjDjVO=new PrintHjDjVO();
//	//			List<String> list1=new ArrayList<String>();
//	//			list1.add("会见准见证");
//				Calendar c = Calendar.getInstance();   
//				SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
//				c  =  Calendar.getInstance(Locale.CHINESE);   
//				String loginTime = simpleDateTimeFormat.format(c.getTime());
//				//list1.add(loginTime.substring(0, 16));
//				printHjDjVO.setDjTime(loginTime.substring(0, 16));
//	//			if(jlHjDj.getHjIndex()!=null){
//	//				String nameString="会见编号:"+jlHjDj.getHjIndex();
//	//				list1.add(nameString);
//	//				
//	//			}
//				if(jlHjDj.getFpLineNo()!=null){
//					String hql="from SysHjLine where lineNo="+jlHjDj.getFpLineNo()+" and jy='"+jlHjDj.getJy()+"'";
//					List<SysHjLine> list=hds.searchAll(hql);
//					if(list.size()>0){
//						SysHjLine sysHjLine=list.get(0);
//	//					String nameString="会见窗口:"+sysHjLine.getZw();
//	//					list1.add(nameString);
//						printHjDjVO.setSeatNo(sysHjLine.getZw());
//					}
//				}
//				if(jlHjDj.getFrName()!=null){
//	//				String nameString="服刑人员姓名:"+jlHjDj.getFrName();
//	//				list1.add(nameString);
//					printHjDjVO.setFrName(jlHjDj.getFrName());
//				}
//				if(jlHjDj.getJqName()!=null){
//	//				String nameString="监区:"+jlHjDj.getJqName();
//	//				list1.add(nameString);
//					printHjDjVO.setJqName(jlHjDj.getJqName());
//				}
//				if(jlHjDj.getHjInfo()!=null){
//	//				String nameString="监区:"+jlHjDj.getJqName();
//	//				list1.add(nameString);
//					printHjDjVO.setHjSm(jlHjDj.getHjInfo());
//				}
//	//			String sql="select jb.JB_No,jb.JB_Name from JL_FR fr,JL_JB jb where fr.JB_No=jb.JB_No and fr.FR_No='"+jlHjDj.getFrNo()+"'";
//	//			List list=hds.searchAllBySql(sql);
//	//			if(list.size()>0){
//	//				Object[] objects=(Object[])list.get(0);
//	//				if(objects[1]!=null){
//	//					nameString+="   "+objects[1].toString();
//	//				}
//	//			}
//	//		    list1.add("本次会见时长:"+jlHjDj.getHjTime()/60+"分钟");
//				printHjDjVO.setHjTime(jlHjDj.getHjTime()/60+"分钟");
//				List<JlQs> list =new ArrayList<JlQs>();
//				int i=0;
//				String name="";
//				if(jlHjDj.getQsInfo1()!=null && jlHjDj.getQsInfo1()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("[")+1,jlHjDj.getQsInfo1().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo2()!=null && jlHjDj.getQsInfo2()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("[")+1,jlHjDj.getQsInfo2().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo3()!=null && jlHjDj.getQsInfo3()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("[")+1,jlHjDj.getQsInfo3().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo4()!=null && jlHjDj.getQsInfo4()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("[")+1,jlHjDj.getQsInfo4().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo5()!=null && jlHjDj.getQsInfo5()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("[")+1,jlHjDj.getQsInfo5().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo6()!=null && jlHjDj.getQsInfo6()!=""){
//					i++;
//				//	list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("[")+1,jlHjDj.getQsInfo2().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo7()!=null && jlHjDj.getQsInfo7()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("[")+1,jlHjDj.getQsInfo7().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo8()!=null && jlHjDj.getQsInfo8()!=""){
//					i++;
//					//list1.add(i+"号会见人姓名:"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("[")+1,jlHjDj.getQsInfo8().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo9()!=null && jlHjDj.getQsInfo9()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("[")+1,jlHjDj.getQsInfo9().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				for(int k=i;k<4;k++){
//					JlQs jlQs=new JlQs();
//					list.add(jlQs);
//				}
//				printHjDjVO.setList(list);
//				printHjDjVO.setHjRsCount(i+"人");
//				//request.setAttribute("list1", list1);
//				request.setAttribute("printHjDjVO", printHjDjVO);
//			}else{
//				returnName="djDel";
//			}
//		    return mapping.findForward(returnName);
		
		
		
		//标准打印小票
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
			String frNo=request.getParameter("webId1");
			JlHjDj jlHjDj=null;
			if(webId!=null){
				jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
			}else{
				String hql="from JlHjDj where frNo='"+frNo+"' and state=0";
				List<JlHjDj> list=hds.searchAll(hql);
				if(list.size()>0){
					jlHjDj=(JlHjDj)list.get(0);
				}
			}
			
			//boolean flag=true;
			String returnName="printView";
			if(jlHjDj!=null){
				List<String> list1=new ArrayList<String>();
				list1.add("会见准见证");
				Calendar c = Calendar.getInstance();   
				SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
				c  =  Calendar.getInstance(Locale.CHINESE);   
				String loginTime = simpleDateTimeFormat.format(c.getTime());
				list1.add(loginTime.substring(0, 16));
				if(jlHjDj.getFrName()!=null){
					String nameString="会见编号:"+jlHjDj.getHjIndex().toString().substring(8);
					list1.add(nameString);
				}
//				if(jlHjDj.getFpLineNo()!=null){
//					String hql="from SysHjLine where lineNo="+jlHjDj.getFpLineNo()+" and jy='"+jlHjDj.getJy()+"'";
//					List<SysHjLine> list=hds.searchAll(hql);
//					if(list.size()>0){
//						SysHjLine sysHjLine=list.get(0);
//						String nameString="会见窗口:"+sysHjLine.getZw();
//						list1.add(nameString);
	//					printHjDjVO.setSeatNo(sysHjLine.getZw());
//					}
//				}
//				if(jlHjDj.getFrNo()!=null){
//					String nameString="服刑人员编号:"+jlHjDj.getFrNo();
//					list1.add(nameString);
//				}
				if(jlHjDj.getFrName()!=null){
					String nameString="罪犯姓名:"+jlHjDj.getFrName();
					list1.add(nameString);
				}
				if(jlHjDj.getJqName()!=null){
					String nameString="监区:"+jlHjDj.getJqName();
					list1.add(nameString);
				}
	//			String sql="select jb.JB_No,jb.JB_Name from JL_FR fr,JL_JB jb where fr.JB_No=jb.JB_No and fr.FR_No='"+jlHjDj.getFrNo()+"'";
	//			List list=hds.searchAllBySql(sql);
	//			if(list.size()>0){
	//				Object[] objects=(Object[])list.get(0);
	//				if(objects[1]!=null){
	//					nameString+="   "+objects[1].toString();
	//				}
	//			}
				list1.add("本次会见时长:"+jlHjDj.getHjTime()/60+"分钟");
				int i=0;
				String name="";
				if(jlHjDj.getQsInfo1()!=null && jlHjDj.getQsInfo1()!=""){
					i++;
					list1.add(i+"号亲属:"+jlHjDj.getQsInfo1());
				}
				if(jlHjDj.getQsInfo2()!=null && jlHjDj.getQsInfo2()!=""){
					i++;
					list1.add(i+"号亲属:"+jlHjDj.getQsInfo2());
				}
				if(jlHjDj.getQsInfo3()!=null && jlHjDj.getQsInfo3()!=""){
					i++;
					list1.add(i+"号亲属:"+jlHjDj.getQsInfo3());
				}
				if(jlHjDj.getQsInfo4()!=null && jlHjDj.getQsInfo4()!=""){
					i++;
					list1.add(i+"号亲属:"+jlHjDj.getQsInfo4());
				}
				if(jlHjDj.getQsInfo5()!=null && jlHjDj.getQsInfo5()!=""){
					i++;
					list1.add(i+"号亲属:"+jlHjDj.getQsInfo5());
				}
				if(jlHjDj.getQsInfo6()!=null && jlHjDj.getQsInfo6()!=""){
					i++;
					list1.add(i+"号亲属:"+jlHjDj.getQsInfo6());
				}
				if(jlHjDj.getQsInfo7()!=null && jlHjDj.getQsInfo7()!=""){
					i++;
					list1.add(i+"号亲属:"+jlHjDj.getQsInfo7());
				}
				if(jlHjDj.getQsInfo8()!=null && jlHjDj.getQsInfo8()!=""){
					i++;
					list1.add(i+"号亲属:"+jlHjDj.getQsInfo8());
				}
				if(jlHjDj.getQsInfo9()!=null && jlHjDj.getQsInfo9()!=""){
					i++;
					list1.add(i+"号亲属:"+jlHjDj.getQsInfo9());
				}
				list1.add("会见总人数:"+i+"人");
				//request.setAttribute("list1", list1);
				
				

				request.setAttribute("list1", list1);
			}else{
				returnName="djDel";
			}
		
		
		
//		SysUser user=(SysUser)request.getSession().getAttribute("users");
//		if(user==null){
//			return mapping.findForward("sessionFailure");
//		}
//		String webId=request.getParameter("webId");
//		String webId1=request.getParameter("webId1");
//		JlHjDj jlHjDj=null;
//		if(webId!=null){
//			jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
//		}else{
//			//jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId1));
//			String hql="from JlHjDj where frNo='"+webId1+"' and state=0";
//			List<JlHjDj> list=hds.searchAll(hql);
//			if(list.size()>0){
//				jlHjDj=(JlHjDj)list.get(0);
//			}
//		}
//		JlFr jlFr = null;
//		String hql1 = "from JlFr where frNo='"+webId1+"'";
//		List<JlFr> list2 = hds.searchAll(hql1);
//		if(list2.size()>0){
//			jlFr = (JlFr)list2.get(0);
//		}else{
//			mapping.findForward("printXPError");
//		}
//		String returnName="printView2";
//		if(jlHjDj!=null){
//			PrintHjDjVO printHjDjVO=new PrintHjDjVO();
//			Calendar c = Calendar.getInstance();   
//			SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy年MM月dd日 HH时mm分ss秒" );   
//			c  =  Calendar.getInstance(Locale.CHINESE);   
//			String loginTime = simpleDateTimeFormat.format(c.getTime());
//			printHjDjVO.setDjTime(loginTime);
//			printHjDjVO.setDjTime1(loginTime.substring(0,11));
//			printHjDjVO.setJpUser(user.getUserName());
//			if(jlHjDj.getFpLineNo()!=null){
//				String hql="from SysHjLine where lineNo="+jlHjDj.getFpLineNo()+" and jy='"+jlHjDj.getJy()+"'";
//				List<SysHjLine> list=hds.searchAll(hql);
//				if(list.size()>0){
//					SysHjLine sysHjLine=list.get(0);
//					printHjDjVO.setSeatNo(sysHjLine.getZw());
//				}
//			}
//			if(jlFr.getInfoZm()!=null){
//				printHjDjVO.setInfoZm(jlFr.getInfoZm());
//			}
//			if(jlHjDj.getFrNo()!=null){
//				printHjDjVO.setFrNo( jlHjDj.getFrNo());
//			}
//			if(jlHjDj.getHjIndex()!=null){
//				printHjDjVO.setHjIndex(String.valueOf(jlHjDj.getHjIndex()).substring(8));
//			}
//			if(jlHjDj.getFrName()!=null){
//				printHjDjVO.setFrName(jlHjDj.getFrName());
//			}
//			if(jlHjDj.getJqName()!=null){
//				printHjDjVO.setJqName(jlHjDj.getJqName());
//			}
//			if(jlHjDj.getHjInfo()!=null){
//				printHjDjVO.setHjSm(jlHjDj.getHjInfo());
//			}
//			printHjDjVO.setHjTime(jlHjDj.getHjTime()/60+"分钟");
//			List<JlQs> list =new ArrayList<JlQs>();
//			int i=0;
//			String name="";
//			if(jlHjDj.getQsInfo1()!=null && jlHjDj.getQsInfo1()!=""){
//				i++;
//				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("[")+1,jlHjDj.getQsInfo1().lastIndexOf("]"))+"'";
//				List<JlQs> list1=hds.searchAll(hql);
//				if(list1.size()>0){
//					JlQs jlQs=list1.get(0);
//					list.add(jlQs);
//				}
//			}
//			if(jlHjDj.getQsInfo2()!=null && jlHjDj.getQsInfo2()!=""){
//				i++;
//				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("[")+1,jlHjDj.getQsInfo2().lastIndexOf("]"))+"'";
//				List<JlQs> list1=hds.searchAll(hql);
//				if(list1.size()>0){
//					JlQs jlQs=list1.get(0);
//					list.add(jlQs);
//				}
//			}
//			if(jlHjDj.getQsInfo3()!=null && jlHjDj.getQsInfo3()!=""){
//				i++;
//				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("[")+1,jlHjDj.getQsInfo3().lastIndexOf("]"))+"'";
//				List<JlQs> list1=hds.searchAll(hql);
//				if(list1.size()>0){
//					JlQs jlQs=list1.get(0);
//					list.add(jlQs);
//				}
//			}
////			if(jlHjDj.getQsInfo4()!=null && jlHjDj.getQsInfo4()!=""){
////				i++;
////				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("[")+1,jlHjDj.getQsInfo4().lastIndexOf("]"))+"'";
////				List<JlQs> list1=hds.searchAll(hql);
////				if(list1.size()>0){
////					JlQs jlQs=list1.get(0);
////					list.add(jlQs);
////				}
////			}
////			if(jlHjDj.getQsInfo5()!=null && jlHjDj.getQsInfo5()!=""){
////				i++;
////				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("[")+1,jlHjDj.getQsInfo5().lastIndexOf("]"))+"'";
////				List<JlQs> list1=hds.searchAll(hql);
////				if(list1.size()>0){
////					JlQs jlQs=list1.get(0);
////					list.add(jlQs);
////				}
////			}
////			if(jlHjDj.getQsInfo6()!=null && jlHjDj.getQsInfo6()!=""){
////				i++;
////				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("[")+1,jlHjDj.getQsInfo2().lastIndexOf("]"))+"'";
////				List<JlQs> list1=hds.searchAll(hql);
////				if(list1.size()>0){
////					JlQs jlQs=list1.get(0);
////					list.add(jlQs);
////				}
////			}
////			if(jlHjDj.getQsInfo7()!=null && jlHjDj.getQsInfo7()!=""){
////				i++;
////				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("[")+1,jlHjDj.getQsInfo7().lastIndexOf("]"))+"'";
////				List<JlQs> list1=hds.searchAll(hql);
////				if(list1.size()>0){
////					JlQs jlQs=list1.get(0);
////					list.add(jlQs);
////				}
////			}
////			if(jlHjDj.getQsInfo8()!=null && jlHjDj.getQsInfo8()!=""){
////				i++;
////				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("[")+1,jlHjDj.getQsInfo8().lastIndexOf("]"))+"'";
////				List<JlQs> list1=hds.searchAll(hql);
////				if(list1.size()>0){
////					JlQs jlQs=list1.get(0);
////					list.add(jlQs);
////				}
////			}
////			if(jlHjDj.getQsInfo9()!=null && jlHjDj.getQsInfo9()!=""){
////				i++;
////				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("[")+1,jlHjDj.getQsInfo9().lastIndexOf("]"))+"'";
////				List<JlQs> list1=hds.searchAll(hql);
////				if(list1.size()>0){
////					JlQs jlQs=list1.get(0);
////					list.add(jlQs);
////				}
////			}
//			for(int k=i;k<3;k++){
//				JlQs jlQs=new JlQs();
//				list.add(jlQs);
//			}
//			printHjDjVO.setList(list);
//			printHjDjVO.setHjRsCount(i+"人");
//			request.setAttribute("printHjDjVO", printHjDjVO);
//		}else{
//			returnName="djDel";
//		}
		    return mapping.findForward(returnName);
	}
	//打印（番禺监狱格式）
	public ActionForward printXp2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//			SysUser user=(SysUser)request.getSession().getAttribute("users");
//			if(user==null){
//				return mapping.findForward("sessionFailure");
//			}
//			String webId=request.getParameter("webId");
//			String webId1=request.getParameter("webId1");
//			JlHjDj jlHjDj=null;
//			if(webId!=null){
//				jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
//			}else{
//				//jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId1));
//				String hql="from JlHjDj where frNo='"+webId1+"' and state=0";
//				List<JlHjDj> list=hds.searchAll(hql);
//				if(list.size()>0){
//					jlHjDj=(JlHjDj)list.get(0);
//				}
//			}
//			
//			//boolean flag=true;
//			String returnName="printView";
//			if(jlHjDj!=null){
//				PrintHjDjVO printHjDjVO=new PrintHjDjVO();
//	//			List<String> list1=new ArrayList<String>();
//	//			list1.add("会见准见证");
//				Calendar c = Calendar.getInstance();   
//				SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
//				c  =  Calendar.getInstance(Locale.CHINESE);   
//				String loginTime = simpleDateTimeFormat.format(c.getTime());
//				//list1.add(loginTime.substring(0, 16));
//				printHjDjVO.setDjTime(loginTime.substring(0, 16));
//	//			if(jlHjDj.getHjIndex()!=null){
//	//				String nameString="会见编号:"+jlHjDj.getHjIndex();
//	//				list1.add(nameString);
//	//				
//	//			}
//				if(jlHjDj.getFpLineNo()!=null){
//					String hql="from SysHjLine where lineNo="+jlHjDj.getFpLineNo()+" and jy='"+jlHjDj.getJy()+"'";
//					List<SysHjLine> list=hds.searchAll(hql);
//					if(list.size()>0){
//						SysHjLine sysHjLine=list.get(0);
//	//					String nameString="会见窗口:"+sysHjLine.getZw();
//	//					list1.add(nameString);
//						printHjDjVO.setSeatNo(sysHjLine.getZw());
//					}
//				}
//				if(jlHjDj.getFrName()!=null){
//	//				String nameString="服刑人员姓名:"+jlHjDj.getFrName();
//	//				list1.add(nameString);
//					printHjDjVO.setFrName(jlHjDj.getFrName());
//				}
//				if(jlHjDj.getJqName()!=null){
//	//				String nameString="监区:"+jlHjDj.getJqName();
//	//				list1.add(nameString);
//					printHjDjVO.setJqName(jlHjDj.getJqName());
//				}
//				if(jlHjDj.getHjInfo()!=null){
//	//				String nameString="监区:"+jlHjDj.getJqName();
//	//				list1.add(nameString);
//					printHjDjVO.setHjSm(jlHjDj.getHjInfo());
//				}
//	//			String sql="select jb.JB_No,jb.JB_Name from JL_FR fr,JL_JB jb where fr.JB_No=jb.JB_No and fr.FR_No='"+jlHjDj.getFrNo()+"'";
//	//			List list=hds.searchAllBySql(sql);
//	//			if(list.size()>0){
//	//				Object[] objects=(Object[])list.get(0);
//	//				if(objects[1]!=null){
//	//					nameString+="   "+objects[1].toString();
//	//				}
//	//			}
//	//		    list1.add("本次会见时长:"+jlHjDj.getHjTime()/60+"分钟");
//				printHjDjVO.setHjTime(jlHjDj.getHjTime()/60+"分钟");
//				List<JlQs> list =new ArrayList<JlQs>();
//				int i=0;
//				String name="";
//				if(jlHjDj.getQsInfo1()!=null && jlHjDj.getQsInfo1()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("[")+1,jlHjDj.getQsInfo1().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo2()!=null && jlHjDj.getQsInfo2()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("[")+1,jlHjDj.getQsInfo2().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo3()!=null && jlHjDj.getQsInfo3()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("[")+1,jlHjDj.getQsInfo3().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo4()!=null && jlHjDj.getQsInfo4()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("[")+1,jlHjDj.getQsInfo4().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo5()!=null && jlHjDj.getQsInfo5()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("[")+1,jlHjDj.getQsInfo5().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo6()!=null && jlHjDj.getQsInfo6()!=""){
//					i++;
//				//	list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("[")+1,jlHjDj.getQsInfo2().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo7()!=null && jlHjDj.getQsInfo7()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("[")+1,jlHjDj.getQsInfo7().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo8()!=null && jlHjDj.getQsInfo8()!=""){
//					i++;
//					//list1.add(i+"号会见人姓名:"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("[")+1,jlHjDj.getQsInfo8().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				if(jlHjDj.getQsInfo9()!=null && jlHjDj.getQsInfo9()!=""){
//					i++;
//					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("]")+1));
//					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("[")+1,jlHjDj.getQsInfo9().lastIndexOf("]"))+"'";
//					List<JlQs> list1=hds.searchAll(hql);
//					if(list1.size()>0){
//						JlQs jlQs=list1.get(0);
//						list.add(jlQs);
//					}
//				}
//				for(int k=i;k<4;k++){
//					JlQs jlQs=new JlQs();
//					list.add(jlQs);
//				}
//				printHjDjVO.setList(list);
//				printHjDjVO.setHjRsCount(i+"人");
//				//request.setAttribute("list1", list1);
//				request.setAttribute("printHjDjVO", printHjDjVO);
//			}else{
//				returnName="djDel";
//			}
//		    return mapping.findForward(returnName);
		
		
		
		//标准打印小票
//			SysUser user=(SysUser)request.getSession().getAttribute("users");
//			if(user==null){
//				return mapping.findForward("sessionFailure");
//			}
//			String webId=request.getParameter("webId");
//			String frNo=request.getParameter("webId1");
//			JlHjDj jlHjDj=null;
//			if(webId!=null){
//				jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
//			}else{
//				String hql="from JlHjDj where frNo='"+frNo+"' and state=0";
//				List<JlHjDj> list=hds.searchAll(hql);
//				if(list.size()>0){
//					jlHjDj=(JlHjDj)list.get(0);
//				}
//			}
//			
//			//boolean flag=true;
//			String returnName="printView";
//			if(jlHjDj!=null){
//				List<String> list1=new ArrayList<String>();
//				list1.add("会见准见证");
//				Calendar c = Calendar.getInstance();   
//				SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
//				c  =  Calendar.getInstance(Locale.CHINESE);   
//				String loginTime = simpleDateTimeFormat.format(c.getTime());
//				list1.add(loginTime.substring(0, 16));
//				if(jlHjDj.getFrName()!=null){
//					String nameString="会见编号:"+jlHjDj.getHjIndex().toString().substring(8);
//					list1.add(nameString);
//				}
//				if(jlHjDj.getFpLineNo()!=null){
//					String hql="from SysHjLine where lineNo="+jlHjDj.getFpLineNo()+" and jy='"+jlHjDj.getJy()+"'";
//					List<SysHjLine> list=hds.searchAll(hql);
//					if(list.size()>0){
//						SysHjLine sysHjLine=list.get(0);
//						String nameString="会见窗口:"+sysHjLine.getZw();
//						list1.add(nameString);
//	//					printHjDjVO.setSeatNo(sysHjLine.getZw());
//					}
//				}
//				if(jlHjDj.getFrName()!=null){
//					String nameString="服刑人员姓名:"+jlHjDj.getFrName();
//					list1.add(nameString);
//				}
//				if(jlHjDj.getJqName()!=null){
//					String nameString="监区:"+jlHjDj.getJqName();
//					list1.add(nameString);
//				}
//	//			String sql="select jb.JB_No,jb.JB_Name from JL_FR fr,JL_JB jb where fr.JB_No=jb.JB_No and fr.FR_No='"+jlHjDj.getFrNo()+"'";
//	//			List list=hds.searchAllBySql(sql);
//	//			if(list.size()>0){
//	//				Object[] objects=(Object[])list.get(0);
//	//				if(objects[1]!=null){
//	//					nameString+="   "+objects[1].toString();
//	//				}
//	//			}
//				list1.add("本次会见时长:"+jlHjDj.getHjTime()/60+"分钟");
//				int i=0;
//				String name="";
//				if(jlHjDj.getQsInfo1()!=null && jlHjDj.getQsInfo1()!=""){
//					i++;
//					list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("]")+1));
//				}
//				if(jlHjDj.getQsInfo2()!=null && jlHjDj.getQsInfo2()!=""){
//					i++;
//					list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("]")+1));
//				}
//				if(jlHjDj.getQsInfo3()!=null && jlHjDj.getQsInfo3()!=""){
//					i++;
//					list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("]")+1));
//				}
//				if(jlHjDj.getQsInfo4()!=null && jlHjDj.getQsInfo4()!=""){
//					i++;
//					list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("]")+1));
//				}
//				if(jlHjDj.getQsInfo5()!=null && jlHjDj.getQsInfo5()!=""){
//					i++;
//					list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("]")+1));
//				}
//				if(jlHjDj.getQsInfo6()!=null && jlHjDj.getQsInfo6()!=""){
//					i++;
//					list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("]")+1));
//				}
//				if(jlHjDj.getQsInfo7()!=null && jlHjDj.getQsInfo7()!=""){
//					i++;
//					list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("]")+1));
//				}
//				if(jlHjDj.getQsInfo8()!=null && jlHjDj.getQsInfo8()!=""){
//					i++;
//					list1.add(i+"号会见人姓名:"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("]")+1));
//				}
//				if(jlHjDj.getQsInfo9()!=null && jlHjDj.getQsInfo9()!=""){
//					i++;
//					list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("]")+1));
//				}
//				list1.add("会见总人数:"+i+"人");
//				request.setAttribute("list1", list1);
//			}else{
//				returnName="djDel";
//			}
		
		
		
		SysUser user=(SysUser)request.getSession().getAttribute("users");
		if(user==null){
			return mapping.findForward("sessionFailure");
		}
		String webId=request.getParameter("webId");
		String webId1=request.getParameter("webId1");
		JlHjDj jlHjDj=null;
		if(webId!=null){
			jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
		}else{
			//jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId1));
			String hql="from JlHjDj where frNo='"+webId1+"' and state=0";
			List<JlHjDj> list=hds.searchAll(hql);
			if(list.size()>0){
				jlHjDj=(JlHjDj)list.get(0);
			}
		}
		JlFr jlFr = null;
		String hql1 = "from JlFr where frNo='"+webId1+"'";
		List<JlFr> list2 = hds.searchAll(hql1);
		if(list2.size()>0){
			jlFr = (JlFr)list2.get(0);
		}else{
			mapping.findForward("printXPError");
		}
		String returnName="printView2";
		if(jlHjDj!=null){
			PrintHjDjVO printHjDjVO=new PrintHjDjVO();
			Calendar c = Calendar.getInstance();   
			SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy年MM月dd日 HH时mm分ss秒" );   
			c  =  Calendar.getInstance(Locale.CHINESE);   
			String loginTime = simpleDateTimeFormat.format(c.getTime());
			printHjDjVO.setDjTime(loginTime);
			printHjDjVO.setDjTime1(loginTime.substring(0,11));
			printHjDjVO.setJpUser(user.getUserName());
			if(jlHjDj.getFpLineNo()!=null){
				String hql="from SysHjLine where lineNo="+jlHjDj.getFpLineNo()+" and jy='"+jlHjDj.getJy()+"'";
				List<SysHjLine> list=hds.searchAll(hql);
				if(list.size()>0){
					SysHjLine sysHjLine=list.get(0);
					printHjDjVO.setSeatNo(sysHjLine.getZw());
				}
			}
			if(jlFr.getInfoZm()!=null){
				printHjDjVO.setInfoZm(jlFr.getInfoZm());
			}
			if(jlHjDj.getFrNo()!=null){
				printHjDjVO.setFrNo( jlHjDj.getFrNo());
			}
			if(jlHjDj.getHjIndex()!=null){
				printHjDjVO.setHjIndex(String.valueOf(jlHjDj.getHjIndex()).substring(8));
			}
			if(jlHjDj.getFrName()!=null){
				printHjDjVO.setFrName(jlHjDj.getFrName());
			}
			if(jlHjDj.getJqName()!=null){
				printHjDjVO.setJqName(jlHjDj.getJqName());
			}
			if(jlHjDj.getHjInfo()!=null){
				printHjDjVO.setHjSm(jlHjDj.getHjInfo());
			}
			printHjDjVO.setHjTime(jlHjDj.getHjTime()/60+"分钟");
			List<JlQs> list =new ArrayList<JlQs>();
			int i=0;
			String name="";
			if(jlHjDj.getQsInfo1()!=null && jlHjDj.getQsInfo1()!=""){
				i++;
				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("[")+1,jlHjDj.getQsInfo1().lastIndexOf("]"))+"'";
				List<JlQs> list1=hds.searchAll(hql);
				if(list1.size()>0){
					JlQs jlQs=list1.get(0);
					list.add(jlQs);
				}
			}
			if(jlHjDj.getQsInfo2()!=null && jlHjDj.getQsInfo2()!=""){
				i++;
				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("[")+1,jlHjDj.getQsInfo2().lastIndexOf("]"))+"'";
				List<JlQs> list1=hds.searchAll(hql);
				if(list1.size()>0){
					JlQs jlQs=list1.get(0);
					list.add(jlQs);
				}
			}
			if(jlHjDj.getQsInfo3()!=null && jlHjDj.getQsInfo3()!=""){
				i++;
				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("[")+1,jlHjDj.getQsInfo3().lastIndexOf("]"))+"'";
				List<JlQs> list1=hds.searchAll(hql);
				if(list1.size()>0){
					JlQs jlQs=list1.get(0);
					list.add(jlQs);
				}
			}
			if(jlHjDj.getQsInfo4()!=null && jlHjDj.getQsInfo4()!=""){
				i++;
				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("[")+1,jlHjDj.getQsInfo4().lastIndexOf("]"))+"'";
				List<JlQs> list1=hds.searchAll(hql);
				if(list1.size()>0){
					JlQs jlQs=list1.get(0);
					list.add(jlQs);
				}
			}
			if(jlHjDj.getQsInfo5()!=null && jlHjDj.getQsInfo5()!=""){
				i++;
				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("[")+1,jlHjDj.getQsInfo5().lastIndexOf("]"))+"'";
				List<JlQs> list1=hds.searchAll(hql);
				if(list1.size()>0){
					JlQs jlQs=list1.get(0);
					list.add(jlQs);
				}
			}
//			if(jlHjDj.getQsInfo6()!=null && jlHjDj.getQsInfo6()!=""){
//				i++;
//				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("[")+1,jlHjDj.getQsInfo2().lastIndexOf("]"))+"'";
//				List<JlQs> list1=hds.searchAll(hql);
//				if(list1.size()>0){
//					JlQs jlQs=list1.get(0);
//					list.add(jlQs);
//				}
//			}
//			if(jlHjDj.getQsInfo7()!=null && jlHjDj.getQsInfo7()!=""){
//				i++;
//				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("[")+1,jlHjDj.getQsInfo7().lastIndexOf("]"))+"'";
//				List<JlQs> list1=hds.searchAll(hql);
//				if(list1.size()>0){
//					JlQs jlQs=list1.get(0);
//					list.add(jlQs);
//				}
//			}
//			if(jlHjDj.getQsInfo8()!=null && jlHjDj.getQsInfo8()!=""){
//				i++;
//				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("[")+1,jlHjDj.getQsInfo8().lastIndexOf("]"))+"'";
//				List<JlQs> list1=hds.searchAll(hql);
//				if(list1.size()>0){
//					JlQs jlQs=list1.get(0);
//					list.add(jlQs);
//				}
//			}
//			if(jlHjDj.getQsInfo9()!=null && jlHjDj.getQsInfo9()!=""){
//				i++;
//				String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("[")+1,jlHjDj.getQsInfo9().lastIndexOf("]"))+"'";
//				List<JlQs> list1=hds.searchAll(hql);
//				if(list1.size()>0){
//					JlQs jlQs=list1.get(0);
//					list.add(jlQs);
//				}
//			}
			for(int k=i;k<5;k++){
				JlQs jlQs=new JlQs();
				list.add(jlQs);
			}
			printHjDjVO.setList(list);
			printHjDjVO.setHjRsCount(i+"人");
			request.setAttribute("printHjDjVO", printHjDjVO);
		}else{
			returnName="djDel";
		}
		    return mapping.findForward(returnName);
	}
	//打印
	public ActionForward printXp1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
			String webId1=request.getParameter("webId1");
			JlHjDj jlHjDj=null;
			if(webId!=null){
				jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
			}else{
				//jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId1));
				String hql="from JlHjDj where frNo='"+webId1+"' and state=0";
				List<JlHjDj> list=hds.searchAll(hql);
				if(list.size()>0){
					jlHjDj=(JlHjDj)list.get(0);
				}
			}
			
			//boolean flag=true;
			String returnName="printView1";
			if(jlHjDj!=null){
				PrintHjDjVO printHjDjVO=new PrintHjDjVO();
	//			List<String> list1=new ArrayList<String>();
	//			list1.add("会见准见证");
				Calendar c = Calendar.getInstance();   
				SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
				c  =  Calendar.getInstance(Locale.CHINESE);   
				String loginTime = simpleDateTimeFormat.format(c.getTime());
				//list1.add(loginTime.substring(0, 16));
				printHjDjVO.setDjTime(loginTime.substring(0, 16));
				printHjDjVO.setHjIndex(jlHjDj.getHjIndex().toString().substring(8));
				printHjDjVO.setDjType(jlHjDj.getDjType()+"");
				printHjDjVO.setFrNo(jlHjDj.getFrNo());
				if(jlHjDj.getSpUser()!=null){
					printHjDjVO.setSpUser(jlHjDj.getSpUser());
				}else{
					printHjDjVO.setSpUser("");
				}
				printHjDjVO.setDjName(jlHjDj.getDjUser());
				String sql5="select fr.FR_No, jb.JB_Name,fr.Info_ZDZF,fr.Info_ZM from JL_Fr fr ,JL_JB jb where fr.JB_No=jb.JB_No and FR_No='"+jlHjDj.getFrNo()+"'";
				List list5 =hds.searchAllBySql(sql5);
				if(list5.size()>0){
					Object[] obj=(Object[])list5.get(0);
					printHjDjVO.setJbName(obj[1].toString());
					if(obj[2]!=null && obj[2].toString().equals("是")){
						printHjDjVO.setZdzf("1");
					}else{
						printHjDjVO.setZdzf("");
					}
					if(obj[3]!=null && (obj[3].toString().indexOf("枪支")>0 || obj[3].toString().indexOf("毒品")>0 || obj[3].toString().indexOf("黑社会")>0)){
						printHjDjVO.setZm("1");
					}else{
						printHjDjVO.setZm("");
					}
					if(obj[3]!=null && !obj[3].toString().equals("")){
						printHjDjVO.setZm1(obj[3].toString());
					}else{
						printHjDjVO.setZm1("");
					}
				}else{
					printHjDjVO.setJbName("");
					printHjDjVO.setZdzf("");
					printHjDjVO.setZm("");
				}
	//			if(jlHjDj.getHjIndex()!=null){
	//				String nameString="会见编号:"+jlHjDj.getHjIndex();
	//				list1.add(nameString);
	//				
	//			}
				if(jlHjDj.getFpLineNo()!=null){
					String hql="from SysHjLine where lineNo="+jlHjDj.getFpLineNo()+" and jy='"+jlHjDj.getJy()+"'";
					List<SysHjLine> list=hds.searchAll(hql);
					if(list.size()>0){
						SysHjLine sysHjLine=list.get(0);
	//					String nameString="会见窗口:"+sysHjLine.getZw();
	//					list1.add(nameString);
						printHjDjVO.setSeatNo(sysHjLine.getZw());
					}
				}
				if(jlHjDj.getFrName()!=null){
	//				String nameString="服刑人员姓名:"+jlHjDj.getFrName();
	//				list1.add(nameString);
					printHjDjVO.setFrName(jlHjDj.getFrName());
				}
				if(jlHjDj.getJqName()!=null){
	//				String nameString="监区:"+jlHjDj.getJqName();
	//				list1.add(nameString);
					printHjDjVO.setJqName(jlHjDj.getJqName());
				}
				if(jlHjDj.getHjInfo()!=null){
	//				String nameString="监区:"+jlHjDj.getJqName();
	//				list1.add(nameString);
					printHjDjVO.setHjSm(jlHjDj.getHjInfo());
				}
	//			String sql="select jb.JB_No,jb.JB_Name from JL_FR fr,JL_JB jb where fr.JB_No=jb.JB_No and fr.FR_No='"+jlHjDj.getFrNo()+"'";
	//			List list=hds.searchAllBySql(sql);
	//			if(list.size()>0){
	//				Object[] objects=(Object[])list.get(0);
	//				if(objects[1]!=null){
	//					nameString+="   "+objects[1].toString();
	//				}
	//			}
	//		    list1.add("本次会见时长:"+jlHjDj.getHjTime()/60+"分钟");
				printHjDjVO.setHjTime(jlHjDj.getHjTime()/60+"分钟");
				List<JlQs> list =new ArrayList<JlQs>();
				int i=0;
				String name="";
				if(jlHjDj.getQsInfo1()!=null && jlHjDj.getQsInfo1()!=""){
					i++;
					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("]")+1));
					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo1().substring(jlHjDj.getQsInfo1().lastIndexOf("[")+1,jlHjDj.getQsInfo1().lastIndexOf("]"))+"'";
					List<JlQs> list1=hds.searchAll(hql);
					if(list1.size()>0){
						JlQs jlQs=list1.get(0);
						list.add(jlQs);
					}
				}
				if(jlHjDj.getQsInfo2()!=null && jlHjDj.getQsInfo2()!=""){
					i++;
					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("]")+1));
					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo2().substring(jlHjDj.getQsInfo2().lastIndexOf("[")+1,jlHjDj.getQsInfo2().lastIndexOf("]"))+"'";
					List<JlQs> list1=hds.searchAll(hql);
					if(list1.size()>0){
						JlQs jlQs=list1.get(0);
						list.add(jlQs);
					}
				}
				if(jlHjDj.getQsInfo3()!=null && jlHjDj.getQsInfo3()!=""){
					i++;
					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("]")+1));
					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo3().substring(jlHjDj.getQsInfo3().lastIndexOf("[")+1,jlHjDj.getQsInfo3().lastIndexOf("]"))+"'";
					List<JlQs> list1=hds.searchAll(hql);
					if(list1.size()>0){
						JlQs jlQs=list1.get(0);
						list.add(jlQs);
					}
				}
				if(jlHjDj.getQsInfo4()!=null && jlHjDj.getQsInfo4()!=""){
					i++;
					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("]")+1));
					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo4().substring(jlHjDj.getQsInfo4().lastIndexOf("[")+1,jlHjDj.getQsInfo4().lastIndexOf("]"))+"'";
					List<JlQs> list1=hds.searchAll(hql);
					if(list1.size()>0){
						JlQs jlQs=list1.get(0);
						list.add(jlQs);
					}
				}
				if(jlHjDj.getQsInfo5()!=null && jlHjDj.getQsInfo5()!=""){
					i++;
					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("]")+1));
					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo5().substring(jlHjDj.getQsInfo5().lastIndexOf("[")+1,jlHjDj.getQsInfo5().lastIndexOf("]"))+"'";
					List<JlQs> list1=hds.searchAll(hql);
					if(list1.size()>0){
						JlQs jlQs=list1.get(0);
						list.add(jlQs);
					}
				}
				if(jlHjDj.getQsInfo6()!=null && jlHjDj.getQsInfo6()!=""){
					i++;
				//	list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("]")+1));
					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo6().substring(jlHjDj.getQsInfo6().lastIndexOf("[")+1,jlHjDj.getQsInfo2().lastIndexOf("]"))+"'";
					List<JlQs> list1=hds.searchAll(hql);
					if(list1.size()>0){
						JlQs jlQs=list1.get(0);
						list.add(jlQs);
					}
				}
				if(jlHjDj.getQsInfo7()!=null && jlHjDj.getQsInfo7()!=""){
					i++;
					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("]")+1));
					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo7().substring(jlHjDj.getQsInfo7().lastIndexOf("[")+1,jlHjDj.getQsInfo7().lastIndexOf("]"))+"'";
					List<JlQs> list1=hds.searchAll(hql);
					if(list1.size()>0){
						JlQs jlQs=list1.get(0);
						list.add(jlQs);
					}
				}
				if(jlHjDj.getQsInfo8()!=null && jlHjDj.getQsInfo8()!=""){
					i++;
					//list1.add(i+"号会见人姓名:"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("]")+1));
					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo8().substring(jlHjDj.getQsInfo8().lastIndexOf("[")+1,jlHjDj.getQsInfo8().lastIndexOf("]"))+"'";
					List<JlQs> list1=hds.searchAll(hql);
					if(list1.size()>0){
						JlQs jlQs=list1.get(0);
						list.add(jlQs);
					}
				}
				if(jlHjDj.getQsInfo9()!=null && jlHjDj.getQsInfo9()!=""){
					i++;
					//list1.add(i+"号亲属姓名:"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("]")+1));
					String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"' and qsName='"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("]")+1)+"' and gx='"+jlHjDj.getQsInfo9().substring(jlHjDj.getQsInfo9().lastIndexOf("[")+1,jlHjDj.getQsInfo9().lastIndexOf("]"))+"'";
					List<JlQs> list1=hds.searchAll(hql);
					if(list1.size()>0){
						JlQs jlQs=list1.get(0);
						list.add(jlQs);
					}
				}
//				for(int k=i;k<4;k++){
//					JlQs jlQs=new JlQs();
//					list.add(jlQs);
//				}
				printHjDjVO.setList(list);
				printHjDjVO.setHjRsCount(i+"");
				//request.setAttribute("list1", list1);
				request.setAttribute("printHjDjVO", printHjDjVO);
			}else{
				returnName="djDel";
			}
		    return mapping.findForward(returnName);
	}
	//删除登记
	public ActionForward deleteDj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
			JlHjDj jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
			if(jlHjDj.getFpFlag()==0){
				hds.delete(JlHjDj.class, Long.parseLong(webId));
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}else if(jlHjDj.getFpFlag()==1){
				String hql="update SysHjLine set hjid=null where hjid="+jlHjDj.getHjid();
				Object[] objects={};
				hds.updates(hql, objects);
				hds.delete(JlHjDj.class, Long.parseLong(webId));
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(1);
				response.getWriter().println(json.toString());
			}
			return null;
	}
	//取消登记
	public ActionForward cancelDj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
			String returnName="cancelDj";
			JlHjDj jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
			if(jlHjDj.getFpFlag()==2){
				returnName="ingHj";
			}else{
				HjDjVO hjDjVO=new HjDjVO();
		    	hjDjVO.setHjid(jlHjDj.getHjid()+"");
		    	hjDjVO.setJy(jlHjDj.getJy());
		    	hjDjVO.setJqNo(jlHjDj.getJqNo());
		    	if(jlHjDj.getFrName()!=null && !jlHjDj.getFrName().equals("")){
		    		hjDjVO.setFrName(jlHjDj.getFrName());
		    	}
		    	String qsName="";
		    	if(jlHjDj.getQsInfo1()!=null && !jlHjDj.getQsInfo1().equals("")){
		    		qsName+=jlHjDj.getQsInfo1();
		    	}
		    	if(jlHjDj.getQsInfo2()!=null && !jlHjDj.getQsInfo2().equals("")){
		    		qsName+=jlHjDj.getQsInfo2();
		    	}
		    	if(jlHjDj.getQsInfo3()!=null && !jlHjDj.getQsInfo3().equals("")){
		    		qsName+=jlHjDj.getQsInfo3();
		    	}
		    	if(jlHjDj.getQsInfo4()!=null && !jlHjDj.getQsInfo4().equals("")){
		    		qsName+=jlHjDj.getQsInfo4();
		    	}
		    	if(jlHjDj.getQsInfo5()!=null && !jlHjDj.getQsInfo5().equals("")){
		    		qsName+=jlHjDj.getQsInfo5();
		    	}
		    	if(jlHjDj.getQsInfo6()!=null && !jlHjDj.getQsInfo6().equals("")){
		    		qsName+=jlHjDj.getQsInfo6();
		    	}
		    	if(jlHjDj.getQsInfo7()!=null && !jlHjDj.getQsInfo7().equals("")){
		    		qsName+=jlHjDj.getQsInfo7();
		    	}
		    	if(jlHjDj.getQsInfo8()!=null && !jlHjDj.getQsInfo8().equals("")){
		    		qsName+=jlHjDj.getQsInfo8();
		    	}
		    	if(jlHjDj.getQsInfo9()!=null && !jlHjDj.getQsInfo9().equals("")){
		    		qsName+=jlHjDj.getQsInfo9();
		    	}
		    	hjDjVO.setQsInfo1(qsName);
		    	hjDjVO.setHjTime(jlHjDj.getHjTime()/60+"");
		    	hjDjVO.setDjUser(jlHjDj.getDjUser());
		    	hjDjVO.setDjTime(jlHjDj.getDjTime().toString().substring(0, 19));
				request.setAttribute("hjDjVO", hjDjVO);
				String hql="from JlJq";
				List<JlJq> list=hds.searchAll(hql);
				request.setAttribute("list", list);
			}
			return mapping.findForward(returnName);
	}
	public ActionForward qxdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("djId");
			String cancelInfo = java.net.URLDecoder.decode((String)request.getParameter("cancelInfo"),"UTF-8");
			JlHjDj jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
			if(jlHjDj.getFpFlag()==0){
				jlHjDj.setState(2);
				jlHjDj.setCancelInfo(cancelInfo);
				hds.update(jlHjDj);
				String hjSQL="delete from JL_HJ_SP where HJID="+jlHjDj.getHjid()+"";
 				hds.executeUpdate(hjSQL);
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}else if(jlHjDj.getFpFlag()==1){
				String hql="update SysHjLine set hjid=null where hjid="+jlHjDj.getHjid();
				Object[] objects={};
				hds.updates(hql, objects);
				jlHjDj.setState(2);
				jlHjDj.setCancelInfo(cancelInfo);
				hds.update(jlHjDj);
//				String hjSQL="delete from JL_HJ_SP where HJID="+jlHjDj.getHjid()+"";
// 				hds.executeUpdate(hjSQL);
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(1);
				response.getWriter().println(json.toString());
			}
			return null;
	}
	//分配座位
	public ActionForward giveZw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
			JlHjDj jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
			int result=hds.getTypeZw("set_ZW_new",jlHjDj.getFrNo(),jlHjDj.getHjType());
			if(result==0){
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(1);
				response.getWriter().println(json.toString());
			}
			return null;
	}
	public ActionForward showPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    String webId=request.getParameter("qsid");
			String sql="from JlQs where webId="+webId;
			List<JlQs> list=hds.searchAll(sql);
			if(list.size()>0){
				JlQs jlQs=(JlQs)list.get(0);
				if(jlQs.getZp()!=null){
					response.setContentType( "image/jpg");
					response.getOutputStream().write(jlQs.getZp()); 
				}
			}
			return null;
	}
	public ActionForward showPhotoJZ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    String webId=request.getParameter("qsid");
			String sql="from JlQs where webId="+webId;
			List<JlQs> list=hds.searchAll(sql);
			if(list.size()>0){
				JlQs jlQs=(JlQs)list.get(0);
				if(jlQs.getJz()!=null){
//					String savePath=request.getRealPath("/")+"images/";
//					String filename=String.valueOf(System.currentTimeMillis())+".jpg";
//					File file=new File(savePath+filename);
//					OutputStream fos=new FileOutputStream(file);
//					System.out.println("图片文件名称:"+filename);
//					fos.write(jlQs.getJz());
//					fos.close();
					response.setContentType( "image/jpg");
					response.getOutputStream().write(jlQs.getJz()); 
				}
			}
			return null;
	}
	public  ActionForward nextCallNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String windowsIndex=request.getParameter("windowsIndex");
			String callNoServer=request.getParameter("callNoServer");
			String callNoServerUrl = java.net.URLDecoder.decode((String)request.getParameter("callNoServerUrl"),"UTF-8");
			//Thread.sleep(3000*Integer.parseInt(windowsIndex));
			//int acdNo=hds.getAcdNo1("hjdj_acd_set", Integer.parseInt(windowsIndex),callNoServer);
			Acd acd=hds.getAcdNo2("hjdj_acd_set", Integer.parseInt(windowsIndex),callNoServer);
			if(acd!=null){
				List<String> list=new ArrayList<String>();
				List<String> list3=new ArrayList<String>();
				String url=callNoServerUrl+"please.mp3";
				String url1=callNoServerUrl+acd.getAcdNo()+".mp3";
				String url2=callNoServerUrl+"window"+windowsIndex+".mp3";
				list.add(url);
				list.add(url1);
				list.add(url2);
				list3.add(url);
				list3.add(url1);
				list3.add(url2);
				list3.add(url);
				list3.add(url1);
				list3.add(url2);
				//writeFile(list,request.getRealPath("/"));
		        List<HjdjAcdInfo> list1=hds.searchAll("from HjdjAcdInfo");
		        if(list1.size()>0){
		        	HjdjAcdInfo hjdjAcdInfo=(HjdjAcdInfo)list1.get(0);
					CallResult callResult=new CallResult();
					callResult.setAcdNo(hjdjAcdInfo.getAcdgetNo());
					callResult.setAcdCount(hjdjAcdInfo.getAcdsetNo());
					callResult.setList(list3);
					callResult.setRepeatList(list);
					callResult.setShowId(acd.getShowID()+"");
					List<CallResult> list2=new ArrayList<CallResult>();
					list2.add(callResult);
					JSONArray json=JSONArray.fromObject(list2);
					response.getWriter().println(json.toString());
				}
			}else{
				JSONArray jsonArray=JSONArray.fromObject(0);
				response.getWriter().println(jsonArray.toString());
			}
			return null;
	}
	public ActionForward refreshMeetInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			List<HjdjAcdInfo> list=hds.searchAll("from HjdjAcdInfo");
			if(list.size()>0){
				HjdjAcdInfo hjdjAcdInfo=(HjdjAcdInfo)list.get(0);
				CallResult callResult=new CallResult();
				callResult.setAcdNo(hjdjAcdInfo.getAcdgetNo());
				callResult.setAcdCount(hjdjAcdInfo.getAcdsetNo());
				List<CallResult> list1=new ArrayList<CallResult>();
				list1.add(callResult);
				JSONArray json=JSONArray.fromObject(list1);
				response.getWriter().println(json.toString());
			}
			return null;
		
	}
//	public synchronized void writeFile(List list,String dir) throws Exception{
//		Element root = new Element("list");
//	    Document Doc = new Document(root);
//        for (int i = 0; i < list.size(); i++) {
//        	// 创建节点 user;
//        	Element elements = new Element("sound");
//           // 给 user 节点添加属性 id;
//        	elements.setAttribute("id", "" + i);
//        	// 给  节点添加子节点并赋值；
//        	elements.addContent(new Element("url").setText((String)list.get(i)));
//        	// 给父节点list添加子节点;
//        	root.addContent(elements);
//        }
//        XMLOutputter XMLOut = new XMLOutputter();
//        XMLOut.output(Doc, new FileOutputStream(dir+"soundList.xml"));
//	}
	public ActionForward updateHjDj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
			JlHjDj jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(webId));
			jlHjDj.setHjTime(jlHjDj.getHjTime()/60);
			request.setAttribute("jlHjDj", jlHjDj);
			String hql="from JlQs where frNo='"+jlHjDj.getFrNo()+"'";
			List<JlQs> list=hds.searchAll(hql);
			request.setAttribute("listJlQs", list);
			String hql5="from SysParam where paramName='HJ_Client3'";
			List<SysParam> list5=hds.searchAll(hql5);
			if(list5.size()>0){
				SysParam sysParam1=(SysParam)list5.get(0);
				request.getSession().setAttribute("hJClient3", sysParam1);
			}
			return mapping.findForward("updateHjDj");
	}
	//修改保存会见登记信息判断家属卡号
	public ActionForward saveupdateHjDj1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String djId=request.getParameter("djId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjTime=request.getParameter("hjTime");
			String hjInfo = java.net.URLDecoder.decode((String)request.getParameter("hjInfo"),"UTF-8");
			String hjType=request.getParameter("hjType");
			System.out.println(hjType);
			JlHjDj jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(djId));
			jlHjDj.setHjType(Integer.parseInt(hjType));
			if(jlHjDj.getHjType()==1){
				jlHjDj.setHjTime(Integer.parseInt(hjTime)*60);
			}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
				jlHjDj.setHjTime(60*60);
			}else{
				jlHjDj.setHjTime(180*60);
			}
			jlHjDj.setHjInfo(hjInfo);
			jlHjDj.setHjType(Integer.parseInt(hjType));
			if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					if(i==0){
						jlHjDj.setQsInfo1(null);
						jlHjDj.setQsInfo2(null);
						jlHjDj.setQsInfo3(null);
						jlHjDj.setQsInfo4(null);
						jlHjDj.setQsInfo5(null);
						jlHjDj.setQsInfo6(null);
						jlHjDj.setQsInfo7(null);
						jlHjDj.setQsInfo8(null);
						jlHjDj.setQsInfo9(null);
						jlHjDj.setQsCard1(null);
						jlHjDj.setQsCard2(null);
						jlHjDj.setQsCard3(null);
						jlHjDj.setQsCard4(null);
						jlHjDj.setQsCard5(null);
						jlHjDj.setQsCard6(null);
						jlHjDj.setQsCard7(null);
						jlHjDj.setQsCard8(null);
						jlHjDj.setQsCard9(null);
						jlHjDj.setQsZp1(null);
						jlHjDj.setQsZp2(null);
						jlHjDj.setQsZp3(null);
						jlHjDj.setQsZp4(null);
						jlHjDj.setQsZp5(null);
						jlHjDj.setQsZp6(null);
						jlHjDj.setQsZp7(null);
						jlHjDj.setQsZp8(null);
						jlHjDj.setQsZp9(null);
					}
					
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard1(jlQs.getQsCard());
								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard2(jlQs.getQsCard());
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard3(jlQs.getQsCard());
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard4(jlQs.getQsCard());
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard5(jlQs.getQsCard());
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard6(jlQs.getQsCard());
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard7(jlQs.getQsCard());
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard8(jlQs.getQsCard());
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard9(jlQs.getQsCard());
								}
							}
							break;
						default:
							break;
					}
				}
			}
			hds.update(jlHjDj);
			SysParam sysParam=(SysParam)request.getSession().getAttribute("hJClient");
			if(sysParam!=null && sysParam.getParamData3().equals("1")){
				JSONArray json=JSONArray.fromObject(Integer.parseInt(djId));
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(-1);
				response.getWriter().println(json.toString());
			}
		
			return null;
	}
	//修改保存会见登记信息不判断家属卡号
	public ActionForward saveupdateHjDj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String djId=request.getParameter("djId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjTime=request.getParameter("hjTime");
			String hjInfo = java.net.URLDecoder.decode((String)request.getParameter("hjInfo"),"UTF-8");
			String hjType=request.getParameter("hjType");
			JlHjDj jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(djId));
			jlHjDj.setHjType(Integer.parseInt(hjType));
			if(jlHjDj.getHjType()==1){
				jlHjDj.setHjTime(Integer.parseInt(hjTime)*60);
			}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
				jlHjDj.setHjTime(60*60);
			}else{
				jlHjDj.setHjTime(180*60);
			}
			if(jlHjDj.getHjType()!=1){
				jlHjDj.setState(1);
			}
			jlHjDj.setHjInfo(hjInfo);
			jlHjDj.setHjType(Integer.parseInt(hjType));
			
			List<FrQsToABDoor> listFrQsToABDoor = new ArrayList<FrQsToABDoor>();
			
			if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					if(i==0){
						jlHjDj.setQsInfo1(null);
						jlHjDj.setQsInfo2(null);
						jlHjDj.setQsInfo3(null);
						jlHjDj.setQsInfo4(null);
						jlHjDj.setQsInfo5(null);
						jlHjDj.setQsInfo6(null);
						jlHjDj.setQsInfo7(null);
						jlHjDj.setQsInfo8(null);
						jlHjDj.setQsInfo9(null);
						jlHjDj.setQsCard1(null);
						jlHjDj.setQsCard2(null);
						jlHjDj.setQsCard3(null);
						jlHjDj.setQsCard4(null);
						jlHjDj.setQsCard5(null);
						jlHjDj.setQsCard6(null);
						jlHjDj.setQsCard7(null);
						jlHjDj.setQsCard8(null);
						jlHjDj.setQsCard9(null);
						jlHjDj.setQsZp1(null);
						jlHjDj.setQsZp2(null);
						jlHjDj.setQsZp3(null);
						jlHjDj.setQsZp4(null);
						jlHjDj.setQsZp5(null);
						jlHjDj.setQsZp6(null);
						jlHjDj.setQsZp7(null);
						jlHjDj.setQsZp8(null);
						jlHjDj.setQsZp9(null);
					}
					
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard1(jlQs.getQsCard());
								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard2(jlQs.getQsCard());
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard3(jlQs.getQsCard());
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard4(jlQs.getQsCard());
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard5(jlQs.getQsCard());
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard6(jlQs.getQsCard());
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard7(jlQs.getQsCard());
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard8(jlQs.getQsCard());
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard9(jlQs.getQsCard());
								}
							}
							break;
						default:
							break;
					}
				}
			}
			hds.update(jlHjDj);
			
			if(!isNo.trim().equals("")){
				String[] isNo2=isNo.split(",");
				String qsSQL="delete from JL_HJ_DJ_QS where HJID="+jlHjDj.getHjid()+"";
				hds.executeUpdate(qsSQL);
				for(int i=0;i<isNo2.length;i++){

					String hql2="from JlQs where webId="+Integer.parseInt(isNo2[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						default:
							break;
					}
						
						
					
				}
					
			}
			
			SysParam sysParam=(SysParam)request.getSession().getAttribute("hJClient");
			if(sysParam!=null && sysParam.getParamData3().equals("1")){
				JSONArray json=JSONArray.fromObject(Integer.parseInt(djId));
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(-1);
				response.getWriter().println(json.toString());
			}
		
			return null;
	}
	//修改保存会见登记信息判断家属卡号（四川达州监狱）
	public ActionForward saveupdateHjDj12(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String djId=request.getParameter("djId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjTime=request.getParameter("hjTime");
			String hjInfo = java.net.URLDecoder.decode((String)request.getParameter("hjInfo"),"UTF-8");
			String hjType=request.getParameter("hjType");
			JlHjDj jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(djId));
			jlHjDj.setHjType(Integer.parseInt(hjType));
			if(jlHjDj.getHjType()==1){
				jlHjDj.setHjTime(Integer.parseInt(hjTime)*60);
			}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
				jlHjDj.setHjTime(60*60);
			}else{
				jlHjDj.setHjTime(180*60);
			}
			if(jlHjDj.getHjType()!=1){
				jlHjDj.setState(1);
			}
			jlHjDj.setHjInfo(hjInfo);
			jlHjDj.setHjType(Integer.parseInt(hjType));
			
			List<FrQsToABDoor> listFrQsToABDoor = new ArrayList<FrQsToABDoor>();
			
			if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					if(i==0){
						jlHjDj.setQsInfo1(null);
						jlHjDj.setQsInfo2(null);
						jlHjDj.setQsInfo3(null);
						jlHjDj.setQsInfo4(null);
						jlHjDj.setQsInfo5(null);
						jlHjDj.setQsInfo6(null);
						jlHjDj.setQsInfo7(null);
						jlHjDj.setQsInfo8(null);
						jlHjDj.setQsInfo9(null);
						jlHjDj.setQsCard1(null);
						jlHjDj.setQsCard2(null);
						jlHjDj.setQsCard3(null);
						jlHjDj.setQsCard4(null);
						jlHjDj.setQsCard5(null);
						jlHjDj.setQsCard6(null);
						jlHjDj.setQsCard7(null);
						jlHjDj.setQsCard8(null);
						jlHjDj.setQsCard9(null);
						jlHjDj.setQsZp1(null);
						jlHjDj.setQsZp2(null);
						jlHjDj.setQsZp3(null);
						jlHjDj.setQsZp4(null);
						jlHjDj.setQsZp5(null);
						jlHjDj.setQsZp6(null);
						jlHjDj.setQsZp7(null);
						jlHjDj.setQsZp8(null);
						jlHjDj.setQsZp9(null);
					}
					
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard1(jlQs.getQsCard());
								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard2(jlQs.getQsCard());
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard3(jlQs.getQsCard());
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard4(jlQs.getQsCard());
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard5(jlQs.getQsCard());
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard6(jlQs.getQsCard());
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard7(jlQs.getQsCard());
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard8(jlQs.getQsCard());
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsCard()==null || jlQs.getQsCard().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsCard()!=null){
									jlHjDj.setQsCard9(jlQs.getQsCard());
								}
							}
							break;
						default:
							break;
					}
				}
			}
			hds.update(jlHjDj);
			
			if(!isNo.trim().equals("")){
				String[] isNo2=isNo.split(",");
				String qsSQL="delete from JL_HJ_DJ_QS where HJID="+jlHjDj.getHjid()+"";
				hds.executeUpdate(qsSQL);
				for(int i=0;i<isNo2.length;i++){

					String hql2="from JlQs where webId="+Integer.parseInt(isNo2[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								jlHjDjQs.setFrNo(jlQs.getFrNo());
								jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
								jlHjDjQs.setQsSfz(jlQs.getQsSfz());
								jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
								jlHjDjQs.setQsName(jlQs.getQsName());
								jlHjDjQs.setQsCard(jlQs.getQsCard());
								jlHjDjQs.setGx(jlQs.getGx());
								jlHjDjQs.setXb(jlQs.getXb());
								jlHjDjQs.setDz(jlQs.getDz());
								jlHjDjQs.setTele(jlQs.getTele());
								jlHjDjQs.setZp(jlQs.getZp());
								jlHjDjQs.setJz(jlQs.getJz());
								hds.save(jlHjDjQs);
							}
							break;
						default:
							break;
					}
						
						
					
				}
					
			}
			
			SysParam sysParam=(SysParam)request.getSession().getAttribute("hJClient");
			if(sysParam!=null && sysParam.getParamData3().equals("1")){
				JSONArray json=JSONArray.fromObject(Integer.parseInt(djId));
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(-1);
				response.getWriter().println(json.toString());
			}
		
			return null;
	}
	//修改保存会见登记信息判断身份证物理号（重庆九龙联动门禁）
	public ActionForward saveupdateHjDj11(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String djId=request.getParameter("djId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjTime=request.getParameter("hjTime");
			String hjInfo = java.net.URLDecoder.decode((String)request.getParameter("hjInfo"),"UTF-8");
			String hjType=request.getParameter("hjType");
			JlHjDj jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(djId));
			jlHjDj.setHjType(Integer.parseInt(hjType));
			if(jlHjDj.getHjType()==1){
				jlHjDj.setHjTime(Integer.parseInt(hjTime)*60);
			}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
				jlHjDj.setHjTime(60*60);
			}else{
				jlHjDj.setHjTime(180*60);
			}
			jlHjDj.setHjInfo(hjInfo);
			jlHjDj.setHjType(Integer.parseInt(hjType));
			
			List<FrQsToABDoor> listFrQsToABDoor = new ArrayList<FrQsToABDoor>();
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String StartTime=sdf.format(date);
			String EndTime=sdf.format(new Date(date.getTime()+Integer.parseInt(Constant.shixiaoshijian) * 60 * 1000));
			
			if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					if(i==0){
						jlHjDj.setQsInfo1(null);
						jlHjDj.setQsInfo2(null);
						jlHjDj.setQsInfo3(null);
						jlHjDj.setQsInfo4(null);
						jlHjDj.setQsInfo5(null);
						jlHjDj.setQsInfo6(null);
						jlHjDj.setQsInfo7(null);
						jlHjDj.setQsInfo8(null);
						jlHjDj.setQsInfo9(null);
						jlHjDj.setQsCard1(null);
						jlHjDj.setQsCard2(null);
						jlHjDj.setQsCard3(null);
						jlHjDj.setQsCard4(null);
						jlHjDj.setQsCard5(null);
						jlHjDj.setQsCard6(null);
						jlHjDj.setQsCard7(null);
						jlHjDj.setQsCard8(null);
						jlHjDj.setQsCard9(null);
						jlHjDj.setQsZp1(null);
						jlHjDj.setQsZp2(null);
						jlHjDj.setQsZp3(null);
						jlHjDj.setQsZp4(null);
						jlHjDj.setQsZp5(null);
						jlHjDj.setQsZp6(null);
						jlHjDj.setQsZp7(null);
						jlHjDj.setQsZp8(null);
						jlHjDj.setQsZp9(null);
					}
					
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard1(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard2(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard3(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard4(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard5(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard6(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard7(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard8(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz())){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								if(jlQs.getSpState()==0){
									JSONArray json=JSONArray.fromObject(-8);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard9(jlQs.getQsSfzWlh());
									
								}
							}
							break;
						default:
							break;
					}
				}
			}
			hds.update(jlHjDj);
			
			if(!isNo.trim().equals("")){
				String[] isNo2=isNo.split(",");
				String qsSQL="delete from JL_HJ_DJ_QS where HJID="+jlHjDj.getHjid()+"";
				hds.executeUpdate(qsSQL);
				for(int i=0;i<isNo2.length;i++){

					String hql2="from JlQs where webId="+Integer.parseInt(isNo2[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						default:
							break;
					}
						
						
					
				}
					
			}
			
			SysParam sysParam=(SysParam)request.getSession().getAttribute("hJClient");
			if(sysParam!=null && sysParam.getParamData3().equals("1")){
				JSONArray json=JSONArray.fromObject(listFrQsToABDoor);
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(-1);
				response.getWriter().println(json.toString());
			}
		
			return null;
	}
	//修改保存会见登记信息判断身份证物理号（云南第一强戒所联动门禁）
	public ActionForward saveupdateHjDj111(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String djId=request.getParameter("djId");
			String isNo = java.net.URLDecoder.decode((String)request.getParameter("isNo"),"UTF-8");
			String hjTime=request.getParameter("hjTime");
			String hjInfo = java.net.URLDecoder.decode((String)request.getParameter("hjInfo"),"UTF-8");
			String hjType=request.getParameter("hjType");
			JlHjDj jlHjDj=(JlHjDj)hds.findById(JlHjDj.class, Long.parseLong(djId));
			jlHjDj.setHjType(Integer.parseInt(hjType));
			if(jlHjDj.getHjType()==1){
				jlHjDj.setHjTime(Integer.parseInt(hjTime)*60);
			}else if(jlHjDj.getHjType()>1 && jlHjDj.getHjType()<5){
				jlHjDj.setHjTime(60*60);
			}else{
				jlHjDj.setHjTime(180*60);
			}
			jlHjDj.setHjInfo(hjInfo);
			jlHjDj.setHjType(Integer.parseInt(hjType));
			
			List<FrQsToABDoor> listFrQsToABDoor = new ArrayList<FrQsToABDoor>();
			
			//门禁权限所需要的数据
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String StartTime=sdf.format(date);
			String EndTime=sdf.format(new Date(date.getTime()+Integer.parseInt(Constant.shixiaoshijian) * 60 * 1000));
			
			if(!isNo.trim().equals("")){
				String[] isNo1=isNo.split(",");
				for(int i=0;i<isNo1.length;i++){
					if(i==0){
						jlHjDj.setQsInfo1(null);
						jlHjDj.setQsInfo2(null);
						jlHjDj.setQsInfo3(null);
						jlHjDj.setQsInfo4(null);
						jlHjDj.setQsInfo5(null);
						jlHjDj.setQsInfo6(null);
						jlHjDj.setQsInfo7(null);
						jlHjDj.setQsInfo8(null);
						jlHjDj.setQsInfo9(null);
						jlHjDj.setQsCard1(null);
						jlHjDj.setQsCard2(null);
						jlHjDj.setQsCard3(null);
						jlHjDj.setQsCard4(null);
						jlHjDj.setQsCard5(null);
						jlHjDj.setQsCard6(null);
						jlHjDj.setQsCard7(null);
						jlHjDj.setQsCard8(null);
						jlHjDj.setQsCard9(null);
						jlHjDj.setQsZp1(null);
						jlHjDj.setQsZp2(null);
						jlHjDj.setQsZp3(null);
						jlHjDj.setQsZp4(null);
						jlHjDj.setQsZp5(null);
						jlHjDj.setQsZp6(null);
						jlHjDj.setQsZp7(null);
						jlHjDj.setQsZp8(null);
						jlHjDj.setQsZp9(null);
					}
					
					String hql2="from JlQs where webId="+Integer.parseInt(isNo1[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo1(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp1(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard1(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);

									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo2(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp2(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard2(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);

									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo3(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp3(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard3(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);

									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo4(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp4(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard4(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);

									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo5(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp5(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard5(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);

									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo6(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp6(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard6(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);

									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo7(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp7(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard7(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);

									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo8(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp8(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard8(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);

									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								if(null==jlQs.getQsSfz() || "".equals(jlQs.getQsSfz()) || jlQs.getQsSfzWlh()==null || jlQs.getQsSfzWlh().equals("")){
									JSONArray json=JSONArray.fromObject(-5);
									response.getWriter().println(json.toString());
									return null;
								}
								if(null==jlQs.getGx() || "".equals(jlQs.getGx())){
									JSONArray json=JSONArray.fromObject(-7);
									response.getWriter().println(json.toString());
									return null;
								}
								
								FrQsToABDoor frQsToABDoor = new FrQsToABDoor();
								frQsToABDoor.setWebId(jlQs.getWebId());
								listFrQsToABDoor.add(frQsToABDoor);
								
								String name="";
								if(jlQs.getGx()!=null && !jlQs.getGx().trim().equals("")){
									name+="["+jlQs.getGx()+"]";
								}
								if(jlQs.getQsName()!=null){
									name+=jlQs.getQsName();
								}
								jlHjDj.setQsInfo9(name);
								if(jlQs.getJz()!=null){
									jlHjDj.setQsZp9(jlQs.getJz());
								}
								if(jlQs.getQsSfzWlh()!=null){
									jlHjDj.setQsCard9(jlQs.getQsSfzWlh());
									int n=6;
									String bb=jlQs.getQsSfzWlh().substring(jlQs.getQsSfzWlh().length()-n,jlQs.getQsSfzWlh().length());
									int qswlh=Integer.parseInt(bb,16);

									//设置门禁卡的权限
									if(Constant.dbIP!=null && Constant.dbuserName!=null && Constant.dbpassword!=null && Constant.dbName!=null){

										  Connection con=Pool.getcon(Constant.dbIP, Constant.dbuserName, Constant.dbpassword, Constant.dbName);
										  if(con!=null){
//											  PreparedStatement ps1=null;
//											  PreparedStatement ps2=null;
											  PreparedStatement ps3=null;
											  PreparedStatement ps4=null;
											  PreparedStatement ps5=null;
											  PreparedStatement ps6=null;
											  PreparedStatement ps7=null;
											  PreparedStatement ps8=null;
											  PreparedStatement ps9=null;
											  PreparedStatement ps10=null;
											  PreparedStatement ps11=null;
											  PreparedStatement ps12=null;
											  PreparedStatement ps13=null;
											  PreparedStatement ps14=null;
											  PreparedStatement ps15=null;
											  PreparedStatement ps16=null;
											  PreparedStatement ps17=null;
											  try {
//												  ps1=con.prepareStatement("SELECT   TOP (1) CallerID  FROM  Cr_Caller_BasicInfo where (1=1) order by callerid desc");
//												  ResultSet rs1=ps1.executeQuery();
//												  while(rs1.next()){
//													  if(!rs1.getString(1).equals("")){
//														  System.out.println(rs1.getString(1));
//														  int a = Integer.parseInt(rs1.getString(1));
//														  int b = a+1;
//														  ps2=con.prepareStatement("insert into Cr_Caller_BasicInfo(CallerID, LoginTime, CallerName, CallerIDType, CallerIDNo, " +
//														  		"EmpNo, EmpName, DptName, CallReason, CallerTakenGoods, PlanOutTime, AuthorRegions, CallerCrtdBy, CallerCrtdDay, CardStatusID, " +
//														  		"CallerSex, CallerAddress, CalleraCarInfo, CallerPartys, CardFixNo, AuthorHours)" +
//														     		" values("+b+",'"+StartTime+"','"+jlQs.getQsName()+"','二代证','"+jlQs.getQsSfz()+"',1,1,'用户名称（可修改）','探访','无','"+EndTime+"','1','system','"+StartTime+"','20','"+jlQs.getXb()+"','"+jlQs.getDz()+"','无','0','8D606155','2')");
//														  ps2.executeUpdate();
//													  }else{
//														  System.out.println("1="+rs1.getString(1));
//													  }
//													  
//												  }
												  ps17=con.prepareStatement("delete from NDr2_AuthorSet1 where CardID="+qswlh);
												  ps17.executeUpdate();
												  ps3=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps3.executeUpdate();
												  ps4=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",4,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps4.executeUpdate();
												  ps5=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps5.executeUpdate();
												  ps6=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",5,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps6.executeUpdate();
												  ps7=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps7.executeUpdate();
												  ps8=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",6,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps8.executeUpdate();
												  ps9=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps9.executeUpdate();
												  ps10=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",8,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps10.executeUpdate();
												  ps11=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps11.executeUpdate();
												  ps12=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",10,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps12.executeUpdate();
												  ps13=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps13.executeUpdate();
												  ps14=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",11,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps14.executeUpdate();
												  ps15=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+StartTime+"')");
												  ps15.executeUpdate();
												  ps16=con.prepareStatement("insert into NDr2_AuthorSet1( CardID,DoorID,PassWord,DueDate,AuthorType,AuthorStatus,UserTimeGrp,DownLoaded,FirstDownLoaded,PreventCard,StartTime)" +
														  " values("+qswlh+",12,'"+Constant.PassWord+"','"+Constant.DueDate+"',"+Constant.AuthorType+","+Constant.AuthorStatus+","+Constant.UserTimeGrp+","+Constant.DownLoaded+","+Constant.FirstDownLoaded+","+Constant.PreventCard+",'"+EndTime+"')");
												  ps16.executeUpdate();
												  
											  }catch (SQLException e) {
													e.printStackTrace();
													JSONArray json=JSONArray.fromObject(-10);
													response.getWriter().println(json.toString());
													return null;
											  }finally{
												  con.close();
//												  ps1.close();
//												  ps2.close();
												  ps3.close();
												  ps4.close();
												  ps5.close();
												  ps6.close();
												  ps7.close();
												  ps8.close();
												  ps9.close();
												  ps10.close();
												  ps11.close();
												  ps12.close();
												  ps13.close();
												  ps14.close();
												  ps15.close();
												  ps16.close();
												  ps17.close();
											  }
										  }else{
											  JSONArray json=JSONArray.fromObject(-9);
											  response.getWriter().println(json.toString());
											  return null;
										  }
									}
								}
							}
							break;
						default:
							break;
					}
				}
			}
			hds.update(jlHjDj);
			
			if(!isNo.trim().equals("")){
				String[] isNo2=isNo.split(",");
				String qsSQL="delete from JL_HJ_DJ_QS where HJID="+jlHjDj.getHjid()+"";
				hds.executeUpdate(qsSQL);
				for(int i=0;i<isNo2.length;i++){

					String hql2="from JlQs where webId="+Integer.parseInt(isNo2[i]);
					List<JlQs> list2=hds.searchAll(hql2);
					switch (i) {
						case 0:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 1:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 2:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 3:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 4:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 5:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 6:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 7:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						case 8:
							if(list2.size()>0){
								JlQs jlQs=list2.get(0);
								
								JlHjDjQs jlHjDjQs = new JlHjDjQs();
								jlHjDjQs.setQsId(jlQs.getWebId());
								jlHjDjQs.setHjId(jlHjDj.getHjid());
								hds.save(jlHjDjQs);
							}
							break;
						default:
							break;
					}
						
						
					
				}
					
			}
			
			SysParam sysParam=(SysParam)request.getSession().getAttribute("hJClient");
			if(sysParam!=null && sysParam.getParamData3().equals("1")){
				JSONArray json=JSONArray.fromObject(Integer.parseInt(djId));
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(-1);
				response.getWriter().println(json.toString());
			}
		
			return null;
	}
	//修改亲属
	public ActionForward updateFrQs1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
//			String frNo=request.getParameter("frNo");

			String hql="from JlQs jl where webId=?";
			Object[] obj={Integer.parseInt(webId)};
			List<JlQs> list1=hds.searchAll(hql, obj);
			JlQs jlQs=(JlQs)list1.get(0);
			List<Integer> qjswList=new ArrayList();
			for(int i=1;i<=9;i++){
				qjswList.add(i);
			}
			List<Integer> unswList=new ArrayList();
			unswList=qjswList;
			StringBuffer str1=new StringBuffer(" from JlQs jl where jl.frNo='");
			str1.append(jlQs.getFrNo()+"'");
			StringBuffer str=new StringBuffer(" from JlFr jl where jl.frNo='");
			str.append(jlQs.getFrNo()+"'");
			List<JlFr> list=hds.searchAll(str.toString());
			JlFr jlFr=(JlFr)list.get(0);
			List<JlQs> swlist=hds.searchAll(str1.toString());
			if(swlist!=null){
				for(int i=0;i<unswList.size();i++)
					for(int j=0;j<swlist.size();j++){
						JlQs jlqs=(JlQs)swlist.get(j);
						if(jlqs.getSw()!=null){
							if(jlqs.getSw().intValue()==unswList.get(i)){
								unswList.remove(i);
								i=0;
							}
						}
					}
			}
			List<String> qsList=new ArrayList();
			String sqlGx="select QS_GX from JL_QS_GX";
			List<String> qsList1 =hds.searchAllBySql(sqlGx);
			for(int i=0;i<qsList1.size();i++){
				qsList.add(qsList1.get(i));
			}
			String hql4="from SysParam where paramName='HJ_Client'";
			List<SysParam> list4=hds.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("sysParam", sysParam1);
			}
			String hql5="from SysUser where userNo='"+user.getUserNo()+"'";
			List<SysUser> list5=hds.searchAll(hql5);
			if(list5.size()>0){
				SysUser sysUser=(SysUser)list5.get(0);
				request.setAttribute("sysUser", sysUser);
			}
			request.setAttribute("qsGxList",qsList);
			request.setAttribute("unswList",unswList);
			request.setAttribute("jlFr", jlFr);
			request.setAttribute("jlQs", jlQs);
			return mapping.findForward("updateFrQs1");
	}
	
	public ActionForward tzHandle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			Timestamp tzSj1 = new Timestamp(System.currentTimeMillis());
			Timestamp tzSx1 = new Timestamp(System.currentTimeMillis()+(Integer.parseInt("300")*60*1000));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String tzSj = sdf.format(tzSj1);
			String tzSx = sdf.format(tzSx1);
			String sql="update JL_JQ set TZ_Handle='1', TZ_SJ='"+tzSj+"', TZ_SX='"+tzSx+"', TZ_Handle1='1', TZ_SJ1='"+tzSj+"', TZ_SX1='"+tzSx+"' ";
			hds.executeUpdate(sql);
	 	    JSONArray json=JSONArray.fromObject(1);
	 	    response.getWriter().println(json.toString());
	 	    return null;
	}
	
	public ActionForward tohjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
			String hjid=request.getParameter("hjid");
			JlHjDj jlHjDj=null;
			String hql="from JlHjDj where HJID='"+hjid+"' and state=3";
			List<JlHjDj> list=hds.searchAll(hql);
			if(list.size()>0){
				jlHjDj=(JlHjDj)list.get(0);
			}else{
				return null;
			}
			HJSP hjsp = new HJSP();
			hjsp.setJqName(jlHjDj.getJqName());
			hjsp.setFrNo(jlHjDj.getFrNo());
			hjsp.setFrName(jlHjDj.getFrName());
			hjsp.setHjid(jlHjDj.getHjid()+"");
			
			//根据会见ID查询审批表
			String sql1="select SPID,SP_JB from JL_HJ_SP where HJID='"+jlHjDj.getHjid()+"'";
			List list1 = hds.searchAllBySql(sql1);
			if(list1.size()>0){
				Object[] obj =(Object[]) list1.get(0);
				if(obj[0]!=null && !obj[0].toString().trim().equals("")){
					hjsp.setSpId(obj[0].toString().trim());
				}
				if(obj[1]!=null && !obj[1].toString().trim().equals("")){
					hjsp.setSpJb(obj[1].toString().trim());
				}
			}
			request.setAttribute("hjsp", hjsp);
			
			//查询审批部门
			List<HJSPBM> bmlist = new ArrayList<HJSPBM>();
//			String sqlbm="select Group_No,Group_Name from SYS_USER_GROUP where Is_SP_Depart=1 and SP_QX_JB='"+(Integer.parseInt(hjsp.getSpJb())+1)+"'";
			String sqlbm="select Group_No,Group_Name from SYS_USER_GROUP where Is_SP_Depart=1 and SP_QX_JB>0";
			List listbm = hds.searchAllBySql(sqlbm);
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
			List listfr =hds.searchAllBySql(sqlfr);
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
			String sql2="select SPID,QS_Info,Special,SP_Remarks,QS_Annex,QS_SFZ,QS_XB,QS_ZP,SP_Reason,WebID,Face_State,Face_ID,QS_ZJLB,SPBZ from JL_HJ_SP_QS where SPID='"+hjsp.getSpId()+"'";
			List list2 = hds.searchAllBySql(sql2);
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
						hjspqs.setFaceState(obj[10].toString().trim());
					}
					if(obj[11]!=null && !obj[11].toString().trim().equals("")){
						hjspqs.setFaceId(obj[11].toString().trim());
					}
					if(obj[12]!=null && !obj[12].toString().trim().equals("")){
						hjspqs.setQsZjlb(obj[12].toString().trim());
					}
					if(obj[13]!=null && !obj[13].toString().trim().equals("")){
						hjspqs.setSpbz(obj[13].toString().trim());
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
	 		List list=hds.searchAllBySql(sql);
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
	//跳转到添加特批亲属页面
	public ActionForward toaddtpqs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user=(SysUser)request.getSession().getAttribute("users");
	    if(user==null){
	    	return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String hjid = request.getParameter("hjid");
		HJSPQS hjspqs = new HJSPQS();
		hjspqs.setWebId(webId);
		hjspqs.setHjid(hjid);
		
		List<String> qsList=new ArrayList();
		String sqlGx="select QS_GX from JL_QS_GX";
		List<String> qsList1 =hds.searchAllBySql(sqlGx);
		for(int i=0;i<qsList1.size();i++){
			qsList.add(qsList1.get(i));
		}
		String hql5="from SysParam where paramName='HJ_Client3'";
		List<SysParam> list5=hds.searchAll(hql5);
		if(list5.size()>0){
			SysParam sysParam1=(SysParam)list5.get(0);
			request.getSession().setAttribute("hJClient3", sysParam1);
		}
		request.setAttribute("hjspqs", hjspqs);
		request.setAttribute("qsList", qsList);
		return mapping.findForward("toaddtpqs");
	}
	//保存修改的特批亲属信息
	public ActionForward savetpqs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user=(SysUser)request.getSession().getAttribute("users");
	    if(user==null){
	    	return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String hjid = request.getParameter("hjid");
		String qsSfz = request.getParameter("qsSfz");
		String qsCard = request.getParameter("qsCard");
		if(qsCard!=null && !qsCard.trim().equals("")){
			String sql12="update JL_QS set QS_CARD=null WHERE QS_Card='"+qsCard+"'";
			hds.executeUpdate(sql12);
		}		
		String qsName = request.getParameter("qsName");
		String photoAddress = request.getParameter("photoAddress");
		String spbz = request.getParameter("spbz");
		String gx = request.getParameter("gx");
		String spyy = java.net.URLDecoder.decode((String)request.getParameter("spyy"),"UTF-8");
		String xb = request.getParameter("xb");
		String zjlb = request.getParameter("zjlb");
		String dz = request.getParameter("dz");
		JlHjSpQs jlhjspqs = (JlHjSpQs) hds.findById(JlHjSpQs.class, Integer.parseInt(webId.trim()));
		JlQs jlQs = new JlQs();
		String sql ="select * from JL_HJ_SP sp,JL_HJ_SP_QS qs where sp.SPID=qs.SPID and sp.HJID='"+hjid.trim()+"' and qs.QS_SFZ='"+qsSfz.trim()+"'";
		List list =hds.searchAllBySql(sql);
		if(list.size()>0){
			JSONArray json=JSONArray.fromObject(-1);
		 	response.getWriter().println(json.toString());
			return null;
		}
		String hql="from JlHjDj where hjid="+hjid;
		List<JlHjDj> hjdjlList = hds.searchAll(hql);
		JlHjDj jlHjDj =hjdjlList.get(0);
		if(qsCard!=null && !qsCard.trim().equals("")){
			if(jlHjDj.getQsCard1()==null || jlHjDj.getQsCard1().equals("")){
				jlHjDj.setQsCard1(qsCard);
			}else if(jlHjDj.getQsCard2()==null || jlHjDj.getQsCard2().equals("")){
				jlHjDj.setQsCard2(qsCard);
			}else if(jlHjDj.getQsCard3()==null || jlHjDj.getQsCard3().equals("")){
				jlHjDj.setQsCard3(qsCard);
			}else if(jlHjDj.getQsCard4()==null || jlHjDj.getQsCard4().equals("")){
				jlHjDj.setQsCard4(qsCard);
			}else if(jlHjDj.getQsCard5()==null || jlHjDj.getQsCard5().equals("")){
				jlHjDj.setQsCard5(qsCard);
			}else if(jlHjDj.getQsCard6()==null || jlHjDj.getQsCard6().equals("")){
				jlHjDj.setQsCard6(qsCard);
			}else if(jlHjDj.getQsCard7()==null || jlHjDj.getQsCard7().equals("")){
				jlHjDj.setQsCard7(qsCard);
			}else if(jlHjDj.getQsCard8()==null || jlHjDj.getQsCard8().equals("")){
				jlHjDj.setQsCard8(qsCard);
			}else if(jlHjDj.getQsCard9()==null || jlHjDj.getQsCard9().equals("")){
				jlHjDj.setQsCard9(qsCard);
			}
		}
		jlQs.setFrNo(jlHjDj.getFrNo());
		if(qsSfz!=null && !qsSfz.trim().equals("")){
			jlhjspqs.setQsSfz(qsSfz.trim());
			jlQs.setQsSfz(qsSfz.trim());
		}
		if(qsName!=null && !qsName.trim().equals("")){
			String str="["+gx.trim()+"]"+qsName.trim();
			jlhjspqs.setQsInfo(str);
			jlQs.setQsName(qsName.trim());
			jlQs.setGx(gx.trim());
			if(jlHjDj.getQsInfo1()==null || jlHjDj.getQsInfo1().equals("")){
				jlHjDj.setQsInfo1(str);
			}else if(jlHjDj.getQsInfo2()==null || jlHjDj.getQsInfo2().equals("")){
				jlHjDj.setQsInfo2(str);
			}else if(jlHjDj.getQsInfo3()==null || jlHjDj.getQsInfo3().equals("")){
				jlHjDj.setQsInfo3(str);
			}else if(jlHjDj.getQsInfo4()==null || jlHjDj.getQsInfo4().equals("")){
				jlHjDj.setQsInfo4(str);
			}else if(jlHjDj.getQsInfo5()==null || jlHjDj.getQsInfo5().equals("")){
				jlHjDj.setQsInfo5(str);
			}else if(jlHjDj.getQsInfo6()==null || jlHjDj.getQsInfo6().equals("")){
				jlHjDj.setQsInfo6(str);
			}else if(jlHjDj.getQsInfo7()==null || jlHjDj.getQsInfo7().equals("")){
				jlHjDj.setQsInfo7(str);
			}else if(jlHjDj.getQsInfo8()==null || jlHjDj.getQsInfo8().equals("")){
				jlHjDj.setQsInfo8(str);
			}else if(jlHjDj.getQsInfo9()==null || jlHjDj.getQsInfo9().equals("")){
				jlHjDj.setQsInfo9(str);
			}
			
			hds.update(jlHjDj);
		}
		if(xb!=null && !xb.trim().equals("")){
			jlhjspqs.setQsXb(xb.trim());
			jlQs.setXb(xb.trim());
		}
		System.out.println("zjlb="+zjlb);
		if(zjlb!=null && !zjlb.trim().equals("")){
			jlhjspqs.setQsZjlb(Integer.parseInt(zjlb));
			jlQs.setQsZjlb(Integer.parseInt(zjlb));
		}
		if(dz!=null && !dz.trim().equals("")){
			jlhjspqs.setQsDZ(dz.trim());
			jlQs.setDz(dz.trim());
		}
		if(!photoAddress.trim().equals("")){
	 		String khdIp=request.getRemoteAddr();
	 		String url1="http://"+khdIp+":9009/"+photoAddress.replace(":", "").replace("\\", "/");
	 		URL url=new URL(url1);
	 		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	 		if(connection.getResponseCode()==200){
	 			InputStream fis = url.openStream();
	 			Thread.sleep(1000);
	 			byte[] byte1 = new byte[fis.available()];
	 			fis.read(byte1);
	 			jlhjspqs.setQsZp(byte1);
	 			jlQs.setZp(byte1);
	 		}
	 	}
		if(spyy!=null && !spyy.equals("")){
			jlhjspqs.setSpReason(spyy);
		}
		if(spbz!=null && !spbz.equals("")){
			jlhjspqs.setSpbz(spbz);
		}
		jlQs.setSpState(0);
		jlQs.setQsCard(qsCard);
		jlQs.setFaceState(0);
		jlQs.setCreateTime(new Timestamp(System.currentTimeMillis()));
		//将身份证照片传输至服务器指定文件夹
	 	String zpUrl = "http://"+request.getRemoteAddr()+":9009/C/"+qsSfz+".Jpg";
 	    String zpName = qsSfz+".Jpg";
 	    
 	    boolean a = isNetFileAvailable(zpUrl);
//	 	System.out.println(a);				 	
	 	if(!a){
//            throw new RuntimeException("文件不存在..");
	 		System.out.println("身份证图片文件不存在");
	 	
	 	}else{
	 		download(zpUrl,zpName,"D:\\Photo\\");
	 	    String zpUrl1="";
	 	    
	 	    String hql5="from SysParam where paramName='HJ_Client3'";
			List<SysParam> list5=hds.searchAll(hql5);
			SysParam sysParam1=(SysParam)list5.get(0);						
			zpUrl1=sysParam1.getParamData3()+qsSfz+".Jpg";
			jlQs.setZpUrl(zpUrl1);
	 	}
		hds.update(jlhjspqs);
		hds.save(jlQs);
		
		JlHjDjQs jlHjDjQs = new JlHjDjQs();
		jlHjDjQs.setQsId(jlQs.getWebId());
		jlHjDjQs.setHjId(jlHjDj.getHjid());
		jlHjDjQs.setFrNo(jlQs.getFrNo());
		jlHjDjQs.setQsZjlb(jlQs.getQsZjlb());
		jlHjDjQs.setQsSfz(jlQs.getQsSfz());
		jlHjDjQs.setQsSfzWlh(jlQs.getQsSfzWlh());
		jlHjDjQs.setQsName(jlQs.getQsName());
		jlHjDjQs.setQsCard(jlQs.getQsCard());
		jlHjDjQs.setGx(jlQs.getGx());
		jlHjDjQs.setXb(jlQs.getXb());
		jlHjDjQs.setDz(jlQs.getDz());
		jlHjDjQs.setTele(jlQs.getTele());
		jlHjDjQs.setZp(jlQs.getZp());
		jlHjDjQs.setJz(jlQs.getJz());
		hds.save(jlHjDjQs);
		
		JSONArray json=JSONArray.fromObject(Integer.parseInt(hjid.trim()));
	 	response.getWriter().println(json.toString());
		return null;
	}
	//跳转到添加正常家属审批原因
	public ActionForward toaddspyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user=(SysUser)request.getSession().getAttribute("users");
	    if(user==null){
	    	return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String hjid = request.getParameter("hjid");
		HJSPQS hjspqs = new HJSPQS();
		hjspqs.setWebId(webId);
		hjspqs.setHjid(hjid);
		
		request.setAttribute("hjspqs", hjspqs);

		return mapping.findForward("toaddspyy");
	}
	//保存修改的正常家属审批原因
	public ActionForward updatespyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user=(SysUser)request.getSession().getAttribute("users");
	    if(user==null){
	    	return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String hjid = request.getParameter("hjid");

		String spyy = java.net.URLDecoder.decode((String)request.getParameter("spyy"),"UTF-8");

		String spbz = java.net.URLDecoder.decode((String)request.getParameter("spbz"),"UTF-8");

		String sql12="update JL_HJ_SP_QS set SP_Reason='"+spyy+ "',SPBZ='"+spbz+"' WHERE WebID="+webId+"";
		hds.executeUpdate(sql12);

		JSONArray json=JSONArray.fromObject(Integer.parseInt(hjid.trim()));
	 	response.getWriter().println(json.toString());
		return null;
	}
	//提交会见审批
	public ActionForward tjhjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user=(SysUser)request.getSession().getAttribute("users");
	    if(user==null){
	    	return mapping.findForward("sessionFailure");
	    }
		String spid = request.getParameter("spid");
		String spbm = request.getParameter("spbm");
		String spUser = request.getParameter("spUser");
		String hql="from SysUser where userNo='"+spUser+"'";
	 	List<SysUser> list=hds.searchAll(hql);
	 	String hql11="from SysUserGroup where groupNo='"+spbm+"'";
 		List<SysUserGroup> list1=hds.searchAll(hql11);
		if(spbm!=null && !spbm.trim().equals("null") && spUser!=null && !spUser.trim().equals("null")){
			String sql="update JL_HJ_SP set SP_State=1,SP_Group_No='"+spbm.trim()+"',SP_User='"+spUser+"',SP_UserName='"+list.get(0).getUserName()+"',SP_JB="+list1.get(0).getSpQxJb()+" where SPID="+spid;
			hds.executeUpdate(sql);
			JSONArray json=JSONArray.fromObject(1);
		 	response.getWriter().println(json.toString());
			return null;
		}else{
			JSONArray json=JSONArray.fromObject(0);
		 	response.getWriter().println(json.toString());
			return null;
		}
		
	}
	public ActionForward addFace(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
	    if(sysUser==null){
		   return mapping.findForward("sessionFailure");
	    }
		String frNo = java.net.URLDecoder.decode((String)request.getParameter("frNo"),"UTF-8");
		String webId = request.getParameter("webId");
		
		JlQs jlQs=(JlQs)hds.findById(JlQs.class, Integer.parseInt(webId));
		
		boolean flag=false;
		Long begin=System.currentTimeMillis();
		Long end=begin+50000;
		Long begining=begin;
		//判断当前登记电脑是否允许注册人脸
		System.out.println("request.getRemoteAddr()= "+request.getRemoteAddr());
		StringBuffer str11=new StringBuffer("from HjdjFaceInfo hj where hj.enrollFacePcIp='"+request.getRemoteAddr()+"'");
		List<HjdjFaceInfo> list11=hds.searchAll(str11.toString());
		if(list11.size()<1){
			JSONArray json=JSONArray.fromObject(5);
			response.getWriter().println(json.toString());
			return null;
		}else{
			HjdjFaceInfo hjdjFaceInfo=(HjdjFaceInfo)list11.get(0);
			//向注册人脸设备发送开始注册指令
			long id = 0;
			IFaceClient faceClient = new TFaceClient();
		    boolean isConnect = faceClient.connect("http://127.0.0.1:8080/PinnacleFace");
		    System.out.println("连接人脸注册系统状态："+isConnect);
		    if(isConnect)
		    {
//		    	boolean a = faceClient.clearDeviceInfo("1070000034765", true);
		        id = faceClient.startEnrollFace(hjdjFaceInfo.getEnrollFaceId(), "http://127.0.0.1:9009/HjSystem/MyServlet");
		        System.out.println("注册命令任务号："+id);
		    }else{
		    	JSONArray json=JSONArray.fromObject(6);
				response.getWriter().println(json.toString());
				return null;
		    }
		    if(id == 0){
		    	JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
				return null;
		    }
		    if(id != 0){
		    	String hql5="update JlQs set faceId=null where faceId="+id+"";
	 			Object[] obj5={};
	 			hds.updates(hql5, obj5);
	 			jlQs.setFaceId(id);
	 			jlQs.setFaceState(0);
	 			hds.update(jlQs);
	 			
	 			while(flag==false && begining<=end){
					if(begin+1000 ==begining){
						StringBuffer str=new StringBuffer("from JlQs jl where jl.faceId="+id+"");
						List<JlQs> list=hds.searchAll(str.toString());
						JlQs jlQs1=(JlQs)list.get(0);
						if(jlQs1.getFaceState()==2){
							flag=true;
							JSONArray json=JSONArray.fromObject(2);
							response.getWriter().println(json.toString());
							return null;
						}
						if(jlQs1.getFaceState()==3){
							flag=true;
							JSONArray json=JSONArray.fromObject(3);
							response.getWriter().println(json.toString());
							return null;
						}
						if(jlQs1.getFaceState()==4){
							flag=true;
							JSONArray json=JSONArray.fromObject(4);
							response.getWriter().println(json.toString());
							return null;
						}
						if(jlQs1.getFaceState()==1){
							flag=true;
							JSONArray json=JSONArray.fromObject(1);
							response.getWriter().println(json.toString());
							return null;
						}
					    begin=begining;
					}
					begining=System.currentTimeMillis();
				}
	 			
	 			JSONArray json=JSONArray.fromObject(10);
				response.getWriter().println(json.toString());
				return null;
	 		}
			
			
		}		
		
		return null;
	}
	public ActionForward addFaceSP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
	    if(sysUser==null){
		   return mapping.findForward("sessionFailure");
	    }
		String frNo = java.net.URLDecoder.decode((String)request.getParameter("frNo"),"UTF-8");
		String webId = request.getParameter("webId");
		
		JlHjSpQs jlHjSpQs=(JlHjSpQs)hds.findById(JlHjSpQs.class, Integer.parseInt(webId));
		
		boolean flag=false;
		Long begin=System.currentTimeMillis();
		Long end=begin+50000;
		Long begining=begin;
		//判断当前登记电脑是否允许注册人脸
		System.out.println("request.getRemoteAddr()= "+request.getRemoteAddr());
		StringBuffer str11=new StringBuffer("from HjdjFaceInfo hj where hj.enrollFacePcIp='"+request.getRemoteAddr()+"'");
		List<HjdjFaceInfo> list11=hds.searchAll(str11.toString());
		if(list11.size()<1){
			JSONArray json=JSONArray.fromObject(5);
			response.getWriter().println(json.toString());
			return null;
		}else{
			HjdjFaceInfo hjdjFaceInfo=(HjdjFaceInfo)list11.get(0);
			//向注册人脸设备发送开始注册指令
			long id = 0;
			IFaceClient faceClient = new TFaceClient();
		    boolean isConnect = faceClient.connect("http://127.0.0.1:8080/PinnacleFace");
		    System.out.println("连接人脸注册系统状态："+isConnect);
		    if(isConnect)
		    {
//		    	boolean a = faceClient.clearDeviceInfo("1070000034765", true);
		        id = faceClient.startEnrollFace(hjdjFaceInfo.getEnrollFaceId(), "http://127.0.0.1:9009/HjSystem/MyServletSP");
		        System.out.println("注册命令任务号："+id);
		    }else{
		    	JSONArray json=JSONArray.fromObject(6);
				response.getWriter().println(json.toString());
				return null;
		    }
		    if(id == 0){
		    	JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
				return null;
		    }
		    if(id != 0){
		    	String hql5="update JlHjSpQs set faceId=null where faceId="+id+"";
	 			Object[] obj5={};
	 			hds.updates(hql5, obj5);
	 			jlHjSpQs.setFaceId(id);
	 			jlHjSpQs.setFaceState(0);
	 			hds.update(jlHjSpQs);
	 			
	 			while(flag==false && begining<=end){
					if(begin+1000 ==begining){
						StringBuffer str=new StringBuffer("from JlHjSpQs jl where jl.faceId="+id+"");
						List<JlHjSpQs> list=hds.searchAll(str.toString());
						JlHjSpQs jlHjSpQs1=(JlHjSpQs)list.get(0);
						if(jlHjSpQs1.getFaceState()==2){
							flag=true;
							JSONArray json=JSONArray.fromObject(2);
							response.getWriter().println(json.toString());
							return null;
						}
						if(jlHjSpQs1.getFaceState()==3){
							flag=true;
							JSONArray json=JSONArray.fromObject(3);
							response.getWriter().println(json.toString());
							return null;
						}
						if(jlHjSpQs1.getFaceState()==4){
							flag=true;
							JSONArray json=JSONArray.fromObject(4);
							response.getWriter().println(json.toString());
							return null;
						}
						if(jlHjSpQs1.getFaceState()==1){
							flag=true;
							JSONArray json=JSONArray.fromObject(1);
							response.getWriter().println(json.toString());
							return null;
						}
					    begin=begining;
					}
					begining=System.currentTimeMillis();
				}
	 			JSONArray json=JSONArray.fromObject(10);
				response.getWriter().println(json.toString());
				return null;
	 		}
			
			
		}		
		
		return null;
	}
	public ActionForward addQsInfoToABDoor1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
	    if(sysUser==null){
		   return mapping.findForward("sessionFailure");
	    }
	    String webId = request.getParameter("webId");
		String hql="from JlQs where webId="+Integer.parseInt(webId);
		List<JlQs> list=hds.searchAll(hql);
		JlQs jlQs = list.get(0);

		List<InterviewQs> listInterviewQs = new ArrayList<InterviewQs>();		
		
		Timestamp now=new Timestamp(System.currentTimeMillis());
		String tsStr = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try {   
            //方法一   
            tsStr = sdf.format(now);   
          //方法二   
            tsStr = now.toString();   
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        
        Calendar end=Calendar.getInstance();						        
        end.add(Calendar.MINUTE,60);						       
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");						       
        String dateStr=sdf1.format(end.getTimeInMillis());
        String time = " 18:00:00";
        String time1 = dateStr+time;
        
		InterviewQs interviewQs = new InterviewQs();
		interviewQs.setJsName(jlQs.getQsName());
		interviewQs.setJsIDNo(jlQs.getQsSfz());
		interviewQs.setJsSex(jlQs.getXb());
		interviewQs.setJsAddr(jlQs.getDz());
		interviewQs.setJsPhoto(jlQs.getZpUrl());
		interviewQs.setGxName(jlQs.getGx());
		interviewQs.setZfNo(jlQs.getFrNo());
		
		String hql1="from JlFr where frNo='"+jlQs.getFrNo()+"'";
		List<JlFr> list1=hds.searchAll(hql1);
		JlFr jlFr=(JlFr)list1.get(0);
		
		interviewQs.setZfName(jlFr.getFrName());
		interviewQs.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs);
		
		InterviewQsList interviewQsList = new InterviewQsList();
		
		interviewQsList.setTotal("1");
		interviewQsList.setRecord(listInterviewQs);
		
		
//		System.out.println("1="+interviewUser.getCardNo()+","+interviewUser.getUserName()+","+interviewUser.getReason()+","+interviewUser.getStartDate()+","+interviewUser.getEndDate()+","+interviewUser.getCardAddress()+","+interviewUser.getCardType()+","+interviewUser.getGender()+","+interviewUser.getNationNames()+","+interviewUser.getApprovedNo()+","+interviewUser.getAccompanyBewrite());
//		System.out.println("2="+listInterviewUser.get(0).getCardNo()+","+listInterviewUser.get(0).getUserName()+","+listInterviewUser.get(0).getReason()+","+listInterviewUser.get(0).getStartDate()+","+listInterviewUser.get(0).getEndDate()+","+listInterviewUser.get(0).getCardAddress()+","+listInterviewUser.get(0).getCardType()+","+listInterviewUser.get(0).getGender()+","+listInterviewUser.get(0).getNationNames()+","+listInterviewUser.get(0).getApprovedNo()+","+listInterviewUser.get(0).getAccompanyBewrite());
		
		JSONArray array = JSONArray.fromObject(interviewQsList);
		String data=array.toString().substring(array.toString().indexOf("[")+1, array.toString().lastIndexOf("]"));
		DasWebSrv service = new DasWebSrv();
		DasWebSrvPortType rtInterface = service.getDasWebSrv();	    
		int aaaa=rtInterface.sendHjAppRegInfo(data);
		System.out.println("返回结果="+aaaa);
	   
		if(aaaa==1){
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(1);
			response.getWriter().println(json.toString());
			return null;
		}else{
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(-1);
			response.getWriter().println(json.toString());
			return null;
		}
		
		
	}
	public ActionForward addQsInfoToABDoor2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
	    if(sysUser==null){
		   return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String webId1 = request.getParameter("webId1");
		
		String hql="from JlQs where webId="+Integer.parseInt(webId);
		List<JlQs> list=hds.searchAll(hql);
		JlQs jlQs = list.get(0);
		
		String hql1="from JlQs where webId="+Integer.parseInt(webId1);
		List<JlQs> list1=hds.searchAll(hql1);
		JlQs jlQs1 = list1.get(0);
		
		List<InterviewQs> listInterviewQs = new ArrayList<InterviewQs>();		
		
		Timestamp now=new Timestamp(System.currentTimeMillis());
		String tsStr = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try {   
            //方法一   
            tsStr = sdf.format(now);   
          //方法二   
            tsStr = now.toString();   
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        
        Calendar end=Calendar.getInstance();						        
        end.add(Calendar.MINUTE,60);						       
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");						       
        String dateStr=sdf1.format(end.getTimeInMillis());
        String time = " 18:00:00";
        String time1 = dateStr+time;
        
		InterviewQs interviewQs = new InterviewQs();
		interviewQs.setJsName(jlQs.getQsName());
		interviewQs.setJsIDNo(jlQs.getQsSfz());
		interviewQs.setJsSex(jlQs.getXb());
		interviewQs.setJsAddr(jlQs.getDz());
		interviewQs.setJsPhoto(jlQs.getZpUrl());
		interviewQs.setGxName(jlQs.getGx());
		interviewQs.setZfNo(jlQs.getFrNo());
		
		String hql2="from JlFr where frNo='"+jlQs.getFrNo()+"'";
		List<JlFr> list2=hds.searchAll(hql2);
		JlFr jlFr=(JlFr)list2.get(0);
		
		interviewQs.setZfName(jlFr.getFrName());
		interviewQs.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs);
		
		InterviewQs interviewQs1 = new InterviewQs();
		interviewQs1.setJsName(jlQs1.getQsName());
		interviewQs1.setJsIDNo(jlQs1.getQsSfz());
		interviewQs1.setJsSex(jlQs1.getXb());
		interviewQs1.setJsAddr(jlQs1.getDz());
		interviewQs1.setJsPhoto(jlQs1.getZpUrl());
		interviewQs1.setGxName(jlQs1.getGx());
		interviewQs1.setZfNo(jlQs1.getFrNo());
		
		String hql3="from JlFr where frNo='"+jlQs1.getFrNo()+"'";
		List<JlFr> list3=hds.searchAll(hql3);
		JlFr jlFr1=(JlFr)list3.get(0);
		
		interviewQs1.setZfName(jlFr1.getFrName());
		interviewQs1.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs1);
		
		InterviewQsList interviewQsList = new InterviewQsList();
				
		interviewQsList.setRecord(listInterviewQs);
		interviewQsList.setTotal("2");
		
//		System.out.println("1="+interviewUser.getCardNo()+","+interviewUser.getUserName()+","+interviewUser.getReason()+","+interviewUser.getStartDate()+","+interviewUser.getEndDate()+","+interviewUser.getCardAddress()+","+interviewUser.getCardType()+","+interviewUser.getGender()+","+interviewUser.getNationNames()+","+interviewUser.getApprovedNo()+","+interviewUser.getAccompanyBewrite());
//		System.out.println("2="+listInterviewUser.get(0).getCardNo()+","+listInterviewUser.get(0).getUserName()+","+listInterviewUser.get(0).getReason()+","+listInterviewUser.get(0).getStartDate()+","+listInterviewUser.get(0).getEndDate()+","+listInterviewUser.get(0).getCardAddress()+","+listInterviewUser.get(0).getCardType()+","+listInterviewUser.get(0).getGender()+","+listInterviewUser.get(0).getNationNames()+","+listInterviewUser.get(0).getApprovedNo()+","+listInterviewUser.get(0).getAccompanyBewrite());
		
		JSONArray array = JSONArray.fromObject(interviewQsList);
		String data=array.toString().substring(array.toString().indexOf("[")+1, array.toString().lastIndexOf("]"));
		DasWebSrv service = new DasWebSrv();
		DasWebSrvPortType rtInterface = service.getDasWebSrv();	    
		int aaaa=rtInterface.sendHjAppRegInfo(data);
		System.out.println("返回结果="+aaaa);
	   
		if(aaaa==1){
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(1);
			response.getWriter().println(json.toString());
			return null;
		}else{
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(-1);
			response.getWriter().println(json.toString());
			return null;
		}
	}
	public ActionForward addQsInfoToABDoor3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
	    if(sysUser==null){
		   return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String webId1 = request.getParameter("webId1");
		String webId2 = request.getParameter("webId2");
		
		String hql="from JlQs where webId="+Integer.parseInt(webId);
		List<JlQs> list=hds.searchAll(hql);
		JlQs jlQs = list.get(0);
		
		String hql1="from JlQs where webId="+Integer.parseInt(webId1);
		List<JlQs> list1=hds.searchAll(hql1);
		JlQs jlQs1 = list1.get(0);
		
		String hql2="from JlQs where webId="+Integer.parseInt(webId2);
		List<JlQs> list2=hds.searchAll(hql2);
		JlQs jlQs2 = list2.get(0);
		
		List<InterviewQs> listInterviewQs = new ArrayList<InterviewQs>();		
		
		Timestamp now=new Timestamp(System.currentTimeMillis());
		String tsStr = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try {   
            //方法一   
            tsStr = sdf.format(now);   
          //方法二   
            tsStr = now.toString();   
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        
        Calendar end=Calendar.getInstance();						        
        end.add(Calendar.MINUTE,60);						       
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");						       
        String dateStr=sdf1.format(end.getTimeInMillis());
        String time = " 18:00:00";
        String time1 = dateStr+time;
        
		InterviewQs interviewQs = new InterviewQs();
		interviewQs.setJsName(jlQs.getQsName());
		interviewQs.setJsIDNo(jlQs.getQsSfz());
		interviewQs.setJsSex(jlQs.getXb());
		interviewQs.setJsAddr(jlQs.getDz());
		interviewQs.setJsPhoto(jlQs.getZpUrl());
		interviewQs.setGxName(jlQs.getGx());
		interviewQs.setZfNo(jlQs.getFrNo());
		
		String hql3="from JlFr where frNo='"+jlQs.getFrNo()+"'";
		List<JlFr> list3=hds.searchAll(hql3);
		JlFr jlFr=(JlFr)list3.get(0);
		
		interviewQs.setZfName(jlFr.getFrName());
		interviewQs.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs);
		
		InterviewQs interviewQs1 = new InterviewQs();
		interviewQs1.setJsName(jlQs1.getQsName());
		interviewQs1.setJsIDNo(jlQs1.getQsSfz());
		interviewQs1.setJsSex(jlQs1.getXb());
		interviewQs1.setJsAddr(jlQs1.getDz());
		interviewQs1.setJsPhoto(jlQs1.getZpUrl());
		interviewQs1.setGxName(jlQs1.getGx());
		interviewQs1.setZfNo(jlQs1.getFrNo());
		
		String hql4="from JlFr where frNo='"+jlQs1.getFrNo()+"'";
		List<JlFr> list4=hds.searchAll(hql4);
		JlFr jlFr1=(JlFr)list4.get(0);
		
		interviewQs1.setZfName(jlFr1.getFrName());
		interviewQs1.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs1);
		
		InterviewQs interviewQs2 = new InterviewQs();
		interviewQs2.setJsName(jlQs2.getQsName());
		interviewQs2.setJsIDNo(jlQs2.getQsSfz());
		interviewQs2.setJsSex(jlQs2.getXb());
		interviewQs2.setJsAddr(jlQs2.getDz());
		interviewQs2.setJsPhoto(jlQs2.getZpUrl());
		interviewQs2.setGxName(jlQs2.getGx());
		interviewQs2.setZfNo(jlQs2.getFrNo());
		
		String hql5="from JlFr where frNo='"+jlQs2.getFrNo()+"'";
		List<JlFr> list5=hds.searchAll(hql5);
		JlFr jlFr2=(JlFr)list5.get(0);
		
		interviewQs2.setZfName(jlFr2.getFrName());
		interviewQs2.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs2);
		
		InterviewQsList interviewQsList = new InterviewQsList();
				
		interviewQsList.setRecord(listInterviewQs);
		interviewQsList.setTotal("3");
		
//		System.out.println("1="+interviewUser.getCardNo()+","+interviewUser.getUserName()+","+interviewUser.getReason()+","+interviewUser.getStartDate()+","+interviewUser.getEndDate()+","+interviewUser.getCardAddress()+","+interviewUser.getCardType()+","+interviewUser.getGender()+","+interviewUser.getNationNames()+","+interviewUser.getApprovedNo()+","+interviewUser.getAccompanyBewrite());
//		System.out.println("2="+listInterviewUser.get(0).getCardNo()+","+listInterviewUser.get(0).getUserName()+","+listInterviewUser.get(0).getReason()+","+listInterviewUser.get(0).getStartDate()+","+listInterviewUser.get(0).getEndDate()+","+listInterviewUser.get(0).getCardAddress()+","+listInterviewUser.get(0).getCardType()+","+listInterviewUser.get(0).getGender()+","+listInterviewUser.get(0).getNationNames()+","+listInterviewUser.get(0).getApprovedNo()+","+listInterviewUser.get(0).getAccompanyBewrite());
		
		JSONArray array = JSONArray.fromObject(interviewQsList);
		String data=array.toString().substring(array.toString().indexOf("[")+1, array.toString().lastIndexOf("]"));
		DasWebSrv service = new DasWebSrv();
		DasWebSrvPortType rtInterface = service.getDasWebSrv();	    
		int aaaa=rtInterface.sendHjAppRegInfo(data);
		System.out.println("返回结果="+aaaa);
	   
		if(aaaa==1){
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(1);
			response.getWriter().println(json.toString());
			return null;
		}else{
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(-1);
			response.getWriter().println(json.toString());
			return null;
		}
	}
	public ActionForward addQsInfoToABDoor4(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
	    if(sysUser==null){
		   return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String webId1 = request.getParameter("webId1");
		String webId2 = request.getParameter("webId2");
		String webId3 = request.getParameter("webId3");
		
		String hql="from JlQs where webId="+Integer.parseInt(webId);
		List<JlQs> list=hds.searchAll(hql);
		JlQs jlQs = list.get(0);
		
		String hql1="from JlQs where webId="+Integer.parseInt(webId1);
		List<JlQs> list1=hds.searchAll(hql1);
		JlQs jlQs1 = list1.get(0);
		
		String hql2="from JlQs where webId="+Integer.parseInt(webId2);
		List<JlQs> list2=hds.searchAll(hql2);
		JlQs jlQs2 = list2.get(0);
		
		String hql3="from JlQs where webId="+Integer.parseInt(webId3);
		List<JlQs> list3=hds.searchAll(hql3);
		JlQs jlQs3 = list3.get(0);
		
		List<InterviewQs> listInterviewQs = new ArrayList<InterviewQs>();		
		
		Timestamp now=new Timestamp(System.currentTimeMillis());
		String tsStr = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try {   
            //方法一   
            tsStr = sdf.format(now);   
          //方法二   
            tsStr = now.toString();   
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        
        Calendar end=Calendar.getInstance();						        
        end.add(Calendar.MINUTE,60);						       
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");						       
        String dateStr=sdf1.format(end.getTimeInMillis());
        String time = " 18:00:00";
        String time1 = dateStr+time;
        
		InterviewQs interviewQs = new InterviewQs();
		interviewQs.setJsName(jlQs.getQsName());
		interviewQs.setJsIDNo(jlQs.getQsSfz());
		interviewQs.setJsSex(jlQs.getXb());
		interviewQs.setJsAddr(jlQs.getDz());
		interviewQs.setJsPhoto(jlQs.getZpUrl());
		interviewQs.setGxName(jlQs.getGx());
		interviewQs.setZfNo(jlQs.getFrNo());
		
		String hql4="from JlFr where frNo='"+jlQs.getFrNo()+"'";
		List<JlFr> list4=hds.searchAll(hql4);
		JlFr jlFr=(JlFr)list4.get(0);
		
		interviewQs.setZfName(jlFr.getFrName());
		interviewQs.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs);
		
		InterviewQs interviewQs1 = new InterviewQs();
		interviewQs1.setJsName(jlQs1.getQsName());
		interviewQs1.setJsIDNo(jlQs1.getQsSfz());
		interviewQs1.setJsSex(jlQs1.getXb());
		interviewQs1.setJsAddr(jlQs1.getDz());
		interviewQs1.setJsPhoto(jlQs1.getZpUrl());
		interviewQs1.setGxName(jlQs1.getGx());
		interviewQs1.setZfNo(jlQs1.getFrNo());
		
		String hql5="from JlFr where frNo='"+jlQs1.getFrNo()+"'";
		List<JlFr> list5=hds.searchAll(hql5);
		JlFr jlFr1=(JlFr)list5.get(0);
		
		interviewQs1.setZfName(jlFr1.getFrName());
		interviewQs1.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs1);
		
		InterviewQs interviewQs2 = new InterviewQs();
		interviewQs2.setJsName(jlQs2.getQsName());
		interviewQs2.setJsIDNo(jlQs2.getQsSfz());
		interviewQs2.setJsSex(jlQs2.getXb());
		interviewQs2.setJsAddr(jlQs2.getDz());
		interviewQs2.setJsPhoto(jlQs2.getZpUrl());
		interviewQs2.setGxName(jlQs2.getGx());
		interviewQs2.setZfNo(jlQs2.getFrNo());
		
		String hql6="from JlFr where frNo='"+jlQs2.getFrNo()+"'";
		List<JlFr> list6=hds.searchAll(hql6);
		JlFr jlFr2=(JlFr)list6.get(0);
		
		interviewQs2.setZfName(jlFr2.getFrName());
		interviewQs2.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs2);
		
		InterviewQs interviewQs3 = new InterviewQs();
		interviewQs3.setJsName(jlQs3.getQsName());
		interviewQs3.setJsIDNo(jlQs3.getQsSfz());
		interviewQs3.setJsSex(jlQs3.getXb());
		interviewQs3.setJsAddr(jlQs3.getDz());
		interviewQs3.setJsPhoto(jlQs3.getZpUrl());
		interviewQs3.setGxName(jlQs3.getGx());
		interviewQs3.setZfNo(jlQs3.getFrNo());
		
		String hql7="from JlFr where frNo='"+jlQs3.getFrNo()+"'";
		List<JlFr> list7=hds.searchAll(hql7);
		JlFr jlFr3=(JlFr)list7.get(0);
		
		interviewQs3.setZfName(jlFr3.getFrName());
		interviewQs3.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs3);
		
		InterviewQsList interviewQsList = new InterviewQsList();
				
		interviewQsList.setRecord(listInterviewQs);
		interviewQsList.setTotal("4");
		
//		System.out.println("1="+interviewUser.getCardNo()+","+interviewUser.getUserName()+","+interviewUser.getReason()+","+interviewUser.getStartDate()+","+interviewUser.getEndDate()+","+interviewUser.getCardAddress()+","+interviewUser.getCardType()+","+interviewUser.getGender()+","+interviewUser.getNationNames()+","+interviewUser.getApprovedNo()+","+interviewUser.getAccompanyBewrite());
//		System.out.println("2="+listInterviewUser.get(0).getCardNo()+","+listInterviewUser.get(0).getUserName()+","+listInterviewUser.get(0).getReason()+","+listInterviewUser.get(0).getStartDate()+","+listInterviewUser.get(0).getEndDate()+","+listInterviewUser.get(0).getCardAddress()+","+listInterviewUser.get(0).getCardType()+","+listInterviewUser.get(0).getGender()+","+listInterviewUser.get(0).getNationNames()+","+listInterviewUser.get(0).getApprovedNo()+","+listInterviewUser.get(0).getAccompanyBewrite());
		
		JSONArray array = JSONArray.fromObject(interviewQsList);
		String data=array.toString().substring(array.toString().indexOf("[")+1, array.toString().lastIndexOf("]"));
		DasWebSrv service = new DasWebSrv();
		DasWebSrvPortType rtInterface = service.getDasWebSrv();	    
		int aaaa=rtInterface.sendHjAppRegInfo(data);
		System.out.println("返回结果="+aaaa);
	   
		if(aaaa==1){
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(1);
			response.getWriter().println(json.toString());
			return null;
		}else{
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(-1);
			response.getWriter().println(json.toString());
			return null;
		}
	}
	public ActionForward addQsInfoToABDoor5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
	    if(sysUser==null){
		   return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String webId1 = request.getParameter("webId1");
		String webId2 = request.getParameter("webId2");
		String webId3 = request.getParameter("webId3");
		String webId4 = request.getParameter("webId4");
		
		String hql="from JlQs where webId="+Integer.parseInt(webId);
		List<JlQs> list=hds.searchAll(hql);
		JlQs jlQs = list.get(0);
		
		String hql1="from JlQs where webId="+Integer.parseInt(webId1);
		List<JlQs> list1=hds.searchAll(hql1);
		JlQs jlQs1 = list1.get(0);
		
		String hql2="from JlQs where webId="+Integer.parseInt(webId2);
		List<JlQs> list2=hds.searchAll(hql2);
		JlQs jlQs2 = list2.get(0);
		
		String hql3="from JlQs where webId="+Integer.parseInt(webId3);
		List<JlQs> list3=hds.searchAll(hql3);
		JlQs jlQs3 = list3.get(0);
		
		String hql4="from JlQs where webId="+Integer.parseInt(webId4);
		List<JlQs> list4=hds.searchAll(hql4);
		JlQs jlQs4 = list4.get(0);
		
		List<InterviewQs> listInterviewQs = new ArrayList<InterviewQs>();		
		
		Timestamp now=new Timestamp(System.currentTimeMillis());
		String tsStr = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try {   
            //方法一   
            tsStr = sdf.format(now);   
          //方法二   
            tsStr = now.toString();   
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        
        Calendar end=Calendar.getInstance();						        
        end.add(Calendar.MINUTE,60);						       
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");						       
        String dateStr=sdf1.format(end.getTimeInMillis());
        String time = " 18:00:00";
        String time1 = dateStr+time;
        
		InterviewQs interviewQs = new InterviewQs();
		interviewQs.setJsName(jlQs.getQsName());
		interviewQs.setJsIDNo(jlQs.getQsSfz());
		interviewQs.setJsSex(jlQs.getXb());
		interviewQs.setJsAddr(jlQs.getDz());
		interviewQs.setJsPhoto(jlQs.getZpUrl());
		interviewQs.setGxName(jlQs.getGx());
		interviewQs.setZfNo(jlQs.getFrNo());
		
		String hql5="from JlFr where frNo='"+jlQs.getFrNo()+"'";
		List<JlFr> list5=hds.searchAll(hql5);
		JlFr jlFr=(JlFr)list5.get(0);
		
		interviewQs.setZfName(jlFr.getFrName());
		interviewQs.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs);
		
		InterviewQs interviewQs1 = new InterviewQs();
		interviewQs1.setJsName(jlQs1.getQsName());
		interviewQs1.setJsIDNo(jlQs1.getQsSfz());
		interviewQs1.setJsSex(jlQs1.getXb());
		interviewQs1.setJsAddr(jlQs1.getDz());
		interviewQs1.setJsPhoto(jlQs1.getZpUrl());
		interviewQs1.setGxName(jlQs1.getGx());
		interviewQs1.setZfNo(jlQs1.getFrNo());
		
		String hql6="from JlFr where frNo='"+jlQs1.getFrNo()+"'";
		List<JlFr> list6=hds.searchAll(hql6);
		JlFr jlFr1=(JlFr)list6.get(0);
		
		interviewQs1.setZfName(jlFr1.getFrName());
		interviewQs1.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs1);
		
		InterviewQs interviewQs2 = new InterviewQs();
		interviewQs2.setJsName(jlQs2.getQsName());
		interviewQs2.setJsIDNo(jlQs2.getQsSfz());
		interviewQs2.setJsSex(jlQs2.getXb());
		interviewQs2.setJsAddr(jlQs2.getDz());
		interviewQs2.setJsPhoto(jlQs2.getZpUrl());
		interviewQs2.setGxName(jlQs2.getGx());
		interviewQs2.setZfNo(jlQs2.getFrNo());
		
		String hql7="from JlFr where frNo='"+jlQs2.getFrNo()+"'";
		List<JlFr> list7=hds.searchAll(hql7);
		JlFr jlFr2=(JlFr)list7.get(0);
		
		interviewQs2.setZfName(jlFr2.getFrName());
		interviewQs2.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs2);
		
		InterviewQs interviewQs3 = new InterviewQs();
		interviewQs3.setJsName(jlQs3.getQsName());
		interviewQs3.setJsIDNo(jlQs3.getQsSfz());
		interviewQs3.setJsSex(jlQs3.getXb());
		interviewQs3.setJsAddr(jlQs3.getDz());
		interviewQs3.setJsPhoto(jlQs3.getZpUrl());
		interviewQs3.setGxName(jlQs3.getGx());
		interviewQs3.setZfNo(jlQs3.getFrNo());
		
		String hql8="from JlFr where frNo='"+jlQs3.getFrNo()+"'";
		List<JlFr> list8=hds.searchAll(hql8);
		JlFr jlFr3=(JlFr)list8.get(0);
		
		interviewQs3.setZfName(jlFr3.getFrName());
		interviewQs3.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs3);
		
		InterviewQs interviewQs4 = new InterviewQs();
		interviewQs4.setJsName(jlQs4.getQsName());
		interviewQs4.setJsIDNo(jlQs4.getQsSfz());
		interviewQs4.setJsSex(jlQs4.getXb());
		interviewQs4.setJsAddr(jlQs4.getDz());
		interviewQs4.setJsPhoto(jlQs4.getZpUrl());
		interviewQs4.setGxName(jlQs4.getGx());
		interviewQs4.setZfNo(jlQs4.getFrNo());
		
		String hql9="from JlFr where frNo='"+jlQs4.getFrNo()+"'";
		List<JlFr> list9=hds.searchAll(hql9);
		JlFr jlFr4=(JlFr)list9.get(0);
		
		interviewQs4.setZfName(jlFr4.getFrName());
		interviewQs4.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs4);
		
		InterviewQsList interviewQsList = new InterviewQsList();
				
		interviewQsList.setRecord(listInterviewQs);
		interviewQsList.setTotal("5");
		
//		System.out.println("1="+interviewUser.getCardNo()+","+interviewUser.getUserName()+","+interviewUser.getReason()+","+interviewUser.getStartDate()+","+interviewUser.getEndDate()+","+interviewUser.getCardAddress()+","+interviewUser.getCardType()+","+interviewUser.getGender()+","+interviewUser.getNationNames()+","+interviewUser.getApprovedNo()+","+interviewUser.getAccompanyBewrite());
//		System.out.println("2="+listInterviewUser.get(0).getCardNo()+","+listInterviewUser.get(0).getUserName()+","+listInterviewUser.get(0).getReason()+","+listInterviewUser.get(0).getStartDate()+","+listInterviewUser.get(0).getEndDate()+","+listInterviewUser.get(0).getCardAddress()+","+listInterviewUser.get(0).getCardType()+","+listInterviewUser.get(0).getGender()+","+listInterviewUser.get(0).getNationNames()+","+listInterviewUser.get(0).getApprovedNo()+","+listInterviewUser.get(0).getAccompanyBewrite());
		
		JSONArray array = JSONArray.fromObject(interviewQsList);
		String data=array.toString().substring(array.toString().indexOf("[")+1, array.toString().lastIndexOf("]"));
		DasWebSrv service = new DasWebSrv();
		DasWebSrvPortType rtInterface = service.getDasWebSrv();	    
		int aaaa=rtInterface.sendHjAppRegInfo(data);
		System.out.println("返回结果="+aaaa);
	   
		if(aaaa==1){
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(1);
			response.getWriter().println(json.toString());
			return null;
		}else{
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(-1);
			response.getWriter().println(json.toString());
			return null;
		}
	}
	public ActionForward addQsInfoToABDoor6(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
	    if(sysUser==null){
		   return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String webId1 = request.getParameter("webId1");
		String webId2 = request.getParameter("webId2");
		String webId3 = request.getParameter("webId3");
		String webId4 = request.getParameter("webId4");
		String webId5 = request.getParameter("webId5");
		
		String hql="from JlQs where webId="+Integer.parseInt(webId);
		List<JlQs> list=hds.searchAll(hql);
		JlQs jlQs = list.get(0);
		
		String hql1="from JlQs where webId="+Integer.parseInt(webId1);
		List<JlQs> list1=hds.searchAll(hql1);
		JlQs jlQs1 = list1.get(0);
		
		String hql2="from JlQs where webId="+Integer.parseInt(webId2);
		List<JlQs> list2=hds.searchAll(hql2);
		JlQs jlQs2 = list2.get(0);
		
		String hql3="from JlQs where webId="+Integer.parseInt(webId3);
		List<JlQs> list3=hds.searchAll(hql3);
		JlQs jlQs3 = list3.get(0);
		
		String hql4="from JlQs where webId="+Integer.parseInt(webId4);
		List<JlQs> list4=hds.searchAll(hql4);
		JlQs jlQs4 = list4.get(0);
		
		String hql5="from JlQs where webId="+Integer.parseInt(webId5);
		List<JlQs> list5=hds.searchAll(hql5);
		JlQs jlQs5 = list5.get(0);
		
		List<InterviewQs> listInterviewQs = new ArrayList<InterviewQs>();		
		
		Timestamp now=new Timestamp(System.currentTimeMillis());
		String tsStr = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try {   
            //方法一   
            tsStr = sdf.format(now);   
          //方法二   
            tsStr = now.toString();   
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        
        Calendar end=Calendar.getInstance();						        
        end.add(Calendar.MINUTE,60);						       
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");						       
        String dateStr=sdf1.format(end.getTimeInMillis());
        String time = " 18:00:00";
        String time1 = dateStr+time;
        
		InterviewQs interviewQs = new InterviewQs();
		interviewQs.setJsName(jlQs.getQsName());
		interviewQs.setJsIDNo(jlQs.getQsSfz());
		interviewQs.setJsSex(jlQs.getXb());
		interviewQs.setJsAddr(jlQs.getDz());
		interviewQs.setJsPhoto(jlQs.getZpUrl());
		interviewQs.setGxName(jlQs.getGx());
		interviewQs.setZfNo(jlQs.getFrNo());
		
		String hql6="from JlFr where frNo='"+jlQs.getFrNo()+"'";
		List<JlFr> list6=hds.searchAll(hql6);
		JlFr jlFr=(JlFr)list6.get(0);
		
		interviewQs.setZfName(jlFr.getFrName());
		interviewQs.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs);
		
		InterviewQs interviewQs1 = new InterviewQs();
		interviewQs1.setJsName(jlQs1.getQsName());
		interviewQs1.setJsIDNo(jlQs1.getQsSfz());
		interviewQs1.setJsSex(jlQs1.getXb());
		interviewQs1.setJsAddr(jlQs1.getDz());
		interviewQs1.setJsPhoto(jlQs1.getZpUrl());
		interviewQs1.setGxName(jlQs1.getGx());
		interviewQs1.setZfNo(jlQs1.getFrNo());
		
		String hql7="from JlFr where frNo='"+jlQs1.getFrNo()+"'";
		List<JlFr> list7=hds.searchAll(hql7);
		JlFr jlFr1=(JlFr)list7.get(0);
		
		interviewQs1.setZfName(jlFr1.getFrName());
		interviewQs1.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs1);
		
		InterviewQs interviewQs2 = new InterviewQs();
		interviewQs2.setJsName(jlQs2.getQsName());
		interviewQs2.setJsIDNo(jlQs2.getQsSfz());
		interviewQs2.setJsSex(jlQs2.getXb());
		interviewQs2.setJsAddr(jlQs2.getDz());
		interviewQs2.setJsPhoto(jlQs2.getZpUrl());
		interviewQs2.setGxName(jlQs2.getGx());
		interviewQs2.setZfNo(jlQs2.getFrNo());
		
		String hql8="from JlFr where frNo='"+jlQs2.getFrNo()+"'";
		List<JlFr> list8=hds.searchAll(hql8);
		JlFr jlFr2=(JlFr)list8.get(0);
		
		interviewQs2.setZfName(jlFr2.getFrName());
		interviewQs2.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs2);
		
		InterviewQs interviewQs3 = new InterviewQs();
		interviewQs3.setJsName(jlQs3.getQsName());
		interviewQs3.setJsIDNo(jlQs3.getQsSfz());
		interviewQs3.setJsSex(jlQs3.getXb());
		interviewQs3.setJsAddr(jlQs3.getDz());
		interviewQs3.setJsPhoto(jlQs3.getZpUrl());
		interviewQs3.setGxName(jlQs3.getGx());
		interviewQs3.setZfNo(jlQs3.getFrNo());
		
		String hql9="from JlFr where frNo='"+jlQs3.getFrNo()+"'";
		List<JlFr> list9=hds.searchAll(hql9);
		JlFr jlFr3=(JlFr)list9.get(0);
		
		interviewQs3.setZfName(jlFr3.getFrName());
		interviewQs3.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs3);
		
		InterviewQs interviewQs4 = new InterviewQs();
		interviewQs4.setJsName(jlQs4.getQsName());
		interviewQs4.setJsIDNo(jlQs4.getQsSfz());
		interviewQs4.setJsSex(jlQs4.getXb());
		interviewQs4.setJsAddr(jlQs4.getDz());
		interviewQs4.setJsPhoto(jlQs4.getZpUrl());
		interviewQs4.setGxName(jlQs4.getGx());
		interviewQs4.setZfNo(jlQs4.getFrNo());
		
		String hql10="from JlFr where frNo='"+jlQs4.getFrNo()+"'";
		List<JlFr> list10=hds.searchAll(hql10);
		JlFr jlFr4=(JlFr)list10.get(0);
		
		interviewQs4.setZfName(jlFr4.getFrName());
		interviewQs4.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs4);
		
		InterviewQs interviewQs5 = new InterviewQs();
		interviewQs5.setJsName(jlQs5.getQsName());
		interviewQs5.setJsIDNo(jlQs5.getQsSfz());
		interviewQs5.setJsSex(jlQs5.getXb());
		interviewQs5.setJsAddr(jlQs5.getDz());
		interviewQs5.setJsPhoto(jlQs5.getZpUrl());
		interviewQs5.setGxName(jlQs5.getGx());
		interviewQs5.setZfNo(jlQs5.getFrNo());
		
		String hql11="from JlFr where frNo='"+jlQs5.getFrNo()+"'";
		List<JlFr> list11=hds.searchAll(hql11);
		JlFr jlFr5=(JlFr)list11.get(0);
		
		interviewQs5.setZfName(jlFr5.getFrName());
		interviewQs5.setRegisterTime(tsStr);
		
		listInterviewQs.add(interviewQs5);
		
		InterviewQsList interviewQsList = new InterviewQsList();
				
		interviewQsList.setRecord(listInterviewQs);
		interviewQsList.setTotal("6");
		
//		System.out.println("1="+interviewUser.getCardNo()+","+interviewUser.getUserName()+","+interviewUser.getReason()+","+interviewUser.getStartDate()+","+interviewUser.getEndDate()+","+interviewUser.getCardAddress()+","+interviewUser.getCardType()+","+interviewUser.getGender()+","+interviewUser.getNationNames()+","+interviewUser.getApprovedNo()+","+interviewUser.getAccompanyBewrite());
//		System.out.println("2="+listInterviewUser.get(0).getCardNo()+","+listInterviewUser.get(0).getUserName()+","+listInterviewUser.get(0).getReason()+","+listInterviewUser.get(0).getStartDate()+","+listInterviewUser.get(0).getEndDate()+","+listInterviewUser.get(0).getCardAddress()+","+listInterviewUser.get(0).getCardType()+","+listInterviewUser.get(0).getGender()+","+listInterviewUser.get(0).getNationNames()+","+listInterviewUser.get(0).getApprovedNo()+","+listInterviewUser.get(0).getAccompanyBewrite());
		
		JSONArray array = JSONArray.fromObject(interviewQsList);
		String data=array.toString().substring(array.toString().indexOf("[")+1, array.toString().lastIndexOf("]"));
		DasWebSrv service = new DasWebSrv();
		DasWebSrvPortType rtInterface = service.getDasWebSrv();	    
		int aaaa=rtInterface.sendHjAppRegInfo(data);
		System.out.println("返回结果="+aaaa);
	   
		if(aaaa==1){
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(1);
			response.getWriter().println(json.toString());
			return null;
		}else{
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(-1);
			response.getWriter().println(json.toString());
			return null;
		}
	}
	public ActionForward addQsInfoToABDoor7(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
	    if(sysUser==null){
		   return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String webId1 = request.getParameter("webId1");
		String webId2 = request.getParameter("webId2");
		String webId3 = request.getParameter("webId3");
		String webId4 = request.getParameter("webId4");
		String webId5 = request.getParameter("webId5");
		String webId6 = request.getParameter("webId6");
		
		String hql="from JlQs where webId="+Integer.parseInt(webId);
		List<JlQs> list=hds.searchAll(hql);
		JlQs jlQs = list.get(0);
		
		String hql1="from JlQs where webId="+Integer.parseInt(webId1);
		List<JlQs> list1=hds.searchAll(hql1);
		JlQs jlQs1 = list1.get(0);
		
		String hql2="from JlQs where webId="+Integer.parseInt(webId2);
		List<JlQs> list2=hds.searchAll(hql2);
		JlQs jlQs2 = list2.get(0);
		
		String hql3="from JlQs where webId="+Integer.parseInt(webId3);
		List<JlQs> list3=hds.searchAll(hql3);
		JlQs jlQs3 = list3.get(0);
		
		String hql4="from JlQs where webId="+Integer.parseInt(webId4);
		List<JlQs> list4=hds.searchAll(hql4);
		JlQs jlQs4 = list4.get(0);
		
		String hql5="from JlQs where webId="+Integer.parseInt(webId5);
		List<JlQs> list5=hds.searchAll(hql5);
		JlQs jlQs5 = list5.get(0);
		
		String hql6="from JlQs where webId="+Integer.parseInt(webId6);
		List<JlQs> list6=hds.searchAll(hql6);
		JlQs jlQs6 = list6.get(0);
		
		String hql11="from JlYjABDoor";
	 	List<JlYjABDoor> list11=hds.searchAll(hql11);

	 	String yjno = "";
	 	for(int i=0;i<list11.size();i++){
	 		JlYjABDoor jlYjABDoor=(JlYjABDoor)list11.get(i);
	 		yjno=yjno+jlYjABDoor.getYjNo()+",";
	 	}
		
		List<InterviewUser> listInterviewUser = new ArrayList<InterviewUser>();		
		
		String url = "http://10.41.63.35:8877/UserInfo/LoginInterview";
		String param = "Username=interviewgh&password=interviewabc.abc";
		String token = getURLByPost(url,param);
		
		Timestamp now=new Timestamp(System.currentTimeMillis());
		String tsStr = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try {   
            //方法一   
            tsStr = sdf.format(now);   
            //System.out.println("1="+tsStr);
          //方法二   
          //  tsStr = now.toString();   
          //  System.out.println("2="+tsStr);
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        
        Calendar end=Calendar.getInstance();						        
        end.add(Calendar.MINUTE,60);						       
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");						       
        String dateStr=sdf1.format(end.getTimeInMillis());
        String time = " 18:00:00";
        String time1 = dateStr+time;
        
		InterviewUser interviewUser = new InterviewUser();
		interviewUser.setCardNo(jlQs.getQsSfz());
		interviewUser.setUserName(jlQs.getQsName());
		interviewUser.setReason(null);
		interviewUser.setStartDate(tsStr);
		interviewUser.setEndDate(time1);
		if(null==jlQs.getDz() || "".equals(jlQs.getDz())){
			interviewUser.setCardAddress(null);									
		}else{
			interviewUser.setCardAddress(jlQs.getDz());
		}
		
		interviewUser.setCardType("身份证");
		
		if(null==jlQs.getXb() || "".equals(jlQs.getXb())){
			interviewUser.setGender("男");									
		}else{
			interviewUser.setGender(jlQs.getXb());
		}
		
		interviewUser.setNationNames(null);
		interviewUser.setApprovedNo(null);
		interviewUser.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser);
		
		InterviewUser interviewUser1 = new InterviewUser();
		interviewUser1.setCardNo(jlQs1.getQsSfz());
		interviewUser1.setUserName(jlQs1.getQsName());
		interviewUser1.setReason(null);
		interviewUser1.setStartDate(tsStr);
		interviewUser1.setEndDate(time1);
		if(null==jlQs1.getDz() || "".equals(jlQs1.getDz())){
			interviewUser1.setCardAddress(null);									
		}else{
			interviewUser1.setCardAddress(jlQs1.getDz());
		}
		
		interviewUser1.setCardType("身份证");
		
		if(null==jlQs1.getXb() || "".equals(jlQs1.getXb())){
			interviewUser1.setGender("男");									
		}else{
			interviewUser1.setGender(jlQs1.getXb());
		}
		
		interviewUser1.setNationNames(null);
		interviewUser1.setApprovedNo(null);
		interviewUser1.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser1);
		
		InterviewUser interviewUser2 = new InterviewUser();
		interviewUser2.setCardNo(jlQs2.getQsSfz());
		interviewUser2.setUserName(jlQs2.getQsName());
		interviewUser2.setReason(null);
		interviewUser2.setStartDate(tsStr);
		interviewUser2.setEndDate(time1);
		if(null==jlQs2.getDz() || "".equals(jlQs2.getDz())){
			interviewUser2.setCardAddress(null);									
		}else{
			interviewUser2.setCardAddress(jlQs2.getDz());
		}
		
		interviewUser2.setCardType("身份证");
		
		if(null==jlQs2.getXb() || "".equals(jlQs2.getXb())){
			interviewUser2.setGender("男");									
		}else{
			interviewUser2.setGender(jlQs2.getXb());
		}
		
		interviewUser2.setNationNames(null);
		interviewUser2.setApprovedNo(null);
		interviewUser2.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser2);
		
		InterviewUser interviewUser3 = new InterviewUser();
		interviewUser3.setCardNo(jlQs3.getQsSfz());
		interviewUser3.setUserName(jlQs3.getQsName());
		interviewUser3.setReason(null);
		interviewUser3.setStartDate(tsStr);
		interviewUser3.setEndDate(time1);
		if(null==jlQs3.getDz() || "".equals(jlQs3.getDz())){
			interviewUser3.setCardAddress(null);									
		}else{
			interviewUser3.setCardAddress(jlQs3.getDz());
		}
		
		interviewUser3.setCardType("身份证");
		
		if(null==jlQs3.getXb() || "".equals(jlQs3.getXb())){
			interviewUser3.setGender("男");									
		}else{
			interviewUser3.setGender(jlQs3.getXb());
		}
		
		interviewUser3.setNationNames(null);
		interviewUser3.setApprovedNo(null);
		interviewUser3.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser3);
		
		InterviewUser interviewUser4 = new InterviewUser();
		interviewUser4.setCardNo(jlQs4.getQsSfz());
		interviewUser4.setUserName(jlQs4.getQsName());
		interviewUser4.setReason(null);
		interviewUser4.setStartDate(tsStr);
		interviewUser4.setEndDate(time1);
		if(null==jlQs4.getDz() || "".equals(jlQs4.getDz())){
			interviewUser4.setCardAddress(null);									
		}else{
			interviewUser4.setCardAddress(jlQs4.getDz());
		}
		
		interviewUser4.setCardType("身份证");
		
		if(null==jlQs4.getXb() || "".equals(jlQs4.getXb())){
			interviewUser4.setGender("男");									
		}else{
			interviewUser4.setGender(jlQs4.getXb());
		}
		
		interviewUser4.setNationNames(null);
		interviewUser4.setApprovedNo(null);
		interviewUser4.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser4);
		
		InterviewUser interviewUser5 = new InterviewUser();
		interviewUser5.setCardNo(jlQs5.getQsSfz());
		interviewUser5.setUserName(jlQs5.getQsName());
		interviewUser5.setReason(null);
		interviewUser5.setStartDate(tsStr);
		interviewUser5.setEndDate(time1);
		if(null==jlQs5.getDz() || "".equals(jlQs5.getDz())){
			interviewUser5.setCardAddress(null);									
		}else{
			interviewUser5.setCardAddress(jlQs5.getDz());
		}
		
		interviewUser5.setCardType("身份证");
		
		if(null==jlQs5.getXb() || "".equals(jlQs5.getXb())){
			interviewUser5.setGender("男");									
		}else{
			interviewUser5.setGender(jlQs5.getXb());
		}
		
		interviewUser5.setNationNames(null);
		interviewUser5.setApprovedNo(null);
		interviewUser5.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser5);
		
		InterviewUser interviewUser6 = new InterviewUser();
		interviewUser6.setCardNo(jlQs6.getQsSfz());
		interviewUser6.setUserName(jlQs6.getQsName());
		interviewUser6.setReason(null);
		interviewUser6.setStartDate(tsStr);
		interviewUser6.setEndDate(time1);
		if(null==jlQs6.getDz() || "".equals(jlQs6.getDz())){
			interviewUser6.setCardAddress(null);									
		}else{
			interviewUser6.setCardAddress(jlQs6.getDz());
		}
		
		interviewUser6.setCardType("身份证");
		
		if(null==jlQs6.getXb() || "".equals(jlQs6.getXb())){
			interviewUser6.setGender("男");									
		}else{
			interviewUser6.setGender(jlQs6.getXb());
		}
		
		interviewUser6.setNationNames(null);
		interviewUser6.setApprovedNo(null);
		interviewUser6.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser6);
		
		JSONArray array = JSONArray.fromObject(listInterviewUser);
	    String jsonstr = array.toString();
	    String jsonstrunicode= string2Unicode(jsonstr);
	    String param1 = "token="+token+"&usersJson="+jsonstrunicode;
//	    String url11 = "http://10.41.63.35:8877/UserInfo/PushInterviewUsers?"+aa;
	    String url1 = "http://10.41.63.35:8877/UserInfo/PushInterviewUsers";

		String result = getURLByPost(url1,param1);
		System.out.println("结果="+result);
		
		FrQsToABDoorResult frQsToABDoorResult = new FrQsToABDoorResult();
		
//		String aaaa = "数据传输异常，请联系AB门系统厂家";
		frQsToABDoorResult.setResult(result);
		if(result.equals("7")){
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(frQsToABDoorResult);
			response.getWriter().println(json.toString());
			return null;
		}else{
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(frQsToABDoorResult);
			response.getWriter().println(json.toString());
			return null;
		}
	}
	public ActionForward addQsInfoToABDoor8(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
	    if(sysUser==null){
		   return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String webId1 = request.getParameter("webId1");
		String webId2 = request.getParameter("webId2");
		String webId3 = request.getParameter("webId3");
		String webId4 = request.getParameter("webId4");
		String webId5 = request.getParameter("webId5");
		String webId6 = request.getParameter("webId6");
		String webId7 = request.getParameter("webId7");
		
		String hql="from JlQs where webId="+Integer.parseInt(webId);
		List<JlQs> list=hds.searchAll(hql);
		JlQs jlQs = list.get(0);
		
		String hql1="from JlQs where webId="+Integer.parseInt(webId1);
		List<JlQs> list1=hds.searchAll(hql1);
		JlQs jlQs1 = list1.get(0);
		
		String hql2="from JlQs where webId="+Integer.parseInt(webId2);
		List<JlQs> list2=hds.searchAll(hql2);
		JlQs jlQs2 = list2.get(0);
		
		String hql3="from JlQs where webId="+Integer.parseInt(webId3);
		List<JlQs> list3=hds.searchAll(hql3);
		JlQs jlQs3 = list3.get(0);
		
		String hql4="from JlQs where webId="+Integer.parseInt(webId4);
		List<JlQs> list4=hds.searchAll(hql4);
		JlQs jlQs4 = list4.get(0);
		
		String hql5="from JlQs where webId="+Integer.parseInt(webId5);
		List<JlQs> list5=hds.searchAll(hql5);
		JlQs jlQs5 = list5.get(0);
		
		String hql6="from JlQs where webId="+Integer.parseInt(webId6);
		List<JlQs> list6=hds.searchAll(hql6);
		JlQs jlQs6 = list6.get(0);
		
		String hql7="from JlQs where webId="+Integer.parseInt(webId7);
		List<JlQs> list7=hds.searchAll(hql7);
		JlQs jlQs7 = list7.get(0);
		
		String hql11="from JlYjABDoor";
	 	List<JlYjABDoor> list11=hds.searchAll(hql11);

	 	String yjno = "";
	 	for(int i=0;i<list11.size();i++){
	 		JlYjABDoor jlYjABDoor=(JlYjABDoor)list11.get(i);
	 		yjno=yjno+jlYjABDoor.getYjNo()+",";
	 	}
		
		List<InterviewUser> listInterviewUser = new ArrayList<InterviewUser>();		
		
		String url = "http://10.41.63.35:8877/UserInfo/LoginInterview";
		String param = "Username=interviewgh&password=interviewabc.abc";
		String token = getURLByPost(url,param);
		
		Timestamp now=new Timestamp(System.currentTimeMillis());
		String tsStr = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try {   
            //方法一   
            tsStr = sdf.format(now);   
            //System.out.println("1="+tsStr);
          //方法二   
          //  tsStr = now.toString();   
          //  System.out.println("2="+tsStr);
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        
        Calendar end=Calendar.getInstance();						        
        end.add(Calendar.MINUTE,60);						       
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");						       
        String dateStr=sdf1.format(end.getTimeInMillis());
        String time = " 18:00:00";
        String time1 = dateStr+time;
        
		InterviewUser interviewUser = new InterviewUser();
		interviewUser.setCardNo(jlQs.getQsSfz());
		interviewUser.setUserName(jlQs.getQsName());
		interviewUser.setReason(null);
		interviewUser.setStartDate(tsStr);
		interviewUser.setEndDate(time1);
		if(null==jlQs.getDz() || "".equals(jlQs.getDz())){
			interviewUser.setCardAddress(null);									
		}else{
			interviewUser.setCardAddress(jlQs.getDz());
		}
		
		interviewUser.setCardType("身份证");
		
		if(null==jlQs.getXb() || "".equals(jlQs.getXb())){
			interviewUser.setGender("男");									
		}else{
			interviewUser.setGender(jlQs.getXb());
		}
		
		interviewUser.setNationNames(null);
		interviewUser.setApprovedNo(null);
		interviewUser.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser);
		
		InterviewUser interviewUser1 = new InterviewUser();
		interviewUser1.setCardNo(jlQs1.getQsSfz());
		interviewUser1.setUserName(jlQs1.getQsName());
		interviewUser1.setReason(null);
		interviewUser1.setStartDate(tsStr);
		interviewUser1.setEndDate(time1);
		if(null==jlQs1.getDz() || "".equals(jlQs1.getDz())){
			interviewUser1.setCardAddress(null);									
		}else{
			interviewUser1.setCardAddress(jlQs1.getDz());
		}
		
		interviewUser1.setCardType("身份证");
		
		if(null==jlQs1.getXb() || "".equals(jlQs1.getXb())){
			interviewUser1.setGender("男");									
		}else{
			interviewUser1.setGender(jlQs1.getXb());
		}
		
		interviewUser1.setNationNames(null);
		interviewUser1.setApprovedNo(null);
		interviewUser1.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser1);
		
		InterviewUser interviewUser2 = new InterviewUser();
		interviewUser2.setCardNo(jlQs2.getQsSfz());
		interviewUser2.setUserName(jlQs2.getQsName());
		interviewUser2.setReason(null);
		interviewUser2.setStartDate(tsStr);
		interviewUser2.setEndDate(time1);
		if(null==jlQs2.getDz() || "".equals(jlQs2.getDz())){
			interviewUser2.setCardAddress(null);									
		}else{
			interviewUser2.setCardAddress(jlQs2.getDz());
		}
		
		interviewUser2.setCardType("身份证");
		
		if(null==jlQs2.getXb() || "".equals(jlQs2.getXb())){
			interviewUser2.setGender("男");									
		}else{
			interviewUser2.setGender(jlQs2.getXb());
		}
		
		interviewUser2.setNationNames(null);
		interviewUser2.setApprovedNo(null);
		interviewUser2.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser2);
		
		InterviewUser interviewUser3 = new InterviewUser();
		interviewUser3.setCardNo(jlQs3.getQsSfz());
		interviewUser3.setUserName(jlQs3.getQsName());
		interviewUser3.setReason(null);
		interviewUser3.setStartDate(tsStr);
		interviewUser3.setEndDate(time1);
		if(null==jlQs3.getDz() || "".equals(jlQs3.getDz())){
			interviewUser3.setCardAddress(null);									
		}else{
			interviewUser3.setCardAddress(jlQs3.getDz());
		}
		
		interviewUser3.setCardType("身份证");
		
		if(null==jlQs3.getXb() || "".equals(jlQs3.getXb())){
			interviewUser3.setGender("男");									
		}else{
			interviewUser3.setGender(jlQs3.getXb());
		}
		
		interviewUser3.setNationNames(null);
		interviewUser3.setApprovedNo(null);
		interviewUser3.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser3);
		
		InterviewUser interviewUser4 = new InterviewUser();
		interviewUser4.setCardNo(jlQs4.getQsSfz());
		interviewUser4.setUserName(jlQs4.getQsName());
		interviewUser4.setReason(null);
		interviewUser4.setStartDate(tsStr);
		interviewUser4.setEndDate(time1);
		if(null==jlQs4.getDz() || "".equals(jlQs4.getDz())){
			interviewUser4.setCardAddress(null);									
		}else{
			interviewUser4.setCardAddress(jlQs4.getDz());
		}
		
		interviewUser4.setCardType("身份证");
		
		if(null==jlQs4.getXb() || "".equals(jlQs4.getXb())){
			interviewUser4.setGender("男");									
		}else{
			interviewUser4.setGender(jlQs4.getXb());
		}
		
		interviewUser4.setNationNames(null);
		interviewUser4.setApprovedNo(null);
		interviewUser4.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser4);
		
		InterviewUser interviewUser5 = new InterviewUser();
		interviewUser5.setCardNo(jlQs5.getQsSfz());
		interviewUser5.setUserName(jlQs5.getQsName());
		interviewUser5.setReason(null);
		interviewUser5.setStartDate(tsStr);
		interviewUser5.setEndDate(time1);
		if(null==jlQs5.getDz() || "".equals(jlQs5.getDz())){
			interviewUser5.setCardAddress(null);									
		}else{
			interviewUser5.setCardAddress(jlQs5.getDz());
		}
		
		interviewUser5.setCardType("身份证");
		
		if(null==jlQs5.getXb() || "".equals(jlQs5.getXb())){
			interviewUser5.setGender("男");									
		}else{
			interviewUser5.setGender(jlQs5.getXb());
		}
		
		interviewUser5.setNationNames(null);
		interviewUser5.setApprovedNo(null);
		interviewUser5.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser5);
		
		InterviewUser interviewUser6 = new InterviewUser();
		interviewUser6.setCardNo(jlQs6.getQsSfz());
		interviewUser6.setUserName(jlQs6.getQsName());
		interviewUser6.setReason(null);
		interviewUser6.setStartDate(tsStr);
		interviewUser6.setEndDate(time1);
		if(null==jlQs6.getDz() || "".equals(jlQs6.getDz())){
			interviewUser6.setCardAddress(null);									
		}else{
			interviewUser6.setCardAddress(jlQs6.getDz());
		}
		
		interviewUser6.setCardType("身份证");
		
		if(null==jlQs6.getXb() || "".equals(jlQs6.getXb())){
			interviewUser6.setGender("男");									
		}else{
			interviewUser6.setGender(jlQs6.getXb());
		}
		
		interviewUser6.setNationNames(null);
		interviewUser6.setApprovedNo(null);
		interviewUser6.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser6);
		
		InterviewUser interviewUser7 = new InterviewUser();
		interviewUser7.setCardNo(jlQs7.getQsSfz());
		interviewUser7.setUserName(jlQs7.getQsName());
		interviewUser7.setReason(null);
		interviewUser7.setStartDate(tsStr);
		interviewUser7.setEndDate(time1);
		if(null==jlQs7.getDz() || "".equals(jlQs7.getDz())){
			interviewUser7.setCardAddress(null);									
		}else{
			interviewUser7.setCardAddress(jlQs7.getDz());
		}
		
		interviewUser7.setCardType("身份证");
		
		if(null==jlQs7.getXb() || "".equals(jlQs7.getXb())){
			interviewUser7.setGender("男");									
		}else{
			interviewUser7.setGender(jlQs7.getXb());
		}
		
		interviewUser7.setNationNames(null);
		interviewUser7.setApprovedNo(null);
		interviewUser7.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser7);
		
		JSONArray array = JSONArray.fromObject(listInterviewUser);
	    String jsonstr = array.toString();
	    String jsonstrunicode= string2Unicode(jsonstr);
	    String param1 = "token="+token+"&usersJson="+jsonstrunicode;
//	    String url11 = "http://10.41.63.35:8877/UserInfo/PushInterviewUsers?"+aa;
	    String url1 = "http://10.41.63.35:8877/UserInfo/PushInterviewUsers";

		String result = getURLByPost(url1,param1);
		System.out.println("结果="+result);
		
		FrQsToABDoorResult frQsToABDoorResult = new FrQsToABDoorResult();
		
//		String aaaa = "数据传输异常，请联系AB门系统厂家";
		frQsToABDoorResult.setResult(result);
		if(result.equals("8")){
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(frQsToABDoorResult);
			response.getWriter().println(json.toString());
			return null;
		}else{
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(frQsToABDoorResult);
			response.getWriter().println(json.toString());
			return null;
		}
	}
	public ActionForward addQsInfoToABDoor9(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
	    if(sysUser==null){
		   return mapping.findForward("sessionFailure");
	    }
		String webId = request.getParameter("webId");
		String webId1 = request.getParameter("webId1");
		String webId2 = request.getParameter("webId2");
		String webId3 = request.getParameter("webId3");
		String webId4 = request.getParameter("webId4");
		String webId5 = request.getParameter("webId5");
		String webId6 = request.getParameter("webId6");
		String webId7 = request.getParameter("webId7");
		String webId8 = request.getParameter("webId8");
		
		String hql="from JlQs where webId="+Integer.parseInt(webId);
		List<JlQs> list=hds.searchAll(hql);
		JlQs jlQs = list.get(0);
		
		String hql1="from JlQs where webId="+Integer.parseInt(webId1);
		List<JlQs> list1=hds.searchAll(hql1);
		JlQs jlQs1 = list1.get(0);
		
		String hql2="from JlQs where webId="+Integer.parseInt(webId2);
		List<JlQs> list2=hds.searchAll(hql2);
		JlQs jlQs2 = list2.get(0);
		
		String hql3="from JlQs where webId="+Integer.parseInt(webId3);
		List<JlQs> list3=hds.searchAll(hql3);
		JlQs jlQs3 = list3.get(0);
		
		String hql4="from JlQs where webId="+Integer.parseInt(webId4);
		List<JlQs> list4=hds.searchAll(hql4);
		JlQs jlQs4 = list4.get(0);
		
		String hql5="from JlQs where webId="+Integer.parseInt(webId5);
		List<JlQs> list5=hds.searchAll(hql5);
		JlQs jlQs5 = list5.get(0);
		
		String hql6="from JlQs where webId="+Integer.parseInt(webId6);
		List<JlQs> list6=hds.searchAll(hql6);
		JlQs jlQs6 = list6.get(0);
		
		String hql7="from JlQs where webId="+Integer.parseInt(webId7);
		List<JlQs> list7=hds.searchAll(hql7);
		JlQs jlQs7 = list7.get(0);
		
		String hql8="from JlQs where webId="+Integer.parseInt(webId8);
		List<JlQs> list8=hds.searchAll(hql8);
		JlQs jlQs8 = list8.get(0);
		
		String hql11="from JlYjABDoor";
	 	List<JlYjABDoor> list11=hds.searchAll(hql11);

	 	String yjno = "";
	 	for(int i=0;i<list11.size();i++){
	 		JlYjABDoor jlYjABDoor=(JlYjABDoor)list11.get(i);
	 		yjno=yjno+jlYjABDoor.getYjNo()+",";
	 	}
		
		List<InterviewUser> listInterviewUser = new ArrayList<InterviewUser>();		
		
		String url = "http://10.41.63.35:8877/UserInfo/LoginInterview";
		String param = "Username=interviewgh&password=interviewabc.abc";
		String token = getURLByPost(url,param);
		
		Timestamp now=new Timestamp(System.currentTimeMillis());
		String tsStr = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try {   
            //方法一   
            tsStr = sdf.format(now);   
            //System.out.println("1="+tsStr);
          //方法二   
          //  tsStr = now.toString();   
          //  System.out.println("2="+tsStr);
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        
        Calendar end=Calendar.getInstance();						        
        end.add(Calendar.MINUTE,60);						       
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");						       
        String dateStr=sdf1.format(end.getTimeInMillis());
        String time = " 18:00:00";
        String time1 = dateStr+time;
        
		InterviewUser interviewUser = new InterviewUser();
		interviewUser.setCardNo(jlQs.getQsSfz());
		interviewUser.setUserName(jlQs.getQsName());
		interviewUser.setReason(null);
		interviewUser.setStartDate(tsStr);
		interviewUser.setEndDate(time1);
		if(null==jlQs.getDz() || "".equals(jlQs.getDz())){
			interviewUser.setCardAddress(null);									
		}else{
			interviewUser.setCardAddress(jlQs.getDz());
		}
		
		interviewUser.setCardType("身份证");
		
		if(null==jlQs.getXb() || "".equals(jlQs.getXb())){
			interviewUser.setGender("男");									
		}else{
			interviewUser.setGender(jlQs.getXb());
		}
		
		interviewUser.setNationNames(null);
		interviewUser.setApprovedNo(null);
		interviewUser.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser);
		
		InterviewUser interviewUser1 = new InterviewUser();
		interviewUser1.setCardNo(jlQs1.getQsSfz());
		interviewUser1.setUserName(jlQs1.getQsName());
		interviewUser1.setReason(null);
		interviewUser1.setStartDate(tsStr);
		interviewUser1.setEndDate(time1);
		if(null==jlQs1.getDz() || "".equals(jlQs1.getDz())){
			interviewUser1.setCardAddress(null);									
		}else{
			interviewUser1.setCardAddress(jlQs1.getDz());
		}
		
		interviewUser1.setCardType("身份证");
		
		if(null==jlQs1.getXb() || "".equals(jlQs1.getXb())){
			interviewUser1.setGender("男");									
		}else{
			interviewUser1.setGender(jlQs1.getXb());
		}
		
		interviewUser1.setNationNames(null);
		interviewUser1.setApprovedNo(null);
		interviewUser1.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser1);
		
		InterviewUser interviewUser2 = new InterviewUser();
		interviewUser2.setCardNo(jlQs2.getQsSfz());
		interviewUser2.setUserName(jlQs2.getQsName());
		interviewUser2.setReason(null);
		interviewUser2.setStartDate(tsStr);
		interviewUser2.setEndDate(time1);
		if(null==jlQs2.getDz() || "".equals(jlQs2.getDz())){
			interviewUser2.setCardAddress(null);									
		}else{
			interviewUser2.setCardAddress(jlQs2.getDz());
		}
		
		interviewUser2.setCardType("身份证");
		
		if(null==jlQs2.getXb() || "".equals(jlQs2.getXb())){
			interviewUser2.setGender("男");									
		}else{
			interviewUser2.setGender(jlQs2.getXb());
		}
		
		interviewUser2.setNationNames(null);
		interviewUser2.setApprovedNo(null);
		interviewUser2.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser2);
		
		InterviewUser interviewUser3 = new InterviewUser();
		interviewUser3.setCardNo(jlQs3.getQsSfz());
		interviewUser3.setUserName(jlQs3.getQsName());
		interviewUser3.setReason(null);
		interviewUser3.setStartDate(tsStr);
		interviewUser3.setEndDate(time1);
		if(null==jlQs3.getDz() || "".equals(jlQs3.getDz())){
			interviewUser3.setCardAddress(null);									
		}else{
			interviewUser3.setCardAddress(jlQs3.getDz());
		}
		
		interviewUser3.setCardType("身份证");
		
		if(null==jlQs3.getXb() || "".equals(jlQs3.getXb())){
			interviewUser3.setGender("男");									
		}else{
			interviewUser3.setGender(jlQs3.getXb());
		}
		
		interviewUser3.setNationNames(null);
		interviewUser3.setApprovedNo(null);
		interviewUser3.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser3);
		
		InterviewUser interviewUser4 = new InterviewUser();
		interviewUser4.setCardNo(jlQs4.getQsSfz());
		interviewUser4.setUserName(jlQs4.getQsName());
		interviewUser4.setReason(null);
		interviewUser4.setStartDate(tsStr);
		interviewUser4.setEndDate(time1);
		if(null==jlQs4.getDz() || "".equals(jlQs4.getDz())){
			interviewUser4.setCardAddress(null);									
		}else{
			interviewUser4.setCardAddress(jlQs4.getDz());
		}
		
		interviewUser4.setCardType("身份证");
		
		if(null==jlQs4.getXb() || "".equals(jlQs4.getXb())){
			interviewUser4.setGender("男");									
		}else{
			interviewUser4.setGender(jlQs4.getXb());
		}
		
		interviewUser4.setNationNames(null);
		interviewUser4.setApprovedNo(null);
		interviewUser4.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser4);
		
		InterviewUser interviewUser5 = new InterviewUser();
		interviewUser5.setCardNo(jlQs5.getQsSfz());
		interviewUser5.setUserName(jlQs5.getQsName());
		interviewUser5.setReason(null);
		interviewUser5.setStartDate(tsStr);
		interviewUser5.setEndDate(time1);
		if(null==jlQs5.getDz() || "".equals(jlQs5.getDz())){
			interviewUser5.setCardAddress(null);									
		}else{
			interviewUser5.setCardAddress(jlQs5.getDz());
		}
		
		interviewUser5.setCardType("身份证");
		
		if(null==jlQs5.getXb() || "".equals(jlQs5.getXb())){
			interviewUser5.setGender("男");									
		}else{
			interviewUser5.setGender(jlQs5.getXb());
		}
		
		interviewUser5.setNationNames(null);
		interviewUser5.setApprovedNo(null);
		interviewUser5.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser5);
		
		InterviewUser interviewUser6 = new InterviewUser();
		interviewUser6.setCardNo(jlQs6.getQsSfz());
		interviewUser6.setUserName(jlQs6.getQsName());
		interviewUser6.setReason(null);
		interviewUser6.setStartDate(tsStr);
		interviewUser6.setEndDate(time1);
		if(null==jlQs6.getDz() || "".equals(jlQs6.getDz())){
			interviewUser6.setCardAddress(null);									
		}else{
			interviewUser6.setCardAddress(jlQs6.getDz());
		}
		
		interviewUser6.setCardType("身份证");
		
		if(null==jlQs6.getXb() || "".equals(jlQs6.getXb())){
			interviewUser6.setGender("男");									
		}else{
			interviewUser6.setGender(jlQs6.getXb());
		}
		
		interviewUser6.setNationNames(null);
		interviewUser6.setApprovedNo(null);
		interviewUser6.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser6);
		
		InterviewUser interviewUser7 = new InterviewUser();
		interviewUser7.setCardNo(jlQs7.getQsSfz());
		interviewUser7.setUserName(jlQs7.getQsName());
		interviewUser7.setReason(null);
		interviewUser7.setStartDate(tsStr);
		interviewUser7.setEndDate(time1);
		if(null==jlQs7.getDz() || "".equals(jlQs7.getDz())){
			interviewUser7.setCardAddress(null);									
		}else{
			interviewUser7.setCardAddress(jlQs7.getDz());
		}
		
		interviewUser7.setCardType("身份证");
		
		if(null==jlQs7.getXb() || "".equals(jlQs7.getXb())){
			interviewUser7.setGender("男");									
		}else{
			interviewUser7.setGender(jlQs7.getXb());
		}
		
		interviewUser7.setNationNames(null);
		interviewUser7.setApprovedNo(null);
		interviewUser7.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser7);
		
		InterviewUser interviewUser8 = new InterviewUser();
		interviewUser8.setCardNo(jlQs8.getQsSfz());
		interviewUser8.setUserName(jlQs8.getQsName());
		interviewUser8.setReason(null);
		interviewUser8.setStartDate(tsStr);
		interviewUser8.setEndDate(time1);
		if(null==jlQs8.getDz() || "".equals(jlQs8.getDz())){
			interviewUser8.setCardAddress(null);									
		}else{
			interviewUser8.setCardAddress(jlQs8.getDz());
		}
		
		interviewUser8.setCardType("身份证");
		
		if(null==jlQs8.getXb() || "".equals(jlQs8.getXb())){
			interviewUser8.setGender("男");									
		}else{
			interviewUser8.setGender(jlQs8.getXb());
		}
		
		interviewUser8.setNationNames(null);
		interviewUser8.setApprovedNo(null);
		interviewUser8.setAccompanyBewrite(yjno.substring(0, yjno.length()-1));
		listInterviewUser.add(interviewUser8);
		
		JSONArray array = JSONArray.fromObject(listInterviewUser);
	    String jsonstr = array.toString();
	    String jsonstrunicode= string2Unicode(jsonstr);
	    String param1 = "token="+token+"&usersJson="+jsonstrunicode;
//	    String url11 = "http://10.41.63.35:8877/UserInfo/PushInterviewUsers?"+aa;
	    String url1 = "http://10.41.63.35:8877/UserInfo/PushInterviewUsers";

		String result = getURLByPost(url1,param1);
		System.out.println("结果="+result);
		
		FrQsToABDoorResult frQsToABDoorResult = new FrQsToABDoorResult();
		
//		String aaaa = "数据传输异常，请联系AB门系统厂家";
		frQsToABDoorResult.setResult(result);
		if(result.equals("9")){
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(frQsToABDoorResult);
			response.getWriter().println(json.toString());
			return null;
		}else{
			response.setCharacterEncoding("utf-8");
			JSONArray json=JSONArray.fromObject(frQsToABDoorResult);
			response.getWriter().println(json.toString());
			return null;
		}
	}
//	public static String sendUrlRequest(String urlStr,String param1,String param2) throwsException{
//		String tempStr=null;
//		HttpClient httpclient=new DefaultHttpClient();
//		Properties properties=new Properties();
//		HttpEntity entity=null;
//		String xmlContent="";
//		try
//		{
//
//			//设置超时时间
//			httpclient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,20000);
//			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,20000);
//
//			//封装需要传递的参数
//			List<NameValuePair> nvps=new ArrayList<NameValuePair>();
//			nvps.add(new BasicNameValuePair("mainMemoCode",strmainMemoCode));
//			nvps.add(new BasicNameValuePair("recordPassWord",strrecordPassWord));
//			//客户端的请求方法类型
//			HttpPost httpPost=new HttpPost(urlStr);
//			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"GBK"));
//			HttpResponse response=httpclient.execute(httpPost);
//
//			//获取服务器返回Http的Content-Type的值
//			tempStr=response.getHeaders("Content-Type")[0].getValue().toString();
//
//			//获取服务器返回页面的值
//			entity=response.getEntity();
//			xmlContent=EntityUtils.toString(entity);
//			Stringstrmessage=null;
//			System.out.println(xmlContent);
//			System.out.println(response.getHeaders("Content-Type")[0].getValue().toString());
//			httpPost.abort();
//			return tempStr;
//		}
//		catch(SocketTimeoutExceptione)
//		{
//		}
//		catch(Exceptionex)
//		{
//			ex.printStackTrace();
//		}
//		finally{
//			httpclient.getConnectionManager().shutdown();
//		}
//	}
//}
//	public static String getURLContent(String urlStr) {
//	    /** 网络的url地址 */        
//		URL url = null;              
//	    /** http连接 */    
//		HttpURLConnection httpConn = null;            
//	     /**//** 输入流 */   
//		BufferedReader in = null;   
//		StringBuilder json = new StringBuilder();   
//		try{     
//			url = new URL(urlStr);
//			HttpURLConnection uc = (HttpURLConnection)url.openConnection();
//			uc.setRequestMethod("POST");
//			in = new BufferedReader( new InputStreamReader(uc.getInputStream(),"UTF-8") );   
//			String str = null;
//			while((str = in.readLine()) != null) {    
//				json.append(str);     
//	        }     
//	     	}catch (Exception ex){   
//	         
//	     	}finally{    
//	     	try{             
//	     		if(in!=null){  
//	     			in.close();     
//	            }     
//	        }catch(IOException ex){      
//	        }     
//	     }     
//	     String result =json.toString();     
//	     return result;    
//	}
	public static String getURLContent(String urlStr) {
		/** 网络的url地址 */
		URL url = null;
		/** http连接 */
		HttpURLConnection httpConn = null;
		/**//** 输入流 */
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
		url = new URL(urlStr);
		in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		String str = null;
		while ((str = in.readLine()) != null) {
		sb.append(str);
		}
		} catch (Exception ex) {
		} finally {
		try {
		if (in != null) {
		in.close();
		}
		} catch (IOException ex) {
		}
		}
		String result = sb.toString();
		System.out.println(result);
		return result;
	}
	public String getURLByPost(String urlStr,String params)throws Exception{    
		URL url=new URL(urlStr);  
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);  
        conn.setDoInput(true);  
        PrintWriter printWriter = new PrintWriter(conn.getOutputStream());  
        printWriter.write(params);  
        printWriter.flush();          
        BufferedReader in = null;   
        StringBuilder sb = new StringBuilder();   
        try{     
           in = new BufferedReader( new InputStreamReader(conn.getInputStream(),"UTF-8") );   
           String str = null;    
           while((str = in.readLine()) != null) {    
               sb.append( str );     
           }     
        } catch (Exception ex) {   
           throw ex;   
        } finally{    
         try{   
             conn.disconnect();  
             if(in!=null){  
                 in.close();  
             }  
             if(printWriter!=null){  
                 printWriter.close();  
             }  
         }catch(IOException ex) {     
             throw ex;   
         }     
        }     
        return sb.toString();   
	}  
	public String string2Unicode(String string){
		StringBuffer unicode = new StringBuffer();
		for(int i=0;i<string.length();i++){
			//取出每个字符
			char c = string.charAt(i);
			//转换为unicode
			unicode.append("\\u"+Integer.toHexString(c));			
		}
		return unicode.toString();
	}
	public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
	public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
//	public static void main(String[] args) {
//        //发送 GET 请求
//        String s=sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
//        System.out.println(s);
//        
//        //发送 POST 请求
//        String sr=sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
//        System.out.println(sr);
//    }
	public static void download(String urlString, String filename,String savePath) throws Exception {  
		// 构造URL  
		URL url = new URL(urlString);  
		// 打开连接  
		URLConnection con = url.openConnection();  
		//设置请求超时为5s  
		con.setConnectTimeout(5*1000);  
		// 输入流  
		InputStream is = con.getInputStream();  
		// 1K的数据缓冲  
		byte[] bs = new byte[1024];  
		// 读取到的数据长度  
		int len;  
		// 输出的文件流  
		File sf=new File(savePath);  
		if(!sf.exists()){  
		sf.mkdirs();  
		}  
		OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);  
		// 开始读取  
		while ((len = is.read(bs)) != -1) {  
		os.write(bs, 0, len);  
		}  
		// 完毕，关闭所有链接  
		os.close();  
		is.close();  
	}
	public static boolean isNetFileAvailable(String strUrl) {
		InputStream netFileInputStream = null;
		try {
			URL url = new URL(strUrl);
			URLConnection urlConn = url.openConnection();
			netFileInputStream = urlConn.getInputStream();
			if (null != netFileInputStream) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (netFileInputStream != null)
					netFileInputStream.close();
			} catch (IOException e) {
			}
		}
	}
}
