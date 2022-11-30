package SVC.MemberSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.MemberDAO;
import VO.MemberVO;

public class MemberJoinService {
	public boolean joinMember(MemberVO member) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);		
		boolean isJoinSuccess = false;		
		int insertCount = memberDAO.registMember(member);
		
		if(insertCount>0){
			commit(con);
			System.out.println("Commit Success");
			isJoinSuccess = true;
		}else{
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		return isJoinSuccess;
	}
}
