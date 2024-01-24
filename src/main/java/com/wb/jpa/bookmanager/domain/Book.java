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
public class Book extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String author;
}
