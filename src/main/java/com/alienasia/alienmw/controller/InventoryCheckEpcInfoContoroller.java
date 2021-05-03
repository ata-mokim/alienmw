package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.InventoryCheckEpcInfoDAO;
import com.alienasia.alienmw.dto.InventoryCheckEpcInfoDTO;
import com.alienasia.alienmw.dto.InventoryCheckEpcInfoRes;
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
@RequestMapping("/InventoryCheckEpcInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class InventoryCheckEpcInfoContoroller {

	@Autowired
	private InventoryCheckEpcInfoDAO service;


	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public InventoryCheckEpcInfoRes getList(@RequestBody InventoryCheckEpcInfoDTO inventoryCheckEpcInfoDTO) {

		System.out.println("-------------/InventoryCheckEpcInfo/getList-------");
		System.out.println(inventoryCheckEpcInfoDTO);
		System.out.println("-------------------");
		InventoryCheckEpcInfoRes inventoryCheckEpcInfoRes = new InventoryCheckEpcInfoRes();
		inventoryCheckEpcInfoRes.setInventoryCheckEpcInfoDTO ( service.getList(inventoryCheckEpcInfoDTO) );
		inventoryCheckEpcInfoRes.setResultCode(1);
		inventoryCheckEpcInfoRes.setResultMessage("success");
		return inventoryCheckEpcInfoRes;
	}


	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<InventoryCheckEpcInfoDTO> inventoryCheckEpcInfoDTO) {

		 System.out.println("-------------/InventoryCheckEpcInfo/insert-------");
		 System.out.println("--------------------" +  inventoryCheckEpcInfoDTO);
		 int res = 0;

		 for(int i=0 ; i< inventoryCheckEpcInfoDTO.size()  ; i++) {
			 res = res + service.insert(inventoryCheckEpcInfoDTO.get(i)) ;
		 }
		 
		 return res ;
	}

	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<InventoryCheckEpcInfoDTO> inventoryCheckEpcInfoDTO) {

		System.out.println("-------------/InventoryCheckEpcInfo/remove-------");
		System.out.println("--------------------" +  inventoryCheckEpcInfoDTO.size() );
		int res = 0;


		for(int i=0 ; i< inventoryCheckEpcInfoDTO.size()  ; i++) {
			res = res + service.remove(inventoryCheckEpcInfoDTO.get(i)) ;
		}

		return res ;
	}

}
