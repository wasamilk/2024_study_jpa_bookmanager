package com.wb.jpa.bookmanager.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class BookReviewInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // optional = false >> 존재여부 필수(not null) >> innerJoin
    // optional = true(default) >> 존재여부 필수X(nullable) >> outerJoin
    @OneToOne(optional = false)
    private Book book;  // table 컬럼은 bookId 로 create됨..!
    //private Long bookId;

    private float averageReviewScore;

    private int reviewCount;
}
