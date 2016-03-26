package com.aeg.ims.config;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.*;
import java.util.Iterator;

/**
 * Simple JAAS-aware application.
 */
public class Test {

    LoginContext l = null;

    /**
     * attempt to log in as the user, returning the =Subject=
     * if successful.
     */
    public Subject login(String username, char[] credentials) {
        try {
            CallbackHandler cb = new Handler(this, username, credentials);
            l = new LoginContext("Example", cb);
        } catch (LoginException e) {
            return null;
        }

        Subject subject = null;
        try {
            l.login();
            subject = l.getSubject();
            if (subject == null) {
                return null;
            }
        } catch (AccountExpiredException e) {
        } catch (CredentialExpiredException e) {
        } catch (FailedLoginException e) {
        } catch (LoginException e) {
        }
        return subject;
    }

    /**
     * log out of application
     */
    public void logout() {
        if (l != null) {
            try {
                l.logout();
            } catch (LoginException e) {
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Test t = new Test();
        String username = "mazhar";
        String password = "123";

        Subject subj = t.login(username, password.toCharArray());
        if (subj != null) {
            Iterator i = subj.getPrincipals(HibernatePrincipal.class).iterator();
            while (i.hasNext()) {
                HibernatePrincipal p = (HibernatePrincipal) i.next();
                System.out.println("logged in as: " + p.getName());
            }
            t.logout();
        }
        else {
            System.out.println("unable to log in as user");
        }
    }
}