package com.alienasia.alienmw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import com.alienasia.alienmw.dto.RfidGetOrderDTO;
import com.alienasia.alienmw.dto.RfidOrderDTO;




public interface RfidOrderDAO {
	public List<RfidOrderDTO> getList(RfidOrderDTO rfidOrderDTO);
	public List<RfidOrderDTO> getSearchList(RfidOrderDTO rfidOrderDTO);
	public int update (RfidOrderDTO rfidOrderDTO);
	public int insert (RfidGetOrderDTO rfidGetOrderDTO);
	public int remove(Long rfid_order_seq);
	public int revComplete(RfidOrderDTO rfidOrderDTO);
	public int statusUpdate(RfidOrderDTO rfidOrderDTO);
	public int rfidTake(RfidOrderDTO rfidOrderDTO);
	public int publishComplete(RfidOrderDTO rfidOrderDTO);
	public List<RfidOrderDTO> dailyValue(RfidOrderDTO rfidOrderDTO);
	
}
