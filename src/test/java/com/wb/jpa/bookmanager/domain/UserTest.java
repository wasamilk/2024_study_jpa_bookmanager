package com.wb.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void test() {
        User user = new User();
        user.setEmail("test@namver.com");
        user.setName("testNm");

        System.out.println("user>>> " + user);
        System.out.println("user>>> " + user.toString());

        User buildUser = User.builder().name("builder").email("builder@naver.com").build();
        System.out.println("buildUser>>> " + buildUser.toString());
    }
}