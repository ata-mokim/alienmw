package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.RePublishInfoDAO;
import com.alienasia.alienmw.dao.RfidInfoDAO;
import com.alienasia.alienmw.dao.RePublishInfoDAO;
import com.alienasia.alienmw.dto.RePublishInfoDTO;
import com.alienasia.alienmw.dto.RePublishInfoRes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/RePublishInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class RePublishInfoContoroller {

	@Autowired
	private RePublishInfoDAO service;
	

	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RePublishInfoRes getList(@RequestBody RePublishInfoDTO rePublishInfoDTO) {

		System.out.println("-------------/RePublishInfo/getList-------");
		System.out.println(rePublishInfoDTO);
		System.out.println("-------------------");
		RePublishInfoRes rePublishInfoRes = new RePublishInfoRes();
		rePublishInfoRes.setRePublishInfoDTO ( service.getList(rePublishInfoDTO) );
		rePublishInfoRes.setResultCode(1);
		rePublishInfoRes.setResultMessage("success");
		return rePublishInfoRes;
	}

	
	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<RePublishInfoDTO> rePublishInfoDTO) {

		 System.out.println("-------------/RePublishInfo/insert-------");
		 System.out.println("--------------------" +  rePublishInfoDTO);
		 int res = 0;

		 for(int i=0 ; i< rePublishInfoDTO.size()  ; i++) {
			 res = res + service.insert(rePublishInfoDTO.get(i)) ;
		 }
		 
		 return res ;
	}

	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<RePublishInfoDTO> rePublishInfoDTO) {

		System.out.println("-------------/RePublishInfo/remove-------");
		System.out.println("--------------------" +  rePublishInfoDTO);
		int res = 0;

		for(int i=0 ; i< rePublishInfoDTO.size()  ; i++) {
			res = res + service.remove(rePublishInfoDTO.get(i)) ;
		}
		return res ;
	}

	@RequestMapping(value = "/statusUpdate", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int statusUpdate(@RequestBody List<RePublishInfoDTO> rePublishInfoDTO) {

		System.out.println("-------------/RePublishInfo/statusUpdate-------");
		System.out.println("--------------------" +  rePublishInfoDTO.size() );
		int res = 0;
		for(int i=0 ; i< rePublishInfoDTO.size()  ; i++) {
			res = res + service.statusUpdate(rePublishInfoDTO.get(i)) ;
		}
		return res ;
	}

	

}
