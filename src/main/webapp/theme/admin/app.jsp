<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
       
<c:set var="APP_SITE_DOMAIN"><spring:eval expression="@siteinfo['app.domain']"/></c:set>  
<c:set var="APP_SITE_NAME"><spring:eval expression="@siteinfo['app.sitename']"></spring:eval></c:set>  
<c:set var="APP_SITE_URL"><spring:eval expression="@siteinfo['app.siteurl']"></spring:eval></c:set>  
<c:set var="CURRENT_REQ_URL">${APP_SITE_URL}${requestScope['javax.servlet.forward.request_uri']}<c:if test='${requestScope["javax.servlet.forward.query_string"]!=null}'>?${requestScope['javax.servlet.forward.query_string']}</c:if></c:set>
<c:set var="IMGUR_CLIENT_ID"><spring:eval expression="@siteinfo['app.upload.imgur.id']"></spring:eval></c:set>
<c:set var="GOOGLE_ANALY_ID"><spring:eval expression="@siteinfo['app.google.analytics.id']"></spring:eval></c:set>
<c:set var="RESOURCES_VER"><spring:eval expression="@siteinfo['app.resource.ver']"></spring:eval></c:set>
<style>
.ll {text-align:left !important;}
#list_target_1 td {text-align:left !important;}
#list_target_1 td.cc {text-align:center !important; font-weight: bold;}
</style>
<div class="contents_wrapper">
    <div class="layer">

        <div class="headline">App Properties</div>

        <div class="paddingbox">

            <div>


                <table class="table_style">
                    <thead>
                        <tr>         
                        	               
                            <th style="width: 100px;">Groups</th>
                            <th>Name</th>
                            <th>Values</th>
                        </tr>
                    </thead>
                    <tbody id="list_target_1">
						
						<tr><td rowspan="2" class="cc">KG이니시스</td><td class="ll">KG이니시스 ID</td><td><spring:eval expression="@siteinfo['app.pay.kg.mid']"/></td></tr>
						<tr><td class="ll">KG이니시스 SignKey</td><td><spring:eval expression="@siteinfo['app.pay.kg.signkey']"/></td></tr>
						
						<tr><td rowspan="6" class="cc">SMTP</td><td class="ll">메일서버 Host</td><td><spring:eval expression="@siteinfo['mail.host']"/></td></tr>
						<tr><td class="ll">메일서버 Port</td><td><spring:eval expression="@siteinfo['mail.port']"/></td></tr>
						<tr><td class="ll">메일서버 ID</td><td><spring:eval expression="@siteinfo['mail.username']"/></td></tr>
						<tr><td class="ll">메일서버 Password</td><td>비공개</td></tr>
						<tr><td class="ll">메일서버 발신자 메일</td><td><spring:eval expression="@siteinfo['mail.frommail']"/></td></tr>
						<tr><td class="ll">운영진 메일알림 수신자</td><td><spring:eval expression="@siteinfo['app.mail.alarm.receiver']"/></td></tr>
						
						<tr><td rowspan="2" class="cc">Basic</td><td class="ll">App 이름</td><td><spring:eval expression="@siteinfo['app.sitename']"/></td></tr>
						<tr><td class="ll">Resources Version</td><td><spring:eval expression="@siteinfo['app.resource.ver']"/></td></tr>
						
						<tr><td rowspan="3" class="cc">접속 로그</td><td class="ll">Google analytics ID</td><td><spring:eval expression="@siteinfo['app.google.analytics.id']"/></td></tr>
						<tr><td class="ll">Naver search log ID</td><td><spring:eval expression="@siteinfo['app.naver.log.id']"/></td></tr>
						<tr><td class="ll">Facebook pixel code</td><td><spring:eval expression="@siteinfo['app.facebook.pixelcode']"/></td></tr>
						
						<tr><td rowspan="2" class="cc">카카오 알림톡</td><td class="ll">Bizm ID</td><td><spring:eval expression="@siteinfo['app.bizm.userid']"/></td></tr>
						<tr><td class="ll">Bizm Profile key</td><td><spring:eval expression="@siteinfo['app.bizm.profile']"/></td></tr>
						
						<!-- social login -->
						<tr><td rowspan="14" class="cc">SNS Login</td><td class="ll">Kakao ID</td><td><spring:eval expression="@siteinfo['app.social.kakao.id']"/></td></tr>
						<tr><td class="ll">Kakao Secret</td><td><spring:eval expression="@siteinfo['app.social.kakao.secret']"/></td></tr>
						<tr><td class="ll">Kakao RedirectURI</td><td><spring:eval expression="@siteinfo['app.social.kakao.redirect.uri']"/></td></tr>
						
						<tr><td class="ll">Google ID</td><td><spring:eval expression="@siteinfo['app.social.google.id']"/></td></tr>
						<tr><td class="ll">Google Secret</td><td><spring:eval expression="@siteinfo['app.social.google.secret']"/></td></tr>
						<tr><td class="ll">Google RedirectURI</td><td><spring:eval expression="@siteinfo['app.social.google.redirect.uri']"/></td></tr>
						
						<tr><td class="ll">Naver ID</td><td><spring:eval expression="@siteinfo['app.social.naver.id']"/></td></tr>
						<tr><td class="ll">Naver Secret</td><td><spring:eval expression="@siteinfo['app.social.naver.secret']"/></td></tr>
						<tr><td class="ll">Naver RedirectURI</td><td><spring:eval expression="@siteinfo['app.social.naver.redirect.uri']"/></td></tr>
						 
						<tr><td class="ll">Facebok ID</td><td><spring:eval expression="@siteinfo['app.social.facebook.id']"/></td></tr>
						<tr><td class="ll">Facebook Secret</td><td><spring:eval expression="@siteinfo['app.social.facebook.secret']"/></td></tr>
						<tr><td class="ll">Facebook Scope</td><td><spring:eval expression="@siteinfo['app.social.facebook.scope']"/></td></tr>
						<tr><td class="ll">Facebook RedirectURI</td><td><spring:eval expression="@siteinfo['app.social.facebook.redirect.uri']"/></td></tr>
						<tr><td class="ll">Facebook Sdk version</td><td><spring:eval expression="@siteinfo['app.social.facebook.sdk.version']"/></td></tr>
						
						
						
						
						
						
                    </tbody>
                </table>
                



            </div>



        </div>



    </div>
</div>	



<script>

</script>






