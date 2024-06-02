//package Models;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PassengerList {
//    private List<Passenger> passengers;
//
//    public PassengerList() {
//        passengers = new ArrayList<>();
//        loadPassengers();
//    }
//
//    private void loadPassengers() {
//        try (BufferedReader reader = new BufferedReader(new FileReader("src\\Data_Storage\\Passengers.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] data = line.split(",");
//                if (data.length != 12) {
//                    System.err.println("Invalid data length: " + line);
//                    continue;
//                }
//                Passenger passenger = new Passenger(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11]);
//                passengers.add(passenger);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Passenger> getPassengers() {
//        return passengers;
//    }
//}
