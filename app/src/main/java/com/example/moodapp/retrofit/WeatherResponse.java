package com.example.moodapp.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {


    /**
     * lon : 10.99
     * lat : 44.34
     */

    private CoordBean coord;
    /**
     * coord : {"lon":10.99,"lat":44.34}
     * weather : [{"id":501,"main":"Rain","description":"moderate rain","icon":"10d"}]
     * base : stations
     * main : {"temp":298.48,"feels_like":298.74,"temp_min":297.56,"temp_max":300.05,"pressure":1015,"humidity":64,"sea_level":1015,"grnd_level":933}
     * visibility : 10000
     * wind : {"speed":0.62,"deg":349,"gust":1.18}
     * rain : {"1h":3.16}
     * clouds : {"all":100}
     * dt : 1661870592
     * sys : {"type":2,"id":2075663,"country":"IT","sunrise":1661834187,"sunset":1661882248}
     * timezone : 7200
     * id : 3163858
     * name : Zocca
     * cod : 200
     */

    private String base;
    /**
     * temp : 298.48
     * feels_like : 298.74
     * temp_min : 297.56
     * temp_max : 300.05
     * pressure : 1015
     * humidity : 64
     * sea_level : 1015
     * grnd_level : 933
     */

    private MainBean main;
    private int visibility;
    /**
     * speed : 0.62
     * deg : 349
     * gust : 1.18
     */

    private WindBean wind;
    /**
     * 1h : 3.16
     */

    private RainBean rain;
    /**
     * all : 100
     */

    private CloudsBean clouds;
    private int dt;
    /**
     * type : 2
     * id : 2075663
     * country : IT
     * sunrise : 1661834187
     * sunset : 1661882248
     */

    private SysBean sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
    /**
     * id : 501
     * main : Rain
     * description : moderate rain
     * icon : 10d
     */

    private List<WeatherBean> weather;

    public CoordBean getCoord() {
        return coord;
    }

    public void setCoord(CoordBean coord) {
        this.coord = coord;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainBean getMain() {
        return main;
    }

    public void setMain(MainBean main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

    public RainBean getRain() {
        return rain;
    }

    public void setRain(RainBean rain) {
        this.rain = rain;
    }

    public CloudsBean getClouds() {
        return clouds;
    }

    public void setClouds(CloudsBean clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public SysBean getSys() {
        return sys;
    }

    public void setSys(SysBean sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

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

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public List<WeatherBean> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherBean> weather) {
        this.weather = weather;
    }

    public static class CoordBean {
        private double lon;
        private double lat;

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        @Override
        public String toString() {
            return "CoordBean{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }

    public static class MainBean {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;
        private int sea_level;
        private int grnd_level;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(double feels_like) {
            this.feels_like = feels_like;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public int getSea_level() {
            return sea_level;
        }

        public void setSea_level(int sea_level) {
            this.sea_level = sea_level;
        }

        public int getGrnd_level() {
            return grnd_level;
        }

        public void setGrnd_level(int grnd_level) {
            this.grnd_level = grnd_level;
        }

        @Override
        public String toString() {
            return "MainBean{" +
                    "temp=" + temp +
                    ", feels_like=" + feels_like +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", sea_level=" + sea_level +
                    ", grnd_level=" + grnd_level +
                    '}';
        }
    }

    public static class WindBean {
        private double speed;
        private int deg;
        private double gust;

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public int getDeg() {
            return deg;
        }

        public void setDeg(int deg) {
            this.deg = deg;
        }

        public double getGust() {
            return gust;
        }

        public void setGust(double gust) {
            this.gust = gust;
        }

        @Override
        public String toString() {
            return "WindBean{" +
                    "speed=" + speed +
                    ", deg=" + deg +
                    ", gust=" + gust +
                    '}';
        }
    }

    public static class RainBean {
        @SerializedName("1h")
        private double _$1h;

        public double get_$1h() {
            return _$1h;
        }

        public void set_$1h(double _$1h) {
            this._$1h = _$1h;
        }

        @Override
        public String toString() {
            return "RainBean{" +
                    "_$1h=" + _$1h +
                    '}';
        }
    }

    public static class CloudsBean {
        private int all;

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        @Override
        public String toString() {
            return "CloudsBean{" +
                    "all=" + all +
                    '}';
        }
    }

    public static class SysBean {
        private int type;
        private int id;
        private String country;
        private int sunrise;
        private int sunset;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getSunrise() {
            return sunrise;
        }

        public void setSunrise(int sunrise) {
            this.sunrise = sunrise;
        }

        public int getSunset() {
            return sunset;
        }

        public void setSunset(int sunset) {
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            return "SysBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", country='" + country + '\'' +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }

    public static class WeatherBean {
        private int id;
        private String main;
        private String description;
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        @Override
        public String toString() {
            return "WeatherBean{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    public WeatherResponse(CoordBean coord, String base, MainBean main, int visibility, WindBean wind, RainBean rain, CloudsBean clouds, int dt, SysBean sys, int timezone, int id, String name, int cod, List<WeatherBean> weather) {
        this.coord = coord;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.rain = rain;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.timezone = timezone;
        this.id = id;
        this.name = name;
        this.cod = cod;
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "coord=" + coord +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", rain=" + rain +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                ", weather=" + weather +
                '}';
    }
}
