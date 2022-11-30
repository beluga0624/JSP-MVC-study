package VO;

import java.util.Date;

public class MemberVO {
	private String userid;
	private String userpw;
	private String name;
	private String birthDate;
	private String gender;
	private String phone;
	private String email;
	private int postcd;
	private String addr1;
	private String addr2;
	private Date regDate;
	private Date updateDate;
	
	public MemberVO() {}

	public MemberVO(String userid, String userpw, String name, String birthDate, String gender, String phone,
			String email, int postcd, String addr1, String addr2, Date regDate, Date updateDate) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.postcd = postcd;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPostcd() {
		return postcd;
	}

	public void setPostcd(int postcd) {
		this.postcd = postcd;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
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
	};
}
