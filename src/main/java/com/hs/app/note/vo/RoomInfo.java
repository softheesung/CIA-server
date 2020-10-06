package com.hs.app.note.vo;

public class RoomInfo {
	
	private int idx; // notes pk
	private int roomIdx; // notes_room pk
	private int fromUserIdx;
	private String fromName;
	private String fromImg;
	private int toUserIdx;
	private String sendtime;
	private String msg;
	private boolean readFlag;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getRoomIdx() {
		return roomIdx;
	}
	public void setRoomIdx(int roomIdx) {
		this.roomIdx = roomIdx;
	}
	public int getFromUserIdx() {
		return fromUserIdx;
	}
	public void setFromUserIdx(int fromUserIdx) {
		this.fromUserIdx = fromUserIdx;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromImg() {
		return fromImg;
	}
	public void setFromImg(String fromImg) {
		this.fromImg = fromImg;
	}
	public int getToUserIdx() {
		return toUserIdx;
	}
	public void setToUserIdx(int toUserIdx) {
		this.toUserIdx = toUserIdx;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isReadFlag() {
		return readFlag;
	}
	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}
	
	
}
