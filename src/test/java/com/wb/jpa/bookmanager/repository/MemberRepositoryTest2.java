package com.wb.jpa.bookmanager.repository;

import com.wb.jpa.bookmanager.domain.Gender;
import com.wb.jpa.bookmanager.domain.Member;
import org.assertj.core.util.Lists;
import org.hibernate.query.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

@SpringBootTest
class MemberRepositoryTest2 {
    @Autowired
    private MemberRepository memberRepository;

    /**
     * 1.쿼리메소드의 리턴값은 다양한 타입 가능 ex)Optional, Set, List...
     * 2. find...By, get..By 등 모두 같은 동작이므로 코드 가독성에 맞춰서 메소드이름 택1,
     *     ...에 엔티티(사실 엔티티 아니고 아무거나넣어도 됨)넣어도 같은동작
     * 3.  First == Top 제공 ,  Last는 제공 X
     * */
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

    /**
     * 좀 더 복잡한 쿼리 메서드
     */
    @Test
    void basic2(){
        /** AND, OR */
        System.out.println("findByEmailAndName >> " + memberRepository.findByEmailAndName("martin@wasamilk.com", "martin"));
        System.out.println("findByEmailOrName >> " + memberRepository.findByEmailOrName("martin@wasamilk.com", "james"));

        
        /** 날짜, 숫자 크기비교 */
        // After(>), Before(<) -- 숫자컬럼도 가능하지만 가독성을 위해 날짜와 시간에만 사용하는것이 좋을 것 같음
        System.out.println("findByCreatedAtAfter >> " + memberRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter >> " + memberRepository.findByIdAfter(1L));

        // GreaterThan, Between
        System.out.println("findByCreatedAtGreaterThan >> " + memberRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdGreaterThanEqual >> " + memberRepository.findByIdGreaterThanEqual(3L));
        System.out.println("findByIdBetween >> " + memberRepository.findByIdBetween(2L, 4L));

        /** IsNull, IsEmpty */
        System.out.println("findByIdIsNotNull >> " + memberRepository.findByIdIsNotNull());
        // isEmpty는  name!='' 은 아님
//        System.out.println("findByAddressIsNotEmpty >> " + memberRepository.findByAddressIsNotEmpty());

        /** In */
        System.out.println("findByNameIn >> " + memberRepository.findByNameIn(Lists.newArrayList("martin","james")));

        /** like */
        System.out.println("findByNameLike >> " + memberRepository.findByNameLike("%rti%"));
        System.out.println("findByNameLike >> " + memberRepository.findByNameLike("rti"));
        System.out.println("findByNameStartingWith >> " + memberRepository.findByNameStartingWith("mar"));
        System.out.println("findByNameEndingWith >> " + memberRepository.findByNameEndingWith("tin"));
        System.out.println("findByNameContains >> " + memberRepository.findByNameContains("rti"));
    }

    /** 정렬조건 추가는 AND 안붙이고 바로 컬럼+정렬기준  (ex. rderByIdDescEmailAsc) */
    @Test
    void sorting(){
        System.out.println("findTop1ByNameOrderByIdDescEmailAsc >> " + memberRepository.findTop1ByNameOrderByIdDescEmailAsc("martin"));

        // 가독성 혹은 코드 재사용성을 높이기 위해서 Sort를 사용하는것도 좋음
        System.out.println("findFirstByName >> " + memberRepository.findFirstByName("martin", Sort.by(Sort.Direction.DESC, "id")));
    }

    @Test
    void paging(){
        System.out.println("findByName >> " + memberRepository.findByName("martin", PageRequest.of(0,1,Sort.by(Sort.Direction.DESC, "id"))));
    }

    @Test
    void insertAndUpdateTest(){
        Member member = new Member();
        member.setName("martin");
        member.setEmail("martin2@wasamilk.com");

        memberRepository.save(member);

        Member member2 = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member2.setName("maaaaaaaaaaaartin");

        memberRepository.save(member2);
    }

    @Test
    void enumTest(){
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member.setGender(Gender.MALE);

        memberRepository.save(member);

        memberRepository.findAll().forEach(System.out::println);

        System.out.println(memberRepository.findRawRecord().get("gender"));
    }
}