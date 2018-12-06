package pl.milgro.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.milgro.carrental.domain.Cost;

import java.util.List;
import java.util.Optional;

public interface CostRepository extends JpaRepository<Cost, Long> {
    List<Cost> findAll();

    Optional<Cost> findById(Long id);

    Optional<Cost> findByName(String name);

    @Override
    <S extends Cost> S save(S cost);

    void deleteById(Long id);

    void deleteByName(String name);

    void deleteAll();
}
