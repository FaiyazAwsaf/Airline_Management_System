package Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CancellationRequests {
    private List<Passenger> passengers;
    public CancellationRequests() {
        passengers = new ArrayList<>();
        loadRequests();
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    private void loadRequests() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Data_Storage\\cancellation_requests.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
//                if (data.length != 10) {
//                    System.err.println("Invalid data length: " + line);
//                    continue;
//                }
                Passenger passenger = new Passenger(data[0], data[1], data[2], data[3], data[4], data[7], data[5], data[6], data[8], data[9]);
                passengers.add(passenger);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCancellingPassengers(Passenger passenger){
        passengers.add(passenger);
    }
}
