package Action.CommunityAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.BoardSVC.GetBoardNameService;
import SVC.BoardSVC.GetCommBoardService;
import SVC.CommunitySVC.GetActNameService;
import SVC.CommunitySVC.GetCommBoardNameService;
import SVC.CommunitySVC.GetCommInfoService;
import SVC.CommunitySVC.GetCommunityMemberService;
import VO.ActionForward;
import VO.CommBoardVO;
import VO.CommunityBoards;
import VO.CommunityMembersVO;
import VO.CommunityVO;

public class GetCommInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GetCommInfoService getCommInfoService = new GetCommInfoService();
		GetCommBoardNameService getCommBoardTitlesService = new GetCommBoardNameService();
		GetCommunityMemberService getCommunityMemberService = new GetCommunityMemberService();
		GetActNameService getActNameService = new GetActNameService();
		GetCommBoardService getCommBoardService = new GetCommBoardService();
		GetBoardNameService getBoardNameService = new GetBoardNameService();
		
		int commCode = Integer.parseInt(request.getParameter("commCode"));

		CommunityVO community = null;
		community = getCommInfoService.getCommInfo(commCode);
		
		HttpSession session = request.getSession();
		session.setAttribute("community", community);
		
		ArrayList<CommunityBoards> boardNameList = null;
		boardNameList = getCommBoardTitlesService.getBoardName(commCode);
		session.setAttribute("boardNameList", boardNameList);
		
		ArrayList<String> boardCategory = null;
		boardCategory = getCommBoardTitlesService.getBoardCategory(commCode);
		session.setAttribute("boardCategory", boardCategory);
		
		ArrayList<CommunityMembersVO> communityMemberList = null;
		communityMemberList = getCommunityMemberService.getCommunityMember(commCode);
		
		String userid = (String)session.getAttribute("userid");
		String actName = getActNameService.getActName(commCode, userid);
		ArrayList<CommBoardVO> commNotice = null;
		ArrayList<CommBoardVO> commFreeBoard = null;
		String commNoticeName = null;
		String commFreeBoardName = null;
		
		commNoticeName = getBoardNameService.getCommNotice(commCode);
		commFreeBoardName = getBoardNameService.getCommFreeBoard(commCode);
		
		commNotice = getCommBoardService.getBoardList(commCode, commNoticeName, 1, 6);
		commFreeBoard = getCommBoardService.getBoardList(commCode, commFreeBoardName, 1, 6);
		
		boolean isCommMember = false;
		for(CommunityMembersVO data : communityMemberList) {
			if(data.getUserid().equals(userid)) {
				isCommMember = true;
			}
		}
		
		session.setAttribute("isCommMember", isCommMember);
		request.setAttribute("commNotice", commNotice);
		request.setAttribute("commFreeBoard", commFreeBoard);
		
		if(actName != null) {
			session.setAttribute("actName", actName);
		}
		
		ActionForward forward = new ActionForward("/square/communityMain.jsp", false);
		
		return forward;
	}
	
}
