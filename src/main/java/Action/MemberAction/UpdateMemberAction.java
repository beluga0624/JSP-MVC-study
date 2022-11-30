package Action.MemberAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.MemberSVC.UpdateMemberService;
import VO.ActionForward;
import VO.MemberVO;

public class UpdateMemberAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UpdateMemberService updateMemberService = new UpdateMemberService();
		
		boolean isUpdateSuccess = false;
		
		MemberVO member = new MemberVO(request.getParameter("useridUpdate"),
										 null,
										 request.getParameter("userNameU"),
										 request.getParameter("birthDateU"),
										 request.getParameter("genderU"),
										 request.getParameter("phoneU"),
										 request.getParameter("emailU"),
										 Integer.parseInt(request.getParameter("postcdU")),
										 request.getParameter("addr1U"),
										 request.getParameter("addr2U"),
										 null,
										 null);
		
		isUpdateSuccess = updateMemberService.updateMember(member);
		
		ActionForward forward = null;
			
		if(isUpdateSuccess) {
			request.setAttribute("isUpdateSuccess", "true");
			forward = new ActionForward("/square/main.jsp", false);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보 수정에 실패 하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}
}
