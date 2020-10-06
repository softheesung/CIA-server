package com.hs.app.note.vo;

public class NoteInfo {
	
	private Integer idx;
	private Integer fromUserIdx;
	private String fromUserName;
	private Integer toUserIdx;
	private String toUserName;
	private String msg;
	private String sendtime;
	private String readtime;
	private boolean readFlag;
	private boolean toDelFlag;
	private boolean fromDelFlag;
	private Integer roomIdx; // delete col
	private Integer dateIdx;
	
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public Integer getFromUserIdx() {
		return fromUserIdx;
	}
	public void setFromUserIdx(Integer fromUserIdx) {
		this.fromUserIdx = fromUserIdx;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public Integer getToUserIdx() {
		return toUserIdx;
	}
	public void setToUserIdx(Integer toUserIdx) {
		this.toUserIdx = toUserIdx;
	}
	
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getReadtime() {
		return readtime;
	}
	public void setReadtime(String readtime) {
		this.readtime = readtime;
	}
	public boolean isReadFlag() {
		return readFlag;
	}
	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}
	public boolean isToDelFlag() {
		return toDelFlag;
	}
	public void setToDelFlag(boolean toDelFlag) {
		this.toDelFlag = toDelFlag;
	}
	public boolean isFromDelFlag() {
		return fromDelFlag;
	}
	public void setFromDelFlag(boolean fromDelFlag) {
		this.fromDelFlag = fromDelFlag;
	}
	public Integer getRoomIdx() {
		return roomIdx;
	}
	public void setRoomIdx(Integer roomIdx) {
		this.roomIdx = roomIdx;
	}
	public Integer getDateIdx() {
		return dateIdx;
	}
	public void setDateIdx(Integer dateIdx) {
		this.dateIdx = dateIdx;
	}
	
	
	
	
	
	
}
