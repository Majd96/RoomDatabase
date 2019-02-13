package com.example.majdbaniodeh.rooms.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.majdbaniodeh.rooms.AppExecutors;
import com.example.majdbaniodeh.rooms.ItemClickSupport;
import com.example.majdbaniodeh.rooms.MainViewModel;
import com.example.majdbaniodeh.rooms.R;
import com.example.majdbaniodeh.rooms.TaskAdapter;
import com.example.majdbaniodeh.rooms.database.AppDatabase;
import com.example.majdbaniodeh.rooms.database.TaskEntry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainViewModel.class.getName();
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private AppDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv);
        adapter=new TaskAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        database=AppDatabase.getInstance(getApplicationContext());

        //we want to get the data from the view model

        setUpViewModel();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        database.getTaskDao().deleteTask(adapter.getTaskEntries().get(position));

                    }
                });


            }
        });
    }

    private void setUpViewModel() {
        MainViewModel mainViewModel=ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getLiveData().observe(this, new Observer<List<TaskEntry>>() {
            @Override
            public void onChanged(@Nullable List<TaskEntry> taskEntries) {
                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");

                adapter.setTasks(taskEntries);
            }
        });
    }

    public void addTask(View view){

        startActivity(new Intent(this,AddTaskActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main_menu,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.red:
            {
                Toast.makeText(this,"RED",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.green:
            {
                Toast.makeText(this,"green",Toast.LENGTH_LONG).show();
                break;
            }
            default:
                break;



        }
        return true;
    }
}
