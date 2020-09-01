package com.nlk.jyweather.bean;

import java.util.List;

public class AreaJsonBean {
    /**
     * area : [{"id":1,"name":"北京","weather_id":"CN101010100"},{"id":2,"name":"海淀","weather_id":"CN101010200"},{"id":3,"name":"朝阳","weather_id":"CN101010300"},{"id":4,"name":"顺义","weather_id":"CN101010400"},{"id":5,"name":"怀柔","weather_id":"CN101010500"},{"id":6,"name":"通州","weather_id":"CN101010600"},{"id":7,"name":"昌平","weather_id":"CN101010700"},{"id":8,"name":"延庆","weather_id":"CN101010800"},{"id":9,"name":"丰台","weather_id":"CN101010900"},{"id":10,"name":"石景山","weather_id":"CN101011000"},{"id":11,"name":"大兴","weather_id":"CN101011100"},{"id":12,"name":"房山","weather_id":"CN101011200"},{"id":13,"name":"密云","weather_id":"CN101011300"},{"id":14,"name":"门头沟","weather_id":"CN101011400"},{"id":15,"name":"平谷","weather_id":"CN101011500"}]
     * id : 1
     */

    private int id;
    private List<AreaBean> area;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<AreaBean> getArea() {
        return area;
    }

    public void setArea(List<AreaBean> area) {
        this.area = area;
    }

    public static class AreaBean {
        /**
         * id : 1
         * name : 北京
         * weather_id : CN101010100
         */

        private int id;
        private String name;
        private String weather_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWeather_id() {
            return weather_id;
        }

        public void setWeather_id(String weather_id) {
            this.weather_id = weather_id;
        }
    }
}
