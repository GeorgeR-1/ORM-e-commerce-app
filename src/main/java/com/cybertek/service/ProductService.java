package com.cybertek.service;

import com.cybertek.model.Currency;
import com.cybertek.model.Product;
import com.cybertek.model.Uom;

import java.util.List;

public interface ProductService{

    Product create(Product product) throws Exception;

    void update(Product product) throws Exception;

    List<Product> readAll();

    Product readById(Long id) throws Exception;

    List<Product> readAllActive();

    List<Product> readAllByCurrency(Currency currency);

    List<Product> readAllByUom(Uom uom);

}
