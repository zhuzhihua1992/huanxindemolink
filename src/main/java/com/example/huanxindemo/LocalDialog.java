package com.example.huanxindemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by Administrator on 2016/10/8.
 */
public class LocalDialog extends Dialog {

    LatLng latLng;


    public LocalDialog(Context context, double lat, double lng) {
        super(context);
        latLng = new LatLng(lat, lng);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        MapView view = (MapView) findViewById(R.id.mapview);
        //设置地图到当前的经纬度
        //拿到控制器
        BaiduMap baiduMap = view.getMap();
        //手动定位到当前
        MapStatus mapstate = new MapStatus.Builder().target(latLng).zoom(18).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapstate);
        baiduMap.setMapStatus(mapStatusUpdate);
    }
}
