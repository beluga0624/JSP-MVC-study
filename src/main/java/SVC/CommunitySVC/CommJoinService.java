package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.CommunityDAO;
import VO.CommunityMembersVO;

public class CommJoinService {
	public boolean commJoin(CommunityMembersVO commMember) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);	
		
		int insertCount = 0;
		boolean isJoinSuccess = false;
		
		insertCount = communityDAO.insertMember(commMember);
		
		if(insertCount>0){
			commit(con);
			System.out.println("Commit Success");
			isJoinSuccess=true;
		}else{
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		
		return isJoinSuccess;
	}
}
