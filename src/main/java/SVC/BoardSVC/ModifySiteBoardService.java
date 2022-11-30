package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.BoardDAO;
import VO.SiteBoardVO;
import VO.UploadFileVO;

public class ModifySiteBoardService {
	public boolean modBoard(SiteBoardVO siteBoard, ArrayList<UploadFileVO> fileList, ArrayList<String> remainFiles) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int insertCnt = 0;

		boolean isInsertSuccess = false;
		
		insertCnt = boardDAO.updateBoard(siteBoard, fileList, remainFiles);
		
		if(insertCnt >= 1) {
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
