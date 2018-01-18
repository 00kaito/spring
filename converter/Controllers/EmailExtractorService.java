package com.coverter.app.converter.Controllers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmailExtractorService {
    public static ArrayList<String> findEmails(String text) {
        text = text + " ";
        ArrayList<String> emails = new ArrayList<String>();
        String tmptextbuilder = "";
        int numberOfCharAfterDot = 25;
        int trigger = -2;
        Character tmpchar;
        for (int i = 0; i < text.length(); i++) {
            tmpchar = text.charAt(i);
            if ((tmpchar == ' ' || tmpchar=='\t') && trigger < 0) {
                tmptextbuilder = "";
                continue;
            }
            if (tmpchar == '@') {
                trigger = 0;
            }
            if (trigger >= 0) {
                if (trigger <= numberOfCharAfterDot) {
                    if (tmpchar == ' ') {
                        emails.add(tmptextbuilder);
                        tmptextbuilder = "";
                        trigger = -2;
                    }
                }
                trigger++;
            }
            tmptextbuilder += tmpchar.toString();
        }
        return emails;
    }
}
