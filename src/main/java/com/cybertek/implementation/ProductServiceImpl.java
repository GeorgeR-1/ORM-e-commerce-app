package com.cybertek.implementation;

import com.cybertek.model.Currency;
import com.cybertek.model.Product;
import com.cybertek.model.Uom;
import com.cybertek.repository.ProductRepository;
import com.cybertek.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product create(Product product) throws Exception {

        if(product.getName() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0 || product.getQuantity() <= 0){
            throw new Exception("Something went wrong please try again");
        }


        return productRepository.save(product);
    }
    @Transactional
    public void update(Product product) throws Exception {

        productRepository.findById(product.getId())
                .orElseThrow(() -> new Exception("This product does not exist"));

        if(product.getName() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0 || product.getQuantity() <= 0){
            throw new Exception("Something went wrong please try again");
        }

        productRepository.save(product);
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
