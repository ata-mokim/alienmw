package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.HoReturnEpcInfoDTO;
import com.alienasia.alienmw.dto.ProductionOutInfoDTO;

import java.util.List;

public interface HoReturnEpcInfoDAO {
	public List<HoReturnEpcInfoDTO> getList(HoReturnEpcInfoDTO hoReturnEpcInfoDTO);
	public List<HoReturnEpcInfoDTO> getGroupList(HoReturnEpcInfoDTO hoReturnEpcInfoDTO);



	public int insert (HoReturnEpcInfoDTO hoReturnEpcInfoDTO);
	public int remove (HoReturnEpcInfoDTO hoReturnEpcInfoDTO);
	public int statusRemove (HoReturnEpcInfoDTO hoReturnEpcInfoDTO);
	public int returnWarehouse (HoReturnEpcInfoDTO hoReturnEpcInfoDTO);

	public int hoReturnInfoUpdate (HoReturnEpcInfoDTO hoReturnEpcInfoDTO);
	public int rfidInfoStatusUpdate (HoReturnEpcInfoDTO hoReturnEpcInfoDTO);
	public int statusUpdate (HoReturnEpcInfoDTO hoReturnEpcInfoDTO);
}
