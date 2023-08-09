package userInteraction;

import collection.CollectionManager;
import collection.baseClasses.MusicBand;
import commands.managers.Command;
import commands.managers.CommandManager;
import fileInteraction.MapToXML;
import fileInteraction.XMLToMap;
import userInteraction.input.InputHandler;
import userInteraction.input.ReadBase;

import java.io.*;
import java.util.stream.Collectors;

/**
 * The main manager who delegates tasks to other managers. In fact, it is the starting point of the program
 */
public class Manager {
    private InputHandler tNow;
    private final CommandManager commM;
    private final CollectionManager collM;
    private MapToXML mtx;
    String[] input;
    {
        try{
            tNow = new InputHandler(new ReadBase(System.in));
        }
        catch (IOException e){
            e.getStackTrace();
        }
        commM = new CommandManager();
        mtx = new MapToXML();
    }
    public Manager(String filePath){
        collM = new CollectionManager(this, tNow, commM, mtx, filePath);
    }
    public String[] getInput(){
        return input;
    }

    public void start(){
        System.out.println("Привет! \nВведи 'help', чтобы увидеть список комманд. ");
        Command command;
        while(true){
            System.out.println("Введите комманду: ");
            input = tNow.read();
            try {
                if(commM.isCommand(input[0])){
                    command = commM.getCommand(input[0]);
                    command.execute(collM, input);
                }
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Вы ввели пустую строку");
            }

        }
    }
}
