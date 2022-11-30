package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.CommunityDAO;
import VO.CommunityVO;

public class GetCommInfoService {
	public CommunityVO getCommInfo(int commCode) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);		
		
		CommunityVO community = null;
		community = communityDAO.getCommInfo(commCode);
		
		close(con);
		return community;
	}
}
