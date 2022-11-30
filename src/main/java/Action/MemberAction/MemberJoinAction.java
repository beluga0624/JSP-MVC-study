package Action.MemberAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.MemberSVC.MemberJoinService;
import VO.ActionForward;
import VO.MemberVO;

public class MemberJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberJoinService memberJoinService = new MemberJoinService();
		
		MemberVO member = new MemberVO(request.getParameter("useridJoin"),
										 request.getParameter("userpw1"),
										 request.getParameter("userName"),
										 request.getParameter("birthDate"),
										 request.getParameter("gender"),
										 request.getParameter("phone"),
										 request.getParameter("email"),
										 Integer.parseInt(request.getParameter("postcd")),
										 request.getParameter("addr1"),
										 request.getParameter("addr2"),
										 null,
										 null);
		
		boolean isJoinSuccess = memberJoinService.joinMember(member);
		ActionForward forward = null;
		
		if(isJoinSuccess) {
			request.setAttribute("isJoinSuccess", "true");
			forward = new ActionForward("/main.square", false);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입에 실패 하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}
	
}
