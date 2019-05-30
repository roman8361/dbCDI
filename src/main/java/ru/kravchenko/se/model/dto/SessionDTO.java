package ru.kravchenko.se.model.dto;

import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Roman Kravchenko
 */

@Getter
@Setter
@NoArgsConstructor
public class SessionDTO extends AbstractEntityDTO {

    @Nullable
    private Date timestamp;

    @Nullable
    private String signature;

    @Nullable
    private String userId;

}
