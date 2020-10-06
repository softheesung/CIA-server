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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hs.app.board.dao.BoardDao;
import com.hs.app.jwt.service.JwtService;
import com.hs.app.note.dao.NoteDao;
import com.hs.app.note.service.NoteService;
import com.hs.app.note.vo.NoteInfo;
import com.hs.app.pay.dao.CashDao;
import com.hs.app.pay.dao.PayDao;
import com.hs.app.pay.service.CashService;
import com.hs.app.pay.vo.ExInfo;
import com.hs.app.user.dao.UserDao;
import com.hs.app.user.service.UserService;
import com.hs.app.user.vo.UserAlba;
import com.hs.app.user.vo.UserInfo;
import com.hs.app.user.vo.UserNotice;
import com.hs.common.util.CookieUtil;

@RestController
@RequestMapping(value = "/api/i")
public class IRestController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private UserService userService;	
	@Autowired private UserDao userDao;
	@Autowired private BoardDao boardDao;
	@Autowired private JwtService jwtService;
	@Autowired private BCryptPasswordEncoder passwordEncoder;
	@Autowired private NoteService noteService;	 
	@Autowired private NoteDao noteDao;	
	@Autowired private CashService cashService;	
	@Autowired private CashDao cashDao;	
	@Autowired private PayDao payDao;
	
	
	
	@RequestMapping(value = "info-profile", method = RequestMethod.POST)
	public void updateInfoProfile(@RequestBody UserInfo userInfo,
			HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, UnsupportedEncodingException {
		
		String token = request.getAttribute("HSID").toString();
		int idx = Integer.parseInt(jwtService.getMemberId(token));
		
		userInfo.setIdx(idx);
		
		if(userDao.updateUserImgIntro(userInfo)) {
			// 자동로그인
			userService.autoSignIn(idx, response);

			//return userInfo;  
			return;
		}
		System.out.println("Info-Profile 실패.. ");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}
	
	@RequestMapping(value = "info", method = RequestMethod.POST)
	public UserInfo updateInfo(@RequestBody UserInfo userInfo,
			HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getAttribute("HSID").toString();
		String idx = jwtService.getMemberId(token);
		
		userInfo.setIdx(Integer.parseInt(idx));
		
		if(userDao.updateUserInfo(userInfo)) {
			return userInfo;
		}
		return null;
	}
	
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
