package com.pvdnc.notebook.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("request-delete-note")
public class DeleteNoteRequest {
    @JsonProperty
    public Long id;
}
