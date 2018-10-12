package com.example.stringbootbo.controller;


import com.example.stringbootbo.bean.BasePoi;
import com.example.stringbootbo.bean.ServiceFacilities;
import com.example.stringbootbo.common.CodeMsg;
import com.example.stringbootbo.common.IsAllFieldNull;
import com.example.stringbootbo.common.Result;
import com.example.stringbootbo.service.ServiceFacilitiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/serviceFacilities")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ServiceFacilitiesController {
    private final static Logger logger = LoggerFactory.getLogger(ServiceFacilitiesController.class);
  @Autowired
   ServiceFacilitiesService  facilitiesService;
    /**
     * 服务设施接口
     * @return
     */
    @RequestMapping("/getServiceFacilitieList")
    public Result getServiceFacilitieList(BasePoi basePoi)throws Exception{
        Boolean allFieldNull = IsAllFieldNull.isAllFieldNull(basePoi);
        if (!allFieldNull) {
            logger.error("err参数错误{}", basePoi);
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        List<ServiceFacilities> list = facilitiesService.getServiceFacilitieList(basePoi);
        if (StringUtils.isEmpty(list)) {
            return Result.success();
        }
        return Result.success(list);
    }


}
