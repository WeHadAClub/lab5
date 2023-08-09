package commands;

import collection.CollectionManager;
import commands.managers.Command;
import exeptions.WrongInputFormat;

import java.io.FileNotFoundException;

public class ExecuteScript implements Command {
    @Override
    public String descr() {
        return "execute_script [file_name]- считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
    @Override
    public void execute(CollectionManager mg, String[] input) {
        try {
            mg.executeScript(input);
        } catch (WrongInputFormat e) {
            System.out.println("Вы не ввели название файла");
        } catch (FileNotFoundException e) {
            System.out.println("Нет файла с таким именем");
        }
    }
}
