package com.alienasia.alienmw.dto;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RfidInfoDTO {
	  private String epc;
	  private String rfid_tag;
	  private String rfid_tag_tid;
	  private String reg_date;
	  private String reg_user_id;
	  private String upd_date;
	  private String upd_user_id;
	  private Long rfid_order_seq;
	  private Long rfid_get_order_seq;	 
	  private String style;
	  private String color;
	  private String size;
	  private String order_degree;
	  private String publish_seq;
	  private String brand;  
	  private String status;
	  private String create_date;
	  private String production_in_date;
	  private String production_out_date;
	  private String w_in_date;
	  private String w_out_date;	
	  private String w_return_date;
	  private String w_in_box_barcode;	  
	  private String w_in_specs;	  
	  private String w_out_box_barcode;	  
	  private String w_out_specs;	  	  
	  private String store_code;	  
	  private String use_yn;	  
	  private String w_in_start_date;
	  private String w_in_end_date;
	  private String w_out_start_date;
	  private String w_out_end_date;
	  private String w_return_start_date;
	  private String w_return_end_date;
	  
	  private String store_in_date;
	  private String store_sale_date;	
	  private String store_return_date;
	  
	  private String store_in_start_date;
	  private String store_in_end_date;
	  private String store_sale_start_date;
	  private String store_sale_end_date;
	  private String store_return_start_date;
	  private String store_return_end_date;
	  private Long amount;
	  private Long dept_seq;
	  private String dept_name;
	  private String store_name;
}
