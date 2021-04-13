package jb.games.tickettoride.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import jb.games.tickettoride.entities.CityLoader;
import jb.games.tickettoride.entities.CityManager;



public class DevTools {

    private CityManager cityManager;
    private DevMode devMode;

    public enum DevMode {
        CITY, RAIL
    }

    public DevTools(CityManager cityManager) {
        this.cityManager = cityManager;
        devMode = DevMode.CITY;
    }



    public void update(float delta) {

        if(Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            devMode = devMode == DevMode.CITY? DevMode.RAIL:DevMode.CITY;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            CityLoader.saveCities("Cities", cityManager.getCities());
            System.out.println("Cities saved");
        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            switch (devMode) {
                case CITY: addCity();break;
                case RAIL: addRail();break;
            }
        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            if(cityManager.isEntitySelected()) {
                TextInputListener listener = new TextInputListener();
                listener.addCity(cityManager.getSelectedCity());
                Gdx.input.getTextInput(listener, "Add city name", "", "City name");
            }
        }
    }

    private void addCity() {
        if(cityManager.isEntitySelected()) {
            cityManager.deleteCity(cityManager.getSelectedCity());
        }
        else {
            cityManager.createCity(Gdx.input.getX()-8, Gdx.graphics.getHeight()-Gdx.input.getY()-8);
        }
    }

    private void addRail() {
        cityManager.createRail(Gdx.input.getX()-8, Gdx.graphics.getHeight()-Gdx.input.getY()-8);

    }

    public String getDevMode() {
        return devMode.toString();
    }
}
