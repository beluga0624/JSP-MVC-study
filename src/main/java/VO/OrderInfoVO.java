package VO;

import java.util.Date;

public class OrderInfoVO {
	private int commCode;
	private String orderCode;
	private String pCode;
	private int orderQty;
	private String orderer;
	private String addr1;
	private String addr2;
	private int postCode;
	private String tel;
	private Date orderDate;
	private Date updateDate;
	private String paymentMethod;
	private String payCheck;
	private String orderStatus;
	
	public OrderInfoVO(int commCode, String orderCode, String pCode, int orderQty, String orderer, String addr1,
			String addr2, int postCode, String tel, Date orderDate, Date updateDate, String paymentMethod,
			String payCheck, String orderStatus) {
		super();
		this.commCode = commCode;
		this.orderCode = orderCode;
		this.pCode = pCode;
		this.orderQty = orderQty;
		this.orderer = orderer;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.postCode = postCode;
		this.tel = tel;
		this.orderDate = orderDate;
		this.updateDate = updateDate;
		this.paymentMethod = paymentMethod;
		this.payCheck = payCheck;
		this.orderStatus = orderStatus;
	}

	public int getCommCode() {
		return commCode;
	}

	public void setCommCode(int commCode) {
		this.commCode = commCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public String getOrderer() {
		return orderer;
	}

	public void setOrderer(String orderer) {
		this.orderer = orderer;
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

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPayCheck() {
		return payCheck;
	}

	public void setPayCheck(String payCheck) {
		this.payCheck = payCheck;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
