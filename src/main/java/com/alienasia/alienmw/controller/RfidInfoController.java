package com.alienasia.alienmw.controller;

import java.util.ArrayList;
import java.util.List;

import com.alienasia.alienmw.dao.RfidInfoDAO;
import com.alienasia.alienmw.dao.RfidOrderDAO;
import com.alienasia.alienmw.dto.*;
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
public class RfidInfoController {

	@Autowired
	private RfidInfoDAO service;

	@Autowired
	private RfidOrderDAO rfidOrderservice;

	@RequestMapping(value = "/list", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidInfoRes list(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------list-------");
		System.out.println(rfidInfoDTO.getStatus());
		System.out.println("-------------list-------");

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

		System.out.println("-------------list-------");
		System.out.println(rfidInfoDTO.getEpc());
		System.out.println("-------------list-------");

		RfidInfoRes rfidInfoRes = new RfidInfoRes();
		rfidInfoRes.setRfidInfoDTO( service.read(rfidInfoDTO) );
		 
		rfidInfoRes.setResultCode(1);
		rfidInfoRes.setResultMessage("success");

		return rfidInfoRes;
	}
	
	@RequestMapping(value = "/statusUpdate", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int statusUpdate(@RequestBody List<RfidInfoDTO> rfidInfoDTO) {

		 System.out.println("-------------modify-------");
		 System.out.println("--------------------" +  rfidInfoDTO.size() );
		 int res = 0;
			 
		 
		 for(int i=0 ; i< rfidInfoDTO.size()  ; i++) {  
			 
			 System.out.println("--------------------" +  rfidInfoDTO.get(i).getStatus() );
			 System.out.println("--------------------" +  rfidInfoDTO.get(i).getEpc() );
			 
			 res = res + service.statusUpdate(rfidInfoDTO.get(i)) ;	
		 }
		 
		 return res ;
	}
	
	
	
	@RequestMapping(value = "/dailyValueWIn", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidInfoRes dailyValueWIn(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------list-------");
		System.out.println(rfidInfoDTO);
				
		RfidInfoRes rfidInfoRes = new RfidInfoRes();
		rfidInfoRes.setRfidInfoDTO( service.dailyValueWIn(rfidInfoDTO) );
		
		System.out.println("-------------list-------");
		System.out.println(rfidInfoRes.getRfidInfoDTO().get(0).getAmount() );
		 
		rfidInfoRes.setResultCode(1);
		rfidInfoRes.setResultMessage("success");

		return rfidInfoRes;
	}
	
	
	
	
	
	@RequestMapping(value = "/dailyValueWOut", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidInfoRes dailyValueWOut(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------list-------");
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

		System.out.println("-------------list-------");
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

		System.out.println("-------------list-------");
		System.out.println(rfidInfoDTO);
				
		RfidInfoRes rfidInfoRes = new RfidInfoRes();
		rfidInfoRes.setRfidInfoDTO( service.dailyValueSOut(rfidInfoDTO) );
		 
		rfidInfoRes.setResultCode(1);
		rfidInfoRes.setResultMessage("success");

		return rfidInfoRes;
	}
	
	

	@RequestMapping(value = "/storeInGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public StoreInGroupListRes storeInGroupList(@RequestBody RfidInfoDTO rfidInfoDTO) {

		System.out.println("-------------storeInList-------");
		System.out.println(rfidInfoDTO);
		System.out.println("-------------storeInList-------");

		// StoreInGroupListRes stroreInGroupListRes = new StoreInGroupListRes();

		StoreInGroupListRes stroreInGroupListRes = new StoreInGroupListRes();

		if ("".equals(rfidInfoDTO.getStatus()) || null == rfidInfoDTO.getStatus()) {
			rfidInfoDTO.setStatus("8");
		}

//		if ("".equals(stroreInGroupListReq.getBrand()) || null == stroreInGroupListReq.getBrand()) {
//			stroreInGroupListRes.setResultCode(0);
//			stroreInGroupListRes.setResultMessage("fail");
//			return stroreInGroupListRes;
//		}

		stroreInGroupListRes.setStoreInGroupListDTO(service.getStoreInGroupList(rfidInfoDTO));

		stroreInGroupListRes.setResultCode(1);
		stroreInGroupListRes.setResultMessage("success");

		return stroreInGroupListRes;
	}

	@RequestMapping(value = "/storeInventoryGroupList", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public StoreInGroupListRes storeInventoryGroupList(@RequestBody StoreInGroupListReq stroreInGroupListReq) {

		System.out.println("-------------storeInList-------");
		System.out.println(stroreInGroupListReq);
		System.out.println("-------------storeInList-------");

		// StoreInGroupListRes stroreInGroupListRes = new StoreInGroupListRes();

		StoreInGroupListRes stroreInGroupListRes = new StoreInGroupListRes();

		if ("".equals(stroreInGroupListReq.getStatus()) || null == stroreInGroupListReq.getStatus()) {
			stroreInGroupListReq.setStatus("10");
		}

		if ("".equals(stroreInGroupListReq.getBrand()) || null == stroreInGroupListReq.getBrand()) {
			stroreInGroupListRes.setResultCode(0);
			stroreInGroupListRes.setResultMessage("fail");
			return stroreInGroupListRes;
		}

		stroreInGroupListRes.setStoreInGroupListDTO(service.getStoreInventoryGroupList(stroreInGroupListReq));

		stroreInGroupListRes.setResultCode(1);
		stroreInGroupListRes.setResultMessage("success");

		return stroreInGroupListRes;
	}

	@RequestMapping(value = "/statusChange", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public StatusChangeRes statusChange(@RequestBody StatusChangeReq req) {

		System.out.println("-------------storeInList-------");
		System.out.println(req);
		System.out.println("-------------storeInList-------");

		StatusChangeRes res = new StatusChangeRes();

		if ("".equals(req.getStatus()) || null == req.getStatus()) {
			res.setResultCode(0);
			res.setResultMessage("fail");
			return res;
		}

		for (String epc : req.getEpc()) {
			RfidInfoDTO RfidInfoDTO = new RfidInfoDTO();
			RfidInfoDTO.setEpc(epc);
			RfidInfoDTO.setStatus(req.getStatus());
			service.update(RfidInfoDTO);
		}

		res.setResultCode(1);
		res.setResultMessage("success");

		return res;
	}

	@RequestMapping(value = "/insert", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int insert(@RequestBody List<RfidInfoDTO> rfidInfoDTO) {

		System.out.println("-------------modify-------");
		System.out.println("--------------------" + rfidInfoDTO.size());
		// System.out.println("--------------------" + rfidInfoDTO);
		int res = 0;
		long rfid_order_seq = 0;
		int count = 0;

		RfidOrderDTO rfidOrderDTO = new RfidOrderDTO();

		for (int i = 0; i < rfidInfoDTO.size(); i++) {

//			res = res + service.insert(rfidInfoDTO.get(i));

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
		
//		if( 1000 <  rfidInfoDTO.size() )
//		{
//			service.arrayInsert(rfidInfoDTO);
//		}else {
		
			int j = rfidInfoDTO.size() / 1000;
			int k = rfidInfoDTO.size() % 1000;
			int i = 0;
			
			
			for ( i = 0; i <  j ; i++)
			{
				System.out.println("----------start----------" + i );
				System.out.println("----------end ----------" + (i * 1000)  + "/" + ( i * 1000 + 1000 )  );
				res = res + service.arrayInsert(rfidInfoDTO.subList( (i * 1000) , ( i * 1000 + 1000 ) ) );

			}
			
			
			if ( k > 0)
			{
				res = res + service.arrayInsert(rfidInfoDTO.subList( (i * 1000) , ( i * 1000 + k ) ) );
			}
			
			System.out.println("----------res ----------" + res );
			
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
