package com.alienasia.alienmw.dto;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class LogInReq  {
	  private String user_id;
	  private String password;
}
