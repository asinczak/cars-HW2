package pl.akademiaspring.cars;

public class Car {

    private long id;
    private String mark;
    private String model;
    private String colour;

    public Car() {
    }

    public Car(long id, String mark, String model, String colour) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.colour = colour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
