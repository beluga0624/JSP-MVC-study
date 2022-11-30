package SVC.BoardSVC;

import java.sql.Connection;
import java.util.ArrayList;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import DAO.BoardDAO;
import VO.SiteBoardVO;
import VO.UploadFileVO;

public class RegNoticeService {
	public boolean regNotice(SiteBoardVO siteBoard, ArrayList<UploadFileVO> fileList) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int insertCnt = 0;

		boolean isInsertSuccess = false;
		
		insertCnt = boardDAO.insertNotice(siteBoard, fileList);
		
		if(insertCnt > 1) {
			commit(con);
			System.out.println("Commit Success");
			isInsertSuccess = true;
		}else {
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		
		return isInsertSuccess;
	}
}
