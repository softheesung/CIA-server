package com.hs.app.note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.app.note.dao.NoteDao;
import com.hs.app.note.vo.NoteInfo;
import com.hs.app.note.vo.RoomInfo;
import com.hs.common.util.PageUtil;

public class NoteService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private NoteDao noteDao;
	
	
	public void ExampleMethods(int fromUserIdx) {
		
		// 내가 안읽은쪽지 갯수
		//int unreadCount = noteDao.getMyNoteSize(null, fromUserIdx, false);
		
		// 나의 모든 쪽지 갯수
		//int allCount = noteDao.getMyNoteSize(null, fromUserIdx, null);
			
	}
	
    public Map<String,Object> loadMyRoomList(int page, int rowBlockCount, Integer toUserIdx) {
        
        page = page<1?1:page;   
        //if(q!=null) {q = q.trim(); logger.debug("검색어: "+q+"로 데이터 검색..");}
        
        PageUtil pu = new PageUtil(page,noteDao.getMyRoomSize(toUserIdx),rowBlockCount,10);			
		List<RoomInfo> lists = noteDao.getMyRoomList(toUserIdx, pu.getStartRow(), pu.getRowBlockCount());	
		
        Map<String,Object> rst = new HashMap<String,Object>();
		rst.put("pageNav", pu);
		rst.put("list", lists);
        return rst;
    }
	
	/** MY쪽지 목록 로드 */
    public Map<String,Object> loadMyNoteList(int page, int rowBlockCount, int myIdx, int dateIdx) {
        
        page = page<1?1:page;   
        //if(q!=null) {q = q.trim(); logger.debug("검색어: "+q+"로 데이터 검색..");}
        
        PageUtil pu = new PageUtil(page,noteDao.getMyNoteSize(dateIdx),rowBlockCount,10);			
		List<NoteInfo> lists = noteDao.getMyNoteList(dateIdx, pu.getStartRow(), pu.getRowBlockCount());	
		
        Map<String,Object> rst = new HashMap<String,Object>();
		rst.put("pageNav", pu);
		rst.put("list", lists);
		rst.put("myIdx", myIdx);
        return rst;
    }
	
	
	/** 쪽지 목록 로드 */
    public Map<String,Object> loadNoteList(int page, int rowBlockCount, String q, Integer toUserIdx, Integer fromUserIdx) {
        
        page = page<1?1:page;   
        if(q!=null) {q = q.trim(); logger.debug("검색어: "+q+"로 데이터 검색..");}
        
        PageUtil pu = new PageUtil(page,noteDao.getNoteSize(q, toUserIdx, fromUserIdx),rowBlockCount,10);			
		List<NoteInfo> lists = noteDao.getNoteList(pu.getStartRow(), pu.getRowBlockCount(), q, toUserIdx, fromUserIdx);		
		
        Map<String,Object> rst = new HashMap<String,Object>();
		rst.put("pageNav", pu);
		rst.put("list", lists);
        return rst;
    }
    

}
