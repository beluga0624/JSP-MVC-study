package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.BoardDAO;
import VO.SiteBoardVO;

public class GetModifyBoardFormService {
	public SiteBoardVO getNoticeForModify(String category, int boardNum) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		SiteBoardVO board = boardDAO.getNoticeDetail(boardNum, category, true);
		
		close(con);

		return board;
	}
}
