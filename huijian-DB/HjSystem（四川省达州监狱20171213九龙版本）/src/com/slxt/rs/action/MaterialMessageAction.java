package com.slxt.rs.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Timestamp;
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

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import com.slxt.rs.form.FrForm;
import com.slxt.rs.model.JlFbFr;
import com.slxt.rs.model.JlFbQs;
import com.slxt.rs.model.JlFr;
import com.slxt.rs.model.JlJb;
import com.slxt.rs.model.JlJq;
import com.slxt.rs.model.JlQs;
import com.slxt.rs.model.SysHjServer;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysParam;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.model.SysUserJq;
import com.slxt.rs.svc.MaterialMessageService;
import com.slxt.rs.util.Constant;
import com.slxt.rs.vo.Fr;
import com.slxt.rs.vo.FrQs;
import com.slxt.rs.vo.Page;

public class MaterialMessageAction extends DispatchAction{
	private  MaterialMessageService mm;

	public void setMm(MaterialMessageService mm) {
		this.mm = mm;
	}
	//查询犯人
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	String paramData5=request.getParameter("paramData5");
		 	if(paramData5!=null){
		 		if(Integer.parseInt(paramData5)==0){
		 			StringBuffer str=new StringBuffer("update SysParam sp set sp.paramData5=1 where sp.paramName=?");
		 			Object[] obj={"System_Set"};
		 			mm.updates(str.toString(), obj);
				}if(Integer.parseInt(paramData5)==1){
					StringBuffer str=new StringBuffer("update SysParam sp set sp.paramData5=0 where sp.paramName=?");
					Object[] obj={"System_Set"};
					mm.updates(str.toString(), obj);
				}
		 	}
		 	Calendar c = Calendar.getInstance();
		 	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		 	c  =  Calendar.getInstance(Locale.CHINESE);   
		 	String loginTime = simpleDateTimeFormat.format(c.getTime());
		 	if(user.getIsSuper()==1){
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(1);
				FrForm ff=(FrForm)form;
				String frNo3 = request.getParameter("frNo3");
				String jq2 = request.getParameter("jq2");
				String jy2 = request.getParameter("jy2");
				String jbNo2 = request.getParameter("jbNo2");
				String frName2 = request.getParameter("frName2");
				String frCard2 = request.getParameter("frCard2");
				String state2 = request.getParameter("state2");
				String monitorFlag2 = request.getParameter("monitorFlag2");
				String stateZdzf2 = request.getParameter("stateZdzf2");
				String pageNo2=request.getParameter("pageNo2");
				if(jy2!=null && !jy2.equals("null")){
					ff.setJy(jy2);
				}
				if(jq2!=null  && !jq2.equals("null")){
					ff.setJq(jq2);
				}
				if(jbNo2!=null  && !jbNo2.equals("null")){
					ff.setJbNo(jbNo2);
				}
				if(pageNo2!=null && !pageNo2.equals("-1")){
					page.setPageNo(Integer.parseInt(pageNo2));
				}
				if(frName2!=null && !frName2.trim().equals("")){
					ff.setFrName(frName2);
				}
				if(frCard2!=null && !frCard2.trim().equals("")){
					ff.setFrCard(frCard2);
				}
				if(frNo3!=null && !frNo3.trim().equals("")){
					ff.setFrNo(frNo3);
				}
				if(stateZdzf2!=null && !stateZdzf2.equals("null")){
					ff.setStateZdzf(stateZdzf2);
				}
				if(state2!=null && !state2.equals("null")){
					ff.setState(state2);
				}
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null || (pageNo2!=null && !pageNo2.equals("-1"))){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No ");
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="from JlJq where jy='"+ff.getJy()+"'";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null  && !ff.getJq().equals("null")){
						str.append(" and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null  && !ff.getJbNo().equals("null")){
						str.append(" and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().trim().equals("")){
						str.append(" and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					if(ff.getFrName()!=null && !ff.getFrName().trim().equals("")){
						//str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
						str.append(" and (dbo.f_get_fryp(jl.FR_Name,'"+ff.getFrName().trim()+"') =1 or jl.FR_Name like '%"+ff.getFrName()+"%')");
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
			 	}
			 	String hql="from SysHjServer";
			 	List<SysHjServer> list=mm.searchAll(hql);
			 	String hql2="from JlJb";
			 	List<JlJq> list2=mm.searchAll(hql2);
			 	String hql3="from SysParam where paramName='System_Set'";
			 	List<SysParam> list4=mm.searchAll(hql3);
			 	SysParam sysParam=(SysParam)list4.get(0);
			 	request.setAttribute("sysQqServerList", list);
			 	request.setAttribute("jqIsNodisable",jqIsNodisable);
			 	request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("sysParam", sysParam);
		 	}else if(user.getGroupNo().equals("Admin")){
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(1);
				FrForm ff=(FrForm)form;
				String frNo3 = request.getParameter("frNo3");
				String jq2 = request.getParameter("jq2");
				String jy2 = request.getParameter("jy2");
				String jbNo2 = request.getParameter("jbNo2");
				String frName2 = request.getParameter("frName2");
				String frCard2 = request.getParameter("frCard2");
				String state2 = request.getParameter("state2");
				String monitorFlag2 = request.getParameter("monitorFlag2");
				String pageNo2=request.getParameter("pageNo2");
				String stateZdzf2 = request.getParameter("stateZdzf2");
				if(jy2!=null && !jy2.equals("null")){
					ff.setJy(jy2);
				}
				if(jq2!=null  && !jq2.equals("null")){
					ff.setJq(jq2);
				}
				if(jbNo2!=null  && !jbNo2.equals("null")){
					ff.setJbNo(jbNo2);
				}
				if(pageNo2!=null && !pageNo2.equals("-1")){
					page.setPageNo(Integer.parseInt(pageNo2));
				}
				if(frName2!=null && !frName2.trim().equals("")){
					ff.setFrName(frName2);
				}
				if(frCard2!=null && !frCard2.trim().equals("")){
					ff.setFrCard(frCard2);
				}
				if(frNo3!=null && !frNo3.trim().equals("")){
					ff.setFrNo(frNo3);
				}
				if(stateZdzf2!=null && !stateZdzf2.equals("null")){
					ff.setStateZdzf(stateZdzf2);
				}
				if(state2!=null && !state2.equals("null")){
					ff.setState(state2);
				}
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null || (pageNo2!=null && !pageNo2.equals("-1"))){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No and jq.Is_Ts!=1 ");
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="from JlJq where jy='"+ff.getJy()+"' and isTs!=1";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null  && !ff.getJq().equals("null")){
						str.append("and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null  && !ff.getJbNo().equals("null")){
						str.append("and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().equals("")){
						str.append("and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					if(ff.getFrName()!=null && !ff.getFrName().equals("")){
						//str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
						str.append(" and (dbo.f_get_fryp(jl.FR_Name,'"+ff.getFrName().trim()+"') =1 or jl.FR_Name like '%"+ff.getFrName()+"%')");
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
			 	}
			 	String hql="from SysHjServer";
			 	List<SysHjServer> list=mm.searchAll(hql);
			 	String hql2="from JlJb";
			 	List<JlJq> list2=mm.searchAll(hql2);
			 	String hql3="from SysParam where paramName='System_Set'";
			 	List<SysParam> list4=mm.searchAll(hql3);
			 	SysParam sysParam=(SysParam)list4.get(0);
			 	request.setAttribute("sysQqServerList", list);
			 	request.setAttribute("jqIsNodisable",jqIsNodisable);
			 	request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("sysParam", sysParam);
		 	}else{
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(1);
				String sql6="select distinct JY from SYS_USER_JQ where Group_No='"+user.getGroupNo()+"'";
				List list6=mm.searchAllBySql(sql6);
				if(list6.size()>0){
					StringBuffer hql=new StringBuffer("from SysHjServer ");
					Iterator it=list6.iterator();
					int i=0;
					while(it.hasNext()){
						if(i==0){
							hql.append(" where serverName='"+it.next()+"' ");
							i++;
						}else{
							hql.append("or serverName='"+it.next()+"' ");
							i++;
						}
					}
					List<SysHjServer> list=mm.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", list);
				}
				FrForm ff=(FrForm)form;
				String frNo3 = request.getParameter("frNo3");
				String jq2 = request.getParameter("jq2");
				String jy2 = request.getParameter("jy2");
				String jbNo2 = request.getParameter("jbNo2");
				String frName2 = request.getParameter("frName2");
				String frCard2 = request.getParameter("frCard2");
				String state2 = request.getParameter("state2");
				String monitorFlag2 = request.getParameter("monitorFlag2");
				String pageNo2=request.getParameter("pageNo2");
				String stateZdzf2 = request.getParameter("stateZdzf2");
				if(jy2!=null && !jy2.equals("null")){
					ff.setJy(jy2);
				}
				if(jq2!=null  && !jq2.equals("null")){
					ff.setJq(jq2);
				}
				if(jbNo2!=null  && !jbNo2.equals("null")){
					ff.setJbNo(jbNo2);
				}
				if(pageNo2!=null && !pageNo2.equals("-1")){
					page.setPageNo(Integer.parseInt(pageNo2));
				}
				if(frName2!=null && !frName2.trim().equals("")){
					ff.setFrName(frName2);
				}
				if(frCard2!=null && !frCard2.trim().equals("")){
					ff.setFrCard(frCard2);
				}
				if(frNo3!=null && !frNo3.trim().equals("")){
					ff.setFrNo(frNo3);
				}
				if(stateZdzf2!=null && !stateZdzf2.equals("null")){
					ff.setStateZdzf(stateZdzf2);
				}
				if(state2!=null && !state2.equals("null")){
					ff.setState(state2);
				}
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null || (pageNo2!=null && !pageNo2.equals("-1"))){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No ");
					String sql5="from SysUserJq where groupNo='"+user.getGroupNo()+"'";
					List list5=mm.searchAll(sql5);
					if(list5.size()>0){
						str.append(" and (");
						for(int i=0;i<list5.size();i++){
							SysUserJq sysUserJq=(SysUserJq)list5.get(i);
							if(i==0){
					    		str.append(" jq.jq_No='"+sysUserJq.getJqNo()+"'");
					    	}else{
					    		str.append(" or jq.jq_No='"+sysUserJq.getJqNo()+"'");
					    	}
					    }
						str.append(" ) ");
					}
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="select jq from JlJq jq,SysUserJq sys where jq.jy='"+ff.getJy()+"' and jq.jqNo=sys.jqNo and sys.groupNo='"+user.getGroupNo()+"'";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null && !ff.getJq().equals("null")){
						str.append("and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null && !ff.getJbNo().equals("null")){
						str.append("and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().equals("")){
						str.append("and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					if(ff.getFrName()!=null && !ff.getFrName().equals("")){
						//str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
						str.append(" and (dbo.f_get_fryp(jl.FR_Name,'"+ff.getFrName().trim()+"') =1 or jl.FR_Name like '%"+ff.getFrName()+"%')");
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
				}
				String hql2="from JlJb";
				List<JlJq> list2=mm.searchAll(hql2);
				String hql3="from SysParam where paramName='System_Set'";
				List<SysParam> list4=mm.searchAll(hql3);
				SysParam sysParam=(SysParam)list4.get(0);
				request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("jqIsNodisable",jqIsNodisable);
				request.setAttribute("sysParam", sysParam);
		 	}
		 	String hql4="from SysParam where paramName='HJ_Client1'";
			List<SysParam> list4=mm.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("sysParam1", sysParam1);
			}
			return mapping.findForward("materialMessageMain");
	}
	//分页查询
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	String paramData5=request.getParameter("paramData5");
		 	if(paramData5!=null){
		 		if(Integer.parseInt(paramData5)==0){
		 			StringBuffer str=new StringBuffer("update SysParam sp set sp.paramData5=1 where sp.paramName=?");
		 			Object[] obj={"System_Set"};
		 			mm.updates(str.toString(), obj);
				}if(Integer.parseInt(paramData5)==1){
					StringBuffer str=new StringBuffer("update SysParam sp set sp.paramData5=0 where sp.paramName=?");
					Object[] obj={"System_Set"};
					mm.updates(str.toString(), obj);
				}
		 	}
		 	Calendar c = Calendar.getInstance();
		 	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		 	c  =  Calendar.getInstance(Locale.CHINESE);   
		 	String loginTime = simpleDateTimeFormat.format(c.getTime());
		 	if(user.getIsSuper()==1){
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
				FrForm ff=(FrForm)form;
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No ");
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="from JlJq where jy='"+ff.getJy()+"'";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null  && !ff.getJq().equals("null")){
						str.append("and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null  && !ff.getJbNo().equals("null")){
						str.append("and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().equals("")){
						str.append("and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getFrName()!=null && !ff.getFrName().equals("")){
						str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
			 	}
			 	String hql="from SysHjServer";
			 	List<SysHjServer> list=mm.searchAll(hql);
			 	String hql2="from JlJb";
			 	List<JlJq> list2=mm.searchAll(hql2);
			 	String hql3="from SysParam where paramName='System_Set'";
			 	List<SysParam> list4=mm.searchAll(hql3);
			 	SysParam sysParam=(SysParam)list4.get(0);
			 	request.setAttribute("sysQqServerList", list);
			 	request.setAttribute("jqIsNodisable",jqIsNodisable);
			 	request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("sysParam", sysParam);
		 	}else if(user.getGroupNo().equals("Admin")){
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
				FrForm ff=(FrForm)form;
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No and jq.Is_Ts!=1");
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="from JlJq where jy='"+ff.getJy()+"'";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null  && !ff.getJq().equals("null")){
						str.append("and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null  && !ff.getJbNo().equals("null")){
						str.append("and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().equals("")){
						str.append("and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getFrName()!=null && !ff.getFrName().equals("")){
						str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						//fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
			 	}
			 	String hql="from SysHjServer";
			 	List<SysHjServer> list=mm.searchAll(hql);
			 	String hql2="from JlJb";
			 	List<JlJq> list2=mm.searchAll(hql2);
			 	String hql3="from SysParam where paramName='System_Set'";
			 	List<SysParam> list4=mm.searchAll(hql3);
			 	SysParam sysParam=(SysParam)list4.get(0);
			 	request.setAttribute("sysQqServerList", list);
			 	request.setAttribute("jqIsNodisable",jqIsNodisable);
			 	request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("sysParam", sysParam);
		 	}else{
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
				String sql6="select distinct JY from SYS_USER_JQ where Group_No='"+user.getGroupNo()+"'";
				List list6=mm.searchAllBySql(sql6);
				if(list6.size()>0){
					StringBuffer hql=new StringBuffer("from SysHjServer ");
					Iterator it=list6.iterator();
					int i=0;
					while(it.hasNext()){
						if(i==0){
							hql.append(" where serverName='"+it.next()+"' ");
							i++;
						}else{
							hql.append("or serverName='"+it.next()+"' ");
							i++;
						}
					}
					List<SysHjServer> list=mm.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", list);
				}
				FrForm ff=(FrForm)form;
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No ");
					String sql5="from SysUserJq where groupNo='"+user.getGroupNo()+"'";
					List list5=mm.searchAll(sql5);
					if(list5.size()>0){
						str.append(" and (");
						for(int i=0;i<list5.size();i++){
							SysUserJq sysUserJq=(SysUserJq)list5.get(i);
							if(i==0){
					    		str.append(" jq.jq_No='"+sysUserJq.getJqNo()+"'");
					    	}else{
					    		str.append(" or jq.jq_No='"+sysUserJq.getJqNo()+"'");
					    	}
					    }
						str.append(" ) ");
					}
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="select jq from JlJq jq,SysUserJq sys where jq.jy='"+ff.getJy()+"' and jq.jqNo=sys.jqNo and sys.groupNo='"+user.getGroupNo()+"'";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null && !ff.getJq().equals("null")){
						str.append("and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null && !ff.getJbNo().equals("null")){
						str.append("and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().equals("")){
						str.append("and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getFrName()!=null && !ff.getFrName().equals("")){
						str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
				}
				String hql2="from JlJb";
				List<JlJq> list2=mm.searchAll(hql2);
				String hql3="from SysParam where paramName='System_Set'";
				List<SysParam> list4=mm.searchAll(hql3);
				SysParam sysParam=(SysParam)list4.get(0);
				request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("jqIsNodisable",jqIsNodisable);
				request.setAttribute("sysParam", sysParam);
		 	} 	
			return mapping.findForward("materialMessageMain");
	}
	//查询犯人
	public ActionForward search2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	String paramData5=request.getParameter("paramData5");
		 	if(paramData5!=null){
		 		if(Integer.parseInt(paramData5)==0){
		 			StringBuffer str=new StringBuffer("update SysParam sp set sp.paramData5=1 where sp.paramName=?");
		 			Object[] obj={"System_Set"};
		 			mm.updates(str.toString(), obj);
				}if(Integer.parseInt(paramData5)==1){
					StringBuffer str=new StringBuffer("update SysParam sp set sp.paramData5=0 where sp.paramName=?");
					Object[] obj={"System_Set"};
					mm.updates(str.toString(), obj);
				}
		 	}
		 	Calendar c = Calendar.getInstance();
		 	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		 	c  =  Calendar.getInstance(Locale.CHINESE);   
		 	String loginTime = simpleDateTimeFormat.format(c.getTime());
		 	if(user.getIsSuper()==1){
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(1);
				FrForm ff=(FrForm)form;
				String frNo3 = request.getParameter("frNo3");
				String jq2 = request.getParameter("jq2");
				String jy2 = request.getParameter("jy2");
				String jbNo2 = request.getParameter("jbNo2");
				String frName2 = request.getParameter("frName2");
				String frCard2 = request.getParameter("frCard2");
				String state2 = request.getParameter("state2");
				String monitorFlag2 = request.getParameter("monitorFlag2");
				String stateZdzf2 = request.getParameter("stateZdzf2");
				String pageNo2=request.getParameter("pageNo2");
				if(jy2!=null && !jy2.equals("null")){
					ff.setJy(jy2);
				}
				if(jq2!=null  && !jq2.equals("null")){
					ff.setJq(jq2);
				}
				if(jbNo2!=null  && !jbNo2.equals("null")){
					ff.setJbNo(jbNo2);
				}
				if(pageNo2!=null && !pageNo2.equals("-1")){
					page.setPageNo(Integer.parseInt(pageNo2));
				}
				if(frName2!=null && !frName2.trim().equals("")){
					ff.setFrName(frName2);
				}
				if(frCard2!=null && !frCard2.trim().equals("")){
					ff.setFrCard(frCard2);
				}
				if(frNo3!=null && !frNo3.trim().equals("")){
					ff.setFrNo(frNo3);
				}
				if(stateZdzf2!=null && !stateZdzf2.equals("null")){
					ff.setStateZdzf(stateZdzf2);
				}
				if(state2!=null && !state2.equals("null")){
					ff.setState(state2);
				}
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null || (pageNo2!=null && !pageNo2.equals("-1"))){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No ");
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="from JlJq where jy='"+ff.getJy()+"'";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null  && !ff.getJq().equals("null")){
						str.append(" and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null  && !ff.getJbNo().equals("null")){
						str.append(" and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().trim().equals("")){
						str.append(" and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					if(ff.getFrName()!=null && !ff.getFrName().trim().equals("")){
						//str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
						str.append(" and (dbo.f_get_fryp(jl.FR_Name,'"+ff.getFrName().trim()+"') =1 or jl.FR_Name like '%"+ff.getFrName()+"%')");
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
			 	}
			 	String hql="from SysHjServer";
			 	List<SysHjServer> list=mm.searchAll(hql);
			 	String hql2="from JlJb";
			 	List<JlJq> list2=mm.searchAll(hql2);
			 	String hql3="from SysParam where paramName='System_Set'";
			 	List<SysParam> list4=mm.searchAll(hql3);
			 	SysParam sysParam=(SysParam)list4.get(0);
			 	request.setAttribute("sysQqServerList", list);
			 	request.setAttribute("jqIsNodisable",jqIsNodisable);
			 	request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("sysParam", sysParam);
		 	}else if(user.getGroupNo().equals("Admin")){
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(1);
				FrForm ff=(FrForm)form;
				String frNo3 = request.getParameter("frNo3");
				String jq2 = request.getParameter("jq2");
				String jy2 = request.getParameter("jy2");
				String jbNo2 = request.getParameter("jbNo2");
				String frName2 = request.getParameter("frName2");
				String frCard2 = request.getParameter("frCard2");
				String state2 = request.getParameter("state2");
				String monitorFlag2 = request.getParameter("monitorFlag2");
				String pageNo2=request.getParameter("pageNo2");
				String stateZdzf2 = request.getParameter("stateZdzf2");
				if(jy2!=null && !jy2.equals("null")){
					ff.setJy(jy2);
				}
				if(jq2!=null  && !jq2.equals("null")){
					ff.setJq(jq2);
				}
				if(jbNo2!=null  && !jbNo2.equals("null")){
					ff.setJbNo(jbNo2);
				}
				if(pageNo2!=null && !pageNo2.equals("-1")){
					page.setPageNo(Integer.parseInt(pageNo2));
				}
				if(frName2!=null && !frName2.trim().equals("")){
					ff.setFrName(frName2);
				}
				if(frCard2!=null && !frCard2.trim().equals("")){
					ff.setFrCard(frCard2);
				}
				if(frNo3!=null && !frNo3.trim().equals("")){
					ff.setFrNo(frNo3);
				}
				if(stateZdzf2!=null && !stateZdzf2.equals("null")){
					ff.setStateZdzf(stateZdzf2);
				}
				if(state2!=null && !state2.equals("null")){
					ff.setState(state2);
				}
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null || (pageNo2!=null && !pageNo2.equals("-1"))){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No and jq.Is_Ts!=1 ");
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="from JlJq where jy='"+ff.getJy()+"' and isTs!=1";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null  && !ff.getJq().equals("null")){
						str.append("and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null  && !ff.getJbNo().equals("null")){
						str.append("and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().equals("")){
						str.append("and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					if(ff.getFrName()!=null && !ff.getFrName().equals("")){
						//str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
						str.append(" and (dbo.f_get_fryp(jl.FR_Name,'"+ff.getFrName().trim()+"') =1 or jl.FR_Name like '%"+ff.getFrName()+"%')");
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
			 	}
			 	String hql="from SysHjServer";
			 	List<SysHjServer> list=mm.searchAll(hql);
			 	String hql2="from JlJb";
			 	List<JlJq> list2=mm.searchAll(hql2);
			 	String hql3="from SysParam where paramName='System_Set'";
			 	List<SysParam> list4=mm.searchAll(hql3);
			 	SysParam sysParam=(SysParam)list4.get(0);
			 	request.setAttribute("sysQqServerList", list);
			 	request.setAttribute("jqIsNodisable",jqIsNodisable);
			 	request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("sysParam", sysParam);
		 	}else{
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(1);
				String sql6="select distinct JY from SYS_USER_JQ where Group_No='"+user.getGroupNo()+"'";
				List list6=mm.searchAllBySql(sql6);
				if(list6.size()>0){
					StringBuffer hql=new StringBuffer("from SysHjServer ");
					Iterator it=list6.iterator();
					int i=0;
					while(it.hasNext()){
						if(i==0){
							hql.append(" where serverName='"+it.next()+"' ");
							i++;
						}else{
							hql.append("or serverName='"+it.next()+"' ");
							i++;
						}
					}
					List<SysHjServer> list=mm.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", list);
				}
				FrForm ff=(FrForm)form;
				String frNo3 = request.getParameter("frNo3");
				String jq2 = request.getParameter("jq2");
				String jy2 = request.getParameter("jy2");
				String jbNo2 = request.getParameter("jbNo2");
				String frName2 = request.getParameter("frName2");
				String frCard2 = request.getParameter("frCard2");
				String state2 = request.getParameter("state2");
				String monitorFlag2 = request.getParameter("monitorFlag2");
				String pageNo2=request.getParameter("pageNo2");
				String stateZdzf2 = request.getParameter("stateZdzf2");
				if(jy2!=null && !jy2.equals("null")){
					ff.setJy(jy2);
				}
				if(jq2!=null  && !jq2.equals("null")){
					ff.setJq(jq2);
				}
				if(jbNo2!=null  && !jbNo2.equals("null")){
					ff.setJbNo(jbNo2);
				}
				if(pageNo2!=null && !pageNo2.equals("-1")){
					page.setPageNo(Integer.parseInt(pageNo2));
				}
				if(frName2!=null && !frName2.trim().equals("")){
					ff.setFrName(frName2);
				}
				if(frCard2!=null && !frCard2.trim().equals("")){
					ff.setFrCard(frCard2);
				}
				if(frNo3!=null && !frNo3.trim().equals("")){
					ff.setFrNo(frNo3);
				}
				if(stateZdzf2!=null && !stateZdzf2.equals("null")){
					ff.setStateZdzf(stateZdzf2);
				}
				if(state2!=null && !state2.equals("null")){
					ff.setState(state2);
				}
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null || (pageNo2!=null && !pageNo2.equals("-1"))){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No ");
					String sql5="from SysUserJq where groupNo='"+user.getGroupNo()+"'";
					List list5=mm.searchAll(sql5);
					if(list5.size()>0){
						str.append(" and (");
						for(int i=0;i<list5.size();i++){
							SysUserJq sysUserJq=(SysUserJq)list5.get(i);
							if(i==0){
					    		str.append(" jq.jq_No='"+sysUserJq.getJqNo()+"'");
					    	}else{
					    		str.append(" or jq.jq_No='"+sysUserJq.getJqNo()+"'");
					    	}
					    }
						str.append(" ) ");
					}
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="select jq from JlJq jq,SysUserJq sys where jq.jy='"+ff.getJy()+"' and jq.jqNo=sys.jqNo and sys.groupNo='"+user.getGroupNo()+"'";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null && !ff.getJq().equals("null")){
						str.append("and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null && !ff.getJbNo().equals("null")){
						str.append("and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().equals("")){
						str.append("and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					if(ff.getFrName()!=null && !ff.getFrName().equals("")){
						//str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
						str.append(" and (dbo.f_get_fryp(jl.FR_Name,'"+ff.getFrName().trim()+"') =1 or jl.FR_Name like '%"+ff.getFrName()+"%')");
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
				}
				String hql2="from JlJb";
				List<JlJq> list2=mm.searchAll(hql2);
				String hql3="from SysParam where paramName='System_Set'";
				List<SysParam> list4=mm.searchAll(hql3);
				SysParam sysParam=(SysParam)list4.get(0);
				request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("jqIsNodisable",jqIsNodisable);
				request.setAttribute("sysParam", sysParam);
		 	}
		 	String hql4="from SysParam where paramName='HJ_Client1'";
			List<SysParam> list4=mm.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("sysParam1", sysParam1);
			}
			return mapping.findForward("materialMessageMain1");
	}
	//分页查询
	public ActionForward search3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	String paramData5=request.getParameter("paramData5");
		 	if(paramData5!=null){
		 		if(Integer.parseInt(paramData5)==0){
		 			StringBuffer str=new StringBuffer("update SysParam sp set sp.paramData5=1 where sp.paramName=?");
		 			Object[] obj={"System_Set"};
		 			mm.updates(str.toString(), obj);
				}if(Integer.parseInt(paramData5)==1){
					StringBuffer str=new StringBuffer("update SysParam sp set sp.paramData5=0 where sp.paramName=?");
					Object[] obj={"System_Set"};
					mm.updates(str.toString(), obj);
				}
		 	}
		 	Calendar c = Calendar.getInstance();
		 	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		 	c  =  Calendar.getInstance(Locale.CHINESE);   
		 	String loginTime = simpleDateTimeFormat.format(c.getTime());
		 	if(user.getIsSuper()==1){
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
				FrForm ff=(FrForm)form;
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No ");
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="from JlJq where jy='"+ff.getJy()+"'";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null  && !ff.getJq().equals("null")){
						str.append("and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null  && !ff.getJbNo().equals("null")){
						str.append("and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().equals("")){
						str.append("and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getFrName()!=null && !ff.getFrName().equals("")){
						str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
			 	}
			 	String hql="from SysHjServer";
			 	List<SysHjServer> list=mm.searchAll(hql);
			 	String hql2="from JlJb";
			 	List<JlJq> list2=mm.searchAll(hql2);
			 	String hql3="from SysParam where paramName='System_Set'";
			 	List<SysParam> list4=mm.searchAll(hql3);
			 	SysParam sysParam=(SysParam)list4.get(0);
			 	request.setAttribute("sysQqServerList", list);
			 	request.setAttribute("jqIsNodisable",jqIsNodisable);
			 	request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("sysParam", sysParam);
		 	}else if(user.getGroupNo().equals("Admin")){
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
				FrForm ff=(FrForm)form;
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No and jq.Is_Ts!=1");
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="from JlJq where jy='"+ff.getJy()+"'";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null  && !ff.getJq().equals("null")){
						str.append("and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null  && !ff.getJbNo().equals("null")){
						str.append("and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().equals("")){
						str.append("and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getFrName()!=null && !ff.getFrName().equals("")){
						str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						//fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
			 	}
			 	String hql="from SysHjServer";
			 	List<SysHjServer> list=mm.searchAll(hql);
			 	String hql2="from JlJb";
			 	List<JlJq> list2=mm.searchAll(hql2);
			 	String hql3="from SysParam where paramName='System_Set'";
			 	List<SysParam> list4=mm.searchAll(hql3);
			 	SysParam sysParam=(SysParam)list4.get(0);
			 	request.setAttribute("sysQqServerList", list);
			 	request.setAttribute("jqIsNodisable",jqIsNodisable);
			 	request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("sysParam", sysParam);
		 	}else{
		 		int jqIsNodisable=0;
			 	Page page=new Page();
			 	page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
				String sql6="select distinct JY from SYS_USER_JQ where Group_No='"+user.getGroupNo()+"'";
				List list6=mm.searchAllBySql(sql6);
				if(list6.size()>0){
					StringBuffer hql=new StringBuffer("from SysHjServer ");
					Iterator it=list6.iterator();
					int i=0;
					while(it.hasNext()){
						if(i==0){
							hql.append(" where serverName='"+it.next()+"' ");
							i++;
						}else{
							hql.append("or serverName='"+it.next()+"' ");
							i++;
						}
					}
					List<SysHjServer> list=mm.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", list);
				}
				FrForm ff=(FrForm)form;
				if(ff.getJy()!=null  || ff.getJq()!=null  || ff.getJbNo()!=null  || ff.getFrNo()!=null || ff.getFrName()!=null || ff.getFrCard()!=null || ff.getStateZdzf()!=null || ff.getState()!=null || ff.getIsHjStop()!=null){
					StringBuffer str=new StringBuffer("select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jl.QQ_Use,jl.QQ_Left,jq.jq_Name,dbo.get_hj_UseCount(jl.FR_No) as use1,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.State_ZDZF,jl.State,jl.ZDZF_Type,jl.FR_GJ,jl.HJ_JB,jl.FR_Card from JL_FR jl,JL_JB jb,JL_Jq jq where jl.JB_No=jb.JB_No and jl.jq=jq.jq_No ");
					String sql5="from SysUserJq where groupNo='"+user.getGroupNo()+"'";
					List list5=mm.searchAll(sql5);
					if(list5.size()>0){
						str.append(" and (");
						for(int i=0;i<list5.size();i++){
							SysUserJq sysUserJq=(SysUserJq)list5.get(i);
							if(i==0){
					    		str.append(" jq.jq_No='"+sysUserJq.getJqNo()+"'");
					    	}else{
					    		str.append(" or jq.jq_No='"+sysUserJq.getJqNo()+"'");
					    	}
					    }
						str.append(" ) ");
					}
					if(ff.getJy()!=null && !ff.getJy().equals("null")){
						jqIsNodisable=1;
						str.append("and jl.jy='"+ff.getJy()+"' ");
						String hql1="select jq from JlJq jq,SysUserJq sys where jq.jy='"+ff.getJy()+"' and jq.jqNo=sys.jqNo and sys.groupNo='"+user.getGroupNo()+"'";
					 	List<JlJq> list1=mm.searchAll(hql1);
						request.setAttribute("jlJqList", list1);
					}
					if(ff.getJq()!=null && !ff.getJq().equals("null")){
						str.append("and jl.jq='"+ff.getJq()+"' ");
					}
					if(ff.getJbNo()!=null && !ff.getJbNo().equals("null")){
						str.append("and jl.JB_No='"+ff.getJbNo()+"' ");
					}
					if(ff.getFrNo()!=null && !ff.getFrNo().equals("")){
						str.append("and jl.FR_No='"+ff.getFrNo()+"' ");
					}
					if(ff.getFrCard()!=null && !ff.getFrCard().trim().equals("")){
						str.append(" and jl.FR_Card='"+ff.getFrCard()+"' ");
					}
					if(ff.getFrName()!=null && !ff.getFrName().equals("")){
						str.append("and jl.FR_Name like '%"+ff.getFrName()+"%' ");
					}
					if(ff.getStateZdzf()!=null && !ff.getStateZdzf().equals("null")){
						str.append(" and jl.State_ZDZF="+ff.getStateZdzf()+" ");
					}
					if(ff.getState()!=null && !ff.getState().equals("null")){
						str.append(" and jl.State="+ff.getState()+" ");
					}
					if(ff.getIsHjStop()!=null && !ff.getIsHjStop().equals("null")){
						if(ff.getIsHjStop().equals("0")){
							str.append(" and jl.HJ_JB=-1 ");
						}else{
							str.append(" and jl.HJ_JB<>-1 ");
						}
						
					}
					str.append(" order by jq.jq_Name,jl.FR_No desc");
					Object[] param={};
					Map map=mm.searchToPageBySql(str.toString(), page.getPageNo(), 20, param);
					page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
					page.setPageSize(20);
					List list3=(List)map.get(Constant.RESULTLIST);
					Iterator it=list3.iterator();
					while(it.hasNext()){
						Object[] obj=(Object[])it.next();
						Fr fr=new Fr();
						fr.setWebId(obj[0].toString());
						fr.setFrNo(obj[1].toString());
						if(obj[2]!=null && obj[2].toString()!=""){
							fr.setFrName(obj[2].toString());
						}
						fr.setJy(obj[3].toString());
						fr.setJq(obj[4].toString());
						if(obj[5]!=null && obj[5].toString()!=""){
							fr.setJbName(obj[5].toString());
						}
						fr.setQqUse(obj[6].toString());
						fr.setQqLeft(obj[7].toString());
						if(obj[8]!=null && obj[8].toString()!=""){
							fr.setJqName(obj[8].toString());
						}
						fr.setHjUse(obj[9].toString());
						fr.setHjLeft(obj[10].toString());
						if(obj[11]!=null && obj[11].toString()!=""){
							fr.setHjLastTime(obj[11].toString().substring(0, 10));
						}
//						if(obj[12]!=null && obj[12].toString()!=""){
//							if(fr.getHjLastTime()!=null && !fr.getHjLastTime().equals(loginTime.substring(0, 10))){
//								fr.setHjUse("0");
//							}else{
//								fr.setHjUse(obj[12].toString());
//							}
//						}else{
//							fr.setHjUse("0");
//						}
						fr.setStateZdzf(obj[13].toString());
						fr.setState(obj[14].toString());
						if(obj[15]!=null && obj[15].toString()!=""){
							fr.setZdzfType(obj[15].toString());
						}
						if(obj[16]!=null && obj[16].toString()!=""){
							fr.setFrGj(obj[16].toString());
						}
						if(obj[17]!=null && obj[17].toString()!=""){
							fr.setHjJb(obj[17].toString());
						}
						if(obj[18]!=null && obj[18].toString()!=""){
							fr.setFrCard(obj[18].toString());
						}
						page.getList().add(fr);
					}
				}
				String hql2="from JlJb";
				List<JlJq> list2=mm.searchAll(hql2);
				String hql3="from SysParam where paramName='System_Set'";
				List<SysParam> list4=mm.searchAll(hql3);
				SysParam sysParam=(SysParam)list4.get(0);
				request.setAttribute("jlJbList", list2);
				request.setAttribute("page", page);
				request.setAttribute("jqIsNodisable",jqIsNodisable);
				request.setAttribute("sysParam", sysParam);
		 	} 	
			return mapping.findForward("materialMessageMain1");
	}
	//添加犯人
	public ActionForward addFr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    String hql3="from SysParam where paramName='System_Set'";
			List<SysParam> list3=mm.searchAll(hql3);
			SysParam sysParam=(SysParam)list3.get(0);
			String frNo="";
			if(sysParam.getParamData5()!=null && sysParam.getParamData5().equals("1")){
				frNo=System.currentTimeMillis()+"";
			}
			if(user.getIsSuper()==1 || user.getGroupNo().equals("Admin")){
				String hql="from SysHjServer";
				List<SysHjServer> list=mm.searchAll(hql);
				request.setAttribute("sysQqServerList", list);
		    }else{
		    	String hql1="select distinct jl.serverName  from SysUserJq suj,SysHjServer jl where jl.serverName=suj.jy and suj.groupNo='"+user.getGroupNo()+"'";
				List list1=mm.searchAll(hql1);
				Iterator it1=list1.iterator();
				List<SysHjServer> list4=new ArrayList();
				while(it1.hasNext()){
					SysHjServer sysQqServer=new SysHjServer();
					sysQqServer.setServerName(it1.next().toString());
					list4.add(sysQqServer);
				}
				request.setAttribute("sysQqServerList", list4);
		    }
			String hql2="from JlJb";
			List<JlJq> list2=mm.searchAll(hql2);
			request.setAttribute("jlJbList", list2);
			request.setAttribute("frNo", frNo);
			
			String frNo3 = java.net.URLDecoder.decode((String)request.getParameter("frNo3"),"UTF-8");
			String jq2 = java.net.URLDecoder.decode((String)request.getParameter("jq2"),"UTF-8");
			String jy2 = java.net.URLDecoder.decode((String)request.getParameter("jy2"),"UTF-8");
			String jbNo2 = java.net.URLDecoder.decode((String)request.getParameter("jbNo2"),"UTF-8");
			String frName2 = java.net.URLDecoder.decode((String)request.getParameter("frName2"),"UTF-8");			
			String pageNo2=request.getParameter("pageNo2");
			String stateZdzf2 = java.net.URLDecoder.decode((String)request.getParameter("stateZdzf2"),"UTF-8");
			String state2 = java.net.URLDecoder.decode((String)request.getParameter("state2"),"UTF-8");
			String frCard2 = java.net.URLDecoder.decode((String)request.getParameter("frCard2"),"UTF-8");
			request.setAttribute("frCard2", frCard2);
			request.setAttribute("state2", state2);
			request.setAttribute("stateZdzf2", stateZdzf2);
			request.setAttribute("frNo3", frNo3);
			request.setAttribute("pageNo2", pageNo2);
			request.setAttribute("jq2", jq2);
			request.setAttribute("jy2", jy2);
			request.setAttribute("jbNo2", jbNo2);
			request.setAttribute("frName2", frName2);
			
			
		    return mapping.findForward("addMaterialMessage");
	}
	//添加保存犯人
	public ActionForward addSaveFr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String frNo = java.net.URLDecoder.decode((String)request.getParameter("frNo"),"UTF-8");
			String jq = java.net.URLDecoder.decode((String)request.getParameter("jq"),"UTF-8");
			String jy = java.net.URLDecoder.decode((String)request.getParameter("jy"),"UTF-8");
			String jbNo = java.net.URLDecoder.decode((String)request.getParameter("jb"),"UTF-8");
			String frName = java.net.URLDecoder.decode((String)request.getParameter("frName"),"UTF-8");
			String frCard = java.net.URLDecoder.decode((String)request.getParameter("frCard"),"UTF-8");
			String frGj = java.net.URLDecoder.decode((String)request.getParameter("frGj"),"UTF-8");
			String stateZdzf = request.getParameter("stateZdzf");
			String infoRjsj=request.getParameter("infoRjsj");
			String infoZm=request.getParameter("infoZm");
			String infoXq=request.getParameter("infoXq");
			String infoCsrq=request.getParameter("infoCsrq");
			String infoHome=request.getParameter("infoHome");
			String zdzfType = java.net.URLDecoder.decode((String)request.getParameter("zdzfType"),"UTF-8");
			String hjjb=request.getParameter("hjjb");
			String monitorFlag=request.getParameter("monitorFlag");
			StringBuffer str=new StringBuffer("from JlFr where frNo='");
			str.append(frNo+"'");
			
			List list=mm.searchAll(str.toString());
			if(list.size()>0){
				JSONArray json=JSONArray.fromObject(-1);
				response.getWriter().println(json.toString());
			}else{
				if(frCard!=null && !frCard.trim().equals("")){
					String hql11="update JlFr set frCard=null where frCard='"+frCard+"'";
					Object[] obj={};
					mm.updates(hql11, obj);
					Calendar c = Calendar.getInstance();
				 	StringBuffer str2=new StringBuffer("");
				 	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
				 	c  =  Calendar.getInstance(Locale.CHINESE);   
				 	String loginTime = simpleDateTimeFormat.format(c.getTime());
					StringBuffer str1=new StringBuffer("from JlJb jlJb where jlJb.jbNo='");
					str1.append(jbNo+"'");
					List<JlJb> list1=mm.searchAll(str1.toString());
					JlJb jlJb=(JlJb)list1.get(0);
					JlFr jlFr=new JlFr();
				 	jlFr.setFrNo(frNo);
				 	if(frName!="" && frName!=null){
				 		jlFr.setFrName(frName);
				 	}
				 	if(frCard!="" && frCard!=null){
				 		jlFr.setFrCard(frCard);
				 	}
				 	jlFr.setJy(jy);
				 	jlFr.setJq(jq);
				 	jlFr.setJbNo(jbNo);
				 	jlFr.setQqJb(1);
				 	jlFr.setQqUse(0);
				 	jlFr.setQqLeft(jlJb.getQqCount());
				 	jlFr.setHjJb(Integer.parseInt(hjjb));
				 	jlFr.setHjUse(0);
				 	jlFr.setHjLeft(jlJb.getHjCount());
				 	jlFr.setMonitorFlag(monitorFlag);
				 	jlFr.setStateZdzf(Integer.parseInt(stateZdzf));
				 	if(zdzfType!="" && zdzfType!=null){
				 		jlFr.setZdzfType(zdzfType);
				 	}
				 	jlFr.setState(0);
				 	jlFr.setSpState(1);
				 	jlFr.setInfoRjsj(infoRjsj);
				 	jlFr.setInfoZm(infoZm);
				 	jlFr.setInfoXq(infoXq);
				 	jlFr.setInfoCsrq(infoCsrq);
				 	jlFr.setInfoHome(infoHome);
				 	jlFr.setFrGj(frGj);
				 	String hql="from JlJb where jbNo='"+jbNo+"'";
				 	List<JlJb> list2=mm.searchAll(hql);
				 	if(list2.size()>0){
				 		JlJb jlJb2=(JlJb)list2.get(0);
				 		if(jlJb2.getAutoDown()==1){
				 			jlFr.setJbSetTime(Integer.parseInt(loginTime.substring(0,4)+loginTime.substring(5, 7)));
				 			jlFr.setJbSetType(0);
				 		}
				 	}
				 	SysLog sl=new SysLog();
				 	sl.setType("正常");
				 	sl.setLogTime(loginTime);
				 	sl.setUserName(user.getUserName());
				 	sl.setUserNo(user.getUserNo());
				 	sl.setUserIp(request.getRemoteAddr());
				 	sl.setModel("罪犯管理");
				 	sl.setInfo(str2.append("增加罪犯（编号：").append(frNo).append(" 罪犯姓名：").append(frName).append(" 所属监区：").append(jq).append(" 级别编号：").append(jbNo).append("）").toString());
				 	sl.setOp("增加罪犯信息");
				 	mm.save(jlFr);
				 	mm.save(sl);
				 	JSONArray json=JSONArray.fromObject(1);
				 	response.getWriter().println(json.toString());
				}else{
					Calendar c = Calendar.getInstance();
				 	StringBuffer str2=new StringBuffer("");
				 	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
				 	c  =  Calendar.getInstance(Locale.CHINESE);   
				 	String loginTime = simpleDateTimeFormat.format(c.getTime());
					StringBuffer str1=new StringBuffer("from JlJb jlJb where jlJb.jbNo='");
					str1.append(jbNo+"'");
					List<JlJb> list1=mm.searchAll(str1.toString());
					JlJb jlJb=(JlJb)list1.get(0);
					JlFr jlFr=new JlFr();
				 	jlFr.setFrNo(frNo);
				 	if(frName!="" && frName!=null){
				 		jlFr.setFrName(frName);
				 	}
				 	if(frCard!="" && frCard!=null){
				 		jlFr.setFrCard(frCard);
				 	}
				 	jlFr.setJy(jy);
				 	jlFr.setJq(jq);
				 	jlFr.setJbNo(jbNo);
				 	jlFr.setQqJb(1);
				 	jlFr.setQqUse(0);
				 	jlFr.setQqLeft(jlJb.getQqCount());
				 	jlFr.setHjJb(Integer.parseInt(hjjb));
				 	jlFr.setHjUse(0);
				 	jlFr.setHjLeft(jlJb.getHjCount());
				 	jlFr.setMonitorFlag(monitorFlag);
				 	jlFr.setStateZdzf(Integer.parseInt(stateZdzf));
				 	if(zdzfType!="" && zdzfType!=null){
				 		jlFr.setZdzfType(zdzfType);
				 	}
				 	jlFr.setState(0);
				 	jlFr.setSpState(1);
				 	jlFr.setInfoRjsj(infoRjsj);
				 	jlFr.setInfoZm(infoZm);
				 	jlFr.setInfoXq(infoXq);
				 	jlFr.setInfoCsrq(infoCsrq);
				 	jlFr.setInfoHome(infoHome);
				 	jlFr.setFrGj(frGj);
				 	String hql="from JlJb where jbNo='"+jbNo+"'";
				 	List<JlJb> list2=mm.searchAll(hql);
				 	if(list2.size()>0){
				 		JlJb jlJb2=(JlJb)list2.get(0);
				 		if(jlJb2.getAutoDown()==1){
				 			jlFr.setJbSetTime(Integer.parseInt(loginTime.substring(0,4)+loginTime.substring(5, 7)));
				 			jlFr.setJbSetType(0);
				 		}
				 	}
				 	SysLog sl=new SysLog();
				 	sl.setType("正常");
				 	sl.setLogTime(loginTime);
				 	sl.setUserName(user.getUserName());
				 	sl.setUserNo(user.getUserNo());
				 	sl.setUserIp(request.getRemoteAddr());
				 	sl.setModel("罪犯管理");
				 	sl.setInfo(str2.append("增加罪犯（编号：").append(frNo).append(" 罪犯姓名：").append(frName).append(" 所属监区：").append(jq).append(" 级别编号：").append(jbNo).append("）").toString());
				 	sl.setOp("增加罪犯信息");
				 	mm.save(jlFr);
				 	mm.save(sl);
				 	JSONArray json=JSONArray.fromObject(1);
				 	response.getWriter().println(json.toString());
				}
				
			}
			return null;
	}
	//删除犯人
	public ActionForward delFr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
		  	String frId=request.getParameter("frId");
		  	JlFr jlFr=(JlFr)mm.findById(JlFr.class, Integer.parseInt(frId));
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
		  	sl.setInfo("删除罪犯（罪犯编号："+jlFr.getFrNo()+" 罪犯姓名："+jlFr.getFrName()+"）");
		  	sl.setOp("删除罪犯信息");
		  	mm.save(sl);
		  	StringBuffer str=new StringBuffer("delete from JlQs jl where jl.frNo=?");
		  	Object[] obj={jlFr.getFrNo()};
		  	//StringBuffer str1=new StringBuffer("delete from JlQqTpdh jl where jl.frNo=?");
		  	mm.deleteByHql(str.toString(), obj);
		  	//mm.deleteByHql(str1.toString(), obj);
		  	mm.delete(JlFr.class, Integer.parseInt(frId));
		  	String frNo3 = java.net.URLDecoder.decode((String)request.getParameter("frNo3"),"UTF-8");
			String jq2 = java.net.URLDecoder.decode((String)request.getParameter("jq2"),"UTF-8");
			String jy2 = java.net.URLDecoder.decode((String)request.getParameter("jy2"),"UTF-8");
			String jbNo2 = java.net.URLDecoder.decode((String)request.getParameter("jbNo2"),"UTF-8");
			String frName2 = java.net.URLDecoder.decode((String)request.getParameter("frName2"),"UTF-8");			
			String pageNo2=request.getParameter("pageNo2");
			String stateZdzf2 = java.net.URLDecoder.decode((String)request.getParameter("stateZdzf2"),"UTF-8");
			String state2 = java.net.URLDecoder.decode((String)request.getParameter("state2"),"UTF-8");
			String frCard2 = java.net.URLDecoder.decode((String)request.getParameter("frCard2"),"UTF-8");
			request.setAttribute("frCard2", frCard2);
			request.setAttribute("state2", state2);
			request.setAttribute("stateZdzf2", stateZdzf2);
			request.setAttribute("frNo3", frNo3);
			request.setAttribute("pageNo2", pageNo2);
			request.setAttribute("jq2", jq2);
			request.setAttribute("jy2", jy2);
			request.setAttribute("jbNo2", jbNo2);
			request.setAttribute("frName2", frName2);
		  	return mapping.findForward("delSuccess");
	}
	//修改犯人
	public ActionForward updateFr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String frId=request.getParameter("frId");
			JlFr jlFr=(JlFr)mm.findById(JlFr.class, Integer.parseInt(frId));
			if(user.getIsSuper()==1){
				String hql="from SysHjServer";
				List<SysHjServer> list=mm.searchAll(hql);
				request.setAttribute("sysQqServerList", list);
				String hql1="from JlJq where jy='"+jlFr.getJy()+"'";
				List<JlJq> jlJqList=mm.searchAll(hql1);
				request.setAttribute("jlJqList", jlJqList);
			}else if( user.getGroupNo().equals("Admin")){
				String hql="from SysHjServer";
				List<SysHjServer> list=mm.searchAll(hql);
				request.setAttribute("sysQqServerList", list);
				String hql1="from JlJq where jy='"+jlFr.getJy()+"' and isTs!=1";
				List<JlJq> jlJqList=mm.searchAll(hql1);
				request.setAttribute("jlJqList", jlJqList);
			}else{
				String hql1="select distinct jl.serverName  from SysUserJq suj,SysHjServer jl where jl.serverName=suj.jy and suj.groupNo='"+user.getGroupNo()+"'";
				List list1=mm.searchAll(hql1);
				Iterator it1=list1.iterator();
				List<SysHjServer> list4=new ArrayList();
				while(it1.hasNext()){
					SysHjServer sysQqServer=new SysHjServer();
					sysQqServer.setServerName(it1.next().toString());
					list4.add(sysQqServer);
				}
				request.setAttribute("sysQqServerList", list4);
				String hql2="select jl.jqNo,jl.jqName  from SysUserJq suj,JlJq jl where jl.jqNo=suj.jqNo and suj.groupNo='"+user.getGroupNo()+"'"+" and suj.jy='"+jlFr.getJy()+"'";
				List list2=mm.searchAll(hql2);
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
			Fr fr=new Fr();
			fr.setWebId(jlFr.getWebId()+"");
			fr.setFrNo(jlFr.getFrNo());
			if(jlFr.getFrName()!=null){
				fr.setFrName(jlFr.getFrName());
			}
			fr.setState(jlFr.getState()+"");
			if(jlFr.getOutTime()!=null){
				fr.setOutTime(jlFr.getOutTime().toString().substring(0, 10));
			}
			String hql2="from JlJb";
			List<JlJq> list2=mm.searchAll(hql2);
			request.setAttribute("jlFr", jlFr);
			request.setAttribute("Fr", fr);
			request.setAttribute("jlJbList", list2);
			
			String frNo3 = java.net.URLDecoder.decode((String)request.getParameter("frNo3"),"UTF-8");
			String jq2 = java.net.URLDecoder.decode((String)request.getParameter("jq2"),"UTF-8");
			String jy2 = java.net.URLDecoder.decode((String)request.getParameter("jy2"),"UTF-8");
			String jbNo2 = java.net.URLDecoder.decode((String)request.getParameter("jbNo2"),"UTF-8");
			String frName2 = java.net.URLDecoder.decode((String)request.getParameter("frName2"),"UTF-8");			
			String pageNo2=request.getParameter("pageNo2");
			String stateZdzf2 = java.net.URLDecoder.decode((String)request.getParameter("stateZdzf2"),"UTF-8");
			String state2 = java.net.URLDecoder.decode((String)request.getParameter("state2"),"UTF-8");
			String frCard2 = java.net.URLDecoder.decode((String)request.getParameter("frCard2"),"UTF-8");
			request.setAttribute("frCard2", frCard2);
			request.setAttribute("state2", state2);
			request.setAttribute("stateZdzf2", stateZdzf2);
			request.setAttribute("frNo3", frNo3);
			request.setAttribute("pageNo2", pageNo2);
			request.setAttribute("jq2", jq2);
			request.setAttribute("jy2", jy2);
			request.setAttribute("jbNo2", jbNo2);
			request.setAttribute("frName2", frName2);			
			return mapping.findForward("updateFr");
	}
	//修改保存犯人
	public ActionForward updateSaveFr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String frId = java.net.URLDecoder.decode((String)request.getParameter("frId"),"UTF-8");
			String jq = java.net.URLDecoder.decode((String)request.getParameter("jq"),"UTF-8");
			String jy = java.net.URLDecoder.decode((String)request.getParameter("jy"),"UTF-8");
			String jbNo = java.net.URLDecoder.decode((String)request.getParameter("jb"),"UTF-8");
			String frName = java.net.URLDecoder.decode((String)request.getParameter("frName"),"UTF-8");
			String frCard = java.net.URLDecoder.decode((String)request.getParameter("frCard"),"UTF-8");
			String hjjb=request.getParameter("hjjb");
			String monitorFlag=request.getParameter("monitorFlag");
			String hjleft=request.getParameter("hjleft");
			String stateZdzf = request.getParameter("stateZdzf");
			String infoRjsj=request.getParameter("infoRjsj");
			String infoZm=request.getParameter("infoZm");
			String infoXq=request.getParameter("infoXq");
			String infoCsrq=request.getParameter("infoCsrq");
			String infoHome=request.getParameter("infoHome");
			String frGj=request.getParameter("frGj");
			String state=request.getParameter("state");
			String outTime=request.getParameter("outTime");
			String hjStopTime=request.getParameter("hjStopTime");
			String hjStopSM=request.getParameter("hjStopSM");
			String zdzfType = java.net.URLDecoder.decode((String)request.getParameter("zdzfType"),"UTF-8");
			if(!frCard.trim().equals("")){
				String hql3="from JlFr  where webId!="+frId+" and frCard='"+frCard+"'";
				List<JlFr> list3=mm.searchAll(hql3);
				if(list3.size()>0){
					JSONArray json=JSONArray.fromObject(list3);
					response.getWriter().println(json.toString());
				}else{
					StringBuffer str=new StringBuffer("update JlFr jl set ");
					Calendar c = Calendar.getInstance();
					SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
					c  =  Calendar.getInstance(Locale.CHINESE);   
					String loginTime = simpleDateTimeFormat.format(c.getTime());
					if(frName!="" && frName!=null){
						str.append("jl.frName='"+frName+"',");
					}else{
						str.append("jl.frName=null,");
					}
					if(frCard!="" && frCard!=null){
						str.append("jl.frCard='"+frCard+"',");
					}else{
						str.append("jl.frCard=null,");
					}
					if(infoRjsj!="" && infoRjsj!=null){
						str.append("jl.infoRjsj='"+infoRjsj+"',");
					}else{
						str.append("jl.infoRjsj=null,");
					}
					if(infoZm!="" && infoZm!=null){
						str.append("jl.infoZm='"+infoZm+"',");
					}else{
						str.append("jl.infoZm=null,");
					}
					if(infoXq!="" && infoXq!=null){
						str.append("jl.infoXq='"+infoXq+"',");
					}else{
						str.append("jl.infoXq=null,");
					}
					if(infoCsrq!="" && infoCsrq!=null){
						str.append("jl.infoCsrq='"+infoCsrq+"',");
					}else{
						str.append("jl.infoCsrq=null,");
					}
					if(infoHome!="" && infoHome!=null){
						str.append("jl.infoHome='"+infoHome+"',");
					}else{
						str.append("jl.infoHome=null,");
					}
					if(frGj!="" && frGj!=null){
						str.append("jl.frGj='"+frGj+"',");
					}else{
						str.append("jl.frGj=null,");
					}
					str.append("jl.jq='"+jq+"',");
					str.append("jl.jy='"+jy+"',");
					str.append("jl.jbNo='"+jbNo+"',");
					JlFr jlFr=(JlFr)mm.findById(JlFr.class, Integer.parseInt(frId));
					StringBuffer str2=new StringBuffer("");
					if((frName!=null && !jlFr.getFrName().equals(frName)) || !jlFr.getJq().equals(jq) || !jlFr.getJbNo().equals(jbNo)){
						str2.append("罪犯编号为"+jlFr.getFrNo()+"的罪犯");
						if(frName!=null && !jlFr.getFrName().equals(frName)){
							str2.append(" 姓名由"+jlFr.getFrName()+"修改为"+frName);
						}
						if(!jlFr.getJq().equals(jq)){
							str2.append(" 监区编号由"+jlFr.getJq()+"修改为"+jq);
						}	
						if(!jlFr.getJbNo().equals(jbNo)){
							str2.append(" 级别编号由"+jlFr.getJbNo()+"修改为"+jbNo);
							String hql="from JlJb where jbNo='"+jbNo+"'";
						 	List<JlJb> list2=mm.searchAll(hql);
						 	if(list2.size()>0){
						 		JlJb jlJb2=(JlJb)list2.get(0);
						 		if(jlJb2.getAutoDown()==1){
						 			str.append("jl.jbSetTime="+Integer.parseInt(loginTime.substring(0,4)+loginTime.substring(5, 7))+",");
						 			str.append("jl.jbSetType="+0+",");
						 		}else{
						 			str.append("jl.jbSetTime=null,");
						 			str.append("jl.jbSetType=null,");
						 		}
						 	}
						}
						SysLog sl=new SysLog();
						sl.setType("正常");
						sl.setLogTime(loginTime);
						sl.setUserName(user.getUserName());
						sl.setUserNo(user.getUserNo());
						sl.setUserIp(request.getRemoteAddr());
						sl.setModel("罪犯管理");
						sl.setInfo(str2.toString());
						sl.setOp("修改罪犯信息");
						mm.save(sl);
					}
					if(!outTime.trim().equals("")){
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						java.util.Date date11 = df1.parse(outTime+" 00:00:00");
						String time = df1.format(date11);
						Timestamp ts = Timestamp.valueOf(time);
						str.append("jl.outTime='"+outTime+"',");
					}else{
						str.append("jl.outTime=null,");
					}
				 	if(Integer.parseInt(state)!=jlFr.getState()){
				 		Calendar c1 = Calendar.getInstance();
					 	StringBuffer str22=new StringBuffer("");
					 	SimpleDateFormat simpleDateTimeFormat1  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");   
					 	c  =  Calendar.getInstance(Locale.CHINESE);   
					 	String loginTime1 = simpleDateTimeFormat.format(c1.getTime());
				 		SysLog sl=new SysLog();
					 	sl.setType("正常");
					 	sl.setLogTime(loginTime1);
					 	sl.setUserName(user.getUserName());
					 	sl.setUserNo(user.getUserNo());
					 	sl.setUserIp(request.getRemoteAddr());
					 	sl.setModel("罪犯管理");
					 	if(Integer.parseInt(state)==1){
					 		sl.setInfo(jlFr.getFrName()+"（编号："+jlFr.getFrNo()+"）于"+outTime+"出狱");
					 	}else{
					 		sl.setInfo(jlFr.getFrName()+"（编号："+jlFr.getFrNo()+"）由出狱状态修改为服刑状态");
					 	}
					 	sl.setOp("修改服刑状态");
					 	mm.save(sl);
					 	
				 	}
					str.append("jl.state="+state+",");
					str.append("jl.hjLeft="+Integer.parseInt(hjleft)+",");
					str.append("jl.hjJb="+hjjb+",");
					str.append("jl.monitorFlag='"+monitorFlag+"',");
					str.append("jl.stateZdzf="+stateZdzf+",");
					if(zdzfType!="" && zdzfType!=null){
						str.append("jl.zdzfType='"+zdzfType+"',");
					}else{
						str.append("jl.zdzfType=null,");
					}
					if(hjStopTime.equals("")){
						str.append("jl.hjStopTime=NULL,");
					}else{
						str.append("jl.hjStopTime='"+hjStopTime+"',");
					}
					str.append("jl.hjStopSM='"+hjStopSM+"'");
					str.append(" where jl.webId=?");
					Object[] obj={Integer.parseInt(frId)};
					mm.updates(str.toString(),obj);
					JSONArray json=JSONArray.fromObject(null);
					response.getWriter().println(json.toString());
				}
			}else{
				StringBuffer str=new StringBuffer("update JlFr jl set ");
				Calendar c = Calendar.getInstance();
				SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
				c  =  Calendar.getInstance(Locale.CHINESE);   
				String loginTime = simpleDateTimeFormat.format(c.getTime());
				if(frName!="" && frName!=null){
					str.append("jl.frName='"+frName+"',");
				}else{
					str.append("jl.frName=null,");
				}
				if(frCard!="" && frCard!=null){
					str.append("jl.frCard='"+frCard+"',");
				}else{
					str.append("jl.frCard=null,");
				}
				if(infoRjsj!="" && infoRjsj!=null){
					str.append("jl.infoRjsj='"+infoRjsj+"',");
				}else{
					str.append("jl.infoRjsj=null,");
				}
				if(infoZm!="" && infoZm!=null){
					str.append("jl.infoZm='"+infoZm+"',");
				}else{
					str.append("jl.infoZm=null,");
				}
				if(infoXq!="" && infoXq!=null){
					str.append("jl.infoXq='"+infoXq+"',");
				}else{
					str.append("jl.infoXq=null,");
				}
				if(infoCsrq!="" && infoCsrq!=null){
					str.append("jl.infoCsrq='"+infoCsrq+"',");
				}else{
					str.append("jl.infoCsrq=null,");
				}
				if(infoHome!="" && infoHome!=null){
					str.append("jl.infoHome='"+infoHome+"',");
				}else{
					str.append("jl.infoHome=null,");
				}
				if(frGj!="" && frGj!=null){
					str.append("jl.frGj='"+frGj+"',");
				}else{
					str.append("jl.frGj=null,");
				}
				str.append("jl.jq='"+jq+"',");
				str.append("jl.jy='"+jy+"',");
				str.append("jl.jbNo='"+jbNo+"',");
				JlFr jlFr=(JlFr)mm.findById(JlFr.class, Integer.parseInt(frId));
				StringBuffer str2=new StringBuffer("");
				if((frName!=null && !jlFr.getFrName().equals(frName)) || !jlFr.getJq().equals(jq) || !jlFr.getJbNo().equals(jbNo)){
					str2.append("罪犯编号为"+jlFr.getFrNo()+"的罪犯");
					if(frName!=null && !jlFr.getFrName().equals(frName)){
						str2.append(" 姓名由"+jlFr.getFrName()+"修改为"+frName);
					}
					if(!jlFr.getJq().equals(jq)){
						str2.append(" 监区编号由"+jlFr.getJq()+"修改为"+jq);
					}	
					if(!jlFr.getJbNo().equals(jbNo)){
						str2.append(" 级别编号由"+jlFr.getJbNo()+"修改为"+jbNo);
						String hql="from JlJb where jbNo='"+jbNo+"'";
					 	List<JlJb> list2=mm.searchAll(hql);
					 	if(list2.size()>0){
					 		JlJb jlJb2=(JlJb)list2.get(0);
					 		if(jlJb2.getAutoDown()==1){
					 			str.append("jl.jbSetTime="+Integer.parseInt(loginTime.substring(0,4)+loginTime.substring(5, 7))+",");
					 			str.append("jl.jbSetType="+0+",");
					 		}else{
					 			str.append("jl.jbSetTime=null,");
					 			str.append("jl.jbSetType=null,");
					 		}
					 	}
					}
					SysLog sl=new SysLog();
					sl.setType("正常");
					sl.setLogTime(loginTime);
					sl.setUserName(user.getUserName());
					sl.setUserNo(user.getUserNo());
					sl.setUserIp(request.getRemoteAddr());
					sl.setModel("罪犯管理");
					sl.setInfo(str2.toString());
					sl.setOp("修改罪犯信息");
					mm.save(sl);
				}
				if(!outTime.trim().equals("")){
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date date11 = df1.parse(outTime+" 00:00:00");
					String time = df1.format(date11);
					Timestamp ts = Timestamp.valueOf(time);
					str.append("jl.outTime='"+outTime+"',");
				}else{
					str.append("jl.outTime=null,");
				}
			 	if(Integer.parseInt(state)!=jlFr.getState()){
			 		Calendar c1 = Calendar.getInstance();
				 	StringBuffer str22=new StringBuffer("");
				 	SimpleDateFormat simpleDateTimeFormat1  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");   
				 	c  =  Calendar.getInstance(Locale.CHINESE);   
				 	String loginTime1 = simpleDateTimeFormat.format(c1.getTime());
			 		SysLog sl=new SysLog();
				 	sl.setType("正常");
				 	sl.setLogTime(loginTime1);
				 	sl.setUserName(user.getUserName());
				 	sl.setUserNo(user.getUserNo());
				 	sl.setUserIp(request.getRemoteAddr());
				 	sl.setModel("罪犯管理");
				 	if(Integer.parseInt(state)==1){
				 		sl.setInfo(jlFr.getFrName()+"（编号："+jlFr.getFrNo()+"）于"+outTime+"出狱");
				 	}else{
				 		sl.setInfo(jlFr.getFrName()+"（编号："+jlFr.getFrNo()+"）由出狱状态修改为服刑状态");
				 	}
				 	sl.setOp("修改服刑状态");
				 	mm.save(sl);
				 	
			 	}
				str.append("jl.state="+state+",");
				str.append("jl.hjLeft="+Integer.parseInt(hjleft)+",");
				str.append("jl.hjJb="+hjjb+",");
				str.append("jl.monitorFlag='"+monitorFlag+"',");
				str.append("jl.stateZdzf="+stateZdzf+",");
				if(zdzfType!="" && zdzfType!=null){
					str.append("jl.zdzfType='"+zdzfType+"',");
				}else{
					str.append("jl.zdzfType=null,");
				}
				if(hjStopTime.equals("")){
					str.append("jl.hjStopTime=NULL,");
				}else{
					str.append("jl.hjStopTime='"+hjStopTime+"',");
				}
				str.append("jl.hjStopSM='"+hjStopSM+"'");
				str.append(" where jl.webId=?");
				Object[] obj={Integer.parseInt(frId)};
				mm.updates(str.toString(),obj);
				JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
			}
			
			return  null;
	}
	//修改犯人
	public ActionForward updateFr1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String frId=request.getParameter("frId");
			JlFr jlFr=(JlFr)mm.findById(JlFr.class, Integer.parseInt(frId));
			if(user.getIsSuper()==1){
				String hql="from SysHjServer";
				List<SysHjServer> list=mm.searchAll(hql);
				request.setAttribute("sysQqServerList", list);
				String hql1="from JlJq where jy='"+jlFr.getJy()+"'";
				List<JlJq> jlJqList=mm.searchAll(hql1);
				request.setAttribute("jlJqList", jlJqList);
			}else if( user.getGroupNo().equals("Admin")){
				String hql="from SysHjServer";
				List<SysHjServer> list=mm.searchAll(hql);
				request.setAttribute("sysQqServerList", list);
				String hql1="from JlJq where jy='"+jlFr.getJy()+"' and isTs!=1";
				List<JlJq> jlJqList=mm.searchAll(hql1);
				request.setAttribute("jlJqList", jlJqList);
			}else{
				String hql1="select distinct jl.serverName  from SysUserJq suj,SysHjServer jl where jl.serverName=suj.jy and suj.groupNo='"+user.getGroupNo()+"'";
				List list1=mm.searchAll(hql1);
				Iterator it1=list1.iterator();
				List<SysHjServer> list4=new ArrayList();
				while(it1.hasNext()){
					SysHjServer sysQqServer=new SysHjServer();
					sysQqServer.setServerName(it1.next().toString());
					list4.add(sysQqServer);
				}
				request.setAttribute("sysQqServerList", list4);
				String hql2="select jl.jqNo,jl.jqName  from SysUserJq suj,JlJq jl where jl.jqNo=suj.jqNo and suj.groupNo='"+user.getGroupNo()+"'"+" and suj.jy='"+jlFr.getJy()+"'";
				List list2=mm.searchAll(hql2);
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
			Fr fr=new Fr();
			fr.setWebId(jlFr.getWebId()+"");
			fr.setFrNo(jlFr.getFrNo());
			if(jlFr.getFrName()!=null){
				fr.setFrName(jlFr.getFrName());
			}
			fr.setState(jlFr.getState()+"");
			if(jlFr.getOutTime()!=null){
				fr.setOutTime(jlFr.getOutTime().toString().substring(0, 10));
			}
			String hql2="from JlJb";
			List<JlJq> list2=mm.searchAll(hql2);
			request.setAttribute("jlFr", jlFr);
			request.setAttribute("Fr", fr);
			request.setAttribute("jlJbList", list2);
			
			String frNo3 = java.net.URLDecoder.decode((String)request.getParameter("frNo3"),"UTF-8");
			String jq2 = java.net.URLDecoder.decode((String)request.getParameter("jq2"),"UTF-8");
			String jy2 = java.net.URLDecoder.decode((String)request.getParameter("jy2"),"UTF-8");
			String jbNo2 = java.net.URLDecoder.decode((String)request.getParameter("jbNo2"),"UTF-8");
			String frName2 = java.net.URLDecoder.decode((String)request.getParameter("frName2"),"UTF-8");			
			String pageNo2=request.getParameter("pageNo2");
			String stateZdzf2 = java.net.URLDecoder.decode((String)request.getParameter("stateZdzf2"),"UTF-8");
			String state2 = java.net.URLDecoder.decode((String)request.getParameter("state2"),"UTF-8");
			String frCard2 = java.net.URLDecoder.decode((String)request.getParameter("frCard2"),"UTF-8");
			request.setAttribute("frCard2", frCard2);
			request.setAttribute("state2", state2);
			request.setAttribute("stateZdzf2", stateZdzf2);
			request.setAttribute("frNo3", frNo3);
			request.setAttribute("pageNo2", pageNo2);
			request.setAttribute("jq2", jq2);
			request.setAttribute("jy2", jy2);
			request.setAttribute("jbNo2", jbNo2);
			request.setAttribute("frName2", frName2);			
			return mapping.findForward("updateFr1");
	}
	//修改保存犯人
	public ActionForward updateSaveFr1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String frId = java.net.URLDecoder.decode((String)request.getParameter("frId"),"UTF-8");
			String jq = java.net.URLDecoder.decode((String)request.getParameter("jq"),"UTF-8");
			String jy = java.net.URLDecoder.decode((String)request.getParameter("jy"),"UTF-8");
			String jbNo = java.net.URLDecoder.decode((String)request.getParameter("jb"),"UTF-8");
			String frName = java.net.URLDecoder.decode((String)request.getParameter("frName"),"UTF-8");
			String frCard = java.net.URLDecoder.decode((String)request.getParameter("frCard"),"UTF-8");
			String hjjb=request.getParameter("hjjb");
			String monitorFlag=request.getParameter("monitorFlag");
			String hjleft=request.getParameter("hjleft");
			String stateZdzf = request.getParameter("stateZdzf");
			String infoRjsj=request.getParameter("infoRjsj");
			String infoZm=request.getParameter("infoZm");
			String infoXq=request.getParameter("infoXq");
			String infoCsrq=request.getParameter("infoCsrq");
			String infoHome=request.getParameter("infoHome");
			String frGj=request.getParameter("frGj");
			String state=request.getParameter("state");
			String outTime=request.getParameter("outTime");
			String hjStopTime=request.getParameter("hjStopTime");
			String hjStopSM=request.getParameter("hjStopSM");
			String zdzfType = java.net.URLDecoder.decode((String)request.getParameter("zdzfType"),"UTF-8");
			if(!frCard.trim().equals("")){
				String hql3="from JlFr  where webId!="+frId+" and frCard='"+frCard+"'";
				List<JlFr> list3=mm.searchAll(hql3);
				if(list3.size()>0){
					JSONArray json=JSONArray.fromObject(list3);
					response.getWriter().println(json.toString());
				}else{
					StringBuffer str=new StringBuffer("update JlFr jl set ");
					Calendar c = Calendar.getInstance();
					SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
					c  =  Calendar.getInstance(Locale.CHINESE);   
					String loginTime = simpleDateTimeFormat.format(c.getTime());
					if(frName!="" && frName!=null){
						str.append("jl.frName='"+frName+"',");
					}else{
						str.append("jl.frName=null,");
					}
					if(frCard!="" && frCard!=null){
						str.append("jl.frCard='"+frCard+"',");
					}else{
						str.append("jl.frCard=null,");
					}
					if(infoRjsj!="" && infoRjsj!=null){
						str.append("jl.infoRjsj='"+infoRjsj+"',");
					}else{
						str.append("jl.infoRjsj=null,");
					}
					if(infoZm!="" && infoZm!=null){
						str.append("jl.infoZm='"+infoZm+"',");
					}else{
						str.append("jl.infoZm=null,");
					}
					if(infoXq!="" && infoXq!=null){
						str.append("jl.infoXq='"+infoXq+"',");
					}else{
						str.append("jl.infoXq=null,");
					}
					if(infoCsrq!="" && infoCsrq!=null){
						str.append("jl.infoCsrq='"+infoCsrq+"',");
					}else{
						str.append("jl.infoCsrq=null,");
					}
					if(infoHome!="" && infoHome!=null){
						str.append("jl.infoHome='"+infoHome+"',");
					}else{
						str.append("jl.infoHome=null,");
					}
					if(frGj!="" && frGj!=null){
						str.append("jl.frGj='"+frGj+"',");
					}else{
						str.append("jl.frGj=null,");
					}
					str.append("jl.jq='"+jq+"',");
					str.append("jl.jy='"+jy+"',");
					str.append("jl.jbNo='"+jbNo+"',");
					JlFr jlFr=(JlFr)mm.findById(JlFr.class, Integer.parseInt(frId));
					StringBuffer str2=new StringBuffer("");
					if((frName!=null && !jlFr.getFrName().equals(frName)) || !jlFr.getJq().equals(jq) || !jlFr.getJbNo().equals(jbNo)){
						str2.append("罪犯编号为"+jlFr.getFrNo()+"的罪犯");
						if(frName!=null && !jlFr.getFrName().equals(frName)){
							str2.append(" 姓名由"+jlFr.getFrName()+"修改为"+frName);
						}
						if(!jlFr.getJq().equals(jq)){
							str2.append(" 监区编号由"+jlFr.getJq()+"修改为"+jq);
						}	
						if(!jlFr.getJbNo().equals(jbNo)){
							str2.append(" 级别编号由"+jlFr.getJbNo()+"修改为"+jbNo);
							String hql="from JlJb where jbNo='"+jbNo+"'";
						 	List<JlJb> list2=mm.searchAll(hql);
						 	if(list2.size()>0){
						 		JlJb jlJb2=(JlJb)list2.get(0);
						 		if(jlJb2.getAutoDown()==1){
						 			str.append("jl.jbSetTime="+Integer.parseInt(loginTime.substring(0,4)+loginTime.substring(5, 7))+",");
						 			str.append("jl.jbSetType="+0+",");
						 		}else{
						 			str.append("jl.jbSetTime=null,");
						 			str.append("jl.jbSetType=null,");
						 		}
						 	}
						}
						SysLog sl=new SysLog();
						sl.setType("正常");
						sl.setLogTime(loginTime);
						sl.setUserName(user.getUserName());
						sl.setUserNo(user.getUserNo());
						sl.setUserIp(request.getRemoteAddr());
						sl.setModel("罪犯管理");
						sl.setInfo(str2.toString());
						sl.setOp("修改罪犯信息");
						mm.save(sl);
					}
					if(!outTime.trim().equals("")){
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						java.util.Date date11 = df1.parse(outTime+" 00:00:00");
						String time = df1.format(date11);
						Timestamp ts = Timestamp.valueOf(time);
						str.append("jl.outTime='"+outTime+"',");
					}else{
						str.append("jl.outTime=null,");
					}
				 	if(Integer.parseInt(state)!=jlFr.getState()){
				 		Calendar c1 = Calendar.getInstance();
					 	StringBuffer str22=new StringBuffer("");
					 	SimpleDateFormat simpleDateTimeFormat1  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");   
					 	c  =  Calendar.getInstance(Locale.CHINESE);   
					 	String loginTime1 = simpleDateTimeFormat.format(c1.getTime());
				 		SysLog sl=new SysLog();
					 	sl.setType("正常");
					 	sl.setLogTime(loginTime1);
					 	sl.setUserName(user.getUserName());
					 	sl.setUserNo(user.getUserNo());
					 	sl.setUserIp(request.getRemoteAddr());
					 	sl.setModel("罪犯管理");
					 	if(Integer.parseInt(state)==1){
					 		sl.setInfo(jlFr.getFrName()+"（编号："+jlFr.getFrNo()+"）于"+outTime+"出狱");
					 	}else{
					 		sl.setInfo(jlFr.getFrName()+"（编号："+jlFr.getFrNo()+"）由出狱状态修改为服刑状态");
					 	}
					 	sl.setOp("修改服刑状态");
					 	mm.save(sl);
					 	
				 	}
					str.append("jl.state="+state+",");
					str.append("jl.hjLeft="+Integer.parseInt(hjleft)+",");
					str.append("jl.hjJb="+hjjb+",");
					str.append("jl.monitorFlag='"+monitorFlag+"',");
					str.append("jl.stateZdzf="+stateZdzf+",");
					if(zdzfType!="" && zdzfType!=null){
						str.append("jl.zdzfType='"+zdzfType+"',");
					}else{
						str.append("jl.zdzfType=null,");
					}
					if(hjStopTime.equals("")){
						str.append("jl.hjStopTime=NULL,");
					}else{
						str.append("jl.hjStopTime='"+hjStopTime+"',");
					}
					str.append("jl.hjStopSM='"+hjStopSM+"'");
					str.append(" where jl.webId=?");
					Object[] obj={Integer.parseInt(frId)};
					mm.updates(str.toString(),obj);
					JSONArray json=JSONArray.fromObject(null);
					response.getWriter().println(json.toString());
				}
			}else{
				StringBuffer str=new StringBuffer("update JlFr jl set ");
				Calendar c = Calendar.getInstance();
				SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
				c  =  Calendar.getInstance(Locale.CHINESE);   
				String loginTime = simpleDateTimeFormat.format(c.getTime());
				if(frName!="" && frName!=null){
					str.append("jl.frName='"+frName+"',");
				}else{
					str.append("jl.frName=null,");
				}
				if(frCard!="" && frCard!=null){
					str.append("jl.frCard='"+frCard+"',");
				}else{
					str.append("jl.frCard=null,");
				}
				if(infoRjsj!="" && infoRjsj!=null){
					str.append("jl.infoRjsj='"+infoRjsj+"',");
				}else{
					str.append("jl.infoRjsj=null,");
				}
				if(infoZm!="" && infoZm!=null){
					str.append("jl.infoZm='"+infoZm+"',");
				}else{
					str.append("jl.infoZm=null,");
				}
				if(infoXq!="" && infoXq!=null){
					str.append("jl.infoXq='"+infoXq+"',");
				}else{
					str.append("jl.infoXq=null,");
				}
				if(infoCsrq!="" && infoCsrq!=null){
					str.append("jl.infoCsrq='"+infoCsrq+"',");
				}else{
					str.append("jl.infoCsrq=null,");
				}
				if(infoHome!="" && infoHome!=null){
					str.append("jl.infoHome='"+infoHome+"',");
				}else{
					str.append("jl.infoHome=null,");
				}
				if(frGj!="" && frGj!=null){
					str.append("jl.frGj='"+frGj+"',");
				}else{
					str.append("jl.frGj=null,");
				}
				str.append("jl.jq='"+jq+"',");
				str.append("jl.jy='"+jy+"',");
				str.append("jl.jbNo='"+jbNo+"',");
				JlFr jlFr=(JlFr)mm.findById(JlFr.class, Integer.parseInt(frId));
				StringBuffer str2=new StringBuffer("");
				if((frName!=null && !jlFr.getFrName().equals(frName)) || !jlFr.getJq().equals(jq) || !jlFr.getJbNo().equals(jbNo)){
					str2.append("罪犯编号为"+jlFr.getFrNo()+"的罪犯");
					if(frName!=null && !jlFr.getFrName().equals(frName)){
						str2.append(" 姓名由"+jlFr.getFrName()+"修改为"+frName);
					}
					if(!jlFr.getJq().equals(jq)){
						str2.append(" 监区编号由"+jlFr.getJq()+"修改为"+jq);
					}	
					if(!jlFr.getJbNo().equals(jbNo)){
						str2.append(" 级别编号由"+jlFr.getJbNo()+"修改为"+jbNo);
						String hql="from JlJb where jbNo='"+jbNo+"'";
					 	List<JlJb> list2=mm.searchAll(hql);
					 	if(list2.size()>0){
					 		JlJb jlJb2=(JlJb)list2.get(0);
					 		if(jlJb2.getAutoDown()==1){
					 			str.append("jl.jbSetTime="+Integer.parseInt(loginTime.substring(0,4)+loginTime.substring(5, 7))+",");
					 			str.append("jl.jbSetType="+0+",");
					 		}else{
					 			str.append("jl.jbSetTime=null,");
					 			str.append("jl.jbSetType=null,");
					 		}
					 	}
					}
					SysLog sl=new SysLog();
					sl.setType("正常");
					sl.setLogTime(loginTime);
					sl.setUserName(user.getUserName());
					sl.setUserNo(user.getUserNo());
					sl.setUserIp(request.getRemoteAddr());
					sl.setModel("罪犯管理");
					sl.setInfo(str2.toString());
					sl.setOp("修改罪犯信息");
					mm.save(sl);
				}
				if(!outTime.trim().equals("")){
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date date11 = df1.parse(outTime+" 00:00:00");
					String time = df1.format(date11);
					Timestamp ts = Timestamp.valueOf(time);
					str.append("jl.outTime='"+outTime+"',");
				}else{
					str.append("jl.outTime=null,");
				}
			 	if(Integer.parseInt(state)!=jlFr.getState()){
			 		Calendar c1 = Calendar.getInstance();
				 	StringBuffer str22=new StringBuffer("");
				 	SimpleDateFormat simpleDateTimeFormat1  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");   
				 	c  =  Calendar.getInstance(Locale.CHINESE);   
				 	String loginTime1 = simpleDateTimeFormat.format(c1.getTime());
			 		SysLog sl=new SysLog();
				 	sl.setType("正常");
				 	sl.setLogTime(loginTime1);
				 	sl.setUserName(user.getUserName());
				 	sl.setUserNo(user.getUserNo());
				 	sl.setUserIp(request.getRemoteAddr());
				 	sl.setModel("罪犯管理");
				 	if(Integer.parseInt(state)==1){
				 		sl.setInfo(jlFr.getFrName()+"（编号："+jlFr.getFrNo()+"）于"+outTime+"出狱");
				 	}else{
				 		sl.setInfo(jlFr.getFrName()+"（编号："+jlFr.getFrNo()+"）由出狱状态修改为服刑状态");
				 	}
				 	sl.setOp("修改服刑状态");
				 	mm.save(sl);
				 	
			 	}
				str.append("jl.state="+state+",");
				str.append("jl.hjLeft="+Integer.parseInt(hjleft)+",");
				str.append("jl.hjJb="+hjjb+",");
				str.append("jl.monitorFlag='"+monitorFlag+"',");
				str.append("jl.stateZdzf="+stateZdzf+",");
				if(zdzfType!="" && zdzfType!=null){
					str.append("jl.zdzfType='"+zdzfType+"',");
				}else{
					str.append("jl.zdzfType=null,");
				}
				if(hjStopTime.equals("")){
					str.append("jl.hjStopTime=NULL,");
				}else{
					str.append("jl.hjStopTime='"+hjStopTime+"',");
				}
				str.append("jl.hjStopSM='"+hjStopSM+"'");
				str.append(" where jl.webId=?");
				Object[] obj={Integer.parseInt(frId)};
				mm.updates(str.toString(),obj);
				JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
			}
			
			return  null;
	}
	//查询亲属
	public ActionForward checkQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String frNo=request.getParameter("frNo");
			List<FrQs> frQsList=new ArrayList();
			StringBuffer str=new StringBuffer("select jl.webId,jl.frNo,jl.qsSfz,jl.qsName,jl.qsCard,jl.gx,jl.xb,jl.dz,jl.tele,jl.sw,jlFr.frName,jl.qsZjlb,jl.qsSfzWlh,jl.bz,jl.spState,jl.hjStopTime,jl.spUserNo,jl.spTime from JlFr jlFr,JlQs jl where jlFr.frNo=jl.frNo and jlFr.frNo='");
			str.append(frNo+"'");
			List<JlQs> list=mm.searchAll(str.toString());
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
					frQs.setQsZjlb(Integer.parseInt(obj[11].toString()));
				}
				if(obj[12]!=null && obj[12].toString().trim()!=""){
					frQs.setQsSfzWlh(obj[12].toString());
				}
				if(obj[13]!=null && obj[13].toString().trim()!=""){
					frQs.setBz(obj[13].toString());
				}
				if(obj[14]!=null && obj[14].toString().trim()!=""){
					frQs.setSpState(obj[14].toString());
				}
				if(obj[15]!=null && obj[15].toString().trim()!=""){
					frQs.setHjStopTime(obj[15].toString());
				}
				if(obj[16]!=null && obj[16].toString().trim()!=""){
					frQs.setSpUserNo(obj[16].toString());
				}
				if(obj[17]!=null && obj[17].toString().trim()!=""){
					frQs.setSpTime(obj[17].toString().substring(0, 10));
				}
				frQsList.add(frQs);
			}
			request.setAttribute("qsList",frQsList);
			request.setAttribute("frNo",frNo);
			String hql4="from SysParam where paramName='HJ_Client1'";
			List<SysParam> list4=mm.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("sysParam", sysParam1);
			}
			
			String frNo3 = java.net.URLDecoder.decode((String)request.getParameter("frNo3"),"UTF-8");
			String jq2 = java.net.URLDecoder.decode((String)request.getParameter("jq2"),"UTF-8");
			String jy2 = java.net.URLDecoder.decode((String)request.getParameter("jy2"),"UTF-8");
			String jbNo2 = java.net.URLDecoder.decode((String)request.getParameter("jbNo2"),"UTF-8");
			String frName2 = java.net.URLDecoder.decode((String)request.getParameter("frName2"),"UTF-8");			
			String pageNo2=request.getParameter("pageNo2");
			String stateZdzf2 = java.net.URLDecoder.decode((String)request.getParameter("stateZdzf2"),"UTF-8");
			String state2 = java.net.URLDecoder.decode((String)request.getParameter("state2"),"UTF-8");
			String frCard2 = java.net.URLDecoder.decode((String)request.getParameter("frCard2"),"UTF-8");
			request.setAttribute("frCard2", frCard2);
			request.setAttribute("state2", state2);
			request.setAttribute("stateZdzf2", stateZdzf2);
			request.setAttribute("frNo3", frNo3);
			request.setAttribute("pageNo2", pageNo2);
			request.setAttribute("jq2", jq2);
			request.setAttribute("jy2", jy2);
			request.setAttribute("jbNo2", jbNo2);
			request.setAttribute("frName2", frName2);
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
			StringBuffer str=new StringBuffer("from JlFr jl where jl.frNo='");
			str.append(frNo+"'");
			List<JlFr> list=mm.searchAll(str.toString());
			JlFr jlFr=(JlFr)list.get(0);
			List<Integer> qjswList=new ArrayList();
			for(int i=1;i<=9;i++){
				qjswList.add(i);
			}
			List<Integer> unswList=new ArrayList();
			unswList=qjswList;
			StringBuffer str1=new StringBuffer(" from JlQs jl where jl.frNo='");
			str1.append(frNo+"'");
			List<JlQs> swlist=mm.searchAll(str1.toString());
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
			List<SysParam> list3=mm.searchAll(hql3);
			SysParam sysParam=(SysParam)list3.get(0);
			String qsSfz="";
			if(sysParam.getParamData5().equals("1")){
				qsSfz=System.currentTimeMillis()+"";
			}
			List<String> qsList=new ArrayList();
			String sqlGx="select QS_GX from JL_QS_GX";
			List<String> qsList1 =mm.searchAllBySql(sqlGx);
			for(int i=0;i<qsList1.size();i++){
				qsList.add(qsList1.get(i));
			}
			request.setAttribute("qsGxList",qsList);
			request.setAttribute("qsSfz", qsSfz);
			request.setAttribute("unswList",unswList);
			request.setAttribute("jlFr", jlFr);
			String hql4="from SysParam where paramName='HJ_Client'";
			List<SysParam> list4=mm.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("sysParam", sysParam1);
			}
			String hql5="from SysUser where userNo='"+user.getUserNo()+"'";
			List<SysUser> list5=mm.searchAll(hql5);
			if(list5.size()>0){
				SysUser sysUser=(SysUser)list5.get(0);
				request.setAttribute("sysUser", sysUser);
			}
			
			
			String frNo3 = java.net.URLDecoder.decode((String)request.getParameter("frNo3"),"UTF-8");
			String jq2 = java.net.URLDecoder.decode((String)request.getParameter("jq2"),"UTF-8");
			String jy2 = java.net.URLDecoder.decode((String)request.getParameter("jy2"),"UTF-8");
			String jbNo2 = java.net.URLDecoder.decode((String)request.getParameter("jbNo2"),"UTF-8");
			String frName2 = java.net.URLDecoder.decode((String)request.getParameter("frName2"),"UTF-8");			
			String pageNo2=request.getParameter("pageNo2");
			String stateZdzf2 = java.net.URLDecoder.decode((String)request.getParameter("stateZdzf2"),"UTF-8");
			String state2 = java.net.URLDecoder.decode((String)request.getParameter("state2"),"UTF-8");
			String frCard2 = java.net.URLDecoder.decode((String)request.getParameter("frCard2"),"UTF-8");
			request.setAttribute("frCard2", frCard2);
			request.setAttribute("state2", state2);
			request.setAttribute("stateZdzf2", stateZdzf2);
			request.setAttribute("frNo3", frNo3);
			request.setAttribute("pageNo2", pageNo2);
			request.setAttribute("jq2", jq2);
			request.setAttribute("jy2", jy2);
			request.setAttribute("jbNo2", jbNo2);
			request.setAttribute("frName2", frName2);
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
		 	String qsSfz = java.net.URLDecoder.decode((String)request.getParameter("qsSfz"),"UTF-8");
		 	String qsName = java.net.URLDecoder.decode((String)request.getParameter("qsName"),"UTF-8");
		 	String qsCard = java.net.URLDecoder.decode((String)request.getParameter("qsCard"),"UTF-8");
		 	String dz = java.net.URLDecoder.decode((String)request.getParameter("dz"),"UTF-8");
		 	String tele = java.net.URLDecoder.decode((String)request.getParameter("tele"),"UTF-8");
		 	String gx = java.net.URLDecoder.decode((String)request.getParameter("gx"),"UTF-8");
		 	String xb = java.net.URLDecoder.decode((String)request.getParameter("xb"),"UTF-8");
		 	String zjlb = java.net.URLDecoder.decode((String)request.getParameter("zjlb"),"UTF-8");
		 	String spState = java.net.URLDecoder.decode((String)request.getParameter("spState"),"UTF-8");
		 	String photoAddress=request.getParameter("photoAddress");
		 	String jz=request.getParameter("jz");
		 	String qsSfzWlh = java.net.URLDecoder.decode((String)request.getParameter("qsSfzWlh"),"UTF-8");
		 	String bz = java.net.URLDecoder.decode((String)request.getParameter("bz"),"UTF-8");
		 	if(!qsSfz.trim().equals("")){
		 		String hql="from JlQs where frNo='"+frNo+"' and qsSfz='"+qsSfz.trim()+"'";
			 	List list=mm.searchAll(hql);
		 		if(list.size()>0){
		 			JSONArray json=JSONArray.fromObject(1);
		 			response.getWriter().println(json.toString());
			 	}else{
			 		if(!qsCard.trim().equals("")){
			 			String hql5="update JlQs set qsCard='' where qsCard='"+qsCard+"'";
			 			Object[] obj5={};
			 			mm.updates(hql5, obj5);
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
			 		jlQs.setGx(gx);
			 		jlQs.setXb(xb);
			 	    if(!zjlb.equals("null")){
			 	    	jlQs.setQsZjlb(Integer.parseInt(zjlb));
			 	    }
			 	    if(!qsSfzWlh.trim().equals("")){
			 			jlQs.setQsSfzWlh(qsSfzWlh);
			 	    }
			 		if(!bz.trim().equals("")){
			 			jlQs.setBz(bz);
			 	    }
			 	    if(jz.length()>2){
			 		   sun.misc.BASE64Decoder decode=new sun.misc.BASE64Decoder();
			 		   byte[] datas=decode.decodeBuffer(jz.substring(1, jz.length()-2));
			 		   jlQs.setJz(datas);
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
			 		
			 	    jlQs.setCreateTime(now1);
			 	    
			 	    String hql8="from SysUser where userNo='"+user.getUserNo()+"'";
					List<SysUser> list8=mm.searchAll(hql8);
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
						List<SysParam> list5=mm.searchAll(hql5);
						SysParam sysParam1=(SysParam)list5.get(0);						
						zpUrl1=sysParam1.getParamData3()+qsSfz+".Jpg";
						jlQs.setZpUrl(zpUrl1);
				 	}
			 	    mm.save(jlQs);
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
			 	    mm.save(sl);
			 	    JSONArray json=JSONArray.fromObject(0);
			 	    response.getWriter().println(json.toString());
			 	}
		 	}else{
		 		JlQs jlQs=new JlQs();
		 		jlQs.setFrNo(frNo);
		 		jlQs.setQsSfz(qsSfz);
		 		if(!qsName.trim().equals("")){
		 			jlQs.setQsName(qsName);
		 	    }
		 		if(!qsCard.trim().equals("")){
		 			String hql5="update JlQs set qsCard='' where qsCard='"+qsCard+"'";
		 			Object[] obj5={};
		 			mm.updates(hql5, obj5);
		 			jlQs.setQsCard(qsCard);
		 		}
		 		if(!dz.trim().equals("")){
		 			jlQs.setDz(dz);
		 	    }
		 		if(!tele.trim().equals("")){
		 			jlQs.setTele(tele);
		 		}
		 		jlQs.setGx(gx);
		 		jlQs.setXb(xb);
		 	    if(!zjlb.equals("null")){
		 	    	jlQs.setQsZjlb(Integer.parseInt(zjlb));
		 	    }
		 	    if(!qsSfzWlh.trim().equals("")){
		 			jlQs.setQsSfzWlh(qsSfzWlh);
		 	    }
		 		if(!bz.trim().equals("")){
		 			jlQs.setBz(bz);
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
				List<SysUser> list8=mm.searchAll(hql8);
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
		 	    mm.save(jlQs);
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
		 	    mm.save(sl);
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
		    JlQs jlQs=(JlQs)mm.findById(JlQs.class, Integer.parseInt(webId));
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
		    mm.save(sl);
		    mm.deleteByHql(hql, obj);
		    request.setAttribute("frNo", frNo);
		    String frNo3 = java.net.URLDecoder.decode((String)request.getParameter("frNo3"),"UTF-8");
			String jq2 = java.net.URLDecoder.decode((String)request.getParameter("jq2"),"UTF-8");
			String jy2 = java.net.URLDecoder.decode((String)request.getParameter("jy2"),"UTF-8");
			String jbNo2 = java.net.URLDecoder.decode((String)request.getParameter("jbNo2"),"UTF-8");
			String frName2 = java.net.URLDecoder.decode((String)request.getParameter("frName2"),"UTF-8");
			String pageNo2=request.getParameter("pageNo2");
			String stateZdzf2 = java.net.URLDecoder.decode((String)request.getParameter("stateZdzf2"),"UTF-8");
			String state2 = java.net.URLDecoder.decode((String)request.getParameter("state2"),"UTF-8");
			String frCard2 = java.net.URLDecoder.decode((String)request.getParameter("frCard2"),"UTF-8");
			request.setAttribute("frCard2", frCard2);
			request.setAttribute("state2", state2);
			request.setAttribute("stateZdzf2", stateZdzf2);
			request.setAttribute("frNo3", frNo3);
			request.setAttribute("pageNo2", pageNo2);
			request.setAttribute("jq2", jq2);
			request.setAttribute("jy2", jy2);
			request.setAttribute("jbNo2", jbNo2);
			request.setAttribute("frName2", frName2);
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
			List<JlFr> list=mm.searchAll(str.toString());
			JlFr jlFr=(JlFr)list.get(0);
			String hql="from JlQs jl where webId=?";
			Object[] obj={Integer.parseInt(webId)};
			List<JlQs> list1=mm.searchAll(hql, obj);
			JlQs jlQs=(JlQs)list1.get(0);
			List<Integer> qjswList=new ArrayList();
			for(int i=1;i<=9;i++){
				qjswList.add(i);
			}
			List<Integer> unswList=new ArrayList();
			unswList=qjswList;
			StringBuffer str1=new StringBuffer(" from JlQs jl where jl.frNo='");
			str1.append(frNo+"'");
			List<JlQs> swlist=mm.searchAll(str1.toString());
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
			List<String> qsList1 =mm.searchAllBySql(sqlGx);
			for(int i=0;i<qsList1.size();i++){
				qsList.add(qsList1.get(i));
			}
			List<String> newQsList = new ArrayList<String>();
			String newQsGx = "select QS_GX from JL_QS_GX where  QS_GX='"+jlQs.getGx()+"'";
			List<String> newQsList1 = mm.searchAllBySql(newQsGx);
			if(newQsList1.size()==0){
				newQsList.add(jlQs.getGx());
			}
			
			request.setAttribute("qsGxList",qsList);
			request.setAttribute("newQsList",newQsList);
			request.setAttribute("unswList",unswList);
			request.setAttribute("jlFr", jlFr);
			request.setAttribute("jlQs", jlQs);
			String hql4="from SysParam where paramName='HJ_Client'";
			List<SysParam> list4=mm.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("sysParam", sysParam1);
			}
			String hql5="from SysUser where userNo='"+user.getUserNo()+"'";
			List<SysUser> list5=mm.searchAll(hql5);
			if(list5.size()>0){
				SysUser sysUser=(SysUser)list5.get(0);
				request.setAttribute("sysUser", sysUser);
			}
			
			String frNo3 = java.net.URLDecoder.decode((String)request.getParameter("frNo3"),"UTF-8");
			String jq2 = java.net.URLDecoder.decode((String)request.getParameter("jq2"),"UTF-8");
			String jy2 = java.net.URLDecoder.decode((String)request.getParameter("jy2"),"UTF-8");
			String jbNo2 = java.net.URLDecoder.decode((String)request.getParameter("jbNo2"),"UTF-8");
			String frName2 = java.net.URLDecoder.decode((String)request.getParameter("frName2"),"UTF-8");
			String pageNo2=request.getParameter("pageNo2");
			String stateZdzf2 = java.net.URLDecoder.decode((String)request.getParameter("stateZdzf2"),"UTF-8");
			String state2 = java.net.URLDecoder.decode((String)request.getParameter("state2"),"UTF-8");
			String frCard2 = java.net.URLDecoder.decode((String)request.getParameter("frCard2"),"UTF-8");
			request.setAttribute("frCard2", frCard2);
			request.setAttribute("state2", state2);
			request.setAttribute("stateZdzf2", stateZdzf2);
			request.setAttribute("frNo3", frNo3);
			request.setAttribute("pageNo2", pageNo2);
			request.setAttribute("jq2", jq2);
			request.setAttribute("jy2", jy2);
			request.setAttribute("jbNo2", jbNo2);
			request.setAttribute("frName2", frName2);
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
			JlQs jlQs=(JlQs)mm.findById(JlQs.class, Integer.parseInt(qsId));
			String qsName = java.net.URLDecoder.decode((String)request.getParameter("qsName"),"UTF-8");
		 	String qsCard = java.net.URLDecoder.decode((String)request.getParameter("qsCard"),"UTF-8");
		 	String dz = java.net.URLDecoder.decode((String)request.getParameter("dz"),"UTF-8");
		 	String tele = java.net.URLDecoder.decode((String)request.getParameter("tele"),"UTF-8");
		 	String gx = java.net.URLDecoder.decode((String)request.getParameter("gx"),"UTF-8");
		 	String xb = java.net.URLDecoder.decode((String)request.getParameter("xb"),"UTF-8");
		 	String zjlb = request.getParameter("zjlb");
		 	String qsSfz = request.getParameter("qsSfz");
		 	String photoAddress = java.net.URLDecoder.decode((String)request.getParameter("photoAddress"),"UTF-8");
		 	String jz = request.getParameter("jz");
		 	String spState = request.getParameter("spState");
		 	String hjStopTime=request.getParameter("hjStopTime");
		 	//String qsCard2 ="";
		 	String qsSfzWlh = java.net.URLDecoder.decode((String)request.getParameter("qsSfzWlh"),"UTF-8");
			String bz = java.net.URLDecoder.decode((String)request.getParameter("bz"),"UTF-8");
		 	if(jlQs.getQsSfz()!=null && !jlQs.getQsSfz().equals(qsSfz.trim())){
		 		String hql="from JlQs jl where jl.qsSfz='"+qsSfz.trim()+"'"+" and jl.frNo='"+jlQs.getFrNo()+"'";
		 		List list=mm.searchAll(hql);
		 		if(list.size()>0){
				 	JSONArray json=JSONArray.fromObject(1);
				 	response.getWriter().println(json.toString());
		 		}else{
		 			if(!qsCard.trim().equals("")){
		 				//int qsCard1 = Integer.parseInt(qsCard);
					 	//int qsCard2=(int) ((qsCard1%(256*256))+(Math.floor(qsCard1/(256*256))%256)*100000); ID卡前10位转后8位
					 	//qsCard2= Double.toString(Math.floor(qsCard1/100000)*256*256+(qsCard1-Math.floor(qsCard1/100000)*100000)); //ID卡后8位转前10位
					 	String hql5="update JlQs set qsCard='' where qsCard='"+qsCard+"'";
			 			Object[] obj5={};
			 			mm.updates(hql5, obj5);
			 		}
		 			jlQs.setQsSfz(qsSfz.trim());
		 			if(!qsName.equals("")){
				 		jlQs.setQsName(qsName.trim());
				 	}else{
				 		jlQs.setQsName("");
				 	}
				 	if(!qsCard.equals("")){
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
				 	if(hjStopTime.equals("")){
				 		jlQs.setHjStopTime(null);
					}else{
						Timestamp ts = new Timestamp(System.currentTimeMillis());      
				        try {   
				            ts = Timestamp.valueOf(hjStopTime);
				            jlQs.setHjStopTime(ts);
				        } catch (Exception e) {   
				            e.printStackTrace();   
				        } 
						
					}
				 	String hql8="from SysUser where userNo='"+user.getUserNo()+"'";
					List<SysUser> list8=mm.searchAll(hql8);
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
	//				 	String savePath=request.getRealPath("/")+"images/";
	//					String filename=String.valueOf(System.currentTimeMillis())+".jpg";
	//					File file=new File(savePath+filename);
	//					OutputStream fos=new FileOutputStream(file);
	//					System.out.println("图片文件名称:"+filename);
	//					fos.write(datas);
	//					fos.close();
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
					    mm.save(sl);
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
						List<SysParam> list5=mm.searchAll(hql5);
						SysParam sysParam1=(SysParam)list5.get(0);						
						zpUrl1=sysParam1.getParamData3()+qsSfz+".Jpg";
						jlQs.setZpUrl(zpUrl1);
				 	}
				 	mm.update(jlQs);
				 	JSONArray json=JSONArray.fromObject(0);
				 	response.getWriter().println(json.toString());
		 		}
		 	}else{
		 		if(jlQs.getQsSfz()==null){
		 			String hql="from JlQs jl where jl.qsSfz='"+qsSfz.trim()+"'"+" and jl.frNo='"+jlQs.getFrNo()+"'";
			 		List list=mm.searchAll(hql);
			 		if(list.size()>0){
					 	JSONArray json=JSONArray.fromObject(1);
					 	response.getWriter().println(json.toString());
			 		}else{
			 			if(!qsCard.trim().equals("")){
				 			String hql5="update JlQs set qsCard='' where qsCard='"+qsCard+"'";
				 			Object[] obj5={};
				 			mm.updates(hql5, obj5);
				 		}
			 			jlQs.setQsSfz(qsSfz.trim());
			 			if(!qsName.equals("")){
					 		jlQs.setQsName(qsName.trim());
					 	}else{
					 		jlQs.setQsName("");
					 	}
					 	if(!qsCard.equals("")){
					 		
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
					 	}else{
					 		jlQs.setQsZjlb(null);
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
					 	if(hjStopTime.equals("")){
					 		jlQs.setHjStopTime(null);
						}else{
							Timestamp ts = new Timestamp(System.currentTimeMillis());      
					        try {   
					            ts = Timestamp.valueOf(hjStopTime);
					            jlQs.setHjStopTime(ts);
					        } catch (Exception e) {   
					            e.printStackTrace();   
					        } 
							
						}
					 	String hql8="from SysUser where userNo='"+user.getUserNo()+"'";
						List<SysUser> list8=mm.searchAll(hql8);
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
		//				 	String savePath=request.getRealPath("/")+"images/";
		//					String filename=String.valueOf(System.currentTimeMillis())+".jpg";
		//					File file=new File(savePath+filename);
		//					OutputStream fos=new FileOutputStream(file);
		//					System.out.println("图片文件名称:"+filename);
		//					fos.write(datas);
		//					fos.close();
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
						    mm.save(sl);
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
							List<SysParam> list5=mm.searchAll(hql5);
							SysParam sysParam1=(SysParam)list5.get(0);						
							zpUrl1=sysParam1.getParamData3()+qsSfz+".Jpg";
							jlQs.setZpUrl(zpUrl1);
					 	}
					 	mm.update(jlQs);
					 	JSONArray json=JSONArray.fromObject(0);
					 	response.getWriter().println(json.toString());
			 		}
		 		}else{
		 			if(!qsCard.trim().equals("")){
			 			String hql5="update JlQs set qsCard='' where qsCard='"+qsCard+"'";
			 			Object[] obj5={};
			 			mm.updates(hql5, obj5);
			 		}
		 			if(!qsName.equals("")){
				 		jlQs.setQsName(qsName.trim());
				 	}else{
				 		jlQs.setQsName("");
				 	}
				 	if(!qsCard.equals("")){
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
				 	if(hjStopTime.equals("")){
				 		jlQs.setHjStopTime(null);
					}else{
						Timestamp ts = new Timestamp(System.currentTimeMillis());      
				        try {   
				            ts = Timestamp.valueOf(hjStopTime);
				            jlQs.setHjStopTime(ts);
				        } catch (Exception e) {   
				            e.printStackTrace();   
				        } 
						
					}
				 	String hql8="from SysUser where userNo='"+user.getUserNo()+"'";
					List<SysUser> list8=mm.searchAll(hql8);
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
		//			 	String savePath=request.getRealPath("/")+"images/";
		//				String filename=String.valueOf(System.currentTimeMillis())+".jpg";
		//				File file=new File(savePath+filename);
		//				OutputStream fos=new FileOutputStream(file);
		//				System.out.println("图片文件名称:"+filename);
		//				fos.write(datas);
		//				fos.close();
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
					    mm.save(sl);
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
						List<SysParam> list5=mm.searchAll(hql5);
						SysParam sysParam1=(SysParam)list5.get(0);						
						zpUrl1=sysParam1.getParamData3()+qsSfz+".Jpg";
						jlQs.setZpUrl(zpUrl1);
				 	}
				 	mm.update(jlQs);
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
			int i=mm.getLineNo("import_qs", frNo);
			if(i==0 || i==-1){
				returnName="updateDownError";
			}
			String frNo3 = request.getParameter("frNo3");
			String jq2 = request.getParameter("jq2");
			String jy2 = request.getParameter("jy2");
			String jbNo2 = request.getParameter("jbNo2");
			String frName2 = request.getParameter("frName2");
			String pageNo2=request.getParameter("pageNo2");
			String stateZdzf2 = request.getParameter("stateZdzf2");
			String state2 = request.getParameter("state2");
			String frCard2 = request.getParameter("frCard2");
			request.setAttribute("frCard2", frCard2);
			request.setAttribute("state2", state2);
			request.setAttribute("stateZdzf2", stateZdzf2);
			request.setAttribute("frNo3", frNo3);
			request.setAttribute("pageNo2", pageNo2);
			request.setAttribute("jq2", jq2);
			request.setAttribute("jy2", jy2);
			request.setAttribute("jbNo2", jbNo2);
			request.setAttribute("frName2", frName2);
			request.setAttribute("frNo", frNo);
			return mapping.findForward(returnName);
	}
	
	public ActionForward updateDownQs1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String frNo = request.getParameter("frNo");
			int i=mm.getLineNo("import_qs", frNo);
			if(i>0){
				JSONArray json=JSONArray.fromObject(i);
			 	response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(-1);
			 	response.getWriter().println(json.toString());
			}
			return null;
	}
	//导出所有犯人信息
	public ActionForward dcAllFrXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    Date date=new Date(System.currentTimeMillis());
		    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss"); 
		    String now=df1.format(date);
		    String filenameTemp = "D:\\\\"+now.substring(0,4)+now.substring(5,7)+now.substring(8,10)+"所有罪犯信息.xls"; 
		    File filename = new File(filenameTemp); 
		    if (!filename.exists()){
		    	filename.createNewFile(); 
		    } 
		    String isNoFlag=request.getParameter("isNoFlag");
			WritableWorkbook wwb6 = null;
			wwb6 = Workbook.createWorkbook(filename);
			WritableSheet ws6 = wwb6.createSheet("罪犯信息", 0);
			ws6.addCell(new Label(0, 0, "罪犯编号"));
			ws6.addCell(new Label(1, 0, "罪犯姓名"));
			ws6.addCell(new Label(2, 0, "监区编号"));
			ws6.addCell(new Label(3, 0, "分管等级编号"));
			ws6.addCell(new Label(4, 0, "国籍"));
			ws6.addCell(new Label(5, 0, "入监时间"));
			ws6.addCell(new Label(6, 0, "罪名"));
			ws6.addCell(new Label(7, 0, "刑期"));
			ws6.addCell(new Label(8, 0, "出生日期"));
			ws6.addCell(new Label(9, 0, "住址"));
			ws6.addCell(new Label(10, 0, "是否重点罪犯（0否1是）"));
			ws6.addCell(new Label(11, 0, "备注"));
			WritableSheet ws7 = wwb6.createSheet("亲属信息", 1);
			ws7.addCell(new Label(0, 0, "罪犯编号"));
			ws7.addCell(new Label(1, 0, "证件类型（1身份证2警官证3工作证4其他）"));			
			ws7.addCell(new Label(2, 0, "身份证号"));
			ws7.addCell(new Label(3, 0, "亲属姓名"));
			ws7.addCell(new Label(4, 0, "性别"));
			ws7.addCell(new Label(5, 0, "关系"));
			ws7.addCell(new Label(6, 0, "电话号码"));
			ws7.addCell(new Label(7, 0, "地址"));
			FrForm  frForm=(FrForm)form;
			if(frForm.getJy()!=null  || frForm.getJq()!=null  || frForm.getJbNo()!=null  || frForm.getFrNo()!=null || frForm.getFrName()!=null){
				StringBuffer str=new StringBuffer("");
				StringBuffer str1=new StringBuffer("");
				if(user.getIsSuper()==1){
					str.append("select jl.JY,jl.FR_No,jl.FR_Name,jq.JQ_Name,jl.JQ,jl.HJ_JB,jb.JB_Name,jl.JB_No,jl.Monitor_Flag,jl.FR_Card,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_XQ,jl.Info_ZM,jl.info_home,jl.fr_gj,jl.info_csrq,jl.State_ZDZF,jl.ZDZF_Type from JL_FR jl,JL_JB jb,JL_Jq jq,SYS_HJ_SERVER sys where jl.JB_No=jb.JB_No and jl.JQ=jq.JQ_No and jl.JY=sys.Server_Name");
					str1.append("select jl.FR_No,jl.FR_Name,qs.QS_Name,qs.QS_SFZ,qs.XB,qs.GX,qs.TELE,qs.DZ,qs.QS_ZJLB from JL_FR jl,JL_JB jb,JL_Jq jq,SYS_HJ_SERVER sys,JL_QS qs where jl.JB_No=jb.JB_No and jl.JQ=jq.JQ_No and jl.JY=sys.Server_Name and jl.FR_No=qs.FR_No");
				}else if(user.getGroupNo().equals("Admin")){
					str.append("select jl.JY,jl.FR_No,jl.FR_Name,jq.JQ_Name,jl.JQ,jl.HJ_JB,jb.JB_Name,jl.JB_No,jl.Monitor_Flag,jl.FR_Card,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_XQ,jl.Info_ZM,jl.info_home,jl.fr_gj,jl.info_csrq,jl.State_ZDZF,jl.ZDZF_Type from JL_FR jl,JL_JB jb,JL_Jq jq,SYS_HJ_SERVER sys where jl.JB_No=jb.JB_No and jl.JQ=jq.JQ_No and jq.Is_Ts!=1 and jl.JY=sys.Server_Name");
					str1.append("select jl.FR_No,jl.FR_Name,qs.QS_Name,qs.QS_SFZ,qs.XB,qs.GX,qs.TELE,qs.DZ,qs.QS_ZJLB from JL_FR jl,JL_JB jb,JL_Jq jq,SYS_HJ_SERVER sys,JL_QS qs where jl.JB_No=jb.JB_No and jl.JQ=jq.JQ_No and jq.Is_Ts!=1 and jl.JY=sys.Server_Name and jl.FR_No=qs.FR_No");
				}else{
					str.append("select jl.JY,jl.FR_No,jl.FR_Name,jq.JQ_Name,jl.JQ,jl.HJ_JB,jb.JB_Name,jl.JB_No,jl.Monitor_Flag,jl.FR_Card,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_XQ,jl.Info_ZM,jl.info_home,jl.fr_gj,jl.info_csrq,jl.State_ZDZF,jl.ZDZF_Type from JL_FR jl,JL_JB jb,JL_JQ jq,SYS_HJ_SERVER sys where jl.JB_No=jb.JB_No and jl.JQ=jq.JQ_No and jl.JY=sys.Server_Name");
					str1.append("select jl.FR_No,jl.FR_Name,qs.QS_Name,qs.QS_SFZ,qs.XB,qs.GX,qs.TELE,qs.DZ,qs.QS_ZJLB from JL_FR jl,JL_JB jb,JL_JQ jq,SYS_HJ_SERVER sys,JL_QS qs where jl.JB_No=jb.JB_No and jl.JQ=jq.JQ_No and jl.JY=sys.Server_Name and jl.FR_No=qs.FR_No");
					String sql5="from SysUserJq where groupNo='"+user.getGroupNo()+"'";
					List list5=mm.searchAll(sql5);
					if(list5.size()>0){
						str.append(" and (");
						str1.append(" and (");
						for(int i=0;i<list5.size();i++){
							SysUserJq sysUserJq=(SysUserJq)list5.get(i);
							if(i==0){
								str.append(" jq.jq_No='"+sysUserJq.getJqNo()+"'");
								str1.append(" jq.jq_No='"+sysUserJq.getJqNo()+"'");
							}else{
								str.append(" or jq.jq_No='"+sysUserJq.getJqNo()+"'");
								str1.append(" or jq.jq_No='"+sysUserJq.getJqNo()+"'");
							}
						}
						str.append(" ) ");
						str1.append(" ) ");
					}
					String sql6="select distinct JY from SYS_USER_JQ where Group_No='"+user.getGroupNo()+"'";
					List list6=mm.searchAllBySql(sql6);
					if(list6.size()>0){
						StringBuffer hql=new StringBuffer("from SysHjServer ");
						Iterator it=list6.iterator();
						int i=0;
						while(it.hasNext()){
							if(i==0){
								hql.append(" where serverName='"+it.next()+"' ");
								i++;
							}else{
								hql.append("or serverName='"+it.next()+"' ");
								i++;
							}
						}
						List<SysHjServer> list=mm.searchAll(hql.toString());
						request.setAttribute("sysQqServerList", list);
					}
				}
				if(frForm.getJy()!=null && !frForm.getJy().equals("null")){
					str.append(" and jl.JY='"+frForm.getJy()+"'");
					str1.append(" and jl.JY='"+frForm.getJy()+"'");
				}
				if(frForm.getJq()!=null && !frForm.getJq().equals("null")){
					str.append(" and jl.JQ='"+frForm.getJq()+"'");
					str1.append(" and jl.JQ='"+frForm.getJq()+"'");
				}
				if(frForm.getFrNo()!=null && !frForm.getFrNo().trim().equals("")){
					str.append(" and jl.FR_No='"+frForm.getFrNo()+"'");
					str1.append(" and jl.FR_No='"+frForm.getFrNo()+"'");
				}
				if(frForm.getFrName()!=null && !frForm.getFrName().trim().equals("")){
					str.append(" and jl.FR_Name like '%"+frForm.getFrName()+"%'");
					str1.append(" and jl.FR_Name like '%"+frForm.getFrName()+"%'");
				}
				if(frForm.getIsHjStop()!=null && !frForm.getIsHjStop().equals("null")){
					if(frForm.getIsHjStop().equals("0")){
						str.append(" and jl.HJ_JB=-1 ");
						str1.append(" and jl.HJ_JB=-1 ");
					}else{
						str.append(" and jl.HJ_JB<>-1 ");
						str1.append(" and jl.HJ_JB<>-1 ");
					}
					
				}
				List list=mm.searchAllBySql(str.toString());
				for(int i=0;i<list.size();i++){
					Object[] obj1=(Object[])list.get(i);
					ws6.addCell(new Label(0, i+1, obj1[1].toString()));
					if(obj1[2]!=null){
						ws6.addCell(new Label(1, i+1, obj1[2].toString()));
					}else{
						ws6.addCell(new Label(1, i+1, ""));
					}
					ws6.addCell(new Label(2, i+1, obj1[4].toString()));
					ws6.addCell(new Label(3, i+1, obj1[7].toString()));
					if(obj1[16]!=null){
						ws6.addCell(new Label(4, i+1, obj1[16].toString()));
					}else{
						ws6.addCell(new Label(4, i+1, ""));
					}
					if(obj1[10]!=null){
						ws6.addCell(new Label(5, i+1, obj1[10].toString()));
					}else{
						ws6.addCell(new Label(5, i+1, ""));
					}
					if(obj1[14]!=null){
						ws6.addCell(new Label(6, i+1, obj1[14].toString()));
					}else{
						ws6.addCell(new Label(6, i+1, ""));
					}
					if(obj1[13]!=null){
						ws6.addCell(new Label(7, i+1, obj1[13].toString()));
					}else{
						ws6.addCell(new Label(7, i+1, ""));
					}
					if(obj1[17]!=null){
						ws6.addCell(new Label(8, i+1, obj1[17].toString()));
					}else{
						ws6.addCell(new Label(8, i+1, ""));
					}
					if(obj1[15]!=null){
						ws6.addCell(new Label(9, i+1, obj1[15].toString()));
					}else{
						ws6.addCell(new Label(9, i+1, ""));
					}
					if(obj1[18]!=null){
						ws6.addCell(new Label(10, i+1, obj1[18].toString()));
					}else{
						ws6.addCell(new Label(10, i+1, ""));
					}
					if(obj1[19]!=null){
						ws6.addCell(new Label(11, i+1, obj1[19].toString()));
					}else{
						ws6.addCell(new Label(11, i+1, ""));
					}
					
				}
				List list1=mm.searchAllBySql(str1.toString());
				for(int i=0;i<list1.size();i++){
					Object[] obj1=(Object[])list1.get(i);
					ws7.addCell(new Label(0, i+1, obj1[0].toString()));
					if(obj1[8]!=null){
						ws7.addCell(new Label(1, i+1, obj1[8].toString()));
					}else{
						ws7.addCell(new Label(1, i+1, ""));
					}
					if(obj1[3]!=null){
						ws7.addCell(new Label(2, i+1, obj1[3].toString()));
					}else{
						ws7.addCell(new Label(2, i+1, ""));
					}
					if(obj1[2]!=null){
						ws7.addCell(new Label(3, i+1, obj1[2].toString()));
					}else{
						ws7.addCell(new Label(3, i+1, ""));
					}
					if(obj1[4]!=null){
						ws7.addCell(new Label(4, i+1, obj1[4].toString()));
					}else{
						ws7.addCell(new Label(4, i+1, ""));
					}
					if(obj1[5]!=null){
						ws7.addCell(new Label(5, i+1, obj1[5].toString()));
					}else{
						ws7.addCell(new Label(5, i+1, ""));
					}
					if(obj1[6]!=null){
						ws7.addCell(new Label(6, i+1, obj1[6].toString()));
					}else{
						ws7.addCell(new Label(6, i+1, ""));
					}
					if(obj1[7]!=null){
						ws7.addCell(new Label(7, i+1, obj1[7].toString()));
					}else{
						ws7.addCell(new Label(7, i+1, ""));
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
	//导入犯人数据
	public ActionForward drAllFrXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    Calendar c = Calendar.getInstance();
		 	StringBuffer str2=new StringBuffer("");
		 	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		 	c  =  Calendar.getInstance(Locale.CHINESE);   
		 	String loginTime = simpleDateTimeFormat.format(c.getTime());
		 	
		    FrForm frForm=(FrForm) form;
		    FormFile frFile=frForm.getMyFile();
		    
		    Object[] obj={};
			mm.saveProcess("import_data");
			StringBuffer str=new StringBuffer();
			StringBuffer str1=new StringBuffer();
			boolean flag=true;
			boolean flag1=true;
		    if(frFile.getFileName().endsWith(".xls")){
		    	Workbook workbook = Workbook.getWorkbook(frFile.getInputStream());
		    	Sheet[] sheet=workbook.getSheets();
		    	for(int i=1;i<sheet[0].getRows();i++){
					String hql="from JlJq where jqNo='"+sheet[0].getCell(2,i).getContents().trim()+"'";
					String hql1="from JlJb where jbNo='"+sheet[0].getCell(3,i).getContents().trim()+"'";
					List list=mm.searchAll(hql);
					List list1=mm.searchAll(hql1);
					if(sheet[0].getCell(0,i).getContents().trim().equals("") || sheet[0].getCell(1,i).getContents().trim().equals("") || sheet[0].getCell(2,i).getContents().trim().equals("") || sheet[0].getCell(3,i).getContents().trim().equals("") || sheet[0].getCell(10,i).getContents().trim().equals("") || list.size()==0 || list1.size()==0){
						if(sheet[0].getCell(0,i).getContents().trim().equals("") ){
							str.append("罪犯信息的第"+(i+1)+"行罪犯编号不能为空\n");
						}
						if(sheet[0].getCell(1,i).getContents().trim().equals("")){
							str.append("罪犯信息的第"+(i+1)+"行罪犯姓名不能为空\n");
						}
						if(sheet[0].getCell(2,i).getContents().trim().equals("")){
							str.append("罪犯信息的第"+(i+1)+"行监区不能为空\n");
						}
						if(sheet[0].getCell(3,i).getContents().trim().equals("")){
							str.append("罪犯信息的第"+(i+1)+"行分管等级不能为空\n");
						}
						if(sheet[0].getCell(10,i).getContents().trim().equals("")){
							str.append("罪犯信息的第"+(i+1)+"行是否是重点罪犯不能为空\n");
						}
						
						if(list.size()==0){
							str.append("罪犯信息的第"+(i+1)+"行监区编号不存在\n");
						}
						if(list1.size()==0){
							str.append("罪犯信息的第"+(i+1)+"行分管等级编号不存在\n");
						}
						flag=false;
					}else if(!sheet[0].getCell(10,i).getContents().trim().equals("0") && !sheet[0].getCell(10,i).getContents().trim().equals("1") ){
						
						str.append("罪犯信息的第"+(i+1)+"行是否是重点罪犯数据非法\n");
						flag=false;
					}else{

						String hql2="from JlFbFr where frNo='"+sheet[0].getCell(0,i).getContents().trim()+"'";
						List list2=mm.searchAll(hql2);
						if(list2.size()>0){

							JlFbFr jlFbFr=(JlFbFr)list2.get(0);
						 	jlFbFr.setJq(sheet[0].getCell(2,i).getContents().trim());
						 	jlFbFr.setJbNo(sheet[0].getCell(3,i).getContents().trim());
						 	
						 	mm.update(jlFbFr);
						}else{

							JlFbFr jlFbFr=new JlFbFr();
							jlFbFr.setFrNo(sheet[0].getCell(0,i).getContents().trim());
							if(!sheet[0].getCell(1,i).getContents().trim().equals("")){
						 		jlFbFr.setFrName(sheet[0].getCell(1,i).getContents().trim());
						 	}
							jlFbFr.setJy("Server1");
							jlFbFr.setJq(sheet[0].getCell(2,i).getContents().trim());
							jlFbFr.setJbNo(sheet[0].getCell(3,i).getContents().trim());
							jlFbFr.setQqJb(1);
							jlFbFr.setQqUse(0);
							jlFbFr.setQqLeft(0);
							jlFbFr.setHjJb(0);
							jlFbFr.setHjUse(0);
							jlFbFr.setHjLeft(1);
							jlFbFr.setMonitorFlag("0");
							jlFbFr.setStateZdzf(Integer.parseInt(sheet[0].getCell(10,i).getContents().trim()));
						 	if(!sheet[0].getCell(11,i).getContents().trim().equals("")){
						 		jlFbFr.setZdzfType(sheet[0].getCell(11,i).getContents().trim());
						 	}
						 	jlFbFr.setState(0);
						 	jlFbFr.setSpState(1);
						 	if(!sheet[0].getCell(4,i).getContents().trim().equals("")){
						 		jlFbFr.setFrGj(sheet[0].getCell(4,i).getContents().trim());
						 	}
						 	if(!sheet[0].getCell(5,i).getContents().trim().equals("")){
						 		jlFbFr.setInfoRjsj(sheet[0].getCell(5,i).getContents().trim());
						 	}
						 	if(!sheet[0].getCell(6,i).getContents().trim().equals("")){
						 		jlFbFr.setInfoZm(sheet[0].getCell(6,i).getContents().trim());
						 	}
						 	if(!sheet[0].getCell(7,i).getContents().trim().equals("")){
						 		jlFbFr.setInfoXq(sheet[0].getCell(7,i).getContents().trim());
						 	}
						 	if(!sheet[0].getCell(8,i).getContents().trim().equals("")){
						 		jlFbFr.setInfoCsrq(sheet[0].getCell(8,i).getContents().trim());
						 	}
						 	if(!sheet[0].getCell(9,i).getContents().trim().equals("")){
						 		jlFbFr.setInfoHome(sheet[0].getCell(9,i).getContents().trim());
						 	}
						 	
//						 	SysLog sl=new SysLog();
//						 	sl.setType("正常");
//						 	sl.setLogTime(loginTime);
//						 	sl.setUserName(user.getUserName());
//						 	sl.setUserNo(user.getUserNo());
//						 	sl.setUserIp(request.getRemoteAddr());
//						 	sl.setModel("罪犯管理");
//						 	sl.setInfo(str2.append("从Excel导入增加罪犯（编号：").append(sheet[0].getCell(0,i).getContents().trim()).append(" 罪犯姓名：").append(frName).append(" 所属监区：").append(jq).append(" 级别编号：").append(jbNo).append("）").toString());
//						 	sl.setOp("增加罪犯信息");
//						 	mm.save(jlFr);
							
							
						 	mm.save(jlFbFr);
						}
					}
				}
				for(int i=1;i<sheet[1].getRows();i++){
					String hql="from JlFbFr where frNo='"+sheet[1].getCell(0,i).getContents().trim()+"'";
					List list=mm.searchAll(hql);
					if(sheet[1].getCell(0,i).getContents().trim().equals("") || sheet[1].getCell(2,i).getContents().trim().equals("")){
						if(sheet[1].getCell(0,i).getContents().trim().equals("")){
							str1.append("亲属信息的第"+(i+1)+"行的罪犯编号不能为空\n");
						}
						if(sheet[1].getCell(2,i).getContents().trim().equals("")){
							str1.append("亲属信息的第"+(i+1)+"行的家属姓名不能为空\n");
						}
						flag1=false;
					}else if(!sheet[1].getCell(4,i).getContents().trim().equals("男") && !sheet[1].getCell(4,i).getContents().trim().equals("女")){
						str1.append("亲属信息的第"+(i+1)+"行的性别数据有误\n");
						flag1=false;
					}else if(!sheet[1].getCell(1,i).getContents().trim().equals("1") && !sheet[1].getCell(1,i).getContents().trim().equals("2") && !sheet[1].getCell(1,i).getContents().trim().equals("3") && !sheet[1].getCell(1,i).getContents().trim().equals("4")){	
						str1.append("亲属信息的第"+(i+1)+"行的证件类型数据有误\n");
						flag1=false;
					}else{
						if(i==1 || (i>1 && !sheet[1].getCell(0,i).getContents().trim().equals(sheet[1].getCell(0,i-1).getContents().trim()))){
							String hqlString="delete from JlFbQs where frNo='"+sheet[1].getCell(0,i).getContents().trim()+"'";
							Object[] param={};
							mm.deleteByHql(hqlString, param);
						}
						JlFbQs jlFbQs=new JlFbQs();
						jlFbQs.setFrNo(sheet[1].getCell(0,i).getContents().trim());						
						if(!sheet[1].getCell(1,i).getContents().trim().equals("")){
							jlFbQs.setQsZjlb(Integer.parseInt(sheet[1].getCell(1,i).getContents().trim()));
						}
						if(!sheet[1].getCell(2,i).getContents().trim().equals("")){
							jlFbQs.setQsSfz(sheet[1].getCell(2,i).getContents().trim());
						}
						if(!sheet[1].getCell(3,i).getContents().trim().equals("")){
							jlFbQs.setQsName(sheet[1].getCell(3,i).getContents().trim());
						}						
						if(!sheet[1].getCell(4,i).getContents().trim().equals("")){
							jlFbQs.setXb(sheet[1].getCell(4,i).getContents().trim());
						}
						if(!sheet[1].getCell(5,i).getContents().trim().equals("")){
							jlFbQs.setGx(sheet[1].getCell(5,i).getContents().trim());
						}
						if(!sheet[1].getCell(6,i).getContents().trim().equals("")){
							jlFbQs.setTele(sheet[1].getCell(6,i).getContents().trim());
						}
						if(!sheet[1].getCell(7,i).getContents().trim().equals("")){
							jlFbQs.setDz(sheet[1].getCell(7,i).getContents().trim());
						}
						jlFbQs.setSpState(1);
						mm.save(jlFbQs);
					}
				}

				if(flag==true){
					if(sheet[0].getRows()!=1){
						str.append("已成功提交"+(sheet[0].getRows()-1)+"个罪犯至临时罪犯表中");
					}else{
						str.append("Excel表格中没有罪犯信息");
					}
				 }
				 if(flag1==true){
					 if(sheet[1].getRows()!=1){
						 str1.append("已成功提交"+(sheet[1].getRows()-1)+"个亲属至临时亲属表中");
					 }else{
						 str1.append("Excel表格中没有亲属信息");
					 }
				 }
		    }
		    request.setAttribute("flag", flag);
		    request.setAttribute("flag1", flag1);
		    request.setAttribute("cheCiErrorMessage", str.toString());
		    request.setAttribute("ztzErrorMessage", str1.toString());
		    return mapping.findForward("flagPage");
	}
	//导入到犯人的正式数据
	public ActionForward daoRuZsDB(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,HttpServletResponse response) throws Exception {
			 SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
			 if(sysUser==null){
				  return mapping.findForward("sessionFailure");
			 }
			 mm.saveProcess("import_data1");
			 Thread.sleep(5000);
			 mm.saveProcess("import_data2");
	         return mapping.findForward("drSuccess");
	 }
	//查询特批电话
//	public ActionForward checkTpDh(ActionMapping mapping, ActionForm form,
//		HttpServletRequest request, HttpServletResponse response)
//		throws Exception {
//		SysUser user=(SysUser)request.getSession().getAttribute("users");
//		if(user==null){
//			return mapping.findForward("sessionFailure");
//	    }
//		String frNo=request.getParameter("frNo");
//		StringBuffer str=new StringBuffer("select jl.tpid,jl.frNo,jl.tpTele,jl.tpxm,jl.tpgx,jl.tpcs,jl.sycs,jl.tpsc,jl.tprNo,jl.tprName,jl.tpsj,jlFr.frName from JlQqTpdh jl,JlFr jlFr where jl.frNo=jlFr.frNo and jlFr.frNo='");
//		str.append(frNo+"'");
//		List list=mm.searchAll(str.toString());
//		List<FrTsdh> frTsdhList=new ArrayList();
//		Iterator it=list.iterator();
//		while(it.hasNext()){
//			Object[] obj=(Object[])it.next();
//			FrTsdh frTsdh=new FrTsdh();
//		    frTsdh.setTpid(Integer.parseInt(obj[0].toString()));
//		    frTsdh.setFrNo(obj[1].toString());
//		    frTsdh.setTpTele(obj[2].toString());
//		    if(obj[3]!=null && obj[3].toString().trim()!=""){
//		    	frTsdh.setTpxm(obj[3].toString());
//		    }
//		    if(obj[4]!=null && obj[4].toString().trim()!=""){
//		    	frTsdh.setTpgx(obj[4].toString());
//		    }
//		    frTsdh.setTpcs(Integer.parseInt(obj[5].toString()));
//		    frTsdh.setSycs(Integer.parseInt(obj[6].toString()));
//		    frTsdh.setTpsc(Integer.parseInt(obj[7].toString()));
//		    if(obj[8]!=null && obj[8].toString().trim()!=""){
//		    	frTsdh.setTprNo(obj[8].toString());
//		    }
//		    if(obj[9]!=null && obj[9].toString().trim()!=""){
//		    	frTsdh.setTprName(obj[9].toString());
//		    }
//		    if(obj[10]!=null && obj[10].toString().trim()!=""){
//		    	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//		    	java.util.Date date11 = df1.parse(obj[10].toString()); 
//		    	String time = df1.format(date11);
//		    	frTsdh.setTpsj(time);
//		    }
//		    if(obj[11]!=null && obj[11].toString().trim()!=""){
//		    	frTsdh.setFrName(obj[11].toString());
//		    }
//		    frTsdhList.add(frTsdh);
//		}
//		request.setAttribute("frTsdhList",frTsdhList);
//		request.setAttribute("frNo", frNo);
//		return mapping.findForward("frTsdhMain");
//	}
//	//添加特批电话
//	public ActionForward addFrTpdh(ActionMapping mapping, ActionForm form,
//		HttpServletRequest request, HttpServletResponse response)
//		throws Exception {
//		SysUser user=(SysUser)request.getSession().getAttribute("users");
//	    if(user==null){
//	    	return mapping.findForward("sessionFailure");
//	    }
//	    String frNo=request.getParameter("frNo");
//	    StringBuffer str=new StringBuffer(" from JlFr jl where jl.frNo='");
//		str.append(frNo+"'");
//		List<JlFr> list=mm.searchAll(str.toString());
//		JlFr jlFr=(JlFr)list.get(0);
//		request.setAttribute("jlFr", jlFr);
//	    return mapping.findForward("addFrTpdh");
//	}
//	//添加保存特批电话
//	public ActionForward addSaveFrTpDh(ActionMapping mapping, ActionForm form,
//		HttpServletRequest request, HttpServletResponse response)
//		throws Exception {
//		SysUser user=(SysUser)request.getSession().getAttribute("users");
//		if(user==null){
//			return mapping.findForward("sessionFailure");
//		}
//		String frNo1 = java.net.URLDecoder.decode((String)request.getParameter("frNo1"),"UTF-8");
//		String tpTele = java.net.URLDecoder.decode((String)request.getParameter("tpTele"),"UTF-8");
//		String tpxm = java.net.URLDecoder.decode((String)request.getParameter("tpxm"),"UTF-8");
//		String tpgx1 = java.net.URLDecoder.decode((String)request.getParameter("tpgx1"),"UTF-8");
//		String tpcs = java.net.URLDecoder.decode((String)request.getParameter("tpcs"),"UTF-8");
//		String tpsc = java.net.URLDecoder.decode((String)request.getParameter("tpsc"),"UTF-8");
//		JlQqTpdh jlQqTpdh =new JlQqTpdh();
//		jlQqTpdh.setFrNo(frNo1);
//		jlQqTpdh.setTpTele(tpTele);
//		if(tpxm!=null && tpxm.trim()!=""){
//			jlQqTpdh.setTpxm(tpxm);
//		}
//		if(tpgx1!=null && tpgx1.trim()!=""){
//			jlQqTpdh.setTpgx(tpgx1);
//		}
//		jlQqTpdh.setTpcs(Integer.parseInt(tpcs));
//		jlQqTpdh.setSycs(Integer.parseInt(tpcs));
//		jlQqTpdh.setTpsc(Integer.parseInt(tpsc));
//		jlQqTpdh.setTprNo(user.getUserNo());
//		if(user.getUserName()!=null && user.getUserName()!=""){
//			jlQqTpdh.setTprName(user.getUserName());
//		}
//		Timestamp now=new Timestamp(System.currentTimeMillis());
//		jlQqTpdh.setTpsj(now);
//		StringBuffer str1=new StringBuffer("");
//	    Calendar c = Calendar.getInstance();   
//		SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
//		c  =  Calendar.getInstance(Locale.CHINESE);   
//		String loginTime = simpleDateTimeFormat.format(c.getTime());
//		SysLog sl=new SysLog();
//		sl.setType("正常");
//		sl.setLogTime(loginTime);
//		sl.setUserName(user.getUserName());
//		sl.setUserNo(user.getUserNo());
//	    sl.setUserIp(request.getRemoteAddr());
//		sl.setModel("犯人管理");
//		sl.setInfo(str1.append("给犯人编号为").append(frNo1).append(" 增加特批电话（特批电话时间：").append(loginTime).append(" 特批通话人：").append(tpxm).append(" 与通话人关系：").append(tpgx1).append("）").toString());
//		sl.setOp("增加犯人特批电话信息");
//		mm.save(sl);
//		mm.save(jlQqTpdh);
//		JSONArray json=JSONArray.fromObject(null);
//		response.getWriter().println(json.toString());
//		return null;
//    }
//	//删除特批电话
//	public ActionForward delfrTsdh(ActionMapping mapping, ActionForm form,
//		HttpServletRequest request, HttpServletResponse response)
//		throws Exception {
//		SysUser user=(SysUser)request.getSession().getAttribute("users");
//		if(user==null){
//			return mapping.findForward("sessionFailure");
//		}
//		String frTsdhId=request.getParameter("frTsdhId");
//		JlQqTpdh jlQqTpdh=(JlQqTpdh)mm.findById(JlQqTpdh.class, Integer.parseInt(frTsdhId));
//		String frNo=request.getParameter("frNo");
//		Calendar c = Calendar.getInstance();   
//		SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
//		c  =  Calendar.getInstance(Locale.CHINESE);   
//		String loginTime = simpleDateTimeFormat.format(c.getTime());
//		SysLog sl=new SysLog();
//		sl.setType("严重");
//		sl.setLogTime(loginTime);
//		sl.setUserName(user.getUserName());
//		sl.setUserNo(user.getUserNo());
//		sl.setUserIp(request.getRemoteAddr());
//		sl.setModel("犯人管理");
//		sl.setInfo("删除犯人编号为"+frNo+"的特批电话（特批通话人："+jlQqTpdh.getTpxm()+" 特批电话号码："+jlQqTpdh.getTpTele()+"）");
//		sl.setOp("删除犯人特批电话");
//		mm.save(sl);
//		mm.delete(JlQqTpdh.class, Integer.parseInt(frTsdhId));
//		request.setAttribute("frNo", frNo);
//		return mapping.findForward("delfrTsdh");
//	}
//	//修改特批电话
//	public ActionForward updatefrTsdh(ActionMapping mapping, ActionForm form,
//		HttpServletRequest request, HttpServletResponse response)
//		throws Exception {
//		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
//		if(sysUser==null){
//			return mapping.findForward("sessionFailure");
//		}
//		String frTsdhId=request.getParameter("frTsdhId");
//		String frNo=request.getParameter("frNo");
//		StringBuffer str=new StringBuffer(" from JlFr jl where jl.frNo='");
//		str.append(frNo+"'");
//		List<JlFr> list=mm.searchAll(str.toString());
//		JlFr jlFr=(JlFr)list.get(0);
//		JlQqTpdh jlQqTpdh=(JlQqTpdh)mm.findById(JlQqTpdh.class, Integer.parseInt(frTsdhId));
//		List<String> qsList=new ArrayList();
//	 	qsList.add("夫妻");
//	 	qsList.add("父子");
//	 	qsList.add("母子");
//	 	qsList.add("父女");
//	 	qsList.add("母女");
//	 	qsList.add("爷孙");
//	 	qsList.add("婆孙");
//	 	qsList.add("兄弟");
//	 	qsList.add("姊妹");
//	 	qsList.add("兄妹");
//	 	qsList.add("姐弟");
//	 	qsList.add("朋友");
//	 	qsList.add("同事");
//	 	qsList.add("表哥");
//	 	qsList.add("表弟");
//	 	qsList.add("表兄");
//	 	qsList.add("表妹");
//	 	qsList.add("表姐");
//	 	qsList.add("姨夫");
//	 	qsList.add("叔侄");
//	 	qsList.add("儿媳");
//	 	qsList.add("岳母");
//	 	qsList.add("岳父");
//	 	qsList.add("岳母");
//	 	qsList.add("妹夫");
//	 	qsList.add("舅舅");
//	 	qsList.add("姐夫");
//	 	qsList.add("内弟");
//	 	qsList.add("表兄");
//	 	qsList.add("表妹");
//	 	qsList.add("表妹");
//	 	qsList.add("姑侄");
//	 	qsList.add("姨侄");
//	 	qsList.add("其它");
//	 	request.setAttribute("qsGxList",qsList);
//	 	request.setAttribute("jlQqTpdh", jlQqTpdh);
//	 	request.setAttribute("jlFr", jlFr);
//	 	return mapping.findForward("updatefrTsdh");
//	}
//	//修改保存特批电话
//	public ActionForward updateSaveFrTpDh(ActionMapping mapping, ActionForm form,
//		HttpServletRequest request, HttpServletResponse response)
//		throws Exception {
//		SysUser sysUser=(SysUser)request.getSession().getAttribute("users");
//		if(sysUser==null){
//			return mapping.findForward("sessionFailure");
//		}
//		String tpid = java.net.URLDecoder.decode((String)request.getParameter("tpid"),"UTF-8");
//		String tpTele = java.net.URLDecoder.decode((String)request.getParameter("tpTele"),"UTF-8");
//		String tpxm = java.net.URLDecoder.decode((String)request.getParameter("tpxm"),"UTF-8");
//		String tpgx1 = java.net.URLDecoder.decode((String)request.getParameter("tpgx1"),"UTF-8");
//		String tpcs = java.net.URLDecoder.decode((String)request.getParameter("tpcs"),"UTF-8");
//		String tpsc = java.net.URLDecoder.decode((String)request.getParameter("tpsc"),"UTF-8");
//		StringBuffer str=new StringBuffer("update JlQqTpdh jl set ");
//		if(tpxm!=null && tpxm.trim()!=""){
//			str.append(" jl.tpxm='"+tpxm+"',");
//		}else{
//			str.append(" jl.tpxm='',");
//		}
//		if(tpgx1!=null && tpgx1.trim()!=""){
//			str.append(" jl.tpgx='"+tpgx1+"',");
//		}else{
//			str.append(" jl.tpgx='',");
//		}
//		str.append(" jl.tprNo='"+sysUser.getUserNo()+"',");
//		Timestamp now=new Timestamp(System.currentTimeMillis());
//		str.append(" jl.tpsj='"+now+"',");
//		if(sysUser.getUserName()!=null && sysUser.getUserName()!=""){
//			str.append(" jl.tprName='"+sysUser.getUserName()+"',");
//		}
//		str.append("jl.tpTele='"+tpTele+"',");
//		str.append("jl.tpcs="+Integer.parseInt(tpcs)+",");
//		str.append("jl.sycs="+Integer.parseInt(tpcs)+",");
//		str.append("jl.tpsc="+Integer.parseInt(tpsc));
//		str.append(" where jl.tpid=?");
//		Object[] obj={Integer.parseInt(tpid)};
//		JlQqTpdh jlQqTpdh=(JlQqTpdh)mm.findById(JlQqTpdh.class, Integer.parseInt(tpid));
//		StringBuffer str2=new StringBuffer("");
//		if((tpxm!=null && tpxm.trim()!="" && !jlQqTpdh.getTpxm().equals(tpxm.trim())) || !jlQqTpdh.getTpgx().equals(tpgx1) || !jlQqTpdh.getTpTele().equals("tpTele")){
//			Calendar c = Calendar.getInstance();   
//			SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
//			c  =  Calendar.getInstance(Locale.CHINESE);   
//			String loginTime = simpleDateTimeFormat.format(c.getTime());
//			str2.append("犯人编号为"+jlQqTpdh.getFrNo()+"的特批电话");
//			if(!jlQqTpdh.getTpTele().equals("tpTele")){
//				str2.append(" 特批电话号码由"+jlQqTpdh.getTpTele()+"修改为"+jlQqTpdh.getTpTele());
//			}
//			if(tpxm!=null && !jlQqTpdh.getTpxm().equals(tpxm.trim())){
//				str2.append(" 通话人姓名由"+jlQqTpdh.getTpxm()+"修改为"+tpxm);
//			}
//			if(!jlQqTpdh.getTpgx().equals(tpgx1)){
//				str2.append(" 与通话人关系由"+jlQqTpdh.getTpgx()+"修改为"+tpgx1);
//			}
//			SysLog sl=new SysLog();
//			sl.setType("正常");
//			sl.setLogTime(loginTime);
//			sl.setUserName(sysUser.getUserName());
//			sl.setUserNo(sysUser.getUserNo());
//			sl.setUserIp(request.getRemoteAddr());
//			sl.setModel("犯人管理");
//			sl.setInfo(str2.toString());
//			sl.setOp("修改犯人特批电话");
//			mm.save(sl);
//		}
//		mm.updates(str.toString(), obj);
//		JSONArray json=JSONArray.fromObject(null);
//		response.getWriter().println(json.toString());
//		return null;
//	}
//	//通过服务器名称查询监区
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
		 	 	List<JlJq> list=mm.searchAll(hql);
		 	 	JSONArray jsonArray=JSONArray.fromObject(list);
		 	 	response.setContentType("text/json; charset=UTF-8");   
				response.setCharacterEncoding("UTF-8");  
		 	 	response.getWriter().println(jsonArray.toString());
		 	}else if(user.getGroupNo().equals("Admin")){
		 		String hql="from JlJq where jy='"+jy1+"' and isTs!=1";
		 	 	List<JlJq> list=mm.searchAll(hql);
		 	 	JSONArray jsonArray=JSONArray.fromObject(list);
		 	 	response.setContentType("text/json; charset=UTF-8");   
				response.setCharacterEncoding("UTF-8");  
		 	 	response.getWriter().println(jsonArray.toString());
		 	}else{
		 		String sql="select jl.JQ_No,jl.JQ_Name from JL_JQ jl,SYS_USER_JQ sys where jl.JQ_No=sys.JQ_No and sys.jy='"+jy1+"' and sys.Group_No='"+user.getGroupNo()+"'";
		 		List list=mm.searchAllBySql(sql);
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
	public ActionForward showPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    String webId=request.getParameter("qsid");
			String sql="from JlQs where webId="+webId;
			List<JlQs> list=mm.searchAll(sql);
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
			List<JlQs> list=mm.searchAll(sql);
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
	public ActionForward remoteUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	String frNo = java.net.URLDecoder.decode((String)request.getParameter("frNo"),"UTF-8");
		 	int num=mm.getLineNo("import_qs", frNo);
		 	if(num==0){
		 		JSONArray json=JSONArray.fromObject(0);
		 		response.getWriter().println(json.toString());
		 	}else{
		 		JSONArray json=JSONArray.fromObject(1);
		 		response.getWriter().println(json.toString());
		 	}
		 	return null;
	}
	public ActionForward remoteFr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	String frNo5 = java.net.URLDecoder.decode((String)request.getParameter("frNo5"),"UTF-8");
		 	int num=mm.getLineNo("import_fr", frNo5);
		 	if(num==0){
		 		JSONArray json=JSONArray.fromObject(0);
		 		response.getWriter().println(json.toString());
		 	}else{
		 		JSONArray json=JSONArray.fromObject(1);
		 		response.getWriter().println(json.toString());
		 	}
		 	return null;
	}
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
