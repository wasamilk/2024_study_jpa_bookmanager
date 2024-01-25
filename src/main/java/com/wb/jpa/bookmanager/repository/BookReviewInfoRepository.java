package com.wb.jpa.bookmanager.repository;

import com.wb.jpa.bookmanager.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewInfoRepository extends JpaRepository<BookReviewInfo, Long> {

}
