package com.example.stringbootbo.bean;


import com.example.stringbootbo.common.IsItNecessary;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author guoxiaoodng
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Brands  {
	/**
<<<<<<< .mine
     * appeal for geography  key
||||||| .r2946
     * 地图应用申请的key
=======
     * poi全局唯一id
     */
	private String poiId;  
	/**
     * 地图应用申请的key
>>>>>>> .r2950
     */
    @IsItNecessary(key = true)
    private String key;
    /**
     * venue id
     */
    @IsItNecessary(key = true)
    private String buildId;
    /**
     * user current coordinates, floor
     */
    private String floor;
    /**
	 * user current coordinates   x
     */
    private double x;
    /**
     * user current coordinates   y
     */
    private double y;
    /**
     * brand name
     */
    private String poiName;
    /**
     * poi uniquely identifies id
     */
    private String poiNo;
    /**
     * 0  have marketing activities
     * 1  No marketing activities
     */
    private Integer marketingType;
    /**
     * search times
     */
    private Integer searchCount;
    /**
     * brand iocn
     */
    private String Icon;
    /**
     * number of stores
     */
    @IsItNecessary(key = true)
    private Long num;
    /**
     * shop id
     */
    private Integer ShopId;

	/**
	 * contact number
	 */
	private String mobile;

}
