package jb.games.tickettoride.entities;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.List;

public enum Cities {
    FRANKFURT(785, 620, "FRANKFURT", "");

    private final Vector2 pos;
    private final String name;
    private final List<String> adjacentCities;

    Cities(float x, float y, String name, String... adjacentCities) {
        pos = new Vector2(x,y);
        this.adjacentCities = Arrays.asList(adjacentCities);
        this.name = name;

    }


    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public String getName() {
        return name;
    }

    public List<String> getAdjacentCities() {
        return adjacentCities;
    }
}
