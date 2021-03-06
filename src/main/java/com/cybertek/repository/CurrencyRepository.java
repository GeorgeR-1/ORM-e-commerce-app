package com.cybertek.repository;

import com.cybertek.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

    Optional<Currency> findCurrencyByNameAndSymbol(String name, String symbol);

    Optional<Currency> findByNameAndSymbol(String name, String symbol);
}
