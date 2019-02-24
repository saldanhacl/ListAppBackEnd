package com.groupoffive.listapp.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptSha256 implements Crypt {

    /**
     * Criptografa uma string com o algoritmo SHA-256
     * @param string
     * @return
     */
    @Override
    public String cryptString(String string) {
        String cryptedString     = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash          = digest.digest(string.getBytes(StandardCharsets.UTF_8));

            cryptedString        = bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            // Não vai acontecer, se Deus quiser
        }
        return cryptedString;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
