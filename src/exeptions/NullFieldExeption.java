package exeptions;

public class NullFieldExeption extends RuntimeException{
    public NullFieldExeption(String message){
        super(message);
    }
    public NullFieldExeption(){
        super();
    }
}
