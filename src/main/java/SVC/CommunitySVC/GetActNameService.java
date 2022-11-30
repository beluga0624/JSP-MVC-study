package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.CommunityDAO;

public class GetActNameService {
	public String getActName(int commCode, String userid) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);
		
		String actName = "";
		
		actName = communityDAO.getActName(commCode, userid);
		
		close(con);

		return actName;
	}
}
