package com.example.stringbootbo.bean;

import com.example.stringbootbo.common.IsItNecessary;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class MarketingIcon {
    @IsItNecessary(key = true)
    private String  key;            //地图应用申请的key
    private String poiNo;           //poi唯一标识id
    private String poiName;
    @IsItNecessary(key = true)       //poi名字
    private String buildId;
    @IsItNecessary(key = true)       //场馆id
    private String floor;            //poi所在位置，楼层
    private String x;               //poi所在位置，x
    private String y;                //poi所在位置，y
    private Integer marketingType;    //营销活动类型

}
