package com.example.stringbootbo.service;

import com.example.stringbootbo.bean.MarketingIcon;

import java.util.List;

public interface MarketingIconService {
    List<MarketingIcon> selectList(MarketingIcon marketingIcon);

    boolean synchronizeMarket(String buildId);
}

