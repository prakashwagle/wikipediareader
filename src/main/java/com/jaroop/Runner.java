package com.jaroop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by prakashwagle on 7/8/16.
 */
@Component
public class Runner implements CommandLineRunner{
    @Autowired
    UIO ui ;
    @Autowired
    WebReader webReader;


    @Override
    public void run(String... strings) throws Exception {
        String input = ui.getUserInput();
        StringBuilder output = webReader.getContent(input);
        ui.putUserOutput(output.toString());
    }
}
