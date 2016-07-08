package com.jaroop;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by prakashwagle on 7/8/16.
 */
@Component
public class HTMLWebReader implements WebReader {
    @Override
    public StringBuilder getContent(String input) {
        URL url;
        URLConnection urlConnection;
        BufferedReader bufferedReader;
        String address = "https://en.wikipedia.org/wiki/"+input;
        StringBuilder result= new StringBuilder();

        try {
            url = new URL(address);
            urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String temp;
            while ((temp=bufferedReader.readLine())!=null)
            {
                result.append(temp);
            }
        }
        catch (MalformedURLException m)
        {
             System.err.print(m);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
