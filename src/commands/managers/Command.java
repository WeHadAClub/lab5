package commands.managers;

import collection.CollectionManager;

/**
 * The interface that all commands of the program implement
 */
public interface Command {
    void execute(CollectionManager mg, String[] input);
    String descr();
}
