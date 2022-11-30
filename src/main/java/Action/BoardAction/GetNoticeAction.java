package Action.BoardAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.BoardSVC.GetNoticeService;
import VO.ActionForward;
import VO.PageInfo;
import VO.SiteBoardVO;

public class GetNoticeAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GetNoticeService getNoticeService = new GetNoticeService();
		
		ArrayList<SiteBoardVO> boardList = null;
		
		int page = 1; 
		int limit = 15; 
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = getNoticeService.getListCount();

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

		boardList = getNoticeService.getNoticeList(page, limit);
		
		ActionForward forward = null;
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("boardList", boardList);
		
		forward = new ActionForward("/square/notice.jsp", false);
		
		return forward;
	}

}
