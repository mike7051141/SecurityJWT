package com.springboot.securityjwt.dao.BookDao;

import com.springboot.securityjwt.entity.Book;

public interface BookDAO {
    Book insertBook(Book book);

    Book selectBook(String title) throws Exception;

    Book updateBook(String title, String author) throws Exception;

    void deleteBook(String title) throws Exception;
}
