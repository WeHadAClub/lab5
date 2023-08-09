package userInteraction.input;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The incoming stream handler class. It has the read, readLine methods
 */
public class InputHandler{
    ReadBase handler; //база чтения
    String[] words;
    public InputHandler(ReadBase n) throws IOException{
        handler = n;
    }
    public InputHandler(String path) throws FileNotFoundException {
        handler = new ReadBase(new FileInputStream(path));
    }

    /**
     *Проверка опциональна, так как лишь от программиста изначально зависит, будет ли вылетать ошибка
     * @return returns an array of strings that are words in the terminal input
     */
    public String[] read(){

        try {
            words = handler.read().split(" ");
            return words;
        }
        catch (NullPointerException | IOException e){
            return new String[0];
        }
    }

    public String readLine(){
        try {
            return handler.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean ready(){
        return handler.ready();
    }
}
