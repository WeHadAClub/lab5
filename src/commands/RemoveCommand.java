package commands;

import collection.CollectionManager;
import commands.managers.Command;
import exeptions.NoKeyExeptions;

public class RemoveCommand implements Command {
    @Override
    public String descr() {
        return "remove_key [key] - удалить элемент из коллекции по его ключу.";
    }
    @Override
    public void execute(CollectionManager mg) {
        try{
            mg.remove_key();
        }
        catch (NoKeyExeptions e){
            System.out.println("Вы не ввели ключ");
        }
    }
}