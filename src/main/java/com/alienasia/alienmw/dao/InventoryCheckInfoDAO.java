package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.InventoryCheckInfoDTO;

import java.util.List;


public interface InventoryCheckInfoDAO {
	public List<InventoryCheckInfoDTO> getList(InventoryCheckInfoDTO inventoryCheckInfoDTO);
	public List<InventoryCheckInfoDTO> getGroupList(InventoryCheckInfoDTO inventoryCheckInfoDTO);
	public List<InventoryCheckInfoDTO> getGroupDetailList(InventoryCheckInfoDTO inventoryCheckInfoDTO);
	public int insert (InventoryCheckInfoDTO inventoryCheckInfoDTO);
	public int remove (InventoryCheckInfoDTO inventoryCheckInfoDTO);
	public int update (InventoryCheckInfoDTO inventoryCheckInfoDTO);

}
