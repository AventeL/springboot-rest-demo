package org.grostarin.springboot.demorest.services;

import org.grostarin.springboot.demorest.domain.BannedBook;
import org.grostarin.springboot.demorest.domain.Book;
import org.grostarin.springboot.demorest.exceptions.BookBannedException;
import org.grostarin.springboot.demorest.exceptions.BookIdMismatchException;
import org.grostarin.springboot.demorest.exceptions.BookNotFoundException;
import org.grostarin.springboot.demorest.repositories.BannedBookRepository;
import org.grostarin.springboot.demorest.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookServices bookService;
    @Autowired
    private BannedBookService bannedBookService;
    @Autowired
    private BannedBookRepository bannedBookRepository;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bannedBookRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    void catchErrorOnAddABannedBook() {
        bannedBookService.create(new BannedBook("title", "author"));
        assertThrows(BookBannedException.class, () -> bookService.create(new Book("title", "author")));
    }

    @Test
    public void testCreationNoAttributes() {
        Book toCreate = new Book();
        assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(() -> bookService.create(toCreate));
    }

    @Test
    public void testUpdateBookWithWrongId() {
        Book toCreate = new Book("title", "author");
        toCreate.setId(1L);
        bookService.create(toCreate);
        assertThrows(BookIdMismatchException.class, (() -> bookService.updateBook(toCreate, 1L)));
    }

    @Test
    public void testUpdateBookNotInRepo() {
        Book toCreate = new Book("title", "author");
        toCreate.setId(1L);
        assertThrows(BookNotFoundException.class, (() -> bookService.updateBook(toCreate, 1L)));
    }

    @Test
    public void testUpdateBook() {
        Book toCreate = new Book("title", "author");
        toCreate.setId(1L);
        bookService.create(toCreate);
        toCreate.setTitle("new title");
        bookService.updateBook(toCreate, 1L);
        assertEquals(toCreate.getTitle(), bookService.findOne(1L).getTitle());
    }
}