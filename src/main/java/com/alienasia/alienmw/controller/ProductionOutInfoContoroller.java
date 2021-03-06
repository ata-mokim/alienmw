package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.ProductionOutInfoDAO;
import com.alienasia.alienmw.dao.RfidInfoDAO;
import com.alienasia.alienmw.dto.ProductionOutInfoDTO;
import com.alienasia.alienmw.dto.ProductionOutInfoRes;
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
@RequestMapping("/ProductionOutInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class ProductionOutInfoContoroller {

	@Autowired
	private ProductionOutInfoDAO service;

	@Autowired
	private RfidInfoDAO rfidInfoDaoService;


	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public ProductionOutInfoRes getList(@RequestBody ProductionOutInfoDTO productionOutInfoDTO) {

		System.out.println("-------------/ProductionOutInfo/getList-------");
		System.out.println(productionOutInfoDTO);
		System.out.println("-------------------");

		ProductionOutInfoRes productionOutInfoRes = new ProductionOutInfoRes();
		productionOutInfoRes.setProductionOutInfoDTO ( service.getList(productionOutInfoDTO) );

		productionOutInfoRes.setResultCode(1);
		productionOutInfoRes.setResultMessage("success");

		return productionOutInfoRes;
	}

	@RequestMapping(value = "/getGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public ProductionOutInfoRes getGroupList(@RequestBody ProductionOutInfoDTO productionOutInfoDTO) {

		System.out.println("-------------/ProductionOutInfo/getGroupList-------");
		System.out.println(productionOutInfoDTO);
		System.out.println("--------------------");

		ProductionOutInfoRes productionOutInfoRes = new ProductionOutInfoRes();
		productionOutInfoRes.setProductionOutInfoDTO ( service.getGroupList(productionOutInfoDTO) );

		productionOutInfoRes.setResultCode(1);
		productionOutInfoRes.setResultMessage("success");

		return productionOutInfoRes;
	}

	
	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<ProductionOutInfoDTO> productionOutInfoDTO) {

		 System.out.println("-------------/ProductionOutInfo/insert--------");
		 System.out.println("--------------------" +  productionOutInfoDTO );
		 int res = 0;
			 
		 
		 for(int i=0 ; i< productionOutInfoDTO.size()  ; i++) {
			 res = res + service.insert(productionOutInfoDTO.get(i)) ;
		 }
		 
		 return res ;
	}

	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<ProductionOutInfoDTO> productionOutInfoDTO) {

		System.out.println("-------------/ProductionOutInfo/remove-------");
		System.out.println("--------------------" +  productionOutInfoDTO );
		int res = 0;


		for(int i=0 ; i< productionOutInfoDTO.size()  ; i++) {
			res = res + service.remove(productionOutInfoDTO.get(i)) ;
		}

		return res ;
	}


	@RequestMapping(value = "/productionOut", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int productionOut(@RequestBody List<ProductionOutInfoDTO> productionOutInfoDTO) {

		System.out.println("-------------/ProductionOutInfo/productionOut-------");
		System.out.println("--------------------" +  productionOutInfoDTO.size() );
		int res = 0;


		for(int i=0 ; i< productionOutInfoDTO.size()  ; i++) {

			service.rfidInfoStatusUpdate(productionOutInfoDTO.get(i)) ;
		}

		for(int i=0 ; i< productionOutInfoDTO.size()  ; i++) {
			res = res + service.remove(productionOutInfoDTO.get(i)) ;
		}

		return res ;
	}
	

}
