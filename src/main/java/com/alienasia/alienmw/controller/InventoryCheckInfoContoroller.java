package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.RfidInfoDAO;
import com.alienasia.alienmw.dao.InventoryCheckInfoDAO;
import com.alienasia.alienmw.dto.InventoryCheckInfoDTO;
import com.alienasia.alienmw.dto.InventoryCheckInfoRes;
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
@RequestMapping("/InventoryCheckInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class InventoryCheckInfoContoroller {

	@Autowired
	private InventoryCheckInfoDAO service;


	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public InventoryCheckInfoRes getList(@RequestBody InventoryCheckInfoDTO inventoryCheckInfoDTO) {

		System.out.println("-------------/InventoryCheckInfo/getList-------");
		System.out.println(inventoryCheckInfoDTO);
		System.out.println("-------------------");
		InventoryCheckInfoRes inventoryCheckInfoRes = new InventoryCheckInfoRes();
		inventoryCheckInfoRes.setInventoryCheckInfoDTO ( service.getList(inventoryCheckInfoDTO) );
		inventoryCheckInfoRes.setResultCode(1);
		inventoryCheckInfoRes.setResultMessage("success");
		return inventoryCheckInfoRes;
	}

	@RequestMapping(value = "/getGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public InventoryCheckInfoRes getGroupList(@RequestBody InventoryCheckInfoDTO inventoryCheckInfoDTO) {

		System.out.println("-------------/InventoryCheckInfo/getGroupList-------");
		System.out.println(inventoryCheckInfoDTO);
		System.out.println("--------------------");
		InventoryCheckInfoRes inventoryCheckInfoRes = new InventoryCheckInfoRes();
		inventoryCheckInfoRes.setInventoryCheckInfoDTO ( service.getGroupList(inventoryCheckInfoDTO) );
		inventoryCheckInfoRes.setResultCode(1);
		inventoryCheckInfoRes.setResultMessage("success");

		return inventoryCheckInfoRes;
	}

	@RequestMapping(value = "/getGroupDetailList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public InventoryCheckInfoRes getGroupDetailList(@RequestBody InventoryCheckInfoDTO inventoryCheckInfoDTO) {

		System.out.println("-------------/InventoryCheckInfo/getGroupDetailList-------");
		System.out.println(inventoryCheckInfoDTO);
		System.out.println("--------------------");
		InventoryCheckInfoRes inventoryCheckInfoRes = new InventoryCheckInfoRes();
		inventoryCheckInfoRes.setInventoryCheckInfoDTO ( service.getGroupDetailList(inventoryCheckInfoDTO) );
		inventoryCheckInfoRes.setResultCode(1);
		inventoryCheckInfoRes.setResultMessage("success");

		return inventoryCheckInfoRes;
	}

	
	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<InventoryCheckInfoDTO> inventoryCheckInfoDTO) {

		 System.out.println("-------------/InventoryCheckInfo/insert-------");
		 System.out.println("--------------------" +  inventoryCheckInfoDTO);
		 int res = 0;
			 
		 
		 for(int i=0 ; i< inventoryCheckInfoDTO.size()  ; i++) {
			 res = res + service.insert(inventoryCheckInfoDTO.get(i)) ;
		 }
		 
		 return res ;
	}

	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<InventoryCheckInfoDTO> inventoryCheckInfoDTO) {

		System.out.println("-------------/InventoryCheckInfo/remove-------");
		System.out.println("--------------------" +  inventoryCheckInfoDTO.size() );
		int res = 0;


		for(int i=0 ; i< inventoryCheckInfoDTO.size()  ; i++) {
			res = res + service.remove(inventoryCheckInfoDTO.get(i)) ;
		}

		return res ;
	}

	@RequestMapping(value = "/update", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int update(@RequestBody List<InventoryCheckInfoDTO> inventoryCheckInfoDTO) {

		System.out.println("-------------/InventoryCheckInfo/update-------");
		System.out.println("--------------------" +  inventoryCheckInfoDTO );
		int res = 0;


		for(int i=0 ; i< inventoryCheckInfoDTO.size()  ; i++) {
			res = res + service.update(inventoryCheckInfoDTO.get(i)) ;
		}

		return res ;
	}

	

}
