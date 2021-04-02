package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.UserInfoDTO;

import java.util.List;


public interface UserInfoDAO {
	public List<UserInfoDTO> getList(UserInfoDTO userInfoDTO);
	public UserInfoDTO logIn(UserInfoDTO userInfoDTO);
	
	public UserInfoDTO get(String userId);
	public int update (UserInfoDTO userInfoDTO);
	public void insert  (UserInfoDTO userInfoDTO);
	public int delete(String userId);	
	
	public int lastLogIn (UserInfoDTO userInfoDTO);
	public int logInFail (UserInfoDTO userInfoDTO);
	public int initPwd (UserInfoDTO userInfoDTO);
}


