package com.akatsuki.dao;

import com.akatsuki.model.Book;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Data access object
 * An simple class for object access
 * register and manager for Book object
 * @see Book
 * @author w.gong
 */
@Repository
public class BookDao {

    private List<Book> list = new ArrayList<>();

    //instance
    private static BookDao INST;

    //ctor
    private BookDao(){
        //dummy data
        list.add(new Book("title1", "author1"));
        list.add(new Book("title2", "author1"));
        list.add(new Book("title3", "author2"));
        list.add(new Book("title4", "author3"));
    }

    public static synchronized BookDao getInstance() {
        if(null == INST) {
            INST = new BookDao();
        }
        return INST;
    }


    /**
     * add a book
     * @param book book to add
     * @return false if add error, true if book added
     */
    public boolean addBook(Book book) {
        if(book == null) {
            return false;
        }
        list.add(book);
        return true;
    }

    /**
     * find a book
     * @param title title of book
     * @return a list of books with the same title and the same author
     */
    @NonNull
    public Collection<Book> getBook(String title, String author) {
        final ArrayList<Book> bookToReturn = new ArrayList<>();
        for (final Book iteaBook : list) {
            if (!StringUtils.isEmpty(title) && title.equals(iteaBook.getTitle())
                    && !StringUtils.isEmpty(author) && author.equals(iteaBook.getAuthor())) {
                //author and title match
                bookToReturn.add(iteaBook);
                continue;
            }
            if (StringUtils.isEmpty(title)
                    && !StringUtils.isEmpty(author) && author.equals(iteaBook.getAuthor())) {
                //only title match
                bookToReturn.add(iteaBook);
                continue;
            }
            if (!StringUtils.isEmpty(title) && title.equals(iteaBook.getTitle())
                    && StringUtils.isEmpty(author)) {
                //only author match
                bookToReturn.add(iteaBook);
                continue;
            }
            if(StringUtils.isEmpty(title) && StringUtils.isEmpty(author)) {
                //return all if all is empty
                bookToReturn.add(iteaBook);
                continue;
            }
        }
        return bookToReturn;
    }

}
