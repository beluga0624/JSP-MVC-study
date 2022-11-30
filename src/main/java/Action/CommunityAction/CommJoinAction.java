package Action.CommunityAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.CommunitySVC.CommJoinService;
import VO.ActionForward;
import VO.CommunityMembersVO;

public class CommJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommJoinService commJoinService = new CommJoinService();
		
		int commCode = Integer.parseInt(request.getParameter("commCode"));
		
		CommunityMembersVO commMember = new CommunityMembersVO(request.getParameter("commJoinUserid"),
															   commCode,
															   request.getParameter("activityName"),
															   request.getParameter("answer1"),
															   request.getParameter("answer2"),
															   request.getParameter("answer3"),
															   null,
															   null);
		
		boolean isJoinSuccess = commJoinService.commJoin(commMember);
		
		HttpSession session = request.getSession();
		
		ActionForward forward = null;
		
		if(isJoinSuccess) {
			
			session.setAttribute("commMember", commMember);
			forward = new ActionForward("/getCommInfo.square?commCode=" + commCode, true);
			
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('커뮤니티 가입 실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
