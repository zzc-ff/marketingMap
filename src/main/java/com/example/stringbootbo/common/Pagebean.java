package com.example.stringbootbo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Pagebean<T> implements Serializable{

    private int pageNo;
    private int pageSize;
    private int pageCount;
    private List<T> dateList;
    private Integer start;
    private Integer size;



    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getDateList() {
        return dateList;
    }

    public void setDateList(List<T> dateList) {
        this.dateList = dateList;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    public static Pagebean getBean(int pageNo,int pageSize) {
        Pagebean pagebean = new Pagebean();
        pagebean.setStart((pageNo-1)*pageSize);
        pagebean.setSize(pageSize);
        return pagebean;
    }


    @Override
    public String toString() {
        return "Pagebean{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", dateList=" + dateList +
                ", start=" + start +
                ", size=" + size +
                '}';
    }
}
