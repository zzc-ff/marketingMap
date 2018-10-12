package com.example.stringbootbo.service;

import com.example.stringbootbo.bean.BasePoi;
import com.example.stringbootbo.bean.ServiceFacilities;

import java.util.List;

public interface ServiceFacilitiesService {

    List<ServiceFacilities> getServiceFacilitieList(BasePoi basePoi);
}
