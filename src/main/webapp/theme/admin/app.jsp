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

        <div class="headline">Category Stat</div>

        <div class="paddingbox">
            <div>


               
                



            </div>
        </div>

    </div>
</div>	



<script>

</script>






