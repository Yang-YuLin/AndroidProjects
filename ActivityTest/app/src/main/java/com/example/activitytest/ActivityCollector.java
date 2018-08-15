package com.example.activitytest;

import android.app.Activity;
import java.util.*;

/**
 * Created by 杨玉林 on 2018/4/13.
 */

//ActivityCollector作为活动管理器
public class ActivityCollector {
    public static List<Activity> activities=new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    //将List中存储的活动全部销毁掉
    public static void finishAll(){
        for(Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
