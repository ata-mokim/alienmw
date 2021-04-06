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
public class RfidOrderDTO {
	  private Long rfid_order_seq;
	  private String reg_date;
	  private String reg_user_id;
	  private String upd_date;
	  private String upd_user_id;
	  private String style;
	  private String color;	  
	  private String size;
	  private String order_degree;
	  private String brand;
	  private Long product_amount;
	  private Long rfid_order_amount;
	  private Long publish_start_number;
	  private Long publish_end_number;
	  private Long rfid_take_amount;
	  private String request_date;
	  private Long publish_amount;
	  private String confirm_date;
	  private String publish_date;
	  private String take_date;
	  private String dept_name;
	  private String dept_seq;
	  private String status;
	  private String address1;
	  private String address2;
	  private Long rfid_get_order_seq;
	  private String request_start_date;
	  private String request_end_date;
	  private String confirm_start_date;
	  private String confirm_end_date;
	  private String publish_start_date;
	  private String publish_end_date;
	  private String publish_id;
	  private String confirm_id;
}
