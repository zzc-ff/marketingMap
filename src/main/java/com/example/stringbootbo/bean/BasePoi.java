package com.example.stringbootbo.bean;

import com.example.stringbootbo.common.IsItNecessary;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BasePoi {

    @IsItNecessary(key = true)
    private String key;
    private String poiNo;           //poi唯一标识id
    private String poiName;
    @IsItNecessary(key = true)     //poi名字
    private String buildId;         //场馆id
    private String floor;            //poi所在位置，楼层
    private Double x;               //poi所在位置，x
    private Double y;                //poi所在位置，y
    private Long dist = 0L;       //直线距离，单位米；跨楼层距离+20

}
