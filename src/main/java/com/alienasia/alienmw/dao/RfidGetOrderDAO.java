package com.alienasia.alienmw.dao;

import java.util.List;

import com.alienasia.alienmw.dto.RfidGetOrderDTO;
import org.apache.ibatis.annotations.Select;



public interface RfidGetOrderDAO {
	public List<RfidGetOrderDTO> getList(RfidGetOrderDTO rfidGetOrderDTO);
	public List<RfidGetOrderDTO> getSearchList(RfidGetOrderDTO rfidGetOrderDTO);
	public RfidGetOrderDTO read(Long rfid_get_order_seq);
	public int remove(Long rfid_get_order_seq);
	public int update (RfidGetOrderDTO rfidGetOrderDTO);
	public int revComplete (RfidGetOrderDTO rfidGetOrderDTO);
	public int rfidOrderReserve (RfidGetOrderDTO rfidGetOrderDTO);
	public int rfidPublish (RfidGetOrderDTO rfidGetOrderDTO);
	public int rfidTake (RfidGetOrderDTO rfidGetOrderDTO);
}
