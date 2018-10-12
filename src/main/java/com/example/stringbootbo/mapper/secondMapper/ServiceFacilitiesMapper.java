package com.example.stringbootbo.mapper.secondMapper;

import com.example.stringbootbo.bean.BasePoi;
import com.example.stringbootbo.bean.ServiceFacilities;

import java.util.List;

public interface ServiceFacilitiesMapper {
    List<ServiceFacilities> getServiceFacilitieList(BasePoi basePoi);
}
