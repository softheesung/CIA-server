<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
       
<c:set var="APP_SITE_DOMAIN"><spring:eval expression="@siteinfo['app.domain']"></spring:eval></c:set>  
<c:set var="APP_SITE_NAME"><spring:eval expression="@siteinfo['app.sitename']"></spring:eval></c:set>  
<c:set var="APP_SITE_URL"><spring:eval expression="@siteinfo['app.siteurl']"></spring:eval></c:set>  
<c:set var="CURRENT_REQ_URL">${APP_SITE_URL}${requestScope['javax.servlet.forward.request_uri']}<c:if test='${requestScope["javax.servlet.forward.query_string"]!=null}'>?${requestScope['javax.servlet.forward.query_string']}</c:if></c:set>
<c:set var="IMGUR_CLIENT_ID"><spring:eval expression="@siteinfo['app.upload.imgur.id']"></spring:eval></c:set>
<c:set var="GOOGLE_ANALY_ID"><spring:eval expression="@siteinfo['app.google.analytics.id']"></spring:eval></c:set>
<c:set var="RESOURCES_VER"><spring:eval expression="@siteinfo['app.resource.ver']"></spring:eval></c:set>

<!DOCTYPE html>
<html>
<head>
    
	<meta charset="UTF-8"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	
	<!-- 검색봇 차단 -->
	<meta name="robots" content="noindex">
	<meta name="googlebot" content="noindex">
	
	<title>Hiddengolfer Management</title> 
	
	<link rel="shortcut icon" href="/resources/favicon.ico" type="image/x-icon"/> 
	
	<!-- font destnation -->
	<link type="text/css" rel="stylesheet" href="/common_${RESOURCES_VER}/font/NanumSquareRound/style.css"/>  
	<link type="text/css" rel="stylesheet" href="/common_${RESOURCES_VER}/font/NanumBarunGothic/style.css"/>  
	<link type="text/css" href="//fonts.googleapis.com/earlyaccess/nanumgothic.css" rel="stylesheet" />
	
	<!-- normalize -->
	<link type="text/css" rel="stylesheet" href="/theme_admin/normalize.css"/>
	
	
	<script type="text/javascript" src="/common_${RESOURCES_VER}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="/common_${RESOURCES_VER}/js/jquery.form.js"></script>
	<script type="text/javascript" src="/common_${RESOURCES_VER}/editor/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="/common_${RESOURCES_VER}/editor/editor.js"></script> 
	<script type="text/javascript" src="/common_${RESOURCES_VER}/js/md5.js"></script> 
	
	<!-- fontawesome -->
	<link rel="stylesheet" href="/common_${RESOURCES_VER}/fontawesome/css/all.min.css"/>
    <script src="/common_${RESOURCES_VER}/fontawesome/js/fontawesome.min.js"></script> 
    
	<script type="text/javascript" src="/common_${RESOURCES_VER}/js/Chart.js"></script>
	
	<!-- global -->
	<link type="text/css" rel="stylesheet" href="/theme_admin/global.css"/>
	<script type="text/javascript" src="/theme_admin/global.js"></script>
	
</head>  
<body>
	<div id="global_navi"></div>
		
	<section id="wrapper">
		
		<header class="header clearfix">
					
			<a href="/coldbrew/"><strong class="logo" style="font-size: 30px; font-weight: 800; line-height: 60px;">HiddenGolfer</strong></a>
			
			<div class="pc-open">   
				
				<span class="bar">&nbsp;</span>
				<a href="/" class="b">home</a>
				<span class="bar">&nbsp;</span>
				<a href="/signout" class="b">logout</a>
				
			</div>
			<div class="m-open-block">
				<a class="ico" id="navi1"><i class="fa fa-bars"></i></a>
				<a class="ico none_hover" id="navi2"><i class="fa fa-ellipsis-h"></i></a>
			</div>
			
		</header>
		
		<!-- #####################################################################[컨테이너 시작] -->
		<section class="container clearfix">	
			
            <div class="sub_navi_wrapper">
				<div class="box" style="height: 100%;">
					<h2>Dashboard</h2>
					<ul class="sub_navi">
						
						<li class="c_g">APP</li>
<!-- 						<li><a href="">Dashboard</a></li> -->
						<li><a href="/coldbrew/pg/app">App properties</a></li>
						
						<li class="c_g">USERS</li> 
						<li><a href="/coldbrew/pg/user/list">회원 관리</a></li>
						<li><a href="/coldbrew/pg/user/alba/list">알바 관리</a></li>
						
						
						<li class="c_g">NOTES</li> 
						<li><a href="/coldbrew/pg/notes/list">쪽지 기록</a></li>
						
						
						<li class="c_g">PAY&CASH</li> 
						<li><a href="/coldbrew/pg/exchange/list">현금인출 요청관리</a></li>
						<li><a href="/coldbrew/pg/pay/list">결제 관리</a></li>
						<li><a href="/coldbrew/pg/cash/list">캐시 내역</a></li>
						
						<li class="c_g">BOARDS</li>
						<li><a href="/coldbrew/pg/board/list">게시판 관리</a></li>
						<li><a href="/coldbrew/pg/board/menu">메뉴 관리</a></li>
<!-- 						<li><a href="">고갠센터 관리</a></li> -->
						
						
					
					</ul>
				</div>
			</div>  
			
            <tiles:insertAttribute name="wrap_content" />
            
			
			<footer class="footer">
				Copyright ⓒ 서비스이름 2020.All rights reserved.
			</footer>	
		</section>
		<!-- #####################################################################[컨테이너 끝] -->
	</section>
    
</body>
</html>  