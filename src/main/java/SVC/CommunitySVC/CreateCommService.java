package SVC.CommunitySVC;


import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.CommunityDAO;
import VO.CommunityBoards;
import VO.CommunityMembersVO;
import VO.CommunityVO;

public class CreateCommService {
	public boolean createComm(CommunityVO comm, CommunityMembersVO commMembers, CommunityBoards commBoards) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);	
		
		boolean isCreateSuccess = false;		
		int insertCount = communityDAO.commCreate(comm, commMembers, commBoards);
		
		if(insertCount>0){
			commit(con);
			System.out.println("Commit Success");
			isCreateSuccess=true;
		}else{
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		return isCreateSuccess;
	}
}
