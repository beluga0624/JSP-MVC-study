package SVC.MemberSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.MemberDAO;
import VO.MemberVO;

public class ChkMemberService {
	public MemberVO chkMember(String userid, String userpw) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);			
		
		MemberVO member = null;
		
		member = memberDAO.selectMember(userid);
		
		if(member.getUserpw().equals(userpw)) {
			return member;
		}
		
		close(con);
	
		return null;
	}
}
