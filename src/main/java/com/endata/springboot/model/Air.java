package com.endata.springboot.model;

import java.util.Date;

public class Air {
    private Integer cityCode;

    private String cityName;

    private Float ca;

    private Float ir;

    private Float et;

    private Float ef;

    private Float ed;

    private Float bw;

    private Float at;

    private Date date;

    private Float addinh;

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

    public Float getCa() {
        return ca;
    }

    public void setCa(Float ca) {
        this.ca = ca;
    }

    public Float getIr() {
        return ir;
    }

    public void setIr(Float ir) {
        this.ir = ir;
    }

    public Float getEt() {
        return et;
    }

    public void setEt(Float et) {
        this.et = et;
    }

    public Float getEf() {
        return ef;
    }

    public void setEf(Float ef) {
        this.ef = ef;
    }

    public Float getEd() {
        return ed;
    }

    public void setEd(Float ed) {
        this.ed = ed;
    }

    public Float getBw() {
        return bw;
    }

    public void setBw(Float bw) {
        this.bw = bw;
    }

    public Float getAt() {
        return at;
    }

    public void setAt(Float at) {
        this.at = at;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getAddinh() {
        return addinh;
    }

    public void setAddinh(Float addinh) {
        this.addinh = addinh;
    }
}