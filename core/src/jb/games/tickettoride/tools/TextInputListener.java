package jb.games.tickettoride.tools;

import com.badlogic.gdx.Input;
import jb.games.tickettoride.entities.City;

public class TextInputListener implements Input.TextInputListener {

    private City city = null;
    @Override
    public void input (String text) {
        city.setName(text);
    }

    @Override
    public void canceled () {
    }

    public void addCity(City city){
        this.city = city;
    }

}
