package com.weibin.step.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * 检测手机是否支持计歩
 * Created by wei.bin on 2017/8/26.
 */
public class StepCountModeDispatcher {

    private Context context;
    private boolean hasSons0r;

    public StepCountModeDispatcher(Context context) {
        this.context = context;
        hasSons0r = isSupportStepCountSensor();
    }

    public boolean isSupportStepCountSensor(){
        return context.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER);
    }

    /**
     * 判断该设备是否支持计歩
     * @param context
     * @return
     */
    public static boolean isSupportStepCountSensor(Context context){
        //获取传感器管理器的实例
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        return countSensor != null || detectorSensor != null;
    }
}



