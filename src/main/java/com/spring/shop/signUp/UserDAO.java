package com.spring.shop.signUp;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	@Autowired
	SqlSession ss;

	public int userJoin(UserDTO n) {
		return ss.getMapper(UserMapper.class).userJoin(n);
	}

	public int idCheck(String id) {
		return ss.getMapper(UserMapper.class).idCheck(id); // 0 ¶Ç´Â 1
	}

	public UserDTO login(UserDTO dto) {
		return ss.getMapper(UserMapper.class).login(dto);
	}

	public UserDTO mypage(String id) {
		return ss.getMapper(UserMapper.class).mypage(id);
	}

	public int userUpdate(UserDTO dto) {
	 	return ss.getMapper(UserMapper.class).userUpdate(dto);
	}

	public int userDelete(String id) {
		return ss.getMapper(UserMapper.class).userDelete(id);
	}

}
