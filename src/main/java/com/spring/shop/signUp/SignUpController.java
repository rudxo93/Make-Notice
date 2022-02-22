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

	// 회원가입 페이지 이동
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String getSignUpPage(HttpServletRequest req) {

		req.setAttribute("content", "signUp/signUp.jsp");

		return "home";
	}
	
	// 로그인 페이지 이동
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String getLoginPage(HttpServletRequest req) {

		req.setAttribute("content", "signIn/signIn.jsp");

		return "home";
	}

	// 회원가입 진행
	@RequestMapping(value = "/userJoin", method = RequestMethod.POST)
	public String write(UserDTO n, HttpServletRequest req) {
		int rst = userService.userJoin(n, req);
		if (rst > 0) {
			req.setAttribute("MSG", "회원가입성공");
		} else {
			req.setAttribute("MSG", "회원가입실패");
		}
		req.setAttribute("content", "signIn/signIn.jsp");
		return "home";
	}

	// id 중복체크
	@RequestMapping(value = "/signUp/idChk", method = RequestMethod.POST)
	@ResponseBody
	public int idCheck(HttpServletRequest req) {
		String id = req.getParameter("mi_id");
		return userService.idCheck(id);
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		req.setAttribute("MSG", "로그아웃성공");
		req.setAttribute("content", "main/main.jsp");
		return "home";
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserDTO dto, HttpServletRequest req) {
		dto.setMi_id(req.getParameter("login_id"));
		dto.setMi_pw(req.getParameter("login_pw"));
		UserDTO user = userService.login(dto);
		if (user != null) { // 0 이상 -> 로그인 가능
			req.setAttribute("MSG", "로그인 성공! 반갑습니다!");
			req.getSession().setAttribute("loginMember", user); // 세션 등록
			req.setAttribute("content", "main/main.jsp");
			return "home";
		} else  { // 0 -> 로그인 실패
			req.setAttribute("MSG", "로그인 실패! 존재하지 않는 계정이거나 비밀번호가 틀렸습니다..!");
			req.setAttribute("content", "signIn/signIn.jsp");
			return "home";
		} 
	}
	
	// 마이 페이지 이동
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(UserDTO dto, HttpServletRequest req) {
		req.setAttribute("content", "signIn/mypage.jsp");
		return "home";
	}
	
	// 회원정보 수정
	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
	public String updateUser(UserDTO dto, HttpServletRequest req) {
		// 업데이트 할 내용 / 비번, 휴대폰, 집전화, 이메일, 주소
		int rst = userService.userUpdate(dto, req);
		
		if(rst > 0) {
			req.setAttribute("MSG", "회원정보가 수정되었습니다.");
			// -> 변경 완료된 세션의 user정보를 다시 보여주자
			// 로그인 할때 모든 정보를 불러왔으니까 login을 통해서 지금이 모든 정보를 가져오자
			// 그렇게 되면 로그인에서 확인하는 정보는 id와 pw가 있다.
			// 현재 회원정보가 수정된 상태이기 때문에 가능
			UserDTO user = userService.login(dto); // 현재 수정이 된 유저 정보를 조회
			req.getSession().setAttribute("loginMember", user); // 세션등록
			req.setAttribute("content", "signIn/mypage.jsp");
		} else {
			req.setAttribute("MSG", "바뀐 회원정보가 없습니다.");
			req.setAttribute("content", "signIn/mypage.jsp");
		}
		
		return "home"; // 1또는 0이 반환된다.
		
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(UserDTO dto, HttpServletRequest req) {
		
		int rst = userService.userDelete(dto.getMi_id(), req); // 세션의 id값만 가지고 와서 db에 넣기
		if(rst > 0) {
			req.getSession().invalidate(); // 해당 세션 날리기
			req.setAttribute("MSG", "탈퇴가 완료되었습니다. 감사합니다!");
			req.setAttribute("content", "main/main.jsp");
		}else {
			req.setAttribute("MSG", "탈퇴실패");
			req.setAttribute("content", "signIn/mypage.jsp");
		}
		
		return "home";
		
	}
	
}
