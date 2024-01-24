package com.wb.jpa.bookmanager.domain;

import com.wb.jpa.bookmanager.domain.listener.MemberEntityListener;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


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

}
