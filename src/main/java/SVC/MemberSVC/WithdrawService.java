package SVC.MemberSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.MemberDAO;

public class WithdrawService {
	public boolean withdraw(String userid) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);		
		boolean isWithdrawSuccess = false;		
		int deleteCnt = memberDAO.deleteMember(userid);
		
		if(deleteCnt>0){
			commit(con);
			System.out.println("Commit Success");
			isWithdrawSuccess = true;
		}else{
			rollback(con);
			System.out.println("Commit failed, rollback");
		}
		
		close(con);
		return isWithdrawSuccess;
	}
}
