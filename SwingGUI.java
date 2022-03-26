import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SwingGUI {
    private Formula1ChampionshipManager f1C;

    public SwingGUI(Formula1ChampionshipManager f1C) {
        this.f1C = f1C;
    }


    public void gui() {
        JFrame f = new JFrame("Formula 1 Championship GUI");

        JButton updateTableBtn = new JButton("Refresh Table");
        updateTableBtn.setBounds(600, 450, 120,30);

        JButton sortAscendingPointsBtn = new JButton("Sort by Points (Ascending order)");
        sortAscendingPointsBtn.setBounds(50, 270, 250, 50);

        JButton sortDescendingFirstPosBtn = new JButton("Sort by First Positions");
        sortDescendingFirstPosBtn.setBounds(310, 270, 200, 50);

        JButton randomRaceBtn = new JButton("Generate Random Race");
        randomRaceBtn.setBounds(520, 270, 170, 50);

        JButton probabilityRandomRaceBtn = new JButton("Generate Random Probability Race");
        probabilityRandomRaceBtn.setBounds(160, 330, 250, 50);

        JButton completedRacesBtn = new JButton("Display Completed Races");
        completedRacesBtn.setBounds(420, 330, 200, 50);

        JButton driverRaceSearchBtn = new JButton("Search Driver Races");
        driverRaceSearchBtn.setBounds(310, 430, 170, 50);

        JTextField driverSearchTxt = new JTextField();
        driverSearchTxt.setText("Enter Driver Last Name");
        driverSearchTxt.setBounds(310, 390, 170,40);

        String[] columns = {"First Name", "Last Name", "Location", "Age", "Height", "Constructor", "First Places", "Second Places", "Third Places", "Races", "Points"};

        String[][] rowValues = new String[f1C.f1Drivers.size()][11];
        ArrayList<Formula1Driver> list = f1C.displayF1DriverTableGUI();

        setValues(rowValues, list);

        JTable table = new JTable(rowValues, columns);
        setTableSize(table);

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.setBounds(20,5,735,250);
        f.add(sp);

        f.add(updateTableBtn);
        f.add(sortAscendingPointsBtn);
        f.add(sortDescendingFirstPosBtn);
        f.add(randomRaceBtn);
        f.add(probabilityRandomRaceBtn);
        f.add(completedRacesBtn);
        f.add(driverRaceSearchBtn);
        f.add(driverSearchTxt);






        updateTableBtn.addActionListener(e -> {             //button that will refresh the tables values to show the up to date information
            String[][] updatedRowValues = new String[f1C.f1Drivers.size()][11];
            ArrayList<Formula1Driver> updatedList = f1C.displayF1DriverTableGUI();
            setValues(updatedRowValues, updatedList);
            JTable updatedTable = new JTable(updatedRowValues, columns);
            setTableSize(updatedTable);

            f.remove(table);
            f.remove(sp);
            JScrollPane newSp = new JScrollPane(updatedTable);
            newSp.setBorder(BorderFactory.createEmptyBorder());
            newSp.setBounds(20,5,735,250);
            f.add(newSp);
            newSp.getViewport().setBackground(new Color(135,206,235));

        });






        sortAscendingPointsBtn.addActionListener(e -> {             //replaces the initial table with one sorted in ascending order
            String[][] newRowValues = new String[f1C.f1Drivers.size()][11];
            ArrayList<Formula1Driver> newList = f1C.ascendingOrderSort();

            setValues(newRowValues, newList);

            JTable newTable = new JTable(newRowValues, columns);

            setTableSize(newTable);
            f.remove(table);
            f.remove(sp);
            JScrollPane newSp = new JScrollPane(newTable);
            newSp.setBorder(BorderFactory.createEmptyBorder());
            newSp.setBounds(20,5,735,250);
            f.add(newSp);
            newSp.getViewport().setBackground(new Color(135,206,235));
        });






        sortDescendingFirstPosBtn.addActionListener(e -> {          //replaces the initial table with one sorted in descending order
            String[][] newRowValues = new String[f1C.f1Drivers.size()][11];
            ArrayList<Formula1Driver> newList = f1C.sortByFirstPositions();

            setValues(newRowValues, newList);

            JTable newTable = new JTable(newRowValues, columns);

            setTableSize(newTable);
            f.remove(table);
            f.remove(sp);
            JScrollPane newSp = new JScrollPane(newTable);
            newSp.setBorder(BorderFactory.createEmptyBorder());
            newSp.setBounds(20,5,735,250);
            f.add(newSp);
            newSp.getViewport().setBackground(new Color(135,206,235));
        });






        randomRaceBtn.addActionListener(e -> {              //displays a new frame that contains the details of a randomly generated race
            JFrame raceFrame = new JFrame("Details of New Race");

            JLabel dateField = new JLabel("Race Date: " + f1C.randomRaceDate());
            dateField.setBounds(290, 20, 200,30);
            dateField.setFont(new Font("Serif", Font.PLAIN, 20));

            JLabel tableField = new JLabel("Table of Standings");
            tableField.setBounds(320, 220, 200,30);
            tableField.setFont(new Font("Serif", Font.PLAIN, 20));

            String[][] raceValues = new String[f1C.f1Drivers.size()][3];
            ArrayList<Formula1Driver> newList = f1C.randomRace();

            for (int i=0; i<f1C.f1Drivers.size(); i++) {
                raceValues[i][0] = newList.get(i).getDriverFName();
                raceValues[i][1] = newList.get(i).getDriverLName();
                raceValues[i][2] = String.valueOf(newList.get(i).getLastRacePosition());
            }

            String[] raceColumns = {"First Name", "Last Name", "Position"};

            String[][] updatedRowValues = new String[f1C.f1Drivers.size()][11];
            ArrayList<Formula1Driver> updatedList = f1C.displayF1DriverTableGUI();
            setValues(updatedRowValues, updatedList);

            JTable raceTable = new JTable(raceValues, raceColumns);

            JTable tableCopy = new JTable(updatedRowValues, columns);
            setTableSize(tableCopy);
            JScrollPane sp2 = new JScrollPane(tableCopy);
            JScrollPane raceSP = new JScrollPane(raceTable);
            sp2.setBorder(BorderFactory.createEmptyBorder());
            raceSP.setBorder(BorderFactory.createEmptyBorder());
            raceSP.setBounds(290,60,200,150);
            sp2.setBounds(20,250,735,150);

            sp2.getViewport().setBackground(new Color(135,206,235));
            raceFrame.add(sp2);
            raceSP.getViewport().setBackground(new Color(135,206,235));
            raceFrame.add(tableField);
            raceFrame.add(dateField);
            raceFrame.add(raceSP);
            raceFrame.getContentPane().setBackground(new Color(135,206,235));
            raceFrame.setSize(795,500);
            raceFrame.setLayout(null);
            raceFrame.setVisible(true);
        });






        probabilityRandomRaceBtn.addActionListener(e -> {               //displays a new frame that contains the details of a randomly generated race (with its first position being determined with probability)
            if(f1C.f1Drivers.size()<10) {
                JOptionPane.showMessageDialog(f,
                        "To generate this race a minimum of 10 Drivers are needed",
                        "Not Enough Drivers",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                JFrame raceFrame = new JFrame("Details of New Race (with probability)");

                JLabel dateField = new JLabel("Race Date: " + f1C.randomRaceDate());
                dateField.setBounds(290, 20, 200,30);
                dateField.setFont(new Font("Serif", Font.PLAIN, 20));

                JLabel tableField = new JLabel("Table of Standings");
                tableField.setBounds(320, 220, 200,30);
                tableField.setFont(new Font("Serif", Font.PLAIN, 20));

                String[][] raceValues = new String[f1C.f1Drivers.size()][3];
                ArrayList<Formula1Driver> newList = f1C.randomProbabilityRace();

                for (int i=0; i<f1C.f1Drivers.size(); i++) {
                    raceValues[i][0] = newList.get(i).getDriverFName();
                    raceValues[i][1] = newList.get(i).getDriverLName();
                    raceValues[i][2] = String.valueOf(newList.get(i).getLastRacePosition());
                }

                String[] raceColumns = {"First Name", "Last Name", "Position"};

                String[][] updatedRowValues = new String[f1C.f1Drivers.size()][11];
                ArrayList<Formula1Driver> updatedList = f1C.displayF1DriverTableGUI();
                setValues(updatedRowValues, updatedList);

                JTable raceTable = new JTable(raceValues, raceColumns);

                JTable tableCopy = new JTable(updatedRowValues, columns);
                setTableSize(tableCopy);
                JScrollPane sp2 = new JScrollPane(tableCopy);
                JScrollPane raceSP = new JScrollPane(raceTable);
                sp2.setBorder(BorderFactory.createEmptyBorder());
                raceSP.setBorder(BorderFactory.createEmptyBorder());
                raceSP.setBounds(290,60,200,150);
                sp2.setBounds(20,250,735,150);

                sp2.getViewport().setBackground(new Color(135,206,235));
                raceFrame.add(sp2);
                raceSP.getViewport().setBackground(new Color(135,206,235));
                raceFrame.add(tableField);
                raceFrame.add(dateField);
                raceFrame.add(raceSP);
                raceFrame.getContentPane().setBackground(new Color(135,206,235));
                raceFrame.setSize(795,500);
                raceFrame.setLayout(null);
                raceFrame.setVisible(true);
            }
        });






        completedRacesBtn.addActionListener(e -> {              //displays in a new frame all completed races in the f1 championship
            JFrame raceFrame = new JFrame("All Races in the Season");

            JLabel dateTitle = new JLabel("All Completed Race Dates");
            dateTitle.setBounds(100, 20, 350,45);
            dateTitle.setFont(new Font("Serif", Font.PLAIN, 30));

            String[] raceColumns = {"Race Number", "Race Date"};

            ArrayList<Integer> raceNums = new ArrayList<>();
            try {
                ArrayList<LocalDate> dateArrayList = f1C.allSortedRaces();
                for (int i=1; i<=dateArrayList.size(); i++) {
                    raceNums.add(i);
                }
            } catch (ParseException parseException) {
                System.out.println("Parse Exception");
            }

            String[][] raceData = new String[raceNums.size()][2];

            for (int i=0; i<raceNums.size(); i++) {
                raceData[i][0] = String.valueOf(raceNums.get(i));
                try {
                    raceData[i][1] = String.valueOf(f1C.allSortedRaces().get(i));
                } catch (ParseException parseException) {
                    System.out.println("Parse Exception");
                }
            }

            JTable raceTable = new JTable(raceData, raceColumns);

            raceTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            raceTable.getColumnModel().getColumn(1).setPreferredWidth(20);

            JScrollPane driverRaceSP = new JScrollPane(raceTable);
            driverRaceSP.setBorder(BorderFactory.createEmptyBorder());
            driverRaceSP.setBounds(110,80,250,250);
            driverRaceSP.getViewport().setBackground(new Color(135,206,235));
            raceFrame.add(driverRaceSP);

            raceFrame.add(dateTitle);
            raceFrame.getContentPane().setBackground(new Color(135,206,235));
            raceFrame.setSize(500,400);
            raceFrame.setLayout(null);
            raceFrame.setVisible(true);
        });






        driverRaceSearchBtn.addActionListener(e -> {                    //button that validates if the user has entered a valid drivers name and then displays on a new frame all races and positions the driver has gotten
            String result;
            result = f1C.searchDriverRace(driverSearchTxt.getText());

            if (result.equals("0")) {
                JOptionPane.showMessageDialog(f,
                        "Invalid Driver Name",
                        "Incorrect Name Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (f1C.returnDriverDates(driverSearchTxt.getText()).size() == 0) {
                JOptionPane.showMessageDialog(f,
                        "Driver has not competed in any Races",
                        "No Races Found",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                JFrame driverRaceFrame = new JFrame("All Driver Races");

                JLabel driverName = new JLabel(result);
                driverName.setBounds(140, 20, 200,30);
                driverName.setFont(new Font("Serif", Font.PLAIN, 22));

                String[] driverRaceColumns = {"Race Date", "Driver Position"};

                ArrayList<String> dates = f1C.returnDriverDates(driverSearchTxt.getText());
                ArrayList<Integer> positions = f1C.returnDriverPositions(driverSearchTxt.getText());
                String[][] driverRaceValues = new String[dates.size()][2];

                for (int i=0; i<dates.size(); i++) {
                    driverRaceValues[i][0] = dates.get(i);
                    driverRaceValues[i][1] = String.valueOf(positions.get(i));
                }

                JTable driverRaceTable = new JTable(driverRaceValues, driverRaceColumns);

                driverRaceTable.getColumnModel().getColumn(0).setPreferredWidth(20);
                driverRaceTable.getColumnModel().getColumn(1).setPreferredWidth(20);

                JScrollPane driverRaceSP = new JScrollPane(driverRaceTable);
                driverRaceSP.setBorder(BorderFactory.createEmptyBorder());
                driverRaceSP.setBounds(110,80,250,250);
                driverRaceSP.getViewport().setBackground(new Color(135,206,235));
                driverRaceFrame.add(driverRaceSP);

                driverRaceFrame.add(driverName);
                driverRaceFrame.getContentPane().setBackground(new Color(135,206,235));
                driverRaceFrame.setSize(500,400);
                driverRaceFrame.setLayout(null);
                driverRaceFrame.setVisible(true);
            }
        });



        sp.getViewport().setBackground(new Color(135,206,235));
        f.getContentPane().setBackground(new Color(135,206,235));
        f.setSize(795,550);
        f.setLayout(null);
        f.setVisible(true);
    }

    private void setTableSize(JTable table) {       //method to set the size of the table
        table.getColumnModel().getColumn(0).setPreferredWidth(75);
        table.getColumnModel().getColumn(1).setPreferredWidth(75);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(75);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(7).setPreferredWidth(95);
        table.getColumnModel().getColumn(8).setPreferredWidth(80);
        table.getColumnModel().getColumn(9).setPreferredWidth(50);
        table.getColumnModel().getColumn(10).setPreferredWidth(50);
    }

    private void setValues(String[][] array2D, ArrayList<Formula1Driver> list) {    //method to stores the values of each driver in a 2d array
        for (int i=0; i<f1C.f1Drivers.size(); i++) {
            array2D[i][0] = list.get(i).getDriverFName();
            array2D[i][1] = list.get(i).getDriverLName();
            array2D[i][2] = list.get(i).getDriverLocation();
            array2D[i][3] = String.valueOf(list.get(i).getDriverAge());
            array2D[i][4] = String.valueOf(list.get(i).getDriverHeight());
            array2D[i][5] = list.get(i).getDriverTeam();
            array2D[i][6] = String.valueOf(list.get(i).getNumOfFirstPositions());
            array2D[i][7] = String.valueOf(list.get(i).getNumOfSecondPositions());
            array2D[i][8] = String.valueOf(list.get(i).getNumOfThirdPositions());
            array2D[i][9] = String.valueOf(list.get(i).getNumOfRaces());
            array2D[i][10] = String.valueOf(list.get(i).getNumOfPoints());
        }
    }


}