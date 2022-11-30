package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.BoardDAO;
import VO.CommBoardVO;
import VO.SiteBoardVO;

public class GetCommBoardService {
	public int getListCount(int commCode, String boardName) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int listCount = 0;
		
		listCount = boardDAO.getCommBoardListCnt(commCode, boardName);
		
		close(con);
		
		return listCount;
	}
	
	public ArrayList<CommBoardVO> getBoardList(int commCode, String boardName, int page, int limit) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<CommBoardVO> boardList = null;
		
		boardList = boardDAO.getCommBoardList(commCode, boardName, page, limit);
		
		close(con);
		
		return boardList;
	}
}
