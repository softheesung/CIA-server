package com.hs.app.pay.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.hs.app.pay.vo.CashInfo;

public class CashDao extends SqlSessionDaoSupport {
	
	/** 전체 회원 포인트 목록 크기 쿼리 */
    public int getAllCashSize(Integer userIdx) {
    	Map<String,Object> pMap = new HashMap<String,Object>();
    	pMap.put("userIdx", userIdx);
        return getSqlSession().selectOne("pay.getAllCashSize", pMap);
    }
    
    /** 전체 회원 포인트 내역 목록 쿼리 */
    public List<CashInfo> getAllCashList(int startRow, int rowBlockCount, Integer userIdx) {
        Map<String,Object> pMap = new HashMap<String,Object>();
        pMap.put("startRow", startRow);
        pMap.put("rowBlockCount", rowBlockCount);
        pMap.put("userIdx", userIdx);
        return getSqlSession().selectList("pay.getAllCashList", pMap);
    }
    
    /** 포인트 정보 등록 (포인트 증가 또는 감소) */
    public Integer insertCashInfo(CashInfo cashInfo) {
    	return getSqlSession().insert("pay.insertCashInfo", cashInfo);
    }

}
