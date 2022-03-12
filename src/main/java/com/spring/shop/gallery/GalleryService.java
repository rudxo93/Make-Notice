package com.spring.shop.gallery;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
public class GalleryService {
	
	@Autowired
	PlatformTransactionManager pt;

	@Autowired
	GalleryDAO galleryDAO;
	
	@Autowired
	Page page;
	
	// 갤러리 글 조회
	public Map<String, Object> getGalleryListAll(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		// 검색 기능 값 가져오기
		String s_type = req.getParameter("type");
		String s_keyword = req.getParameter("keyword");
		
		param.put("searchType", s_type);
		param.put("keyword", s_keyword);
		int totalCnt = galleryDAO.getTotalCnt(param);
		int pagePerCnt = Integer.parseInt(req.getParameter("pagePerCnt"));
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		
		param.putAll(page.pageGenerator(totalCnt, pagePerCnt, curPage));
		List<GalleryDTO> list = galleryDAO.getGalleryListAll(param);
		
		result.put("list", list);
		result.put("paging", param);
		
		return result;
		
	}
	
	// 갤러리 정보 페이지 이동 -> 업로드 된 사진파일 추가되어서 보여줄 것
	public GalleryDTO getGallery(int gi_no) {
		return galleryDAO.getGallery(gi_no);
	}
	
	// 갤러리 게시글 작성 + 다중 파일 업로드
	public int galleryInsert(GalleryDTO dto, HttpServletRequest req) throws IOException {
		TransactionStatus status = pt.getTransaction(new DefaultTransactionAttribute());
		// realPath 경로 생성
		String path = req.getSession().getServletContext().getRealPath("resources/file");
		File saveDir = new File(path);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		// 다중 파일 업로드
		try { // 갤러리 게시글 작성 try / catch
				// 다중 파일 list에 저장
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
			List<MultipartFile> fileList = mr.getFiles("file");

			galleryDAO.galleryInsert(dto); // 갤러리 게시글 작성

			// 리스트를 for문으로 돌리기 -> 각각 파일의 originName, tempName, size를 구한다.
			for (MultipartFile mf : fileList) {

				// for문으로 모든 파일들의 name작업 진행
				String name = mf.getOriginalFilename();

				// 업로드 할 파일이 존재한다면?
				if (!name.isEmpty()) {
					File destination = File.createTempFile("F_" + System.currentTimeMillis(), name.substring(name.lastIndexOf(".")), saveDir);
					String fileSavedName = destination.getName();
					long fileSize = mf.getSize();
					FileCopyUtils.copy(mf.getInputStream(), new FileOutputStream(destination));

					// name작업이 마쳤으면 파일 업로드를 진행한다.
					try { // 파일 업로드 try / catch
							// 파일 업로드 진행
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("board_id", dto.getGi_no());
						param.put("file_name", name);
						param.put("saved_file_name", fileSavedName);
						param.put("file_size", fileSize);

						// 갤러리 다중 파일 업로드
						galleryDAO.galleryInsertBoard(param);

					} catch (Exception e) {
						// TODO: handle exception
						pt.rollback(status); // 롤백 - 비정상적인 종료시 모든 상태를 원상태로 돌린다.
						File delFile = new File(path + "/" + fileSavedName);
						delFile.delete();
						return 0;
					}
				}
			}
			pt.commit(status);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("서버올리기 실패!");
			return 0;
		}

	}
}
