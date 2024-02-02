### JPA

JOIN

@OnnToOne

    // optional = false >> 존재여부 필수(not null) >> innerJoin
    // optional = true(default) >> 존재여부 필수X(nullable) >> outerJoin
    @OneToOne(optional = false)
    private Book book;  // table 컬럼은 bookId 로 create됨..!
    //private Long bookId;