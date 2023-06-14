package collection.baseClasses;

public class Coordinates {
    private int x; //Значение поля должно быть больше -123
    private Float y; //Поле не может быть null

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }
}