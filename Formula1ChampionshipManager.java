import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager, Serializable {

    private static final long serialVersionUID = 3L;
    Scanner scan = new Scanner(System.in);

    protected  ArrayList<Formula1Driver> f1Drivers = new ArrayList<>();
    private  ArrayList<Integer> positionNums = new ArrayList<>();
    private ArrayList<String> constructorTeams = new ArrayList<>();

    public int validNum(String text) {  //validates if user entered an integer
        boolean intValid = false;
        int num = 0;

        do {
            try {
                System.out.println(text);
                num = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Input was not a Number, Please Enter Again");
                scan.next();
                continue;
            }
            intValid = true;
        } while (!intValid);

        return num;
    }

    public int validPosition(String text, int numOfRacers) {  //validates if user entered an integer and to make sure the position has not been repeated
        boolean positionValid = false;
        int position = 0;

        do {
            try {
                System.out.println(text);
                position = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Input was not a Number, Please Enter Again");
                scan.next();
                continue;
            }

            if (position < 1 || position > numOfRacers) {
                System.out.println("Invalid Position, Please Enter Again");
            } else if (positionNums.contains(position)) {
                System.out.println("Position has already been taken by another driver, Please Enter Again");
            } else {
                positionNums.add(position);
                positionValid = true;
            }

        } while (!positionValid);

        return position;
    }

    public int validRandomPosition(Boolean firstPositionFlag) {      //generates a random positions and validates if it has been already taken
        boolean positionValid = false;
        int position;
        Random rand = new Random();

        do {
            if (!firstPositionFlag) {
                position = rand.nextInt(f1Drivers.size() - 1 + 1) + 1;

            } else {
                position = rand.nextInt(f1Drivers.size() - 2 + 1) + 2;
            }
            if (!positionNums.contains(position)) {
                positionNums.add(position);
                positionValid = true;
            }

        } while (!positionValid);

        return position;
    }

    public String validDate(String text) {                          //validates the date entered by the user
        boolean dateValid = false;
        String date = "";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        do {
            try {
                System.out.println(text);
                date = scan.nextLine();
                df.parse(date);
            } catch (ParseException e) {
                System.out.println("Input was not a valid date, use the format dd/mm/yyyy, Please Enter Again");
                continue;
            }

            int year = Integer.parseInt(date.substring(date.length()-4));
            if(year > 2019 && year < 2023) {
                dateValid = true;
            } else {
                System.out.println("Year is out of range, Please enter a race that took place between 2020 and 2022");
            }

        } while (!dateValid);

        return date;
    }

    public String validTeam(String text) {      //validates that the constructor teams are not repeated
        boolean teamValid = false;
        String teamName;

        do {
            System.out.println(text);
            teamName = scan.nextLine();

            if (constructorTeams.contains(teamName)) {
                System.out.println(teamName + " is already in the championship, Please Enter Again");
            } else {
                constructorTeams.add(teamName);
                teamValid = true;
            }
        } while (!teamValid);

        return teamName;
    }

    @Override
    public void addDriver() {                       //adds a new driver to the f1 championship table
        Formula1Driver f1 = new Formula1Driver();
        f1Drivers.add(f1);

        String text;

        System.out.println("Enter Drivers First Name: ");
        f1Drivers.get(f1Drivers.size()-1).setDriverFName(scan.nextLine());

        System.out.println("Enter Drivers Last Name: ");
        f1Drivers.get(f1Drivers.size()-1).setDriverLName(scan.nextLine());

        System.out.println("Enter " + f1Drivers.get(f1Drivers.size()-1).getDriverFName() + " " + f1Drivers.get(f1Drivers.size()-1).getDriverLName() + "'s location: ");
        f1Drivers.get(f1Drivers.size()-1).setDriverLocation(scan.nextLine());

        text = "Enter " + f1Drivers.get(f1Drivers.size()-1).getDriverFName() + " " + f1Drivers.get(f1Drivers.size()-1).getDriverLName() + "'s Age: ";
        f1Drivers.get(f1Drivers.size()-1).setDriverAge(validNum(text));
        scan.nextLine();

        text = "Enter " + f1Drivers.get(f1Drivers.size()-1).getDriverFName() + " " + f1Drivers.get(f1Drivers.size()-1).getDriverLName() + "'s Height in CM: ";
        f1Drivers.get(f1Drivers.size()-1).setDriverHeight(validNum(text));
        scan.nextLine();

        text = "Enter " + f1Drivers.get(f1Drivers.size()-1).getDriverFName() + " " + f1Drivers.get(f1Drivers.size()-1).getDriverLName() + "'s Constructor Team: ";
        f1Drivers.get(f1Drivers.size()-1).setDriverTeam(validTeam(text));

        text = "Enter " + f1Drivers.get(f1Drivers.size()-1).getDriverFName() + " " + f1Drivers.get(f1Drivers.size()-1).getDriverLName() + "'s number of times getting First Position: ";
        f1Drivers.get(f1Drivers.size()-1).setNumOfFirstPositions(validNum(text));
        scan.nextLine();

        text = "Enter " + f1Drivers.get(f1Drivers.size()-1).getDriverFName() + " " + f1Drivers.get(f1Drivers.size()-1).getDriverLName() + "'s number of times getting Second Position: ";
        f1Drivers.get(f1Drivers.size()-1).setNumOfSecondPositions(validNum(text));
        scan.nextLine();

        text = "Enter " + f1Drivers.get(f1Drivers.size()-1).getDriverFName() + " " + f1Drivers.get(f1Drivers.size()-1).getDriverLName() + "'s number of times getting Third Position: ";
        f1Drivers.get(f1Drivers.size()-1).setNumOfThirdPositions(validNum(text));
        scan.nextLine();

        text = "Enter " + f1Drivers.get(f1Drivers.size()-1).getDriverFName() + " " + f1Drivers.get(f1Drivers.size()-1).getDriverLName() + "'s number of Races: ";
        f1Drivers.get(f1Drivers.size()-1).setNumOfRaces(validNum(text));
        scan.nextLine();

        text = "Enter " + f1Drivers.get(f1Drivers.size()-1).getDriverFName() + " " + f1Drivers.get(f1Drivers.size()-1).getDriverLName() + "'s number of Points: ";
        f1Drivers.get(f1Drivers.size()-1).setNumOfPoints(validNum(text));
        scan.nextLine();

        System.out.println("\nDriver " + f1Drivers.get(f1Drivers.size()-1).getDriverFName() + " " + f1Drivers.get(f1Drivers.size()-1).getDriverLName() + " has been added");

    }

    @Override
    public void deleteDriver() {            //method to delete any driver from the f1 championship table
        boolean found = false;

        System.out.println("Enter Drivers Last Name that you wish to delete: ");
        String driverLastName = scan.nextLine();

        for (Formula1Driver f1CM : f1Drivers) {
            if(f1CM.getDriverLName().equals(driverLastName)) {
                System.out.println(f1CM.getDriverFName() + " " + f1CM.getDriverLName() + " has been removed");
                f1Drivers.remove(f1CM);
                constructorTeams.remove(f1CM.getDriverTeam());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No driver with that name has been found please try again");
        }

    }

    @Override
    public void changeDriverForTeam() {         //method to change the driver of any present constructor teams
        boolean found = false;
        String text;

        System.out.println("Enter the name of the constructor to replace their driver: ");
        String constructorName = scan.nextLine();

        for (Formula1Driver f1CM : f1Drivers) {
            if(f1CM.getDriverTeam().equals(constructorName)) {
                System.out.println("Enter the new Drivers First Name: ");
                f1CM.setDriverFName(scan.nextLine());

                System.out.println("Enter the new Drivers Last Name: ");
                f1CM.setDriverLName(scan.nextLine());

                System.out.println("Enter " + f1CM.getDriverFName() + " " + f1CM.getDriverLName() + "'s Location: ");
                f1CM.setDriverLocation(scan.nextLine());

                text = "Enter " + f1CM.getDriverFName() + " " + f1CM.getDriverLName() + "'s Age: ";
                f1CM.setDriverAge(validNum(text));

                text = "Enter " + f1CM.getDriverFName() + " " + f1CM.getDriverLName() + "'s Height: ";
                f1CM.setDriverHeight(validNum(text));

                text = "Enter " + f1CM.getDriverFName() + " " + f1CM.getDriverLName() + "'s Number of First Positions: ";
                f1CM.setNumOfFirstPositions(validNum(text));

                text = "Enter " + f1CM.getDriverFName() + " " + f1CM.getDriverLName() + "'s Number of Second Positions: ";
                f1CM.setNumOfSecondPositions(validNum(text));

                text = "Enter " + f1CM.getDriverFName() + " " + f1CM.getDriverLName() + "'s Number of Third Positions: ";
                f1CM.setNumOfThirdPositions(validNum(text));

                text = "Enter " + f1CM.getDriverFName() + " " + f1CM.getDriverLName() + "'s Number of Races: ";
                f1CM.setNumOfRaces(validNum(text));

                text = "Enter " + f1CM.getDriverFName() + " " + f1CM.getDriverLName() + "'s Number of Points: ";
                f1CM.setNumOfPoints(validNum(text));

                System.out.println(f1CM.getDriverFName() + " " + f1CM.getDriverLName() + " is now the driver for " + f1CM.getDriverTeam());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No constructor with that name has been found please try again");
        }
    }

    @Override
    public void displayDriverStats() {              //displays the provided and updated statistics about a given driver
        boolean found = false;

        System.out.println("Enter the last name of the Driver you wish to view: ");
        String driverName = scan.nextLine();

        for (Formula1Driver f1CM : f1Drivers) {
            if(f1CM.getDriverLName().equals(driverName)) {
                System.out.println(f1CM);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No driver with that name has been found please try again");
        }

    }

    @Override
    public void displayF1DriverTable() {                //displays using and ASCII table the data of all drivers in the f1 championship
        System.out.println("Formula 1 Driver Table:");
        Formula1Driver.sortNumFlag = 1;
        ArrayList<Formula1Driver> clone = new ArrayList<>(f1Drivers);
        clone.sort(Collections.reverseOrder());

        String leftAlignFormat = "| %-10s | %-10s | %-12s | %-3d | %-6d | %-11s | %-11d | %-12d | %-11d | %-11d | %-12d |%n";       //REFERENCE -> https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console

        System.out.format("+------------+------------+--------------+-----+--------+-------------+-------------+--------------+-------------+-------------+--------------+%n");
        System.out.format("| First Name | Last Name  | Location     | Age | Height | Constructor | First Place | Second Place | Third Place | Total Races | Total Points |%n");
        System.out.format("+------------+------------+--------------+-----+--------+-------------+-------------+--------------+-------------+-------------+--------------+%n");
        for (Formula1Driver f1D : clone) {
            System.out.format(leftAlignFormat, f1D.getDriverFName(), f1D.getDriverLName(), f1D.getDriverLocation(), f1D.getDriverAge(), f1D.getDriverHeight(), f1D.getDriverTeam(), f1D.getNumOfFirstPositions(), f1D.getNumOfSecondPositions(), f1D.getNumOfThirdPositions(), f1D.getNumOfRaces(), f1D.getNumOfPoints());
        }
        System.out.format("+------------+------------+--------------+-----+--------+-------------+-------------+--------------+-------------+-------------+--------------+%n");
    }

    @Override
    public void addRace() {             //adds a race for a provided date that also updates the positions and points of the drivers
        int position;
        String text;

        text = "Enter the date of the completed race: ";
        String date = validDate(text);

        for(Formula1Driver f1D : f1Drivers) {
            f1D.setNumOfRaces(f1D.getNumOfRaces() + 1); //increments races for each driver by 1

            text = "Enter the position that " + f1D.getDriverFName() + " " + f1D.getDriverLName() + " got in this race: ";
            position = validPosition(text, f1Drivers.size());
            scan.nextLine();

            f1D.setRaceDate(date);
            racePositionChecker(position, f1D);
        }

        positionNums.clear();
        System.out.println("\nThe race that took place on " + date + " has been recorded");
    }

    private void racePositionChecker(int position, Formula1Driver f1D) {        //method to check if driver is in first, second or third place and increments the value
        f1D.setRacePosition(position); //notes down the position of the given driver in the race

        f1D.calculatePoints(position); //adds points to driver depending on position

        if (position == 1) {
            f1D.setNumOfFirstPositions(f1D.getNumOfFirstPositions() + 1);
        } else if (position == 2) {
            f1D.setNumOfSecondPositions(f1D.getNumOfSecondPositions() + 1);
        } else if (position == 3) {
            f1D.setNumOfThirdPositions(f1D.getNumOfThirdPositions() + 1);
        }
    }

    @Override
    public void saveToFile() {                  //stores the data of drivers and races in a serialized file
        String fileName = "f1DriverFile.ser";

        try {
            FileOutputStream saveFile = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(saveFile);
            outputStream.writeObject(this.f1Drivers);

            saveFile.close();
            outputStream.close();
            System.out.println("The Formula 1 Championship data has been saved to a file");
        } catch (IOException e) {
            System.out.println("IO Exception Triggered");
        }
    }

    @Override
    public void readFromFile() {            //reads the stored data of the driver and race file,else prints that no file has been found
        try{
            FileInputStream fileInputStream = new FileInputStream("f1DriverFile.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            f1Drivers = (ArrayList<Formula1Driver>) objectInputStream.readObject();

            fileInputStream.close();
            objectInputStream.close();
            System.out.println("The Formula 1 Championship data has been loaded:\n");
            displayF1DriverTable();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("No Formula 1 Championship data File Detected");
        }
    }


    //GUI METHODS BELOW


    public ArrayList<Formula1Driver> displayF1DriverTableGUI() {        //returns a clone of the sorted arraylist of drivers in descending order
        Formula1Driver.sortNumFlag = 1;
        ArrayList<Formula1Driver> clone = new ArrayList<>(f1Drivers);

        clone.sort(Collections.reverseOrder());

        return clone;
    }

    public ArrayList<Formula1Driver> ascendingOrderSort() {             //returns a clone of the sorted arraylist of drivers in ascending order
        Formula1Driver.sortNumFlag = 1;
        ArrayList<Formula1Driver> clone = new ArrayList<>(f1Drivers);
        Collections.sort(clone);

        return clone;
    }

    public ArrayList<Formula1Driver> sortByFirstPositions() {           //returns a clone of the sorted arraylist of drivers sorted by number of first positions
        Formula1Driver.sortNumFlag = 2;
        ArrayList<Formula1Driver> clone = new ArrayList<>(f1Drivers);
        clone.sort(Collections.reverseOrder());

        return clone;
    }

    private String formatChecker(int num) {             //method to check if input day or month has 2 digits to properly format the date
        String sNum = String.valueOf(num);
        if (num < 10) {
            sNum = String.format("%02d", num);
        }
        return sNum;
    }

    public String randomRaceDate() {                    //generates a random date between the range of 01/01/2020 to 31/12/2022
        Random rand = new Random();

        String day = formatChecker(rand.nextInt(31 - 1 + 1) + 1);
        String month = formatChecker(rand.nextInt(12 - 1 + 1) + 1);
        int year = rand.nextInt(2022 - 2020 + 1) + 2020;

        String date = day + "/" + month + "/" + year;

        for (Formula1Driver f1D : f1Drivers) {
            f1D.setNumOfRaces(f1D.getNumOfRaces() + 1); //increments races for each driver by 1

            f1D.setRaceDate(date);
        }

        return date;
    }

    public ArrayList<Formula1Driver> randomRace() {     //returns a clone of the sorted arraylist of drivers sorted by driver position
        positionNums.clear();
        int position;

        for (Formula1Driver f1D : f1Drivers) {
            position = validRandomPosition(false);
            racePositionChecker(position, f1D);

        }

        Formula1Driver.sortNumFlag = 3;
        ArrayList<Formula1Driver> clone = new ArrayList<>(f1Drivers);
        Collections.sort(clone);

        return clone;
    }

    private void setFirstPosition(Formula1Driver f1D) {             //updates drivers number of first positions
        f1D.setRacePosition(1);
        f1D.setNumOfFirstPositions(f1D.getNumOfFirstPositions() + 1);
        f1D.calculatePoints(1);
    }

    public ArrayList<Formula1Driver> randomProbabilityRace() {      //uses probability of starting position to determine which driver achieves first place and method generates random positions for the remaining drivers
        positionNums.clear();

        Random rand = new Random();
        int position;

        for (Formula1Driver f1D : f1Drivers) {      //sets the random initial positions for all drivers
            position = validRandomPosition(false);
            f1D.setInitialRacePosition(position);
        }
        positionNums.clear();

        int probability = rand.nextInt(100 - 1 + 1) + 1;

        for (Formula1Driver f1D : f1Drivers) {

            if (f1D.getInitialRacePosition() == 1 && probability <=40) {
                setFirstPosition(f1D);
            } else if (f1D.getInitialRacePosition() == 2 && probability <=70 && probability > 40) {
                setFirstPosition(f1D);
            } else if (f1D.getInitialRacePosition() == 3 && probability <=80 && probability > 70) {
                setFirstPosition(f1D);
            } else if (f1D.getInitialRacePosition() == 4 && probability <=90 && probability > 80) {
                setFirstPosition(f1D);
            } else if (f1D.getInitialRacePosition() == 5 && probability <=92 && probability > 90) {
                setFirstPosition(f1D);
            } else if (f1D.getInitialRacePosition() == 6 && probability <=94 && probability > 92) {
                setFirstPosition(f1D);
            } else if (f1D.getInitialRacePosition() == 7 && probability <=96 && probability > 94) {
                setFirstPosition(f1D);
            } else if (f1D.getInitialRacePosition() == 8 && probability <=98 && probability > 96) {
                setFirstPosition(f1D);
            } else if (f1D.getInitialRacePosition() == 9 && probability <=100 && probability > 98) {
                setFirstPosition(f1D);
            } else {
                position = validRandomPosition(true);
                racePositionChecker(position, f1D);
            }
        }

        Formula1Driver.sortNumFlag = 3;
        ArrayList<Formula1Driver> clone = new ArrayList<>(f1Drivers);
        Collections.sort(clone);

        return clone;

    }

    public ArrayList<LocalDate> allSortedRaces() throws ParseException {        //returns all sorted races in the f1 championship
        ArrayList<String> clone = new ArrayList<>();
        ArrayList<LocalDate> dateList = new ArrayList<>();

        for (Formula1Driver f1D : f1Drivers) {
            if (clone.size() < f1D.raceDates.size()) {
                clone = f1D.raceDates;
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");    //Reference: https://stackoverflow.com/questions/4216745/java-string-to-date-conversion

        for (String date : clone) {
            LocalDate dateFormat = LocalDate.parse(date, formatter);

            if (!dateList.contains(dateFormat)) {
                dateList.add(dateFormat);
            }
        }

        Collections.sort(dateList);

        return dateList;
    }

    public ArrayList<String> returnDriverDates(String dlName) {     //method to return the driver dates (will never be empty as it has been validated in the driverRaceSearchBtn action listener)
        ArrayList<String> dates = new ArrayList<>();

        for (Formula1Driver f1D : f1Drivers) {
            if (f1D.getDriverLName().equals(dlName)) {
                dates = f1D.raceDates;
            }
        }

        return dates;
    }

    public ArrayList<Integer> returnDriverPositions(String dlName) {    //method to return the driver positions from each race (will never be empty as it has been validated in the driverRaceSearchBtn action listener)
        ArrayList<Integer> positions = new ArrayList<>();

        for (Formula1Driver f1D : f1Drivers) {
            if (f1D.getDriverLName().equals(dlName)) {
                positions = f1D.racePositions;
            }
        }

        return positions;
    }

    public String searchDriverRace(String dLName) {     //returns the string with the drivers name, else it returns a 0 to show that the user has not entered a drivers name
        String result = "";
        for (Formula1Driver f1D : f1Drivers) {
            if (dLName.equals("Enter Driver Last Name")) {
                result = "0";
            } else if(f1D.getDriverLName().equals(dLName)) {
                result = f1D.getDriverFName() + " " + f1D.getDriverLName() + " Details:";
                break;
            } else {
                result = "0";
            }
        }

        return result;
    }

}