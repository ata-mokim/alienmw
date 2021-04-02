package com.alienasia.alienmw.dto;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class LogInRes extends CommonAPIResult {
	  private String user_id;
	  private String password;
	  private String accessToken;
	  private Long dept_seq;
	  private String dept_name;
	  private String address1;
	  private String address2;
}
