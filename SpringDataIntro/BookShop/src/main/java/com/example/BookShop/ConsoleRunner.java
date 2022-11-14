package com.example.BookShop;

import com.example.BookShop.services.author.AuthorService;
import com.example.BookShop.services.book.BookService;
import com.example.BookShop.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private LocalDate BOOK_YEAR_AFTER = LocalDate.of(2000,1,1);
    private final LocalDate BOOK_YEAR_BEFORE = LocalDate.of(1990, 1, 1);
    private final BookService bookService;
    private final SeedService seedService;
    private AuthorService authorService;


    @Autowired
    public ConsoleRunner(BookService bookService, SeedService seedService,AuthorService authorService) {
        this.bookService = bookService;
        this.seedService = seedService;
        this.authorService = authorService;

    }


    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAllData();
        this.getAllOrderByBooks();
    }
    private void getAllBooksAfterAGivenYear(){
        this.bookService
                .findAllByReleaseDateAfter(BOOK_YEAR_AFTER)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void getAllAuthorsWithBooksReleaseDateBefore() {
        this.authorService
                .findDistinctByBooksBefore(BOOK_YEAR_BEFORE)
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }
    private void getAllOrderByBooks() {
        this.authorService.findAllOrderByBooks()
                .forEach(author -> System.out.println(author.toStringWithCount()));
    }

    private void findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc() {
        this.bookService
                .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George", "Powell")
                .forEach(book -> System.out.println(book.getTitle() + " "
                        + book.getReleaseDate() + " "
                        + book.getCopies()));
    }

}
