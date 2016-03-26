package com.aeg.shared.spring.util;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.resolver.Resolver;

/**
 *
 * @author kriswang
 *
 */
public class CollectorUtils {
    public static Yaml instanceOfYaml() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setDefaultScalarStyle(DumperOptions.ScalarStyle.DOUBLE_QUOTED);

        final Yaml yaml = new Yaml(new Constructor(), new Representer(),
                options, new Resolver() {
            protected void addImplicitResolvers() {
                addImplicitResolver(Tag.BOOL, BOOL, "yYnNtTfFoO");
                addImplicitResolver(Tag.MERGE, MERGE, "<");
                addImplicitResolver(Tag.NULL, NULL, "~nN\0");
                addImplicitResolver(Tag.NULL, EMPTY, null);
                addImplicitResolver(Tag.TIMESTAMP, TIMESTAMP,
                        "0123456789");
                addImplicitResolver(Tag.STR, VALUE, "=");
            }
        });

        return yaml;
    }
}