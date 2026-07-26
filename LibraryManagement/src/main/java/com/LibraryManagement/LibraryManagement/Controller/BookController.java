package com.LibraryManagement.LibraryManagement.Controller;

import com.LibraryManagement.LibraryManagement.Service.BookService;
import com.LibraryManagement.LibraryManagement.dto.bookDto;
import com.LibraryManagement.LibraryManagement.entity.Book;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book addBook( @RequestBody Book book) {
        System.out.println(book);
        return bookService.saveBook(book);
    }
    @GetMapping
    public List<Book> getAllBook() {

        return bookService.getAllBooks();
    }


    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public String deleteBookById(@PathVariable Long id) {
        return bookService.deleteBookById(id);
    }

    @GetMapping("/{id}")
    public bookDto getBookById(@PathVariable Long id) {
        Book book = new Book();
        return bookService.convertToDTO(book);
    }

    @GetMapping("/page")
    public Page<Book> getBooks(@RequestParam int page, @RequestParam int size, Pageable pageable) {
        return bookService.getBooks(pageable);
    }

    @GetMapping("/authors/{authors}")
    public List<Book> getBooksByAuthors(@PathVariable String authors) {
        return bookService.getBooksByAuthor(authors);
    }

    @GetMapping("/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) {
        return bookService.getBooksByTitle(title);
    }

}
