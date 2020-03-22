package de.viadee.service.entity;

import com.sun.istack.NotNull;
import de.viadee.service.converter.UhrzeitConverterStundenMinuten;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "TIMEWINDOW")
public class TimeWindowEntity {

    @Id
    @NotNull
    @Column(columnDefinition = "DECIMAL(10) NOT NULL")
    private Integer id;

    @NotNull
    @Column(name = "tw_set",columnDefinition = "DECIMAL(10) NOT NULL")
    private Integer timeWindowSet;

    @Column(name = "tw_start_time")
    @Convert(converter = UhrzeitConverterStundenMinuten.class)
    private LocalTime startTime;

    @Column(name = "tw_end_time")
    @Convert(converter = UhrzeitConverterStundenMinuten.class)
    private LocalTime endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeWindowSet() {
        return timeWindowSet;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setTimeWindowSet(Integer timeWindowSet) {
        this.timeWindowSet = timeWindowSet;
    }
}
