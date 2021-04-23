package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.StoreInInfoDTO;
import com.alienasia.alienmw.dto.WarehouseInInfoDTO;

import java.util.List;


public interface StoreInInfoDAO {
	public int insert (StoreInInfoDTO storeInInfoDTO);
	public int remove (StoreInInfoDTO storeInInfoDTO);
	public int rfidInfoStatusUpdate (StoreInInfoDTO storeInInfoDTO);
	public List<StoreInInfoDTO> getList(StoreInInfoDTO storeInInfoDTO);
	public List<StoreInInfoDTO> getGroupList(StoreInInfoDTO storeInInfoDTO);
}
