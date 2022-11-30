package Action.CommunityAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.CommunitySVC.CommMemberUpdateService;
import VO.ActionForward;
import VO.CommunityMembersVO;

public class CommMemberUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommMemberUpdateService commMemberUpdateService = new CommMemberUpdateService();
		
		int commCode = Integer.parseInt(request.getParameter("commCode"));
		
		CommunityMembersVO commMember = new CommunityMembersVO(request.getParameter("commUserid"),
															   commCode,
															   request.getParameter("activityName"),
															   request.getParameter("answer1"),
															   request.getParameter("answer2"),
															   request.getParameter("answer3"),
															   null,
															   null);
		
		boolean isUpdateSuccess = commMemberUpdateService.updateMemberInfo(commMember);
		
		ActionForward forward = null;
		
		if(isUpdateSuccess) {
			forward = new ActionForward("/getCommInfo.square?commCode=" + commCode, true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('커뮤니티 회원정보 수정에 실패 하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
