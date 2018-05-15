package com.example.asus.iscan;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;



public class Monitoring extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    private Button run;
    private LinearLayout mLinearLayout;
    private ListView mListView;
    private ProcessesAdapter processesAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* TextView tv = (TextView) findViewById(R.id.textView);
        ActivityManager activityManager = (ActivityManager) this
                .getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager
                .getRunningAppProcesses();
        for (int idx = 0; idx < procInfos.size(); idx++) {
            tv.setText(tv.getText() + "" + (idx + 1) + "."
                    + procInfos.get(idx).processName + "\n");
        }*/
    }




        }

