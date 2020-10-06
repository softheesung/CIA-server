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

<div class="contents_wrapper">
    <div class="layer">

        <div class="headline">동반자(선수) 관리</div>

        <div class="paddingbox">

            <div>

                <form id="searchfrm" action="" method="get" onsubmit="return false;">
                    <div class="normal_search_group clearfix" style="margin-top: 20px;">
                        <!-- <select name="menuIdx" data-name="menuIdx" class="selbox h43" style="width: 180px;" onchange="searchfrmFilterChange(this)">
                            <option value="">전체 메뉴</option>
                        </select> -->
                        <!-- <select name="order" class="selbox h43" style="width: 180px;" onchange="searchfrmFilterChange(this)">
                            <option value="">날짜 오래된순</option>
                            <option value="start_date_desc">날짜 최신순</option>
                            <option value="corder_desc">랭크순</option>
                        </select> -->
                        <input class="txtbox h43" type="text" name="query" placeholder="ID,이름,연락처,이메일로 검색" value="" style="width: 300px; margin-right: 10px;"/>
                        <a class="btn" onclick="searchfrmKeyChange()" style="cursor: pointer;"><i class="fa fa-search" style="font-size: 15px;"></i> 검색</a>
                    </div>	
                </form>
                <script>
                
                </script>


                <div class="clearfix" style="padding: 20px 0px; text-align: right;">

                    <span class="b_txt" style="float: left;">전체 : <strong class="data_total"></strong>건</span>

                    <a href="/coldbrew/pg/user/list" class="btn white" style="margin-right: 5px; width: 122px;"><i class="fa fa-bars"></i>&nbsp;&nbsp;전체목록</a>
<!--                     <a href="/coldbrew/pg/user/input" class="btn blue2" style="margin-right: 5px; width: 132px;">게시물 등록</a> -->
                    <a class="btn" style=" width: 122px;cursor: pointer; " onclick="deleteByCheckbox()">선택삭제</a>

                </div>



                <table class="table_style">
                    <thead>
                        <tr>
                            <th style="width: 40px;"><input class="dims_checkbox all" type="checkbox"/></th>
                            <th class="pc-open" style="width: 50px;">번호</th>
                            <th>지역</th>
                            <th>타수</th>
                            <th>ID(이름,나이)</th>
                            <th>이메일</th>
                            <th>연락처</th>
                            <th style="width: 100px;">희망 골프공</th>
                            <th style="width: ;">기타</th>
                        </tr>
                    </thead>
                    <tbody id="list_target_1">

                    </tbody>
                </table>
                <div class="pg_wrapper">
                    <div class="pg clearfix">

                        <!-- <a class="numb" href="?page=1"><i class="fa fa-chevron-left"></i></a>
                        <a class="numb sel">1</a> 
                        <a class="numb ">2</a> 
                        <a class="numb ">3</a>      
                        <a class="numb ">4</a> 
                        <a class="numb ">5</a> 
                        <a class="numb" href="?page=2"><i class="fa fa-chevron-right"></i></a> -->

                    </div>
                </div>



            </div>



        </div>



    </div>
</div>	



<script>

// var g_menuIdx = null;
var g_query = null;

function searchfrmKeyChange(i) {
	
	// query
	var query = $("#searchfrm input[name=query]").val();
	if(query.trim() == '') {g_query = null;}
	else { g_query = query; }
	
// 	// menuIdx
// 	var menuIdx = $("#searchfrm select[name=menuIdx]").val();
// 	if(menuIdx=='') {g_menuIdx = null;}
// 	else { g_menuIdx = menuIdx; }

	loadUserList(1);
}
function searchfrmFilterChange(i) {
	
	var val = $(i).val();
	var nm = $(i).attr("name");
	if(val == '') { val =  null; }
	
// 	menuIdx
// 	if(nm == "menuIdx") { g_menuIdx = val; }
	
	// query
	var query = $("#searchfrm input[name=query]").val();
	if(query.trim() == '') {g_query = null;}
	else { g_query = query; }
	
	loadUserList(1);
}


    
function loadUserList(page) {
	
    $.ajax({ 
        type : "GET",
        dataType : "json",
        data : {
            "page" : page,
            "q" : g_query
        },
        url : "/api/users/albas",	           
        success : function(data){           
            var c = "";
            console.log(data.list);
            $(data.list).each(function(i,d){
                c += '<tr>';  
                c += '<td><input type="checkbox" name="seq" class="dims_checkbox item_dims_checkbox" value="'+d.idx+'"/></td>';
                c += '<td>'+(i+1)+'</td>';
                c += '<td>'+d.region+'</td>';
                c += '<td>'+d.tasu+'</td>';
                c += '<td style="text-align:left;">';
                c += '<a href="../view?idx='+d.idx+'">';
                
                c += '<span class="profileCss" style="background-image: url('+d.img1+'), url(/theme/admin/resources/img/profile.png)"></span> ';
                
                c += d.name+'('+d.age+')'+' <i style="color: #959595;" class="far fa-window-restore"></i>';
                c += '</a></td>';
                c += '<td>'+d.email+'</td>';
                c += '<td>'+d.phonenm+'</td>';
                c += '<td>'+getPriceWithComma(d.amount)+'개</td>';
                c += '<td>';
                    //c += ' <a class="btn blue h38" href="./input?idx='+d.idx+'"><i class="fa fa-check"></i> 수정</a> ';
                c += '</td>';
            });
            $("#list_target_1").html(c);
            
            c = "";
            console.log(data.pageNav);
            var pn = data.pageNav;
            $(".data_total").text(pn.totalRowCount);
            if(pn.startPageNum > pn.pageBlockCount) {
            	c += '<a class="numb" onclick="loadUserList('+(1)+')"><i class="fa fa-chevron-left"></i></a>';
            	c += '<a class="numb" onclick="loadUserList('+(pn.startPageNum-1)+')"><i class="fa fa-chevron-left"></i></a>';
            }
            for(var i=pn.startPageNum;i<=pn.endPageNum;i++) {
            	c += '<a class="numb '+(i==pn.pageNum?"sel":"")+'" onclick="loadUserList('+i+')">'+i+'</a>';
            }
            if(pn.endPageNum < pn.totalPageCount) {
            	c += '<a class="numb" onclick="loadUserList('+(pn.endPageNum+1)+')"><i class="fa fa-chevron-left"></i></a>';
            	c += '<a class="numb" onclick="loadUserList('+(pn.totalPageCount)+')"><i class="fa fa-chevron-left"></i></a>';
            }
            $(".pg_wrapper > .pg").html(c);
          	
        }, 
        error : function(e1,e2,e3){
            console.log(e1);console.log(e2);console.log(e3);
        } 
    });	
}    
loadUserList(1);    
 
$(".dims_checkbox.all").on("click",function(){
    var state = $(this).prop('checked');	
    $(".dims_checkbox").prop('checked', state);
});

function deleteByCheckbox() {
    if($(".item_dims_checkbox:checked").length==0){
        alert("삭제할 항목을 선택해주세요.");
        return;
    }

    var count = $(".item_dims_checkbox:checked").length;
    if(window.confirm('선택된 '+count+'개의 항목을 삭제하시겠습니까?','')){
    	var arr = new Array();
        $.each($(".item_dims_checkbox:checked"), function(idx,val){
        	arr.push($(val).val());
        });
        $.ajax({ 
            type : "DELETE",
            contentType : "application/json; charset=utf-8",
            data : JSON.stringify(arr),
            url : "/api/users",           
            error : function(e1,e2,e3){
                console.log(e1);console.log(e2);console.log(e3);
            },
            success : function(){
            	$(".dims_checkbox.all").prop('checked', false);
            	loadUserList(1, null);
            }  
        });	
    }
}
</script>






