package VO;

import java.util.Date;

public class CommunityMembersVO {
	private String userid;
	private int commCode;
	private String activityName;
	private String answer1;
	private String answer2;
	private String answer3;
	private Date joinDate;
	private Date updateDate;
	
	public CommunityMembersVO(String userid, int commCode, String activityName, String answer1, String answer2,
			String answer3, Date joinDate, Date updatedate) {
		super();
		this.userid = userid;
		this.commCode = commCode;
		this.activityName = activityName;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.joinDate = joinDate;
		this.updateDate = updatedate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getCommCode() {
		return commCode;
	}

	public void setCommCode(int commCode) {
		this.commCode = commCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updatedate) {
		this.updateDate = updatedate;
	}
}
