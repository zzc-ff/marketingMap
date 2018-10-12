package com.example.stringbootbo.mapper.secondMapper;

import com.example.stringbootbo.bean.Brands;
import com.example.stringbootbo.bean.Poi;
import com.example.stringbootbo.bean.Shop;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BrandsMapper {
    List<Brands> getBrandsList(Brands brands);
    List<Brands> getMaketBrands(@Param("ids") List<String> ids);
    boolean insert(Shop shop);
    boolean update(Brands brands);
    boolean updateCount(@Param("id")String id);

    List<Brands> getBrands(Brands brands);

}
