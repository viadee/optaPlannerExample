package de.viadee.service;

import de.viadee.RowMapper.RequestMapper;
import de.viadee.RowMapper.TimeWindowMapper;
import de.viadee.domain.Request;
import de.viadee.domain.Route;
import de.viadee.domain.TimeWindow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DataLoaderService {

    private final JdbcTemplate jdbcTemplate;

    public DataLoaderService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Request> getRequestsWithAvailableTimeWindows() {
        return getRequestWithAvailableTimeWindows(Optional.empty());
    }

    public List<Request> getRequestsWithAvailableTimeWindowsBySetId(Integer setId) {
        return getRequestWithAvailableTimeWindows(Optional.ofNullable(setId));
    }

    private List<Request> getRequestWithAvailableTimeWindows(Optional<Integer> setId) {
        List<Request> requests = new ArrayList<>();

        if (setId.isPresent()) {
            String sql = "SELECT * FROM REQUEST where set_id = ?";
            requests = jdbcTemplate.query(
                    sql, new RequestMapper(), setId.get());
        } else {
            String sql = "SELECT * FROM REQUEST";
            requests = jdbcTemplate.query(
                    sql, new RequestMapper());
        }

        requests.stream().forEach(request -> {
            String sqlForTimewindows = "Select tw.tw_start_time, tw.tw_end_time from "
                    + "request req inner join "
                    + "request_timewindow  req_tw on req.id = req_tw.request_id inner join "
                    + "timewindow tw on tw.id = req_tw.tw_id "
                    + "where req.id = ?";
            List<TimeWindow> timeWindows = jdbcTemplate.query(
                    sqlForTimewindows, new TimeWindowMapper(), request.getId());
            request.setAvailableTimeWindows(timeWindows);
        });

        return requests;
    }

    public List<Route> getRoutes() {
        // Todo: Testdaten für Routen hinterlegen
        return Arrays.asList(new Route(), new Route());
    }
}
