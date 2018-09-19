package com.slxt.rs.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.slxt.rs.model.JlFrWpPk;
import com.slxt.rs.model.JlHjDj;
import com.slxt.rs.model.SysParam;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.WpDjService;
import com.slxt.rs.vo.HjDjVO;
import com.slxt.rs.vo.Wp;

public class WpDjAction extends DispatchAction{
	private WpDjService wds;

	public void setWds(WpDjService wds) {
		this.wds = wds;
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
			List<SysParam> list10=wds.searchAll(hql10);
			if(list10.size()>0){
				SysParam sysParam=list10.get(0);
				if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
					hql11=" and (dj.DJ_Type=0 or dj.DJ_Type=2)";
				}
			}
			if(user.getIsSuper()==1){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time from JL_HJ_DJ dj where dj.State=0";
				hql+=hql11;
				hql+=" order by JY,FP_TZFR_Flag,HJ_Index desc";
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time from JL_HJ_DJ dj,JL_JQ jq where dj.State=0 and dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0";
				hql+=hql11;
				hql+=" order by dj.JY,dj.FP_TZFR_Flag,dj.HJ_Index desc";
			}else{
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.State=0";
				hql+=hql11;
				hql+=" and dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' order by dj.JY,dj.FP_TZFR_Flag,dj.HJ_Index desc";
			}
			List list=wds.searchAllBySql(hql);
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
		    	list1.add(hjDjVO);
		    }
			request.setAttribute("list1",list1);
			String hql4="from SysParam where paramName='HJ_Client'";
			List<SysParam> list4=wds.searchAll(hql4);
			if(list4.size()>0){
				SysParam sysParam1=(SysParam)list4.get(0);
				request.setAttribute("hJClient1", sysParam1);
			}
			return mapping.findForward("wpdjMain");
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
			String hql10="from SysParam where paramName='HJ_Client1'";
			List<SysParam> list10=wds.searchAll(hql10);
			if(list10.size()>0){
				SysParam sysParam=list10.get(0);
				if(sysParam.getParamData4()!=null && sysParam.getParamData4().equals("1")){
					hql11=" and (dj.DJ_Type=0 or dj.DJ_Type=2)";
				}
			}
			if(user.getIsSuper()==1){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time from JL_HJ_DJ dj where dj.State=0";
				hql+=hql11;
				hql+=" order by JY,FP_TZFR_Flag,HJ_Index desc";
			}else if(user.getIsSuper()==0 && user.getGroupNo().equals("Admin")){
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time from JL_HJ_DJ dj,JL_JQ jq where dj.State=0 and dj.JQ_Name=jq.JQ_Name and jq.Is_Ts=0";
				hql+=hql11;
				hql+=" order by dj.JY,dj.FP_TZFR_Flag,dj.HJ_Index desc";
			}else{
				hql="select dj.HJID,dj.JY,dj.JQ_Name,dj.FR_No,dj.FR_Name,dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,dj.HJ_Time,dj.HJ_Info,dj.HJ_Type,dj.Monitor_Flag,dj.FP_Flag,dbo.get_ck(dj.FP_Line_No,dj.JY) as zw,dj.DJ_User,dj.DJ_Time,dj.HJ_Index,dj.FP_TZFR_Flag,dj.Info_Wp,dj.QS_In_Time from JL_HJ_DJ dj,JL_JQ jq,SYS_USER_JQ suj where dj.State=0";
				hql+=hql11;
				hql+=" and dj.JQ_Name=jq.JQ_Name and jq.JQ_No=suj.JQ_No and suj.Group_No='"+user.getGroupNo()+"' order by dj.JY,dj.FP_TZFR_Flag,dj.HJ_Index desc";
			}
			List list=wds.searchAllBySql(hql);
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
		    	list1.add(hjDjVO);
		    }
			response.setContentType("text/json; charset=utf-8"); 
			JSONArray json=JSONArray.fromObject(list1);
			response.getWriter().println(json.toString());
			return null;
	}
	public ActionForward updateWpdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			JlHjDj jlHjDj=(JlHjDj)wds.findById(JlHjDj.class, Long.parseLong(hjid));
			String sql="select wp.wpNo,wp.wpName, wp.wpUnit, ISNULL(pk.wpCount, 0) from JL_FR_WP wp left join (select * from JL_FR_WP_PK where hjid="+hjid+") as pk on wp.wpNo=pk.wpNo";
			List list=wds.searchAllBySql(sql);
			List<Wp> list1=new ArrayList<Wp>();
			String info="";
			for(int i=0;i<list.size();i++){
				Object[] obj=(Object[])list.get(i);
				Wp wp=new Wp();
				
				if(i==0){
					info+=obj[0].toString();
				}else{
					info+=","+obj[0].toString();
				}
				
				wp.setWpNo(obj[0].toString());
				wp.setWpName(obj[1].toString());
				wp.setWpUnit(obj[2].toString());
				wp.setWpCount(obj[3].toString());
				list1.add(wp);
			}
			
			request.setAttribute("jlHjDj", jlHjDj);
			request.setAttribute("list1", list1);
			request.setAttribute("info", info);
			return mapping.findForward("updateWpdj");
	}
	public ActionForward updateSaveWpdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				JSONArray json=JSONArray.fromObject(0);
				response.getWriter().println(json.toString());
			}else{
				String hjid=request.getParameter("hjid");
				String wpNo=request.getParameter("wpNo");
				String[] args=wpNo.split(",");
				for(int i=0;i<args.length;i++){
					String arg=request.getParameter("count"+args[i]);
					String sql="select * from JL_FR_WP_PK where hjid="+hjid+" and wpNo="+args[i];
					List list=wds.searchAllBySql(sql);
					if(list.size()>0){
						if(Integer.parseInt(arg)>0){
							String hql="update JlFrWpPk set wpCount="+Integer.parseInt(arg)+" where hjid="+hjid+" and wpNo='"+args[i]+"'";
							Object[] obj={};
							wds.updates(hql, obj);
						}else{
							String hql="delete from JlFrWpPk where hjid="+hjid+" and wpNo='"+args[i]+"'";
							Object[] obj={};
							wds.deleteByHql(hql, obj);
						}
					}else{
						if(Integer.parseInt(arg)>0){
							JlFrWpPk jlFrWpPk=new JlFrWpPk();
							jlFrWpPk.setHjid(Long.parseLong(hjid));
							jlFrWpPk.setWpCount(Integer.parseInt(arg));
							jlFrWpPk.setWpNo(args[i]);
							wds.save(jlFrWpPk);
						}
					}
				}
				String sql="select * from JL_FR_WP_PK where hjid="+hjid;
				List list=wds.searchAllBySql(sql);
				if(list.size()>0){
					JlHjDj jlHjDj=(JlHjDj)wds.findById(JlHjDj.class, Long.parseLong(hjid));
					jlHjDj.setInfoWp(1);
					jlHjDj.setWpDjr(user.getUserName()+"["+user.getUserNo()+"]");
					jlHjDj.setWpDjTime(new Timestamp(System.currentTimeMillis()));
					wds.update(jlHjDj);
					JSONArray json=JSONArray.fromObject(1);
					response.getWriter().println(json.toString());
				}else{
					JlHjDj jlHjDj=(JlHjDj)wds.findById(JlHjDj.class, Long.parseLong(hjid));
					jlHjDj.setInfoWp(0);
					jlHjDj.setWpDjr(null);
					jlHjDj.setWpDjTime(null);
					wds.update(jlHjDj);
					JSONArray json=JSONArray.fromObject(2);
					response.getWriter().println(json.toString());
				}
			}
			return null;
	}
	public ActionForward printWp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String hjid=request.getParameter("hjid");
			JlHjDj jlHjDj=(JlHjDj)wds.findById(JlHjDj.class, Long.parseLong(hjid));
			String sql="select wp.wpNo,wp.wpName, wp.wpUnit, ISNULL(pk.wpCount, 0) from JL_FR_WP wp ,JL_FR_WP_PK pk where wp.wpNo=pk.wpNo and pk.hjid="+hjid;
			List list=wds.searchAllBySql(sql);
			List<Wp> list1=new ArrayList<Wp>();
			String info="";
			for(int i=0;i<list.size();i++){
				Object[] obj=(Object[])list.get(i);
				Wp wp=new Wp();
				
				if(i==0){
					info+=obj[0].toString();
				}else{
					info+=","+obj[0].toString();
				}
				
				wp.setWpNo(obj[0].toString());
				wp.setWpName(obj[1].toString());
				wp.setWpUnit(obj[2].toString());
				wp.setWpCount(obj[3].toString());
				list1.add(wp);
			}
			request.setAttribute("list1", list1);
			request.setAttribute("jlHjDj", jlHjDj);
			return mapping.findForward("printView");
	}
}
