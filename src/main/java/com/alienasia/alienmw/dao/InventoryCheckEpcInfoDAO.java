package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.InventoryCheckEpcInfoDTO;

import java.util.List;

public interface InventoryCheckEpcInfoDAO {
	public List<InventoryCheckEpcInfoDTO> getList(InventoryCheckEpcInfoDTO inventoryCheckEpcInfoDTO);
	public int insert (InventoryCheckEpcInfoDTO inventoryCheckEpcInfoDTO);
	public int remove (InventoryCheckEpcInfoDTO inventoryCheckEpcInfoDTO);
}
