package com.springboot.securityjwt.controller;

import com.springboot.securityjwt.dto.BoardDto.BoardChangeDto;
import com.springboot.securityjwt.dto.BoardDto.BoardRequestDto;
import com.springboot.securityjwt.dto.BoardDto.BoardResponseDto;
import com.springboot.securityjwt.dto.BookDto.BookChangeDto;
import com.springboot.securityjwt.dto.BookDto.BookRequestDto;
import com.springboot.securityjwt.dto.BookDto.BookResponseDto;
import com.springboot.securityjwt.service.BookService;
import io.swagger.annotations.ApiImplicitParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-api")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiImplicitParam(name = "X-AUTH-TOKEN",value="로그인 성공 후 발급 받은 access_token", required = true, dataType ="String",paramType = "header")
    @GetMapping("/get")
    public ResponseEntity<BookResponseDto> getBook(String title) throws Exception {
        BookResponseDto bookResponseDto = bookService.getBook(title);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDto);
    }
    @ApiImplicitParam(name = "X-AUTH-TOKEN",value="로그인 성공 후 발급 받은 access_token", required = true, dataType ="String",paramType = "header")
    @PostMapping("/save")
    public ResponseEntity<BookResponseDto> saveBook(@RequestBody BookRequestDto bookRequestDto ){
        BookResponseDto bookResponseDto = bookService.saveBook(bookRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDto);
    }
    @ApiImplicitParam(name = "X-AUTH-TOKEN",value="로그인 성공 후 발급 받은 access_token", required = true, dataType ="String",paramType = "header")
    @PutMapping("/change")
    public ResponseEntity<BookResponseDto> changeBook(@RequestBody BookChangeDto bookChangeDto) throws Exception {
        BookResponseDto bookResponseDto = bookService.changeBook(bookChangeDto);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDto);
    }
    @ApiImplicitParam(name = "X-AUTH-TOKEN",value="로그인 성공 후 발급 받은 access_token", required = true, dataType ="String",paramType = "header")
    @DeleteMapping("/deleteBook")
    public ResponseEntity<String> deleteBook(String title) throws Exception {
        bookService.deleteBook(title);
        return ResponseEntity.status(HttpStatus.OK).body("삭제완료");
    }
}