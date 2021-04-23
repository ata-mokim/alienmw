package com.alienasia.alienmw.dto;

import lombok.*;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RePublishInfoDTO {
	  private Long re_publish_info_seq;
	  private String reg_date;
	  private String reg_user_id;
	  private String upd_date;
	  private String upd_user_id;
	  private String epc;
	  private String style;
	  private String color;	  
	  private String size;
	  private String order_degree;
	  private String publish_seq;
	  private String dept_name;
	  private Long dept_seq;
	  private String status;
	  private String address1;
	  private String address2;
	  private String request_date;
	  private String publish_date;
}
