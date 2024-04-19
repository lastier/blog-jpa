package com.estsoft.blogjpa.tdd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    Account account;

    @BeforeEach
    void setUp(){
        account = new Account(10000);
    }


    @DisplayName("잔액 조회)")
    @Test
    void 잔고_조회(){
        assertEquals(10000, account.getBalance());

        account = new Account(1000);
        assertEquals(1000, account.getBalance());

        account = new Account(0);
        assertEquals(0, account.getBalance());
    }

    @ Test
    void 계좌_입급(){
        account.deposit(1000);
        assertEquals(account.getBalance(), 11000);
    }

    @Test
    void 계좌_출금(){
        account.withdraw(1000);
        assertEquals(9000, account.getBalance());
        Assertions.assertThat(account.getBalance()).isEqualTo(9000);
    }
}
