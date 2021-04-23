package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.WarehouseOutInfoDTO;

import java.util.List;


public interface WarehouseOutInfoDAO {
	public int insert (WarehouseOutInfoDTO warehouseOutInfoDTO);
	public int remove (WarehouseOutInfoDTO warehouseOutInfoDTO);
	public int rfidInfoStatusUpdate (WarehouseOutInfoDTO warehouseOutInfoDTO);
	public List<WarehouseOutInfoDTO> getList(WarehouseOutInfoDTO warehouseOutInfoDTO);
	public List<WarehouseOutInfoDTO> getGroupList(WarehouseOutInfoDTO warehouseOutInfoDTO);
}
