package com.utkubayrak.ToDoApp.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Getter
@Setter
abstract public class AuditingAwareBaseDto implements Serializable {

    //Serileştirme
    public static final Long serialVersionUID = 1L;

    private Long id; // ID
    @Builder.Default
    private Date systemDate=new Date(System.currentTimeMillis()); // DATE

    // AUDITING
    @JsonIgnore
    protected String createdUser;

    @JsonIgnore
    protected Date createdDate;

    @JsonIgnore
    protected String updatedUser;

    @JsonIgnore
    protected Date updatedDate;
}