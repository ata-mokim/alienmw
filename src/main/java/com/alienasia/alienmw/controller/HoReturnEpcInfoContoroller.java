package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.HoReturnEpcInfoDAO;
import com.alienasia.alienmw.dto.*;
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
@RequestMapping("/HoReturnEpcInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class HoReturnEpcInfoContoroller {

	@Autowired
	private HoReturnEpcInfoDAO service;


	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public HoReturnEpcInfoRes getList(@RequestBody HoReturnEpcInfoDTO hoReturnEpcInfoDTO) {

		System.out.println("-------------/HoReturnEpcInfo/getList-------");
		System.out.println(hoReturnEpcInfoDTO);
		System.out.println("-------------------");
		HoReturnEpcInfoRes hoReturnEpcInfoRes = new HoReturnEpcInfoRes();
		hoReturnEpcInfoRes.setHoReturnEpcInfoDTO ( service.getList(hoReturnEpcInfoDTO) );
		hoReturnEpcInfoRes.setResultCode(1);
		hoReturnEpcInfoRes.setResultMessage("success");
		return hoReturnEpcInfoRes;
	}

	@RequestMapping(value = "/getGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public HoReturnEpcInfoRes getGroupList(@RequestBody HoReturnEpcInfoDTO hoReturnEpcInfoDTO) {

		System.out.println("-------------/HoReturnEpcInfo/getGroupList-------");
		System.out.println(hoReturnEpcInfoDTO);
		System.out.println("--------------------");
		HoReturnEpcInfoRes hoReturnEpcInfoRes = new HoReturnEpcInfoRes();
		hoReturnEpcInfoRes.setHoReturnEpcInfoDTO ( service.getGroupList(hoReturnEpcInfoDTO) );
		hoReturnEpcInfoRes.setResultCode(1);
		hoReturnEpcInfoRes.setResultMessage("success");

		return hoReturnEpcInfoRes;
	}


	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<HoReturnEpcInfoDTO> hoReturnEpcInfoDTO) {

		 System.out.println("-------------/HoReturnEpcInfo/insert-------");
		 System.out.println("--------------------" +  hoReturnEpcInfoDTO);
		 int res = 0;

		 for(int i=0 ; i< hoReturnEpcInfoDTO.size()  ; i++) {
			 res = res + service.insert(hoReturnEpcInfoDTO.get(i)) ;
		 }
		 
		 return res ;
	}

	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<HoReturnEpcInfoDTO> hoReturnEpcInfoDTO) {

		System.out.println("-------------/HoReturnEpcInfo/remove-------");
		System.out.println("--------------------" +  hoReturnEpcInfoDTO );
		int res = 0;


		for(int i=0 ; i< hoReturnEpcInfoDTO.size()  ; i++) {
			service.hoReturnInfoUpdate(hoReturnEpcInfoDTO.get(i)) ;

		}
		for(int i=0 ; i< hoReturnEpcInfoDTO.size()  ; i++) {
			res = res + service.remove(hoReturnEpcInfoDTO.get(i)) ;
		}
		return res ;
	}


	@RequestMapping(value = "/returnOut", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int returnOut(@RequestBody List<HoReturnEpcInfoDTO> hoReturnEpcInfoDTO) {

		System.out.println("-------------/HoReturnEpcInfo/returnOut-------");
		System.out.println("--------------------" +  hoReturnEpcInfoDTO  );
		int res = 0;


		for(int i=0 ; i< hoReturnEpcInfoDTO.size()  ; i++) {

			service.rfidInfoStatusUpdate(hoReturnEpcInfoDTO.get(i)) ;
			res = res + service.statusUpdate(hoReturnEpcInfoDTO.get(i)) ;
		}

		return res ;
	}


	@RequestMapping(value = "/statusUpdate", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int statusUpdate(@RequestBody List<HoReturnEpcInfoDTO> hoReturnEpcInfoDTO) {

		System.out.println("-------------/HoReturnEpcInfo/statusUpdate-------");
		System.out.println("--------------------" +  hoReturnEpcInfoDTO  );
		int res = 0;


		for(int i=0 ; i< hoReturnEpcInfoDTO.size()  ; i++) {

//			service.rfidInfoStatusUpdate(hoReturnEpcInfoDTO.get(i)) ;
			res = res + service.statusUpdate(hoReturnEpcInfoDTO.get(i)) ;
		}

		return res ;
	}


	@RequestMapping(value = "/statusRemove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int statusRemove(@RequestBody List<HoReturnEpcInfoDTO> hoReturnEpcInfoDTO) {

		System.out.println("-------------/HoReturnEpcInfo/statusRemove-------");
		System.out.println("--------------------" +  hoReturnEpcInfoDTO );
		int res = 0;

		for(int i=0 ; i< hoReturnEpcInfoDTO.size()  ; i++) {
			res = res + service.statusRemove(hoReturnEpcInfoDTO.get(i)) ;
		}

		return res ;
	}


	@RequestMapping(value = "/returnWarehouse", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int returnWarehouse(@RequestBody List<HoReturnEpcInfoDTO> hoReturnEpcInfoDTO) {

		System.out.println("-------------/HoReturnEpcInfo/returnOut-------");
		System.out.println("--------------------" +  hoReturnEpcInfoDTO  );
		int res = 0;


		for(int i=0 ; i< hoReturnEpcInfoDTO.size()  ; i++) {

			service.returnWarehouse(hoReturnEpcInfoDTO.get(i)) ;
			res = res + service.statusUpdate(hoReturnEpcInfoDTO.get(i)) ;
		}

		return res ;
	}

}
