package com.edstem.auditing.service;

import com.edstem.auditing.contract.BookDTO;
import com.edstem.auditing.model.Book;
import com.edstem.auditing.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public BookDTO getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::convertToDTO).orElse(null);
    }

    public List<BookDTO> createBooks(List<BookDTO> bookDTOs) {
        List<Book> books = bookDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        List<Book> savedBooks = bookRepository.saveAll(books);
        return savedBooks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO updateBook(Long id, Book books) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(books.getTitle());
            book.setAuthor(books.getAuthor());
            book.setIsbn(books.getIsbn());
            book.setPrice(books.getPrice());
            Book updatedBook = bookRepository.save(book);
            return convertToDTO(updatedBook);
        }
        return null;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


    private BookDTO convertToDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .price(book.getPrice())
                .createdDate(book.getCreatedDate())
                .createdBy(book.getCreatedBy())
                .lastModifiedBy(book.getLastModifiedBy())
                .lastModifiedDate(book.getLastModifiedDate())
                .build();
    }


    private Book convertToEntity(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .isbn(bookDTO.getIsbn())
                .author(bookDTO.getAuthor())
                .price(bookDTO.getPrice())
                .build();
    }
}
