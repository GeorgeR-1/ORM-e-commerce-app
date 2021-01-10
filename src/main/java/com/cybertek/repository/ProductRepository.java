package com.cybertek.repository;

import com.cybertek.model.Currency;
import com.cybertek.model.Product;
import com.cybertek.model.Uom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p where p.status = 'ACTIVE'")
    List<Product> findAllByStatusActive();

    List<Product> findAllByCurrency(Currency currency);

    List<Product> findAllByUom(Uom uom);

}
