package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.ProductTransferEpcInfoDAO;
import com.alienasia.alienmw.dto.ProductTransferEpcInfoDTO;
import com.alienasia.alienmw.dto.ProductTransferEpcInfoRes;
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
@RequestMapping("/ProductTransferEpcInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class ProductTransferEpcInfoContoroller {

	@Autowired
	private ProductTransferEpcInfoDAO service;


	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public ProductTransferEpcInfoRes getList(@RequestBody ProductTransferEpcInfoDTO productTransferEpcInfoDTO) {

		System.out.println("-------------/ProductTransferEpcInfo/getList-------");
		System.out.println(productTransferEpcInfoDTO);
		System.out.println("-------------------");
		ProductTransferEpcInfoRes productTransferEpcInfoRes = new ProductTransferEpcInfoRes();
		productTransferEpcInfoRes.setProductTransferEpcInfoDTO ( service.getList(productTransferEpcInfoDTO) );
		productTransferEpcInfoRes.setResultCode(1);
		productTransferEpcInfoRes.setResultMessage("success");
		return productTransferEpcInfoRes;
	}

	@RequestMapping(value = "/getGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public ProductTransferEpcInfoRes getGroupList(@RequestBody ProductTransferEpcInfoDTO productTransferEpcInfoDTO) {

		System.out.println("-------------/ProductTransferEpcInfo/getGroupList-------");
		System.out.println(productTransferEpcInfoDTO);
		System.out.println("--------------------");
		ProductTransferEpcInfoRes productTransferEpcInfoRes = new ProductTransferEpcInfoRes();
		productTransferEpcInfoRes.setProductTransferEpcInfoDTO ( service.getGroupList(productTransferEpcInfoDTO) );
		productTransferEpcInfoRes.setResultCode(1);
		productTransferEpcInfoRes.setResultMessage("success");

		return productTransferEpcInfoRes;
	}


	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<ProductTransferEpcInfoDTO> productTransferEpcInfoDTO) {

		 System.out.println("-------------/ProductTransferEpcInfo/insert-------");
		 System.out.println("--------------------" +  productTransferEpcInfoDTO);
		 int res = 0;

		 for(int i=0 ; i< productTransferEpcInfoDTO.size()  ; i++) {
			 res = res + service.insert(productTransferEpcInfoDTO.get(i)) ;
		 }
		 
		 return res ;
	}

	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<ProductTransferEpcInfoDTO> productTransferEpcInfoDTO) {

		System.out.println("-------------/ProductTransferEpcInfo/remove-------");
		System.out.println("--------------------" +  productTransferEpcInfoDTO );
		int res = 0;



		for(int i=0 ; i< productTransferEpcInfoDTO.size()  ; i++) {
			System.out.println("--------------------" + i );
			service.productTransferInfoUpdate(productTransferEpcInfoDTO.get(i));
		}


		for(int i=0 ; i< productTransferEpcInfoDTO.size()  ; i++) {
			res = res + service.remove(productTransferEpcInfoDTO.get(i)) ;
		}


		return res ;
	}


	@RequestMapping(value = "/statusRemove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int statusRemove(@RequestBody List<ProductTransferEpcInfoDTO> productTransferEpcInfoDTO) {

		System.out.println("-------------/ProductTransferEpcInfo/remove-------");
		System.out.println("--------------------" +  productTransferEpcInfoDTO );
		int res = 0;

		for(int i=0 ; i< productTransferEpcInfoDTO.size()  ; i++) {
			res = res + service.statusRemove(productTransferEpcInfoDTO.get(i)) ;
		}

		return res ;
	}

	@RequestMapping(value = "/save", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int save(@RequestBody List<ProductTransferEpcInfoDTO> productTransferEpcInfoDTO) {

		System.out.println("-------------/ProductTransferEpcInfo/save-------");
		System.out.println("--------------------" +  productTransferEpcInfoDTO  );
		int res = 0;


		for(int i=0 ; i< productTransferEpcInfoDTO.size()  ; i++) {

			res = res + service.statusUpdate(productTransferEpcInfoDTO.get(i)) ;

		}


		for(int i=0 ; i< productTransferEpcInfoDTO.size()  ; i++) {

			service.rfidInfoStatusUpdate(productTransferEpcInfoDTO.get(i)) ;

		}

		return res ;
	}


	@RequestMapping(value = "/statusUpdate", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int statusUpdate(@RequestBody List<ProductTransferEpcInfoDTO> productTransferEpcInfoDTO) {

		System.out.println("-------------/ProductTransferEpcInfo/statusUpdate-------");
		System.out.println("--------------------" +  productTransferEpcInfoDTO  );
		int res = 0;


		for(int i=0 ; i< productTransferEpcInfoDTO.size()  ; i++) {

			res = res + service.statusUpdate(productTransferEpcInfoDTO.get(i)) ;
		}
		return res ;
	}

	@RequestMapping(value = "/storeMove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int storeMove(@RequestBody List<ProductTransferEpcInfoDTO> productTransferEpcInfoDTO) {

		System.out.println("-------------/ProductTransferEpcInfo/storeMove-------");
		System.out.println("--------------------" +  productTransferEpcInfoDTO  );
		int res = 0;


		for(int i=0 ; i< productTransferEpcInfoDTO.size()  ; i++) {
			service.statusUpdate(productTransferEpcInfoDTO.get(i));
		}

		for(int i=0 ; i< productTransferEpcInfoDTO.size()  ; i++) {

			res = res + service.storeMove(productTransferEpcInfoDTO.get(i)) ;
		}
		return res ;
	}


}
