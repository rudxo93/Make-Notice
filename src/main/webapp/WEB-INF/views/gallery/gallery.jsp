<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<main>

	<section class="py-5 text-center container">
		<div class="row py-lg-5">
			<div class="col-lg-6 col-md-8 mx-auto">
				<h1 class="fw-light">사진과 함께 소개하는 시간~</h1>
				<p class="lead text-muted">사진과 함께 소개하는 시간을 가져봅시다</p>
				<p>
					<a href="/gallery/write" class="btn btn-primary my-2">갤러리 등록</a> <a
						href="#" class="btn btn-secondary my-2">뭐가 오면 좋을까요..?</a>
				</p>
			</div>
		</div>
	</section>
	<div class="album py-5 bg-light">
		<div class="row gallery_box column">
			<!-- 현재 이부분이 사진 + 제목 + 내용 -->
					<!-- list가 들어온다 -->
						<!-- 이 부분이 반복되면서 list로 보여준다 
						<div class="gallery_info">
							<h3>갤러리 제목이 올 부분</h3>
							<div class="card shadow-sm">
								<svg class="bd-placeholder-img card-img-top" width="225px"
									height="225px">     
									<text x="50%" y="50%" fill="#dddddd" dy=".3em">여기가 이미지가 올 부분</text></svg>

								<div class="card-body">
									<p class="card-text">갤러리 상세 글을 간략하게만 보여주기</p>
									<div class="d-flex justify-content-between align-items-center">
										<div class="btn-group">
											<button type="button"
												class="btn btn-sm btn-outline-secondary" id="v_btn">View</button>
											<button type="button"
												class="btn btn-sm btn-outline-secondary" id="e_btn">Edit</button>
										</div>
										<small class="text-muted">9 mins</small>
									</div>
								</div>
							</div>
						</div>
						<!-- 이 부분이 반복되면서 list로 보여준다 -->


					</div>


		<div id="pageNav"></div>
		<div class="search-wrap" style="margin-top: 30px;">
			<button type="button" class="s_btn">검색</button>
			<input type="text" name="searchKeyword" id="searchKeyword"> <select
				name="searchType" id="searchType">
				<!-- searchType의 값은 option의 value가 된다 -->
				<option value="all">전체</option>
				<option value="ni_title">제목</option>
				<option value="ni_content">내용</option>
				<option value="ni_writer">작성자</option>
			</select>
		</div>
	</div>


</main>

<script>
	$(document).ready(function() { // 문서가 준비가 완료되면 매개변수로 넣은 콜백함수 실행
		search(1); // 실행할 기능을 정의
	});

	$(document).ready(function() { // 검색 버튼 클릭시 실행
		$(".s_btn").click(function() {
			search(1);
		});
	});
	
	$(document).ready(function() { // edit버튼 클릭 시 작성자가 누군지 알람으로 띄워주기
		$("#e_btn").click(function() {
			
		});
	});
	
	function search(selPage){ // ready로부터 콜백함수 실행
		let keyword = $("#searchKeyword").val(); // 키워드와 검색타입 값을 가져온다.
		let s_type = $("#searchType").val();
		var data = {}; // data변수 만들어서 값을 넣어서 뭉탱이로 보낸다.
		data.curPage = selPage; // data변수에 값 추가 -> selPage 현재 보고있는 페이지
		data.pagePerCnt = 8; // data변수에 값 추가 -> 한 페이지에 보여주고자 하는 게시글 수
		data.type = s_type; // 검색 타입과 키워드값을 추가
		data.keyword = keyword;
		
		console.log(data); // data 값 확인
		
		$.ajax({
		    type : 'POST',
		    url : '/getGalleryList',
		    data : data,
		    error : function(error) {
		        alert("Error!");
		    },
		    success : function(value) {
		    	console.log(value);
		    	let list = "";
		    	let html = "";
		    	$(".gallery_box").children().remove();
		    	for (var i = 0; i < value.list.length; i++) {
		    		list = value.list[i];
		    		html += '<div class="col-3">';
		    		html += '<div class="gallery_info">';
		    		html += '<h3>' + list.gi_title + '</h3>';
		    		html += '<div class="card shadow-sm">';
		    		html += '<svg class="bd-placeholder-img card-img-top" width="225px" height="225px">';
		    		html += '<text x="50%" y="50%" fill="#dddddd" dy=".3em">여기가 이미지가 올 부분</text></svg>';
		    		html += '<div class="card-body">';
		    		html += '<p class="card-text">'+ list.gi_content + '</p>';
		    		html += '<div class="d-flex justify-content-between align-items-center">';
		    		html += '<div class="btn-group">';
		    		html += '<button type="button" class="btn btn-sm btn-outline-secondary" id="v_btn">View</button>';
		    		html += '<button type="button" class="btn btn-sm btn-outline-secondary" id="e_btn">Edit</button>';
		    		html += '</div>';
		    		html += '<small class="text-muted">9 mins</small>';
		    		html += '</div>';
		    		html += '</div>';
		    		html += '</div>';
		    		html += '</div>';
		    		html += '</div>';
		    		
				
				}
		    	
		    	console.log(html);
		    	$(".gallery_box").append(html);   
		    	$("#pageNav").paging({ // 페이징 navbar.paging해서 jQuery로 이동할때 값을 넣어준다.
		    		pageSize : data.pagePerCnt,
		    		currentPage : data.curPage,
		    		pageTotal : value.paging.TOTALCNT
		    	});	
		    }
		});

	}
	
	function goPage(selPage){ //* goPage가 실행이 되면 실행
		search(selPage); // 현재 보고있는 페이지에서 클릭이 일어난다.
	}
	
</script>

