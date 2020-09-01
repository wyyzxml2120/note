package com.nlk.jyweather.bean;

import java.util.List;

public class CityJsonBean {
    /**
     * id : 1
     * name : 北京
     * citys : [{"id":1,"name":"北京"}]
     */

    private int id;
    private String name;
    private List<CitysBean> citys;

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

    public List<CitysBean> getCitys() {
        return citys;
    }

    public void setCitys(List<CitysBean> citys) {
        this.citys = citys;
    }

    public static class CitysBean {
        /**
         * id : 1
         * name : 北京
         */

        private int id;
        private String name;

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
    }
}
