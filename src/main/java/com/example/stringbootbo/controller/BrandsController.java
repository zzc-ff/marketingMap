package com.example.stringbootbo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stringbootbo.bean.Brands;
import com.example.stringbootbo.common.CodeMsg;
import com.example.stringbootbo.common.IsAllFieldNull;
import com.example.stringbootbo.common.Result;
import com.example.stringbootbo.service.BrandsService;


@RestController
@RequestMapping("/brands")
@CrossOrigin(origins = "*",maxAge = 3600)
public class BrandsController {
    private final static Logger logger = LoggerFactory.getLogger(BrandsController.class);
    @Autowired
    private BrandsService brandsService;


    /**
     * 热门店铺接口
     * @param {"key":"1234567890","buildId":"860100010020300001","num":10}
     * @return
     */
    @RequestMapping("/getBrandsList")
    public Result getBrandsList(Brands brands)throws Exception{
        Boolean allFieldNull = IsAllFieldNull.isAllFieldNull(brands);
        if(!allFieldNull){
            logger.error("ERR参数错误{}",brands);
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        return Result.success( brandsService.getBrandsList(brands));
    }
}
