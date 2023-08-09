package commands;

import collection.CollectionManager;
import commands.managers.Command;

public class AverageNOPCommand implements Command {
    @Override
    public String descr() {
        return "averageNOP - вывести среднее значение поля numberOfParticipants для всех элементов коллекции";
    }
    @Override
    public void execute(CollectionManager mg, String[] input) {
        mg.average();
    }
}
