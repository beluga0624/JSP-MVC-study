package Action.BoardAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Action.Action;
import SVC.BoardSVC.RegReplyService;
import VO.ActionForward;
import VO.ReplyVO;

public class RegReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RegReplyService regReplyService = new RegReplyService();
		
		HttpSession session = request.getSession();
		
		String userid = (String)session.getAttribute("userid");
		
		if(userid.equals("admin")) {
			userid = "관리자";
		}
		
		String reply = request.getParameter("reply");
		String category = request.getParameter("category");
		int commCode = 0;
		int boardNum = Integer.parseInt(request.getParameter("bno"));
		
		boolean isInsertSuccess = false;
		
		ActionForward forward = null;
		
		ReplyVO rpl = new ReplyVO(userid,
								  reply,
								  category,
								  commCode,
								  boardNum,
								  'N',
								  0,
								  null,
								  null);	
		
		isInsertSuccess = regReplyService.regReply(rpl);
		
		if(isInsertSuccess) {
			forward = new ActionForward("/getSiteBoardDetail.board?boardNum=" + boardNum + "&category=" + category, false);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 등록에 실패하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
