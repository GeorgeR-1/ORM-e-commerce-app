package com.cybertek.implementation;

import com.cybertek.model.Currency;
import com.cybertek.model.Product;
import com.cybertek.repository.CurrencyRepository;
import com.cybertek.repository.ProductRepository;
import com.cybertek.service.CurrencyService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ProductRepository productRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, ProductRepository productRepository) {
        this.currencyRepository = currencyRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Currency create(Currency currency) throws Exception {

        Optional<Currency> foundedCurrency = currencyRepository
                                .findCurrencyByNameAndSymbol(currency.getName(), currency.getSymbol());

        if (foundedCurrency.isPresent()){
            throw new Exception("This currency with provided name or symbol already exists");
        }

        return currencyRepository.save(currency);
    }

    @Transactional
    public void update(Currency currency) throws Exception {

        currencyRepository.findCurrencyByNameAndSymbol(currency.getName(), currency.getSymbol())
                .orElseThrow(() -> new Exception("This currency can not be found"));

        currencyRepository.save(currency);
    }

    public List<Currency> readAll(){
        return currencyRepository.findAll(Sort.by("name"));
    }

    public Currency readById(Integer id) throws Exception {
        return currencyRepository.findById(id).orElseThrow(() -> new Exception("This currency does not exist"));
    }

    public Currency findByNameAndSymbol(String name, String symbol) throws Exception {
        return currencyRepository.findByNameAndSymbol(name,symbol)
                .orElseThrow(() -> new Exception("This currency can not be found"));


    }

    @Transactional
    public void deleteByNameAndSymbol(String name, String symbol) throws Exception {

        Currency foundedCurrency = currencyRepository.findByNameAndSymbol(name,symbol)
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
