<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/front.css" rel="stylesheet" type="text/css">

<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->

<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]--> 
<script type="text/javascript" src="script/jquery-3.6.3.js"></script>
<script type="text/javascript">
/* <board>
	 	<tr><subject>제목1</subject><date>2023-01-01</date></tr>
	 	<tr><subject>제목2</subject><date>2023-01-02</date></tr>
	 	<tr><subject>제목3</subject><date>2023-01-03</date></tr>
	 </board> */
/* 	 var arr = [{"subject":"제목1","date":"2023-01-01"},
		 		{"subject":"제목2","date":"2023-01-02"},
		 		{"subject":"제목3","date":"2023-01-03"}]; */
	$(document).ready(function(){
		$('.brown').click(function(){
			alert("클릭");
			
			$('table').html('');
			
			$.ajax({
				url:'jsonArray.jsp',
				dataType:'json',
				success:function(arr){
					$.each(arr,function(index,item){
						$('table').append('<tr><td class="contxt"><a href="#">'+item.subject+'</a></td><td>'+item.date+'</td></tr>');
					});		
				}
			
			});

		});
	});
		 		
/* 		 		$(document).ready(function(){ 
		 			$('.brown').click(function(){
		 				alert("클릭");
		 				
		 				$('table').html('');
		 				
		 				$.ajax({
		 					url:'jsonArray.jsp',
		 					dataType:'json',
		 					success:function(arr){
		 						$.each(arr,function(index,item){
		 							$('table').append('<tr><td class="contxt"><a href="#">'+item.subject+'</a></td><td>'+item.date+'</td></tr>');
		 						});		
		 					}
		 				
		 				});

		 			});
		 		}); */
</script>
</head>
<body>
<div id="wrap">
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더파일들어가는 곳 -->
<!-- 메인이미지 들어가는곳 -->
<div class="clear"></div>
<div id="main_img"><img src="images/main_img.jpg"
 width="971" height="282"></div>
<!-- 메인이미지 들어가는곳 -->
<!-- 메인 콘텐츠 들어가는 곳 -->
<article id="front">
<div id="solution">
<div id="hosting">
<h3>Web Hosting Solution</h3>
<p>Lorem impsun Lorem impsunLorem impsunLorem
 impsunLorem impsunLorem impsunLorem impsunLorem
  impsunLorem impsunLorem impsun....</p>
</div>
<div id="security">
<h3>Web Security Solution</h3>
<p>Lorem impsun Lorem impsunLorem impsunLorem
 impsunLorem impsunLorem impsunLorem impsunLorem
  impsunLorem impsunLorem impsun....</p>
</div>
<div id="payment">
<h3>Web Payment Solution</h3>
<p>Lorem impsun Lorem impsunLorem impsunLorem
 impsunLorem impsunLorem impsunLorem impsunLorem
  impsunLorem impsunLorem impsun....</p>
</div>
</div>
<div class="clear"></div>
<div id="sec_news">
<h3><span class="orange">Security</span> News</h3>
<dl>
<dt>Vivamus id ligula....</dt>
<dd>Proin quis ante Proin quis anteProin 
quis anteProin quis anteProin quis anteProin 
quis ante......</dd>
</dl>
<dl>
<dt>Vivamus id ligula....</dt>
<dd>Proin quis ante Proin quis anteProin 
quis anteProin quis anteProin quis anteProin 
quis ante......</dd>
</dl>
</div>
<div id="news_notice">
<h3 class="brown">News &amp; Notice</h3>
<table>
<!-- <tr><td class="contxt"><a href="#">Vivans....</a></td>
    <td>2012.11.02</td></tr>
<tr><td class="contxt"><a href="#">Vivans....</a></td>
    <td>2012.11.02</td></tr>
<tr><td class="contxt"><a href="#">Vivans....</a></td>
    <td>2012.11.02</td></tr>
<tr><td class="contxt"><a href="#">Vivans....</a></td>
    <td>2012.11.02</td></tr>
<tr><td class="contxt"><a href="#">Vivans....</a></td>
    <td>2012.11.02</td></tr> -->
</table>
</div>
</article>
<!-- 메인 콘텐츠 들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터 들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터 들어가는 곳 -->
</div>
</body>
</html>