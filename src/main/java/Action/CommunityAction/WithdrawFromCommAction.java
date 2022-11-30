package Action.CommunityAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.CommunitySVC.WithdrawFromCommService;
import VO.ActionForward;

public class WithdrawFromCommAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		WithdrawFromCommService withdrawFromCommService = new WithdrawFromCommService();
		
		String userid = request.getParameter("useridForWithdraw");
		int commCode = Integer.parseInt(request.getParameter("commCode"));
		
		boolean isWithdrawSuccess =  withdrawFromCommService.withdrawComm(commCode, userid);
		
		ActionForward forward = null;
		
		if(isWithdrawSuccess) {
			forward = new ActionForward("/getCommInfo.square?commCode=" + commCode , true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('커뮤니티 탈퇴 처리에 실패 하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
