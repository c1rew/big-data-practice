package com.viper.demo.domain;

/**
 * 地址管理类
 * @author c1rew
 * @create 2021-02-19 18:02
 */
public class AddressDO {

    /**
     * 地址id
     */
    private long id;
    /**
     * 街道
     */
    private String street;
    /**
     * 城市
     */
    private String city;
    /**
     * 国家
     */
    private String country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
