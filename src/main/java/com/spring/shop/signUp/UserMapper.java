package com.spring.shop.signUp;

public interface UserMapper {
	// ȸ������
	public abstract int userJoin(UserDTO n);
	// ���̵� �ߺ�Ȯ��
	public abstract int idCheck(String id); // 0 �Ǵ� 1
	// �α��� ����
	public abstract UserDTO login(UserDTO dto);
	// ���������� 
	public abstract UserDTO mypage(String id);
	// ���� ���� ������Ʈ
	public abstract int userUpdate(UserDTO dto);
	// ���� ���� ����
	public abstract int userDelete(String id);
}
