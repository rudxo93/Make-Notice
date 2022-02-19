package com.spring.shop.signUp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;

	public int userJoin(UserDTO n, HttpServletRequest req) {
		String addr = req.getParameter("mi_addr_num") + "!" + req.getParameter("mi_address") + "!"
				+ req.getParameter("mi_addr_dtl");
		n.setMi_addr(addr);
		try {
			return userDAO.userJoin(n);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int idCheck(String id) {
		if (id.length() == 0) {
			return 2;
		}
		return userDAO.idCheck(id); // 0 �Ǵ� 1
	}

	public UserDTO login(UserDTO dto) {
		return userDAO.login(dto);
	}

	public UserDTO mypage(String id) {
		return userDAO.mypage(id);
	}

	public int userUpdate(UserDTO dto, HttpServletRequest req) {
		UserDTO user = (UserDTO) req.getSession().getAttribute("loginMember"); // ��ϵ� ���� ��������

		String sessionId = user.getMi_id(); // ���ǿ��� id�� ����ֱ�

		// ���� �Է��� ���� update(��й�ȣ, �޴���, ������ȭ, �̸���, �ּ�)
		String addr = req.getParameter("mi_addr_num") + "!" + req.getParameter("mi_address") + "!"
				+ req.getParameter("mi_addr_dtl");
		
		dto.setMi_addr(addr);
		
		if (dto.getMi_id().equals(sessionId)) {
			return userDAO.userUpdate(dto); // ����� 1
		} else {
			return 0;
		}

	}

	public int userDelete(String id, HttpServletRequest req) {
		UserDTO user = (UserDTO) req.getSession().getAttribute("loginMember");
		String sessionID = user.getMi_id();
		if (id.equals(sessionID)) {
			try {
				return userDAO.userDelete(id);
			} catch (Exception e) {
				return 0;
			}
		} else {
			return 0;
		}

	}

}