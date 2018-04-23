package com.example.huanxindemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 */
public class MyApp extends Application {
    MyApp appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        if (processAppName == null || !processAppName.equalsIgnoreCase(appContext.getPackageName())) {
            Log.e("TAG", "enter the service process!");
            return;
        }

        Log.e("TAG","---------------------------------");
        EMOptions options = new EMOptions();
        //默认自动登录
        options.setAutoLogin(false);
        //是否 需要 同意 好友申请
        options.setAcceptInvitationAlways(false);
        EMClient.getInstance().init(this, options);
        EaseUI.getInstance().init(this,options);
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
