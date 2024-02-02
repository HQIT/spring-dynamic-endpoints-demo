package com.cloume.fkb.rest;

import com.cloume.fkb.domain.EndpointDescriptor;
import com.cloume.fkb.service.DynamicRouteService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class EndpointsController {
    private final DynamicRouteService dynamicRouteService;

    public EndpointsController(DynamicRouteService dynamicRouteService) {
        this.dynamicRouteService = dynamicRouteService;
    }

    @PostMapping("/endpoints2")
    public String createEndpoint(@RequestBody EndpointDescriptor descriptor) {
        dynamicRouteService.addDynamicRoute(descriptor);
        return "Endpoint created";
    }

    @PostMapping("/endpoints")
    public String createEndpoint(@RequestBody String yamlContentOfOpenAPI) {
        SwaggerParseResult result = new OpenAPIV3Parser().readContents(yamlContentOfOpenAPI);
        OpenAPI api = result.getOpenAPI();
        //dynamicRouteService.addDynamicRoute(api.getPaths().values(), descriptor.getResponse());
        return "Endpoint created";
    }

    @DeleteMapping("/endpoints/{id}")
    public Mono<String> deleteEndpoint(@PathVariable("id") String id){
        dynamicRouteService.removeDynamicRoute(id);
        return Mono.just("Good");
    }
}
