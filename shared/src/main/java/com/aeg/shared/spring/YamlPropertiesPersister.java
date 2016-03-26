package com.aeg.shared.spring;

import com.aeg.shared.spring.util.CollectorUtils;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertiesPersister;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class YamlPropertiesPersister implements PropertiesPersister {
    @Override
    public void load(Properties props, InputStream is) throws IOException {
        load(props, new InputStreamReader(is));
    }

    /**
     * We want to traverse map representing Yaml object and each time we find String=String pair we want to
     * save it as Property. As we are going deeper into map we generate compound key as path-like String
     *
     * @param props
     * @param reader
     * @throws IOException
     * @see org.springframework.util.PropertiesPersister#load(java.util.Properties, java.io.Reader)
     */
    @Override
    public void load(Properties props, Reader reader) throws IOException {
        Yaml yaml = CollectorUtils.instanceOfYaml();
        Map<String, Object> map = (Map<String, Object>) yaml.load(reader);
        // now we can populate supplied props
        assignProperties(props, map, null);
    }

    /**
     * @param props
     * @param map
     */
    public void assignProperties(Properties props, Map<String, Object> map, String path) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            if (StringUtils.isNotEmpty(path))
                key = path + "." + key;
            Object val = entry.getValue();
            if (val instanceof String) {
                // see if we need to create a compound key
                props.put(key, val);
            } else if (val instanceof Map) {
                assignProperties(props, (Map<String, Object>) val, key);
            }
        }
    }

    @Override
    public void store(Properties props, OutputStream os, String header) throws IOException {
        throw new NotImplementedException("Current implementation is a read-only");
    }

    @Override
    public void store(Properties props, Writer writer, String header) throws IOException {
        throw new NotImplementedException("Current implementation is a read-only");
    }

    @Override
    public void loadFromXml(Properties props, InputStream is) throws IOException {
        throw new NotImplementedException("Use DefaultPropertiesPersister if you want to read/write XML");
    }

    @Override
    public void storeToXml(Properties props, OutputStream os, String header) throws IOException {
        throw new NotImplementedException("Use DefaultPropertiesPersister if you want to load/store to XML");
    }

    @Override
    public void storeToXml(Properties props, OutputStream os, String header, String encoding) throws IOException {
        throw new NotImplementedException("Use DefaultPropertiesPersister if you want to read/write XML");
    }

    /*public static Yaml instanceOfYaml() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setDefaultScalarStyle(DumperOptions.ScalarStyle.DOUBLE_QUOTED);
        final Yaml yaml = new Yaml(new Loader(), new Dumper(options), new Resolver() {

            @Override
            protected void addImplicitResolvers() {
                addImplicitResolver(Tag.BOOL, BOOL, "yYnNtTfFoO");
                // disable resolving of floats and integers
                // addImplicitResolver(Tags.FLOAT, FLOAT, "-+0123456789.");
                // addImplicitResolver(Tag.INT, INT, "-+0123456789");
                addImplicitResolver(Tag.MERGE, MERGE, "<");
                addImplicitResolver(Tag.NULL, NULL, "~nN\0");
                addImplicitResolver(Tag.NULL, EMPTY, null);
                addImplicitResolver(Tag.TIMESTAMP, TIMESTAMP, "0123456789");
                addImplicitResolver(Tag.VALUE, VALUE, "=");
            }
        });
        return yaml;
    }*/
}