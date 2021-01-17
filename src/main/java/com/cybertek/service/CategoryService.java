package com.cybertek.service;

import com.cybertek.model.Category;
import com.cybertek.model.SubCategory;
import com.cybertek.repository.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryService subCategoryService;

    public CategoryService(CategoryRepository categoryRepository, SubCategoryService subCategoryService) {
        this.categoryRepository = categoryRepository;
        this.subCategoryService = subCategoryService;
    }

    @Transactional
    public Category create(Category category) throws Exception {

        Optional<Category> founded = categoryRepository.findByName(category.getName());

        if (founded.isPresent()) {
            throw new Exception("Category already exists");
        }

        return categoryRepository.save(category);

    }

    @Transactional
    public void deleteById(Integer id) throws Exception {
        Category foundedCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("category doesn't exist"));

        List<SubCategory> subCategories = subCategoryService.readAllByCategory(foundedCategory);

        if (subCategories.size() > 0) {
            throw new Exception("This Category can not be deleted");
        }

        foundedCategory.setName(foundedCategory.getName() + "-" + foundedCategory.getId());
        foundedCategory.setIsDeleted(true);
        categoryRepository.save(foundedCategory);

    }

    @Transactional
    public void update(Category category) throws Exception {
        categoryRepository.findByName(category.getName())
                .orElseThrow(() -> new Exception("Category does not exist"));

        categoryRepository.save(category);
    }

    public List<Category> readAll() {
        return categoryRepository.findAll(Sort.by("name"));
    }

    public Category readById(Integer id) throws Exception {
        return categoryRepository.findById(id).orElseThrow(() -> new Exception("No Category with such id"));
    }




}
