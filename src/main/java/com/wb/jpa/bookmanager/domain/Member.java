package com.wb.jpa.bookmanager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
/* ---- @Data ---- */
//@Setter
//@Getter
//@ToString
//@RequiredArgsConstructor
//@EqualsAndHashCode
/* --------------- */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

//    @NonNull  --> RequiredArgsConstructor
    private String name;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
