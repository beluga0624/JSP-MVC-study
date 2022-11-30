package Action.MemberAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.MemberSVC.ChkMemberService;
import VO.ActionForward;
import VO.MemberVO;

public class RechkMemberAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ChkMemberService chkMemberService = new ChkMemberService();
		
		HttpSession session = request.getSession();
		
		String userid = (String)session.getAttribute("userid");
		String userpw = request.getParameter("userpw");
		String oper = request.getParameter("oper");
		MemberVO member = null;
		ActionForward forward = null;
		
		member = chkMemberService.chkMember(userid, userpw);
		
		
			if(member != null) {
				
				if(oper.equals("change")) {
					request.setAttribute("isRightUser", "true");
					forward = new ActionForward("/square/changePw.jsp", false);
				}else {
					request.setAttribute("isRightUser", "true");
					forward = new ActionForward("/withdraw.mem", false);
				}
				
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		return forward;	
	}
}
