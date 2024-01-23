package com.wb.jpa.bookmanager.repository;

import com.wb.jpa.bookmanager.domain.Member;
import com.wb.jpa.bookmanager.domain.MemberHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

// JpaRepository<엔티티, 엔티티의 pk>
@Repository
public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
}
