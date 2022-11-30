package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static db.JdbcUtil.*;

import VO.MemberVO;

public class MemberDAO {
	Connection con;
	private static MemberDAO memberDAO;
	
	private MemberDAO() {}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public static MemberDAO getInstance() {
		
		if(memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		
		return memberDAO;
	}
	
	public int registMember(MemberVO member) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String sql ="";
		
		try {
			sql = "insert into member values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, null)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getUserpw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirthDate());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			pstmt.setInt(8, member.getPostcd());
			pstmt.setString(9, member.getAddr1());
			pstmt.setString(10, member.getAddr2());
			insertCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return insertCount;
	}
	
	//아이디 중복 체크
	public boolean idCheck(String userid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isExist = false;
		String sql = "";
		
		try {
			sql = "select * from member where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isExist = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return isExist;
	}
	
	//이메일 중복 체크
	public boolean emailCheck(String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isExist = false;
		String sql = "";
		
		try {
			sql = "select * from member where email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isExist = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return isExist;
	}
	
	//전화번호 중복 체크
	public boolean phoneCheck(String phone) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isExist = false;
		String sql = "";
		
		try {
			sql = "select * from member where phone = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isExist = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return isExist;
	}

	//아이디 찾기
	public String findID(String name, String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userid = "";
		String sql = "";
		
		try {
			sql = "select userid from member where username = ? and email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userid = rs.getString(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return userid;
	}
	
	//비밀번호 찾기(체크)
	public int chkUser(String userid, String email, String phone) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int result = 0;
		
		try {
			sql = "select count(*) from member where userid = ? and email = ? and phone = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public MemberVO selectMember(String userid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = "";
		
		try {
			sql = "select * from member where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberVO(rs.getString("userid"),
									  rs.getString("userpw"),
									  rs.getString("username"),
									  rs.getString("birthdate"),
									  rs.getString("gender"),
									  rs.getString("phone"),
									  rs.getString("email"),
									  rs.getInt("postcd"),
									  rs.getString("addr1"),
									  rs.getString("addr2"),
									  rs.getDate("regdate"),
									  rs.getDate("updatedate"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return member;
	}
	
	public int infoUpdate(MemberVO member) {
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		String sql = "";
		
		try {
			sql = "update member set username = ?,"
					+ "			     birthdate = ?,"
					+ "				 gender = ?,"
					+ "				 phone = ?,"
					+ "				 email = ?,"
					+ "				 postcd = ?,"
					+ "				 addr1 = ?,"
					+ "				 addr2 = ?,"
					+ "				 updatedate = sysdate"
					+ " where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getBirthDate());
			pstmt.setString(3, member.getGender());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.setInt(6, member.getPostcd());
			pstmt.setString(7, member.getAddr1());
			pstmt.setString(8, member.getAddr2());
			pstmt.setString(9, member.getUserid());
			updateCnt = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return updateCnt;
	}
	
	public int updatePw(String userid, String userpw) {
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		String sql = "";
		
		try {
			sql = "update member set userpw = ? where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userpw);
			pstmt.setString(2, userid);
			updateCnt = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return updateCnt;
	}
	
	public int deleteMember(String userid) {
		PreparedStatement pstmt = null;
		int deleteCnt = 0;
		String sql = "";
		
		try {
			sql = "delete from member where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			deleteCnt = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return deleteCnt;
	}
	
}
