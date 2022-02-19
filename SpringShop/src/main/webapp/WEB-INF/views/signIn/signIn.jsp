<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Bootstrap core CSS -->

<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->

<style>
.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-signin .checkbox {
  font-weight: 400;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>

<main class="form-signin text-center">
	<form action="/login" method="post">
		<h1 class="h3 mb-3 fw-normal">Please sign in</h1>

		<div class="form-floating">
			<input type="text" class="form-control" id="login_id" name="login_id"> 
			<label for="login_id">ID</label>
		</div>
		<div class="form-floating">
			<input type="password" class="form-control" id="login_pw" name="login_pw" placeholder="Password"> 
			<label for="login_pw">Password</label>
		</div>

		<div class="checkbox mb-3">
			<label> 
				<input type="checkbox" value="remember-me">Remember me
			</label>
		</div>
		<button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
		<a href="signUp">sign up</a>
		<p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
	</form>
</main>

