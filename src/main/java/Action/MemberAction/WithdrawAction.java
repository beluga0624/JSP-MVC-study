package Action.MemberAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.MemberSVC.WithdrawService;
import VO.ActionForward;

public class WithdrawAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WithdrawService withdrawService = new WithdrawService();
		
		HttpSession session = request.getSession();
		
		String userid = (String)session.getAttribute("userid");
		String isRightUser = (String)request.getAttribute("isRightUser");
		
		if(isRightUser == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 요청입니다.\n다시 시도하세요');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		boolean isWithdrawSuccess = withdrawService.withdraw(userid);
		
		ActionForward forward = null;
		
		if(isWithdrawSuccess) {
			session.invalidate();
			forward = new ActionForward("/square/main.jsp",true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원 탈퇴 처리에 실패하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
