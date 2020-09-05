package com.pvdnc.notebook.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("request-add-note")
public class AddNoteRequest {
    @JsonProperty
    public Boolean isPrivate;
    @JsonProperty
    public String title;
    @JsonProperty
    public String content;
}
