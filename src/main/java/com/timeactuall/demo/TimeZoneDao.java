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
    /*
    Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
    List<String> zoneList = new ArrayList<>(allZoneIds);

    public List<String> getZoneList() {
        Collections.sort(zoneList);
        return zoneList;
    }
    */
    Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
    List<String> zoneList = new ArrayList<>(allZoneIds);
    Map<Object, LocalTime> zoneTime = new TreeMap<>();

    @ModelAttribute("zoneTimes")
    public Map<Object, LocalTime> getZoneTime() {
        Collections.sort(zoneList);
        Iterator iterator = zoneList.iterator();

        while (iterator.hasNext()) {

            LocalTime now = LocalTime.parse(LocalTime.now(ZoneId.of(iterator.next().toString())).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            zoneTime.put(iterator.next(), now);

        }
        return zoneTime;
    }


@ModelAttribute("zoneTimeValues")
    public String getZoneTimeValue(){
        String zoneTimeValue = "";
        Gson gson = new Gson();
        zoneTimeValue = gson.toJson(zoneTime);
        System.out.println("zoneTimeValue" + zoneTimeValue);
        return zoneTimeValue;

    }
}
