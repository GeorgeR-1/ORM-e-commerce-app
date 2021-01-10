package com.cybertek.service;

import com.cybertek.model.Currency;
import com.cybertek.model.Product;
import com.cybertek.repository.CurrencyRepository;
import com.cybertek.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ProductRepository productRepository;

    public CurrencyService(CurrencyRepository currencyRepository, ProductRepository productRepository) {
        this.currencyRepository = currencyRepository;
        this.productRepository = productRepository;
    }

    public Currency create(Currency currency) throws Exception {

        Optional<Currency> foundedCurrency = currencyRepository
                                .findCurrencyByNameAndSymbol(currency.getName(), currency.getSymbol());

        if (foundedCurrency.isPresent()){
            throw new Exception("This currency with provided name or symbol already exists");
        }

        return currencyRepository.save(currency);
    }

    public void update(Currency currency) throws Exception {

        currencyRepository.findCurrencyByNameAndSymbol(currency.getName(), currency.getSymbol())
                .orElseThrow(() -> new Exception("This currency can not be found"));

        currencyRepository.save(currency);
    }

    public List<Currency> readAll(){
        return currencyRepository.findAll(Sort.by("name"));
    }

    public Currency findByName(String name) throws Exception {
        return currencyRepository.findByName(name)
                .orElseThrow(() -> new Exception("This currency can not be found"));
    }

    public void deleteByName(String name) throws Exception {
        Currency foundedCurrency = currencyRepository.findByName(name)
                .orElseThrow(() -> new Exception("This currency does not exist"));

        List<Product> productList = productRepository.findAllByCurrency(foundedCurrency);

        if (productList.size() > 0){
            throw new Exception("This currency can not be deleted");
        }

        foundedCurrency.setName(foundedCurrency.getName() + "-" + foundedCurrency.getId());
        foundedCurrency.setIsDeleted(true);

        currencyRepository.save(foundedCurrency);
    }




}
