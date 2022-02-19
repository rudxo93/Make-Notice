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
		return userDAO.idCheck(id); // 0 또는 1
	}

	public UserDTO login(UserDTO dto) {
		return userDAO.login(dto);
	}

	public UserDTO mypage(String id) {
		return userDAO.mypage(id);
	}

	public int userUpdate(UserDTO dto, HttpServletRequest req) {
		UserDTO user = (UserDTO) req.getSession().getAttribute("loginMember"); // 등록된 세션 가져오기

		String sessionId = user.getMi_id(); // 세션에서 id를 담아주기

		// 고객이 입력한 정보 update(비밀번호, 휴대폰, 유선전화, 이메일, 주소)
		String addr = req.getParameter("mi_addr_num") + "!" + req.getParameter("mi_address") + "!"
				+ req.getParameter("mi_addr_dtl");
		
		dto.setMi_addr(addr);
		
		if (dto.getMi_id().equals(sessionId)) {
			return userDAO.userUpdate(dto); // 결과값 1
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