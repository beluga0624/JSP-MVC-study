package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.BoardDAO;
import VO.CommBoardVO;
import VO.SiteBoardVO;

public class GetCommBoardDetailService {
	public CommBoardVO getBoardDetail(int boardNum, String category, int commCode, boolean isCookieExist) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		CommBoardVO board = null;
		
		board = boardDAO.getCommBoardDetail(boardNum, category, commCode, isCookieExist);
		
		close(con);
		
		return board;
	}
}
