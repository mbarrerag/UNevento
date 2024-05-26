package com.unevento.api.controllers.services.BadWordsHandler;

import java.util.Arrays;
import java.util.List;

public class BadWordsList {
    private static final List<String> BAD_WORDS = Arrays.asList( "careverga",
            "hpta", "mrd", "fck", "bastardo", "puta", "malparido", "gonorrea", "verga", "pendejo", "culero",
            "coño", "mierda", "cabrón", "zorra", "culiao", "chinga", "chingada", "cagada", "imbecil", "estupido",
            "idiota", "naco", "picha", "pirobo", "culicagado", "sapo", "maldito", "jodido", "huevón", "lamer", "perra"
    );

    public static List<String> getBadWords() {
        return BAD_WORDS;
    }
}
