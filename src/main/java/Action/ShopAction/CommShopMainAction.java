package Action.ShopAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.ShopService.CommShopMainService;
import VO.ActionForward;
import VO.GoodsVO;
import VO.PageInfo;

public class CommShopMainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommShopMainService commShopMainService = new CommShopMainService();
		
		ArrayList<GoodsVO> goodsList = null;
		int commCode = Integer.parseInt(request.getParameter("commCode"));
		
		int page = 1; 
		int limit = 9; 
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = commShopMainService.getListCount(commCode);

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
		
		goodsList = commShopMainService.getGoodsList(commCode, page, limit);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("goodsList", goodsList);
		request.setAttribute("pageInfo", pageInfo);
		
		ActionForward forward = new ActionForward("/square/communityShop.jsp", false);
		
		return forward;
	}
}
