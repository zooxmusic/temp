package com.aeg.ims.config;

import org.apache.catalina.util.HexUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Quick and dirty password digest function.  The HexUtils class
 * comes from the Tomcat catalina.jar.
 */
public class Digest {

    static MessageDigest md = null;

    public Digest() {
        this("MD5");
    }

    public Digest(String digest) {
        try {
            md = MessageDigest.getInstance(digest);
        } catch (NoSuchAlgorithmException e) {
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) { }
        }
    }

    /**
     * Digest function from Tomcat.
     */
    public String digest(String credentials) {
        if (md == null) {
            return credentials;
        }

        synchronized (this) {
            try {
                md.reset();
                md.update(credentials.getBytes());
                return (HexUtils.convert(md.digest()));
            } catch (Exception e) {
                return credentials;
            }
        }
    }
}