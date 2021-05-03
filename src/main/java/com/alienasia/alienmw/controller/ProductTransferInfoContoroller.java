package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.ProductTransferEpcInfoDAO;
import com.alienasia.alienmw.dao.ProductTransferInfoDAO;
import com.alienasia.alienmw.dto.ProductTransferEpcInfoReq;
import com.alienasia.alienmw.dto.ProductTransferInfoDTO;
import com.alienasia.alienmw.dto.ProductTransferInfoRes;
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
@RequestMapping("/ProductTransferInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class ProductTransferInfoContoroller {

	@Autowired
	private ProductTransferInfoDAO service;

	@Autowired
	private ProductTransferEpcInfoDAO epcService;


	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public ProductTransferInfoRes getList(@RequestBody ProductTransferInfoDTO productTransferInfoDTO) {

		System.out.println("-------------/ProductTransferInfo/getList-------");
		System.out.println(productTransferInfoDTO);
		System.out.println("-------------------");
		ProductTransferInfoRes productTransferInfoRes = new ProductTransferInfoRes();
		productTransferInfoRes.setProductTransferInfoDTO ( service.getList(productTransferInfoDTO) );
		productTransferInfoRes.setResultCode(1);
		productTransferInfoRes.setResultMessage("success");
		return productTransferInfoRes;
	}

	@RequestMapping(value = "/getGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public ProductTransferInfoRes getGroupList(@RequestBody ProductTransferInfoDTO productTransferInfoDTO) {

		System.out.println("-------------/ProductTransferInfo/getGroupList-------");
		System.out.println(productTransferInfoDTO);
		System.out.println("--------------------");
		ProductTransferInfoRes productTransferInfoRes = new ProductTransferInfoRes();
		productTransferInfoRes.setProductTransferInfoDTO ( service.getGroupList(productTransferInfoDTO) );
		productTransferInfoRes.setResultCode(1);
		productTransferInfoRes.setResultMessage("success");

		return productTransferInfoRes;
	}

	@RequestMapping(value = "/getGroupDetailList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public ProductTransferInfoRes getGroupDetailList(@RequestBody ProductTransferInfoDTO productTransferInfoDTO) {

		System.out.println("-------------/ProductTransferInfo/getGroupDetailList-------");
		System.out.println(productTransferInfoDTO);
		System.out.println("--------------------");
		ProductTransferInfoRes productTransferInfoRes = new ProductTransferInfoRes();
		productTransferInfoRes.setProductTransferInfoDTO ( service.getGroupDetailList(productTransferInfoDTO) );
		productTransferInfoRes.setResultCode(1);
		productTransferInfoRes.setResultMessage("success");

		return productTransferInfoRes;
	}


	
	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<ProductTransferInfoDTO> productTransferInfoDTO) {

		 System.out.println("-------------/ProductTransferInfo/insert-------");
		 System.out.println("--------------------" +  productTransferInfoDTO);
		 int res = 0;


		 for(int i=0 ; i< productTransferInfoDTO.size()  ; i++) {
			 res = res + service.insert(productTransferInfoDTO.get(i)) ;
		 }

		 return res ;
	}




	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<ProductTransferInfoDTO> productTransferInfoDTO) {

		System.out.println("-------------/ProductTransferInfo/remove-------");
		System.out.println("--------------------" +  productTransferInfoDTO );
		int res = 0;


		for(int i=0 ; i< productTransferInfoDTO.size()  ; i++) {
			res = res + service.remove(productTransferInfoDTO.get(i)) ;
		}

		return res ;
	}

	@RequestMapping(value = "/update", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int update(@RequestBody ProductTransferEpcInfoReq productTransferEpcInfoReq) {

		System.out.println("-------------/ProductTransferInfo/insert-------");
		System.out.println("--------------------" +  productTransferEpcInfoReq.getProductTransferEpcInfoDTO());
		System.out.println("--------------------" +  productTransferEpcInfoReq.getProductTransferInfoDTO());

		int res = 0;

		for(int i=0 ; i< productTransferEpcInfoReq.getProductTransferEpcInfoDTO().size()  ; i++) {
			res = res + epcService.insert(productTransferEpcInfoReq.getProductTransferEpcInfoDTO().get(i)) ;
		}

		for(int i=0 ; i< productTransferEpcInfoReq.getProductTransferInfoDTO().size()  ; i++) {
			 service.update(productTransferEpcInfoReq.getProductTransferInfoDTO().get(i)) ;
		}

		return res ;
	}

	

}
