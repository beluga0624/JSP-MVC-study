package SVC.CommunitySVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.CommunityDAO;
import VO.CommunityMembersVO;

public class GetCommunityMemberService {
	public ArrayList<CommunityMembersVO> getCommunityMember(int commCode) {
		CommunityDAO communityDAO = CommunityDAO.getInstance();
		Connection con = getConnection();
		communityDAO.setConnection(con);		

		ArrayList<CommunityMembersVO> communityMemberList = null;
		
		communityMemberList = communityDAO.getCommunityMembers(commCode);
		
		close(con);
		
		return communityMemberList;
	}
}
