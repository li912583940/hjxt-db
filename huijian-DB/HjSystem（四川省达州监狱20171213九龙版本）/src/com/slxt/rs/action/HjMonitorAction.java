package com.slxt.rs.action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.slxt.rs.model.JlHjDj;
import com.slxt.rs.model.JlHjMon;
import com.slxt.rs.model.JlHjMonitorTimeAdd;
import com.slxt.rs.model.JlMonitorVoc;
import com.slxt.rs.model.SysHjLine;
import com.slxt.rs.model.SysHjServer;
import com.slxt.rs.model.SysHjVideo;
import com.slxt.rs.model.SysParam;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.HjMonitorService;
import com.slxt.rs.vo.HjMonitor;

public class HjMonitorAction extends DispatchAction{
	private HjMonitorService  hms;
	public void setHms(HjMonitorService hms) {
		this.hms = hms;
	}
	//查询所有线路（普通）
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
			int a1=0;
			int a2=0;
			int a3=0;
//			int b1=0;
//			int b2=0;
//			int b3=0;
			int c1=0;
			int c2=0;
			int c3=0;
//			int s1=0;
//			int s2=0;
		    if(user.getIsSuper() == 1){
		    	StringBuffer str5=new StringBuffer();
		    	StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    	String hql="from SysHjServer";
		    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
				request.setAttribute("sysQqServerList", sysHjServer);
				for(int i=0;i<sysHjServer.size();i++){
					SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
					if(i==0){
						str5.append(sysServer.getServerName());
						str5.append(","+sysServer.getIp());
						str5.append(","+sysServer.getPort());
					}else{
						str5.append("|");
						str5.append(sysServer.getServerName());
						str5.append(","+sysServer.getIp());
						str5.append(","+sysServer.getPort());
					}
				}
				request.setAttribute("sysQqServerList1", str5.toString());
				List list=hms.searchAllBySql(str.toString());
				List<HjMonitor> list1=new ArrayList();
				Iterator it=list.iterator();

				while(it.hasNext()){
					Object[] obj=( Object[])it.next();
					HjMonitor rm=new HjMonitor();
					rm.setLineNo(obj[0].toString());
					rm.setJy(obj[1].toString());
					rm.setLineType(obj[2].toString());
					if(obj[3]!=null && !obj[3].toString().equals("")){
						rm.setZw(obj[3].toString());					
					}
					
					rm.setHJState(obj[4].toString());

					if(obj[5]!=null && !obj[5].toString().equals("")){
						rm.setHjId(obj[5].toString());
					}
					if(obj[6]!=null && !obj[6].toString().equals("")){
						rm.setAcdBh(obj[6].toString());
					}
					if(obj[7]!=null && !obj[7].toString().equals("")){
						rm.setAcdFr(obj[7].toString());
					}
					if(obj[8]!=null && !obj[8].toString().equals("")){
						rm.setMonitorState(obj[8].toString());
					}
					if(obj[9]!=null && !obj[9].toString().equals("")){
						rm.setMonitorJq(obj[9].toString());
					}
					if(obj[10]!=null && !obj[10].toString().equals("")){
						rm.setMonitorFr(obj[10].toString());
					}
					if(obj[11]!=null && !obj[11].toString().equals("")){
						rm.setMonitorQs(obj[11].toString());
					}
					if(obj[12]!=null && !obj[12].toString().equals("")){
						rm.setMonitorYj(obj[12].toString());
					}
					if(obj[13]!=null && !obj[13].toString().equals("")){
						rm.setMonitorTime(obj[13].toString());
					}
					if(obj[14]!=null && !obj[14].toString().equals("")){
						rm.setMonitorJKBZ(obj[14].toString());
					}
					if(obj[15]!=null && !obj[15].toString().equals("")){
						rm.setMonitorCallID(obj[15].toString());
					}
					if(obj[16]!=null && !obj[16].toString().equals("")){
						 rm.setIp(obj[16].toString());
					}
					if(obj[17]!=null && !obj[17].toString().equals("")){
						rm.setPort(obj[17].toString());
					}
					if(obj[18]!=null && !obj[18].toString().equals("")){
						rm.setAudioPort(obj[18].toString());
					}
					if(obj[19]!=null && !obj[19].toString().equals("")){
						rm.setUserNo(obj[19].toString());
					}
					if(obj[20]!=null && !obj[20].toString().equals("")){
						rm.setMonitorFlagId(obj[20].toString());
					}
					if(obj[21]!=null && !obj[21].toString().equals("")){
						rm.setWriteTxt(obj[21].toString());
					}
					if(obj[22]!=null && !obj[22].toString().equals("")){
						rm.setVideoChan1Server(obj[22].toString());
					}
					if(obj[23]!=null && !obj[23].toString().equals("")){
						rm.setVideoChan1No(obj[23].toString());
					}
					if(obj[24]!=null && !obj[24].toString().equals("")){
						rm.setVideoChan2Server(obj[24].toString());
					}
					if(obj[25]!=null && !obj[25].toString().equals("")){
						rm.setVideoChan2No(obj[25].toString());
					}
					if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
					    a1++;
				    }
					if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
					    a2++;
				    }
					if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
					    a3++;
				    }
					if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
					    c1++;
				    }
					if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
					    c2++;
				    }
					if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
					    c3++;
				    }
					list1.add(rm);
				}
				request.setAttribute("sysQqLineList", list1);
		    }else if(user.getGroupNo().trim().equals("Admin")){
		    	StringBuffer str5=new StringBuffer();
		    	StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    	String hql="from SysHjServer";
		    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
				request.setAttribute("sysQqServerList", sysHjServer);
				for(int i=0;i<sysHjServer.size();i++){
					SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
					if(i==0){
						str5.append(sysServer.getServerName());
						str5.append(","+sysServer.getIp());
						str5.append(","+sysServer.getPort());
					}else{
						str5.append("|");
						str5.append(sysServer.getServerName());
						str5.append(","+sysServer.getIp());
						str5.append(","+sysServer.getPort());
					}
				}
				request.setAttribute("sysQqServerList1", str5.toString());
				List list=hms.searchAllBySql(str.toString());
				List<HjMonitor> list1=new ArrayList();
				Iterator it=list.iterator();

				while(it.hasNext()){
					Object[] obj=( Object[])it.next();
					HjMonitor rm=new HjMonitor();
					rm.setLineNo(obj[0].toString());
					rm.setJy(obj[1].toString());
					rm.setLineType(obj[2].toString());
					if(obj[3]!=null && !obj[3].toString().equals("")){
						rm.setZw(obj[3].toString());

					}
					
					rm.setHJState(obj[4].toString());

					if(obj[5]!=null && !obj[5].toString().equals("")){
						rm.setHjId(obj[5].toString());
//						System.out.println(obj[5].toString());
					}
					if(obj[6]!=null && !obj[6].toString().equals("")){
						rm.setAcdBh(obj[6].toString());
					}
					if(obj[7]!=null && !obj[7].toString().equals("")){
						rm.setAcdFr(obj[7].toString());
					}
					if(obj[8]!=null && !obj[8].toString().equals("")){
						rm.setMonitorState(obj[8].toString());
					}
					if(obj[9]!=null && !obj[9].toString().equals("")){
						rm.setMonitorJq(obj[9].toString());
					}
					if(obj[10]!=null && !obj[10].toString().equals("")){
						rm.setMonitorFr(obj[10].toString());
					}
					if(obj[11]!=null && !obj[11].toString().equals("")){
						rm.setMonitorQs(obj[11].toString());
					}
					if(obj[12]!=null && !obj[12].toString().equals("")){
						rm.setMonitorYj(obj[12].toString());
					}
					if(obj[13]!=null && !obj[13].toString().equals("")){
						rm.setMonitorTime(obj[13].toString());
					}
					if(obj[14]!=null && !obj[14].toString().equals("")){
						rm.setMonitorJKBZ(obj[14].toString());
					}
					if(obj[15]!=null && !obj[15].toString().equals("")){
						rm.setMonitorCallID(obj[15].toString());
					}
					if(obj[16]!=null && !obj[16].toString().equals("")){
						 rm.setIp(obj[16].toString());
					}
					if(obj[17]!=null && !obj[17].toString().equals("")){
						rm.setPort(obj[17].toString());
					}
					if(obj[18]!=null && !obj[18].toString().equals("")){
						rm.setAudioPort(obj[18].toString());
					}
					if(obj[19]!=null && !obj[19].toString().equals("")){
						rm.setUserNo(obj[19].toString());
					}
					if(obj[20]!=null && !obj[20].toString().equals("")){
						rm.setMonitorFlagId(obj[20].toString());
					}
					if(obj[21]!=null && !obj[21].toString().equals("")){
						rm.setWriteTxt(obj[21].toString());
					}
					if(obj[22]!=null && !obj[22].toString().equals("")){
						rm.setVideoChan1Server(obj[22].toString());
					}
					if(obj[23]!=null && !obj[23].toString().equals("")){
						rm.setVideoChan1No(obj[23].toString());
					}
					if(obj[24]!=null && !obj[24].toString().equals("")){
						rm.setVideoChan2Server(obj[24].toString());
					}
					if(obj[25]!=null && !obj[25].toString().equals("")){
						rm.setVideoChan2No(obj[25].toString());
					}

					if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
					    a1++;
				    }
					if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
					    a2++;
				    }
					if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
					    a3++;
				    }

					if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
					    c1++;
				    }
					if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
					    c2++;
				    }
					if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
					    c3++;
				    }
					list1.add(rm);
				}
				request.setAttribute("sysQqLineList", list1);
		    }else {
		    	StringBuffer str1=new StringBuffer("select jq_name from jl_jq where jq_no in (select jq_no from sys_user_jq where group_no='"+user.getGroupNo()+"')");
		    	List list2=hms.searchAllBySql(str1.toString());
		    	StringBuffer str5=new StringBuffer();
		    	if(list2.size()==1){
		    		
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND sl.Monitor_JQ ='"+list2.get(0)+"') as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	}else if(list2.size()==2){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	}else if(list2.size()==3){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	}else if(list2.size()==4){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==5){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==6){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==7){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==8){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==9){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==10){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==11){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==12){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==13){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==14){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==15){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"' or sl.Monitor_JQ ='"+list2.get(14)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==16){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"' or sl.Monitor_JQ ='"+list2.get(14)+"' or sl.Monitor_JQ ='"+list2.get(15)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==17){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"' or sl.Monitor_JQ ='"+list2.get(14)+"' or sl.Monitor_JQ ='"+list2.get(15)+"' or sl.Monitor_JQ ='"+list2.get(16)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	
		    	}else if(list2.size()==18){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"' or sl.Monitor_JQ ='"+list2.get(14)+"' or sl.Monitor_JQ ='"+list2.get(15)+"' or sl.Monitor_JQ ='"+list2.get(16)+"' or sl.Monitor_JQ ='"+list2.get(17)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);

		    	}else{
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"' or sl.Monitor_JQ ='"+list2.get(14)+"' or sl.Monitor_JQ ='"+list2.get(15)+"' or sl.Monitor_JQ ='"+list2.get(16)+"' or sl.Monitor_JQ ='"+list2.get(17)+"' or sl.Monitor_JQ ='"+list2.get(18)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
			    	String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();

					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
//							System.out.println(obj[3].toString().substring(0, 1));
							
						}
						
						rm.setHJState(obj[4].toString());

						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							rm.setVideoChan2No(obj[25].toString());
						}

						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
						    a1++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    a2++;
					    }
						if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
						    a3++;
					    }

						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
						    c1++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
						    c2++;
					    }
						if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
						    c3++;
					    }
						list1.add(rm);
					}
					request.setAttribute("sysQqLineList", list1);
		    	}
		    	
		    }
			
			HjMonitor hjm = new HjMonitor();
			hjm.setThreeShiyong(a1+"");
			hjm.setThreeWeishiyong(a2+"");
			hjm.setThreePingbi(a3+"");

			hjm.setThreeKJshiyong(c1+"");
			hjm.setThreeKJweishiyong(c2+"");
			hjm.setThreeKJpingbi(c3+"");

			request.setAttribute("hjm", hjm);
			String sqlString="from JlMonitorVoc";
			List<JlMonitorVoc> list2=hms.searchAll(sqlString);
			
			String hql1="from SysParam where paramName='HJ_Client1'";
			List<SysParam> list3=hms.searchAll(hql1);
			if(list3.size()>0){
				SysParam sysParam=(SysParam)list3.get(0);
				request.setAttribute("sysParam", sysParam);
			}
			
			request.setAttribute("jlMonitorVocList", list2);
			return mapping.findForward("hjMonitorMain");
	}
	//查询所有线路
	public ActionForward search1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		  
			StringBuffer str5=new StringBuffer();
	    	StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
	    	String hql="from SysHjServer";
	    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
			request.setAttribute("sysQqServerList", sysHjServer);
			for(int i=0;i<sysHjServer.size();i++){
				SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
				if(i==0){
					str5.append(sysServer.getServerName());
					str5.append(","+sysServer.getIp());
					str5.append(","+sysServer.getPort());
				}else{
					str5.append("|");
					str5.append(sysServer.getServerName());
					str5.append(","+sysServer.getIp());
					str5.append(","+sysServer.getPort());
				}
			}
			request.setAttribute("sysQqServerList1", str5.toString());
			List list=hms.searchAllBySql(str.toString());
			List<HjMonitor> list1=new ArrayList();
			Iterator it=list.iterator();
			int a1=0;
			int a2=0;
			int a3=0;
//			int b1=0;
//			int b2=0;
//			int b3=0;
			int c1=0;
			int c2=0;
			int c3=0;
//			int s1=0;
//			int s2=0;
			while(it.hasNext()){
				Object[] obj=( Object[])it.next();
				HjMonitor rm=new HjMonitor();
				rm.setLineNo(obj[0].toString());
				rm.setJy(obj[1].toString());
				rm.setLineType(obj[2].toString());
				if(obj[3]!=null && !obj[3].toString().equals("")){
					rm.setZw(obj[3].toString());
//					System.out.println(obj[3].toString().substring(0, 1));
					
				}
				
				rm.setHJState(obj[4].toString());
//				if(obj[4].toString().equals("1") && obj[26].toString().equals("1")){
//					s1++;
//				}else if(obj[26].toString().equals("1")){
//					s2++;
//				}
				if(obj[5]!=null && !obj[5].toString().equals("")){
					rm.setHjId(obj[5].toString());
//					System.out.println(obj[5].toString());
				}
				if(obj[6]!=null && !obj[6].toString().equals("")){
					rm.setAcdBh(obj[6].toString());
				}
				if(obj[7]!=null && !obj[7].toString().equals("")){
					rm.setAcdFr(obj[7].toString());
				}
				if(obj[8]!=null && !obj[8].toString().equals("")){
					rm.setMonitorState(obj[8].toString());
				}
				if(obj[9]!=null && !obj[9].toString().equals("")){
					rm.setMonitorJq(obj[9].toString());
				}
				if(obj[10]!=null && !obj[10].toString().equals("")){
					rm.setMonitorFr(obj[10].toString());
				}
				if(obj[11]!=null && !obj[11].toString().equals("")){
					rm.setMonitorQs(obj[11].toString());
				}
				if(obj[12]!=null && !obj[12].toString().equals("")){
					rm.setMonitorYj(obj[12].toString());
				}
				if(obj[13]!=null && !obj[13].toString().equals("")){
					rm.setMonitorTime(obj[13].toString());
				}
				if(obj[14]!=null && !obj[14].toString().equals("")){
					rm.setMonitorJKBZ(obj[14].toString());
				}
				if(obj[15]!=null && !obj[15].toString().equals("")){
					rm.setMonitorCallID(obj[15].toString());
				}
				if(obj[16]!=null && !obj[16].toString().equals("")){
					 rm.setIp(obj[16].toString());
				}
				if(obj[17]!=null && !obj[17].toString().equals("")){
					rm.setPort(obj[17].toString());
				}
				if(obj[18]!=null && !obj[18].toString().equals("")){
					rm.setAudioPort(obj[18].toString());
				}
				if(obj[19]!=null && !obj[19].toString().equals("")){
					rm.setUserNo(obj[19].toString());
				}
				if(obj[20]!=null && !obj[20].toString().equals("")){
					rm.setMonitorFlagId(obj[20].toString());
				}
				if(obj[21]!=null && !obj[21].toString().equals("")){
					rm.setWriteTxt(obj[21].toString());
				}
				if(obj[22]!=null && !obj[22].toString().equals("")){
					rm.setVideoChan1Server(obj[22].toString());
				}
				if(obj[23]!=null && !obj[23].toString().equals("")){
					rm.setVideoChan1No(obj[23].toString());
				}
				if(obj[24]!=null && !obj[24].toString().equals("")){
					rm.setVideoChan2Server(obj[24].toString());
				}
				if(obj[25]!=null && !obj[25].toString().equals("")){
					rm.setVideoChan2No(obj[25].toString());
				}
//揭阳
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
//				    a1++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
//				    a2++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("0") && obj[26].toString().equals("0")){
//				    a3++;
//			    }
				if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
				    a1++;
			    }
				if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
				    a2++;
			    }
				if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
				    a3++;
			    }
//				if(obj[3].toString().substring(0, 1).equals("4") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
//				    b1++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("4") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
//				    b2++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("4") && obj[2].toString().equals("0") && obj[26].toString().equals("0")){
//				    b3++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
//				    c1++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
//				    c2++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("1") && obj[26].toString().equals("0")){
//				    c3++;
//			    }
				if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
				    c1++;
			    }
				if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
				    c2++;
			    }
				if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
				    c3++;
			    }
				list1.add(rm);
			}
//			System.out.println(a1);
//			System.out.println(a2);
//			System.out.println(a3);
//			System.out.println(b1);
//			System.out.println(b2);
//			System.out.println(b3);
//			System.out.println(c1);
//			System.out.println(c2);
//			System.out.println(c3);
			HjMonitor hjm = new HjMonitor();
			hjm.setThreeShiyong(a1+"");
			hjm.setThreeWeishiyong(a2+"");
			hjm.setThreePingbi(a3+"");
//			hjm.setFourShiyong(b1+"");
//			hjm.setFourWeishiyong(b2+"");
//			hjm.setFourPingbi(b3+"");
			hjm.setThreeKJshiyong(c1+"");
			hjm.setThreeKJweishiyong(c2+"");
			hjm.setThreeKJpingbi(c3+"");
//			hjm.setWeishiyong(s1+"");
//			hjm.setWeishiyong(s2+"");
			request.setAttribute("hjm", hjm);
			String sqlString="from JlMonitorVoc";
			List<JlMonitorVoc> list2=hms.searchAll(sqlString);
			
			String hql1="from SysParam where paramName='HJ_Client1'";
			List<SysParam> list3=hms.searchAll(hql1);
			if(list3.size()>0){
				SysParam sysParam=(SysParam)list3.get(0);
				request.setAttribute("sysParam", sysParam);
			}
			request.setAttribute("sysQqLineList", list1);
			request.setAttribute("jlMonitorVocList", list2);
			return mapping.findForward("hjMonitorMain1");
	}
	//查询所有线路
	public ActionForward search3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		  
			StringBuffer str5=new StringBuffer();
	    	StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
	    	String hql="from SysHjServer";
	    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
			request.setAttribute("sysQqServerList", sysHjServer);
			for(int i=0;i<sysHjServer.size();i++){
				SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
				if(i==0){
					str5.append(sysServer.getServerName());
					str5.append(","+sysServer.getIp());
					str5.append(","+sysServer.getPort());
				}else{
					str5.append("|");
					str5.append(sysServer.getServerName());
					str5.append(","+sysServer.getIp());
					str5.append(","+sysServer.getPort());
				}
			}
			request.setAttribute("sysQqServerList1", str5.toString());
			List list=hms.searchAllBySql(str.toString());
			List<HjMonitor> list1=new ArrayList();
			Iterator it=list.iterator();
			int a1=0;
			int a2=0;
			int a3=0;
//			int b1=0;
//			int b2=0;
//			int b3=0;
			int c1=0;
			int c2=0;
			int c3=0;
//			int s1=0;
//			int s2=0;
			while(it.hasNext()){
				Object[] obj=( Object[])it.next();
				HjMonitor rm=new HjMonitor();
				rm.setLineNo(obj[0].toString());
				rm.setJy(obj[1].toString());
				rm.setLineType(obj[2].toString());
				if(obj[3]!=null && !obj[3].toString().equals("")){
					rm.setZw(obj[3].toString());
//					System.out.println(obj[3].toString().substring(0, 1));
					
				}
				
				rm.setHJState(obj[4].toString());
//				if(obj[4].toString().equals("1") && obj[26].toString().equals("1")){
//					s1++;
//				}else if(obj[26].toString().equals("1")){
//					s2++;
//				}
				if(obj[5]!=null && !obj[5].toString().equals("")){
					rm.setHjId(obj[5].toString());
//					System.out.println(obj[5].toString());
				}
				if(obj[6]!=null && !obj[6].toString().equals("")){
					rm.setAcdBh(obj[6].toString());
				}
				if(obj[7]!=null && !obj[7].toString().equals("")){
					rm.setAcdFr(obj[7].toString());
				}
				if(obj[8]!=null && !obj[8].toString().equals("")){
					rm.setMonitorState(obj[8].toString());
				}
				if(obj[9]!=null && !obj[9].toString().equals("")){
					rm.setMonitorJq(obj[9].toString());
				}
				if(obj[10]!=null && !obj[10].toString().equals("")){
					rm.setMonitorFr(obj[10].toString());
				}
				if(obj[11]!=null && !obj[11].toString().equals("")){
					rm.setMonitorQs(obj[11].toString());
				}
				if(obj[12]!=null && !obj[12].toString().equals("")){
					rm.setMonitorYj(obj[12].toString());
				}
				if(obj[13]!=null && !obj[13].toString().equals("")){
					rm.setMonitorTime(obj[13].toString());
				}
				if(obj[14]!=null && !obj[14].toString().equals("")){
					rm.setMonitorJKBZ(obj[14].toString());
				}
				if(obj[15]!=null && !obj[15].toString().equals("")){
					rm.setMonitorCallID(obj[15].toString());
				}
				if(obj[16]!=null && !obj[16].toString().equals("")){
					 rm.setIp(obj[16].toString());
				}
				if(obj[17]!=null && !obj[17].toString().equals("")){
					rm.setPort(obj[17].toString());
				}
				if(obj[18]!=null && !obj[18].toString().equals("")){
					rm.setAudioPort(obj[18].toString());
				}
				if(obj[19]!=null && !obj[19].toString().equals("")){
					rm.setUserNo(obj[19].toString());
				}
				if(obj[20]!=null && !obj[20].toString().equals("")){
					rm.setMonitorFlagId(obj[20].toString());
				}
				if(obj[21]!=null && !obj[21].toString().equals("")){
					rm.setWriteTxt(obj[21].toString());
				}
				if(obj[22]!=null && !obj[22].toString().equals("")){
					rm.setVideoChan1Server(obj[22].toString());
				}
				if(obj[23]!=null && !obj[23].toString().equals("")){
					rm.setVideoChan1No(obj[23].toString());
				}
				if(obj[24]!=null && !obj[24].toString().equals("")){
					rm.setVideoChan2Server(obj[24].toString());
				}
				if(obj[25]!=null && !obj[25].toString().equals("")){
					rm.setVideoChan2No(obj[25].toString());
				}
//揭阳
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
//				    a1++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
//				    a2++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("0") && obj[26].toString().equals("0")){
//				    a3++;
//			    }
				if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
				    a1++;
			    }
				if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
				    a2++;
			    }
				if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
				    a3++;
			    }
//				if(obj[3].toString().substring(0, 1).equals("4") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
//				    b1++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("4") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
//				    b2++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("4") && obj[2].toString().equals("0") && obj[26].toString().equals("0")){
//				    b3++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
//				    c1++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
//				    c2++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("1") && obj[26].toString().equals("0")){
//				    c3++;
//			    }
				if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
				    c1++;
			    }
				if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
				    c2++;
			    }
				if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
				    c3++;
			    }
				list1.add(rm);
			}
//			System.out.println(a1);
//			System.out.println(a2);
//			System.out.println(a3);
//			System.out.println(b1);
//			System.out.println(b2);
//			System.out.println(b3);
//			System.out.println(c1);
//			System.out.println(c2);
//			System.out.println(c3);
			HjMonitor hjm = new HjMonitor();
			hjm.setThreeShiyong(a1+"");
			hjm.setThreeWeishiyong(a2+"");
			hjm.setThreePingbi(a3+"");
//			hjm.setFourShiyong(b1+"");
//			hjm.setFourWeishiyong(b2+"");
//			hjm.setFourPingbi(b3+"");
			hjm.setThreeKJshiyong(c1+"");
			hjm.setThreeKJweishiyong(c2+"");
			hjm.setThreeKJpingbi(c3+"");
//			hjm.setWeishiyong(s1+"");
//			hjm.setWeishiyong(s2+"");
			request.setAttribute("hjm", hjm);
			String sqlString="from JlMonitorVoc";
			List<JlMonitorVoc> list2=hms.searchAll(sqlString);
			
			String hql1="from SysParam where paramName='HJ_Client1'";
			List<SysParam> list3=hms.searchAll(hql1);
			if(list3.size()>0){
				SysParam sysParam=(SysParam)list3.get(0);
				request.setAttribute("sysParam", sysParam);
			}
			request.setAttribute("sysQqLineList", list1);
			request.setAttribute("jlMonitorVocList", list2);
			return mapping.findForward("hjMonitorMain3");
	}
	//查询所有线路
	public ActionForward search5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		  
			StringBuffer str5=new StringBuffer();
	    	StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
	    	String hql="from SysHjServer";
	    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
			request.setAttribute("sysQqServerList", sysHjServer);
			for(int i=0;i<sysHjServer.size();i++){
				SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
				if(i==0){
					str5.append(sysServer.getServerName());
					str5.append(","+sysServer.getIp());
					str5.append(","+sysServer.getPort());
				}else{
					str5.append("|");
					str5.append(sysServer.getServerName());
					str5.append(","+sysServer.getIp());
					str5.append(","+sysServer.getPort());
				}
			}
			request.setAttribute("sysQqServerList1", str5.toString());
			List list=hms.searchAllBySql(str.toString());
			List<HjMonitor> list1=new ArrayList();
			Iterator it=list.iterator();
			int a1=0;
			int a2=0;
			int a3=0;
//			int b1=0;
//			int b2=0;
//			int b3=0;
			int c1=0;
			int c2=0;
			int c3=0;
//			int s1=0;
//			int s2=0;
			while(it.hasNext()){
				Object[] obj=( Object[])it.next();
				HjMonitor rm=new HjMonitor();
				rm.setLineNo(obj[0].toString());
				rm.setJy(obj[1].toString());
				rm.setLineType(obj[2].toString());
				if(obj[3]!=null && !obj[3].toString().equals("")){
					rm.setZw(obj[3].toString());
//					System.out.println(obj[3].toString().substring(0, 1));
					
				}
				
				rm.setHJState(obj[4].toString());
//				if(obj[4].toString().equals("1") && obj[26].toString().equals("1")){
//					s1++;
//				}else if(obj[26].toString().equals("1")){
//					s2++;
//				}
				if(obj[5]!=null && !obj[5].toString().equals("")){
					rm.setHjId(obj[5].toString());
//					System.out.println(obj[5].toString());
				}
				if(obj[6]!=null && !obj[6].toString().equals("")){
					rm.setAcdBh(obj[6].toString());
				}
				if(obj[7]!=null && !obj[7].toString().equals("")){
					rm.setAcdFr(obj[7].toString());
				}
				if(obj[8]!=null && !obj[8].toString().equals("")){
					rm.setMonitorState(obj[8].toString());
				}
				if(obj[9]!=null && !obj[9].toString().equals("")){
					rm.setMonitorJq(obj[9].toString());
				}
				if(obj[10]!=null && !obj[10].toString().equals("")){
					rm.setMonitorFr(obj[10].toString());
				}
				if(obj[11]!=null && !obj[11].toString().equals("")){
					rm.setMonitorQs(obj[11].toString());
				}
				if(obj[12]!=null && !obj[12].toString().equals("")){
					rm.setMonitorYj(obj[12].toString());
				}
				if(obj[13]!=null && !obj[13].toString().equals("")){
					rm.setMonitorTime(obj[13].toString());
				}
				if(obj[14]!=null && !obj[14].toString().equals("")){
					rm.setMonitorJKBZ(obj[14].toString());
				}
				if(obj[15]!=null && !obj[15].toString().equals("")){
					rm.setMonitorCallID(obj[15].toString());
				}
				if(obj[16]!=null && !obj[16].toString().equals("")){
					 rm.setIp(obj[16].toString());
				}
				if(obj[17]!=null && !obj[17].toString().equals("")){
					rm.setPort(obj[17].toString());
				}
				if(obj[18]!=null && !obj[18].toString().equals("")){
					rm.setAudioPort(obj[18].toString());
				}
				if(obj[19]!=null && !obj[19].toString().equals("")){
					rm.setUserNo(obj[19].toString());
				}
				if(obj[20]!=null && !obj[20].toString().equals("")){
					rm.setMonitorFlagId(obj[20].toString());
				}
				if(obj[21]!=null && !obj[21].toString().equals("")){
					rm.setWriteTxt(obj[21].toString());
				}
				if(obj[22]!=null && !obj[22].toString().equals("")){
					rm.setVideoChan1Server(obj[22].toString());
				}
				if(obj[23]!=null && !obj[23].toString().equals("")){
					rm.setVideoChan1No(obj[23].toString());
				}
				if(obj[24]!=null && !obj[24].toString().equals("")){
					rm.setVideoChan2Server(obj[24].toString());
				}
				if(obj[25]!=null && !obj[25].toString().equals("")){
					rm.setVideoChan2No(obj[25].toString());
				}
//揭阳
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
//				    a1++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
//				    a2++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("0") && obj[26].toString().equals("0")){
//				    a3++;
//			    }
				if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
				    a1++;
			    }
				if(obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
				    a2++;
			    }
				if(obj[2].toString().equals("0") && obj[26].toString().equals("0")){
				    a3++;
			    }
//				if(obj[3].toString().substring(0, 1).equals("4") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]!=null){
//				    b1++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("4") && obj[2].toString().equals("0") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
//				    b2++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("4") && obj[2].toString().equals("0") && obj[26].toString().equals("0")){
//				    b3++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
//				    c1++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
//				    c2++;
//			    }
//				if(obj[3].toString().substring(0, 1).equals("3") && obj[2].toString().equals("1") && obj[26].toString().equals("0")){
//				    c3++;
//			    }
				if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]!=null){
				    c1++;
			    }
				if(obj[2].toString().equals("1") && obj[26].toString().equals("1") && obj[5]==null && obj[4].toString().equals("0")){
				    c2++;
			    }
				if(obj[2].toString().equals("1") && obj[26].toString().equals("0")){
				    c3++;
			    }
				list1.add(rm);
			}
//			System.out.println(a1);
//			System.out.println(a2);
//			System.out.println(a3);
//			System.out.println(b1);
//			System.out.println(b2);
//			System.out.println(b3);
//			System.out.println(c1);
//			System.out.println(c2);
//			System.out.println(c3);
			HjMonitor hjm = new HjMonitor();
			hjm.setThreeShiyong(a1+"");
			hjm.setThreeWeishiyong(a2+"");
			hjm.setThreePingbi(a3+"");
//			hjm.setFourShiyong(b1+"");
//			hjm.setFourWeishiyong(b2+"");
//			hjm.setFourPingbi(b3+"");
			hjm.setThreeKJshiyong(c1+"");
			hjm.setThreeKJweishiyong(c2+"");
			hjm.setThreeKJpingbi(c3+"");
//			hjm.setWeishiyong(s1+"");
//			hjm.setWeishiyong(s2+"");
			request.setAttribute("hjm", hjm);
			String sqlString="from JlMonitorVoc";
			List<JlMonitorVoc> list2=hms.searchAll(sqlString);
			
			String hql1="from SysParam where paramName='HJ_Client1'";
			List<SysParam> list3=hms.searchAll(hql1);
			if(list3.size()>0){
				SysParam sysParam=(SysParam)list3.get(0);
				request.setAttribute("sysParam", sysParam);
			}
			request.setAttribute("sysQqLineList", list1);
			request.setAttribute("jlMonitorVocList", list2);
			return mapping.findForward("hjMonitorMain5");
	}
	public ActionForward jquerSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    if(user.getIsSuper() == 1){
			    StringBuffer str5=new StringBuffer();
		    	StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    	String hql="from SysHjServer";
		    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
				request.setAttribute("sysQqServerList", sysHjServer);
				for(int i=0;i<sysHjServer.size();i++){
					SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
					if(i==0){
						str5.append(sysServer.getServerName());
						str5.append(","+sysServer.getIp());
						str5.append(","+sysServer.getPort());
					}else{
						str5.append("|");
						str5.append(sysServer.getServerName());
						str5.append(","+sysServer.getIp());
						str5.append(","+sysServer.getPort());
					}
				}
				request.setAttribute("sysQqServerList1", str5.toString());
				List list=hms.searchAllBySql(str.toString());
				List<HjMonitor> list1=new ArrayList();
				Iterator it=list.iterator();
				while(it.hasNext()){
					Object[] obj=( Object[])it.next();
					HjMonitor rm=new HjMonitor();
					rm.setLineNo(obj[0].toString());
					rm.setJy(obj[1].toString());
					rm.setLineType(obj[2].toString());
					if(obj[3]!=null && !obj[3].toString().equals("")){
						rm.setZw(obj[3].toString());
					}
					rm.setHJState(obj[4].toString());
					if(obj[5]!=null && !obj[5].toString().equals("")){
						rm.setHjId(obj[5].toString());
					}
					if(obj[6]!=null && !obj[6].toString().equals("")){
						rm.setAcdBh(obj[6].toString());
					}
					if(obj[7]!=null && !obj[7].toString().equals("")){
						rm.setAcdFr(obj[7].toString());
					}
					if(obj[8]!=null && !obj[8].toString().equals("")){
						rm.setMonitorState(obj[8].toString());
					}
					if(obj[9]!=null && !obj[9].toString().equals("")){
						rm.setMonitorJq(obj[9].toString());
					}
					if(obj[10]!=null && !obj[10].toString().equals("")){
						rm.setMonitorFr(obj[10].toString());
					}
					if(obj[11]!=null && !obj[11].toString().equals("")){
						rm.setMonitorQs(obj[11].toString());
					}
					if(obj[12]!=null && !obj[12].toString().equals("")){
						rm.setMonitorYj(obj[12].toString());
					}
					if(obj[13]!=null && !obj[13].toString().equals("")){
						rm.setMonitorTime(obj[13].toString());
					}
					if(obj[14]!=null && !obj[14].toString().equals("")){
						rm.setMonitorJKBZ(obj[14].toString());
					}
					if(obj[15]!=null && !obj[15].toString().equals("")){
						rm.setMonitorCallID(obj[15].toString());
					}
					if(obj[16]!=null && !obj[16].toString().equals("")){
						 rm.setIp(obj[16].toString());
					}
					if(obj[17]!=null && !obj[17].toString().equals("")){
						rm.setPort(obj[17].toString());
					}
					if(obj[18]!=null && !obj[18].toString().equals("")){
						rm.setAudioPort(obj[18].toString());
					}
					if(obj[19]!=null && !obj[19].toString().equals("")){
						rm.setUserNo(obj[19].toString());
					}
					if(obj[20]!=null && !obj[20].toString().equals("")){
						rm.setMonitorFlagId(obj[20].toString());
					}
					if(obj[21]!=null && !obj[21].toString().equals("")){
						rm.setWriteTxt(obj[21].toString());
					}
					if(obj[22]!=null && !obj[22].toString().equals("")){
						rm.setVideoChan1Server(obj[22].toString());
					}
					if(obj[23]!=null && !obj[23].toString().equals("")){
//						System.out.println("no1="+obj[23].toString());
						rm.setVideoChan1No(obj[23].toString());
					}
					if(obj[24]!=null && !obj[24].toString().equals("")){
						rm.setVideoChan2Server(obj[24].toString());
					}
					if(obj[25]!=null && !obj[25].toString().equals("")){
//						System.out.println("no2="+obj[25].toString());
						rm.setVideoChan2No(obj[25].toString());
					}
					rm.setState(obj[26].toString());
					list1.add(rm);
				}
				
				
				response.setContentType("text/json; charset=utf-8");   
				JSONArray json=JSONArray.fromObject(list1);
				response.getWriter().println(json.toString());
				return null;
		    }else if(user.getGroupNo().trim().equals("Admin")){
		    	StringBuffer str5=new StringBuffer();
		    	StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    	String hql="from SysHjServer";
		    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
				request.setAttribute("sysQqServerList", sysHjServer);
				for(int i=0;i<sysHjServer.size();i++){
					SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
					if(i==0){
						str5.append(sysServer.getServerName());
						str5.append(","+sysServer.getIp());
						str5.append(","+sysServer.getPort());
					}else{
						str5.append("|");
						str5.append(sysServer.getServerName());
						str5.append(","+sysServer.getIp());
						str5.append(","+sysServer.getPort());
					}
				}
				request.setAttribute("sysQqServerList1", str5.toString());
				List list=hms.searchAllBySql(str.toString());
				List<HjMonitor> list1=new ArrayList();
				Iterator it=list.iterator();
				while(it.hasNext()){
					Object[] obj=( Object[])it.next();
					HjMonitor rm=new HjMonitor();
					rm.setLineNo(obj[0].toString());
					rm.setJy(obj[1].toString());
					rm.setLineType(obj[2].toString());
					if(obj[3]!=null && !obj[3].toString().equals("")){
						rm.setZw(obj[3].toString());
					}
					rm.setHJState(obj[4].toString());
					if(obj[5]!=null && !obj[5].toString().equals("")){
						rm.setHjId(obj[5].toString());
					}
					if(obj[6]!=null && !obj[6].toString().equals("")){
						rm.setAcdBh(obj[6].toString());
					}
					if(obj[7]!=null && !obj[7].toString().equals("")){
						rm.setAcdFr(obj[7].toString());
					}
					if(obj[8]!=null && !obj[8].toString().equals("")){
						rm.setMonitorState(obj[8].toString());
					}
					if(obj[9]!=null && !obj[9].toString().equals("")){
						rm.setMonitorJq(obj[9].toString());
					}
					if(obj[10]!=null && !obj[10].toString().equals("")){
						rm.setMonitorFr(obj[10].toString());
					}
					if(obj[11]!=null && !obj[11].toString().equals("")){
						rm.setMonitorQs(obj[11].toString());
					}
					if(obj[12]!=null && !obj[12].toString().equals("")){
						rm.setMonitorYj(obj[12].toString());
					}
					if(obj[13]!=null && !obj[13].toString().equals("")){
						rm.setMonitorTime(obj[13].toString());
					}
					if(obj[14]!=null && !obj[14].toString().equals("")){
						rm.setMonitorJKBZ(obj[14].toString());
					}
					if(obj[15]!=null && !obj[15].toString().equals("")){
						rm.setMonitorCallID(obj[15].toString());
					}
					if(obj[16]!=null && !obj[16].toString().equals("")){
						 rm.setIp(obj[16].toString());
					}
					if(obj[17]!=null && !obj[17].toString().equals("")){
						rm.setPort(obj[17].toString());
					}
					if(obj[18]!=null && !obj[18].toString().equals("")){
						rm.setAudioPort(obj[18].toString());
					}
					if(obj[19]!=null && !obj[19].toString().equals("")){
						rm.setUserNo(obj[19].toString());
					}
					if(obj[20]!=null && !obj[20].toString().equals("")){
						rm.setMonitorFlagId(obj[20].toString());
					}
					if(obj[21]!=null && !obj[21].toString().equals("")){
						rm.setWriteTxt(obj[21].toString());
					}
					if(obj[22]!=null && !obj[22].toString().equals("")){
						rm.setVideoChan1Server(obj[22].toString());
					}
					if(obj[23]!=null && !obj[23].toString().equals("")){
//						System.out.println("no1="+obj[23].toString());
						rm.setVideoChan1No(obj[23].toString());
					}
					if(obj[24]!=null && !obj[24].toString().equals("")){
						rm.setVideoChan2Server(obj[24].toString());
					}
					if(obj[25]!=null && !obj[25].toString().equals("")){
//						System.out.println("no2="+obj[25].toString());
						rm.setVideoChan2No(obj[25].toString());
					}
					rm.setState(obj[26].toString());
					list1.add(rm);
				}
				
				
				response.setContentType("text/json; charset=utf-8");   
				JSONArray json=JSONArray.fromObject(list1);
				response.getWriter().println(json.toString());
				return null;
		    }else{
		    	StringBuffer str1=new StringBuffer("select jq_name from jl_jq where jq_no in (select jq_no from sys_user_jq where group_no='"+user.getGroupNo()+"')");
		    	List list2=hms.searchAllBySql(str1.toString());
		    	StringBuffer str5=new StringBuffer();
		    	if(list2.size()==1){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND sl.Monitor_JQ ='"+list2.get(0)+"') as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
//							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
//							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==2){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
//							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
//							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==3){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
//							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
//							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==4){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==5){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==6){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==7){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==8){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==9){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==10){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==11){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==12){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==13){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==14){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==15){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"' or sl.Monitor_JQ ='"+list2.get(14)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==16){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"' or sl.Monitor_JQ ='"+list2.get(14)+"' or sl.Monitor_JQ ='"+list2.get(15)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==17){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"' or sl.Monitor_JQ ='"+list2.get(14)+"' or sl.Monitor_JQ ='"+list2.get(15)+"' or sl.Monitor_JQ ='"+list2.get(16)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else if(list2.size()==18){
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"' or sl.Monitor_JQ ='"+list2.get(14)+"' or sl.Monitor_JQ ='"+list2.get(15)+"' or sl.Monitor_JQ ='"+list2.get(16)+"' or sl.Monitor_JQ ='"+list2.get(17)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}else{
		    		StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name  AND (sl.Monitor_JQ ='"+list2.get(0)+"' or sl.Monitor_JQ ='"+list2.get(1)+"' or sl.Monitor_JQ ='"+list2.get(2)+"' or sl.Monitor_JQ ='"+list2.get(3)+"' or sl.Monitor_JQ ='"+list2.get(4)+"' or sl.Monitor_JQ ='"+list2.get(5)+"' or sl.Monitor_JQ ='"+list2.get(6)+"' or sl.Monitor_JQ ='"+list2.get(7)+"' or sl.Monitor_JQ ='"+list2.get(8)+"' or sl.Monitor_JQ ='"+list2.get(9)+"' or sl.Monitor_JQ ='"+list2.get(10)+"' or sl.Monitor_JQ ='"+list2.get(11)+"' or sl.Monitor_JQ ='"+list2.get(12)+"' or sl.Monitor_JQ ='"+list2.get(13)+"' or sl.Monitor_JQ ='"+list2.get(14)+"' or sl.Monitor_JQ ='"+list2.get(15)+"' or sl.Monitor_JQ ='"+list2.get(16)+"' or sl.Monitor_JQ ='"+list2.get(17)+"' or sl.Monitor_JQ ='"+list2.get(18)+"')) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
		    		String hql="from SysHjServer";
			    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
					request.setAttribute("sysQqServerList", sysHjServer);
					for(int i=0;i<sysHjServer.size();i++){
						SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
						if(i==0){
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}else{
							str5.append("|");
							str5.append(sysServer.getServerName());
							str5.append(","+sysServer.getIp());
							str5.append(","+sysServer.getPort());
						}
					}
					request.setAttribute("sysQqServerList1", str5.toString());
					List list=hms.searchAllBySql(str.toString());
					List<HjMonitor> list1=new ArrayList();
					Iterator it=list.iterator();
					while(it.hasNext()){
						Object[] obj=( Object[])it.next();
						HjMonitor rm=new HjMonitor();
						rm.setLineNo(obj[0].toString());
						rm.setJy(obj[1].toString());
						rm.setLineType(obj[2].toString());
						if(obj[3]!=null && !obj[3].toString().equals("")){
							rm.setZw(obj[3].toString());
						}
						rm.setHJState(obj[4].toString());
						if(obj[5]!=null && !obj[5].toString().equals("")){
							rm.setHjId(obj[5].toString());
						}
						if(obj[6]!=null && !obj[6].toString().equals("")){
							rm.setAcdBh(obj[6].toString());
						}
						if(obj[7]!=null && !obj[7].toString().equals("")){
							rm.setAcdFr(obj[7].toString());
						}
						if(obj[8]!=null && !obj[8].toString().equals("")){
							rm.setMonitorState(obj[8].toString());
						}
						if(obj[9]!=null && !obj[9].toString().equals("")){
							rm.setMonitorJq(obj[9].toString());
						}
						if(obj[10]!=null && !obj[10].toString().equals("")){
							rm.setMonitorFr(obj[10].toString());
						}
						if(obj[11]!=null && !obj[11].toString().equals("")){
							rm.setMonitorQs(obj[11].toString());
						}
						if(obj[12]!=null && !obj[12].toString().equals("")){
							rm.setMonitorYj(obj[12].toString());
						}
						if(obj[13]!=null && !obj[13].toString().equals("")){
							rm.setMonitorTime(obj[13].toString());
						}
						if(obj[14]!=null && !obj[14].toString().equals("")){
							rm.setMonitorJKBZ(obj[14].toString());
						}
						if(obj[15]!=null && !obj[15].toString().equals("")){
							rm.setMonitorCallID(obj[15].toString());
						}
						if(obj[16]!=null && !obj[16].toString().equals("")){
							 rm.setIp(obj[16].toString());
						}
						if(obj[17]!=null && !obj[17].toString().equals("")){
							rm.setPort(obj[17].toString());
						}
						if(obj[18]!=null && !obj[18].toString().equals("")){
							rm.setAudioPort(obj[18].toString());
						}
						if(obj[19]!=null && !obj[19].toString().equals("")){
							rm.setUserNo(obj[19].toString());
						}
						if(obj[20]!=null && !obj[20].toString().equals("")){
							rm.setMonitorFlagId(obj[20].toString());
						}
						if(obj[21]!=null && !obj[21].toString().equals("")){
							rm.setWriteTxt(obj[21].toString());
						}
						if(obj[22]!=null && !obj[22].toString().equals("")){
							rm.setVideoChan1Server(obj[22].toString());
						}
						if(obj[23]!=null && !obj[23].toString().equals("")){
							System.out.println("no1="+obj[23].toString());
							rm.setVideoChan1No(obj[23].toString());
						}
						if(obj[24]!=null && !obj[24].toString().equals("")){
							rm.setVideoChan2Server(obj[24].toString());
						}
						if(obj[25]!=null && !obj[25].toString().equals("")){
							System.out.println("no2="+obj[25].toString());
							rm.setVideoChan2No(obj[25].toString());
						}
						rm.setState(obj[26].toString());
						list1.add(rm);
					}
					
					
					response.setContentType("text/json; charset=utf-8");   
					JSONArray json=JSONArray.fromObject(list1);
					response.getWriter().println(json.toString());
					return null;
		    	}
		    	
		    	
		    }
	}
	public ActionForward jquerSearch1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    SysUser user=(SysUser)request.getSession().getAttribute("users");
		    if(user==null){
		    	return mapping.findForward("sessionFailure");
		    }
		    StringBuffer str5=new StringBuffer();
	    	StringBuffer str=new StringBuffer("select bb.Line_No, bb.JY, bb.Line_Type, bb.ZW, bb.HJState, bb.HJID, bb.AcdBH, bb.AcdFr, bb.Monitor_State, bb.Monitor_JQ, bb.Monitor_FR, bb.Monitor_QS,  bb.Monitor_YJ, bb.Monitor_Time, bb.Monitor_JKBZ, bb.Monitor_CallID, bb.IP, bb.Port, bb.AudioPort, jqm.User_No, jqm.WebID, jqm.Write_Txt,bb.VideoChan1_Server,bb.VideoChan1_No,bb.VideoChan2_Server,bb.VideoChan2_No,bb.State from  (select sl.Line_No, sl.JY, sl.Line_Type, sl.ZW, sl.HJState, sl.HJID, sl.AcdBH, sl.AcdFr, sl.Monitor_State, sl.Monitor_JQ, sl.Monitor_FR,  sl.Monitor_QS, sl.Monitor_YJ, sl.Monitor_Time, sl.Monitor_JKBZ, sl.Monitor_CallID, ss.IP, ss.Port,ss.AudioPort,sl.VideoChan1_Server,sl.VideoChan1_No,sl.VideoChan2_Server,sl.VideoChan2_No,sl.State from  SYS_HJ_LINE sl, SYS_HJ_SERVER as ss where  sl.JY = ss.Server_Name) as bb left join JL_HJ_MON AS jqm ON bb.Monitor_CallID = jqm.Call_ID AND jqm.User_No='"+user.getUserNo()+"'");
	    	String hql="from SysHjServer";
	    	List<SysHjServer> sysHjServer=hms.searchAll(hql.toString());
			request.setAttribute("sysQqServerList", sysHjServer);
			for(int i=0;i<sysHjServer.size();i++){
				SysHjServer sysServer=(SysHjServer)sysHjServer.get(i);
				if(i==0){
					str5.append(sysServer.getServerName());
					str5.append(","+sysServer.getIp());
					str5.append(","+sysServer.getPort());
				}else{
					str5.append("|");
					str5.append(sysServer.getServerName());
					str5.append(","+sysServer.getIp());
					str5.append(","+sysServer.getPort());
				}
			}
			request.setAttribute("sysQqServerList1", str5.toString());
			List list=hms.searchAllBySql(str.toString());
			List<HjMonitor> list1=new ArrayList();
			Iterator it=list.iterator();
			while(it.hasNext()){
				Object[] obj=( Object[])it.next();
				HjMonitor rm=new HjMonitor();
				rm.setLineNo(obj[0].toString());
				rm.setJy(obj[1].toString());
				rm.setLineType(obj[2].toString());
				if(obj[3]!=null && !obj[3].toString().equals("")){
					rm.setZw(obj[3].toString());
				}
				rm.setHJState(obj[4].toString());
				if(obj[5]!=null && !obj[5].toString().equals("")){
					rm.setHjId(obj[5].toString());
				}
				if(obj[6]!=null && !obj[6].toString().equals("")){
					rm.setAcdBh(obj[6].toString());
				}
				if(obj[7]!=null && !obj[7].toString().equals("")){
					rm.setAcdFr(obj[7].toString());
				}
				if(obj[8]!=null && !obj[8].toString().equals("")){
					rm.setMonitorState(obj[8].toString());
				}
				if(obj[9]!=null && !obj[9].toString().equals("")){
					rm.setMonitorJq(obj[9].toString());
				}
				if(obj[10]!=null && !obj[10].toString().equals("")){
					rm.setMonitorFr(obj[10].toString());
				}
				if(obj[11]!=null && !obj[11].toString().equals("")){
					rm.setMonitorQs(obj[11].toString());
				}
				if(obj[12]!=null && !obj[12].toString().equals("")){
					rm.setMonitorYj(obj[12].toString());
				}
				if(obj[13]!=null && !obj[13].toString().equals("")){
					rm.setMonitorTime(obj[13].toString());
				}
				if(obj[14]!=null && !obj[14].toString().equals("")){
					rm.setMonitorJKBZ(obj[14].toString());
				}
				if(obj[15]!=null && !obj[15].toString().equals("")){
					rm.setMonitorCallID(obj[15].toString());
				}
				if(obj[16]!=null && !obj[16].toString().equals("")){
					 rm.setIp(obj[16].toString());
				}
				if(obj[17]!=null && !obj[17].toString().equals("")){
					rm.setPort(obj[17].toString());
				}
				if(obj[18]!=null && !obj[18].toString().equals("")){
					rm.setAudioPort(obj[18].toString());
				}
				if(obj[19]!=null && !obj[19].toString().equals("")){
					rm.setUserNo(obj[19].toString());
				}
				if(obj[20]!=null && !obj[20].toString().equals("")){
					rm.setMonitorFlagId(obj[20].toString());
				}
				if(obj[21]!=null && !obj[21].toString().equals("")){
					rm.setWriteTxt(obj[21].toString());
				}
				if(obj[22]!=null && !obj[22].toString().equals("")){
					rm.setVideoChan1Server(obj[22].toString());
				}
				if(obj[23]!=null && !obj[23].toString().equals("")){
//					System.out.println("no1="+obj[23].toString());
					rm.setVideoChan1No(obj[23].toString());
				}
				if(obj[24]!=null && !obj[24].toString().equals("")){
					rm.setVideoChan2Server(obj[24].toString());
				}
				if(obj[25]!=null && !obj[25].toString().equals("")){
//					System.out.println("no2="+obj[25].toString());
					rm.setVideoChan2No(obj[25].toString());
				}
				rm.setState(obj[26].toString());
				list1.add(rm);
			}
			
			
			response.setContentType("text/json; charset=utf-8");   
			JSONArray json=JSONArray.fromObject(list1);
			response.getWriter().println(json.toString());
			return null;
	}
	//添加监听记录
	public ActionForward addSaveMonitorInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String monitorCallID=request.getParameter("monitorCallID");

			StringBuffer str=new StringBuffer("from JlHjMon jm where jm.userNo='"+user.getUserNo());
			str.append("' and jm.callId='"+monitorCallID+"'");
			List<JlHjMon> list=hms.searchAll(str.toString());
			if(list.size()>0){

			}else{

				List<JlHjMon> list1=new ArrayList<JlHjMon>();
				JlHjMon jlQqMon=new JlHjMon();
				jlQqMon.setCallId(monitorCallID);
				jlQqMon.setUserNo(user.getUserNo());
				jlQqMon.setUserName(user.getUserName());
				jlQqMon.setWriteTxtLx("无摘要");
				hms.save(jlQqMon);
			}
			
			return null;
	}
	//添加保存监听注释
	public ActionForward addSaveMonitorFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String ingmonitorCallID=request.getParameter("ingmonitorCallID");
			String writeTxt=java.net.URLDecoder.decode((String)request.getParameter("writeTxt"),"UTF-8");
			StringBuffer str=new StringBuffer("from JlHjMon jm where jm.userNo='"+user.getUserNo());
			str.append("' and jm.callId='"+ingmonitorCallID+"'");
			List<JlHjMon> list=hms.searchAll(str.toString());
			if(list.size()>0 && writeTxt!=null && !writeTxt.equals("")){
				String sql2="update JL_HJ_MON set Write_Txt='"+writeTxt+"',Write_Txt_Lx='有摘要' where Call_ID='"+ingmonitorCallID+"' and User_No='"+user.getUserNo()+"'";
				hms.executeUpdate(sql2);
				JSONArray json=JSONArray.fromObject(-1); 
				response.getWriter().println(json.toString());
				return null;
			}else{
				
			}
			return null;
	}
	//修改保存监听注释
	public ActionForward updateSaveMonitorFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			String ingmonitorFlagId=request.getParameter("ingmonitorFlagId");
			String writeTxt=java.net.URLDecoder.decode((String)request.getParameter("writeTxt"),"UTF-8");
//			JlHjMon jlQqMon=(JlHjMon)hms.findByIdLong(JlHjMon.class, Long.parseLong(ingmonitorFlagId));
			StringBuffer str=new StringBuffer(" update JlHjMon jqm set jqm.writeTxt='");
			if(writeTxt!=""){
				str.append(writeTxt+"'");
			}else{
				str.append("'");
			}
			str.append(" where jqm.webId=?");
			Object[] obj={Long.parseLong(ingmonitorFlagId)};
			hms.updates(str.toString(), obj);
			JSONArray json=JSONArray.fromObject(null);
			response.getWriter().println(json.toString());
			return null;
	}
	public ActionForward spMonitor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String videoChan1Server=request.getParameter("videoChan1Server");
			String videoChan1No=request.getParameter("videoChan1No");
			String videoChan2No=request.getParameter("videoChan2No");
			String hql="from SysHjVideo where serverName='"+videoChan1Server+"'";
			List<SysHjVideo> list=hms.searchAll(hql);
			if(list.size()>0){
				SysHjVideo sysHjVideo=(SysHjVideo)list.get(0);
				request.setAttribute("sysHjVideo", sysHjVideo);
			}
			request.setAttribute("videoChan1No", videoChan1No);
			request.setAttribute("videoChan2No", videoChan2No);
			return mapping.findForward("spMonitor");
	}
	public ActionForward endKj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String hjid=request.getParameter("hjid");
			JlHjDj jlHjDj=(JlHjDj)hms.findById(JlHjDj.class, Long.parseLong(hjid));
			jlHjDj.setState(3);
			hms.update(jlHjDj);
			JSONArray jsonArray=JSONArray.fromObject(0);
			response.getWriter().println(jsonArray.toString());
			return null;
	}
	//保存修改的会见时长
	public ActionForward insertAddTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user=(SysUser)request.getSession().getAttribute("users");
			if(user==null){
				return mapping.findForward("sessionFailure");
			}
			
			String lineCh=java.net.URLDecoder.decode((String)request.getParameter("lineCh"),"UTF-8");
			String serverNameCh=java.net.URLDecoder.decode((String)request.getParameter("serverNameCh"),"UTF-8");
			String monitorCallIDCh=java.net.URLDecoder.decode((String)request.getParameter("monitorCallIDCh"),"UTF-8");
			String time2=java.net.URLDecoder.decode((String)request.getParameter("time2"),"UTF-8");
			
			List<SysHjLine> list1=null;
			StringBuffer str1=new StringBuffer("from SysHjLine sys where sys.lineNo="+lineCh+" and sys.jy='"+serverNameCh+"'");
			list1=hms.searchAll(str1.toString());
			SysHjLine sysHjLine=(SysHjLine)list1.get(0);
			
			StringBuffer hql=new StringBuffer("from SysUser su where su.isAddTime=1 and su.userNo='");
		 	hql.append(user.getUserNo()+"'");
		 	List list11=hms.searchAll(hql.toString());
		 	
			if(sysHjLine.getMonitorState().equals("通话") && list11.size()>0){
				JlHjMonitorTimeAdd jlHjMonitorTimeAdd=new JlHjMonitorTimeAdd();
				jlHjMonitorTimeAdd.setLineNo(Integer.parseInt(lineCh));
				jlHjMonitorTimeAdd.setJy(serverNameCh);
				jlHjMonitorTimeAdd.setCallId(monitorCallIDCh);
				jlHjMonitorTimeAdd.setAddTime(Integer.parseInt(time2)*60);
				jlHjMonitorTimeAdd.setState(0);
				hms.save(jlHjMonitorTimeAdd);

//				System.out.println(jlHjMonitorTimeAdd.getWebId());
//				Thread.sleep(5000);
				boolean flag=false;
				Long begin=System.currentTimeMillis();
				Long end=begin+10000;
				Long begining=begin;
				List<JlHjMonitorTimeAdd> list=null;
				while(flag==false && begining<=end){
					if(begin+1000==begining){
						StringBuffer str=new StringBuffer("from JlHjMonitorTimeAdd time where time.webId="+jlHjMonitorTimeAdd.getWebId()+"");
						list=hms.searchAll(str.toString());
						JlHjMonitorTimeAdd jlHjMonitorTimeAdd1=(JlHjMonitorTimeAdd)list.get(0);
						if(jlHjMonitorTimeAdd1.getState()==1){
							flag=true;
						}
					    begin=begining;
					}
					begining=System.currentTimeMillis();
				}
				if(flag==true){
					JSONArray json=JSONArray.fromObject(0);
					response.getWriter().println(json.toString());
					return null;
				}else{
					JSONArray json=JSONArray.fromObject(1);
					response.getWriter().println(json.toString());
					return null;
				}
			}else{
				JSONArray json=JSONArray.fromObject(1);
				response.getWriter().println(json.toString());
				return null;
			}

			
			
			
	}
}
