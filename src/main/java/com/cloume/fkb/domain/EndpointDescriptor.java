package com.cloume.fkb.domain;

import lombok.Data;

@Data @Deprecated
public class EndpointDescriptor {
    private String id;
    private String path;
    private String response = "Not found";
}
