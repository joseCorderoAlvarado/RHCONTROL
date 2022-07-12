/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.ws.cfg;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@Provider
public class JsonMoxyConfigurationContextResolver implements ContextResolver<MoxyJsonConfig> {

    private final MoxyJsonConfig config;

    public JsonMoxyConfigurationContextResolver() {
        final Map<String, String> namespacePrefixMapper = new HashMap<>();
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");

        config = new MoxyJsonConfig()
                .setNamespacePrefixMapper(namespacePrefixMapper)
                .setNamespaceSeparator(':')
                .setAttributePrefix("")
                .setValueWrapper("value")
                .property(JAXBContextProperties.JSON_WRAPPER_AS_ARRAY_NAME, true)
                .setFormattedOutput(true)
                .setIncludeRoot(true)
                .setMarshalEmptyCollections(true);
    }

    @Override
    public MoxyJsonConfig getContext(Class<?> objectType) {
        return config;
    }
}
