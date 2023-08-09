package commands;
import  collection.CollectionManager;
import collection.baseClasses.MusicBand;
import commands.managers.Command;
import exeptions.NoKeyExeptions;

import java.security.cert.CertificateEncodingException;

public class InsertCommand implements Command {
    public InsertCommand(){
    }
    @Override
    public String descr() {
        return "insert - добавление нового элемента с заданным ключом. формат ввода: insert [key]";
    }

    @Override
    public void execute(CollectionManager manager, String[] input) {
        try{
            manager.add(input);
        }
        catch (NoKeyExeptions ex){
            System.out.println("Вы не ввели ключ");
        }
    }
}
