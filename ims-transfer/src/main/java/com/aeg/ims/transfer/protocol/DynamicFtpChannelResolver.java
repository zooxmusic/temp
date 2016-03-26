package com.aeg.ims.transfer.protocol;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.messaging.MessageChannel;


public class DynamicFtpChannelResolver {

    //In production environment this value will be significantly higher
    //This is just to demonstrate the concept of limiting the max number of
    //Dynamically created application contexts we'll hold in memory when we execute
    //the code from a junit
    public static final int MAX_CACHE_SIZE = 2;

    private final LinkedHashMap<String, MessageChannel> channels = new LinkedHashMap<String, MessageChannel>() {

                private static final long serialVersionUID = 1L;

                @Override
                protected boolean removeEldestEntry(Entry<String, MessageChannel> eldest) {
                    //This returning true means the least recently used
                    //channel and its application context will be closed and removed
                    boolean remove = size() > MAX_CACHE_SIZE;
                    if(remove) {
                        MessageChannel channel = eldest.getValue();
                        ConfigurableApplicationContext ctx = contexts.get(channel);
                        if(ctx != null) { //shouldn't be null ideally
                            ctx.close();
                            contexts.remove(channel);
                        }
                    }
                    return remove;
                }
            };

    private final Map<MessageChannel, ConfigurableApplicationContext> contexts = new HashMap<MessageChannel, ConfigurableApplicationContext>();



    /**
     * Resolve a client to a channel, where each client gets a private
     * application context and the channel is the inbound channel to that
     * application context.
     *
     * @param client
     * @return a channel
     */
    public MessageChannel resolve(String client) {
        MessageChannel channel = this.channels.get(client);
        if (channel == null) {
            channel = createNewClientChannel(client);
        }
        return channel;
    }

    private synchronized MessageChannel createNewClientChannel(String client) {
        MessageChannel channel = this.channels.get(client);
        if (channel == null) {
            ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
                    new String[] { "classpath:dynamic-ftp-outbound-adapter-context.xml" },
                    false);

            this.setEnvironmentForClient(ctx, client);
            ctx.refresh();
            channel = ctx.getBean("toFtpChannel", MessageChannel.class);
            this.channels.put(client, channel);
            //Will works as the same reference is presented always
            this.contexts.put(channel, ctx);
        }
        return channel;
    }

    /**
     * Use Spring 3.1. environment support to set properties for the
     * customer-specific application context.
     *
     * @param ctx
     * @param client
     */
    private void setEnvironmentForClient(ConfigurableApplicationContext ctx, String client) {
        StandardEnvironment env = new StandardEnvironment();
        Properties props = new Properties();
        // populate properties for customer
        props.setProperty("host", "host.for." + client);
        props.setProperty("user", "user");
        props.setProperty("password", "password");
        props.setProperty("remote.directory", "/tmp");
        PropertiesPropertySource pps = new PropertiesPropertySource("ftpprops", props);
        env.getPropertySources().addLast(pps);
        ctx.setEnvironment(env);
    }
}