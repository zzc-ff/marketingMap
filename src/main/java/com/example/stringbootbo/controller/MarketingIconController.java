package com.example.stringbootbo.controller;

import com.example.stringbootbo.bean.MarketingIcon;
import com.example.stringbootbo.common.CodeMsg;
import com.example.stringbootbo.common.IsAllFieldNull;
import com.example.stringbootbo.common.Result;
import com.example.stringbootbo.service.MarketingIconService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/marketingIcon")
@CrossOrigin(origins = "*",maxAge = 3600)
public class MarketingIconController {
    private final static Logger logger = LoggerFactory.getLogger(MarketingIconController.class);
     @Autowired
    MarketingIconService marketingIconService;
    /**
     * 营销图标接口
     * @return
     */
    @RequestMapping("/getMarketingIcon")
    public Result getMarketingIcon(MarketingIcon marketingIcon)throws Exception{
        Boolean allFieldNull = IsAllFieldNull.isAllFieldNull(marketingIcon);
        if (!allFieldNull) {
            logger.error("err参数错误{}",marketingIcon);
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        List<MarketingIcon> list = marketingIconService.selectList(marketingIcon);
        return Result.success(list);
    }

    /**
     * 同步营销数据
     * @return
     */
    @RequestMapping("/synchronizeMarket")
    public Result synchronizeMarket(String buildId)throws Exception{

        if (StringUtils.isBlank(buildId)) {
            logger.error("err参数错误{}",buildId);
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
       boolean flag = marketingIconService.synchronizeMarket(buildId);
        return Result.success(flag);
    }

}
