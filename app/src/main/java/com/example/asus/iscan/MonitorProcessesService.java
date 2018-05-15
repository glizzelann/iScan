package com.example.asus.iscan;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.Debug;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MonitorProcessesService extends Service{

  public static final String ACTION_UPDATE_PROCESS_LIST = "action-update-process-list";
  public static final String EXTRA_PROCESSES = "extra-processes";
  private Timer timer;

  @Override public void onCreate() {
    super.onCreate();
    timer = new Timer();
    timer.schedule(timerTask, 1000, 5000);
  }

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    return START_STICKY;
  }
  @Nullable @Override public IBinder onBind(Intent intent) {
    return null;
  }
  @Override public boolean onUnbind(Intent intent) {
    return super.onUnbind(intent);
  }
  @Override public void onRebind(Intent intent) {
    super.onRebind(intent);
  }
  @Override public void onDestroy() {
    super.onDestroy();
  }

  private TimerTask timerTask = new TimerTask() {
    @Override public void run() {
      Log.d("TEST", "Running timerTask ... ");
      doMonitorRunningServices();
    }
  };

  /**
   * Will monitor/get all the running service from the device
   */
  private void doMonitorRunningServices(){
    /**
     * Get all the running services
     */
    ActivityManager actvityManager = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
    ArrayList<ActivityManager.RunningServiceInfo> processInfoList = (ArrayList<ActivityManager.RunningServiceInfo>) actvityManager.getRunningServices(Integer.MAX_VALUE);

    /**
     * This block removed the duplicates and retain single copy of the process
     */
    HashMap<String, ActivityManager.RunningServiceInfo> consolidatedProcesses = new HashMap<>();
    for (ActivityManager.RunningServiceInfo info:processInfoList){
      if (!consolidatedProcesses.containsKey(info.process)){
        consolidatedProcesses.put(info.process, info);
      }
    }

    ArrayList<ActivityManager.RunningServiceInfo> consolidatedProcesseList = new ArrayList<>(consolidatedProcesses.size());
    for (HashMap.Entry entry: consolidatedProcesses.entrySet()){
      consolidatedProcesseList.add((ActivityManager.RunningServiceInfo)entry.getValue());
    }

    int[] pids = new int[consolidatedProcesseList.size()];
    for (int i = 0; i<consolidatedProcesseList.size(); i++){
      pids[i]=consolidatedProcesseList.get(i).pid;
    }

    /**
     * Reference for investigating RAM
     * https://developer.android.com/studio/profile/investigate-ram.html
     *
     * We will display the Private Dirty RAM coz as per the docs,
     * "All Dalvik and native heap allocations you make will be private dirty RAM;"
     */
    Debug.MemoryInfo[] memoryInfos = actvityManager.getProcessMemoryInfo(pids);


    /**
     * Will send a broadcast to the BroadcastReceiver in the MonitoringActivity
     * with ACTION_UPDATE_PROCESS_LIST intent filter to update the UI list
     */
    ProcessInfo processInfo = new ProcessInfo(consolidatedProcesseList, memoryInfos);

    Intent updateList = new Intent(ACTION_UPDATE_PROCESS_LIST);
    updateList.putExtra(EXTRA_PROCESSES, processInfo);
    LocalBroadcastManager.getInstance(this).sendBroadcast(updateList);
  }
}


