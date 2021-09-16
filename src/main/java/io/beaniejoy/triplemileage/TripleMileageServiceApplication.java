package io.beaniejoy.triplemileage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TripleMileageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripleMileageServiceApplication.class, args);
    }

}
