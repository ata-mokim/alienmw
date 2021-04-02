package com.alienasia.alienmw.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class DailyValueRes extends CommonAPIResult {
	@Autowired
	private List<RfidOrderDTO> rfidOrderDTO;
}
