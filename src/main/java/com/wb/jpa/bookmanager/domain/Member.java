package com.wb.jpa.bookmanager.domain;

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

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

}
