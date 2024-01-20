package com.wb.jpa.bookmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest2 {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void select(){
        System.out.println(memberRepository.findByName("martin"));
    }
}