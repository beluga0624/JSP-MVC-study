package Action.CommunityAction;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Action.Action;
import SVC.CommunitySVC.CreateCommService;
import VO.ActionForward;
import VO.CommunityBoards;
import VO.CommunityMembersVO;
import VO.CommunityVO;


public class CreateCommAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CreateCommService createCommService = new CreateCommService();
		
		HttpSession session = request.getSession();
		String commAdmin = (String)session.getAttribute("userid");
		
		String encType = "utf-8";
		int maxSize = 10 * 1024 * 1024;
		
		String realFolder = "";
		String saveFolder = "/resources/images/commMainImg";
		
		ServletContext context = request.getServletContext();
		
		realFolder = context.getRealPath(saveFolder);
		
		System.out.println(realFolder);
		
		MultipartRequest multi = new MultipartRequest(
														request,
														realFolder,
														maxSize,
														encType,
														new DefaultFileRenamePolicy());
		
		String mainImg = multi.getFilesystemName("mainImg");
		String subImg = multi.getFilesystemName("subImg");
		
		CommunityVO comm = new CommunityVO(0,
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
		
		CommunityMembersVO commMembers = new CommunityMembersVO(commAdmin,
																0,
																multi.getParameter("activityName"),
																null,
																null,
																null,
																null,
																null);
		
		CommunityBoards commBoards = null;
		
		boolean createSuccess = createCommService.createComm(comm, commMembers, commBoards);
	
		ActionForward forward = null;
		
		if(createSuccess) {
			forward = new ActionForward("getCommList.square?oper=show", true);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('커뮤니티 생성 실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
