package com.pangtrue.practice.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class ConvertTime {

    public static Date convertLocalTimeToUTC(String localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Los_Angels"));

        try {
            Date dateLocalTime = sdf.parse(localTime);
            long longLocalTime = dateLocalTime.getTime();

            TimeZone timeZone = TimeZone.getTimeZone("UTC");
            int offset = timeZone.getOffset(longLocalTime);
            long longUtcTime = longLocalTime + offset;

            Date dateUtcTime = new Date();
            dateUtcTime.setTime(longUtcTime);
            return dateUtcTime;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
