import collection.baseClasses.MusicBand;
import collection.baseClasses.MusicGenre;
import userInteraction.*;

public class Main {
    public static void main(String[] args){
        Manager manager = new Manager("Linkin_park.xml");
        manager.start();
    }
}

