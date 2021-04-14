package jb.games.tickettoride.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import jb.games.tickettoride.entities.CityLoader;
import jb.games.tickettoride.entities.CityManager;
import jb.games.tickettoride.entities.Rail;


public class DevTools {

    private CityManager cityManager;
    private DevMode devMode;
    private static Vector2 mousePos;
    private Rail rail;


    public enum DevMode {
        CITY, RAIL
    }

    public DevTools(CityManager cityManager) {
        rail = new Rail(0,0,0);
        mousePos = new Vector2();
        this.cityManager = cityManager;
        devMode = DevMode.CITY;
    }



    public void update(float delta) {

        mousePos.x = Gdx.input.getX();
        mousePos.y = Gdx.graphics.getHeight()-Gdx.input.getY();

        if(Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            devMode = devMode == DevMode.CITY? DevMode.RAIL:DevMode.CITY;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            CityLoader.saveCities("Cities", cityManager.getCities());
            System.out.println("Cities saved");
        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            switch (devMode) {
                case CITY: addOrDeleteCity();break;
                case RAIL: addRail();break;
            }
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && devMode == DevMode.RAIL) {

            cityManager.createRail(rail);
            rail.setRotation(getAngle(rail.getPos(), mousePos));
        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            switch (devMode) {
                case CITY: renameCity();break;
                case RAIL: addRail();break;
            }
        }
    }

    private void addOrDeleteCity() {
        if(cityManager.isEntitySelected()) {
            cityManager.deleteCity(cityManager.getSelectedCity());
        }
        else {
            cityManager.createCity(mousePos.x-8, mousePos.y-8);
        }
    }

    private void addRail() {
        rail.setPos(mousePos.x, mousePos.y);
    }

    public static float getAngle(Vector2 origin, Vector2 target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - origin.y, target.x - origin.x));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    public static Vector2 getMouse() {
        return mousePos;
    }

    public String getDevMode() {
        return devMode.toString();
    }

    public void renameCity() {
        if(cityManager.isEntitySelected() && cityManager.getSelectedCity() != null) {
            TextInputListener listener = new TextInputListener();
            listener.addCity(cityManager.getSelectedCity());
            Gdx.input.getTextInput(listener, "Add city name", cityManager.getSelectedCity().getName(), "" +
                    "");
        }
    }
}
