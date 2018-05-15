package com.example.asus.iscan;

import android.app.ActivityManager;
import android.os.Debug;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class ProcessInfo implements Parcelable{
  private ArrayList<ActivityManager.RunningServiceInfo> runningServiceInfos;
  private Debug.MemoryInfo[] memoryInfos;

  public ProcessInfo(ArrayList<ActivityManager.RunningServiceInfo> runningServiceInfos,
                     Debug.MemoryInfo[] memoryInfos) {
    this.runningServiceInfos = runningServiceInfos;
    this.memoryInfos = memoryInfos;
  }

  public ArrayList<ActivityManager.RunningServiceInfo> getRunningServiceInfos() {
    return runningServiceInfos;
  }

  public Debug.MemoryInfo[] getMemoryInfos() {
    return memoryInfos;
  }

  public int describeContents() {
    return 0;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeList(runningServiceInfos);
    dest.writeArray(memoryInfos);
  }

  public void readFromParcel(Parcel source) {
    runningServiceInfos = source.readArrayList(null);
    memoryInfos = (Debug.MemoryInfo[])source.readArray(null);
  }

  public static final Creator<ProcessInfo> CREATOR = new Creator<ProcessInfo>() {
    public ProcessInfo createFromParcel(Parcel source) {
      return new ProcessInfo(source);
    }
    public ProcessInfo[] newArray(int size) {
      return new ProcessInfo[size];
    }
  };

  private ProcessInfo(Parcel source) {
    readFromParcel(source);
  }
}
