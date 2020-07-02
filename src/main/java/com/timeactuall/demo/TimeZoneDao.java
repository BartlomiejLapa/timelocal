package com.timeactuall.demo;

import com.google.gson.Gson;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class TimeZoneDao {

    Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
    List<String> zoneList = new ArrayList<>(allZoneIds);

    @ModelAttribute("zoneChoose")
    public List getZone(){
        Collections.sort(zoneList);
        return zoneList;
    }

    public LocalTime getTime(String zone){
        LocalTime timeNow = LocalTime.parse(LocalTime.now(ZoneId.of(zone)).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        return timeNow;
    }
}
