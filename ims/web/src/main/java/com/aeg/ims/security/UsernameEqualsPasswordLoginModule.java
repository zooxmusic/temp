package com.aeg.ims.security;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class UsernameEqualsPasswordLoginModule implements LoginModule {
    // ~ Instance fields
    // ================================================================================================

    private String password;
    private String username;
    private Subject subject;

    // ~ Methods
    // ========================================================================================================

    public boolean abort() throws LoginException {
        return true;
    }

    public boolean commit() throws LoginException {
        return true;
    }

    public void initialize(Subject subject, CallbackHandler callbackHandler,
                           Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;

        try {
            NameCallback nameCallback = new NameCallback("prompt");
            PasswordCallback passwordCallback = new PasswordCallback("prompt", false);

            callbackHandler.handle(new Callback[] { nameCallback, passwordCallback });

            password = new String(passwordCallback.getPassword());
            username = nameCallback.getName();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login() throws LoginException {
        if (username == null || !username.equals(password)) {
            throw new LoginException("username is not equal to password");
        }
        if ("".equals(username)) {
            throw new LoginException("username cannot be empty string");
        }

        subject.getPrincipals().add(new UsernamePrincipal(username));
        return true;
    }

    public boolean logout() throws LoginException {
        return true;
    }

    private static class UsernamePrincipal implements Principal, Serializable {
        private final String username;

        public UsernamePrincipal(String username) {
            this.username = username;
        }

        public String getName() {
            return username;
        }

        public String toString() {
            return "Principal [name=" + getName() + "]";
        }

        private static final long serialVersionUID = 8049681145355488137L;
    }
}