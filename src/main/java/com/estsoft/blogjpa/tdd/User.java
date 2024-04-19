package com.estsoft.blogjpa.tdd;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String pw;
    private List<ICoupon> couponList;
    public User(String id, String pw){
        this.id = id;
        this.pw = pw;
        couponList = new ArrayList<>();
    }

    public int getTotalCouponCount() {
        return couponList.size();
    }

    public void addCoupon(ICoupon coupon) {
        if(coupon.isValid()) {
            couponList.add(coupon);
        }
    }
}
