package com.example.stringbootbo.mapper.firstMapper;

import com.example.stringbootbo.bean.Poi;
import com.example.stringbootbo.bean.Shop;

import java.util.List;
import java.util.Map;

public interface PoiMapper {
    List<Poi> searchkeys( Map map);
    List<Poi> searchKeysNoCoord( Map map);
    List<Shop> findPoiList(Map map);
}
