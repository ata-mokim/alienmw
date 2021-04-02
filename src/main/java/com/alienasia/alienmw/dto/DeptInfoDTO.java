package com.alienasia.alienmw.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class DeptInfoDTO {
	
	  private Long dept_seq;
	  private String reg_date;
	  private String reg_user_id;
	  private String upd_date;
	  private String upd_user_id;
	  private String address1;
	  private String address2;
	  private String business_code;
	  private Long company_seq;
	  private String country;
	  private String country_code;
	  private String production_code;
	  private String dept_code;
	  private Long dept_create_seq;
	  private String division;
	  private String location_type;
	  private String manager;
	  private String manager_tel_no;
	  private String name;
	  private String tel_no;
	  private String type;
	  private String use_yn;
	  private String zip_code;
}
