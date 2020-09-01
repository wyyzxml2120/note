package com.nlk.jyweather;

import android.app.Activity;
import android.content.res.AssetManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nlk.jyweather.bean.AreaBean;
import com.nlk.jyweather.bean.AreaJsonBean;
import com.nlk.jyweather.bean.CityJsonBean;
import com.nlk.jyweather.bean.WeatherBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class JYWeather {

    Activity context;
    String key;

    public JYWeather(Activity context,String key) {
        this.context = context;
        this.key = key;
    }

    public String getWeather(){
        //城市id
        int cityId = 0;
        String weatherId = "";
        WeatherBean weatherBean = null;

        //先获取省、市、区（县）
        AreaBean areaBean = new JYLocation(context).getWeatherLocation();
        //去掉 省 市 区 三个字
        areaBean.setProvince(areaBean.getProvince().replace("省",""));
        areaBean.setCity(areaBean.getCity().replace("市",""));
        areaBean.setArea(areaBean.getArea().replace("区",""));
        areaBean.setArea(areaBean.getArea().replace("县",""));

        //解析city.json
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("city.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<CityJsonBean> listCityJson = JSONArray.parseArray(stringBuilder.toString(), CityJsonBean.class);

        //通过循环查找出cityId
        for (int i=0;i<listCityJson.size();i++){
            if (listCityJson.get(i).getName().equals(areaBean.getProvince())){
                //已经查找到省id
                for (int j=0; j<listCityJson.get(i).getCitys().size();j++){
                    if (listCityJson.get(i).getCitys().get(j).getName().equals(areaBean.getCity())){
                        //已经查找到市的id 接下来根据市的id去查区县
                        cityId = listCityJson.get(i).getCitys().get(j).getId();
                        break;
                    }
                }
            }
        }

        //加载第二个数据json
        StringBuilder stringBuilder2 = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("area.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder2.append(line);
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<AreaJsonBean> listAreaJson = JSONArray.parseArray(stringBuilder2.toString(), AreaJsonBean.class);
        //循环出所在区县的weatherId
        for (int k=0;k<listAreaJson.size();k++){
            if (listAreaJson.get(k).getId() == cityId){
                for (int m=0;m<listAreaJson.get(k).getArea().size();m++){
                    if (listAreaJson.get(k).getArea().get(m).getName().equals(areaBean.getArea())){
                        weatherId = listAreaJson.get(k).getArea().get(m).getWeather_id();
                        break;
                    }
                }
            }
        }

        //通过weatherId进行网络查询对应天气
        try {
            URL url = new URL("http://guolin.tech/api/weather?cityid="+weatherId+"&key="+key);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream in = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                response.append(line);
            }

            weatherBean = JSON.parseObject(response.toString(), WeatherBean.class);
            in.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return weatherBean != null? weatherBean.getHeWeather().get(0).getNow().getCond_txt()+"-"+weatherBean.getHeWeather().get(0).getNow().getWind_dir() : "";
    }

    public void test(){

    }
}
