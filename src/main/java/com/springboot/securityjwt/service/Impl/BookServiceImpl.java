package com.springboot.securityjwt.service.Impl;
import com.springboot.securityjwt.dao.BookDao.BookDAO;
import com.springboot.securityjwt.dto.BookDto.BookChangeDto;
import com.springboot.securityjwt.dto.BookDto.BookRequestDto;
import com.springboot.securityjwt.dto.BookDto.BookResponseDto;
import com.springboot.securityjwt.entity.Book;
import com.springboot.securityjwt.repository.BoardRepository;
import com.springboot.securityjwt.repository.BookRepository;
import com.springboot.securityjwt.service.BoardService;
import com.springboot.securityjwt.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookServiceImpl implements BookService {
    private Logger logger = LoggerFactory.getLogger(BoardService.class);

    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    private BookResponseDto getBookResponseDto(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setAuthor(book.getAuthor());
        bookResponseDto.setCreateAt(book.getCreateDate());
        bookResponseDto.setUpdateAt(book.getUpdateDate());
        return bookResponseDto;
    }

    @Override
    public BookResponseDto getBook(String title) throws Exception {
        logger.info("[getBook] : 책 찾기");
        Book book = bookDAO.selectBook(title);

        return getBookResponseDto(book);
    }

    @Override
    public BookResponseDto saveBook(BookRequestDto bookRequestDto) {
        logger.info("[saveBook] 책 추가 : {}",bookRequestDto.toString());

        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setCreateDate(LocalDateTime.now());
        book.setUpdateDate(LocalDateTime.now());

        Book savedBook = bookDAO.insertBook(book);

        return getBookResponseDto(savedBook);
    }



    @Override
    public BookResponseDto changeBook(BookChangeDto bookChangeDto) throws Exception {
        logger.info("[changeBook] 책 수정 : {}");
        
        Book findBook = bookDAO.updateBook(bookChangeDto.getTitle(), bookChangeDto.getAuthor());
        findBook.setUpdateDate(LocalDateTime.now());
        return getBookResponseDto(findBook);
    }

    @Override
    public void deleteBook(String title) throws Exception {
        logger.info("[deleteBook] : 책 삭제");
        bookDAO.deleteBook(title);
    }


}
