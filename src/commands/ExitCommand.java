package commands;

import collection.CollectionManager;
import commands.managers.Command;

public class ExitCommand implements Command {
    @Override
    public String descr() {
        return "exit - завершить программу (без сохранения в файл)";
    }

    @Override
    public void execute(CollectionManager manager, String[] input) {
        manager.exit();
    }
}
