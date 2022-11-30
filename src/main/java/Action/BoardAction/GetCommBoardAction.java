package Action.BoardAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.BoardSVC.GetCommBoardService;
import VO.ActionForward;
import VO.CommBoardVO;
import VO.PageInfo;

public class GetCommBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GetCommBoardService getCommBoardService = new GetCommBoardService();
		
		String boardName = request.getParameter("boardName");
		int commCode = Integer.parseInt(request.getParameter("commCode"));
		
		ArrayList<CommBoardVO> boardList = null;
		
		int page = 1; 
		int limit = 15; 
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = getCommBoardService.getListCount(commCode, boardName);

		int maxPage = (int)((double)listCount/limit + 0.95);
		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 +1;
		int endPage = startPage + 9;
		
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);

		boardList = getCommBoardService.getBoardList(commCode, boardName, page, limit);
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		
		session.setAttribute("boardName", boardName);
		session.setAttribute("commCode", commCode);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("boardList", boardList);
		
		forward = new ActionForward("/square/commBoard.jsp", false);

		return forward;
	}

}
