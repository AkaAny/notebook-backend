package com.pvdnc.notebook.entity.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("result")
public class BaseResult {
    @JsonProperty
    public int code;

    @JsonProperty
    public String msg;
}
