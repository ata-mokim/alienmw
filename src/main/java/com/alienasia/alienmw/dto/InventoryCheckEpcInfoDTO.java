package com.alienasia.alienmw.dto;

import lombok.*;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCheckEpcInfoDTO {
	  private Long inventory_check_epc_info_seq;
	  private String reg_date;
	  private String reg_user_id;
	  private String upd_date;
	  private String upd_user_id;
	  private Long inventory_check_info_seq;
	  private Long inventory_check_num;
	  private String epc;
	  private String check_date;
}
