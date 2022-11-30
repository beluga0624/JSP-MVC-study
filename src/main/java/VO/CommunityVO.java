package VO;

import java.io.Serializable;
import java.util.Date;

public class CommunityVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int commCode;
	private String commName;
	private String description;
	private String commAdmin;
	private String mainImg;
	private String subImg;
	private String question1;
	private String question2;
	private String question3;
	private String category;
	private int memberCnt;
	private Date regdate;
	private Date updateDate;
	
	public CommunityVO(int commCode, String commName, String description, String commAdmin, String mainImg,
			String subImg, String question1, String question2, String question3, String category,
			Date regdate, Date updatedate, int memberCnt) {
		super();
		this.commCode = commCode;
		this.commName = commName;
		this.description = description;
		this.commAdmin = commAdmin;
		this.mainImg = mainImg;
		this.subImg = subImg;
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.category = category;
		this.memberCnt = memberCnt;
		this.regdate = regdate;
		this.updateDate = updatedate;
	}

	public int getCommCode() {
		return commCode;
	}

	public void setCommCode(int commCode) {
		this.commCode = commCode;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCommAdmin() {
		return commAdmin;
	}

	public void setCommAdmin(String commAdmin) {
		this.commAdmin = commAdmin;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public String getSubImg() {
		return subImg;
	}

	public void setSubImg(String subImg) {
		this.subImg = subImg;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getmemberCnt() {
		return memberCnt;
	}

	public void setmemberCnt(int memberCnt) {
		this.memberCnt = memberCnt;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updatedate) {
		this.updateDate = updatedate;
	}
}
