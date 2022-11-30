package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.CommunityDAO;
import VO.CommunityBoards;

public class ModBoardTitleService {
	public boolean modBoardName(CommunityBoards commBoard) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);	
		
		int updateCnt = 0;
		boolean isUdateSuccess = false;
		
		updateCnt = communityDAO.updateBoardName(commBoard);
		
		if(updateCnt>0){
			commit(con);
			System.out.println("Commit Success");
			isUdateSuccess=true;
		}else{
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		
		return isUdateSuccess;
	}
}
