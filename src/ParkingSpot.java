public class ParkingSpot {
    private int floor;
    private int spot;
    public ParkingSpot(int floor, int spot){
        this.floor = floor;
        this.spot  = spot;
    }
    public ParkingSpot() {}
    public int getFloor() {
        return floor;
    }
    public int getSpot() {
        return spot;
    }
    //Gibt den Platz als String aus
    public String parkingSpot(){
        String place =  "E" + floor + "P" + spot;
        return place;
    }
}
