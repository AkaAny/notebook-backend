package com.pvdnc.notebook.entity.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.pvdnc.notebook.entity.Note;

import java.util.List;

public class NoteResult extends BaseResult{
    @JsonProperty
    public List<Note> mNoteList;
}
