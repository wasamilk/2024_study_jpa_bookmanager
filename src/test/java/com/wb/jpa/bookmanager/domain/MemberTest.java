package com.wb.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

class MemberTest {
    @Test
    void test() {
        Member member = new Member();
        member.setEmail("test@wasamilk.com");
        member.setName("testNm");

        System.out.println("user>>> " + member);
        System.out.println("user>>> " + member.toString());

        Member buildMember = Member.builder().name("builder").email("builder@wasamilk.com").build();
        System.out.println("buildUser>>> " + buildMember.toString());
    }
}