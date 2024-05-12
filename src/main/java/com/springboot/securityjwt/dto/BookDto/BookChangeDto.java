package com.springboot.securityjwt.dto.BookDto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookChangeDto {
    private Long id;
    private String title;
    private String author;
}