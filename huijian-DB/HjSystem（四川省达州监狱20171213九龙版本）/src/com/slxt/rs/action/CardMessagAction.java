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

import com.slxt.rs.form.CardMessageForm;
import com.slxt.rs.model.JlJq;
import com.slxt.rs.svc.CardMessageService;
import com.slxt.rs.util.Constant;
import com.slxt.rs.vo.CardMessageVO;
import com.slxt.rs.vo.Page;

public class CardMessagAction extends DispatchAction{
	private CardMessageService cms;

	public void setCms(CardMessageService cms) {
		this.cms = cms;
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			CardMessageForm cmf=(CardMessageForm) form;
			Page page = new Page();
			page.setPageNo(1);
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			if (cmf.getCallTimeEnd() != null || cmf.getCallTimeStart() != null  ||  (cmf.getJqNo() != null && !cmf.getJqNo().equals("null")) || cmf.getQsName() != null ||  cmf.getFrNo() != null || cmf.getFrName() != null) {
				StringBuffer str=new StringBuffer("select jl.sj,jl.CardNum,jl.FR_No,jl.FR_Name,jl.QS_Name,jq.JQ_Name,jl.WebID from JL_TRADE_CARD_INFO jl,JL_JQ jq where jl.JQ_Name=jq.JQ_No");
				if (cmf.getCallTimeStart() != null && !cmf.getCallTimeStart().trim().equals("")) {
					str.append(" and jl.SJ>='"+ cmf.getCallTimeStart() + "'");
					todayBegin = cmf.getCallTimeStart();
				}
				if (cmf.getCallTimeEnd() != null && !cmf.getCallTimeEnd().trim().equals("")) {
					str.append(" and jl.SJ<='"+ cmf.getCallTimeEnd() + "'");
					todayEnd = cmf.getCallTimeEnd();
				}
				if (cmf.getFrNo() != null && !cmf.getFrNo().equals("")) {
					str.append(" and jl.FR_No='" + cmf.getFrNo() + "'");
				}
				if (cmf.getFrName() != null && !cmf.getFrName().trim().equals("")) {
					str.append(" and jl.FR_Name like '%" + cmf.getFrName() + "%'");
				}
				
				if (cmf.getJqNo() != null && !cmf.getJqNo().equals("null")) {
					str.append(" and jl.JQ_Name='" + cmf.getJqNo() + "'");
				}
				if (cmf.getQsName() != null && !cmf.getQsName().equals("")) {
					str.append(" and jl.QS_Name like '%" + cmf.getQsName() + "%'");
				}
				Object[] obj={};
				Map map=cms.searchToPageBySql(str.toString(), page.getPageNo(), Constant.PAGESIZE, obj);
				page.setRecordCount((Integer) map.get(Constant.ALLFILEDCOUNT));
				List list2 = (List) map.get(Constant.RESULTLIST);
				page.setPageSize(Constant.PAGESIZE);
				Iterator it = list2.iterator();
				while (it.hasNext()) {
						Object[] obj1=(Object[])it.next();
						CardMessageVO cardMessageVO=new CardMessageVO();
						cardMessageVO.setSj(obj1[0].toString().substring(0, 19));
						cardMessageVO.setCardNum(obj1[1].toString());
						cardMessageVO.setFrNo(obj1[2].toString());
						cardMessageVO.setFrName(obj1[3].toString());
						cardMessageVO.setQsName(obj1[4].toString());
						cardMessageVO.setJqName(obj1[5].toString());
						cardMessageVO.setWebId(obj1[6].toString());
						page.getList().add(cardMessageVO);
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
			List<JlJq> list1 = cms.searchAll(hql1);
			request.setAttribute("jlJqList", list1);
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("cardMessageMain");
	}
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			CardMessageForm cmf=(CardMessageForm) form;
			Page page = new Page();
			page.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = df1.format(date);
			String todayBegin = "";
			String todayEnd = "";
			if (cmf.getCallTimeEnd() != null || cmf.getCallTimeStart() != null  ||  (cmf.getJqNo() != null && !cmf.getJqNo().equals("null")) || cmf.getQsName() != null ||  cmf.getFrNo() != null || cmf.getFrName() != null) {
				StringBuffer str=new StringBuffer("select jl.sj,jl.CardNum,jl.FR_No,jl.FR_Name,jl.QS_Name,jq.JQ_Name,jl.WebID from JL_TRADE_CARD_INFO jl,JL_JQ jq where jl.JQ_Name=jq.JQ_No");
				if (cmf.getCallTimeStart() != null && !cmf.getCallTimeStart().trim().equals("")) {
					str.append(" and jl.SJ>='"+ cmf.getCallTimeStart() + "'");
					todayBegin = cmf.getCallTimeStart();
				}
				if (cmf.getCallTimeEnd() != null && !cmf.getCallTimeEnd().trim().equals("")) {
					str.append(" and jl.SJ<='"+ cmf.getCallTimeEnd() + "'");
					todayEnd = cmf.getCallTimeEnd();
				}
				if (cmf.getFrNo() != null && !cmf.getFrNo().equals("")) {
					str.append(" and jl.FR_No='" + cmf.getFrNo() + "'");
				}
				if (cmf.getFrName() != null && !cmf.getFrName().trim().equals("")) {
					str.append(" and jl.FR_Name like '%" + cmf.getFrName() + "%'");
				}
				
				if (cmf.getJqNo() != null && !cmf.getJqNo().equals("null")) {
					str.append(" and jl.JQ_Name='" + cmf.getJqNo() + "'");
				}
				if (cmf.getQsName() != null && !cmf.getQsName().equals("")) {
					str.append(" and jl.QS_Name like '%" + cmf.getQsName() + "%'");
				}
				Object[] obj={};
				Map map=cms.searchToPageBySql(str.toString(), page.getPageNo(), Constant.PAGESIZE, obj);
				page.setRecordCount((Integer) map.get(Constant.ALLFILEDCOUNT));
				List list2 = (List) map.get(Constant.RESULTLIST);
				page.setPageSize(Constant.PAGESIZE);
				Iterator it = list2.iterator();
				while (it.hasNext()) {
						Object[] obj1=(Object[])it.next();
						CardMessageVO cardMessageVO=new CardMessageVO();
						cardMessageVO.setSj(obj1[0].toString().substring(0, 19));
						cardMessageVO.setCardNum(obj1[1].toString());
						cardMessageVO.setFrNo(obj1[2].toString());
						cardMessageVO.setFrName(obj1[3].toString());
						cardMessageVO.setQsName(obj1[4].toString());
						cardMessageVO.setJqName(obj1[5].toString());
						cardMessageVO.setWebId(obj1[6].toString());
						page.getList().add(cardMessageVO);
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
			List<JlJq> list1 = cms.searchAll(hql1);
			request.setAttribute("jlJqList", list1);
			request.setAttribute("todayBegin", todayBegin);
			request.setAttribute("todayEnd", todayEnd);
			request.setAttribute("page", page);
			return mapping.findForward("cardMessageMain");
	}
}
