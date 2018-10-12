package com.example.stringbootbo.service.Impl;

import com.example.stringbootbo.Tasks.CouponTasks;
import com.example.stringbootbo.bean.Brands;
import com.example.stringbootbo.bean.Poi;
import com.example.stringbootbo.common.Constant;
import com.example.stringbootbo.common.Pagebean;
import com.example.stringbootbo.common.StringUtil;
import com.example.stringbootbo.mapper.firstMapper.PoiMapper;
import com.example.stringbootbo.mapper.secondMapper.BrandsMapper;
import com.example.stringbootbo.service.PoiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PoiServiceImpl implements PoiService {

    @Autowired
    private PoiMapper poiMapper;
    @Autowired
    private BrandsMapper brandsMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    CouponTasks couponTasks;
    @Override
    public Pagebean<Poi> searchkeys(Poi poi)throws Exception {
        Pagebean<Poi> pagebean = new Pagebean<>();
        String tableName = StringUtils.join(Constant.TABLENAME, poi.getBuildId());
        Map<String, Object> map = new HashMap<>();
        Pagebean bean = Pagebean.getBean(poi.getPageNo(), poi.getPageSize());
        map.put("buildId", poi.getBuildId());
        map.put("tableName", tableName);
        map.put("floor", poi.getFloor());
        map.put("keywords", poi.getKeywords());
        map.put("start", bean.getStart());
        map.put("size", bean.getSize());
        map.put("x", poi.getX());
        map.put("y", poi.getY());
        List<Poi> list;
        if (map.get("floor") == null || "".equals(map.get("floor")) || map.get("x") == null || (double)map.get("x") <= 0.0 || map.get("y") == null || (double)map.get("y") >= 0.0) {
        	list = poiMapper.searchKeysNoCoord(map); // 有坐标信息
		} else {
	        list = poiMapper.searchkeys(map); // 无坐标信息
		}
        int count = list.size();
        // 设置id
    	for(Poi p:list) {
    		String bid = p.getBuildId();
    		String flr = p.getFloor();
    		String pno = p.getPoiNo();
    		String id = bid + '_' + flr + '_' + pno;
    		p.setId(id);
    	}
        // 计算权重
        for (Poi po : list) {
        	float a = StringUtil.getDistance(po.getPoiName(), poi.getKeywords());
        	double weight = (1 - a) * Poi.WEIGHT_DEFAULT + po.getDist() ;
        	po.setWeight(weight);
        }
        list.sort(Comparator.comparing(Poi::getWeight)); // 按权重排序
        //取前若干个
        if (poi.getPageSize() < list.size()) {
        	list = list.subList(0, poi.getPageSize());
		}
        
        //判断是否有营销信息
        if (list.size() > 0) {
            List<Brands> idBrandList = getMaketInfo(list);
            for (Poi po : list) {
    			String id = po.getId();
    			for (Brands brands : idBrandList) {
    				if (id.equals(brands.getPoiId())) {
    					po.setMarketingType(1);
    					break;
    				}
    			}
    		}
		}
        
        pagebean.setPageNo(poi.getPageNo());
        pagebean.setPageSize(poi.getPageSize());
        pagebean.setDateList(list);
        pagebean.setPageCount(count);
        return pagebean;
    }



    private List<Brands> getMaketInfo(List<Poi> list) {
    	List<String> ids = new ArrayList<String>();
    	for(Poi p:list) {
    		ids.add(p.getId());
    	}
    	List<Brands> retlist = brandsMapper.getMaketBrands(ids);
    	return retlist;
	}


    @Override
    public boolean synchronizePoi(String buildId) {
        return couponTasks.synchronousShopTask(buildId);
    }
}

