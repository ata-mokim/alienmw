package com.alienasia.alienmw.dao;

import java.util.List;

import com.alienasia.alienmw.dto.StoreInGroupListDTO;
import com.alienasia.alienmw.dto.StoreInGroupListReq;
import org.apache.ibatis.annotations.Select;


import com.alienasia.alienmw.dto.RfidGetOrderDTO;
import com.alienasia.alienmw.dto.RfidInfoDTO;


public interface RfidInfoDAO {
	public List<RfidInfoDTO> getList(RfidInfoDTO rfidInfoDTO);
	public List<RfidInfoDTO> getSearchList(RfidInfoDTO rfidInfoDTO);
	public List<RfidInfoDTO> read(RfidInfoDTO rfidInfoDTO);
	public List<RfidInfoDTO> dailyValueWIn(RfidInfoDTO rfidInfoDTO);
	public List<RfidInfoDTO> dailyValueWOut(RfidInfoDTO rfidInfoDTO);
	public List<RfidInfoDTO> dailyValueSIn(RfidInfoDTO rfidInfoDTO);
	public List<RfidInfoDTO> dailyValueSOut(RfidInfoDTO rfidInfoDTO);
	
	public List<StoreInGroupListDTO> getStoreInGroupList(RfidInfoDTO rfidInfoDTO);
	public List<StoreInGroupListDTO> getStoreInventoryGroupList(StoreInGroupListReq stroreInGroupListReq);
	public int update (RfidInfoDTO rfidInfoDTO);
	public int rfidTake (RfidInfoDTO rfidInfoDTO);
	
	public int insert(RfidInfoDTO rfidInfoDTO);
	public int arrayInsert(List<RfidInfoDTO> rfidInfoDTO);
	public int statusUpdate(RfidInfoDTO rfidInfoDTO);
	public int delete(String epc);

}
