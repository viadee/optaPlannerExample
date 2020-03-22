package de.viadee.service.entity;

import com.sun.istack.NotNull;
import de.viadee.planningproblem.TimeWindow;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "REQUEST")
public class Request {

    @Id
    @NotNull
    @Column(columnDefinition = "DECIMAL(10) NOT NULL")
    private Integer id;

    @Valid
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "REQUEST_TIMEWINDOW",
            joinColumns = @JoinColumn(name = "request_id",referencedColumnName = "id")
            ,inverseJoinColumns=@JoinColumn(name="tw_id", referencedColumnName="ID"))
    private List<TimeWindowEntity> timeWindows;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public List<TimeWindowEntity> getTimeWindows() {
        return timeWindows;
    }

    public void setTimeWindows(List<TimeWindowEntity> timeWindows) {
        this.timeWindows = timeWindows;
    }
}
