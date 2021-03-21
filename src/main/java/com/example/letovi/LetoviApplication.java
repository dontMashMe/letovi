package com.example.letovi;

import com.amadeus.Amadeus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LetoviApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetoviApplication.class, args);
    }

    @Bean
    public Amadeus getAmadeus(){
        return Amadeus
                .builder("AKhSPSzOeQocfhbGn0jn0o2XjbbD722s", "1uIG98kERrX6GBaQ")
                .build();
    }

}
