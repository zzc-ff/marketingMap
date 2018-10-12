package com.example.stringbootbo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Marketing {
    public String getPoiNo() {
        return poiNo;
    }

    public void setPoiNo(String poiNo) {
        this.poiNo = poiNo;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Integer getMarketingType() {
        return marketingType;
    }

    public void setMarketingType(Integer marketingType) {
        this.marketingType = marketingType;
    }

    public Integer getMarktetNum() {
        return marktetNum;
    }

    public void setMarktetNum(Integer marktetNum) {
        this.marktetNum = marktetNum;
    }

    public Integer getMarktetId() {
        return marktetId;
    }

    public void setMarktetId(Integer marktetId) {
        this.marktetId = marktetId;
    }

    private String poiNo;           //poi唯一标识id
    private String poiName;         //poi名字
    private String buildId;         //场馆id
    private String floor;            //poi所在位置，楼层
    private String x;               //poi所在位置，x
    private String y;                //poi所在位置，y
    private Integer marketingType;    //营销活动类型
    private Integer marktetNum;
    private Integer marktetId;

}
