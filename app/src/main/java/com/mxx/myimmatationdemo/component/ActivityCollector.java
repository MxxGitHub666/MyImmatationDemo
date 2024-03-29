package com.mxx.myimmatationdemo.component;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

public class ActivityCollector {

    private static ActivityCollector activityCollector;

    public synchronized static ActivityCollector getInstance(){
        if(activityCollector == null){
            activityCollector = new ActivityCollector();
        }
        return activityCollector;
    }

    private Set<Activity> allActivities;

    public void addActivity(Activity activity){
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
