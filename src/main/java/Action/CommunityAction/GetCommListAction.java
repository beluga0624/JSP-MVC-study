package Action.CommunityAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.CommunitySVC.GetCommListService;
import VO.ActionForward;
import VO.CommunityVO;
import VO.PageInfo;

public class GetCommListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GetCommListService getCommListService = new GetCommListService();
		
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		String oper = request.getParameter("oper");
		
		ArrayList<CommunityVO> ownCommList = null;
		ArrayList<CommunityVO> joinedCommList = null;
		
		int page = 1; 
		int limit = 10; 
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = getCommListService.getListCount(userid);

		ownCommList = getCommListService.getOwnCommunityList(userid);
		joinedCommList = getCommListService.getJoinedCommList(userid, page, limit);
		
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
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("ownCommList", ownCommList);
		request.setAttribute("joinedCommList", joinedCommList);
		
		ActionForward forward = null;
		
		if(oper.equals("show")) {
			forward = new ActionForward("/square/ownCommList.jsp", false);
		}else if(oper.equals("mod")) {
			forward = new ActionForward("/square/selectCommModify.jsp", false);
		}else if(oper.equals("close")) {
			forward = new ActionForward("/square/closeComm.jsp", false);
		}

		return forward;
	}
	
}
