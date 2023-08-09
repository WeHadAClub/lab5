package collection;

import collection.baseClasses.MusicBand;
import collection.baseClasses.MusicGenre;
import commands.managers.Command;
import commands.managers.CommandManager;
import exeptions.NoKeyExeptions;
import exeptions.WrongInputFormat;
import fileInteraction.MapToXML;
import fileInteraction.XMLToMap;
import userInteraction.CTInput;
import userInteraction.Manager;
import userInteraction.input.InputHandler;


import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * collection manager. In the classroom, all commands are executed and direct interaction with the collection takes place
 */
public class CollectionManager {
    final int MAX_SCRIPT_TRANSITION_COUNT = 3;

    Map<Integer, MusicBand> musicBandCatalogue = new LinkedHashMap<>();
    int scriptTransitionCount = 0;
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

    public void genreRemove(String[] input) throws WrongInputFormat {
        if(input.length == 1) throw new WrongInputFormat();
        String isGr = input[1];
        MusicGenre genre;
        try{
             genre = MusicGenre.valueOf(isGr);
        }
        catch(IllegalArgumentException e){
            System.out.println("Такой музыкальный жанр не поддерживается.\n");
            return;
        }
        Set<Integer> keys = musicBandCatalogue.keySet();
        if(keys.isEmpty()){
            System.out.println("Коллекция пуста\n");
            return;
        }
        for(Integer key: keys) {
            MusicBand mb = musicBandCatalogue.get(key);
            if (mb.getGenre() == genre) {
                musicBandCatalogue.remove(key);
            }
        }
        System.out.println("Все элементы жанра " + isGr + " удалены из коллекции.");
    }
    public void average(){
        Set<Integer> keys = musicBandCatalogue.keySet();
        long sumNop = 0;
        if(keys.isEmpty()){
            System.out.println("Коллекция пуста\n");
            return;
        }
        for(Integer key: keys) {
            MusicBand mb = musicBandCatalogue.get(key);
            sumNop += mb.getNumberOfParticipants();
        }
        float averSum = (float) sumNop/musicBandCatalogue.size();
        System.out.println("Среднее значение поля numberOfParticipants для всех элементов равно: " + averSum);
    }
    public void iflow(String[] input) throws NoKeyExeptions, WrongInputFormat {
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

    public void remGreatLow(String[] input) throws NoKeyExeptions, WrongInputFormat {
        if (input.length == 1) throw new NoKeyExeptions();
        if (!checker.checkKey(input[1])) return;

        Integer mainKay = Integer.valueOf(input[1]);
        if (!musicBandCatalogue.containsKey(mainKay)) throw new WrongInputFormat();
        Set<Integer> keys = musicBandCatalogue.keySet();
        if (keys.isEmpty()) {
            System.out.println("Коллекция пуста\n");
            return;
        }
        MusicBand mainBand = musicBandCatalogue.get(mainKay);
        int k = 0;
        for (Integer key : keys) {
            MusicBand secBand = musicBandCatalogue.get(key);
            if (input[0].equals("remove_greater")) {
                if (mainBand.compareTo(secBand) < 0) {
                    musicBandCatalogue.remove(key);
                    System.out.println("sksv");
                    k++;
                }
            } else if (input[0].equals("remove_lower")) {
                if (mainBand.compareTo(secBand) > 0) {
                    musicBandCatalogue.remove(key);
                    k++;
                }
            }
        }
        if (input[0].equals("remove_lower")){
            if (k == 0) {
                System.out.println("Нет элементов, меньших чем заданный");
            }
            else{
                System.out.println("Элементы коллекции, меньшие " + mainBand.getName() + ", были удалены");
            }
        }
        else if(input[0].equals("remove_greater")){
            if (k == 0) {
                System.out.println("Нет элементов, больших чем заданный");
            }
            else{
                System.out.println("Элементы коллекции, большие " + mainBand.getName() + ", были удалены");
            }
        }
    }

    public void filter(String[] input) throws NoKeyExeptions, NumberFormatException{
        if(input.length == 1) throw new NoKeyExeptions();
        Long nop = Long.valueOf(input[1]);
        Set<Integer> keys = musicBandCatalogue.keySet();
        if(keys.isEmpty()){
            System.out.println("Коллекция пуста\n");
            return;
        }
        for(Integer key: keys) {
            MusicBand mb = musicBandCatalogue.get(key);
            if(mb.getNumberOfParticipants() < nop){
                System.out.println("Музыкальные группы, количество участников которых меньше чем " + nop + ":");
                System.out.println(mb + "\n");
            }
        }
    }
    public void update(String[] input) throws NoKeyExeptions{
        if(input.length == 1) throw new NoKeyExeptions();
        String cKey = input[1];
        if(!checker.checkKey(cKey)){
            return;
        }
        if(!musicBandCatalogue.containsKey(Integer.valueOf(input[1]))){
            System.out.println("В коллекции нет элемента с таким ключом. Команда отменена");
            return;
        }
        MusicBand mb = new MusicBand();
        mb.setId(Long.valueOf(cKey));
        mb.setName(checker.setName(t2));
        mb.setCoordinates(checker.setCoordinates(t2));
        mb.setNumberOfParticipants(checker.setNOP(t2));
        mb.setGenre(checker.setMG(t2));
        mb.setFrontMan(checker.personality(t2, cKey));
        musicBandCatalogue.put(Integer.valueOf(cKey), mb);
        System.out.println("Музыкальный коллектив под ключом " + cKey + " обновлен");
    }
    public void clear(){
        musicBandCatalogue.clear();
        System.out.println("Коллекция очищена\n");
    }

    public void remove_key(String[] input) throws NoKeyExeptions{
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
    public void add(String[] input) throws NoKeyExeptions {
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

    public void executeScript(String[] input) throws WrongInputFormat, FileNotFoundException {
        CommandManager cm = new CommandManager();
        Command command;
        if(input.length == 1) throw new WrongInputFormat();
        String file = input[1]; //имя файла
        InputHandler readF = new InputHandler(file); //тут может вылететь ошибка FileNotFoundException
        while(readF.ready()){
            String[] input2 = readF.read();
            if(cm.isCommand(input2[0])){
                if(Objects.equals(input2[0], "execute_script")){
                    scriptTransitionCount += 1;
                }
                if(scriptTransitionCount > MAX_SCRIPT_TRANSITION_COUNT){
                    System.out.println("Произошло зацикливание. Выполнение команды остановлено\n");
                    scriptTransitionCount = 0;
                    return;
                }
                command = cm.getCommand(input2[0]);
                command.execute(this, input2);
            }
        }
    }
    public void exit(){
        System.out.println("Завершаем работу");
        System.exit(0);
    }
}
