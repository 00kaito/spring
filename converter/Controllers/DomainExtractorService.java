package com.coverter.app.converter.Controllers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainExtractorService {
    public static ArrayList<String> findDomains(String text) {
        text = text + " ";
        ArrayList<String> domains = new ArrayList<String>();
        String tmptextbuilder = "";
        int numberOfCharAfterDot = 10;
        int trigger = -2;
        Character tmpchar;
        for (int i = 0; i < text.length(); i++) {
            tmpchar = text.charAt(i);
            if ((tmpchar == ' ' || tmpchar=='\t') && trigger < 0) {
                tmptextbuilder = "";
                continue;
            }
            if (tmpchar == '.') {
                trigger = 0;
            }
            if (trigger >= 0) {
                if (trigger <= numberOfCharAfterDot) {
                    if (tmpchar == ' ') {
                        domains.add(tmptextbuilder);
                        tmptextbuilder = "";
                        trigger = -2;
                    }
                }
                trigger++;
            }
            tmptextbuilder += tmpchar.toString();
        }
        return domains;
    }
}
