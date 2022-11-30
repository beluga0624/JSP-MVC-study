package SVC.BoardSVC;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.BoardDAO;
import VO.UploadFileVO;

public class GetUploadedFilesService {
	public ArrayList<UploadFileVO> getFileList(int boardNum, String category) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<UploadFileVO> fileList = null;
		
		fileList = boardDAO.getFileList(boardNum, category);
		
		close(con);
		
		return fileList;
	}
	
	public ArrayList<UploadFileVO> getFileList(int boardNum, String category, int commCode) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		ArrayList<UploadFileVO> fileList = null;
		
		fileList = boardDAO.getFileList(boardNum, category, commCode);
		
		close(con);
		
		return fileList;
	}
}
