package com.groupoffive.listapp.util;

import info.debatty.java.stringsimilarity.NormalizedLevenshtein;

import java.text.Normalizer;

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

        /* Caso uma das strings contenha a outra, remover 0,1*t de distância */
        if (normalizeString(s1).contains(normalizeString(s2)) ||
                normalizeString(s2).contains(normalizeString(s1))) {
            distancia -= s1.length() * 0.1d;
        }

        /* Caso uma das strings comece com a outra, remover 0,05*t de distância */
        if (normalizeString(s1).startsWith(normalizeString(s2)) ||
                normalizeString(s2).startsWith(normalizeString(s1))) {
            distancia -= s1.length() * 0.05d;
        }

        return distancia;
    }

    private static String normalizeString(String text) {
        return text == null ? null : Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }

}
