package com.hs.app.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hs.app.user.vo.StudyInfo;
import com.hs.app.user.vo.StudyStudent;
import com.hs.app.user.vo.UserAlba;
import com.hs.app.user.vo.UserInfo;
import com.hs.app.user.vo.UserNotice;

public class UserDao extends SqlSessionDaoSupport {
	
	@Autowired private BCryptPasswordEncoder passwordEncoder;
	
	
	
	public StudyInfo getStudy(int idx) {
		return getSqlSession().selectOne("user.getStudy", idx);
	}
	
	public boolean deleteStudy(int idx) {
		Integer r = getSqlSession().delete("user.deleteStudy", idx);
		if(r!=null&&r>0) {
			return true;
		}
		return false;
	}
	
	public boolean updateStudy(StudyInfo studyInfo) {
		Integer r = getSqlSession().update("user.updateStudy", studyInfo);
		if(r!=null&&r>0) {
			return true;
		}
		return false;
	}
	
	public boolean insertStudy(StudyInfo studyInfo) {
		Integer r = getSqlSession().insert("user.insertStudy", studyInfo);
		if(r!=null&&r>0) {
			return true;
		}
		return false;
	}
	
	public int getInsertedStudyIdx(StudyInfo studyInfo) {
		return getSqlSession().selectOne("user.getInsertedStudyIdx", studyInfo);
	}
	
	public int getStudySize() {
		return getSqlSession().selectOne("user.getStudySize");
	}
	
	public List<StudyInfo> loadStudy(int startRow, int rowBlockCount) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("startRow", startRow);
		pMap.put("rowBlockCount", rowBlockCount);
		return getSqlSession().selectList("user.loadStudy", pMap);
	}
	
	public List<StudyStudent> getStudyStudents(int studyIdx) {
		return getSqlSession().selectList("user.getStudyStudents", studyIdx);
	}
	
	public boolean deleteStudent(int studyIdx, int userIdx) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("studyIdx", studyIdx);
		pMap.put("userIdx", userIdx);
		Integer r =  getSqlSession().delete("user.deleteStudent", pMap);
		if(r!=null&&r>0) {
			return true;
		}
		return false;
	}
	
	public boolean insertStudent(int studyIdx, int userIdx) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("studyIdx", studyIdx);
		pMap.put("userIdx", userIdx);
		Integer r =  getSqlSession().insert("user.insertStudent", pMap);
		if(r!=null&&r>0) {
			return true;
		}
		return false;
	}
	
	public UserInfo getUser(int idx) {
		return getSqlSession().selectOne("user.getUser", idx);
	}
	
	
	
	
	
	
	

	
	public Integer updateEnableCheckEmail(int idx) {
		return getSqlSession().update("user.updateEnableCheckEmail", idx);
	}
	
	public Integer hasCheckEmail(int userIdx, String signKey) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("userIdx", userIdx);
		pMap.put("signKey", signKey);
		return getSqlSession().selectOne("user.hasCheckEmail", pMap);
	}
	
	public boolean insertCheckEmail(int userIdx, String signKey) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("userIdx", userIdx);
		pMap.put("signKey", signKey);
		Integer r = getSqlSession().insert("user.insertCheckEmail", pMap);
		if(r != null && r > 0) {
			return true;
		}
		return false;
	}


	
	public boolean updateUserImgIntro(UserInfo userInfo) {
		Integer r = getSqlSession().update("user.updateUserImgIntro", userInfo);
		if(r != null && r > 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateUserInfo(UserInfo userInfo) {
		Integer r = getSqlSession().update("user.updateUserInfo", userInfo);
		if(r != null && r > 0) {
			return true;
		}
		return false;
	}
	

	
	/** 이메일로 회원번호 찾기 */
	public UserInfo getUserIdxByEmail(String email) {
		return getSqlSession().selectOne("user.getUserIdxByEmail", email);
	}
	
	/** 회원 등록 */
	public UserInfo insertUserInfo(UserInfo userInfo) {
		Integer r = getSqlSession().insert("user.insertUserInfo", userInfo);
		if(r!=null&&r>0) {
			UserInfo dbInfo = getSqlSession().selectOne("user.getUserIdxByEmail", userInfo.getEmail());
			if(dbInfo!=null) {
				return dbInfo;
			}
		}
		return null;
	}
	
	/** 회원 삭제 */
	public boolean deleteUser(int idx) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("idx", idx);
	
		Integer r = getSqlSession().delete("user.deleteUser", pMap);
		if(r != null && r > 0) {
			return true;
		}
		return false;
	}
	
	/** 패스워드 변경 */
	public boolean updateUserPassword(int idx, String password) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("idx", idx);
		
		// 패스워드 단방향 해시알고리즘 인코딩
		String encodedPassword = passwordEncoder.encode(password);
		pMap.put("password", encodedPassword);
		
		Integer r = getSqlSession().update("user.updateUserPassword", pMap);
		if(r != null && r > 0) {
			return true;
		}
		return false;
	}
	
	/** 회원 탈퇴플래그값 변경 */
	public boolean updateUserEnable(int idx, boolean eFlag) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("idx", idx);
		pMap.put("eFlag", eFlag);
		Integer r = getSqlSession().update("user.updateUserEnable", pMap);
		if(r != null && r > 0) {
			return true;
		}
		return false;
	}
	
	/** 권한 삭제 */
	public boolean deleteAuth(int userIdx, String authName) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("userIdx", userIdx);
		pMap.put("authName", authName);
		Integer r = getSqlSession().insert("user.deleteAuth", pMap);
		if(r != null && r > 0) {
			return true;
		}
		return false;
	}
	
	/** 권한 등록 */
	public boolean insertAuth(int userIdx, String authName) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("userIdx", userIdx);
		pMap.put("authName", authName);
		Integer r = getSqlSession().insert("user.insertAuth", pMap);
		if(r != null && r > 0) {
			return true;
		}
		return false;
	}
	
	
	
    
  
    
}