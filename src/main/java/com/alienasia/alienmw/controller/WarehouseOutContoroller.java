package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.RfidInfoDAO;
import com.alienasia.alienmw.dao.WarehouseOutInfoDAO;
import com.alienasia.alienmw.dto.WarehouseOutInfoDTO;
import com.alienasia.alienmw.dto.WarehouseOutInfoRes;
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
@RequestMapping("/WarehouseOutInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class WarehouseOutContoroller {

	@Autowired
	private WarehouseOutInfoDAO service;

	@Autowired
	private RfidInfoDAO rfidInfoDaoService;


	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public WarehouseOutInfoRes getList(@RequestBody WarehouseOutInfoDTO warehouseOutInfoDTO) {

		System.out.println("-------------/WarehouseOutInfo/getList-------");
		System.out.println(warehouseOutInfoDTO);
		System.out.println("-------------------");
		WarehouseOutInfoRes warehouseOutInfoRes = new WarehouseOutInfoRes();
		warehouseOutInfoRes.setWarehouseOutInfoDTO ( service.getList(warehouseOutInfoDTO) );
		warehouseOutInfoRes.setResultCode(1);
		warehouseOutInfoRes.setResultMessage("success");
		return warehouseOutInfoRes;
	}

	@RequestMapping(value = "/getGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public WarehouseOutInfoRes getGroupList(@RequestBody WarehouseOutInfoDTO warehouseOutInfoDTO) {

		System.out.println("-------------/WarehouseOutInfo/getGroupList-------");
		System.out.println(warehouseOutInfoDTO);
		System.out.println("--------------------");
		WarehouseOutInfoRes warehouseOutInfoRes = new WarehouseOutInfoRes();
		warehouseOutInfoRes.setWarehouseOutInfoDTO ( service.getGroupList(warehouseOutInfoDTO) );
		warehouseOutInfoRes.setResultCode(1);
		warehouseOutInfoRes.setResultMessage("success");

		return warehouseOutInfoRes;
	}

	
	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<WarehouseOutInfoDTO> warehouseOutInfoDTO) {

		 System.out.println("-------------/WarehouseOutInfo/insert-------");
		 System.out.println("--------------------" +  warehouseOutInfoDTO.size() );
		 int res = 0;
			 
		 
		 for(int i=0 ; i< warehouseOutInfoDTO.size()  ; i++) {
			 res = res + service.insert(warehouseOutInfoDTO.get(i)) ;
		 }
		 
		 return res ;
	}

	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<WarehouseOutInfoDTO> warehouseOutInfoDTO) {

		System.out.println("-------------/WarehouseOutInfo/remove-------");
		System.out.println("--------------------" +  warehouseOutInfoDTO.size() );
		int res = 0;


		for(int i=0 ; i< warehouseOutInfoDTO.size()  ; i++) {
			res = res + service.remove(warehouseOutInfoDTO.get(i)) ;
		}

		return res ;
	}


	@RequestMapping(value = "/warehouseOut", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int warehouseOut(@RequestBody List<WarehouseOutInfoDTO> warehouseOutInfoDTO) {

		System.out.println("-------------/WarehouseOutInfo/warehouseOut-------");
		System.out.println("--------------------" +  warehouseOutInfoDTO);
		int res = 0;


		for(int i=0 ; i< warehouseOutInfoDTO.size()  ; i++) {

			service.rfidInfoStatusUpdate(warehouseOutInfoDTO.get(i)) ;
		}

		for(int i=0 ; i< warehouseOutInfoDTO.size()  ; i++) {
			res = res + service.remove(warehouseOutInfoDTO.get(i)) ;
		}

		return res ;
	}
	

}
