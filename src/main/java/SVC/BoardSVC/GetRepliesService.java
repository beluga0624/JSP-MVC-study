package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.BoardDAO;
import VO.ReplyVO;

public class GetRepliesService {
	public ArrayList<ReplyVO> getReplies (int boardNum, String category, int page, int limit) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<ReplyVO> replies = null;
		
		replies = boardDAO.getReplies(boardNum, category, page, limit);
		
		close(con);
		
		return replies;
	}
	
	public int getListCount (int boardNum, String category) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int count = 0;
		
		count = boardDAO.getRepliesCount(boardNum, category);
		
		close(con);
		
		return count;
	}
}
