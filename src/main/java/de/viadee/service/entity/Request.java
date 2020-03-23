package de.viadee.service.entity;

import com.sun.istack.NotNull;
import de.viadee.planningproblem.TimeWindow;

import javax.persistence.*;
import javax.validation.Valid;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.List;

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

    private BigDecimal basketValue;

    @NotNull
    @Embedded
    private Position position;

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
