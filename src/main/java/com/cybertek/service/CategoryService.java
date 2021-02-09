package com.cybertek.service;

import com.cybertek.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(Category category) throws Exception;

    void deleteById(Integer id) throws Exception;

    void update(Category category) throws Exception;

    List<Category> readAll();

}
