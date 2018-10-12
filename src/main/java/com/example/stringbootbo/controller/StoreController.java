package com.example.stringbootbo.controller;


import com.example.stringbootbo.bean.StoreDetails;
import com.example.stringbootbo.common.CodeMsg;
import com.example.stringbootbo.common.IsAllFieldNull;
import com.example.stringbootbo.common.Result;
import com.example.stringbootbo.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/storeDetails")
@CrossOrigin(origins = "*",maxAge = 3600)
public class StoreController {
    private final static Logger logger = LoggerFactory.getLogger(PoiController.class);
    @Autowired
    private StoreService storeService;
    /**
     * 服务设施接口
     * @return
     */
    @RequestMapping("/getstoreDetails")
    public Result getstoreDetails(StoreDetails storeDetails)throws Exception{
        Boolean allFieldNull = IsAllFieldNull.isAllFieldNull(storeDetails);
        if(!allFieldNull){
            logger.error("ERR参数错误{}",storeDetails);
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        String id = storeDetails.getBuildId() + "_" + storeDetails.getFloor() + "_" + storeDetails.getPoiNo();
        storeDetails.setId(id);
        return Result.success(storeService.findStoreDetails(storeDetails));
    }


}
