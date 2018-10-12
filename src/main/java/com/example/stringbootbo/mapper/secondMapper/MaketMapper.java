package com.example.stringbootbo.mapper.secondMapper;

import com.example.stringbootbo.bean.Maket;
import com.example.stringbootbo.bean.MarketingIcon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaketMapper {
    Integer insert(Maket maket);
    Integer deleteAll();
    Integer selectCount(@Param("maketId") Integer maketId);
    List<MarketingIcon> selectList(MarketingIcon marketingIcon);
}
