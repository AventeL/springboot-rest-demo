package org.grostarin.springboot.demorest.services;

import org.grostarin.springboot.demorest.domain.BannedBook;
import org.grostarin.springboot.demorest.repositories.BannedBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BannedBookServiceTest {
    @Autowired
    private BannedBookRepository bannedBookRepository;
    @Autowired
    private BannedBookService bannedBookService;

    @BeforeEach
    void setUp() {
        bannedBookRepository.deleteAll();
    }

    @Test
    void isBanned() {
        BannedBook bannedBook = new BannedBook("title", "author");
        bannedBookRepository.save(bannedBook);
        boolean result = bannedBookService.isBanned("title", "author");
        assertTrue(result);
    }

    @Test
    void create() {
        int count = (int) bannedBookRepository.count();
        BannedBook bannedBook = new BannedBook("title", "author");
        BannedBook result = bannedBookService.create(bannedBook);
        int count2 = (int) bannedBookRepository.count();
        assertEquals(count2, count + 1);
    }


}