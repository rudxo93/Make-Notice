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

	// �Խñ� ��ȸ
	public Map<String, Object> getNoticeListAll(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		// �˻� ��� �� ��������
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

	// �Խñ� ���� ������ �̵� -> ���ε� �� ���� �߰� �� �� �ְ�
	public NoticeDTO getNotice(int ni_no) {
		return noticeDAO.getNotice(ni_no);
	}

	// �Խñ� �ۼ��ϱ� + ���� ���ε�
	public int noticeInsert(NoticeDTO dto, HttpServletRequest req) throws IOException {
		// post���� ������ ������ req�� �ִ�.
		TransactionStatus status = pt.getTransaction(new DefaultTransactionAttribute());
		// realPath ��� ����
		String path = req.getSession().getServletContext().getRealPath("resources/file");
		File saveDir = new File(path);
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		
		try {
			// req�� ����� ���� ������ ��������
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
			MultipartFile file = mr.getFile("file");
			String name = file.getOriginalFilename();
			
			noticeDAO.noticeInsert(dto); // �Խñ� �ۼ�
			
			// file �����Ͱ� ������� �ʴٸ�
			if(!name.isEmpty()) {
				File destination = File.createTempFile("F_" + System.currentTimeMillis(), name.substring(name.lastIndexOf(".")), saveDir);
				System.out.println(destination);
				String fileSavedName = destination.getName();
				long size = file.getSize();
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination));		
				try {
					// ���� ���ε� ���� -> ������ �������� �ʴٸ� ���� ���ε嵵 �� �ʿ䰡 ����.
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("board_id", dto.getNi_no());
					param.put("file_name", name);
					param.put("saved_file_name", fileSavedName);
					param.put("file_size", size);
					noticeDAO.insertBoardAttach(param); // ����� ?
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
					// �ѹ� ���������� �����ΰ�???? ��� �۾��� �����·� ������
					pt.rollback(status);
					File delFile = new File(path + "/" + fileSavedName);
					delFile.delete();
					return 0;
				}
			}
			// Ŀ�� �������� �����ΰ�???? ���ݱ��� ������ ��� �۾��� '������'
			// �����϶�� ���� ������.
			pt.commit(status);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("�����ø��� ����");
			return 0;
		}
	}


	// �Խñ� ������Ʈ + ���ο� ���� ������Ʈ �� ���� ���� �������ֱ� ++ ������ư ������ ���� �����ֱ�(����)
	public int noticeUpdate(NoticeDTO dto, HttpServletRequest req) throws IOException {
		TransactionStatus status = pt.getTransaction(new DefaultTransactionAttribute());
		// ======== ���� ������
		String path = req.getSession().getServletContext().getRealPath("resources/file"); 
		File saveDir = new File(path);
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		// =================================  ���� ���ε� ����=======================================
		// ����� �Խ����� ������Ʈ �ϴµ�
		// ���� ������Ʈ ���� - > ���� ��� 0, 1�� �����µ� �̰���  Ʈ������ ó���� �ϸ� �ȴ�.
		try {
			// req�� ����� ������ ������ 
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
			MultipartFile file = mr.getFile("file");
			
			String name = file.getOriginalFilename(); // �������ϸ�
			File destination = File.createTempFile("F_" + System.currentTimeMillis(), name.substring(name.lastIndexOf(".")), saveDir);// temp ���ϸ� ����
			// ������ �߰����� �ʰ� �ø��� �Ǹ� ���⼭ cathch�� �Ѿ��.
			String fileSavedName = destination.getName(); // temp���ϸ�
			
			long size = file.getSize();
			
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination)); // temp���ϸ��ٰ� �������� ������ ������
			try {
				noticeDAO.noticeUpdate(dto);
				
				Map<String, Object> param2	= new HashMap<String, Object>();
				param2.put("file_size", size);
				param2.put("file_name", name);
				param2.put("saved_file_name", fileSavedName);
				param2.put("board_id", dto.getNi_no());
				
				// ������ tempname�������� => update�� ����Ǳ� ���� ������ �־�� �Ѵ�.
				NoticeDTO t_Name =  noticeDAO.getNotice(dto.getNi_no());
				
				int rst = noticeDAO.boardUpate(param2); // ���� ���ε� update
				if(rst > 0) {
					File delFile = new File(path + "/" + t_Name.getSaved_file_name());  // ������ tempName���� �� ������ �������־�� �Ѵ�.
					delFile.delete();					
				}
				
				pt.commit(status);
				return 1; // ���� ���ε� update ��� ����
			} catch (Exception e) {
				pt.rollback(status);
				File delFile = new File(path + "/" + fileSavedName);
				delFile.delete();
				return 0;
			} 
		} catch (Exception e) {
			System.out.println("�����ø��� ����");
			return 0;
		}
	}
	
	// �Խñ� �����ϱ� + ���� ����(�ؾߵ�)
		public int noticeDelete(NoticeDTO dto, HttpServletRequest req) {
			TransactionStatus status = pt.getTransaction(new DefaultTransactionAttribute());
			// ======== ���� ������ =========
			String path = req.getSession().getServletContext().getRealPath("resources/file"); 
			File saveDir = new File(path);
			if(!saveDir.exists()) {
				saveDir.mkdirs();
			}
			
			
			// ���� ����(���� ���� �� file�� �����ϴ��� ?
			try {
				// �Խñ� ���� ����
				noticeDAO.noticeDelete(dto.getNi_no());
				
				if() {
					
					
				}
				
				
				
				// �Խñۿ� ������ �����Ѵٸ�?
				if(dto.getFile_name().isEmpty()) {
					noticeDAO.boardDelete(dto.getFile_name());
					
					File delFile = new File(path + "/" + dto.getSaved_file_name());
					delFile.delete();

				}
				pt.commit(status);
				return 1;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���� ����");
				return 0;
			}
			
			
			
			return 1;
		}
		
		/*
		// ���� ����
	
	}
	
	
	 	//�Խñۿ� ������ �����Ѵٸ�?
			if(!name.isEmpty()) {
				// ���� ��� ������ ����
				File destination = File.createTempFile("F_" + System.currentTimeMillis(), name.substring(name.lastIndexOf(".")), saveDir);
				System.out.println(destination);
				String fileSavedName = destination.getName(); // tempName
				long size = file.getSize();
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination));
				// ====================== ���� ���� ����================================
				try {
					// ���� ����
					noticeDAO.boardDelete(dto.getNi_no());
					File delFile = new File(path + "/" + fileSavedName);
					delFile.delete();
				} catch (Exception e) {
					pt.rollback(status); // ���� ���� -> �ѹ�
					return 0;
				} 
				
			}
			pt.commit(status);
			return 1; //���� ���� ����
	 */
	
	/*
	// ���� �����ϱ�
	public int boardDelete(NoticeDTO dto, HttpServletRequest req) {
		// ====== ��� ���� ���ٸ� ���� ���� ����� ======
		String path = req.getSession().getServletContext().getRealPath("resources/file");
		File saveDir = new File(path);
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		// �Խñ� �ϳ� ��ȸ
		NoticeDTO n_search = noticeDAO.getNotice(dto.getNi_no());
		// ������ ����� ���� �ѹ� ��������
		int f_num = n_search.getFile_num();
		
		int f_delete = noticeDAO.boardDelete(f_num);
		
		if(f_delete > 0) { // ���� ���� ���� ����
			File delFile = new File(path + "/" + n_search.getSaved_file_name()); // ����� tempName�� ã�´�
			delFile.delete(); // ��ο� �ִ� ���� ����
			
		}
		
		return f_delete;
	}
*/	
}
