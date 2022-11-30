package Action.BoardAction;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.BoardSVC.GetSiteBoardDetailService;
import SVC.BoardSVC.GetRepliesService;
import SVC.BoardSVC.GetUploadedFilesService;
import VO.ActionForward;
import VO.PageInfo;
import VO.ReplyVO;
import VO.SiteBoardVO;
import VO.UploadFileVO;

public class GetSiteBoardDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GetSiteBoardDetailService getSiteBoardDetailService = new GetSiteBoardDetailService();
		GetUploadedFilesService getUploadedFilesService = new GetUploadedFilesService();
		GetRepliesService getRepliesService = new GetRepliesService();
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String category = request.getParameter("category");
		
		int commCode = 0;
		
		if(request.getParameter("commCode") != null) {
			commCode = Integer.parseInt(request.getParameter("commCode"));
		}
		
		ArrayList<ReplyVO> replies = null;
		
		Cookie viewCookie=null;
		Cookie[] cookies=request.getCookies();
		
		boolean isCookieExist = false;
		
		//읽었던 글 조회수 올림 막기
		if(cookies != null) {
			for (int i = 0; i < cookies.length; i++) {

				if(cookies[i].getName().equals("boardNum" + boardNum) && cookies[i].getValue().equals(category)) {
					
					viewCookie=cookies[i];
					
					isCookieExist = true;
				}else {
					viewCookie = new Cookie("boardNum" + boardNum, category);
					viewCookie.setMaxAge(3*60*60);
					response.addCookie(viewCookie);
				}
			}
		}else {
			viewCookie = new Cookie("boardNum" + boardNum, category);
			viewCookie.setMaxAge(3*60*60);
			response.addCookie(viewCookie);
		}
		
		SiteBoardVO board = null;
		ArrayList<UploadFileVO> fileList = null;
		
		int page = 1; 
		int limit = 20; 
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = getRepliesService.getListCount(boardNum, category);
		
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
		
		board = getSiteBoardDetailService.getBoardDetail(boardNum, category, isCookieExist);
		fileList = getUploadedFilesService.getFileList(boardNum, category);
		replies = getRepliesService.getReplies(boardNum, category, page, limit);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("board", board);
		request.setAttribute("fileList", fileList);
		request.setAttribute("replies", replies);
		
		ActionForward forward = new ActionForward("/square/siteBoardDetail.jsp", false);
		
		return forward;
	}

}
