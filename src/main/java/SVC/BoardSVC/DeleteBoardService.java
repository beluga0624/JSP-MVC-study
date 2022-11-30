package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.BoardDAO;

public class DeleteBoardService {
	public boolean deleteBoard(String category, int boardNum) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int updateCnt = 0;

		boolean isUpdateSuccess = false;
		
		updateCnt = boardDAO.deleteBoard(category, boardNum);
		
		if(updateCnt > 0) {
			commit(con);
			System.out.println("Commit Success");
			isUpdateSuccess = true;
		}else {
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);

		return isUpdateSuccess;
	}
}
