package com.pvdnc.notebook.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("request-user-register")
public class RegisterRequest {
    @JsonProperty
    public String name;
    @JsonProperty
    public String password;
}
