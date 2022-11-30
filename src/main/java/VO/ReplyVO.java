package VO;

import java.util.Date;

public class ReplyVO {
	private String userid;
	private String replyContent;
	private String category;
	private int commCode;
	private int boardNum;
	private char deleted_yn;
	private int replyNum;
	private Date regDate;
	private Date updateDate;
	
	public ReplyVO(String userid, String replyContent, String category, int commCode, int boardNum, char deleted_yn, int replyNum, Date regDate, Date updateDate) {
		super();
		this.userid = userid;
		this.replyContent = replyContent;
		this.category = category;
		this.commCode = commCode;
		this.boardNum = boardNum;
		this.deleted_yn = deleted_yn;
		this.replyNum = replyNum;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCommCode() {
		return commCode;
	}

	public void setCommCode(int commCode) {
		this.commCode = commCode;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public char getDeleted_yn() {
		return deleted_yn;
	}

	public void setDeleted_yn(char deleted_yn) {
		this.deleted_yn = deleted_yn;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
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
}
