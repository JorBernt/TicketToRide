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
    private List<Rail> tempRails = new ArrayList<>();


    private boolean entitySelected;

    public CityManager() {

        tempRails = new ArrayList<>();
        cities = new ArrayList<>();
        railRoads = new HashMap<>();
        allRails = new ArrayList<>();

        entitySelected = false;

        createCities();
        createRailRoads();
    }

   /* private void createCities() {
        for(Cities cityTemplate : Cities.values()) {
            City city = new City(cityTemplate.getX(), cityTemplate.getY(), cityTemplate.getName());
            cities.add(city);
        }
        for(City city : cities) {
            city.addAdjacent(cities);
        }
    }*/

    private void createCities() {
        cities.addAll(CityLoader.loadCities("Cities",cities));
        System.out.println(cities.size());
        for (City city : cities) {
            System.out.println(city.getName());
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

    public void createRail(float x, float y) {
        Rail rail = new Rail(x,y,0);
        tempRails.add(rail);
    }
}
