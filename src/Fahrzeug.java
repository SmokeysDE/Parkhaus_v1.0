//Fahrzeug als Abstrakte Klasse zur Vererbung
public abstract class Fahrzeug {
    private String numPlate;
    public Fahrzeug(String numPlate){
        this.numPlate = numPlate;
    }
    public Fahrzeug(){}
    public String getNumPlate() {
        return numPlate;
    }
    //Abstrakte Methode
    public abstract String getType();

}
class Car extends Fahrzeug{
    public Car(String numPlate){
        super(numPlate);
    }

   @Override
    public String getType() {
        //Gibt den String zurück
        return("Auto");
    }
}
class Motorcycle extends Fahrzeug{
    public Motorcycle(String numPlate){
        super(numPlate);
    }

   @Override
    public String getType() {

        return("Motorrad");
    }
}