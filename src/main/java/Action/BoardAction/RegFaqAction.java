package Action.BoardAction;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Action.Action;
import SVC.BoardSVC.RegFaqService;
import VO.ActionForward;
import VO.SiteBoardVO;
import VO.UploadFileVO;

public class RegFaqAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RegFaqService regFaqService = new RegFaqService();

		int maxSize = 5 * 1024 * 1024;
		
		String realFolder = "";
		String saveFolder = "/resources/images/siteBoard";
		
		ServletContext context = request.getServletContext();
		
		realFolder = context.getRealPath(saveFolder);

        File attachesDir = new File(realFolder);

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        fileItemFactory.setRepository(attachesDir);
        fileItemFactory.setSizeThreshold(maxSize);
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
        
        ArrayList<UploadFileVO> fileList = new ArrayList<UploadFileVO>();
        UploadFileVO file = null;
        
        List<FileItem> items = fileUpload.parseRequest(request);
        
        String subject = "";
        String content = "";
        
        for (FileItem item : items) {
            if (!item.isFormField()) {
            	
                if (item.getSize() > 0) {
                    String separator = File.separator;
                    int index =  item.getName().lastIndexOf(separator);
                    String fileName = item.getName().substring(index  + 1);
                    File uploadFile = new File(realFolder +  separator + fileName);
                    item.write(uploadFile);
                    
                    String type = "";
                	
                	if(item.getContentType().startsWith("image")) {
                		type = "image";
                	}else {
                		type = "file";
                	}
                	
                	file = new UploadFileVO(item.getName(),
                							item.getSize(),
                							"faq",
                							0,
                							type,
                							0);
                	
                	fileList.add(file);
                }
            }else {
            	if(item.getFieldName().equals("subject")) {
            		subject = item.getString("utf-8");
            	}else if(item.getFieldName().equals("content")){
            		content = item.getString("utf-8");
            	}
            }
        }
		
		SiteBoardVO siteBoard = new SiteBoardVO(0, 
												"?????????",
												subject,
												content,
												0,
												null,
												null,
												'N',
												"faq",
												0);
		
		boolean isInsertSuccess = false;
		
		isInsertSuccess = regFaqService.regFaq(siteBoard, fileList);
		
		ActionForward forward = null;
		
		if(isInsertSuccess) {
			forward = new ActionForward("getFAQ.board", true);
		}else{
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('???????????? ????????? ?????????????????????.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
