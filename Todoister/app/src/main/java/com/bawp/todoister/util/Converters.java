package com.bawp.todoister.util;

import androidx.room.TypeConverter;

import com.bawp.todoister.model.enums.Priority;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static Date fromTimestampToDate(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long fromDateToTimestamp(Date date){
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String fromPriorityToString(Priority priority){
        return priority == null ? null : priority.name();
    }

    @TypeConverter
    public static Priority fromStringToPriority(String priority){
        return priority == null ? null : Priority.valueOf(priority);
    }
}
