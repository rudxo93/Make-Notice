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
	<tbody class="notice_list"> <!-- 게시글 -->
		
	</tbody>
</table>
<div id="pageNav"></div> <!-- 페이징 관련 nav bar -->
<div class="search-wrap">
	<button type="button" class="s_btn">검색</button>
	<input type="text" name="searchKeyword" id="searchKeyword">
	<select name="searchType" id="searchType"> <!-- searchType의 값은 option의 value가 된다 -->
		<option value="all">전체</option>
		<option value="ni_title">제목</option>
		<option value="ni_content">내용</option>
		<option value="ni_writer">작성자</option>
	</select>
</div>
<div class="write_bar">
	<button type="button">
		<a href="/notice/write"/>글 작성하기
	</button>
</div>
<script>
	$(document).ready(function(){ // 문서가 준비가 완료되면 매개변수로 넣은 콜백함수 실행
		search(1); // 실행할 기능을 정의
	});
	
	$(document).ready(function() { // 검색 버튼 클릭시 실행
		$(".s_btn").click(function() {
			search(1);
		});
	});
	
	function search(selPage){ // ready로부터 콜백함수 실행
		let keyword = $("#searchKeyword").val(); // 키워드와 검색타입 값을 가져온다.
		let s_type = $("#searchType").val();
		var data = {}; // data변수 만들어서 값을 넣어서 뭉탱이로 보낸다.
		data.curPage = selPage; // data변수에 값 추가 -> selPage 현재 보고있는 페이지
		data.pagePerCnt = 4; // data변수에 값 추가 -> 한 페이지에 보여주고자 하는 게시글 수
		data.type = s_type; // 검색 타입과 키워드값을 추가
		data.keyword = keyword;
		
		console.log(data); // data 값 확인
		
		$.ajax({
		    type : 'POST',
		    url : '/getNoticeList',
		    data : data,
		    error : function(error) {
		        alert("Error!");
		    },
		    success : function(value) {
		    	console.log(value);
		    	let list = "";
		    	let html = "";
		    	$(".notice_list").children().remove();
		    	for (var i = 0; i < value.list.length; i++) {
		    		list = value.list[i];
		    		html += '<tr><td>' + list.ni_no + '</td>';
					html += '<td><a href="/notice/noticeInfo?ni_no='+list.ni_no+'">'+list.ni_title +'</a></td>';
					html += '<td>' + list.ni_writer + '</td>';
					html += '<td>' + list.ni_insertdt + '</td></tr>';
				}
		    	
		    	console.log(html);
		    	$(".notice_list").append(html);   
		    	$("#pageNav").paging({ // 페이징 navbar.paging해서 jQuery로 이동할때 값을 넣어준다.
		    		pageSize : data.pagePerCnt,
		    		currentPage : data.curPage,
		    		pageTotal : value.paging.TOTALCNT
		    	});
		    	
		    }
		});

	}
	
	function goPage(selPage){ /* goPage가 실행이 되면 실행*/
		search(selPage); // 현재 보고있는 페이지에서 클릭이 일어난다.
	}
</script>