package com.example.BookShop.services.category;

import com.example.BookShop.domain.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategory(List<Category> categories);

    boolean isDataSeeded();

    Set<Category> getRandomCategory();
}
