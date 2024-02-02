package com.cloume.fkb.service;

import com.cloume.fkb.DynamicRouteStore;
import com.cloume.fkb.domain.EndpointDescriptor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DynamicRouteService {
    private final DynamicRouteStore routeStore;

    public DynamicRouteService(DynamicRouteStore routeStore) {
        this.routeStore = routeStore;
    }

    public void addDynamicRoute(EndpointDescriptor descriptor) {
        descriptor.setId(UUID.randomUUID().toString());
        routeStore.addRoute(descriptor);
    }

    public void removeDynamicRoute(String id) {
        routeStore.removeRoute(id);
    }
}
