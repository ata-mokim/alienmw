package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.RePublishInfoDTO;
import com.alienasia.alienmw.dto.StoreInInfoDTO;

import java.util.List;


public interface RePublishInfoDAO {
	public int insert (RePublishInfoDTO rePublishInfoDTO);
	public int remove (RePublishInfoDTO rePublishInfoDTO);
	public int statusUpdate (RePublishInfoDTO rePublishInfoDTO);
	public List<RePublishInfoDTO> getList(RePublishInfoDTO rePublishInfoDTO);
}
