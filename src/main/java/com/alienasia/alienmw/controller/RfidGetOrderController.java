package com.alienasia.alienmw.controller;

import java.util.ArrayList;
import java.util.List;

import com.alienasia.alienmw.dao.RfidGetOrderDAO;
import com.alienasia.alienmw.dao.RfidOrderDAO;
import com.alienasia.alienmw.dto.RfidGetOrderDTO;
import com.alienasia.alienmw.dto.RfidGetOrderRes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import lombok.AllArgsConstructor;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/rfidGetOrder/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class RfidGetOrderController {
	
	@Autowired
	private RfidGetOrderDAO service;
	@Autowired
	private RfidOrderDAO rfidOrderService;
	
	
	 @RequestMapping(value = "/list",  produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,	MediaType.APPLICATION_ATOM_XML_VALUE}  )
	 public RfidGetOrderRes list(@RequestBody RfidGetOrderDTO rfidGetOrderDTO) {

		 System.out.println("-------------/rfidGetOrder/list-------");
		 System.out.println("--------------------" +  rfidGetOrderDTO );
		 System.out.println("--------------------" );
		 
		 RfidGetOrderRes rfidGetOrderRes = new RfidGetOrderRes();
		 rfidGetOrderRes.setRfidGetOrderDto(service.getList(rfidGetOrderDTO) );
		 
		 rfidGetOrderRes.setResultCode(1);
		 rfidGetOrderRes.setResultMessage("success");
		 
		 return rfidGetOrderRes   ;
	 }
	 
	 
	 @RequestMapping(value = "/rfidOrderReserve",produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,	MediaType.APPLICATION_ATOM_XML_VALUE}  )
	 public int rfidOrderReserve(@RequestBody List<RfidGetOrderDTO> rfidGetOrderDTO) {

		 System.out.println("-------------/rfidGetOrder/rfidOrderReserve-------");
		 System.out.println("--------------------" +  rfidGetOrderDTO.size() );
		 System.out.println("--------------------" +  rfidGetOrderDTO);
//		 System.out.println("--------------------" +  user_id);
		 int res = 0;
			 
		 
		 for(int i=0 ; i< rfidGetOrderDTO.size()  ; i++) {
			 res = res + rfidOrderService.insert(rfidGetOrderDTO.get(i)) ;
			 service.rfidOrderReserve(rfidGetOrderDTO.get(i));
		 }
		 
		 return res ;
	 }
	 

	 
}
