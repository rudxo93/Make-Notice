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
	
	// ������ �� ��ȸ
	public Map<String, Object> getGalleryListAll(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		// �˻� ��� �� ��������
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
	
	// ������ ���� ������ �̵� -> ���ε� �� �������� �߰��Ǿ ������ ��
	public GalleryDTO getGallery(int gi_no) {
		return galleryDAO.getGallery(gi_no);
	}
	
	// ������ �Խñ� �ۼ� + ���� ���� ���ε�
	public int galleryInsert(GalleryDTO dto, HttpServletRequest req) throws IOException {
		TransactionStatus status = pt.getTransaction(new DefaultTransactionAttribute());
		// realPath ��� ����
		String path = req.getSession().getServletContext().getRealPath("resources/file");
		File saveDir = new File(path);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		// ���� ���� ���ε�
		try { // ������ �Խñ� �ۼ� try / catch
				// ���� ���� list�� ����
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
			List<MultipartFile> fileList = mr.getFiles("file");

			galleryDAO.galleryInsert(dto); // ������ �Խñ� �ۼ�

			// ����Ʈ�� for������ ������ -> ���� ������ originName, tempName, size�� ���Ѵ�.
			for (MultipartFile mf : fileList) {

				// for������ ��� ���ϵ��� name�۾� ����
				String name = mf.getOriginalFilename();

				// ���ε� �� ������ �����Ѵٸ�?
				if (!name.isEmpty()) {
					File destination = File.createTempFile("F_" + System.currentTimeMillis(), name.substring(name.lastIndexOf(".")), saveDir);
					String fileSavedName = destination.getName();
					long fileSize = mf.getSize();
					FileCopyUtils.copy(mf.getInputStream(), new FileOutputStream(destination));

					// name�۾��� �������� ���� ���ε带 �����Ѵ�.
					try { // ���� ���ε� try / catch
							// ���� ���ε� ����
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("board_id", dto.getGi_no());
						param.put("file_name", name);
						param.put("saved_file_name", fileSavedName);
						param.put("file_size", fileSize);

						// ������ ���� ���� ���ε�
						galleryDAO.galleryInsertBoard(param);

					} catch (Exception e) {
						// TODO: handle exception
						pt.rollback(status); // �ѹ� - ���������� ����� ��� ���¸� �����·� ������.
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
			System.out.println("�����ø��� ����!");
			return 0;
		}

	}
}
