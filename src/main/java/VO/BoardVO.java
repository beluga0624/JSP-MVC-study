package VO;

import java.util.Date;

public class BoardVO {
	private int commCode;
	private int boardnum;
	private String boardName;
	private String writer;
	private String subject;
	private String content;
	private int likeCount;
	private int readCount;
	private Date regDate;
	private Date updateDate;
	private String deleted_yn;
	
	public BoardVO(int commCode, int boardnum, String boardName, String writer, String subject, String content,
			int likeCount, int readCount, Date regDate, Date updateDate, String deleted_yn) {
		super();
		this.commCode = commCode;
		this.boardnum = boardnum;
		this.boardName = boardName;
		this.writer = writer;
		this.subject = subject;
		this.content = content;
		this.likeCount = likeCount;
		this.readCount = readCount;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.deleted_yn = deleted_yn;
	}

	public int getCommCode() {
		return commCode;
	}

	public void setCommCode(int commCode) {
		this.commCode = commCode;
	}

	public int getBoardnum() {
		return boardnum;
	}

	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
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

	public String getDeleted_yn() {
		return deleted_yn;
	}

	public void setDeleted_yn(String deleted_yn) {
		this.deleted_yn = deleted_yn;
	}
}
