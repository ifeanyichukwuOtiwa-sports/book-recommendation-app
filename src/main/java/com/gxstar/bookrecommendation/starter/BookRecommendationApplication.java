package com.gxstar.bookrecommendation.starter;

import com.gxstar.bookrecommendation.config.BookRecommendationAppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(BookRecommendationAppConfig.class)
public class BookRecommendationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRecommendationApplication.class, args);
    }

}
