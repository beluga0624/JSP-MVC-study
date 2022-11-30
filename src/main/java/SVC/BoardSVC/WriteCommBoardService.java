package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.BoardDAO;
import VO.CommBoardVO;
import VO.UploadFileVO;

public class WriteCommBoardService {
	public boolean regCommBoard(CommBoardVO commBoard, ArrayList<UploadFileVO> fileList) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int insertCnt = 0;

		boolean isInsertSuccess = false;
		
		insertCnt = boardDAO.insertCommBoard(commBoard, fileList);
		
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
