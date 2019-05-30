package ru.kravchenko.se.model.dto;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Roman Kravchenko
 */

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEntityDTO {

    @NotNull
    protected String id = UUID.randomUUID().toString();

    @Nullable
    protected String name;

    @Nullable
    protected String description;

}
