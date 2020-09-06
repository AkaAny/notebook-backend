package com.pvdnc.notebook.controller;

import com.pvdnc.notebook.aop.NeedLoginCheck;
import com.pvdnc.notebook.entity.Note;
import com.pvdnc.notebook.entity.request.AddNoteRequest;
import com.pvdnc.notebook.entity.request.DeleteNoteRequest;
import com.pvdnc.notebook.entity.request.UpdateNoteRequest;
import com.pvdnc.notebook.entity.rest.BaseResult;
import com.pvdnc.notebook.entity.rest.NoteResult;
import com.pvdnc.notebook.entity.rest.ResultFactory;
import com.pvdnc.notebook.mapper.NoteMapper;
import com.pvdnc.notebook.utils.SessionWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Api("Note CRUD APIs")
@RestController
public class NoteController {

    @Resource
    private NoteMapper mNoteMapper;

    @ApiOperation(value ="Get all Notes set to be public",httpMethod = "GET")
    @GetMapping("/note/public")
    public NoteResult getNote(HttpSession session){
        NoteResult result=new NoteResult();
        String name= SessionWrapper.getName(session);
        if(name==null){//未登录
            result.code=200;
            result.msg="since did not logon,only public note are return";
            result.mNoteList=mNoteMapper.getNoteForGuest();
            return result;
        }
        result.code=200;
        result.msg="name:"+name+" -> session id:"+session.getId();
        result.mNoteList= mNoteMapper.getNoteList(name);
        return result;
    }

    @ApiOperation(value ="[Login Required] Get all Notes added by current user in session",httpMethod = "GET")
    @NeedLoginCheck(action = "get self notes",resultType = NoteResult.class)
    @GetMapping("/note/self")
    public NoteResult getNoteForSelf(HttpSession session){
        NoteResult result=new NoteResult();
        String name= SessionWrapper.getName(session);
        result.code=200;
        result.msg="name:"+name+" -> session id:"+session.getId();
        result.mNoteList= mNoteMapper.getUserNote(name);
        return result;
    }


    @ApiOperation(value ="Get a Note by id",httpMethod = "GET")
    @GetMapping("/note/{id}")
    public NoteResult getNoteById(@ApiParam(value = "note id",required = true) @PathVariable("id") Long id,
                                  HttpSession session){
        NoteResult result=new NoteResult();
        Note note=mNoteMapper.getNoteById(id);
        if(note==null){
            result.code=404;
            result.msg="note id:"+id+" does not exist";
            return result;
        }
        String name= SessionWrapper.getName(session);
        if(note.isPrivate) {//私有note只有创建的用户可以访问
            if(!checkNoteOp(name,id,note,result)){
                return result;
            }
        }
        result.code=200;
        result.msg="name:"+name+" -> session id:"+session.getId();
        result.mNoteList= Collections.singletonList(note);
        return result;
    }

    @ApiOperation(value ="[Login Required] Add a Note",httpMethod = "POST",produces = "application/json;charset=UTF-8")
    @NeedLoginCheck(action = "add note")
    @PostMapping(value = "/note/add",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResult addNote(@RequestBody AddNoteRequest request,HttpSession session){
        BaseResult result=new BaseResult();
        String name= SessionWrapper.getName(session);
        Note note=new Note();
        note.fromUser=name;
        note.isPrivate=request.isPrivate;
        note.title=request.title;
        note.content=request.content;
        mNoteMapper.addNote(note);
        result.code=200;
        result.msg="user:"+name+" note added";
        return result;
    }

    @ApiOperation(value ="[Login Required] Delete a Note",httpMethod = "DELETE",produces = "application/json;charset=UTF-8")
    @NeedLoginCheck(action = "delete note")
    @DeleteMapping(value = "/note/delete",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResult deleteNote(@RequestBody DeleteNoteRequest request,HttpSession session){
        BaseResult result=new BaseResult();
        String name= SessionWrapper.getName(session);
        Long noteId=request.id;
        Note noteToDelete=mNoteMapper.getNoteById(noteId);
        if(!checkNoteOp(name,noteId,noteToDelete,result)){
            return result;
        }
        mNoteMapper.deleteNote(noteId);
        result.code=200;
        result.msg="note:"+noteId+" deleted";
        return result;
    }

    private <T extends BaseResult> boolean checkNoteOp(String name,Long noteId, Note note,
                                T result)
            throws NullPointerException,UnsupportedOperationException {
        if(note==null){
            result.code=404;
            result.msg="note id:"+noteId+" does not exist";
            return false;
        }
        if(!note.fromUser.equals(name)){
            result.code=403;
           result.msg= "note id:"+note.id
                    +" does not belong to user:"+name;
           return false;
        }
        return true;
    }

    @ApiOperation(value ="[Login Required] Update a Note",httpMethod = "PUT",produces = "application/json;charset=UTF-8")
    @NeedLoginCheck(action = "update note")
    @PutMapping(value = "/note/update",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResult updateNote(@RequestBody UpdateNoteRequest request,HttpSession session){
        BaseResult result=new BaseResult();
        String name= SessionWrapper.getName(session);
        Long noteId=request.id;
        Note noteToUpdate=mNoteMapper.getNoteById(noteId);

            if(!checkNoteOp(name,noteId,noteToUpdate,result)){
                return result;
            }
        StringBuilder resultMsgBuilder=new StringBuilder("note:"+noteId+" update ops:");
        noteToUpdate.isPrivate=request.isPrivate;
        if(request.title!=null){
            noteToUpdate.title=request.title;
            resultMsgBuilder.append("title updated").append("\n");
        }
        if(request.content!=null){
            noteToUpdate.content=request.content;
            resultMsgBuilder.append("content update").append("\n");
        }
        mNoteMapper.updateNote(noteToUpdate);
        resultMsgBuilder.append("committed");
        result.code=200;
        result.msg=resultMsgBuilder.toString();
        return result;
    }
}
