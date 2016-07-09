package com.jaroop;

import com.jaroop.Interface.UIO;
import org.springframework.stereotype.Component;
import java.util.Scanner;

/**
 * Created by prakashwagle on 7/8/16.
 * This class implements IO methods to interact with user through command line interface
 */
@Component
public class CommandLineUI implements UIO {

    /**
     * This method ask user to input topic he wants to search.
     * It replaces whitespace with "_"
      * @return userInput [String] - Topic entered by user
     */
    @Override
    public String getUserInput() {

        System.out.println("Please enter a topic of your interest : ");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine().replaceAll("\\s","_");
        return userInput;
    }

    /**
     * This method prints the given input on commandline
     * @param out [String]
     */
    @Override
    public void putUserOutput(String out) {
        System.out.println(out);
    }
}
