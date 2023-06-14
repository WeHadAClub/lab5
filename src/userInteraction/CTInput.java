package userInteraction;

import collection.baseClasses.Coordinates;
import collection.baseClasses.MusicGenre;
import collection.baseClasses.Person;
import exeptions.NoKeyExeptions;
import exeptions.WrongInputFormat;
import exeptions.NullFieldExeption;
import userInteraction.input.InputHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * check the input
 */
public class CTInput {
    public boolean checkKey(String key){
        try{
            Integer a = Integer.valueOf(key);
            if(a > 0){
                return true;
            }
            else{
                System.out.println("Ключ должен быть больше нуля");
                return false;
            }
        }
        catch (NumberFormatException ex){
            System.out.println("Задайте ключ целочисленным значением!");
            return false;
        }
    }
    //ЗАМЕНИТЬ!!!
    public Long lowCompareKey(InputHandler t2, Integer oldKey){
        while(true){
            try{
                String[] in = t2.read();
                if(in.length == 0) throw new NoKeyExeptions();
                if(!checkKey(in[0])) return null;
                Long key = Long.valueOf(in[0]);
                if(key < oldKey){
                    System.out.println("Ключ удовлетворяет условию.\nРазрешен ввод полей элемента колекции.\n");
                    return key;
                }
                else{
                    System.out.println("Введенный ключ не меньше исходного. Замена отменена");
                    return null;
                }
            }
            catch (NoKeyExeptions e){
                System.out.println("Вы не ввели ключ!\nПопробуйте еще раз:");
            }
            catch(NumberFormatException e){
                System.out.println("Ключ должен быть целым числом!\nПопробуйте еще раз:");
            }
        }
    }

    public String setName(InputHandler t2){
        while(true){
            System.out.println("Введите название музыкального коллектива: ");
            String[] name = t2.read();
            try {
                if (name.length==0) throw new NullFieldExeption();
                StringBuilder finalName = new StringBuilder();
                for (String str: name) {
                    finalName.append(str);
                }
                return finalName.toString();
            }
            catch (NullFieldExeption ex){
                System.out.println("Имя не может быть пустым значением");
            }
        }
    }

    public Coordinates setCoordinates(InputHandler t2) {
        Coordinates coord = new Coordinates();
        String[] numb;
        while (true) {
            System.out.println("Задайте координату Х");
            numb = t2.read();
            try {
                if (numb.length == 0) throw new NullFieldExeption();
                if (numb.length > 2) throw new NumberFormatException();
                int x = Integer.parseInt(numb[0]);
                if (x < -123) throw new WrongInputFormat();
                coord.setX(x);
                break;
            } catch (NullFieldExeption e) {
                System.out.println("Поле не может быть пустым");
            } catch (NumberFormatException e) {
                System.out.println("Поле должно быть одним числом типа int");
            } catch (WrongInputFormat e) {
                System.out.println("Поле должно быть больше -123");
            }

        }
        while (true) {
            System.out.println("Задайте координату Y");
            numb = t2.read();
            try {
                if (numb.length == 0) throw new NullFieldExeption();
                if (numb.length > 2) throw new NumberFormatException();
                float y = Float.parseFloat(numb[0]);
                coord.setY(y);
                break;
            } catch (NullFieldExeption e) {
                System.out.println("Поле не может быть пустым");
            } catch (NumberFormatException e) {
                System.out.println("Поле должно быть одним числом типа float");
            }
        }
        return coord;
    }

    /**
     * Set NumberOfParticipants
     */
    public Long setNOP(InputHandler t2){
        String[] numb;
        while (true){
            System.out.println("Введите количество участников музыкального коллектива");
            numb = t2.read();
            try{
                if(numb.length > 1) throw new WrongInputFormat();
                if(numb.length == 0) return null;
                long num = Long.parseLong(numb[0]);
                if(num < 0) throw new WrongInputFormat();
                return num;
            }
            catch (WrongInputFormat | NumberFormatException e){
                System.out.println("Поле должно быть одним числом типа long, большим 0");
            }
        }
    }

    /**
     * set musicGenre
     */
    public MusicGenre setMG(InputHandler t2){
        String[] genre;
        while(true){
            System.out.println("Выберите жанр вашего коллектива");
            MusicGenre[] n = MusicGenre.values();
            for (MusicGenre s:
                    n) {
                System.out.println(s);
            }
            genre = t2.read();
            try{
                if(genre.length > 1) throw new WrongInputFormat();
                if(genre.length == 0) return null;
                MusicGenre fgen = MusicGenre.valueOf(genre[0]);
                return fgen;
            }
            catch (WrongInputFormat| IllegalArgumentException e){
                System.out.println("Такого коллектива нет. Выберите коллектив из существующих");
            }
        }
    }

    /**
     * gradually fill in each field about the frontman
     * @param t2 is the user input
     * @return
     */
    public Person personality(InputHandler t2, String passID){
        String[] allIn;
        Person person = new Person();

        //name
        while(true){
            System.out.println("Введите имя вокалиста");
            allIn = t2.read();
            try{
                if(allIn.length == 0) throw new WrongInputFormat();
                StringBuilder name = new StringBuilder();
                for (String e: allIn) {
                    name.append(e);
                    name.append(" ");
                }
                person.setName(name.toString());
                break;
            }
            catch (WrongInputFormat e){
                System.out.println("Имя вокалиста должно быть строкой");
            }
        }

        //set birthday
        while(true){
            System.out.println("Введите дату рождения вокалиста в формате мм.дд.гггг");
            allIn = t2.read();
            DateFormat df = new SimpleDateFormat("MM.dd.yyyy");
            try{
                Date startDate = df.parse(allIn[0]);
                person.setBirthday(startDate);
                break;
            }
            catch (ParseException e){
                System.out.println("Глупышка, ты нарушил формат ввода. Попробуй еще раз😊");
            }
        }

        //set height
        while (true){
            System.out.println("Введите рост вокалиста");
            allIn = t2.read();
            try{
                if(allIn.length == 0){
                    person.setHeight(null);
                    break;
                }
                Float hght = Float.valueOf(allIn[0]);
                person.setHeight(hght);
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Рост вокалиста должен быть числом типа Float");
            }
        }

        //set weight
        while (true){
            System.out.println("Введите вес вокалиста");
            allIn = t2.read();
            try {
                if (allIn.length == 0) throw new NullFieldExeption();
                int wght = Integer.parseInt(allIn[0]);
                if(wght < 0) throw new WrongInputFormat();
                person.setWeight(wght);
                break;
            }
            catch (NullFieldExeption e){
                System.out.println("Вес не может быть пустым значением");
            }
            catch (NumberFormatException e){
                System.out.println("Вес должен быть целым числом");
            }
            catch (WrongInputFormat e){
                System.out.println("Вес не может быть отрицательным числом");
            }
        }

        //set passportID
        person.setPassportID(passID);

        return person;
    }
}
