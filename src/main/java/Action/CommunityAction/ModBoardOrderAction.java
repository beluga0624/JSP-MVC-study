package Action.CommunityAction;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import SVC.CommunitySVC.ModBoardOrderService;
import VO.ActionForward;

public class ModBoardOrderAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModBoardOrderService modBoardOrderService = new ModBoardOrderService();
		
		int commCode = Integer.parseInt(request.getParameter("orderFrmCommCode"));
		String[] boardNumsArray = request.getParameterValues("orderFrmBoardNum");
		int[] boardNums = new int[boardNumsArray.length];
		
		for(int i=0; i<boardNumsArray.length; i++) {
			boardNums[i] = Integer.parseInt(boardNumsArray[i]); 
		}
		
		boolean isUpdateSuccess = false;
		isUpdateSuccess = modBoardOrderService.modBoardOrder(commCode, boardNums);
		
		ActionForward forward = null;
		
		if(isUpdateSuccess) {
			forward = new ActionForward("/getCommInfo.square?commCode=" + commCode, true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시판 순서 변경에 실패 하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
