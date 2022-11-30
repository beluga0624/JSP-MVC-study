package Action.CommunityAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.CommunitySVC.AddBoardService;
import VO.ActionForward;
import VO.CommunityBoards;

public class AddBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AddBoardService addBoardService = new AddBoardService();
		
		boolean isAddSuccess = false;
		
		int commCode = Integer.parseInt(request.getParameter("newCommCode"));
		
		CommunityBoards commBoard = new CommunityBoards(commCode,
														0, 
														request.getParameter("newBoardName"),
														request.getParameter("newCategory"),
														0);
		
		isAddSuccess = addBoardService.addBoard(commBoard);
		
		ActionForward forward = null;
		
		if(isAddSuccess) {
			forward = new ActionForward("/getCommInfo.square?commCode=" + commCode, false);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시판 생성에 실패 하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
