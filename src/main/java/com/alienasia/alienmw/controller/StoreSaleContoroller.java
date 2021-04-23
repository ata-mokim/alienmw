package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.RfidInfoDAO;
import com.alienasia.alienmw.dao.StoreSaleInfoDAO;
import com.alienasia.alienmw.dto.StoreSaleInfoDTO;
import com.alienasia.alienmw.dto.StoreSaleInfoRes;
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
@RequestMapping("/StoreSaleInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class StoreSaleContoroller {

	@Autowired
	private StoreSaleInfoDAO service;

	@Autowired
	private RfidInfoDAO rfidInfoDaoService;


	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public StoreSaleInfoRes getList(@RequestBody StoreSaleInfoDTO storeSaleInfoDTO) {

		System.out.println("-------------/StoreSaleInfo/getList-------");
		System.out.println(storeSaleInfoDTO);
		System.out.println("-------------------");
		StoreSaleInfoRes storeSaleInfoRes = new StoreSaleInfoRes();
		storeSaleInfoRes.setStoreSaleInfoDTO ( service.getList(storeSaleInfoDTO) );
		storeSaleInfoRes.setResultCode(1);
		storeSaleInfoRes.setResultMessage("success");
		return storeSaleInfoRes;
	}

	@RequestMapping(value = "/getGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public StoreSaleInfoRes getGroupList(@RequestBody StoreSaleInfoDTO storeSaleInfoDTO) {

		System.out.println("-------------/StoreSaleInfo/getGroupList-------");
		System.out.println(storeSaleInfoDTO);
		System.out.println("--------------------");
		StoreSaleInfoRes storeSaleInfoRes = new StoreSaleInfoRes();
		storeSaleInfoRes.setStoreSaleInfoDTO ( service.getGroupList(storeSaleInfoDTO) );
		storeSaleInfoRes.setResultCode(1);
		storeSaleInfoRes.setResultMessage("success");

		return storeSaleInfoRes;
	}

	
	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<StoreSaleInfoDTO> storeSaleInfoDTO) {

		 System.out.println("-------------/StoreSaleInfo/insert-------");
		 System.out.println("--------------------" +  storeSaleInfoDTO);
		 int res = 0;
			 
		 
		 for(int i=0 ; i< storeSaleInfoDTO.size()  ; i++) {
			 res = res + service.insert(storeSaleInfoDTO.get(i)) ;
		 }
		 
		 return res ;
	}

	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<StoreSaleInfoDTO> storeSaleInfoDTO) {

		System.out.println("-------------/StoreSaleInfo/remove-------");
		System.out.println("--------------------" +  storeSaleInfoDTO );
		int res = 0;


		for(int i=0 ; i< storeSaleInfoDTO.size()  ; i++) {
			res = res + service.remove(storeSaleInfoDTO.get(i)) ;
		}

		return res ;
	}


	@RequestMapping(value = "/storeSale", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int storeIn(@RequestBody List<StoreSaleInfoDTO> storeSaleInfoDTO) {

		System.out.println("-------------/StoreSaleInfo/storeIn-------");
		System.out.println("--------------------" +  storeSaleInfoDTO.size() );
		int res = 0;


		for(int i=0 ; i< storeSaleInfoDTO.size()  ; i++) {

			service.rfidInfoStatusUpdate(storeSaleInfoDTO.get(i)) ;
		}

		for(int i=0 ; i< storeSaleInfoDTO.size()  ; i++) {
			res = res + service.remove(storeSaleInfoDTO.get(i)) ;
		}

		return res ;
	}
	

}
