<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-lg-12">
	<form  action="/notice/notice-update" method="post" enctype="multipart/form-data">
		<div class="card">
			<div class="card-header with-border">
				<h3 class="card-title">게시글 수정하기</h3>
			</div>
				<div class="card-body">
				<input type="hidden" id="ni_no" name="ni_no" value="${notice.ni_no }"> 
				
				<div class="form-group">
					<label for="ni_writer">작성자</label> 
					<input class="form-control" id="ni_writer" name="ni_writer" value="${loginMember.mi_id }" readonly="readonly">
				</div>
				<div class="form-group">	
					<label for="ni_title">제목</label>
					<input class="form-control" id="ni_title" name="ni_title" value="${notice.ni_title }">
				</div>
				<div class="form-group">
					<label for="ni_content">내용</label>
					<textarea class="form-control" id="ni_content" name="ni_content" rows="30" placeholder="내용을 입력해주세요" style="resize: none;">${notice.ni_content }</textarea>
				</div>
				<div class="form-group">
					<label for="file">첨부파일</label> 
					<input class="form-control" id="writer" name="file" type="file"	>
					<label>${notice.file_name }</label><button type="button" class="del_btn">x</button>
				</div>
			</div>
			<div class="card-footer">
				<button type="button" class="btn btn-primary">
					<i class="fa fa-list"></i> <a href="/notice" style="color: white;">목록</a>
				</button>
				<div class="float-right">
					<button type="reset" class="btn btn-warning">
						<i class="fa fa-reply"></i> 초기화
					</button>
					<button type="submit" class="btn btn-success">
						<i class="fa fa-save"></i> 저장
					</button>
				</div>
			</div>
		</div>
	</form>
</div>
