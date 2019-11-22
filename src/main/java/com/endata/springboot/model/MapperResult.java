package com.endata.springboot.model;

public class MapperResult {
    private Integer cityCode;

    private String cityName;
    private Float addoralFood;

    private Float addoralSoil;

    private Float adddermalSoil;
    private Float addoralWater;

    private Float adddermalWater;
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

    public Float getAddoralFood() {
        return addoralFood;
    }

    public void setAddoralFood(Float addoralFood) {
        this.addoralFood = addoralFood;
    }

    public Float getAddoralSoil() {
        return addoralSoil;
    }

    public void setAddoralSoil(Float addoralSoil) {
        this.addoralSoil = addoralSoil;
    }

    public Float getAdddermalSoil() {
        return adddermalSoil;
    }

    public void setAdddermalSoil(Float adddermalSoil) {
        this.adddermalSoil = adddermalSoil;
    }

    public Float getAddoralWater() {
        return addoralWater;
    }

    public void setAddoralWater(Float addoralWater) {
        this.addoralWater = addoralWater;
    }

    public Float getAdddermalWater() {
        return adddermalWater;
    }

    public void setAdddermalWater(Float adddermalWater) {
        this.adddermalWater = adddermalWater;
    }

    public Float getAddinh() {
        return addinh;
    }

    public void setAddinh(Float addinh) {
        this.addinh = addinh;
    }

    public MapperResult(Integer cityCode,String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

}
