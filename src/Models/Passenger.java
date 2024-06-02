package Models;

public class Passenger {
    private String name;
    private String contact;
    private String email;
    private String passportID;
    private String gender;
    private String nationality;
    private String flightCode;
    private String departureCity;
    private String destinationCity;
    private String departureDateAndTime;
    private String airplaneModel;
    private String seatNumber;

    public Passenger(String name, String contact, String email, String passportID, String gender, String nationality,
                     String flightCode, String departureCity, String destinationCity, String departureDateAndTime,
                     String airplaneModel, String seatNumber) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.passportID = passportID;
        this.gender = gender;
        this.nationality = nationality;
        this.flightCode = flightCode;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureDateAndTime = departureDateAndTime;
        this.airplaneModel = airplaneModel;
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return name + ", " + contact + ", " + email + ", " + passportID + ", " + gender + ", " + nationality + ", "
                + flightCode + ", " + departureCity + ", " + destinationCity + ", " + departureDateAndTime + ", "
                + airplaneModel + ", " + seatNumber;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureDateAndTime() {
        return departureDateAndTime;
    }

    public void setDepartureDateAndTime(String departureDateAndTime) {
        this.departureDateAndTime = departureDateAndTime;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
