package MemberSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.MemberDAO;

public class CheckIDService {
	public boolean checkID(String userid) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		boolean isIDExist = false;
		
		isIDExist = memberDAO.idCheck(userid);
		
		close(con);
		return isIDExist;
	}
}
