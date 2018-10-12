package com.example.stringbootbo.service.Impl;

import com.example.stringbootbo.Tasks.CouponTasks;
import com.example.stringbootbo.bean.MarketingIcon;
import com.example.stringbootbo.mapper.secondMapper.MaketMapper;
import com.example.stringbootbo.service.MarketingIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketingIconServiceImpl implements MarketingIconService {

    @Autowired
    private MaketMapper maketMapper;
    @Autowired
    CouponTasks couponTasks;

    @Override
    public List<MarketingIcon> selectList(MarketingIcon marketingIcon) {
        return maketMapper.selectList(marketingIcon);
    }

    @Override
    public boolean synchronizeMarket(String buildId) {
        return couponTasks.testTask(buildId);
    }
}
