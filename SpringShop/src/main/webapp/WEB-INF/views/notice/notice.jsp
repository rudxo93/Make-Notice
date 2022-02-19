<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.write_bar {
	display: flex;
	flex-direction: row-reverse;
}

.write_bar button {
	padding: 5px;
	margin: 0px 10px 10px 0px;
}
</style>
<table class="table">
	<thead>
		<tr>
			<th scope="col">No.</th>
			<th scope="col">Title</th>
			<th scope="col">Writer</th>
			<th scope="col">Date</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th scope="row">${ni_no }</th>
			<td><a href="/notice/notice/ni_no=${ni_no }">${ni_title }</a></td>
			<td>${ni_writer }</td>
			<td>${ni_insertdt }</td>
		</tr>
	</tbody>
</table>
<div class="write_bar">
	<button type="buttom"><a href="/notice/write-page">글 작성하기</button>
</div>
