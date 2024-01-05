package org.grostarin.springboot.demorest.controllers;

import org.grostarin.springboot.demorest.domain.BannedBook;
import org.grostarin.springboot.demorest.domain.Book;
import org.grostarin.springboot.demorest.services.BannedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/banned-books")
public class BannedBookController {
    @Autowired
    BannedBookService bannedBookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BannedBook create(@RequestBody BannedBook book) {
        return bannedBookService.create(book);
    }
}
