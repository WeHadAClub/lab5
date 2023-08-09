package commands.managers;

import commands.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Command manager. Stores information objects of the command classes in the collection
 */
public class CommandManager {
    Map<String, Command> commandMap = new HashMap<>();

    public CommandManager() {
        commandMap.put("exit", new ExitCommand());
        commandMap.put("insert", new InsertCommand());
        commandMap.put("help", new HelpCommand());
        commandMap.put("info", new InfoCommand());
        commandMap.put("save", new SaveCommand());
        commandMap.put("show", new ShowCommand());
        commandMap.put("replace_if_low_now", new IfLowCommand());
        commandMap.put("remove_key", new RemoveCommand());
        commandMap.put("remove_all_by_genre", new RemByGenreCommand());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("clear", new ClearCommand());
        commandMap.put("update", new UpdateCommand());
        commandMap.put("remove_greater", new RemoveGreaterCommand());
        commandMap.put("averageNOP", new AverageNOPCommand());
        commandMap.put("remove_lower", new RemoveLowerCommand());
        commandMap.put("filter_less_than_number_of_participants", new FilterLessThenNOPCommand());
    }

    /**
     *the command checks whether the entered line is in the list of commands
     * @param com - input from the terminal
     * @return
     */
    public boolean isCommand(String com){
        if(commandMap.containsKey(com)){
            return true;
        }
        else{
            System.out.println("CommandManager: Друг, такой комманды у нас нет. Попробуй еще раз");
            return false;
        }
    }

    public Command getCommand(String com){
        return commandMap.get(com);
    }
    public void getDescriptions(){
        for(Map.Entry<String, Command> entry: commandMap.entrySet()) {
            // get command
            Command value = entry.getValue();
            System.out.println("* " + value.descr());
        }
    }

}
