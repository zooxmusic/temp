package com.aeg.ims.config;

import javax.security.auth.callback.*;
import java.io.IOException;

/**
 * simple CallbackHandler suitable for testing purposes
 */
public class Handler implements CallbackHandler {

    private Test t;
    private String username;
    private char[] credentials;

    public Handler(Test t, String username, char[] credentials) {
        super();
        this.t = t;
        this.username = username;
        this.credentials = credentials;
    }

    public void handle(Callback callbacks[])
            throws IOException, UnsupportedCallbackException {

        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                ((NameCallback) callbacks[i]).setName(username);
            }
            else if (callbacks[i] instanceof PasswordCallback) {
                ((PasswordCallback) callbacks[i]).setPassword(credentials);
            } else {
                throw new UnsupportedCallbackException(callbacks[i]);
            }
        }
    }
}