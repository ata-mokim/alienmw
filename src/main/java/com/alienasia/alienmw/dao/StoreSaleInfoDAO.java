package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.StoreSaleInfoDTO;

import java.util.List;


public interface StoreSaleInfoDAO {
	public int insert (StoreSaleInfoDTO storeSaleInfoDTO);
	public int remove (StoreSaleInfoDTO storeSaleInfoDTO);
	public int rfidInfoStatusUpdate (StoreSaleInfoDTO storeSaleInfoDTO);
	public List<StoreSaleInfoDTO> getList(StoreSaleInfoDTO storeSaleInfoDTO);
	public List<StoreSaleInfoDTO> getGroupList(StoreSaleInfoDTO storeSaleInfoDTO);
}
