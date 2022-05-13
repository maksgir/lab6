package lab6.common.entities;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Location implements Serializable {

    /**
     * поля для хранения координат
     */
    @NotNull(message = "The X coordinate cannot be null")
    private Double x; //Поле не может быть null
    @NotNull(message = "The X coordinate cannot be null")
    private long y;
    /**
     * поле для хранения названия локации
     */
    @NotNull(message = "Location's name cannot be null")
    private String name; //Поле не может быть null


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return name + " x: " + x + " y: " + y;
    }
}