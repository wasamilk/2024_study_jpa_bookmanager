package com.wb.jpa.bookmanager.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class MemberHistory extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private Long memberId;

    private String name;

    private String email;

}
