package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.BoardDAO;
import VO.SiteBoardVO;

public class GetNoticeService {
	public ArrayList<SiteBoardVO> getNoticeList(int page, int limit){
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<SiteBoardVO> boardList = null;
		
		boardList = boardDAO.getNoticeList(page, limit);
		
		close(con);
		
		return boardList;
	}
	
	public ArrayList<SiteBoardVO> getNoticeList2(){
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<SiteBoardVO> boardList = null;
		
		boardList = boardDAO.getNoticeList2();
		
		close(con);
		
		return boardList;
	}
	
	public int getListCount() {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		int listCount = 0;
		
		listCount = boardDAO.getNoticeListCnt();
		
		close(con);
		
		return listCount;
	}
	
	
}
