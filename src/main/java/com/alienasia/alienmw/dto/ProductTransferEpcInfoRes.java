package com.alienasia.alienmw.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@Setter
@Getter
public class ProductTransferEpcInfoRes extends CommonAPIResult {
	@Autowired
	private List<ProductTransferEpcInfoDTO> productTransferEpcInfoDTO;
}
