package com.alienasia.alienmw.dto;

import lombok.*;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseInInfoDTO {
	  private Long warehouse_in_info_seq;
	  private Long seq;
	  private String reg_date;
	  private String reg_user_id;
	  private String upd_date;
	  private String upd_user_id;
	  private Long dept_seq;
	  private String w_in_box_barcode;
	  private String epc;
	  private String rfid_tag;
	  private String style;
	  private String color;
	  private String size;
	  private String order_degree;
	  private Long   amount;
}
