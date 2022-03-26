import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String userChoice;
        boolean endLoop = false;

        Formula1ChampionshipManager f1C = new Formula1ChampionshipManager();
        SwingGUI newGUI = new SwingGUI(f1C);

        f1C.readFromFile();

        while (!endLoop) {
            System.out.println("\nPlease select one of the following options:\n\n" +
                    "A to add a new driver\n" +
                    "D to delete a driver\n" +
                    "C to change driver for an existing team\n" +
                    "V to view the various statistics about the driver\n" +
                    "T to Display the Formula 1 Driver Table\n" +
                    "R to add a completed race\n" +
                    "S to store program data in file\n" +
                    "L to load program data from file\n" +
                    "G to open the GUI\n" +
                    "Q to quit");

            userChoice = scan.nextLine();

            switch (userChoice) {    //loops till user types Q to quit
                case "A":
                    f1C.addDriver();
                    break;
                case "D":
                    f1C.deleteDriver();
                    break;
                case "C":
                    f1C.changeDriverForTeam();
                    break;
                case "V":
                    f1C.displayDriverStats();
                    break;
                case "T":
                    f1C.displayF1DriverTable();
                    break;
                case "R":
                    f1C.addRace();
                    break;
                case "S":
                    f1C.saveToFile();
                    break;
                case "L":
                    f1C.readFromFile();
                    break;
                case "G":
                    newGUI.gui();
                    break;
                case "Q":
                    System.out.println("Thank you and Goodbye!");
                    endLoop = true;
                    break;
                default:
                    System.out.println("Invalid Option Selected");
                    break;
            }

        }

    }

}