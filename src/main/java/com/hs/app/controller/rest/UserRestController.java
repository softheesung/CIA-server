package com.hs.app.controller.rest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hs.app.board.dao.BoardDao;
import com.hs.app.jwt.service.JwtService;
import com.hs.app.user.dao.UserDao;
import com.hs.app.user.service.UserService;
import com.hs.app.user.vo.StudyInfo;
import com.hs.app.user.vo.StudyStudent;
import com.hs.app.user.vo.UserInfo;
import com.hs.common.util.CookieUtil;

@RestController
@RequestMapping(value = "/api")
public class UserRestController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private UserService userService;	
	@Autowired private UserDao userDao;
	@Autowired private BoardDao boardDao;
	@Autowired private JwtService jwtService;
	@Autowired private BCryptPasswordEncoder passwordEncoder;

/**
	
사진업로드: /file/upload [POST]

로그인: /api/users/signin [POST] 요청[email,password] 응답헤더[HSID]
회원가입: 	/api/users/signup [POST] 요청[name,email,phonenm,password]
프로필정보 가져오기: /api/users/profile [POST][Authorization] 응답[user(idx,password,name,phonenm,regdate,img)]

스터디등록: /api/study/ [POST][Authorization] 요청[title,img,note,station,signdate,maxPeople] 응답[insertedIdx]
스터디목록 가져오기: /api/study/ [GET] 요청[page,rowBlockCount(1페이지당 가져올 컨텐츠수)] 응답[list,pageNav(페이징정보)]
스터디수정: /api/study/{스터디PK} [PUT][Authorization] 요청[title,img,note,station,signdate,maxPeople]
스터디삭제: /api/study/{스터디PK} [DELETE][Authorization] 
스터디상세정보 가져오기: /api/study/{스터디PK} [GET] 응답[idx,title,img,note,station,signdate,maxPeople]

스터디 수강회원 목록 가져오기: /api/study/{스터디PK}/students [GET] 응답[list]
스터디 가입: /api/study/{스터디PK}/students [POST][Authorization]
스터디 탈퇴: /api/study/{스터디PK}/students [POST][Authorization]


	
*/

	
	
	/** 회원 로그인 */
	@RequestMapping(value = "/users/signin")//, method = RequestMethod.POST)
	public void login(
			@RequestParam(required=false) String email,
			@RequestParam(required=false) String password,
			HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws UnsupportedEncodingException {
		
		if(!userService.signIn(email, password, response)) {
			model.put("email", email);
			model.put("error", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}else {
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}
	
	/** 회원 가입  */
	@RequestMapping(value = "/users/signup")//, method = RequestMethod.POST)
	public Map<String, Object> signUp(/* @RequestBody UserInfo userInfo, */
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String phonenm,
			@RequestParam String password,
			HttpServletRequest request, HttpServletResponse response) {
		
		UserInfo userInfo = new UserInfo();
		userInfo.setName(name);
		userInfo.setEmail(email);
		userInfo.setPhonenm(phonenm);
		userInfo.setPassword(password);
		
		
		logger.info(userInfo.toString());
		
		Map<String,Object> rst = new HashMap<String,Object>();
		
		UserInfo signedUser = userService.signUp(userInfo);
		if(signedUser==null) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			userInfo.setPassword("");
			rst.put("user", userInfo);
			return rst;
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return null;
	}
	
	
	/** 내 프로필 정보 로드  */
	@RequestMapping(value = "/users/profile")//, method = RequestMethod.POST)
	public Map<String, Object> myinfo(HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getAttribute("HSID").toString();
		int userIdx = Integer.parseInt(jwtService.getMemberId(token));
		
		UserInfo userInfo = userDao.getUser(userIdx);
		Map<String,Object> rst = new HashMap<String,Object>();
		rst.put("user", userInfo);
		return rst;
	}
	
	
	
	/** 스터디 삭제 */
	@RequestMapping(value = "/study/{idx}", method = RequestMethod.DELETE)
	public void deleteStudy(
			@PathVariable Integer idx,
			HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getAttribute("HSID").toString();
		int userIdx = Integer.parseInt(jwtService.getMemberId(token));
		
		StudyInfo studyInfo = userDao.getStudy(idx);
		if(studyInfo!=null && studyInfo.getUserIdx() == userIdx) {
			if(!userDao.deleteStudy(idx)) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return;
			}else {
				response.setStatus(HttpServletResponse.SC_OK);
				return;
			}
			
		}
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		
		
	}
	
	/** 스터디 수정 */
	@RequestMapping(value = "/study/{studyIdx}", method = RequestMethod.PUT)
	public void updateStudy(
			@RequestParam String img,
			@RequestParam String title,
			@RequestParam String note,
			@RequestParam String station,
			@RequestParam String signdate,
			@RequestParam Integer maxPeople,
			@PathVariable Integer studyIdx,
			HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getAttribute("HSID").toString();
		int userIdx = Integer.parseInt(jwtService.getMemberId(token));
		
		StudyInfo studyInfo = userDao.getStudy(studyIdx);
		
		if(studyInfo.getUserIdx() != userIdx) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		studyInfo.setImg(img);
		studyInfo.setTitle(title);
		studyInfo.setNote(note);
		studyInfo.setStation(station);
		studyInfo.setSigndate(signdate);
		studyInfo.setMaxPeople(maxPeople);
		
		if(!userDao.updateStudy(studyInfo)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
	/** 스터디 등록 */
	@RequestMapping(value = "/study", method = RequestMethod.POST)
	public Map<String,Object> insertStudy(
			@RequestParam String img,
			@RequestParam String title,
			@RequestParam String note,
			@RequestParam String station,
			@RequestParam String signdate,
			@RequestParam Integer maxPeople,
			HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getAttribute("HSID").toString();
		int userIdx = Integer.parseInt(jwtService.getMemberId(token));
		
		StudyInfo studyInfo = new StudyInfo(userIdx, title, note, img, station, signdate, maxPeople);
		if(userDao.insertStudy(studyInfo)) {
			int idx = userDao.getInsertedStudyIdx(studyInfo);
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("insertedIdx", idx); // 방금 등록된 Row PK values
			return result;
		}
		
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return null;
	}
	
	/** 스터디 정보 조회 */
	@RequestMapping(value = "/study/{studyIdx}", method = RequestMethod.GET)
	public Map<String, Object> getStudy(
			@PathVariable Integer studyIdx,
			HttpServletRequest request, HttpServletResponse response) {
		
		Map<String,Object> rst = new HashMap<String,Object>();
		StudyInfo studyInfo = userDao.getStudy(studyIdx);
		rst.put("info", studyInfo);
		return rst;
	}
	
	/** 스터디 목록 쿼리 */
	@RequestMapping(value = "/study", method = RequestMethod.GET)
	public Map<String, Object> loadStudy(
			@RequestParam Integer page,
			@RequestParam Integer rowBlockCount,
			HttpServletRequest request, HttpServletResponse response) {
		
		page = page==null?1:page;
		rowBlockCount = rowBlockCount==null?15:(rowBlockCount>50||rowBlockCount<2)?15:rowBlockCount;
		
		Map<String,Object> rst = new HashMap<String,Object>();
		rst = userService.loadStudy(page, rowBlockCount);
	
		response.setStatus(HttpServletResponse.SC_OK);
		return rst;
	}
	
	
	
	/** 스터디 수강회원 목록 쿼리 */
	@RequestMapping(value = "/study/{studyIdx}/students", method = RequestMethod.GET)
	public Map<String, Object> loadStudyStudents(
			@PathVariable Integer studyIdx,
			HttpServletRequest request, HttpServletResponse response) {
		
	
		Map<String,Object> rst = new HashMap<String,Object>();
		List<StudyStudent> lists = userDao.getStudyStudents(studyIdx);
		rst.put("lists", lists);
	
		response.setStatus(HttpServletResponse.SC_OK);
		return rst;
	}
	
	
	/** 스터디 가입 */
	@RequestMapping(value = "/study/{studyIdx}/students", method = RequestMethod.POST)
	public void registStudyStudents(
			@PathVariable Integer studyIdx,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		String token = request.getAttribute("HSID").toString();
		int userIdx = Integer.parseInt(jwtService.getMemberId(token));
	
		if(!userDao.insertStudent(studyIdx, userIdx)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		
	}
	
	/** 스터디 탈퇴 */
	@RequestMapping(value = "/study/{studyIdx}/students", method = RequestMethod.DELETE)
	public void withdrawStudyStudents(
			@PathVariable Integer studyIdx,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		String token = request.getAttribute("HSID").toString();
		int userIdx = Integer.parseInt(jwtService.getMemberId(token));
	
		if(!userDao.deleteStudent(studyIdx, userIdx)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		
	}
	
	
	
	
	
	/*
	 * 
	 *
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	/** (다중 삭제) 회원 */
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public void deleteUsers(@RequestBody int[] idxArray, HttpServletResponse response) {
	
		for(int i=0;i<idxArray.length;i++) {		
			Integer idx = idxArray[i];
			if(!userDao.deleteUser(idx)) {
				logger.error("정상적으로 삭제되지 않았습니다.");
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
		}      
	}
	
	
	
	/** (삭제) 회원 */
	@RequestMapping(value = "/{idx}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer idx, HttpServletResponse response) {
		
		if(!userDao.deleteUser(idx)) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/** (패스워드 수정) 회원 */
	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public void updatePassword(@RequestBody Map<String,Object> pMap, HttpServletResponse response) {
		
		Integer idx = Integer.parseInt(pMap.get("idx").toString());
		String password = pMap.get("password").toString();
		
		if(!userDao.updateUserPassword(idx, password)) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	/** (탈퇴여부 수정) 회원 */
	@RequestMapping(value = "/enable", method = RequestMethod.PUT)
	public void updateEFlag(@RequestBody Map<String,Object> pMap, HttpServletResponse response) {
		
		Integer idx = Integer.parseInt(pMap.get("idx").toString());
		boolean eFlag = Boolean.parseBoolean(pMap.get("eFlag").toString());
		
		if(!userDao.updateUserEnable(idx, eFlag)) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	/** (삭제) 권한 */
	@RequestMapping(value = "/auth", method = RequestMethod.DELETE)
	public void delAuth(@RequestBody Map<String,Object> pMap, HttpServletResponse response) {
		
		Integer idx = Integer.parseInt(pMap.get("idx").toString());
		String authName = pMap.get("authName").toString().trim();
		authName = "ROLE_"+authName;
		
		if(!userDao.deleteAuth(idx, authName)) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	
	

	
}
