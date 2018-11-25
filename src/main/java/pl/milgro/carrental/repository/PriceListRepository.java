package pl.milgro.carrental.repository;

import pl.milgro.carrental.domain.Cost;

import java.util.List;
import java.util.Optional;

public interface PriceListRepository {
    List<Cost> findAll();

    Cost save(Cost cost);

    Optional<Cost> findById(Long id);

    Optional<Cost> findByName(String name);

    void deleteById(Long id);

    void deleteByName(String name);

    void deleteAll();
}
