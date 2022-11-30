package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.CommunityDAO;

public class WithdrawFromCommService {
	public boolean withdrawComm(int commCode, String userid) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);	
		
		int deleteCount = 0;
		boolean isDeleteSuccess = false;
		
		deleteCount = communityDAO.withdrawComm(commCode, userid);
		
		if(deleteCount>0){
			commit(con);
			System.out.println("Commit Success");
			isDeleteSuccess=true;
		}else{
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		
		return isDeleteSuccess;
	}
}
