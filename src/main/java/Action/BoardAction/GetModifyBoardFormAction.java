package Action.BoardAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.BoardSVC.GetModifyBoardFormService;
import SVC.BoardSVC.GetUploadedFilesService;
import VO.ActionForward;
import VO.SiteBoardVO;
import VO.UploadFileVO;

public class GetModifyBoardFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GetModifyBoardFormService getModifyBoardFormService = new GetModifyBoardFormService();
		GetUploadedFilesService getUploadedFilesService = new GetUploadedFilesService();
		
		String category = request.getParameter("category");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		ArrayList<UploadFileVO> fileList = null;
		SiteBoardVO board = null;
		
		board = getModifyBoardFormService.getNoticeForModify(category, boardNum);
		fileList = getUploadedFilesService.getFileList(boardNum, category);
		
		ActionForward forward = null;
		
		request.setAttribute("board", board);
		request.setAttribute("fileList", fileList);
		
		forward = new ActionForward("/square/modBoardForm.jsp", false);
		
		return forward;
	}

}
