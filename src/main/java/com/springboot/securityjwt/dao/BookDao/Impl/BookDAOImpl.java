package com.springboot.securityjwt.dao.BookDao.Impl;

import com.springboot.securityjwt.dao.BookDao.BookDAO;
import com.springboot.securityjwt.entity.Book;
import com.springboot.securityjwt.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class BookDAOImpl implements BookDAO {
    private final BookRepository bookRepository;

    @Autowired
    public BookDAOImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book insertBook(Book book) {
        Book result = bookRepository.save(book);
        return result;
    }

    @Override
    public Book selectBook(String title) throws Exception {
        Optional<Book> book = bookRepository.findByTitle(title);

        if(book.isPresent()){
            Book result = book.get();
            return result;
        }else {
            throw new Exception("책을 찾을 수 없음");
        }
    }

    @Override
    public Book updateBook(String title, String author) throws Exception {
        Optional<Book> findBook = bookRepository.findByTitle(title);

        Book result;

        if (findBook != null) {
            Book book = findBook.get();

            book.setTitle(title);
            book.setAuthor(author);
            book.setUpdateDate(LocalDateTime.now());

            result=bookRepository.save(book);
        }else {
            throw new Exception("책을 찾을 수 없음");
        }
        return result;
    }

    @Override
    public void deleteBook(String title) throws Exception {
        Optional<Book> findBook = bookRepository.findByTitle(title);

        if (findBook != null) {
            Book book = findBook.get();
            bookRepository.delete(book);
        }else {
            throw new Exception("책을 찾을 수 없음");
        }

    }
}
