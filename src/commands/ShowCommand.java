package commands;

import collection.CollectionManager;
import commands.managers.Command;

public class ShowCommand implements Command {
    @Override
    public String descr() {
        return "show - выводит информацию о всех элементах коллекции";
    }
    @Override
    public void execute(CollectionManager mg) {
        mg.show();
    }
}
