package VO;

import java.util.Date;

public class GoodsVO {
	private int commCode;
	private String pCode;
	private String pName;
	private String p_Image;
	private String description;
	private int price;
	private int stock;
	private Date regDate;
	private Date updateDate;
	private String status;
	
	public GoodsVO(int commCode, String pCode, String pName, String p_Image, String description, int price, int stock,
			Date regDate, Date updateDate, String status) {
		super();
		this.commCode = commCode;
		this.pCode = pCode;
		this.pName = pName;
		this.p_Image = p_Image;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.status = status;
	}

	public int getCommCode() {
		return commCode;
	}

	public void setCommCode(int commCode) {
		this.commCode = commCode;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getP_Image() {
		return p_Image;
	}

	public void setP_Image(String p_Image) {
		this.p_Image = p_Image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
