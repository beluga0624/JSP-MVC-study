package SVC.MemberSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.MemberDAO;

public class FindPasswdService {
	public int chkInfo(String userid, String email, String phone) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);

		int result = memberDAO.chkUser(userid, email, phone);
		
		close(con);
		
		return result;
	}
}
