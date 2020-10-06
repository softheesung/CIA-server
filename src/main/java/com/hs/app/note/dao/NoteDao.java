package com.hs.app.note.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.hs.app.note.vo.NoteInfo;
import com.hs.app.note.vo.RoomInfo;

public class NoteDao extends SqlSessionDaoSupport {
	
	
	public RoomInfo getRoomInfo(int roomIdx) {
		return getSqlSession().selectOne("note.getRoomInfo", roomIdx);
	}
	
	public int getMyRoomSize(int toUserIdx) {
		Map<String,Object> pMap = new HashMap<String, Object>();
		pMap.put("toUserIdx", toUserIdx);
		return getSqlSession().selectOne("note.getMyRoomSize", pMap);
	}
	public List<RoomInfo> getMyRoomList(int toUserIdx, int startRow, int rowBlockCount) {
		Map<String,Object> pMap = new HashMap<String, Object>();
		pMap.put("toUserIdx", toUserIdx);
		pMap.put("startRow", startRow);
		pMap.put("rowBlockCount", rowBlockCount);
		return getSqlSession().selectList("note.getMyRoomList", pMap);
	}
	
	/** idx보다 최신 게시물 쿼리 */
	public List<NoteInfo> getMyRecentNoteList(int dateIdx, int idx, int maxRow) {
		Map<String,Object> pMap = new HashMap<String,Object>();
        pMap.put("maxRow", maxRow);
        pMap.put("idx", idx);
        pMap.put("dateIdx", dateIdx);
        return getSqlSession().selectList("note.getMyRecentNoteList", pMap);
	}
	
	/** MY목록 크기 가져오기 */
    public int getMyNoteSize(int dateIdx) {
    	Map<String,Object> pMap = new HashMap<String,Object>();
    	pMap.put("dateIdx", dateIdx);
        return getSqlSession().selectOne("note.getMyNoteSize", pMap);
    }
    
    /** MY목록 가져오기 */
    public List<NoteInfo> getMyNoteList(int dateIdx, int startRow, int rowBlockCount) {
        Map<String,Object> pMap = new HashMap<String,Object>();
        pMap.put("startRow", startRow);
        pMap.put("rowBlockCount", rowBlockCount);
        pMap.put("dateIdx", dateIdx);
        return getSqlSession().selectList("note.getMyNoteList", pMap);
    }
	
	/** 특정 쪽지 가져오기 */
	public NoteInfo getNote(int idx) {
		return getSqlSession().selectOne("note.getNote", idx);
	}
	
	/** 수신자 쪽지 삭제 */
	public Integer updateToDelFlag(int idx, boolean toDelFlag) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("idx", idx);
		pMap.put("toDelFlag", toDelFlag);
		return getSqlSession().update("note.updateToDelFlag", pMap);
	}
	
	/** 발신자 쪽지 삭제 */
	public Integer updateFromDelFlag(int idx, boolean fromDelFlag) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("idx", idx);
		pMap.put("fromDelFlag", fromDelFlag);
		return getSqlSession().update("note.updateFromDelFlag", pMap);
	}
	
	/** 등록 */
	public Integer insertNote(NoteInfo noteInfo) {
		return getSqlSession().insert("note.insertNote", noteInfo);
	}
	
	/** 읽음처리 */
	public boolean updateNoteReadFlag(int idx) {
		Integer r = getSqlSession().update("note.updateNoteReadFlag", idx);
		if(r != null && r > 0) {
			return true;
		}
		return false;
	}
	
	/** 삭제 */
	public Integer deleteNoteByIdx(int idx) {
		return getSqlSession().delete("note.deleteNoteByIdx", idx);
	}
	
	/** 목록 크기 가져오기 */
    public int getNoteSize(String q, Integer toUserIdx, Integer fromUserIdx) {
    	Map<String,Object> pMap = new HashMap<String,Object>();
    	pMap.put("q", q);
    	pMap.put("toUserIdx", toUserIdx);
        pMap.put("fromUserIdx", fromUserIdx);
        return getSqlSession().selectOne("note.getNoteSize", pMap);
    }
    
    /** 목록 가져오기 */
    public List<NoteInfo> getNoteList(int startRow, int rowBlockCount, String q, Integer toUserIdx, Integer fromUserIdx) {
        Map<String,Object> pMap = new HashMap<String,Object>();
        pMap.put("startRow", startRow);
        pMap.put("rowBlockCount", rowBlockCount);
        pMap.put("q", q);
        pMap.put("toUserIdx", toUserIdx);
        pMap.put("fromUserIdx", fromUserIdx);
        return getSqlSession().selectList("note.getNoteList", pMap);
    }
    

}
