package Action.MemberAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.MemberSVC.UpdatePwService;
import VO.ActionForward;

public class UpdatePwAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UpdatePwService updatePwService = new UpdatePwService();
		
		HttpSession session = request.getSession();
		
		String userid = null;
		boolean isNewWindow = false;
		
		if(session.getAttribute("userid") != null) {
			userid = (String)session.getAttribute("userid");
		}else {
			userid = request.getParameter("userid");
			isNewWindow = true;
		}
		
		String userpw = request.getParameter("pw1");
		
		boolean isUpdateSucces = false;
		
		isUpdateSucces = updatePwService.updateUserPw(userid, userpw);
		
		ActionForward forward = null;
		
		if(isUpdateSucces) {
			request.setAttribute("isPwUpdateSucces", "true");
			
			if(isNewWindow) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 변경되었습니다.');");
				out.println("window.close();");
				out.println("</script>");
			}else {
				forward = new ActionForward("/square/main.jsp", false);
			}

		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 변경에 실패 하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
