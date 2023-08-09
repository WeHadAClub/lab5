package commands;

import collection.CollectionManager;
import commands.managers.Command;
import exeptions.NoKeyExeptions;
import exeptions.WrongInputFormat;

public class RemoveGreaterCommand implements Command {
    @Override
    public String descr() {
        return "remove_greater [id_element] - удалить из коллекции все элементы, большие, чем заданный";
    }

    @Override
    public void execute(CollectionManager mg, String[] input) {
        try{
            mg.remGreatLow(input);
        }
        catch (NoKeyExeptions e){
            System.out.println("Вы не ввели ключ элемента");
        } catch (WrongInputFormat e) {
            System.out.println("Нет элемента с таким ключом");
        }
    }
}
