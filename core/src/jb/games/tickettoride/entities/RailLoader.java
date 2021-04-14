package jb.games.tickettoride.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.List;

public class RailLoader {
    private static Json json = new Json();

    public static List<RailRoad> loadRails(String name, List<RailRoad> currentRailRoads, CityManager cityManager) {
        Gdx.files.local("rails/").file().mkdirs();
        FileHandle file = Gdx.files.local("rails/" + name + ".json");

        if(file.exists()) {
            RailReader[] loadedRails = json.fromJson(RailReader[].class, file.readString());
            List<RailRoad> railRoads = new ArrayList<>();
            for(RailReader railRoad : loadedRails) {
                railRoads.add(createRailRoad(railRoad));
            }
            return railRoads;
        }
        else {
            saveRails(name, currentRailRoads);
            return currentRailRoads;
        }
    }

    public static void saveRails(String name, List<RailRoad> railRoads) {
        List<RailReader> railSaveList = new ArrayList<>();
        for(RailRoad railRoad : railRoads) {
            railSaveList.add(railRoad.getSave());
        }

        Gdx.files.local("rail/").file().mkdirs();
        FileHandle file = Gdx.files.local("rail/" + name + ".json");
        file.writeString(json.prettyPrint(railSaveList), false);
    }

    private static RailRoad createRailRoad(RailReader railReader) {
        //RailRoad railRoad = new RailRoad(, railReader.getY(),railReader.getRails());
        return null;
    }
}
