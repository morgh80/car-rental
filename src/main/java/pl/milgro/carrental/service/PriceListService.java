package pl.milgro.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.milgro.carrental.domain.Cost;
import pl.milgro.carrental.repository.PriceListRepository;

import java.util.List;

@Service
public class PriceListService {
    private PriceListRepository priceListRepository;

    public List<Cost> getAllCosts() {
        return priceListRepository.findAll();
    }

    public Cost getCostByName(String name) {
        return priceListRepository.findByName(name).orElse(null);
    }

    public Cost getCostById(Long id) {
        return priceListRepository.findById(id).orElse(null);
    }

    public Cost saveCost(Cost cost) {
        return priceListRepository.save(cost);
    }

    public void deleteByName(String name) {
        priceListRepository.deleteByName(name);
    }

    public void deleteById(Long id) {
        priceListRepository.deleteById(id);
    }

    public void deleteAll() {
        priceListRepository.deleteAll();
    }

    @Autowired
    public PriceListService(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }


}
