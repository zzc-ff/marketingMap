package com.example.stringbootbo.bean;

import com.example.stringbootbo.common.IsItNecessary;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class StoreDetails {
	@IsItNecessary(key = true)
    private String  key;         	//地图应用申请的key
    private String poiName;         //poi名字
    private String keywords;       	//搜索关键字
    @IsItNecessary(key = true)
    private String buildId;         //场馆id
    @IsItNecessary(key = true)
    private String poiNo;           //poi单楼层唯一id
    @IsItNecessary(key = true)
    private String floor;           //poi所在位置，楼层
    private double X;         		//poi所在位置，x
    private double Y;         		//poi所在位置，y
    private double locateX = 0.0;         //当前位置，x
    private double locateY = 0.0;         //当前位置，y
    private String locateFloor;     //当前位置，楼层
    private double dist;       		//直线距离，单位米；跨楼层距离+20
    private Integer marketType;    	//营销活动类型
    private String openTime;        //营业时间
    private String typeName;       	//店铺类型
    private String typeNo;         	//店铺类型id
    private int maketId;
    private String id;           	//poi全局唯一id
    private String icon;
    private Integer count;
    private String mobile;
    private int poiType;
    private String floorAlias;
    private Marketing marketing;
    
    public boolean validLocateCoord() {
        if (floor == null || "".equals(floor) || X <= 0.0 || Y >= 0.0) {
        	return false;
		} else {
			return true;
		}
	}
}
