import java.util.ArrayList;
import java.util.HashMap;
public class Parkhaus {
    private int numFloor;
    private int numSpotPerFloor;
    private HashMap<String, String> parkingSpotHashMap;
    private ArrayList<String> freeSpaceList;

    //Konstruktor
    public Parkhaus(int numFloor, int numSpotPerFloor){
        this.numFloor = numFloor;
        this.numSpotPerFloor = numSpotPerFloor;
        this.parkingSpotHashMap = new HashMap<String, String>();
        this.freeSpaceList = new ArrayList<String>();
        for(int floor=1; !(floor > numFloor); floor++){
            //Schleife um die Plätze in die Liste einzufügen
            for(int spot = 1; !(spot > numSpotPerFloor); spot++){
                ParkingSpot parked = new ParkingSpot(floor,spot);
                freeSpaceList.add(parked.parkingSpot());
            }
        }
    }

    public boolean driveIn(Fahrzeug fahrzeug) throws AlreadyParked, NoSpaceException{
//Einfahren eines Fahrzeugs
        if (parkingSpotHashMap.containsKey(fahrzeug.getNumPlate())) {
            throw new AlreadyParked();
        } else if (freeSpaceList.size() == 0) {
            throw new NoSpaceException();
        }
        String pspot = freeSpaceList.remove(0);
        parkingSpotHashMap.put(fahrzeug.getNumPlate(), pspot);
        System.out.println("Geparkt auf " + pspot);

        return true;
    }
    //Ausfahren
    public boolean driveOut(String plate) throws NoCarException, NotInside{
        if(parkingSpotHashMap.isEmpty()) {
            throw new NoCarException();
        } else if (!parkingSpotHashMap.containsKey(plate)){
            throw new NotInside();
        }
        String spot = parkingSpotHashMap.get(plate);
        parkingSpotHashMap.remove(plate);
        freeSpaceList.add(spot);
        System.out.println(plate + " Entfernt");

        return true;
    }
    //Positionsabfrage
    public boolean getPositionVehicle(String plate) throws NotInside{
        if(parkingSpotHashMap.containsKey(plate)){
            System.out.println("Das Fahrzeug " + plate + " steht auf Platz " + parkingSpotHashMap.get(plate));
        } else if (!parkingSpotHashMap.containsKey(equals(plate))){
            throw new NotInside();
        }
        return true;
    }
    //Abfrage der Freien Plätze
    public boolean getFreeSize() throws NoSpaceException{
        if(freeSpaceList.size() == 0){
            throw new NoSpaceException();
        }
        System.out.println("Es sind " + freeSpaceList.size() + " Plätze frei.");
        return true;
    }
    public boolean getParkingSpotSize() throws NoCarException{
        if(parkingSpotHashMap.size() == 0){
            throw new NoCarException();
        }
        System.out.println("Es sind " + parkingSpotHashMap.size() + " Plätze belegt.");
        return true;
    }

    //Zur Weiterentwicklung
    public ArrayList<String> getFreeSpaceList() {
        return freeSpaceList;
    }

    public HashMap<String, String> getParkingSpotHashMap() {
        return parkingSpotHashMap;
    }

}
