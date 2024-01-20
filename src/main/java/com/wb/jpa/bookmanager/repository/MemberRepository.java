package com.wb.jpa.bookmanager.repository;

import com.wb.jpa.bookmanager.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository<엔티티, 엔티티의 pk>
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
}
