package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.RfidInfoDAO;
import com.alienasia.alienmw.dao.StoreInInfoDAO;
import com.alienasia.alienmw.dto.StoreInInfoDTO;
import com.alienasia.alienmw.dto.StoreInInfoRes;
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
@RequestMapping("/StoreInInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class StoreInContoroller {

	@Autowired
	private StoreInInfoDAO service;

	@Autowired
	private RfidInfoDAO rfidInfoDaoService;


	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public StoreInInfoRes getList(@RequestBody StoreInInfoDTO storeInInfoDTO) {

		System.out.println("-------------/StoreInInfo/getList-------");
		System.out.println(storeInInfoDTO);
		System.out.println("-------------------");
		StoreInInfoRes storeInInfoRes = new StoreInInfoRes();
		storeInInfoRes.setStoreInInfoDTO ( service.getList(storeInInfoDTO) );
		storeInInfoRes.setResultCode(1);
		storeInInfoRes.setResultMessage("success");
		return storeInInfoRes;
	}

	@RequestMapping(value = "/getGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public StoreInInfoRes getGroupList(@RequestBody StoreInInfoDTO storeInInfoDTO) {

		System.out.println("-------------/StoreInInfo/getGroupList-------");
		System.out.println(storeInInfoDTO);
		System.out.println("--------------------");
		StoreInInfoRes storeInInfoRes = new StoreInInfoRes();
		storeInInfoRes.setStoreInInfoDTO ( service.getGroupList(storeInInfoDTO) );
		storeInInfoRes.setResultCode(1);
		storeInInfoRes.setResultMessage("success");

		return storeInInfoRes;
	}

	
	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<StoreInInfoDTO> storeInInfoDTO) {

		 System.out.println("-------------/StoreInInfo/insert-------");
		 System.out.println("--------------------" +  storeInInfoDTO);
		 int res = 0;
			 
		 
		 for(int i=0 ; i< storeInInfoDTO.size()  ; i++) {
			 res = res + service.insert(storeInInfoDTO.get(i)) ;
		 }
		 
		 return res ;
	}

	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<StoreInInfoDTO> storeInInfoDTO) {

		System.out.println("-------------/StoreInInfo/remove-------");
		System.out.println("--------------------" +  storeInInfoDTO.size() );
		int res = 0;


		for(int i=0 ; i< storeInInfoDTO.size()  ; i++) {
			res = res + service.remove(storeInInfoDTO.get(i)) ;
		}

		return res ;
	}


	@RequestMapping(value = "/storeIn", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int storeIn(@RequestBody List<StoreInInfoDTO> storeInInfoDTO) {

		System.out.println("-------------/StoreInInfo/storeIn-------");
		System.out.println("--------------------" +  storeInInfoDTO.size() );
		int res = 0;


		for(int i=0 ; i< storeInInfoDTO.size()  ; i++) {

			service.rfidInfoStatusUpdate(storeInInfoDTO.get(i)) ;
		}

		for(int i=0 ; i< storeInInfoDTO.size()  ; i++) {
			res = res + service.remove(storeInInfoDTO.get(i)) ;
		}

		return res ;
	}
	

}
