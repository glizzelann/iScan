package com.example.asus.iscan;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ScanActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private ArrayList<AppInstalled> appInstalledList = new ArrayList<AppInstalled>();
    private AppsListAdapter adapter;
    private ListView listView;
    private ProgressBar progressBar;
    private Button scan;
    private TextView textView2;
    private TextView progressInfo;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_app_adapter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Scan Result");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appInstalledList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvApps);
        progressBar = (ProgressBar) findViewById(R.id.pbLoader);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ScanActivity.this, AppRiskLevel.class);
                intent.putExtra(AppRiskLevel.EXTA_APP_INSTALLED_OBJECT, appInstalledList.get(position));
                //based on item add info to intent
                startActivity(intent);

                Toast.makeText(ScanActivity.this,
                        "Clicked: " + appInstalledList.get(position).getName(),
                        Toast.LENGTH_LONG).show();
                ;
            }

        });

        new CheckAllAppsPermissionTask(new OnCheckAllAppsListener() {
            @Override
            public void onFinish() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new AppsListAdapter(ScanActivity.this, appInstalledList);
                        listView.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);

                    }
                }, 1000);
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
      //  client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void getAllAppsWithPermissions() {
        appInstalledList.clear();

        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo ai : packages) {
            Log.d("test", "App: " + ai.name + " Package: " + ai.packageName);
            try {
                PackageInfo packageInfo = pm.getPackageInfo(ai.packageName, PackageManager.GET_PERMISSIONS);
                //Get Permissions
                if (!isSystemPackage(packageInfo)) {
                    String[] requestedPermissions = packageInfo.requestedPermissions;
                    if (requestedPermissions != null) {
                        AppInstalled appInstalled = new AppInstalled();
                        appInstalled.setName(ai.loadLabel(pm).toString());
                        appInstalled.setPackageName(ai.packageName);
                        appInstalled.setVersion(packageInfo.versionName);
                        appInstalled.setLauncherIcon(ai.loadIcon(pm));

                        List<AppPermission> appPermissions = new ArrayList<>(requestedPermissions.length);
                        int ctr = 0;
                        for (int i = 0; i < requestedPermissions.length; i++) {
                            Log.d("test", requestedPermissions[i]);
                            AppPermission appPermission;
                            if (DangerousPermissions.PERMISSIONS().contains(requestedPermissions[i])) {
                                ctr++;
                                appPermission = new AppPermission(requestedPermissions[i], AppPermission.Type.DANGEROUS);
                            } else {
                                appPermission = new AppPermission(requestedPermissions[i], AppPermission.Type.NORMAL);
                            }
                            appPermissions.add(appPermission);
                        }

                        appInstalled.setDangerousPermissionCount(ctr);
                        appInstalled.setAppPermissions(appPermissions);
                        appInstalledList.add(appInstalled);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }


    private class CheckAllAppsPermissionTask extends AsyncTask<Void, Void, Void> {
        private OnCheckAllAppsListener listener;

        public CheckAllAppsPermissionTask(OnCheckAllAppsListener listener) {
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(Void... params) {
            getAllAppsWithPermissions();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.onFinish();
        }
    }

    public interface OnCheckAllAppsListener {
        void onFinish();
    }

    public void onButtonClick(View v) {

        /*Intent intent = new Intent(ScanActivity.this, ScanActivity.class);
        startActivity(intent);*/


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
}
