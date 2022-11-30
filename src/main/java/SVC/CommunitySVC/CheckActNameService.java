package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.CommunityDAO;

public class CheckActNameService {
	public boolean checkActName(int commCode, String actName) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);
		
		boolean isIDExist = false;
		
		isIDExist = communityDAO.actNameCheck(commCode, actName);
		
		close(con);
		
		return isIDExist;
		
	}

}
