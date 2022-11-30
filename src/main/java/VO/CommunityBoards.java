package VO;

import java.io.Serializable;

public class CommunityBoards implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int commCode;
	private int boardNum;
	private String boardName;
	private String category;
	private int boardOrder;
	
	public CommunityBoards(int commCode, int boardNum, String boardName, String category, int boardOrder) {
		super();
		this.commCode = commCode;
		this.boardNum = boardNum;
		this.boardName = boardName;
		this.category = category;
		this.boardOrder = boardOrder;
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

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBoardOrder() {
		return boardOrder;
	}

	public void setBoardOrder(int boardOrder) {
		this.boardOrder = boardOrder;
	}
}
