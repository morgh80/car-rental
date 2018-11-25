package pl.milgro.carrental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.milgro.carrental.repository.PriceListDbSample;
import pl.milgro.carrental.repository.PriceListRepository;

@Configuration
public class CarRentalConfig {

    @Bean
    public PriceListRepository priceListRepository() {
        return new PriceListDbSample();
    }

}
