package com.example.asus.iscan;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getContext;


public class AppInstalled implements Parcelable{
    private String name;
    private String packageName;
    private Drawable launcherIcon;
    private String version;
    private List<AppPermission> appPermissions;
    private int dangerousPermissionCount;

    public AppInstalled(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getLauncherIcon() {
        return launcherIcon;
    }

    public void setLauncherIcon(Drawable launcherIcon) {
        this.launcherIcon = launcherIcon;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getDangerousPermissionCount() {
        return dangerousPermissionCount;
    }

    public void setDangerousPermissionCount(int dangerousPermissionCount) {
        this.dangerousPermissionCount = dangerousPermissionCount;
    }

    public List<AppPermission> getAppPermissions() {
        return appPermissions;
    }

    public void setAppPermissions(List<AppPermission> appPermissions) {
        this.appPermissions = appPermissions;
    }

    @Override
    public String toString() {
        return "AppInstalled{" +
                "name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", version='" + version + '\'' +
                ", appPermissions=" + appPermissions +
                ", dangerousPermissionCount=" + dangerousPermissionCount +
                "}\n\n";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(packageName);
        out.writeString(version);
        out.writeInt(dangerousPermissionCount);
        out.writeTypedList(appPermissions);
    }

    public static final Parcelable.Creator<AppInstalled> CREATOR
            = new Parcelable.Creator<AppInstalled>() {
        public AppInstalled createFromParcel(Parcel in) {
            return new AppInstalled(in);
        }

        public AppInstalled[] newArray(int size) {
            return new AppInstalled[size];
        }
    };

    private AppInstalled(Parcel in){
        name = in.readString();
        packageName = in.readString();
        version = in.readString();
        dangerousPermissionCount = in.readInt();

        appPermissions = new ArrayList<>();
        in.readTypedList(appPermissions, AppPermission.CREATOR);
    }
}
