package com.cisco.appointmentservice.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtil {

    public static Timestamp getTimeStamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    public static LocalDateTime getLocalDateTime(Timestamp timestamp, ZoneId zoneId) {
        return timestamp.toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(zoneId).toLocalDateTime();
    }

    public static LocalDateTime getLocalDateTimeAtUTC(LocalDateTime localDateTime, ZoneId zone) {
        return localDateTime.atZone(zone).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
    }

    public static ZonedDateTime getZonedDateTime(LocalDateTime localDateTime, ZoneId zone) {
        return localDateTime.atZone(zone);
    }

    public static ZonedDateTime getZonedDateTimeAtUTC(ZonedDateTime zonedDateTime) {
        return zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    }

}
