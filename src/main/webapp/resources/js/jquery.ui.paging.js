(function($) {
	$.fn.paging = function(options) { // paging 함수가 실행 되면 실행이 된다.
		var curThis = this;
		var defaults = { 
			pageSize: 20, // 보여줄 게시글 수
			currentPage: 1, // 현재 페이지
			pageTotal: 0, // 총 게시글 수
			pageBlock: 10 // 페이징 해서 보여줄 번호 개수
		};
		
		var subOption = $.extend(true, defaults, options); // 복수의 오브젝트를 합쳐서 되돌려주는 함수 -> true, defaults, options를 합친다.
		var goPageFnName = null;
		
		if( subOption.goPageFnName == undefined || subOption.goPageFnName == null  || subOption.goPageFnName == "" ) {
			goPageFnName = "goPage";
		} else {
			goPageFnName = subOption.goPageFnName;
		}

		return this.each(function() {
			var currentPage = subOption.currentPage*1;  // 현재 페이지
			var pageSize = subOption.pageSize*1;   // 출력 리스트 수
			var pageBlock = subOption.pageBlock*1;  // 1~10까지 블락
			var pageTotal = subOption.pageTotal*1;  // 총 데이터 수
			
			if (!pageSize) pageSize = 10;     // 출력 리스트수가 없으면 초기값 10으로 설정
			if (!pageBlock) pageBlock = 10;     // 블락 초기값이 없으면 10으로 설정
			
			var pageTotalCnt = Math.ceil(pageTotal/pageSize);
			var pageBlockCnt = Math.ceil(currentPage/pageBlock);
			var sPage, ePage;
			var html = "";
			
			
			// 페이징의 시작점 번호
			if (pageBlockCnt > 1) {
				sPage = (pageBlockCnt-1) * pageBlock+1;
			} else {
				sPage = 1;
			}
			
			// 페이징의 마지막 번호
			if ((pageBlockCnt * pageBlock) >= pageTotalCnt) {
				ePage = pageTotalCnt;
			} else {
				ePage = pageBlockCnt * pageBlock;
			} 
			
			// << , <   이전 버튼과 처음으로 이동 버튼 관련
			if (sPage > 1) { 
				html += '<a href="#" onclick="' + goPageFnName + '(' + 1 + ');" class="first"><span><i class="fas fa-angle-double-left"></i></span></a>';
				html += '<a href="#" onclick="' + goPageFnName + '(' + (sPage-pageBlock) + ');" class="prev"><span><i class="fas fa-angle-left"></i></span></a>'; 
			}
			
			for (var i=sPage; i<=ePage; i++) {
				if (currentPage == i) { 
					html += '<a href="#" class="active">'+i+'</a>';   
				} else {
					html += '<a href="#" onclick="' + goPageFnName + '(' + i + ');">'+i+'</a>' 
				}
			}
			// >, >> 다음과 맨 끝으로 이동 버튼 관련 
			if (ePage < pageTotalCnt) {
				html += '<a href="#" onclick="' + goPageFnName + '(' + (sPage+pageBlock) + ');" class="next"><span><i class="fas fa-angle-right"></i></span></a>'
				html += '<a href="#" onclick="' + goPageFnName + '(' + pageTotalCnt + ');" class="last"><span><i class="fas fa-angle-double-right"></i></span></a>'
			}
			
			html += '';
			
			$(curThis).empty().append(html);
		});
	};
	
})(jQuery);