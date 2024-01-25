package com.wb.jpa.bookmanager.repository;

import com.wb.jpa.bookmanager.domain.Book;
import com.wb.jpa.bookmanager.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EntityListenerTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberHistoryRepository memberHistoryRepository;

    @Test
    void listenerTest(){
        Member member = new Member();

        member.setName("woobin");
        member.setEmail("woobin@wasamilk.com");

        // >>> prePersist
        memberRepository.save(member);
        System.out.println(memberRepository.findByEmail("woobin@wasamilk.com"));
        // >>> postPersist

        Member member2 = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        // >>> postLoad

        member2.setName("maaaaaaaaaaatim");

        // >>> preUpdate
        memberRepository.save(member2);
        // >>> postUpdate

        // >>> preRemove
        memberRepository.deleteById(4L);
        // >>> postRemove
    }

    @Test
    void listenerTest2(){
        Book book = new Book();
        book.setName("테스트북");
        book.setAuthorId(1L);

        bookRepository.save(book);
    }

    @Test
    void listenerTest3(){
        Member member = new Member();
        member.setName("woobin");
        member.setEmail("woobin@wasamilk.com");

        memberRepository.save(member);

        member.setName("woobinNew");
        memberRepository.save(member);

        memberHistoryRepository.findAll().forEach(System.out::println);
    }
}