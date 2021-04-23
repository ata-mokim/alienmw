package com.alienasia.alienmw.controller;

import java.util.ArrayList;
import java.util.List;

import com.alienasia.alienmw.dao.RfidInfoDAO;
import com.alienasia.alienmw.dao.RfidOrderDAO;
import com.alienasia.alienmw.dto.*;
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
@RequestMapping("/rfidInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class RfidInfoController {

	@Autowired
	private RfidInfoDAO service;

	@Autowired
	private RfidOrderDAO rfidOrderservice;

	@RequestMapping(value = "/list", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidInfoRes list(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------/rfidInfo/list-------");
		System.out.println(rfidInfoDTO);


		if ("".equals(rfidInfoDTO.getStatus()) || null == rfidInfoDTO.getStatus()) {
			rfidInfoDTO.setStatus("3");
		}
		
		
		RfidInfoRes rfidInfoRes = new RfidInfoRes();
		rfidInfoRes.setRfidInfoDTO( service.getList(rfidInfoDTO) );
		 
		rfidInfoRes.setResultCode(1);
		rfidInfoRes.setResultMessage("success");

		return rfidInfoRes;
	}
	
	@RequestMapping(value = "/read", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidInfoRes read(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------/rfidInfo/read-------");
		System.out.println(rfidInfoDTO.getRfid_tag());


		RfidInfoRes rfidInfoRes = new RfidInfoRes();
		rfidInfoRes.setRfidInfoDTO( service.read(rfidInfoDTO) );
		 
		rfidInfoRes.setResultCode(1);
		rfidInfoRes.setResultMessage("success");

		return rfidInfoRes;
	}

	@RequestMapping(value = "/search", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidInfoRes search(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------/rfidInfo/search-------");
		System.out.println(rfidInfoDTO.getRfid_tag());


		RfidInfoRes rfidInfoRes = new RfidInfoRes();
		rfidInfoRes.setRfidInfoDTO( service.search(rfidInfoDTO) );



		rfidInfoRes.setResultCode(1);
		rfidInfoRes.setResultMessage("success");

		return rfidInfoRes;
	}
	
	@RequestMapping(value = "/statusUpdate", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int statusUpdate(@RequestBody List<RfidInfoDTO> rfidInfoDTO) {

		 System.out.println("-------------/rfidInfo/statusUpdate-------");
		 System.out.println("--------------------" +  rfidInfoDTO.size() );
		 int res = 0;
			 
		 
		 for(int i=0 ; i< rfidInfoDTO.size()  ; i++) {
			 res = res + service.statusUpdate(rfidInfoDTO.get(i)) ;	
		 }
		 
		 return res ;
	}
	
	
	
	@RequestMapping(value = "/dailyValueWIn", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidInfoRes dailyValueWIn(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------/rfidInfo/dailyValueWIn-------");
		System.out.println(rfidInfoDTO);
				
		RfidInfoRes rfidInfoRes = new RfidInfoRes();
		rfidInfoRes.setRfidInfoDTO( service.dailyValueWIn(rfidInfoDTO) );

		System.out.println(rfidInfoRes.getRfidInfoDTO().get(0).getAmount() );
		 
		rfidInfoRes.setResultCode(1);
		rfidInfoRes.setResultMessage("success");

		return rfidInfoRes;
	}
	
	
	
	
	
	@RequestMapping(value = "/dailyValueWOut", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidInfoRes dailyValueWOut(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------/rfidInfo/dailyValueWOut-------");
		System.out.println(rfidInfoDTO);
				
		RfidInfoRes rfidInfoRes = new RfidInfoRes();
		rfidInfoRes.setRfidInfoDTO( service.dailyValueWOut(rfidInfoDTO) );
		 
		rfidInfoRes.setResultCode(1);
		rfidInfoRes.setResultMessage("success");

		return rfidInfoRes;
	}
	
	@RequestMapping(value = "/dailyValueSIn", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidInfoRes dailyValueSIn(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------/rfidInfo/dailyValueSIn-------");
		System.out.println(rfidInfoDTO);
				
		RfidInfoRes rfidInfoRes = new RfidInfoRes();
		rfidInfoRes.setRfidInfoDTO( service.dailyValueSIn(rfidInfoDTO) );
		

		 
		rfidInfoRes.setResultCode(1);
		rfidInfoRes.setResultMessage("success");

		return rfidInfoRes;
	}
	
	@RequestMapping(value = "/dailyValueSOut", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidInfoRes dailyValueSOut(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------/rfidInfo/dailyValueSOut-------");
		System.out.println(rfidInfoDTO);
				
		RfidInfoRes rfidInfoRes = new RfidInfoRes();
		rfidInfoRes.setRfidInfoDTO( service.dailyValueSOut(rfidInfoDTO) );
		 
		rfidInfoRes.setResultCode(1);
		rfidInfoRes.setResultMessage("success");

		return rfidInfoRes;
	}
	
	

	@RequestMapping(value = "/getStoreInGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public StoreInGroupListRes getStoreInGroupList(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------/rfidInfo/storeInGroupList-------");
		System.out.println(rfidInfoDTO);

		StoreInGroupListRes stroreInGroupListRes = new StoreInGroupListRes();

		if ("".equals(rfidInfoDTO.getStatus()) || null == rfidInfoDTO.getStatus()) {
			rfidInfoDTO.setStatus("4");
		}

		stroreInGroupListRes.setStoreInGroupListDTO(service.getStoreInGroupList(rfidInfoDTO));
		stroreInGroupListRes.setResultCode(1);
		stroreInGroupListRes.setResultMessage("success");

		return stroreInGroupListRes;
	}

	@RequestMapping(value = "/getWarehouseInOutGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public StoreInGroupListRes getWarehouseInOutGroupList(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------/rfidInfo/warehouseInGroupList-------");
		System.out.println(rfidInfoDTO);

		StoreInGroupListRes stroreInGroupListRes = new StoreInGroupListRes();

		if ("".equals(rfidInfoDTO.getStatus()) || null == rfidInfoDTO.getStatus()) {
			rfidInfoDTO.setStatus("2");
		}

		stroreInGroupListRes.setStoreInGroupListDTO(service.getWarehouseInOutGroupList(rfidInfoDTO));
		stroreInGroupListRes.setResultCode(1);
		stroreInGroupListRes.setResultMessage("success");

		System.out.println(stroreInGroupListRes);

		return stroreInGroupListRes;
	}



//	@RequestMapping(value = "/storeInventoryGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
//			MediaType.APPLICATION_ATOM_XML_VALUE })
//	public StoreInGroupListRes storeInventoryGroupList(@RequestBody StoreInGroupListReq stroreInGroupListReq) {
//
//		System.out.println("-------------/rfidInfo/storeInventoryGroupList-------");
//		System.out.println(stroreInGroupListReq);
//
//
//		// StoreInGroupListRes stroreInGroupListRes = new StoreInGroupListRes();
//
//		StoreInGroupListRes stroreInGroupListRes = new StoreInGroupListRes();
//
//		if ("".equals(stroreInGroupListReq.getStatus()) || null == stroreInGroupListReq.getStatus()) {
//			stroreInGroupListReq.setStatus("10");
//		}
//
//		if ("".equals(stroreInGroupListReq.getBrand()) || null == stroreInGroupListReq.getBrand()) {
//			stroreInGroupListRes.setResultCode(0);
//			stroreInGroupListRes.setResultMessage("fail");
//			return stroreInGroupListRes;
//		}
//
//		stroreInGroupListRes.setStoreInGroupListDTO(service.getStoreInventoryGroupList(stroreInGroupListReq));
//
//		stroreInGroupListRes.setResultCode(1);
//		stroreInGroupListRes.setResultMessage("success");
//
//		return stroreInGroupListRes;
//	}

//	@RequestMapping(value = "/statusChange", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
//			MediaType.APPLICATION_ATOM_XML_VALUE })
//	public StatusChangeRes statusChange(@RequestBody StatusChangeReq req) {
//
//		System.out.println("-------------/rfidInfo/statusChange-------");
//		System.out.println(req);
//
//		StatusChangeRes res = new StatusChangeRes();
//
//		if ("".equals(req.getStatus()) || null == req.getStatus()) {
//			res.setResultCode(0);
//			res.setResultMessage("fail");
//			return res;
//		}
//
//		for (String epc : req.getEpc()) {
//			RfidInfoDTO RfidInfoDTO = new RfidInfoDTO();
//			RfidInfoDTO.setEpc(epc);
//			RfidInfoDTO.setStatus(req.getStatus());
//			service.update(RfidInfoDTO);
//		}
//
//		res.setResultCode(1);
//		res.setResultMessage("success");
//
//		return res;
//	}

	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<RfidInfoDTO> rfidInfoDTO) {

		System.out.println("-------------/rfidInfo/insert-------");
		System.out.println("--------------------" + rfidInfoDTO.size());
		// System.out.println("--------------------" + rfidInfoDTO);
		int res = 0;
		long rfid_order_seq = 0;
		int count = 0;

		RfidOrderDTO rfidOrderDTO = new RfidOrderDTO();

		for (int i = 0; i < rfidInfoDTO.size(); i++) {

			if (rfid_order_seq != rfidInfoDTO.get(i).getRfid_order_seq()) {
				count = 0;
				if (rfid_order_seq == 0) {
					rfid_order_seq = rfidInfoDTO.get(i).getRfid_order_seq();
					count ++;
					continue;
				} else {
					rfidOrderDTO.setStatus("4");
					rfidOrderDTO.setRfid_order_seq(rfid_order_seq);
					rfidOrderDTO.setPublish_amount(Long.valueOf(i) );
					rfidOrderDTO.setUpd_user_id(rfidInfoDTO.get(i).getReg_user_id());
					rfidOrderDTO.setPublish_id(rfidInfoDTO.get(i).getReg_user_id());
					rfidOrderservice.publishComplete(rfidOrderDTO);
				}
			}
			
			count ++;
			rfid_order_seq =  rfidInfoDTO.get(i).getRfid_order_seq();

		}

			int j = rfidInfoDTO.size() / 1000;
			int k = rfidInfoDTO.size() % 1000;
			int i = 0;

			for ( i = 0; i <  j ; i++)
			{
				res = res + service.arrayInsert(rfidInfoDTO.subList( (i * 1000) , ( i * 1000 + 1000 ) ) );
			}

			System.out.println("--------------------" + j + "//" + k +"//" + i +"//");
			
			if ( k > 0)
			{
				res = res + service.arrayInsert(rfidInfoDTO.subList( (i * 1000) , ( i * 1000 + k ) ) );
			}
			
//		}
		
		rfidOrderDTO.setStatus("4");
		rfidOrderDTO.setRfid_order_seq(rfid_order_seq);
		rfidOrderDTO.setPublish_amount(Long.valueOf(count) );
		rfidOrderDTO.setUpd_user_id(rfidInfoDTO.get(i).getReg_user_id());
		rfidOrderDTO.setPublish_id(rfidInfoDTO.get(i).getReg_user_id());
		rfidOrderservice.publishComplete(rfidOrderDTO);

		return res;
	}

}
