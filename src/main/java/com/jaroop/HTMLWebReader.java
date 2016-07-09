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
 * @author Created by prakashwagle on 7/8/16.
 *
 * This class implements WebReader interface. Main function of this page is to get the User requestd page and
 * return the content present in the body of a HTML page
 */
@Component
public class HTMLWebReader implements WebReader {

    /**
     * This methods override getConetent method of WebReader interface.
     * It implemets functionality to get User requested content through HTML page.
     * @param input : Topic requested by User
     * @return result : Contains content of the requested HTML page.
     */
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
                result.append("Sorry...no Wikipedia Page is present for your input topic");
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

        }

        return result;
    }

    /**
     * This method uses JSoup library to parse the HTML page for content
     * It will parse for content between HTLM "<p>..</p> " tag
     * @param sb : Input is a HTML page
     * @return elements : Elements object which contains body of the content requested by user
     * or if the search fails then it returns null.
     */
    public Elements parseWebpage(StringBuilder sb)
    {
        Document document = Jsoup.parse(sb.toString());
        Element element = document.body();
        Elements elements = element.getElementsByTag("p");
        Element el = elements.stream().filter(x->x.text().matches("(?i).*may refer to:.*")).findAny().orElse(null);
        if (el!=null)
        {
            Elements temp = element.getElementsByTag("ul");
            temp.forEach(s-> System.out.println(s.text()));
           return null;
        }
        else {
           return elements;
        }
    }
}
