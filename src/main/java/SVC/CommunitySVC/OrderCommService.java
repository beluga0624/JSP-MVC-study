package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.CommunityDAO;
import VO.CommunityVO;

public class OrderCommService {
	//인기순 정렬
	public ArrayList<CommunityVO> getOrderedCommList(int page, int limit){
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);		
		
		ArrayList<CommunityVO> commList = null;
		
		commList = communityDAO.getOrderedCommList(page, limit);

		close(con);

		return commList;
	}
	
	//카테고리별 정령
	public ArrayList<CommunityVO> getOrderedCommList(String type, int page, int limit){
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);		
		
		ArrayList<CommunityVO> commList = null;
		
		commList = communityDAO.getOrderedCommList(type, page, limit);
		
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
}
