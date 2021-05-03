package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.HoReturnEpcInfoDAO;
import com.alienasia.alienmw.dao.HoReturnInfoDAO;
import com.alienasia.alienmw.dto.HoReturnEpcInfoReq;
import com.alienasia.alienmw.dto.HoReturnInfoDTO;
import com.alienasia.alienmw.dto.HoReturnInfoRes;
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
@RequestMapping("/HoReturnInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class HoReturnInfoContoroller {

	@Autowired
	private HoReturnInfoDAO service;

	@Autowired
	private HoReturnEpcInfoDAO epcService;


	@RequestMapping(value = "/getList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public HoReturnInfoRes getList(@RequestBody HoReturnInfoDTO hoReturnInfoDTO) {

		System.out.println("-------------/HoReturnInfo/getList-------");
		System.out.println(hoReturnInfoDTO);
		System.out.println("-------------------");
		HoReturnInfoRes hoReturnInfoRes = new HoReturnInfoRes();
		hoReturnInfoRes.setHoReturnInfoDTO ( service.getList(hoReturnInfoDTO) );
		hoReturnInfoRes.setResultCode(1);
		hoReturnInfoRes.setResultMessage("success");
		return hoReturnInfoRes;
	}

	@RequestMapping(value = "/getGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public HoReturnInfoRes getGroupList(@RequestBody HoReturnInfoDTO hoReturnInfoDTO) {

		System.out.println("-------------/HoReturnInfo/getGroupList-------");
		System.out.println(hoReturnInfoDTO);
		System.out.println("--------------------");
		HoReturnInfoRes hoReturnInfoRes = new HoReturnInfoRes();
		hoReturnInfoRes.setHoReturnInfoDTO ( service.getGroupList(hoReturnInfoDTO) );
		hoReturnInfoRes.setResultCode(1);
		hoReturnInfoRes.setResultMessage("success");

		return hoReturnInfoRes;
	}

	@RequestMapping(value = "/getGroupDetailList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public HoReturnInfoRes getGroupDetailList(@RequestBody HoReturnInfoDTO hoReturnInfoDTO) {

		System.out.println("-------------/HoReturnInfo/getGroupDetailList-------");
		System.out.println(hoReturnInfoDTO);
		System.out.println("--------------------");
		HoReturnInfoRes hoReturnInfoRes = new HoReturnInfoRes();
		hoReturnInfoRes.setHoReturnInfoDTO ( service.getGroupDetailList(hoReturnInfoDTO) );
		hoReturnInfoRes.setResultCode(1);
		hoReturnInfoRes.setResultMessage("success");

		return hoReturnInfoRes;
	}


	
	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<HoReturnInfoDTO> hoReturnInfoDTO) {

		 System.out.println("-------------/HoReturnInfo/insert-------");
		 System.out.println("--------------------" +  hoReturnInfoDTO);
		 int res = 0;


		 for(int i=0 ; i< hoReturnInfoDTO.size()  ; i++) {
			 res = res + service.insert(hoReturnInfoDTO.get(i)) ;
		 }

		 return res ;
	}




	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<HoReturnInfoDTO> hoReturnInfoDTO) {

		System.out.println("-------------/HoReturnInfo/remove-------");
		System.out.println("--------------------" +  hoReturnInfoDTO.size() );
		int res = 0;


		for(int i=0 ; i< hoReturnInfoDTO.size()  ; i++) {
			res = res + service.remove(hoReturnInfoDTO.get(i)) ;
		}

		return res ;
	}

	@RequestMapping(value = "/update", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int update(@RequestBody HoReturnEpcInfoReq hoReturnEpcInfoReq) {

		System.out.println("-------------/HoReturnInfo/insert-------");
		System.out.println("--------------------" +  hoReturnEpcInfoReq.getHoReturnEpcInfoDTO());
		System.out.println("--------------------" +  hoReturnEpcInfoReq.getHoReturnInfoDTO());

		int res = 0;

		for(int i=0 ; i< hoReturnEpcInfoReq.getHoReturnEpcInfoDTO().size()  ; i++) {
			res = res + epcService.insert(hoReturnEpcInfoReq.getHoReturnEpcInfoDTO().get(i)) ;
		}

		for(int i=0 ; i< hoReturnEpcInfoReq.getHoReturnInfoDTO().size()  ; i++) {
			 service.update(hoReturnEpcInfoReq.getHoReturnInfoDTO().get(i)) ;
		}

		return res ;
	}

	

}
