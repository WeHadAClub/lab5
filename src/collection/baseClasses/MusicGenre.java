package collection.baseClasses;

public enum MusicGenre {
    ROCK(1),
    JAZZ(2),
    MATH_ROCK(3),
    PUNK_ROCK(4),
    POST_PUNK(5);
    private int number;
    MusicGenre(int number){
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}