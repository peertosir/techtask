package dev.peertosir.techtasksimbsoft;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechTaskApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TechTaskApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
