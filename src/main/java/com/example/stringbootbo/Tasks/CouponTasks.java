package com.example.stringbootbo.Tasks;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.stringbootbo.bean.Brands;
import com.example.stringbootbo.bean.Maket;
import com.example.stringbootbo.bean.Shop;
import com.example.stringbootbo.common.Pagebean;
import com.example.stringbootbo.mapper.firstMapper.PoiMapper;
import com.example.stringbootbo.mapper.secondMapper.BrandsMapper;
import com.example.stringbootbo.mapper.secondMapper.MaketMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class CouponTasks {


    public static final String GET_URL_SHOP = "https://coupon.rtmap.com/rtmap-coupon-web/api/developer/coupon/batch/under/shop/activity/list?status=1&";
    public static final String GET_URL_MARKET = "https://pubdata.rtmap.com/rts_pubdata_server/shop/developer/relation/list?marketId=12555&page=1&pageSize=2000";


    private final static Logger logger = LoggerFactory.getLogger(CouponTasks.class);
    @Autowired
    MaketMapper maketMapper;
    @Autowired
    BrandsMapper brandsMapper;
    @Autowired
    PoiMapper poiMapper;

    public boolean testTask(String buildId) {
        try {

            Brands brands = new Brands();
            brands.setBuildId(buildId);
            List<Brands> brandsList = brandsMapper.getBrands(brands);
            for (Brands brand : brandsList) {
                if (brand != null) {
                    URL url = new URL(GET_URL_SHOP + "shopId=" + brand.getShopId());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    // 发送请求
                    connection.connect();
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();
                    connection.disconnect();
                    System.out.println(sb.toString());
                    logger.info("成功");
                    JSONObject jsonObject = JSONObject.parseObject(sb.toString());
                    String data = jsonObject.getString("data");
                    List<Maket> listPram = (List<Maket>) JSONArray.parseArray(data, Maket.class);
                    if (listPram == null || listPram.size() == 0) {
                        continue;
                    }
                    for (Maket maket : listPram) {
                        maket.setX(brand.getX());
                        maket.setY(brand.getY());
                        maket.setMaketType(1);
                        brands.setMarketingType(1);
                        brands.setShopId(brand.getShopId());
                        maket.setShopId(brand.getShopId());
                        maketMapper.insert(maket);
                        brandsMapper.update(brands);
                    }

                }
            }
            logger.info("Maket,数据同步成功");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Maket,数据同步失败");
        }
        return false;
    }


    public boolean synchronousShopTask(String buildId) {
        try {
            URL url = new URL(GET_URL_MARKET);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            connection.disconnect();
            System.out.println(sb.toString());
            logger.info("成功");
            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            String data = jsonObject.getString("data");
            JSONObject json = JSONObject.parseObject(data);
            String list = json.getString("list");
            List<Shop> listPram = (List<Shop>) JSONArray.parseArray(list, Shop.class);
            Map<String, Shop> map = listPram.stream().collect(Collectors.toMap(e -> e.getFloorId() + e.getPoiNo(), Function.identity(), (v1, v2) -> v2));
            String tableName = "poi_" + buildId;
            Map<String, Object> param = new HashMap<>();
            param.put("buildId", buildId);
            param.put("tableName", tableName);
            List<Shop> shopList = poiMapper.findPoiList(param);
            for (Shop shop : shopList) {
                Shop shop1 = map.get(shop.getFloorId() + shop.getPoiNo());
                String id = StringUtils.join(shop.getBuildId(), "_", shop.getFloorId(), "_", shop.getPoiNo());
                shop.setId(id);
                shop.setOpenTime("10:00-22:00");
                if (shop1 != null) {
                    shop.setImgLogoUrl(shop1.getImgLogoUrl());
                    shop.setMobile(shop1.getMobile());
                    shop.setShopId(shop1.getShopId());
                    shop.setPoiType(1);
                }
                brandsMapper.insert(shop);
            }
            logger.info("shop,数据同步成功");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("shop,数据同步错误", e);
        }
        return false;
    }
}