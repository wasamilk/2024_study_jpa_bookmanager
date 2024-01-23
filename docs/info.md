### data.sql
>- resources 하위에 만들어두면 JPA가 로딩 시 자동으로 해당 쿼리를 수행해줌
>- src/resources/data.sql 
>- test/resources/data.sql  (테스트 시)


@Entity // 엔티티임을 선언

@Id // pk를 의미

@GeneratedValue // 
-- GenerationType : TABLE, SEQUENCE(oracle), IDENTITY(MySql), AUTO

@Table  // @Table(name="member_legacy")
-- name, catalog, schema, uniqueConstraints, indexes

@Column(nullable=false)
@Column(unique=true)  -- table의 uniqueConstraint는 여러개의 컬럼으로 unique 지정때사용

@Column(insertable=true)
@Column(updatable=true)

-- name, nullable, unique

@Transient -- 영속성처리에서 제외, DB 데이터에 반영X

@Enumerated()  -- EnumType
-- enum을 사용하는 경우 ORDINAL(zero-based) 값으로 저장됨
-- @Enumerated(value=EnumType.STRING) 처리 필요