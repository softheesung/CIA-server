package com.hs.app.controller.rest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hs.app.jwt.service.JwtService;
import com.hs.app.note.dao.NoteDao;
import com.hs.app.note.service.NoteService;
import com.hs.app.note.vo.NoteInfo;
import com.hs.app.user.dao.UserDao;
import com.hs.common.util.CookieUtil;

@RestController
@RequestMapping(value = "/api/notes")
public class NoteRestController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private JwtService jwtService;
	@Autowired private NoteService noteService;	
	@Autowired private NoteDao noteDao;
	@Autowired private UserDao userDao;
	
	/*
		＃NotesService
		
		[쪽지 목록 쿼리] 	/api/notes/my-messages 	[GET] 
			
		[쪽지 목록 쿼리] 	/api/notes 				[GET] 
		[쪽지 다중 삭제] 	/api/notes 				[DELETE] 
		[쪽지 등록] 		/api/notes 				[POST] 
		[쪽지 읽음 처리] 	/api/notes/{idx} 		[PUT] 
		
	*/
	
//	/** (list) MY쪽지 */
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public Map<String,Object> getMyNoteList(
//        @RequestParam(required=false) Integer page,
//        @RequestParam(required=false) String q) {
//		
//        page = (page==null||page<1)?1:page;
//        Map<String,Object> rst = noteService.loadNoteList(page, 10, q, null, null);
//        return rst;
//	}
//	
	/** (list) MY 쪽지 */
	/*@RequestMapping(value = "/my-messages", method = RequestMethod.GET)
	public Map<String,Object> getMyNoteList(
        @RequestParam(required=false) Integer page,
        @RequestParam(required=false) Boolean readFlag,
        @RequestParam(required=false) String q) {
		
		int userIdx = 1;
		
        page = (page==null||page<1)?1:page;
        Map<String,Object> rst = noteService.loadMyNoteList(page, 15, userIdx, readFlag, q);
        return rst;
	}*/
	
	/** (list) 쪽지 
	 * @throws UnsupportedEncodingException */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String,Object> getNoteList(
        @RequestParam(required=false) Integer page,
        @RequestParam(required=false) String q, HttpServletRequest request) throws UnsupportedEncodingException {
		
		
        page = (page==null||page<1)?1:page;
        Map<String,Object> rst = noteService.loadNoteList(page, 10, q, null, null);
        return rst;
	}
	
	/** (update) 읽음처리 */
	@RequestMapping(value = "{idx}", method = RequestMethod.PUT)
	public void updateReadFlag(@PathVariable int idx, HttpServletResponse response) {
		
		if(!noteDao.updateNoteReadFlag(idx)) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	/** (delete) 쪽지 */
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public void deleteBoardItem(@RequestBody int[] idxArray,
            HttpServletRequest request, HttpServletResponse response) {
		
		for(int i=0;i<idxArray.length;i++) {		
			Integer idx = idxArray[i];
			Integer r = noteDao.deleteNoteByIdx(idx);
			if(r==null || r<1) {
				logger.error("게시물이 정상적으로 삭제되지 않았습니다.");
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
		}      
	}
	
	/** (insert) 쪽지 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String,Object> insertNotes(@RequestBody NoteInfo info,
            HttpServletRequest request, HttpServletResponse response) {
        
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		// 유효성 검사 + 회원 일치 여부 검사
		
		Map<String,Object> result = new HashMap<String,Object>();
        Integer r = noteDao.insertNote(info); 
        if(r!=null&&r>0){
        	response.setStatus(HttpServletResponse.SC_OK);
        	result.put("noteInfo", info);
        	return result;
        }else {
        	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
		
		return null;
	}

}
