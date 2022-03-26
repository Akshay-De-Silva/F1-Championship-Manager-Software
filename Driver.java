import java.io.Serializable;

public abstract class Driver implements Serializable {

    private static final long serialVersionUID = 1L;
    private String driverFName;
    private String driverLName;
    private String driverLocation;
    private int driverAge;
    private int driverHeight; //in centimeters

    //Default Constructor
    public Driver(){

    }

    public String getDriverFName() {
        return driverFName;
    }

    public void setDriverFName(String driverFName) {
        this.driverFName = driverFName;
    }

    public String getDriverLName() {
        return driverLName;
    }

    public void setDriverLName(String driverLName) {
        this.driverLName = driverLName;
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public int getDriverHeight() {
        return driverHeight;
    }

    public void setDriverHeight(int driverHeight) {
        this.driverHeight = driverHeight;
    }

}
