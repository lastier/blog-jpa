package com.estsoft.blogjpa.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @DisplayName("쿠폰 발급 성공")
    @Test
    public void testAddCoupon(){
        User user = new User("area00", "pw");
        assertEquals(0, user.getTotalCouponCount());

        ICoupon coupon = Mockito.mock(ICoupon.class);
        BDDMockito.given(coupon.isValid())
                        .willReturn(true);

        user.addCoupon(coupon);
        assertEquals(1, user.getTotalCouponCount());
        System.out.println("보유 쿠폰 :" +user.getTotalCouponCount());
    }

    @DisplayName("쿠폰 발급 실패 (유효하지 않은 쿠폰")
    @Test
    public void testNotAddCoupon(){
        User user = new User("area00", "pw");
        assertEquals(0, user.getTotalCouponCount());

        ICoupon couponX = Mockito.mock(ICoupon.class);
        BDDMockito.given(couponX.isValid())
                .willReturn(false);

        user.addCoupon(couponX);
        assertEquals(0, user.getTotalCouponCount());
        System.out.println("보유 쿠폰 : "+user.getTotalCouponCount());
    }
}
