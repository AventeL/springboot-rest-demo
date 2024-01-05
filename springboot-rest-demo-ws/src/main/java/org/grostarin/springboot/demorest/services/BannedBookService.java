package org.grostarin.springboot.demorest.services;

import org.grostarin.springboot.demorest.domain.BannedBook;
import org.grostarin.springboot.demorest.repositories.BannedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannedBookService {
    @Autowired

    BannedBookRepository bannedBookRepository;
    public boolean isBanned(String title, String author) {
        return bannedBookRepository.existsByTitleAndAuthor(title, author);
    }

    public BannedBook create(BannedBook bannedBook) {
        return bannedBookRepository.save(bannedBook);
    }
}
