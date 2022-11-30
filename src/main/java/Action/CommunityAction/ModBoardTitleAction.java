package Action.CommunityAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.CommunitySVC.ModBoardTitleService;
import VO.ActionForward;
import VO.CommunityBoards;

public class ModBoardTitleAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModBoardTitleService modBoardTitleService = new ModBoardTitleService();
		
		String boardName = request.getParameter("modBoardName");
		int boardNum = Integer.parseInt(request.getParameter("modBoardNum"));
		int commCode = Integer.parseInt(request.getParameter("modBoardCommCode"));
		
		CommunityBoards commBoard = new CommunityBoards(commCode, boardNum, boardName, null, 0);
		
		boolean isUdateSuccess = false;
		
		isUdateSuccess = modBoardTitleService.modBoardName(commBoard);
		
		ActionForward forward = null;
		
		if(isUdateSuccess) {
			forward = new ActionForward("/getCommInfo.square?commCode=" + commCode, true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시판 이름 변경에 실패 하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}

		return forward;
	}

}
