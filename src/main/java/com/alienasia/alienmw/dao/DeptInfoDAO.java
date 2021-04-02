package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.DeptInfoDTO;

import java.util.List;


public interface DeptInfoDAO {
	public List<DeptInfoDTO> getList(DeptInfoDTO DeptInfoDTO);
	public DeptInfoDTO get(String userId);
	public int update (DeptInfoDTO DeptInfoDTO);
	public void insert  (DeptInfoDTO DeptInfoDTO);
	public int delete(String userId);	

}


