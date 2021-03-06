package lab6.common.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Исходный класс для хранения информации о координатах маршрута
 */
public class Coordinates implements Serializable {
    public static final Integer MAX_X = 981;
    public static final int MAX_Y = 1000;

    @NotNull(message = "The X coordinate cannot be null")
    @Max(value = 981, message = "The X coordinate must be less than 947")
    private Integer x; //Максимальное значение поля: 981, Поле не может быть null

    @NotNull(message = "The Y coordinate cannot be null")
    private Long y; //Поле не может быть null


    public void setX(Integer x) {
        this.x = x;

    }

    public int getMAX_X() {
        return this.MAX_X;
    }

    public Integer getX() {
        return this.x;
    }

    public void setY(Long y) {
        this.y = y;

    }

    public Long getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }


}
