package com.example.asus.iscan;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.asus.iscan.R.id.toolbar;


public class AppsListAdapter extends ArrayAdapter<AppInstalled> {
    private ArrayList<AppInstalled> appInstalledList;
    private Toolbar toolbar;

    public AppsListAdapter(Context context, ArrayList<AppInstalled> appInstalledList) {
        super(context, 0, appInstalledList);
        this.appInstalledList = appInstalledList;
    }

    @Override
    public int getCount() {
        return appInstalledList.size();
    }

    @Nullable
    @Override
    public AppInstalled getItem(int position) {
        return appInstalledList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_permission, parent, false);

        AppInstalled appInstalled = getItem(position);
        ImageView icon = (ImageView) convertView.findViewById(R.id.ivIcon);
        icon.setImageDrawable(appInstalled.getLauncherIcon());

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(appInstalled.getName());

        TextView tvVersion = (TextView) convertView.findViewById(R.id.tvVersion);
        tvVersion.setText("Version: "+appInstalled.getVersion());

        TextView tvDangerousPermission = (TextView) convertView.findViewById(R.id.tvDangerousPermission);

        if (appInstalled.getDangerousPermissionCount()>0) {
            tvDangerousPermission.setText("Dangerous Permissions: " + appInstalled.getDangerousPermissionCount());
            tvDangerousPermission.setTextColor(Color.RED);
        } else {
            tvDangerousPermission.setText("No Dangerous Permissions");
            tvDangerousPermission.setTextColor(Color.GREEN);
        }

        return convertView;
    }

}

