package commands;

import collection.CollectionManager;
import commands.managers.Command;
import exeptions.WrongInputFormat;

public class RemByGenreCommand implements Command {
    @Override
    public String descr() {
        return "remove_all_by_genre [genre_name]- удалить из коллекции все элементы, значение поля genre которого эквивалентно заданному";
    }
    @Override
    public void execute(CollectionManager mg, String[] input) {
        try {
            mg.genreRemove(input);
        } catch (WrongInputFormat e) {
            System.out.println("Вы не ввели жанр!\n");
        }
    }
}
