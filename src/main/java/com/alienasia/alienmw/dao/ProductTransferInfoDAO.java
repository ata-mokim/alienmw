package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.ProductTransferInfoDTO;

import java.util.List;


public interface ProductTransferInfoDAO {
	public List<ProductTransferInfoDTO> getList(ProductTransferInfoDTO productTransferInfoDTO);
	public List<ProductTransferInfoDTO> getGroupList(ProductTransferInfoDTO productTransferInfoDTO);
	public List<ProductTransferInfoDTO> getGroupDetailList(ProductTransferInfoDTO productTransferInfoDTO);
	public int insert (ProductTransferInfoDTO productTransferInfoDTO);
	public int remove (ProductTransferInfoDTO productTransferInfoDTO);
	public int update (ProductTransferInfoDTO productTransferInfoDTO);
}
