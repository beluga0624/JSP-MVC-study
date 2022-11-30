package Action.BoardAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.BoardSVC.ModReplyService;
import VO.ActionForward;
import VO.ReplyVO;

public class ModCommReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModReplyService modReplyService = new ModReplyService();

		String reply = request.getParameter("modReplyContent");
		String category = request.getParameter("modReplyCategory");
		int commCode = 0;
		int boardNum = Integer.parseInt(request.getParameter("modReplyBoardNum"));
		int modReplyNum = Integer.parseInt(request.getParameter("modReplyNum"));
		
		if(request.getParameter("commCode") != null) {
			commCode = Integer.parseInt(request.getParameter("commCode"));
		}
		
		ReplyVO rpl = new ReplyVO("",
								  reply,
								  category,
								  commCode,
								  boardNum,
								  'N',
								  modReplyNum,
								  null,
								  null);
		
		boolean isUpdateSuccess = false;
		
		isUpdateSuccess = modReplyService.updateReply(rpl);
		
		ActionForward forward = null;
		
		if(isUpdateSuccess) {
			forward = new ActionForward("/getCommBoardDetail.board?boardNum=" + boardNum + "&category=" + category + "&commCode=" + commCode, false);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 수정에 실패하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
