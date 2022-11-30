package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.CommunityDAO;
import VO.CommunityVO;

public class ModifyCommInfoService {
	public boolean modifyComm(CommunityVO comm) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);
		
		int updateCnt = 0;
		boolean isUpdateSuccess = false;
		
		updateCnt = communityDAO.updateCommInfo(comm);
		
		if(updateCnt > 0) {
			commit(con);
			System.out.println("Commit Success");
			isUpdateSuccess = true;
		}else {
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		
		return isUpdateSuccess;
	}
}
