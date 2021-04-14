package jb.games.tickettoride.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import jb.games.tickettoride.entities.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DevTools {

    private CityManager cityManager;
    private DevMode devMode;
    private static Vector2 mousePos;

    private boolean railJustCreated;
    private String railCreationInfo;

    private List<City> cityPoints;


    public enum DevMode {
        CITY, RAIL, CITYSELECT;
    }

    public DevTools(CityManager cityManager) {
        cityPoints = new ArrayList<>();
        mousePos = new Vector2();
        this.cityManager = cityManager;
        devMode = DevMode.CITY;
        railJustCreated = false;
        railCreationInfo ="";
    }



    public void update(float delta) {

        if(devMode == DevMode.CITYSELECT) {
            railCreationInfo = "\nSelected cities : " + (cityPoints.isEmpty()?"":cityPoints.get(0).getName());
        }

        mousePos.x = Gdx.input.getX();
        mousePos.y = Gdx.graphics.getHeight()-Gdx.input.getY();

        if(Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            if(devMode == DevMode.CITY) devMode = DevMode.RAIL;
            else if(devMode == DevMode.RAIL) devMode = DevMode.CITYSELECT;
            else if(devMode == DevMode.CITYSELECT) devMode = DevMode.CITY;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            CityLoader.saveCities("Cities", cityManager.getCities());
            RailLoader.saveRails("RailRoads", cityManager.getRailRods());
            System.out.println("Cities and rails saved.");
        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            switch (devMode) {
                case CITY: addOrDeleteCity();break;
                case RAIL: addRail(DevTools.getMouse());break;
                case CITYSELECT : selectCity();break;
            }
        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            switch (devMode) {
                case CITY: renameCity();break;
                case RAIL: addRail(DevTools.getMouse());break;
            }
        }

        if(railJustCreated && Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            railJustCreated = false;
            addRail(cityManager.getTempRail());
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

    private void addRail(Vector2 pos) {
        if(!railJustCreated){
            cityManager.createRail(new Rail(pos.x, pos.y, 0));
            railJustCreated = true;
        }
    }

    private void selectCity() {
        if(cityPoints.size() < 2) {
            cityPoints.add(cityManager.getSelectedCity());
        }
        if(cityPoints.size() == 2){
            cityManager.saveRails(cityPoints);
            cityPoints.clear();
            railJustCreated = false;
        }
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
        return devMode.toString()+(devMode==DevMode.CITYSELECT?railCreationInfo:"");
    }

    public void renameCity() {
        if(cityManager.isEntitySelected() && cityManager.getSelectedCity() != null) {
            TextInputListener listener = new TextInputListener();
            listener.addCity(cityManager.getSelectedCity());
            Gdx.input.getTextInput(listener, "Add city name", cityManager.getSelectedCity().getName(), "" +
                    "");
        }
    }

    public static double getDistance(Vector2 start, Vector2 end) {
        return Math.sqrt(Math.pow(Math.abs(start.x-end.x), 2)+Math.pow(Math.abs(end.y- start.y), 2));
    }

    public static double getDeltaX(Vector2 start, Vector2 end) {
        return Math.sqrt(Math.pow(Math.abs(start.x-end.x), 2));
    }

    public static double getDeltaY(Vector2 start, Vector2 end) {
        return Math.sqrt(Math.pow(Math.abs(end.y- start.y), 2));
    }
}
