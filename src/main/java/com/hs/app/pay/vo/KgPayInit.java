package com.hs.app.pay.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.hs.common.util.GeneralUtil;
import com.inicis.std.util.SignatureUtil;


public class KgPayInit {
	
	private String version = "1.0";			// 버전
	private String mid = "INIpayTest";		// 상점아이디
	private String oid;						// 주문번호
	private String goodname;				// 상품명
	private int price;						// 결제가격	
	private String currency = "WON";		// 통화
	private String buyername;				// 구매자명
	private String buyertel;				// 구매자 핸드폰번호
	private String buyeremail;				// 구매자 이메일
	private String timestamp;				// 타임스탬프
	private String signature;				// 위변조방지 SHA256 Hash값
	private String returnUrl;				// 리턴Url(인증결과수신Url)
	private String mKey;					// signkey에 대하 검증값
	private String closeUrl;				// 결제창 닫기처리Url
	private String gopaymethod;				// 결제방법
	private String acceptmethod;
	
	
	private int userseq;
	
	private String mNextUrl;	// 모바일 결제승인URL (동기방식)
	private String mNotiUrl;	// 모바일 결제통지URL (비동기방식, 가상계좌 입금통보시 사용)
	private String mReturnUrl;	// 모바일 리턴URL	
	
	
	
	
	public KgPayInit() {}
	
	public KgPayInit(String thisUrl, String mid, String signkey, String goodname, int price, String buyername, 
			String buyertel, String buyeremail, String gopaymethod, int userseq) throws Exception {
		
		   
		this.mid = mid;
		this.oid = GeneralUtil.getOrderNo();
	
		this.goodname = goodname;
		this.price = price;
		this.buyername = buyername;
		this.buyertel = buyertel;
		this.buyeremail = buyeremail;
		this.gopaymethod = gopaymethod;
		
		this.userseq = userseq;
	
		
		this.returnUrl = thisUrl + "api/pay/notify";//"jsp/INIStdPayReturn.jsp";
		this.closeUrl = thisUrl + "api/pay/close";
		
		this.mNextUrl = thisUrl + "api/pay/process";//"mpayment/process";
		this.mNotiUrl = thisUrl + "api/pay/notify";//"mpayment/notify";
		this.mReturnUrl = thisUrl + "mpayment/return";
		
		this.timestamp = SignatureUtil.getTimestamp();
		this.mKey = SignatureUtil.hash(signkey, "SHA-256");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		this.acceptmethod = "vbank("+sdf.format(cal.getTime())+")";
		
		//###############################################
		// 2.signature 생성
		//###############################################
		Map<String, String> signParam = new HashMap<String, String>();
		signParam.put("oid", this.oid); 				// 필수
		signParam.put("price", this.price+"");			// 필수
		signParam.put("timestamp", this.timestamp);		// 필수
		// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
		this.signature = SignatureUtil.makeSignature(signParam);
		
		
		
	}




	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getGoodname() {
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBuyername() {
		return buyername;
	}
	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}
	public String getBuyertel() {
		return buyertel;
	}
	public void setBuyertel(String buyertel) {
		this.buyertel = buyertel;
	}
	public String getBuyeremail() {
		return buyeremail;
	}
	public void setBuyeremail(String buyeremail) {
		this.buyeremail = buyeremail;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getmKey() {
		return mKey;
	}
	public void setmKey(String mKey) {
		this.mKey = mKey;
	}
	public String getCloseUrl() {
		return closeUrl;
	}
	public void setCloseUrl(String closeUrl) {
		this.closeUrl = closeUrl;
	}
	
	public String getGopaymethod() {
		return gopaymethod;
	}
	public void setGopaymethod(String gopaymethod) {
		this.gopaymethod = gopaymethod;
	}


	public int getUserseq() {
		return userseq;
	}

	public void setUserseq(int userseq) {
		this.userseq = userseq;
	}

	
	

	public String getAcceptmethod() {
		return acceptmethod;
	}

	public void setAcceptmethod(String acceptmethod) {
		this.acceptmethod = acceptmethod;
	}

	public String getmNextUrl() {
		return mNextUrl;
	}

	public void setmNextUrl(String mNextUrl) {
		this.mNextUrl = mNextUrl;
	}

	public String getmNotiUrl() {
		return mNotiUrl;
	}

	public void setmNotiUrl(String mNotiUrl) {
		this.mNotiUrl = mNotiUrl;
	}

	public String getmReturnUrl() {
		return mReturnUrl;
	}

	public void setmReturnUrl(String mReturnUrl) {
		this.mReturnUrl = mReturnUrl;
	}
	
}
