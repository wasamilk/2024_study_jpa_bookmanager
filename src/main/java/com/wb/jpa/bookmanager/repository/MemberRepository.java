package com.wb.jpa.bookmanager.repository;

import com.wb.jpa.bookmanager.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

// JpaRepository<엔티티, 엔티티의 pk>
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
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
}
