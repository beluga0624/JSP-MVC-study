package SVC.MemberSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.MemberDAO;
import VO.MemberVO;

public class UpdateMemberService {
	public boolean updateMember(MemberVO member) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);		
		boolean isUpdateSuccess = false;		
		int updateCount = memberDAO.infoUpdate(member);
		
		if(updateCount>0){
			commit(con);
			System.out.println("Commit Success");
			isUpdateSuccess = true;
		}else{
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		return isUpdateSuccess;
	}
}
