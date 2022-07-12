package com.arrebol.apc.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Picasso
 */
public class APCSecure {
    
    private static final String DIGEST_METHOD = "SHA-256";
    private static final String ENCODING = "UTF-8";
    private static final String[] SECRET = {"Capoeira", "CaperucitaRoja", "Guardian"};

    private final String app;
    private String password;

    public APCSecure(String app, String password) {
        this.app = app;
        this.password = password;
    }
    
    public String getPassword() {
        MessageDigest md = null;
        StringBuilder builder = new StringBuilder().append(SECRET[0].charAt(SECRET[0].length() - 2));

        try {
            md = MessageDigest.getInstance(DIGEST_METHOD);

            builder.append(password.charAt(1));
            builder.append(password.charAt(password.length() - 2));
            builder.append(password);
            builder.append(SECRET[1]);
            builder.append(password.charAt(0));
            builder.append(password.charAt(password.length() - 1));

            for (int i = app.length(); i > 0; i--) {
                builder.append(app.charAt(i - 1));
            }
            
            for (int i = SECRET[0].length(); i > 0; i--) {
                builder.append(SECRET[0].charAt(i - 1));
            }

            for (int i = password.length(); i > 0; i--) {
                builder.append(password.charAt(i - 1));
            }
            
            for (int i = 0; i < SECRET[2].length();  i++) {
                builder.append(SECRET[2].charAt(i));
            }
            
            byte[] digest = md.digest(builder.toString().getBytes(Charset.forName(ENCODING)));
            
            return this.password = DatatypeConverter.printHexBinary(digest).toUpperCase();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(APCSecure.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
}
