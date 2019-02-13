package com.example.majdbaniodeh.rooms;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.majdbaniodeh.rooms.database.TaskEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context context;
    private List<TaskEntry> taskEntries;

    public TaskAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        return new TaskViewHolder(inflater.inflate(R.layout.task_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {

        taskViewHolder.textViewName.setText(taskEntries.get(i).getName());
        taskViewHolder.textViewPriority.setText(taskEntries.get(i).getPriority());
        taskViewHolder.textViewDate.setText(formatedate(taskEntries.get(i).getAddedAt()));

    }

    @Override
    public int getItemCount() {

        return taskEntries==null?0:taskEntries.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewPriority;
        TextView textViewDate;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.textViewName);
            textViewDate=itemView.findViewById(R.id.textViewDate);
            textViewPriority=itemView.findViewById(R.id.textViewPriority);



        }
    }

    private String formatedate(Date date){
        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM d, yyyy  hh:mm ", Locale.getDefault());
        return newFormat.format(date);



    }

    public void setTasks(List<TaskEntry> list){
        taskEntries=list;
        notifyDataSetChanged();
    }

    public List<TaskEntry> getTaskEntries() {
        return taskEntries;
    }
}
