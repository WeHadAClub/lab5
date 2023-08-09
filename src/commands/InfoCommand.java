package commands;

import collection.CollectionManager;
import commands.managers.Command;

public class InfoCommand implements Command {
    @Override
    public String descr() {
        return "info - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов)";
    }
    @Override
    public void execute(CollectionManager mg, String[] input) {
        mg.info();
    }
}
