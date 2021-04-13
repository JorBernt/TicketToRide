package jb.games.tickettoride.entities;

import java.util.HashMap;
import java.util.Map;

public enum RailRoadLength {
    LONDON("BERLIN 2"),
    BERLIN("LONDON 2","MOSCOW 4","PARIS 5"),
    MOSCOW("BERLIN 4","PARIS 6"),
    PARIS("MOSCOW 6", "BERLIN 5");

    private final Map<String, Integer> railDistance;

    RailRoadLength(String... adjacent) {
        railDistance = new HashMap<>();
        for(String dist : adjacent) {
            String[] parse = dist.split(" ");
            railDistance.put(parse[0], Integer.parseInt(parse[1]));
        }
    }

    public int getRailDistance(String destination) {
        return railDistance.get(destination);
    }
}
