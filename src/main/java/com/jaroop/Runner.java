package com.jaroop;

import com.jaroop.Interface.UIO;
import com.jaroop.Interface.WebReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Created by prakashwagle on 7/8/16.
 *
 * This Class act as a controller between UserInterface and the backend Buissness Logic.
 * It Mainly Depends upon Two classes for execution
 * UIO - This class handles User Input Output
 * Webreader - This class handles Buissness logic of getting content requested by User through UIO metohds
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
