package com.example.BookShop.services.seed;

import com.example.BookShop.domain.entities.Author;
import com.example.BookShop.domain.entities.Book;
import com.example.BookShop.domain.entities.Category;
import com.example.BookShop.domain.enums.AgeRestriction;
import com.example.BookShop.domain.enums.EditionType;
import com.example.BookShop.services.author.AuthorService;
import com.example.BookShop.services.book.BookService;
import com.example.BookShop.services.book.BookServiceImpl;
import com.example.BookShop.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.BookShop.Constants.FilePath.*;

@Component
public class SeedServiceImpl implements SeedService{
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws IOException {
        if(!this.authorService.isDataSeeded()){
            this.authorService.seedAuthors(Files.readAllLines(Path.of(RESOURCE_URL + AUTHOR_FILE_NAME)).stream()
                    .filter(s -> !s.isBlank())
                    .map(names -> Author.builder()
                            .firstName(names.split(" ")[0])
                            .lastName(names.split(" ")[1])
                            .build())
                    .collect(Collectors.toList()));
        }
    }

    @Override
    public void seedBooks() throws IOException {
        final List<Book> books = Files.readAllLines(Path.of(RESOURCE_URL + BOOK_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(row -> {
                    String[] data = row.split("\\s+");
                    String title = Arrays.stream(data)
                            .skip(5)
                            .collect(Collectors.joining(" "));
                    return Book.builder()
                            .title(title)
                            .editionType(EditionType.values()[Integer.parseInt(data[0])])
                            .price(new BigDecimal(data[3]))
                            .releaseDate(LocalDate.parse(data[1],DateTimeFormatter.ofPattern("d/M/yyyy")))
                            .ageRestriction(AgeRestriction.values()[Integer.parseInt(data[4])])
                            .author(this.authorService.getRandomAuthor())
                            .categories(this.categoryService.getRandomCategory())
                            .copies(Integer.parseInt(data[2]))
                            .build();
                }).collect(Collectors.toList());
        this.bookService.seedBooks(books);
    }

    @Override
    public void seedCategory() throws IOException {
        if(!this.categoryService.isDataSeeded()){
            this.categoryService.seedCategory(Files.readAllLines(Path.of(RESOURCE_URL+CATEGORY_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(name -> Category.builder().name(name).build())
                    .collect(Collectors.toList()));
        }
    }


}
