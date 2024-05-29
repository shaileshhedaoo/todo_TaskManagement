package com.mytodoapp.dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskRequest {

    private String title;

    private String description;

    private boolean completed;
}
