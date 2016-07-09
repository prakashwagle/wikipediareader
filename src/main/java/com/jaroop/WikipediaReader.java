package com.jaroop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Created by prakashwagle on 7/8/16.
 *This Class act as a entry point for JVM and  bootsup Spring Container
 */

@SpringBootApplication
public class WikipediaReader {

    public static void main(String args[]) {
        SpringApplication.run(WikipediaReader.class);
    }

}
