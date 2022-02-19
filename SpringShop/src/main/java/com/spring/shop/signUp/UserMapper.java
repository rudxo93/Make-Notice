package com.spring.shop.signUp;

public interface UserMapper {
	// 회원가입
	public abstract int userJoin(UserDTO n);
	// 아이디 중복확인
	public abstract int idCheck(String id); // 0 또는 1
	// 로그인 구현
	public abstract UserDTO login(UserDTO dto);
	// 마이페이지 
	public abstract UserDTO mypage(String id);
	// 유저 정보 업데이트
	public abstract int userUpdate(UserDTO dto);
	// 유저 정보 삭제
	public abstract int userDelete(String id);
}
