package ru.kravchenko.se.model.entity;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kravchenko.se.model.dto.TaskDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Roman Kravchenko
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "app_Task")
public class Task extends AbstractEntity {

    @ManyToOne
    private Project project;

    @ManyToOne
    private User user;

    @Nullable
    @Column(name = "name")
    private String name;

    @Nullable
    @Column(name = "description")
    private String description;

    @Nullable
    @Column(name = "dateBegin")
    private Date dateBegin;

    @Nullable
    @Column(name = "dateEnd")
    private Date dateEnd;

    @NotNull
    @Column(name = "status")
    private StatusProjectTask status = StatusProjectTask.PLANNED;

    @Override
    public String toString() {
        return "TASK NAME: \"" + this.name + "\" DESCRIPTION TASK: \"" + this.description + "\" " + "TASK ID: " +
                super.getId() + " STATUS: " + this.status;
    }

    public TaskDTO getDTO() {
        @NotNull final TaskDTO dto = new TaskDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        dto.setDateBegin(dateBegin);
        dto.setDateEnd(dateEnd);
        dto.setStatus(status);
        dto.setUserId(user.getId());
        dto.setProjectId(project.getId());
        return dto;
    }

}
