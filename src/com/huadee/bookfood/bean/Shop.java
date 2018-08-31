package com.huadee.bookfood.bean;

import java.util.List;

public class Shop {
    private int shop_id;
    private String id_card;
    private String business_license;
    private String cater_permit;
    private String shop_out_photo;
    private String shop_inner_photo;
    private String shop_name;
    private String contact;
    private String phone;
    private String desc;
    private String address;
    private int seller_id;
    private int status;
    private List<Dish> dishList;


    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }


    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getBusiness_license() {
        return business_license;
    }

    public void setBusiness_license(String business_license) {
        this.business_license = business_license;
    }

    public String getCater_permit() {
        return cater_permit;
    }

    public void setCater_permit(String cater_permit) {
        this.cater_permit = cater_permit;
    }

    public String getShop_out_photo() {
        return shop_out_photo;
    }

    public void setShop_out_photo(String shop_out_photo) {
        this.shop_out_photo = shop_out_photo;
    }

    public String getShop_inner_photo() {
        return shop_inner_photo;
    }

    public void setShop_inner_photo(String shop_inner_photo) {
        this.shop_inner_photo = shop_inner_photo;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
