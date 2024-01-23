package com.wb.jpa.bookmanager.domain;

import com.wb.jpa.bookmanager.domain.listener.Auditable;
import com.wb.jpa.bookmanager.domain.listener.MyEntityListener;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@EntityListeners(value = MyEntityListener.class)
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
