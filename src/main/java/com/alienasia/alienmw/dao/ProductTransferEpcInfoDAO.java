package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.ProductTransferEpcInfoDTO;

import java.util.List;

public interface ProductTransferEpcInfoDAO {
	public List<ProductTransferEpcInfoDTO> getList(ProductTransferEpcInfoDTO productTransferEpcInfoDTO);
	public List<ProductTransferEpcInfoDTO> getGroupList(ProductTransferEpcInfoDTO productTransferEpcInfoDTO);



	public int insert (ProductTransferEpcInfoDTO productTransferEpcInfoDTO);
	public int remove (ProductTransferEpcInfoDTO productTransferEpcInfoDTO);
	public int productTransferInfoUpdate (ProductTransferEpcInfoDTO productTransferEpcInfoDTO);
	public int rfidInfoStatusUpdate (ProductTransferEpcInfoDTO productTransferEpcInfoDTO);
	public int statusUpdate (ProductTransferEpcInfoDTO productTransferEpcInfoDTO);
	public int storeMove (ProductTransferEpcInfoDTO productTransferEpcInfoDTO);
	public int statusRemove (ProductTransferEpcInfoDTO productTransferEpcInfoDTO);

}
