package com.jaroop;

import org.springframework.stereotype.Component;
import java.util.Scanner;

/**
 * Created by prakashwagle on 7/8/16.
 */
@Component
public class UserInterface implements UIO {


    @Override
    public String getUserInput() {

        System.out.println("Please enter a topic of your interest : ");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        return userInput;
    }

    @Override
    public void putUserOutput(String out) {
        System.out.println(out);
    }
}
