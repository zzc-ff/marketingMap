package com.example.stringbootbo.bean;



import com.example.stringbootbo.common.IsItNecessary;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Poi implements Serializable {
	public static double WEIGHT_DEFAULT = 100.0;
	@IsItNecessary(key = true)
    private String  key;            //地图应用申请的key
    @IsItNecessary(key = true)
    private Integer pageNo;         //当前页
    @IsItNecessary(key = true)
    private Integer pageSize;       //每页条数
    private String poiNo;           //poi唯一标识id，单楼层
    private String poiName;         //poi名字
    private String id;         		//poi唯一标识id，全局
    private String searchOut;         //搜索关键字
    private String keywords;       //搜索关键字
    @IsItNecessary(key = true)
    private String buildId;         //场馆id
    private String floor;            //poi所在位置，楼层
    private double x;               //poi所在位置，x
    private double y;                //poi所在位置，y
    private double dist = WEIGHT_DEFAULT;                //直线距离，单位米；跨楼层距离+20
    private double weight = WEIGHT_DEFAULT; // 排序权重
    private Integer marketingType = 0;    //营销活动类型

}
