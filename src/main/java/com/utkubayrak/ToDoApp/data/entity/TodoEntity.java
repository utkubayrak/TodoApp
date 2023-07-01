package com.utkubayrak.ToDoApp.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties(value = {"created_date,updated_date"},allowGetters = true)

@EntityListeners(AuditingEntityListener.class) // AUDITING

@Table(name="todos")
public class TodoEntity extends BaseEntity implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Column(name = "content")
    private String content;
    @Column(name = "is_checked")
    private Boolean isChecked;


}
