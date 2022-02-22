<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-lg-12">
	<form  action="/notice/notice-delete" method="post">
		<div class="card">
			<div class="card-header with-border">
				<h3 class="card-title">게시글</h3>
			</div>
				<div class="card-body">
				<input type="hidden" id="ni_no" name="ni_no" value="${notice.ni_no }"> 
				
				<div class="form-group">
					<label for="ni_writer">작성자</label> 
					<input class="form-control" id="ni_writer" name="ni_writer" value="${notice.ni_writer }" readonly="readonly">
				</div>
				<div class="form-group">	
					<label for="ni_title">제목</label>
					<input class="form-control" id="ni_title" name="ni_title" value="${notice.ni_title }" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="ni_content">내용</label>
					<textarea class="form-control" id="ni_content" name="ni_content" readonly="readonly" style="resize: none;"><c:out value="${notice.ni_content } "></c:out></textarea>
				</div>
			</div>
			<div class="card-footer">
				<button type="button" class="btn btn-primary">
					<i class="fa fa-list"></i> <a href="/notice" style="color: white;">목록</a>
				</button>
				<div class="float-right">
					<button type="button" class="btn btn-success">
						<i class="fa fa-save"></i><a href="/notice/notice-updatePage?ni_no=${notice.ni_no }" style="color: white;">글 수정하기</a>
					</button>
					<button type="submit" class="btn btn-success">
						<i class="fa fa-save"></i> 글 삭제하기
					</button>
				</div>
			</div>
		</div>
	</form>
</div>