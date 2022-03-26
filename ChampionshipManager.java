public interface ChampionshipManager {

    void addDriver();       //adds a new driver to the f1 championship table

    void deleteDriver();        //method to delete any driver from the f1 championship table

    void changeDriverForTeam();     //method to change the driver of any present constructor teams

    void displayDriverStats();      //displays the provided and updated statistics about a given driver

    void displayF1DriverTable();        //displays using and ASCII table the data of all drivers in the f1 championship

    void addRace();     //adds a race for a provided date that also updates the positions and points of the drivers

    void saveToFile();      //stores the data of drivers and races in a serialized file

    void readFromFile();        //reads the stored data of the driver and race file,else prints that no file has been found

}