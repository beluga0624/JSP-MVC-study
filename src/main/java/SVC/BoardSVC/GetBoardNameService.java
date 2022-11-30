package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.BoardDAO;
import VO.SiteBoardVO;

public class GetBoardNameService {
	public String getCommNotice(int commCode) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		String commNotice = "";
		
		commNotice = boardDAO.getCommNoticeBoard(commCode);
		
		close(con);
		
		return commNotice;
	}
	
	public String getCommFreeBoard(int commCode) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		String commFreeBoard = "";
		
		commFreeBoard = boardDAO.getCommFreeBoard(commCode);
		
		close(con);
		
		return commFreeBoard;
	}
}
