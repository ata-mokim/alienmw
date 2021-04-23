package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.BoxBarcodeInfoDTO;
import com.alienasia.alienmw.dto.RfidInfoDTO;
import com.alienasia.alienmw.dto.StoreInGroupListDTO;
import com.alienasia.alienmw.dto.StoreInGroupListReq;

import java.util.List;


public interface BoxBarcodeInfoDAO {
	public List<BoxBarcodeInfoDTO> read(BoxBarcodeInfoDTO boxBarcodeInfoDTO);
}
