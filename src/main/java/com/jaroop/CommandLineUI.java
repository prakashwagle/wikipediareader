package com.jaroop;

import com.jaroop.Interface.UIO;
import org.springframework.stereotype.Component;
import java.util.Scanner;

/**
 * @author  Created by prakashwagle on 7/8/16.
 * This class implements IO methods to interact with user through command line interface
 */
@Component
public class CommandLineUI implements UIO {

    /**
     * This method ask user to input topic he wants to search.
     * It replaces whitespace with "_"
      * @return userInput - Topic entered by user
     */
    @Override
    public String getUserInput() {

        System.out.println("Please enter a topic of your interest : ");
        Scanner sc ;
        String line;
        while (true)
        {
           sc = new Scanner(System.in);
            line = sc.nextLine();
        if (line.trim().isEmpty()) {
            System.out.println("Please enter valid topic: ");
        }
            else
        {
            break;
        }

      }
        String userInput = line.replaceAll("\\s","_");//sc.nextLine().replaceAll("\\s","_");
//        System.out.println(userInput);
        return userInput;
    }

    /**
     * This method prints the given input on commandline
     * @param out : Prints the output on Users Console
     */
    @Override
    public void putUserOutput(String out) {
        System.out.println("/*************** Information **************/ \n");
        System.out.println(out);
        System.out.println("/*************** ###### **************/");
    }
}
