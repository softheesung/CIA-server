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

        <div class="headline">현금인출 요청 관리</div>

        <div class="paddingbox">

            <div>

                <form id="searchfrm" action="" method="get" onsubmit="return false;">
                    <div class="normal_search_group clearfix" style="margin-top: 20px;">
                        
                        <input class="txtbox h43" type="text" name="query" placeholder="ID,이름,연락처,이메일로 검색" value="" style="width: 300px; margin-right: 10px;"/>
                        <a class="btn" onclick="searchfrmKeyChange()" style="cursor: pointer;"><i class="fa fa-search" style="font-size: 15px;"></i> 검색</a>
                    </div>	
                </form>
                <script>
                
                </script>


                <div class="clearfix" style="padding: 20px 0px; text-align: right;">

                    <span class="b_txt" style="float: left;">전체 : <strong class="data_total"></strong>건</span>

                    <a href="./list" class="btn white" style="margin-right: 5px; width: 122px;"><i class="fa fa-bars"></i>&nbsp;&nbsp;전체목록</a>
<!--                     <a href="/coldbrew/pg/user/input" class="btn blue2" style="margin-right: 5px; width: 132px;">게시물 등록</a> -->
                    <a class="btn" style=" width: 122px;cursor: pointer; " onclick="deleteByCheckbox()">선택삭제</a>

                </div>



                <table class="table_style">
                    <thead>
                        <tr>
                        
                            <th style="width: 40px;"><input class="dims_checkbox all" type="checkbox"/></th>
                            <th class="pc-open" style="width: 50px;">번호</th>
                            <th style="width: 100px;">요청일</th>
                        	<th style="width: 150px;">요청회원(현재잔여)</th>
                            <th>요청 골프공</th>
                            <th>입금받을 계좌</th>
                            <th style="width: 200px;">Update</th>
                            
                            
                        </tr>
                    </thead>
                    <tbody id="list_target_1">

                    </tbody>
                </table>
                <div class="pg_wrapper">
                    <div class="pg clearfix">

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

	loadExchangeList(1);
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
	
	loadExchangeList(1);
}


    
function loadExchangeList(page) {
	
    $.ajax({ 
        type : "GET",
        dataType : "json",
        data : {
            "page" : page
//             ,"q" : g_query
        },
        url : "/api/pay/exchange/items",	           
        success : function(data){           
            var c = "";
            
            $(data.list).each(function(i,d){
                c += '<tr>';
                c += '<td><input type="checkbox" name="seq" class="dims_checkbox item_dims_checkbox" value="'+d.idx+'"/></td>';
                c += '<td>'+(d.idx)+'</td>';
                c += '<td>'+d.regdate+'</td>';
                c += '<td><a style="text-decoration: underline;" href="/coldbrew/pg/user/view?idx='+d.userIdx+'">'+d.userName+'</a> ('+d.cashPoint+'개)</td>';
                
                c += '<td><strong>'+d.amount+'</strong>개 ';
                if(d.resultFlag==null && d.cashPoint < d.amount) {
                	c += '<span style="font-size: 8pt; color: red;">(잔여부족)</span>';
                }
                c += '</td>';
                
                
                //c += '<td>'+(d.resultFlag==null?"요청대기":d.resultFlag==true?"승인완료":"거부처리완료")+'</td>';
                c += '<td>';
                c += d.accountBank+" "+d.accountNumber + " ("+d.accountHolder+")";
                c += '</td>';
                
                
                c += '<td>';
                	if(d.resultFlag==null) {
                		if(d.cashPoint >= d.amount) {
                			c += ' <a class="btn blue h38" style="cursor:pointer" onclick="updateExchangeFlag('+d.idx+', true)"><i class="fa fa-check"></i> 승인</a> ';
                		}else{
                			c += '<div style="font-size: 8pt;margin-bottom: 5px;">잔여 골프공 갯수가 부족해서<br/>승인처리 할 수 없습니다.</div>';
                		}
                		c += ' <a class="btn red h38" style="cursor:pointer" onclick="updateExchangeFlag('+d.idx+', false)"><i class="fa fa-check"></i> 거부</a> ';
                	}else{
                		c += (d.resultFlag==null?"요청대기":d.resultFlag==true?"승인완료":"거부처리완료");
                	}
                	
                c += '</td>';
                
            });
            $("#list_target_1").html(c);
            
            c = "";
           
            var pn = data.pageNav;
            $(".data_total").text(pn.totalRowCount);
            if(pn.startPageNum > pn.pageBlockCount) {
            	c += '<a class="numb" onclick="loadExchangeList('+(1)+')"><i class="fa fa-chevron-left"></i></a>';
            	c += '<a class="numb" onclick="loadExchangeList('+(pn.startPageNum-1)+')"><i class="fa fa-chevron-left"></i></a>';
            }
            for(var i=pn.startPageNum;i<=pn.endPageNum;i++) {
            	c += '<a class="numb '+(i==pn.pageNum?"sel":"")+'" onclick="loadExchangeList('+i+')">'+i+'</a>';
            }
            if(pn.endPageNum < pn.totalPageCount) {
            	c += '<a class="numb" onclick="loadExchangeList('+(pn.endPageNum+1)+')"><i class="fa fa-chevron-left"></i></a>';
            	c += '<a class="numb" onclick="loadExchangeList('+(pn.totalPageCount)+')"><i class="fa fa-chevron-left"></i></a>';
            }
            $(".pg_wrapper > .pg").html(c);
          	
        }, 
        error : function(e1,e2,e3){
            console.log(e1);console.log(e2);console.log(e3);
        } 
    });	
}    
loadExchangeList(1);    

function updateExchangeFlag(idx, flag) {
	$.ajax({ 
        type : "POST",
        dataType : "json",
        data : {
            "idx" : idx
            ,"resultFlag" : flag
        },
        url : "/api/pay/exchange/flag",	           
        success : function(data){           
            console.log(data);
            if(data==true) {
            	location.href = "./list";
            }else{
            	if(data=="deny") {
            		alert("현재 회원 보유 골프공 보다 인출 요청한 골프공이 더 많아 자동 거부 처리 되었습니다.");
            	}
            }
        }, 
        error : function(e1,e2,e3){
            console.log(e1);console.log(e2);console.log(e3);
        } 
    });	
}

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
            	loadExchangeList(1, null);
            }  
        });	
    }
}
</script>






