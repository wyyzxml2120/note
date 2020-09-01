package com.nlk.jyweather;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.nlk.jyweather.bean.AreaBean;

import java.io.IOException;
import java.util.List;

public class JYLocation {
    Activity context;

    public JYLocation(Activity context) {
        this.context = context;
    }

    //获取详细定位地址
    public String getLocation(){
        String address = "";
        if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }else {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            String provider = LocationManager.NETWORK_PROVIDER;// 指定LocationManager的定位方法
            //NETWORK_PROVIDER 网络定位、GPS_PROVIDER GPS定位
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return "";
            }
            Location location = locationManager.getLastKnownLocation(provider);// 调用getLastKnownLocation()方法获取当前的位置信息
            assert location != null;
            double lat = location.getLatitude();//获取纬度
            double lng = location.getLongitude();//获取经度

            address =  getAddress(lng,lat).getAddressLine(0);

        }
        return address;
    }

    //获取省、市、区
    public AreaBean getWeatherLocation(){
        AreaBean areaBean = new AreaBean();
        if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }else {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            String provider = LocationManager.NETWORK_PROVIDER;// 指定LocationManager的定位方法
            //NETWORK_PROVIDER 网络定位、GPS_PROVIDER GPS定位
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return areaBean;
            }
            Location location = locationManager.getLastKnownLocation(provider);// 调用getLastKnownLocation()方法获取当前的位置信息
            assert location != null;
            double lat = location.getLatitude();//获取纬度
            double lng = location.getLongitude();//获取经度

            Address address = getAddress(lng,lat);
            areaBean.setProvince(address.getAdminArea());
            areaBean.setCity(address.getLocality());
            areaBean.setArea(address.getSubLocality());

        }
        return areaBean;
    }

    private Address getAddress(double lnt , double lat){
        Geocoder geocoder = new Geocoder(context);
        Address address = null;
        try {
            //根据经纬度获取地理位置信息---这里会获取最近的几组地址信息，具体几组由最后一个参数决定
            List<Address> addresses = geocoder.getFromLocation(lat , lnt, 1);

            if (addresses.size() > 0) {
                address = addresses.get(0);

//                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
//                    //每一组地址里面还会有许多地址。这里我取的前2个地址。xxx街道-xxx位置
//                    if(i == 0) {
//                        stringBuilder.append(address.getAddressLine(i)).append("-");
//                    }
//
//                    if(i == 1){
//                        stringBuilder.append(address.getAddressLine(i));
//                        break;
//                    }
//                }
//                stringBuilder.append(address.getCountryName()).append("_");//国家
//                stringBuilder.append(address.getFeatureName()).append("_");//周边地址
//                stringBuilder.append(address.getLocality()).append("_");//市
//                stringBuilder.append(address.getPostalCode()).append("_");
//                stringBuilder.append(address.getCountryCode()).append("_");//国家编码
//                stringBuilder.append(address.getAdminArea()).append("_");//省份
//                stringBuilder.append(address.getSubAdminArea()).append("_");
//                stringBuilder.append(address.getThoroughfare()).append("_");//道路
//                stringBuilder.append(address.getSubLocality()).append("_");//香洲区
//                stringBuilder.append(address.getLatitude()).append("_");//经度
//                stringBuilder.append(address.getLongitude());//维度
                //Log.d("thistt", "地址信息--->" + stringBuilder);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Toast.makeText(context, "报错", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return address;
    }
}
