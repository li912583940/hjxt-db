package com.slxt.rs.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.slxt.rs.form.HjReportForm;
import com.slxt.rs.model.JlJq;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.HjReportService;
import com.slxt.rs.util.Constant;
import com.slxt.rs.vo.HjReportVO;
import com.slxt.rs.vo.Page;

public class HjReportAction extends DispatchAction{
	private HjReportService  hrps;
	public void setHrps(HjReportService hrps) {
		this.hrps = hrps;
	}
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			HjReportForm hjReportForm=(HjReportForm)form;
			Page page=new Page();
			page.setPageNo(1);
			page.setPageSize(20);
			String beginTime="";
			String endTime="";
			if(hjReportForm.getCallTimeStart()!=null || hjReportForm.getCallTimeEnd()!=null){
				StringBuffer str=new StringBuffer("select yj.JQ_Name,yj.cishu,yj.countTime,kj.cishu1,kj.countTime1 from ");
				StringBuffer str1=new StringBuffer("(select jq.JQ_No AS jqNo, hj.JQ_No, jq.JQ_Name, COUNT(hj.JQ_No) AS cishu, ISNULL(SUM(dbo.f_get_callTimeLen(hj.Call_Time_Len)),0) as countTime from JL_JQ as jq left join (select * from JL_HJ_REC where 1=1 ");
				StringBuffer str2=new StringBuffer("(select jq.JQ_No AS jqNo, hj.JQ_No, jq.JQ_Name, COUNT(hj.JQ_No) AS cishu1, ISNULL(SUM(dbo.f_get_callTimeLen(hj.Call_Time_Len)) ,0) as countTime1 from JL_JQ as jq left join (select * from JL_HJ_REC where 1=1 ");
				if(hjReportForm.getCallTimeStart()!=null && !hjReportForm.getCallTimeStart().equals("")){
					str1.append("and Call_Time_Start>='"+hjReportForm.getCallTimeStart()+"' ");
					str2.append("and Call_Time_Start>='"+hjReportForm.getCallTimeStart()+"' ");
					beginTime=hjReportForm.getCallTimeStart();
				}
				if(hjReportForm.getCallTimeEnd()!=null && !hjReportForm.getCallTimeEnd().equals("")){
					str1.append("and Call_Time_Start<='"+hjReportForm.getCallTimeEnd()+"' ");
					str2.append("and Call_Time_Start<='"+hjReportForm.getCallTimeEnd()+"' ");
					endTime=hjReportForm.getCallTimeEnd();
				}
				str1.append("and HJ_Type = 1) as hj on jq.JQ_No=hj.JQ_No where 1=1 group by jq.JQ_No,hj.JQ_No,jq.JQ_Name)");
				str2.append("and HJ_Type = 2) as hj on jq.JQ_No=hj.JQ_No where 1=1 group by jq.JQ_No,hj.JQ_No,jq.JQ_Name)");
				str.append(str1.toString()).append(" as yj left join ").append(str2.toString()).append(" as kj on yj.jqNo = kj.jqNo");
				Object[] obj={};
				Map map=(Map)hrps.searchToPageBySql(str.toString(), page.getPageNo(), 20, obj);
				page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
				List list=(List)map.get(Constant.RESULTLIST);
				Iterator it=list.iterator();
				while(it.hasNext()){
					Object[] obj1=(Object[])it.next();
					HjReportVO hjReportVO=new HjReportVO();
					if(obj1[0]!=null && !obj1[0].toString().equals("")){
						hjReportVO.setJqName(obj1[0].toString());
					}
					if(obj1[1]!=null && !obj1[1].toString().equals("")){
						hjReportVO.setYjCount(obj1[1].toString());
					}
					if(obj1[2]!=null && !obj1[2].toString().equals("")){
						hjReportVO.setYjTime(obj1[2].toString());
					}
					if(obj1[3]!=null && !obj1[3].toString().equals("")){
						hjReportVO.setKjCount(obj1[3].toString());
					}
					if(obj1[4]!=null && !obj1[4].toString().equals("")){
						hjReportVO.setKjTime(obj1[4].toString());
					}
					page.getList().add(hjReportVO);
				}
			}else{
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String now = df1.format(date);
				beginTime=now.substring(0, 8)+"01 00:00:00";
				endTime=now;
			}
			String hql="from JlJq";
	 	 	List<JlJq> list=hrps.searchAll(hql);
			request.setAttribute("beginTime", beginTime);
			request.setAttribute("list", list);
			request.setAttribute("endTime", endTime);
			request.setAttribute("page", page);
			return mapping.findForward("hjReportMain");
	}
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			HjReportForm hjReportForm=(HjReportForm)form;
			Page page=new Page();
			page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			page.setPageSize(20);
			String beginTime="";
			String endTime="";
			if(hjReportForm.getCallTimeStart()!=null || hjReportForm.getCallTimeEnd()!=null){
				StringBuffer str=new StringBuffer("select yj.JQ_Name,yj.cishu,yj.countTime,kj.cishu1,kj.countTime1 from ");
				StringBuffer str1=new StringBuffer("(select jq.JQ_No AS jqNo, hj.JQ_No, jq.JQ_Name, COUNT(hj.JQ_No) AS cishu, ISNULL(SUM(dbo.f_get_callTimeLen(hj.Call_Time_Len)),0) as countTime from JL_JQ as jq left join (select * from JL_HJ_REC where 1=1 ");
				StringBuffer str2=new StringBuffer("(select jq.JQ_No AS jqNo, hj.JQ_No, jq.JQ_Name, COUNT(hj.JQ_No) AS cishu1, ISNULL(SUM(dbo.f_get_callTimeLen(hj.Call_Time_Len)) ,0) as countTime1 from JL_JQ as jq left join (select * from JL_HJ_REC where 1=1 ");
				if(hjReportForm.getCallTimeStart()!=null && !hjReportForm.getCallTimeStart().equals("")){
					str1.append("and Call_Time_Start>='"+hjReportForm.getCallTimeStart()+"' ");
					str2.append("and Call_Time_Start>='"+hjReportForm.getCallTimeStart()+"' ");
					beginTime=hjReportForm.getCallTimeStart();
				}
				if(hjReportForm.getCallTimeEnd()!=null && !hjReportForm.getCallTimeEnd().equals("")){
					str1.append("and Call_Time_Start<='"+hjReportForm.getCallTimeEnd()+"' ");
					str2.append("and Call_Time_Start<='"+hjReportForm.getCallTimeEnd()+"' ");
					endTime=hjReportForm.getCallTimeEnd();
				}
				str1.append("and HJ_Type = 1) as hj on jq.JQ_No=hj.JQ_No where 1=1 group by jq.JQ_No,hj.JQ_No,jq.JQ_Name)");
				str2.append("and HJ_Type = 2) as hj on jq.JQ_No=hj.JQ_No where 1=1 group by jq.JQ_No,hj.JQ_No,jq.JQ_Name)");
				str.append(str1.toString()).append(" as yj left join ").append(str2.toString()).append(" as kj on yj.jqNo = kj.jqNo");
				Object[] obj={};
				Map map=(Map)hrps.searchToPageBySql(str.toString(), page.getPageNo(), 20, obj);
				page.setRecordCount((Integer)map.get(Constant.ALLFILEDCOUNT));
				List list=(List)map.get(Constant.RESULTLIST);
				Iterator it=list.iterator();
				while(it.hasNext()){
					Object[] obj1=(Object[])it.next();
					HjReportVO hjReportVO=new HjReportVO();
					if(obj1[0]!=null && !obj1[0].toString().equals("")){
						hjReportVO.setJqName(obj1[0].toString());
					}
					if(obj1[1]!=null && !obj1[1].toString().equals("")){
						hjReportVO.setYjCount(obj1[1].toString());
					}
					if(obj1[2]!=null && !obj1[2].toString().equals("")){
						hjReportVO.setYjTime(obj1[2].toString());
					}
					if(obj1[3]!=null && !obj1[3].toString().equals("")){
						hjReportVO.setKjCount(obj1[3].toString());
					}
					if(obj1[4]!=null && !obj1[4].toString().equals("")){
						hjReportVO.setKjTime(obj1[4].toString());
					}
					page.getList().add(hjReportVO);
				}
			}else{
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String now = df1.format(date);
				beginTime=now.substring(0, 8)+"01 00:00:00";
				endTime=now;
			}
			request.setAttribute("beginTime", beginTime);
			request.setAttribute("endTime", endTime);
			request.setAttribute("page", page);
			return mapping.findForward("hjReportMain");
	}
	public ActionForward goCountMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String returnName="";
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			HjReportForm hjReportForm=(HjReportForm)form;
			String beginTime="";
			String endTime="";
			if(hjReportForm.getCallTimeStart()!=null && !hjReportForm.getCallTimeStart().equals("")){
				beginTime=hjReportForm.getCallTimeStart();
				request.setAttribute("beginTime", beginTime);
			}
			if(hjReportForm.getCallTimeEnd()!=null && !hjReportForm.getCallTimeEnd().equals("")){
				endTime=hjReportForm.getCallTimeEnd();
				request.setAttribute("endTime", endTime);
			}
			if(hjReportForm.getJq()!=null && !hjReportForm.getJq().equals("null")){
				request.setAttribute("jq", hjReportForm.getJq());
				returnName="jqcountMap";
			}else{
				returnName="countMap";
			}
			return mapping.findForward(returnName);
	}
	public ActionForward whjfrMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			HjReportForm hjReportForm=(HjReportForm)form;
			String beginTime="";
			String endTime="";
			if(hjReportForm.getCallTimeStart()!=null && !hjReportForm.getCallTimeStart().equals("")){
				beginTime=hjReportForm.getCallTimeStart();
				request.setAttribute("beginTime", beginTime);
			}
			if(hjReportForm.getCallTimeEnd()!=null && !hjReportForm.getCallTimeEnd().equals("")){
				endTime=hjReportForm.getCallTimeEnd();
				request.setAttribute("endTime", endTime);
			}
			if(hjReportForm.getJq()!=null && !hjReportForm.getJq().equals("null")){
				request.setAttribute("jq", hjReportForm.getJq());
			}
			return mapping.findForward("whjfrMap");
	}
}
