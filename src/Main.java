import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int floors = 0;
        int spots = 0;
        boolean run = true;
        boolean start = false;
        //Start der Programmausführung 1. Schleife
        try {
            while (true) {
                try {
                    System.out.println("Willkommen im Simulator möchten sie ein Parkhaus erstellen ?\n(y/n)");
                    String answer = input.nextLine();
                    if (answer.equals("y")) {
                        break;
                    //sollte der Nutzer (n) eingeben wird das Programm durch negation des bools beendet
                    } else if (answer.equals("n")) {
                        run = false;
                        break;
                    } else if (!answer.equals("y") && !answer.equals("n")) {
                        throw new Fault();
                        //Standard Exception für fehlerhafte eingabe
                    }
                } catch (Fault e) {
                    System.out.println(e.getMessage());
                }
            }
            while (run) {
                try {
                    //2. Schleife (abfrage der Werte)
                    System.out.println("Geben sie die Etagen ein");
                    int value = input.nextInt();
                    if (value > 0) {
                        floors = value;
                        input.nextLine();
                    } else if (value <= 0) {
                        System.out.println("Geben sie eine Zahl größer 0 ein");
                        input.nextLine();
                    }
                    //Hier wird die versehentliche eingabe eines String datentypen abgefangen
                } catch (InputMismatchException e) {
                    System.out.println("Bitte eine Ganzzahl eingeben");
                    input.nextLine();
                    continue;
                }//Nachtrag: 2. Schleife verhindert eine doppelte Abfrage der Werte
                while (run) {
                    try {
                    System.out.println("Geben sie die Parkplätze pro Etage ein");
                    int value2 = input.nextInt();
                    if (value2 > 0 && floors > 0) {
                        spots = value2;
                        run = false;
                        start = true;
                        input.nextLine();
                    } else if(value2<=0 && floors > 0){
                        System.out.println("Geben sie eine Zahl größer 0 ein");
                        input.nextLine();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Bitte eine Ganzzahl eingeben");
                    input.nextLine();
                }
                }
            }
            //Ausgabe und Parkhaus erstellung über den Konstruktor
            System.out.println("Etagen: " + floors + " \nParkplätze pro Etage: " +spots+"\nGesamte Parkplätze: "
                    + floors * spots);
            Parkhaus parkhaus = new Parkhaus(floors, spots);
            /*Letzte Schleife mit dem Menü (Anmerk.: Auslagerung der Schleife möglich wurde zur Übersicht auf
            später verschoben
             */
            while (start) {
                try {
                    int key = 0;
                    System.out.println("Wählen sie eine Option\n" +
                            "1: Fahrzeug hinzufügen. (parken)\n" +
                            "2: Fahrzeug entfernen. (ausparken)\n" +
                            "3: Fahrzeug finden.\n" +
                            "4: Freie Parkplätze abfragen.\n" +
                            "5: Belegte Parkplätze abfragen.\n" +
                            "0: Programm beenden.\n");
                    try {
                        key = input.nextInt();
                        switch (key) {
                            case 0:
                                start = false;
                                break;
                                //Fahrzeug erstellung nach case 1 (entweder Motorrad oder Auto die ident ist das Kennzeichen
                            case 1:
                                System.out.println("Welches Fahrzeug möchten sie hinzufügen ?\n" +
                                        "Bitte um eingabe:\nAuto\n" + "Motorrad");
                                input.nextLine();
                                String vehicle = input.nextLine();
                                if (vehicle.equals("Auto")||vehicle.equals("auto")) {
                                    System.out.println("Wie lautet das Nummernschild von dem Auto ? ");
                                    String numPlate = input.nextLine();
                                    Car car = new Car(numPlate);
                                    parkhaus.driveIn(car);
                                    System.out.println("Das " + car.getType() + " wurde eingeparkt.");
                                    break;
                                } else if (vehicle.equals("Motorrad")||vehicle.equals("motorrad")) {
                                    System.out.println("Wie lautet das Nummernschild von dem Motorrad? ");
                                    String numPlate = input.nextLine();
                                    Motorcycle bike = new Motorcycle(numPlate);
                                    parkhaus.driveIn(bike);
                                    System.out.println("Das " + bike.getType() + " wurde eingeparkt.");
                                    break;
                                }else{
                                    throw new Fault();
                                }
                                //Fahrzeug entfernen
                            case 2:
                                input.nextLine();
                                System.out.println("Geben sie das zu Entfernende Fahrzeug an.");
                                String plate = input.nextLine();
                                parkhaus.driveOut(plate);
                                break;
                                //Fahrzeug finden
                            case 3:
                                input.nextLine();
                                System.out.println("Welches Fahrzeug möchten sie finden ? ");
                                String findVehicle = input.nextLine();
                                parkhaus.getPositionVehicle(findVehicle);

                                break;
                            //Freie Parkplätze Abfragen
                            case 4:
                                parkhaus.getFreeSize();
                                break;
                                //belegte Parkplätze abfragen
                            case 5:
                                parkhaus.getParkingSpotSize();
                                break;

                        }
                    }  catch (InputMismatchException e){
                        System.out.println("Bitte eine Zahl eingeben.");

                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());



                }
            }

            System.out.println("Programm auf ihren Wunsch beendet.\n" +
                    "Auf Wiedersehen.");
    }catch (Exception e){
            throw new RuntimeException();

        }
    }
}

