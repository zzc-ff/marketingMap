package com.example.stringbootbo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author guoxiaodong
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ServiceFacilities {

	private String name;
    /**
     * 服务设施icon
     */
    private String icon;

    private String poiJson;
    private List<BasePoi> poilist;

}
