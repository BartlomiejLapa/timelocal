package com.timeactuall.demo;

import lombok.*;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
public class TimeZone {
    private String zoneName;

}
