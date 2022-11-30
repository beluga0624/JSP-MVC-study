package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.BoardDAO;
import VO.ReplyVO;

public class ModReplyService {
	public boolean updateReply(ReplyVO rpl) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int updateCnt = 0;
		boolean isUpdateSuccess = false;

		updateCnt = boardDAO.updateReply(rpl);
	
		if(updateCnt > 0) {
			isUpdateSuccess = true;
			commit(con);
			System.out.println("Commit Success");
		}else {
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);

		return isUpdateSuccess;
	}
}
