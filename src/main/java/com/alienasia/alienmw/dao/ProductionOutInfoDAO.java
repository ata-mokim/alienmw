package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.DeptInfoDTO;
import com.alienasia.alienmw.dto.ProductionOutInfoDTO;
import com.alienasia.alienmw.dto.RfidGetOrderDTO;
import java.util.List;


public interface ProductionOutInfoDAO {
	public int save (ProductionOutInfoDTO productionOutInfoDTO);
	public int remove (ProductionOutInfoDTO productionOutInfoDTO);
	public int rfidInfoStatusUpdate (ProductionOutInfoDTO productionOutInfoDTO);
	public List<ProductionOutInfoDTO> getList(ProductionOutInfoDTO productionOutInfoDTO);
	public List<ProductionOutInfoDTO> getGroupList(ProductionOutInfoDTO productionOutInfoDTO);
}
