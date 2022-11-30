package MemberSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.MemberDAO;
import VO.MemberVO;

public class MemberLoginService {
	public boolean checkMemberInfo(String userid, String userpw) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);	
		
		boolean isMember = false;
		MemberVO member = memberDAO.selectMember(userid);
		
		if(member == null || !member.getUserpw().equals(userpw)) {
			isMember = false;
		}else if(member.getUserpw().equals(userpw)){
			isMember = true;
		}
		
		close(con);
		return isMember;
	}
}
