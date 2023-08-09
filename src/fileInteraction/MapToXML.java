package fileInteraction;

import collection.baseClasses.MusicBand;
import commands.managers.Command;
import exeptions.WrongInputFormat;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * A class that translates a collection item into a file
 */
public class MapToXML {
    /**
     *Создается сам файл и производятся нужные проверки. Также вызывается метод записи информации.
     * @param path - путь к файлу
     * @param map - коллекция, которая записывается в файл
     */
    public void createFile(String path, Map<Integer, MusicBand> map) {
        String[] checkP = path.split("\\.");

        try {
            if(!checkP[1].equals("xml")) throw new FileNotFoundException();
            PrintWriter writer = new PrintWriter(path);

            writer.println("<?xml version=\"1.1\" encoding=\"UTF-8\" ?>");
            writer.println();
            writer.println("<musicbandcatalog>");
            writeToFile(writer, map);
            writer.println("</musicbandcatalog>");
            writer.close();

        } catch (FileNotFoundException |ArrayIndexOutOfBoundsException e) {
            System.out.println("Формат файла не .xml или вовсе не указан.\nФайл автоматически переводится в формат xml\n");
            path = path + ".xml";
            File xml = new File(path);
            try {
                boolean isCreated = xml.createNewFile();
                if (isCreated){
                    createFile(path, map);
                }
                else{
                    throw new IOException("Cannot create file.");
                }
            } catch (IOException ex) {
                System.out.println("Не удается создать файл. Причина не в тебе, но попробуй создать файл вручную\nи пропиши его название");
            }
        }
    }

    /**
     * Метод реализует запись информации коллекции в файл. Определеяется порядок полей и их положение в файле
     * @param writer - поток вывода
     * @param map - коллекция
     */
    public void writeToFile(PrintWriter writer, Map<Integer, MusicBand> map){
        for(Map.Entry<Integer, MusicBand> entry: map.entrySet()) {
            MusicBand mB = entry.getValue();
            writer.println("<musicband>" +
                    "\n<id>" + mB.getFrontMan().getPassportID() + "</id>"+
                    "\n<name>" + mB.getName() + "</name>"+
                    "\n<coordinates>\n\t<x>" + mB.getCoordinates().getX() + "</x>\n\t<y>" + mB.getCoordinates().getY()+
                    "</y>"+"\n</coordinates>"+
                    "\n<creationDate>" + mB.getCreationDate() + "</creationDate>"+
                    "\n<numberOfParticipants>" + mB.getNumberOfParticipants() + "</numberOfParticipants>"+
                    "\n<genre>" + mB.getGenre() + "</genre>"+
                    "\n<frontMan>\n\t<name>" + mB.getFrontMan().getName() + "</name>"+
                    "\n\t<birthday>" + mB.getFrontMan().getBirthday() + "</birthday>"+
                    "\n\t<height>" + mB.getFrontMan().getHeight() + "</height>"+
                    "\n\t<weight>" + mB.getFrontMan().getWeight() + "</weight>"+
                    "\n\t<passportID>" + mB.getFrontMan().getPassportID() + "</passportID>"+
                    "\n</frontMan>"+
                    "\n</musicband>");
        }
    }
}