package com.alienasia.alienmw.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserInfoDTO {
	
	  private Long user_seq;
	  private String reg_date;
	  private String reg_user_id;
	  private String upd_date;
	  private String upd_user_id;
	  private String user_id;
	  private String password;
	  private String password_init_yn;
	  private String check_yn;
	  private String last_login_date;
	  private String use_yn;
	  private Long company_seq;
	  private String external_yn;
	  private String last_pw_change_date;
	  private Long login_fail_count;
	  private String user_name;
	  private Long dept_seq;
	  private String accessToken;
	  private String dept_name;
	  private String address1;
	  private String address2;
}
