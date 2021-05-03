package com.alienasia.alienmw.dto;

import lombok.*;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HoReturnEpcInfoDTO {
	  private Long ho_return_epc_info_seq;
	  private String reg_date;
	  private String reg_user_id;
	  private String upd_date;
	  private String upd_user_id;
	  private String barcode;
	  private Long ho_return_info_seq;
	  private Long ho_return_num;
	  private String epc;
	  private String style;
	  private String color;
	  private String size;
	  private String order_degree;
	  private Long store_code;
	  private String check_date;
	  private Long amount;
	  private String status;
}
