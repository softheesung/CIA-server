package com.hs.app.pay.vo;

public class PayInfo {
	
	
	private int idx;
	private String regdate;
	private String goodName;
	private String oid;
	private int amount;
	private int userIdx;
	private int ball;
	private boolean sFlag;
	private boolean refundFlag;
	
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public int getBall() {
		return ball;
	}
	public void setBall(int ball) {
		this.ball = ball;
	}
	public boolean issFlag() {
		return sFlag;
	}
	public void setsFlag(boolean sFlag) {
		this.sFlag = sFlag;
	}
	public boolean isRefundFlag() {
		return refundFlag;
	}
	public void setRefundFlag(boolean refundFlag) {
		this.refundFlag = refundFlag;
	}
	
	
	
	
}
