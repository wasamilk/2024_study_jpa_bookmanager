package com.wb.jpa.bookmanager.domain;

import com.wb.jpa.bookmanager.domain.listener.MemberEntityListener;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/* ---- @Data ---- */
//@Setter
//@Getter
//@ToString
//@EqualsAndHashCode
/* --------------- */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "member",
        indexes = {@Index(columnList = "name")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@EntityListeners(value = MemberEntityListener.class)
//@EntityListeners(value = {MyEntityListener.class, MemberEntityListener.class})
public class Member {

    @Id
//    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_generator")
    @SequenceGenerator(name = "member_generator", sequenceName = "member_seq", allocationSize = 1)
    private Long id;

    @NonNull  //--> RequiredArgsConstructor
    private String name;

    @NonNull
    private String email;

    @Column
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Enumerated(value=EnumType.STRING)
    private Gender gender;

    @Transient
    private String testData;
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

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


}
