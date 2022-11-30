package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import Action.BoardAction.DeleteBoardAction;
import Action.BoardAction.DeleteCommReplyAction;
import Action.BoardAction.DeleteReplyAction;
import Action.BoardAction.GetCommBoardAction;
import Action.BoardAction.GetCommBoardDetailAction;
import Action.BoardAction.GetFaqAction;
import Action.BoardAction.GetModifyBoardFormAction;
import Action.BoardAction.GetNoticeAction;
import Action.BoardAction.GetSiteBoardDetailAction;
import Action.BoardAction.ModCommReplyAction;
import Action.BoardAction.ModReplyAction;
import Action.BoardAction.ModifySiteBoardAction;
import Action.BoardAction.RegCommReplyAction;
import Action.BoardAction.RegFaqAction;
import Action.BoardAction.RegNoticeAction;
import Action.BoardAction.RegReplyAction;
import Action.BoardAction.WriteCommBoardAction;
import VO.ActionForward;

@WebServlet("*.board")
public class BoardFrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public BoardFrontController() {
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
		
		//공지사항 글목록 가져오기
		if(command.equals("/getNotice.board")) {
			action = new GetNoticeAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//공지사항 글목록 가져오기
		if(command.equals("/getFAQ.board")) {
			action = new GetFaqAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//공지사항 등록
		if(command.equals("/regNotice.board")) {
			action = new RegNoticeAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//FAQ 등록
		if(command.equals("/regFaq.board")) {
			action = new RegFaqAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//사이트 게시판 글보기
		if(command.equals("/getSiteBoardDetail.board")) {
			action = new GetSiteBoardDetailAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//댓글 등록
		if(command.equals("/regReply.board")) {
			action = new RegReplyAction();

			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 게시글 댓글 등록 
		if(command.equals("/regCommReply.board")) {
			action = new RegCommReplyAction();

			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//댓글 수정
		if(command.equals("/modReply.board")) {
			action = new ModReplyAction();

			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 댓글 수정
		if(command.equals("/modCommReply.board")) {
			action = new ModCommReplyAction();

			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//댓글 삭제
		if(command.equals("/delReply.board")) {
			action = new DeleteReplyAction();

			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 댓글 삭제
		if(command.equals("/delCommReply.board")) {
			action = new DeleteCommReplyAction();

			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//게시글 삭제
		if(command.equals("/deleteBoard.board")) {
			action = new DeleteBoardAction();

			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//게시글 수정 양식 가져오기
		if(command.equals("/modBoard.board")) {
			action = new GetModifyBoardFormAction();

			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		//게시글 수정 프로세스
		if(command.equals("/modSiteBoard.board")) {
			action = new ModifySiteBoardAction();

			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 특정 게시판 리스트 보기 
		if(command.equals("/commBoard.board")) {
			action = new GetCommBoardAction();

			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 게시판 글쓰기
		if(command.equals("/writeCommBoard.board")) {
			action = new WriteCommBoardAction();

			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//커뮤니티 게시판 글보기
		if(command.equals("/getCommBoardDetail.board")) {
			action = new GetCommBoardDetailAction();
			
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
