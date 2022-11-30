package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.CommunityDAO;

public class CloseCommService {
	public boolean closeComm(int commCode) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);		
		
		boolean isCommClosed =false;
		
		int updateCnt = 0;
		
		updateCnt = communityDAO.closeComm(commCode);
		
		if(updateCnt>0) {
			commit(con);
			System.out.println("Commit Success");
			isCommClosed = true;
		}else {
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		
		return isCommClosed;
	}
}
