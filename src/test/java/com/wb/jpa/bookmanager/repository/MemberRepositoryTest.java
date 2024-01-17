package com.wb.jpa.bookmanager.repository;

import com.wb.jpa.bookmanager.domain.Member;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void crud(){
        Member member1 = new Member("angela", "angela@wasamilk.com");
        Member member2 = new Member("tomas", "tomas@wasamilk.com");

        memberRepository.saveAll(Lists.newArrayList(member1, member2));

        List<Member> members
                = memberRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        members.forEach(System.out::println);
    }
}