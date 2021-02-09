package com.cybertek.implementation;

import com.cybertek.model.Product;
import com.cybertek.model.Uom;
import com.cybertek.repository.ProductRepository;
import com.cybertek.repository.UomRepository;
import com.cybertek.service.UomService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UomServiceImpl implements UomService {

    private final UomRepository uomRepository;
    private final ProductRepository productRepository;

    public UomServiceImpl(UomRepository uomRepository, ProductRepository productRepository) {
        this.uomRepository = uomRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Uom create(Uom uom) throws Exception {

        Optional<Uom> foundedUom = uomRepository.findByName(uom.getName());

        if (foundedUom.isPresent()) {
            throw new Exception("This unit of measure already exists");
        }

        return uomRepository.save(uom);
    }

    @Transactional
    public void update(Uom uom) throws Exception {

        Uom foundedUom = uomRepository.findByName(uom.getName())
                .orElseThrow(() -> new Exception("This unit of measure does not exist"));

        uomRepository.save(uom);

    }

    @Transactional
    public void deleteById(Integer id) throws Exception {

        Uom uom = uomRepository.findById(id)
                .orElseThrow(() -> new Exception("This unit of measure does not exist"));

        List<Product> list = productRepository.findAllByUom(uom);

        if (list.size() > 0){
            throw new Exception("This Unit Of Measure can not be deleted");
        }

        uom.setIsDeleted(true);
        uom.setName(uom.getName() + "-" + uom.getId());
        uomRepository.save(uom);

    }

    public List<Uom> readAll() {
        return uomRepository.findAll();
    }

    public Uom readById(Integer id) throws Exception {
        return uomRepository.findById(id)
                .orElseThrow(() -> new Exception("Unit of measure with such ID does not exist"));
    }




}
