package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.ProductionOutInfoDTO;
import com.alienasia.alienmw.dto.WarehouseInInfoDTO;

import java.util.List;


public interface WarehouseInInfoDAO {
	public int insert (WarehouseInInfoDTO warehouseInInfoDTO);
	public int remove (WarehouseInInfoDTO warehouseInInfoDTO);
	public int rfidInfoStatusUpdate (WarehouseInInfoDTO warehouseInInfoDTO);
	public List<WarehouseInInfoDTO> getList(WarehouseInInfoDTO warehouseInInfoDTO);
	public List<WarehouseInInfoDTO> getGroupList(WarehouseInInfoDTO warehouseInInfoDTO);
}
