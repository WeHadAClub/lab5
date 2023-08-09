package commands;

import collection.CollectionManager;
import commands.managers.Command;

public class SaveCommand implements Command {
    @Override
    public String descr() {
        return "save - сохраняет файл в файл. Формат ввода: save [filename]";
    }
    @Override
    public void execute(CollectionManager mg, String[] input) {
        mg.save();
    }

}
