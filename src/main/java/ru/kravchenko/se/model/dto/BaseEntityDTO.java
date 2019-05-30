package ru.kravchenko.se.model.dto;

import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.Setter;
import ru.kravchenko.se.model.entity.StatusProjectTask;

import java.util.Date;

/**
 * @author Roman Kravchenko
 */

@Getter
@Setter
public class BaseEntityDTO extends AbstractEntityDTO {

    @Nullable
    protected Date dateBegin = new Date();

    @Nullable
    protected Date dateEnd = null;

    @Nullable
    protected String userId = "";

    @Getter
    protected StatusProjectTask status = StatusProjectTask.PLANNED;

}
