package com.hs.app.pay.vo;

public class CashInfo {
	
	private Integer idx;		// 일련번호
	private Integer userIdx;	// 회원번호
	private String regdate;		// 포인트 획득/사용 시점
	private Integer addPoint;	// 획득 포인트 금액
	private Integer usePoint;	// 사용 포인트 금액	
	private Integer amount;		// 잔여 포인트 금액
	private String memo;		// 획득/사용 메모
	
	private String userName;	// 회원이름
	
	public CashInfo() {}
	public CashInfo(int userIdx, int addPoint, int usePoint, int amount, String memo) {
		this.userIdx = userIdx;
		this.addPoint = addPoint;
		this.usePoint = usePoint;
		this.amount = amount;
		this.memo = memo;
	}
	
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public Integer getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(Integer userIdx) {
		this.userIdx = userIdx;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public Integer getAddPoint() {
		return addPoint;
	}
	public void setAddPoint(Integer addPoint) {
		this.addPoint = addPoint;
	}
	public Integer getUsePoint() {
		return usePoint;
	}
	public void setUsePoint(Integer usePoint) {
		this.usePoint = usePoint;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}

