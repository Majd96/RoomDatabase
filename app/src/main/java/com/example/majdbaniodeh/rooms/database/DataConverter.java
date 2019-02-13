package com.example.majdbaniodeh.rooms.database;

import android.arch.persistence.room.TypeConverter;
import android.provider.ContactsContract;

import java.util.Date;

public class DataConverter {

    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp==null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date){
        return date==null?null:date.getTime();
    }
}
