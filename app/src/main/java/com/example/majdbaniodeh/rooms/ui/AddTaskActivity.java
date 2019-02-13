package com.example.majdbaniodeh.rooms.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.majdbaniodeh.rooms.AppExecutors;
import com.example.majdbaniodeh.rooms.R;
import com.example.majdbaniodeh.rooms.database.AppDatabase;
import com.example.majdbaniodeh.rooms.database.TaskEntry;

import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPriority;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        editTextName=findViewById(R.id.editTextName);
        editTextPriority=findViewById(R.id.editTextPriority);
        appDatabase=AppDatabase.getInstance(getApplicationContext());
    }

    public void add(View view) {
        final String name=editTextName.getText().toString();
        final String priority=editTextPriority.getText().toString();
        final Date date=Calendar.getInstance().getTime();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.getTaskDao().insertTask(new TaskEntry(name,priority,date));


            }
        });

        finish();


    }
}
