package com.pvdnc.notebook.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Note Update Request")
@JsonRootName("request-update-note")
public class UpdateNoteRequest {
    @ApiModelProperty(value = "note id",required = true)
    @JsonProperty
    public Long id;
    @ApiModelProperty(value = "must be contained otherwise the note will become a public one",required = true)
    @JsonProperty
    public Boolean isPrivate;
    @ApiModelProperty(value = "new title (null if need not to update)",allowEmptyValue = true)
    @JsonProperty
    public String title;//不修改的留空
    @ApiModelProperty(value = "new content (null if need not to update)",allowEmptyValue = true)
    @JsonProperty
    public String content;//不修改的留空
}
