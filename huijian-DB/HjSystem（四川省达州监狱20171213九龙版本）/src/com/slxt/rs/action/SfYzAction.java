package com.slxt.rs.action;


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

import sun.misc.BASE64Encoder;
import com.slxt.rs.model.JlFr;
import com.slxt.rs.model.JlJb;
import com.slxt.rs.model.JlJq;
import com.slxt.rs.model.JlQs;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.SfYzService;
import com.slxt.rs.vo.Fr;
import com.slxt.rs.vo.FrQs;
import com.slxt.rs.vo.FrSfVO;
import com.slxt.rs.vo.SfyzVO;
import com.slxt.rs.vo.SfyzVO1;
import com.slxt.rs.vo.SfyzVO2;
import com.slxt.rs.vo.SfyzVO3;
public class SfYzAction extends DispatchAction{
	private SfYzService sys;

	public void setSys(SfYzService sys) {
		this.sys = sys;
	}
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			return mapping.findForward("sfYzMain");
	}
	public ActionForward jquerSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String qsName = request.getParameter("qsName").trim();
		String qsSfz = request.getParameter("qsSfz").trim();
		System.out.println("qsName:"+qsName);
		System.out.println("qsSfz:"+qsSfz);
		BASE64Encoder encoder = new BASE64Encoder();
		String hql="select  dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,qs.gx,dj.FR_No,dj.FR_Name,dj.JQ_Name,dbo.get_ck(dj.FP_Line_No,dj.jy) as zw,qs.QS_Name,dj.HJID,dj.DJ_Time from JL_HJ_DJ dj,JL_QS qs where dj.State=0 and dj.FR_No=qs.FR_No  ";
		if(qsSfz!=null && !qsSfz.trim().equals("")){
			qsSfz=java.net.URLDecoder.decode(qsSfz,"UTF-8");
			hql+=" and qs.QS_SFZ='"+qsSfz+"'";
		}
		if(qsName!=null && !qsName.trim().equals("")){
			qsName=java.net.URLDecoder.decode(qsName,"UTF-8");
			hql+=" and (dj.QS_Info1 like '%"+qsName+"' or dj.QS_Info2 like '%"+qsName+"' or dj.QS_Info3 like '%"+qsName+"'or dj.QS_Info4 like '%"+qsName+"'or dj.QS_Info5 like '%"+qsName+"'or dj.QS_Info6 like '%"+qsName+"'or dj.QS_Info7 like '%"+qsName+"'or dj.QS_Info8 like '%"+qsName+"'or dj.QS_Info9 like '%"+qsName+"')";
		}
		List list=sys.searchAllBySql(hql);
		System.out.println("list:"+list.size());
		List<FrQs> qslist = new ArrayList<FrQs>();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				
				boolean flag=false;
				Object[] obj1=(Object[])list.get(i);
				
				String gx="";
				String qsName2="";
				if(obj1[9]!=null && !obj1[9].toString().equals("")){
					gx=obj1[9].toString();
				}
				if(obj1[14]!=null && !obj1[14].toString().equals("")){
					qsName2=obj1[14].toString();
				}
				if(obj1[0]!=null && !obj1[0].toString().equals("")){
					String gx1=obj1[0].toString().substring(1, obj1[0].toString().indexOf("]"));
					String qsName1=obj1[0].toString().substring(obj1[0].toString().indexOf("]")+1);
					if(gx.equals(gx1)  && qsName2.equals(qsName1)){
						flag=true;
						
						
					}
					String sql ="select XB,QS_SFZ,WebID,DZ,ZP from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
					List list2 = sys.searchAllBySql(sql);
					if(list2.size()>0){
						Object[] obj2 = (Object[]) list2.get(0);
						FrQs frqs = new FrQs();
						frqs.setQsName(qsName1);
						frqs.setGx(gx1);
						if(obj2[0]!=null && !obj2[0].toString().equals("")){
							frqs.setXb(obj2[0].toString());
						}
						if(obj2[1]!=null && !obj2[1].toString().equals("")){
							frqs.setQsSfz(obj2[1].toString());
						}
						if(obj2[2]!=null && !obj2[2].toString().equals("")){
							frqs.setWebId(Integer.parseInt(obj2[2].toString()));
						}
						if(obj2[3]!=null && !obj2[3].toString().equals("")){
							frqs.setDz(obj2[3].toString());
						}
						if(obj2[4]!=null && !obj2[4].toString().equals("")){
							frqs.setZpState("1");
						}else{
							frqs.setZpState("0");
						}
						qslist.add(frqs);
					}
				}
				if(obj1[1]!=null && !obj1[1].toString().equals("")){
					String gx1=obj1[1].toString().substring(1, obj1[1].toString().indexOf("]"));
					String qsName1=obj1[1].toString().substring(obj1[1].toString().indexOf("]")+1);
					if(gx.equals(gx1)  && qsName2.equals(qsName1)){
						flag=true;
						
					}
					String sql ="select XB,QS_SFZ,WebID,DZ,ZP  from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
					List list2 = sys.searchAllBySql(sql);
					if(list2.size()>0){
						Object[] obj2 = (Object[]) list2.get(0);
						FrQs frqs = new FrQs();
						frqs.setQsName(qsName1);
						frqs.setGx(gx1);
						if(obj2[0]!=null && !obj2[0].toString().equals("")){
							frqs.setXb(obj2[0].toString());
						}
						if(obj2[1]!=null && !obj2[1].toString().equals("")){
							frqs.setQsSfz(obj2[1].toString());
						}
						if(obj2[2]!=null && !obj2[2].toString().equals("")){
							frqs.setWebId(Integer.parseInt(obj2[2].toString()));
						}
						if(obj2[3]!=null && !obj2[3].toString().equals("")){
							frqs.setDz(obj2[3].toString());
						}
						if(obj2[4]!=null && !obj2[4].toString().equals("")){
							frqs.setZpState("1");
						}else{
							frqs.setZpState("0");
						}
						qslist.add(frqs);
					}
				}
				if(obj1[2]!=null && !obj1[2].toString().equals("")){
					String gx1=obj1[2].toString().substring(1, obj1[2].toString().indexOf("]"));
					String qsName1=obj1[2].toString().substring(obj1[2].toString().indexOf("]")+1);
					if(gx.equals(gx1)  && qsName2.equals(qsName1)){
						flag=true;
					
					}
					String sql ="select XB,QS_SFZ,WebID,DZ,ZP  from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
					List list2 = sys.searchAllBySql(sql);
					if(list2.size()>0){
						Object[] obj2 = (Object[]) list2.get(0);
						FrQs frqs = new FrQs();
						frqs.setQsName(qsName1);
						frqs.setGx(gx1);
						if(obj2[0]!=null && !obj2[0].toString().equals("")){
							frqs.setXb(obj2[0].toString());
						}
						if(obj2[1]!=null && !obj2[1].toString().equals("")){
							frqs.setQsSfz(obj2[1].toString());
						}
						if(obj2[2]!=null && !obj2[2].toString().equals("")){
							frqs.setWebId(Integer.parseInt(obj2[2].toString()));
						}
						if(obj2[3]!=null && !obj2[3].toString().equals("")){
							frqs.setDz(obj2[3].toString());
						}
						if(obj2[4]!=null && !obj2[4].toString().equals("")){
							frqs.setZpState("1");
						}else{
							frqs.setZpState("0");
						}
						qslist.add(frqs);
					}
				}
				if(obj1[3]!=null && !obj1[3].toString().equals("")){
					String gx1=obj1[3].toString().substring(1, obj1[3].toString().indexOf("]"));
					String qsName1=obj1[3].toString().substring(obj1[3].toString().indexOf("]")+1);
					if(gx.equals(gx1)  && qsName2.equals(qsName1)){
						flag=true;
					}
					String sql ="select XB,QS_SFZ,WebID,DZ,ZP  from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
					List list2 = sys.searchAllBySql(sql);
					if(list2.size()>0){
						Object[] obj2 = (Object[]) list2.get(0);
						FrQs frqs = new FrQs();
						frqs.setQsName(qsName1);
						frqs.setGx(gx1);
						if(obj2[0]!=null && !obj2[0].toString().equals("")){
							frqs.setXb(obj2[0].toString());
						}
						if(obj2[1]!=null && !obj2[1].toString().equals("")){
							frqs.setQsSfz(obj2[1].toString());
						}
						if(obj2[2]!=null && !obj2[2].toString().equals("")){
							frqs.setWebId(Integer.parseInt(obj2[2].toString()));
						}
						if(obj2[3]!=null && !obj2[3].toString().equals("")){
							frqs.setDz(obj2[3].toString());
						}
						if(obj2[4]!=null && !obj2[4].toString().equals("")){
							frqs.setZpState("1");
						}else{
							frqs.setZpState("0");
						}
						qslist.add(frqs);
					}
				}
				if(obj1[4]!=null && !obj1[4].toString().equals("")){
					String gx1=obj1[4].toString().substring(1, obj1[4].toString().indexOf("]"));
					String qsName1=obj1[4].toString().substring(obj1[4].toString().indexOf("]")+1);
					if(gx.equals(gx1)  && qsName2.equals(qsName1)){
						flag=true;
						String sql ="select XB,QS_SFZ,WebID,DZ,ZP  from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
						List list2 = sys.searchAllBySql(sql);
						if(list2.size()>0){
							Object[] obj2 = (Object[]) list2.get(0);
							FrQs frqs = new FrQs();
							frqs.setQsName(qsName1);
							frqs.setGx(gx1);
							if(obj2[0]!=null && !obj2[0].toString().equals("")){
								frqs.setXb(obj2[0].toString());
							}
							if(obj2[1]!=null && !obj2[1].toString().equals("")){
								frqs.setQsSfz(obj2[1].toString());
							}
							if(obj2[2]!=null && !obj2[2].toString().equals("")){
								frqs.setWebId(Integer.parseInt(obj2[2].toString()));
							}
							if(obj2[3]!=null && !obj2[3].toString().equals("")){
								frqs.setDz(obj2[3].toString());
							}
							if(obj2[4]!=null && !obj2[4].toString().equals("")){
								frqs.setZpState("1");
							}else{
								frqs.setZpState("0");
							}
							qslist.add(frqs);
						}
					}
				}
				if(obj1[5]!=null && !obj1[5].toString().equals("")){
					String gx1=obj1[5].toString().substring(1, obj1[5].toString().indexOf("]"));
					String qsName1=obj1[5].toString().substring(obj1[5].toString().indexOf("]")+1);
					if(gx.equals(gx1)  && qsName2.equals(qsName1)){
						flag=true;
						String sql ="select XB,QS_SFZ,WebID,DZ,ZP  from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
						List list2 = sys.searchAllBySql(sql);
						if(list2.size()>0){
							Object[] obj2 = (Object[]) list2.get(0);
							FrQs frqs = new FrQs();
							frqs.setQsName(qsName1);
							frqs.setGx(gx1);
							if(obj2[0]!=null && !obj2[0].toString().equals("")){
								frqs.setXb(obj2[0].toString());
							}
							if(obj2[1]!=null && !obj2[1].toString().equals("")){
								frqs.setQsSfz(obj2[1].toString());
							}
							if(obj2[2]!=null && !obj2[2].toString().equals("")){
								frqs.setWebId(Integer.parseInt(obj2[2].toString()));
							}
							if(obj2[3]!=null && !obj2[3].toString().equals("")){
								frqs.setDz(obj2[3].toString());
							}
							if(obj2[4]!=null && !obj2[4].toString().equals("")){
								frqs.setZpState("1");
							}else{
								frqs.setZpState("0");
							}
							qslist.add(frqs);
						}
					}
				}
				if(obj1[6]!=null && !obj1[6].toString().equals("")){
					String gx1=obj1[6].toString().substring(1, obj1[6].toString().indexOf("]"));
					String qsName1=obj1[6].toString().substring(obj1[6].toString().indexOf("]")+1);
					if(gx.equals(gx1)  && qsName2.equals(qsName1)){
						flag=true;
						String sql ="select XB,QS_SFZ,WebID,DZ,ZP  from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
						List list2 = sys.searchAllBySql(sql);
						if(list2.size()>0){
							Object[] obj2 = (Object[]) list2.get(0);
							FrQs frqs = new FrQs();
							frqs.setQsName(qsName1);
							frqs.setGx(gx1);
							if(obj2[0]!=null && !obj2[0].toString().equals("")){
								frqs.setXb(obj2[0].toString());
							}
							if(obj2[1]!=null && !obj2[1].toString().equals("")){
								frqs.setQsSfz(obj2[1].toString());
							}
							if(obj2[2]!=null && !obj2[2].toString().equals("")){
								frqs.setWebId(Integer.parseInt(obj2[2].toString()));
							}
							if(obj2[3]!=null && !obj2[3].toString().equals("")){
								frqs.setDz(obj2[3].toString());
							}
							if(obj2[4]!=null && !obj2[4].toString().equals("")){
								frqs.setZpState("1");
							}else{
								frqs.setZpState("0");
							}
							qslist.add(frqs);
						}
					}
				}
				if(obj1[7]!=null && !obj1[7].toString().equals("")){
					String gx1=obj1[7].toString().substring(1, obj1[7].toString().indexOf("]"));
					String qsName1=obj1[7].toString().substring(obj1[7].toString().indexOf("]")+1);
					if(gx.equals(gx1)  && qsName2.equals(qsName1)){
						flag=true;
						String sql ="select XB,QS_SFZ,WebID,DZ,ZP  from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
						List list2 = sys.searchAllBySql(sql);
						if(list2.size()>0){
							Object[] obj2 = (Object[]) list2.get(0);
							FrQs frqs = new FrQs();
							frqs.setQsName(qsName1);
							frqs.setGx(gx1);
							if(obj2[0]!=null && !obj2[0].toString().equals("")){
								frqs.setXb(obj2[0].toString());
							}
							if(obj2[1]!=null && !obj2[1].toString().equals("")){
								frqs.setQsSfz(obj2[1].toString());
							}
							if(obj2[2]!=null && !obj2[2].toString().equals("")){
								frqs.setWebId(Integer.parseInt(obj2[2].toString()));
							}
							if(obj2[3]!=null && !obj2[3].toString().equals("")){
								frqs.setDz(obj2[3].toString());
							}
							if(obj2[4]!=null && !obj2[4].toString().equals("")){
								frqs.setZpState("1");
							}else{
								frqs.setZpState("0");
							}
							qslist.add(frqs);
						}
					}
				}
				if(obj1[8]!=null && !obj1[8].toString().equals("")){
					String gx1=obj1[8].toString().substring(1, obj1[8].toString().indexOf("]"));
					String qsName1=obj1[8].toString().substring(obj1[8].toString().indexOf("]")+1);
					if(gx.equals(gx1)  && qsName2.equals(qsName1)){
						flag=true;
						String sql ="select XB,QS_SFZ,WebID,DZ,ZP  from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
						List list2 = sys.searchAllBySql(sql);
						if(list2.size()>0){
							Object[] obj2 = (Object[]) list2.get(0);
							FrQs frqs = new FrQs();
							frqs.setQsName(qsName1);
							frqs.setGx(gx1);
							if(obj2[0]!=null && !obj2[0].toString().equals("")){
								frqs.setXb(obj2[0].toString());
							}
							if(obj2[1]!=null && !obj2[1].toString().equals("")){
								frqs.setQsSfz(obj2[1].toString());
							}
							if(obj2[2]!=null && !obj2[2].toString().equals("")){
								frqs.setWebId(Integer.parseInt(obj2[2].toString()));
							}
							if(obj2[3]!=null && !obj2[3].toString().equals("")){
								frqs.setDz(obj2[3].toString());
							}
							if(obj2[4]!=null && !obj2[4].toString().equals("")){
								frqs.setZpState("1");
							}else{
								frqs.setZpState("0");
							}
							qslist.add(frqs);
						}
					}
				}
				if(flag){
					FrQs frqs = new FrQs();
					frqs.setFrNo(obj1[10].toString());
					frqs.setFrName(obj1[11].toString());
					frqs.setJqName(obj1[12].toString());
					//frqs.setGx(gx);
					frqs.setDjTime(obj1[16].toString());
					frqs.setHjid(obj1[15].toString());
					if(obj1[13]!=null && !obj1[13].toString().equals("")){
						frqs.setZwNo(obj1[13].toString());
					}else{
						frqs.setZwNo("未分配");
					}
					qslist.add(frqs);
				}
				

			}
			response.setContentType("text/json; charset=utf-8");
			JSONArray json=JSONArray.fromObject(qslist);
			response.getWriter().println(json.toString());
		}else{
			response.setContentType("text/json; charset=utf-8");
			JSONArray json=JSONArray.fromObject(null);
			response.getWriter().println(json.toString());
		}
		
		return null;
	}
	
	
	public ActionForward search4(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			return mapping.findForward("sfYzMain4");
	}
	public ActionForward jquerSearch4(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cardNo = request.getParameter("cardNo").trim();
		String hqlqs="from JlQs where qsCard='"+cardNo+"'";
		List<JlQs> qsList = sys.searchAll(hqlqs);
		if(qsList.size()>1){
			JSONArray json=JSONArray.fromObject(-1);
			response.getWriter().println(json.toString());
		}else if(qsList.size()==1){
			JlQs jlQs = (JlQs)qsList.get(0);
			
			String hql="select  dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,qs.gx,dj.FR_No,dj.FR_Name,dj.JQ_Name,dbo.get_ck(dj.FP_Line_No,dj.jy) as zw,qs.QS_Name,dj.HJID,dj.DJ_Time from JL_HJ_DJ dj,JL_QS qs where dj.State=0 and dj.FR_No=qs.FR_No  ";
			if(jlQs.getQsSfz()!=null && !jlQs.getQsSfz().trim().equals("")){
				hql+=" and qs.QS_SFZ='"+jlQs.getQsSfz()+"'";
			}
			if(jlQs.getQsName()!=null && !jlQs.getQsName().trim().equals("")){
				hql+=" and (dj.QS_Info1 like '%"+jlQs.getQsName()+"' or dj.QS_Info2 like '%"+jlQs.getQsName()+"' or dj.QS_Info3 like '%"+jlQs.getQsName()+"'or dj.QS_Info4 like '%"+jlQs.getQsName()+"'or dj.QS_Info5 like '%"+jlQs.getQsName()+"'or dj.QS_Info6 like '%"+jlQs.getQsName()+"'or dj.QS_Info7 like '%"+jlQs.getQsName()+"'or dj.QS_Info8 like '%"+jlQs.getQsName()+"'or dj.QS_Info9 like '%"+jlQs.getQsName()+"')";
			}
			List list=sys.searchAllBySql(hql);
			List<FrQs> qslist = new ArrayList<FrQs>();
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					
					boolean flag=false;
					Object[] obj1=(Object[])list.get(i);
					
					String gx="";
					String qsName2="";
					if(obj1[9]!=null && !obj1[9].toString().equals("")){
						gx=obj1[9].toString();
					}
					if(obj1[14]!=null && !obj1[14].toString().equals("")){
						qsName2=obj1[14].toString();
					}
					if(obj1[0]!=null && !obj1[0].toString().equals("")){
						String gx1=obj1[0].toString().substring(1, obj1[0].toString().indexOf("]"));
						String qsName1=obj1[0].toString().substring(obj1[0].toString().indexOf("]")+1);
						if(gx.equals(gx1)  && qsName2.equals(qsName1)){
							flag=true;
							
							
						}
						String sql ="select XB,QS_SFZ,WebID,DZ,ZP,Face_State from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
						List list2 = sys.searchAllBySql(sql);
						if(list2.size()>0){
							Object[] obj2 = (Object[]) list2.get(0);
							FrQs frqs = new FrQs();
							frqs.setQsName(qsName1);
							frqs.setGx(gx1);
							if(obj2[0]!=null && !obj2[0].toString().equals("")){
								frqs.setXb(obj2[0].toString());
							}
							if(obj2[1]!=null && !obj2[1].toString().equals("")){
								frqs.setQsSfz(obj2[1].toString());
							}
							if(obj2[2]!=null && !obj2[2].toString().equals("")){
								frqs.setWebId(Integer.parseInt(obj2[2].toString()));
							}
							if(obj2[3]!=null && !obj2[3].toString().equals("")){
								frqs.setDz(obj2[3].toString());
							}
							if(obj2[4]!=null && !obj2[4].toString().equals("")){
								frqs.setZpState("1");
							}else{
								frqs.setZpState("0");
							}
							if(obj2[5]!=null && !obj2[5].toString().equals("")){
								frqs.setFaceState(Integer.parseInt(obj2[5].toString()));
							}
							qslist.add(frqs);
						}
					}
					if(obj1[1]!=null && !obj1[1].toString().equals("")){
						String gx1=obj1[1].toString().substring(1, obj1[1].toString().indexOf("]"));
						String qsName1=obj1[1].toString().substring(obj1[1].toString().indexOf("]")+1);
						if(gx.equals(gx1)  && qsName2.equals(qsName1)){
							flag=true;
							
						}
						String sql ="select XB,QS_SFZ,WebID,DZ,ZP from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
						List list2 = sys.searchAllBySql(sql);
						if(list2.size()>0){
							Object[] obj2 = (Object[]) list2.get(0);
							FrQs frqs = new FrQs();
							frqs.setQsName(qsName1);
							frqs.setGx(gx1);
							if(obj2[0]!=null && !obj2[0].toString().equals("")){
								frqs.setXb(obj2[0].toString());
							}
							if(obj2[1]!=null && !obj2[1].toString().equals("")){
								frqs.setQsSfz(obj2[1].toString());
							}
							if(obj2[2]!=null && !obj2[2].toString().equals("")){
								frqs.setWebId(Integer.parseInt(obj2[2].toString()));
							}
							if(obj2[3]!=null && !obj2[3].toString().equals("")){
								frqs.setDz(obj2[3].toString());
							}
							if(obj2[4]!=null && !obj2[4].toString().equals("")){
								frqs.setZpState("1");
							}else{
								frqs.setZpState("0");
							}
							if(obj2[5]!=null && !obj2[5].toString().equals("")){
								frqs.setFaceState(Integer.parseInt(obj2[5].toString()));
							}
							qslist.add(frqs);
						}
					}
					if(obj1[2]!=null && !obj1[2].toString().equals("")){
						String gx1=obj1[2].toString().substring(1, obj1[2].toString().indexOf("]"));
						String qsName1=obj1[2].toString().substring(obj1[2].toString().indexOf("]")+1);
						if(gx.equals(gx1)  && qsName2.equals(qsName1)){
							flag=true;
						
						}
						String sql ="select XB,QS_SFZ,WebID,DZ,ZP from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
						List list2 = sys.searchAllBySql(sql);
						if(list2.size()>0){
							Object[] obj2 = (Object[]) list2.get(0);
							FrQs frqs = new FrQs();
							frqs.setQsName(qsName1);
							frqs.setGx(gx1);
							if(obj2[0]!=null && !obj2[0].toString().equals("")){
								frqs.setXb(obj2[0].toString());
							}
							if(obj2[1]!=null && !obj2[1].toString().equals("")){
								frqs.setQsSfz(obj2[1].toString());
							}
							if(obj2[2]!=null && !obj2[2].toString().equals("")){
								frqs.setWebId(Integer.parseInt(obj2[2].toString()));
							}
							if(obj2[3]!=null && !obj2[3].toString().equals("")){
								frqs.setDz(obj2[3].toString());
							}
							if(obj2[4]!=null && !obj2[4].toString().equals("")){
								frqs.setZpState("1");
							}else{
								frqs.setZpState("0");
							}
							if(obj2[5]!=null && !obj2[5].toString().equals("")){
								frqs.setFaceState(Integer.parseInt(obj2[5].toString()));
							}
							qslist.add(frqs);
						}
					}
					if(obj1[3]!=null && !obj1[3].toString().equals("")){
						String gx1=obj1[3].toString().substring(1, obj1[3].toString().indexOf("]"));
						String qsName1=obj1[3].toString().substring(obj1[3].toString().indexOf("]")+1);
						if(gx.equals(gx1)  && qsName2.equals(qsName1)){
							flag=true;
						}
						String sql ="select XB,QS_SFZ,WebID,DZ,ZP from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
						List list2 = sys.searchAllBySql(sql);
						if(list2.size()>0){
							Object[] obj2 = (Object[]) list2.get(0);
							FrQs frqs = new FrQs();
							frqs.setQsName(qsName1);
							frqs.setGx(gx1);
							if(obj2[0]!=null && !obj2[0].toString().equals("")){
								frqs.setXb(obj2[0].toString());
							}
							if(obj2[1]!=null && !obj2[1].toString().equals("")){
								frqs.setQsSfz(obj2[1].toString());
							}
							if(obj2[2]!=null && !obj2[2].toString().equals("")){
								frqs.setWebId(Integer.parseInt(obj2[2].toString()));
							}
							if(obj2[3]!=null && !obj2[3].toString().equals("")){
								frqs.setDz(obj2[3].toString());
							}
							if(obj2[4]!=null && !obj2[4].toString().equals("")){
								frqs.setZpState("1");
							}else{
								frqs.setZpState("0");
							}
							if(obj2[5]!=null && !obj2[5].toString().equals("")){
								frqs.setFaceState(Integer.parseInt(obj2[5].toString()));
							}
							qslist.add(frqs);
						}
					}
					if(obj1[4]!=null && !obj1[4].toString().equals("")){
						String gx1=obj1[4].toString().substring(1, obj1[4].toString().indexOf("]"));
						String qsName1=obj1[4].toString().substring(obj1[4].toString().indexOf("]")+1);
						if(gx.equals(gx1)  && qsName2.equals(qsName1)){
							flag=true;
							String sql ="select XB,QS_SFZ,WebID,DZ,ZP from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
							List list2 = sys.searchAllBySql(sql);
							if(list2.size()>0){
								Object[] obj2 = (Object[]) list2.get(0);
								FrQs frqs = new FrQs();
								frqs.setQsName(qsName1);
								frqs.setGx(gx1);
								if(obj2[0]!=null && !obj2[0].toString().equals("")){
									frqs.setXb(obj2[0].toString());
								}
								if(obj2[1]!=null && !obj2[1].toString().equals("")){
									frqs.setQsSfz(obj2[1].toString());
								}
								if(obj2[2]!=null && !obj2[2].toString().equals("")){
									frqs.setWebId(Integer.parseInt(obj2[2].toString()));
								}
								if(obj2[3]!=null && !obj2[3].toString().equals("")){
									frqs.setDz(obj2[3].toString());
								}
								if(obj2[4]!=null && !obj2[4].toString().equals("")){
									frqs.setZpState("1");
								}else{
									frqs.setZpState("0");
								}
								if(obj2[5]!=null && !obj2[5].toString().equals("")){
									frqs.setFaceState(Integer.parseInt(obj2[5].toString()));
								}
								qslist.add(frqs);
							}
						}
					}
					if(obj1[5]!=null && !obj1[5].toString().equals("")){
						String gx1=obj1[5].toString().substring(1, obj1[5].toString().indexOf("]"));
						String qsName1=obj1[5].toString().substring(obj1[5].toString().indexOf("]")+1);
						if(gx.equals(gx1)  && qsName2.equals(qsName1)){
							flag=true;
							String sql ="select XB,QS_SFZ,WebID,DZ,ZP from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
							List list2 = sys.searchAllBySql(sql);
							if(list2.size()>0){
								Object[] obj2 = (Object[]) list2.get(0);
								FrQs frqs = new FrQs();
								frqs.setQsName(qsName1);
								frqs.setGx(gx1);
								if(obj2[0]!=null && !obj2[0].toString().equals("")){
									frqs.setXb(obj2[0].toString());
								}
								if(obj2[1]!=null && !obj2[1].toString().equals("")){
									frqs.setQsSfz(obj2[1].toString());
								}
								if(obj2[2]!=null && !obj2[2].toString().equals("")){
									frqs.setWebId(Integer.parseInt(obj2[2].toString()));
								}
								if(obj2[3]!=null && !obj2[3].toString().equals("")){
									frqs.setDz(obj2[3].toString());
								}
								if(obj2[4]!=null && !obj2[4].toString().equals("")){
									frqs.setZpState("1");
								}else{
									frqs.setZpState("0");
								}
								if(obj2[5]!=null && !obj2[5].toString().equals("")){
									frqs.setFaceState(Integer.parseInt(obj2[5].toString()));
								}
								qslist.add(frqs);
							}
						}
					}
					if(obj1[6]!=null && !obj1[6].toString().equals("")){
						String gx1=obj1[6].toString().substring(1, obj1[6].toString().indexOf("]"));
						String qsName1=obj1[6].toString().substring(obj1[6].toString().indexOf("]")+1);
						if(gx.equals(gx1)  && qsName2.equals(qsName1)){
							flag=true;
							String sql ="select XB,QS_SFZ,WebID,DZ,ZP from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
							List list2 = sys.searchAllBySql(sql);
							if(list2.size()>0){
								Object[] obj2 = (Object[]) list2.get(0);
								FrQs frqs = new FrQs();
								frqs.setQsName(qsName1);
								frqs.setGx(gx1);
								if(obj2[0]!=null && !obj2[0].toString().equals("")){
									frqs.setXb(obj2[0].toString());
								}
								if(obj2[1]!=null && !obj2[1].toString().equals("")){
									frqs.setQsSfz(obj2[1].toString());
								}
								if(obj2[2]!=null && !obj2[2].toString().equals("")){
									frqs.setWebId(Integer.parseInt(obj2[2].toString()));
								}
								if(obj2[3]!=null && !obj2[3].toString().equals("")){
									frqs.setDz(obj2[3].toString());
								}
								if(obj2[4]!=null && !obj2[4].toString().equals("")){
									frqs.setZpState("1");
								}else{
									frqs.setZpState("0");
								}
								if(obj2[5]!=null && !obj2[5].toString().equals("")){
									frqs.setFaceState(Integer.parseInt(obj2[5].toString()));
								}
								qslist.add(frqs);
							}
						}
					}
					if(obj1[7]!=null && !obj1[7].toString().equals("")){
						String gx1=obj1[7].toString().substring(1, obj1[7].toString().indexOf("]"));
						String qsName1=obj1[7].toString().substring(obj1[7].toString().indexOf("]")+1);
						if(gx.equals(gx1)  && qsName2.equals(qsName1)){
							flag=true;
							String sql ="select XB,QS_SFZ,WebID,DZ,ZP from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
							List list2 = sys.searchAllBySql(sql);
							if(list2.size()>0){
								Object[] obj2 = (Object[]) list2.get(0);
								FrQs frqs = new FrQs();
								frqs.setQsName(qsName1);
								frqs.setGx(gx1);
								if(obj2[0]!=null && !obj2[0].toString().equals("")){
									frqs.setXb(obj2[0].toString());
								}
								if(obj2[1]!=null && !obj2[1].toString().equals("")){
									frqs.setQsSfz(obj2[1].toString());
								}
								if(obj2[2]!=null && !obj2[2].toString().equals("")){
									frqs.setWebId(Integer.parseInt(obj2[2].toString()));
								}
								if(obj2[3]!=null && !obj2[3].toString().equals("")){
									frqs.setDz(obj2[3].toString());
								}
								if(obj2[4]!=null && !obj2[4].toString().equals("")){
									frqs.setZpState("1");
								}else{
									frqs.setZpState("0");
								}
								if(obj2[5]!=null && !obj2[5].toString().equals("")){
									frqs.setFaceState(Integer.parseInt(obj2[5].toString()));
								}
								qslist.add(frqs);
							}
						}
					}
					if(obj1[8]!=null && !obj1[8].toString().equals("")){
						String gx1=obj1[8].toString().substring(1, obj1[8].toString().indexOf("]"));
						String qsName1=obj1[8].toString().substring(obj1[8].toString().indexOf("]")+1);
						if(gx.equals(gx1)  && qsName2.equals(qsName1)){
							flag=true;
							String sql ="select XB,QS_SFZ,WebID,DZ,ZP from JL_QS where QS_Name='"+qsName1+"' and FR_No='"+obj1[10].toString()+"'";
							List list2 = sys.searchAllBySql(sql);
							if(list2.size()>0){
								Object[] obj2 = (Object[]) list2.get(0);
								FrQs frqs = new FrQs();
								frqs.setQsName(qsName1);
								frqs.setGx(gx1);
								if(obj2[0]!=null && !obj2[0].toString().equals("")){
									frqs.setXb(obj2[0].toString());
								}
								if(obj2[1]!=null && !obj2[1].toString().equals("")){
									frqs.setQsSfz(obj2[1].toString());
								}
								if(obj2[2]!=null && !obj2[2].toString().equals("")){
									frqs.setWebId(Integer.parseInt(obj2[2].toString()));
								}
								if(obj2[3]!=null && !obj2[3].toString().equals("")){
									frqs.setDz(obj2[3].toString());
								}
								if(obj2[4]!=null && !obj2[4].toString().equals("")){
									frqs.setZpState("1");
								}else{
									frqs.setZpState("0");
								}
								if(obj2[5]!=null && !obj2[5].toString().equals("")){
									frqs.setFaceState(Integer.parseInt(obj2[5].toString()));
								}
								qslist.add(frqs);
							}
						}
					}
					if(flag){
						FrQs frqs1 = new FrQs();
						frqs1.setQsName(jlQs.getQsName());
						frqs1.setGx(jlQs.getGx());
						frqs1.setXb(jlQs.getXb());
						frqs1.setQsSfz(jlQs.getQsSfz());
						frqs1.setWebId(jlQs.getWebId());
						frqs1.setDz(jlQs.getDz());
						if(jlQs.getZp()!=null && !jlQs.getZp().equals("")){
							frqs1.setZpState("1");
						}else{
							frqs1.setZpState("0");
						}
						frqs1.setWebId(jlQs.getWebId());
						frqs1.setFaceState(jlQs.getFaceState());
						qslist.add(frqs1);
						
						FrQs frqs = new FrQs();
						frqs.setFrNo(obj1[10].toString());
						frqs.setFrName(obj1[11].toString());
						frqs.setJqName(obj1[12].toString());
						//frqs.setGx(gx);
						frqs.setDjTime(obj1[16].toString());
						frqs.setHjid(obj1[15].toString());
						qslist.add(frqs);
					}
					
				
				}
				response.setContentType("text/json; charset=utf-8");
				JSONArray json=JSONArray.fromObject(qslist);
				response.getWriter().println(json.toString());
			}else{
				response.setContentType("text/json; charset=utf-8");
				JSONArray json=JSONArray.fromObject(null);
				response.getWriter().println(json.toString());
			}
			
		}else{
			JSONArray json=JSONArray.fromObject(-2);
			response.getWriter().println(json.toString());
		}
		
		
		return null;
	}
	
	public ActionForward showPhoto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    String webID=request.getParameter("webID");
			String sql="from JlQs where webId="+webID;
			List<JlQs> list=sys.searchAll(sql);
			if(list.size()>0){
				JlQs jlQs=(JlQs)list.get(0);
				if(jlQs.getZp()!=null){
					response.setContentType( "image/jpg");
					response.getOutputStream().write(jlQs.getZp()); 
				}
			}
			return null;
	}
	
	
	
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		 	c  =  Calendar.getInstance(Locale.CHINESE);   
		 	String loginTime1 = simpleDateTimeFormat.format(c.getTime());
		 	String loginTime=loginTime1.substring(0, 10)+" 23:59:59";
		 	String endTime=loginTime1.substring(0, 10)+" 00:00:00";
			String sql="select count(*) from JL_HJ_QS_INOUTINFO where state=1 and sj<='"+loginTime+"' and sj>='"+endTime+"'";
			String sql1="select count(*) from JL_HJ_QS_INOUTINFO where state=2 and sj<='"+loginTime+"' and sj>='"+endTime+"'";
			List list3=sys.searchAllBySql(sql);
			List list4=sys.searchAllBySql(sql1);
			int left=Integer.parseInt(list3.iterator().next().toString())-Integer.parseInt(list4.iterator().next().toString());
			request.setAttribute("inCount", list3.iterator().next().toString());
			request.setAttribute("outCount",list4.iterator().next().toString());
			request.setAttribute("leftCount",left);
			return mapping.findForward("sfYzMain1");
	}
	public ActionForward jquerSearch1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String idCardNo = java.net.URLDecoder.decode((String)request.getParameter("idCardNo"),"UTF-8");
			int k=sys.getLineNo("jl_qs_inout", idCardNo);
			Calendar c = Calendar.getInstance();
			SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		 	c  =  Calendar.getInstance(Locale.CHINESE);   
		 	String loginTime1 = simpleDateTimeFormat.format(c.getTime());
		 	String loginTime=loginTime1.substring(0, 10)+" 23:59:59";
		 	String endTime=loginTime1.substring(0, 10)+" 00:00:00";
			if(k==1){
				List<SfyzVO> list1=new ArrayList<SfyzVO>();
				List<SfyzVO1> list2=new ArrayList<SfyzVO1>();
				String hql="select dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,qs.gx,dj.FR_No,dj.FR_Name,dj.JQ_Name,dbo.get_ck(dj.FP_Line_No,dj.jy) as zw,qs.WebID,qs.QS_Name,fr.HJ_Last_Time,fr.HJ_Use,dj.HJID from JL_HJ_DJ dj,JL_QS qs,JL_Fr fr where dj.State=0 and dj.FR_No=qs.FR_No and qs.FR_No=fr.FR_No and qs.QS_Card='"+idCardNo+"'";
				List list=sys.searchAllBySql(hql);
				SfyzVO1 sfyzVO1=new SfyzVO1();
				if(list.size()>0){
					String qsId="";
					for(int i=0;i<list.size();i++){
						SfyzVO sfyzVO=new SfyzVO();
						boolean flag=false;
						Object[] obj1=(Object[])list.get(i);
						String gx="";
						String qsName="";
						if(obj1[9]!=null && !obj1[9].toString().equals("")){
							gx=obj1[9].toString();
						}
						if(obj1[15]!=null && !obj1[15].toString().equals("")){
							qsName=obj1[15].toString();
						}
						if(obj1[0]!=null && !obj1[0].toString().equals("")){
							String gx1=obj1[0].toString().substring(1, obj1[0].toString().indexOf("]"));
							String qsName1=obj1[0].toString().substring(obj1[0].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[1]!=null && !obj1[1].toString().equals("")){
							String gx1=obj1[1].toString().substring(1, obj1[1].toString().indexOf("]"));
							String qsName1=obj1[1].toString().substring(obj1[1].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[2]!=null && !obj1[2].toString().equals("")){
							String gx1=obj1[2].toString().substring(1, obj1[2].toString().indexOf("]"));
							String qsName1=obj1[2].toString().substring(obj1[2].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[3]!=null && !obj1[3].toString().equals("")){
							String gx1=obj1[3].toString().substring(1, obj1[3].toString().indexOf("]"));
							String qsName1=obj1[3].toString().substring(obj1[3].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[4]!=null && !obj1[4].toString().equals("")){
							String gx1=obj1[4].toString().substring(1, obj1[4].toString().indexOf("]"));
							String qsName1=obj1[4].toString().substring(obj1[4].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[5]!=null && !obj1[5].toString().equals("")){
							String gx1=obj1[5].toString().substring(1, obj1[5].toString().indexOf("]"));
							String qsName1=obj1[5].toString().substring(obj1[5].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[6]!=null && !obj1[6].toString().equals("")){
							String gx1=obj1[6].toString().substring(1, obj1[6].toString().indexOf("]"));
							String qsName1=obj1[6].toString().substring(obj1[6].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[7]!=null && !obj1[7].toString().equals("")){
							String gx1=obj1[7].toString().substring(1, obj1[7].toString().indexOf("]"));
							String qsName1=obj1[7].toString().substring(obj1[7].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[8]!=null && !obj1[8].toString().equals("")){
							String gx1=obj1[8].toString().substring(1, obj1[8].toString().indexOf("]"));
							String qsName1=obj1[8].toString().substring(obj1[8].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(flag){
							sfyzVO.setFrNo(obj1[10].toString());
							sfyzVO.setFrName(obj1[11].toString());
							sfyzVO.setJqName(obj1[12].toString());
							if(obj1[13]!=null && !obj1[13].toString().equals("")){
								sfyzVO.setZwNo(obj1[13].toString());
							}else{
								sfyzVO.setZwNo("未分配");
							}
							if(obj1[14]!=null && !obj1[14].toString().equals("")){
								qsId=obj1[14].toString();
							}
							if(obj1[16]!=null && !obj1[16].toString().equals("")){
								sfyzVO.setHjLastTime(obj1[16].toString().substring(0, 10));
							}
							if(obj1[17]!=null && obj1[17].toString()!=""){
								if(sfyzVO.getHjLastTime()!=null && !sfyzVO.getHjLastTime().equals(loginTime.substring(0, 10))){
									sfyzVO.setHjUse("0");
								}else{
									sfyzVO.setHjUse(obj1[17].toString());
								}
							}else{
								sfyzVO.setHjUse("0");
							}
							list1.add(sfyzVO);
						}
					}
					sfyzVO1.setList(list1);
					JlQs jlQs=(JlQs)sys.findById(JlQs.class, Integer.parseInt(qsId));
					sfyzVO1.setJlQs(jlQs);
					BASE64Encoder encoder = new BASE64Encoder();
					if(jlQs.getJz()!=null){
						sfyzVO1.setJzBase64(encoder.encode(jlQs.getJz()));
					}
					if(jlQs.getZp()!=null){
						sfyzVO1.setZpBase64(encoder.encode(jlQs.getZp()));
					}
					sfyzVO1.setState("进入");
					String sql="select count(*) from JL_HJ_QS_INOUTINFO where state=1 and sj<='"+loginTime+"' and sj>='"+endTime+"'";
					String sql1="select count(*) from JL_HJ_QS_INOUTINFO where state=2 and sj<='"+loginTime+"' and sj>='"+endTime+"'";
					List list3=sys.searchAllBySql(sql);
					List list4=sys.searchAllBySql(sql1);
					sfyzVO1.setInCount(list3.iterator().next().toString());
					sfyzVO1.setOutCount(list4.iterator().next().toString());
					int left=Integer.parseInt(list3.iterator().next().toString())-Integer.parseInt(list4.iterator().next().toString());
					sfyzVO1.setLeftCount(left+"");
					list2.add(sfyzVO1);
					response.setContentType("text/json; charset=utf-8");
					JSONArray json=JSONArray.fromObject(list2);
					response.getWriter().println(json.toString());
				}
			}else if(k==2){
				List<SfyzVO> list1=new ArrayList<SfyzVO>();
				List<SfyzVO1> list2=new ArrayList<SfyzVO1>();
				String hql="select dj.QS_Info1,dj.QS_Info2,dj.QS_Info3,dj.QS_Info4,dj.QS_Info5,dj.QS_Info6,dj.QS_Info7,dj.QS_Info8,dj.QS_Info9,qs.gx,dj.FR_No,dj.FR_Name,dj.JQ_Name,dbo.get_ck(dj.FP_Line_No,dj.jy) as zw,qs.WebID,qs.QS_Name,fr.HJ_Last_Time,fr.HJ_Use,dj.HJID from JL_HJ_DJ dj,JL_QS qs,JL_Fr fr where dj.HJID=dbo.f_get_MaxDJID(dj.FR_No) and dj.FR_No=qs.FR_No and qs.FR_No=fr.FR_No and qs.QS_Card='"+idCardNo+"'";
				List list=sys.searchAllBySql(hql);
				SfyzVO1 sfyzVO1=new SfyzVO1();
				if(list.size()>0){
					String qsId="";
					for(int i=0;i<list.size();i++){
						SfyzVO sfyzVO=new SfyzVO();
						boolean flag=false;
						Object[] obj1=(Object[])list.get(i);
						String gx="";
						String qsName="";
						if(obj1[9]!=null && !obj1[9].toString().equals("")){
							gx=obj1[9].toString();
						}
						if(obj1[15]!=null && !obj1[15].toString().equals("")){
							qsName=obj1[15].toString();
						}
						if(obj1[0]!=null && !obj1[0].toString().equals("")){
							String gx1=obj1[0].toString().substring(1, obj1[0].toString().indexOf("]"));
							String qsName1=obj1[0].toString().substring(obj1[0].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[1]!=null && !obj1[1].toString().equals("")){
							String gx1=obj1[1].toString().substring(1, obj1[1].toString().indexOf("]"));
							String qsName1=obj1[1].toString().substring(obj1[1].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[2]!=null && !obj1[2].toString().equals("")){
							String gx1=obj1[2].toString().substring(1, obj1[2].toString().indexOf("]"));
							String qsName1=obj1[2].toString().substring(obj1[2].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[3]!=null && !obj1[3].toString().equals("")){
							String gx1=obj1[3].toString().substring(1, obj1[3].toString().indexOf("]"));
							String qsName1=obj1[3].toString().substring(obj1[3].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[4]!=null && !obj1[4].toString().equals("")){
							String gx1=obj1[4].toString().substring(1, obj1[4].toString().indexOf("]"));
							String qsName1=obj1[4].toString().substring(obj1[4].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[5]!=null && !obj1[5].toString().equals("")){
							String gx1=obj1[5].toString().substring(1, obj1[5].toString().indexOf("]"));
							String qsName1=obj1[5].toString().substring(obj1[5].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[6]!=null && !obj1[6].toString().equals("")){
							String gx1=obj1[6].toString().substring(1, obj1[6].toString().indexOf("]"));
							String qsName1=obj1[6].toString().substring(obj1[6].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[7]!=null && !obj1[7].toString().equals("")){
							String gx1=obj1[7].toString().substring(1, obj1[7].toString().indexOf("]"));
							String qsName1=obj1[7].toString().substring(obj1[7].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(obj1[8]!=null && !obj1[8].toString().equals("")){
							String gx1=obj1[8].toString().substring(1, obj1[8].toString().indexOf("]"));
							String qsName1=obj1[8].toString().substring(obj1[8].toString().indexOf("]")+1);
							if(gx.equals(gx1)  && qsName.equals(qsName1)){
								flag=true;
							}
						}
						if(flag){
							sfyzVO.setFrNo(obj1[10].toString());
							sfyzVO.setFrName(obj1[11].toString());
							sfyzVO.setJqName(obj1[12].toString());
							if(obj1[13]!=null && !obj1[13].toString().equals("")){
								sfyzVO.setZwNo(obj1[13].toString());
							}else{
								sfyzVO.setZwNo("未分配");
							}
							if(obj1[14]!=null && !obj1[14].toString().equals("")){
								qsId=obj1[14].toString();
							}
							if(obj1[16]!=null && !obj1[16].toString().equals("")){
								sfyzVO.setHjLastTime(obj1[16].toString().substring(0, 10));
							}
							if(obj1[17]!=null && obj1[17].toString()!=""){
								if(sfyzVO.getHjLastTime()!=null && !sfyzVO.getHjLastTime().equals(loginTime.substring(0, 10))){
									sfyzVO.setHjUse("0");
								}else{
									sfyzVO.setHjUse(obj1[17].toString());
								}
							}else{
								sfyzVO.setHjUse("0");
							}
							list1.add(sfyzVO);
						}
					}
					sfyzVO1.setList(list1);
					JlQs jlQs=(JlQs)sys.findById(JlQs.class, Integer.parseInt(qsId));
					sfyzVO1.setJlQs(jlQs);
					BASE64Encoder encoder = new BASE64Encoder();
					if(jlQs.getJz()!=null){
						sfyzVO1.setJzBase64(encoder.encode(jlQs.getJz()));
					}
					if(jlQs.getZp()!=null){
						sfyzVO1.setZpBase64(encoder.encode(jlQs.getZp()));
					}
					sfyzVO1.setState("离开");
					String sql="select count(*) from JL_HJ_QS_INOUTINFO where state=1 and sj<='"+loginTime+"' and sj>='"+endTime+"'";
					String sql1="select count(*) from JL_HJ_QS_INOUTINFO where state=2 and sj<='"+loginTime+"' and sj>='"+endTime+"'";
					List list3=sys.searchAllBySql(sql);
					List list4=sys.searchAllBySql(sql1);
					sfyzVO1.setInCount(list3.iterator().next().toString());
					sfyzVO1.setOutCount(list4.iterator().next().toString());
					int left=Integer.parseInt(list3.iterator().next().toString())-Integer.parseInt(list4.iterator().next().toString());
					sfyzVO1.setLeftCount(left+"");
					list2.add(sfyzVO1);
				}
				response.setContentType("text/json; charset=utf-8");
				JSONArray json=JSONArray.fromObject(list2);
				response.getWriter().println(json.toString());
			}else if(k==-1){
				JSONArray json=JSONArray.fromObject(-1);
				response.getWriter().println(json.toString());
			}else if(k==-2){
				JSONArray json=JSONArray.fromObject(-2);
				response.getWriter().println(json.toString());
			}else if(k==-3){
				JSONArray json=JSONArray.fromObject(-3);
				response.getWriter().println(json.toString());
			}else if(k==-4){
				JSONArray json=JSONArray.fromObject(-4);
				response.getWriter().println(json.toString());
			}else if(k==-5){
				JSONArray json=JSONArray.fromObject(-5);
				response.getWriter().println(json.toString());
			}else if(k==-6){
				JSONArray json=JSONArray.fromObject(-6);
				response.getWriter().println(json.toString());
			}
			return null;
	}
	public ActionForward search2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
			return mapping.findForward("sfYzMain2");
	}
	public ActionForward jquerSearch2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String idCardNo = java.net.URLDecoder.decode((String)request.getParameter("idCardNo"),"UTF-8");
			String hql="from JlFr where frCard='"+idCardNo+"'";
			List list=sys.searchAll(hql);
			List<FrSfVO> list1=new ArrayList<FrSfVO>();
			BASE64Encoder encoder = new BASE64Encoder();
			for(int i=0;i<list.size();i++){
				JlFr jlFr=(JlFr)list.get(i);
				FrSfVO frSfVO=new FrSfVO();
				frSfVO.setFrNo(jlFr.getFrNo());
				if(jlFr.getFrName()!=null){
					frSfVO.setFrName(jlFr.getFrName());
				}
				if(jlFr.getFrCard()!=null){
					frSfVO.setFrCard(jlFr.getFrName());
				}
				String hql1="from JlJq where jqNo='"+jlFr.getJq()+"'";
				List<JlJq> list2=sys.searchAll(hql1);
				if(list2.size()>0){
					JlJq jlJq=list2.get(0);
					if(jlJq.getJqName()!=null){
						frSfVO.setJq(jlJq.getJqName());
					}
				}
				String hql2="from JlJb where jbNo='"+jlFr.getJbNo()+"'";
				List<JlJb> list3=sys.searchAll(hql2);
				if(list3.size()>0){
					JlJb jlJb=list3.get(0);
					if(jlJb.getJbName()!=null){
						frSfVO.setJbNo(jlJb.getJbName());
					}
				}
				if(jlFr.getInfoCsrq()!=null){
					frSfVO.setInfoCsrq(jlFr.getInfoCsrq());
				}
				if(jlFr.getInfoHome()!=null){
					frSfVO.setInfoHome(jlFr.getInfoHome());
				}
				if(jlFr.getInfoRjsj()!=null){
					frSfVO.setInfoRjsj(jlFr.getInfoRjsj());
				}
				if(jlFr.getInfoXq()!=null){
					frSfVO.setInfoXq(jlFr.getInfoXq());
				}
				if(jlFr.getInfoZm()!=null){
					frSfVO.setInfoZm(jlFr.getInfoZm());
				}
				if(jlFr.getZp()!=null){
					frSfVO.setZp(encoder.encode(jlFr.getZp()));
				}
			
				list1.add(frSfVO);
			}
//			String sql="select fr.FR_No,fr.FR_Name,jq.JQ_Name,jb.JB_Name,fr.Info_RJSJ,fr.Info_ZM,fr.Info_XQ,fr.Info_CSRQ,fr.Info_HOME,fr.ZP from JL_FR fr,JL_JQ jq,JL_JB jb where fr.JQ=jq.JQ_No and fr.JB_No=jb.JB_No and fr.FR_Card='"+idCardNo+"'";
//			List list1=sys.searchAllBySql(sql);
//			List<FrSfVO> list=new ArrayList<FrSfVO>();
//			for(int i=0;i<list1.size();i++){
//				FrSfVO frSfVO=new FrSfVO();
//				Object[] obj=(Object[])list1.get(i);
//				frSfVO.setFrNo(obj[0].toString());
//				if(obj[1]!=null && !obj[1].toString().equals("")){
//					frSfVO.setFrName(obj[1].toString());
//				}
//				if(obj[2]!=null && !obj[2].toString().equals("")){
//					frSfVO.setJq(obj[2].toString());
//				}
//				if(obj[3]!=null && !obj[3].toString().equals("")){
//					frSfVO.setJbNo(obj[3].toString());
//				}
//				if(obj[4]!=null && !obj[4].toString().equals("")){
//					frSfVO.setInfoRjsj(obj[4].toString());
//				}
//				if(obj[5]!=null && !obj[5].toString().equals("")){
//					frSfVO.setInfoZm(obj[5].toString());
//				}
//				if(obj[6]!=null && !obj[6].toString().equals("")){
//					frSfVO.setInfoXq(obj[6].toString());
//				}
//				if(obj[7]!=null && !obj[7].toString().equals("")){
//					frSfVO.setInfoCsrq(obj[7].toString());
//				}
//				if(obj[8]!=null && !obj[8].toString().equals("")){
//					frSfVO.setInfoHome(obj[8].toString());
//				}
//				if(obj[9]!=null && !obj[9].toString().equals("")){
//					
//					BASE64Encoder encoder = new BASE64Encoder();
//					byte[] by=(byte[])obj[9];
//				
//					frSfVO.setZp(encoder.encode(by));
//				}
//				list.add(frSfVO);
//			}
			response.setContentType("text/json; charset=utf-8");
			JSONArray json=JSONArray.fromObject(list1);
			response.getWriter().println(json.toString());
			return null;
	}
	public ActionForward search3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
			return mapping.findForward("sfYzMain3");
	}
	public ActionForward jquerSearch3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String idCardNo = java.net.URLDecoder.decode((String)request.getParameter("idCardNo"),"UTF-8");
			String hql="from JlQs where qsCard='"+idCardNo+"'";
			List<JlQs> list=sys.searchAll(hql);
			List<FrSfVO> list1=new ArrayList<FrSfVO>();
			BASE64Encoder encoder = new BASE64Encoder();
			if(list.size()>0){
				JlQs jlQs=list.get(0);
				String hql4="from JlFr where frNo='"+jlQs.getFrNo()+"'";
				List<JlFr> list4=sys.searchAll(hql4);
				if(list4.size()>0){
					JlFr jlFr=list4.get(0);
					FrSfVO frSfVO=new FrSfVO();
					frSfVO.setFrNo(jlFr.getFrNo());
					if(jlFr.getFrName()!=null){
						frSfVO.setFrName(jlFr.getFrName());
					}
					if(jlFr.getFrCard()!=null){
						frSfVO.setFrCard(jlFr.getFrName());
					}
					String hql1="from JlJq where jqNo='"+jlFr.getJq()+"'";
					List<JlJq> list2=sys.searchAll(hql1);
					if(list2.size()>0){
						JlJq jlJq=list2.get(0);
						if(jlJq.getJqName()!=null){
							frSfVO.setJq(jlJq.getJqName());
						}
					}
					String hql2="from JlJb where jbNo='"+jlFr.getJbNo()+"'";
					List<JlJb> list3=sys.searchAll(hql2);
					if(list3.size()>0){
						JlJb jlJb=list3.get(0);
						if(jlJb.getJbName()!=null){
							frSfVO.setJbNo(jlJb.getJbName());
						}
					}
					if(jlFr.getInfoCsrq()!=null){
						frSfVO.setInfoCsrq(jlFr.getInfoCsrq());
					}
					if(jlFr.getInfoHome()!=null){
						frSfVO.setInfoHome(jlFr.getInfoHome());
					}
					if(jlFr.getInfoRjsj()!=null){
						frSfVO.setInfoRjsj(jlFr.getInfoRjsj());
					}
					if(jlFr.getInfoXq()!=null){
						frSfVO.setInfoXq(jlFr.getInfoXq());
					}
					if(jlFr.getInfoZm()!=null){
						frSfVO.setInfoZm(jlFr.getInfoZm());
					}
					if(jlFr.getZp()!=null){
						frSfVO.setZp(encoder.encode(jlFr.getZp()));
					}
					if(jlQs.getQsName()!=null){
					
						frSfVO.setQsName(jlQs.getQsName());
					}
					if(jlQs.getQsCard()!=null){
						frSfVO.setCardNo(jlQs.getQsCard());
					}
					if(jlQs.getQsSfz()!=null){
						frSfVO.setQsSfz(jlQs.getQsSfz());
					}
//					if(jlQs.getJz()!=null){
//						frSfVO.setQsZp(encoder.encode(jlQs.getJz()));
//					}
					if(jlQs.getZp()!=null){
						frSfVO.setQsZp(encoder.encode(jlQs.getZp()));
					}
					if(jlQs.getGx()!=null){
						frSfVO.setGx(jlQs.getGx());
					}
					if(jlQs.getXb()!=null){
						frSfVO.setXb(jlQs.getXb());
					}
					if(jlQs.getDz()!=null){
						frSfVO.setJtdz(jlQs.getDz());
					}
					list1.add(frSfVO);
					
				}
				
			}
//			String sql="select fr.FR_No,fr.FR_Name,jq.JQ_Name,jb.JB_Name,fr.Info_RJSJ,fr.Info_ZM,fr.Info_XQ,fr.Info_CSRQ,fr.Info_HOME,fr.ZP from JL_FR fr,JL_JQ jq,JL_JB jb where fr.JQ=jq.JQ_No and fr.JB_No=jb.JB_No and fr.FR_Card='"+idCardNo+"'";
//			List list1=sys.searchAllBySql(sql);
//			List<FrSfVO> list=new ArrayList<FrSfVO>();
//			for(int i=0;i<list1.size();i++){
//				FrSfVO frSfVO=new FrSfVO();
//				Object[] obj=(Object[])list1.get(i);
//				frSfVO.setFrNo(obj[0].toString());
//				if(obj[1]!=null && !obj[1].toString().equals("")){
//					frSfVO.setFrName(obj[1].toString());
//				}
//				if(obj[2]!=null && !obj[2].toString().equals("")){
//					frSfVO.setJq(obj[2].toString());
//				}
//				if(obj[3]!=null && !obj[3].toString().equals("")){
//					frSfVO.setJbNo(obj[3].toString());
//				}
//				if(obj[4]!=null && !obj[4].toString().equals("")){
//					frSfVO.setInfoRjsj(obj[4].toString());
//				}
//				if(obj[5]!=null && !obj[5].toString().equals("")){
//					frSfVO.setInfoZm(obj[5].toString());
//				}
//				if(obj[6]!=null && !obj[6].toString().equals("")){
//					frSfVO.setInfoXq(obj[6].toString());
//				}
//				if(obj[7]!=null && !obj[7].toString().equals("")){
//					frSfVO.setInfoCsrq(obj[7].toString());
//				}
//				if(obj[8]!=null && !obj[8].toString().equals("")){
//					frSfVO.setInfoHome(obj[8].toString());
//				}
//				if(obj[9]!=null && !obj[9].toString().equals("")){
//					
//					BASE64Encoder encoder = new BASE64Encoder();
//					byte[] by=(byte[])obj[9];
//				
//					frSfVO.setZp(encoder.encode(by));
//				}
//				list.add(frSfVO);
//			}
			response.setContentType("text/json; charset=utf-8");
			JSONArray json=JSONArray.fromObject(list1);
			response.getWriter().println(json.toString());
			return null;
	}
	public ActionForward search5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
		 	c  =  Calendar.getInstance(Locale.CHINESE);   
		 	String loginTime1 = simpleDateTimeFormat.format(c.getTime());
		 	String loginTime=loginTime1.substring(0, 10)+" 23:59:59";
		 	String endTime=loginTime1.substring(0, 10)+" 00:00:00";
			String sql="select count(*) from JL_HJ_QS_INOUTINFO where state=1 and sj<='"+loginTime+"' and sj>='"+endTime+"'";
			String sql1="select count(*) from JL_HJ_QS_INOUTINFO where state=2 and sj<='"+loginTime+"' and sj>='"+endTime+"'";
			List list3=sys.searchAllBySql(sql);
			List list4=sys.searchAllBySql(sql1);
			int left=Integer.parseInt(list3.iterator().next().toString())-Integer.parseInt(list4.iterator().next().toString());
			request.setAttribute("inCount", list3.iterator().next().toString());
			request.setAttribute("outCount",list4.iterator().next().toString());
			request.setAttribute("leftCount",left);
			return mapping.findForward("sfYzMain5");
	}
	
	public ActionForward jquerSearch5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user!=null){
			 	String qsSfz = java.net.URLDecoder.decode((String)request.getParameter("idCardNo"),"UTF-8");
			 	Calendar c = Calendar.getInstance();
			 	SimpleDateFormat simpleDateTimeFormat  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );   
			 	c  =  Calendar.getInstance(Locale.CHINESE);   
			 	String loginTime = simpleDateTimeFormat.format(c.getTime());
			 	StringBuffer str=new StringBuffer("select aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,dbo.get_hj_UseCount(aa.FR_No) as use1,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,(select top 1 DJ_Time from jl_hj_rec where FR_No=aa.FR_No order by webid desc) as HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,dbo.f_get_jg(aa.Info_JG) as Info_JG,Info_ZDZF,Info_ZM,Info_XQ,Info_CSRQ,info_home,(select top 1 JQ_Name from jl_hj_dj where FR_No=aa.FR_No and state=1 order by hjid desc) as Former_JQ_Name from (select jl.webId,jl.FR_No,jl.FR_Name,jl.jy,jl.jq,jb.JB_Name,jq.jq_Name,jl.HJ_JB,jl.Monitor_Flag,jb.HJ_Count,jl.HJ_Last_Time,jl.HJ_Use,jl.Info_RJSJ,jl.Info_JG,jl.Info_ZDZF,jl.Info_ZM,jl.Info_XQ,jl.Info_CSRQ,jl.info_home,Former_JQ_Name from JL_FR jl,JL_JB jb,JL_Jq jq where jb.JB_No=jl.JB_No and jq.jq_No=jl.jq ) as aa left join (select * from JL_QS qs where 1=1 ) as qs on aa.FR_No=qs.FR_No where 1=1 ");
				if(!qsSfz.trim().equals("")){
					str.append("and qs.QS_SFZ ='"+qsSfz.trim()+"' ");
				}
				str.append("group by aa.webId,aa.FR_No,aa.FR_Name,aa.jy,aa.jq,aa.JB_Name,aa.jq_Name,aa.HJ_JB,aa.Monitor_Flag,aa.HJ_Count,aa.HJ_Last_Time,aa.HJ_Use,aa.Info_RJSJ,aa.Info_JG,aa.Info_ZDZF,aa.Info_ZM,aa.Info_XQ,aa.Info_CSRQ,aa.info_home order by aa.webId desc");
				List list3=sys.searchAllBySql(str.toString());
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
//					System.out.println(obj[11].toString().substring(0, 10));
//					if(obj[12]!=null && !obj[12].toString().equals("")){
//						if(fr.getHjLastTime()!=null && !fr.getHjLastTime().substring(0, 7).equals(loginTime.substring(0, 7))){
//							fr.setHjUse("0");
//						}else{
//							fr.setHjUse(obj[12].toString());
//						}
//					}else{
//						fr.setHjUse("0");
//					}
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
					listFrList.add(fr);
				}
				response.setContentType("text/json; charset=utf-8");
				JSONArray json=JSONArray.fromObject(listFrList);
				response.getWriter().println(json.toString());
			}else{
				JSONArray json=JSONArray.fromObject(-1);
				response.getWriter().println(json.toString());
			}
			
			return null;
	}
}
