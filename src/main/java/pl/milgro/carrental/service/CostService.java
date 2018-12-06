package pl.milgro.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.milgro.carrental.domain.Cost;
import pl.milgro.carrental.repository.CostRepository;

import java.util.List;

@Service
public class CostService {

    private CostRepository costRepository;

    public List<Cost> getAllCosts() {
        return costRepository.findAll();
    }

    public Cost getCostByName(String name) {
        return costRepository.findByName(name).orElse(null);
    }

    public Cost getCostById(Long id) {
        return costRepository.findById(id).orElse(null);
    }

    public Cost saveCost(Cost cost) {
        return costRepository.save(cost);
    }

    public void deleteByName(String name) {
        costRepository.deleteByName(name);
    }

    public void deleteById(Long id) {
        costRepository.deleteById(id);
    }

    @Autowired
    public CostService(CostRepository costRepository) {
        this.costRepository = costRepository;
    }

}
