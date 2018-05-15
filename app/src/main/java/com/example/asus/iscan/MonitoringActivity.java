package com.example.asus.iscan;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.asus.iscan.R.id.load;

public class MonitoringActivity extends AppCompatActivity implements ProcessesAdapter.OnStopProccessListener {



    private Toolbar toolbar;
    private Context mContext;
    private TextView mTextView;
    private Button run;
    private Button back;
    private ArrayList<AppInstalled> appInstalledList;
    private AppsListAdapter adapter;
    private ProcessesAdapter processesAdapter;
    private ListView listView;
    private ProgressBar progressBar;
    private TextView tvName;
    private Button stop;


    private ArrayList<ActivityManager.RunningServiceInfo> processInfoArrayList;
    private BroadcastReceiver activityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case MonitorProcessesService.ACTION_UPDATE_PROCESS_LIST:
                    Log.d("TEST", "Boradcast: ACTION_UPDATE_PROCESS_LIST");
                    ProcessInfo data = intent.getParcelableExtra(MonitorProcessesService.EXTRA_PROCESSES);

                    if (processesAdapter == null) {
                        processInfoArrayList = new ArrayList<>(data.getRunningServiceInfos());
                        processesAdapter = new ProcessesAdapter(MonitoringActivity.this, processInfoArrayList,
                                data.getMemoryInfos(), MonitoringActivity.this);
                        listView.setAdapter(processesAdapter);
                    } else {
                        //TODO do some updating in the list
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Processes");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_white_24dp);

        appInstalledList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lv);
        progressBar = (ProgressBar) findViewById(R.id.pbLoader);



    }



    @Override
    protected void onStart() {
        super.onStart();
        //Starting the service
        startService(new Intent(this, MonitorProcessesService.class));

        //Registering the BroadcastReceiver
        LocalBroadcastManager.getInstance(this).registerReceiver(activityReceiver,
                new IntentFilter(MonitorProcessesService.ACTION_UPDATE_PROCESS_LIST));
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Unregistering the BroadcastReceiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(activityReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop(int position, ActivityManager.RunningServiceInfo info) {

        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.killBackgroundProcesses(info.process);
        android.os.Process.killProcess(info.pid);

        processInfoArrayList.remove(position);
        processesAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Process has been killed: PID = "+info.pid, Toast.LENGTH_LONG).show();


    }



}



