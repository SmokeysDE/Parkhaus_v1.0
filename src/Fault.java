public class Fault extends Exception {
    //Allgemein
    Fault(){
        super("Fehler0x01: Bitte versuchen sie es erneut");
    }
}
//Speziell
class NoCarException extends Exception{

    NoCarException() {
        super("Es befindet sich kein Fahrzeug in dem Parkhaus");
    }
}
class NoSpaceException extends Exception{

    NoSpaceException(){
        super("Leider ist das Parkhaus bereits voll.");
    }
}
class AlreadyParked extends Exception{

    AlreadyParked(){
        super("Das Fahrzeug ist schon vorhanden");
    }
}
class NotInside extends Exception{

    NotInside(){
        super("Das Fahrzeug befindet sich nicht im Parkhaus");
    }
}