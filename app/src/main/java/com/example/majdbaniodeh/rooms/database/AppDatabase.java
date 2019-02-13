package com.example.majdbaniodeh.rooms.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

@Database(entities = {TaskEntry.class},version =1,exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME="todolist";
    private static final String TAG=AppDatabase.class.getName();
    private static final Object LOCK=new Object();
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance==null){
            //synchronized  means that one thread at a time can accessed tin bloc of code
            synchronized (LOCK){
                Log.d(TAG,"Creating a new database instance");
                instance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME).build();
            }
        }
        Log.d(TAG,"Getting an already existing object");
        return instance;

    }

    public abstract TaskDao getTaskDao();




}
