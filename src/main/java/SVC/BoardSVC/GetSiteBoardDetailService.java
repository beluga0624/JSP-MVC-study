package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.BoardDAO;
import VO.SiteBoardVO;

public class GetSiteBoardDetailService {
	public SiteBoardVO getBoardDetail(int boardNum, String category, boolean isCookieExist) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		SiteBoardVO board = null;
		
		board = boardDAO.getNoticeDetail(boardNum, category, isCookieExist);
		
		close(con);
		
		return board;
	}
}
