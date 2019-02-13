package com.example.majdbaniodeh.rooms;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.majdbaniodeh.rooms.database.AppDatabase;
import com.example.majdbaniodeh.rooms.database.TaskEntry;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    //the view model is used to cash the model in the form of LiveData
    private LiveData<List<TaskEntry>> liveData;
    private static final String TAG=MainViewModel.class.getName();


    public MainViewModel(Application application) {
        super(application);
        AppDatabase appDatabase=AppDatabase.getInstance(this.getApplication());
        Log.d(TAG,"The ViewModel has fetched the data");
        liveData=appDatabase.getTaskDao().getAllTasks();
    }

    public LiveData<List<TaskEntry>> getLiveData() {
        return liveData;
    }
}
