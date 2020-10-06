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

        <div class="headline">쪽지 관리</div>

        <div class="paddingbox">

            <div>

                <form id="searchfrm" action="" method="get" onsubmit="return false;">
                    <div class="normal_search_group clearfix" style="margin-top: 20px;">
                       
                        <input class="txtbox h43" type="text" name="query" placeholder="메세지 내용으로 검색" value="" style="width: 300px; margin-right: 10px;"/>
                        <a class="btn" onclick="searchfrmKeyChange()" style="cursor: pointer;"><i class="fa fa-search" style="font-size: 15px;"></i> 검색</a>
                    </div>	
                </form>
                <script>
                
                </script>


                <div class="clearfix" style="padding: 20px 0px; text-align: right;">

                    <span class="b_txt" style="float: left;">전체 : <strong class="data_total"></strong>건</span>

                    <a href="./list" class="btn white" style="margin-right: 5px; width: 122px;"><i class="fa fa-bars"></i>&nbsp;&nbsp;전체목록</a>
<!--                     <a href="/coldbrew/pg/board/input" class="btn blue2" style="margin-right: 5px; width: 132px;">게시물 등록</a> -->
                    <a class="btn" style=" width: 122px;cursor: pointer; " onclick="deleteByCheckbox()">선택삭제</a>

                </div>



                <table class="table_style">
                    <thead>
                        <tr>
                            <th style="width: 40px;"><input class="dims_checkbox all" type="checkbox"/></th>
                            <th class="pc-open" style="width: 50px;">번호</th>
                            <th style="width: 100px;">발송일</th>
                            <th style="width: 120px;">보낸이</th>
                            <th style="width: 170px;">받는이(읽음여부)</th>
                            <th>내용</th>                         
                        </tr>
                    </thead>
                    <tbody id="list_target_1">

                    </tbody>
                </table>
                <div class="pg_wrapper">
                    <div class="pg clearfix">

                        <a class="numb" href="?page=1"><i class="fa fa-chevron-left"></i></a>
                        <a class="numb sel">1</a> 
                        <a class="numb ">2</a> 
                        <a class="numb ">3</a>      
                        <a class="numb ">4</a> 
                        <a class="numb ">5</a> 
                        <a class="numb" href="?page=2"><i class="fa fa-chevron-right"></i></a>

                    </div>
                </div>



            </div>



        </div>



    </div>
</div>	



<script>

var g_query = null;

function searchfrmKeyChange(i) {
	
	// query
	var query = $("#searchfrm input[name=query]").val();
	if(query.trim() == '') {g_query = null;}
	else { g_query = query; }

	loadNoteList(1);
}
function searchfrmFilterChange(i) {

	// query
	var query = $("#searchfrm input[name=query]").val();
	if(query.trim() == '') {g_query = null;}
	else { g_query = query; }
	
	loadNoteList(1);
}

    
// get UserList    
function loadNoteList(page) {
	
    $.ajax({ 
        type : "GET",
        dataType : "json",
        data : {
            "page" : page,
            "q" : g_query
        },
        url : "/api/notes/",	           
        success : function(data){           
            var c = "";
            console.log(data.list);
            $(data.list).each(function(i,d){
                
            	c += '<tr>';
                c += '<td><input type="checkbox" name="seq" class="dims_checkbox item_dims_checkbox" value="'+d.idx+'"/></td>';
                c += '<td>'+(i+1)+'</td>';
                c += '<td>'+d.sendtime+'</td>';
                c += '<td>'+d.fromUserName+'</td>';
                c += '<td>'+d.toUserName+'('+(d.readFlag==true?d.readtime:'읽지않음')+')</td>';
                c += '<td>'+d.msg+'</td>';
            
            });
            $("#list_target_1").html(c);
            
            c = "";
            console.log(data.pageNav);
            var pn = data.pageNav;
            $(".data_total").text(pn.totalRowCount);
            if(pn.startPageNum > pn.pageBlockCount) {
            	c += '<a class="numb" onclick="loadNoteList('+(1)+')"><i class="fa fa-chevron-left"></i></a>';
            	c += '<a class="numb" onclick="loadNoteList('+(pn.startPageNum-1)+')"><i class="fa fa-chevron-left"></i></a>';
            }
            for(var i=pn.startPageNum;i<=pn.endPageNum;i++) {
            	c += '<a class="numb '+(i==pn.pageNum?"sel":"")+'" onclick="loadNoteList('+i+')">'+i+'</a>';
            }
            if(pn.endPageNum < pn.totalPageCount) {
            	c += '<a class="numb" onclick="loadNoteList('+(pn.endPageNum+1)+')"><i class="fa fa-chevron-left"></i></a>';
            	c += '<a class="numb" onclick="loadNoteList('+(pn.totalPageCount)+')"><i class="fa fa-chevron-left"></i></a>';
            }
            $(".pg_wrapper > .pg").html(c);
          	
        }, 
        error : function(e1,e2,e3){
            console.log(e1);console.log(e2);console.log(e3);
        } 
    });	
}    
loadNoteList(1);    
 
$(".dims_checkbox.all").on("click",function(){
    var state = $(this).prop('checked');	
    $(".dims_checkbox").prop('checked', state);
});

function deleteByCheckbox() {
    if($(".item_dims_checkbox:checked").length==0){
        alert("삭제할 상품을 선택해주세요.");
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
            url : "/api/notes/",           
            error : function(e1,e2,e3){
                console.log(e1);console.log(e2);console.log(e3);
            },
            success : function(){
            	$(".dims_checkbox.all").prop('checked', false);
            	loadNoteList(1, null);
            }  
        });	
    }
}
</script>






