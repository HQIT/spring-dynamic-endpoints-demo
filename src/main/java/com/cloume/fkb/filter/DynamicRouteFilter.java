package com.cloume.fkb.filter;

import com.cloume.fkb.DynamicRouteStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Order(-1) // 确保这个过滤器在其他过滤器之前运行
public class DynamicRouteFilter implements WebFilter {

    private final DynamicRouteStore routeStore;

    public DynamicRouteFilter(DynamicRouteStore routeStore) {
        this.routeStore = routeStore;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (routeStore.isDynamicRoute(path)) {
            String response = routeStore.getResponseForPath(path);
            // 假设所有动态路由的响应都是简单的文本
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                    .bufferFactory().wrap(response.getBytes())));
        }
        // 对于非动态路由，放行请求
        return chain.filter(exchange);
    }
}
