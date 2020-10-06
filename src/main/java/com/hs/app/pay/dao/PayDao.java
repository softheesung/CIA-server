package com.hs.app.pay.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.hs.app.pay.vo.ExInfo;
import com.hs.app.pay.vo.PayInfo;

public class PayDao extends SqlSessionDaoSupport {
	
	public ExInfo getExInfoByIdx(int idx) {
		return getSqlSession().selectOne("pay.getExInfoByIdx", idx);
	}
	
	public boolean deleteExchangeByAdmin(int idx) {
		Integer r = getSqlSession().delete("pay.deleteExchangeByAdmin", idx);
		if(r!=null&&r>0) {
			return true;
		}
		return false;
	}
	
	public boolean updateExchangeFlag(int idx, boolean resultFlag) {
		Map<String,Object> pMap = new HashMap<String,Object>();
        pMap.put("idx", idx);
        pMap.put("resultFlag", resultFlag);
		Integer r = getSqlSession().update("pay.updateExchangeFlag", pMap);
		if(r!=null&&r>0) {
			return true;
		}
		return false;
	}
	
	public List<ExInfo> getExchangeList(int startRow, int rowBlockCount) {
		 Map<String,Object> pMap = new HashMap<String,Object>();
	        pMap.put("startRow", startRow);
	        pMap.put("rowBlockCount", rowBlockCount);
		return getSqlSession().selectList("pay.getExchangeList", pMap);
	}
	public int getExchangeSize() {
		return getSqlSession().selectOne("pay.getExchangeSize");
	}
	
	
	
	public boolean deleteExchange(int userIdx) {
		Integer r = getSqlSession().delete("pay.deleteExchange", userIdx);
		if(r!=null && r>0) {
			return true;
		}
		return false;
	}
	
	public boolean insertExchange(int userIdx, int amount) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("userIdx", userIdx);
		pMap.put("amount", amount);
		Integer r = getSqlSession().insert("pay.insertExchange", pMap);
		if(r!=null && r>0) {
			return true;
		}
		return false;
	}
	
	public ExInfo getMyExReq(int userIdx) {
		return getSqlSession().selectOne("pay.getMyExReq", userIdx);
	}
	
	public List<PayInfo> getPayList(int startRow, int rowBlockCount) {
		 Map<String,Object> pMap = new HashMap<String,Object>();
	        pMap.put("startRow", startRow);
	        pMap.put("rowBlockCount", rowBlockCount);
		return getSqlSession().selectList("pay.getPayList", pMap);
	}
	public int getPaySize() {
		return getSqlSession().selectOne("pay.getPaySize");
	}
	
	public Map<String,Object> getPay(String oid, int amount) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("oid", oid);
		pMap.put("amount", amount);
		return getSqlSession().selectOne("pay.getPay", pMap);
	}
	
	public boolean approvalPay(String oid, int amount) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("oid", oid);
		pMap.put("amount", amount);
		Integer r = getSqlSession().update("pay.approvalPay", pMap);
		if(r!=null&&r>0) {
			return true;
		}
		return false;
	}
	
	public boolean insertPay(String goodName, String oid, int amount, int userIdx, int ball) {
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("goodName", goodName);
		pMap.put("oid", oid);
		pMap.put("amount", amount);
		pMap.put("userIdx", userIdx);
		pMap.put("ball", ball);
		Integer r = getSqlSession().insert("pay.insertPay", pMap);
		if(r!=null&&r>0) {
			return true;
		}
		return false;
	}
	
}
