package com.ten10.imdb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

//@Controller
@SpringBootApplication
public class ImdbApplication {


    public static void main(String[] args) {
        SpringApplication.run(ImdbApplication.class, args);
    }

}



