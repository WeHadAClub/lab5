package userInteraction.input;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.IOException;

public class InputHandler{
    ReadBase handler;
    String[] words;
    public InputHandler(ReadBase n) throws IOException{
        handler = n;
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
        catch (NullPointerException e){
            return new String[0];
        } catch (IOException e) {
            return new String[0];
        }
    }
}
