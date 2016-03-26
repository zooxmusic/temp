package com.aeg.ims.config;

import com.aeg.ims.model.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.jboss.security.auth.spi.Users;
import org.jgroups.util.Digest;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * HibernateLoginModule is a LoginModule that authenticates
 * a given username/password credential against a Hibernate
 * session.
 *
 * @see javax.security.auth.spi.LoginModule
 */
public class HibernateLoginModule implements LoginModule {

    // initial state
    CallbackHandler handler;
    Subject subject;
    Map sharedState;
    Map options;
    com.aeg.ims.config.Digest digest;

    // temporary state
    Vector principals, credentials;

    // authentication status
    boolean success;

    // configurable options
    boolean debug;

    /** Hibernate session factory  */
    SessionFactory sf = null;

    /** Hibernate query */
    private static final String query =
            "from u in class " + Users.User.class + " where u.name=?";

    public HibernateLoginModule() {
        credentials = new Vector();
        principals = new Vector();
        success = false;
        debug = false;
    }

    /**
     * Initialize our state.
     */
    public void initialize (Subject subject, CallbackHandler handler,
                            Map sharedState, Map options) {

        this.handler = handler;
        this.subject = subject;
        this.sharedState = sharedState;
        this.options = options;

        if (options.containsKey("debug")) {
            debug = "true".equalsIgnoreCase((String) options.get("debug"));
        }
        if (options.containsKey("digest")) {
            digest = new com.aeg.ims.config.Digest((String) options.get("digest"));
        } else {
            digest = new com.aeg.ims.config.Digest();
        }

        // elided: standard code to get Hibernate =SessionFactory=.
    }

    /**
     *  First phase of login process.
     */
    public boolean login() throws LoginException {
        if (handler == null) {
            throw new LoginException("Error: no CallbackHandler available");
        }

        try {
            Callback[] callbacks = new Callback[] {
                    new NameCallback("User: "),
                    new PasswordCallback("Password: ", false)
            };

            handler.handle(callbacks);

            String username = ((NameCallback) callbacks[0]).getName();
            char[] password = ((PasswordCallback) callbacks[1]).getPassword();

            ((PasswordCallback) callbacks[1]).clearPassword();

            success = validate(username, password);

            callbacks[0] = null;
            callbacks[1] = null;

            if (!success) {
                throw new LoginException("Authentication failed: Password does not match");
            }
            return true;
        } catch (LoginException e) {
            throw e;
        } catch (Exception e) {
            success = false;
            throw new LoginException(e.getMessage());
        }
    }

    /**
     * Second phase of login - by now we know user is authenticated
     * and we just need to update the subject.
     */
    public boolean commit() throws LoginException {
        if (success) {
            if (subject.isReadOnly()) {
                throw new LoginException("Subject is read-only");
            }

            try {
                Iterator i = principals.iterator();
                subject.getPrincipals().addAll(principals);
                principals.clear();
                return true;
            } catch (Exception e) {
                throw new LoginException(e.getMessage());
            }
        } else {
            principals.clear();
        }
        return true;
    }

    /**
     * Second phase - somebody else rejected user so we need to
     * clear our state.
     */
    public boolean abort() throws LoginException {
        success = false;
        logout();
        return true;
    }

    /**
     *  User is logging out - clear our information from the subject.
     */
    public boolean logout() throws LoginException {
        principals.clear();

        // remove the principals the login module added
        Iterator i = subject.getPrincipals(HibernatePrincipal.class).iterator();
        while (i.hasNext()) {
            HibernatePrincipal p = (HibernatePrincipal) i.next();
            subject.getPrincipals().remove(p);
        }

        return true;
    }

    /**
     * Validate the user name and password.  This is the Hibernate-specific
     * code.
     */
    private boolean validate(String username, char[] password) throws Exception {
        boolean valid = false;
        List users = null;

        Session s = null;
        try {
            s = sf.openSession();
           // users = (List) s.find(query, username, Hibernate.STRING);
        } catch (Exception e) {
        } finally {
            if (s != null) {
                try { s.close(); } catch (HibernateException e) { }
            }
        }

        // are there no matching records?...
        if (users == null || users.size() == 0) {
            return false;
        }

        // compare passwords...
        User user = (User) users.get(0);
        String hash = user.getPassword();
        if (hash != null && password != null && password.length > 0) {
            valid = hash.equals(digest.digest(new String(password)));
        }

        if (valid) {
            this.principals.add(new com.aeg.ims.config.HibernatePrincipal(user.getName()));
        }
        return valid;
    }
}