package com.cybertek.service;

import com.cybertek.model.Currency;

import java.util.List;

public interface CurrencyService {

    Currency create(Currency currency) throws Exception;

    void update(Currency currency)throws Exception;

    List<Currency> readAll();

    Currency readById(Integer id) throws Exception;

    Currency findByNameAndSymbol(String name, String symbol) throws Exception;

    void deleteByNameAndSymbol(String name, String symbol) throws Exception;




}
