package DAO;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import VO.CommBoardVO;
import VO.ReplyVO;
import VO.SiteBoardVO;
import VO.UploadFileVO;

public class BoardDAO {
	Connection con;
	private static BoardDAO boardDAO;
	
	private BoardDAO() {}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public static BoardDAO getInstance() {
		
		if(boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		
		return boardDAO;
	}
	
	//공지사항 리스트 가져오기
	public ArrayList<SiteBoardVO> getNoticeList(int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int startrow = (page-1)*10;
		int endrow = startrow + limit;
		ArrayList<SiteBoardVO> boardList = new ArrayList<SiteBoardVO>();
		
		try {
			sql = "select * from site_board where deleted_yn = 'N' and board_category = 'notice' and rownum >= ? and rownum < ? order by board_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do {
					SiteBoardVO board = new SiteBoardVO(rs.getInt("board_num"),
														rs.getString("writer"),
														rs.getString("subject"),
														rs.getString("content"),
														rs.getInt("read_count"),
														rs.getDate("reg_date"),
														rs.getDate("update_date"),
														'N',
														rs.getString("board_category"),
														rs.getInt("reply_count"));
					
					boardList.add(board);
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
		
		return boardList;
	}
	
	//aside용 공지사항 리스트
	public ArrayList<SiteBoardVO> getNoticeList2(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<SiteBoardVO> boardList = new ArrayList<SiteBoardVO>();
		
		try {
			sql = "select * from site_board where deleted_yn = 'N' and board_category = 'notice' and rownum < 6 order by board_num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do {
					SiteBoardVO board = new SiteBoardVO(rs.getInt("board_num"),
														rs.getString("writer"),
														rs.getString("subject"),
														rs.getString("content"),
														rs.getInt("read_count"),
														rs.getDate("reg_date"),
														rs.getDate("update_date"),
														'N',
														rs.getString("board_category"),
														rs.getInt("reply_count"));
					
					boardList.add(board);
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
		
		return boardList;
	}
	
	//공지사항 페이징용 갯수 가져오기
	public int getNoticeListCnt() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String sql = "";
		
		try {
			sql = "select count(*) from site_board where board_category = 'notice' and deleted_yn = 'N'";
			pstmt = con.prepareStatement(sql);
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
	
	//공지사항 등록
	public int insertNotice(SiteBoardVO siteBoard, ArrayList<UploadFileVO> fileList) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int insertCnt1 = 0;
		int insertCnt2 = 0;

		try {
			int boardNum = 0;
			sql = "select max(board_num) as board_num from site_board where board_category = 'notice'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardNum = rs.getInt(1) + 1;
			}else {
				boardNum = 1;
			}
			
			insertCnt2 = fileUpload(fileList, boardNum);
			
			sql = "insert into site_board values (?, ?, ?, ?, ?, sysdate, null, 'N', ?, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, siteBoard.getWriter());
			pstmt.setString(3, siteBoard.getSubject());
			pstmt.setString(4, siteBoard.getContent());
			pstmt.setInt(5, 0);
			pstmt.setString(6, "notice");
			insertCnt1 = pstmt.executeUpdate();
			
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
		
		return insertCnt1 + insertCnt2;
	}
	
	//공지사항 글보기
	public SiteBoardVO getNoticeDetail(int boardNum, String category, boolean isCookieExist) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		SiteBoardVO board = null;
		
		try {
			if(!isCookieExist) {
				sql = "update site_board set read_count = read_count + 1  where board_num = ? and board_category = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, boardNum);
				pstmt.setString(2, category);
				int updateCnt = pstmt.executeUpdate();
				
				if(updateCnt > 0) {
					con.commit();
				}else {
					con.rollback();
				}
			}
			
			sql = "select * from site_board where board_num = ? and board_category = ? and deleted_yn = 'N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, category);
			rs = pstmt.executeQuery();
	
			if(rs.next()) {
				board = new SiteBoardVO(rs.getInt("board_num"),
										rs.getString("writer"),
										rs.getString("subject"),
										rs.getString("content"),
										rs.getInt("read_count"),
										rs.getDate("reg_date"),
										rs.getDate("update_date"),
										'N',
										rs.getString("board_category"),
										rs.getInt("reply_count"));
			};

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
		
		return board;
	}
	
	//파일 업로드
	public int fileUpload(ArrayList<UploadFileVO> fileList, int boardNum) {
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCount = 0;
		
		try {
			sql = "insert into uploadFile values (?, ?, ?, ?, ?, ?)";
			
			for(UploadFileVO file : fileList) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, file.getFileName());
				pstmt.setLong(2, file.getFileSize());
				pstmt.setString(3, file.getCategory());
				pstmt.setInt(4, file.getCommCode());
				pstmt.setString(5, file.getType());
				pstmt.setInt(6, boardNum);
				insertCount += pstmt.executeUpdate();
			}
			
			if(insertCount != fileList.size()) {
				insertCount = 0;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	
	//파일 업로드
	public int fileUpload(ArrayList<UploadFileVO> fileList, int boardNum, int commCode, String category) {
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCount = 0;
		
		try {
			sql = "insert into uploadFile values (?, ?, ?, ?, ?, ?)";
			
			for(UploadFileVO file : fileList) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, file.getFileName());
				pstmt.setLong(2, file.getFileSize());
				pstmt.setString(3, category);
				pstmt.setInt(4, commCode);
				pstmt.setString(5, file.getType());
				pstmt.setInt(6, boardNum);
				insertCount += pstmt.executeUpdate();
			}
			
			if(insertCount != fileList.size()) {
				insertCount = 0;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	
	//업로드 파일 삭제
	public int deleteFile(SiteBoardVO siteBoard, ArrayList<String> remainFiles) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int deletedCount = 0;
		String sql = "";
		ArrayList<String> deleteFiles = new ArrayList<String>();
		
		try {
			
			sql = "select file_name from uploadFile where category = ? and board_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, siteBoard.getBoardCategory());
			pstmt.setInt(2, siteBoard.getBoardNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do {
					if(!remainFiles.contains(rs.getString("file_name"))) {
						deleteFiles.add(rs.getString("file_name"));
					}
				}while(rs.next());
			}

			for(String fileName : deleteFiles) {
				
				sql = "delete uploadFile where category = ? and board_num = ? and file_name = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, siteBoard.getBoardCategory());
				pstmt.setInt(2, siteBoard.getBoardNum());
				pstmt.setString(3, fileName);
				
				deletedCount += pstmt.executeUpdate();
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
		
		return deletedCount;
	}
	
	//파일리스트 불러오기
	public ArrayList<UploadFileVO> getFileList(int boardNum, String category) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<UploadFileVO> fileList = new ArrayList<UploadFileVO>();
		
		try {
			sql = "select * from uploadFile where board_num = ? and category = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, category);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do {
					UploadFileVO file = new UploadFileVO(rs.getString("file_name"),
														 rs.getLong("file_size"),
														 rs.getString("category"),
														 rs.getInt("comm_code"),
														 rs.getString("type"),
														 rs.getInt("board_num"));
					
					fileList.add(file);
					
				}while(rs.next());
				
			}
			
//			System.out.println(fileList.get(0).getFileName());
			
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

		return fileList;
	}
	
	//커뮤니티 게시판 파일리스트 불러오기
	public ArrayList<UploadFileVO> getFileList(int boardNum, String category, int commCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<UploadFileVO> fileList = new ArrayList<UploadFileVO>();
		
		try {
			sql = "select * from uploadFile where board_num = ? and category = ? and comm_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, category);
			pstmt.setInt(3, commCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do {
					UploadFileVO file = new UploadFileVO(rs.getString("file_name"),
														 rs.getLong("file_size"),
														 rs.getString("category"),
														 rs.getInt("comm_code"),
														 rs.getString("type"),
														 rs.getInt("board_num"));
					
					fileList.add(file);
					
				}while(rs.next());
				
			}
			
//				System.out.println(fileList.get(0).getFileName());
			
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

		return fileList;
	}
	
	//댓글 등록
	public int insertReply(ReplyVO rpl) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int insertCnt = 0;
		int rplNum = 0;
		
		try {
			sql = "select max(reply_number) from reply where category = ? and boardNum = ? and commCode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rpl.getCategory());
			pstmt.setInt(2, rpl.getBoardNum());
			pstmt.setInt(3, rpl.getCommCode());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rplNum = rs.getInt(1) + 1;
			}else {
				rplNum = 1;
			}
			
			
			
			
			sql = "insert into reply values (?, ?, ?, ?, ?, ?, sysdate, null, 'N')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rpl.getUserid());
			pstmt.setString(2, rpl.getCategory());
			pstmt.setInt(3, rpl.getCommCode());
			pstmt.setInt(4, rpl.getBoardNum());
			pstmt.setInt(5, rplNum);
			pstmt.setString(6, rpl.getReplyContent());

			insertCnt = pstmt.executeUpdate();
			
			
			
			sql = "update comm_board set reply_count = reply_count + 1 where board_num = ? and board_category = ? and comm_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rpl.getBoardNum());
			pstmt.setString(2, rpl.getCategory());
			pstmt.setInt(3, rpl.getCommCode());
			
			insertCnt += pstmt.executeUpdate();
			
			if(insertCnt == 2) {
				insertCnt = 1;
			}else {
				insertCnt = 0;
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
		
		return insertCnt;
	}
	
	//댓글 리스트 가져오기
	public ArrayList<ReplyVO> getReplies(int boardNum, String category, int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReplyVO> replies = new ArrayList<ReplyVO>();
		String sql = "";
		
		try {
			sql = "select * from reply where category = ? and boardNum = ? and rowNum >= ? "
					+ " and rowNum < ? and deleted_yn = 'N' order by reply_number desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, boardNum);
			pstmt.setInt(3, page);
			pstmt.setInt(4, limit);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					
					ReplyVO reply = new ReplyVO(rs.getString("userid"),
												rs.getString("reply_content"),
												rs.getString("category"),
												rs.getInt("commCode"),
												rs.getInt("boardNum"),
												'N',
												rs.getInt("reply_number"),
												rs.getDate("regDate"),
												rs.getDate("updateDate"));
					
					replies.add(reply);
					
				}while(rs.next());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}

		return replies;
	}
	
	//댓글 수 가져오기
	public int getRepliesCount(int boardNum, String category) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String sql = "";
		
		try {
			sql = "select count(*) from reply where category = ? and boardNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, boardNum);
			
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
	
	//댓글 수정
	public int updateReply(ReplyVO rpl) {
		PreparedStatement pstmt = null;
		String sql = "";
		int updateCnt = 0;
		
		try {
			sql = "update reply set reply_content = ? where category = ? and boardNum = ? and reply_number = ? and commCode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rpl.getReplyContent());
			pstmt.setString(2, rpl.getCategory());
			pstmt.setInt(3, rpl.getBoardNum());
			pstmt.setInt(4, rpl.getReplyNum());
			pstmt.setInt(5, rpl.getCommCode());
			
			updateCnt = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return updateCnt;
	}
	
	//댓글 삭제
	public int deleteReply(int bno, String category, int rplNo) {
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		String sql = "";
		
		try {
			sql = "update reply set deleted_yn = 'Y' where category = ? and boardNum = ? and reply_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, bno);
			pstmt.setInt(3, rplNo);
			
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
	
	//커뮤니티 댓글 삭제
	public int deleteReply(int commCode, int bno, String category, int rplNo) {
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		String sql = "";
		
		try {
			sql = "update reply set deleted_yn = 'Y' where category = ? and boardNum = ? and reply_number = ? and commCode = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, bno);
			pstmt.setInt(3, rplNo);
			pstmt.setInt(4, commCode);
			
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
	
	//게시글 삭제
	public int deleteBoard(String category, int boardNum) {
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		String sql = "";
		
		try {
			sql = "update site_board set deleted_yn = 'Y' where board_category = ? and board_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, boardNum);
			
			updateCnt = pstmt.executeUpdate();
			
			System.out.println("updateCnt " + updateCnt);
			
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
	
	//게시글 수정
	public int updateBoard(SiteBoardVO siteBoard, ArrayList<UploadFileVO> fileList, ArrayList<String> remainFiles) {
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCnt1 = 0;
		int insertCnt2 = 0;
		int deleteCnt = 0;

		try {
			int boardNum = siteBoard.getBoardNum();
			
			if(remainFiles.size() != 0) {
				deleteCnt = deleteFile(siteBoard, remainFiles);
			}
			
			if(fileList.size() != 0) {
				insertCnt2 = fileUpload(fileList, boardNum);
			}
			
			sql = "update site_board set subject = ?, content = ?, update_date = sysdate where board_num = ? and board_category = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, siteBoard.getSubject());
			pstmt.setString(2, siteBoard.getContent());
			pstmt.setInt(3, siteBoard.getBoardNum());
			pstmt.setString(4, siteBoard.getBoardCategory());
			insertCnt1 = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return insertCnt1 + insertCnt2 + deleteCnt;
	}
	
	//FAQ 페이징용 갯수 가져오기
	public int getFaqListCnt() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String sql = "";
		
		try {
			sql = "select count(*) from site_board where board_category = 'faq' and deleted_yn = 'N'";
			pstmt = con.prepareStatement(sql);
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
	
	//FAQ 리스트 가져오기
	public ArrayList<SiteBoardVO> getFaqList(int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int startrow = (page-1)*10;
		int endrow = startrow + limit;
		ArrayList<SiteBoardVO> boardList = new ArrayList<SiteBoardVO>();
		
		try {
			sql = "select * from site_board where deleted_yn = 'N' and board_category = 'faq' and rownum >= ? and rownum < ? order by board_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do {
					SiteBoardVO board = new SiteBoardVO(rs.getInt("board_num"),
														rs.getString("writer"),
														rs.getString("subject"),
														rs.getString("content"),
														rs.getInt("read_count"),
														rs.getDate("reg_date"),
														rs.getDate("update_date"),
														'N',
														rs.getString("board_category"),
														rs.getInt("reply_count"));
					
					boardList.add(board);
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
		
		return boardList;
	}
	
	//aside용 FAQ 리스트
	public ArrayList<SiteBoardVO> getFaqList2(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<SiteBoardVO> boardList = new ArrayList<SiteBoardVO>();
		
		try {
			sql = "select * from site_board where deleted_yn = 'N' and board_category = 'faq' and rownum < 6 order by board_num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do {
					SiteBoardVO board = new SiteBoardVO(rs.getInt("board_num"),
														rs.getString("writer"),
														rs.getString("subject"),
														rs.getString("content"),
														rs.getInt("read_count"),
														rs.getDate("reg_date"),
														rs.getDate("update_date"),
														'N',
														rs.getString("board_category"),
														rs.getInt("reply_count"));
					
					boardList.add(board);
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
		
		return boardList;
	}
	
	//FAQ 등록
	public int insertFaq(SiteBoardVO siteBoard, ArrayList<UploadFileVO> fileList) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int insertCnt1 = 0;
		int insertCnt2 = 0;

		try {
			int boardNum = 0;
			sql = "select max(board_num) as board_num from site_board where board_category = 'faq'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardNum = rs.getInt(1) + 1;
			}else {
				boardNum = 1;
			}
			
			insertCnt2 = fileUpload(fileList, boardNum);
			
			sql = "insert into site_board values (?, ?, ?, ?, ?, sysdate, null, 'N', ?, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, siteBoard.getWriter());
			pstmt.setString(3, siteBoard.getSubject());
			pstmt.setString(4, siteBoard.getContent());
			pstmt.setInt(5, 0);
			pstmt.setString(6, "faq");
			insertCnt1 = pstmt.executeUpdate();
			
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
		
		return insertCnt1 + insertCnt2;
	}
	
	//커뮤니티 게시글 갯수 가져오기
	public int getCommBoardListCnt(int commCode, String boardName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String sql = "";
		
		try {
			sql = "select count(*) from comm_board where comm_code = ? and board_name = ? and deleted_yn = 'N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			pstmt.setString(2, boardName);
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
	
	//커뮤니티 게시글 리스트 가져오기
	public ArrayList<CommBoardVO> getCommBoardList(int commCode, String boardName, int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int startrow = (page-1)*10;
		int endrow = startrow + limit;
		ArrayList<CommBoardVO> boardList = new ArrayList<CommBoardVO>();
		
		try {
			sql = "select * from comm_board where deleted_yn = 'N' and comm_code = ? and board_name = ? and rownum >= ? and rownum < ? order by board_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			pstmt.setString(2, boardName);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do {
					CommBoardVO board = new CommBoardVO(rs.getInt("comm_code"),
														rs.getString("board_name"),
														rs.getInt("board_num"),
														rs.getString("writer"),
														rs.getString("subject"),
														rs.getString("content"),
														rs.getInt("read_count"),
														rs.getDate("reg_date"),
														rs.getDate("update_date"),
														'N',
														rs.getInt("reply_count"));
					
					boardList.add(board);
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
		
		return boardList;
	}
	
	//커뮤니티 게시글 등록
	public int insertCommBoard(CommBoardVO commBoard, ArrayList<UploadFileVO> fileList) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int insertCnt1 = 0;
		int insertCnt2 = 0;

		try {
			int boardNum = 0;
			sql = "select max(board_num) as board_num from comm_board where comm_code = ? and board_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commBoard.getCommCode());
			pstmt.setString(2, commBoard.getBoardName());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardNum = rs.getInt(1) + 1;
			}else {
				boardNum = 1;
			}
			
			if(fileList.size() != 0) {
				insertCnt2 = fileUpload(fileList, boardNum, commBoard.getCommCode(), commBoard.getBoardName());
			}else {
				insertCnt2 = 1;
			}
			
			System.out.println(boardNum + " " + commBoard.getBoardName() + " " + commBoard.getCommCode());

			sql = "insert into comm_board values (?, ?, ?, ?, ?, ?, 0, sysdate, null, 'N', 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commBoard.getCommCode());
			pstmt.setString(2, commBoard.getBoardName());
			pstmt.setInt(3, boardNum);
			pstmt.setString(4, commBoard.getWriter());
			pstmt.setString(5, commBoard.getSubject());
			pstmt.setString(6, commBoard.getContent());
			
			insertCnt1 = pstmt.executeUpdate();
			
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
		
		return insertCnt1 + insertCnt2;
	}
	
	//커뮤니티 게시판 글 보기
	public CommBoardVO getCommBoardDetail(int boardNum, String category, int commCode, boolean isCookieExist) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		CommBoardVO board = null;
		
		try {
			if(!isCookieExist) {
				sql = "update comm_board set read_count = read_count + 1  where board_num = ? and board_name = ? and comm_code = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, boardNum);
				pstmt.setString(2, category);
				pstmt.setInt(3, commCode);
				int updateCnt = pstmt.executeUpdate();
				
				if(updateCnt > 0) {
					con.commit();
				}else {
					con.rollback();
				}
			}
			
			sql = "select * from comm_board where board_num = ? and board_name = ? and  comm_code = ? and deleted_yn = 'N'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, category);
			pstmt.setInt(3, commCode);
			rs = pstmt.executeQuery();
	
			if(rs.next()) {
				board = new CommBoardVO(rs.getInt("comm_code"),
										rs.getString("board_name"),
										rs.getInt("board_num"),
										rs.getString("writer"),
										rs.getString("subject"),
										rs.getString("content"),
										rs.getInt("read_count"),
										rs.getDate("reg_date"),
										rs.getDate("update_date"),
										'N',
										rs.getInt("reply_count"));
			};

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
		
		return board;
	}
	
	//커뮤니티 공지사항 이름 가져오기
	public String getCommNoticeBoard(int commCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String noticeName = "";
		
		try {
			sql = "select boardName from comm_boardList where commCode = ? and boardNum = 1";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeName = rs.getString(1);
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

		return noticeName;
	}
	
	//커뮤니티 자유게시판 이름 가져오기
	public String getCommFreeBoard(int commCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String FreeBoardName = "";
		
		try {
			sql = "select boardName from comm_boardList where commCode = ? and boardNum = 2";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				FreeBoardName = rs.getString(1);
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
		
		return FreeBoardName;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
