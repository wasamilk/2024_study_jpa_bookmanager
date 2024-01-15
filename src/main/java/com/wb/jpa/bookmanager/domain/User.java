package com.wb.jpa.bookmanager.domain;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class User {
    @NonNull
    private String name;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
