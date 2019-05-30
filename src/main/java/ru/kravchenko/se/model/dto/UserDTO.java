package ru.kravchenko.se.model.dto;

import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kravchenko.se.model.entity.Status;

/**
 * @author Roman Kravchenko
 */

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends AbstractEntityDTO {

    @Nullable
    private String login = "";

    @Nullable
    private String password = "";

    @Nullable
    private Status role = Status.USER;

}
