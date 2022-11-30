package Action.MemberAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.MemberSVC.MemberLoginService;
import VO.ActionForward;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberLoginService memberLoginService = new MemberLoginService();
		
		String userid = request.getParameter("useridLogin");
		String userpw = request.getParameter("userpwLogin");
		boolean isMember = false;
		
		isMember = memberLoginService.checkMemberInfo(userid, userpw);
		
		ActionForward forward = null;
		
		if(isMember) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(3*60*60);
			session.setAttribute("userid", userid);
			forward = new ActionForward("/main.square", true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디나 비밀번호를 확인하세요.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
