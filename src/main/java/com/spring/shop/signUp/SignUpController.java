package com.spring.shop.signUp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignUpController {

	@Autowired
	UserService userService;

	// ȸ������ ������ �̵�
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String getSignUpPage(HttpServletRequest req) {

		req.setAttribute("content", "signUp/signUp.jsp");

		return "home";
	}
	
	// �α��� ������ �̵�
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String getLoginPage(HttpServletRequest req) {

		req.setAttribute("content", "signIn/signIn.jsp");

		return "home";
	}

	// ȸ������ ����
	@RequestMapping(value = "/userJoin", method = RequestMethod.POST)
	public String write(UserDTO n, HttpServletRequest req) {
		int rst = userService.userJoin(n, req);
		if (rst > 0) {
			req.setAttribute("MSG", "ȸ�����Լ���");
		} else {
			req.setAttribute("MSG", "ȸ�����Խ���");
		}
		req.setAttribute("content", "signIn/signIn.jsp");
		return "home";
	}

	// id �ߺ�üũ
	@RequestMapping(value = "/signUp/idChk", method = RequestMethod.POST)
	@ResponseBody
	public int idCheck(HttpServletRequest req) {
		String id = req.getParameter("mi_id");
		return userService.idCheck(id);
	}
	
	// �α׾ƿ�
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		req.setAttribute("MSG", "�α׾ƿ�����");
		req.setAttribute("content", "main/main.jsp");
		return "home";
	}

	// �α���
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserDTO dto, HttpServletRequest req) {
		dto.setMi_id(req.getParameter("login_id"));
		dto.setMi_pw(req.getParameter("login_pw"));
		UserDTO user = userService.login(dto);
		if (user != null) { // 0 �̻� -> �α��� ����
			req.setAttribute("MSG", "�α��� ����! �ݰ����ϴ�!");
			req.getSession().setAttribute("loginMember", user); // ���� ���
			req.setAttribute("content", "main/main.jsp");
			return "home";
		} else  { // 0 -> �α��� ����
			req.setAttribute("MSG", "�α��� ����! �������� �ʴ� �����̰ų� ��й�ȣ�� Ʋ�Ƚ��ϴ�..!");
			req.setAttribute("content", "signIn/signIn.jsp");
			return "home";
		} 
	}
	
	// ���� ������ �̵�
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(UserDTO dto, HttpServletRequest req) {
		req.setAttribute("content", "signIn/mypage.jsp");
		return "home";
	}
	
	// ȸ������ ����
	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
	public String updateUser(UserDTO dto, HttpServletRequest req) {
		// ������Ʈ �� ���� / ���, �޴���, ����ȭ, �̸���, �ּ�
		int rst = userService.userUpdate(dto, req);
		
		if(rst > 0) {
			req.setAttribute("MSG", "ȸ�������� �����Ǿ����ϴ�.");
			// -> ���� �Ϸ�� ������ user������ �ٽ� ��������
			// �α��� �Ҷ� ��� ������ �ҷ������ϱ� login�� ���ؼ� ������ ��� ������ ��������
			// �׷��� �Ǹ� �α��ο��� Ȯ���ϴ� ������ id�� pw�� �ִ�.
			// ���� ȸ�������� ������ �����̱� ������ ����
			UserDTO user = userService.login(dto); // ���� ������ �� ���� ������ ��ȸ
			req.getSession().setAttribute("loginMember", user); // ���ǵ��
			req.setAttribute("content", "signIn/mypage.jsp");
		} else {
			req.setAttribute("MSG", "�ٲ� ȸ�������� �����ϴ�.");
			req.setAttribute("content", "signIn/mypage.jsp");
		}
		
		return "home"; // 1�Ǵ� 0�� ��ȯ�ȴ�.
		
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(UserDTO dto, HttpServletRequest req) {
		
		int rst = userService.userDelete(dto.getMi_id(), req); // ������ id���� ������ �ͼ� db�� �ֱ�
		if(rst > 0) {
			req.getSession().invalidate(); // �ش� ���� ������
			req.setAttribute("MSG", "Ż�� �Ϸ�Ǿ����ϴ�. �����մϴ�!");
			req.setAttribute("content", "main/main.jsp");
		}else {
			req.setAttribute("MSG", "Ż�����");
			req.setAttribute("content", "signIn/mypage.jsp");
		}
		
		return "home";
		
	}
	
}
