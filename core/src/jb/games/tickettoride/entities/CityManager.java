package jb.games.tickettoride.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import jb.games.tickettoride.tools.DevTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityManager {
    private final List<City> cities;
    private final Map<City, List<RailRoad>> railRoads;
    private final List<RailRoad> allRails;
    private List<Rail> tempRails = new ArrayList<>();


    private boolean entitySelected;

    public CityManager() {
        tempRails = new ArrayList<>();
        cities = new ArrayList<>();
        railRoads = new HashMap<>();
        allRails = new ArrayList<>();

        entitySelected = false;

        createCities();
    }


    private void createCities() {
        cities.addAll(CityLoader.loadCities("Cities",cities));
        System.out.println(cities.size());
        for (City city : cities) {
            System.out.println(city.getName());
        }
    }





    public List<City> getCities() {
        return cities;
    }

    public List<RailRoad> getRailRods() {
        return allRails;
    }

    public void update(float delta) {
        entitySelected = false;

        for(City city : cities) {
            city.update(delta);
            if(city.isSelected()) entitySelected = true;
         }
        for(RailRoad railRoad : allRails) {
            railRoad.update(delta);
            if(railRoad.isSelected()) entitySelected = true;
        }
        for(Rail rail : tempRails) {
            rail.update(delta);
            if(rail.isSelected()) entitySelected = true;
        }
    }

    public void render(SpriteBatch batch) {
        for(City city : cities) {
            city.render(batch);
        }

        for(RailRoad railRoad : allRails) {
            railRoad.render(batch);
        }
        for(Rail rail : tempRails) {
            rail.render(batch);
        }

    }

    public boolean isEntitySelected() {
        return entitySelected;
    }

    public String getHoverInfo() {
        for(City city : cities) {
            if(city.isSelected()) {
                return city.getInfo();
            }
        }
        for(RailRoad railRoad : allRails) {
            if(railRoad.isSelected()) {
                return railRoad.getHoverInfo();
            }
        }
        return "";
    }

    public City getSelectedCity() {
        for(City city : cities) {
            if(city.isSelected()) {
                return city;
            }
        }
        return null;
    }

    public Vector2 getTempRail() {
        return tempRails.get(tempRails.size()-1).getEndPos();
    }

    public void saveRails(List<City> cityPoints) {
        City start = cityPoints.get(0);
        City end = cityPoints.get(1);
        RailRoad railRoad = new RailRoad(start, end, tempRails);
        if(!railRoads.containsKey(start)) {
            List<RailRoad> rr = new ArrayList<>();
            rr.add(railRoad);
            railRoads.put(start, rr);
        }
        else {
            railRoads.get(start).add(railRoad);
        }

        if(!railRoads.containsKey(end)) {
            List<RailRoad> rr = new ArrayList<>();
            rr.add(railRoad);
            railRoads.put(end, rr);
        }
        else {
            railRoads.get(end).add(railRoad);
        }
        allRails.add(railRoad);
        tempRails.clear();
    }


    ////////////////////////
    ////////DEV TOOLS///////
    ///////////////////////


    public void deleteCity(City city) {
        cities.remove(city);
    }

    public void createCity(float x, float y) {
        City city = new City(x,y,"temp");
        cities.add(city);
    }

    public void createRail(Rail rail) {
        tempRails.add(rail);
    }
}
