package Action.BoardAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.BoardSVC.DeleteBoardService;
import VO.ActionForward;

public class DeleteBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DeleteBoardService deleteBoardService = new DeleteBoardService();
		
		String category = request.getParameter("category");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		boolean isDeleteSuccess = false;
		
		isDeleteSuccess = deleteBoardService.deleteBoard(category, boardNum);
		
		ActionForward forward = null;
		
		if(isDeleteSuccess) {
			forward = new ActionForward("/getNotice.board",true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글 삭제에 실패하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
