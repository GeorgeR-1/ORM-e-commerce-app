package com.cybertek.service;

import com.cybertek.model.Category;
import com.cybertek.model.SubCategory;
import com.cybertek.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Transactional
    public SubCategory create(SubCategory subCategory) throws Exception {

        Optional<SubCategory> foundSubCategory =
                subCategoryRepository.findByNameAndCategoryId(subCategory.getName(),
                        subCategory.getCategory().getId());

        if(foundSubCategory.isPresent()){
            throw new Exception("Sub Category already exists");
        }

        return subCategoryRepository.save(subCategory);

    }

    @Transactional
    public void update(SubCategory subCategory) throws Exception {

        SubCategory foundedCategory = subCategoryRepository.findByNameAndCategoryId(subCategory.getName(), subCategory.getCategory().getId())
                .orElseThrow(() -> new Exception("This subCategory does not exist"));

        subCategory.setId(foundedCategory.getId());
        subCategoryRepository.save(subCategory);

    }

    public List<SubCategory> readAll(){

        return subCategoryRepository.findAll();

    }

    public SubCategory readById(Integer id){

        return subCategoryRepository.findById(id).orElse(null);

    }

    @Transactional
    public void deleteById(Integer id) throws Exception {

        SubCategory foundedSubCategory = subCategoryRepository
                .findById(id).orElseThrow(() -> new Exception("This subCategory does not exist"));

        foundedSubCategory.setName(foundedSubCategory.getName()+"-"+foundedSubCategory.getId());
        foundedSubCategory.setIsDeleted(true);

        subCategoryRepository.save(foundedSubCategory);
    }

    public List<SubCategory> readAllByCategory(Category category){

        return subCategoryRepository.findAllByCategory(category);

    }




}
