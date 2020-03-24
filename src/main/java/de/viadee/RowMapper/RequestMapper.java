package de.viadee.RowMapper;

import de.viadee.domain.Request;
import org.springframework.jdbc.core.RowMapper;

import java.awt.geom.Point2D;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestMapper implements RowMapper<Request> {

    @Override
    public Request mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Request(resultSet.getInt("id"),resultSet.getDouble("basket_value"), new Point2D.Double(resultSet.getDouble("cus_lat"),resultSet.getDouble("cus_long")));
    }
}
