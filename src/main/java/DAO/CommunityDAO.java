package DAO;


import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import VO.CommunityBoards;
import VO.CommunityMembersVO;
import VO.CommunityVO;

public class CommunityDAO {
	Connection con;
	private static CommunityDAO communityDAO;
	
	private CommunityDAO() {}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public static CommunityDAO getInstance() {
		
		if(communityDAO == null) {
			communityDAO = new CommunityDAO();
		}
		
		return communityDAO;
	}
	
	//전체 커뮤니티 리스트 가져오기
	public ArrayList<CommunityVO> getCommList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CommunityVO> commList = null;
		String sql = "";
		int startrow = (page-1)*10;
		int endrow = startrow + limit;
		
		try {
			sql = "select *"
					+ " from (select rownum, c.* from community c where closed_yn = 'N')"
					+ " where rownum >= ? and rownum < ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				commList = new ArrayList<CommunityVO>();
				
				do {
					commList.add(new CommunityVO(rs.getInt("commcode"),
												rs.getString("commname"),
												rs.getString("description"),
												rs.getString("commadmin"),
												rs.getString("mainimg"),
												rs.getString("subimg"),
												rs.getString("question1"),
												rs.getString("question2"),
												rs.getString("question3"),
												rs.getString("category"),
												rs.getDate("regdate"),
												rs.getDate("updatedate"),
												rs.getInt("memberCnt")));
													
				}while(rs.next());
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return commList;
	}
	
	//유저가 개설한 커뮤니티 리스트 가져오기
	public ArrayList<CommunityVO> getOwnCommList(String userid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CommunityVO> ownCommList = null;
		String sql = "";
		
		try {
			sql = "select * from community where commadmin = ? and closed_yn = 'N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ownCommList = new ArrayList<CommunityVO>();
				
				do {
					ownCommList.add(new CommunityVO(rs.getInt("commcode"),
												rs.getString("commname"),
												rs.getString("description"),
												rs.getString("commadmin"),
												rs.getString("mainimg"),
												rs.getString("subimg"),
												rs.getString("question1"),
												rs.getString("question2"),
												rs.getString("question3"),
												rs.getString("category"),
												rs.getDate("regdate"),
												rs.getDate("updatedate"),
												rs.getInt("memberCnt")));
													
				}while(rs.next());
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return ownCommList;
	}
	
	//유저가 가입한 커뮤니티 리스트 가져오기
	public ArrayList<CommunityVO> getJoinedCommList(String userid, int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CommunityVO> joinedCommList = null;
		String sql = "";
		int startrow = (page-1)*10;
		int endrow = startrow + limit;
		
		try {
			sql = "select rownum, m.commcode as commCode, c.commName as commName, c.description, c.commAdmin, c.mainImg, c.subImg, c.question1, c.question2, c.question3, c.category, c.regdate, c.updatedate, c.memberCnt "
					+ " from community c, comm_member m "
					+ " where m.userid = ? "
					+ " and m.commCode = c.commCode "
					+ " and commAdmin <> ?"
					+ " and closed_yn = 'N'"
					+ " and rownum >= ? "
					+ " and rownum < ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userid);;
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				joinedCommList = new ArrayList<CommunityVO>();
				
				do {
					joinedCommList.add(new CommunityVO(rs.getInt("commcode"),
												rs.getString("commname"),
												rs.getString("description"),
												rs.getString("commadmin"),
												rs.getString("mainimg"),
												rs.getString("subimg"),
												rs.getString("question1"),
												rs.getString("question2"),
												rs.getString("question3"),
												rs.getString("category"),
												rs.getDate("regdate"),
												rs.getDate("updatedate"),
												rs.getInt("memberCnt")));
													
				}while(rs.next());
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return joinedCommList;
	}
	
	public int selectJoinedCommCount(String userid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String sql = "";
		
		try {
			sql = "select count(*) from comm_member where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
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
		
		return cnt;
	}
	
	public synchronized int commCreate(CommunityVO comm, CommunityMembersVO commMembers, CommunityBoards commBoards) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int insertCount = 0;
		int commCode = 0;
		String sql = "";
		
		try {
			sql = "select max(commcode) from community";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				commCode = rs.getInt(1) + 1;
			}else {
				commCode = 1;
			}
			
			commMembers.setCommCode(commCode);
			joinCommMember(commMembers);
			
			commBoards = new CommunityBoards(commCode, 0, "공지사항", "공지사항", 0);
			createBoard(commBoards, commCode);
			
			commBoards = new CommunityBoards(commCode, 0, "자유게시판", "자유게시판", 1);
			createBoard(commBoards, commCode);
			
			sql = "insert into community values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, null, 1, 'N')";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			pstmt.setString(2, comm.getCommName());
			pstmt.setString(3, comm.getDescription());
			pstmt.setString(4, comm.getCommAdmin());
			pstmt.setString(5, comm.getMainImg());
			pstmt.setString(6, comm.getSubImg());
			pstmt.setString(7, comm.getQuestion1());
			pstmt.setString(8, comm.getQuestion2());
			pstmt.setString(9, comm.getQuestion3());
			pstmt.setString(10, comm.getCategory());
			
			insertCount = pstmt.executeUpdate();
	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {}
		}
		
		return insertCount;
	}
	
	public int joinCommMember(CommunityMembersVO commMembers) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String sql = "";
		
		try {
			sql = "insert into comm_member values(?, ?, ?, ?, ?, ?, sysdate, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, commMembers.getUserid());
			pstmt.setInt(2, commMembers.getCommCode());
			pstmt.setString(3, commMembers.getActivityName());
			pstmt.setString(4, commMembers.getAnswer1());
			pstmt.setString(5, commMembers.getAnswer2());
			pstmt.setString(6, commMembers.getAnswer3());
			pstmt.setString(7, null);
			
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
	
	public int createBoard(CommunityBoards commBoards, int commCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardNum = 0;
		int insertCount = 0;
		String sql = "";
		
		try {
			sql = "select max(boardNum) from comm_boardList where commcode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardNum = rs.getInt(1) + 1;
			}else {
				boardNum = 1;
			}
			
			sql = "insert into comm_boardList values(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commBoards.getCommCode());
			pstmt.setInt(2, boardNum);
			pstmt.setString(3, commBoards.getBoardName());
			pstmt.setString(4, commBoards.getCategory());
			pstmt.setInt(5, commBoards.getBoardOrder());
			insertCount = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {}
		}

		return insertCount;
	}
	
	public int updateCommInfo(CommunityVO comm) {
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		String sql = "";
		int index = 1;
		try {
			sql = "update community set description = ?, ";
			
					if(comm.getMainImg() != null) {
						sql += "mainimg = ?, ";
					};
					if(comm.getSubImg() != null) {
						sql += "subimg = ?,  ";
					};
					
					sql += "question1 = ?, "
					+ "question2 = ?, "
					+ "question3 = ?, "
					+ "category = ?, "
					+ "updatedate = sysdate "
					+ "where commcode = ?";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, comm.getDescription());
			
			if(comm.getMainImg() != null) {
				pstmt.setString(++index, comm.getMainImg());
			};
			
			if(comm.getSubImg() != null) {
				pstmt.setString(++index, comm.getSubImg());
			};
			
			pstmt.setString(++index, comm.getQuestion1());
			pstmt.setString(++index, comm.getQuestion2());
			pstmt.setString(++index, comm.getQuestion3());
			pstmt.setString(++index, comm.getCategory());
			pstmt.setInt(++index, comm.getCommCode());
			
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
	
	//인기순 커뮤니티 리스트
	public ArrayList<CommunityVO> getOrderedCommList(int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<CommunityVO> orderedCommList = new ArrayList<CommunityVO>();
		int startrow = (page-1)*10;
		int endrow = startrow + limit;
		
		try {
			sql =   "select * from community where closed_yn = 'N' and rownum >= ? and rownum < ? order by membercnt desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					orderedCommList.add(new CommunityVO(rs.getInt("commcode"),
														rs.getString("commname"),
														rs.getString("description"),
														rs.getString("commadmin"),
														rs.getString("mainimg"),
														rs.getString("subimg"),
														"",
														"",
														"",
														"",
														rs.getDate("regdate"),
														null,
														rs.getInt("memberCnt")));
													
				}while(rs.next());
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
		
		return orderedCommList;
	}
	
	//카테고리별 정렬된 커뮤니티 리스트
	public ArrayList<CommunityVO> getOrderedCommList(String category, int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<CommunityVO> orderedCommList = new ArrayList<CommunityVO>();
		int startrow = (page-1)*10;
		int endrow = startrow + limit;
			
		try {
			sql = "select * from (select * from community where category = ? and closed_yn = 'N') where rownum >= ? and rownum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					orderedCommList.add(new CommunityVO(rs.getInt("commcode"),
														rs.getString("commname"),
														rs.getString("description"),
														rs.getString("commadmin"),
														rs.getString("mainimg"),
														rs.getString("subimg"),
														rs.getString("question1"),
														rs.getString("question2"),
														rs.getString("question3"),
														rs.getString("category"),
														rs.getDate("regdate"),
														rs.getDate("updatedate"),
														rs.getInt("memberCnt")));
													
				}while(rs.next());
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
		
		return orderedCommList;
	}
	
	//커뮤니티 정보 가져오기
	public CommunityVO getCommInfo(int commCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommunityVO comm = null;
		String sql = "";
		
		try {
			sql = "select * from community where commCode = ? and closed_yn = 'N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				comm = new CommunityVO(rs.getInt("commcode"),
										rs.getString("commname"),
										rs.getString("description"),
										rs.getString("commadmin"),
										rs.getString("mainimg"),
										rs.getString("subimg"),
										rs.getString("question1"),
										rs.getString("question2"),
										rs.getString("question3"),
										rs.getString("category"),
										rs.getDate("regdate"),
										rs.getDate("updatedate"),
										rs.getInt("memberCnt"));
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

		return comm;
	}
	
	//게시판 정보 가져오기
	public ArrayList<CommunityBoards> getCommBoardName(int commCode){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<CommunityBoards> commBoardNameList = new ArrayList<CommunityBoards>();
		
		try {
			sql = "select * from comm_boardList where commCode = ? order by board_order";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do {
					CommunityBoards commBoards = new CommunityBoards(rs.getInt("commCode"), 
																	 rs.getInt("boardNum"), 
																	 rs.getString("boardName"), 
																	 rs.getString("category"), 
																	 rs.getInt("board_order"));
					
					commBoardNameList.add(commBoards);
				}while(rs.next());
				
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
		
		return commBoardNameList;
	}
	
	//게시판 카테고리 가져오기
	public ArrayList<String> getBoardCategory(int commCode){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<String> boardCategory = new ArrayList<String>();
		
		try {
			sql = "select category from comm_boardlist where commCode = ? order by board_order";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do {
					if(!boardCategory.contains(rs.getString("category"))) {
						boardCategory.add(rs.getString("category"));
					}
				}while(rs.next());
				
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
		return boardCategory;
	}
	
	//커뮤니티 가입자 리스트 가져오기
	public ArrayList<CommunityMembersVO> getCommunityMembers(int commCode){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		CommunityMembersVO commMember = null;
		ArrayList<CommunityMembersVO> communityMembers = new ArrayList<CommunityMembersVO>();
		
		try {
			sql = "select * from comm_member where commCode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					commMember = new CommunityMembersVO(rs.getString("userid"),
														rs.getInt("commCode"),
														rs.getString("activityName"),
														rs.getString("answer1"),
														rs.getString("answer2"),
														rs.getString("answer3"),
														rs.getDate("joindate"),
														rs.getDate("updatedate"));
					
					communityMembers.add(commMember);
				}while(rs.next());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return communityMembers;
	}
	
	//커뮤니티 멤버 가입
	public int insertMember(CommunityMembersVO commMember) {
		PreparedStatement pstmt = null;
		int insertCnt = 0;
		String sql = "";
		
		try {
			sql = "insert into comm_member values (?, ?, ?, ?, ?, ?, sysdate, null)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, commMember.getUserid());
			pstmt.setInt(2, commMember.getCommCode());
			pstmt.setString(3, commMember.getActivityName());
			pstmt.setString(4, commMember.getAnswer1());
			pstmt.setString(5, commMember.getAnswer2());
			pstmt.setString(6, commMember.getAnswer3());
			
			insertCnt = pstmt.executeUpdate();
			
			if(insertCnt > 0) {
				updateMemeberCnt(commMember.getCommCode(), 1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return insertCnt;
	}
	
	//커뮤니티 회원수 증가
	public int updateMemeberCnt(int commCode, int cnt) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		int updateCnt = 0;
		
		try {
			sql = "select memberCnt from community where commCode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			rs = pstmt.executeQuery();
			int memberCnt = 0;
			
			if(rs.next()) {
				memberCnt = rs.getInt("memberCnt");
			}
			
			memberCnt += cnt;
			
			sql = "update community set memberCnt = ? where commCode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberCnt);
			pstmt.setInt(2, commCode);
			updateCnt = pstmt.executeUpdate();
			
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
		
		return updateCnt;
	}
	
	//커뮤니티 가입정보 가져오기
	public CommunityMembersVO getCommMemberInfo(String userid, int commCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		CommunityMembersVO commMember = null;
		
		try {
			sql = "select * from comm_member where userid = ? and commCode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, commCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				commMember = new CommunityMembersVO(rs.getString("userid"),
													rs.getInt("commCode"),
													rs.getString("activityName"),
													rs.getString("answer1"),
													rs.getString("answer2"),
													rs.getString("answer3"),
													rs.getDate("joinDate"),
													rs.getDate("updateDate"));
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
		
		return commMember;
	}
	
	//커뮤니티 회원정보 수정
	public int commMemberUpdate(CommunityMembersVO commMember) {
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		String sql = "";
		
		try {
			sql = "update comm_member set answer1 = ?, answer2 = ?, answer3 = ?, updatedate = sysdate where commCode = ? and userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, commMember.getAnswer1());
			pstmt.setString(2, commMember.getAnswer2());
			pstmt.setString(3, commMember.getAnswer3());
			pstmt.setInt(4, commMember.getCommCode());
			pstmt.setString(5, commMember.getUserid());
			
			updateCnt = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return updateCnt;
	}
	
	//커뮤니티 탈퇴
	public int withdrawComm(int commCode, String userid) {
		PreparedStatement pstmt = null;
		String sql = "";
		int deleteCnt = 0;
		
		try {
			sql = "delete from comm_member where commCode = ? and userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			pstmt.setString(2, userid);
			
			deleteCnt = pstmt.executeUpdate();
			
			if(deleteCnt > 0) {
				updateMemeberCnt(commCode, -1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return deleteCnt;
	}
	
	//커뮤니티 게시판 추가
	public int addBoard(CommunityBoards commBoard) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int insertCnt = 0;
		int boardNum = 0;
		int boardOrder = 0;
		String sql = "";

		try {
			sql = "select max(boardnum) as boardNum from comm_boardList where commCode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commBoard.getCommCode());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardNum = rs.getInt("boardNum") + 1;
			}
			
			sql = "select max(board_order) as board_order from comm_boardList where commCode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commBoard.getCommCode());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardOrder = rs.getInt("board_order") + 1;
			}
			
			sql = "insert into comm_boardList values(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commBoard.getCommCode());
			pstmt.setInt(2, boardNum);
			pstmt.setString(3, commBoard.getBoardName());
			pstmt.setString(4, commBoard.getCategory());
			pstmt.setInt(5, boardOrder);
			
			insertCnt = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return insertCnt;
	}
	
	//커뮤니티 게시판 이름 변경
	public int updateBoardName(CommunityBoards commBoard) {
		PreparedStatement pstmt = null;
		String sql = "";
		int updateCnt = 0;
		
		try {
			sql = "update comm_boardList set boardName = ? where commCode = ? and boardNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, commBoard.getBoardName());
			pstmt.setInt(2, commBoard.getCommCode());
			pstmt.setInt(3, commBoard.getBoardNum());
			
			updateCnt = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return updateCnt;
	}
	
	//게시판 순서 변경
	public int updateBoardOrder(int commCode, int[] boardNums) {
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		String sql = "";
		
		try {
			sql = "update comm_boardList set board_order = ? where commCode = ? and boardNum = ?";
			for(int i=0; i<boardNums.length; i++) {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, i);
				pstmt.setInt(2, commCode);
				pstmt.setInt(3, boardNums[i]);
				updateCnt += pstmt.executeUpdate();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return updateCnt;
	}
	
	//커뮤니티 활동명 중복 체크
	public boolean actNameCheck(int commCode, String actName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isExist = false;
		String sql = "";
		
		try {
			sql = "select * from comm_member where activityName = ? and commCode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, actName);
			pstmt.setInt(2, commCode);
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
	
	//커뮤니티 폐쇄
	public int closeComm(int commCode) {
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		String sql = "";
		
		try {
			sql = "update community set closed_yn = 'Y' where commCode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			updateCnt = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return updateCnt;
	}
	
	//커뮤니티 리스트 수 가져오기
	public int selectListCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		
		try {
			
			pstmt = con.prepareStatement("select count(*) from community");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {

				listCount = rs.getInt(1);

			}
		}catch(Exception e) {
			System.out.println("getListCount 에러: " + e);
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return listCount;
	}
	
	public String getActName(int commCode, String userid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String actName = "";
		String sql = "";
		
		try {
			sql = "select activityName from comm_member where commCode = ? and userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			pstmt.setString(2, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				actName = rs.getString(1);
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
		
		
		return actName;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}