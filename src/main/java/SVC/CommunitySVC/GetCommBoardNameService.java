package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.CommunityDAO;
import VO.CommunityBoards;

public class GetCommBoardNameService {
	public ArrayList<CommunityBoards> getBoardName(int commCode){
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);		

		ArrayList<CommunityBoards> commBoards = null;
		
		commBoards = communityDAO.getCommBoardName(commCode);
		
		close(con);
		
		return commBoards;
	}
	
	public ArrayList<String> getBoardCategory(int commCode){
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);
		
		ArrayList<String> commBoardCategory = null;
		
		commBoardCategory = communityDAO.getBoardCategory(commCode);
		
		close(con);
		
		return commBoardCategory;
	}

}
