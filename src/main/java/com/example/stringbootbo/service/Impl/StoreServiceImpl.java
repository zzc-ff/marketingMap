package com.example.stringbootbo.service.Impl;

import com.example.stringbootbo.bean.Brands;
import com.example.stringbootbo.bean.Marketing;
import com.example.stringbootbo.bean.Poi;
import com.example.stringbootbo.bean.StoreDetails;
import com.example.stringbootbo.common.GeoUtil;
import com.example.stringbootbo.mapper.secondMapper.BrandsMapper;
import com.example.stringbootbo.mapper.secondMapper.MaketMapper;
import com.example.stringbootbo.mapper.secondMapper.StoreMapper;
import com.example.stringbootbo.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private MaketMapper maketMapper;
    @Autowired
    private BrandsMapper brandsMapper;
    @Override
    public StoreDetails findStoreDetails(StoreDetails storeDetails) {
    	// 获取poi详情
    	StoreDetails details = null;
    	if (storeDetails.validLocateCoord()) {
    		details = storeMapper.findStoreDetails(storeDetails);
		} else {
			details = storeMapper.findStoreDetailsNoCoord(storeDetails);
		}
    	
        if(details!=null){
        	// 获取营销信息
            if(details.getMarketType()==1){
                Marketing marketing = new Marketing();
                marketing.setMarktetId(details.getMaketId());
                Integer count = maketMapper.selectCount(details.getMaketId());
                marketing.setMarktetNum(count);
                details.setMarketing(marketing);
            }
            // 更新计数
            brandsMapper.updateCount(details.getId());
        }
        return details;
    }
}
