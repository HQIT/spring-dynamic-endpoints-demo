package com.cloume.fkb;

import com.cloume.fkb.domain.EndpointDescriptor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class DynamicRouteStore {
    private final ConcurrentHashMap<String, EndpointDescriptor> routes = new ConcurrentHashMap<>();

    public void addRoute(EndpointDescriptor descriptor) {
        routes.put(descriptor.getId(), descriptor);
    }

    public void removeRoute(String id) {
        routes.remove(id);
    }

    public boolean isDynamicRoute(String path) {
        return !getRoutes().values().stream()
                .filter(endpoint -> path.equals(endpoint.getPath()))
                .findAny().isEmpty();
    }

    public ConcurrentHashMap<String, EndpointDescriptor> getRoutes() {
        return routes;
    }

    public String getResponseForPath(String path) {
        return getRoutes().values().stream()
                .filter(endpoint -> path.equals(endpoint.getPath()))
                .findFirst()
                .orElse(new EndpointDescriptor())
                .getResponse();
    }
}
