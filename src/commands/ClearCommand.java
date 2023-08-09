package commands;

import collection.CollectionManager;
import commands.managers.Command;

public class ClearCommand implements Command {
    @Override
    public String descr() {
        return "clear - очистить коллекцию";
    }

    @Override
    public void execute(CollectionManager mg, String[] input) {
        mg.clear();
    }
}
