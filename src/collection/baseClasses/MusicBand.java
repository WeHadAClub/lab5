package collection.baseClasses;


import java.time.LocalDateTime;

/**
 * A class containing information about a music group
 */
public class MusicBand implements Comparable<MusicBand>{

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private MusicGenre genre; //Поле может быть null
    private Person frontMan; //Поле не может быть null

    /**
     * as soon as an object is created, the creation time is remembered
     */
    public MusicBand(){
        creationDate = LocalDateTime.now();
        coordinates = new Coordinates();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates.setX(coordinates.getX());
        this.coordinates.setY(coordinates.getY());
    }

    public Long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public Person getFrontMan() {
        return frontMan;
    }

    public void setFrontMan(Person frontMan) {
        this.frontMan = frontMan;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public int compareTo(MusicBand o) {
        return (int) (getId() - o.getId());
    }

    @Override
    public String toString() {
        return "Называние музыкальной группы: " + getName() + "" +
                "\nID музыкальной группы: " + getId() + "" +
                "\nКоордината X:" + getCoordinates().getX()+
                "\nКоордината Y:" + getCoordinates().getY()+
                "\nДата создания группы: " + getCreationDate() +
                "\nКоличество участников группы: " + getNumberOfParticipants() +
                "\nЖанр: " + getGenre() +"" +
                "\nДата создания группы: " + getCreationDate() + "" +
                "\nИмя вокалиста: " + getFrontMan().getName() +
                "\nДень рождения вокалиста: " + getFrontMan().getBirthday() +
                "\nВес вокалиста: " + getFrontMan().getWeight() +
                "\nРост вокалиста: " + getFrontMan().getHeight() +
                "\n";
    }
}