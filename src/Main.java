import collection.baseClasses.MusicBand;
import collection.baseClasses.MusicGenre;
import userInteraction.*;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        try{
            Manager manager = new Manager("Linkin_park.xml");
            manager.start();
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Вы не ввели название файла");
        }
    }
}

