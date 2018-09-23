package com.akatsuki.controller;


import com.akatsuki.dao.BookDao;
import com.akatsuki.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Controller of Book
 * @see Book
 * @author w.gong
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookDao bookDao = BookDao.getInstance();

    @RequestMapping(method = RequestMethod.POST)
    public Book addBook(@RequestParam(value = "title") String title,
                        @RequestParam(value = "author") String author) {
        Book book = new Book(title, author);
        bookDao.addBook(book);
        return book;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Book> getBook(@RequestParam(value="title", defaultValue = "") String title,
                        @RequestParam(value="author", defaultValue = "") String author) {
        Collection<Book> books = bookDao.getBook(title, author);
        return books;
    }
}
