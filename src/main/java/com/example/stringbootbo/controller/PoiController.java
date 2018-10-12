package com.example.stringbootbo.controller;

import com.example.stringbootbo.bean.Poi;
import com.example.stringbootbo.common.CodeMsg;
import com.example.stringbootbo.common.IsAllFieldNull;
import com.example.stringbootbo.common.Pagebean;
import com.example.stringbootbo.common.Result;
import com.example.stringbootbo.common.redis.RedisUtil;
import com.example.stringbootbo.service.PoiService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/poi")
@CrossOrigin(origins = "*",maxAge = 3600)
public class PoiController {
    private final static Logger logger = LoggerFactory.getLogger(PoiController.class);
   @Autowired
    PoiService poiService;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 模糊搜索
     * @return
     */
    @RequestMapping("/searchkeys")
    public Result searchkeys(Poi poi)throws Exception{
        Boolean allFieldNull = IsAllFieldNull.isAllFieldNull(poi);
        if (!allFieldNull) {
            logger.error("err参数错误{}",poi);
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        if(StringUtils.isBlank(poi.getKeywords())){
            return Result.success();
        }
        Pagebean<Poi> pagebean = poiService.searchkeys(poi);
        return Result.success(pagebean);
    }


    /**
     * 同步poi 数据
     * @return
     */
    @RequestMapping("/synchronizePoi")
    public Result synchronizePoi(String buildId)throws Exception{

        if (StringUtils.isBlank(buildId)) {
            logger.error("err参数错误{}",buildId);
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        boolean flag = poiService.synchronizePoi(buildId);
        return Result.success(flag);
    }

}
