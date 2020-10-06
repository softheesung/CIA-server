package com.hs.app.pay.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.app.pay.dao.CashDao;
import com.hs.app.pay.vo.CashInfo;
import com.hs.common.util.PageUtil;

public class CashService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private CashDao cashDao;
	
	
	/** 전체캐시 기록 목록 로드 */
    public Map<String,Object> loadAllCashList(int page, int rowBlockCount, Integer userIdx) {
        
        page = page<1?1:page;   
      
        PageUtil pu = new PageUtil(page,cashDao.getAllCashSize(userIdx),rowBlockCount,10);			
		List<CashInfo> lists = cashDao.getAllCashList(pu.getStartRow(), pu.getRowBlockCount(), userIdx);	
		
        Map<String,Object> rst = new HashMap<String,Object>();
		rst.put("pageNav", pu);
		rst.put("list", lists);
        return rst;
    }

}
