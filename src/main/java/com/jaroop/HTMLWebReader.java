package com.jaroop;

import com.jaroop.Interface.WebReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        StringBuilder buffer= new StringBuilder();
        StringBuilder result= new StringBuilder();

        try {
            url = new URL(address);
            urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String temp;
            while ((temp=bufferedReader.readLine())!=null)
            {
                buffer.append(temp);
            }
            Elements elements = parseWebpage(buffer);

            if (elements==null)
            {
                System.out.println("Yo no page to Display Bro !!!");
            }
            else
            {
                elements.forEach(s -> result.append(s.text() +"\n"));
            }
        }
        catch (MalformedURLException m)
        {
             System.err.print(m);
        } catch (IOException e) {
            System.out.println("Page for the given Input: "+input+" cannot be found !!!");
           // e.printStackTrace();
        }

        return result;
    }


    public Elements parseWebpage(StringBuilder sb)
    {
        Document document = Jsoup.parse(sb.toString());
        Element element = document.body();
        Elements elements = element.getElementsByTag("p");
        Element el = elements.stream().filter(x->x.text().matches("(?i).*may refer to:.*")).findAny().orElse(null);
        if (el!=null)
        {
           return null;
        }
        else {
           return elements;
        }
    }
}
