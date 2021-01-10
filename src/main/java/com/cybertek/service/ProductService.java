package com.cybertek.service;

import com.cybertek.model.Currency;
import com.cybertek.model.Product;
import com.cybertek.model.Uom;
import com.cybertek.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product){
        return productRepository.save(product);
    }

    public void update(Product product) throws Exception {

        productRepository.findById(product.getId())
                .orElseThrow(() -> new Exception("This product does not exist"));
    }

    public List<Product> readAll(){
        return productRepository.findAll();
    }

    public Product readById(Long id) throws Exception {
        return productRepository.findById(id)
                .orElseThrow(() -> new Exception("No product with such ID"));
    }

    public List<Product> readAllActive(){
        return productRepository.findAllByStatusActive();
    }

    public List<Product> readAllByCurrency(Currency currency){
        return productRepository.findAllByCurrency(currency);
    }

    public List<Product> readAllByUom(Uom uom){
        return productRepository.findAllByUom(uom);
    }

}
