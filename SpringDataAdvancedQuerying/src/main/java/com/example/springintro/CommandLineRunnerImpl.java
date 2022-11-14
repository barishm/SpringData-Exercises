package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;




@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;


    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //seedData();


        // zad1 printAllByAgeRestriction("teEN");
        // zad2 printAllBooksByEditionTypeAndCopiesLessThan("GOLD",5000);
        // zad3 printAllBooksByPriceLessThanOrPriceGreaterThan(new BigDecimal(5), new BigDecimal(45));
        // zad4 printAllBooksByReleaseDateNot(2000);
        // zad5 printAllBooksByReleaseDateBefore(LocalDate.of(1992,4,12));
        // zad6 printAllAuthorsByFirstNameEndingWith("dy");
        // zad7 printAllBooksByTitleContaining("WOR");
        // zad8 printAllBooksByAuthorLastNameStartingWith("Ric");
        // zad9 getCountOfBooksWhereLengthLongerThan(40);
        // zad11 printFirstByTitle("Things Fall Apart");




    }

    private void printFirstByTitle(String title){
        System.out.println(bookService.findFirstByTitle(title));
    }

    private void getCountOfBooksWhereLengthLongerThan(Integer length){
        System.out.println(bookService.findCountOfBooksWhereLengthLongerThan(length));
    }
    private void printAllBooksByAuthorLastNameStartingWith(String prefix){
        bookService.findAllByAuthorLastNameStartingWith(prefix).forEach(System.out::println);
    }
    private void printAllBooksByTitleContaining(String contains){
        bookService.findAllByTitleContaining(contains).forEach(System.out::println);
    }

    private void printAllAuthorsByFirstNameEndingWith(String suffix){
        authorService.findAllByFirstNameEndingWith(suffix).forEach(System.out::println);
    }

    private void printAllBooksByReleaseDateBefore(LocalDate date){
        bookService.findAllByReleaseDateBefore(date).forEach(System.out::println);
    }

    private void printAllBooksByReleaseDateNot(int year){
        bookService.findAllByReleaseDateNot(year).forEach(System.out::println);
    }

    private void printAllBooksByPriceLessThanOrPriceGreaterThan(BigDecimal low,BigDecimal high){
        bookService.findAllByPriceLessThanOrPriceGreaterThan(low,high).forEach(System.out::println);
    }
    private void printAllBooksByEditionTypeAndCopiesLessThan(String editionType,Integer copiesNumber){
        bookService.findAllByEditionTypeAndCopiesLessThan(EditionType.valueOf(editionType.toUpperCase()),copiesNumber).forEach(System.out::println);
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllByAgeRestriction(String ageRestriction){
        bookService.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase())).forEach(System.out::println);
    }



    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
