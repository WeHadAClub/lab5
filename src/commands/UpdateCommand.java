package commands;

import collection.CollectionManager;
import commands.managers.Command;
import exeptions.NoKeyExeptions;

public class UpdateCommand implements Command {
    @Override
    public String descr() {
        return "update [id] : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void execute(CollectionManager mg, String[] input) {
        try {
            mg.update(input);
        }
        catch(NoKeyExeptions e){
            System.out.println("Вы не ввели ключ элемента.");
        }
    }
}
