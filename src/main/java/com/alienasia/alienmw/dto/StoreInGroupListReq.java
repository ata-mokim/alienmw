package com.alienasia.alienmw.dto;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class StoreInGroupListReq {

	private String status;
	private String storeCode;
	private String brand;
	private String startDate;
	private String endDate;

}
