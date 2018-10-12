package com.example.stringbootbo.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.stringbootbo.Tasks.CouponTasks;
import com.example.stringbootbo.bean.Brands;
import com.example.stringbootbo.bean.Maket;
import com.example.stringbootbo.bean.Shop;
import com.example.stringbootbo.mapper.secondMapper.BrandsMapper;
import com.example.stringbootbo.service.BrandsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BrandsServiceImpl implements BrandsService {
	private final static Logger logger = LoggerFactory.getLogger(BrandsServiceImpl.class);
	
    @Autowired
    private BrandsMapper brandsMapper;
    @Override
    public List<Brands> getBrandsList(Brands brands){
        return brandsMapper.getBrandsList(brands);
    }   
}
