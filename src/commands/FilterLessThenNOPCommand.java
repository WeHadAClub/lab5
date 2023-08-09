package commands;

import collection.CollectionManager;
import commands.managers.Command;
import exeptions.NoKeyExeptions;

public class FilterLessThenNOPCommand implements Command {
    @Override
    public String descr() {
        return "filter_less_than_number_of_participants [numberOfParticipants]: вывести элементы, значение поля numberOfParticipants которых меньше заданного";
    }
    @Override
    public void execute(CollectionManager mg, String[] input) {
        try{
            mg.filter(input);
        }
        catch (NoKeyExeptions e){
            System.out.println("Вы не ввели количество участников");
        }
        catch (NumberFormatException e){
            System.out.println("Количество участников должно быть числом формата Long");
        }
    }
}
