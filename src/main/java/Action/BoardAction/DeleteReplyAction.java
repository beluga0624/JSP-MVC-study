package Action.BoardAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.BoardSVC.DeleteReplyService;
import VO.ActionForward;

public class DeleteReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DeleteReplyService deleteReplyService = new DeleteReplyService();
		
		int bno = Integer.parseInt(request.getParameter("delBoardNum"));
		String category = request.getParameter("delCategory");
		int rplNo = Integer.parseInt(request.getParameter("delReplyNum"));
		
		boolean isDeleteSuccess = false;
		
		isDeleteSuccess = deleteReplyService.deleteReply(bno, category, rplNo);
		
		ActionForward forward = null;
		
		if(isDeleteSuccess) {
			forward = new ActionForward("/getSiteBoardDetail.board?boardNum=" + bno + "&category=" + category, false);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 삭제에 실패하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}

		return forward;
	}

}
