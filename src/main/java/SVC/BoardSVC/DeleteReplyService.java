package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.BoardDAO;

public class DeleteReplyService {
	public boolean deleteReply(int bno, String category, int rplNo) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int updateCnt = 0;

		boolean isUpdateSuccess = false;
		
		updateCnt = boardDAO.deleteReply(bno, category, rplNo);
		
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
	
	public boolean deleteReply(int commCode, int bno, String category, int rplNo) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int updateCnt = 0;

		boolean isUpdateSuccess = false;
		
		updateCnt = boardDAO.deleteReply(commCode, bno, category, rplNo);
		
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
