package com.pvdnc.notebook.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("Note Model")
@JsonRootName("note")
public class Note {
    @ApiModelProperty(value = "note id (auto increase and unique)",required = true)
    @JsonProperty
    public Long id= 0L;
    @ApiModelProperty(value = "user who created the note",required = true)
    @JsonProperty
    public String fromUser;
    @ApiModelProperty(value = "determine whether the note can be accessed worldwide",required = true)
    @JsonProperty
    public Boolean isPrivate;
    @JsonProperty
    @ApiModelProperty(value = "date time when the note is added",required = true)
    public Date createdDate;
    @ApiModelProperty(value = "title",required = true)
    @JsonProperty
    public String title;
    @ApiModelProperty(value = "content",required = true)
    @JsonProperty
    public String content;
}
