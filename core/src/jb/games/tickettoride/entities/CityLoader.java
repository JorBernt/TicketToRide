package jb.games.tickettoride.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.List;

public class CityLoader {
    private static Json json = new Json();

    public static List<City> loadCities(String name, List<City> currentCities) {

        Gdx.files.local("cities/").file().mkdirs();
        FileHandle file = Gdx.files.local("cities/" + name + ".json");

        if(file.exists()) {
            CityReader[] loadedCities = json.fromJson(CityReader[].class, file.readString());
            List<City> cities = new ArrayList<>();
            for(CityReader city : loadedCities) {
                cities.add(createCity(city));
            }
            return cities;
        }
        else {
            saveCities(name, currentCities);
            return currentCities;
        }
    }

    public static void saveCities(String name, List<City> cities) {
        List<CityReader> citySaveList = new ArrayList<>();
        for(City city : cities) {
            citySaveList.add(city.getSave());
        }

        Gdx.files.local("cities/").file().mkdirs();
        FileHandle file = Gdx.files.local("cities/" + name + ".json");
        file.writeString(json.prettyPrint(citySaveList), false);
    }

    private static City createCity(CityReader cityReader) {
        City city = new City(cityReader.getX(), cityReader.getY(),cityReader.getName());
        return city;
    }
}
