package Action.MemberAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.CommunitySVC.CloseCommService;
import SVC.CommunitySVC.GetCommInfoService;
import SVC.CommunitySVC.GetCommMemberInfoService;
import SVC.MemberSVC.ChkMemberService;
import VO.ActionForward;
import VO.CommunityMembersVO;
import VO.CommunityVO;
import VO.MemberVO;

public class ChkMemberAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ChkMemberService chkMemberService = new ChkMemberService();
		
		HttpSession session = request.getSession();
		
		String userid = (String)session.getAttribute("userid");
		String userpw = request.getParameter("userpw");
		String oper = request.getParameter("oper");
		int commCode = 0;
		if(request.getParameter("commCode") != null) {
			commCode = Integer.parseInt(request.getParameter("commCode"));
		}
		MemberVO member = null;
		ActionForward forward = null;
		
		member = chkMemberService.chkMember(userid, userpw);
		
		if(member != null) {
			if(oper == null) {
				request.setAttribute("memberInfo", member);
				forward = new ActionForward("/square/memberInfo.jsp", false);
			}else if(oper.equals("updateForComm")) {
				GetCommMemberInfoService getCommMemberInfoService = new GetCommMemberInfoService(); 
				
				CommunityMembersVO commMember = getCommMemberInfoService.getCommMember(userid, commCode);
				
				request.setAttribute("commMember", commMember);
				
				forward = new ActionForward("/square/updateCommMemberInfo.jsp", false);
			}else if(oper.equals("withdrawFromComm")) {
				GetCommMemberInfoService getCommMemberInfoService = new GetCommMemberInfoService(); 
				
				CommunityMembersVO commMember = getCommMemberInfoService.getCommMember(userid, commCode);
				
				request.setAttribute("commMember", commMember);
				
				forward = new ActionForward("/square/withdrawFromComm.jsp", false);
			}else if(oper.equals("commMod")) {
				GetCommInfoService getCommInfoService = new GetCommInfoService();
				
				CommunityVO comm = getCommInfoService.getCommInfo(commCode);
				
				request.setAttribute("community", comm);
				
				forward = new ActionForward("/square/modifyCommInfo.jsp", false);
			}else if(oper.equals("close")) {
				
				forward = new ActionForward("/closeComm.square?commCode=" + commCode, false);
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
