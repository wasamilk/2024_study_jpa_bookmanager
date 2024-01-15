package com.wb.jpa.bookmanager.domain;

import lombok.*;

import java.time.LocalDateTime;
/* ---- @Data ---- */
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
/* --------------- */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @NonNull
    private String name;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
