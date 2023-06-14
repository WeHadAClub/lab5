package commands;

import collection.CollectionManager;
import commands.managers.Command;

public class HelpCommand implements Command {


    @Override
    public String descr() {
        return "help - комманда помощник. Введите ее и узнаете о других коммандах";
    }

    @Override
    public void execute(CollectionManager mg){
        mg.help();
    }
}
