package com.endata.springboot.model;

import java.util.List;

/**
 * @author ligang
 * @create 2019-12-01 19:32
 */
public class EnvirMap {

    private Integer cityCode;

    private String cityName;

    private List<CityParam> cityParamlist;

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

    public List<CityParam> getCityParamlist() {
        return cityParamlist;
    }

    public void setCityParamlist(List<CityParam> cityParamlist) {
        this.cityParamlist = cityParamlist;
    }

    public EnvirMap(Integer cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }
}
