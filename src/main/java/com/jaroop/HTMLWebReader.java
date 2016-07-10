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
import java.util.Arrays;

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
     * Page for topics requested user is called using URLConnection - If it is present then the
     * result is collected into StringBuilder object through InputStream using BufferReader.
     *
     * This page is then passed to parseWebpage() method in order to get parsed content, which is then returned
     * back to the Runner class for displaying in Users console
     *
     * @param input : Topic requested by User sent through Runner Class
     * @return result : Contains content of the requested HTML page, it is sent back to Runner Class
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
            result = parseWebpage(buffer);
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
     * This method uses JSoup library to parse the HTML page for content.
     *
     * Document object is created from HTML page present in Input String [sb]
     * If the size of Elements return is less than two than it may contain suggested topics
     * else
     * It will parse for content between HTLM "<p>..</p> " tag
     *
     * @param sb : Input is a HTML page
     * @return elements : Elements object which contains body of the content requested by user
     * or if the search fails then it returns null.
     */
    public StringBuilder parseWebpage(StringBuilder sb)
    {
        Document document = Jsoup.parse(sb.toString());
        Element element = document.body();
        Elements elements = element.getElementsByTag("p");
        StringBuilder result =new StringBuilder();

        if(elements.size()<=1) {
            Element el = elements.stream().filter(x -> x.text().matches("(?i).*may refer to:.*")).findAny().orElse(null);
            if (el != null) {
                Elements temp = document.select("p~*");
                result.append("You may refer to Following topics: \n \n");
                temp.forEach(s -> result.append(s.tagName("li").text().replace("[edit]"," : ")+"\n"));
                        }
        }
        else
        {
            String[] st = elements.first().text().split("\\. ");
            Arrays.stream(st).forEach(s->result.append(s+"\n"));

        }

        return result;

    }
}
