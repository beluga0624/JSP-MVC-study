package Action.CommunityAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.CommunitySVC.OrderCommService;
import VO.ActionForward;
import VO.CommunityVO;
import VO.PageInfo;

public class OrderCommAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderCommService orderCommService = new OrderCommService();
		
		String oper = request.getParameter("oper");
		String type = request.getParameter("type");
		
		ArrayList<CommunityVO> orderedCommList = new ArrayList<CommunityVO>();
		
		int page = 1; 
		int limit = 12; 
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = orderCommService.getListCount();

		if(oper.equals("popular")) {
			orderedCommList = orderCommService.getOrderedCommList(page, limit);
			
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
			
			request.setAttribute("orderedCommList", orderedCommList);
			
		}else {
			orderedCommList = orderCommService.getOrderedCommList(type, page, limit);
			
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
			pageInfo.setType(type);
			
			request.setAttribute("pageInfo", pageInfo);
			
			request.setAttribute("orderedCommList", orderedCommList);
			
		}
		
		ActionForward forward = new ActionForward("/square/orderedCommList.jsp", false);
		
		return forward;
	}

}
