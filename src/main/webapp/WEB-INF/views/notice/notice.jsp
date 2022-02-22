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
<form action="/notice/noticeInfo" method="post">
	<table class="table">
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">Title</th>
				<th scope="col">Writer</th>
				<th scope="col">Date</th>
			</tr>
		</thead>
		<tbody class="notice_list">
			
		</tbody>
	</table>

</form>
<div class="write_bar">
	<button type="buttom">
		<a href="/notice/write" />글 작성하기
	</button>
</div>
<script>
	$(document).ready(function(){
		search();
	});
	
	function search(){
		$.ajax({
		    type : 'POST',
		    url : '/getNoticeList',
		    data : {},
		    error : function(error) {
		        alert("Error!");
		    },
		    success : function(value) {
		    	console.log(value);
		    	let html = "";
		    	for (var i = 0; i < value.length; i++) {
		    		html += '<tr><td>' + value[i].ni_no + '</td>';
					html += '<td><a href="/notice/noticeInfo?ni_no='+value[i].ni_no+'">'+value[i].ni_title +'</a></td>';
					html += '<td>' + value[i].ni_writer + '</td>';
					html += '<td>' + value[i].ni_insertdt + '</td></tr>';
				}
		    	console.log(html);
		    	$(".notice_list").append(html);    	
		    }
		});

	}

</script>