package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.CommunityDAO;
import VO.CommunityMembersVO;

public class GetCommMemberInfoService {
	public CommunityMembersVO getCommMember(String userid, int commCode) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);	
		
		CommunityMembersVO commMember = communityDAO.getCommMemberInfo(userid, commCode);
		
		close(con);
		
		return commMember;
	}
}
