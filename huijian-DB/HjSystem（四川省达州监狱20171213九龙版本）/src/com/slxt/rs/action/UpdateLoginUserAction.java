package com.slxt.rs.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.slxt.rs.model.JlYj;
import com.slxt.rs.model.SysUser;
import com.slxt.rs.svc.UpdateLoginUserService;


public class UpdateLoginUserAction extends DispatchAction{
    private UpdateLoginUserService ulus;
    
	public void setUlus(UpdateLoginUserService ulus) {
		this.ulus = ulus;
	}
	//修改用户密码
	public ActionForward updatePasswordUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SysUser user1=(SysUser)request.getSession().getAttribute("users");
			if(user1==null){
				return mapping.findForward("sessionFailure");
			}
			HttpSession session=request.getSession();
			session.removeAttribute("users");
			String password=request.getParameter("password1");
			String userId=request.getParameter("userId");
			SysUser sysUser=(SysUser)ulus.findById(SysUser.class, Integer.parseInt(userId));
			StringBuffer str=new StringBuffer("update from SysUser su set su.userPwd='");
			str.append(password+"'").append(" where su.webId=?");
			Object[] obj={Integer.parseInt(userId)};
			ulus.updates(str.toString(), obj);
			SysUser user=(SysUser)ulus.findById(SysUser.class, Integer.parseInt(userId));
			request.getSession().setAttribute("users", user);
			return mapping.findForward("success");
	}
	//修改狱警密码
	public ActionForward updatePasswordYj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			JlYj jlYj1=(JlYj)request.getSession().getAttribute("jlYj");
			if(jlYj1==null){
				return mapping.findForward("sessionFailure");
			}
			HttpSession session=request.getSession();
			session.removeAttribute("jlYj");
			String password=request.getParameter("password1");
			String userId=request.getParameter("userId");
			StringBuffer str=new StringBuffer("update from JlYj su set su.telePwd='");
			str.append(password+"'").append(" where su.webId=?");
			Object[] obj={Integer.parseInt(userId)};
			ulus.updates(str.toString(), obj);
			JlYj jlYj=(JlYj)ulus.findById(JlYj.class, Integer.parseInt(userId));
			request.getSession().setAttribute("jlYj", jlYj);
			JSONArray json=JSONArray.fromObject(null);
			response.getWriter().println(json.toString());
			return null;
	}
	//注销用户
	public ActionForward closeSessionUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    request.getSession().removeAttribute("users");
			JSONArray json=JSONArray.fromObject(null);
			response.getWriter().println(json.toString());
		    return null;
	}
	//注销狱警
	public ActionForward closeSessionYj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    request.getSession().removeAttribute("jlYj");
			JSONArray json=JSONArray.fromObject(null);
			response.getWriter().println(json.toString());
		    return null;
	}
	
}
