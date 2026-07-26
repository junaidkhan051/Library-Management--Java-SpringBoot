package com.LibraryManagement.LibraryManagement.Repository;

import com.LibraryManagement.LibraryManagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
     Book findById(long id);

    long id(Long id);

    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    Book findByTitleAndAuthor(String title, String author);
    Book findByPublisher(String publisher);
}
