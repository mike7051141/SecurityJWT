package com.springboot.securityjwt.dto.BookDto;

import com.springboot.securityjwt.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private User user;
}