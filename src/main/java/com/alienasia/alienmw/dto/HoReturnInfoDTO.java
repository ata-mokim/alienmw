package com.alienasia.alienmw.dto;

import lombok.*;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HoReturnInfoDTO {
	  private Long ho_return_info_seq;
	  private String reg_date;
	  private String reg_user_id;
	  private String upd_date;
	  private String upd_user_id;
	  private Long ho_return_num;
	  private String ho_return_name;
	  private Long store_code;
	  private String store_name;
	  private String style;
	  private String color;	  
	  private String size;
	  private String order_degree;
	  private Long amount;
	  private Long check_amount;
	  private String request_date;
	  private String check_date;
	  private String request_start_date;
	  private String request_end_date;
	  private String check_start_date;
	  private String check_end_date;
	  private Long total_count;
}
