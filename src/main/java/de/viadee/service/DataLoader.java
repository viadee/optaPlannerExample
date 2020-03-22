package de.viadee.service;

import de.viadee.planningproblem.Bestellung;
import de.viadee.planningproblem.Request;
import de.viadee.planningproblem.TimeWindow;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader {

    public void loadAllTestData() {
        try {
            List<TimeWindow> timeWindowList = readTimeWindowData();
            List<Bestellung> bestellungList = readBestellungData();
            List<Request> requestTimeWindowList = readRequestTimeWindow();

            System.out.println("fertig");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<TimeWindow> readTimeWindowData() throws IOException {
        List<TimeWindow> timeWindowList = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/data/2_time_windows.sql"));
        String row;
        csvReader.readLine();
       /* while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            timeWindowList
                    .add(new TimeWindow(Integer.parseInt(data[0]), LocalTime.of(Integer.parseInt(data[2]), 0), LocalTime.of(Integer.parseInt(data[3]), 0)));
        }*/
        csvReader.close();
        return timeWindowList;
    }

    private List<Bestellung> readBestellungData() throws IOException {
        List<Bestellung> bestellung = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/data/1_request.sql"));
        String row;
        csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
        /*    bestellung.add(new Bestellung(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Double.parseDouble(data[2]),
                    Double.parseDouble(data[3]), Double.parseDouble(data[4])));*/
        }
        csvReader.close();
        return bestellung;
    }

    private List<Request> readRequestTimeWindow() throws IOException {
        List<Request> requestTimeWindow = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/data/3_requests_v_time_windows.sql"));
        String row;
        csvReader.readLine();
       /* while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            requestTimeWindow.add(new Request(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
        }*/
        csvReader.close();
        return requestTimeWindow;
    }
}
