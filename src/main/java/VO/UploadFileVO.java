package VO;

public class UploadFileVO {
	private String fileName;
	private long fileSize;
	private String category;
	private int commCode;
	private String type;
	private int boardNum;
	
	public UploadFileVO(String fileName, long fileSize, String category,
			int commCode, String type, int boardNum) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.category = category;
		this.commCode = commCode;
		this.type = type;
		this.boardNum = boardNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
