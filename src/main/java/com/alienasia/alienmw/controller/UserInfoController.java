package com.alienasia.alienmw.controller;

import java.util.ArrayList;
import java.util.List;

import com.alienasia.alienmw.dao.UserInfoDAO;
import com.alienasia.alienmw.dto.LogInReq;
import com.alienasia.alienmw.dto.LogInRes;
import com.alienasia.alienmw.dto.UserInfoDTO;
import com.alienasia.alienmw.dto.UserInfoRes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import lombok.AllArgsConstructor;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/UserInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class UserInfoController {

	@Autowired
	private UserInfoDAO service;

	@RequestMapping(value = "/logIn", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public LogInRes logIn(@RequestBody LogInReq reqLogInDTO) {

		System.out.println("-------------/UserInfo/logIn-------");
		System.out.println(reqLogInDTO);
		
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setUser_id(reqLogInDTO.getUser_id());
		userInfoDTO.setPassword(reqLogInDTO.getPassword());

		userInfoDTO = service.logIn(userInfoDTO);
		
		LogInRes logInRes = new LogInRes();
		
		if (userInfoDTO != null )
		{
			logInRes.setPassword(userInfoDTO.getPassword() );
			logInRes.setUser_id(userInfoDTO.getUser_id() );
			logInRes.setAccessToken(userInfoDTO.getAccessToken());
			logInRes.setDept_seq(userInfoDTO.getDept_seq() );
			logInRes.setDept_name(userInfoDTO.getDept_name() );
			logInRes.setAddress1( userInfoDTO.getAddress1() );
			logInRes.setAddress2( userInfoDTO.getAddress2() );
			logInRes.setResultCode(1);
			logInRes.setResultMessage("success"); //
			service.lastLogIn(userInfoDTO);
			
		}else {
			logInRes.setResultCode(0);
			logInRes.setResultMessage("fail");
			
			UserInfoDTO  tmpUserInfoDTO = new UserInfoDTO();
			tmpUserInfoDTO.setUser_id(reqLogInDTO.getUser_id());			
			service.logInFail(tmpUserInfoDTO); // ���н� �α�Ų ���� ī��Ʈ ���� 
		}
		return logInRes;
	}
	
	
	 @RequestMapping(value = "/list",  produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,	MediaType.APPLICATION_ATOM_XML_VALUE}  )
	 public UserInfoRes list(@RequestBody UserInfoDTO userInfoDTO) {

		 System.out.println("-------------/UserInfo/list-------");
		 System.out.println("--------------------" +  userInfoDTO );
		 System.out.println("--------------------" );
		 
		 UserInfoRes userInfoRes = new UserInfoRes();
		 userInfoRes.setUserInfoDTO( service.getList(userInfoDTO) );
		 
		 userInfoRes.setResultCode(1);
		 userInfoRes.setResultMessage("success");
		 
		 return userInfoRes   ;
	 }
	 
	@RequestMapping(value = "/update", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int update(@RequestBody List<UserInfoDTO> userInfoDTO) {
		System.out.println("-------------/UserInfo/update-------");
		System.out.println("--------------------" + userInfoDTO.size());
		System.out.println("--------------------" + userInfoDTO);
		int res = 0;

		for (int i = 0; i < userInfoDTO.size(); i++) {

			res = res + service.update(userInfoDTO.get(i));
		}
		return res;
	}
	 
	 
	@RequestMapping(value = "/initPwd", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int initPwd(@RequestBody List<UserInfoDTO> userInfoDTO) {
		System.out.println("-------------/UserInfo/initPwd-------");
		System.out.println("--------------------" + userInfoDTO.size());
		System.out.println("--------------------" + userInfoDTO);
		int res = 0;

		for (int i = 0; i < userInfoDTO.size(); i++) {

			res = res + service.initPwd(userInfoDTO.get(i));
		}
		return res;
	}


}
