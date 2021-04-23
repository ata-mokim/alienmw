package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.ProductionOutInfoDAO;
import com.alienasia.alienmw.dao.RfidInfoDAO;
import com.alienasia.alienmw.dao.WarehouseInInfoDAO;
import com.alienasia.alienmw.dto.WarehouseInInfoDTO;
import com.alienasia.alienmw.dto.ProductionOutInfoRes;
import com.alienasia.alienmw.dto.WarehouseInInfoRes;
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
@RequestMapping("/WarehouseInInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class WarehouseInContoroller {

	@Autowired
	private WarehouseInInfoDAO service;

	@Autowired
	private RfidInfoDAO rfidInfoDaoService;


	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public WarehouseInInfoRes getList(@RequestBody WarehouseInInfoDTO warehouseInInfoDTO) {

		System.out.println("-------------/WarehouseInInfo/getList-------");
		System.out.println(warehouseInInfoDTO);
		System.out.println("-------------------");
		WarehouseInInfoRes warehouseInInfoRes = new WarehouseInInfoRes();
		warehouseInInfoRes.setWarehouseInInfoDTO ( service.getList(warehouseInInfoDTO) );
		warehouseInInfoRes.setResultCode(1);
		warehouseInInfoRes.setResultMessage("success");
		return warehouseInInfoRes;
	}

	@RequestMapping(value = "/getGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public WarehouseInInfoRes getGroupList(@RequestBody WarehouseInInfoDTO warehouseInInfoDTO) {

		System.out.println("-------------/WarehouseInInfo/getGroupList-------");
		System.out.println(warehouseInInfoDTO);
		System.out.println("--------------------");
		WarehouseInInfoRes warehouseInInfoRes = new WarehouseInInfoRes();
		warehouseInInfoRes.setWarehouseInInfoDTO ( service.getGroupList(warehouseInInfoDTO) );
		warehouseInInfoRes.setResultCode(1);
		warehouseInInfoRes.setResultMessage("success");

		return warehouseInInfoRes;
	}

	
	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<WarehouseInInfoDTO> warehouseInInfoDTO) {

		 System.out.println("-------------/WarehouseInInfo/insert-------");
		 System.out.println("--------------------" +  warehouseInInfoDTO.size() );
		 int res = 0;
			 
		 
		 for(int i=0 ; i< warehouseInInfoDTO.size()  ; i++) {
			 res = res + service.insert(warehouseInInfoDTO.get(i)) ;
		 }
		 
		 return res ;
	}

	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<WarehouseInInfoDTO> warehouseInInfoDTO) {

		System.out.println("-------------/WarehouseInInfo/remove-------");
		System.out.println("--------------------" +  warehouseInInfoDTO.size() );
		int res = 0;


		for(int i=0 ; i< warehouseInInfoDTO.size()  ; i++) {
			res = res + service.remove(warehouseInInfoDTO.get(i)) ;
		}

		return res ;
	}


	@RequestMapping(value = "/warehouseIn", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int warehouseIn(@RequestBody List<WarehouseInInfoDTO> warehouseInInfoDTO) {

		System.out.println("-------------/WarehouseInInfo/warehouseIn-------");
		System.out.println("--------------------" +  warehouseInInfoDTO.size() );
		int res = 0;


		for(int i=0 ; i< warehouseInInfoDTO.size()  ; i++) {

			service.rfidInfoStatusUpdate(warehouseInInfoDTO.get(i)) ;
		}

		for(int i=0 ; i< warehouseInInfoDTO.size()  ; i++) {
			res = res + service.remove(warehouseInInfoDTO.get(i)) ;
		}

		return res ;
	}
	

}
