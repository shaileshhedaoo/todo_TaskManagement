package com.mytodoapp.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TaskResponse {

    private String title;

    private String description;

    private boolean completed;
}
