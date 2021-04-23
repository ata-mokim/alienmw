package com.alienasia.alienmw.dto;

import lombok.*;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoxBarcodeInfoDTO {
	  private String w_out_box_barcode;
	  private String reg_date;
	  private String reg_user_id;
	  private String upd_date;
	  private String upd_user_id;
	  private String store_code;
	  private String dept_name;

}
