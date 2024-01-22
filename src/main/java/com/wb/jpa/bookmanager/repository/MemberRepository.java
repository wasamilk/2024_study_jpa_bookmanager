package com.wb.jpa.bookmanager.repository;

import com.wb.jpa.bookmanager.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// JpaRepository<엔티티, 엔티티의 pk>
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // basic 1 ----------------------------------------------------
    /** 쿼리메소드의 리턴값은 다양한 타입 가능 ex)Optional, Set, List... */
//    Optional<Member> findByName(String name);
//    Set<Member> findByName(String name);
    List<Member> findByName(String name);

    /** find...By, get..By 등 모두 같은 동작이므로 코드 가독성에 맞춰서 메소드이름 택1,
     *  ...에 엔티티(사실 엔티티 아니고 아무거나넣어도 됨)넣어도 같은동작 */
    Member findByEmail(String email);
    Member getByEmail(String email);
    Member readByEmail(String email);
    Member queryByEmail(String email);
    Member searchByEmail(String email);
    Member streamByEmail(String email);
    Member findMemberByEmail(String email);
    Member findSomethingByEmail(String email);

    /** First == Top  ,  Last는 제공 X */
    Member findFirst1ByName(String name);
    Member findTop1ByName(String name);
    List<Member> findFirst2ByName(String name);
    List<Member> findTop2ByName(String name);

    // basic 2 ----------------------------------------------------
    List<Member> findByEmailAndName(String email, String name);
    List<Member> findByEmailOrName(String email, String name);

    List<Member> findByCreatedAtAfter(LocalDateTime yesterday);
    List<Member> findByIdAfter(Long id);
    List<Member> findByCreatedAtGreaterThan(LocalDateTime yesterday);
    List<Member> findByIdGreaterThanEqual(Long id);
    List<Member> findByIdBetween(Long id1, Long id2);

    List<Member> findByIdIsNotNull();
//    List<Member> findByAddressIsNotEmpty();

    List<Member> findByNameIn(List<String> names);

    List<Member> findByNameLike(String name);
    List<Member> findByNameStartingWith(String name);
    List<Member> findByNameEndingWith(String name);
    List<Member> findByNameContains(String name);

    // sorting ----------------------------------------------------
    List<Member> findTop1ByNameOrderByIdDescEmailAsc(String name);
    List<Member> findFirstByName(String name, Sort sort);

    // paging ----------------------------------------------------
    Page<Member> findByName(String name, Pageable pageable);
}
