package com.slxt.rs.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.slxt.rs.form.YjComeForm;
import com.slxt.rs.model.JlFr;
import com.slxt.rs.model.JlHjDj;
import com.slxt.rs.model.JlJb;
import com.slxt.rs.model.JlJq;
import com.slxt.rs.model.JlQs;
import com.slxt.rs.model.JlYj;
import com.slxt.rs.model.SysHjLine;
import com.slxt.rs.model.SysHjServer;
import com.slxt.rs.model.SysLog;
import com.slxt.rs.model.SysParam;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.HjMonitorService;
import com.slxt.rs.svc.YjComeService;
import com.slxt.rs.vo.HjDjVO;
import com.slxt.rs.vo.HjMonitor;
import com.slxt.rs.vo.MessageVO;
import com.slxt.rs.vo.SfyzVO1;

public class YjComeAction extends DispatchAction{
	private YjComeService ycs;
	public void setYcs(YjComeService ycs) {
		this.ycs = ycs;
	}
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		   	SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			StringBuffer hql=null;
			if(user.getIsSuper()==1){
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp from JL_HJ_DJ dj where dj.State=0 and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp from JL_HJ_DJ dj,JL_JQ jq where dj.State=0 and dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0 and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}else{
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.State=0 and dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}
			YjComeForm yjComeForm=(YjComeForm)form;
			if(yjComeForm.getFrNo()!=null || yjComeForm.getFrName()!=null || yjComeForm.getHjIndex()!=null || yjComeForm.getJq()!=null){
				if(yjComeForm.getFrNo()!=null && !yjComeForm.getFrNo().trim().equals("")){
					hql.append(" and dj.FR_No='"+yjComeForm.getFrNo().trim()+"'");
				}
				if(yjComeForm.getFrName()!=null && !yjComeForm.getFrName().trim().equals("")){
					hql.append(" and dj.FR_Name like '%"+yjComeForm.getFrName().trim()+"%'");
				}
				if(yjComeForm.getHjIndex()!=null && !yjComeForm.getHjIndex().trim().equals("")){
					hql.append(" and dj.HJ_Index ='"+yjComeForm.getHjIndex().trim()+"'");
				}
				if(yjComeForm.getJq()!=null && !yjComeForm.getJq().equals("null")){
					hql.append(" and dj.JQ_No ='"+yjComeForm.getJq()+"'");
				}
			}
			hql.append(" order by dj.FP_Flag,dj.JQ_No desc");
			List list=ycs.searchAllBySql(hql.toString());
			List<HjDjVO> list1=new ArrayList<HjDjVO>();
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
		    	if(obj1[23]!=null){
		    		hjDjVO.setInfoWp(obj1[23].toString());
		    	}else{
		    		hjDjVO.setInfoWp("0");
		    	}
		    	list1.add(hjDjVO);
		    }
			String hql1="from JlJq";
			List<JlJq> list2=ycs.searchAll(hql1);
			request.setAttribute("jlJqList", list2);
			request.setAttribute("list1",list1);
			return mapping.findForward("yjComeMain");
	}
	public ActionForward jquerSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			StringBuffer hql=null;
			if(user.getIsSuper()==1){
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp,dj.SH_State from JL_HJ_DJ dj where dj.State=0 and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp,dj.SH_State from JL_HJ_DJ dj,JL_JQ jq where dj.State=0 and dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0 and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}else{
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp,dj.SH_State from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.State=0 and dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}
			hql.append(" order by dj.JQ_No");
			List list=ycs.searchAllBySql(hql.toString());
			List<HjDjVO> list1=new ArrayList<HjDjVO>();
			for(int i=0;i<list.size();i++){
		    	Object[] obj1=(Object[])list.get(i);
		    	HjDjVO hjDjVO=new HjDjVO();
		    	hjDjVO.setHjid(obj1[0].toString());
		    	hjDjVO.setJy(obj1[1].toString());
		    	if(obj1[2]!=null && !obj1[2].toString().equals("")){
		    		hjDjVO.setJqName(obj1[2].toString());
		    	}
		    	hjDjVO.setFrNo(obj1[3].toString());
		    	
		    	StringBuffer str=new StringBuffer("from JlFr where frNo='");
				str.append(obj1[3].toString()+"'");				
				List<JlFr> list11=ycs.searchAll(str.toString());
				
				if(list11.get(0).getStateZdzf()==1){
					hjDjVO.setStateZdzf("是");
				}else{
					hjDjVO.setStateZdzf("否");
				}
				
				StringBuffer str1=new StringBuffer("from JlJb where jbNo='");
				str1.append(list11.get(0).getJbNo()+"'");				
				List<JlJb> list111=ycs.searchAll(str1.toString());
				
				hjDjVO.setJbName(list111.get(0).getJbName());
				
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
		    	if(obj1[23]!=null){
		    		hjDjVO.setInfoWp(obj1[23].toString());
		    	}else{
		    		hjDjVO.setInfoWp("0");
		    	}
		    	hjDjVO.setShState(obj1[24].toString());
		    	list1.add(hjDjVO);
		    }
			response.setContentType("text/json; charset=utf-8"); 
			JSONArray json=JSONArray.fromObject(list1);
			response.getWriter().println(json.toString());
			return null;
	}
	public ActionForward goToYjCome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
			if(jlHjDj.getYjNo()!=null && !jlHjDj.getYjNo().equals("")){
				String hql="from JlYj where yjNum='"+jlHjDj.getYjNo()+"'";
				List<JlYj> list=ycs.searchAll(hql);
				if(list.size()>0){
					request.setAttribute("jlYj", list.get(0));
				}
				
			}
			request.setAttribute("jlHjDj", jlHjDj);
			return mapping.findForward("goToYjCome");
	}
	//警察签到刷卡或输入编号分配座位（通用型）
	public ActionForward fpZw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			String yjNum = java.net.URLDecoder.decode((String)request.getParameter("yjNum"),"UTF-8");
			String hql="from JlYj where yjNum='"+yjNum.trim()+"'";
			List<JlYj> list=ycs.searchAll(hql);
			if(list.size()>0){
				JlYj jlYj=list.get(0);
				JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
//				if(jlHjDj.getJy().equals("Server2")){
//					if(jlHjDj.getHjType()==1){
//						jlHjDj.setYjNo(yjNum.trim());
//						jlHjDj.setYjName(jlYj.getYjName());
//						Timestamp now=new Timestamp(System.currentTimeMillis());
//						jlHjDj.setFrInTime(now);
//						jlHjDj.setFrInUser(user.getUserNo());
//						ycs.update(jlHjDj);
//						if(jlHjDj.getFpFlag()==0){
//							int result=ycs.getLineNo("set_ZW",jlHjDj.getFrNo());
//							if(result==0){
//								JSONArray json=JSONArray.fromObject(0);
//								response.getWriter().println(json.toString());
//							}else{
//								JSONArray json=JSONArray.fromObject(1);
//								response.getWriter().println(json.toString());
//							}
//						}else{
//							JSONArray json=JSONArray.fromObject(0);
//							response.getWriter().println(json.toString());
//						}
//					}else{
//						
//						Timestamp now=new Timestamp(System.currentTimeMillis());
//						jlHjDj.setState(1);
//						jlHjDj.setYjNo(yjNum.trim());
//						jlHjDj.setYjName(jlYj.getYjName());
//						jlHjDj.setFrInTime(now);
//						jlHjDj.setFrInUser(user.getUserNo());
//						ycs.update(jlHjDj);
//						ycs.saveProcess("update_hjcs", jlHjDj.getFrNo());
//						JSONArray json=JSONArray.fromObject(3);
//						response.getWriter().println(json.toString());
//					}
//				}else{
//					if(jlHjDj.getHjType()==1){
//						jlHjDj.setYjNo(yjNum.trim());
//						jlHjDj.setYjName(jlYj.getYjName());
//						Timestamp now=new Timestamp(System.currentTimeMillis());
//						jlHjDj.setFrInTime(now);
//						jlHjDj.setFrInUser(user.getUserNo());
//						ycs.update(jlHjDj);
//						if(jlHjDj.getFpFlag()==0){
//							int result=ycs.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
//							if(result==0){
//								MessageVO messageVO=new MessageVO();
//								messageVO.setFrName(jlHjDj.getFrName());
//								String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
//								if(lineNo.equals("-1")){
//									messageVO.setXx("分配座位成功                座位号为：无");
//								}else{
//									messageVO.setXx("分配座位成功                座位号为："+lineNo);
//								}
//								response.setContentType("text/json; charset=utf-8"); 
//								JSONArray json=JSONArray.fromObject(messageVO);
//								response.getWriter().println(json.toString());
//							}else{
//								JSONArray json=JSONArray.fromObject(-1);
//								response.getWriter().println(json.toString());
//							}
//						}else{
//							MessageVO messageVO=new MessageVO();
//							messageVO.setFrName(jlHjDj.getFrName());
//							String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
//							if(lineNo.equals("-1")){
//								messageVO.setXx("分配座位成功                座位号为：无");
//							}else{
//								messageVO.setXx("分配座位成功                座位号为："+lineNo);
//							}
//							response.setContentType("text/json; charset=utf-8"); 
//							JSONArray json=JSONArray.fromObject(messageVO);
//							response.getWriter().println(json.toString());
//						}
//					}else{
//						jlHjDj.setYjNo(yjNum.trim());
//						jlHjDj.setYjName(jlYj.getYjName());
//						Timestamp now=new Timestamp(System.currentTimeMillis());
//						jlHjDj.setFrInTime(now);
//						jlHjDj.setFrInUser(user.getUserNo());
//						ycs.update(jlHjDj);
//						if(jlHjDj.getFpFlag()==0){
//							int result=ycs.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
//							if(result==0){
//								MessageVO messageVO=new MessageVO();
//								messageVO.setFrName(jlHjDj.getFrName());
//								String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
//								if(lineNo.equals("-1")){
//									messageVO.setXx("分配座位成功                座位号为：无");
//								}else{
//									messageVO.setXx("分配座位成功                座位号为："+lineNo);
//								}
//								JSONArray json=JSONArray.fromObject(messageVO);
//								response.getWriter().println(json.toString());
//							}else{
//								JSONArray json=JSONArray.fromObject(-1);
//								response.getWriter().println(json.toString());
//							}
//						}else{
//							MessageVO messageVO=new MessageVO();
//							messageVO.setFrName(jlHjDj.getFrName());
//							String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
//							if(lineNo.equals("-1")){
//								messageVO.setXx("分配座位成功                座位号为：无");
//							}else{
//								messageVO.setXx("分配座位成功                座位号为："+lineNo);
//							}
//							response.setContentType("text/json; charset=utf-8"); 
//							JSONArray json=JSONArray.fromObject(messageVO);
//							response.getWriter().println(json.toString());
//						}
////						Timestamp now=new Timestamp(System.currentTimeMillis());
////						jlHjDj.setState(1);
////						jlHjDj.setYjNo(yjNum.trim());
////						jlHjDj.setYjName("");
////						jlHjDj.setFrInTime(now);
////						jlHjDj.setFrInUser(user.getUserNo());
////						ycs.update(jlHjDj);
////						ycs.saveProcess("update_hjcs", jlHjDj.getFrNo());
////						JSONArray json=JSONArray.fromObject(3);
////						response.getWriter().println(json.toString());
//					}
//				}
				jlHjDj.setYjNo(yjNum.trim());
				jlHjDj.setYjName(jlYj.getYjName());
				Timestamp now=new Timestamp(System.currentTimeMillis());
				jlHjDj.setFrInTime(now);
				jlHjDj.setFrInUser(user.getUserNo());
				ycs.update(jlHjDj);
				if(jlHjDj.getFpFlag()==0){
					int result=ycs.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
					if(result==0){
						MessageVO messageVO=new MessageVO();
						messageVO.setFrName(jlHjDj.getFrName());
						String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
						//System.out.println(lineNo);
						if(lineNo.equals("-1")){
							messageVO.setXx("分配座位失败                座位号为：无");
						}else{
							messageVO.setXx("分配座位成功                座位号为："+lineNo);
						}
						response.setContentType("text/json; charset=utf-8"); 
						JSONArray json=JSONArray.fromObject(messageVO);
						response.getWriter().println(json.toString());
					}else{
						JSONArray json=JSONArray.fromObject(1);
						response.getWriter().println(json.toString());
					}
				}else{
					MessageVO messageVO=new MessageVO();
					messageVO.setFrName(jlHjDj.getFrName());
					String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
					if(lineNo.equals("-1")){
						messageVO.setXx("分配座位失败                座位号为：无");
					}else{
						messageVO.setXx("分配座位成功                座位号为："+lineNo);
					}
					response.setContentType("text/json; charset=utf-8"); 
					JSONArray json=JSONArray.fromObject(messageVO);
					response.getWriter().println(json.toString());
				}
			}else{
				JSONArray json=JSONArray.fromObject(2);
				response.getWriter().println(json.toString());
			}
			return null;
	}
	//警察签到刷卡或输入编号分配座位（适用广东高明监狱）
	public ActionForward fpZw11(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			String yjNum = java.net.URLDecoder.decode((String)request.getParameter("yjNum"),"UTF-8");
			String hql="from JlYj where yjNum='"+yjNum.trim()+"'";
			List<JlYj> list=ycs.searchAll(hql);
			if(list.size()>0){
				JlYj jlYj=list.get(0);
				JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
				if(jlHjDj.getJy().equals("Server2")){
					if(jlHjDj.getHjType()==1){
						jlHjDj.setYjNo(yjNum.trim());
						jlHjDj.setYjName(jlYj.getYjName());
						Timestamp now=new Timestamp(System.currentTimeMillis());
						jlHjDj.setFrInTime(now);
						jlHjDj.setFrInUser(user.getUserNo());
						ycs.update(jlHjDj);
						if(jlHjDj.getFpFlag()==0){
							int result=ycs.getLineNo("set_ZW",jlHjDj.getFrNo());
							if(result==0){
								JSONArray json=JSONArray.fromObject(0);
								response.getWriter().println(json.toString());
							}else{
								JSONArray json=JSONArray.fromObject(1);
								response.getWriter().println(json.toString());
							}
						}else{
							JSONArray json=JSONArray.fromObject(0);
							response.getWriter().println(json.toString());
						}
					}else{
						
						Timestamp now=new Timestamp(System.currentTimeMillis());
						jlHjDj.setState(1);
						jlHjDj.setYjNo(yjNum.trim());
						jlHjDj.setYjName(jlYj.getYjName());
						jlHjDj.setFrInTime(now);
						jlHjDj.setFrInUser(user.getUserNo());
						ycs.update(jlHjDj);
						ycs.saveProcess("update_hjcs", jlHjDj.getFrNo());
						JSONArray json=JSONArray.fromObject(3);
						response.getWriter().println(json.toString());
					}
				}else{
					if(jlHjDj.getHjType()==1){
						jlHjDj.setYjNo(yjNum.trim());
						jlHjDj.setYjName(jlYj.getYjName());
						Timestamp now=new Timestamp(System.currentTimeMillis());
						jlHjDj.setFrInTime(now);
						jlHjDj.setFrInUser(user.getUserNo());
						ycs.update(jlHjDj);
						if(jlHjDj.getFpFlag()==0){
							int result=ycs.getTypeZw("set_ZW_new",jlHjDj.getFrNo(),jlHjDj.getHjType());
							if(result==0){
								JSONArray json=JSONArray.fromObject(0);
								response.getWriter().println(json.toString());
							}else{
								JSONArray json=JSONArray.fromObject(1);
								response.getWriter().println(json.toString());
							}
						}else{
							JSONArray json=JSONArray.fromObject(0);
							response.getWriter().println(json.toString());
						}
					}else{
						jlHjDj.setYjNo(yjNum.trim());
						jlHjDj.setYjName(jlYj.getYjName());
						Timestamp now=new Timestamp(System.currentTimeMillis());
						jlHjDj.setFrInTime(now);
						jlHjDj.setFrInUser(user.getUserNo());
						ycs.update(jlHjDj);
						if(jlHjDj.getFpFlag()==0){
							int result=ycs.getTypeZw("set_ZW_new",jlHjDj.getFrNo(),jlHjDj.getHjType());
							if(result==0){
								JSONArray json=JSONArray.fromObject(0);
								response.getWriter().println(json.toString());
							}else{
								JSONArray json=JSONArray.fromObject(1);
								response.getWriter().println(json.toString());
							}
						}else{
							JSONArray json=JSONArray.fromObject(0);
							response.getWriter().println(json.toString());
						}
//						Timestamp now=new Timestamp(System.currentTimeMillis());
//						jlHjDj.setState(1);
//						jlHjDj.setYjNo(yjNum.trim());
//						jlHjDj.setYjName("");
//						jlHjDj.setFrInTime(now);
//						jlHjDj.setFrInUser(user.getUserNo());
//						ycs.update(jlHjDj);
//						ycs.saveProcess("update_hjcs", jlHjDj.getFrNo());
//						JSONArray json=JSONArray.fromObject(3);
//						response.getWriter().println(json.toString());
					}
				}
				
			}else{
				JSONArray json=JSONArray.fromObject(2);
				response.getWriter().println(json.toString());
			}
			return null;
	}
	//取消签到通用
	public ActionForward delQd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			
			String hql5="from SysParam where paramName='HJ_Client4'";
			List<SysParam> list5=ycs.searchAll(hql5);
			if(list5.size()>0){
				SysParam sysParam1=(SysParam)list5.get(0);
				if(!request.getRemoteAddr().equals(sysParam1.getParamData1())){
					JSONArray json=JSONArray.fromObject(3);
					response.getWriter().println(json.toString());
					return null;
				}
			}
		
			String hjid=request.getParameter("hjid");
			JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
			if(jlHjDj.getFpFlag()==2){
				JSONArray json=JSONArray.fromObject(1);
				response.getWriter().println(json.toString());
				return null;
			}else if(jlHjDj.getFpFlag()==0){
//				jlHjDj.setYjName(null);
//				jlHjDj.setYjNo(null);
//				jlHjDj.setFrInTime(null);
//				jlHjDj.setFrInUser(null);
//				ycs.update(jlHjDj);
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
				return null;
			}else{
				String hql="update SysHjLine set hjid=null where hjid="+jlHjDj.getHjid();
				Object[] objects={};
				ycs.updates(hql, objects);
//				jlHjDj.setYjName(null);
//				jlHjDj.setYjNo(null);
				String hql1="from JlFr where frNo='"+jlHjDj.getFrNo()+"'";
				List<JlFr> list=ycs.searchAll(hql1);
				if(list.size()>0){
					jlHjDj.setJy(list.get(0).getJy());
				}else{
					jlHjDj.setJy("Server1");
				}
				
				jlHjDj.setFpFlag(0);
				jlHjDj.setFpLineNo(null);
				jlHjDj.setFpTime(null);
				jlHjDj.setFpTimeFr(null);
				jlHjDj.setFpTimeQs(null);
//				jlHjDj.setFrInTime(null);
//				jlHjDj.setFrInUser(null);
				ycs.update(jlHjDj);
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
				return null;
			}
			
	}
	//取消签到（广东高明）
	public ActionForward delQd1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
			if(jlHjDj.getFpFlag()==2){
				JSONArray json=JSONArray.fromObject(1);
				response.getWriter().println(json.toString());
			}else if(jlHjDj.getFpFlag()==0){
				jlHjDj.setYjName(null);
				jlHjDj.setYjNo(null);
				jlHjDj.setFrInTime(null);
				jlHjDj.setFrInUser(null);
				ycs.update(jlHjDj);
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}else{
				String hql="update SysHjLine set hjid=null where hjid="+jlHjDj.getHjid();
				Object[] objects={};
				ycs.updates(hql, objects);
				jlHjDj.setYjName(null);
				jlHjDj.setYjNo(null);
				String hql1="from JlFr where frNo='"+jlHjDj.getFrNo()+"'";
				List<JlFr> list=ycs.searchAll(hql1);
				if(list.size()>0){
					jlHjDj.setJy(list.get(0).getJy());
				}else{
					jlHjDj.setJy("Server1");
				}
				
				jlHjDj.setFpFlag(0);
				jlHjDj.setFpLineNo(null);
				jlHjDj.setFpTime(null);
				jlHjDj.setFrInTime(null);
				jlHjDj.setFrInUser(null);
				ycs.update(jlHjDj);
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}
			return null;
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
				String  hql="from JlYj where yjCard=('"+yjCardNo+"')";
				List<JlYj> list=ycs.searchAll(hql);
				response.setContentType("text/json; charset=utf-8"); 
				JSONArray json=JSONArray.fromObject(list);
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
			}
			
			
			return null;
	}
	public ActionForward rgfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
			String hjid=request.getParameter("hjid");
			JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
			request.setAttribute("jlHjDj", jlHjDj);
			if(jlHjDj.getJy().equals("Server1")){
				String hql1="from SysHjLine where  hjid is null and state=1 and hjstate=0";
				if(jlHjDj.getHjType()==1){
					hql1+=" and (jy='Server1' and lineType=0) or (jy='Server3' and lineType=0)";
				}else{
					hql1+=" and (jy='Server1' and lineType=1) or (jy='Server3' and lineType=1)";
				}
				List<SysHjLine> list1=ycs.searchAll(hql1);
				request.setAttribute("sysHjLineList", list1);
				String hql="from SysHjServer";
				List<SysHjServer> list=ycs.searchAll(hql);
				request.setAttribute("sysQqServerList", list);
			}else{
				String hql1="from SysHjLine where jy='Server2'  and hjid is null and state=1 and hjstate=0";
				if(jlHjDj.getHjType()==1){
					hql1+=" and lineType=0";
				}else{
					hql1+=" and lineType=1";
				}
				List<SysHjLine> list1=ycs.searchAll(hql1);
				request.setAttribute("sysHjLineList", list1);
				String hql="from SysHjServer";
				List<SysHjServer> list=ycs.searchAll(hql);
				request.setAttribute("sysQqServerList", list);
			}
			if(jlHjDj.getYjNo()!=null && !jlHjDj.getYjNo().equals("")){
				String hql="from JlYj where yjNum='"+jlHjDj.getYjNo()+"'";
				List<JlYj> list=ycs.searchAll(hql);
				if(list.size()>0){
					request.setAttribute("jlYj", list.get(0));
				}
				
			}
			return mapping.findForward("rgfp");
	}
	public ActionForward checkLine(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 	SysUser user=(SysUser)request.getSession().getAttribute("users");
		 	if(user==null){
		 		return mapping.findForward("sessionFailure");
		 	}
		 	String jy1 = java.net.URLDecoder.decode((String)request.getParameter("jy1"),"UTF-8");
		 	String hjType = request.getParameter("hjType");
		 	
	 	 	String hql="from SysHjLine where jy='"+jy1+"' and hjid is null and state=1 and hjstate=0";
	 	 	if(Integer.parseInt(hjType)==1){
				hql+=" and lineType=0";
			}else{
				hql+=" and lineType=1";
			}
	 	 	List<SysHjLine> list=ycs.searchAll(hql);
	 	 	JSONArray jsonArray=JSONArray.fromObject(list);
	 	 	response.setContentType("text/json; charset=UTF-8");   
			response.setCharacterEncoding("UTF-8");  
	 	 	response.getWriter().println(jsonArray.toString());	 	
		 	return null;
	}
	//警察签到刷卡或输入编号人工分配座位（通用型）
	public ActionForward rgfpZw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			String yjNum = java.net.URLDecoder.decode((String)request.getParameter("yjNum"),"UTF-8");
			String hql="from JlYj where yjNum='"+yjNum.trim()+"'";
			List<JlYj> list=ycs.searchAll(hql);
			if(list.size()>0){
				String jy = java.net.URLDecoder.decode((String)request.getParameter("jy"),"UTF-8");
				String zwNo= java.net.URLDecoder.decode((String)request.getParameter("zwNo"),"UTF-8");
				JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
				jlHjDj.setYjNo(yjNum.trim());
				jlHjDj.setYjName("");
				Timestamp now=new Timestamp(System.currentTimeMillis());
				jlHjDj.setFrInTime(now);
				jlHjDj.setFrInUser(user.getUserNo());
				ycs.update(jlHjDj);
				if(jlHjDj.getFpFlag()==0){
					int result=ycs.rgfpZw("set_ZW_ts",jlHjDj.getFrNo(),jy,Integer.parseInt(zwNo));
					if(result==0){
						JSONArray json=JSONArray.fromObject(0);
						response.getWriter().println(json.toString());
					}else{
						JSONArray json=JSONArray.fromObject(1);
						response.getWriter().println(json.toString());
					}
				}else{
					JSONArray json=JSONArray.fromObject(0);
					response.getWriter().println(json.toString());
				}
			}else{
				JSONArray json=JSONArray.fromObject(2);
				response.getWriter().println(json.toString());
			}
			return null;
	}
	//警察签到刷卡或输入编号人工分配座位（广东高明监狱）
	public ActionForward rgfpZw2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			String yjNum = java.net.URLDecoder.decode((String)request.getParameter("yjNum"),"UTF-8");
			String hql="from JlYj where yjNum='"+yjNum.trim()+"'";
			List<JlYj> list=ycs.searchAll(hql);
			if(list.size()>0){
				String jy = java.net.URLDecoder.decode((String)request.getParameter("jy"),"UTF-8");
				String zwNo= java.net.URLDecoder.decode((String)request.getParameter("zwNo"),"UTF-8");
				JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
				jlHjDj.setYjNo(yjNum.trim());
				jlHjDj.setYjName("");
				Timestamp now=new Timestamp(System.currentTimeMillis());
				jlHjDj.setFrInTime(now);
				jlHjDj.setFrInUser(user.getUserNo());
				ycs.update(jlHjDj);
				if(jlHjDj.getFpFlag()==0){
					int result=ycs.rgfpZw("set_ZW_ts",jlHjDj.getFrNo(),jy,Integer.parseInt(zwNo));
					if(result==0){
						JSONArray json=JSONArray.fromObject(0);
						response.getWriter().println(json.toString());
					}else{
						JSONArray json=JSONArray.fromObject(1);
						response.getWriter().println(json.toString());
					}
				}else{
					JSONArray json=JSONArray.fromObject(0);
					response.getWriter().println(json.toString());
				}
			}else{
				JSONArray json=JSONArray.fromObject(2);
				response.getWriter().println(json.toString());
			}
			return null;
	}
	//打印
	public ActionForward printXp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
			JlHjDj jlHjDj=(JlHjDj)ycs.findById(JlHjDj.class, Long.parseLong(webId));

			
			//boolean flag=true;
			String returnName="printView";
			if(jlHjDj!=null){
				List<String> list1=new ArrayList<String>();
//				if(jlHjDj.getFrName()!=null){
//					String nameString="服刑人员姓名:"+jlHjDj.getFrName();
//					list1.add(nameString);
//				}
//				if(jlHjDj.getFrNo()!=null){
//					String nameString="服刑人员编号:"+jlHjDj.getFrNo();
//					list1.add(nameString);
//				}
				String zw=ycs.getZw1("get_zw1", jlHjDj.getHjid());
				if(zw.equals("-1")){
					list1.add("会见窗口:");
				}else{
					list1.add("会见窗口:"+zw);
				}
				
				int i=0;
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
			
				request.setAttribute("list1", list1);
			}else{
				returnName="djDel";
			}
		    return mapping.findForward(returnName);
	}
	//授权才能使用电话
	public ActionForward updateShState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			
			String hql5="from SysParam where paramName='HJ_Client4'";
			List<SysParam> list5=ycs.searchAll(hql5);
			if(list5.size()>0){
				SysParam sysParam1=(SysParam)list5.get(0);
				if(!request.getRemoteAddr().equals(sysParam1.getParamData1())){
					JSONArray json=JSONArray.fromObject(3);
					response.getWriter().println(json.toString());
					return null;
				}
			}
			
			String webId=request.getParameter("webId");
			String str="update JL_HJ_DJ set SH_State = 1,YJ_No='"+user.getUserNo()+"',YJ_Name='"+user.getUserName()+"',FR_In_User='"+user.getUserNo()+"',FR_In_Time=getdate() where HJID = "+webId+"";    
		    ycs.executeUpdate(str);
			JSONArray json=JSONArray.fromObject(0);
			response.getWriter().println(json.toString());
			
		    return null;
	}
	//取消授权才能使用电话
	public ActionForward delShState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			
			String hql5="from SysParam where paramName='HJ_Client4'";
			List<SysParam> list5=ycs.searchAll(hql5);
			if(list5.size()>0){
				SysParam sysParam1=(SysParam)list5.get(0);
				if(!request.getRemoteAddr().equals(sysParam1.getParamData1())){
					JSONArray json=JSONArray.fromObject(3);
					response.getWriter().println(json.toString());
					return null;
				}
			}
			
			String webId=request.getParameter("webId");
			String str="update JL_HJ_DJ set SH_State = 0 where HJID = "+webId+"";    
		    ycs.executeUpdate(str);
			JSONArray json=JSONArray.fromObject(0);
			response.getWriter().println(json.toString());
			
		    return null;
	}
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		   	SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			StringBuffer hql=null;
			if(user.getIsSuper()==1){
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp,dj.SH_State from JL_HJ_DJ dj where dj.State=0 and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp,dj.SH_State from JL_HJ_DJ dj,JL_JQ jq where dj.State=0 and dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0 and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}else{
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp,dj.SH_State from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.State=0 and dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}
			YjComeForm yjComeForm=(YjComeForm)form;
			if(yjComeForm.getFrNo()!=null || yjComeForm.getFrName()!=null || yjComeForm.getHjIndex()!=null){
				if(yjComeForm.getFrNo()!=null && !yjComeForm.getFrNo().trim().equals("")){
					hql.append(" and dj.FR_No='"+yjComeForm.getFrNo().trim()+"'");
				}
				if(yjComeForm.getFrName()!=null && !yjComeForm.getFrName().trim().equals("")){
					hql.append(" and dj.FR_Name like '%"+yjComeForm.getFrName().trim()+"%'");
				}
				if(yjComeForm.getHjIndex()!=null && !yjComeForm.getHjIndex().trim().equals("")){
					hql.append(" and dj.HJ_Index ='"+yjComeForm.getHjIndex().trim()+"'");
				}
			}
			hql.append(" order by dj.JQ_No ");
			List list=ycs.searchAllBySql(hql.toString());
			List<HjDjVO> list1=new ArrayList<HjDjVO>();
			for(int i=0;i<list.size();i++){
		    	Object[] obj1=(Object[])list.get(i);
		    	HjDjVO hjDjVO=new HjDjVO();
		    	hjDjVO.setHjid(obj1[0].toString());
		    	hjDjVO.setJy(obj1[1].toString());
		    	if(obj1[2]!=null && !obj1[2].toString().equals("")){
		    		hjDjVO.setJqName(obj1[2].toString());
		    	}
		    	hjDjVO.setFrNo(obj1[3].toString());
		    	
		    	StringBuffer str=new StringBuffer("from JlFr where frNo='");
				str.append(obj1[3].toString()+"'");				
				List<JlFr> list11=ycs.searchAll(str.toString());
				
				if(list11.get(0).getStateZdzf()==1){
					hjDjVO.setStateZdzf("是");
				}else{
					hjDjVO.setStateZdzf("否");
				}
				
				StringBuffer str1=new StringBuffer("from JlJb where jbNo='");
				str1.append(list11.get(0).getJbNo()+"'");				
				List<JlJb> list111=ycs.searchAll(str1.toString());
				
				hjDjVO.setJbName(list111.get(0).getJbName());

				
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
		    	if(obj1[23]!=null){
		    		hjDjVO.setInfoWp(obj1[23].toString());
		    	}else{
		    		hjDjVO.setInfoWp("0");
		    	}
		    	hjDjVO.setShState(obj1[24].toString());
		    	list1.add(hjDjVO);
		    }
			Calendar c = Calendar.getInstance();
			SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		 	c  =  Calendar.getInstance(Locale.CHINESE);   
		 	String loginTime1 = simpleDateTimeFormat.format(c.getTime());
		 	String loginTime=loginTime1.substring(0, 10)+" 23:59:59";
		 	String endTime=loginTime1.substring(0, 10)+" 00:00:00";
			String sql="select count(*) from JL_HJ_FR_INOUTINFO where state=1 and sj<='"+loginTime+"' and sj>='"+endTime+"'";
			String sql1="select count(*) from JL_HJ_FR_INOUTINFO where state=2 and sj<='"+loginTime+"' and sj>='"+endTime+"'";
			List list3=ycs.searchAllBySql(sql);
			List list4=ycs.searchAllBySql(sql1);
			int left=Integer.parseInt(list3.iterator().next().toString())-Integer.parseInt(list4.iterator().next().toString());
			request.setAttribute("inCount", list3.iterator().next().toString());
			request.setAttribute("outCount",list4.iterator().next().toString());
			request.setAttribute("leftCount",left);
			request.setAttribute("list1",list1);
			return mapping.findForward("yjComeMain1");
	}
	public ActionForward fpZw1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			
			String hql5="from SysParam where paramName='HJ_Client4'";
			List<SysParam> list5=ycs.searchAll(hql5);
			if(list5.size()>0){
				SysParam sysParam1=(SysParam)list5.get(0);
				if(!request.getRemoteAddr().equals(sysParam1.getParamData1())){
					JSONArray json=JSONArray.fromObject(3);
					response.getWriter().println(json.toString());
					return null;
				}
			}
			
			String hjid=request.getParameter("hjid");
			long hjid1=Long.parseLong(hjid);
			JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
//			Timestamp now=new Timestamp(System.currentTimeMillis());
//			jlHjDj.setFrInTime(now);
//			jlHjDj.setFrInUser(user.getUserNo());
//			ycs.update(jlHjDj);
			if(jlHjDj.getFpFlag()==0){
				int result=ycs.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
				if(result==0){
					JSONArray json=JSONArray.fromObject(hjid1);
					response.getWriter().println(json.toString());
					return null;
				}else{
					JSONArray json=JSONArray.fromObject(1);
					response.getWriter().println(json.toString());
					return null;
				}
			}else{
				JSONArray json=JSONArray.fromObject(2);
				response.getWriter().println(json.toString());
				return null;
			}
			
	}
	public ActionForward rgfp1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
			request.setAttribute("jlHjDj", jlHjDj);
			String hql="from SysHjServer";
			List<SysHjServer> list=ycs.searchAll(hql);
			request.setAttribute("sysQqServerList", list);
			String hql1="from SysHjLine where jy='Server1' and hjid is null and state=1 and hjstate=0";
			if(jlHjDj.getHjType()==2){
				hql1+=" and lineType=1";
			}else{
				hql1+=" and lineType=0";
			}
			List<SysHjLine> list1=ycs.searchAll(hql1);
			request.setAttribute("sysHjLineList", list1);
			return mapping.findForward("rgfp1");
	}
	public ActionForward rgfpZw1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			
			String hql5="from SysParam where paramName='HJ_Client4'";
			List<SysParam> list5=ycs.searchAll(hql5);
			if(list5.size()>0){
				SysParam sysParam1=(SysParam)list5.get(0);
				if(!request.getRemoteAddr().equals(sysParam1.getParamData1())){
					JSONArray json=JSONArray.fromObject(3);
					response.getWriter().println(json.toString());
					return null;
				}
			}
			
			String hjid=request.getParameter("hjid");
			long hjid1=Long.parseLong(hjid);
			String jy = java.net.URLDecoder.decode((String)request.getParameter("jy"),"UTF-8");
			String zwNo= java.net.URLDecoder.decode((String)request.getParameter("zwNo"),"UTF-8");
			JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
//			jlHjDj.setYjNo("");
//			jlHjDj.setYjName("");
//			Timestamp now=new Timestamp(System.currentTimeMillis());
//			jlHjDj.setFrInTime(now);
//			jlHjDj.setFrInUser(user.getUserNo());
//			ycs.update(jlHjDj);
			if(jlHjDj.getFpFlag()==0){
				int result=ycs.rgfpZw("set_ZW_ts",jlHjDj.getFrNo(),jy,Integer.parseInt(zwNo));
				if(result==0){
					JSONArray json=JSONArray.fromObject(hjid1);
					response.getWriter().println(json.toString());
					return null;
				}else{
					JSONArray json=JSONArray.fromObject(1);
					response.getWriter().println(json.toString());
					return null;
				}
			}else{
				JSONArray json=JSONArray.fromObject(2);
				response.getWriter().println(json.toString());
				return null;
			}
			
			
	}
	public ActionForward fpZw2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String cardNum=request.getParameter("cardNum");
			String hql="from JlFr where frCard='"+cardNum+"'";
			List<JlFr> list=ycs.searchAll(hql);			
		 	if(list.size()>0){
		 		JlFr jlFr = list.get(0);
		 		String hql1="from JlHjDj where state=0 and frNo='"+jlFr.getFrNo()+"'";
				List<JlHjDj> list1=ycs.searchAll(hql1);
			 	if(list1.size()>0){
			 		for(int i=0;i<list1.size();i++){
			 			JlHjDj jlHjDj = list1.get(0);
						Timestamp now=new Timestamp(System.currentTimeMillis());
						jlHjDj.setFrInTime(now);
						jlHjDj.setFrInUser(user.getUserNo());
						ycs.update(jlHjDj);
						if(jlHjDj.getFpFlag()==0){
							int result=ycs.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
							if(result==0){
								JSONArray json=JSONArray.fromObject(0);
								response.getWriter().println(json.toString());
							}else{
								JSONArray json=JSONArray.fromObject(-7);
								response.getWriter().println(json.toString());
							}
						}else{
							JSONArray json=JSONArray.fromObject(-8);
							response.getWriter().println(json.toString());
						}
			 		}
			 	}else{
			 		JSONArray json=JSONArray.fromObject(-4);
					response.getWriter().println(json.toString());
				}
		 	}else{
		 		JSONArray json=JSONArray.fromObject(-9);
				response.getWriter().println(json.toString());
		 	}
		 
			return null;
	}
	public ActionForward setCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
			String hql="from JlFr where frNo='"+jlHjDj.getFrNo()+"'";
			List<JlFr> list=ycs.searchAll(hql);
			if(list.size()>0){
				JlFr jlFr=(JlFr)list.get(0);
				request.setAttribute("jlFr", jlFr);
			}
			request.setAttribute("jlHjDj", jlHjDj);
			return mapping.findForward("setCard");
	}
	public ActionForward updateSaveFr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String frId = java.net.URLDecoder.decode((String)request.getParameter("frId"),"UTF-8");
			String djId = java.net.URLDecoder.decode((String)request.getParameter("djId"),"UTF-8");
			String frCard = java.net.URLDecoder.decode((String)request.getParameter("frCard"),"UTF-8");
			String hql="update JlFr set frCard=null where frCard='"+frCard+"'";
			Object[] obj={};
			ycs.updates(hql, obj);
			JlFr jlFr=(JlFr)ycs.findById(JlFr.class, Integer.parseInt(frId));
			jlFr.setFrCard(frCard);
			ycs.update(jlFr);
			JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(djId));
			Timestamp now=new Timestamp(System.currentTimeMillis());
			jlHjDj.setFrInTime(now);
			jlHjDj.setFrInUser(user.getUserNo());
			ycs.update(jlHjDj);
			if(jlHjDj.getFpFlag()==0){
				int result=ycs.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
				if(result==0){
					JSONArray json=JSONArray.fromObject(0);
					response.getWriter().println(json.toString());
				}else{
					JSONArray json=JSONArray.fromObject(1);
					response.getWriter().println(json.toString());
				}
			}else{
				JSONArray json=JSONArray.fromObject(2);
				response.getWriter().println(json.toString());
			}
			return  null;
	}
	public ActionForward selectRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String webId=request.getParameter("webId");
			JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(webId));
			if(jlHjDj.getFpFlag()==0){
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(1);
				response.getWriter().println(json.toString());
			}
			return null;
	}
	//跨楼层批量分配座位，查询当前三楼是否大于等于选择要分配的人数
	public ActionForward jquerSearch1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
	    	String str="select count(*) from  SYS_HJ_LINE  where ZW LIKE '%3楼%' AND Line_Type = 0 AND State = 1 AND HJID IS NULL AND HJState=0";    
	    	List list=ycs.searchAllBySql(str);			
			response.setContentType("text/json; charset=utf-8");   
			JSONArray json=JSONArray.fromObject(list);
			response.getWriter().println(json.toString());
			return null;
	}
	//跨楼层批量分配座位，查询当前四楼是否大于等于选择要分配的人数
	public ActionForward jquerSearch2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }   
	    	String str1="select count(*) from  SYS_HJ_LINE  where ZW LIKE '%4楼%' AND Line_Type = 0 AND State = 1 AND HJID IS NULL AND HJState=0";
	    	List list1=ycs.searchAllBySql(str1);
	    	System.out.println(list1.get(0));
			response.setContentType("text/json; charset=utf-8");   
			JSONArray json=JSONArray.fromObject(list1);
			response.getWriter().println(json.toString());
			return null;
	}
	//普通批量签到
	public ActionForward plQd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String strs=request.getParameter("str");
			String[] str=strs.split(";");
			List<JlHjDj> list=new ArrayList<JlHjDj>();
			for(int i=0;i<str.length;i++){
				String hjid=str[i];
				JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
				if(jlHjDj.getFpFlag()==0){
					list.add(jlHjDj);
				}
			}
			String returnName="plQd";
			if(list.size()==0){
				returnName="Allyjfp";
			}else{
				request.setAttribute("jlHjDj", list);
			}
			
			return mapping.findForward(returnName);
	}
	//批量签到到三楼（广东揭阳监狱）
	public ActionForward plQd1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String strs=request.getParameter("str");
			String[] str=strs.split(";");
			List<JlHjDj> list=new ArrayList<JlHjDj>();
			for(int i=0;i<str.length;i++){
				String hjid=str[i];
				JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
				if(jlHjDj.getFpFlag()==0){
					list.add(jlHjDj);
				}
			}
//			System.out.println("三楼");
			String returnName="plQd";
			if(list.size()==0){
				returnName="Allyjfp";
			}else{
				request.setAttribute("jlHjDj", list);
			}
			
			return mapping.findForward(returnName);
	}
	//批量签到到四楼（广东揭阳监狱）
	public ActionForward plQd2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String strs=request.getParameter("str");
			String[] str=strs.split(";");
			List<JlHjDj> list=new ArrayList<JlHjDj>();
			for(int i=0;i<str.length;i++){
				String hjid=str[i];
				JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(hjid));
				if(jlHjDj.getFpFlag()==0){
					list.add(jlHjDj);
				}
			}
//			System.out.println("四楼");
			String returnName="plQd1";
			if(list.size()==0){
				returnName="Allyjfp";
			}else{
				request.setAttribute("jlHjDj", list);
			}
			
			return mapping.findForward(returnName);
	}
	//普通批量分配座位
	public ActionForward plfpZw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
//			String hjids=request.getParameter("hjid");
//			String[] str=hjids.split(";");
//			String yjNum = java.net.URLDecoder.decode((String)request.getParameter("yjNum"),"UTF-8");
//			String hql="from JlYj where yjNum='"+yjNum.trim()+"'";
//			List<JlYj> list=ycs.searchAll(hql);
//			List<MessageVO> mv=new ArrayList<MessageVO>();
//			if(list.size()>0){
//				JlYj jlYj=list.get(0);
//				for(int i=0;i<str.length;i++){
//					JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(str[i]));
//					if(jlHjDj.getHjType()==1){
//						jlHjDj.setYjNo(yjNum.trim());
//						jlHjDj.setYjName(jlYj.getYjName());
//						Timestamp now=new Timestamp(System.currentTimeMillis());
//						jlHjDj.setFrInTime(now);
//						jlHjDj.setFrInUser(user.getUserNo());
//						ycs.update(jlHjDj);
//						if(jlHjDj.getFpFlag()==0){
//							int result=ycs.getTypeZw("set_ZW_new",jlHjDj.getFrNo(),jlHjDj.getHjType());
//							if(result==0){
//								MessageVO messageVO=new MessageVO();
//								messageVO.setFrName(jlHjDj.getFrName());
//								String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
//								if(lineNo.equals("-1")){
//									messageVO.setXx("分配座位成功                座位号为：无");
//								}else{
//									messageVO.setXx("分配座位成功                座位号为："+lineNo);
//								}
//								
//								mv.add(messageVO);
//							}else{
//								MessageVO messageVO=new MessageVO();
//								messageVO.setFrName(jlHjDj.getFrName());
//								messageVO.setXx("分配座位失败");
//								mv.add(messageVO);
//							}
//						}else{
//							MessageVO messageVO=new MessageVO();
//							messageVO.setFrName(jlHjDj.getFrName());
//							messageVO.setXx("座位已经被分配");
//							mv.add(messageVO);
//						}
//					}else{
//						jlHjDj.setYjNo(yjNum.trim());
//						jlHjDj.setYjName(jlYj.getYjName());
//						Timestamp now=new Timestamp(System.currentTimeMillis());
//						jlHjDj.setFrInTime(now);
//						jlHjDj.setFrInUser(user.getUserNo());
//						ycs.update(jlHjDj);
//						if(jlHjDj.getFpFlag()==0){
//							int result=ycs.getTypeZw("set_ZW_new",jlHjDj.getFrNo(),jlHjDj.getHjType());
//							if(result==0){
//								MessageVO messageVO=new MessageVO();
//								messageVO.setFrName(jlHjDj.getFrName());
//								String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
//								if(lineNo.equals("-1")){
//									messageVO.setXx("分配座位成功                座位号为：无");
//								}else{
//									messageVO.setXx("分配座位成功                座位号为："+lineNo);
//								}
//								mv.add(messageVO);
//							}else{
//								MessageVO messageVO=new MessageVO();
//								messageVO.setFrName(jlHjDj.getFrName());
//								messageVO.setXx("分配座位失败");
//								mv.add(messageVO);
//							}
//						}else{
//							MessageVO messageVO=new MessageVO();
//							messageVO.setFrName(jlHjDj.getFrName());
//							messageVO.setXx("座位已经被分配");
//							mv.add(messageVO);
//						}
//
//					}
//				
//				}
//				
//			}else{
//				MessageVO messageVO=new MessageVO();
//				messageVO.setFrName("警察信息不存在：");
//				messageVO.setXx("座位分配失败");
//				mv.add(messageVO);
//			}
//			response.setContentType("text/json; charset=utf-8"); 
//			JSONArray json=JSONArray.fromObject(mv);
//			response.getWriter().println(json.toString());
//			return null;
			String hjids=request.getParameter("hjid");
			String[] str=hjids.split(";");
			String yjNum = java.net.URLDecoder.decode((String)request.getParameter("yjNum"),"UTF-8");
			String hql="from JlYj where yjNum='"+yjNum.trim()+"'";
			List<JlYj> list=ycs.searchAll(hql);
			List<MessageVO> mv=new ArrayList<MessageVO>();
			if(list.size()>0){
				JlYj jlYj=list.get(0);
				for(int i=0;i<str.length;i++){
					JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(str[i]));
					if(jlHjDj.getHjType()==1){
						jlHjDj.setYjNo(yjNum.trim());
						jlHjDj.setYjName(jlYj.getYjName());
						Timestamp now=new Timestamp(System.currentTimeMillis());
						jlHjDj.setFrInTime(now);
						jlHjDj.setFrInUser(user.getUserNo());
						ycs.update(jlHjDj);
						if(jlHjDj.getFpFlag()==0){
							System.out.println("测试");
							int result=ycs.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
							if(result==0){
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
								if(lineNo.equals("-1")){
									messageVO.setXx("座位号：无");
								}else{
									messageVO.setXx("座位号："+lineNo);
								}
								
								mv.add(messageVO);
							}else{
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								messageVO.setXx("分配座位失败");
								mv.add(messageVO);
							}
						}else{
							MessageVO messageVO=new MessageVO();
							messageVO.setFrName(jlHjDj.getFrName());
							messageVO.setXx("座位已经被分配");
							mv.add(messageVO);
						}
					}else{
						jlHjDj.setYjNo(yjNum.trim());
						jlHjDj.setYjName(jlYj.getYjName());
						Timestamp now=new Timestamp(System.currentTimeMillis());
						jlHjDj.setFrInTime(now);
						jlHjDj.setFrInUser(user.getUserNo());
						ycs.update(jlHjDj);
						if(jlHjDj.getFpFlag()==0){
							int result=ycs.getTypeZw("set_ZW_ex_new1",jlHjDj.getFrNo(),jlHjDj.getHjType());
							if(result==0){
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
								if(lineNo.equals("-1")){
									messageVO.setXx("座位号：无");
								}else{
									messageVO.setXx("座位号："+lineNo);
								}
								mv.add(messageVO);
							}else{
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								messageVO.setXx("分配座位失败");
								mv.add(messageVO);
							}
						}else{
							MessageVO messageVO=new MessageVO();
							messageVO.setFrName(jlHjDj.getFrName());
							messageVO.setXx("座位已经被分配");
							mv.add(messageVO);
						}

					}
				
				}
				
			}else{
				MessageVO messageVO=new MessageVO();
				messageVO.setFrName("警察信息不存在：");
				messageVO.setXx("座位分配失败");
				mv.add(messageVO);
			}
			request.setAttribute("mv", mv);
			return mapping.findForward("fpMessage");
	}
	//批量分配座位到三楼（广州揭阳监狱）
	public ActionForward plfpZw1(ActionMapping mapping, ActionForm form,
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
			List<JlYj> list=ycs.searchAll(hql);
			List<MessageVO> mv=new ArrayList<MessageVO>();
			if(list.size()>0){
				JlYj jlYj=list.get(0);
				for(int i=0;i<str.length;i++){
					JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(str[i]));
					if(jlHjDj.getHjType()==1){
						jlHjDj.setYjNo(yjNum.trim());
						jlHjDj.setYjName(jlYj.getYjName());
						Timestamp now=new Timestamp(System.currentTimeMillis());
						jlHjDj.setFrInTime(now);
						jlHjDj.setFrInUser(user.getUserNo());
						ycs.update(jlHjDj);
						if(jlHjDj.getFpFlag()==0){
//							System.out.println("三楼1");
							int result=ycs.getTypeZw("set_ZW_ex_new2",jlHjDj.getFrNo(),jlHjDj.getHjType());
							if(result==0){
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
								if(lineNo.equals("-1")){
									messageVO.setXx("座位号：无");
								}else{
									messageVO.setXx("座位号："+lineNo);
								}
								
								mv.add(messageVO);
							}else{
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								messageVO.setXx("分配座位失败");
								mv.add(messageVO);
							}
						}else{
							MessageVO messageVO=new MessageVO();
							messageVO.setFrName(jlHjDj.getFrName());
							messageVO.setXx("座位已经被分配");
							mv.add(messageVO);
						}
					}else{
						jlHjDj.setYjNo(yjNum.trim());
						jlHjDj.setYjName(jlYj.getYjName());
						Timestamp now=new Timestamp(System.currentTimeMillis());
						jlHjDj.setFrInTime(now);
						jlHjDj.setFrInUser(user.getUserNo());
						ycs.update(jlHjDj);
						if(jlHjDj.getFpFlag()==0){
//							System.out.println("三楼2");
							int result=ycs.getTypeZw("set_ZW_ex_new2",jlHjDj.getFrNo(),jlHjDj.getHjType());
							if(result==0){
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
								if(lineNo.equals("-1")){
									messageVO.setXx("座位号：无");
								}else{
									messageVO.setXx("座位号："+lineNo);
								}
								mv.add(messageVO);
							}else{
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								messageVO.setXx("分配座位失败");
								mv.add(messageVO);
							}
						}else{
							MessageVO messageVO=new MessageVO();
							messageVO.setFrName(jlHjDj.getFrName());
							messageVO.setXx("座位已经被分配");
							mv.add(messageVO);
						}

					}
				
				}
				
			}else{
				MessageVO messageVO=new MessageVO();
				messageVO.setFrName("警察信息不存在：");
				messageVO.setXx("座位分配失败");
				mv.add(messageVO);
			}
			request.setAttribute("mv", mv);
			return mapping.findForward("fpMessage");
	}
	//批量分配座位到四楼（广州揭阳监狱）
	public ActionForward plfpZw2(ActionMapping mapping, ActionForm form,
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
			List<JlYj> list=ycs.searchAll(hql);
			List<MessageVO> mv=new ArrayList<MessageVO>();
			if(list.size()>0){
				JlYj jlYj=list.get(0);
				for(int i=0;i<str.length;i++){
					JlHjDj jlHjDj=(JlHjDj)ycs.findByIdLong(JlHjDj.class, Long.parseLong(str[i]));
					if(jlHjDj.getHjType()==1){
						jlHjDj.setYjNo(yjNum.trim());
						jlHjDj.setYjName(jlYj.getYjName());
						Timestamp now=new Timestamp(System.currentTimeMillis());
						jlHjDj.setFrInTime(now);
						jlHjDj.setFrInUser(user.getUserNo());
						ycs.update(jlHjDj);
						if(jlHjDj.getFpFlag()==0){
							System.out.println("四楼1");
							int result=ycs.getTypeZw("set_ZW_ex_new3",jlHjDj.getFrNo(),jlHjDj.getHjType());
							if(result==0){
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
								if(lineNo.equals("-1")){
									messageVO.setXx("座位号：无");
								}else{
									messageVO.setXx("座位号："+lineNo);
								}
								
								mv.add(messageVO);
							}else{
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								messageVO.setXx("分配座位失败");
								mv.add(messageVO);
							}
						}else{
							MessageVO messageVO=new MessageVO();
							messageVO.setFrName(jlHjDj.getFrName());
							messageVO.setXx("座位已经被分配");
							mv.add(messageVO);
						}
					}else{
						jlHjDj.setYjNo(yjNum.trim());
						jlHjDj.setYjName(jlYj.getYjName());
						Timestamp now=new Timestamp(System.currentTimeMillis());
						jlHjDj.setFrInTime(now);
						jlHjDj.setFrInUser(user.getUserNo());
						ycs.update(jlHjDj);
						if(jlHjDj.getFpFlag()==0){
							System.out.println("四楼2");
							int result=ycs.getTypeZw("set_ZW_ex_new3",jlHjDj.getFrNo(),jlHjDj.getHjType());
							if(result==0){
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								String lineNo=ycs.getZw1("get_zw1", jlHjDj.getHjid());
								if(lineNo.equals("-1")){
									messageVO.setXx("座位号：无");
								}else{
									messageVO.setXx("座位号："+lineNo);
								}
								mv.add(messageVO);
							}else{
								MessageVO messageVO=new MessageVO();
								messageVO.setFrName(jlHjDj.getFrName());
								messageVO.setXx("分配座位失败");
								mv.add(messageVO);
							}
						}else{
							MessageVO messageVO=new MessageVO();
							messageVO.setFrName(jlHjDj.getFrName());
							messageVO.setXx("座位已经被分配");
							mv.add(messageVO);
						}

					}
				
				}
				
			}else{
				MessageVO messageVO=new MessageVO();
				messageVO.setFrName("警察信息不存在：");
				messageVO.setXx("座位分配失败");
				mv.add(messageVO);
			}
			request.setAttribute("mv", mv);
			return mapping.findForward("fpMessage");
	}
	public ActionForward search2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		   	SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			StringBuffer hql=null;
			if(user.getIsSuper()==1){
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp from JL_HJ_DJ dj where dj.State=0 and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp from JL_HJ_DJ dj,JL_JQ jq where dj.State=0 and dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0 and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}else{
				hql=new StringBuffer("select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.Info_Wp from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.State=0 and dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' and (dj.DJ_Type=0 or dj.DJ_Type=2)");
			}
			YjComeForm yjComeForm=(YjComeForm)form;
			if(yjComeForm.getFrNo()!=null || yjComeForm.getFrName()!=null || yjComeForm.getHjIndex()!=null || yjComeForm.getJq()!=null){
				if(yjComeForm.getFrNo()!=null && !yjComeForm.getFrNo().trim().equals("")){
					hql.append(" and dj.FR_No='"+yjComeForm.getFrNo().trim()+"'");
				}
				if(yjComeForm.getFrName()!=null && !yjComeForm.getFrName().trim().equals("")){
					hql.append(" and dj.FR_Name like '%"+yjComeForm.getFrName().trim()+"%'");
				}
				if(yjComeForm.getHjIndex()!=null && !yjComeForm.getHjIndex().trim().equals("")){
					hql.append(" and dj.HJ_Index ='"+yjComeForm.getHjIndex().trim()+"'");
				}
				if(yjComeForm.getJq()!=null && !yjComeForm.getJq().equals("null")){
					hql.append(" and dj.JQ_No ='"+yjComeForm.getJq()+"'");
				}
				hql.append(" order by dj.FP_Flag,dj.JQ_No desc");
				List list=ycs.searchAllBySql(hql.toString());
				List<HjDjVO> list1=new ArrayList<HjDjVO>();
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
			    	if(obj1[23]!=null){
			    		hjDjVO.setInfoWp(obj1[23].toString());
			    	}else{
			    		hjDjVO.setInfoWp("0");
			    	}
			    	list1.add(hjDjVO);
			    }
				request.setAttribute("list1",list1);
			}
			
			String hql1="from JlJq";
			List<JlJq> list2=ycs.searchAll(hql1);
			request.setAttribute("jlJqList", list2);
			return mapping.findForward("yjComeMain");
	}
	
}
