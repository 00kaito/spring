package com.coverter.app.converter.Controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Service
public class SitemMapGenService {

    public static ArrayList<String> start(String txt) throws IOException {
        Document doc = getSource(txt);
        return generate(doc, txt, txt);
    }

    public static ArrayList<String> generate(org.jsoup.nodes.Document doc, String urlToCheck, String homeURL) throws IOException {
//        doc = getSource(urlToCheck);
        System.out.println("START");
        ArrayList<String> siteMap = new ArrayList<>();
        int sitemapIndex = 0;

        int rcode=200;
        if (siteMap.size()==0){
            siteMap.add(urlToCheck);
        }
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("abs:href");
            if ((linkHref.startsWith(homeURL)) && !(linkHref.contains("#")) && !(linkHref.contains(".jpg")) && !(linkHref.contains(".png"))){
                boolean duplicate = false;
                for (String el : siteMap) {
                    if ((el.equals(linkHref)))
                        duplicate = true;
                    if (duplicate) {
                        break;
                    }
                }
                int tmprcode;
                if (!duplicate){
                    tmprcode = responseCode(linkHref);
                    System.out.println(tmprcode);
                    if (rcode == tmprcode){
                        System.out.println("zapisuje: "+linkHref);
                        siteMap.add(linkHref);
                        System.out.println(linkHref);
                        if(siteMap.size()>=15)
                            return siteMap;
                        if (tmprcode==0) {
                            siteMap.add("ERROR");
                            return siteMap;
                        }
                    }
                }
            }
        }
        while(sitemapIndex  < siteMap.size()-1){
            sitemapIndex ++;
            String urlAdress = siteMap.get(sitemapIndex ).toString();
            org.jsoup.nodes.Document document = getSource(urlAdress);
            generate(document, urlAdress, homeURL);
        }
        return siteMap;
    }

    private static org.jsoup.nodes.Document getSource(String str) throws IOException {
        Document doc = Jsoup.connect(str).get();
        return doc;
    }

    public static int responseCode(String str) throws IOException{
        URL url = new URL(str);
        int code = 0;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            code = connection.getResponseCode();
        }catch(IOException e){
            System.out.println("EXCEPTION HERE");
            return 0;
        }
        return code;
    }
}
