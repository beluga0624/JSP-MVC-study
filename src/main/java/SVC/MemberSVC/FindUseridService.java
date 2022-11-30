package SVC.MemberSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.MemberDAO;

public class FindUseridService {
	public String findUserid(String name, String email) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);

		String userid = memberDAO.findID(name, email);
		
		close(con);
		
		return userid;
	}
}
