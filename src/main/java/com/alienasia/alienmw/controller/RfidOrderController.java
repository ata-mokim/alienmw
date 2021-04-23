package com.alienasia.alienmw.controller;

import java.util.List;

import com.alienasia.alienmw.dao.RfidGetOrderDAO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/rfidOrder/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class RfidOrderController {

	@Autowired
	private RfidOrderDAO service;
	@Autowired
	private RfidGetOrderDAO rfidGetOrderService;
	@Autowired
	private RfidInfoDAO rfidInfoService;

	@RequestMapping(value = "/list", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidOrderRes list(@RequestBody RfidOrderDTO rfidOrderDTO) {

		System.out.println("-------------/rfidOrder/list-------");
		System.out.println(rfidOrderDTO);

		RfidOrderRes rfidOrderRes = new RfidOrderRes();
		rfidOrderRes.setRfidOrderDTO(service.getList(rfidOrderDTO));

		rfidOrderRes.setResultCode(1);
		rfidOrderRes.setResultMessage("success");

		return rfidOrderRes;

	}

	@RequestMapping(value = "/revComplete", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int revComplete(@RequestBody List<RfidOrderDTO> rfidOrderDTO) {

		System.out.println("-------------/rfidOrder/revComplete-------");
		System.out.println("--------------------" + rfidOrderDTO);
		RfidGetOrderDTO rfidGetOrderDTO = new RfidGetOrderDTO();
		int res = 0;

		for (int i = 0; i < rfidOrderDTO.size(); i++) {

			// rfidGetOrderDTO.getrfid_order_confirm_amount()
			rfidGetOrderDTO = rfidGetOrderService.read(rfidOrderDTO.get(i).getRfid_get_order_seq());

			rfidOrderDTO.get(i).setStatus("1");
			rfidOrderDTO.get(i).setPublish_start_number(rfidGetOrderDTO.getRfid_order_confirm_amount() + 1);
			rfidOrderDTO.get(i).setPublish_end_number(
			rfidOrderDTO.get(i).getRfid_order_amount() + rfidGetOrderDTO.getRfid_order_confirm_amount());
			res = res + service.revComplete(rfidOrderDTO.get(i));

			rfidGetOrderDTO.setRfid_get_order_seq(rfidOrderDTO.get(i).getRfid_get_order_seq());
			rfidGetOrderDTO.setRfid_order_amount(rfidOrderDTO.get(i).getRfid_order_amount());
			//rfidGetOrderDTO.setRfid_complete_yn("Y");
			System.out.println("--------------------" + rfidGetOrderDTO);
			rfidGetOrderService.revComplete(rfidGetOrderDTO);
		}
		return res;
	}

	@RequestMapping(value = "/rfidReceipt", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int rfidReceipt(@RequestBody List<RfidOrderDTO> rfidOrderDTO) {

		System.out.println("-------------/rfidOrder/rfidReceipt-------");
		System.out.println("--------------------" + rfidOrderDTO);

		int res = 0;

		for (int i = 0; i < rfidOrderDTO.size(); i++) {

			rfidOrderDTO.get(i).setStatus("2");
			res = res + service.statusUpdate(rfidOrderDTO.get(i));

		}
		return res;
	}

	@RequestMapping(value = "/rfidPublishStart", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int rfidPublishStart(@RequestBody List<RfidOrderDTO> rfidOrderDTO) {

		System.out.println("-------------/rfidOrder/rfidPublishStart-------");
		System.out.println("--------------------" + rfidOrderDTO.size());
		System.out.println("--------------------" + rfidOrderDTO);

		int res = 0;

		for (int i = 0; i < rfidOrderDTO.size(); i++) {

			rfidOrderDTO.get(i).setStatus("3");
			res = res + service.statusUpdate(rfidOrderDTO.get(i));

		}
		return res;
	}


	@RequestMapping(value = "/rfidTakeComplete", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int rfidTakeComplete(@RequestBody List<RfidOrderDTO> rfidOrderDTO) {

		System.out.println("-------------/rfidOrder/rfidTakeComplete-------");
		System.out.println("--------------------" + rfidOrderDTO.size());
		System.out.println("--------------------" + rfidOrderDTO);

		int res = 0;
		RfidGetOrderDTO rfidGetOrderDTO = new RfidGetOrderDTO();

		for (int i = 0; i < rfidOrderDTO.size(); i++) {
			rfidOrderDTO.get(i).setStatus("5");
			res = res + service.rfidTake(rfidOrderDTO.get(i));
			//  rfidGetOrder update
			rfidGetOrderDTO.setRfid_get_order_seq(rfidOrderDTO.get(i).getRfid_get_order_seq());
			rfidGetOrderDTO.setRfid_take_amount(rfidOrderDTO.get(i).getRfid_take_amount());
			rfidGetOrderService.rfidTake(rfidGetOrderDTO);
			// rfidInfo update
			RfidInfoDTO rfidInfoDTO = new RfidInfoDTO();
			rfidInfoDTO.setUpd_user_id(rfidOrderDTO.get(i).getUpd_user_id());
			rfidInfoDTO.setStatus("1");
			rfidInfoDTO.setRfid_order_seq(rfidOrderDTO.get(i).getRfid_order_seq());
			rfidInfoService.productionInUpdate(rfidInfoDTO) ;
		}
		return res;
	}

	@RequestMapping(value = "/rfidTakeEpcComplete", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int rfidTakeEpcComplete(@RequestBody RfidTakeEpcCompleteRes rfidTakeEpcCompleteRes ){

		System.out.println("-------------/rfidOrder/rfidTakeEpcComplete-------");
		System.out.println("--------------------" + rfidTakeEpcCompleteRes.getRfidOrderDTO().size());
		System.out.println("--------------------" + rfidTakeEpcCompleteRes.getRfidOrderDTO());


		int res = 0;
		RfidGetOrderDTO rfidGetOrderDTO = new RfidGetOrderDTO();

		for (int i = 0; i < rfidTakeEpcCompleteRes.getRfidOrderDTO().size(); i++) {
			rfidTakeEpcCompleteRes.getRfidOrderDTO().get(i).setStatus("5");
			res = res + service.rfidTake(rfidTakeEpcCompleteRes.getRfidOrderDTO().get(i));
			//  update
			rfidGetOrderDTO.setRfid_get_order_seq(rfidTakeEpcCompleteRes.getRfidOrderDTO().get(i).getRfid_get_order_seq());
			rfidGetOrderDTO.setRfid_take_amount(rfidTakeEpcCompleteRes.getRfidOrderDTO().get(i).getRfid_take_amount());
			rfidGetOrderService.rfidTake(rfidGetOrderDTO);
		}

		for (int i = 0; i < rfidTakeEpcCompleteRes.getRfidInfoDTO().size(); i++) {
			rfidTakeEpcCompleteRes.getRfidInfoDTO().get(i).setStatus("1");
			rfidInfoService.statusUpdate(rfidTakeEpcCompleteRes.getRfidInfoDTO().get(i));
		}

		return res;
	}


	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<RfidOrderDTO> rfidOrderDTO) {

		System.out.println("-------------/rfidOrder/remove-------");
		System.out.println("--------------------" + rfidOrderDTO.size());
		System.out.println("--------------------" + rfidOrderDTO);

		int res = 0;

		for (int i = 0; i < rfidOrderDTO.size(); i++) {
			res = res + service.remove(rfidOrderDTO.get(i).getRfid_order_seq());
		}
		return res;
	}
	
	@RequestMapping(value = "/dailyValue", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public DailyValueRes dailyValue(@RequestBody RfidOrderDTO rfidOrderDTO) {

		System.out.println("-------------/rfidOrder/dailyValue-------");
		System.out.println("--------------------" + rfidOrderDTO);
		
		DailyValueRes dailyValueRes = new DailyValueRes();
		dailyValueRes.setRfidOrderDTO(service.dailyValue(rfidOrderDTO));
		dailyValueRes.setResultCode(1);
		dailyValueRes.setResultMessage("success");
		
		return dailyValueRes;
	}
	
	
	
}
