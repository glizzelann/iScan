package com.example.asus.iscan;

import java.util.ArrayList;
import java.util.List;


public class DangerousPermissions {

    public static final List<String> PERMISSIONS(){
        List<String> list = new ArrayList<>(24);
        list.add("android.permission.READ_CALENDAR");
        list.add("android.permission.WRITE_CALENDAR");
        list.add("android.permission.CAMERA");
        list.add("android.permission.READ_CONTACTS");
        list.add("android.permission.WRITE_CONTACTS");
        list.add("android.permission.GET_ACCOUNTS");
        list.add("android.permission.ACCESS_FINE_LOCATION");
        list.add("android.permission.ACCESS_COARSE_LOCATION");
        list.add("android.permission.RECORD_AUDIO");
        list.add("android.permission.READ_PHONE_STATE");
        list.add("android.permission.CALL_PHONE");
        list.add("android.permission.READ_CALL_LOG");
        list.add("android.permission.WRITE_CALL_LOG");
        list.add("com.android.voicemail.permission.ADD_VOICEMAIL");
        list.add("android.permission.USE_SIP");
        list.add("android.permission.PROCESS_OUTGOING_CALLS");
        list.add("android.permission.BODY_SENSORS");
        list.add("android.permission.SEND_SMS");
        list.add("android.permission.RECEIVE_SMS");
        list.add("android.permission.READ_SMS");
        list.add("android.permission.RECEIVE_WAP_PUSH");
        list.add("android.permission.RECEIVE_MMS");
        list.add("android.permission.READ_EXTERNAL_STORAGE");
        list.add("android.permission.WRITE_EXTERNAL_STORAGE");
        return list;
    }
}
