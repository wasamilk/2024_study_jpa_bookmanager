
### @Data
> @Setter 
> @Getter
> @ToString
> @EqualsAndHashCode



### data.sql
>- resources 하위에 만들어두면 JPA가 로딩 시 자동으로 해당 쿼리를 수행해줌
>- src/resources/data.sql 
>- test/resources/data.sql  (테스트 시)



### JPA annotation
>- @Entity // 엔티티임을 선언

>- @Id // pk를 의미

>- @GeneratedValue // 
>- -- GenerationType : TABLE, SEQUENCE(oracle), IDENTITY(MySql), AUTO

>- @Table  // @Table(name="member_legacy")
>- -- name, catalog, schema, uniqueConstraints, indexes

>- @Column(nullable=false)
>- @Column(unique=true)  -- table의 uniqueConstraint는 여러개의 컬럼으로 unique 지정때사용

>- @Column(insertable=true)
>- @Column(updatable=true)

>- -- name, nullable, unique

>- @Transient -- 영속성처리에서 제외, DB 데이터에 반영X

>- @Enumerated()  -- EnumType
>- -- enum을 사용하는 경우 ORDINAL(zero-based) 값으로 저장됨
>- -- @Enumerated(value=EnumType.STRING) 처리 필요

 
### entity listener
```java
// pre-before, post-after
    @PrePersist
    public void prePersist(){
        this.createdAt=LocalDateTime.now();
        this.updatedAt=LocalDateTime.now();
        System.out.println(">>> prePersist");
    }
    @PreUpdate
    public void preUpdate(){
        this.updatedAt=LocalDateTime.now();
        System.out.println(">>> preUpdate");
    }
    @PreRemove
    public void preRemove(){
        System.out.println(">>> preRemove");
    }
    @PostPersist
    public void postPersist(){
        System.out.println(">>> postPersist");
    }
    @PostUpdate
    public void postUpdate(){
        System.out.println(">>> postUpdate");
    }
    @PostRemove
    public void postRemove(){
        System.out.println(">>> postRemove");
    }
    @PostLoad
    public void postLoad(){
        System.out.println(">>> postLoad");
    }
```


### Listener 직접생성 후 사용
```java
// 리스너 생성
public class MyEntityListener {
    @PrePersist
    public void prePersist(Object o) {
        if(o instanceof Auditable){
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o) {
        if(o instanceof Auditable){
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}


// 엔티티에 @리스너
@Entity
@NoArgsConstructor
@Data
@EntityListeners(value = MyEntityListener.class)
//@EntityListeners(value = {MyEntityListener.class, MemberEntityListener.class})
public class MemberHistory implements Auditable {
    @Id
    @GeneratedValue
    private Long id;

    private Long MemberId;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}


public interface Auditable {
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(LocalDateTime updatedAt);
}

```
