<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<div class="col-lg-12">
	<form  action="/notice/notice-write" method="post">
		<div class="card">
			<div class="card-header with-border">
				<h3 class="card-title">�Խñ� �ۼ�</h3>
			</div>
			<div class="card-body">
				<div class="form-group">
					<label for="ni_title">����</label>
					<input class="form-control" id="ni_title" name="ni_title" placeholder="������ �Է����ּ���">
				</div>
				<div class="form-group">
					<label for="ni_content">����</label>
					<textarea class="form-control" id="conteni_contentnt" name="ni_content" rows="30" placeholder="������ �Է����ּ���" style="resize: none;"></textarea>
				</div>
				<div class="form-group">
					<label for="writer">�ۼ���</label> 
					<input class="form-control" id="writer" name="writer" value="${loginMember.mi_id }" readonly="readonly">
				</div>
			</div>
			<div class="card-footer">
				<button type="button" class="btn btn-primary">
					<i class="fa fa-list"></i> <a href="/notice" style="color: white;">���</a>
				</button>
				<div class="float-right">
					<button type="reset" class="btn btn-warning">
						<i class="fa fa-reply"></i> �ʱ�ȭ
					</button>
					<button type="submit" class="btn btn-success">
						<i class="fa fa-save"></i> ����
					</button>
				</div>
			</div>
		</div>
	</form>
</div>
