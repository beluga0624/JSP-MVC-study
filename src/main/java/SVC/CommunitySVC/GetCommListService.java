package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.CommunityDAO;
import VO.CommunityVO;
import VO.PageInfo;

public class GetCommListService {
	public ArrayList<CommunityVO> getCommunityList(int page, int limit) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);		
		
		ArrayList<CommunityVO> commList = null;
		
		commList = communityDAO.getCommList(page, limit);
		
		close(con);
		
		return commList;
	}
	
	public ArrayList<CommunityVO> getOwnCommunityList(String userid) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);		
		
		ArrayList<CommunityVO> commList = null;
		
		commList = communityDAO.getOwnCommList(userid);
		
		close(con);
		
		return commList;
	}
	
	public ArrayList<CommunityVO> getJoinedCommList(String userid, int page, int limit) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);		
		
		ArrayList<CommunityVO> commList = null;
		
		commList = communityDAO.getJoinedCommList(userid, page, limit);
		
		close(con);
		
		return commList;
	}
	
	public int getListCount() {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);
		
		int listCount = 0;
		
		listCount = communityDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}
	
	public int getListCount(String userid) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);
		
		int listCount = 0;
		
		listCount = communityDAO.selectJoinedCommCount(userid);
		
		close(con);
		
		return listCount;
	}
}
