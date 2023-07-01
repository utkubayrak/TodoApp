package com.utkubayrak.ToDoApp.business.dto;

import com.utkubayrak.ToDoApp.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto extends AuditingAwareBaseDto implements Serializable {

    public static final Long serialVersionUID = 1L;

    @NotEmpty(message = "todo.content.validation.constraints.NotNull.message")
    private String content;
    @Builder.Default
    private Boolean isChecked = false;


}
