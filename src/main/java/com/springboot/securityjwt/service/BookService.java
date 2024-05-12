package com.springboot.securityjwt.service;

import com.springboot.securityjwt.dto.BookDto.BookChangeDto;
import com.springboot.securityjwt.dto.BookDto.BookRequestDto;
import com.springboot.securityjwt.dto.BookDto.BookResponseDto;


public interface BookService {
    BookResponseDto getBook(String title) throws Exception;
    BookResponseDto saveBook(BookRequestDto bookRequestDto);
    BookResponseDto changeBook(BookChangeDto bookChangeDto) throws Exception;
    void deleteBook(String title) throws Exception;
}
