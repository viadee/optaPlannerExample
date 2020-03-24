package de.viadee.RowMapper;

import de.viadee.domain.TimeWindow;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeWindowMapper implements RowMapper<TimeWindow> {

    @Override
    public TimeWindow mapRow(ResultSet resultSet, int i) throws SQLException {
        return new TimeWindow(resultSet.getTime("tw_start_time").toLocalTime(),resultSet.getTime("tw_end_time").toLocalTime());
    }
}
