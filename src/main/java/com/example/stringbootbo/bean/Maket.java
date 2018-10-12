package com.example.stringbootbo.bean;

import lombok.Data;



@Data
public class Maket {

	private String buildId;         //场馆id
    private String floorId;            //poi所在位置，楼层
    private double x;               //poi所在位置，x
    private double y;                //poi所在位置，y
    private Integer maketType;    //营销活动类型
    private Integer shopId;
    private Long quantity;
    private Long poiNo;
    private String activityId;
    private String couponActivityId;
	private String mainInfo;

}
