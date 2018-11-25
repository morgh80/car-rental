package pl.milgro.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.milgro.carrental.domain.Cost;

public interface PriceListJpaRepository extends JpaRepository<Cost, Long>, PriceListRepository {
}
