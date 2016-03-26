package com.aeg.ims.config;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * CallbackHanlder implementation.
 *
 *
 * @author Rahul Bhattacharjee
 *
 */
public class RanchCallbackHandler implements CallbackHandler{

    private String userName;
    private String password;

    /**
     * @param user
     * @param pass
     */
    public RanchCallbackHandler(String user , String pass){
        this.userName = user;
        this.password = pass;
    }

    public void handle(Callback[] suppliedCallback) throws IOException,UnsupportedCallbackException {

        for (int i = 0; i < suppliedCallback.length; i++) {
            if (suppliedCallback[i] instanceof NameCallback) {
                if (userName != null) {
                    ((NameCallback)suppliedCallback[i]).setName(userName);
                }
            }
            else if (suppliedCallback[i] instanceof PasswordCallback) {
                if (password != null) {
                    ((PasswordCallback)suppliedCallback[i]).setPassword(password.toCharArray());
                }
            }
            else {
                throw new UnsupportedCallbackException(suppliedCallback[i]);
            }
        }

    }

}
