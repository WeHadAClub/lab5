package fileInteraction;

import collection.baseClasses.Coordinates;
import collection.baseClasses.MusicBand;
import collection.baseClasses.MusicGenre;
import collection.baseClasses.Person;
import exeptions.WrongInputFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class XMLToMap {

    public void readFromX(String file, Map<Integer, MusicBand> baseColl) {
        MusicBand mb = new MusicBand();
        Coordinates crd = new Coordinates();
        Person frontman = new Person();
        Scanner in = null;
        try {
            in = new Scanner(new File(file));
            int k = 0;
            boolean flag = true; //для проверки корректности ввода
            while (in.hasNextLine()) {
                String rn = in.nextLine();
                switch (k) {
                    case 3:
                        try {
                            if (rn.equals("><")) throw new WrongInputFormat();
                            String arg = rn.replace("<id>", "");
                            arg = arg.replace("</id>", "");
                            arg = arg.replaceAll("\t", "");
                            Long id = Long.parseLong(arg);
                            if (id < 0) throw new WrongInputFormat();
                            mb.setId(id);
                        } catch (WrongInputFormat | NumberFormatException e) {
                            System.out.println("Неверный ввод. Поле ID должно быть целым числом больше 0");
                            flag = false;
                        }
                        break;
                    case 4:
                        try {
                            if (rn.contains("><")) throw new WrongInputFormat();
                            String arg = rn.replace("<name>", "");
                            arg = arg.replace("</name>", "");
                            arg = arg.replaceAll("\t", "");
                            mb.setName(arg);

                        } catch (WrongInputFormat e) {
                            System.out.println("Неверный ввод. Поле имени не может быть пустым");
                            flag = false;
                        }
                        break;
                    case 6:
                        try {
                            if (rn.contains("><")) throw new WrongInputFormat();
                            String arg = rn.replace("<x>", "");
                            arg = arg.replace("</x>", "");
                            arg = arg.replaceAll("\t", "");
                            int end = Integer.parseInt(arg);
                            if (end < -123) throw new WrongInputFormat();
                            crd.setX(end);
                        } catch (WrongInputFormat | NumberFormatException e) {
                            System.out.println("Неверный ввод. Поле не может быть null и должно быть больше -123");
                            flag = false;
                        }
                        break;
                    case 7:
                        try {
                            if (rn.contains("><")) throw new WrongInputFormat();
                            String arg = rn.replace("<y>", "");
                            arg = arg.replace("</y>", "");
                            arg = arg.replaceAll("\t", "");
                            float end = Float.parseFloat(arg);
                            crd.setY(end);
                            mb.setCoordinates(crd);
                        } catch (WrongInputFormat | NumberFormatException e) {
                            System.out.println("Неверный ввод. Поле не может быть null");
                            flag = false;
                        }
                        break;
                    case 9:
                        try {
                            if (rn.contains("><")) throw new WrongInputFormat();
                            String arg = rn.replace("<creationDate>", "");
                            arg = arg.replace("</creationDate>", "");
                            arg = arg.replaceAll("\t", "");
                            arg = arg.replaceAll("`", "");
                            LocalDateTime ldt = LocalDateTime.parse(arg);
                            mb.setCreationDate(ldt);
                        } catch (WrongInputFormat | DateTimeParseException e) {
                            System.out.println("Неверный ввод. Поле не может быть null, поле должно быть датой");
                            flag = false;
                        }
                        break;
                    case 10:
                        try {
                            if (rn.contains("><")) throw new WrongInputFormat();
                            String arg = rn.replace("<numberOfParticipants>", "");
                            arg = arg.replace("</numberOfParticipants>", "");
                            arg = arg.replaceAll("\t", "");
                            Long nop = Long.parseLong(arg);
                            if (nop < 0) throw new WrongInputFormat();
                            mb.setNumberOfParticipants(nop);
                        } catch (WrongInputFormat | NumberFormatException e) {
                            System.out.println("Неверный ввод. Поле не может быть null, значение должно быть больше 0");
                            flag = false;
                        }
                        break;
                    case 11:
                        try {
                            String arg = rn.replace("<genre>", "");
                            arg = arg.replace("</genre>", "");
                            arg = arg.replaceAll("\t", "");
                            MusicGenre genre = MusicGenre.valueOf(arg);
                            mb.setGenre(genre);
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный ввод. Нет такого музыкального жанра");
                            flag = false;
                        }
                        break;
                    case 13:
                        try {
                            if (rn.contains("><")) throw new WrongInputFormat();
                            String arg = rn.replace("<name>", "");
                            arg = arg.replace("</name>", "");
                            arg = arg.replaceAll("\t", "");
                            frontman.setName(arg);
                        } catch (WrongInputFormat | NumberFormatException e) {
                            System.out.println("Неверный ввод. Имя не может быть null");
                            flag = false;
                        }
                        break;
                    case 15:
                        try {
                            String arg = rn.replace("<height>", "");
                            arg = arg.replace("</height>", "");
                            arg = arg.replaceAll("\t", "");
                            float h = Float.parseFloat(arg);
                            if (h < 0) throw new WrongInputFormat();
                            frontman.setHeight(h);
                        } catch (WrongInputFormat e) {
                            System.out.println("Неверный ввод. Рост должен быть больше нуля");
                            flag = false;
                        }
                        break;
                    case 16:
                        try {
                            String arg = rn.replace("<weight>", "");
                            arg = arg.replace("</weight>", "");
                            arg = arg.replaceAll("\t", "");
                            int w = Integer.parseInt(arg);
                            if (w < 0) throw new WrongInputFormat();
                            frontman.setWeight(w);
                        } catch (WrongInputFormat e) {
                            System.out.println("Неверный ввод. Рост должен быть больше нуля");
                            flag = false;
                        }
                        break;
                    case 17:
                        try {
                            if (rn.contains("> <")) throw new WrongInputFormat();
                            String arg = rn.replace("<passportID>", "");
                            arg = arg.replace("</passportID>", "");
                            arg = arg.replaceAll("\t", "");
                            Integer ID = Integer.valueOf(arg);
                            if (baseColl.containsKey(ID)) throw new WrongInputFormat();
                            frontman.setPassportID(arg);
                            mb.setFrontMan(frontman);
                            baseColl.put(ID, mb);
                        } catch (WrongInputFormat e) {
                            System.out.println("Неверный ввод. ID не может быть пустой строкой и должен быть уникальным");
                            flag = false;
                        }
                        break;
                }
                if (!flag) {
                    break;
                }
                k++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Нет файла с таким именем");
        }
    }
}
