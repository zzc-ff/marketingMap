package com.example.stringbootbo.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.example.stringbootbo.bean.BasePoi;
import com.example.stringbootbo.bean.ServiceFacilities;
import com.example.stringbootbo.common.GeoUtil;
import com.example.stringbootbo.mapper.secondMapper.ServiceFacilitiesMapper;
import com.example.stringbootbo.service.ServiceFacilitiesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceFacilitiesServiceImpl implements ServiceFacilitiesService {
    @Autowired
    ServiceFacilitiesMapper facilitiesMapper;

    @Override
    public List<ServiceFacilities> getServiceFacilitieList(BasePoi basePoi) {
        List<ServiceFacilities> serviceFacilitieList = facilitiesMapper.getServiceFacilitieList(basePoi);
        for (ServiceFacilities serviceFacilities : serviceFacilitieList) {
            String poiJson = serviceFacilities.getPoiJson();
            List<BasePoi> basePois = JSONArray.parseArray(poiJson, BasePoi.class);
            List<BasePoi> dist = getDist(basePoi, basePois);
            serviceFacilities.setPoiJson(null);
            serviceFacilities.setPoilist(dist);
        }
        return serviceFacilitieList;
    }

    /**
     * 根据用户当前距离 计算距离并排序
     *
     * @param basePoi
     * @param basePois
     */
    private List<BasePoi> getDist(BasePoi basePoi, List<BasePoi> basePois) {
        for (BasePoi e : basePois) {
            if (StringUtils.isNoneBlank(basePoi.getFloor())) {
                if (basePoi.getX() != null && basePoi.getY() != null) {
                    double dist = GeoUtil.getDistanceOfMeter(basePoi.getX(), basePoi.getY(), e.getX(), e.getY());
                    Long round = Math.round(dist);
                    e.setDist(round);
                    if (!basePoi.getFloor().equals(e.getFloor())) {
                        e.setDist(round + 20);
                    }
                } else {
                    e.setDist(0L);
                }
            }
        }
        List<BasePoi> collect = basePois.stream().sorted(Comparator.comparing(BasePoi::getDist)).collect(Collectors.toList());
        return collect;
    }
}
