package SVC.MemberSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import DAO.MemberDAO;

public class CheckPhoneService {
	public boolean checkPhone(String phone) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		boolean isIDExist = false;
		
		isIDExist = memberDAO.phoneCheck(phone);
		
		close(con);
		
		return isIDExist;
	}
}
