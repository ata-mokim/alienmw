package com.alienasia.alienmw.controller;

import java.util.List;

import com.alienasia.alienmw.dao.RfidGetOrderDAO;
import com.alienasia.alienmw.dao.RfidOrderDAO;
import com.alienasia.alienmw.dto.DailyValueRes;
import com.alienasia.alienmw.dto.RfidGetOrderDTO;
import com.alienasia.alienmw.dto.RfidOrderDTO;
import com.alienasia.alienmw.dto.RfidOrderRes;
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
public class RfidOrderController {

	@Autowired
	private RfidOrderDAO service;
	@Autowired
	private RfidGetOrderDAO rfidGetOrderService;

	@RequestMapping(value = "/list", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public RfidOrderRes list(@RequestBody RfidOrderDTO rfidOrderDTO) {

		System.out.println("-------------list-------");
		System.out.println(rfidOrderDTO.getStatus());
		System.out.println("-------------list-------");

//		if ("".equals(rfidOrderDTO.getStatus()) || null == rfidOrderDTO.getStatus()) {
//			rfidOrderDTO.setStatus("0");
//		}

		RfidOrderRes rfidOrderRes = new RfidOrderRes();
		rfidOrderRes.setRfidOrderDTO(service.getList(rfidOrderDTO));

		rfidOrderRes.setResultCode(1);
		rfidOrderRes.setResultMessage("success");

		return rfidOrderRes;

	}

	@RequestMapping(value = "/revComplete", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int revComplete(@RequestBody List<RfidOrderDTO> rfidOrderDTO) {

		System.out.println("-------------modify-------");
		System.out.println("--------------------" + rfidOrderDTO.size());
		System.out.println("--------------------" + rfidOrderDTO);
		RfidGetOrderDTO rfidGetOrderDTO = new RfidGetOrderDTO();
		int res = 0;

		for (int i = 0; i < rfidOrderDTO.size(); i++) {

			// rfidGetOrderDTO.getrfid_order_confirm_amount() ���� ������������
			rfidGetOrderDTO = rfidGetOrderService.read(rfidOrderDTO.get(i).getRfid_get_order_seq());

			// rfidOrder �� �� ����
			rfidOrderDTO.get(i).setStatus("1");
			rfidOrderDTO.get(i).setPublish_start_number(rfidGetOrderDTO.getRfid_order_confirm_amount() + 1);
			rfidOrderDTO.get(i).setPublish_end_number(
			rfidOrderDTO.get(i).getRfid_order_amount() + rfidGetOrderDTO.getRfid_order_confirm_amount());
			// rfidOrder ����
			res = res + service.revComplete(rfidOrderDTO.get(i));

			rfidGetOrderDTO.setRfid_get_order_seq(rfidOrderDTO.get(i).getRfid_get_order_seq());
			rfidGetOrderDTO.setRfid_order_amount(rfidOrderDTO.get(i).getRfid_order_amount());
			//rfidGetOrderDTO.setRfid_complete_yn("Y");
			System.out.println("--------------------" + rfidGetOrderDTO);
			// rfidGetOrder ����
			rfidGetOrderService.revComplete(rfidGetOrderDTO);
		}
		return res;
	}

	@RequestMapping(value = "/rfidReceipt", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int rfidReceipt(@RequestBody List<RfidOrderDTO> rfidOrderDTO) {

		System.out.println("-------------modify-------");
		System.out.println("--------------------" + rfidOrderDTO.size());
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

		System.out.println("-------------modify-------");
		System.out.println("--------------------" + rfidOrderDTO.size());
		System.out.println("--------------------" + rfidOrderDTO);

		int res = 0;

		for (int i = 0; i < rfidOrderDTO.size(); i++) {

			rfidOrderDTO.get(i).setStatus("3");
			res = res + service.statusUpdate(rfidOrderDTO.get(i));

		}
		return res;
	}

//	@RequestMapping(value = "/rfidPublishComplete", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
//			MediaType.APPLICATION_ATOM_XML_VALUE })
//	public int rfidPublishComplete(@RequestBody List<RfidOrderDTO> rfidOrderDTO) {
//
//		System.out.println("-------------modify-------");
//		System.out.println("--------------------" + rfidOrderDTO.size());
//		System.out.println("--------------------" + rfidOrderDTO);
//		int res = 0;
//
//		for (int i = 0; i < rfidOrderDTO.size(); i++) {
//
//			rfidOrderDTO.get(i).setStatus("4");
//			res = res + service.statusUpdate(rfidOrderDTO.get(i));
//
//		}
//		return res;
//	}

	@RequestMapping(value = "/rfidTakeComplete", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int rfidTakeComplete(@RequestBody List<RfidOrderDTO> rfidOrderDTO) {

		System.out.println("-------------modify-------");
		System.out.println("--------------------" + rfidOrderDTO.size());
		System.out.println("--------------------" + rfidOrderDTO);

		int res = 0;
		RfidGetOrderDTO rfidGetOrderDTO = new RfidGetOrderDTO();

		for (int i = 0; i < rfidOrderDTO.size(); i++) {

			rfidOrderDTO.get(i).setStatus("5");
			res = res + service.rfidTake(rfidOrderDTO.get(i));

			// �μ����� update
			rfidGetOrderDTO.setRfid_get_order_seq(rfidOrderDTO.get(i).getRfid_get_order_seq());
			rfidGetOrderDTO.setRfid_take_amount(rfidOrderDTO.get(i).getRfid_take_amount());
			rfidGetOrderService.rfidTake(rfidGetOrderDTO);
		}
		return res;
	}

	@RequestMapping(value = "/remove", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public int remove(@RequestBody List<RfidOrderDTO> rfidOrderDTO) {

		System.out.println("-------------modify-------");
		System.out.println("--------------------" + rfidOrderDTO.size());
		System.out.println("--------------------" + rfidOrderDTO);

		int res = 0;

		for (int i = 0; i < rfidOrderDTO.size(); i++) {
			res = res + service.delete(rfidOrderDTO.get(i).getRfid_order_seq());
		}
		return res;
	}
	
	@RequestMapping(value = "/dailyValue", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
			MediaType.APPLICATION_ATOM_XML_VALUE })
	public DailyValueRes dailyValue(@RequestBody RfidOrderDTO rfidOrderDTO) {

		System.out.println("-------------modify-------");
		System.out.println("--------------------" + rfidOrderDTO);
		
		DailyValueRes dailyValueRes = new DailyValueRes();
		dailyValueRes.setRfidOrderDTO(service.dailyValue(rfidOrderDTO));
		dailyValueRes.setResultCode(1);
		dailyValueRes.setResultMessage("success");
		
		return dailyValueRes;
	}
	
	
	
}
