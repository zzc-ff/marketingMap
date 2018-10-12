package com.example.stringbootbo.service;

import com.example.stringbootbo.bean.Poi;
import com.example.stringbootbo.common.Pagebean;

public interface PoiService {
    Pagebean<Poi> searchkeys(Poi poi) throws Exception;

    boolean synchronizePoi(String buildId);
}
