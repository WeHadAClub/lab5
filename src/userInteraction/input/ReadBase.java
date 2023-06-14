package userInteraction.input;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * the class on which the reading of files is based
 * @author zubair
 */
public class ReadBase {
    /**
     * f - поле потока ввода
     */
    private InputStreamReader f;

    /**
     *
     * @param is - Input Stream
     */
    public ReadBase(InputStream is){
        f = new InputStreamReader(is);
    }

    /**
     * @return str - строка, полученная путем преобразования юникода введенных
     * в поток ввода символов в строку
     * @throws IOException
     */
    public String read() throws IOException {
        int rn = f.read();
        String str = Character.toString((char)rn);
        String test = "";

        //все символы совмещаются в одну строку
        while(f.ready()){
            rn = f.read();
            test = Character.toString((char)rn);
            if(!test.equals("\n")){
                str += test;
            }
        }
        if(!str.equals("\n")) {
            return str;

        }
        else{
            return null;
        }
    }


}
