package SVC.MemberSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.MemberDAO;

public class CheckEmailService {
	public boolean checkEmail(String email) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		boolean isIDExist = false;
		
		isIDExist = memberDAO.emailCheck(email);
		
		close(con);
		
		return isIDExist;
	}
}
