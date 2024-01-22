package com.wb.jpa.bookmanager.repository;

import com.wb.jpa.bookmanager.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest2 {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void basic(){
        // 쿼리메소드의 리턴값은 다양한 타입 가능 ex)Optional, Set, List...
        System.out.println(memberRepository.findByName("martin"));

        System.out.println(memberRepository.findByEmail("martin@wasamilk.com"));
        System.out.println(memberRepository.getByEmail("martin@wasamilk.com"));
        System.out.println(memberRepository.readByEmail("martin@wasamilk.com"));
        System.out.println(memberRepository.queryByEmail("martin@wasamilk.com"));
        System.out.println(memberRepository.searchByEmail("martin@wasamilk.com"));
        System.out.println(memberRepository.streamByEmail("martin@wasamilk.com"));
        System.out.println(memberRepository.findMemberByEmail("martin@wasamilk.com"));
        System.out.println(memberRepository.findSomethingByEmail("martin@wasamilk.com"));

        System.out.println("findFirst1ByName >> " + memberRepository.findFirst1ByName("martin"));
        System.out.println("findTop1ByName >> " + memberRepository.findTop1ByName("martin"));
        System.out.println("findFirst2ByName >> " + memberRepository.findFirst2ByName("martin"));
        System.out.println("findTop2ByName >> " + memberRepository.findTop2ByName("martin"));
    }
    
    @Test
    void basic2(){
    }
}