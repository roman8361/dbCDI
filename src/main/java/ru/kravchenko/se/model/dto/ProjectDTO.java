package ru.kravchenko.se.model.dto;

import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Roman Kravchenko
 */

@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO extends BaseEntityDTO {

    public ProjectDTO(
            @Nullable final String name,
            @Nullable final String description,
            @Nullable final String userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

}
