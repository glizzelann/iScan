package com.example.asus.iscan;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.support.test.InstrumentationRegistry.getContext;

/**
 * Created by glizz on 01/02/2017.
 */

public class AppRiskLevel extends AppCompatActivity {
    public static final String EXTA_APP_INSTALLED_OBJECT = "extra-app-installed-object";
    private Button back;
    private Toolbar toolbar;
    private AppInstalled appInstalled;
    private ListView listView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_risk_level);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("App Risk Level");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (getIntent().hasExtra(EXTA_APP_INSTALLED_OBJECT)){
            appInstalled = getIntent().getParcelableExtra(EXTA_APP_INSTALLED_OBJECT);
            Log.d("TEST", "AppRiskLevel: "+ appInstalled.toString());
            ImageView icon = (ImageView) findViewById(R.id.appIcon);
            icon.setImageDrawable(appInstalled.getLauncherIcon());

            TextView tvName = (TextView) findViewById(R.id.appName);
            tvName.setText(appInstalled.getPackageName());

            TextView tvVersion = (TextView) findViewById(R.id.appVersion);
            tvVersion.setText("Version: "+appInstalled.getVersion());

            TextView tvDangerousPermission = (TextView) findViewById(R.id.appDangerousPermission);

            if (appInstalled.getDangerousPermissionCount()==3) {
                tvDangerousPermission.setText("Risk Level: Medium Risk!");
                tvDangerousPermission.setTextColor(Color.YELLOW);
            }
            else if (appInstalled.getDangerousPermissionCount()<3){
                tvDangerousPermission.setText("Risk Level: Safe!");
                tvDangerousPermission.setTextColor(Color.GREEN);
            }else {
                tvDangerousPermission.setText("Risk Level: High risk!");
                tvDangerousPermission.setTextColor(Color.RED);
            }
            String permissions = "";
            for (AppPermission permission: appInstalled.getAppPermissions()){
                permissions = permissions +"Permission Name: " + permission.getName() +"\n" + "Type: " + permission.getType()+"\n\n";
                TextView permissionName = (TextView) findViewById(R.id.permissions);
                permissionName.setText( permissions);


            }



        }




    }

    public void onButtonClick(View v) {
        onBackPressed();
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

