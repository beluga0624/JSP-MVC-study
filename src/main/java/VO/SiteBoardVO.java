package VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class SiteBoardVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int boardNum;
	private String writer;
	private String subject;
	private String content;
	private int readCnt;
	private Date regDate;
	private Date updateDate;
	private char deleted_yn;
	private String boardCategory;
	private int replyCnt;
	
	private ArrayList<UploadFileVO> uploadFileList;

	public SiteBoardVO(int boardNum, String writer, String subject, String content, int readCnt,
			Date regDate, Date updateDate, char deleted_yn, String boardCategory, int replyCnt) {
		super();
		this.boardNum = boardNum;
		this.writer = writer;
		this.subject = subject;
		this.content = content;
		this.readCnt = readCnt;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.deleted_yn = deleted_yn;
		this.boardCategory = boardCategory;
		this.replyCnt = replyCnt;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public char getDeleted_yn() {
		return deleted_yn;
	}

	public void setDeleted_yn(char deleted_yn) {
		this.deleted_yn = deleted_yn;
	}

	public String getBoardCategory() {
		return boardCategory;
	}

	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	public ArrayList<UploadFileVO> getUploadFileList() {
		return uploadFileList;
	}

	public void setUploadFileList(ArrayList<UploadFileVO> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}
}
