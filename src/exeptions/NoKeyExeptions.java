package exeptions;

public class NoKeyExeptions extends RuntimeException{
    public NoKeyExeptions() {
    }

    public NoKeyExeptions(String message) {
        super(message);
    }
}
