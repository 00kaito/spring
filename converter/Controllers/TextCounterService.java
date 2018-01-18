package com.coverter.app.converter.Controllers;

import org.springframework.stereotype.Service;

@Service
public class TextCounterService {
    public static int countWords(String text){
        int result = 1;
        if (text.length()==0)
            return 0;

        for (int i=0; i<text.length(); i++){
            if (text.charAt(i)==' ')
                result++;
        }
        return result;
    }
    public static int countCharacters(String text){
        return text.length();
    }
}
