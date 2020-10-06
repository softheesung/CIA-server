package com.hs.app.pay.vo;

public class ExInfo {
	
	private int idx;
	private int userIdx;
	private int amount;
	private String regdate;
	private Boolean resultFlag;
	
	private String userName;
	private int cashPoint;
	
	private String accountNumber;
	private String accountBank;
	private String accountHolder;
	
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public int getCashPoint() {
		return cashPoint;
	}
	public void setCashPoint(int cashPoint) {
		this.cashPoint = cashPoint;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public Boolean getResultFlag() {
		return resultFlag;
	}
	public void setResultFlag(Boolean resultFlag) {
		this.resultFlag = resultFlag;
	}
	
	
}
