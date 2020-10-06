<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="RESOURCES_VER"><spring:eval expression="@siteinfo['app.resource.ver']"></spring:eval></c:set>
<c:set var="APP_SITE_URL"><spring:eval expression="@siteinfo['app.siteurl']"></spring:eval></c:set>
<c:set var="APP_SITENAME"><spring:eval expression="@siteinfo['app.sitename']"></spring:eval></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta name="description" content="" />
	<meta name="keywords" content="" /> 
	
	<title>[404] ${APP_SITENAME} :: 페이지를 찾을 수 없습니다.</title>
	
	<!-- 기본 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
	<meta http-equiv="Expires" content="0"/> 
	<meta http-equiv="Cache-Control" content="no-cache"/> 
	<meta http-equiv="Pragma" content="no-cache"/>
  
	<link href="/favicon.ico" rel="shortcut icon" />
	
	<!-- 기본 리소스 -->	
	<link type="text/css" href="//fonts.googleapis.com/earlyaccess/nanumgothic.css" rel="stylesheet" />
	<link type="text/css" rel="stylesheet" href="/static-${RESOURCES_VER}/common/default.css" />
	<script type="text/javascript" src="/common_${RESOURCES_VER}/js/default.js"></script>
	
	<link type="text/css" rel="stylesheet" href="/theme/basic/resources/skin.css" />
	<link type="text/css" rel="stylesheet" href="/theme/error/error.css" />

</head>    
<body style="background-color:#fafafa;">
<!-- body -->  
	<div id="body">
		<div id="content" style="margin-top:0px;">	 
		<!-- content -->
			
			<!-- 메인 컨텐츠 -->
			<div class="error_page_box">				
				<div>
			  		<!-- 1단 -->   
			  		<div class="error_row1">    			  			             
			  			<span style=""><a href="/">${APP_SITENAME}홈</a></span>			  			 
				  		<span style="color:#9DA4AB;margin:0px 3px;">|</span>   				  		     
				  		<span><a href="/faq">FAQ</a></span>         		  			     
			  		</div>        
			  		<!-- 2단 -->
			  		<div class="error_row2"> 			  			
			  			<h2 class="csc_font">
			  				죄송합니다.<br/>
			  				요청하신 페이지를 찾을 수 없습니다.    
			  			</h2>           
			  			<p>    
			  				방문하시려는 페이지의 주소가 잘못 입력되었거나,<br/>
							페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.<br/>
							<br/>
							입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.<br/>
							<br/>
							관련 문의사항은 <a class="csc_font" href="/help">고객센터</a>에 알려주시면 친절하게 안내해 드리겠습니다.<br/>
							<br/> 
							감사합니다.<br/>        
			  			</p>       			  			            
			  		</div>       
			  		<!-- 3단 -->     
			  		<div class="error_row3">		  			             
			  			<div class="logo_left" style="text-align:center;">      
		  	 				<img style="width: 50%;" src="/theme/error/logo.png" />	  
		  	 			</div>                
		  	 			<div class="cont_right">
		  	 				<p>      	
		  	 					(주)가자 | 대표:김경수<br/>
								대전시 유성구 문화원로 77, 204호<br/>
								TEL: 010-3760-0710<br/>
								E-mail: help@hiddengolfer.com<br/>
								사업자등록번호: 219-86-01835<br/>
								통신판매번호: 제 2020-대전유성-0599 호
		  	 				</p>   
		  	 			</div>		  		
			  		</div>
			  		<!-- 4단 -->      
			  		<div class="error_row4">			  			  
			  			Copyright ⓒHiddenGolfer 2020, All rights reserved         			  			
			  		</div>	     
				</div>          
			</div>
					
		
		<!-- content -->
		</div>
	</div>
<!-- body -->
</body>
</html>