package commands;

import collection.CollectionManager;
import commands.managers.Command;
import exeptions.NoKeyExeptions;
import exeptions.WrongInputFormat;

public class IfLowCommand implements Command {
    @Override
    public String descr() {
        return "replace_if_low_now [key] - заменить значение по ключу, если новое значение меньше старого.";
    }
    @Override
    public void execute(CollectionManager mg) {
        try{
            mg.iflow();
        }
        catch(NoKeyExeptions e){
            System.out.println("Вы не ввели ключ");
        }
        catch(WrongInputFormat e){
            System.out.println("Элемента с таким ключом нет в коллекции.\nКоманда отменена");
        }
    }

}
