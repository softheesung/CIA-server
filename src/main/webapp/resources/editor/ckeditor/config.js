/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */




//==============================================================================
// 기본 정의 
//==============================================================================
CKEDITOR.editorConfig = function( config ) {
	
	// 이 기능을 추가시 이미지 팝업에 업로드 기능이 생긴다.
	//config.filebrowserUploadUrl = '/file/upload/';					// 이미지 파일 업로드 패스
	
	config.image_previewText = CKEDITOR.tools.repeat( '    ', 100 );	// 업로드된이미지 미리보기&설정창 글씨 제거  
	config.language = 'ko';												// 언어
	config.uiColor = '#E1E1E1'; 										// 스킨 배경 색상
	config.enterMode = 2; 												// 엔터시 설정(1:<p> 2:<br/> 3:<div>)
	config.shiftEnterMode = 3;											// 쉬프트+엔터시 설정(1:<p> 2:<br/> 3:<div>)
	config.enableTabKeyTools = true; 									// 탭키 사용여부
	//config.tabIndex = 1;
	config.tabSpaces = 4; 
	
	config.font_names = '굴림;바탕;돋움;궁서;Arial;Times New Roman;Verdana';
	config.fontSize_sizes = '8pt/8pt;9pt/8pt;10pt/10pt;11pt/11pt;12pt/12pt;14pt/14pt;18pt/18pt;24pt/24pt;36pt/36pt;';
	
	// 툴바 배치 
	config.toolbar = [      
      ['Undo','Redo'],'-',
      ['Link','Unlink'],'-',
      ['Imgur','Table','HorizontalRule','Smiley','Iframe'],'-',
      ['Maximize'],'-',
      ['Source','Preview'],
	  '/', 
	  ['Bold','Italic','Underline','Strike','RemoveFormat'],'-',
	  ['NumberedList','BulletedList','Blockquote',"JustifyLeft","JustifyCenter","JustifyRight"],
	  ["Font"],["FontSize"],"-",
	  ["TextColor"]
	]; 
	     
	// 툴바 삭제할 목록 
	config.removeButtons = 'JustifyBlock,Outdent,Indent,Copy,Cut,PasteText,PasteFromWord,Paste,HiddenField,Form,Language,BidiRtl,BidiLtr,Styles,PageBreak,Select,ShowBlocks,Radio,Checkbox,Flash,SelectAll,Replace,Find,ImageButton,Button,Subscript,Superscript,Scayt,Anchor,SpecialChar,Format,About,CreateDiv,BGColor,NewPage,Print,Templates,Save,Textarea,TextField';
	  
	// 드래그 앤 드롭 파일 업로드 방식 플러그인 추가
	// 버튼식 파일 업로드(IMGUR) 방식 플러그인 추가 
	//config.extraPlugins = 'dragdrop';//,imgur';									
	
	// 버튼식 파일 업로드(IMGUR)
	//imgurClientId = IMGUR_CLIENT_ID;//'8a66387631f67ed'
	
	// 드래그앤드롭 파일 업로드(IMGUR) 
//	config.droplerConfig = { 
//	    backend: 'imgur',
//	    settings: {
//	    	clientId: IMGUR_CLIENT_ID//8a66387631f67ed
//	    }        
//	};    
	
	// 로컬 서버 (사용불가능)
	config.droplerConfig = {
		backend: 'basic',
		settings: {
			uploadUrl: '/file/imgUpload/'
		}	
	}
	
	//config.contentsCss = ['/resources/common/default.css'],['contents.css'];	//홈페이지에서 사용하는 Css 파일 인클루드
	
	// AWS S3(아마존 클라우드)
//	config.droplerConfig = {
//		backend: 's3',
//		settings: {
//			bucket: 'bucketname',
//			region: 'your-region',
//			accessKeyId: 'key',
//			secretAccessKey: 'secret-key'
//		}
//	};
	  
	
	
};      

//==============================================================================
// 다이얼로그 정의
//==============================================================================
CKEDITOR.on('dialogDefinition', function( ev ){
   
	var dialogName = ev.data.name;
    var dialogDefinition = ev.data.definition;
    switch (dialogName) {
        case 'image':													// 이미지 속성 다이얼로그         
        	
        	//dialogDefinition.removeContents('info');
            dialogDefinition.removeContents('Link');					// Link 탭 제거
            dialogDefinition.removeContents('advanced');				// advanced 탭 제거
            
            var infoTab = dialogDefinition.getContents( 'info' ); 
        	infoTab.remove( 'txtBorder' ); 								// Border 제거
            infoTab.remove( 'txtHSpace' ); 								// HSpace 제거
            infoTab.remove( 'txtVSpace' );								// VSpace 제거
            //infoTab.remove( 'txtWidth' ); 							// width 제거
            //infoTab.remove( 'txtHeight' ); 							// height 제거
            
            break;
    }
    
});   
   



/*
  
툴바 아이템 리스트

Complete List of Toolbar Items for CKEditor

items
"Source","Save","NewPage","DocProps","Preview","Print","Templates","document"

items
"Cut","Copy","Paste","PasteText","PasteFromWord","Undo","Redo"

items
"Find","Replace","SelectAll","Scayt"

items
"Form","Checkbox","Radio","TextField","Textarea","Select","Button","ImageButton","HiddenField"

items
"Bold","Italic","Underline","Strike","Subscript","Superscript","RemoveFormat"

items
"NumberedList","BulletedList","Outdent","Indent","Blockquote","CreateDiv"
"JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock","BidiLtr","BidiRtl","Language"

items
"Link","Unlink","Anchor"

items
"CreatePlaceholder","Image","Flash","Table","HorizontalRule","Smiley","SpecialChar","PageBreak"
"Iframe","InsertPre"

items
"Styles","Format","Font","FontSize"

items
"TextColor","BGColor"

items
"UIColor","Maximize","ShowBlocks"

items
"button1","button2","button3","oembed","MediaEmbed"

items
"About"

*/