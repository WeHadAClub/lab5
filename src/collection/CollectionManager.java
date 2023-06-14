package collection;

import collection.baseClasses.MusicBand;
import commands.managers.CommandManager;
import exeptions.NoKeyExeptions;
import exeptions.WrongInputFormat;
import fileInteraction.MapToXML;
import fileInteraction.XMLToMap;
import userInteraction.CTInput;
import userInteraction.Manager;
import userInteraction.input.InputHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CollectionManager {
    Map<Integer, MusicBand> musicBandCatalogue = new LinkedHashMap<>();
    private final Manager mainManager;
    private final CTInput checker;
    private final InputHandler t2;
    private final CommandManager cM;
    private final java.time.LocalDateTime creationDate;
    private MapToXML mtx;
    public CollectionManager(Manager main, InputHandler t1, CommandManager cM, MapToXML mtx, String file) {
        mainManager = main;
        checker = new CTInput();
        t2 = t1;
        this.cM = cM;
        this.mtx = mtx;
        creationDate = LocalDateTime.now();

        XMLToMap xml = new XMLToMap();
        xml.readFromX(file, musicBandCatalogue);
    }

    public void help(){
        cM.getDescriptions();
    }
    public void save(){
        mtx.createFile(mainManager.getInput()[1], musicBandCatalogue);
        System.out.println("Коллекция сохранена в файл.");
    }
    public void info(){
        System.out.println("'Коллекция музыкальных коллективов'");
        System.out.println("Тип коллекции - " + musicBandCatalogue.getClass().getName() +"\n" +
                "Дата создания коллекции - " + creationDate + "\n" +
                "Количество элементов - " + musicBandCatalogue.size());
    }
    public void show(){
        System.out.println("\nВывод всех элементов коллекции:\n");
        Set<Integer> keys = musicBandCatalogue.keySet();
        for(Integer key: keys) {
            System.out.println(musicBandCatalogue.get(key));
        }
        if(keys.isEmpty()){
            System.out.println("Коллекция пуста\n");
        }
    }

    public void iflow() throws NoKeyExeptions, WrongInputFormat {
        String[] input = mainManager.getInput();
        if(input.length == 1) throw new NoKeyExeptions();
        if(musicBandCatalogue.isEmpty()) {
            System.out.println("Невозможно выполнить комманду - коллекция пуста.\n");
            return;
        }
        if(!musicBandCatalogue.containsKey(Integer.valueOf(input[1]))) throw new WrongInputFormat(); //если такого ключа нет в коллекции
        try {
            Integer oldKey = Integer.valueOf(input[1]);
            System.out.println("Введите ключ нового элемента: ");
            Long id = checker.lowCompareKey(t2, oldKey);//сюда добавляется ключ нового элемента. Ввел, потому что compareKey возвращает boolean
            if(id!= null){
                MusicBand mb = new MusicBand();
                mb.setId(id);
                mb.setName(checker.setName(t2));
                mb.setCoordinates(checker.setCoordinates(t2));
                mb.setNumberOfParticipants(checker.setNOP(t2));
                mb.setGenre(checker.setMG(t2));
                mb.setFrontMan(checker.personality(t2, String.valueOf(id)));
                //тут реализовать замену объекта
                musicBandCatalogue.remove(oldKey);

                Integer key = Integer.valueOf(String.valueOf(id));
                musicBandCatalogue.put(key, mb);
                System.out.println("Музыкальный коллектив записан в коллекцию.");
            }
        }
        catch(NumberFormatException e){
            System.out.println("Ключ должен быть целым числом");
        }


    }

    public void remove_key() throws NoKeyExeptions{
        String[] input = mainManager.getInput();
        if(input.length == 1) throw new NoKeyExeptions();
        try{
            Integer key = Integer.valueOf(input[1]);
            if(!musicBandCatalogue.containsKey(key)) throw new WrongInputFormat();
            musicBandCatalogue.remove(key);
            System.out.println("Элемент с ключом " + key + " удален из коллекции");
        }
        catch (NumberFormatException e){
            System.out.println("Введеный ключ должен быть целым числом, большим либо равным 0\n");
        } catch (WrongInputFormat e) {
            System.out.println("Элемента с таким ключом нет в коллекции\n");
        }
    }
    public void add() throws NoKeyExeptions {
        String[] input = mainManager.getInput();
        if(input.length == 1) throw new NoKeyExeptions();
        String cKey = input[1];
        if(!checker.checkKey(cKey)){
            return;
        }
        else {
            Integer key = Integer.valueOf(cKey);
            if(!musicBandCatalogue.containsKey(key)){
                //about music band
                MusicBand mb = new MusicBand();
                mb.setId(Long.valueOf(key));
                mb.setName(checker.setName(t2));
                mb.setCoordinates(checker.setCoordinates(t2));
                mb.setNumberOfParticipants(checker.setNOP(t2));
                mb.setGenre(checker.setMG(t2));
                mb.setFrontMan(checker.personality(t2, cKey));
                musicBandCatalogue.put(key, mb);
                System.out.println("Музыкальный коллектив записан в коллекцию.");
            }
            else{
                System.out.println("Группа с таким ключом уже существует, попробуйте ввести другой");
            }
        }
    }


    public void exit(){
        System.out.println("Завершаем работу");
        System.exit(0);
    }
}
