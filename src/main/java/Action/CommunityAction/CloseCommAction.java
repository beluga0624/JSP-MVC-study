package Action.CommunityAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.CommunitySVC.CloseCommService;
import VO.ActionForward;

public class CloseCommAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CloseCommService closeCommService = new CloseCommService();
		
		int commCode = Integer.parseInt(request.getParameter("commCode"));
		
		boolean isCommClosed = closeCommService.closeComm(commCode);
		
		ActionForward forward = null;
		
		if(isCommClosed) {
			forward = new ActionForward("/main.square", true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('커뮤니티 폐쇄 실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}
	
}
