package com.alienasia.alienmw.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CommonAPIResult {
	
    private int resultCode;
    private String resultMessage;

}
