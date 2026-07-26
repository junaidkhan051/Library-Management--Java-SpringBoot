package com.LibraryManagement.LibraryManagement.Service;

import com.LibraryManagement.LibraryManagement.Repository.BookRepo;
import com.LibraryManagement.LibraryManagement.dto.bookDto;
import com.LibraryManagement.LibraryManagement.entity.Book;
import com.LibraryManagement.LibraryManagement.exception.BookNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepo repository;

    public BookService(BookRepo repository) {
        this.repository = repository;
    }

    public Book saveBook(Book book) {
        return repository.save(book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

   public Book getBookById(int id) {
        return repository.findById(id);
   }

    public Book updateBook(Long id, Book updatedBook) {

        Book existingBook = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setPublishedYear(updatedBook.getPublishedYear());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setQuantity(updatedBook.getQuantity());

        return repository.save(existingBook);
    }

    public String deleteBookById(Long id) {
        Book book = repository.findById(id).orElseThrow(() -> new BookNotFoundException("Book Not Found"));
        repository.delete(book);
        return "Book Deleted Successfully";
    }

    public bookDto convertToDTO(Book book){

        bookDto dto = new bookDto();

        dto.setId(book.getId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setAuthor(String.valueOf(book.getAuthor()));
       dto.setPrice(book.getPrice());

        return dto;
    }

    public Page<Book> getBooks(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Book> getBooksByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    public List<Book> getBooksByTitle(String title) {
        return repository.findByTitle(title);
    }

}