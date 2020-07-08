package com.timeactuall.demo.weather;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter

public class WeatherDao {
    JsonNode main;
    JsonNode weather;
    private String temp;
    private String weatherMain;
    private String description;
    private String icon;
    private String city;
//
}
