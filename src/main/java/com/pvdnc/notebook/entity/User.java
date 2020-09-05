package com.pvdnc.notebook.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

@JsonRootName("user")
public class User implements Serializable {
    public static final long serialVersionUID=1L;
    @JsonProperty
    public String name;
    @JsonProperty
    public String password;
}
