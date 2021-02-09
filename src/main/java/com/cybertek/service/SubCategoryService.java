package com.cybertek.service;

import com.cybertek.model.Category;
import com.cybertek.model.SubCategory;

import java.util.List;

public interface SubCategoryService {

    SubCategory create(SubCategory subCategory) throws Exception;

    void update(SubCategory subCategory) throws Exception;

    List<SubCategory> readAll();

    SubCategory readById(Integer id);

    void deleteById(Integer id) throws Exception;

    List<SubCategory> readAllByCategory(Category category);
}
