package com.unevento.api.controllers.services.BadWordsHandler;


import org.springframework.stereotype.Service;

@Service
public class ContentFilterService {
    public boolean containsBadWords(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        for (String word : BadWordsList.getBadWords()) {
            if (text.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
