package com.spring.shop.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.shop.commons.Page;

@Service
public class NoticeService {
	
	
	@Autowired
	PlatformTransactionManager pt;

	@Autowired
	NoticeDAO noticeDAO;

	@Autowired
	Page page;

	// 게시글 조회
	public Map<String, Object> getNoticeListAll(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		// 검색 기능 값 가져오기
		String s_type = req.getParameter("type");
		String s_keyword = req.getParameter("keyword");

		param.put("searchType", s_type);
		param.put("keyword", s_keyword);
		int totalCnt = noticeDAO.getTotalCnt(param);
		int pagePerCnt = Integer.parseInt(req.getParameter("pagePerCnt"));
		int curPage = Integer.parseInt(req.getParameter("curPage"));

		param.putAll(page.pageGenerator(totalCnt, pagePerCnt, curPage));
		List<NoticeDTO> list = noticeDAO.getNoticeListAll(param);

		result.put("list", list);
		result.put("paging", param);

		return result;
	}

	// 게시글 정보 페이지 이동 -> 업로드 된 파일 추가 될 수 있게
	public NoticeDTO getNotice(int ni_no) {
		return noticeDAO.getNotice(ni_no);
	}

	// 게시글 작성하기 + 파일 업로드
	public int noticeInsert(NoticeDTO dto, HttpServletRequest req) throws IOException {
		// post보낸 파일의 정보는 req에 있다.
		TransactionStatus status = pt.getTransaction(new DefaultTransactionAttribute());
		// realPath 경로 생성
		String path = req.getSession().getServletContext().getRealPath("resources/file");
		File saveDir = new File(path);
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		
		try {
			// req에 저장된 파일 데이터 가져오기
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
			MultipartFile file = mr.getFile("file");
			String name = file.getOriginalFilename();
			
			noticeDAO.noticeInsert(dto); // 게시글 작성
			
			// file 데이터가 비어있지 않다면
			if(!name.isEmpty()) {
				File destination = File.createTempFile("F_" + System.currentTimeMillis(), name.substring(name.lastIndexOf(".")), saveDir);
				System.out.println(destination);
				String fileSavedName = destination.getName();
				long size = file.getSize();
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination));		
				try {
					// 파일 업로드 관련 -> 파일이 존재하지 않다면 파일 업로드도 할 필요가 없다.
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("board_id", dto.getNi_no());
					param.put("file_name", name);
					param.put("saved_file_name", fileSavedName);
					param.put("file_size", size);
					noticeDAO.insertBoardAttach(param); // 결과값 ?
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
					// 롤백 비정상적인 종료인가???? 모든 작업을 원상태로 돌린다
					pt.rollback(status);
					File delFile = new File(path + "/" + fileSavedName);
					delFile.delete();
					return 0;
				}
			}
			// 커밋 정상적인 종료인가???? 지금까지 실행한 모든 작업을 '실제로'
			// 수행하라는 명렁을 내린다.
			pt.commit(status);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("서버올리기 실패");
			return 0;
		}
	}


	// 게시글 업데이트 + 새로운 파일 업데이트 시 기존 파일 삭제해주기 ++ 삭제버튼 누를시 파일 지워주기(예정)
	public int noticeUpdate(NoticeDTO dto, HttpServletRequest req) throws IOException {
		TransactionStatus status = pt.getTransaction(new DefaultTransactionAttribute());
		// ======== 파일 저장경로
		String path = req.getSession().getServletContext().getRealPath("resources/file"); 
		File saveDir = new File(path);
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		// =================================  파일 업로드 관련=======================================
		// 보드와 게시판을 업데이트 하는데
		// 보드 업데이트 진행 - > 실행 결과 0, 1이 나오는데 이것은  트랜젝션 처리를 하면 된다.
		try {
			// req에 저장된 파일을 가지고 
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
			MultipartFile file = mr.getFile("file");
			
			String name = file.getOriginalFilename(); // 원본파일명
			File destination = File.createTempFile("F_" + System.currentTimeMillis(), name.substring(name.lastIndexOf(".")), saveDir);// temp 파일명 제작
			// 파일을 추가하지 않고 올리게 되면 여기서 cathch로 넘어간다.
			String fileSavedName = destination.getName(); // temp파일명
			
			long size = file.getSize();
			
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination)); // temp파일명에다가 원본파일 데이터 입히기
			try {
				noticeDAO.noticeUpdate(dto);
				
				Map<String, Object> param2	= new HashMap<String, Object>();
				param2.put("file_size", size);
				param2.put("file_name", name);
				param2.put("saved_file_name", fileSavedName);
				param2.put("board_id", dto.getNi_no());
				
				// 이전의 tempname가져오기 => update가 진행되기 전에 가지고 있어야 한다.
				NoticeDTO t_Name =  noticeDAO.getNotice(dto.getNi_no());
				
				int rst = noticeDAO.boardUpate(param2); // 파일 업로드 update
				if(rst > 0) {
					File delFile = new File(path + "/" + t_Name.getSaved_file_name());  // 이전의 tempName으로 된 파일을 삭제해주어야 한다.
					delFile.delete();					
				}
				
				pt.commit(status);
				return 1; // 파일 업로드 update 결과 성공
			} catch (Exception e) {
				pt.rollback(status);
				File delFile = new File(path + "/" + fileSavedName);
				delFile.delete();
				return 0;
			} 
		} catch (Exception e) {
			System.out.println("서버올리기 실패");
			return 0;
		}
	}
	
	// 게시글 삭제하기 + 파일 삭제(해야됨)
		public int noticeDelete(NoticeDTO dto, HttpServletRequest req) {
			TransactionStatus status = pt.getTransaction(new DefaultTransactionAttribute());
			// ======== 파일 저장경로 =========
			String path = req.getSession().getServletContext().getRealPath("resources/file"); 
			File saveDir = new File(path);
			if(!saveDir.exists()) {
				saveDir.mkdirs();
			}
			
			
			// 삭제 진행(삭제 진행 시 file이 존재하는지 ?
			try {
				// 게시글 삭제 진행
				noticeDAO.noticeDelete(dto.getNi_no());
				
				if() {
					
					
				}
				
				
				
				// 게시글에 파일이 존재한다면?
				if(dto.getFile_name().isEmpty()) {
					noticeDAO.boardDelete(dto.getFile_name());
					
					File delFile = new File(path + "/" + dto.getSaved_file_name());
					delFile.delete();

				}
				pt.commit(status);
				return 1;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("삭제 실패");
				return 0;
			}
			
			
			
			return 1;
		}
		
		/*
		// 삭제 진행
	
	}
	
	
	 	//게시글에 파일이 존재한다면?
			if(!name.isEmpty()) {
				// 파일 명과 데이터 생성
				File destination = File.createTempFile("F_" + System.currentTimeMillis(), name.substring(name.lastIndexOf(".")), saveDir);
				System.out.println(destination);
				String fileSavedName = destination.getName(); // tempName
				long size = file.getSize();
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination));
				// ====================== 파일 삭제 진행================================
				try {
					// 삭제 성공
					noticeDAO.boardDelete(dto.getNi_no());
					File delFile = new File(path + "/" + fileSavedName);
					delFile.delete();
				} catch (Exception e) {
					pt.rollback(status); // 삭제 실패 -> 롤백
					return 0;
				} 
				
			}
			pt.commit(status);
			return 1; //파일 삭제 성공
	 */
	
	/*
	// 파일 삭제하기
	public int boardDelete(NoticeDTO dto, HttpServletRequest req) {
		// ====== 경로 생성 없다면 서버 파일 만들기 ======
		String path = req.getSession().getServletContext().getRealPath("resources/file");
		File saveDir = new File(path);
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		// 게시글 하나 조회
		NoticeDTO n_search = noticeDAO.getNotice(dto.getNi_no());
		// 쿼리에 사용할 파일 넘버 가져오기
		int f_num = n_search.getFile_num();
		
		int f_delete = noticeDAO.boardDelete(f_num);
		
		if(f_delete > 0) { // 파일 삭제 쿼리 성공
			File delFile = new File(path + "/" + n_search.getSaved_file_name()); // 경로의 tempName을 찾는다
			delFile.delete(); // 경로에 있는 파일 삭제
			
		}
		
		return f_delete;
	}
*/	
}
