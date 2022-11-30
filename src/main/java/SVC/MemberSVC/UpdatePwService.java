package SVC.MemberSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.MemberDAO;

public class UpdatePwService {
	public boolean updateUserPw(String userid, String userpw) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);		
		boolean isUpdatePwSuccess = false;		
		int updateCount = memberDAO.updatePw(userid, userpw);
		
		if(updateCount>0){
			commit(con);
			System.out.println("Commit Success");
			isUpdatePwSuccess = true;
		}else{
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		return isUpdatePwSuccess;
	}
}
