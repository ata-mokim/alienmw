package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.HoReturnInfoDTO;

import java.util.List;


public interface HoReturnInfoDAO {
	public List<HoReturnInfoDTO> getList(HoReturnInfoDTO hoReturnInfoDTO);
	public List<HoReturnInfoDTO> getGroupList(HoReturnInfoDTO hoReturnInfoDTO);
	public List<HoReturnInfoDTO> getGroupDetailList(HoReturnInfoDTO hoReturnInfoDTO);
	public int insert (HoReturnInfoDTO hoReturnInfoDTO);
	public int remove (HoReturnInfoDTO hoReturnInfoDTO);
	public int update (HoReturnInfoDTO hoReturnInfoDTO);
}
