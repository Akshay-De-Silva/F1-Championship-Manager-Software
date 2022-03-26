import java.io.Serializable;
import java.util.ArrayList;

public class Formula1Driver extends Driver implements Comparable<Formula1Driver>, Serializable {

    private static final long serialVersionUID = 2L;
    private String driverTeam;
    private int numOfFirstPositions;
    private int numOfSecondPositions;
    private int numOfThirdPositions;
    private int numOfRaces;
    private int numOfPoints;
    protected static int sortNumFlag;
    protected ArrayList<String> raceDates = new ArrayList<>();
    protected ArrayList<Integer> racePositions = new ArrayList<>();
    private int initialRacePosition;

    //Default Constructor
    public Formula1Driver(){

    }

    //Getters and Setters
    public String getDriverTeam() {
        return driverTeam;
    }

    public void setDriverTeam(String driverTeam) {
        this.driverTeam = driverTeam;
    }

    public int getNumOfFirstPositions() {
        return numOfFirstPositions;
    }

    public void setNumOfFirstPositions(int numOfFirstPositions) {
        this.numOfFirstPositions = numOfFirstPositions;
    }

    public int getNumOfSecondPositions() {
        return numOfSecondPositions;
    }

    public void setNumOfSecondPositions(int numOfSecondPositions) {
        this.numOfSecondPositions = numOfSecondPositions;
    }

    public int getNumOfThirdPositions() {
        return numOfThirdPositions;
    }

    public void setNumOfThirdPositions(int numOfThirdPositions) {
        this.numOfThirdPositions = numOfThirdPositions;
    }

    public int getNumOfRaces() {
        return numOfRaces;
    }

    public void setNumOfRaces(int numOfRaces) {
        this.numOfRaces = numOfRaces;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }

    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = numOfPoints;
    }

    public void setRaceDate(String raceDate){
        this.raceDates.add(raceDate);
    }

    public void setRacePosition(int position) {
        this.racePositions.add(position);
    }

    public int getLastRacePosition(){
        return racePositions.get(racePositions.size()-1);
    }

    public int getInitialRacePosition() {
        return initialRacePosition;
    }

    public void setInitialRacePosition(int initialRacePosition) {
        this.initialRacePosition = initialRacePosition;
    }

    public void calculatePoints(int positionNum) {  //method to increment points to drivers based on position
        switch (positionNum) {
            case 1:
                this.numOfPoints += 25;
                break;
            case 2:
                this.numOfPoints += 18;
                break;
            case 3:
                this.numOfPoints += 15;
                break;
            case 4:
                this.numOfPoints += 12;
                break;
            case 5:
                this.numOfPoints += 10;
                break;
            case 6:
                this.numOfPoints += 8;
                break;
            case 7:
                this.numOfPoints += 6;
                break;
            case 8:
                this.numOfPoints += 4;
                break;
            case 9:
                this.numOfPoints += 2;
                break;
            case 10:
                this.numOfPoints += 1;
                break;
            default:
                break;
        }
    }

    @Override
    public int compareTo(Formula1Driver f1Driver) {         //method to sort the arraylist of driver in 3 ways (sorted by points, first positions, and last race positions)
        if (sortNumFlag == 1) {
            if (this.getNumOfPoints() > f1Driver.getNumOfPoints()) {
                return 1;
            } else if (this.getNumOfPoints() < f1Driver.getNumOfPoints()) {
                return -1;
            } else if(this.getNumOfFirstPositions() > f1Driver.getNumOfFirstPositions()) {
                return 1;
            } else {
                return -1;
            }
        } else if (sortNumFlag == 2) {
            if (this.getNumOfFirstPositions() > f1Driver.getNumOfFirstPositions()) {
                return 1;
            } else if (this.getNumOfFirstPositions() < f1Driver.getNumOfFirstPositions()) {
                return -1;
            } else if(this.getNumOfSecondPositions() > f1Driver.getNumOfSecondPositions()) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (this.getLastRacePosition() > f1Driver.getLastRacePosition()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    @Override
    public String toString() {
        return  "\nDrivers Name:               " + super.getDriverFName() + " " + super.getDriverLName() +
                "\nDrivers Location:           " + super.getDriverLocation() +
                "\nDrivers Age:                " + super.getDriverAge() +
                "\nDrivers Height:             " + super.getDriverHeight() +
                "\nDrivers Constructor Team:   " + driverTeam +
                "\nNumber of First Positions:  " + numOfFirstPositions +
                "\nNumber of Second Positions: " + numOfSecondPositions +
                "\nNumber of Third Positions:  " + numOfThirdPositions +
                "\nNumber of Races:            " + numOfRaces +
                "\nNumber of Points:           " + numOfPoints +
                "\n";
    }

}