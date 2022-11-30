package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import Action.MemberAction.ChkMemberAction;
import Action.MemberAction.LogoutAction;
import Action.MemberAction.MemberJoinAction;
import Action.MemberAction.MemberLoginAction;
import Action.MemberAction.RechkMemberAction;
import Action.MemberAction.UpdateMemberAction;
import Action.MemberAction.UpdatePwAction;
import Action.MemberAction.WithdrawAction;
import SVC.MemberSVC.CheckEmailService;
import SVC.MemberSVC.CheckIDService;
import SVC.MemberSVC.CheckPhoneService;
import SVC.MemberSVC.FindPasswdService;
import SVC.MemberSVC.FindUseridService;
import VO.ActionForward;


@WebServlet("*.mem")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/memberJoin.mem")) {
			action = new MemberJoinAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//아이디 중복 체크
		if(command.equals("/checkID.mem")) {
			response.setContentType("text/html; charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			
			CheckIDService checkIDService = new CheckIDService();
			
			String userid = request.getParameter("userid");
			
			boolean isIDExist = false;
			
			isIDExist = checkIDService.checkID(userid);
			
			if(isIDExist) {
				response.getWriter().write("unavailable");
			}else {
				response.getWriter().write("available");
			}
		}
		
		//이메일 중복 체크
		if(command.equals("/checkEmail.mem")) {
			response.setContentType("text/html; charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			
			CheckEmailService checkEmailService = new CheckEmailService();
			
			String email = request.getParameter("email");
			
			boolean isIDExist = false;
			
			isIDExist = checkEmailService.checkEmail(email);
			
			if(isIDExist) {
				response.getWriter().write("unavailable");
			}else {
				response.getWriter().write("available");
			}
		}
		
		//전화번호 중복 체크
		if(command.equals("/checkPhone.mem")) {
			response.setContentType("text/html; charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			
			CheckPhoneService checkPhoneService = new CheckPhoneService();
			
			String phone = request.getParameter("phone");
			
			boolean isIDExist = false;
			
			isIDExist = checkPhoneService.checkPhone(phone);
			
			if(isIDExist) {
				response.getWriter().write("unavailable");
			}else {
				response.getWriter().write("available");
			}
		}
		
		if(command.equals("/memberLogin.mem")) {
			action = new MemberLoginAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(command.equals("/logout.mem")) {
			action = new LogoutAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(command.equals("/checkMember.mem")) {
			action = new ChkMemberAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(command.equals("/recheckMember.mem")) {
			action = new RechkMemberAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(command.equals("/updateMemberInfo.mem")) {
			action = new UpdateMemberAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(command.equals("/updatePw.mem")) {
			action = new UpdatePwAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//회원탈퇴
		if(command.equals("/withdraw.mem")) {
			action = new WithdrawAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//아이디 찾기
		if(command.equals("/findUserid.mem")) {
			response.setContentType("text/html; charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			
			FindUseridService findUseridService = new FindUseridService();
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			String userid = "";
			
			userid = findUseridService.findUserid(name, email);
			
			response.getWriter().write(userid);
		}
		
		//비밀번호 찾기(유저체크)
		if(command.equals("/findPasswd.mem")) {
			response.setContentType("text/html; charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			
			FindPasswdService findPasswdService = new FindPasswdService();
			
			String userid = request.getParameter("userid");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			
			int result = 0;
			String existUser = "";
			
			result = findPasswdService.chkInfo(userid, email, phone);
			
			if(result == 1) {
				existUser = "yes";
			}else {
				existUser = "no";
			}
			
			response.getWriter().write(existUser);
		}
		
		//비밀번호 찾기(재설정)
		if(command.equals("/resetPasswd.mem")) {
			action = new UpdatePwAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

}
