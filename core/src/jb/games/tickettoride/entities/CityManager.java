package jb.games.tickettoride.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityManager {
    private final List<City> cities;
    private final Map<City, List<RailRoad>> railRoads;
    private final List<RailRoad> allRails;


    private boolean entitySelected;

    public CityManager() {

        cities = new ArrayList<>();
        railRoads = new HashMap<>();
        allRails = new ArrayList<>();

        entitySelected = false;

        createCities();
        createRailRoads();
    }

    private void createCities() {
        for(Cities cityTemplate : Cities.values()) {
            City city = new City(cityTemplate.getX(), cityTemplate.getY(), cityTemplate.getName());
            cities.add(city);
        }
        for(City city : cities) {
            city.addAdjacent(cities);
        }
    }

    private void createRailRoads() {
        for(City city : cities) {
            List<RailRoad> rails = new ArrayList<>();
            for(City adjacent : city.getAdjacentCities()) {
                if(railRoads.containsKey(adjacent)) {
                    for(RailRoad rr : railRoads.get(adjacent)) {
                        if(rr.getDeparture() == adjacent && rr.getDestinationCity() == city) {
                            rails.add(rr);
                            break;
                        }
                    }
                }
                else {
                    RailRoad railRoad = new RailRoad(city, adjacent, RailRoadLength.valueOf(city.getName()).getRailDistance(adjacent.getName()));
                    rails.add(railRoad);
                    allRails.add(railRoad);
                }
            }
            railRoads.put(city, rails);
        }
    }

    public List<City> getCities() {
        return cities;
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
    }

    public void render(SpriteBatch batch) {
        for(City city : cities) {
            city.render(batch);
        }

        for(RailRoad railRoad : allRails) {
            railRoad.render(batch);
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
}
