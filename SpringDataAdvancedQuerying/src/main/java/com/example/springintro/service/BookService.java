package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copiesNumber);
    List<String> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal low, BigDecimal high);
    List<String> findAllByReleaseDateNot(int year);
    List<String> findAllByReleaseDateBefore(LocalDate releaseDateBefore);
    List<String> findAllByTitleContaining(String contains);
    List<String> findAllByAuthorLastNameStartingWith(String prefix);
    Integer findCountOfBooksWhereLengthLongerThan(Integer length);
    List<String> findFirstByTitle(String title);
}
