package de.viadee.service.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public class Position {

    @Column(name = "cus_long", precision =6, scale = 4, nullable = true)
    private BigDecimal longitude;

    @Column(name = "cus_lat",  precision =6, scale = 4, nullable = true)
    private BigDecimal lattitude;

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public BigDecimal getLattitude() {
        return lattitude;
    }

    public void setLattitude(BigDecimal lattitude) {
        this.lattitude = lattitude;
    }
}
