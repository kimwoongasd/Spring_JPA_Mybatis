<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#box_check,#f{
		display: none;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var code = 0;
		
		$("#btnSendSms").click(function() {
			var phone = $("#phone").val();
			var data = {phone:phone};
			
			$.ajax({
				url:"sendCodeSms",
				data:data,
				success:function(r) {
					code = r;
					console.log(r);
					$("#box_check").css("display", "block")
				}
			})
		});
		
		$("#btnCheck").click(function(){
			var num = $("#checkNUM").val()
			if (num == code) {
				var phone = $("#phone").val()
				$("#f").css("display", "block")
				$(".check").css("display", "none");
				$("#phone2").attr("value", phone);
			} else {
				alert("코드가 일치하지 않습니다.");
			}
		});
	});
</script>
</head>
<body>
	<h2>화원가입</h2>
	
	<div id="box_email" class="check">
		전화번호 : <input type="text" id="phone">
		<button id="btnSendSms">인증</button>
	</div>
	
	<div id="box_check" class="check">
		인증번호 입력 : <input type="text" id="checkNUM">
		<button id="btnCheck">확인</button>
	</div>
	
	<form action="join" method="post" id="f">
		아이디 : <input type="text" name="id"><br>
		비밀번호 : <input type="password" name="pwd"><br>
		이름 : <input type="text" name="name"><br>
		이메일 : <input type="email" name="email" id="email2"><br>
		전화번호 : <input type="text" name="phone" id="phone2"><br>
		<input type="submit" value="회원가입">
		<input type="reset" value="다시입력">
	</form>
</body>
</html>