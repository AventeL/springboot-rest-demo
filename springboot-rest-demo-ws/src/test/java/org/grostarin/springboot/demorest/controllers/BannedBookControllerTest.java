package org.grostarin.springboot.demorest.controllers;

import org.grostarin.springboot.demorest.domain.BannedBook;
import org.grostarin.springboot.demorest.repositories.BannedBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BannedBookControllerTest {
    @Autowired
    private TestRestTemplate testRestTemple;
    @Autowired
    private BannedBookRepository bannedBookRepository;

    @Test
    void create() {
        BannedBook bannedBook = new BannedBook("title", "author");
        BannedBook result = this.testRestTemple.postForObject("/api/banned-books", bannedBook, BannedBook.class);
        assertEquals(bannedBook.getTitle(), result.getTitle());
        assertEquals(bannedBook.getAuthor(), result.getAuthor());

        Optional<BannedBook> book = bannedBookRepository.findById(result.getId());
        assertTrue(book.isPresent());
        assertEquals(bannedBook.getTitle(), book.get().getTitle());
        assertEquals(bannedBook.getAuthor(), book.get().getAuthor());
    }
}