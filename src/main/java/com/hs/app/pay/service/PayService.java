package com.hs.app.pay.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.app.pay.dao.PayDao;
import com.hs.app.pay.vo.ExInfo;
import com.hs.app.pay.vo.PayInfo;
import com.hs.common.util.PageUtil;

public class PayService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private PayDao payDao;
	
	/** 목록 로드 */
    public Map<String,Object> loadExchangeList(int page, int rowBlockCount) {
        
        page = page<1?1:page;   
        
        PageUtil pu = new PageUtil(page,payDao.getExchangeSize(),rowBlockCount,10);			
		List<ExInfo> lists = payDao.getExchangeList(pu.getStartRow(), pu.getRowBlockCount());		
		
        Map<String,Object> rst = new HashMap<String,Object>();
		rst.put("pageNav", pu);
		rst.put("list", lists);
        return rst;
    }
	
	/** 목록 로드 */
    public Map<String,Object> loadPaidList(int page, int rowBlockCount) {
        
        page = page<1?1:page;   
//        if(q!=null&&q!="") {q = q.trim(); logger.debug("검색어: "+q+"로 게시물 검색..");}
//        else {q=null;}
        
        PageUtil pu = new PageUtil(page,payDao.getPaySize(),rowBlockCount,10);			
		List<PayInfo> lists = payDao.getPayList(pu.getStartRow(), pu.getRowBlockCount());		
		
        Map<String,Object> rst = new HashMap<String,Object>();
		rst.put("pageNav", pu);
		rst.put("list", lists);
        return rst;
    }

}
