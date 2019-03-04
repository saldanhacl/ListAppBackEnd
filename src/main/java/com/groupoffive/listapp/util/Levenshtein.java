package com.groupoffive.listapp.util;

import info.debatty.java.stringsimilarity.NormalizedLevenshtein;

public class Levenshtein {

    /**
     * Devolve a relevância de duas Strings passadas por parâmetro
     * @param s1
     * @param s2
     * @return
     */
    public static Double stringsDistance(String s1, String s2) {
        NormalizedLevenshtein l = new NormalizedLevenshtein();
        Double distancia        = l.distance(s1, s2);
        if (s1.toLowerCase().contains(s2.toLowerCase()) ||
                s2.toLowerCase().contains(s1.toLowerCase())) {
            distancia -= s1.length() * 0.15;
        }
        return distancia;
    }

}
