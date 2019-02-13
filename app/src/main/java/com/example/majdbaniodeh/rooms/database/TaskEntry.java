package com.example.majdbaniodeh.rooms.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName ="Tasks" )
public class TaskEntry {

    @PrimaryKey(autoGenerate =true)
    private int id;
    private String name;
    private String priority;
    private Date addedAt;

    //it has two constructors

    //one when we need to add a task to the database
    @Ignore
    public TaskEntry(String name, String priority, Date addedAt) {
        this.name = name;
        this.priority = priority;
        this.addedAt = addedAt;
    }

    //constructor that room will use to read from the SQL database
    public TaskEntry(int id, String name, String priority, Date addedAt) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.addedAt = addedAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public Date getAddedAt() {
        return addedAt;
    }
}
