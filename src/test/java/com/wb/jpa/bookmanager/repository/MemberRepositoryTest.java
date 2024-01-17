package com.wb.jpa.bookmanager.repository;

import com.wb.jpa.bookmanager.domain.Member;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

/**
 * Repository CRUD + paging 테스트
 */
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveTest(){
        Member member1 = new Member("angela", "angela@wasamilk.com");
        Member member2 = new Member("tomas", "tomas@wasamilk.com");
        memberRepository.saveAll(Lists.newArrayList(member1, member2));

        List<Member> members
                = memberRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        members.forEach(System.out::println);
    }

    /**
     * getOne()은 lazyLoading 지원이라 @Transactional 필요
     * getOne의 구현체를 보면 reference값만 우선 가져오는 걸 알 수 있음
     * findById의 경우 entity manager를 통해 값을 직접 가져옴
     */
    @Test
    @Transactional
    void getOneTest(){
        Member member = memberRepository.getOne(1L);
        System.out.println(member);
    }

    /**
     * findById() : 리턴타입이 Optinal이라 Optional로 받거나 추가처리 필요
     * Optional -  https://mangkyu.tistory.com/70
     */
    @Test
    void findByIdTest(){
        Optional<Member> member = memberRepository.findById(1L);
        Member memberNoOptional = memberRepository.findById(1L).orElse(null);
        System.out.println(member);
        System.out.println(memberNoOptional);
    }

    /** [save() >> flush()] == saveAndFlush() */
    @Test
    void flushTest(){
        Member member1 = new Member("angela", "angela@wasamilk.com");
        Member member2 = new Member("tomas", "tomas@wasamilk.com");

        memberRepository.save(member1);
        memberRepository.flush();
        memberRepository.saveAndFlush(member2);

        memberRepository.findAll().forEach(System.out::println);
    }

    @Test
    void countTest(){
        long count = memberRepository.count();
        System.out.println("count >> " + count );
    }

    /** existsById() 리턴값은 boolean이지만 실행쿼리를 보면 결국 count로 존재여부 판별 */
    @Test
    void existsTest(){
        boolean exists = memberRepository.existsById(1L);
        System.out.println("exists >> " + exists );
    }

    /**
     * delete - select 2번 실행, 엔티티로 id 찾아서 id로 엔티티 찾아서 delete
     * delete - select 1번 실행, id로 엔티티 찾아서 delete
     */
    @Test
    void deleteTest(){
        // delete()의 Params: entity – must not be null. 이므로
        // orElse(null) 보단 .orElseThrow 처리가 좋음
        memberRepository.delete(memberRepository.findById(1L).orElseThrow(RuntimeException::new));
        memberRepository.deleteById(1L);
    }

    /**
     * deleteAll()의 경우 엔티티존재여부를 전부 select 해보기때문에 성능이슈 있을 수 있음
     * deleteAllInBatch()의 경우 select 없이 바로 delete 실행
     */
    @Test
    void deleteAllTest(){
        // 파라미터 주면 쿼리에서 in 사용해서 조회 후 해당값만 delete 가능
        memberRepository.deleteAll(memberRepository.findAllById(Lists.newArrayList(1L, 3L)));
        memberRepository.deleteAll();

        memberRepository.deleteAllInBatch(memberRepository.findAllById(Lists.newArrayList(1L, 3L)));
        memberRepository.deleteAllInBatch();

        memberRepository.findAll().forEach(System.out::println);
    }

    /**
     *  https://cs-ssupport.tistory.com/509
     * param pageNumber - zero based
     */
    @Test
    void pagingTest(){
        Page<Member> members = memberRepository.findAll(PageRequest.of(0,3));
        System.out.println("page : " + members);
        System.out.println("총 데이터 개수 [TotalElements] -- " + members.getTotalElements());
        System.out.println("총 페이지 [TotalPages] --------- " + members.getTotalPages());
        System.out.println("현재 페이지 [Number] ----------- " + members.getNumber());
        System.out.println("현재 페이지 데이터 개수 [NumberOfElements] " + members.getNumberOfElements());
        System.out.println("정렬 [Sort] ------------------ " + members.getSort());
        System.out.println("페이지 당 데이터 개수 [Size] ----------" + members.getSize());

        members.getContent().forEach(System.out::println);
    }

    /**
     * example은 그냥 참고정도만.. 검색에 한계가 있어서 잘 안쓰임
     */
    @Test
    void exampleTest(){
        // v1) matcher와 probe로 example 하면 matcher 조건으로 where절 생성
        //      .withIgnorePaths("name")는 생성자에 이름이 필수값이라 추가적으로 처리함
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("name")
//                .withMatcher("email", endsWith());
//        Example<Member> example = Example.of(new Member("ma", "wasamilk.com"), matcher);


        // v2) matcher 없이 probe만 가지고 example 하면 where a=b 로 조회
//         Example<Member> example = Example.of(new Member("martin", "wasamilk.com"));


        // v3) 꼭 생성자를 통해 probe를 만들필요는 없음
        Member member = new Member();
        member.setEmail("slow");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("email", contains());
        Example<Member> example = Example.of(member, matcher);

        memberRepository.findAll(example).forEach(System.out::println);
    }

    /**
     * update의 경우 SimpleJpaRepository를 확인해보면, save 수행 시 엔티티의 id값이 존재할 시 update 됨
     */
    @Test
    void updateTest(){
        memberRepository.save(new Member("david", "david@wasamilk.com"));

        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        member.setEmail("martin-update@wasamilk.com");

        memberRepository.save(member);
    }
}