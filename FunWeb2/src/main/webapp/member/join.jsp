<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/subpage.css" rel="stylesheet" type="text/css">
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
 $(document).ready(function(){
	 
	 $('#join').submit(function(){
		 if($('.id').val()=="") {
			 alert("아이디 입력하세요");
			 $('.id').focus();
			 return false;
		 }
		 if($('.pass').val()=="") {
			 alert("비밀번호 입력하세요");
			 $('.pass').focus();
			 return false;
		 }
		 if($('.pass2').val()=="") {
			 alert("비밀번호 확인 입력하세요");
			 $('.pass2').focus();
			 return false;
		 }
		 if($('.pass').val()!=$('.pass2').val()) {
			 alert("비밀번호 틀림");
			 $('.pass2').focus();
			 return false;
		 }
		 if($('.email').val()=="") {
			 alert("이메일 입력하세요");
			 $('.email').focus();
			 return false;
		 }
		 if($('.email2').val()=="") {
			 alert("이메일 확인 입력하세요");
			 $('.email2').focus();
			 return false;
		 }
		 if($('.email').val()!=$('.email2').val()) {
			 alert("이메일 재입력 틀림");
			 $('.email2').focus();
			 return false;
		 }
	 });
	 
	 $('.dup').click(function(){
			if($('.id').val()==""){
				alert("아이디 입력하세요");
				$('.id').focus();
				return false;
			}
		
			$.ajax({ //버튼에 J쿼리 -> ajax를 이용하여 페이지없이 바로 자바단으로 이동하여 DB후 자바단 결과 불러오기
				url:'MemberIdCheck.me',
				data:{'id':$('.id').val()}, // key : value 형식으로 Parameter형식으로 url로 넘겨줌 
				success:function(result){
			
					if(result.trim()=="아이디 중복"){
						$('.divresult').html(result).css("color","red");
					}else{
						$('.divresult').html(result).css("color","blue");
					}
				}
				
			});
			
		});
	 
	});
</script>

</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 본문메인이미지 -->
<div id="sub_img_member"></div>
<!-- 본문메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="#">Join us</a></li>
<li><a href="#">Privacy policy</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<article>
<h1>Join Us</h1>
<form action="MemberInsertPro.me" id="join">
<fieldset>
<legend>Basic Info</legend>
<label>User ID</label>
<input type="text" name="id" class="id">
<input type="button" value="dup. check" class="dup"><br>

<label></label>
<div class="divresult">아이디 중복체크 결과</div><br>

<label>Password</label>
<input type="password" name="pass" class="pass"><br>
<label>Retype Password</label>
<input type="password" name="pass2" class="pass2"><br>
<label>Name</label>
<input type="text" name="name" class="name"><br>
<label>E-Mail</label>
<input type="email" name="email" class="email"><br>
<label>Retype E-Mail</label>
<input type="email" name="email2" class="email2"><br>
</fieldset>

<fieldset>
<legend>Optional</legend>
<label>Address</label>
<input type="text" name="address"><br>
<label>Phone Number</label>
<input type="text" name="phone"><br>
<label>Mobile Phone Number</label>
<input type="text" name="mobile"><br>
</fieldset>
<div class="clear"></div>
<div id="buttons">
<input type="submit" value="Submit" class="submit">
<input type="reset" value="Cancel" class="cancel">
</div>
</form>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>