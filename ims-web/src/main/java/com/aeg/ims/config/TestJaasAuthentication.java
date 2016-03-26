package com.aeg.ims.config;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.util.Properties;

/**
 *
 * @author Rahul Bhattacharjee
 *
 */
public class TestJaasAuthentication {

    public static void main(String[] args) {
        static {
            Properties properties = getContextProperties();
            try {
                context = (Context) new InitialContext(properties).lookup("ejb:");
            } catch (NamingException e) {
                throw new IllegalArgumentException("Couldn't instantiate initial context", e);
            }
        }


    }

    private static Properties getContextProperties() {
        Properties properties = new Properties();
        properties.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        properties.setProperty("endpoint.name", "client-endpoint");
        properties.setProperty("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        properties.setProperty("remote.connections", "default");
        properties.setProperty("remote.connection.default.port", System.getProperty("remote.connection.default.port"));
        properties.setProperty("remote.connection.default.host", System.getProperty("remote.connection.default.host"));
        properties.setProperty("remote.connection.default.username", Authenticator.getPrincipalName());
        properties.setProperty("remote.connection.default.password", Authenticator.getPrincipalSecret());
        properties.setProperty("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
        properties.setProperty("connect.options.org.jboss.remoting3.RemotingOptions.HEARTBEAT_INTERVAL", HEARTBEAT_INTERVAL_VALUE);
        properties.setProperty("org.jboss.ejb.client.scoped.context", "true");
        return properties;
    }

    public Object lookUpBean(String serviceName, Class serviceClass) {
//        LOG.debug("getEJBHome(" + serviceName + ")");
        Object docomueBean;
  //      LOG.debug("Look up the remote bean for the service: " + serviceName);
        StringBuilder beanLookUpPath = new StringBuilder();
        beanLookUpPath.append(APPLICATION_NAME);
        beanLookUpPath.append("/");
        beanLookUpPath.append(SERVICES_JAR_NAME);
        beanLookUpPath.append("/");
        beanLookUpPath.append(serviceName);
        beanLookUpPath.append(BEAN_POSTFIX);
        beanLookUpPath.append(serviceClass.getName());


        if (STATEFUL_SERVICES_NAMES.contains(serviceName)) {
            beanLookUpPath.append("?stateful");
        }
        try {
            docomueBean = context.lookup(beanLookUpPath.toString());
        } catch (Exception ex) {
            String errmsg = "Failed to return the EJBHome for " + serviceName + " object: ";
            errmsg += ex;
            LOG.error(errmsg);
            throw new BusinessException(BusinessException.CREATE_SESSIONBEAN_FEHLGESCHLAGEN, errmsg, ex);
        }
        return docomueBean;
    }
    /*public static void main(String[] args) {
        try {
            Properties jndiProps = new Properties();
            jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            jndiProps.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
            jndiProps.put("remote.connections", "default");
            jndiProps.put("remote.connection.default.host", "localhost");
            jndiProps.put("remote.connection.default.port", "4447");
            jndiProps.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
            jndiProps.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
            jndiProps.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_DISALLOWED_MECHANISMS","JBOSS-LOCAL-USER");
            jndiProps.put("jboss.naming.client.ejb.context", "true");

            //jndiProps.put(Context.PROVIDER_URL,"remote://localhost:4447");
            // username
            jndiProps.put(Context.SECURITY_PRINCIPAL, "zooxmusic");
            // password
            jndiProps.put(Context.SECURITY_CREDENTIALS, "Z00xMu$1c");

            // This is an important property to set if you want to do EJB invocations via the remote-naming project
            //jndiProps.put("jboss.naming.client.ejb.context", false);
            // create a context passing these properties
            Context ctx = new InitialContext(jndiProps);

            DynamicFormRemote dyn = (DynamicFormRemote)ctx.lookup( "java:global/Reapp-0.1-dev/ReAppEjb/DynamicFormRemote!it.antlia.reapp.session.DynamicFormRemote");
            dyn.findAllOrderedByName();
        }
        catch(Exception ne) {
            ne.printStackTrace();
        }

    }*/












    /*public static void main(String[] args) {

        String userName = System.getProperty("user");
        String password = System.getProperty("pass");

        boolean loginStatus = true;

        CallbackHandler handler = new RanchCallbackHandler(userName,password);

        try {

            LoginContext loginContext = new LoginContext("RanchLogin" , handler);
            loginContext.login();

        } catch (LoginException e) {
            e.printStackTrace();
            loginStatus = false;
        }

        if(loginStatus){
            System.out.println("Login Successful.");
        }else{
            System.out.println("Login Failed.");
        }
    }*/

}
