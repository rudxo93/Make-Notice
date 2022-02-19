<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
body {
	min-height: 100vh;
	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),
		to(#1d466c));
	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
}

.input-form {
	max-width: 680px;
	margin-top: 80px;
	padding: 32px;
	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
}

.idChk{
	width: 100px;
	height: 38px;
}
</style>
<div class="container">
	<div class="input-form-backgroud row">
		<div class="input-form col-md-12 mx-auto">
			<h4 class="mb-3">회원가입</h4>
			<form class="validation-form" action="/userJoin" method="post">
				<div class="row" style="align-items: center;">
					<div class="col-md-6 mb-3">
						<label for="mi_id">아이디</label> 
						<input type="text" name="mi_id" class="form-control" id="mi_id">
					</div>
					<div style="width: 120px; height: 40px;">
						<button type="button" class="idChk" id="idChk">중복 체크</button>								
					</div>
				</div>
					<div class="col-md-6 mb-3">
						<label for="mi_name">이름</label> 
						<input type="text" name="mi_name" class="form-control" id="mi_name">
					</div>
				<div class="mb-3">
					<label for="mi_pw">비밀번호</label> 
					<input type="password" name="mi_pw" class="form-control" id="mi_pw">
				</div>
				<div class="mb-3">
					<label for="mi_chkpw">비밀번호 확인</label> 
					<input type="password" class="form-control" id="mi_chkpw">
				</div>
				<div class="mb-3">
					<label for="mi_moblie">휴대전화</label> 
					<input type="text" name="mi_moblie" class="form-control" id="mi_moblie">
				</div>
				<div class="mb-3">
					<label for="mi_tell">유선전화</label> 
					<input type="text" name="mi_tell" class="form-control" id="mi_tell">
				</div>
				<div class="mb-3">
					<label for="mi_email">이메일</label> 
					<input type="email" name="mi_email" class="form-control" id="mi_email" placeholder="you@example.com">
				</div>
				<div class="mb-3">
					<label for="mi_addr">주소</label> 
					<input type="text" name="mi_addr_num" class="form-control" id="mi_addr_num" readonly="readonly" placeholder="우편번호">
					<input type="button" value="주소검색" onclick="kakaopost()">
					<input type="text" name="mi_address" class="form-control" id="mi_addr" readonly="readonly" placeholder="주소">
					<input type="text" name="mi_addr_dtl" class="form-control" id="mi_addr_dtl" placeholder="상세주소">
				</div>
				<hr class="mb-4">
				<div class="mb-4"></div>
				<button class="btn btn-primary btn-lg btn-block" type="submit">가입 완료</button>
			</form>
		</div>
	</div>
</div>

<script> 
	$(document).ready(function() {
		$("#idChk").click(function() {
			let id = $("#mi_id").val();
			checkID(id);
		});
	});

	function checkID(id) {
		$.ajax({
			type : 'POST',
			url : '/signUp/idChk',
			data : {
				"mi_id" : id
			},
			error : function(error) {
				alert("Error!");
			},
			success : function(value) {
				if (value == 1) {
					alert("중복된 아이디입니다.");
					$("#mi_id").focus();
				} else if(value == 0) {
					alert("사용가능한 아이디입니다.");
					$("#mi_name").focus();
				} else {
					alert("아이디를 입력하지 않았습니다.");
					$("#mi_id").focus();
				}
			}
		});
	}
</script>

