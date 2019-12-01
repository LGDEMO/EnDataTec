package com.endata.springboot.model;

/**
 * @author ligang
 * @create 2019-12-01 19:32
 */
public class EnvirMap {

    private Integer cityCode;

    private String cityName;
    private Float so2;

    private Float no2;

    private Float co;

    private Float pm25;

    private Float o3;

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

    public EnvirMap(Integer cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }
}
