package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import Action.CommunityAction.AddBoardAction;
import Action.CommunityAction.CloseCommAction;
import Action.CommunityAction.CommJoinAction;
import Action.CommunityAction.CommMemberUpdateAction;
import Action.CommunityAction.CreateCommAction;
import Action.CommunityAction.GetCommInfoAction;
import Action.CommunityAction.GetCommListAction;
import Action.CommunityAction.LoadMainAction;
import Action.CommunityAction.ModBoardOrderAction;
import Action.CommunityAction.ModBoardTitleAction;
import Action.CommunityAction.ModifyCommInfoAction;
import Action.CommunityAction.OrderCommAction;
import Action.CommunityAction.WithdrawFromCommAction;
import SVC.CommunitySVC.CheckActNameService;
import VO.ActionForward;

@WebServlet("*.square")
public class CommunityFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CommunityFrontController() {
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
		
		//메인화면
		if(command.equals("/main.square")) {
			action = new LoadMainAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 개설
		else if(command.equals("/createComm.square")) {
			action = new CreateCommAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 리스트 가져오기
		else if(command.equals("/getCommList.square")) {
			action = new GetCommListAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 정보 수정
		else if(command.equals("/modifyCommInfo.square")) {
			action = new ModifyCommInfoAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 정보 가져오기
		else if(command.equals("/getCommInfo.square")) {
			action = new GetCommInfoAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 가입
		else if(command.equals("/communityJoin.square")) {
			action = new CommJoinAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 가입정보 수정
		else if(command.equals("/communityMemberUpdate.square")) {
			action = new CommMemberUpdateAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 탈퇴
		else if(command.equals("/withdrawFromComm.square")) {
			action = new WithdrawFromCommAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//게시판 리스트 수정
		else if(command.equals("/modBoardTitle.square")) {
			action = new ModBoardTitleAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//게시판 추가
		else if(command.equals("/addBoard.square")) {
			action = new AddBoardAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 게시판 순서 재지정
		else if(command.equals("/modifyOrder.square")) {
			action = new ModBoardOrderAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 활동명 중복 체크
		if(command.equals("/checkActName.square")) {
			response.setContentType("text/html; charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			
			CheckActNameService checkActNameService = new CheckActNameService();
			
			int commCode = Integer.parseInt(request.getParameter("commCode"));
			String actName = request.getParameter("activityName");
			
			boolean isIDExist = false;
			
			isIDExist = checkActNameService.checkActName(commCode, actName);
			
			if(isIDExist) {
				response.getWriter().write("unavailable");
			}else {
				response.getWriter().write("available");
			}
		}
		
		//커뮤니티 정렬
		else if(command.equals("/orderComm.square")) {
			action = new OrderCommAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		//커뮤니티 폐쇄
		else if(command.equals("/closeComm.square")) {
			action = new CloseCommAction();
			
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
