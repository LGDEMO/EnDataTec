package com.endata.springboot.model;

import java.util.Date;
import java.util.List;

public class Envir {
    private Integer id;

    private Integer cityCode;

    private String cityName;

    private String dotName;

    private Float so2;

    private Float no2;

    private Float co;

    private Float pm25;

    private Float o3;

    private Date date;

    private List<CityParam> cityList;

    public Envir() {
        super();
    }

    public Envir(Integer cityCode, String cityName, List<CityParam> cityList) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.cityList = cityList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDotName() {
        return dotName;
    }

    public void setDotName(String dotName) {
        this.dotName = dotName;
    }

    public Float getSo2() {
        return so2;
    }

    public void setSo2(Float so2) {
        this.so2 = so2;
    }

    public Float getNo2() {
        return no2;
    }

    public void setNo2(Float no2) {
        this.no2 = no2;
    }

    public Float getCo() {
        return co;
    }

    public void setCo(Float co) {
        this.co = co;
    }

    public Float getPm25() {
        return pm25;
    }

    public void setPm25(Float pm25) {
        this.pm25 = pm25;
    }

    public Float getO3() {
        return o3;
    }

    public void setO3(Float o3) {
        this.o3 = o3;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CityParam> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityParam> cityList) {
        this.cityList = cityList;
    }
}