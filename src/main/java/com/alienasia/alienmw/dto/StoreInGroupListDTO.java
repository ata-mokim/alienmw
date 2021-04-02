package com.alienasia.alienmw.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.alienasia.alienmw.dto.CommonAPIResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class StoreInGroupListDTO extends CommonAPIResult {
		
	  private String store_code;
	  private String style;
	  private String color;
	  private String size;
	  private String order_degree;
	  private Integer count;
	  
	
}
