package com.example.asus.iscan;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProcessesAdapter extends ArrayAdapter<ActivityManager.RunningServiceInfo>{

  public ArrayList<ActivityManager.RunningServiceInfo> processes;
  private Debug.MemoryInfo[] memoryInfos;
  private OnStopProccessListener listener;

  public ProcessesAdapter(Context context, ArrayList<ActivityManager.RunningServiceInfo> objects,
                          Debug.MemoryInfo[] memoryInfos, OnStopProccessListener listener) {
    super(context, 0, objects);
    this.processes = objects;
    this.memoryInfos=memoryInfos;
    this.listener = listener;
  }

  @Override public int getCount() {
    return processes.size();
  }

  @Nullable @Override public ActivityManager.RunningServiceInfo getItem(int position) {
    return processes.get(position);

  }



  @NonNull @Override public View getView(final int position, View convertView, ViewGroup parent) {
    if (convertView==null)
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_processes, parent, false);

    TextView tv = (TextView) convertView.findViewById(R.id.tvName);

    tv.setText(getItem(position).process +
            "\nPID: "+getItem(position).pid +
            "\nLast Activity Time: "+getItem(position).lastActivityTime+
            "\nStarted Since: "+getItem(position).activeSince+
            "\nIs Foreground: "+getItem(position).foreground+
            "\nMemory Usage: "+memoryInfos[position].getTotalPrivateDirty()+" kB");

    Button btnStop = (Button) convertView.findViewById(R.id.btnStop);
    btnStop.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        listener.onStop(position, getItem(position));
      }
    });

    return convertView;

  }

  public interface OnStopProccessListener{
    void onStop(int position, ActivityManager.RunningServiceInfo info);
  }

}
