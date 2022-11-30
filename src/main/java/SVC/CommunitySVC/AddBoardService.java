package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.CommunityDAO;
import VO.CommunityBoards;

public class AddBoardService {
	public boolean addBoard(CommunityBoards commBoard) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);	
		
		int insertCnt = 0;
		boolean isInsertSuccess = false;
		
		insertCnt = communityDAO.addBoard(commBoard);
		
		if(insertCnt>0){
			commit(con);
			System.out.println("Commit Success");
			isInsertSuccess=true;
		}else{
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		
		return isInsertSuccess;
	}
}
