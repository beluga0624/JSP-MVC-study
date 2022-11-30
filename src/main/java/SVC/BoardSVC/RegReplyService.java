package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.BoardDAO;
import VO.ReplyVO;

public class RegReplyService {
	public boolean regReply(ReplyVO rpl) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int insertCnt = 0;
		boolean isInsertSuccess = false;

		insertCnt = boardDAO.insertReply(rpl);
		
		if(insertCnt > 0) {
			isInsertSuccess = true;
			commit(con);
			System.out.println("Commit Success");
		}else {
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		
		return isInsertSuccess;
	}
}
