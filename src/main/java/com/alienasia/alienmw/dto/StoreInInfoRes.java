package com.alienasia.alienmw.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@Setter
@Getter
public class StoreInInfoRes extends CommonAPIResult {
	@Autowired
	private List<StoreInInfoDTO> storeInInfoDTO;
}
