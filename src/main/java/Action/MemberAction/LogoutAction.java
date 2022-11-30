package Action.MemberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import VO.ActionForward;

public class LogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		request.setAttribute("logout", "true");
		
		ActionForward forward = new ActionForward("/main.square", false);
		
		return forward;
	}
	
}
