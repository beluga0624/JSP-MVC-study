package Action.CommunityAction;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Action.Action;
import SVC.CommunitySVC.ModifyCommInfoService;
import VO.ActionForward;
import VO.CommunityVO;

public class ModifyCommInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModifyCommInfoService modifyCommInfoService = new ModifyCommInfoService();
		
		HttpSession session = request.getSession();
		String commAdmin = (String)session.getAttribute("userid");
		
		String encType = "utf-8";
		int maxSize = 10 * 1024 * 1024;

		String realFolder = "";
		String saveFolder = "/resources/images/commMainImg";
		
		ServletContext context = request.getServletContext();
		
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(request,
													realFolder,
													maxSize,
													encType,
													new DefaultFileRenamePolicy());

		String mainImg = multi.getFilesystemName("mainImg");
		String subImg = multi.getFilesystemName("subImg");
		
		int commCode = Integer.parseInt(multi.getParameter("commCode"));
		
		CommunityVO comm = new CommunityVO(commCode,
										   multi.getParameter("commName"),
										   multi.getParameter("description"),
										   commAdmin,
										   mainImg,
										   subImg,
										   multi.getParameter("question1"),
										   multi.getParameter("question2"),
										   multi.getParameter("question3"),
										   multi.getParameter("category"),
										   null,
										   null,
										   0);
		
		boolean isUpdateSuccess = modifyCommInfoService.modifyComm(comm);
		
		ActionForward forward = null;
		
		if(isUpdateSuccess) {
			forward = new ActionForward("/getCommInfo.square?commCode=" + commCode, true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('커뮤니티 정보 수정 실패');");
			out.println("history.go(-3);");
			out.println("</script>");
		}

		return forward;
	}

}
