package com.cybertek.service;

import com.cybertek.model.Uom;

import java.util.List;

public interface UomService {

    Uom create(Uom uom) throws Exception;

    void update(Uom uom) throws Exception;

    void deleteById(Integer id) throws Exception;

    List<Uom> readAll();

    Uom readById(Integer id) throws Exception;



}
